var groupColumn = 0;
var options2 = {style: "currency", currency: "USD"};
$(document).ready(function () {
    listaTiempo();
    POAPLAN(t);
});

function listaTiempo() {
    var time;
    $.ajax({
        url: "../proyecto?accion=VerificacionEnviosEje",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                time = response.tiempo;
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    if (!time) {
        $('.modalEnviarReqUnid').addClass("d-none");
    } else {
        $('.modalEnviarReqUnid').removeClass("d-none");
    }
}
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

//Listado del POA Director
function POAPLAN(t) {
    listaTiempo();
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListRequeUnReq",
        type: 'POST',
        data: {area: $('#agjustificativo').val(), tipo: $('#tagjustificativo').val(), anio: $('#selectanio').val()},
        dataType: 'json',
        dataSrc: ''
    })
            .done(function (response) {
                $.each(response, function () {
                    var addID, observacion, iva, estado;
                    if (this.solestado_observacion === "null" || this.solestado_observacion == null) {
                        observacion = '----';
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    if (this.req_iva === 1) {
                        iva = 'SI';
                    } else {
                        iva = 'NO';
                    }
                    if (this.actividad_nombre === "null" || this.actividad_nombre == null) {
                        estado = "-----";
                    } else {
                        estado = this.actividad_nombre;
                    }
                    if (this.solicitud_estado === 28 || this.solicitud_estado === 29 || this.solicitud_estado === 30 || this.solicitud_estado === 50 || this.solicitud_estado === 53) {
                        addID = t.row.add(['<div style="text-align:justify;">' + this.ag_nombre + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div class="text-justify">' + this.proyecto_nombre + '</div>',
                            '<div>' + this.req_nombre + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                            '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + observacion + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat"></div>']).draw(false);
                        var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                        theNode.setAttribute('id', this.act_id);
                        $(theNode).css({"background": "#B8BDB5"});
                    } else if (this.solicitud_estado === 3) {
                        addID = t.row.add(['<div style="text-align:justify;">' + this.ag_nombre + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div class="text-justify">' + this.proyecto_nombre + '</div>',
                            '<div>' + this.req_nombre + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                            '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + observacion + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat" data-id="' + this.req_id + '" data-cantidad="' + this.req_cantidad + '" data-costou="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_total + '" data-costosin="' + this.req_costo_sin_iva + '" \n\
                            data-nombre="' + this.req_nombre + '" data-oei="' + this.perspectiva_id + '" data-unidad="' + this.unidad_nombre + '" data-iva="' + this.req_iva + '" data-prproyecto="' + this.presupuesto_proyecto + '"><input type="checkbox" name="reqfinmod" id="reqfinmod"></div>']).draw(false);
                        var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                        theNode.setAttribute('id', this.act_id);
                        $(theNode).css({"background": "#F9BFC1"});
                    } else {
                        addID = t.row.add(['<div style="text-align:justify;">' + this.ag_nombre + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div class="text-justify">' + this.proyecto_nombre + '</div>',
                            '<div>' + this.req_nombre + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                            '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + observacion + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat" data-id="' + this.req_id + '" data-cantidad="' + this.req_cantidad + '" data-costou="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_total + '" data-costosin="' + this.req_costo_sin_iva + '" \n\
                            data-nombre="' + this.req_nombre + '" data-oei="' + this.perspectiva_id + '" data-unidad="' + this.unidad_nombre + '" data-iva="' + this.req_iva + '" data-prproyecto="' + this.presupuesto_proyecto + '"><input type="checkbox" name="reqfinmod" id="reqfinmod"></div>']).draw(false);
                        var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                        theNode.setAttribute('id', this.act_id);
                    }
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
    $('input[name=centro]').val("");
    $('select[name=rectoring]').val(0);
    $('#cedulacustodio').val("");
    $('#nombrecustodio').val("");
    $('#cargocustodio').val("");
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
                'cantidad': data.cantidad,
                'costou': data.costou,
                'costoiva': data.costoiva,
                'costosin': data.costosin,
                'oei': data.oei,
                'iva': data.iva,
                'prproyecto': data.prproyecto,
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
        $('#listaProyectos').append('<tr><td>' + array[i].oei + '</td>\n\
                        <td><input type="text" class="form-control" name="can' + array[i].id + '" value="' + array[i].cantidad + '"></td>\n\
                        <td>' + array[i].unidad + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td><input type="text" class="form-control" name="costu' + array[i].id + '" class="input" value="' + array[i].costou + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costosin) + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-proyecto="' + array[i].prproyecto + '" data-iva="' + array[i].iva + '"></td>\n\
                    </tr>');
        sum = sum + array[i].costosin;
    }
    $('#listaProyectos').append('<tr><td colspan="8" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sum) + '</td>');
    $('#generarJ').modal();
});


//Ingresar compra
$('#modalGuardarJust').on('click', function () {
    var centrocosto = $('input[name=centro]').val();
    var ag = $('input[name=agsol]').val();
    var rector = $('select[name=rectoring]').val();
    var table = $('#example').DataTable();
    var cedula = $('#cedulacustodio').val();
    var nombre = $('#nombrecustodio').val();
    var cargo = $('#cargocustodio').val();
    var anio = $('#selectanio').val()
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(Math.round($('input[name=can' + $(this).val() + ']').val() * 100) / 100);
        rem.push($('input[name=nomb' + $(this).val() + ']').val());
        rem.push(Math.round($('input[name=costu' + $(this).val() + ']').val() * 100) / 100);
        rem.push(data['proyecto']);
        rem.push(data['iva']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (centrocosto === "") {
        alert("Debe ingresar el centro de costo.");
    } else if (cedula === "") {
        alert("Debe ingresar el numero de cedula del custodio.");
    } else if (nombre === "") {
        alert("Debe ingresar el nombre del custodio.");
    } else if (cargo === "") {
        alert("Debe ingresar el cargo del custodio.");
    } else {
        var formData = new FormData();
        formData.append('centro', centrocosto);
        formData.append('req', req);
        formData.append('ag', ag);
        formData.append('rector', rector);
        formData.append('cedula', cedula);
        formData.append('nombre', nombre);
        formData.append('cargo', cargo);
        formData.append('anio', anio);
        formData.append('cedulausuario', $('#cedulaProyecto').val());
        $.ajax({
            url: "../solicitud?accion=IngresarSolicitud",
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
    } else if (data['id'] === 50) {
        $('#exampleModalLabelE').html('Requerimientos a Dirección Administrativa');
    } else if (data['id'] === 53) {
        $('#exampleModalLabelE').html('Requerimientos a DIM');
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