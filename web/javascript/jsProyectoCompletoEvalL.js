let proy, mul, fi, ff, long, banarchivos = true, banActividad = true, banRequerimiento = true, estadof, proynombre, persp, banIndicaroEl = true, banActividadElim = true, banRequerimientoElim = true;
let tusu = $('#tipousuario').val();
let tiempo = $('#fechacierre').val();
let m, prio, banAnio = false, estado;
var options2 = {style: "currency", currency: "USD"};

$('#selectcuatrimestre').on('change', function () {
    var cu;
    if ($('#selectcuatrimestre').val() === '1') {
        cu = "I";
    } else if ($('#selectcuatrimestre').val() === '2') {
        cu = "II";
    } else if ($('#selectcuatrimestre').val() === '3') {
        cu = "III";
    }
    $('#cuatrititulo').html(cu + ' CUATRIMESTRE');
    $('#cuatrieval').val($('#selectcuatrimestre').val());
    $('#cuatrimestreeval').val($('#selectcuatrimestre').val());
    listaProyecto($("#idProy").val(), $('#cuatrimestreeval').val());
    drawChart();
});

$(document).ready(function () {
    listaProyecto($("#idProy").val(), $('#cuatrimestreeval').val());
});

function listaProyecto(proye, cuatrimestre) {
    $('#fechaestados').empty();
    $.ajax({
        url: "../proyecto?accion=ListarProyectoCompEvaluacion",
        data: {proy: proye, cuatrimestre: cuatrimestre},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var cuatri, tp;
                $.each(response, function () {
                    proy = this.proyecto_id;
                    estado = this.estado_id;
                    cuatri = this.tp_id;
                    $('#modNombreProy').html(this.proyecto_nombre);
                    $('#modMontoProy').html(new Intl.NumberFormat("US", options2).format(this.proyecto_monto));
                    tp = this.pp_id;
                    proynombre = this.proyecto_nombre;
                    $('#tituloAg').html(this.ag.ag_nombre);

                    $('#proyectoNombreF').html(this.proyecto_nombre);
                    if (this.proyecto_fi_rep == null || this.proyecto_fi_req === '') {
                        $('#fechaInicioF').html(this.proyecto_fi);
                        fi = this.proyecto_fi;
                    } else {
                        $('#fechaInicioF').html(this.proyecto_fi_rep);
                        fi = this.proyecto_fi_rep;
                    }
                    if (this.proyecto_ff_rep == null || this.proyecto_ff_req === '') {
                        $('#fechaFinF').html(this.proyecto_ff);
                        ff = this.proyecto_ff;
                    } else {
                        $('#fechaFinF').html(this.proyecto_ff_rep);
                        ff = this.proyecto_ff_rep;
                    }
                    $('#montoF').html(new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto));
                    m = this.proyecto_monto;
                    estadof = this.estado_id;
                    var tipo = this.tp_id;
                    if (tipo > 0) {
                        tipo = tipo.toString();
                    }
                    if (cuatrimestre.toString() === tipo) {
                        if (estado === 0 || estado === 'undefined') {
                            $('#estadoF').html('En proceso');
                        } else {
                            $('#estadoF').html(this.estado_nombre);
                        }
                    } else {
                        $('#estadoF').html('En proceso');
                    }

                    if ((tusu === "15" || tusu === "3") && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                    } else if ((tusu === "2" || tusu === "5" || tusu === "7" || tusu === "8" || tusu === "19") && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                    } else {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModalVer");
                    }
                    if (this.estado.length > 0) {
                        var options = {
                            weekday: "long", year: "numeric", month: "short",
                            day: "numeric", hour: "2-digit", minute: "2-digit"
                        };
                        $.each(this.estado, function (indice, estadoe) {
                            var date = new Date(this.estado_fecha);
                            $('#fechaestados').append('<div>' + estadoe.estado_nombre + ' por ' + estadoe.usuario_nombre + ' el ' + date.toLocaleTimeString("es-ES", options) + '</div>');
                        });
                    }
                });

                if ((tusu === "3" && (estadof === 2 || estadof === 1)) || (tusu === "4" && (estadof === 4 || estadof === 8 || estadof === 9)) || ((tusu === "7" || tusu === "8") && (estadof === 41)) || (tusu === "19" && (estadof === 2 || estadof === 9 || estadof === 10)) || (tusu === "5" && (estadof === 2 || estadof === 9)) || ((tusu === "16" || tusu === "17") && (estadof === 1 || estadof === 6)) || (tusu === "11" && (estadof === 11 || estadof === 21 || estadof === 22 || estadof === 2 || estadof === 9)) || (tusu === "14" && (estadof === 2 || estadof === 6 || estadof === 10 || estadof === 11 || estadof === 22 || estadof === 27))) {
                    //if ((tusu === "3" && estadof === 2) || ((tusu === "7" || tusu === "8") && (estadof === 6 || estadof === 23 || estadof === 24)) || (tusu === "19" && (estadof === 2 || estadof === 9 || estadof === 10)) || ((tusu === "16" || tusu === "17") && estadof === 6) || (tusu === "11" && (estadof === 11 || estadof === 21 || estadof === 22 || estadof === 2))) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else {
                    $('#btn_proyecto_enviar').addClass('d-none');
                }

                if ((tusu === "14" || tusu === "16" || tusu === "17") && (estadof === 42 || estadof === 44)) {
                    $('#footereval').html('<button type="button" class="btn bton" id="enviarModalBton">ENVIAR</button><a class="btn bton mb-3" id="btn_proyecto_reprogramar" href="pProyectoRep.jsp?id=' + proy + '">Reprogramar</a>\n\
                    <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>')
                    $('#btnEvalRep').append('<button class="btn bton" id="btn_proyecto_reprogramar" data-toggle="modal" data-target="#enviarModal">Reprogramar</button>');
                } else if ((tusu === "14" || tusu === "16" || tusu === "17") && (estadof === 44) && (tp === 2 || tp === 3)) {
                    $('#btnEvalRep').append('<a class="btn bton" id="btn_proyecto_reprogramar" href="pProyectoRep.jsp?id=' + proy + '">Reprogramar</a>');
                } else if ((tusu === "3") && (estadof === 43 || estadof === 46)) {
                    $('#btnEvalRep').append('<a class="btn bton" id="btn_proyecto_reprogramar" href="pProyectoRep.jsp?id=' + proy + '">Reprogramar</a>');
                }
                listarComponente(proy, estado, cuatrimestre, cuatri);
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

//Listar fechas
function tiempos(id2) {
    $.ajax({
        url: "../proyecto?accion=Tiempo",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var segundos = 1000;
                var minutos = segundos * 60;
                var horas = minutos * 60;
                var dias = horas * 24;
                var timer;
                var fin = new Date(response.tiempo);
                var days;

                function showRemaining() {
                    var hoy = new Date();
                    var distance = fin - hoy;
                    if (distance < 0) {
                        clearInterval(timer);
                        $('#' + id2).val(distance);
                        return;
                    } else {
                        $('#' + id2).val(distance);
                    }
                    days = Math.floor(distance / dias);
                    var hours = Math.floor((distance % dias) / horas);
                    var minutes = Math.floor((distance % horas) / minutos);
                    var seconds = Math.floor((distance % minutos) / segundos);
                }
                timer = setInterval(showRemaining, 1000);
                return days;
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

//Activar las pestanias
$('#agregaC').on('click', function () {
    if ($('#pestania2').hasClass('d-none')) {
        $('#pestania2').removeClass('d-none');
        $('#pestania2').addClass('d-block');
    } else if ($('#pestania3').hasClass('d-none')) {
        $('#pestania3').removeClass('d-none');
        $('#pestania3').addClass('d-block');
    } else if ($('#pestania4').hasClass('d-none')) {
        $('#pestania4').removeClass('d-none');
        $('#pestania4').addClass('d-block');
    } else if ($('#pestania5').hasClass('d-none')) {
        $('#pestania5').removeClass('d-none');
        $('#pestania5').addClass('d-block');
    } else {
        $('#agregaC').addClass('d-none');
    }
});

function porcentajecuatrimestre(comp, cuatrimestre) {
    var ret;
    $.ajax({
        url: "../evaluacion?accion=ConsultarPorcentaje" + "&componente=" + comp + "&cuatrimestre=" + cuatrimestre,
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                ret = response;
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    return ret;
}

//Listar componente
function listarComponente(proy, estado, cuatrimestre, cuatri) {
    var i = 1;
    $.ajax({
        url: "../componenteMeta?accion=ListarComponentesRep" + "&proy=" + proy,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    if (response.length < 2) {
                        $.each(response, function () {
                            if (porcentajecuatrimestre(this.componente_id, cuatrimestre) > 0) {
                                $("#idComponente" + i).val(this.componente_id);
                                $('#contenedorlogros' + i).removeClass('d-none');
                                $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                                listaMeta(this.componente_id, i, cuatrimestre);
                                long = response.length;
                                listarLogros(this.componente_id, i);
                                listaActividad(this.componente_id, i, cuatrimestre, cuatri, estado);
                                listaRequerimiento(this.componente_id, i, estado, cuatrimestre);
                                i++;
                            } else {
                                $("#idComponente" + i).val(this.componente_id);
                                $("#textoMeta" + i).empty();
                                $("#lisIndicador" + i).empty();
                                $("#listaActividades" + i).empty();
                                $("#listaRequerimiento" + i).empty();
                                $("#listaIndicadores" + i).empty();
                                $('#btnBotonMeta' + i).empty();
                                $("#inputComp" + i).html('<div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">Sin actividades a evaluar en este cuatrimestre</div></div>');
                                i++;
                            }
                        });
                    } else {
                        var sm = 1;
                        $.each(response, function () {
                            if (porcentajecuatrimestre(this.componente_id, cuatrimestre) > 0) {
                                $("#idComponente" + i).val(this.componente_id);
                                $('#contenedorlogros' + i).removeClass('d-none');
                                $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                                listaMeta(this.componente_id, i, cuatrimestre);
                                long = response.length;
                                listarLogros(this.componente_id, i);
                                listaActividad(this.componente_id, i, cuatrimestre, cuatri, estado);
                                listaRequerimiento(this.componente_id, i, estado, cuatrimestre);
                                i++;
                                sm++;
                            } else {
                                $("#idComponente" + i).val(this.componente_id);
                                $("#textoMeta" + i).empty();
                                $("#lisIndicador" + i).empty();
                                $("#listaActividades" + i).empty();
                                $("#listaRequerimiento" + i).empty();
                                $("#listaIndicadores" + i).empty();
                                $('#btnBotonMeta' + i).empty();
                                $("#inputComp" + i).html('<div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">Sin actividades a evaluar en este cuatrimestre</div></div>');
                                i++;
                                sm++;
                            }
                        });
                    }
                }
                if (response.length === 3 && $('#tipoAg').val() !== "4") {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $('#agregaC').remove();
                } else if (response.length === 2) {
                    $("#pestania2").removeClass('d-none');
                } else if (response.length === 3 && ($('#tipoAg').val() === "1" || $('#tipoAg').val() === "4")) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                } else if (response.length === 4 && ($('#tipoAg').val() === "1" || $('#tipoAg').val() === "4" || $('#idAgObEs').val() === "2" || $('#idAgObEs').val() === "8" || $('#idAgObEs').val() === "14" || $('#idAgObEs').val() === "17" || $('#idAgObEs').val() === "22" || $('#idAgObEs').val() === "27" || $('#idAgObEs').val() === "31" || $('#idAgObEs').val() === "36" || $('#idAgObEs').val() === "37")) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $("#pestania4").removeClass('d-none');
                    $('#agregaC').remove();
                } else if (response.length === 5 && ($('#tipoAg').val() === "1" || $('#tipoAg').val() === "4" || $('#idAgObEs').val() === "2" || $('#idAgObEs').val() === "8" || $('#idAgObEs').val() === "14" || $('#idAgObEs').val() === "17" || $('#idAgObEs').val() === "22" || $('#idAgObEs').val() === "27" || $('#idAgObEs').val() === "31" || $('#idAgObEs').val() === "36" || $('#idAgObEs').val() === "37")) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $("#pestania4").removeClass('d-none');
                    $("#pestania5").removeClass('d-none');
                    $('#agregaC').remove();
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

//Listar Logros
function listarLogros(comp, i) {
    $.ajax({
        url: "../componenteMeta?accion=ListaComponentesLogros",
        type: 'POST',
        data: {"componente": comp, anio: $('#selectanio').val(), cuatrimestre: $('#cuatrimestreeval').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $.each(response, function () {
                        if (this.componente_nombre == null || this.componente_nombre == 'undefined') {
                            $('#txtLogros' + i).attr("readonly", true);
                            $('#txtNudos' + i).attr("readonly", true);
                        } else {
                            $('#contenedorlogros' + i).children('.col-12').children('#txtLogros' + i).html(this.componente_nombre).prop("readonly", true);
                            $('#contenedorlogros' + i).children('.col-12').children('#txtNudos' + i).html(this.meta_nombre).prop("readonly", true);
                        }
                        $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).addClass('d-none');
                        if (estado == 1 || estado == 2 || estado == 4 || estado == 6 || estado == 8 || estado == 10 || estado == 11 || estado == 21 || estado == 22 || estado == 41 || estado == 42 || estado == 43 || estado == 44 || estado == 45 || estado == 46) {
                            $('#botonLog' + i).html('');
                        }
                    });
                } else {
                    $('#txtLogros' + i).prop('readonly', true);
                    $('#txtNudos' + i).prop('readonly', true);
                    $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).addClass('d-none');
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

//Listar meta
function listaMeta(comp, i, cuat) {
    $.ajax({
        url: "../evaluacion?accion=ListarMeta" + "&comp=" + comp + "&cuatri=" + cuat,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $.each(response, function () {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#contenedorMeta' + i).addClass('d-flex');
                        $("#metaF" + i).attr('type', 'hidden');
                        $("#inputMeta" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Meta:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.meta_nombre + '</div></div>');

                        if (this.mes.length > 0) {
                            $.each(this.mes, function (indice, me) {
                                $('#btn_meta' + i).html(me.me_porcentaje + '%');
                            })
                        } else {
                            $('#btnBotonMeta' + i).html('----');
                        }
                        listarIndicador(this.meta_id, i);
                    });
                } else {
                    $('#contenedorMeta' + i).removeClass('d-none');
                    $('#contenedorMeta' + i).addClass('d-flex');
                    $('#botonIndicador' + i).addClass('d-none');
                    $('#botonActividad' + i).addClass('d-none');
                    $('#detalleIndicador' + i).addClass('d-none');
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

//listar indicador
function listarIndicador(meta, i) {
    $.ajax({
        url: "../componenteMeta?accion=ListarIndicadorRep" + "&meta=" + meta,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    var con = 1;
                    $('#listaIndicadores' + i).empty();
                    $('#lisIndicador' + i).empty();
                    $('#detalleIndicador' + i).removeClass('d-none');
                    if (tusu === "15" && estadof === 3) {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#botonIndicador' + i).removeClass('d-none');
                        $('#botonActividad' + i).removeClass('d-none');
                    } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#botonIndicador' + i).removeClass('d-none');
                        $('#botonActividad' + i).removeClass('d-none');
                    } else if (tusu === "3" && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#botonIndicador' + i).removeClass('d-none');
                        $('#botonActividad' + i).removeClass('d-none');
                    } else {
                        $('#botonIndicador' + i).removeClass('d-flex');
                        $('#botonIndicador' + i).addClass('d-none');
                        $('#botonActividad' + i).removeClass('d-flex');
                        $('#botonActividad' + i).addClass('d-none');
                    }
                    $('#contenedorIndicador' + i).removeClass('d-block');
                    $('#contenedorIndicador' + i).addClass('d-none');
                    $.each(response, function () {
                        var formula, indic;
                        if (this.indicador_tipo === "Cualitativo") {
                            formula = "No tiene formula";
                        } else {
                            formula = this.indicador_ejecutado + " / " + this.indicador_planificado + "(" + this.indicador_numero + ")";
                        }
                        if (con === 1) {
                            indic = "Indicadores: ";
                        } else {
                            indic = " ";
                        }
                        $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row" id="rowindicador"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"></div></div></div>');
                        $("#listaIndicadores" + i).append('<tr><td class="text-justify align-middle">' + this.indicador_nombre + '</td><td class="text-justify align-middle">' + this.indicador_descripcion + '</td><td class="align-middle">' + this.indicador_tipo + '</td><td class="text-justify align-middle">' + formula + '</td></tr>');
                        con++;
                    });
                } else {
                    $('#contenedorIndicador' + i).removeClass('d-none');
                    $('#contenedorIndicador' + i).addClass('d-block');
                    $('#botonIndicador' + i).addClass('d-none');
                    $('#botonActividad' + i).addClass('d-none');
                    $('#detalleIndicador' + i).addClass('d-none');
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listaActividad(comp, i, cuatrimestre, cuat, estado) {
    $("#listaActividades" + i).empty();
    let total = 0.0, color = 'rgba(0, 0, 0, 0.05);';
    $.ajax({
        url: "../evaluacion?accion=ListarActividad" + "&comp=" + comp + "&cuatri=" + cuatrimestre + "&tipo=" + 2,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $('#contenedorActividad' + i).css({"display": "none"});
                    $('#listadoActividades' + i).removeClass('d-none');
                    $("#listaActividades" + i).empty();
                    var id, num = 1;
                    $.each(response, function () {
                        var porcen;
                        if (num % 2 === 0) {
                            color = 'rgba(0, 0, 0, 0.05);';
                        } else {
                            color = '#fff';
                        }
                        id = this.actividad_id;
                        var po1 = 0, po2 = 0, po3 = 0, monto;
                        var div, div2;
                        if (this.cuatri.length > 0) {
                            $.each(this.cuatri, function (indice, c) {
                                po1 = c.c1;
                                po2 = c.c2;
                                po3 = c.c3;
                            });
                        }
                        var mesac = [];
                        $.each(this.mes, function (indice, mes) {
                            mesac.push(mes.mes_id);
                        });

                        var obser;
                        if (this.actividad_eval.length > 0) {
                            $.each(this.actividad_eval, function (indice, eval) {
                                if (($('#tipousuario').val() === '3' && (estado == 1 || estado == 2)) || ($('#tipousuario').val() === '4' && (estado == 4 || estado == 8)) || ($('#tipousuario').val() === '19' && (estado == 2 || estado == 10))) {
                                    if (eval.actividad_nombre == null || eval.actividad_nombre == 'undefined') {
                                        if (eval.ae_observacion == null) {
                                            porcen = eval.ae_autoeval + '% <i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i>';
                                        } else {
                                            porcen = eval.ae_autoeval + '%,  ' + eval.ae_observacion + '<i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i>';
                                        }
                                    } else {
                                        porcen = eval.actividad_nombre + '%';
                                    }
                                } else if ((($('#tipousuario').val() === '16' || $('#tipousuario').val() === '17') && (estado == 6 || estado == 1)) || ($('#tipousuario').val() === '14' && (estado == 6 || estado == 10 || estado == 11 || estado == 10 || estado == 2 || estado == 22 || estado == 27))) {
                                    if (eval.actividad_nombre == null || eval.actividad_nombre == 'undefined') {
                                        if (eval.ae_observacion == null) {
                                            porcen = eval.ae_autoeval + '% <i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i><i class="fas fa-clipboard-check" title="Evaluar" id="evaluar" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-autoeval="' + eval.ae_autoeval + '" data-cuat="' + cuat + '" data-estado="' + estado + '" data-ejecucion="' + eval.ae_ejecucion + '" data-tiempo="' + eval.ae_tiempo + '" data-eval="' + eval.ae_eval + '"></i>';
                                        } else {
                                            porcen = eval.ae_autoeval + '%,  ' + eval.ae_observacion + '<i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i><i class="fas fa-clipboard-check" title="Evaluar" id="evaluar" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-autoeval="' + eval.ae_autoeval + '" data-cuat="' + cuat + '" data-estado="' + estado + '" data-ejecucion="' + eval.ae_ejecucion + '" data-tiempo="' + eval.ae_tiempo + '" data-eval="' + eval.ae_eval + '"></i>';
                                        }
                                    } else {
                                        porcen = eval.actividad_nombre + '% <i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i><i class="fas fa-clipboard-check" title="Evaluar" id="evaluar" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-autoeval="' + eval.ae_autoeval + '" data-cuat="' + cuat + '" data-estado="' + estado + '" data-ejecucion="' + eval.ae_ejecucion + '" data-tiempo="' + eval.ae_tiempo + '" data-eval="' + eval.ae_eval + '"></i>';
                                    }
                                } else {
                                    if (eval.actividad_nombre == null || eval.actividad_nombre == 'undefined') {
                                        if (eval.ae_observacion == null) {
                                            obser = " ";
                                        } else {
                                            obser = eval.ae_observacion
                                        }
                                        porcen = '<div class="inputev">' + eval.ae_autoeval + '%, ' + obser + '</div>';
                                    } else {
                                        porcen = eval.actividad_nombre + '%';
                                    }
                                }
                            });
                        } else {
                            if (($('#tipousuario').val() === '3' && (estado == 1 || estado == 2)) || ($('#tipousuario').val() === '4' && (estado == 4 || estado == 8)) || ($('#tipousuario').val() === '19' && (estado == 2 || estado == 10))) {
                                porcen = '----  <i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i>';
                            } else if ((($('#tipousuario').val() === '16' || $('#tipousuario').val() === '17') && (estado == 6 || estado == 1)) || ($('#tipousuario').val() === '14' && (estado == 6 || estado == 10 || estado == 11 || estado == 10 || estado == 2 || estado == 22 || estado == 27))) {
                                porcen = '<i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i><i class="fas fa-clipboard-check" title="Evaluar" id="evaluar" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-autoeval="' + eval.ae_autoeval + '" data-cuat="' + cuat + '" data-estado="' + estado + '" data-ejecucion="' + eval.ae_ejecucion + '" data-tiempo="' + eval.ae_tiempo + '" data-eval="' + eval.ae_eval + '"></i>';
                            } else {
                                porcen = '--';
                            }
                        }

                        //Tipo de actividad
                        if (this.actividad_tipo === 0) {
                            monto = "-----";
                            div2 = " ";
                        } else {
                            monto = new Intl.NumberFormat("US", formateador()).format(this.actividad_monto);
                            if (estadof === 16 || estadof === 17 || estadof === 18) {
                                if (this.actividad_prioridad === 1) {
                                    div2 = '<i class="fas fa-check-double"></i>';
                                } else if (this.actividad_prioridad === 2) {
                                    div2 = '<i class="fas fa-check"></i>';
                                } else {
                                    div2 = '<i class="fas fa-times"></i>';
                                }
                            } else {
                                div2 = '';
                            }
                        }

                        div = '<i class="fas fa-chevron-circle-down lisArchivos" data-id="' + this.actividad_id + '" data-pestania="' + i + '"></i><i class="fas fa-list-alt lisReq" data-id="' + this.actividad_id + '" data-pestania="' + i + '"></i>';


                        if ((cuatrimestre == 1 && po1 > 0) || (cuatrimestre == 2 && po2 > 0) || (cuatrimestre == 3 && po3 > 0)) {
                            $("#listaActividades" + i).append('<div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_5 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_5 estilobody text-justify" style="background-color:' + color + '" id="listaeval' + this.actividad_id + i + '">' + porcen + '</div><div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '">' + div + div2 + '</div>\n\
                                <div class="encabezado_completo estilo p-0" id="listaArchivos' + this.actividad_id + i + '" style="background-color:rgba(0,0,0,0.2); display:none;"><div class="encabezado_6 estilo" style="font-weight:bold;">Descripci\u00f3n Archivo</div><div class="encabezado_6 estilo" style="font-weight:bold;">Nombre Archivo</div>\n\
                                <div class="encabezado_6 estilo" style="font-weight:bold;">Acci\u00f3n</div><div class="encabezado_completo estilo p-0" id="lArchivos' + this.actividad_id + i + '"></div></div>');


                            if (this.req.length > 0) {
                                var div3;
                                $.each(this.req, function (indice, archivos) {
                                    div3 = ' ';

                                    $("#listaActividades" + i).children('.estilo').children("#lArchivos" + id + i).append('<div class="encabezado_6 estilo">' + archivos.are_descripcion + '</div><div class="encabezado_6 estilo"><a href="https://planificacion.espoch.edu.ec/sip/evaluacion/' + cuatrimestre + '/' + archivos.are_archivo + '" download="' + archivos.are_archivos + '">' + archivos.are_archivo + '</a></div>\n\
                                    <div class="encabezado_6 estilo">' + div3 + '</div>');
                                });
                            } else {
                                $("#listaActividades" + i).children('.estilo').children("#lArchivos" + id + i).append("Sin archivos que mostrar");
                            }

                            if (this.cuatri.length > 0) {
                                $.each(this.cuatri, function (indice, cuatrimestre) {
                                    var cu1, cu2, cu3;
                                    if (cuatrimestre.c1 === 100) {
                                        cu1 = cuatrimestre.c1;
                                    } else {
                                        cu1 = cuatrimestre.c1.toFixed(2);
                                    }
                                    if (cuatrimestre.c2 === 100) {
                                        cu2 = cuatrimestre.c2;
                                    } else {
                                        cu2 = cuatrimestre.c2.toFixed(2);
                                    }
                                    if (cuatrimestre.c3 === 100) {
                                        cu3 = cuatrimestre.c3;
                                    } else {
                                        cu3 = cuatrimestre.c3.toFixed(2);
                                    }
                                    $("#listaActividades" + i).children('#cuatrimestre' + id).append('<div class="centro encabezado_6">' + cu1 + '%</div><div class="centro encabezado_6">' + cu2 + '%</div><div class="centro encabezado_6">' + cu3 + '%</div>');
                                });
                            }
                        }
                        total += this.actividad_monto;
                        num++;
                        listarEjecucionReq(this.actividad_id, cuatrimestre);
                    });
                    if (num % 2 === 0) {
                        color = 'rgba(0, 0, 0, 0.05);';
                    } else {
                        color = '#fff';
                    }
                }
                $("#listaActividades" + i).append('<div class="encabezado_completo estilobody centro" style="background-color:' + color + '; font-weight:bold;">TOTAL: ' + new Intl.NumberFormat("US", formateador()).format(total) + '</div>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listarEjecucionReq(act, cuatrimestre) {
    $('#listaProyectos').empty();
    $.ajax({
        url: "../evaluacion?accion=ListaReqEjec",
        type: 'POST',
        data: {act: act, cuatrimestre: cuatrimestre},
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaProyectos').empty();
                var suma = 0;
                $.each(response, function () {
                    var i = this.req_id;
                    $('#listaProyectos').append('<tr><td>' + this.req_nombre + '</td><td id="ref' + i + '"></td><td id="just' + i + '"></td><td id="cert' + i + '"></td><td id="certmonto' + i + '" class="text-center"></td></tr>');
                    $.each(this.deudas, function (indice, req) {
                        $('#listaProyectos').children('tr').children("#ref" + i).append(req.solestado_observacion);
                        $('#listaProyectos').children('tr').children("#just" + i).append(req.solicitud_codigo);
                        $('#listaProyectos').children('tr').children("#cert" + i).append(req.cp_tipo);
                        $('#listaProyectos').children('tr').children("#certmonto" + i).append(new Intl.NumberFormat("US", formateador()).format(req.cp_valor));
                        suma += req.cp_valor;
                    });
                });
                $('#listaProyectos').append('<tr><td colspan="4" class="text-center" style="font-weight:bold">TOTAL</td><td class="text-center" style="font-weight:bold">' + new Intl.NumberFormat("US", formateador()).format(suma) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('.encabezado').on('click', '.encabezado_4 .lisArchivos', function () {
    var data = $(this).data();
    $('.encabezado').children('#listaArchivos' + data['id'] + data['pestania']).slideToggle();
});

$('.encabezado').on('click', '.encabezado_4 .lisReq', function () {
    $('#generarJ').modal();
});

$('.encabezado').on('click', '.encabezado_5 #observacion', function () {
    var data = $(this).data();
    $('.encabezado').children('.encabezado_5#listaeval' + data['actividad'] + data['pestania']).html('<div class="inputev"><input type="text" id="observ' + data['actividad'] + data['pestania'] + '" class="form-control"></div><div class="botonev guardar"><i class="fas fa-save" id="observguardar" data-actividad="' + data['actividad'] + '" data-pestania="' + data['pestania'] + '" data-cuatrimestre="' + data['cuatrimestre'] + '" data-comp="' + data['comp'] + '" data-cuat="' + data['cuat'] + '" data-estado="' + data['estado'] + '" data-comp="' + data['comp'] + '"></i><i class="fas fa-times-circle" id="cancelarobs" data-pestania="' + data['pestania'] + '" data-cuatrimestre="' + data['cuatrimestre'] + '" data-comp="' + data['comp'] + '" data-cuat="' + data['cuat'] + '" data-estado="' + data['estado'] + '"></i></div>');
});

$('.encabezado').on('click', '.encabezado_5 #evaluar', function () {
    var data = $(this).data();
    var porcentaje, ejecucion, tiempo;
    if ((data['eval'] === 0 || data['eval'] == null || data['eval'] == 'undefined' || data['eval'] == '') && (data['autoeval'] == null || data['autoeval'] == 'undefined' || data['autoeval'] == '')) {
        porcentaje = '';
    } else if (data['eval'] > 0) {
        porcentaje = data['eval'];
    } else {
        porcentaje = data['autoeval'];
    }
    if (data['ejecucion'] == null || data['ejecucion'] == 'undefined' || data['ejecucion'] == '') {
        ejecucion = '';
    } else {
        ejecucion = data['ejecucion'];
    }
    if (data['tiempo'] == null || data['tiempo'] == 'undefined' || data['tiempo'] == '') {
        tiempo = '';
    } else {
        tiempo = data['tiempo'];
    }
    $('.encabezado').children('.encabezado_5#listaeval' + data['actividad'] + data['pestania']).html('<div class="inputev"><input type="text" id="ra' + data['actividad'] + data['pestania'] + '" placeholder="Autoevaluaci\u00f3n" value="' + porcentaje + '" class="form-control"><input type="text" id="ta' + data['actividad'] + data['pestania'] + '" placeholder="Dinero" value="' + ejecucion + '" class="form-control"><input type="text" id="ma' + data['actividad'] + data['pestania'] + '" placeholder="Tiempo" value="' + tiempo + '" class="form-control"></div><div class="botonev guardar"><i class="fas fa-save" id="guardareval" data-actividad="' + data['actividad'] + '" data-pestania="' + data['pestania'] + '" data-cuatrimestre="' + data['cuatrimestre'] + '" data-comp="' + data['comp'] + '" data-cuat="' + data['cuat'] + '" data-estado="' + data['estado'] + '"></i><i class="fas fa-times-circle" id="cancelarobs" data-pestania="' + data['pestania'] + '" data-cuatrimestre="' + data['cuatrimestre'] + '" data-comp="' + data['comp'] + '" data-cuat="' + data['cuat'] + '" data-estado="' + data['estado'] + '"></i></div>');
});

$('.encabezado').on('click', '.encabezado_5 #cancelarobs', function () {
    var data = $(this).data();
    listaActividad(data['comp'], data['pestania'], data['cuatrimestre'], data['cuat'], data['estado']);
});

$('.encabezado').on('click', '.encabezado_5 .botonev #observguardar', function () {
    var data = $(this).data();
    var observacion = $('#observ' + data['actividad'] + data['pestania']).val();
    if (observacion == null || observacion == '') {
        alert("Debe ingresar observaci\u00f3");
    } else {
        $.ajax({
            url: "../evaluacion?accion=IngresarObservacion",
            type: 'POST',
            data: {actividad: data['actividad'], cuatrimestre: data['cuatrimestre'], observacion: observacion},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listaActividad(data['comp'], data['pestania'], data['cuatrimestre'], data['cuat'], data['estado']);
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

$('.encabezado').on('click', '.encabezado_5 .guardar #guardareval', function () {
    var data = $(this).data();
    var autoeval = $('#ra' + data['actividad'] + data['pestania']).val();
    var dinero = $('#ta' + data['actividad'] + data['pestania']).val();
    var tiempo = $('#ma' + data['actividad'] + data['pestania']).val();
    if (autoeval == null || autoeval == '') {
        alert("Debe ingresar el valor");
    } else if (dinero == null || dinero == '') {
        alert("Debe ingresar el valor de ejercuci\u00f3n");
    } else if (tiempo == null || tiempo == '') {
        alert("Debe ingresar el tiempo de ejercuci\u00f3n");
    } else {
        $.ajax({
            url: "../evaluacion?accion=IngresarEvaluacion",
            type: 'POST',
            data: {actividad: data['actividad'], cuatrimestre: data['cuatrimestre'], autoeval: autoeval, dinero: dinero, tiempo: tiempo},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listaActividad(data['comp'], data['pestania'], data['cuatrimestre'], data['cuat'], data['estado']);
                        listaMeta(data['comp'], data['pestania'], data['cuatrimestre']);
                        drawChart();
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

//Mostrar detalles de indicador
$('.btn_indicador_detalle').on('click', function () {
    var data = $(this).data();
    $('#listaIndicadorTabla' + data['id']).slideToggle();
});

$('.btn_indicador_detalle').on('click', function () {
    var data = $(this).data();
    //$('#listaIndicadorTabla' + data['id']).slideToggle();
    if ($('#listaIndicadorTabla' + data['id']).hasClass('d-none')) {
        $('#listaIndicadorTabla' + data['id']).removeClass('d-none');
    } else {
        $('#listaIndicadorTabla' + data['id']).addClass('d-none');
    }
});

//Listar requerimientos
function listaRequerimiento(comp, i, estado, cuatrimestre) {
    $.ajax({
        url: "../evaluacion?accion=ListarRequerimiento" + "&comp=" + comp + "&cuatri=" + cuatrimestre + "&tipo=" + 2,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                let  total = 0, color = 'rgba(0, 0, 0, 0.05);';
                $("#listaRequerimiento" + i).empty();
                if (response.length > 0) {
                    $('#listadoRequerimiento' + i).removeClass('d-none');
                    var id, num = 1, num = 0;
                    $.each(response, function () {
                        var n = 0;
                        id = this.actividad_id;
                        var colort;
                        if (num % 2 === 0) {
                            colort = 'rgba(0, 0, 0, 0.05);';
                        } else {
                            colort = '#fff';
                        }
                        $("#listaRequerimiento" + i).append('<div class="encabezado_2 estilobody text-justify" style="background-color:' + color + ';">' + this.actividad_nombre + '</div><div class="encabezado_3 p-0" id="listaact' + this.actividad_id + i + '"></div>');
                        if (this.deudas.length > 0) {
                            $.each(this.deudas, function (indice, r) {
                                var po1 = 0, po2 = 0, po3 = 0;
                                $.each(r.cuatri, function (indice, cu) {
                                    if (cu.mes_id === 1) {
                                        po1 = cu.actividad_porcentaje;
                                    } else if (cu.mes_id === 2) {
                                        po2 = cu.actividad_porcentaje;
                                    } else if (cu.mes_id === 3) {
                                        po3 = cu.actividad_porcentaje;
                                    }
                                });
                                if (po1 === 0 || po2 === 0 || po3 === 0) {
                                    n++;
                                }
                                var rg;
                                if (r.req_rg === 0) {
                                    rg = "Ninguno";
                                } else {
                                    rg = r.req_rgnombre;
                                }
                                if (r.req_verificacion === 2) {
                                    color = 'rgba(203, 50, 52, 0.5);';
                                } else {
                                    color = colort;
                                }
                                if ((cuatrimestre == 1 && po1 > 0) || (cuatrimestre == 2 && po2 > 0) || (cuatrimestre == 3 && po3 > 0)) {
                                    $('#listaRequerimiento' + i).children('#listaact' + id + i).append('<div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '">' + r.req_nombre + '</div><div class="estilobody encabezado_2 p-0" style="background-color:' + color + '" id="cuatrireq' + r.req_id + '"></div>\n\
                                <div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + r.req_cantidad + '</div><div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_sin_iva) + '</div>\n\
                                <div class="estilobody encabezado_5 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_total) + '</div><div class="encabezado_5 estilobody centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.ae_ejecucion) + '</div>\n\
                                <div class="estilobody encabezado_4" style="background-color:' + color + '" id="accion' + r.req_id + i + '"></div>\n\
                                <div class="estilobody encabezado_completo" id="desrequerimiento' + this.req_id + i + '" style="display:none; background-color:rgba(0,0,0,0.15);"></div>');
                                    var pres;
                                    if (prio === 1) {
                                        pres = 'año:' + r.actividad_id;
                                    } else {
                                        pres = '';
                                    }
                                    if (r.tc_id < 1) {
                                        $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#desrequerimiento' + this.req_id + i).html('<div class="main__contenedor_listados_s_xs">NO PAC</div><div class="main__contenedor_listados_m_m">Descripcion: ' + r.req_descripcion + '</div><div class="main__contenedor_listados_s_m"> ' + r.financiamiento_nombre + '</div><div class="main__contenedor_listados_s_m"> ' + pres + '</div>');
                                    } else {
                                        $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#desrequerimiento' + this.req_id + i).html('<div class="main__contenedor_listados_s_xs">PAC</div><div class="main__contenedor_listados_m_m">Descripcion: ' + r.req_descripcion + '</div><div class="main__contenedor_listados_s_m"> ' + r.financiamiento_nombre + '</div><div class="main__contenedor_listados_s_m">Tipo de compra: ' + r.tc_nombre + '</div><div class="main__contenedor_listados_s_m">Unidad: ' + r.unidad_nombre + '</div>\n\
                                    <div class="main__contenedor_listados_s_m">CPC: ' + r.req_cpc + '</div><div class="main__contenedor_listados_s_m"> ' + pres + '</div>');
                                    }
                                    if (r.cuatri.length > 0) {
                                        $('#listaact' + id + i).children('#cuatrireq' + r.req_id).append('<div class="centro encabezado_6">' + po1.toFixed(2) + '%</div><div class="encabezado_6 centro">' + po2.toFixed(2) + '%</div><div class="encabezado_6 centro">' + po3.toFixed(2) + '%</div>');
                                    }
                                    total += r.ae_ejecucion;
                                }
                            });
                            if (n == this.deudas.length) {
                                $("#listaRequerimiento" + i).children('#listaact' + id + i).append('<div class="encabezado_completo estilobody" style="background-color:' + color + '">Sin requerimientos a evaluar en este cuatrimestre.</div>');
                            }
                        } else {
                            $("#listaRequerimiento" + i).children('#listaact' + id + i).html('<div class="encabezado_completo estilobody" style="background-color:' + color + '">No existe requerimientos ingresados para esta actividad</div>');
                        }
                        num++;
                    });
                    if (num % 2 === 0) {
                        color = 'rgba(0, 0, 0, 0.05);';
                    } else {
                        color = '#fff';
                    }
                }
                $("#listaRequerimiento" + i).append('<div class="encabezado_completo centro estilobody" style="background-color:' + color + '; font-weight:bold;">TOTAL: ' + new Intl.NumberFormat("US", formateador()).format(total) + '</div>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

//Ver descripcion requerimientos
$(".encabezado").on('click', '.encabezado_4 #verReq', function () {
    var data = $(this).data();
    $('#desrequerimiento' + data['req'] + data['valor']).slideToggle();
});

//Ver modal requerimientos
$(".encabezado").on('click', '.encabezado_4 #verestadosreq', function () {
    var data = $(this).data();
    $('.card').children('#inputEnviarReq').empty();
    var options = {
        weekday: "long", year: "numeric", month: "short",
        day: "numeric", hour: "2-digit", minute: "2-digit"
    };
    $.ajax({
        url: "../actividadReq?accion=ListarFechasRequerimientos",
        type: 'POST',
        data: {"req": data['req']},
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

$('#btn_proyecto_enviar').on('click', function (event) {
    event.preventDefault();
    if ($('#tipousuario').val() === "3" && estadof === 2) {
        $('#aprobarRadios').val(4);
        $('#modificarRadios').val(5);
    } else if (($('#tipousuario').val() === "4" || $('#tipousuario').val() === "5" || $('#tipousuario').val() === "19" || $('#tipousuario').val() === "11") && estadof !== 9) {
        if ($('#tipousuario').val() === "4") {
            $('#aprobarRadios').val(6);
            $('#modificarRadios').val(7);
        } else if ($('#tipousuario').val() === "5") {
            $('#aprobarRadios').val(22);
            $('#modificarRadios').val(3);
        } else if ($('#tipousuario').val() === "19") {
            $('#aprobarRadios').val(21);
            $('#modificarRadios').val(20);
        } else if ($('#tipousuario').val() === "11") {
            $('#aprobarRadios').val(15);
            $('#modificarRadios').val(14);
        }
    } else if ($('#tipousuario').val() === "4" && estadof === 9) {
        $('#aprobarRadios').val(6);
        $('#modificarRadios').val(7);
    } else if ($('#tipousuario').val() === "5" && estadof === 9) {
        $('#aprobarRadios').val(22);
        $('#modificarRadios').val(3);
    } else if ($('#tipousuario').val() === "7") {
        $('#aprobarRadios').val(10);
        $('#modificarRadios').val(12);
    } else if ($('#tipousuario').val() === "8") {
        $('#aprobarRadios').val(11);
        $('#modificarRadios').val(13);
    } else if ($('#tipousuario').val() === "19" && estadof === 9) {
        $('#aprobarRadios').val(21);
        $('#modificarRadios').val(20);
    } else if ($('#tipousuario').val() === "16") {
        $('#aprobarRadios').val(41);
        $('#modificarRadios').val(25);
    } else if ($('#tipousuario').val() === "17") {
        $('#aprobarRadios').val(41);
        $('#modificarRadios').val(26);
    } else if ($('#tipousuario').val() === "11" && estadof === 9) {
        $('#aprobarRadios').val(15);
        $('#modificarRadios').val(14);
    } else if ($('#tipousuario').val() === "14" && (estadof === 2 || estadof === 6 || estadof === 10 || estadof === 11 || estadof === 22 || estadof === 27)) {
        $('#aprobarRadios').val(42);
        $('#modificarRadios').val(14);
    }
    $('#estadoproy').val(proy);
});

$('#footereval').on('click', '#enviarModalBton', function () {
    $.ajax({
        url: "../evaluacion?accion=EnviarProyecto",
        type: 'POST',
        data: {idProyecto: $('#idProy').val(), cedula: $('#cedulaProyecto').val(), estado: $('#estadoproy').val(), cuatrimestre: $('#cuatrimestreeval').val()},
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

$('#guardarEnviar').on('click', function () {
    $.ajax({
        url: "../evaluacion?accion=EnviarProyecto",
        type: 'POST',
        data: {estado: $('input:radio[name=verificarRadios]:checked').val(), cedula: $('#cedulaProyecto').val(), idProyecto: $('#idProy').val(), cuatrimestre: $('#cuatrimestreeval').val()},
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

$('#btnEvalRep').on('click', '#btn_proyecto_reprogramar', function () {
    $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00BFEst\u00E1 seguro que desea mandar a reprogramar el proyecto?<input type="hidden" id="estadoproy" value="43">');
});