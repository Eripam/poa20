var banDeuda = false;
$(document).ready(function () {
    listarDeudas();
});

$('#frmAddProyecto').submit(function (event) {
    event.preventDefault();
    if (banDeuda) {
        $.ajax({
            url: '../proyecto?accion=IngresarDeudas',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: $('#frmAddProyecto').serialize()
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listarDeudas();
                        $("#frmAddProyecto")[0].reset();
                        $('#frmAddProyecto').addClass('d-none');
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
    } else {
        $.ajax({
            url: '../proyecto?accion=ModificarDeudas',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: $('#frmAddProyecto').serialize()
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listarDeudas();
                        $("#frmAddProyecto")[0].reset();
                        $('#frmAddProyecto').addClass('d-none');
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

$('#agregardeuda').on('click', function () {
    $('#frmAddProyecto').removeClass('d-none');
    $("#frmAddProyecto")[0].reset();
    $('#selectTipoCon').selectpicker('refresh');
    $('#selectFin').selectpicker('refresh');
    $('#selectTipoPres').selectpicker('refresh');
    banDeuda = true;
});

function listarDeudas() {
    $.ajax({
        url: "../proyecto?accion=ListarDeudas" + "&ag=" + $('#idAgObEs').val() + "&anio=" + $('#selectanio').val(),
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#proyectosForm').empty();
                var estado, div, st = 0, siva = 0, santicipo = 0, spendiente = 0, stotalpe = 0, tipo;
                $.each(response, function () {
                    if (this.estado_id === 0) {
                        estado = 'Planificando';
                    } else {
                        estado = this.estado_nombre;
                    }

                    if (this.tp_id === 1) {
                        tipo = "Obligaciones Pendientes";
                    } else {
                        tipo = "Comprometidos No Deveng";
                    }

                    if (this.estado_id === 0 || this.estado_id === 3) {
                        div = '<i class="fas fa-edit" title="Editar Deuda" id="modDeudas" data-id="' + this.deudas_id + '" data-oei="' + this.deudas_oei + '" data-proyecto="' + this.proyecto_nombre + '" \n\
                    data-proceso="' + this.deudas_proceso + '" data-contrato="' + this.deudas_contrato + '" data-tcon="' + this.deudas_tcon + '" data-tconnombre="' + this.tcon_nombre + '" data-financiamiento="' + this.deudas_financiamiento + '" \n\
                    data-financiamientonombre="' + this.financiamiento_nombre + '" data-presupuesto="' + this.deudas_presupuesto + '" \n\
                    data-monto="' + this.deuda_monto_contrato + '" data-iva="' + this.deuda_monto_iva + '" data-tipo="' + this.tp_id + '" data-anticipo="' + this.deudas_anticipo + '" data-pendiente="' + this.deudas_monto_pendiente + '"></i>\n\
                    <i class="far fa-paper-plane" title="Enviar Deuda" id="enviarDeuda" data-deuda="' + this.deudas_id + '"></i><i class="fas fa-trash" title="Eliminar Deuda" id="elimDeudas" data-id="' + this.deudas_id + '"></i><i class="far fa-eye" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i>';
                    } else {
                        div = '<i class="far fa-eye" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i>';
                    }
                    $('#proyectosForm').append('<tr><td class="text-justify">OEI-0' + this.deudas_oei + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td>' + this.deudas_proceso + '</td>\n\
                    <td>' + this.deudas_contrato + '</td><td>' + this.tcon_nombre + '</td><td>' + this.financiamiento_nombre + '</td><td>' + this.deudas_presupuesto + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_contrato) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_iva) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_anticipo) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_monto_pendiente) + '</td><td>' + tipo + '</td><td>' + estado + '</td><td>' + div + '</td></tr>');
                    st = st + this.deuda_monto_contrato;
                    siva = siva + this.deuda_monto_iva;
                    santicipo = santicipo + this.deudas_anticipo;
                    spendiente = spendiente + this.deudas_monto_pendiente;
                    stotalpe = stotalpe + (this.deuda_monto_iva + this.deudas_monto_pendiente);
                });
                $('#proyectosForm').append('<tr><td colspan="7">TOTAL</td><td>' + new Intl.NumberFormat("US", formateador()).format(st) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(siva) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(santicipo) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(spendiente) + '</td>\n\
                  <td>' + new Intl.NumberFormat("US", formateador()).format(stotalpe) + '</td></tr>');
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
    $('#eliminarModal').modal();
    $('.modal-body').html('<input type="hidden" name="iddeudas" id="iddeudas" value="' + data['id'] + '">Esta seguro que desea eliminar?');
});

$('#eliminarModalBton').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EliminarDeudas",
        type: 'POST',
        data: {"deuda": $('#iddeudas').val(), "cedula": $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
                } else {
                    alert(responde);
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('#montoContrato').keyup(function () {
    var iv;
    if ($('input[name=rIVA]:checked').val() === "1") {
        iv = $("#montoContrato").val() * 0.12;
    } else {
        iv = 0.0;
    }
    $('#montoIva').val(iv.toFixed(2));
});

$('#montoAnticipo').keyup(function () {
    var iv = $("#montoContrato").val() - $('#montoAnticipo').val();
    $('#montoPendiente').val(iv.toFixed(2));
});

//Modificar Deuda
$("table").on('click', 'tbody tr td #modDeudas', function () {
    var data = $(this).data();
    $('[name="tipoDeuda"][value="' + data['tipo'] + '"]').prop("checked", true);
    $('#montoContrato').val(data['monto']);
    $('#montoPendiente').val(data['pendiente']);
    $('#montoAnticipo').val(data['anticipo']);
    $('#iddeudas').val(data['id']);
    $('#nmbOEI').val(data['oei']);
    $('#textNombreProy').val(data['proyecto']);
    $('#txtNomProceso').val(data['proceso']);
    $('#textContrato').val(data['contrato']);
    $('#montoIva').val(data['iva']);
    if (data['iva'] > 0) {
        $('[name="rIVA"][value="1"]').prop("checked", true);
    } else {
        $('[name="rIVA"][value="0"]').prop("checked", true);
    }
    $('#selectTipoCon').find('option[value="' + data['tcon'] + '"]').remove();
    $('#selectTipoCon').append('<option value="' + data['tcon'] + '" selected>' + data['tconnombre'] + '</option>');
    $('#selectFin').find('option[value="' + data['financiamiento'] + '"]').remove();
    $('#selectFin').append('<option value="' + data['financiamiento'] + '" selected>' + data['financiamientonombre'] + '</option>');
    $('#selectTipoPres').find('option[value="' + data['presupuesto'] + '"]').remove();
    $('#selectTipoPres').append('<option value="' + data['presupuesto'] + '" selected>' + data['presupuesto'] + '</option>');
    $('#frmAddProyecto').removeClass('d-none');
    $('#selectTipoCon').selectpicker('refresh');
    $('#selectFin').selectpicker('refresh');
    $('#selectTipoPres').selectpicker('refresh');
    window.location.href = '#frmAddProyecto';
    banDeuda = false;
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

$("table").on('click', 'tbody tr td #enviarDeuda', function () {
    var data = $(this).data();
    $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar?<input type="hidden" name="estadoproy" id="estadoproy" value="' + data['deuda'] + '">');
    $('#enviarModal').modal();
});

$('#enviarModalBton').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EnviarDeuda",
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