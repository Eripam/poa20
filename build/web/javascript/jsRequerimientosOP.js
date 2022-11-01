var groupColumn = 0;
var options2 = {style: "currency", currency: "USD"};
var t = $('#example').DataTable({
    "columnDefs": [
        {"visible": false, "targets": groupColumn}],
    "order": [[groupColumn, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]],
    "drawCallback": function (settings) {
        var api = this.api();
        var rows = api.rows({page: 'current'}).nodes();
        var last = null;
        api.column(groupColumn, {page: 'current'}).data().each(function (group, i) {
            if (last !== group) {
                $(rows).eq(i).before(
                        '<tr class="group"><td colspan="10">' + group + '</td></tr>'
                        );
                last = group;
            }
        });
    }
}).clear().draw();

$('#example tbody').on('click', 'tr.group', function () {
    var currentOrder = t.order()[0];
    if (currentOrder[0] === groupColumn && currentOrder[1] === 'asc') {
        t.order([groupColumn, 'desc']).draw();
    } else {
        t.order([groupColumn, 'asc']).draw();
    }
});

POAPLAN(t);

//Listado del POA Director
function POAPLAN(t) {
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListRequeUnReqOP",
        type: 'POST',
        data: {area: $('#agjustificativo').val(), tipo: $('#tagjustificativo').val(), anio: $('#selectanio').val()},
        dataType: 'json',
        dataSrc: ''
    })
            .done(function (response) {
                $.each(response, function () {
                    var addID, tipo, observacion, estado, div;
                    if (this.solestado_observacion === "null" || this.solestado_observacion == null) {
                        observacion = '----';
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    if (this.actividad_nombre === "null" || this.actividad_nombre == null) {
                        estado = "-----";
//                        div = '<input type="checkbox" name="reqfinmod" id="reqfinmod">';
                    } else {
                        estado = this.actividad_nombre;
//                        if (this.solicitud_id > 0 || this.solicitud_estado === 47) {
//                            div = '<input type="checkbox" name="reqfinmod" id="reqfinmod">';
//                        } else {
//                            div = ' ';
//                        }
                    }
                    div = '<input type="checkbox" name="reqfinmod" id="reqfinmod">';
                    if (this.tc_id === 1) {
                        tipo = 'OBLIGACIONES PENDIENTES';
                    } else {
                        tipo = 'COMPROMETIDOS NO DEVENGADOS';
                    }
                    addID = t.row.add(['<div style="text-align:justify;">' + this.ag_nombre + '</div>', '<div>' + tipo + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div class="text-justify">' + this.proyecto_nombre + '</div>',
                        '<div>' + this.req_nombre + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                        '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div>' + this.presupuesto_fuente + '</div>', '<div>' + estado + '</div>', '<div>' + observacion + '</div>', '<div class="dat" data-id="' + this.req_id + '" data-monto="' + this.req_costo_unitario + '" data-anticipo="' + this.req_costo_total + '" data-iva="' + this.req_costo_sin_iva + '" \n\
                            data-nombre="' + this.req_nombre + '" data-oei="' + this.perspectiva_id + '" data-prproyecto="' + this.presupuesto_proyecto + '" data-fuente="' + this.presupuesto_fuente + '" data-tipo="' + this.tc_id + '">' + div + '</div>']).draw(false);
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

$('.main').on('click', '.row .content .pestania #btnGenerarJ', function () {
    $('#listaProyectos').empty();
    $('#reqsol').empty();
    $('#tcosto').empty();
    $('#tcostoiva').empty();
    $('input[name=descripcions]').val("");
    $('select[name=rectoring]').val(0);
    var array = [];
    var sum = 0;
    var options2 = {style: "currency", currency: "USD"};
    $('table#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'id': data.id,
                'nombre': data.nombre,
                'monto': data.monto,
                'iva': data.iva,
                'anticipo': data.anticipo,
                'oei': data.oei,
                'fuente': data.fuente,
                'tipo': data.tipo,
                'prproyecto': data.prproyecto});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var tipo, monto, iva, anticipo;
        if (array[i].tipo === 1) {
            tipo = 'OBLIGACIONES PENDIENTES';
        } else {
            tipo = 'COMPROMETIDOS NO DEVENGADOS';
        }
        monto = '<input type="text" size="40" class="form-control" name="monto' + array[i].id + '" class="input" value="' + array[i].monto + '">';
        iva = '<input type="text" size="40" class="form-control" name="iva' + array[i].id + '" class="input" value="' + array[i].iva + '">';
        anticipo = '<input type="text" size="40" class="form-control" name="anticipo' + array[i].id + '" class="input" value="' + array[i].anticipo + '">';
        $('#listaProyectos').append('<tr><td>' + tipo + '</td><td>' + array[i].oei + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td>' + monto + '</td>\n\
                        <td>' + iva + '</td>\n\
                        <td>' + anticipo + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-proyecto="' + array[i].prproyecto + '" data-iva="' + array[i].iva + '"></td>\n\
                    </tr>');
        sum = sum + array[i].monto + array[i].iva + array[i].anticipo;
    }
    $('#listaProyectos').append('<tr><td colspan="8" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sum) + '</td>');
    $('#generarJ').modal();
});


//Ingresar compra
$('#modalGuardarJust').on('click', function () {
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
        var m = $('input[name=monto' + $(this).val() + ']').val();
        var mo, iv, an;
        if (m == null || m === null || m === 'undefined') {
            mo = 0;
        } else {
            mo = m;
        }
        var i = $('input[name=iva' + $(this).val() + ']').val();
        if (i == null || i === null || i === 'undefined') {
            iv = 0;
        } else {
            iv = i;
        }
        var a = $('input[name=anticipo' + $(this).val() + ']').val();
        if (a == null || a === null || a === 'undefined') {
            an = 0;
        } else {
            an = a;
        }
        rem.push(mo);
        rem.push(iv);
        rem.push(an);
        rem.push(data['proyecto']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (cargo === "") {
        alert("Debe ingresar la descripci\u00f3n.");
    } else {
        var formData = new FormData();
        formData.append('req', req);
        formData.append('ag', ag);
        formData.append('rector', rector);
        formData.append('cargo', cargo);
        formData.append('anio', anio);
        formData.append('cedulausuario', $('#cedulaProyecto').val());
        $.ajax({
            url: "../solicitud?accion=IngresarSolicitudOPC",
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
                        $('#generarJ').modal('hide');
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
    if (data['id'] === 28) {
        $('#exampleModalLabelE').html('Requerimientos al DTIC');
    } else if (data['id'] === 30) {
        $('#exampleModalLabelE').html('Requerimientos a DIRCOM');
    } else if (data['id'] === 29) {
        $('#exampleModalLabelE').html('Requerimientos a Bodega');
    }

    var array = [];
    var sum = 0, sumto = 0;
    $('table#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'id': data.id,
                'nombre': data.nombre,
                'cantidad': data.cantidad,
                'costou': data.costou,
                'costoiva': data.costoiva,
                'costosin': data.costosin,
                'oei': data.oei,
                'iva': data.iva,
                'unidad': data.unidad});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var iva;
        if (array[i].iva === 1) {
            iva = array[i].costosin * 1.12;
        } else {
            iva = array[i].costosin;
        }
        $('#listaRequerimientosSolReq').append('<tr><td>' + array[i].oei + '</td>\n\
                        <td><input type="text" class="form-control" name="canenv' + array[i].id + '" value="' + array[i].cantidad + '"><input type="hidden" value="' + array[i].cantidad + '" name="cantenvoc' + array[i].id + '"></td>\n\
                        <td>' + array[i].unidad + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td><input type="text" class="form-control" name="cosenv' + array[i].id + '" value="' + array[i].costou + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costosin) + '<input type="hidden" value="' + array[i].costosin + '" name="totalenv' + array[i].id + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" data-iva="' + array[i].iva + '" checked="checked"></td>\n\
                    </div>');
        sum = sum + array[i].costosin;
    }
    $('#listaRequerimientosSolReq').append('<tr><td colspan="9" class="text-center">TOTAL:' + new Intl.NumberFormat("US", options2).format(sum) + '</td></tr>');
    $('#enviarid').val(data['id']);
    $('#enviarReq').modal();
});

$('#modalGuardarJustEnv').on('click', function () {
    var usuario = $('input[name=cedulaProyecto]').val();
    var enviar = $('input[name=enviarid]').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push($('input[name=canenv' + $(this).val() + ']').val());
        rem.push($('input[name=cantenvoc' + $(this).val() + ']').val());
        rem.push($('input[name=cosenv' + $(this).val() + ']').val());
        rem.push($('input[name=totalenv' + $(this).val() + ']').val());
        rem.push(data['iva']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    var formData = new FormData();
    formData.append('usuario', usuario);
    formData.append('req', req);
    formData.append('enviar', enviar);
    $.ajax({
        url: "../solicitud?accion=EnviarRequerimientos",
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
                'monto': data.monto,
                'anticipo': data.anticipo,
                'oei': data.oei,
                'iva': data.iva});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        $('#listaRequerimientosSolReq').append('<tr><td>' + array[i].oei + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].monto) + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].anticipo) + '<input type="hidden" value="' + array[i].monto + '" name="montoip' + array[i].id + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].iva) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" data-iva="' + array[i].iva + '" checked="checked"></td>\n\
                    </div>');
        sum = sum + array[i].monto + array[i].iva;
    }
    var req = JSON.stringify(re);
    $('#listaRequerimientosSolReq').append('<tr><td colspan="9" class="text-center">TOTAL:' + new Intl.NumberFormat("US", options2).format(sum) + '</td></tr>');
    $('#enviarid').val(data['id']);
    $('#BtnEnviarTh').html('<a href="pServiciosProfesionalesOP.jsp?id=' + req + '" class="btn bton" id="modalGuardarJustEnv">GUARDAR</a>\n\
    <a href="#" class="btn bton" data-dismiss="modal">CANCELAR</a>');
    $('#enviarReq').modal();
});