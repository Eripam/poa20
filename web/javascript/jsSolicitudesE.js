var options2 = {style: "currency", currency: "USD"};
var groupColumn = 0;
var t = $('#example').DataTable({
    "columnDefs": [
        {"visible": true, "targets": groupColumn}],
    "order": [[groupColumn, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]]
}).clear().draw();
var groupColumnM = 0;
var tm = $('#modalexample').DataTable({
    "columnDefs": [
        {"visible": false, "targets": groupColumnM}],
    "order": [[groupColumnM, 'asc']],
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
        api.column(groupColumnM, {page: 'current'}).data().each(function (group, i) {
            if (last !== group) {
                $(rows).eq(i).before(
                        '<tr class="group"><td colspan="10">' + group + '</td></tr>'
                        );
                last = group;
            }
        });
    }
}).clear().draw();
$('#modalexample tbody').on('click', 'tr.group', function () {
    var currentOrder = t.order()[0];
    if (currentOrder[0] === groupColumnM && currentOrder[1] === 'asc') {
        tm.order([groupColumnM, 'desc']).draw();
    } else {
        tm.order([groupColumnM, 'asc']).draw();
    }
});

//Listado del POA Director
function listaSolicitud(t) {
    var validacion;
    $.ajax({
        url: "../solicitud?accion=ValidacionEjecucion",
        type: 'POST',
        data: {anio: $('#selectanio').val()},
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                validacion = response;
            })
            .fail(function () {
                console.log('error en el controlador');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesNPE",
        data: {anio: $('#selectanio').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var addID, estado, codigo, observacion, tipousu = $('#tipoUsuario').val();
                    if (this.estado_nombre == null) {
                        estado = 'Realizando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.solestado_observacion == null || this.solestado_observacion == 'undefined' || this.solestado_observacion =='null') {
                        observacion = 'Sin observaciones'
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    if (this.solicitud_codigo === null || this.solicitud_codigo == null || this.solicitud_codigo == "") {
                        codigo = '----';
                    } else {
                        codigo = this.solicitud_codigo + '-STP-' + $('#selectanio').val();
                    }
                    var div;
                    if(!validacion && this.solicitud_estado !== 14 && this.solicitud_estado!==40){
                        div = '<a href="#" title="Visualizar Solicitud" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-observacion="' + this.actividad_nombre + '" data-tsid="' + this.tc_id + '" data-tsnombre="' + this.tc_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a><a href="pCertificacionPNP.jsp?id=' + this.solicitud_id + '&codigo=' + this.solicitud_codigo + '" title="Agregar certificaci\u00f3n"><i class="far fa-plus-square"></i></a>';
                    }else if (this.solicitud_estado === 1 && tipousu == 22) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-observacion="' + this.actividad_nombre + '" data-tsid="' + this.tc_id + '" data-tsnombre="' + this.tc_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-observacion="' + this.actividad_nombre + '" data-tsid="' + this.tc_id + '" data-tsnombre="' + this.tc_nombre + '"><i class="far fa-calendar-alt"></i></a><a href="#" title="Verificar recibido" class="icon_sol" id="verificarR" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-check-square"></i></a>';
                    } else if (this.solicitud_estado === 15) {
                        div = '<a href="#" title="Visualizar Solicitud" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-observacion="' + this.actividad_nombre + '" data-tsid="' + this.tc_id + '" data-tsnombre="' + this.tc_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a><a href="#" title="Verificar recibido" class="icon_sol" id="verificarRA" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-check-square"></i></a>\n\
                        <a href="#" title="Anular" id="anularSol" data-id="' + this.solicitud_id + '" data-codigo="' + this.solicitud_codigo + '"><i class="far fa-times-circle"></i></a>';
                    } else if (this.solicitud_estado === 34) {
                        div = '<a href="#" title="Visualizar Solicitud" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-observacion="' + this.actividad_nombre + '" data-tsid="' + this.tc_id + '" data-tsnombre="' + this.tc_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a><a href="pCertificacionPNP.jsp?id=' + this.solicitud_id + '&codigo=' + this.solicitud_codigo + '" title="Agregar certificaci\u00f3n"><i class="far fa-plus-square"></i></a>\n\
                        <a href="#" title="Anular" id="anularSol" data-id="' + this.solicitud_id + '" data-codigo="' + this.solicitud_codigo + '"><i class="far fa-times-circle"></i></a>';
                    } else if (this.solicitud_estado === 49) {
                        div = '<a href="#" title="Visualizar Solicitud" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-observacion="' + this.actividad_nombre + '" data-tsid="' + this.tc_id + '" data-tsnombre="' + this.tc_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a><a href="pCertificacionPNP.jsp?id=' + this.solicitud_id + '&codigo=' + this.solicitud_codigo + '" title="Agregar certificaci\u00f3n"><i class="far fa-plus-square"></i></a>';
                    } else {
                        div = '<a href="#" title="Visualizar Solicitud" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-observacion="' + this.actividad_nombre + '" data-tsid="' + this.tc_id + '" data-tsnombre="' + this.tc_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a>';
                    }
                    total += this.actividad_monto;
                    addID = t.row.add(['<tr><td>' + codigo + '</td>', '<td>' + this.solicitud_centro_costo + '</td>', '<td>' + this.tc_nombre + '</td>',
                        '<td>' + new Intl.NumberFormat("US", options2).format(this.actividad_monto) + '</td>', '<td>' + this.actividad_nombre + '</td>', '<td>' + estado + '</td>', '<td>' +observacion+ '</td>',
                        '<td>' + div + '</td></tr>']).draw(false);
                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                    theNode.setAttribute('id', this.solicitud_id);
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

function activarPDF(id) {
    document.getElementById("FrmDescargarSol" + id).submit();
}

function activarPDFTH(id) {
    document.getElementById("FrmDescargarSolTH" + id).submit();
}

$('#example').on('click', 'tr td #verificarR', function () {
    var data = $(this).data();
    $('#idreqregresarjv').val(data['sol']);
    $('#observacionregv').val();
    $('#centroCregV').html("<div style='font-weight: bold'>DEPENDENCIA: </div><div>" + data['centro'] + "</div>");
    $('#montoregV').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $('#regresarModalVer').modal();
});

$('#example').on('click', 'tr td #verificarRA', function () {
    var data = $(this).data();
    $('#idreqregresarjvA').val(data['sol']);
    $('#observacionregvA').val('');
    $('#centroCregVA').html("<div style='font-weight: bold'>DEPENDENCIA: </div><div>" + data['centro'] + "</div>");
    $('#montoregVA').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $('#regresarModalVerA').modal();
});

$('#example').on('click', 'tr td #anularSol', function () {
    var data = $(this).data();
    $('#txtobservanulados').val('');
    $('#anularSelim').html('<input type="hidden" id="solicitudid" value="' + data['id'] + '"><input type="hidden" id="solicitudcodigo" value="' + data['codigo'] + '">SOLICITUD: ' + data['codigo'] + '-STP-' + $('#selectanio').val());
    $('#eliminarModal').modal();
});

listaSolicitud(t);

$('#example').on('click', 'tr td #visualReq', function () {
    var data = $(this).data();
    $('#centrovis').val(data['observacion']);
    $('#idsolvist').val(data['sol']);
    $('#autoridadvis').val(data['tsnombre']);
    $('#listaRequerimientosSolVis').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitudNP",
        type: 'POST',
        data: {solicitud: data['sol']},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0, totaliv = 0, div;
                $.each(response, function () {
                    total += this.req_costo_total;
                    if (this.actividad_eval.length > 0) {
                        div = '<i class="fas fa-list-alt"></i>';
                    } else {
                        div = ' ';
                    }
                    $('#listaRequerimientosSolVis').append('<tr><td class="text-center">' + this.req_id + '</td><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td class="text-center">' + this.req_cantidad + '</td>\n\
                    <td>' + this.req_nombre + '</td><td>' + this.req_descripcion + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</td>\n\
                    <td class="text-center" id="servprof" data-req="' + this.req_id + '" data-nombre="' + this.req_nombre + '" data-ag="' + data['ag'] + '" data-estado="' + data['estado'] + '" data-sol="' + data['sol'] + '">' + div + '</td></tr>');
                    num++;
                });
                $('#listaRequerimientosSolVis').append('<tr><td colspan="8" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $("#modalJustVis").modal();
});

$('#listaRequerimientosSolVis').on('click', 'tr td#servprof', function () {
    var data = $(this).data();
    $('#listaRequerimientosServProf').empty();
    $('#reqnom').html(data['nombre']);
    if (data['estado'] === 15 || data['estado'] === 34) {
        $.ajax({
            url: "../solicitud?accion=ListarServiciosSol",
            type: 'POST',
            data: {solicitud: data['sol'], req: data['req']},
            dataType: 'json'
        })
                .done(function (response) {
                    var tipo, fi, ff, iva2, total = 0, codigo;
                    $.each(response, function () {
                        if (this.solicitud_id === 1) {
                            codigo = this.solicitud_codigo + "-STP-" + this.req_anio;
                        } else {
                            codigo = "---";
                        }
                        if (this.actividad_id === 1) {
                            tipo = 'HORAS CLASE';
                            fi = '----';
                            ff = '----';
                        } else if (this.actividad_id === 2) {
                            tipo = 'HONORARIOS MENSUALES';
                            fi = this.fecha_inicio;
                            ff = this.fecha_fin;
                        } else if (this.actividad_id === 3) {
                            tipo = 'HONORARIO FIJO';
                            fi = '----';
                            ff = '----';
                        } else {
                            fi = '----';
                            ff = '----';
                            tipo = '---';
                        }
                        iva2 = this.req_costo_total - this.req_costo_sin_iva;
                        $('#listaRequerimientosServProf').append('<tr><td class="text-center">' + this.req_descripcion + ' ' + this.req_nombre + '</td><td class="text-center">' + this.actividad_nombre + '</td>\n\
                    <td>' + tipo + '</td><td>' + fi + '</td>\n\
                    <td class="text-center">' + ff + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(iva2.toFixed(2)) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total.toFixed(2)) + '</td>\n\
                    <td class="text-center">' + codigo + '</td></tr>');
                        total = total + this.req_costo_total;
                    });
                    $('#listaRequerimientosServProf').append('<tr><td colspan="9" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    } else {
        $.ajax({
            url: "../solicitud?accion=ListarServiciosReq",
            type: 'POST',
            data: {req: data['req']},
            dataType: 'json'
        })
                .done(function (response) {
                    var tipo, fi, ff, iva2, total = 0, codigo;
                    $.each(response, function () {
                        if (this.solicitud_id === 1) {
                            codigo = this.solicitud_codigo + "-STP-" + this.req_anio;
                        } else {
                            codigo = "---";
                        }
                        if (this.actividad_id === 1) {
                            tipo = 'HORAS CLASE';
                            fi = '----';
                            ff = '----';
                        } else if (this.actividad_id === 2) {
                            tipo = 'HONORARIOS MENSUALES';
                            fi = this.fecha_inicio;
                            ff = this.fecha_fin;
                        } else if (this.actividad_id === 3) {
                            tipo = 'HONORARIO FIJO';
                            fi = '----';
                            ff = '----';
                        } else {
                            fi = '----';
                            ff = '----';
                            tipo = '---';
                        }
                        iva2 = this.req_costo_total - this.req_costo_sin_iva;
                        if (this.solestado_numero === 1) {
                            $('#listaRequerimientosServProf').append('<tr><td class="text-center">' + this.req_descripcion + ' ' + this.req_nombre + '</td><td class="text-center">' + this.actividad_nombre + '</td>\n\
                    <td>' + tipo + '</td><td>' + fi + '</td>\n\
                    <td class="text-center">' + ff + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(iva2.toFixed(2)) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total.toFixed(2)) + '</td>\n\
                    <td class="text-center">' + codigo + '</td></tr>');
                            total = total + this.req_costo_total;
                        }
                    });
                    $('#listaRequerimientosServProf').append('<tr><td colspan="9" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
    $('#modalServProf').modal();
});

$('#example').on('click', 'tr td #agregReq', function () {
    var data = $(this).data();
    $('#solicag').val(data['sol']);
    $('#modalJustAgregar').modal();
});
//Agregar Requerimientos
$('#modalGuardarJustAgreg').on('click', function () {
    var solic = $('input[name=solicag]').val();
    var re = [];
    $("input[name=reqfinmod]:checked").each(function () {
        var rem = [];
        var d = $(this).data();
        rem.push(d['id']);
        rem.push(d['cantidad']);
        rem.push(d['costou']);
        rem.push(d['costosin']);
        rem.push(d['costoiva']);
        rem.push(d['prproyecto']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    var formData = new FormData();
    formData.append('req', req);
    formData.append('solc', solic);
    $.ajax({
        url: "../solicitud?accion=AgregarRequerimientosNP",
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
function listaSolicitudModificar(sol) {
    $('#listaRequerimientosSol').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitudNP",
        type: 'POST',
        data: {solicitud: sol},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0, tipous = $('#tipoUsuario').val();
                $.each(response, function () {
                    var color;
                    if (num % 2 === 0) {
                        color = 'rgba(19, 51, 81, .25)';
                    } else {
                        color = '#fff';
                    }
                    total += this.req_costo_sin_iva;
                    if (tipous === '23') {
                        $('#listaRequerimientosSol').append('<tr><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td><input type="hidden" class="form-control"  name="canti' + this.req_id + '" class="input" id="canti' + this.req_id + '" value="1">1</td>\n\
                    <td>' + this.req_nombre + '</td><td>' + this.req_descripcion + '</td>\n\
                    <td><input type="hidden" class="form-control"  name="costouni' + this.req_id + '" id="costouni' + this.req_id + '" class="input" value="' + this.req_costo_unitario + '">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                    <td><input type="hidden" value="' + this.presupuesto_proyecto + '" name="proypres' + this.req_id + '" id="proypres' + this.req_id + '"><input type="checkbox" value="' + this.req_id + '" name="re" id="re" checked="checked"></td></tr>');
                    } else {
                        $('#listaRequerimientosSol').append('<tr><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td><input type="text" class="form-control"  name="canti' + this.req_id + '" class="input" id="canti' + this.req_id + '" value="' + this.req_cantidad + '"></td>\n\
                    <td>' + this.req_nombre + '</td><td>' + this.req_descripcion + '</td>\n\
                    <td><input type="text" class="form-control"  name="costouni' + this.req_id + '" id="costouni' + this.req_id + '" class="input" value="' + this.req_costo_unitario + '"></td>\n\
                    <td>' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                    <td><input type="hidden" value="' + this.presupuesto_proyecto + '" name="proypres' + this.req_id + '" id="proypres' + this.req_id + '"><input type="checkbox" value="' + this.req_id + '" name="re" id="re" checked="checked"></td></tr>');
                    }
                    num++;
                });
                $('#listaRequerimientosSol').append('<tr><td colspan="6" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listaSolicitudInformacion(sol) {
    $('#listaRequerimientosSoli').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitud",
        type: 'POST',
        data: {solicitud: sol},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    var color;
                    if (num % 2 === 0) {
                        color = 'rgba(19, 51, 81, .25)';
                    } else {
                        color = '#fff';
                    }
                    total += this.req_costo_sin_iva;
                    $('#listaRequerimientosSoli').append('<tr><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td>' + this.req_cantidad + '</td>\n\
                    <td class="text-center">' + this.unidad_nombre + '</td>\n\
                    <td>' + this.req_nombre + '</td>\n\
                    <td>' + this.req_costo_unitario + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td></tr>');
                    num++;
                });
                $('#listaRequerimientosSoli').append('<tr><td colspan="5" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#example').on('click', 'tr td #modJusti', function () {
    var data = $(this).data();
    $('#solidmod').val(data['sol']);
    $('#descripcionsmod').val(data['observacion']);
    $('#rectoringmod').find('option[value="' + data['tsid'] + '"]').remove();
    $('#rectoringmod').append('<option value="' + data['tsid'] + '" selected="selected">' + data['tsnombre'] + '</option>');
    listaSolicitudModificar(data['sol']);
    $('#generarJ').modal();
});
$('#example').on('click', 'tr td #modJustiInfo', function () {
    var data = $(this).data();
    $('#solidmodi').val(data['sol']);
    $('#centromodi').val(data['centro']);
    $('#cedulacustodiomodi').val(data['cedula']);
    $('#nombrecustodiomodi').val(data['nombre']);
    $('#cargocustodiomodi').val(data['cargo']);
    $('#rectoringmodi').find('option[value="' + data['autoridades'] + '"]').remove();
    $('#rectoringmodi').append('<option value="' + data['autoridades'] + '" selected="selected">' + data['autoridadesnombre'] + '</option>');
    listaSolicitudInformacion(data['sol']);
    $('#modificarInfo').modal();
});

//Modificar solicitud
$('#modalGuardarJustMod').on('click', function () {
    var descripcion = $('#descripcionsmod').val();
    ;
    var solicitud = $('input[name=solidmod]').val();
    var rector = $('#rectoringmod').val();
    ;
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        rem.push($(this).val());
        rem.push($('input[name=canti' + $(this).val() + ']').val());
        rem.push($('input[name=costouni' + $(this).val() + ']').val());
        rem.push($('input[name=proypres' + $(this).val() + ']').val());
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (descripcion === "") {
        alert("Debe ingresar el centro de costo");
    } else {
        var formData = new FormData();
        formData.append('descripcion', descripcion);
        formData.append('req', req);
        formData.append('solicitud', solicitud);
        formData.append('rector', rector);
        formData.append('cedula', $('#cedulaProyecto').val());
        $.ajax({
            url: "../solicitud?accion=ModificarSolicitudNP",
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
//Modificar solicitud Informacion
$('#modalGuardarJustModi').on('click', function () {
    var centrocosto = $('input[name=centromodi]').val();
    var solicitud = $('input[name=solidmodi]').val();
    var rector = $('select[name=rectoringmodi]').val();
    var cedula = $('#cedulacustodiomodi').val();
    var nombre = $('#nombrecustodiomodi').val();
    var cargo = $('#cargocustodiomodi').val();
    if (centrocosto === "") {
        alert("Debe ingresar el centro de costo");
    } else {
        var formData = new FormData();
        formData.append('centro', centrocosto);
        formData.append('solicitud', solicitud);
        formData.append('rector', rector);
        formData.append('cedula', $('#cedulaProyectoi').val());
        formData.append('cedulac', cedula);
        formData.append('nombre', nombre);
        formData.append('cargo', cargo);
        $.ajax({
            url: "../solicitud?accion=ModificarSolicitudInformacion",
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
$('#example').on('click', 'tr td  #elimSol', function () {
    var data = $(this).data();
    $('#solicitudid').val(data['solicitud']);
    $('#eliminarModal').modal();
});

$('#modalElSolicitud').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EliminarSolicitudNP",
        type: 'POST',
        data: {solicitud: $('#solicitudid').val(), cedula: $('#cedulaP').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('#modalAnularSolicitud').on('click', function () {
    if ($('#txtobservanulados').val() === null || $('#txtobservanulados').val() === 'undefined' || $('#txtobservanulados').val() === "") {
        alert("Se debe ingresar una observacion");
    } else {
        $.ajax({
            url: "../solicitud?accion=AnularSolicitud",
            type: 'POST',
            data: {solicitud: $('#solicitudid').val(), cedula: $('#cedulaP').val(), estado: 40, observacion: $('#txtobservanulados').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
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

$('#modalAnularSolicitudLP').on('click', function(){
   window.location.href='pServiciosProfLiq.jsp?id=' + $('#solicitudid').val() + '&codigo='+$('#solicitudcodigo').val(); 
});

$('#example').on('click', 'tr td #enviarSol', function () {
    var data = $(this).data();
    $('#idsolicitudenviar').val(data['solicitud']);
    if (data['estado'] === 33) {
        $('#idestadoenviar').val(37);
    } else if (data['estado'] === 35) {
        $('#idestadoenviar').val(38);
    } else {
        $('#idestadoenviar').val(1);
    }
    $('#enviarModal').modal();
});
//Enviar solicitud
$('#modalEnvJust').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EnviarSolicitudNP",
        type: 'POST',
        data: {idSolicitud: $('#idsolicitudenviar').val(), cedula: $('#cedulaP').val(), estado: $('#idestadoenviar').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
                } else {
                    alert(response);
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});
$('#example').on('click', 'tr td #listarFechas', function () {
    var data = $(this).data();
    $('#fechaenvioreq').empty();
    $.ajax({
        url: "../solicitud?accion=ListaEnviosNP",
        type: 'POST',
        data: {solicitud: data['sol'], tipo: 1},
        dataType: 'json'
    })
            .done(function (response) {
                var options = {
                    weekday: "long", year: "numeric", month: "short",
                    day: "numeric", hour: "2-digit", minute: "2-digit"
                };
                $.each(response, function () {
                    var date = new Date(this.solestado_fecha);
                    var observacion;
                    if (this.solestado_observacion == null || this.solestado_observacion === "" || this.solestado_observacion === "undefined") {
                        observacion = "";
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    $('#fechaenvioreq').append('<div style="width:100%">' + this.estado_nombre + ' por ' + this.usuario_nombre + ' el ' + date.toLocaleTimeString("es-ES", options) + ', ' + observacion + '</div>');
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $('#devolverModal').modal();
});

$('.intrevisarv').on('click', function () {
    var data = $(this).data();
    var observacion = $('textarea[name=observacionregv]').val();
    if ((observacion == "" || observacion == null || observacion == '') && (data['estado'] !== 15)) {
        alert("Debe ingresar la observacion");
    } else {
        $.ajax({
            url: "../solicitud?accion=EnviarSolicitudNP",
            type: 'POST',
            data: {idSolicitud: $('#idreqregresarjv').val(), cedula: $('#cedulaP').val(), estado: data['estado'], observacion: observacion, anio: $('#selectanio').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
                    } else {
                        alert(response);
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

$('.intrevisarva').on('click', function () {
    var data = $(this).data();
    var observacion = $('textarea[name=observacionregva]').val();
    if ((observacion == "" || observacion == null || observacion == '') && (data['estado'] !== 15 && data['estado'] !== 34)) {
        alert("Debe ingresar la observacion");
    } else {
        $.ajax({
            url: "../solicitud?accion=EnviarSolicitudNP",
            type: 'POST',
            data: {idSolicitud: $('#idreqregresarjvA').val(), cedula: $('#cedulaP').val(), estado: data['estado'], observacion: observacion, anio: $('#selectanio').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
                    } else {
                        alert(response);
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