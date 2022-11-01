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
    $('#cuatrieval').val($('#selectcuatrimestre').val());
    $('#cuatrimestreeval').val($('#selectcuatrimestre').val());
    listaProyecto($("#idProy").val(), $('#cuatrimestreeval').val());
    $('#cuatrititulo').html('EVALUACIÓN ' + cu + ' CUATRIMESTRE');
    drawChart();
});

$(document).ready(function () {
    listaProyecto($("#idProy").val(), $('#cuatrimestreeval').val());
});

function listaProyecto(proye, cuatrimestre) {
    $.ajax({
        url: "../proyecto?accion=ListarProyectoCompEvaluacion",
        data: {proy: proye, cuatrimestre: cuatrimestre},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var cuatri;
                $.each(response, function () {
                    proy = this.proyecto_id;
                    estado = this.estado_id;
                    cuatri = this.tp_id;
                    $('#modNombreProy').html(this.proyecto_nombre);
                    $('#modMontoProy').html(new Intl.NumberFormat("US", options2).format(this.proyecto_monto));
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
                    } else if ((tusu === "2" || tusu === "5" || tusu === "8" || tusu === "19") && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                    } else {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModalVer");
                    }

                    $('#fechaestados').empty();
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

                let es, time;
                $.ajax({
                    url: "../proyecto?accion=VerificacionEnviosE",
                    type: 'POST',
                    data: {proyecto: proy, cuatrimestre: cuatrimestre},
                    dataType: 'json',
                    async: false
                })
                        .done(function (response) {
                            es = response.estado;
                        })
                        .fail(function () {
                            console.log('No existe conexión con la base de datos.');
                        })
                        .always(function () {
                            console.log('Se completo correctamente');
                        });

                $.ajax({
                    url: "../proyecto?accion=VerificacionEnviosET",
                    type: 'POST',
                    data: {tipo: 2},
                    dataType: 'json',
                    async: false
                })
                        .done(function (response) {
                            time = response.tiempo;
                        })
                        .fail(function () {
                            console.log('No existe conexión con la base de datos.');
                        })
                        .always(function () {
                            console.log('Se completo correctamente');
                        });

                if (!es && !time) {
                    $('#btn_proyecto_enviar').addClass('d-none');
                } else if (tusu === "15" && estadof === 3) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "19") && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if ((tusu === "2" || tusu === "5" || tusu === "8" || tusu === "19") && estadof === 1) {
                    $('#btn_proyecto_enviar').removeClass('d-none');
                } else if (tusu === "3" && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                    $('#btn_proyecto_enviar').removeClass('d-none');
                } else if (tusu === "3" && (estadof === 1)) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if ((tusu === "5" || tusu === "8" || tusu === "19") && (estadof === 8)) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if (tusu === "7" && (estadof === 41)) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else {
                    $('#btn_proyecto_enviar').addClass('d-none');
                }

                if ((tusu === "2" || tusu === "15" || tusu === "3" || tusu === "19") && (estadof === 43 || estadof === 46)) {
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
        url: "../proyecto?accion=TiempoE",
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
                            if (this.componente_ag == $('#idAgObEs').val()) {
                                if (porcentajecuatrimestre(this.componente_id, cuatrimestre) > 0) {
                                    $('#contenedorlogros' + i).removeClass('d-none');
                                    $("#idComponente" + i).val(this.componente_id);
                                    $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                                    listaMeta(this.componente_id, i, cuatrimestre);
                                    long = response.length;
                                    listarLogros(this.componente_id, i);
                                    listaActividad(this.componente_id, i, cuatrimestre, cuatri, estado);
                                    listaRequerimiento(this.componente_id, i, estado, cuatrimestre);
                                    i++;
                                } else {
                                    $("#idComponente" + i).val(this.componente_id);
                                    $("#inputMeta" + i).empty();
                                    $("#lisIndicador" + i).empty();
                                    $("#listaActividades" + i).empty();
                                    $("#listaRequerimiento" + i).empty();
                                    $("#listaIndicadores" + i).empty();
                                    $('#btnBotonMeta' + i).empty();
                                    $("#inputComp" + i).html('<div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">Sin actividades a evaluar en este cuatrimestre</div></div>');
                                    i++;
                                }
                            }
                        });
                    } else {
                        var sm = 1;
                        $.each(response, function () {
                            if (this.componente_ag == $('#idAgObEs').val()) {
                                if (porcentajecuatrimestre(this.componente_id, cuatrimestre) > 0) {
                                    $('#contenedorlogros' + i).removeClass('d-none');
                                    $("#idComponente" + i).val(this.componente_id);
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
                                    $("#inputMeta" + i).empty();
                                    $("#lisIndicador" + i).empty();
                                    $("#listaActividades" + i).empty();
                                    $("#listaRequerimiento" + i).empty();
                                    $("#listaIndicadores" + i).empty();
                                    $('#btnBotonMeta' + i).empty();
                                    $("#inputComp" + i).html('<div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">Sin actividades a evaluar en este cuatrimestre</div></div>');
                                    i++;
                                    sm++;
                                }
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
                } else if (response.length === 3 && $('#tipoAg').val() === "4") {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                } else if (response.length === 4 && ($('#tipoAg').val() === "4" || $('#tipoAg').val() === "2" || $('#tipoAg').val() === "3" || $('#tipoAg').val() === "5" || $('#idAgObEs').val() === "2" || $('#idAgObEs').val() === "8" || $('#idAgObEs').val() === "14" || $('#idAgObEs').val() === "17" || $('#idAgObEs').val() === "22" || $('#idAgObEs').val() === "27" || $('#idAgObEs').val() === "31" || $('#idAgObEs').val() === "36" || $('#idAgObEs').val() === "37")) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $("#pestania4").removeClass('d-none');
                    $('#agregaC').remove();
                } else if (response.length === 5 && ($('#tipoAg').val() === "4" || $('#tipoAg').val() === "2" || $('#tipoAg').val() === "3" || $('#tipoAg').val() === "5" || $('#idAgObEs').val() === "2" || $('#idAgObEs').val() === "8" || $('#idAgObEs').val() === "14" || $('#idAgObEs').val() === "17" || $('#idAgObEs').val() === "22" || $('#idAgObEs').val() === "27" || $('#idAgObEs').val() === "31" || $('#idAgObEs').val() === "36" || $('#idAgObEs').val() === "37")) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $("#pestania4").removeClass('d-none');
                    $("#pestania5").removeClass('d-none');
                    $('#agregaC').remove();
                } else if (response.length === 1 && (tusu === "15" && estadof === 3)) {
                    $('#agregaC').removeClass('d-none');
                } else if (response.length === 1 && ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26))) {
                    $('#agregaC').removeClass('d-none');
                } else if (response.length === 1 && (tusu === "3" && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26))) {
                    $('#agregaC').removeClass('d-none');
                } else {
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

//Listar meta
function listaMeta(comp, i, cuat) {
    $('#btn_meta' + i).empty();
    $('#btnBotonMeta' + i).empty();
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
                                if ($('#tipousuario').val() === '3' && estado == 1) {
                                    if (eval.actividad_nombre == null || eval.actividad_nombre == 'undefined') {
                                        if (eval.ae_observacion == null) {
                                            porcen = eval.ae_autoeval + '% <i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i>';
                                        } else {
                                            porcen = eval.ae_autoeval + '%,  ' + eval.ae_observacion + '<i class="fas fa-comments" title="Agregar observaci\u00f3n" id="observacion" data-comp="' + comp + '" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-porcentaje="' + eval.ae_autoeval + '" data-observacion="' + eval.ae_observacion + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i>';
                                        }
                                    } else {
                                        porcen = eval.actividad_nombre + '%';
                                    }
                                } else if (estado == 1 || estado == 2 || estado == 4 || estado == 6 || estado == 8 || estado == 10 || estado == 11 || estado == 21 || estado == 22 || estado == 41 || estado == 42 || estado == 43 || estado == 44 || estado == 45 || estado == 46) {
                                    if (eval.actividad_nombre == null || eval.actividad_nombre == 'undefined') {
                                        if (eval.ae_observacion == null) {
                                            porcen = eval.ae_autoeval + '%';
                                        } else {
                                            porcen = eval.ae_autoeval + '%  ' + eval.ae_observacion;
                                        }
                                    } else {
                                        porcen = eval.actividad_nombre + '%';
                                    }
                                } else {
                                    if (eval.ae_observacion == null) {
                                        obser = " ";
                                    } else {
                                        obser = eval.ae_observacion
                                    }
                                    porcen = '<div class="inputev">' + eval.ae_autoeval + '% ' + obser + '</div><div class="botonevm" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-comp="' + comp + '" data-cuat="' + cuat + '" data-estado="' + estado + '" data-bandera="' + false + '" data-auto="' + eval.ae_autoeval + '" id="modAuto"><i class="fas fa-edit"></i></div>';
                                }
                            });
                        } else {
                            if (estado == 1 || estado == 2 || estado == 4 || estado == 6 || estado == 8 || estado == 10 || estado == 11 || estado == 21 || estado == 22 || estado == 41 || estado == 42 || estado == 43 || estado == 44 || estado == 45 || estado == 46) {
                                porcen = '----';
                            } else {
                                porcen = '<div class="inputev"><input type="text" class="form-control" name="porcen' + id + i + '" id="porcen' + id + i + '"></div><div class="botonev guardar" data-actividad="' + id + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-comp="' + comp + '" data-cuat="' + cuat + '" data-estado="' + estado + '" data-bandera="' + true + '"><i class="fas fa-save"></i></div>';
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
                        if (cuat == cuatrimestre && (estado == 1 || estado == 2 || estado == 4 || estado == 6 || estado == 8 || estado == 10 || estado == 11 || estado == 21 || estado == 22 || estado == 41 || estado == 42 || estado == 43 || estado == 44 || estado == 45 || estado == 46)) {
                            div = '<i class="fas fa-chevron-circle-down lisArchivos" data-id="' + this.actividad_id + '" data-pestania="' + i + '"></i><i class="fas fa-list-alt lisReq" data-id="' + this.actividad_id + '" data-pestania="' + i + '"></i>';
                        } else {
                            div = '<i class="fas fa-upload agregarArch" title="Cargar Archivos" data-id="' + this.actividad_id + '" data-nombre="' + this.actividad_nombre + '" data-componente="' + comp + '" data-pestania="' + i + '" data-cuat="' + cuat + '" data-estado="' + estado + '"></i><i class="fas fa-chevron-circle-down lisArchivos" data-id="' + this.actividad_id + '" data-pestania="' + i + '"></i><i class="fas fa-list-alt lisReq" data-id="' + this.actividad_id + '" data-pestania="' + i + '"></i>';
                        }

                        if ((cuatrimestre == 1 && po1 > 0) || (cuatrimestre == 2 && po2 > 0) || (cuatrimestre == 3 && po3 > 0)) {
                            $("#listaActividades" + i).append('<div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_5 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_5 estilobody text-justify" style="background-color:' + color + '" id="listaeval' + this.actividad_id + i + '">' + porcen + '</div><div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '">' + div + div2 + '</div>\n\
                                <div class="encabezado_completo estilo p-0" id="listaArchivos' + this.actividad_id + i + '" style="background-color:rgba(0,0,0,0.2); display:none;"><div class="encabezado_6 estilo" style="font-weight:bold;">Descripci\u00f3n Archivo</div><div class="encabezado_6 estilo" style="font-weight:bold;">Nombre Archivo</div>\n\
                                <div class="encabezado_6 estilo" style="font-weight:bold;">Acci\u00f3n</div><div class="encabezado_completo estilo p-0" id="lArchivos' + this.actividad_id + i + '"></div></div>');


                            if (this.req.length > 0) {
                                var div3;
                                $.each(this.req, function (indice, archivos) {
                                    if (cuat == cuatrimestre && (estado == 1 || estado == 2 || estado == 4 || estado == 6 || estado == 8 || estado == 10 || estado == 11 || estado == 21 || estado == 22 || estado == 41 || estado == 42 || estado == 43 || estado == 44 || estado == 45 || estado == 46)) {
                                        div3 = ' ';
                                    } else {
                                        div3 = '<div class="icono_i" id="editararchivo" data-id="' + archivos.are_id + '" data-nombre="' + archivos.are_descripcion + '" data-idact="' + id + '" data-componente="' + comp + '" data-pestania="' + i + '" data-baneval="' + false + '"  data-cuat="' + cuat + '" data-estado="' + estado + '" data-cuatrimestre="' + cuatrimestre + '" >\n\
                                    <i class="fas fa-pen-square"></i></div><div class="icono_i" style="color:red" id="eliminararchivo" data-id="' + archivos.are_id + '" data-componente="' + comp + '" data-archivo="' + archivos.are_archivo + '" data-pestania="' + i + '" data-cuatrimestre="' + cuatrimestre + '" data-cuat="' + cuat + '" data-estado="' + estado + '"><i class="far fa-times-circle"></i></div>';
                                    }

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

$('.encabezado').on('click', '.encabezado_4 .lisArchivos', function () {
    var data = $(this).data();
    $('.encabezado').children('#listaArchivos' + data['id'] + data['pestania']).slideToggle();
});

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

$('.encabezado').on('click', '.encabezado_4 .lisReq', function () {
    $('#generarJ').modal();
});

$('.encabezado').on('click', '.encabezado_4 .agregarArch', function () {
    var data = $(this).data();
    $('#nombrearchivos').html(data['nombre']);
    $('#actividadarchivo').val(data['id']);
    $('#componentearchivo').val(data['componente']);
    $('#pestania').val(data['pestania']);
    $('#cuatrimestreevalact').val($('#cuatrimestreeval').val());
    $('#cuateval').val(data['cuat']);
    $('#estadoeval').val(data['estado']);
    $('#archivosS').modal();
    banarchivos = true;
});

$('.encabezado').on('click', '.encabezado_5 #modAuto', function () {
    var data = $(this).data();
    $('.encabezado').children('.encabezado_5#listaeval' + data['actividad'] + data['pestania']).html('<div class="inputev"><input type="text" class="form-control" name="porcen' + data['actividad'] + data['pestania'] + '" id="porcen' + data['actividad'] + data['pestania'] + '" value="' + data['auto'] + '"></div><div class="botonev guardar" data-actividad="' + data['actividad'] + '" data-pestania="' + data['pestania'] + '" data-cuatrimestre="' + data['cuatrimestre'] + '" data-comp="' + data['comp'] + '" data-cuat="' + data['cuat'] + '" data-estado="' + data['estado'] + '"><i class="fas fa-save"></i></div>');
});

$('.encabezado').on('click', '.encabezado_5 #observacion', function () {
    var data = $(this).data();
    $('.encabezado').children('.encabezado_5#listaeval' + data['actividad'] + data['pestania']).html('<div class="inputev"><input type="text" id="observ' + data['actividad'] + data['pestania'] + '" class="form-control"></div><div class="botonev2 guardar"><i class="fas fa-save" id="observguardar" data-actividad="' + data['actividad'] + '" data-pestania="' + data['pestania'] + '" data-cuatrimestre="' + data['cuatrimestre'] + '" data-comp="' + data['comp'] + '" data-cuat="' + data['cuat'] + '" data-estado="' + data['estado'] + '" data-comp="' + data['comp'] + '"></i><i class="fas fa-times-circle" id="cancelarobs" data-pestania="' + data['pestania'] + '" data-cuatrimestre="' + data['cuatrimestre'] + '" data-comp="' + data['comp'] + '" data-cuat="' + data['cuat'] + '" data-estado="' + data['estado'] + '"></i></div>');
});

$('.encabezado').on('click', '.encabezado_5 #cancelarobs', function () {
    var data = $(this).data();
    listaActividad(data['comp'], data['pestania'], data['cuatrimestre'], data['cuat'], data['estado']);
});

$('.encabezado').on('click', '.encabezado_5 .botonev2 #observguardar', function () {
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

//Comprobar extension
function comprueba_extensionE(archivo) {
    $('#archivoactividad').empty();
    var extensiones_permitidas = new Array(".pdf", ".rar", ".zip");
    //recupero la extensión de este nombre de archivo 
    var extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();
    //compruebo si la extensión está entre las permitidas 
    var permitida = false;
    for (var i = 0; i < extensiones_permitidas.length; i++) {

        if (extensiones_permitidas[i] === extension) {
            permitida = true;
            break;
        }
    }
    if (!permitida) {
        alert("Compruebe la extensi\u00f3n de los archivos a subir.");
    } else {
        return 1;
    }
    return 0;
}

$('#modalGuardarArc').on('click', function () {
    var comp = $('#componentearchivo').val();
    var pest = $('#pestania').val();
    var cuatri = $('#cuatrimestreevalact').val();
    if ($('#descarchivo').val() == null || $('#descarchivo').val() == '') {
        alert("Debe ingresar la descripci\u00f3n del archivo.");
    } else if (banarchivos) {
        var archivo = document.getElementById("archivoactividad");
        // the file is the first element in the files property
        var file = archivo.files[0];
        if (file == null) {
            alert("Debe ingresar el archivo");
        } else {
            var doc = document.getElementById('archivoactividad').files[0].name;
            if (comprueba_extensionE(doc) === 1) {
                $.ajax({
                    url: "../evaluacion?accion=IngresarEvidencias",
                    type: 'POST',
                    dataType: 'json',
                    cache: false,
                    processData: false,
                    data: new FormData($('#FrmArchivosEvidencias')[0]),
                    contentType: false
                })
                        .done(function (response) {
                            if (response === "Correcto") {
                                listaActividad(comp, pest, cuatri, $('#cuateval').val(), $('#estadoeval').val());
                                $('#archivosS').modal('hide');
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
                alert("Verifique que el archivo sea .pdf, .rar o .zip");
            }
        }
    } else {
        $.ajax({
            url: "../evaluacion?accion=ModificarArchivos",
            type: 'POST',
            data: {id: $('#idevalmod').val(), descripcion: $('#descarchivo').val(), cedulaProyecto: $('#cedulaProyecto').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listaActividad($('#componentearchivo').val(), $('#pestania').val(), $('#cuatrimestreevalact').val(), $('#cuateval').val(), $('#estadoeval').val());
                        $('#archivosS').modal('hide');
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

$('.encabezado').on('click', '.encabezado_completo .encabezado_completo .encabezado_6 #editararchivo', function () {
    var data = $(this).data();
    $('#nombrearchivos').html(data['nombreact']);
    $('#actividadarchivo').val(data['idact']);
    $('#componentearchivo').val(data['componente']);
    $('#pestania').val(data['pestania']);
    $('#cuatrimestreevalact').val(data['cuatrimestre']);
    $('#descarchivo').val(data['nombre']);
    $('#archivoactividad').prop("disabled", true);
    $('#cuateval').val(data['cuat']);
    $('#idevalmod').val(data['id']);
    $('#estadoeval').val(data['estado']);
    $('#archivosS').modal();
    banarchivos = false;
});

$('.encabezado').on('click', '.encabezado_completo .encabezado_completo .encabezado_6 #eliminararchivo', function () {
    var data = $(this).data();
    $('#inputElim').html('<input type="hidden" id="comeval" value="' + data['componente'] + '"><input type="hidden" id="pestaniaeval" value="' + data['pestania'] + '">\n\
    <input type="hidden" id="ideval" value="' + data['id'] + '"><input type="hidden" id="cuateval2" value="' + data['cuatrimestre'] + '">\n\
    <input type="hidden" id="archivoeval" value="' + data['archivo'] + '"><input type="hidden" id="cuatevalel" value="' + data['cuat'] + '">\n\
    <input type="hidden" id="estadoevalel" value="' + data['estado'] + '">');
    $('#eliminarModal').modal();
});

$('#eliminarModalBton').on('click', function () {
    $.ajax({
        url: "../evaluacion?accion=EliminarArchivos",
        type: 'POST',
        data: {id: $('#ideval').val(), archivo: $('#archivoeval').val(), cuat: $('#cuateval2').val(), cedulaProyecto: $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    listaActividad($('#comeval').val(), $('#pestaniaeval').val(), $('#cuateval2').val(), $('#cuatevalel').val(), $('#estadoevalel').val());
                    $('#eliminarModal').modal('hide');
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

$('.encabezado').on('click', '.encabezado_5 .botonev', function () {
    var data = $(this).data();
    var porcentaje = $('#porcen' + data['actividad'] + data['pestania']).val();
    if (porcentaje > -1 && porcentaje < 101) {
        if (data['bandera']) {
            $.ajax({
                url: "../evaluacion?accion=IngresarAutoEvaluacion",
                type: 'POST',
                data: {actividad: data['actividad'], cuatrimestre: data['cuatrimestre'], porcentaje: porcentaje, cedulaProyecto: $('#cedulaProyecto').val()},
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
        } else {
            $.ajax({
                url: "../evaluacion?accion=ModificarAutoEvaluacion",
                type: 'POST',
                data: {actividad: data['actividad'], cuatrimestre: data['cuatrimestre'], porcentaje: porcentaje, cedulaProyecto: $('#cedulaProyecto').val()},
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
    } else {
        alert("El valor ingresado debe estar entre 0 - 100");
    }
});

//Mostrar detalles de indicador
$('.btn_indicador_detalle').on('click', function () {
    var data = $(this).data();
    $('#listaIndicadorTabla' + data['id']).slideToggle();
});

//Guardar logros
$('.row').on('click', '.col-12 .btn_guardar_logros', function (event) {
    event.stopImmediatePropagation();
    var data = $(this).data();
    $.ajax({
        url: "../componenteMeta?accion=IngresarComponenteLogros",
        type: 'POST',
        data: {"componente": $('#idComponente' + data['id']).val(), logros: $('#txtLogros' + data['id']).val(), nudos: $('#txtNudos' + data['id']).val(), cuatrimestre: $('#cuatrimestreeval').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    listarLogros($('#idComponente' + data['id']).val(), data['id']);
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

//Listar Logros
function listarLogros(comp, i) {
    $('#txtLogros' + i).prop('readonly', false);
    $('#txtNudos' + i).prop('readonly', false);
    $('#txtLogros' + i).val('');
    $('#txtNudos' + i).val('');
    $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).removeClass('btn_guardar_logros_mod');
    $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).addClass('btn_guardar_logros');
    $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).text("INGRESAR");
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
                            $('#txtLogros' + i).prop('readonly', false);
                            $('#txtNudos' + i).prop('readonly', false);
                            $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).removeClass('btn_guardar_logros_mod');
                            $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).addClass('btn_guardar_logros');
                            $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).text("INGRESAR");
                        } else {
                            $('#contenedorlogros' + i).children('.col-12').children('#txtLogros' + i).val(this.componente_nombre).prop("readonly", true);
                            $('#contenedorlogros' + i).children('.col-12').children('#txtNudos' + i).val(this.meta_nombre).prop("readonly", true);
                            $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).addClass('btn_guardar_logros_mod');
                            $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).removeClass('btn_guardar_logros');
                            $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).text("MODIFICAR");
                        }

                        if (estado == 1 || estado == 2 || estado == 4 || estado == 6 || estado == 8 || estado == 10 || estado == 11 || estado == 21 || estado == 22 || estado == 41 || estado == 42 || estado == 43 || estado == 44 || estado == 45 || estado == 46) {
                            $('#botonLog' + i).addClass('d-none');
                        }
                    });
                } else {
                    $('#txtLogros' + i).prop('readonly', false);
                    $('#txtNudos' + i).prop('readonly', false);
                    $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).removeClass('btn_guardar_logros_mod');
                    $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).addClass('btn_guardar_logros');
                    $('#contenedorlogros' + i).children('.col-12').children('#guardarComp' + i).text("INGRESAR");
                    $('#botonLog' + i).removeClass('d-none');
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('.row').on('click', '.col-12 .btn_guardar_logros_mod', function () {
    var data = $(this).data();
    $('#txtLogros' + data['id']).prop('readonly', false);
    $('#txtNudos' + data['id']).prop('readonly', false);
    $('#botonLog' + data['id']).html('<button class="btn bton btn_guardar_logros" id="guardarComp' + data['id'] + '" data-id="' + data['id'] + '">GUARDAR</button>');
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
                                if ((po1 === 0 && cuatrimestre==1) || (cuatrimestre==2 && po2 === 0) || (cuatrimestre==3 && po3 === 0)) {
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
                                $("#listaRequerimiento" + i).children('#listaact' + id + i).html('<div class="encabezado_completo estilobody" style="background-color:' + color + '">Sin requerimientos a evaluar en este cuatrimestre.</div>');
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
    let proy;
    if ($('#tipousuario').val() === "15") {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 1;
    } else if (($('#tipousuario').val() === "2" || $('#tipousuario').val() === "5" || $('#tipousuario').val() === "8") && (estadof == "undefined" || estadof == null || estadof === 0 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 2;
    } else if ($('#tipousuario').val() === "3" && estadof !== 1) {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 8;
    } else if ($('#tipousuario').val() === "19" && (estadof == "undefined" || estadof == null || estadof === 0)) {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 27;
    } else if ($('#tipousuario').val() === "7" && estadof === 41) {
        $('#aprobarRadios').val(10);
        $('#modificarRadios').val(12);
    } else if (($('#tipousuario').val() === "2" || $('#tipousuario').val() === "5" || $('#tipousuario').val() === "8") && (estadof === 1 || estadof === 8)) {
        $('#aprobarRadios').val(2);
        $('#modificarRadios').val(3);
    } else if ($('#tipousuario').val() === "3" && estadof === 1) {
        $('#aprobarRadios').val(4);
        $('#modificarRadios').val(5);
    } else if ($('#tipousuario').val() === "19" && estadof === 8) {
        $('#aprobarRadios').val(27);
        $('#modificarRadios').val(20);
    }
    $('#estadoproy').val(proy);
});

$('#enviarModalBton').on('click', function () {
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