var options2 = {style: "currency", currency: "USD"};
var banservicios = true;
$(document).ready(function () {
    listaCertificacion();
    $('#fechain').datepicker();
    $('#fechaing').datepicker();
    $('#fechainUn').datepicker();
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
        url: "../solicitud?accion=ListaSolicitudesSolicitudUnif",
        type: 'POST',
        data: {solicitud: $('#solicitudid').val()},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    var req, total, estado, fecha, saldo = 0, sumcert = 0, sum = 0, div2;
                    req = this.req_id;
                    if (this.tc_nombre === "undefined" || this.tc_nombre == null) {
                        estado = '-----';
                    } else {
                        estado = this.tc_nombre;
                    }
                    if (this.req_iva === 1) {
                        total = this.req_costo_sin_iva * 1.12;
                    } else {
                        total = this.req_costo_sin_iva;
                    }
                    if (!validacion) {
                        div2 = '<i class="fas fa-angle-down" id="listaCP" data-req="' + this.req_id + '" title="Listar Certificaci\u00f3n"></i>';
                    } else {
                        div2 = '<input type="checkbox" id="reqfinmod" name="reqfinmod"><i class="fas fa-angle-down" id="listaCP" data-req="' + this.req_id + '" title="Listar Certificaci\u00f3n"></i>';
                        //div2 = '<i class="fas fa-plus" id="ingresarCP" data-req="' + this.req_id + '" data-costosi="' + this.req_costo_sin_iva + '" data-costot="' + total + '" title="Ingresar Certificaci\u00f3n"></i><i class="fas fa-angle-down" id="listaCP" data-req="' + this.req_id + '" title="Listar Certificaci\u00f3n"></i>';
                    }
                    $('#listaServicios').append('<div class="estilobody encabezado_4" style="justify-content: center;">' + this.req_id + '</div><div class="estilobody encabezado_7">' + this.req_nombre + '</div><div class="estilobody encabezado_2" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>\n\
                    <div class="estilobody encabezado_2" style="justify-content: center;">' + new Intl.NumberFormat("US", options2).format(total) + '</div><div class="estilobody encabezado_5" style="justify-content: center;">' + estado + '</div><div class="estilobody encabezado_4" data-req="' + this.req_id + '" data-cantidad="' + this.req_cantidad + '" data-costosi="' + this.req_costo_sin_iva + '" data-costot="' + total + '" data-nombre="' + this.req_nombre + '">' + div2 + '</div>\n\
                    <div style="display:none; background:rgba(0, 0, 0, 0.1); font-weight:bold;" id="certip' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    if (this.req.length > 0) {
                        var estadoreq, div;
                        $.each(this.req, function (indice, requ) {
                            sumcert = 0, sum = 0;
                            if (requ.req_iva === 1) {
                                total = requ.req_costo_sin_iva * 1.12;
                            } else {
                                total = requ.req_costo_sin_iva;
                            }
                            if (requ.estado_nombre == 'undefined' || requ.estado_nombre == '' || requ.estado_nombre == null) {
                                estadoreq = '---';
                            } else {
                                estadoreq = requ.estado_nombre;
                            }
                            if (!validacion) {
                                div = '<i class="fas fa-angle-down" id="listaCPUN" data-req="' + requ.req_id + '" title="Listar Certificaci\u00f3n"></i>';
                            } else {
                                div = '<i class="fas fa-plus" id="ingresarCP" data-req="' + requ.req_id + '" data-costosi="' + requ.req_costo_sin_iva + '" data-costot="' + total + '" title="Ingresar Certificaci\u00f3n"></i><i class="fas fa-angle-down" id="listaCPUN" data-req="' + requ.req_id + '" title="Listar Certificaci\u00f3n"></i>';
                            }
                            saldo = Math.round(requ.req_costo_total * 100) / 100;
                            $('#listaServicios').children('#certip' + req).append('<div class="encabezado_7 estilobody text-justify">' + requ.ag_nombre + '</div><div class="encabezado_4 estilobody text-justify">C\u00f3digo: ' + requ.req_id + '</div><div class="encabezado_2 estilobody text-justify">Nombre: ' + requ.req_nombre + '</div><div class="encabezado_5 estilobody" style="justify-content: center;">Valor: ' + new Intl.NumberFormat("US", options2).format(requ.req_costo_total) + '</div><div class="encabezado_8 estilobody" style="justify-content: center;">F: ' + requ.presupuesto_fuente + '</div><div class="encabezado_8 estilobody" style="justify-content: center;">I: ' + requ.presupuesto_renglo + '</div><div class="encabezado_5 estilobody" style="justify-content: center;">' + estadoreq + '</div><div class="encabezado_8 estilobody" style="justify-content: center;">' + div + '</div><div style="display:none; background:rgba(0, 0, 0, 0.1);" id="certipreq' + requ.req_id + '" class="align-self-center encabezado p-0">');
                            if (requ.req.length > 0) {
                                $.each(requ.req, function (indice, cp) {
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
                                        $('#listaServicios').children('#certip' + req).children('#certipreq' + requ.req_id).append('<div class="encabezado_5 estilobody text-justify m-0">C\u00f3digo: ' + cp.req_nombre + '</div><div class="encabezado_4 estilobody text-justify m-0" style="justify-content: center;">' + cp.tc_nombre + '</div><div class="encabezado_2 estilobody m-0" style="justify-content: center;">Valor: ' + new Intl.NumberFormat("US", options2).format(cp.req_costo_total) + '</div><div class="encabezado_2 estilobody m-0" style="justify-content: center;">Fecha aprobación: ' + fecha + '</div><div class="encabezado_5 estilobody m-0" style="justify-content: center;">Saldo: ' + new Intl.NumberFormat("US", options2).format(saldo) + '</div><div class="encabezado_2 estilobody m-0">Observación: ' + cp.ae_observacion + '</div>');
                                    } else {
                                        $('#listaServicios').children('#certip' + req).children('#certipreq' + requ.req_id).append('<div class="encabezado_4 estilobody text-justify m-0">C\u00f3digo: ' + cp.req_nombre + '</div><div class="encabezado_4 estilobody text-justify m-0" style="justify-content: center;">' + cp.tc_nombre + '</div><div class="encabezado_2 estilobody m-0" style="justify-content: center;">Valor: ' + new Intl.NumberFormat("US", options2).format(cp.req_costo_total) + '</div><div class="encabezado_2 estilobody m-0" style="justify-content: center;">Fecha aprobación: ' + fecha + '</div><div class="encabezado_5 estilobody m-0" style="justify-content: center;">Saldo: ' + new Intl.NumberFormat("US", options2).format(saldo) + '</div><div class="encabezado_5 estilobody m-0">Observación: ' + cp.ae_observacion + '</div><div class="encabezado_4 estilobody m-0" style="justify-content: center;"><i class="fas fa-edit" id="modificarCPSP" data-id="' + cp.req_id + '" data-rec="' + cp.ae_tiempo + '" data-tc="' + cp.tc_id + '" data-tcnombre="' + cp.tc_nombre + '" data-valor="' + cp.req_costo_total + '" data-codigo="' + cp.req_nombre + '" data-obs="' + cp.ae_observacion + '" data-fecha="' + cp.fecha_inicio + '" data-liquidacion="' + cp.unidad_id + '" title="Modificar"></i><i class="fas fa-trash" id="eliminarCPSP" data-id="' + cp.req_id + '" title="Eliminar"></i></div>');
                                    }
                                });
                            }
                        });
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

$('#listaServicios').on('click', '.encabezado .estilobody #listaCPUN', function () {
    var data = $(this).data();
    $('#certipreq' + data['req']).slideToggle();
});

$('#listaServicios').on('click', '.estilobody #ingresarCP', function () {
    var data = $(this).data();
    var iv = data['costot'] - data['costosi'];
    $('#reqidcp').val(data['req']);
    $('#valorcp').val(data['costosi']);
    $('#valorcpiva').val(iv.toFixed(2));
    $('#certpid').val('');
    $('#codigocp').val('');
    $('#rectoring').selectpicker('refresh');
    $('#codigoivaCP').removeClass('d-none');
    $('#montoIvaCP').removeClass('d-none');
    $('#generarCP').modal();
    $('#valoriva').val(iv);
    banservicios = true;
});

$('#listaServicios').on('click', '.encabezado .encabezado .estilobody #modificarCPSP', function () {
    var data = $(this).data();
    $('#certpid').val(data['id']);
    $('#codigocp').val(data['codigo']);
    $('#valorcp').val(data['valor']);
    $('#rectoring').find('option[value="' + data['tc'] + '"]').remove();
    $('#rectoring').append('<option value="' + data['tc'] + '" selected="selected">' + data['tcnombre'] + '</option>');
    if (data['tc'] == "1") {
        $('#recurrenteDiv').removeClass('d-none');
    } else {
        $('#recurrenteDiv').addClass('d-none');
    }
    if (data['rec'] == "1") {
        $('[name="recurrenteCert"][value="' + data['rec'] + '"]').prop("checked", true);
    } else {
        $('[name="recurrenteCert"][value="0"]').prop("checked", true);
    }
    if (data['liquidacion'] == "1") {
        $('[name="liquCert"][value="1"]').prop("checked", true);
    } else {
        $('[name="liquCert"][value="0"]').prop("checked", true);
    }
    if (data['fecha'] == null || data['fecha'] == 'undefined') {
        $('#fechain').val('');
    } else {
        $('#fechain').val(data['fecha']);
    }
    $('#rectoring').selectpicker('refresh');
    $('#codigoivaCP').addClass('d-none');
    $('#montoIvaCP').addClass('d-none');
    if (data['obs'] == "Sin observación") {
        $('#txtobservacion').val('');
    } else {
        $('#txtobservacion').val(data['obs']);
    }
    $('#fechain').val(data['fecha']);
    $('#generarCP').modal();
    banservicios = false;
});

$('#listaServicios').on('click', '.encabezado .estilobody #eliminarCPSP', function () {
    var data = $(this).data();
    $('#eliminarCPin').val(data['id']);
    $('#eliminarModal').modal();
});

$('#listaServicios').on('click', '.estilobody #listaCP', function () {
    var data = $(this).data();
    $('#certip' + data['req']).slideToggle();
});

$('#modalGuardarCP').on('click', function () {
    $('#loader').removeClass("d-none");
    if (banservicios) {
        $.ajax({
            url: "../solicitud?accion=IngresarCertificacion",
            type: 'POST',
            data: $("#frmIngresarCP").serialize(),
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#generarCP').modal('hide');
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
            url: "../solicitud?accion=ModificarCertificacion",
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

$('#btnAgregarCP').on('click', function () {
    $('#listaRequerimientos').empty();
    $('#codigocpg').val('');
    $('#codigocpivag').val('');
    var array = [];
    var sumi = 0, sumt = 0, iva;
    var options2 = {style: "currency", currency: "USD"};
    $('#listaServicios').find('div.encabezado_4').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'req': data.req,
                'costosi': data.costosi,
                'costot': data.costot,
                'fuente': data.fuente,
                'item': data.item,
                'nombre': data.nombre,
                'cantidad': data.cantidad
            });
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        sumi = sumi + array[i].costosi;
        sumt = sumt + array[i].costot;
        $('#listaRequerimientos').append('<tr><td class="text-center">' + array[i].req + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(array[i].costot) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].req + '" name="re" id="re" checked="checked" data-costoi="' + array[i].costosi + '" data-costot="' + array[i].costot + '" data-cantidad="' + array[i].cantidad + '"></td>\n\
                    </tr>');
    }
    iva = sumt - sumi
    $('#valorcpg').val(sumi.toFixed(2));
    $('#valorcpivag').val(iva.toFixed(2));
    $('#generarJ').modal();
});

$('#modalGuardarCPG').on('click', function () {
    var sol = $('#solicitudidcpg').val();
    var tipo = $('#rectoringg').val();
    var codigo = $('#codigocpg').val();
    var codigoi = $('#codigocpivag').val();
    var observacion = $('#txtobservaciong').val();
    var porcentaje = $('#porcanticipog').val();
    var fecha = $('#fechainUn').val();
    var recurrente = $('input[name=recurrenteCertUn]:checked').val();
    var liquidacion = $('input[name=liquCertUn]:checked').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(data['costoi']);
        rem.push(data['costot']);
        rem.push(data['cantidad']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    var formData = new FormData();
    formData.append('sol', sol);
    formData.append('req', req);
    formData.append('tipo', tipo);
    formData.append('codigo', codigo);
    formData.append('codigoi', codigoi);
    formData.append('observacion', observacion);
    formData.append('porcentaje', porcentaje);
    formData.append('recurrente', recurrente);
    formData.append('liquidacion', liquidacion);
    formData.append('fechain', fecha);
    $.ajax({
        url: "../solicitud?accion=IngresarCertificacionPGU",
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

$('#valorcp').keyup(function () {
    if ($('#valoriva').val() > 0) {
        var iv = $("#valorcp").val() * 0.12;
        $('#valorcpiva').val(iv.toFixed(2));
    } else {
        $('#valorcpiva').val(0.0);
    }
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
    if ($(this).val() === "1") {
        $('#codigolblg').html("C\u00f3digo:");
        $('#valorlblg').html("Valor:");
        $('#porcentajecompg').addClass('d-none');
        $('#recurrenteDivUn').removeClass('d-none');
    } else if ($(this).val() === "3" || $(this).val() === "4" || $(this).val() === "5") {
        $('#codigolblg').html("C\u00f3digo:");
        $('#valorlblg').html("Valor:");
        $('#porcentajecompg').addClass('d-none');
        $('#recurrenteDivUn').addClass('d-none');
    } else if ($(this).val() === "2") {
        $('#codigolblg').html("Cur de comprometido:");
        $('#valorlblg').html("Valor anticipo:");
        $('#porcentajecompg').removeClass('d-none');
        $('#recurrenteDivUn').addClass('d-none');
    }
});

$('#eliminarModalBton').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EliminarCertificacion",
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