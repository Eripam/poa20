var groupColumn = 0;
var options2 = {style: "currency", currency: "USD"};
var t = $('#example').DataTable({
    "order": [[groupColumn, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]]
}).clear().draw();

POAPLAN(t);

//Listado del POA Director
function POAPLAN(t) {
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListaServiciosProf",
        type: 'POST',
        data: {tipo: 2},
        dataType: 'json',
        dataSrc: ''
    })
            .done(function (response) {
                $.each(response, function () {
                    var addID, tipo, div, inicio, fin;
                    if (this.actividad_id === 1) {
                        tipo = "Horas Clase";
                    } else if (this.actividad_id === 2) {
                        tipo = "Honorario Mensual";
                    } else {
                        tipo = "Honorario Fijo";
                    }
                    
                     if (this.fecha_inicio == null || this.fecha_inicio == 'undefined') {
                        inicio = "---";
                    } else {
                        inicio = this.fecha_inicio;
                    }
                    if (this.fecha_fin == null || this.fecha_fin == 'undefined') {
                        fin = "---";
                    } else {
                        fin = this.fecha_fin;
                    }
                    
                    if(this.unidad_id!=1){
                        div = '<input type="checkbox" name="reqfinmod" id="reqfinmod">';
                    }else{
                        div="";
                    }
                    addID = t.row.add(['<div style="text-align:justify;">' + this.autoridades_cedula + '</div>', '<div>' + this.autoridades_nombre + ' ' + this.actividad_responsable + '</div>', '<div class="text-justify">' + this.autoridades_cargo + '</div>', '<div style="text-align:center;">' + inicio + '</div>', '<div style="text-align:center;">' + fin + '</div>',
                        '<div>' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + tipo + '</div>', '<div style="text-align:center;">' + this.usuario_cedula + '</div>', '<div style="text-align:center;">' + this.usuario_nombre + ' ' + this.usuario_titulo + '</div>',
                        '<div style="text-align:center;">' + this.solicitud_codigo + '-STP-' + this.req_anio + '</div>', '<div style="text-align:center;">' + this.tc_nombre + '</div>', '<div class="dat">' + this.estado_nombre + '</div>', '<div class="dat" data-id="' + this.req_id + '" data-cedula="' + this.autoridades_cedula + '" data-nombre="' + this.autoridades_nombre + '" data-apellido="' + this.actividad_responsable + '" data-cedestudiante="' + this.usuario_cedula + '" data-nombreestu="' + this.usuario_nombre + '" \n\
                            data-apellidoestu="' + this.usuario_titulo + '" data-total="' + this.req_costo_total + '" data-codigosol="' + this.solicitud_codigo + '" data-spid="' + this.req_id + '" data-reqid="' + this.solicitud_id + '" data-cargo="' + this.autoridades_cargo + '" data-anio="' + this.req_anio + '" data-tipo="' + tipo + '" data-reqsiniva="'+this.req_costo_sin_iva+'" data-iva="'+this.req_iva+'">' + div + '</div>']).draw(false);
                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                    theNode.setAttribute('id', this.act_id);
                });
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('error en el controlador');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#modalGuardarJustEnv').on('click', function () {
    var ag = $('input[name=agsol]').val();
    var rector = $('select[name=rectoring]').val();
    var table = $('#example').DataTable();
    var cargo = $('#descripcions').val();
    var anio = $('#selectanio').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(data['reqid']);
        rem.push(data['siniva']);
        rem.push(data['total']);
        rem.push(data['iva']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (cargo === "") {
        alert("Debe ingresar el centro de costo.");
    } else {
        var formData = new FormData();
        formData.append('req', req);
        formData.append('ag', ag);
        formData.append('rector', rector);
        formData.append('cargo', cargo);
        formData.append('anio', anio);
        formData.append('cedulausuario', $('#cedulaProyecto').val());
        $.ajax({
            url: "../solicitud?accion=IngresarSolicitudNPTH",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        table
                                .clear()
                                .draw();
                        POAPLAN(t);
                        $('#enviarReq').modal('hide');
                    } else {
                        alert(response);
                    }
                })
                .fail(function () {
                    console.log('error en el controlador');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
});

$('.modalEnviarReqUnid').on('click', function () {
    $('#listaRequerimientosSolReq').empty();
    var data = $(this).data();
    var array = [];
    var sum = 0, sumto = 0, re = [];
    $('table#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            var rem = [];
            rem.push(data.id);
            re.push(rem);
            art = ({'id': data.id,
                'nombre': data.nombre,
                'apellido': data.apellido,
                'cedula': data.cedula,
                'cedestudiante': data.cedestudiante,
                'nombreestu': data.nombreestu,
                'apellidoestu': data.apellidoestu,
                'total': data.total,
                'codigosol': data.codigosol,
                'spid': data.spid,
                'reqid': data.reqid,
                'cargo': data.cargo,
                'anio': data.anio,
                'siniva':data.reqsiniva,
                'iva':data.iva,
                'tipo': data.tipo});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        $('#listaRequerimientosSolReq').append('<tr><td>' + array[i].cedula + '</td>\n\
                        <td>' + array[i].nombre + ' ' + array[i].apellido + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].total) + '</td>\n\
                        <td>' + array[i].cedula + '</td>\n\
                        <td>' + array[i].nombreestu + ' ' + array[i].apellidoestu + '</td>\n\
                        <td>' + array[i].codigosol + '-STP-' + array[i].anio + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" data-reqanio="' + array[i].anio + '" data-aniovig="' + $('#selectanio').val() + '" data-reqid="'+array[i].reqid+'" data-siniva="'+array[i].siniva+'" data-total="'+array[i].total+'" data-iva="'+array[i].iva+'" checked="checked"></td>\n\
                    </div>');
        listaRequ(array[i].id);
        sum = sum + array[i].total;
    }
    var req = JSON.stringify(re);
    $('#listaRequerimientosSolReq').append('<tr><td colspan="9" class="text-center">TOTAL:' + new Intl.NumberFormat("US", options2).format(sum) + '</td></tr>');
    $('#enviarid').val(data['id']);
    $('#enviarReq').modal();
});


//Listado del POA Director
function listaRequ(id) {
    $.ajax({
        url: "../actividadReq?accion=ListarReqServicios",
        type: 'POST',
        data: {ag: $('#agjustificativo').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    $('#select' + id).append('<option value="' + this.req_id + '">' + this.req_nombre + '</option>')
                });
                $('#select' + id).selectpicker('refresh');
            })
            .fail(function () {
                console.log('error en el controlador');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}