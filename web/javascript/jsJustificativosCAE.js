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
    var ag = $('#areaPadre').val();
    var anio = $('#selectanio').val();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesCAE",
        type: 'POST',
        data: {area: ag, anio: anio},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var addID, estado, codigo, observacion;
                    if (this.estado_nombre == null) {
                        estado = 'Realizando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.solestado_observacion == null) {
                        observacion = 'Sin observaciones'
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    if (this.solicitud_codigo == null || this.solicitud_codigo === "undefined") {
                        codigo = '----';
                    } else {
                        codigo = this.solicitud_codigo + '-UCP-' + anio;
                    }
                    var div;
                    if (!validacion && this.solicitud_estado !== 40) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><a href="pCertificacionP.jsp?id=' + this.solicitud_id + '&codigo=' + this.solicitud_codigo + '" title="Agregar certificaci\u00f3n"><i class="far fa-plus-square"></i></a>';
                    }
                    if (this.solicitud_estado === 32) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><a href="#" title="Verificar recibido" class="icon_sol" id="verificarR" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-check-square"></i></a>';
                    } else if (this.solicitud_estado === 34) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><a href="#" class="icon_sol" id="entregar" title="Entregar Financiero" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="fab fa-avianex"></i></a>';
                    } else if (this.solicitud_estado === 35) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><form method="POST" action="../controladorReportePDF" id="FrmDescargarJust' + this.solicitud_id + '" class="instructivos_archivos_icono" target="_blank" style="margin:0; padding:0;" onclick="activarPDF(' + this.solicitud_id + ');"><input type="hidden" value="reporteOficio" name="accion">\n\
                        <input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><input type="hidden" name="estadojus" id="estadojus" value="' + this.solicitud_estado + '"><input type="hidden" name="numerooficio" id="numerooficio" value="' + this.solestado_numero + '"><input type="hidden" name="tipo" id="tipo" value="1"><input type="hidden" name="agid" id="agid" value="' + ag + '"><input type="hidden" name="observacion" id="observacion" value="' + observacion + '">\n\
                        <a href="#" title="Descargar Oficio" class="icon_sol" id="descargarOficio" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-download"></i></a></form>';
                    } else if (this.solicitud_estado === 36) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><a href="pCertificacionP.jsp?id=' + this.solicitud_id + '&codigo=' + this.solicitud_codigo + '" title="Agregar certificaci\u00f3n"><i class="far fa-plus-square"></i></a>';
                    } else {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a>';
                    }
                    total += this.actividad_monto;
                    addID = t.row.add(['<tr><td>' + codigo + '</td>', '<td>' + this.autoridades_nombre + '</td>', '<td>' + this.solicitud_centro_costo + '</td>',
                        '<td>' + new Intl.NumberFormat("US", options2).format(this.actividad_monto) + '</td>', '<td>' + estado + '</td>',
                        '<td>' + observacion + '</td>', '<td>' + div + '</td></tr>']).draw(false);
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

listaSolicitud(t);

function activarPDF(id) {
    document.getElementById("FrmDescargarJust" + id).submit();
}

