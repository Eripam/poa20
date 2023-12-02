var options2 = {style: "currency", currency: "USD"};
var banservicios = true;
$(document).ready(function () {
    listaServiciosP();
});

function listaFechaActual() {
    var f = sumarDias(new Date(), 4), fi;
    var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
    if (diasSemana[f.getDay()] === "Sabado" || diasSemana[f.getDay()] === "Domingo" || diasSemana[f.getDay()] === "Lunes" || diasSemana[f.getDay()] === "Martes") {
        f = sumarDias(new Date(), 6);
        fi = f.getDate();
    } else if (diasSemana[f.getDay()] === "Miercoles") {
        f = sumarDias(new Date(), 5);
        fi = f.getDate();
    } else {
        fi = fi = f.getDate();
    }
    $('#fechainicio').datepicker({
        minDate: fi + "/" + (f.getMonth() + 1) + "/" + $('#selectanio').val(),
        //minDate: "01/01/" + response,
        maxDate: "31/12/" + $('#selectanio').val()
    });
    $('#fechafin').datepicker({
        minDate: fi + "/" + (f.getMonth() + 1) + "/" + $('#selectanio').val(),
        maxDate: "31/12/" + $('#selectanio').val()
    });
}

function sumarDias(fecha, dias) {
    fecha.setDate(fecha.getDate() + dias);
    return fecha;
}

