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
        url: "../solicitud?accion=ListaSolicitudesCAEU",
        data: {anio: anio},
        type: 'POST',
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
                    if(!validacion && this.solicitud_estado!==40){
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><form method="POST" action="../reporteExcel" id="FrmDescargarJustExcel' + this.solicitud_id + '" class="instructivos_archivos_icono" style="margin:0; padding:0;" onclick="activarExcel(' + this.solicitud_id + ');"><input type="hidden" value="reporteJustificativoExcel" name="accion"><input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><a href="#" title="Descargar Justificativo Excel" class="icon_sol" id="descargarJustExcel" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-excel"></i></a></form>\n\
                        <a href="pCertificacionPU.jsp?id=' + this.solicitud_id + '&codigo=' + this.solicitud_codigo + '" title="Agregar certificaci\u00f3n"><i class="far fa-plus-square"></i></a>';
                    }else if (this.solicitud_estado === 32) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><a href="#" title="Verificar recibido" class="icon_sol" id="verificarR" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-check-square"></i></a><form method="POST" action="../reporteExcel" id="FrmDescargarJustExcel' + this.solicitud_id + '" class="instructivos_archivos_icono" style="margin:0; padding:0;" onclick="activarExcel(' + this.solicitud_id + ');"><input type="hidden" value="reporteJustificativoExcel" name="accion"><input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><a href="#" title="Descargar Justificativo Excel" class="icon_sol" id="descargarJustExcel" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-excel"></i></a></form>';
                    } else if (this.solicitud_estado === 34) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><a href="#" class="icon_sol" id="entregar" title="Entregar Financiero" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="fab fa-avianex"></i></a><form method="POST" action="../reporteExcel" id="FrmDescargarJustExcel' + this.solicitud_id + '" class="instructivos_archivos_icono" style="margin:0; padding:0;" onclick="activarExcel(' + this.solicitud_id + ');"><input type="hidden" value="reporteJustificativoExcel" name="accion"><input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><a href="#" title="Descargar Justificativo Excel" class="icon_sol" id="descargarJustExcel" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-excel"></i></a></form>';
                    } else if (this.solicitud_estado === 35) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><form method="POST" action="../controladorReportePDF" id="FrmDescargarJust' + this.solicitud_id + '" class="instructivos_archivos_icono" target="_blank" style="margin:0; padding:0;" onclick="activarPDF(' + this.solicitud_id + ');"><input type="hidden" value="reporteOficio" name="accion">\n\
                        <input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><input type="hidden" name="estadojus" id="estadojus" value="' + this.solicitud_estado + '"><input type="hidden" name="numerooficio" id="numerooficio" value="' + this.solestado_numero + '"><input type="hidden" name="tipo" id="tipo" value="1"><input type="hidden" name="agid" id="agid" value="' + ag + '"><input type="hidden" name="observacion" id="observacion" value="' + observacion + '">\n\
                        <a href="#" title="Descargar Oficio" class="icon_sol" id="descargarOficio" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-download"></i></a></form><form method="POST" action="../reporteExcel" id="FrmDescargarJustExcel' + this.solicitud_id + '" class="instructivos_archivos_icono" style="margin:0; padding:0;" onclick="activarExcel(' + this.solicitud_id + ');"><input type="hidden" value="reporteJustificativoExcel" name="accion"><input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><a href="#" title="Descargar Justificativo Excel" class="icon_sol" id="descargarJustExcel" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-excel"></i></a></form>';
                    } else if (this.solicitud_estado === 36) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-custodio="' + this.solicitud_nombre + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-monto="' + this.actividad_monto + '"><i class="far fa-calendar-alt"></i></a><form method="POST" action="../reporteExcel" id="FrmDescargarJustExcel' + this.solicitud_id + '" class="instructivos_archivos_icono" style="margin:0; padding:0;" onclick="activarExcel(' + this.solicitud_id + ');"><input type="hidden" value="reporteJustificativoExcel" name="accion"><input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><a href="#" title="Descargar Justificativo Excel" class="icon_sol" id="descargarJustExcel" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-excel"></i></a></form>\n\
                        <a href="pCertificacionPU.jsp?id=' + this.solicitud_id + '&codigo=' + this.solicitud_codigo + '" title="Agregar certificaci\u00f3n"><i class="far fa-plus-square"></i></a>';
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

function activarExcel(id) {
    document.getElementById("FrmDescargarJustExcel" + id).submit();
}