$('#example').on('click', 'tr td #visualReq', function () {
    var data = $(this).data();
    $('#centrovis').val(data['centro']);
    $('#idsolvist').val(data['sol']);
    $('#custodiovis').val(data['custodio']);
    $('#autoridadvis').html(data['autoridadesnombre']);
    $('#listaRequerimientosSolVis').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitud",
        type: 'POST',
        data: {solicitud: data['sol']},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0, totaliva = 0, id;
                $.each(response, function () {
                    total += this.req_costo_sin_iva;
                    var iva;
                    if (this.req_iva === 1) {
                        iva = this.req_costo_sin_iva * 1.12;
                    } else {
                        iva = this.req_costo_sin_iva;
                    }
                    id = this.req_id;
                    totaliva += iva;
                    $('#listaRequerimientosSolVis').append('<tr><td class="text-center">'+this.req_id+'</td><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td class="text-center">' + this.req_cantidad + '</td>\n\
                    <td class="text-center">' + this.unidad_nombre + '</td><td>' + this.req_nombre + '</td><td>' + this.req_descripcion + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                    <td class="text-center"><i class="fas fa-search-plus" id="verestr" data-req="' + this.req_id + '" title="Ver Estructura"></i><input type="checkbox" id="validar' + this.req_id + '" value="1" onchange=validarreq("' + this.req_id + '")></td></tr><tr><td colspan="9" id="estructurareq' + this.req_id + '" style="display:none;"></td></tr>');
                    num++;
                    if (this.deudas.length > 0) {
                        $.each(this.deudas, function (indice, est) {
                            $('#listaRequerimientosSolVis').children('tr').children('#estructurareq' + id).html('<div class="row"><div class="col-2"><label style="font-weight:bold;">Cantidad:</label>' + est.req_cantidad + '</div>\n\
                            <div class="col-3"><label style="font-weight:bold;">Costo Unitario:</label>' + new Intl.NumberFormat("US", options2).format(est.req_costo_unitario) + '</div><div class="col-3"><label style="font-weight:bold;">Costo Sin IVA:</label>' + new Intl.NumberFormat("US", options2).format(est.req_costo_sin_iva) + '</div><div class="col-3"><label style="font-weight:bold;">Costo Total:</label>' + new Intl.NumberFormat("US", options2).format(est.req_costo_total) + '</div>\n\
                            <div class="col-2"><label style="font-weight:bold;">Programa:</label>' + est.presupuesto_programa + '</div><div class="col-2"><label style="font-weight:bold;">Proyecto:</label>' + est.presupuesto_proyecto + '</div>\n\
                            <div class="col-2"><label style="font-weight:bold;">Actividad:</label>' + est.presupuesto_actividad + '</div><div class="col-3"><label style="font-weight:bold;">Renglo:</label>' + est.presupuesto_renglo + '</div><div class="col-2"><label style="font-weight:bold;">Fuente:</label>' + est.presupuesto_fuente + '</div></div>');
                        });
                    }
                });
                $('#listaRequerimientosSolVis').append('<tr><td colspan="7" class="text-center">TOTAL</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(total) + '</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(totaliva) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    $("#modalJustVis").modal();
});

function validarreq(req) {
    if ($('#validar' + req).is(':checked')) {
        $.ajax({
            url: "../solicitud?accion=ValidarReq",
            type: 'POST',
            data: {"req": req, validar: false},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response !== "Correcto") {
                        alert(response);
                    }
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    } else {
        $.ajax({
            url: "../solicitud?accion=ValidarReq",
            type: 'POST',
            data: {"req": req, validar: true},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response !== "Correcto") {
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
}

//Ver estructura requerimientos
$("#listaRequerimientosSolVis").on('click', 'tr td #verestr', function () {
    var data = $(this).data();
    $('#estructurareq' + data['req']).slideToggle();
});

$('#example').on('click', 'tr td #enviarSol', function () {
    var data = $(this).data();
    $('#idsolicitudenviar').val(data['solicitud']);
    $('#enviarModal').modal();
});

//Enviar solicitud
$('#modalEnvJust').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EnviarSolicitud",
        type: 'POST',
        data: {idSolicitud: $('#idsolicitudenviar').val(), cedula: $('#cedulaP').val(), estado: 1},
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
    $('#centroCregF').html("<div style='font-weight: bold'>CENTRO DE COSTO: </div><div>" + data['centro'] + "</div>");
    $('#montoregF').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $.ajax({
        url: "../solicitud?accion=ListaEnvios",
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

//Guardar autoridad de solicitud
$('#modalGuardarJustVis').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=ModificarAutoridades",
        type: 'POST',
        data: {idSolicitud: $('#idsolvist').val(), autoridad: $('#autoridadvis').val()},
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

$('#example').on('click', 'tr td #entregar', function () {
    var data = $(this).data();
    $('#idreqregresarj').val(data['sol']);
    $('#centroCreg').html("<div style='font-weight: bold'>CENTRO DE COSTO: </div><div>" + data['centro'] + "</div>");
    $('#montoreg').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $('#observacionreg').val("");
    $('#observacionregv').val("");
    $('#regresarModal').modal();
});

$('#example').on('click', 'tr td #verificarR', function () {
    var data = $(this).data();
    $('#idreqregresarjv').val(data['sol']);
    $('#centroCregV').html("<div style='font-weight: bold'>CENTRO DE COSTO: </div><div>" + data['centro'] + "</div>");
    $('#montoregV').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $('#observacionreg').val("");
    $('#observacionregv').val("");
    $('#regresarModalVer').modal();
});

$('.intrevisar').on('click', function () {
    var data = $(this).data();
    var observacion = $('textarea[name=observacionreg]').val();
    var tipo = $('#tipolista').val();
//    if (data['estado'] === 31) {
//        observacion = " ";
//    }
    if ((observacion == "" || observacion == null || observacion == '') && data['estado'] !== 35 && data['estado'] !== 39) {
        alert("Debe ingresar la observacion");
    } else {
        $.ajax({
            url: "../solicitud?accion=EnviarSolicitudJus",
            type: 'POST',
            data: {idSolicitud: $('#idreqregresarj').val(), cedula: $('#cedulaP').val(), estado: data['estado'], observacion: observacion, tipo: tipo, anio: $('#selectanio').val()},
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

$('.intrevisarv').on('click', function () {
    var data = $(this).data();
    var observacion = $('textarea[name=observacionregv]').val();
    var tipo = $('#tipolista').val();
    if ((observacion == "" || observacion == null || observacion == '') && (data['estado'] !== 34 && data['estado'] !== 36)) {
        alert("Debe ingresar la observacion");
    } else {
        $.ajax({
            url: "../solicitud?accion=EnviarSolicitudJus",
            type: 'POST',
            data: {idSolicitud: $('#idreqregresarjv').val(), cedula: $('#cedulaP').val(), estado: data['estado'], observacion: observacion, tipo: tipo, anio: $('#selectanio').val()},
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