var options2 = {style: "currency", currency: "USD"};
var banservicios = true;
$(document).ready(function () {
    listaCertificacion();
    $('#fechain').datepicker();
    $('#fechainsp').datepicker();
    $('#fechaing').datepicker();
    $('#fechainm').datepicker();
});

function listaCertificacion() {
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
    $('#listaServicios').empty();
    $.ajax({
        url: "../solicitud?accion=ListaCertificacionPOP",
        type: 'POST',
        data: {solicitud: $('#solicitudid').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var tmonto, tiva, tanticipo;
                $.each(response, function () {
                    var req, total, estado, fecha, saldo = 0, sumcert = 0, sum = 0, div2;
                    req = this.req_id;
                    if (this.tc_nombre === "undefined" || this.tc_nombre == null) {
                        estado = '-----';
                    } else {
                        estado = this.tc_nombre;
                    }

                    tmonto = this.req_costo_unitario;
                    tiva = this.req_costo_sin_iva;
                    tanticipo = this.req_costo_total;
                    saldo = Math.round((tmonto + tiva + tanticipo) * 100) / 100;
                    if (!validacion) {
                        if (this.cuatri.length > 0) {
                            div2 = '<i class="fas fa-angle-down" id="listaCP" data-req="' + this.req_id + '" data-serv="' + this.cuatri + '" title="Listar Servicios Profesionals"></i><i class="fas fa-th-list" data-req="' + this.req_id + '" id="estructuraDeuda" title="Listar estructura presupuestaria"></i>';
                        } else {
                            div2 = '<i class="fas fa-angle-down" id="listaCP" data-req="' + this.req_id + '" data-serv="' + this.cuatri + '" title="Listar Certificaci\u00f3n"></i><i class="fas fa-th-list" data-req="' + this.req_id + '" id="estructuraDeuda" title="Listar estructura presupuestaria"></i>';
                        }
                        $('#listaServicios').append('<div class="estilobody encabezado_4" style="justify-content: center;">' + this.req_id + '</div><div class="estilobody encabezado_2">' + this.req_nombre + '</div><div class="estilobody encabezado_5" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>\n\
                    <div class="estilobody encabezado_5" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div><div class="estilobody encabezado_5" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div><div class="estilobody encabezado_5" style="justify-content: center;">' + estado + '</div><div class="estilobody encabezado_4 p-2 centro" data-req="' + this.req_id + '" data-costomonto="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_sin_iva + '" data-costoanticipo="' + this.req_costo_total + '" data-nombre="' + this.req_nombre + '">' + div2 + '</div>\n\
                    <div style="display:none; background:rgba(0, 0, 0, 0.1);" id="certip' + this.req_id + '" class="align-self-center encabezado p-0"></div><div style="display:none; background:rgba(0, 0, 0, 0.1);" id="estrdeudas' + this.req_id + '" class="align-self-center encabezado p-0"></div><div style="display:none; background:rgba(0, 0, 0, 0.1); font-weigth:bold" id="servicios' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    } else {
                        if (this.cuatri.length > 0) {
                            div2 = '<i class="fas fa-angle-down" id="listaCP" data-req="' + this.req_id + '" data-serv="' + this.cuatri + '" title="Listar Servicios Profesionals"></i><i class="fas fa-th-list" data-req="' + this.req_id + '" id="estructuraDeuda" title="Listar estructura presupuestaria"></i>';
                        } else {
                            div2 = '<input type="checkbox" id="reqfinmod" name="reqfinmod"><i class="fas fa-plus" id="ingresarCP" data-req="' + this.req_id + '" data-costomonto="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_sin_iva + '" data-costoanticipo="' + this.req_costo_total + '" title="Ingresar Certificaci\u00f3n"></i><i class="fas fa-angle-down" id="listaCP" data-req="' + this.req_id + '" data-serv="' + this.cuatri + '" title="Listar Certificaci\u00f3n"></i><i class="fas fa-th-list" data-req="' + this.req_id + '" id="estructuraDeuda" title="Listar estructura presupuestaria"></i>';

                        }
                        $('#listaServicios').append('<div class="estilobody encabezado_4" style="justify-content: center;">' + this.req_id + '</div><div class="estilobody encabezado_2">' + this.req_nombre + '</div><div class="estilobody encabezado_5" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>\n\
                    <div class="estilobody encabezado_5" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div><div class="estilobody encabezado_5" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div><div class="estilobody encabezado_5" style="justify-content: center;">' + estado + '</div><div class="estilobody encabezado_4  p-2 centro" data-req="' + this.req_id + '" data-costomonto="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_sin_iva + '" data-costoanticipo="' + this.req_costo_total + '" data-nombre="' + this.req_nombre + '">' + div2 + '</div>\n\
                    <div style="display:none; background:rgba(0, 0, 0, 0.1);" id="certip' + this.req_id + '" class="align-self-center encabezado p-0"></div><div style="display:none; background:rgba(0, 0, 0, 0.1);" id="estrdeudas' + this.req_id + '" class="align-self-center encabezado p-0"></div><div style="display:none; background:rgba(0, 0, 0, 0.1); font-weigth:bold" id="servicios' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    }
                    if (this.req.length > 0) {
                        $.each(this.req, function (indice, cp) {
                            if (cp.fecha_inicio == null || cp.fecha_inicio === "undefined") {
                                fecha = '---';
                            } else {
                                fecha = cp.fecha_inicio;
                            }
                            if (cp.tc_id === 1) {
                                sumcert = sumcert + cp.req_costo_total;
                            } else {
                                sum = sum + 1;
                            }
                            if (sum === 1) {
                                saldo = Math.round(sumcert * 100) / 100;
                            }
                            if (saldo === 0) {
                                saldo = cp.req_costo_total;
                            }
                            saldo = Math.round((saldo - cp.req_costo_total) * 100) / 100;
                            if (!validacion) {
                                $('#listaServicios').children('#certip' + req).append('<div class="encabezado_5 estilobody text-justify">C\u00f3digo: ' + cp.req_nombre + '</div><div class="encabezado_4 estilobody text-justify">' + cp.tc_nombre + '</div><div class="encabezado_7 estilobody" style="justify-content: center;">Valor: ' + new Intl.NumberFormat("US", options2).format(cp.req_costo_total) + '</div><div class="encabezado_2 estilobody" style="justify-content: center;">Fecha aprobación: ' + fecha + '</div><div class="encabezadp_5 estilobody">Saldo: ' + new Intl.NumberFormat("US", options2).format(saldo) + '</div><div class="encabezado_5 estilobody" style="justify-content: center;"></div>');
                            } else {
                                $('#listaServicios').children('#certip' + req).append('<div class="encabezado_5 estilobody text-justify">C\u00f3digo: ' + cp.req_nombre + '</div><div class="encabezado_4 estilobody text-justify">' + cp.tc_nombre + '</div><div class="encabezado_7 estilobody" style="justify-content: center;">Valor: ' + new Intl.NumberFormat("US", options2).format(cp.req_costo_total) + '</div><div class="encabezado_2 estilobody" style="justify-content: center;">Fecha aprobación: ' + fecha + '</div><div class="encabezadp_5 estilobody">Saldo: ' + new Intl.NumberFormat("US", options2).format(saldo) + '</div><div class="encabezado_5 estilobody" style="justify-content: center;"><i class="fas fa-edit" id="modificarCP" data-id="' + cp.req_id + '" data-rec="' + cp.ae_tiempo + '" data-tc="' + cp.tc_id + '" data-tcnombre="' + cp.tc_nombre + '" data-valor="' + cp.req_costo_total + '" data-codigo="' + cp.req_nombre + '" data-obs="' + cp.ae_observacion + '" data-fecha="' + cp.fecha_inicio + '" data-liquidacion="'+cp.unidad_id+'" title="Modificar"></i><i class="fas fa-trash" id="eliminarCP" data-id="' + cp.req_id + '" title="Eliminar"></i></div>');
                            }
                        });
                    }

                    if (this.actividad_eval.length > 0) {
                        $.each(this.actividad_eval, function (indice, est) {
                            if (tmonto > 0 && est.actividad_tipo === 0) {
                                $('#listaServicios').children('#estrdeudas' + req).append('<div class="encabezado_2 estilobody text-justify">Tipo: Monto Pendiente</div><div class="encabezado_2 estilobody text-justify">Programa: ' + est.presupuesto_programa + '</div><div class="encabezado_2 estilobody text-justify">Proyecto: ' + est.presupuesto_proyecto + '</div><div class="encabezado_2 estilobody text-justify">Renglon: ' + est.presupuesto_renglo + '</div><div class="encabezado_2 estilobody text-justify">Fuente: ' + est.presupuesto_fuente + '</div>');
                            }
                            if (tiva > 0 && est.actividad_tipo === 1) {
                                $('#listaServicios').children('#estrdeudas' + req).append('<div class="encabezado_2 estilobody text-justify">Tipo: Monto Pendiente IVA</div><div class="encabezado_2 estilobody text-justify">Programa: ' + est.presupuesto_programa + '</div><div class="encabezado_2 estilobody text-justify">Proyecto: ' + est.presupuesto_proyecto + '</div><div class="encabezado_2 estilobody text-justify">Renglon: ' + est.presupuesto_renglo + '</div><div class="encabezado_2 estilobody text-justify">Fuente: ' + est.presupuesto_fuente + '</div>');
                            }
                            if (tanticipo > 0 && est.actividad_tipo === 2) {
                                $('#listaServicios').children('#estrdeudas' + req).append('<div class="encabezado_2 estilobody text-justify">Tipo: Monto Anticipo</div><div class="encabezado_2 estilobody text-justify">Programa: ' + est.presupuesto_programa + '</div><div class="encabezado_2 estilobody text-justify">Proyecto: ' + est.presupuesto_proyecto + '</div><div class="encabezado_2 estilobody text-justify">Renglon: ' + est.presupuesto_renglo + '</div><div class="encabezado_2 estilobody text-justify">Fuente: ' + est.presupuesto_fuente + '</div>');
                            }
                        });
                    }
                    if (this.cuatri.length > 0) {
                        var tipo, div, estadosp;
                        $.each(this.cuatri, function (indice, ser) {
                            sumcert = 0, sum = 0;
                            if (ser.actividad_id === 1) {
                                tipo = 'HORAS CLASE';
                            } else if (ser.actividad_id === 2) {
                                tipo = 'HONORARIOS MENSUALES';
                            } else if (ser.actividad_id === 3) {
                                tipo = 'HONORARIO FIJO';
                            } else {
                                tipo = '---';
                            }
                            if (ser.ae_observacion == null || ser.ae_observacion == 'undefined') {
                                estadosp = '---';
                            } else {
                                estadosp = ser.ae_observacion;
                            }
                            if (!validacion) {
                                div = '';
                            } else {
                                div = '<i class="fas fa-plus" id="ingresarCPsp" data-req="' + ser.req_id + '" data-costosi="' + ser.req_costo_sin_iva + '" data-costot="' + ser.req_costo_total + '" data-serv="1" title="Ingresar Certificaci\u00f3n"></i><i class="fas fa-angle-down" id="listaCPSP" data-req="' + ser.req_id + '" title="Listar Certificaci\u00f3n"></i>';
                            }
                            saldo = Math.round(ser.req_costo_total * 100) / 100;
                            $('#listaServicios').children('#servicios' + req).append('<div class="encabezado_9 estilobody text-justify" style="font-weight:bold;">Nombres: ' + ser.req_nombre + ' ' + ser.req_descripcion + '</div><div class="encabezado_2 estilobody text-justify" style="font-weight:bold;">Tipo: ' + tipo + '</div><div class="encabezado_2 estilobody" style="justify-content: center; font-weight:bold;">Valor: ' + new Intl.NumberFormat("US", options2).format(ser.req_costo_total) + '</div><div class="encabezado_5 estilobody" style="justify-content: center; font-weight:bold;">Estado: ' + estadosp + '</div><div class="encabezado_5 estilobody" style="justify-content: center;">' + div + '</div><div style="display:none; background:rgba(0, 0, 0, 0.1);" id="certipserv' + ser.req_id + '" class="align-self-center encabezado p-0"></div>');
                            if (ser.cuatri.length > 0) {
                                $.each(ser.cuatri, function (indice, cp) {
                                    if (cp.fecha_inicio == null || cp.fecha_inicio === "undefined") {
                                        fecha = '---';
                                    } else {
                                        fecha = cp.fecha_inicio;
                                    }
                                    if (cp.tc_id === 1) {
                                        sumcert = sumcert + cp.req_costo_total;
                                    } else {
                                        sum = sum + 1;
                                    }
                                    if (sum === 1) {
                                        saldo = Math.round(sumcert * 100) / 100;
                                    }
                                    saldo = Math.round((saldo - cp.req_costo_total) * 100) / 100;
                                    if (!validacion) {
                                        $('#listaServicios').children('#servicios' + req).children('#certipserv' + ser.req_id).append('<div class="encabezado_5 estilobody text-justify">C\u00f3digo: ' + cp.req_nombre + '</div><div class="encabezado_4 estilobody text-justify">' + cp.tc_nombre + '</div><div class="encabezado_7 estilobody" style="justify-content: center;">Valor: ' + new Intl.NumberFormat("US", options2).format(cp.req_costo_total) + '</div><div class="encabezado_2 estilobody" style="justify-content: center;">Fecha aprobación: ' + fecha + '</div><div class="encabezadp_5 estilobody">Saldo: ' + new Intl.NumberFormat("US", options2).format(saldo) + '</div><div class="encabezado_5 estilobody" style="justify-content: center;"></div>');
                                    } else {
                                        $('#listaServicios').children('#servicios' + req).children('#certipserv' + ser.req_id).append('<div class="encabezado_5 estilobody text-justify">C\u00f3digo: ' + cp.req_nombre + '</div><div class="encabezado_4 estilobody text-justify">' + cp.tc_nombre + '</div><div class="encabezado_7 estilobody" style="justify-content: center;">Valor: ' + new Intl.NumberFormat("US", options2).format(cp.req_costo_total) + '</div><div class="encabezado_2 estilobody" style="justify-content: center;">Fecha aprobación: ' + fecha + '</div><div class="encabezadp_5 estilobody">Saldo: ' + new Intl.NumberFormat("US", options2).format(saldo) + '</div><div class="encabezado_5 estilobody" style="justify-content: center;"><i class="fas fa-edit" id="modificarCPSP" data-id="' + cp.req_id + '" data-rec="' + cp.ae_tiempo + '" data-tc="' + cp.tc_id + '" data-tcnombre="' + cp.tc_nombre + '" data-valor="' + cp.req_costo_total + '" data-codigo="' + cp.req_nombre + '" data-obs="' + cp.ae_observacion + '" data-fecha="' + cp.fecha_inicio + '" title="Modificar"></i><i class="fas fa-trash" id="eliminarCPSP" data-id="' + cp.req_id + '" title="Eliminar"></i></div>');
                                    }
                                });
                            }
                        })
                    }
                });
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#listaServicios').on('click', '.estilobody #ingresarCP', function () {
    var data = $(this).data();
    $('#reqidcp').val(data['req']);
    $('#valorcp').val(data['costomonto']);
    $('#valorcph').val(data['costomonto']);
    if (data['costomonto'] > 0) {
        $('#certificacionMonto').removeClass('d-none');
    } else {
        $('#certificacionMonto').addClass('d-none');
    }
    $('#valorcpiva').val(data['costoiva']);
    $('#valorcpivah').val(data['costoiva']);
    if (data['costoiva'] > 0) {
        $('#certificacionIVA').removeClass('d-none');
    } else {
        $('#certificacionIVA').addClass('d-none');
    }
    $('#valorcpAn').val(data['costoanticipo']);
    $('#valorcpAnh').val(data['costoanticipo']);
    if (data['costoanticipo'] > 0) {
        $('#certificacionAnti').removeClass('d-none');
    } else {
        $('#certificacionAnti').addClass('d-none');
    }
    $('#certpid').val('');
    $('#codigocp').val('');
    $('#codigocpiva').val('');
    $('#codigocpAn').val('');
    $('#fechain').val('');
    $('#rectoring').selectpicker('refresh');
    $('#generarCP').modal();
    banservicios = true;
});

$('#listaServicios').on('click', '.encabezado .estilobody #ingresarCPsp', function () {
    var data = $(this).data();
    var iv = data['costot'] - data['costosi'];
    $('#reqidcpsp').val(data['req']);
    $('#valorcpsp').val(data['costosi']);
    $('#valorcpivasp').val(iv.toFixed(2));
    $('#certpidsp').val('');
    $('#codigocpsp').val('');
    $('#fechainsp').val('');
    $('#rectoringsp').selectpicker('refresh');
    $('#codigoivaCPsp').removeClass('d-none');
    $('#montoIvaCPsp').removeClass('d-none');
    $('#generarCPSP').modal();
    $('#valorivasp').val(iv);
    banservicios = true;
});

$('#listaServicios').on('click', '.encabezado .estilobody #modificarCP', function () {
    var data = $(this).data();
    $('#certpidm').val(data['id']);
    $('#codigocpm').val(data['codigo']);
    $('#valorcpm').val(data['valor']);
    $('#txtobservacionm').val(data['obs']);
    $('#fechainm').val(data['fecha']);
    $('#rectoringm').find('option[value="' + data['tc'] + '"]').remove();
    $('#rectoringm').append('<option value="' + data['tc'] + '" selected="selected">' + data['tcnombre'] + '</option>');
    if (data['tc'] == "1") {
        $('#recurrenteDivM').removeClass('d-none');
    } else {
        $('#recurrenteDivM').addClass('d-none');
    }
    if (data['rec'] == "1") {
        $('[name="recurrenteCertm"][value="' + data['rec'] + '"]').prop("checked", true);
    } else {
        $('[name="recurrenteCertm"][value="0"]').prop("checked", true);
    }
    if (data['liquidacion'] == "1") {
        $('[name="liquCertm"][value="1"]').prop("checked", true);
    } else {
        $('[name="liquCertm"][value="0"]').prop("checked", true);
    }
    $('#rectoringm').selectpicker('refresh');
    $('#modificarCP').modal();
    banservicios = false;
});

$('#listaServicios').on('click', '.encabezado .estilobody #modificarCPSP', function () {
    var data = $(this).data();
    $('#certpidsp').val(data['id']);
    $('#codigocpsp').val(data['codigo']);
    $('#valorcpsp').val(data['valor']);
    $('#rectoringsp').find('option[value="' + data['tc'] + '"]').remove();
    $('#rectoringsp').append('<option value="' + data['tc'] + '" selected="selected">' + data['tcnombre'] + '</option>');
    if (data['tc'] == "1") {
        $('#recurrenteDivsp').removeClass('d-none');
    } else {
        $('#recurrenteDivsp').addClass('d-none');
    }
    if (data['rec'] == "1") {
        $('[name="recurrenteCertsp"][value="' + data['rec'] + '"]').prop("checked", true);
    } else {
        $('[name="recurrenteCertsp"][value="0"]').prop("checked", true);
    }
    $('#fechainsp').val(data['fecha']);
    $('#rectoringsp').selectpicker('refresh');
    $('#codigoivaCPsp').addClass('d-none');
    $('#montoIvaCPsp').addClass('d-none');
    $('#generarCPSP').modal();
    banservicios = false;
});

$('#listaServicios').on('click', '.estilobody #listaCP', function () {
    var data = $(this).data();
    if (data['serv'].length > 0) {
        $('#servicios' + data['req']).slideToggle();
    } else {
        $('#certip' + data['req']).slideToggle();
    }
});

$('#listaServicios').on('click', '.encabezado .estilobody #listaCPSP', function () {
    var data = $(this).data();
    $('#certipserv' + data['req']).slideToggle();
});

$('#listaServicios').on('click', '.estilobody #estructuraDeuda', function () {
    var data = $(this).data();
    $('#estrdeudas' + data['req']).slideToggle();
});

$('#listaServicios').on('click', '.encabezado .estilobody #eliminarCP', function () {
    var data = $(this).data();
    $('#eliminarCPin').val(data['id']);
    $('#eliminarModal').modal();
});

$('#listaServicios').on('click', '.encabezado .estilobody #eliminarCPSP', function () {
    var data = $(this).data();
    $('#eliminarCPinsp').val(data['id']);
    $('#eliminarModalSP').modal();
});

$('#modalGuardarCP').on('click', function () {
    $('#loader').removeClass("d-none");
    if ($('#valorcph').val() > 0 && $('#codigocp').val() == '') {
        alert("Debe ingresar todos los campos.");
    } else if ($('#valorcpivah').val() > 0 && $('#codigocpiva').val() == '') {
        alert("Debe ingresar todos los campos.");
    } else if ($('#valorcpAnh').val() > 0 && $('#codigocpAn').val() == '' && $('#rectoring').val() !== "4") {
        alert("Debe ingresar todos los campos.");
    } else {
        $.ajax({
            url: "../solicitud?accion=IngresarCertificacionOP",
            type: 'POST',
            data: $("#frmIngresarCP").serialize(),
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        window.location.reload();
                    } else {
                        alert(response);
                    }
                    $('#loader').addClass('d-none');
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                    $('#loader').addClass('d-none');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
});

$('#modalGuardarCPsp').on('click', function () {
    $('#loader').removeClass("d-none");
    if (banservicios) {
        $.ajax({
            url: "../solicitud?accion=IngresarCertificacionSPOP",
            type: 'POST',
            data: $("#frmIngresarCPsp").serialize(),
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        window.location.reload();
                    } else {
                        alert(response);
                    }
                    $('#loader').addClass('d-none');
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                    $('#loader').addClass('d-none');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    } else {
        $.ajax({
            url: "../solicitud?accion=ModificarCertificacionSPOP",
            type: 'POST',
            data: $("#frmIngresarCPsp").serialize(),
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        window.location.reload();
                    } else {
                        alert(response);
                    }
                    $('#loader').addClass('d-none');
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                    $('#loader').addClass('d-none');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
});

$('#modalGuardarCPM').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=ModificarCertificacionOP",
        type: 'POST',
        data: $("#frmIngresarCPM").serialize(),
        dataType: 'json'
    })
            .done(function (response) {
                if (response === 'Correcto') {
                    window.location.reload();
                } else {
                    alert(response);
                }
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('#btnAgregarCP').on('click', function () {
    $('#listaRequerimientos').empty();
    $('#codigocpg').val('');
    $('#codigocpivag').val('');
    $('#fechaing').val('');
    var array = [];
    var monto = 0, iva = 0, anticipo = 0;
    var options2 = {style: "currency", currency: "USD"};
    $('#listaServicios').find('div.encabezado_4').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'req': data.req,
                'costomonto': data.costomonto,
                'costoiva': data.costoiva,
                'costoanticipo': data.costoanticipo,
                'nombre': data.nombre
            });
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        monto = monto + array[i].costomonto;
        iva = iva + array[i].costoiva;
        anticipo = anticipo + array[i].costoanticipo;
        $('#listaRequerimientos').append('<tr><td class="text-center">' + array[i].req + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(array[i].costomonto) + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(array[i].costoiva) + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(array[i].costoanticipo) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].req + '" name="re" id="re" checked="checked" data-costo="' + array[i].costomonto + '" data-costoiva="' + array[i].costoiva + '" data-costoanticipo="' + array[i].costoanticipo + '"></td>\n\
                    </tr>');
    }

    if (monto > 0) {
        $('#certificacionMontol').removeClass('d-none');
        $('#valorcpl').val(monto.toFixed(2)).attr('readonly', true);
    } else {
        $('#certificacionMontol').addClass('d-none');
    }
    if (iva > 0) {
        $('#certificacionIVAl').removeClass('d-none');
        $('#valorcpival').val(iva.toFixed(2)).attr('readonly', true);
    } else {
        $('#certificacionIVAl').addClass('d-none');
    }
    if (anticipo > 0) {
        $('#certificacionAntil').removeClass('d-none');
        $('#valorcpAnl').val(anticipo.toFixed(2)).attr('readonly', true);
    } else {
        $('#certificacionAntil').addClass('d-none');
    }
    $('#generarJ').modal();
});

$('#modalGuardarCPG').on('click', function () {
    var tipocer = $('#rectoringgl').val();
    var codigomonto = $('#codigocpl').val();
    var codigoiva = $('#codigocpival').val();
    var observacion = $('#txtobservaciongl').val();
    var codigoanticipo = $('#codigocpAnl').val();
    var fecha = $('#fechaing').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(data['costo']);
        rem.push(data['costoiva']);
        rem.push(data['costoanticipo']);
        re.push(rem);
    });
    if ($('#valorcpl').val() > 0 && $('#codigocpl').val() == '') {
        alert("Debe ingresar todos los campos.");
    } else if ($('#valorcpival').val() > 0 && $('#codigocpival').val() == '') {
        alert("Debe ingresar todos los campos.");
    } else if ($('#valorcpAnl').val() > 0 && $('#codigocpAnl').val() == '' && $('#rectoringgl').val() !== "4") {
        alert("Debe ingresar todos los campos.");
    } else {
        var req = JSON.stringify(re);
        var formData = new FormData();
        formData.append('tipo', tipocer);
        formData.append('req', req);
        formData.append('codigomonto', codigomonto);
        formData.append('codigoiva', codigoiva);
        formData.append('codigoanticipo', codigoanticipo);
        formData.append('observacion', observacion);
        formData.append('solicitud', $('#solicitudid').val());
        formData.append('fechain', fecha);
        $.ajax({
            url: "../solicitud?accion=IngresarCertificacionOPC",
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

$('#eliminarModalBton').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EliminarCertificacionVP",
        type: 'POST',
        data: {id: $('#eliminarCPin').val(), usuario: $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === 'Correcto') {
                    window.location.reload();
                } else {
                    alert(response);
                }
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('#eliminarModalBtonsp').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EliminarCertificacionSPOP",
        type: 'POST',
        data: {id: $('#eliminarCPinsp').val(), usuario: $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === 'Correcto') {
                    window.location.reload();
                } else {
                    alert(response);
                }
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('#rectoring').change(function () {
    if ($(this).val() === "1") {
        $('#codigolbl').html("C\u00f3digo:");
        $('#valorlbl').html("Valor:");
        $('#porcentajecomp').addClass('d-none');
        $('#recurrenteDiv').removeClass('d-none');
    } else if ($(this).val() === "3" || $(this).val() === "4" || $(this).val() === "5") {
        $('#codigolbl').html("C\u00f3digo:");
        $('#valorlbl').html("Valor:");
        $('#porcentajecomp').addClass('d-none');
        $('#recurrenteDiv').addClass('d-none');
    } else if ($(this).val() === "2") {
        $('#codigolbl').html("Cur de comprometido:");
        $('#valorlbl').html("Valor anticipo:");
        $('#porcentajecomp').removeClass('d-none');
        $('#recurrenteDiv').addClass('d-none');
    }
});

$('#rectoringg').change(function () {
    if ($(this).val() === "1" || $(this).val() === "3" || $(this).val() === "4" || $(this).val() === "5") {
        $('#codigolblg').html("C\u00f3digo:");
        $('#valorlblg').html("Valor:");
        $('#porcentajecompg').addClass('d-none');
    } else if ($(this).val() === "2") {
        $('#codigolblg').html("Cur de comprometido:");
        $('#valorlblg').html("Valor anticipo:");
        $('#porcentajecompg').removeClass('d-none');
    }
});

$('#rectoringsp').change(function () {
    if ($(this).val() === "1") {
        $('#codigolblsp').html("C\u00f3digo:");
        $('#valorlblsp').html("Valor:");
        $('#porcentajecompsp').addClass('d-none');
        $('#recurrenteDivsp').removeClass('d-none');
    } else if ($(this).val() === "3" || $(this).val() === "4" || $(this).val() === "5") {
        $('#codigolblsp').html("C\u00f3digo:");
        $('#valorlblsp').html("Valor:");
        $('#porcentajecompsp').addClass('d-none');
        $('#recurrenteDivsp').addClass('d-none');
    } else if ($(this).val() === "2") {
        $('#codigolblsp').html("Cur de comprometido:");
        $('#valorlblsp').html("Valor anticipo:");
        $('#porcentajecompsp').removeClass('d-none');
        $('#recurrenteDivsp').addClass('d-none');
    }
});

$('#rectoringg').change(function () {
    if ($(this).val() === "1" || $(this).val() === "3" || $(this).val() === "4" || $(this).val() === "5") {
        $('#codigolblg').html("C\u00f3digo:");
        $('#valorlblg').html("Valor:");
        $('#porcentajecompg').addClass('d-none');
    } else if ($(this).val() === "2") {
        $('#codigolblg').html("Cur de comprometido:");
        $('#valorlblg').html("Valor anticipo:");
        $('#porcentajecompg').removeClass('d-none');
    }
});