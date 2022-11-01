var options2 = {style: "currency", currency: "USD"};
var banservicios = true;
$(document).ready(function () {
    listaServiciosP();
    listaFechaActual();
});

function listaFechaActual() {
    $.ajax({
        url: "../proyecto?accion=ListarFecha",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#fechainicio').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
                $('#fechafin').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listaServiciosP() {
    $('#loader').removeClass("d-none");
    $('#listaServicios').empty();
    var a = $('#array').val();
    $.ajax({
        url: "../actividadReq?accion=ListaServiciosO",
        type: 'POST',
        data: {req: a},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var req, estado, es = this.actividad_id, div2, ssp = 0, ssp2 = 0;
                    req = this.req_id;
                    $.each(this.cuatri, function (indice, sp) {
                        if (sp.solicitud_estado !== 1) {
                            ssp++;
                        } else {
                            ssp2++;
                        }
                    });

//                    if ((this.solicitud_id == null || this.solicitud_id === 0) && this.actividad_id !== 47 && this.actividad_id !== 0 && ssp2 === this.cuatri.length) {
//                        div2 = '<i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i>';
//                    } else {
                    div2 = '<i class="fas fa-plus m-1" id="agregarS" data-req="' + this.req_id + '" data-nombre="' + this.req_nombre + '" title="Agregar"></i><i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i><i class="fas fa-location-arrow m-1" id="enviarS" data-id="' + this.req_id + '" data-cantidad="' + this.cuatri.length + '" title="Enviar"></i>';
//                    }
                    if (this.estado_nombre == null || this.estado_nombre === '' || this.estado_nombre == 'undefined') {
                        estado = '----';
                    } else {
                        estado = this.estado_nombre;
                    }
                    $('#listaServicios').append('<div class="encabezado_2 estilobody text-justify">' + this.req_descripcion + '</div><div class="encabezado_2 estilobody text-justify">' + this.req_nombre + '</div>\n\
                    <div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>\n\
                    <div class="encabezado_4 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>\n\
                    <div class="encabezado_4 estilobody justify-content-center">' + estado + '</div><div class="encabezado_4 estilobody text-center">' + div2 + '</div>\n\
                    <div style="display:none; background:rgba(0, 0, 0, 0.05);" id="lserv' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    total = total + this.req_costo_total + this.req_costo_unitario + this.req_costo_sin_iva;
                    if (this.cuatri.length > 0) {
                        $.each(this.cuatri, function (indice, sp) {
                            var div;
                            var tipo;
                            if (sp.actividad_id === 3) {
                                tipo = 'HONORARIO FIJO';
                            } else if (sp.actividad_id === 2) {
                                tipo = 'HONORARIO MENSUAL';
                            } else if (sp.actividad_id === 1) {
                                tipo = 'HORAS CLASE';
                            }


                            if (sp.solicitud_estado !== 1 && sp.solicitud_estado !== 32) {
                                div = '<i class="fas fa-edit" id="modserv" data-req="' + req + '" data-id="' + sp.req_id + '" data-tipo="' + sp.actividad_id + '" data-horas="' + sp.n_horas + '" data-fi="' + sp.fecha_inicio + '" data-ff="' + sp.fecha_fin + '" data-cantidad="' + sp.cantidad + '" data-cargo="' + sp.actividad_nombre + '" data-nombre="' + sp.req_nombre + '" data-apellido="' + sp.req_descripcion + '" data-total="' + sp.req_costo_total + '" data-cedula="' + sp.solicitud_cedula + '" data-observacion="' + sp.solestado_observacion + '" data-siniva="'+sp.req_costo_sin_iva+'"></i><i class="fas fa-trash" id="elimserv" data-id="' + sp.req_id + '"></i>';
                            } else {
                                div = sp.estado_nombre;
                            }
                            $('#listaServicios').children('div#lserv' + req).append('<div class="encabezado_2 estilobody text-justify">NOMBRE: ' + sp.req_nombre + '</div><div class="encabezado_2 estilobody text-justify">APELLIDO: ' + sp.req_descripcion + '</div><div class="encabezado_2 estilobody text-justify">CARGO: ' + sp.actividad_nombre + '</div>\n\
                            <div class="encabezado_5 estilobody justify-content-center">TIPO: ' + tipo + '</div><div class="encabezado_5 estilobody justify-content-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sp.req_costo_total) + '</div><div class="encabezado_4 estilobody text-center">' + div + '</div>');
                        });
                    }
                });
                $('#listaServicios').append('<div class="encabezado_completo estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
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

$('#example').on('click', 'div div #agregarS', function () {
    var data = $(this).data();
    $('#apellidoI').val('');
    $('#nombreser').val('');
    $('#reqidV').val(data['req']);
    $('#generarJ').modal();
    $('input[name=nhoras]').val('');
    $('input[name=sueldoH]').val('');
    $('input[name=fechainicio]').val('');
    $('input[name=fechafin]').val('');
    $('input[name=sueldoMensual]').val('');
    $('input[name=totalpagar]').val('');
    $('input[name=cedulaser]').val('');
    $('textarea[name=observoficio]').val('');
    $('select[name=rectoring]').val('0');
    $('#cargo').val('');
    banservicios = true;
});

$('#example').on('click', 'div div #listaS', function () {
    var data = $(this).data();
    $('#lserv' + data['req']).slideToggle();
});

$('#rectoring').on('change', function () {
    var id = $(this).val();
    if (id === "1") {
        $('#horas').removeClass('d-none');
        $('#shoras').removeClass('d-none');
        $('#fehchai').addClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').addClass('d-none');
    } else if (id === "2") {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').removeClass('d-none');
        $('#sueldo').removeClass('d-none');
        $('#personalmed').removeClass('d-none');
    } else if (id === "3") {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#fehchai').addClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#tpagas').removeClass('d-none');
    }
});

//Ingresar servicios profesionales
$('#modalGuardarJust').on('click', function () {
    var iva;
    var horas = $('input[name=nhoras]').val();
    var shoras = $('input[name=sueldoH]').val();
    var fechai = $('input[name=fechainicio]').val();
    var fechaf = $('input[name=fechafin]').val();
    var sueldoMensual = $('input[name=sueldoMensual]').val();
    var totalpagar = $('input[name=totalpagar]').val();
    var tipo = $('select[name=rectoring]').val();
    var req = $('#reqidV').val();
    var nombre = $('#nombreser').val();
    var apellido = $('#apellidoI').val();
    var cedula = $('#cedulaProyecto').val();
    var serv = $('#servId').val();
    var cargo = $('#cargo').val();
    var ced = $('#cedulaser').val();
    var observacion = $('#observoficio').val();
    if ($('input:checkbox[name=personalm]:checked').val() == 'undefiden' || $('input:checkbox[name=personalm]:checked').val() == null || $('input:checkbox[name=personalm]:checked').val() == '') {
        iva = 0;
    } else {
        iva = $('input:checkbox[name=personalm]:checked').val();
    }
    if (observacion == null || observacion === 'undefined' || observacion === '') {
        alert('Debe ingresar la observaci\u00f3n.');
    } else {
        if (banservicios) {
            $.ajax({
                url: "../actividadReq?accion=IngresarServiciosPO",
                type: 'POST',
                data: {horas: horas, shoras: shoras, fechai: fechai, fechaf: fechaf, sueldoMensual: sueldoMensual,
                    totalpagar: totalpagar, nombre: nombre, req: req, cedula: cedula, tipo: tipo, cargo: cargo, apellido: apellido, iva: iva, ced: ced, observacion: observacion},
                dataType: 'json'
            })
                    .done(function (response) {
                        if (response === "Correcto") {
                            $('#generarJ').modal('hide');
                            listaServiciosP();
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
        } else {
            $.ajax({
                url: "../actividadReq?accion=ModificarServiciosPO",
                type: 'POST',
                data: {horas: horas, shoras: shoras, fechai: fechai, fechaf: fechaf, sueldoMensual: sueldoMensual,
                    totalpagar: totalpagar, nombre: nombre, req: req, cedula: cedula, tipo: tipo, servicio: serv, apellido: apellido, cargo: cargo, cedulaser: ced, iva: iva, observacion: observacion},
                dataType: 'json'
            })
                    .done(function (response) {
                        if (response === "Correcto") {
                            $('#generarJ').modal('hide');
                            listaServiciosP();
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
    }
});

//Modificar servicios profesionales modal
$('#example').on('click', 'div div #modserv', function () {
    var data = $(this).data();
    var id = data['tipo'], tipo;
    $('#servId').val(data['id']);
    $('#cargo').val(data['cargo']);
    $('#reqidV').val(data['req']);
    $('#apellidoI').val(data['apellido']);
    $('#nombreser').val(data['nombre']);
    $('#cedulaser').val(data['cedula']);
    $('#observoficio').val(data['observacion']);
    $('#rectoring').find('option[value="' + data['tipo'] + '"]').remove();
    if (id === 1) {
        tipo = "Horas Clase";
    } else if (id === 2) {
        tipo = "Honorarios Mensuales";
    } else if (id === 3) {
        tipo = "Honorario Fijo";
    }
    $('#rectoring').append('<option value="' + data['tipo'] + '" selected="selected">' + tipo + '</option>');
    if (id === 1) {
        $('#horas').removeClass('d-none');
        $('#shoras').removeClass('d-none');
        $('#fehchai').addClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#nhoras').val(data['horas']);
        $('#sueldoH').val(data['cantidad']);
    } else if (id === 2) {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').removeClass('d-none');
        $('#personalmed').removeClass('d-none');
        $('#sueldo').removeClass('d-none');
        $('#fechainicio').val(data['fi']);
        $('#fechafin').val(data['ff']);
        $('#sueldoMensual').val(data['cantidad']);
    } else if (id === 3) {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#fehchai').addClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').removeClass('d-none');
        $('#totalpagar').val(data['siniva'].toFixed(2));
    }
    $('#generarJ').modal();
    banservicios = false;
});

$('#example').on('click', 'div div #elimserv', function () {
    var data = $(this).data();
    $('#idserveli').val(data['id']);
    $('#eliminarModal').modal();
});

$('#eliminarModalBton').on('click', function () {
    var cedula = $('#cedulaProyecto').val();
    var serv = $('#idserveli').val();
    $.ajax({
        url: "../actividadReq?accion=EliminarServiciosPO",
        type: 'POST',
        data: {servicio: serv, cedula: cedula},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    $('#eliminarModal').modal('hide');
                    listaServiciosP();
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

$('#example').on('click', 'div div #enviarS', function () {
    var data = $(this).data();
    if (data['cantidad'] > 0) {
        $('#idservenv').val(data['id']);
        $('#enviarModal').modal();
    } else {
        alert("Debe tener ingresado al menos un dato para enviar a talento humano.");
    }
});

$('#enviarModalBton').on('click', function () {
    var cedula = $('#cedulaProyecto').val();
    var serv = $('#idservenv').val();
    $.ajax({
        url: "../actividadReq?accion=EnviarServiciosPO",
        type: 'POST',
        data: {servicio: serv, cedula: cedula, estado: 1},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    $('#enviarModal').modal('hide');
                    listaServiciosP();
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