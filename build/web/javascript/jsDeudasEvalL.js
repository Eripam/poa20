var banDeuda = false, banAuto = false;
$(document).ready(function () {
    listarDeudas(0);
});

$('#selectareas').on('change', function () {
    listarDeudas($(this).val());
});

function listarDeudas(ag) {
    $.ajax({
        url: "../proyecto?accion=ListarDeudasEvalL" + "&ag=" + ag + "&anio=" + $('#selectanio').val(),
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#proyectosForm').empty();
                var estado, div, st = 0, siva = 0, santicipo = 0, spendiente = 0, stotalpe = 0, tipo, div2, estadoid;
                $.each(response, function () {
                    estadoid = this.estado_id;
                    if (this.estado_id === 0) {
                        estado = '----';
                    } else {
                        estado = this.estado_nombre;
                    }

                    if (this.tp_id === 1) {
                        tipo = "Obligaciones Pendientes";
                    } else {
                        tipo = "Comprometidos No Deveng";
                    }

                    if (this.estado_id === 0 || this.estado_id === 3) {
                        div = '<i class="fas fa-plus m-1" title="Agregar Evidencia" id="modDeudas" data-id="' + this.deudas_id + '" data-oei="' + this.deudas_oei + '" data-proyecto="' + this.proyecto_nombre + '" \n\
                    data-proceso="' + this.deudas_proceso + '" data-contrato="' + this.deudas_contrato + '" data-tcon="' + this.deudas_tcon + '"></i>\n\
                    <i class="far fa-paper-plane m-1" title="Enviar Deuda" id="enviarDeuda" data-deuda="' + this.deudas_id + '"></i><i class="fas fa-angle-down m-1" title="Visualizar Archivos" id="elimDeudas" data-id="' + this.deudas_id + '"></i>\n\
                    <i class="far fa-eye m-1" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i><i class="fas fa-clipboard-list" id="autoevaluar" data-deuda="' + this.deudas_id + '" data-monto="' + this.deudas_monto_pendiente + '" data-nombre="' + this.deudas_proceso + '" data-iva="' + this.deuda_monto_iva + '" data-anticipo="' + this.deudas_anticipo + '" data-estado="' + estadoid + '"></i>';
                    } else {
                        div = '<i class="far fa-eye m-1" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i><i class="fas fa-angle-down m-1" title="Visualizar Archivos" id="elimDeudas" data-id="' + this.deudas_id + '"></i><i class="fas fa-clipboard-list" id="autoevaluar" data-deuda="' + this.deudas_id + '" data-monto="' + this.deudas_monto_pendiente + '" data-nombre="' + this.deudas_proceso + '" data-iva="' + this.deuda_monto_iva + '" data-anticipo="' + this.deudas_anticipo + '" data-estado="' + estadoid + '"></i>';
                    }
                    $('#proyectosForm').append('<tr><td class="text-justify">OEI-0' + this.deudas_oei + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td>' + this.deudas_proceso + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_contrato) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_iva) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_anticipo) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_monto_pendiente) + '</td><td>' + tipo + '</td><td>' + estado + '</td><td>' + div + '</td></tr><tr><td colspan="10" id="evidencias' + this.deudas_id + '" style="display:none;"></td></tr>');
                    $.each(this.estado, function (indice, archivos) {
                        if (estadoid === 0 || estadoid === 3) {
                            div2 = '<i class="fas fa-edit" title="Editar Evidencia" id="modDeudasEv" data-id="' + archivos.deudas_id + '" data-deudas="' + archivos.deudas_oei + '" data-nombre="' + archivos.deudas_contrato + '" data-archivos="' + archivos.deudas_proceso + '"></i><i class="fas fa-trash" title="Eliminar Evidencia" id="elimDeudasEv" data-id="' + archivos.deudas_id + '" data-deudas="' + archivos.deudas_oei + '"></i>';
                        } else {
                            div2 = '';
                        }
                        $('#proyectosForm').children('tr').children('td#evidencias' + archivos.deudas_oei).append('<tr><td colspan="4" style="font-size:.75em;" class="text-justify">' + archivos.deudas_contrato + '</td><td colspan="4" style="font-size:.75em;" class="text-justify"><a href="https://planificacion.espoch.edu.ec/sip/evaluacion/deudas/' + archivos.deudas_proceso + '" download="' + archivos.deudas_proceso + '">' + archivos.deudas_proceso + '</a></td><td colspan="2">' + div2 + '</td></tr>')
                    });
                    st = st + this.deuda_monto_contrato;
                    siva = siva + this.deuda_monto_iva;
                    santicipo = santicipo + this.deudas_anticipo;
                    spendiente = spendiente + this.deudas_monto_pendiente;
                    stotalpe = stotalpe + (this.deuda_monto_iva + this.deudas_monto_pendiente);
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$("table").on('click', 'tbody tr td #elimDeudas', function () {
    var data = $(this).data();
    $('#proyectosForm').children('tr').children('td#evidencias' + data['id']).slideToggle();
});

//Agregar autoevaluacion
$("table").on('click', 'tbody tr td #autoevaluar', function () {
    $('.card').children('#inputEvaluar').empty();
    var data = $(this).data();
    var suma = data['monto'] + data['iva'] + data['anticipo'];
    var ejecutado, evaluacion, porcentaje, teval, tporcen;
    $.ajax({
        url: "../evaluacion?accion=ListarAutoevaluacionDuedas",
        type: 'POST',
        data: {"deuda": data['deuda']},
        dataType: 'json',
        async: false

    })
            .done(function (response) {
                if (response.length > 0) {
                    banAuto = true;
                    $.each(response, function () {
                        ejecutado = this.ae_autoeval;
                        evaluacion = this.ae_evaluacion;
                        porcentaje = this.ae_porcentaje;
                    })
                } else {
                    banAuto = false;
                    ejecutado = '';
                    evaluacion = '';
                    porcentaje = '';
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    if (porcentaje === 'undefined' || porcentaje === null || porcentaje == "undefined") {
        evaluacion = ejecutado;
        porcentaje = 0;
    } else {
        evaluacion = evaluacion;
        porcentaje = porcentaje;
    }
    $('.card').children('#inputEvaluar').append('<div class="row"><div class="col-12 text-justify mb-1" style="font-weight:bold;">' + data['nombre'] + '</div>\n\
        <label class="col-6">Monto Planificado:</label><div class="col-5">' + new Intl.NumberFormat("US", formateador()).format(suma) + '</div>\n\
        <label class="col-6">Monto ingresado unidad:</label><div class="col-5">' + new Intl.NumberFormat("US", formateador()).format(ejecutado) + '</div>\n\
        <label class="col-6">Monto ingresado evaluaci\u00F3n:</label><input type="number" class="form-control col-5" id="valorevaluacionE" value="' + ejecutado + '">\n\
        <label class="col-6">Porcentaje:</label><div class="col-5">' + new Intl.NumberFormat("US", formateador()).format(porcentaje) + '%</div></div>\n\
<input type="hidden" value="' + data['deuda'] + '" id="deudaideval"><input type="hidden" id="sumadeuda" value="' + suma + '">');
    $('#valorevaluacion').attr("readonly", true);
    if (data['estado'] === 1) {
        $('#guardarEvaluacion').removeClass('d-none');
    } else {
        $('#guardarEvaluacion').addClass('d-none');
    }
    $('#evaluarModal').modal();
});

$('#guardarEvaluacion').on('click', function () {
    $.ajax({
        url: "../evaluacion?accion=EvaluacionDuedas",
        type: 'POST',
        data: {"deuda": $('#deudaideval').val(), "suma": $('#sumadeuda').val(), "ejecucion": $('#valorevaluacionE').val()},
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

//Ver modal listado de deudas
$("table").on('click', 'tbody tr td #verestadosreq', function () {
    var data = $(this).data();
    $('.card').children('#inputEnviarReq').empty();
    var options = {
        weekday: "long", year: "numeric", month: "short",
        day: "numeric", hour: "2-digit", minute: "2-digit"
    };
    $.ajax({
        url: "../proyecto?accion=ListarFechasDeudasEval",
        type: 'POST',
        data: {"deuda": data['deuda']},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    var date = new Date(this.estado_fecha);
                    var observacion;
                    if (this.estado_observacion == null) {
                        observacion = '';
                    } else {
                        observacion = ',' + this.estado_observacion;
                    }
                    $('.card').children('#inputEnviarReq').append('<p class="card-text">' + this.estado_nombre + ' por ' + this.usuario_nombre + ' el ' + date.toLocaleTimeString("es-ES", options) + observacion + '</p>');
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

$("table").on('click', 'tbody tr td #enviarDeuda', function () {
    var data = $(this).data();
    $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar?<input type="hidden" name="estadoproy" id="estadoproy" value="' + data['deuda'] + '">');
    $('#enviarModal').modal();
});

$('#enviarModalBton').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EnviarDeudaEval",
        type: 'POST',
        data: {estado: 1, cedulaProyecto: $('#cedulaProyecto').val(), deuda: $('#estadoproy').val(), observacion: "Sin observacion"},
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