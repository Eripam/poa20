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

function listaFechaActual(id) {
    var f = sumarDias(new Date(), 4), fi;
    var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
    $.ajax({
        url: "../proyecto?accion=ListarFecha",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (diasSemana[f.getDay()] === "Sabado" || diasSemana[f.getDay()] === "Domingo" || diasSemana[f.getDay()] === "Lunes" || diasSemana[f.getDay()] === "Martes") {
                    f = sumarDias(new Date(), 6);
                    fi = f.getDate();
                } else if (diasSemana[f.getDay()] === "Miercoles") {
                    f = sumarDias(new Date(), 5);
                    fi = f.getDate();
                } else {
                    fi = fi = f.getDate();
                }
                $('#listaRequerimientosSolReq').children('tr').children('td').children('#fechafin'+id).datepicker({
                    //$('table').children('tbody#listaRequerimientosSolReq').children('tr td').children('#fechafin').datepicker({
                    minDate: fi + "/" + (f.getMonth() + 1) + "/" + response,
                    maxDate: "31/12/" + response
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function sumarDias(fecha, dias) {
    fecha.setDate(fecha.getDate() + dias);
    return fecha;
}


//Listado del POA Director
function POAPLAN(t) {
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListaServiciosProf",
        type: 'POST',
        data: {area: $('#agjustificativo').val(), tipo: 1},
        dataType: 'json',
        dataSrc: ''
    })
            .done(function (response) {
                $.each(response, function () {
                    var addID, tipo, estado, div, inicio, fin;
                    if (this.actividad_id === 1) {
                        tipo = "Horas Clase";
                    } else if (this.actividad_id === 2) {
                        tipo = "Honorario Mensual";
                    } else {
                        tipo = "Honorario Fijo";
                    }

                    if (this.ae_tiempo == 48) {
                        estado = this.estado_nombre;
                        div = "";
                    } else {
                        div = '<input type="checkbox" name="reqfinmod" id="reqfinmod">';
                        estado = "---";
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
                    addID = t.row.add(['<div style="text-align:justify;">' + this.autoridades_cedula + '</div>', '<div>' + this.autoridades_nombre + ' ' + this.actividad_responsable + '</div>', '<div class="text-justify">' + this.autoridades_cargo + '</div>', '<div style="text-align:center;">' + inicio + '</div>', '<div style="text-align:center;">' + fin + '</div>',
                        '<div>' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + tipo + '</div>', '<div style="text-align:center;">' + this.usuario_cedula + '</div>', '<div style="text-align:center;">' + this.usuario_nombre + ' ' + this.usuario_titulo + '</div>',
                        '<div style="text-align:center;">' + this.solicitud_codigo + '-STP-' + this.req_anio + '</div>', '<div style="text-align:center;">' + this.tc_nombre + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat" data-id="' + this.req_id + '" data-cedula="' + this.autoridades_cedula + '" data-nombre="' + this.autoridades_nombre + '" data-apellido="' + this.actividad_responsable + '" data-cedestudiante="' + this.usuario_cedula + '" data-nombreestu="' + this.usuario_nombre + '" \n\
                            data-apellidoestu="' + this.usuario_titulo + '" data-total="' + this.req_costo_total + '" data-codigosol="' + this.solicitud_codigo + '" data-reqid="' + this.solicitud_id + '" data-cargo="' + this.autoridades_cargo + '" data-anio="' + this.req_anio + '" data-tipo="' + tipo + '" data-idtipo="'+this.actividad_id+'" data-fechai="'+this.fecha_inicio+'" data-fechaf="'+this.fecha_fin+'" data-valorsiva="'+this.req_costo_sin_iva+'">' + div + '</div>']).draw(false);
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
    var usuario = $('input[name=cedulaProyecto]').val();
    var enviar = 48;
    var re = [];
    var sel;
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(data['reqanio']);
        rem.push(data['aniovig']);
        rem.push(sel);
        rem.push($('input[name=fechafin'+$(this).val()+']').val());
        rem.push(data['cedestudiante']);
        rem.push(data['nombreestu']);
        rem.push(data['apellidoestu']);
        rem.push(data['cargo']);
        rem.push(data['total']);
        rem.push(data['cedula']);
        rem.push(data['nombre']);
        rem.push(data['apellido']);
        rem.push(data['codigosol']);
        rem.push(data['idtipo']);
        rem.push(data['fechai']);
        rem.push(data['reqid']);
        rem.push(data['valorsiva']);
        rem.push($('select[name=select'+$(this).val()+']').val());
        re.push(rem);
    });
    var req = JSON.stringify(re);
    var formData = new FormData();
    formData.append('usuario', usuario);
    formData.append('req', req);
    formData.append('estado', enviar);
    $.ajax({
        url: "../solicitud?accion=EnviarServiciosProf",
        type: 'POST',
        data: formData,
        dataType: 'json',
        cache: false,
        processData: false,
        contentType: false
    })
            .done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
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
                'reqid': data.reqid,
                'cargo': data.cargo,
                'anio': data.anio,
                'tipo': data.tipo,
                'tipoid':data.idtipo,
                'fechai':data.fechai,
                'fechaf':data.fechaf,
                'valorsiva':data.valorsiva});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        if (array[i].anio == $('#selectanio').val()) {
            $('#listaRequerimientosSolReq').append('<tr><td>' + array[i].cedula + '</td>\n\
                        <td>' + array[i].nombre + ' ' + array[i].apellido + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].total) + '</td>\n\
                        <td>' + array[i].cedula + '</td>\n\
                        <td>' + array[i].nombreestu + ' ' + array[i].apellidoestu + '</td>\n\
                        <td>' + array[i].codigosol + '-STP-' + array[i].anio + '</td>\n\
                        <td><input type="text" class="form-control" name="fechafin'+array[i].id+'" id="fechafin'+array[i].id+'" readonly></td>\n\
                        <td></td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" data-reqanio="' + array[i].anio + '" data-aniovig="' + $('#selectanio').val() + '" checked="checked"></td>\n\
                    </div>');
        } else {
            $('#listaRequerimientosSolReq').append('<tr><td>' + array[i].cedula + '</td>\n\
                        <td>' + array[i].nombre + ' ' + array[i].apellido + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].total) + '</td>\n\
                        <td>' + array[i].cedula + '</td>\n\
                        <td>' + array[i].nombreestu + ' ' + array[i].apellidoestu + '</td>\n\
                        <td>' + array[i].codigosol + '-STP-' + array[i].anio + '</td>\n\
                        <td><input type="text" class="form-control" name="fechafin'+array[i].id+'" id="fechafin'+array[i].id+'" readonly></td>\n\
                        <td id="seprof' + array[i].id + '"><select class="selectpicker" data-live-search="true" id="select' + array[i].id + '" name="select' + array[i].id + '"></select></td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" data-reqanio="' + array[i].anio + '" data-aniovig="' + $('#selectanio').val() + '" data-nombre="'+array[i].nombre+'" data-apellido="'+array[i].apellido+'"\n\
                        data-cedula="'+array[i].cedula+'" data-cedestudiante="'+array[i].cedestudiante+'" data-nombreestu="'+array[i].nombreestu+'" data-apellidoestu?"'+array[i].apellidoestu+'" data-total="'+array[i].total+'" data-codigosol="'+array[i].codigosol+'" \n\
                        data-reqid="'+array[i].reqid+'" data-cargo="'+array[i].cargo+'" data-tipoid="'+array[i].idtipo+'" data-fechai="'+array[i].fechai+'" data-fechaf="'+array[i].fechaf+'" data-valorsiva="'+array[i].valorsiva+'" checked="checked"></td>\n\
                    </div>');
        }
        listaFechaActual(array[i].id);
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