let proy, mul, fi, ff, long, banIndicador = true, banActividad = true, banRequerimiento = true, estadof, proynombre, persp, banIndicaroEl = true, banActividadElim = true, banRequerimientoElim = true;
let tusu = $('#tipousuario').val();
$(document).ready(function () {
    $.ajax({
        url: "../proyecto?accion=ListarProyectoCompleto" + "&proy=" + $("#idProy").val(),
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    proy = this.proyecto_id;
                    mul = this.proyecto_multi;
                    fi = this.proyecto_fi;
                    ff = this.proyecto_ff;
                    proynombre = this.proyecto_nombre;
                    var perfil, integrantes;
                    if (this.proyecto_doc == null) {
                        perfil = "No tiene perfil";
                    } else {
                        perfil = '<a href="https://planificacion.espoch.edu.ec/sip/formulacion/docs/' + this.proyecto_doc + '" download="' + this.proyecto_doc + '">' + this.proyecto_doc + '</a>';
                    }
                    if (this.proyecto_integrantes == null) {
                        integrantes = "No tiene integrantes";
                    } else {
                        integrantes = this.proyecto_integrantes;
                    }
                    persp = this.perspectiva_id;
                    $('#tituloAg').html(this.ag.ag_nombre);
                    $('#objEstartF').html(this.per.perspectiva_nombre);
                    $('#objObjF').html(this.per.objetivo_nombre);
                    $('#actividadPresF').html(this.per.ap_nombre);
                    $('#proyectoNombreF').html(this.proyecto_nombre);
                    $('#finProyectoF').html(this.proyecto_fin);
                    $('#propositoF').html(this.proyecto_proposito);
                    $('#fechaInicioF').html(this.proyecto_fi);
                    $('#fechaFinF').html(this.proyecto_ff);
                    $('#responsableF').html(this.proyecto_responsable);
                    $('#integrantesF').html(integrantes);
                    $('#perfilF').append(perfil + ' <a class="d-none" id="btn-cross-perfil" style="color:red" title="Eliminar" href="#"><i class="fas fa-times"></i></a>');
                    $('#montoF').html(new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto));
                    $('#mon-mod').html(new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto));
                    estadof = this.estado_id;
                    if (this.estado_id === 0) {
                        $('#estadoF').html('Planificando');
                    } else {
                        $('#estadoF').html(this.estado_nombre);
                    }
                    if ((tusu === "15" || tusu === "3") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14)) {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                    } else if ((tusu === "2" || tusu === "5" || tusu === "7" || tusu === "8" || tusu === "19") && (estadof === 0 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14)) {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                    } else {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModalVer");
                    }
                    if (this.estado.length > 0) {
                        var options = {
                            weekday: "long", year: "numeric", month: "short",
                            day: "numeric", hour: "2-digit", minute: "2-digit"
                        };
                        $.each(this.estado, function (indice, estado) {
                            var date = new Date(this.estado_fecha);
                            var observacion;
                            if (this.estado_observacion == null) {
                                observacion = '';
                            } else {
                                observacion = ',' + this.estado_observacion;
                            }
                            $('#fechaestados').append('<div>' + this.estado_nombre + ' por ' + this.usuario_nombre + ' el ' + date.toLocaleTimeString("es-ES", options) + observacion + '</div>');
                        });
                    }
                    if (!this.proyecto_multi) {
                        $('#multiCarreras').html('NO');
                    } else {
                        $('#ismulti').val(true);
                        $.each(this.areas, function (indice, area) {
                            $('#multiCarreras').append('<div>- ' + area.ag_nombre + '</div>');
                        });
                    }

                    if (this.proyecto_plurianual === 1) {
                        $('#pluriAnualV').removeClass('d-none');
                        $('#montoPluri').removeClass('d-none');
                        $('#montoPluri').addClass('d-block');
                        $('#plurianual').html('SI');
                        if (this.monto_proy.length > 0) {
                            $.each(this.monto_proy, function (indice, montos) {
                                $('#div' + montos.mp_anio).html('<label style="font-weight: bold">' + montos.mp_anio + ':</label>' + new Intl.NumberFormat("US", formateador()).format(montos.mp_monto));
                            });
                        }
                    }
                    if (this.proceso.length > 0) {
                        var co = 0, co2 = 0;
                        $.each(this.proceso, function (indice, accion) {
                            if (accion.proceso_codigo === "AI2016" || accion.proceso_codigo === "EE2019") {
                                let valPI = $('#selectProceso option:selected').text();
                                $('#modAccionInstitucional').append('<div>- ' + accion.am_nombre + '</div>');
                                if (co === 0) {
                                    $('#modAccionInstitucionalMod').append('<div class="row container main-center cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea class="evaluacion"  name="accmejins[]" rows="3">' + accion.am_nombre + '</textarea><i class="fas fa-plus" id="iconoplusins"></i> </div></div>');
                                } else {
                                    $('#modAccionInstitucionalMod').append('<div class="row container main-center cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea class="evaluacion"  name="accmejins[]" rows="3">' + accion.am_nombre + '</textarea><i class="fas fa-minus" id="iconoremoveins"></i> </div></div>');
                                }
                                co++;
                            } else {
                                $('#modAccionCarrera').append('<div>- ' + accion.am_nombre + '</div>');
                                if (co2 === 0) {
                                    $('#modAccionCarreraMod').append('<div class="row container main-center cross-center" id="acmcar"><div class="col-10 col-xs-10 col-md-9"><textarea class="evaluacion"  name="accmejca[]" rows="3">' + accion.am_nombre + '</textarea><i class="fas fa-plus" id="iconopluscar"></i> </div></div>');
                                } else {
                                    $('#modAccionCarreraMod').append('<div class="row container main-center cross-center" id="acmcar"><div class="col-10 col-xs-10 col-md-9"><textarea class="evaluacion"  name="accmejca[]" rows="3">' + accion.am_nombre + '</textarea><i class="fas fa-minus" id="iconoremovecar"></i></div></div>');
                                }
                                co2++;
                            }
                        });
                        if (co === 0) {
                            $('#modAccionInstitucionalMod').append('<div class="row container main-center cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea name="accmejins[]" class="evaluacion" rows="3"></textarea><i class="fa fa-plus" title="agregar" id="iconoplusins"></i></div></div>');
                        }
                        if (co2 === 0) {
                            $('#modAccionCarreraMod').append('<div class="row container main-center cross-center" id="acmcar"><div class="col-10 col-xs-10 col-md-9"><textarea name="accmejca[]" class="evaluacion" rows="3"></textarea><i class="fa fa-plus" title="agregar" id="iconopluscar"></i></div></div>');
                        }
                    } else {
                        $('#modAccionInstitucionalMod').append('<div class="row container main-center cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea name="accmejins[]" class="evaluacion" rows="3"></textarea><i class="fa fa-plus" title="agregar" id="iconoplusins"></i></div></div>');
                        $('#modAccionCarreraMod').append('<div class="row container main-center cross-center" id="acmcar"><div class="col-10 col-xs-10 col-md-9"><textarea name="accmejca[]" class="evaluacion" rows="3"></textarea><i class="fa fa-plus" title="agregar" id="iconopluscar"></i></div></div>');
                    }
                    if (estadof !== 16 && estadof !== 17 && estadof !== 18) {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else {
                        $('#btn_proyecto_enviar').addClass('d-none');
                    }
                });
                listarComponente(proy);
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