$('#example').on('click', 'tr td #visualReq', function () {
    var data = $(this).data();
    $('#centrovis').val(data['centro']);
    $('#idsolvist').val(data['sol']);
    $('#custodiovis').val(data['custodio']);
    $('#autoridadvis').val(data['autoridadesnombre']);
    $('#listaRequerimientosSolVis').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitudUnif",
        type: 'POST',
        data: {solicitud: data['sol']},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    total += this.req_costo_sin_iva;
                    var iva;
                    if (this.req_iva === 1) {
                        iva = "SI";
                    } else {
                        iva = "NO";
                    }
                    $('#listaRequerimientosSolVis').append('<tr><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td class="text-center">' + this.req_cantidad + '</td>\n\
                    <td class="text-center">' + this.unidad_nombre + '</td><td>' + this.req_nombre + '</td><td>' + this.req_descripcion + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td><td class="text-center">' + iva + '</td><td class="text-center"><i class="fas fa-clipboard-list" data-req="' + this.req_id + '" data-nombre="' + this.req_nombre + '" \n\
                    data-descripcion="' + this.req_descripcion + '" data-cantidad="' + this.req_cantidad + '" data-costo="' + this.req_costo_unitario + '" data-unidad="' + this.unidad_nombre + '" id="mostrarReq"></i></td></tr>');
                    num++;
                });
                $('#listaRequerimientosSolVis').append('<tr><td colspan="9" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    $("#modalJustVis").modal();
});

$('#example').on('click', 'tr td #enviarSol', function () {
    var data = $(this).data();
    $('#idsolicitudenviar').val(data['solicitud']);
    $('#enviarModal').modal();
});

$('#example').on('click', 'tr td #listarFechas', function () {
    var data = $(this).data();
    $('#fechaenvioreq').empty();
    $('#centroCregF').html("<div style='font-weight: bold'>CENTRO DE COSTO: </div><div>" + data['centro'] + "</div>");
    $('#montoregF').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $.ajax({
        url: "../solicitud?accion=ListaEnvios",
        type: 'POST',
        data: {solicitud: data['sol'], tipo: 2},
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

$('#example').on('click', 'tr td #entregar', function () {
    var data = $(this).data();
    $('#idreqregresarj').val(data['sol']);
    $('#observacionreg').val('');
    $('#centroCreg').html("<div style='font-weight: bold'>CENTRO DE COSTO: </div><div>" + data['centro'] + "</div>");
    $('#montoreg').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $('#regresarModal').modal();
});

$('#example').on('click', 'tr td #verificarJust', function () {
    var data = $(this).data();
    $('#idreqregresarj').val(data['sol']);
    $('#observacionreg').val('');
    $('#centroCreg').html("<div style='font-weight: bold'>CENTRO DE COSTO: </div><div>" + data['centro'] + "</div>");
    $('#montoreg').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $('#regresarModal').modal();
});

$('#example').on('click', 'tr td #verificarR', function () {
    var data = $(this).data();
    $('#idreqregresarjv').val(data['sol']);
    $('#observacionregv').val('');
    $('#centroCregV').html("<div style='font-weight: bold'>CENTRO DE COSTO: </div><div>" + data['centro'] + "</div>");
    $('#montoregV').html("<div style='font-weight: bold'>MONTO: </div><div>" + new Intl.NumberFormat("US", options2).format(data['monto']) + "</div>");
    $('#regresarModalVer').modal();
});

$('.intrevisar').on('click', function () {
    var data = $(this).data();
    var observacion = $('textarea[name=observacionreg]').val();
    var tipo = $('#tipolista').val();
//    if (data['estado'] === 31) {
//        observacion = " ";
//    }
    if ((observacion == "" || observacion == null || observacion == '') && data['estado'] !== 31 && data['estado'] !== 36) {
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
    if (data['estado'] === 32) {
        observacion = " ";
    }
    if ((observacion == "" || observacion == null || observacion == '') && (data['estado'] !== 31 && data['estado'] !== 36)) {
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

////Mostrar requerimientos
$('#listaRequerimientosSolVis').on('click', 'tr td #mostrarReq', function () {
    var data = $(this).data();
    var sum = 0, sumto = 0, total = 0, sumci = 0, tipo;
    $('#nombreunif').val(data['nombre']);
    $('#descunif').val(data['descripcion']);
    $('#cantidadunif').val(data['cantidad']);
    $('#costounif').val(data['costo']);
    $('#unidadinp').val(data['unidad']);
    $('#listaRequerimientosUnificaReq').empty();
    if(data['cantidad']>1){
        tipo=2;
    }else{
        tipo=1;
    }
    $.ajax({
        url: "../solicitud?accion=ListaRequerimientosU",
        data: {req: data['req'], tipo:tipo},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var options2 = {style: "currency", currency: "USD"};
                $.each(response, function () {
                    var iva;
                    if (this.req_iva === 1) {
                        iva = this.req_costo_sin_iva * 1.12;
                    } else {
                        iva = this.req_costo_sin_iva;
                    }
                    $('#listaRequerimientosUnificaReq').append('<tr><td>' + this.ag_nombre + '</td>\n\
                        <td class="text-center">OEI-0' + this.perspectiva_id + '</td>\n\
                        <td class="text-center">' + this.req_cantidad + '</td>\n\
                        <td class="text-center">' + this.unidad_nombre + '</td>\n\
                        <td>' + this.req_nombre + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                    </tr>');
                    sumto = sumto + this.req_cantidad;
                    sum = sum + this.req_costo_unitario;
                    sumci = sumci + this.req_costo_sin_iva;
                    total = total + iva;
                });
                $('#listaRequerimientosUnificaReq').append('<tr><td colspan="5" class="text-center">TOTAL</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(sum) + '</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(sumci) + '</td><td class="text-center">' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    $('#unificarR').modal();
});