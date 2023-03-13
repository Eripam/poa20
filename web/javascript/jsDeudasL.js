var banDeuda = false;
$(document).ready(function () {
    listarDeudas(0);
});

function listarDeudas(ag) {
    $.ajax({
        url: "../proyecto?accion=ListarDeudasL" + "&ag=" + ag + "&anio=" + $('#selectaniof').val(),
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#proyectosForm').empty();
                var estado, div, st = 0, siva = 0, santicipo = 0, spendiente = 0, stotalpe = 0, tipo;
                $.each(response, function () {
                    estado = this.estado_nombre;
                    if (this.estado_id === 1 && $('#idTipoUsu').val() != "24") {
                        div = '<i class="far fa-paper-plane" title="Enviar Deuda" id="enviarDeuda" data-deuda="' + this.deudas_id + '"></i><i class="far fa-eye" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i>';
                    } else if ($('#idTipoUsu').val() != "24") {
                        div = '<i class="far fa-eye" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i>';
                    } else {
                        div = '<i class="far fa-eye" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i><i class="far fa-trash-alt" id="eliminarEstado" data-deuda="' + this.deudas_id + '" data-proceso="' + this.deudas_proceso + '" data-estadonombre="' + this.estado_nombre + '" data-estadoid="' + this.estado_id + '" data-estadofecha="' + this.deudas_contrato + '" data-observacion="' + this.estado_observacion + '" data-usuario="' + this.usuario_nombre + '" title="Eliminar Estado"></i>';
                    }

                    if (this.tp_id === 1) {
                        tipo = "Obligaciones Pendientes";
                    } else {
                        tipo = "Comprometidos No Deveng";
                    }
                    $('#proyectosForm').append('<tr><td class="text-justify">OEI-0' + this.deudas_oei + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td>' + this.deudas_proceso + '</td>\n\
                    <td>' + this.deudas_contrato + '</td><td>' + this.tcon_nombre + '</td><td>' + this.financiamiento_nombre + '</td><td>' + this.deudas_presupuesto + '</td><td>' + tipo + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_contrato) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_iva) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_anticipo) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_monto_pendiente) + '</td><td>' + estado + '</td><td>' + div + '</td></tr>');
                    st = st + this.deuda_monto_contrato;
                    siva = siva + this.deuda_monto_iva;
                    santicipo = santicipo + this.deudas_anticipo;
                    spendiente = spendiente + this.deudas_monto_pendiente;
                    stotalpe = stotalpe + (this.deuda_monto_iva + this.deudas_monto_pendiente);
                });
                $('#proyectosForm').append('<tr><td colspan="8">TOTAL</td><td>' + new Intl.NumberFormat("US", formateador()).format(st) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(siva) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(santicipo) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(spendiente) + '</td>\n\
                  <td>' + new Intl.NumberFormat("US", formateador()).format(stotalpe) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#selectareas').on('change', function () {
    listarDeudas($(this).val());
});

//Ver modal requerimientos
$("table").on('click', 'tbody tr td #verestadosreq', function () {
    var data = $(this).data();
    $('.card').children('#inputEnviarReq').empty();
    var options = {
        weekday: "long", year: "numeric", month: "short",
        day: "numeric", hour: "2-digit", minute: "2-digit"
    };
    $.ajax({
        url: "../proyecto?accion=ListarFechasDeudas",
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

//Ver modal requerimientos
$("table").on('click', 'tbody tr td #eliminarEstado', function () {
    var data = $(this).data();
    $('#nombrePE').html('<input type="hidden" name="iddeuda" id="iddeuda" value="' + data['deuda'] + '">' + data['proceso'])
    $('#estadoP').html('<input type="hidden" name="idestado" id="idestado" value="' + data['estadoid'] + '"><input type="hidden" name="fechae" id="fechae" value="' + data['estadofecha'] + '"><input type="hidden" name="usuarioe" id="usuarioe" value="' + data['usuario'] + '"><input type="hidden" name="observacione" id="observacione" value="' + data['observacion'] + '">' + data['estadonombre'])
    $('#eliminarDeuda').modal();
});

$("table").on('click', 'tbody tr td #enviarDeuda', function () {
    var data = $(this).data();
    $('#aprobarRadios').val(22);
    $('#modificarRadios').val(3);
    $('#estadoproy').val(data['deuda']);
    $('#enviarModalVer').modal();
});

$('#guardarEnviar').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EnviarDeuda",
        type: 'POST',
        data: {estado: $('input:radio[name=verificarRadios]:checked').val(), cedulaProyecto: $('#cedulaProyecto').val(), deuda: $('#estadoproy').val(), observacion: $('#observacionEnviar').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    if ($('#selectareas').val() == null) {
                        listarDeudas(0);
                    } else {
                        listarDeudas($('#selectareas').val());
                    }
                    $('#enviarModalVer').modal('hide');
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

$('#modalEliminarEstados').on('click', function () {
    $.ajax({
        url: '../proyecto?accion=EliminarEstadoDeuda',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: {cedulaProyecto: $('#cedulaProyecto').val(), deuda: $('#iddeuda').val(), estado: $('#idestado').val(), fecha: $('#fechae').val(), cedula: $('#usuarioe').val(), observacion: $('#observacione').val()}
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
})