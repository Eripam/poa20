var groupColumn = 0;
var estado, ag = $('#agjustificativo').val();
if (ag === '68' || ag === '99') {
    estado = 28;
} else if (ag === '54') {
    estado = 30;
} else if (ag === '60' || ag === '105') {
    estado = 29;
} else if (ag === '57') {
    estado = 50;
} else if (ag === '69') {
    estado = 53;
} else if (ag === '107') {
    estado = 54;
}
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
    var estado, ag = $('#agjustificativo').val();
    if (ag === '68' || ag === '99') {
        estado = 28;
    } else if (ag === '54') {
        estado = 30;
    } else if (ag === '60' || ag === '105') {
        estado = 29;
    } else if (ag === '57') {
        estado = 50;
    } else if (ag === '69') {
        estado = 53;
    } else if (ag === '107') {
        estado = 54;
    }
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListRequeUnionReq",
        type: 'POST',
        data: {estado: estado, anio: $('#selectanio').val()},
        dataType: 'json',
        dataSrc: ''
    })
            .done(function (response) {
                $.each(response, function () {
                    var addID, total, iva;
                    if (this.req_iva === 1) {
                        total = this.req_costo_sin_iva * 1.12;
                    } else {
                        total = this.req_costo_sin_iva;
                    }
                    if (this.req_iva === 1) {
                        iva = 'SI';
                    } else {
                        iva = 'NO';
                    }

                    addID = t.row.add(['<div style="text-align:justify;">' + this.ag_nombre + '</div>', '<div style="text-align:justify;">OEI-0' + this.perspectiva_id + '</div>', '<div style="text-align:justify;">' + this.req_nombre + '</div>',
                        '<div style="text-align:justify;">' + this.req_descripcion + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(total) + '</div>',
                        '<div style="text-align:center;" data-id="' + this.req_id + '" title="Regresar" id="regresarReq"><i class="fas fa-reply"></i></div>', '<div class="dat" data-id="' + this.req_id + '" data-cantidad="' + this.req_cantidad + '" data-costou="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_total + '" data-costosin="' + this.req_costo_sin_iva + '" data-nombre="' + this.req_nombre + '" data-oei="' + this.perspectiva_id + '" data-unidadid="' + this.unidad_id + '" data-unidad="' + this.unidad_nombre + '" data-iva="' + this.req_iva + '">\n\
                        <input type="checkbox" name="reqfinmod" id="reqfinmod"></div>']).draw(false);
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

//Regresar requerimientos
$('#example').on('click', 'tbody tr td #regresarReq', function () {
    var data = $(this).data();
    $('#idreqregresar').val(data['id']);
    $('#regresarModal').modal();
});

$('#modalEnvRegresar').on('click', function () {
    var observacion = $('#observacionreg').val();
    var table = $('#example').DataTable();
    if (observacion === "") {
        alert("Debe ingresar una observaci\xf3n");
    } else {
        $.ajax({
            url: "../solicitud?accion=EnviarRequerimientosReg",
            type: 'POST',
            data: {req: $('#idreqregresar').val(), observacion: observacion, usuario: $('#usuariorequerimiento').val(), estado: 3},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        table
                                .clear()
                                .draw();
                        POAPLAN(t);
                        $('#regresarModal').modal('hide');
                    }
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
});

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
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push($('input[name=can' + $(this).val() + ']').val());
        rem.push($('input[name=nomb' + $(this).val() + ']').val());
        rem.push($('input[name=costu' + $(this).val() + ']').val());
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
    }
});

$('.modalEnviarReqUnid').on('click', function () {
    $('#listaRequerimientosSolReq').empty();
    var data = $(this).data();
    if (data['id'] === 28 || data['id'] === 99) {
        $('#exampleModalLabelE').html('Requerimientos al DTIC');
    } else if (data['id'] === 30) {
        $('#exampleModalLabelE').html('Requerimientos a DIRCOM');
    } else if (data['id'] === 29 || data['id'] === 105) {
        $('#exampleModalLabelE').html('Requerimientos a Bodega');
    } else if (data['id'] === 50) {
        $('#exampleModalLabelE').html('Requerimientos a Dirección Administrativa');
    } else if (data['id'] === 53) {
        $('#exampleModalLabelE').html('Requerimientos a DIM');
    } else if (data['id'] === 54) {
        $('#exampleModalLabelE').html('Requerimientos a Control Quimico');
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

//Modal de unificacion
$('#modalUnificarReq').on('click', function () {
    $('#listaRequerimientosUnificaReq').empty();
    $('textarea[name=nombreunif]').val("");
    $('input[name=costounif]').val("");
    $('textarea[name=descunif]').val("");
    $('select[name=selunidaduni]').val("");
    var array = [];
    var sum = 0, sumto = 0, total = 0, sumci = 0;
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
                'unidad': data.unidad,
                'unidadid': data.unidadid});
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
        $('#listaRequerimientosUnificaReq').append('<tr><td class="text-center">' + array[i].oei + '</td>\n\
                        <td class="text-center">' + array[i].cantidad + '<input type="hidden" id="cantidadun' + array[i].id + '" name="cantidadun' + array[i].id + '" value="' + array[i].cantidad + '"></td>\n\
                        <td class="text-center">' + array[i].unidad + '<input type="hidden" id="oeiuni' + array[i].id + '" name="oeiuni' + array[i].id + '" value="' + array[i].oei + '"></td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(array[i].costou) + '<input type="hidden" id="costouun' + array[i].id + '" name="costouun' + array[i].id + '" value="' + array[i].costou + '"></td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(array[i].costosin) + '<input type="hidden" id="costosin' + array[i].id + '" name="costosin' + array[i].id + '" value="' + array[i].costosin + '"></td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td class="text-center"><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-iva="' + array[i].iva + '" data-unidad="' + array[i].unidadid + '"></td>\n\
                    </tr>');
        sumto = sumto + array[i].cantidad;
        sum = sum + array[i].costosin;
        total = total + iva;
        sumci = sumci + array[i].costou;
    }
    $('#cantidadunif').val(sumto);
    if ($('#agsol').val() === '54') {
        $('#costounif').val(sum);
        $('#costounif').prop('readonly', true);
    } else {
        $('#costounif').prop('readonly', false);
    }
    $('#listaRequerimientosUnificaReq').append('<tr><td colspan="4" class="text-center">TOTAL</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(sumci) + '</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(sum) + '</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
    $('#unificarR').modal();
});

//Ingresar Unificación
$('#modalGuardarJustUnific').on('click', function () {
    var nombre = $('textarea[name=nombreunif]').val();
    var costouni = $('input[name=costounif]').val();
    var descripcion = $('textarea[name=descunif]').val();
    var unidad = $('select[name=selunidaduni]').val();
    var anio = $('#selectanio').val();
    var table = $('#example').DataTable();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push($('input[name=costouun' + $(this).val() + ']').val());
        rem.push($('input[name=cantidadun' + $(this).val() + ']').val());
        rem.push($('input[name=oeiuni' + $(this).val() + ']').val());
        rem.push(data['iva']);
        rem.push(data['unidad']);
        rem.push($('input[name=costosin' + $(this).val() + ']').val());
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (nombre === "") {
        alert("Debe ingresar el nombre del requerimiento.");
    } else if (costouni === "" && $("input[name=partesid]:checked").val() === "undefined") {
        alert("Debe ingresar el costo unitario del requerimiento.");
    } else if (descripcion === "") {
        alert("Debe ingresar la descripcion del requerimiento.");
    } else {
        var formData = new FormData();
        formData.append('nombre', nombre);
        formData.append('req', req);
        formData.append('costouni', costouni);
        formData.append('descripcion', descripcion);
        formData.append('unidad', unidad);
        formData.append('estado', estado);
        formData.append('anio', anio);
        formData.append('partes', $("input[name=partesid]:checked").val());
        formData.append('cedula', $("#usuariorequerimiento").val());
        $.ajax({
            url: "../solicitud?accion=IngresarUnificacion",
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
                        $('#unificarR').modal('hide');
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