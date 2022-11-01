var options2 = {style: "currency", currency: "USD"};
var banservicios = true;
$(document).ready(function () {
    listaServiciosP();
});

function listaFechaActual() {
    var f = sumarDias(new Date(), 4), fi;
    var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado");
    $.ajax({
        url: "../proyecto?accion=ListarFecha",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
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
                    minDate: fi + "/" + (f.getMonth() + 1) + "/" + response,
                    //minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
                $('#fechafin').datepicker({
                    minDate: fi + "/" + (f.getMonth() + 1) + "/" + response,
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

function sumarDias(fecha, dias) {
    fecha.setDate(fecha.getDate() + dias);
    return fecha;
}

function listaServiciosP() {
    $('#loader').removeClass("d-none");
    $('#listaServicios').empty();
    var a = $('#array').val();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitudNP",
        type: 'POST',
        data: {solicitud: $('#solicitudidl').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var req, div2, total;
                    if (this.req_iva == 1) {
                        total = this.req_costo_sin_iva * 1.12;
                    } else {
                        total = this.req_costo_sin_iva;
                    }
                    req = this.req_id;
                    $('#listaServicios').append('<div class="encabezado_2 estilobody text-justify">' + this.req_nombre + '</div><div class="encabezado_2 estilobody text-justify">' + this.req_descripcion + '</div>\n\
                    <div class="encabezado_4 estilobody justify-content-center">' + this.req_cantidad + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>\n\
                    <div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(total) + '</div>\n\
                    <div style="background:rgba(0, 0, 0, 0.05);" id="lserv' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    total = total + this.req_costo_total;
                    if (this.cuatri.length > 0) {
                        $.each(this.cuatri, function (indice, sp) {
                            var div, fechainicio, fechafin, estudiante;
                            var tipo;
                            if (sp.actividad_id === 3) {
                                tipo = 'HONORARIO FIJO';
                            } else if (sp.actividad_id === 2) {
                                tipo = 'HONORARIO MENSUAL';
                            } else if (sp.actividad_id === 1) {
                                tipo = 'HORAS CLASE';
                            }
                            if (sp.fecha_inicio == 'undefined' || sp.fecha_inicio == null) {
                                fechainicio = "---";
                            } else {
                                fechainicio = sp.fecha_inicio;
                            }
                            if (sp.fecha_fin == 'undefined' || sp.fecha_fin == null) {
                                fechafin = "---";
                            } else {
                                fechafin = sp.fecha_fin;
                            }

                            if (sp.autoridades_cargo == null || sp.autoridades_cargo == 'undefined') {
                                estudiante = "---";
                            } else {
                                estudiante = sp.autoridades_nombre + ' ' + sp.autoridades_cargo;
                            }

                            if (sp.req_estado == "4") {
                                div = '<input type="checkbox" name="reqfinmod" id="reqfinmod" checked>';
                            } else {
                                div = '<input type="checkbox" name="reqfinmod" id="reqfinmod">';
                            }
                            if (sp.solicitud_id == $('#solicitudidl').val()) {
                                $('#listaServicios').children('div#lserv' + req).append('<div class="encabezado_5 estilobody text-justify">NOMBRE: ' + sp.req_nombre + ' ' + sp.req_descripcion + '</div><div class="encabezado_7 estilobody text-justify">CARGO: ' + sp.actividad_nombre + '</div>\n\
                            <div class="encabezado_4 estilobody justify-content-center">TIPO: ' + tipo + '</div><div class="estilobody encabezado_4 justify-content-center">' + fechainicio + '</div><div class="estilobody encabezado_4 justify-content-center">' + fechafin + '</div><div class="encabezado_4 estilobody justify-content-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sp.req_costo_total) + '</div><div class="encabezado_5 estilobody justify-content-center">ESTUDIANTE: ' + estudiante + '</div><div class="encabezado_8 estilobody text-center"><div class="dat" data-id="' + sp.req_id + '" data-fechai="' + sp.fecha_inicio + '" data-fechaf="' + sp.fecha_fin + '" data-cantidad="' + sp.cantidad + '" data-nombre="' + sp.req_nombre + ' ' + sp.req_descripcion + '" data-estudiante="' + sp.autoridades_nombre + ' ' + sp.autoridades_cargo + '" data-cedula="' + sp.solicitud_cedula + '" data-tipo="' + tipo + '" data-costot="' + sp.req_costo_total + '" data-tipoid="' + sp.actividad_id + '" data-fechai="' + sp.fecha_inicio + '" data-fechaf="' + sp.fecha_fin + '">' + div + '</div></div>');
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

$('.main').on('click', '.row .content .pestania #btnGenerarJ', function () {
    $('#listaProyectos').empty();
    $('#nombrecustodio').val("");
    var array = [];
    var sum = 0;
    var options2 = {style: "currency", currency: "USD"};
    $('div#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'id': data.id,
                'nombre': data.nombre,
                'cantidad': data.cantidad,
                'costosin': data.costosin,
                'cedula': data.cedula,
                'estudiante': data.estudiante,
                'tipo': data.tipo,
                'costot': data.costot,
                'tipoid': data.tipoid,
                'fechai': data.fechai,
                'fechaf': data.fechaf});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var fei, fef;
        if (array[i].tipoid !== 2) {
            fei = "---";
        } else {
            fei = '<input type="text" class="form-control input fechas" name="fechai' + array[i].id + '" id="fechai' + array[i].id + '" value="' + array[i].fechai + '">';
        }
        if (array[i].tipoid !== 2) {
            fef = "---";
        } else {
            fef = '<input type="text" class="form-control input fechas" name="fechaf' + array[i].id + '" id="fechaf' + array[i].id + '" value="' + array[i].fechaf + '">';
        }
        $('#listaProyectos').append('<tr><td class="text-center">' + array[i].cedula + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td>' + array[i].tipo + '</td>\n\
                        <td class="text-center">' + fei + '</td>\n\
                        <td class="text-center">' + fef + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(array[i].costot) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-tipoid="' + array[i].tipoid + '" data-cantidad="' + array[i].cantidad + '" data-fechai="' + array[i].fechai + '" data-fechaf="' + array[i].fechaf + '"></td>\n\
                    </tr>');
        sum = sum + array[i].costot;
        var f = new Date(array[i].fechai);
        $('#fechai' + array[i].id).datepicker({
            minDate: f.getDate() + "/" + (f.getMonth() + 1) + "/" + $('#selectanio').val(),
            maxDate: "31/12/" + $('#selectanio').val()
        });
        $('#fechaf' + array[i].id).datepicker({
            minDate: f.getDate() + "/" + (f.getMonth() + 1) + "/" + $('#selectanio').val(),
            maxDate: "31/12/" + $('#selectanio').val()
        });
    }
    $('#listaProyectos').append('<tr><td colspan="8" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sum) + '</td>');
    $('#generarJ').modal();
});

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

//Ingresar Liquidación
$('#modalGuardarJust').on('click', function () {
    var ag = $('input[name=agsol]').val();
    var nombre = $('#nombrecustodio').val();
    var anio = $('#selectanio').val()
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push($('input[name=fechai' + $(this).val() + ']').val());
        rem.push($('input[name=fechaf' + $(this).val() + ']').val());
        rem.push(data['cantidad']);
        rem.push(data['tipoid']);
        rem.push(data['fechai']);
        rem.push(data['fechaf']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (nombre === "") {
        alert("Debe ingresar la observaci\uf003n.");
    } else {
        var formData = new FormData();
        formData.append('req', req);
        formData.append('solicitud', $('#solicitudidl').val());
        formData.append('ag', ag);
        formData.append('nombre', nombre);
        formData.append('anio', anio);
        formData.append('cedulausuario', $('#cedulaProyecto').val());
        $.ajax({
            url: "../actividadReq?accion=LiquidarSolicitud",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.href="pListarSolE.jsp";
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
        var d = data['total'] / 1.12;
        $('#totalpagar').val(d.toFixed(2));
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