//Listar componente
function listarComponente(proy) {
    var i = 1;
    $.ajax({
        url: "../componenteMeta?accion=ListarComponentes" + "&proy=" + proy,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    if (response.length < 2) {
                        $.each(response, function () {
                            $("#idComponente" + i).val(this.componente_id);
                            $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                            if (tusu === "15" && estadof === 3) {
                                $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                            } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                                $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                            } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                                $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                            } else {
                                $('#agregaC').addClass('d-none');
                                $("#btn_comp" + i).empty();
                            }
                            listaMeta(this.componente_id, i);
                            long = response.length;
                            listaActividad(this.componente_id, i, long);
                            listaRequerimiento(this.componente_id, i);
                            i++;
                        });
                    } else {
                        var sm = 1;
                        $.each(response, function () {
                            $("#idComponente" + i).val(this.componente_id);
                            $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');

                            $('#agregaC').addClass('d-none');
                            $("#btn_comp" + i).empty();

                            listaMeta(this.componente_id, i);
                            long = response.length;
                            listaActividad(this.componente_id, i);
                            listaRequerimiento(this.componente_id, i);
                            i++;
                            sm++;
                        });
                    }
                } else {
                    $('#agregaC').addClass('d-none');
                    $('#btn_proyecto_enviar').addClass('d-none');
                    $('#botonIndicador' + i).addClass('d-none');
                    $('#botonActividad' + i).addClass('d-none');
                    $('#detalleIndicador' + i).addClass('d-none');
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
                } else if (response.length === 4 && ($('#tipoAg').val() === "4" || $('#tipoAg').val() === "1" || $('#idAgObEs').val() === "2" || $('#idAgObEs').val() === "8" || $('#idAgObEs').val() === "14" || $('#idAgObEs').val() === "17" || $('#idAgObEs').val() === "22" || $('#idAgObEs').val() === "27" || $('#idAgObEs').val() === "31" || $('#idAgObEs').val() === "36" || $('#idAgObEs').val() === "37")) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $("#pestania4").removeClass('d-none');
                    $('#agregaC').remove();
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
function listaMeta(comp, i) {
    $.ajax({
        url: "../componenteMeta?accion=ListarMeta" + "&comp=" + comp,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $.each(response, function () {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#contenedorMeta' + i).addClass('d-flex');
                        $("#metaF" + i).attr('type', 'hidden');
                        $("#inputMeta" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Meta:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.meta_nombre + '</div><input type="hidden" name="idMeta' + i + '" id="idMeta' + i + '" value="' + this.meta_id + '"></div>');
                        $("#btn_meta" + i).empty();
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
        url: "../componenteMeta?accion=ListarIndicador" + "&meta=" + meta,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    var con = 1;
                    $('#listaIndicadores' + i).empty();
                    $('#lisIndicador' + i).empty();
                    $('#detalleIndicador' + i).removeClass('d-none');
                    $('#contenedorIndicador' + i).removeClass('d-block');
                    $('#contenedorIndicador' + i).addClass('d-none');
                    $.each(response, function () {
                        var formula, indic;
                        if (this.indicador_tipo === "Cualitativo") {
                            formula = "No tiene formula";
                        } else {
                            formula = this.indicador_ejecutado + " / " + this.indicador_planificado+ "("+this.indicador_numero+")";
                        }
                        if (con === 1) {
                            indic = "Indicadores: ";
                        } else {
                            indic = " ";
                        }
                        $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"></div></div></div>');
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

function listaActividad(comp, i) {
    $("#listaActividades" + i).empty();
    let total = 0.0, color = 'rgba(0, 0, 0, 0.05);';
    $.ajax({
        url: "../actividadReq?accion=ListarActividad" + "&comp=" + comp,
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
//Tipo de actividad
                        if (this.actividad_tipo === 0) {
                            monto = "-----";
                            div2 = " ";
                            div = '----';
                        } else {
                            monto = new Intl.NumberFormat("US", formateador()).format(this.actividad_monto);
                            div = '<i class="fas fa-edit" title="Editar Actividad" id="modActividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '"></i><i class="fas fa-trash-alt" id="elimActividad" title="Eliminar actividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '"></i><i class="fas fa-cart-plus" title="Agregar Requerimientos" id="agregarReq" data-id="' + this.actividad_id + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-valor="' + i + '"></i>';
                            if (estadof === 16 || estadof === 17 || estadof === 18) {
                                if (this.actividad_prioridad === 1) {
                                    div = '<i class="fas fa-check-double"></i>';
                                } else if (this.actividad_prioridad === 2) {
                                    div = '<i class="fas fa-check"></i>';
                                } else {
                                    div = '<i class="fas fa-times"></i>';
                                }
                            } else {
                                if (this.actividad_prioridad === 1) {
                                    div = '<input type="checkbox" onchange="guardarprioridadactividad(' + this.actividad_id + ', ' + comp + ',' + i + ')" name="checkpriorizar" id="checkpriorizar' + this.actividad_id + '" value="1" data-id="' + this.actividad_id + '" checked>';
                                } else {
                                    div = '<input type="checkbox" onchange="guardarprioridadactividad(' + this.actividad_id + ', ' + comp + ',' + i + ')" name="checkpriorizar" id="checkpriorizar' + this.actividad_id + '" value="1" data-id="' + this.actividad_id + '">';
                                }
                            }
                        }

                        $("#listaActividades" + i).append('<div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_2 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_4 estilobody" style="background-color:' + color + '">' + div + '</div>');
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
                        total += this.actividad_monto;
                        num++;
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

//Click en el checkbox priorizacion
function guardarprioridadactividad(id, comp, i) {
    var prioridad;
    if ($('#checkpriorizar' + id).is(':checked')) {
        prioridad = 1;
    } else {
        prioridad = 2;
    }

    $.ajax({
        url: "../actividadReq?accion=PriorizarActividad",
        type: 'POST',
        data: {actividad: id, prioridad: prioridad, cedula: $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    listaActividad(comp, i);
                    listaRequerimiento(comp, i);
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

//Listar requerimientos
function listaRequerimiento(comp, i) {
    $.ajax({
        url: "../actividadReq?accion=ListarRequerimiento" + "&comp=" + comp,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                let  total = 0, color = 'rgba(0, 0, 0, 0.05);';
                $("#listaRequerimiento" + i).empty();
                if (response.length > 0) {
                    $('#listadoRequerimiento' + i).removeClass('d-none');
                    $('#contenedorReqPac' + i)[0].reset();
                    var id, num = 1, num = 0;
                    $.each(response, function () {
                        id = this.actividad_id;
                        var colort;
                        if (num % 2 === 0) {
                            colort = 'rgba(0, 0, 0, 0.05);';
                        } else {
                            colort = '#fff';
                        }
                        $("#listaRequerimiento" + i).append('<div class="encabezado_2 estilobody text-justify" style="background-color:' + color + ';">' + this.actividad_nombre + '</div><div class="encabezado_3 p-0" id="listaact' + this.actividad_id + i + '"></div>');
                        if (this.req.length > 0) {
                            $.each(this.req, function (indice, r) {
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
                                var pac, rg;
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

                                if (estadof === 16 || estadof === 17 || estadof === 18) {
                                    if (r.req_prioridad === 1) {
                                        pac = '<div class="icon_ter"><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i></div><div class="icon_ter"><i class="fas fa-check"></i></div>';
                                    } else {
                                        pac = '<div class="icon_ter"><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i></div><div class="icon_ter"><i class="fas fa-times"></i></div>';
                                    }
                                } else {
                                    if (r.req_prioridad === 1) {
                                        pac = '<div class="icon_ter"><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i></div><div class="icon_ter"><input type="checkbox" class="reqchpriori" name="checkreqprio" id="checkreqprio' + r.req_id + '" checked onchange="guardarprioridadreq(' + r.req_id + ', ' + comp + ',' + i + ')"></div>';
                                    } else {
                                        pac = '<div class="icon_ter"><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i></div><div class="icon_ter"><input type="checkbox" class="reqchpriori" name="checkreqprio" id="checkreqprio' + r.req_id + '" onchange="guardarprioridadreq(' + r.req_id + ', ' + comp + ',' + i + ')"></div>';
                                    }
                                }
                                $('#listaRequerimiento' + i).children('#listaact' + id + i).append('<div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '">' + r.req_nombre + '</div><div class="estilobody encabezado_2 p-0" style="background-color:' + color + '" id="cuatrireq' + r.req_id + '"></div>\n\
                                <div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + r.req_cantidad + '</div><div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_unitario) + '</div>\n\
                                <div class="estilobody encabezado_5 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_sin_iva) + '</div><div class="encabezado_5 estilobody centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_total) + '</div>\n\
                                <div class="estilobody encabezado_4" style="background-color:' + color + '" id="accion' + r.req_id + i + '">' + pac + '</div>\n\
                                <div class="estilobody encabezado_completo" id="desrequerimiento' + this.req_id + i + '" style="display:none; background-color:rgba(0,0,0,0.15);"></div>');
                                if (r.tc_id < 1) {
                                    $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#desrequerimiento' + this.req_id + i).html('<div class="main__contenedor_listados_s_xs">NO PAC</div><div class="main__contenedor_listados_m_m">Descripcion: ' + r.req_descripcion + '</div><div class="main__contenedor_listados_s_m"> ' + r.financiamiento_nombre + '</div>');
                                } else {
                                    $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#desrequerimiento' + this.req_id + i).html('<div class="main__contenedor_listados_s_xs">PAC</div><div class="main__contenedor_listados_m_m">Descripcion: ' + r.req_descripcion + '</div><div class="main__contenedor_listados_s_m"> ' + r.financiamiento_nombre + '</div><div class="main__contenedor_listados_s_m">Tipo de compra: ' + r.tc_nombre + '</div><div class="main__contenedor_listados_s_m">Unidad: ' + r.unidad_nombre + '</div>\n\
                                    <div class="main__contenedor_listados_s_m">CPC: ' + r.req_cpc + '</div>');
                                }
                                if (r.cuatri.length > 0) {
                                    $('#listaact' + id + i).children('#cuatrireq' + r.req_id).append('<div class="centro encabezado_6">' + po1.toFixed(2) + '%</div><div class="encabezado_6 centro">' + po2.toFixed(2) + '%</div><div class="encabezado_6 centro">' + po3.toFixed(2) + '%</div>');
                                }
                                total += r.req_costo_total;
                            });
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

//Click en el checkbox priorizacion
function guardarprioridadreq(id, comp, i) {
    var prioridad;
    if ($('#checkreqprio' + id).is(':checked')) {
        prioridad = 1;
    } else {
        prioridad = 0;
    }

    $.ajax({
        url: "../actividadReq?accion=PriorizarRequerimiento",
        type: 'POST',
        data: {req: id, prioridad: prioridad, cedula: $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    listaActividad(comp, i);
                    listaRequerimiento(comp, i);
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

$('.btn_indicador_detalle').on('click', function () {
    var data = $(this).data();
    //$('#listaIndicadorTabla' + data['id']).slideToggle();
    if ($('#listaIndicadorTabla' + data['id']).hasClass('d-none')) {
        $('#listaIndicadorTabla' + data['id']).removeClass('d-none');
    } else {
        $('#listaIndicadorTabla' + data['id']).addClass('d-none');
    }
});

$('#guardarEnviar').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EnviarProyecto",
        type: 'POST',
        data: {estado: $('input:radio[name=verificarRadios]:checked').val(), cedulaProyecto: $('#cedulaProyecto').val(), proyecto: $('#idProy').val(), observacion: '', tipou: $('#tipousuario').val()},
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