function listaServiciosP() {
    $('#loader').removeClass("d-none");
    $('#listaServicios').empty();
    var a = $('#array').val();
    $.ajax({
        url: "../actividadReq?accion=ListaServicios",
        type: 'POST',
        data: {req: a, area: $('#agjustificativo').val(), tipo: $('#tagjustificativo').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var req, estado, es = this.actividad_id, div2, ssp = 0, ssp2 = 0;
                    req = this.req_id;
                    $.each(this.cuatri, function (indice, sp) {
                        if (sp.solicitud_estado !== 1 && sp.solicitud_estado !== 32) {
                            ssp++;
                        } else {
                            ssp2++;
                        }
                    });
//                    if ((this.solicitud_id == null || this.solicitud_id === 0) && this.actividad_id !== 47 && this.actividad_id !== 0 && ssp2 === this.cuatri.length) {
//                        div2 = '<i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i>';
//                    } else {
                    div2 = '<i class="fas fa-plus m-1" id="agregarS" data-req="' + this.req_id + '" data-nombre="' + this.req_nombre + '" data-proyecto="' + this.proyecto_id + '" title="Agregar"></i><i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i><i class="fas fa-location-arrow m-1" id="enviarS" data-id="' + this.req_id + '" data-cantidad="' + this.cuatri.length + '" title="Enviar"></i>';
//                    }
                    if (this.estado_nombre == null || this.estado_nombre === '' || this.estado_nombre == 'undefined') {
                        estado = '----';
                    } else {
                        estado = this.estado_nombre;
                    }
                    $('#listaServicios').append('<div class="encabezado_5 estilobody text-justify">' + this.req_nombre + '</div><div class="encabezado_5 estilobody text-justify">' + this.req_descripcion + '</div>\n\
                    <div class="encabezado_4 estilobody justify-content-center">' + this.req_cantidad + '</div><div class="encabezado_4 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>\n\
                    <div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>\n\
                    <div class="encabezado_4 estilobody justify-content-center">' + estado + '</div><div class="encabezado_4 estilobody text-center">' + div2 + '</div>\n\
                    <div style="display:none; background:rgba(0, 0, 0, 0.05);" id="lserv' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    total = total + this.req_costo_total;
                    if (this.cuatri.length > 0) {
                        $.each(this.cuatri, function (indice, sp) {
                            var div, estudiante, solicitud;
                            var tipo;
                            if (sp.actividad_id === 3) {
                                tipo = 'HONORARIO FIJO';
                            } else if (sp.actividad_id === 2) {
                                tipo = 'HONORARIO MENSUAL';
                            } else if (sp.actividad_id === 1) {
                                tipo = 'HORAS CLASE';
                            }

                            if (sp.solicitud_codigo == 'undefined' || sp.solicitud_codigo == null) {
                                solicitud = '----';
                            } else {
                                solicitud = sp.solicitud_codigo + '-STP-' + $('#selectanio').val();
                            }

                            if (sp.solicitud_estado !== 1 && sp.solicitud_estado !== 32) {
                                div = '<i class="fas fa-edit" id="modserv" data-req="' + req + '" data-id="' + sp.req_id + '" data-tipo="' + sp.actividad_id + '" data-cedula="' + sp.solicitud_cedula + '" data-horas="' + sp.n_horas + '" data-total="' + sp.req_costo_total + '" data-fi="' + sp.fecha_inicio + '" data-ff="' + sp.fecha_fin + '" data-cantidad="' + sp.cantidad + '" data-cargo="' + sp.actividad_nombre + '" data-nombre="' + sp.req_nombre + '" data-apellido="' + sp.req_descripcion + '" data-observacion="' + sp.solestado_observacion + '" data-cedest="' + sp.autoridades_cedula + '" data-nombreest="' + sp.autoridades_nombre + '" data-apellidoest="' + sp.autoridades_cargo + '" data-siniva="' + sp.req_costo_sin_iva + '"></i><i class="fas fa-trash" id="elimserv" data-id="' + sp.req_id + '"></i>';
                            } else {
                                div = sp.estado_nombre;
                            }

                            if (sp.autoridades_nombre == null || sp.autoridades_nombre == 'undefined') {
                                estudiante = "---";
                            } else {
                                estudiante = sp.autoridades_nombre + ' ' + sp.autoridades_cargo;
                            }
                            $('#listaServicios').children('div#lserv' + req).append('<div class="encabezado_5 estilobody text-justify">NOMBRE: ' + sp.req_nombre + ' ' + sp.req_descripcion + '</div><div class="encabezado_5 estilobody text-justify">CARGO: ' + sp.actividad_nombre + '</div>\n\
                            <div class="encabezado_5 estilobody justify-content-center">TIPO: ' + tipo + '</div><div class="encabezado_5 estilobody justify-content-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sp.req_costo_total) + '</div><div class="encabezado_2 estilobody text-justify">NOMBRE ESTUDIANTE: ' + estudiante + '</div><div class="encabezado_4 estilobody justify-content-center"> ' + solicitud + '</div><div class="encabezado_4 estilobody centro">' + div + '</div>');
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
    $('input[name=cedulaEstu]').val('');
    $('input[name=nombreEstu]').val('');
    $('input[name=apellidoEstu]').val('');
    $('textarea[name=observoficio]').val('');
    $('select[name=rectoring]').val('0');
    $('#cargo').val('');
    banservicios = true;
    if (data['req'] != 10151 && data['req'] != 10143 && data['req'] != 14994 && data['req'] != 14995 && data['req'] != 14996 && data['req'] != 19979 && data['req'] != 21485 && data['req'] != 29055) {
        $('#fechainicio').datepicker("destroy");
        $('#fechafin').datepicker("destroy");
        listaFechaActual();
    } else {
        $('#fechainicio').datepicker("destroy");
        $('#fechafin').datepicker("destroy");
        $('#fechainicio').datepicker({
            maxDate: "31/12/" + $('#selectanio').val()
        });
        $('#fechafin').datepicker({
            maxDate: "31/12/" + $('#selectanio').val()
        });
    }
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
        $('#fehchai').removeClass('d-none');
        $('#fechaf').removeClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#datosEstu').addClass('d-none');
    } else if (id === "2") {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#datosEstu').addClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').removeClass('d-none');
        $('#sueldo').removeClass('d-none');
        $('#personalmed').removeClass('d-none');
    } else if (id === "3") {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#tpagas').removeClass('d-none');
        $('#datosEstu').removeClass('d-none');
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
    var cedulaEst = $('#cedulaEstu').val();
    var nombreEst = $('#nombreEstu').val();
    var apellidoEst = $('#apellidoEstu').val();
    if ($('input:checkbox[name=personalm]:checked').val() == 'undefiden' || $('input:checkbox[name=personalm]:checked').val() == null || $('input:checkbox[name=personalm]:checked').val() == '') {
        iva = 0;
    } else {
        iva = $('input:checkbox[name=personalm]:checked').val();
    }
    if (nombre === 'undefined' || nombre === '' || apellido === 'undefined' || apellido == '' || cedula === 'undefined' || cedula == '' || cargo === 'undefined' || cargo == '') {
        alert('Debe llenar todos los campos');
    } else if (tipo === "3" && (cedulaEst == null || cedulaEst === 'undefined' || cedulaEst === '' || nombreEst == null || nombreEst === 'undefined' || nombreEst === '' || apellidoEst == null || apellidoEst === 'undefined' || apellidoEst === '') && $('#agjustificativo').val() === "46") {
        alert('Debe ingresar los datos del estudiante');
    } else if (fechai === 'undefined' || fechai === null || fechai === '') {
        alert('Debe igresar la fecha de inicio.');
    } else if ((fechaf === 'undefined' || fechaf === null || fechaf === '') && (tipo === "1" || tipo === "2")) {
        alert('Debe ingresar la feha fin');
    } else if (observacion == null || observacion === 'undefined' || observacion === '') {
        alert('Debe ingresar la observaci\u00f3n.');
    } else {
        if (banservicios) {
            $.ajax({
                url: "../actividadReq?accion=IngresarServiciosP",
                type: 'POST',
                data: {horas: horas, shoras: shoras, fechai: fechai, fechaf: fechaf, sueldoMensual: sueldoMensual,
                    totalpagar: totalpagar, nombre: nombre, req: req, cedula: cedula, tipo: tipo, cargo: cargo, apellido: apellido, iva: iva, ced: ced, observacion: observacion, apellidoEst: apellidoEst, nombreEst: nombreEst, cedulaEst: cedulaEst},
                dataType: 'json'
            })
                    .done(function (response) {
                        var mensaje1, mensaje, mensajenota;
                        if (response === "Correcto") {
                            $('#generarJ').modal('hide');
                            listaServiciosP();
                        } else {
                            if (response === "Estudiante") {
                                mensaje1 = "Duplicidad de Tramite"
                                mensaje = "El estudiante ya se ingreso con el mismo miembro o tutor."
                                mensajenota = "NOTA: SI REQUIERE CONTINUAR CON LA GESTIÓN, ANULE EL TRAMITE ANTERIOR";
                            } else {
                                mensaje1 = "Verifique los campos"
                                mensaje = "Verifique que todos los campos esten llenos y que el monto no sobrepase el disponible";
                                mensajenota = " ";
                            }
                            $('#alertaMensajeDiv').html('<div class="col-12" style="font-weight: bold;">' + mensaje1 + '</div>\n\
                            <div class="col-12">' + mensaje + '</div><div class="col-12">' + mensajenota + '</div>');
                            $('#alertaMensaje').modal();
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
                url: "../actividadReq?accion=ModificarServiciosP",
                type: 'POST',
                data: {horas: horas, shoras: shoras, fechai: fechai, fechaf: fechaf, sueldoMensual: sueldoMensual,
                    totalpagar: totalpagar, nombre: nombre, req: req, cedula: cedula, tipo: tipo, servicio: serv, apellido: apellido, cargo: cargo, cedulaser: ced, iva: iva, observacion: observacion, apellidoEst: apellidoEst, nombreEst: nombreEst, cedulaEst: cedulaEst},
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
    $('#rectoring').find('option[value="' + data['tipo'] + '"]').remove();
    $('#observoficio').val(data['observacion']);
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
        $('#fehchai').removeClass('d-none');
        $('#fechaf').removeClass('d-none');
        $('#fechainicio').val(data['fi']);
        $('#fechafin').val(data['ff']);
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#nhoras').val(data['horas']);
        $('#sueldoH').val(data['cantidad']);
        $('#datosEstu').addClass('d-none');
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
        $('#datosEstu').addClass('d-none');
    } else if (id === 3) {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#fechainicio').val(data['fi']);
        $('#fehchai').removeClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').removeClass('d-none');
        $('#totalpagar').val(data['siniva'].toFixed(2));
        $('#cedulaEstu').val(data['cedest']);
        $('#nombreEstu').val(data['nombreest']);
        $('#apellidoEstu').val(data['apellidoest']);
        $('#datosEstu').removeClass('d-none');
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
        url: "../actividadReq?accion=EliminarServiciosP",
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
        alert("Debe tener ingresado al menos un datos para enviar a talento humano.");
    }
});

$('#enviarModalBton').on('click', function () {
    var cedula = $('#cedulaProyecto').val();
    var serv = $('#idservenv').val();
    $.ajax({
        url: "../actividadReq?accion=EnviarServiciosP",
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