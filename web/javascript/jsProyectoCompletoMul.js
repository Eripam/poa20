let proy, mul, fi, ff, long, banIndicador = true, banActividad = true, banRequerimiento = true, estadof, proynombre, persp, banIndicaroEl = true, banActividadElim = true, banRequerimientoElim = true;
let tusu = $('#tipousuario').val();
let tiempo = $('#fechacierre').val();
let m, prio, banAnio = false, usuarioen;
$(document).ready(function () {
    var sareas, i = 1;
    $.ajax({
        url: "../proyecto?accion=ListarProyectoCompleto" + "&proy=" + $("#idProy").val(),
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    sareas = this.areas.length;
                    proy = this.proyecto_id;
                    mul = this.proyecto_multi;
                    proynombre = this.proyecto_nombre;
                    var perfil, integrantes = '', su = 0, tint = this.integrantes.length;
                    if (this.proyecto_doc == null) {
                        perfil = "No tiene perfil";
                    } else if (this.proyecto_proceso == null) {
                        perfil = '<a href="https://planificacion.espoch.edu.ec/sip/formulacion/docs/' + this.proyecto_doc + '" download="' + this.proyecto_doc + '">' + this.proyecto_doc + '</a>';
                    } else {
                        perfil = '<a href="https://planificacion.espoch.edu.ec/sip/formulacion/docs/' + this.proyecto_proceso + '" download="' + this.proyecto_proceso + '">' + this.proyecto_proceso + '</a>';
                    }
                    if ((this.proyecto_integrantes == null || this.proyecto_integrantes === 'null') && this.integrantes.length > 0) {
                        $.each(this.integrantes, function (indice, inte) {
                            if (su === 0) {
                                $('#inpintegrantes').append('<div class="row container cross-center" id="acminte"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="textIntegrantes" name="textIntegrantes[]">' + inte.proyecto_integrantes + '</textarea></div><i class="fas fa-plus" id="iconoplusint"></i></div>');
                            } else {
                                $('#inpintegrantes').append('<div class="row container cross-center" id="acminte"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="textIntegrantes" name="textIntegrantes[]">' + inte.proyecto_integrantes + '</textarea></div><i class="fas fa-minus" id="iconoremoveinte"></i></div>');
                            }
                            su++;
                            if (su === tint) {
                                integrantes += ' - ' + inte.proyecto_integrantes;
                            } else {
                                integrantes += ' - ' + inte.proyecto_integrantes + ', ';
                            }
                        });
                    } else if ((this.proyecto_integrantes == null || this.proyecto_integrantes == 'null') && this.integrantes.length == 0) {
                        integrantes = "No tiene integrantes";
                    } else {
                        integrantes = this.proyecto_integrantes;
                    }
                    persp = this.perspectiva_id;
                    $('#tituloAg').html(this.ag.ag_nombre);
                    $('#objEstartF').html(this.per.perspectiva_nombre);
                    $('#objest option[value="' + this.per.perspectiva_id + '"]').prop('selected', 'true');
                    $('#objest').selectpicker('refresh');
                    listarObjetivosOperativos(this.per.perspectiva_id, this.per.objetivo_id);
                    listarActividadesPresupuestarias(this.per.objetivo_id, this.per.ap_id);
                    $('#objObjF').html(this.per.objetivo_nombre);
                    $('#actividadPresF').html(this.per.ap_nombre);
                    $('#act-mod').val(this.per.ap_nombre);
                    $('#proyectoNombreF').html(this.proyecto_nombre);
                    $('#nombre-mod').val(this.proyecto_nombre);
                    $('#finProyectoF').html(this.proyecto_fin);
                    $('#finp-mod').val(this.proyecto_fin);
                    $('#propositoF').html(this.proyecto_proposito);
                    $('#prop-mod').val(this.proyecto_proposito);
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
                    $('#fini-mod').val(this.proyecto_fi);
                    $('#ffin-mod').val(this.proyecto_ff);
                    $('#responsableF').html(this.proyecto_responsable);
                    $('#res-mod').val(this.proyecto_responsable);
                    $('#integrantesF').html(integrantes);
                    $('#perfilF').append(perfil + ' <a class="d-none" id="btn-cross-perfil" style="color:red" title="Eliminar" href="#"><i class="fas fa-times"></i></a>');
                    $('#inpmodificarPerfil').val(this.proyecto_doc);
                    $('#montoF').html(new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto));
                    $('#mon-mod').html(new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto));
                    m = this.proyecto_monto;
                    prio = this.proyecto_plurianual;
                    if (this.proyecto_plurianual === 1) {
                        $('#pluriAnualV').removeClass('d-none');
                        $('#montoPluri').removeClass('d-none');
                        $('#montoPluri').addClass('d-block');
                        $('#plurianual').html('SI');
                        if (this.monto_proy.length > 0) {
                            $.each(this.monto_proy, function (indice, montos) {
                                $('#div' + montos.mp_anio).html('<label style="font-weight: bold">' + montos.mp_anio + ':</label>' + new Intl.NumberFormat("US", formateador()).format(montos.mp_monto));
                                $('#txt' + montos.mp_anio).val(montos.mp_monto);
                            });
                        }
                    }
                    estadof = this.estado_id;
                    if (this.estado_id === 0) {
                        $('#estadoF').html('Planificando');
                    } else {
                        $('#estadoF').html(this.estado_nombre);
                    }

                    $.ajax({
                        url: "../proyecto?accion=ListaEstadoAsignacion",
                        type: 'POST',
                        data: {proyecto: proy},
                        dataType: 'json',
                        async: false
                    })
                            .done(function (response) {
                                $.each(response, function () {
                                    usuarioen = this.proyecto_ag;
                                });
                            })
                            .fail(function () {
                                console.log('No existe conexión con la base de datos.');
                            })
                            .always(function () {
                                console.log('Se completo correctamente');
                            });
                    if ((tusu === "15" || tusu === "3") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                    } else if ((tusu === "2" || tusu === "5" || tusu === "7" || tusu === "8" || tusu === "19" || tusu === "18") && (estadof === 0 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                        $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                    } else if ((tusu === "15" || tusu === "3" || tusu === "2") && (estadof === 0 || estadof === 2 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
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
                    if (this.per.perspectiva_id === 2 || this.per.perspectiva_id === 3) {
                        $('#int-mod').removeProp("readonly");
                    } else {
                        $('#int-mod').prop('readonly', 'true');
                    }
                    if (this.per.perspectiva_id === 1) {
                        $('#btn_proyecto_pluri').removeClass('d-none');
                    } else {
                        $('#btn_proyecto_pluri').addClass('d-none');
                    }
                    if (!this.proyecto_multi) {
                        $('#multiCarreras').html('NO');
                        $('#multiCarrerasA').html('NO');
                    } else {
                        $('#ismulti').val(true);
                        $.each(this.areas, function (indice, area) {
                            $('#multiCarreras').append('<div>- ' + area.ag_nombre + '</div>');
                            $('#multiCarrerasA').append('<div>- ' + area.ag_nombre + '</div>');
                            $('#mul-mod option[value="' + area.ag_id + '"]').prop('selected', 'true');
                        });
                        $('#mul-mod').selectpicker('refresh');
                    }
                    if (this.proceso.length > 0) {
                        var co = 0, co2 = 0;
                        $.each(this.proceso, function (indice, accion) {
                            if (accion.proceso_codigo === "AI2016" || accion.proceso_codigo === "EE2019" || accion.proceso_codigo === "PACESPOCH" || accion.proceso_codigo === "PACSMS" || accion.proceso_codigo === "PACSO" || accion.proceso_codigo === "PMI20" || accion.proceso_codigo === "PMIFC") {
                                let valPI = $('#selectProceso option:selected').text();
                                $('#proceEvalInstitucional').text(valPI);
                                $('#modAccionInstitucional').append('<div>- ' + accion.am_nombre + '</div>');
                                if (co === 0) {
                                    $('#modAccionInstitucionalMod').append('<div class="row container main-center cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea class="evaluacion"  name="accmejins[]" rows="3">' + accion.am_nombre + '</textarea><i class="fas fa-plus" id="iconoplusins"></i> </div></div>');
                                } else {
                                    $('#modAccionInstitucionalMod').append('<div class="row container main-center cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea class="evaluacion"  name="accmejins[]" rows="3">' + accion.am_nombre + '</textarea><i class="fas fa-minus" id="iconoremoveins"></i> </div></div>');
                                }
                                co++;
                            } else {
                                let valPI = $('#selectProcesoCar option:selected').text();
                                $('#proceEvalCarrera').text(valPI);
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
                    if (tusu === "15" && estadof === 3 && $('#idAgObEs').val() === this.ag.ag_id.toString()) {
                        $('#btn-modificar').css({"display": "flex"});
                        $('#btn_proyecto_eliminar').css({"display": "flex"});
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                        $('#btn_proyecto_articulacion').css({"display": "flex"});
                    } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19" || tusu === "18") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgObEs').val() === this.ag.ag_id.toString()) {
                        $('#btn-modificar').css({"display": "flex"});
                        $('#btn_proyecto_eliminar').css({"display": "flex"});
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                        $('#btn_proyecto_articulacion').css({"display": "flex"});
                    } else if ((tusu === "2" || tusu === "5" || tusu === "7" || tusu === "8") && estadof === 1 && $('#idAgObEs').val() === this.ag.ag_id.toString()) {
                        $('#btn_proyecto_enviar').removeClass('d-none');
                        $('#btn_proyecto_articulacion').removeClass('d-none');
                        $('#btn-modificar').addClass('d-none');
                        $('#btn_proyecto_eliminar').addClass('d-none');
                    } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgObEs').val() === this.ag.ag_id.toString()) {
                        $('#btn-modificar').removeClass('d-none');
                        $('#btn_proyecto_eliminar').removeClass('d-none');
                        $('#btn_proyecto_enviar').removeClass('d-none');
                        $('#btn_proyecto_articulacion').removeClass('d-none');
                    } else if ((tusu === "3" || tusu === "5" || tusu === "15") && (estadof === 0 || estadof === 2 || estadof === 7 || estadof === 8 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgObEs').val() !== this.ag.ag_id.toString() && usuarioen !== $('#idAgObEs').val()) {
                        $('#btn_proyecto_enviar').removeClass('d-none');
                        $('#btn_proyecto_articulacion').removeClass('d-none');
                        $('#btn-modificar').addClass('d-none');
                        $('#btn_proyecto_eliminar').addClass('d-none');
                    } else if ((tusu === "3" || tusu === "5" || tusu === "15") && (estadof === 0 || estadof === 7 || estadof === 8 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgObEs').val() !== this.ag.ag_id.toString() && usuarioen === $('#idAgObEs').val()) {
                        $('#btn_proyecto_enviar').removeClass('d-none');
                        $('#btn_proyecto_articulacion').removeClass('d-none');
                        $('#btn-modificar').addClass('d-none');
                        $('#btn_proyecto_eliminar').addClass('d-none');
                    } else if (tusu === "11" && estadof === 2) {
                        $('#btn_proyecto_enviar').removeClass('d-none');
                        $('#btn_proyecto_articulacion').addClass('d-none');
                        $('#btn-modificar').addClass('d-none');
                        $('#btn_proyecto_eliminar').addClass('d-none');
                    } else if (tusu === "26" && estadof === 15) {
                        $('#btn_proyecto_enviar').removeClass('d-none');
                        $('#btn_proyecto_articulacion').css({"display": "flex"});
                        $('#btn-modificar').addClass('d-none');
                        $('#btn_proyecto_eliminar').addClass('d-none');
                    } else if (tusu === "1" && estadof === 52) {
                        $('#btn_proyecto_enviar').removeClass('d-none');
                        $('#btn_proyecto_articulacion').removeClass('d-none');
                        $('#btn-modificar').addClass('d-none');
                        $('#btn_proyecto_eliminar').addClass('d-none');
                    } else {
                        $('#btn_proyecto_enviar').addClass('d-none');
                        $('#btn_proyecto_articulacion').addClass('d-none');
                        $('#btn-modificar').addClass('d-none');
                        $('#btn_proyecto_eliminar').addClass('d-none');
                    }

                    if (this.proyecto_anio === 2020) {
                        $('#btn_proyecto_planificado').removeClass('d-none');
                    } else {
                        $('#btn_proyecto_planificado').addClass('d-none');
                    }

                    $.ajax({
                        url: "../proyecto?accion=ListaEstadoAsignacion",
                        type: 'POST',
                        data: {proyecto: proy},
                        dataType: 'json',
                        async: false
                    })
                            .done(function (response) {
                                $.each(response, function () {
                                    usuarioen = this.proyecto_ag;
                                });
                            })
                            .fail(function () {
                                console.log('No existe conexión con la base de datos.');
                            })
                            .always(function () {
                                console.log('Se completo correctamente');
                            });
                    let es;
                    $.ajax({
                        url: "../proyecto?accion=VerificacionEnvios",
                        type: 'POST',
                        data: {proyecto: proy},
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
                    if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19" || tusu === "3") && tiempo < 0 && es && usuarioen === $('#idAgObEs').val()) {
                        $('#btn_proyecto_enviar').addClass('d-none');
                    } else {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    }
                    //Cuerpo del proyecto
                    $('#cuerpoMenu').empty();
                    $('#cuerpoDetalle').empty();
                    var tp;
                    $.each(this.areas, function (indice, area) {
                        if (i === 1) {
                            tp = 'active';
                        } else {
                            tp = 'fade';
                        }
                        $('#cuerpoMenu').append('<li class="nav-item" id="pestania' + i + '"><a class="nav-link" data-toggle="tab" href="#componente' + i + '">Componente ' + area.ag_alias + '</a></li>');
                        $('#cuerpoDetalle').append('<div id="componente' + i + '" class="container-fluid tab-pane ' + tp + ' pestania"><br>\n\
                                <div class="main-center" style="font-weight: bold;">' + area.ag_nombre + '</div>\n\
                                    <div class="row mt-3 align-items-center">\n\
                                        <input type="hidden" name="idAgComp" id="idAgComp' + i + '" value="' + area.ag_id + '">\n\
                                        <input type="hidden" name="idComponente' + i + '" id="idComponente' + i + '">\n\
                                        <div class="col-11" id="inputComp' + i + '"><input class="form-control" type="text" id="componenteF' + i + '" name="componenteF' + i + '" placeholder="Ingresar componente"></div>\n\
                                        <div class="col-1" id="btn_comp' + i + '"><i class="fas fa-save" style="cursor: pointer" id="ingresarComponente" data-id="' + i + '"></i></div>\n\
                                    </div>\n\
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta' + i + '">\n\
                                        <div class="col-11" id="inputMeta' + i + '">\n\
                                            <input class="form-control" type="text" id="metaF' + i + '" name="metaF' + i + '" placeholder="Ingresar meta">\n\
                                            <input type="hidden" name="idMeta" id="idMeta' + i + '">\n\
                                        </div>\n\
                                        <div class="col-1" id="btn_meta' + i + '"><i class="fas fa-save" style="cursor: pointer" id="ingresarMeta" data-id="' + i + '"></i></div>\n\
                                    </div>\n\
                                    <div class="row mt-3 align-items-center" id="lisIndicador' + i + '"></div>\n\
                                    <div class="col-12 mt-3 main-end">\n\
                                        <button class="btn bton d-none btn_indicador_detalle" id="detalleIndicador' + i + '" data-id="' + i + '">DETALLES INDICADORES</button>\n\
                                        <button class="btn bton d-none btn_proyecto_agregar_indicador" id="botonIndicador' + i + '" data-id="' + i + '">+ INDICADOR</button>\n\
                                        <button class="btn bton d-none btn_proyecto_actividad" id="botonActividad' + i + '" data-id="' + i + '">+ ACTIVIDAD</button>\n\
                                    </div>\n\
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none contenedorIndicador"  method="POST" id="contenedorIndicador' + i + '" data-id="' + i + '" novalidate accept-charset="UTF-8">\n\
                                        <p class="titulo2">INDICADOR</p>\n\
                                        <input type="hidden" name="idIndicador' + i + '" id="idIndicador' + i + '">\n\
                                        <div class="form-row">\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>\n\
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreIndicador' + i + '" name="txtnombreIndicador' + i + '" required minlength="1"></textarea>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>\n\
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescipcionIndicador' + i + '" name="txtdescipcionIndicador' + i + '" required minlength="1"></textarea>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Tipo:</label>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="tipoIndicador' + i + '" name="tipoIndicador' + i + '" value="Cualitativo" checked>\n\
                                                        <label class="form-check-label"  style="font-weight: normal;">Cualitativo</label>\n\
                                                    </div>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="tipoIndicador' + i + '" name="tipoIndicador' + i + '" value="Cuantitativo">\n\
                                                        <label class="form-check-label" style="font-weight: normal;">Cuantitativo</label>\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="formulaIndicador' + i + '">\n\
                                                <div class="row main-center">\n\
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Fórmula:</label>\n\
                                                    <div class="col-12 col-xs-12 col-md-10 row">\n\
                                                        <div class="col-12 col-xs-12 col-md-8">\n\
                                                            <input type="text" class="col-5" placeholder="Meta ejecutada" name="txtindicadorejecutadovalor' + i + '" id="txtindicadorejecutadovalor' + i + '">=<input type="text" class="col-3" placeholder="% ejecutado" readonly>\n\
                                                        </div> \n\
                                                        <div class="col-12 col-xs-12 col-md-8">\n\
                                                            <input type="text" class="col-5" placeholder="Meta planificada" name="txtindicadorplanificadovalor' + i + '" id="txtindicadorplanificadovalor' + i + '">=<input type="text" class="col-3" placeholder="% planificado" name="txtindicadorplanificado' + i + '" id="txtindicadorplanificado' + i + '">\n\
                                                        </div> \n\
                                                        <div class="form-check col-12 col-xs-6 col-md-1">\n\
                                                            <input class="form-check-input" type="radio" name="valorIndicador' + i + '" id="valorIndicador' + i + '" value="1" checked>\n\
                                                            <label class="form-check-label"  style="font-weight: normal;">%</label>\n\
                                                        </div> \n\
                                                        <div class="form-check col-12 col-xs-6 col-md-1">\n\
                                                            <input class="form-check-input" type="radio" name="valorIndicador' + i + '" id="valorIndicador' + i + '" value="2" checked>\n\
                                                            <label class="form-check-label"  style="font-weight: normal;">#</label>\n\
                                                        </div> \n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                        </div>\n\
                                        <button class="btn bton" type="submit" id="botonindicadorg' + i + '">GUARDAR</button>\n\
                                        <button class="btn bton">CANCELAR</button>\n\
                                    </form>\n\
                                    <!--Lista indicadores-->\n\
                                    <table class="container-fluid table mt-5 table-bordered table-hover table-striped table-responsive-md table-responsive-sm tablas d-none" id="listaIndicadorTabla' + i + '">\n\
                                        <thead class="table-azul">\n\
                                            <tr>\n\
                                                <th colspan="4" class="p-0">INDICADORES</th>\n\
                                            </tr>\n\
                                            <tr>\n\
                                                <th class="p-0">NOMBRE</th>\n\
                                                <th class="p-0">DESCRIPCIÓN</th>\n\
                                                <th class="p-0">TIPO</th>\n\
                                                <th class="p-0">FORMULA</th>\n\
                                            </tr>\n\
                                        </thead>\n\
                                        <tbody id="listaIndicadores' + i + '"></tbody>\n\
                                    </table>\n\
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorActividad d-none"  method="POST" action="../actividadReq?accion=IngresarActividad"  id="contenedorActividad' + i + '" data-id="' + i + '" novalidate accept-charset="UTF-8">\n\
                                        <p class="titulo2">ACTIVIDAD</p>\n\
                                        <input type="hidden" name="idActividadMod' + i + '" id="idActividadMod' + i + '">\n\
                                        <div class="form-row">\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>\n\
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreActividad' + i + '" name="txtnombreActividad' + i + '" required minlength="1"></textarea>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Responsable:</label>\n\
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpresponsable' + i + '" name="inpresponsable' + i + '">\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 mesactividad" data-id="' + i + '">\n\
                                                <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Programación:</label>\n\
                                                <div class="row col-10 text-justify" id="programacionactividad' + i + '"></div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">\n\
                                                <div class="row">\n\
                                                    <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Porcentajes:</label>\n\
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="pori' + i + '">\n\
                                                        <div class="col-10">\n\
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajei' + i + '" required>\n\
                                                        </div>\n\
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">I</label>\n\
                                                    </div>\n\
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="porii' + i + '">\n\
                                                        <div class="col-10">\n\
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeii' + i + '" required>\n\
                                                        </div>\n\
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">II</label>\n\
                                                    </div>\n\
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="poriii' + i + '">\n\
                                                        <div class="col-10">\n\
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeiii' + i + '" required>\n\
                                                        </div>\n\
                                                        <label class="col-2 col-form-label p-0 align-self-center text-center">III</label>\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita financiamiento:</label>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento' + i + '" name="tipofinanciamiento' + i + '" value="1" checked>\n\
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>\n\
                                                    </div>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento' + i + '" name="tipofinanciamiento' + i + '" value="0">\n\
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                        </div>\n\
                                        <button class="btn bton" type="submit" id="actividadBoton' + i + '">GUARDAR</button>\n\
                                        <button class="btn bton">CANCELAR</button>\n\
                                    </form>\n\
                                    <!--Lista Actividades-->\n\
                                    <div class="tablaover">\n\
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades' + i + '">\n\
                                            <div class="table-azul encabezado p-0">\n\
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>\n\
                                                <div class="p-0 estilo encabezado_7">NOMBRE</div>\n\
                                                <div class="p-0 estilo encabezado_7">RESPONSABLE</div>\n\
                                                <div class="p-0 encabezado_2">\n\
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>\n\
                                                    <div class="estilo encabezado_6">I</div>\n\
                                                    <div class="estilo encabezado_6">II</div>\n\
                                                    <div class="estilo encabezado_6">III</div>\n\
                                                </div>\n\
                                                <div class="estilo encabezado_2">MONTO</div>\n\
                                                <div class="estilo encabezado_4">ACCIÓN</div>\n\
                                            </div>\n\
                                            <div id="listaActividades' + i + '" class="align-self-center encabezado p-0"></div>\n\
                                        </div>\n\
                                    </div>\n\
                                    <!--Ingresar requerimientos-->\n\
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=IngresarRequerimiento"  id="contenedorReqPac' + i + '" data-id="' + i + '" novalidate accept-charset="UTF-8">\n\
                                        <p class="titulo2">REQUERIMIENTO</p>\n\
                                        <div class="form-row">\n\
                                             <div class="row col-12 justify-content-center">\n\
                                                <div class="col-11 mb-3 row p-1" style="border:1px solid #000000; background: #EBF5FB">\n\
                                                    <div class="col-1 align-self-center"><i class="fas fa-info-circle fa-2x" style="color: #EC7063"></i></div>\n\
                                                    <div class="col-11 text-justify">Conforme lo dispuesto en el Acuerdo No. 0075 del Ministerio de Economía y Finanzas, en el artículo 1, literal a) (…) “La entidad que inicie nuevos procesos de contratación deberá certificar únicamente por el valor de la base imponible que implique el bien o servicio a contratar y procederá al registro contable del IVA.”, en esta razón, la planificación operativa anual no se debe considerar el IVA en los requerimientos, sino únicamente el monto neto del bien o servicio.</div>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <input type="hidden" name="idactividadreq' + i + '" id="idactividadreq' + i + '">\n\
                                                    <input type="hidden" name="idrequerimiento' + i + '" id="idrequerimiento' + i + '">\n\
                                                    <input type="hidden" name="reqverificacion' + i + '" id="reqverificacion' + i + '">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita Contratación Pública?</label>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="tipoReq' + i + '" name="tipoReq' + i + '" value="1" checked>\n\
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>\n\
                                                    </div>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="tipoReq' + i + '" name="tipoReq' + i + '" value="2">\n\
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3" id="reqgeneral' + i + '">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Requerimientos precargados: </label>\n\
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 reqgeneral" data-live-search="true" id="selectReqeG' + i + '" name="selectReqeG' + i + '" data-id="' + i + '"><option>vddvd</option></select>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>\n\
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreReq' + i + '" name="txtnombreReq' + i + '" required minlength="1"></textarea>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>\n\
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescripcionReq' + i + '" name="txtdescripcionReq' + i + '" required minlength="1"></textarea>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Cantidad Anual:</label>\n\
                                                    <input type="number" id="intcantidad' + i + '" name="intcantidad' + i + '" class="col-12 col-xs-12 col-md-8">\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Costo Unitario:</label>\n\
                                                    <input type="number" class="col-12 col-xs-12 col-md-8" id="intcosto' + i + '" name="intcosto' + i + '">\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento' + i + '" data-id="' + i + '"></div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Graba IVA?(Dato informativo no de cálculo)</label>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="radioiva' + i + '" name="radioiva' + i + '" value="SI" checked>\n\
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>\n\
                                                    </div>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="radioiva1' + i + '" name="radioiva' + i + '" value="NO">\n\
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento: </label>\n\
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" selectfinan id="selfinan' + i + '" name="selfinan' + i + '">\n\
                                                    </select>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="unidadM' + i + '">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Unidad de medida: </label>\n\
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 selectunidad" data-live-search="true" id="selunidad' + i + '" name="selunidad' + i + '">\n\
                                                    </select>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="tipoC' + i + '">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de compra: </label>\n\
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 selectipoc" id="seltipoc' + i + '" name="seltipoc' + i + '">\n\
                                                    </select>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="cpc' + i + '">\n\
                                                <div class="row main-center">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">CPC:</label>\n\
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpcpc' + i + '" name="inpcpc' + i + '">\n\
                                                </div>\n\
                                            </div>\n\
                                        </div>\n\
                                        <button class="btn bton" type="submit" id="botonrequerimientog' + i + '">GUARDAR</button>\n\
                                        <button class="btn bton">CANCELAR</button>\n\
                                    </form>\n\
                                    <!--Modificar año-->\n\
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=ModificarAnioReq"  id="contenedorReqMod' + i + '" data-id="' + i + '" novalidate accept-charset="UTF-8">\n\
                                        <p class="titulo2">REQUERIMIENTO</p>\n\
                                        <p id="nombrereqTit1"></p>\n\
                                        <div class="form-row">\n\
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">\n\
                                                <div class="row main-center">\n\
                                                    <input type="hidden" name="idrequerimientoAn' + i + '" id="idrequerimientoAn' + i + '">\n\
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año de planificación:</label>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="AnioReq' + i + '" name="AnioReq' + i + '" value="2021" checked>\n\
                                                        <label class="form-check-label"  style="font-weight: normal;">2021</label>\n\
                                                    </div>\n\
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="' + i + '">\n\
                                                        <input class="form-check-input" type="radio" id="AnioReq' + i + '" name="AnioReq' + i + '" value="2022">\n\
                                                        <label class="form-check-label" style="font-weight: normal;">2022</label>\n\
                                                    </div>\n\
                                                </div>\n\
                                            </div>\n\
                                        </div>\n\
                                        <button class="btn bton" type="submit" id="botonrequerimientoAnio' + i + '">GUARDAR</button>\n\
                                    </form>\n\
                                    <!--Lista requerimientos-->\n\
                                    <div class="tablaover mb-4">\n\
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv" id="listadoRequerimiento' + i + '">\n\
                                            <div class="table-azul encabezado p-0">\n\
                                                <div class="encabezado_completo estilo">REQUERIMIENTOS</div>\n\
                                                <div class="estilo encabezado_2">ACTIVIDADES</div>\n\
                                                <div class="p-0 encabezado_3">\n\
                                                    <div class="p-0 estilo encabezado_2">NOMBRE</div>\n\
                                                    <div class="p-0 encabezado_2">\n\
                                                        <div class="estilo encabezado_completo">CUATRIMESTRE</div>\n\
                                                        <div class="estilo encabezado_6">I</div>\n\
                                                        <div class="estilo encabezado_6">II</div>\n\
                                                        <div class="estilo encabezado_6">III</div>\n\
                                                    </div>\n\
                                                    <div class="estilo encabezado_4">CANTIDAD</div>\n\
                                                    <div class="estilo encabezado_4">COSTO SIN IVA</div>\n\
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>\n\
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>\n\
                                                    <div class="estilo encabezado_4">ACCIÓN</div>\n\
                                                </div>\n\
                                            </div>\n\
                                            <div id="listaRequerimiento' + i + '" class="align-self-center encabezado p-0"></div>\n\
                                        </div>\n\
                                    </div>\n\
                                </div>');
                        i++;
                    });
                    listarComponente(proy, this.areas.length);
                    listaProcesosAr();
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});
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

function myFunction() {
    var options = document.getElementById("selectAcciones");
    var limite = 130;

    for (var i = 0; i < options.length; i++) {
        options[i].setAttribute('title', options[i].innerText); // para poder ver el texto completo en el hover del elemento antes de cortarlo
        options[i].innerText = options[i].innerText.slice(0, limite) + '...';
    }
}

$('.formulario').on('change', '.form-row .col-12 .row #selectProceso', function () {
    var datos = $(this).val();
    $.ajax({
        url: "../proyecto?accion=ListaActividadesProceso",
        type: 'POST',
        data: {proceso: datos},
        dataType: 'json'
    })
            .done(function (response) {
                $('#selectAcciones').empty();
                $.each(response, function () {
                    $('#selectAcciones').append('<option value="' + this.am_id + '">' + this.am_codigo + '. ' + this.am_nombre + '</option>');
                });
                myFunction();
                $('#selectAcciones').selectpicker('refresh');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

//Listar actividades presupuestarias
function actividadPres(tipo, i) {
    $('#selcActPres' + i).empty();
    $.ajax({
        url: "../proyecto?accion=ActividadPresupuestaria",
        type: 'POST',
        data: {tipo: tipo},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    $('#selcActPres' + i).append('<option value="' + this.estado_id + '">' + this.estado_nombre + '</option>');
                });
                $('#selcActPres' + i).selectpicker('refresh');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#btn_proyecto_articulacion').on('click', function () {
    $('.formulario#formArticulacion').removeClass('d-none');
    window.location.href = '#formArticulacion';
});

$('#btncancelarArticu').on('click', function (event) {
    event.preventDefault();
    $('.formulario#formArticulacion').addClass('d-none');
});

//Listar articulacion
function listaProcesosAr() {
    $('#listaAccionesM').empty();
    $.ajax({
        url: "../proyecto?accion=ListarActividadProceso",
        data: {proy: $('#idProy').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    var div;
                    $.each(response, function () {
                        if ((tusu === "15" || tusu === "3") && estadof === 3) {
                            div = '<i class="fas fa-trash" id="btn_eliminar_articulacion" data-actividad="' + this.am_id + '" data-actividadnombre="' + this.am_nombre + '" title="Eliminar articulación"></i>';
                        } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "3" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                            div = '<i class="fas fa-trash" id="btn_eliminar_articulacion" data-actividad="' + this.am_id + '" data-actividadnombre="' + this.am_nombre + '" title="Eliminar articulación"></i>';
                        } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                            div = '<i class="fas fa-trash" id="btn_eliminar_articulacion" data-actividad="' + this.am_id + '" data-actividadnombre="' + this.am_nombre + '" title="Eliminar articulación"></i>';
                        } else if (tusu === "26" && (estadof === 15 || estadof === 6 || estadof === 10 || estadof === 11)) {
                            if (this.am_validar) {
                                div = '<input type="checkbox" id="checkvalidar'+this.proceso_tipo+'" checked name="checkvalidar" onchange="validarArt(' + this.proceso_tipo + ')">';
                            } else {
                                div = '<input type="checkbox" id="checkvalidar'+this.proceso_tipo+'" name="checkvalidar" onchange="validarArt(' + this.proceso_tipo + ')">';
                            }
                        } else {
                            if (this.am_validar) {
                                div = '<i class="fas fa-check"></i>';
                            } else {
                                div = '<i class="fas fa-times"></i>';
                            }
                        }
                        $('#listaAccionesM').append('<div class="p-0 estilobody encabezado_4 centro">' + this.proceso_codigo + '</div><div class="p-0 estilobody encabezado_4 text-justify">' + this.proceso_nombre + '</div><div class="p-0 estilobody encabezado_4 centro">' + this.am_codigo + '</div><div class="estilobody encabezado_7 text-justify">' + this.am_meta + '</div><div class="estilobody encabezado_7 text-justify">' + this.am_nombre + '</div><div class="estilobody text-center encabezado_5 centro">' + this.am_responsable + '</div><div class="estilobody centro encabezado_8">' + div + '</div>');
                    });
                }else{
                    $('#listaAccionesM').append('<div class="p-0 estilobody encabezado_completo centro">SIN ARTICULACIÓN</div>');
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function validarArt(id) {
    $.ajax({
        url: "../proyecto?accion=ValidarArticulacion",
        type: 'POST',
        data: {proyecto: $("#idProy").val(), id: id, validar: $('#checkvalidar'+id).is(':checked')},
        dataType: 'json',
        cache: false
    })
            .done(function (response) {
                if (response === "Correcto") {
                    alert("Ingresado correctamente");
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
    } else {
        $('#agregaC').addClass('d-none');
    }
});
//Ingresar componente
$('.pestania').on('click', '.row .col-1 #ingresarComponente', function () {
    var data = $(this).data();
    $.ajax({
        url: "../componenteMeta?accion=IngresarComponente",
        type: 'POST',
        data: {idProyCompleto: $("#idProy").val(), txtnombreComp: $('#componenteF' + data['id']).val(), idAgComp: $('#idAgComp' + data['id']).val(), cedulaProyecto: $('#cedulaProyecto').val()},
        dataType: 'json',
        cache: false
    })
            .done(function (response) {
                if (response.result === "Correcto") {
                    window.location.reload();
                } else {
                    alert(response.result);
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

//Ingresar articulación
$('.col-10').on('click', '#btnGuardarArti', function (event) {
    event.preventDefault();
    $.ajax({
        url: "../proyecto?accion=AgregarArticulacion",
        type: 'POST',
        data: $("#formArticulacion").serialize(),
        dataType: 'json',
        cache: false
    })
            .done(function (response) {
                if (response === "Correcto") {
                    listaProcesosAr();
                } else {
                    alert(response);
                    listaProcesosAr();
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

//Ver descripcion requerimientos
$(".encabezado").on('click', '.encabezado_8 #btn_eliminar_articulacion', function () {
    var data = $(this).data();
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar la articulación con la actividad <b>"' + data['actividadnombre'] + '"</b>?<input type="hidden" name="idactividadart" id="idactividadart" value="' + data['actividad'] + '"><input type="hidden" name="tipom" id="tipom" value="articulacion">');
    $('#eliminarModal').modal();
});

//Listar componente
function listarComponente(proy, num) {
    $.ajax({
        url: "../componenteMeta?accion=ListarComponentes" + "&proy=" + proy,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    if (response.length === num) {
                        var i = 1;
                        $.each(response, function () {
                            if ($('#idAgComp' + i).val() === $('#idAgObEs').val()) {
                                $("#idComponente" + i).val(this.componente_id);
                                $("#componente" + i).children(".row").children("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                                if (tusu === "15" && estadof === 3 && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                    $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                    $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                    $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                    $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                } else {
                                    $('#agregaC').addClass('d-none');
                                    $("#btn_comp" + i).empty();
                                }
                            } else {
                                $("#idComponente" + i).val(this.componente_id);
                                $("#componente" + i).children(".row").children("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                                $("#btn_comp" + i).empty();
                            }
                            listaMeta(this.componente_id, i);
                            long = response.length;
                            listaActividad(this.componente_id, i, long);
                            listaRequerimiento(this.componente_id, i);
                            i++;
                        });
                    } else {
                        for (var i = 1; i <= num; i++) {
                            if (i === 1) {
                                $.each(response, function () {
                                    if ($('#idAgComp' + i).val() === $('#idAgObEs').val()) {
                                        $("#idComponente" + i).val(this.componente_id);
                                        $("#componente" + i).children(".row").children("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                                        if (tusu === "15" && estadof === 3 && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                            $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                        } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                            $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                        } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                            $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                        } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                                            $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '"></i>');
                                        } else {
                                            $('#agregaC').addClass('d-none');
                                            $("#btn_comp" + i).empty();
                                        }
                                    } else {
                                        $("#idComponente" + i).val(this.componente_id);
                                        $("#componente" + i).children(".row").children("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                                        $("#btn_comp" + i).empty();
                                    }
                                    listaMeta(this.componente_id, i);
                                    long = response.length;
                                    listaActividad(this.componente_id, i, long);
                                    listaRequerimiento(this.componente_id, i);
                                });
                            } else {
                                if ($('#idAgComp' + i).val() !== $('#idAgObEs').val()) {
                                    $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div></div>');
                                    $("#btn_comp" + i).empty();
                                }
                            }
                        }
                    }
                } else {
                    for (var i = 1; i <= num; i++) {
                        if (tusu === "18") {
                            $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div></div>');
                            $("#btn_comp" + i).empty();
                        } else if ($('#idAgComp' + i).val() === $('#idAgObEs').val()) {
                            $('#agregaC').addClass('d-none');
                            $('#btn_proyecto_enviar').addClass('d-none');
                            $('#botonIndicador' + i).addClass('d-none');
                            $('#botonActividad' + i).addClass('d-none');
                            $('#detalleIndicador' + i).addClass('d-none');
                        } else {
                            $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div></div>');
                            $("#btn_comp" + i).empty();
                        }
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
                } else if (response.length === 4 && ($('#tipoAg').val() === "4" || $('#idAgObEs').val() === "2" || $('#idAgObEs').val() === "8" || $('#idAgObEs').val() === "14" || $('#idAgObEs').val() === "17" || $('#idAgObEs').val() === "22" || $('#idAgObEs').val() === "27" || $('#idAgObEs').val() === "31" || $('#idAgObEs').val() === "36" || $('#idAgObEs').val() === "37")) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $("#pestania4").removeClass('d-none');
                    $('#agregaC').remove();
                } else if (response.length === 1 && (tusu === "15" && estadof === 3)) {
                    $('#agregaC').removeClass('d-none');
                } else if (response.length === 1 && ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26))) {
                    $('#agregaC').removeClass('d-none');
                } else if (response.length === 1 && (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26))) {
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
function listaMeta(comp, i) {
    $.ajax({
        url: "../componenteMeta?accion=ListarMetaRep" + "&comp=" + comp,
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
                        if (tusu === "15" && estadof === 3 && usuarioen !== $('#idAgObEs').val()) {
                            $("#btn_meta" + i).html('<i class="fas fa-edit" id="btn_proyecto_meta_modificar" data-id="' + i + '" data-meta="' + this.meta_nombre + '" data-idmeta="' + this.meta_id + '" data-comp="' + comp + '" title="Editar meta"></i>');
                        } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                            $("#btn_meta" + i).html('<i class="fas fa-edit" id="btn_proyecto_meta_modificar" data-id="' + i + '" data-meta="' + this.meta_nombre + '" data-idmeta="' + this.meta_id + '" data-comp="' + comp + '" title="Editar meta"></i>');
                        } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                            $("#btn_meta" + i).html('<i class="fas fa-edit" id="btn_proyecto_meta_modificar" data-id="' + i + '" data-meta="' + this.meta_nombre + '" data-idmeta="' + this.meta_id + '" data-comp="' + comp + '" title="Editar meta"></i>');
                        } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                            $("#btn_meta" + i).html('<i class="fas fa-edit" id="btn_proyecto_meta_modificar" data-id="' + i + '" data-meta="' + this.meta_nombre + '" data-idmeta="' + this.meta_id + '" data-comp="' + comp + '" title="Editar meta"></i>');
                        } else {
                            $("#btn_meta" + i).empty();
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
                    if (tusu === "15" && estadof === 3 && usuarioen !== $('#idAgObEs').val()) {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#botonIndicador' + i).removeClass('d-none');
                        $('#botonActividad' + i).removeClass('d-none');
                    } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#botonIndicador' + i).removeClass('d-none');
                        $('#botonActividad' + i).removeClass('d-none');
                    } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                        $('#contenedorMeta' + i).removeClass('d-none');
                        $('#botonIndicador' + i).removeClass('d-none');
                        $('#botonActividad' + i).removeClass('d-none');
                    } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
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
                        if (tusu === "15" && estadof === 3 && usuarioen !== $('#idAgObEs').val()) {
                            $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row" id="rowindicador"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"><i class="fas fa-edit" title="Editar Indicador" id="modIndicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i><i class="fas fa-trash-alt" id="elimIndicador" title="Eliminar indicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i></div></div></div>');
                            $("#listaIndicadores" + i).append('<tr><td class="text-justify align-middle">' + this.indicador_nombre + '</td><td class="text-justify align-middle">' + this.indicador_descripcion + '</td><td class="align-middle">' + this.indicador_tipo + '</td><td class="text-justify align-middle">' + formula + '</td></tr>');
                        } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                            $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row" id="rowindicador"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"><i class="fas fa-edit" title="Editar Indicador" id="modIndicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i><i class="fas fa-trash-alt" id="elimIndicador" title="Eliminar indicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i></div></div></div>');
                            $("#listaIndicadores" + i).append('<tr><td class="text-justify align-middle">' + this.indicador_nombre + '</td><td class="text-justify align-middle">' + this.indicador_descripcion + '</td><td class="align-middle">' + this.indicador_tipo + '</td><td class="text-justify align-middle">' + formula + '</td></tr>');
                        } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                            $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row" id="rowindicador"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"><i class="fas fa-edit" title="Editar Indicador" id="modIndicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i><i class="fas fa-trash-alt" id="elimIndicador" title="Eliminar indicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i></div></div></div>');
                            $("#listaIndicadores" + i).append('<tr><td class="text-justify align-middle">' + this.indicador_nombre + '</td><td class="text-justify align-middle">' + this.indicador_descripcion + '</td><td class="align-middle">' + this.indicador_tipo + '</td><td class="text-justify align-middle">' + formula + '</td></tr>');
                        } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                            $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row" id="rowindicador"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"><i class="fas fa-edit" title="Editar Indicador" id="modIndicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i><i class="fas fa-trash-alt" id="elimIndicador" title="Eliminar indicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '"></i></div></div></div>');
                            $("#listaIndicadores" + i).append('<tr><td class="text-justify align-middle">' + this.indicador_nombre + '</td><td class="text-justify align-middle">' + this.indicador_descripcion + '</td><td class="align-middle">' + this.indicador_tipo + '</td><td class="text-justify align-middle">' + formula + '</td></tr>');
                        } else {
                            $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"></div></div></div>');
                            $("#listaIndicadores" + i).append('<tr><td class="text-justify align-middle">' + this.indicador_nombre + '</td><td class="text-justify align-middle">' + this.indicador_descripcion + '</td><td class="align-middle">' + this.indicador_tipo + '</td><td class="text-justify align-middle">' + formula + '</td></tr>');
                        }
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

function listaActividad(comp, i, numero) {
    $("#listaActividades" + i).empty();
    let total = 0.0, color = 'rgba(0, 0, 0, 0.05);';
    $.ajax({
        url: "../actividadReq?accion=ListarActividadRep" + "&comp=" + comp + "&tipo=" + 1,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $('#contenedorActividad' + i).css({"display": "none"});
                    $('#listadoActividades' + i).removeClass('d-none');
                    if (tusu === "15" && estadof === 3 && usuarioen !== $('#idAgObEs').val()) {
                        $('#agregaC').removeClass('d-none');
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if ((tusu === "2" || tusu === "5" || tusu === "8" || tusu === "7" || tusu === "15" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                        $('#agregaC').removeClass('d-none');
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                        $('#agregaC').removeClass('d-none');
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if ((tusu === "2" || tusu === "5" || tusu === "8" || tusu === "7" || tusu === "19") && estadof === 1 && usuarioen !== $('#idAgObEs').val()) {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 8 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 7 || estadof === 8 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen === $('#idAgObEs').val()) {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if (tusu === "11" && estadof === 2) {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if (tusu === "26" && estadof === 15) {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else if (tusu === "1" && estadof === 52) {
                        $('#btn_proyecto_enviar').css({"display": "flex"});
                    } else {
                        $('#btn_proyecto_enviar').css({"display": "none"});
                    }
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
                            div = '<i class="fas fa-edit" title="Editar Actividad" id="modActividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '"></i><i class="fas fa-trash-alt" id="elimActividad" title="Eliminar actividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '"></i>';
                        } else {
                            monto = new Intl.NumberFormat("US", formateador()).format(this.actividad_monto);
                            div = '<i class="fas fa-edit" title="Editar Actividad" id="modActividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '"></i><i class="fas fa-trash-alt" id="elimActividad" title="Eliminar actividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '"></i><i class="fas fa-cart-plus" title="Agregar Requerimientos" id="agregarReq" data-id="' + this.actividad_id + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-valor="' + i + '"></i>';
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
                        if (tusu === "15" && estadof === 3 && usuarioen !== $('#idAgObEs').val()) {
                            $("#listaActividades" + i).append('<div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_2 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '">' + div + '</div>');
                        } else if ((tusu === "2" || tusu === "5" || tusu === "8" || tusu === "7" || tusu === "15" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                            $("#listaActividades" + i).append('<div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_2 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '">' + div + '</div>');
                        } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val()) {
                            $("#listaActividades" + i).append('<div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_2 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '">' + div + '</div>');
                        } else if ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val()) {
                            $("#listaActividades" + i).append('<div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_2 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '">' + div + '</div>');
                        } else {
                            $("#listaActividades" + i).append('<div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_nombre + '</div><div class="encabezado_7 estilobody text-justify" style="background-color:' + color + '">' + this.actividad_responsable + '</div>\n\
                                <div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '" id="cuatrimestre' + this.actividad_id + '"></div><div class="encabezado_2 estilobody centro" style="background-color:' + color + '">' + monto + '</div>\n\
                                <div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '">' + div2 + '</div>');
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
                        total += this.actividad_monto;
                        num++;
                    });
                    if (num % 2 === 0) {
                        color = 'rgba(0, 0, 0, 0.05);';
                    } else {
                        color = '#fff';
                    }
                } else if (numero > 1 && (tusu === "15" && estadof === 3 && usuarioen !== $('#idAgObEs').val())) {
                    $('#agregaC').removeClass('d-none');
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if (numero > 1 && ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val())) {
                    $('#agregaC').removeClass('d-none');
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if (numero > 1 && (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && usuarioen !== $('#idAgObEs').val())) {
                    $('#agregaC').removeClass('d-none');
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if (numero > 1 && ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 2 || estadof === 7 || estadof === 8 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen !== $('#idAgObEs').val())) {
                    $('#agregaC').removeClass('d-none');
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else if (numero > 1 && ((tusu === "15" || tusu === "5" || tusu === "3") && (estadof === 3 || estadof === 7 || estadof === 8 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26) && $('#idAgComp' + i).val() === $('#idAgObEs').val() && usuarioen === $('#idAgObEs').val())) {
                    $('#agregaC').removeClass('d-none');
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                } else {
                    $('#agregaC').addClass('d-none');
                    $('#btn_proyecto_enviar').css({"display": "none"});
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

//Plurianual
$('#btn_proyecto_pluri').on('click', function () {
    $('#plurianualdiv').addClass('d-none');
    $('#plurianualtext').removeClass('d-none');
});
$('#plurianualtext').on('click', '#gPlurianual', function () {
    let v20 = $('#txt2020').val();
    let v21 = $('#txt2021').val();
    var vsuma = parseFloat(v20) + parseFloat(v21);
    if ((v20 !== null && v21 !== null) || (v20 !== '' && v21 !== '')) {
        if (m === vsuma) {
            $.ajax({
                url: "../proyecto?accion=IngresarMontosProy",
                type: 'POST',
                data: {proy: proy, v20: v20, v21: v21},
                dataType: 'json'
            })
                    .done(function (response) {
                        if (response === "Correcto") {
                            window.location.reload();
                        } else {
                            alert('Error al ingresar los montos, por favor verique el total disponible');
                        }
                    })
                    .fail(function () {
                        console.log('No existe conexión con la base de datos.');
                    })
                    .always(function () {
                        console.log('Se completo correctamente');
                    });
        } else {
            alert('La suma de los valores ingresados debe ser el monto total del royecto');
        }
    } else {
        alert('Debe ingresar valores');
    }
});
//Ingresar meta
$('.pestania').on('click', '.row .col-1 #ingresarMeta', function () {
    var data = $(this).data();
    $.ajax({
        url: "../componenteMeta?accion=IngresarMeta",
        type: 'POST',
        data: {txtnombreMeta: $('#metaF' + data['id']).val(), idComponenteMeta: $('#idComponente' + data['id']).val(), cedulaProyecto: $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response.result === "Correcto") {
                    $("#botonIndicador" + data['id']).css({"display": "flex"});
                    $("#contenedorIndicador" + data['id']).css({"display": "flex"});
                    listaMeta($('#idComponente' + data['id']).val(), data['id']);
                } else {
                    alert(response.result);
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});
//Radio bottom
$('.row').on('change', '.tipoIndicador', function () {
    var data = $(this).data();
    if ($('input[name=tipoIndicador' + data['id'] + ']:checked', '#contenedorIndicador' + data['id']).val() === "Cuantitativo") {
        $('#formulaIndicador' + data['id']).removeClass('d-none');
        $('#formulaIndicador' + data['id']).addClass('d-block');
    } else {
        $('#formulaIndicador' + data['id']).removeClass('d-block');
        $('#formulaIndicador' + data['id']).addClass('d-none');
    }
});
//Radio bottom requerimiento
$('.pestania').on('change', '.formulario .form-row .col-12 .row .tipoRequerimiento', function () {
    var data = $(this).data();
    if ($('input[name=tipoReq' + data['id'] + ']:checked', '#contenedorReqPac' + data['id']).val() === "2") {
        $('#selectReqeG' + data['id'] + ' option[value="0"]').prop("selected", true);
        $('#reqgeneral' + data['id']).addClass('d-none');
        $('#unidadM' + data['id']).addClass('d-none');
        $('#tipoC' + data['id']).addClass('d-none');
        $('#cpc' + data['id']).addClass('d-none');
        if (banRequerimiento) {
            $('#txtnombreReq' + data['id']).val('');
            $('#txtdescripcionReq' + data['id']).val('');
            $('#intcosto' + data['id']).val('');
        }
        $('#txtnombreReq' + data['id']).removeAttr("readonly");
        $('#txtdescripcionReq' + data['id']).removeAttr("readonly");
        $('#intcosto' + data['id']).removeAttr("readonly");
    } else {
        if (banRequerimiento) {
            $('#txtnombreReq' + data['id']).val('');
            $('#txtdescripcionReq' + data['id']).val('');
            $('#intcosto' + data['id']).val('');
        }
        $('#reqgeneral' + data['id']).removeClass('d-none');
        $('#unidadM' + data['id']).removeClass('d-none');
        $('#tipoC' + data['id']).removeClass('d-none');
        $('#cpc' + data['id']).removeClass('d-none');
        $('#selectReqeG' + data['id'] + ' option[value="0"]').prop("selected", true);
    }
});
$('.reqprogramacion').on('change', function () {
    var data = $(this).data();
    var suma = 0, p1 = 0, p2 = 0, p3 = 0;
    $('#checkcuatreq' + data['id'] + ':checked').each(
            function () {
                if ($(this).val() == 1) {
                    p1 = $(this).val();
                } else if ($(this).val() == 2) {
                    p2 = $(this).val();
                } else {
                    p3 = $(this).val();
                }
                suma++;
            });
    if (suma > 1) {
        if (p1 > 0) {
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).attr('type', 'text');
        } else {
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).attr('type', 'hidden');
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).val('');
        }
        if (p2 > 0) {
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).attr('type', 'text');
        } else {
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).attr('type', 'hidden');
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).val('');
        }
        if (p3 > 0) {
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).attr('type', 'text');
        } else {
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).attr('type', 'hidden');
            $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).val('');
        }
    } else {
        $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).attr('type', 'hidden');
        $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).attr('type', 'hidden');
        $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).attr('type', 'hidden');
        $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).val('');
        $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).val('');
        $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).val('');
    }
});
//Ingresar y modificar indicador
$('.pestania').on('submit', '.contenedorIndicador', function (event) {
    event.preventDefault();
    var data = $(this).data();
    if (banIndicaroEl) {
        if (banIndicador) {
            $.ajax({
                url: "../componenteMeta?accion=IngresarIndicador",
                type: 'POST',
                data: {idMetaIndicador: $("#idMeta" + data['id']).val(), txtnombreIndicador: $("#txtnombreIndicador" + data['id']).val(),
                    txtdescipcionIndicador: $("#txtdescipcionIndicador" + data['id']).val(), tipoIndicador: $('input:radio[name=tipoIndicador' + data['id'] + ']:checked').val(),
                    txtindicadorejecutadovalor: $("#txtindicadorejecutadovalor" + data['id']).val(), txtindicadorplanificadovalor: $("#txtindicadorplanificadovalor" + data['id']).val(),
                    txtindicadorplanificado: $("#txtindicadorplanificado" + data['id']).val(), valorIndicador: $('input:radio[name=valorIndicador' + data['id'] + ']:checked').val(), cedulaProyecto: $('#cedulaProyecto').val()},
                dataType: 'json'
            })
                    .done(function (response) {
                        if (response.result === "Correcto") {
                            $("#contenedorIndicador" + data['id']).addClass('d-none');
                            $("#botonActividad" + data['id']).removeClass('d-none');
                            $("#botonIndicador" + data['id']).removeClass('d-none');
                            listarIndicador($("#idMeta" + data['id']).val(), data['id']);
                        } else {
                            alert(response.result);
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
                url: "../componenteMeta?accion=ModificarIndicador",
                type: 'POST',
                data: {idIndicador: $("#idIndicador" + data['id']).val(), txtnombreIndicador: $("#txtnombreIndicador" + data['id']).val(),
                    txtdescipcionIndicador: $("#txtdescipcionIndicador" + data['id']).val(), tipoIndicador: $('input:radio[name=tipoIndicador' + data['id'] + ']:checked').val(),
                    txtindicadorejecutadovalor: $("#txtindicadorejecutadovalor" + data['id']).val(), txtindicadorplanificadovalor: $("#txtindicadorplanificadovalor" + data['id']).val(),
                    txtindicadorplanificado: $("#txtindicadorplanificado" + data['id']).val(), valorIndicador: $('input:radio[name=valorIndicador' + data['id'] + ']:checked').val(), cedulaProyecto: $('#cedulaProyecto').val()},
                dataType: 'json'
            })
                    .done(function (response) {
                        if (response === "Correcto") {
                            $("#contenedorIndicador" + data['id']).removeClass('d-none');
                            listarIndicador($("#idMeta" + data['id']).val(), data['id']);
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
    }
});
//Mostrar contenedor de indicador
$('.pestania').on('click', '.btn_proyecto_agregar_indicador', function () {
    var data = $(this).data();
    $('#contenedorIndicador' + data['id']).removeClass('d-none');
    $('#contenedorIndicador' + data['id']).addClass('d-block');
    window.location.href = '#contenedorIndicador' + data['id'];
    $('#botonindicadorg' + data['id']).html('GUARDAR');
    $('#botonindicadorg' + data['id']).removeAttr("data-toggle", "modal");
    $('#botonindicadorg' + data['id']).removeAttr("data-target", "#eliminarModal");
    banIndicador = true;
    banIndicaroEl = true;
});
//Mostrar detalles de indicador
$('.pestania').on('click', '.btn_indicador_detalle', function () {
    var data = $(this).data();
    $('#listaIndicadorTabla' + data['id']).slideToggle();
});
//Mostrar contenedor de actividad 
$(".pestania").on('click', '.btn_proyecto_actividad', function () {
    var data = $(this).data();
    $("#contenedorActividad" + data['id'])[0].reset();
    $('#txtnombreActividad' + data['id']).val("").removeAttr("readonly");
    $('#inpresponsable' + data['id']).val("").removeAttr("readonly");
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    var fecha1 = fi.split("-");
    var fecha2 = ff.split("-");
    var fia = new Date(fecha1[0], fecha1[1] - 1, fecha1[2]);
    var ffa = new Date(fecha2[0], fecha2[1] - 1, fecha2[2]);
    $('#programacionactividad' + data['id']).empty();
    $('#pori' + data['id']).css({"display": "none"});
    $('#porii' + data['id']).css({"display": "none"});
    $('#poriii' + data['id']).css({"display": "none"});
    var j = 1;
    for (var i = 0; i < mes.length; i++) {
        if ((j >= (fia.getMonth() + 1))) {
            if ((j <= (ffa.getMonth() + 1))) {
                $('#programacionactividad' + data['id']).append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes' + data['id'] + '" id="checkmes' + data['id'] + '"><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
            }
        }
        j++;
    }

    $('#pori' + data['id']).removeClass('d-flex');
    $('#pori' + data['id']).addClass('d-none');
    $('#porcentajei' + data['id']).val("").removeAttr("readonly");
    $('#porii' + data['id']).removeClass('d-flex');
    $('#porii' + data['id']).addClass('d-none');
    $('#porcentajeii' + data['id']).val("").removeAttr("readonly");
    $('#poriii' + data['id']).removeClass('d-flex');
    $('#poriii' + data['id']).addClass('d-none');
    $('#porcentajeiii' + data['id']).val("").removeAttr("readonly");
    $('[name="tipofinanciamiento' + data['id'] + '"]').attr("disabled", false);
    $('#contenedorActividad' + data['id']).removeClass('d-none');
    $('#contenedorActividad' + data['id']).addClass('d-block');
    window.location.href = '#contenedorActividad' + data['id'];
    $('#actividadBoton' + data['id']).html('GUARDAR');
    $('#actividadBoton' + data['id']).removeAttr("data-toggle", "modal");
    $('#actividadBoton' + data['id']).removeAttr("data-target", "#eliminarModal");
    banActividad = true;
    banActividadElim = true;
});
$('.pestania').on('click', '.btn_proyecto_agregar_indicador', function () {
    var data = $(this).data();
    $('#txtnombreIndicador' + data['id']).val("").removeAttr("readonly");
    $('#txtdescipcionIndicador' + data['id']).val("").removeAttr("readonly");
    $('[name="tipoIndicador' + data['id'] + '"]').attr("disabled", false);
    $('#txtindicadorejecutadovalor' + data['id']).val("").removeAttr("readonly");
    $('#txtindicadorplanificadovalor' + data['id']).val("").removeAttr("readonly");
    $('#txtindicadorplanificado' + data['id']).val("").removeAttr("readonly");
    $('#contenedorIndicador' + data['id'])[0].reset();
    $('#contenedorIndicador' + data['id']).removeClass('d-none');
    $('#formulaIndicador' + data['id']).addClass('d-none');
    window.location.href = '#contenedorIndicador' + data['id'];
    banIndicador = true;
});
$('.pestania').on('click', '.btn_indicador_detalle', function () {
    var data = $(this).data();
    //$('#listaIndicadorTabla' + data['id']).slideToggle();
    if ($('#listaIndicadorTabla' + data['id']).hasClass('d-none')) {
        $('#listaIndicadorTabla' + data['id']).removeClass('d-none');
    } else {
        $('#listaIndicadorTabla' + data['id']).addClass('d-none');
    }
});
$(".pestania").on('click', '.btn_proyecto_actividad', function () {
    var data = $(this).data();
    $("#contenedorActividad" + data['id'])[0].reset();
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    var fecha1 = fi.split("-");
    var fecha2 = ff.split("-");
    var fia = new Date(fecha1[0], fecha1[1] - 1, fecha1[2]);
    var ffa = new Date(fecha2[0], fecha2[1] - 1, fecha2[2]);
    $('#programacionactividad' + data['id']).empty();
    $('#pori' + data['id']).css({"display": "none"});
    $('#porii' + data['id']).css({"display": "none"});
    $('#poriii' + data['id']).css({"display": "none"});
    var j = 1;
    for (var i = 0; i < mes.length; i++) {
        if ((j >= (fia.getMonth() + 1))) {
            if ((j <= (ffa.getMonth() + 1))) {
                $('#programacionactividad' + data['id']).append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes' + data['id'] + '" id="checkmes' + data['id'] + '"><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
            }
        }
        j++;
    }
    $('#contenedorActividad' + data['id']).removeClass('d-none');
    $('#contenedorActividad' + data['id']).addClass('d-block');
    window.location.href = '#contenedorActividad' + data['id'];
    banActividad = true;
});
//Check
$('.pestania').on('change', '.formulario .form-row .mesactividad', function () {
    var data = $(this).data();
    var i = [1, 2, 3, 4];
    var ii = [5, 6, 7, 8];
    var iii = [9, 10, 11, 12];
    var cont1 = 0, cont2 = 0, cont3 = 0;
    $('#checkmes' + data['id'] + ':checked').each(
            function () {
                for (var k = 0; k < i.length; k++) {
                    if (i[k] == $(this).val()) {
                        cont1++;
                    }
                }
                for (var k = 0; k < ii.length; k++) {
                    if (ii[k] == $(this).val()) {
                        cont2++;
                    }
                }
                for (var k = 0; k < iii.length; k++) {
                    if (iii[k] == $(this).val()) {
                        cont3++;
                    }
                }
            }
    );
    if (cont1 > 0) {
        $('#pori' + data['id']).removeClass('d-none');
        $('#pori' + data['id']).addClass('d-flex');
    } else {
        $('#pori' + data['id']).removeClass('d-flex');
        $('#pori' + data['id']).addClass('d-none');
        $('#porcentajei' + data['id']).val("");
    }
    if (cont2 > 0) {
        $('#porii' + data['id']).removeClass('d-none');
        $('#porii' + data['id']).addClass('d-flex');
    } else {
        $('#porii' + data['id']).removeClass('d-flex');
        $('#porii' + data['id']).addClass('d-none');
        $('#porcentajeii' + data['id']).val("");
    }
    if (cont3 > 0) {
        $('#poriii' + data['id']).removeClass('d-none');
        $('#poriii' + data['id']).addClass('d-flex');
    } else {
        $('#poriii' + data['id']).removeClass('d-flex');
        $('#poriii' + data['id']).addClass('d-none');
        $('#porcentajeiii' + data['id']).val("");
    }
});
//Ingresar y modificar actividad
$('.pestania').on('submit', '.contenedorActividad', function (event) {
    let data = $(this).data();
    event.preventDefault();
    let metodo = $(this).attr("method");
    let accion = $(this).attr("action");
    var cua1 = [];
    var cua2 = [];
    var cua3 = [];
    for (var i = 1; i <= 4; i++) {
        $('#checkmes' + data['id'] + ':checked').each(
                function () {
                    if (i == $(this).val()) {
                        cua1.push($(this).val());
                    }
                }
        );
    }
    for (var i = 5; i <= 8; i++) {
        $('#checkmes' + data['id'] + ':checked').each(
                function () {
                    if (i == $(this).val()) {
                        cua2.push($(this).val());
                    }
                }
        );
    }
    for (var i = 9; i <= 12; i++) {
        $('#checkmes' + data['id'] + ':checked').each(
                function () {
                    if (i == $(this).val()) {
                        cua3.push($(this).val());
                    }
                }
        );
    }

    var c1 = JSON.stringify(cua1);
    var c2 = JSON.stringify(cua2);
    var c3 = JSON.stringify(cua3);
    if (banActividadElim) {
        if (banActividad) {
            $.ajax({
                url: accion,
                type: metodo,
                data: {cuatrimestre1: c1, cuatrimestre2: c2, cuatrimestre3: c3, nombre: $('#txtnombreActividad' + data['id']).val(), responsable: $('#inpresponsable' + data['id']).val(),
                    porcentaje1: $('#porcentajei' + data['id']).val(), porcentaje2: $('#porcentajeii' + data['id']).val(), porcentaje3: $('#porcentajeiii' + data['id']).val(),
                    tipo: $('input:radio[name=tipofinanciamiento' + data['id'] + ']:checked').val(), componente: $('#idComponente' + data['id']).val(), cedula: $('#cedulaProyecto').val()},
                dataType: 'json'
            })
                    .done(function (response) {
                        if (response === "Correcto") {
                            $('#contenedorActividad' + data['id']).removeClass('d-block');
                            $('#contenedorActividad' + data['id']).addClass('d-none');
                            $("#contenedorActividad" + data['id'])[0].reset();
                            listaActividad($('#idComponente' + data['id']).val(), data['id'], long);
                            $('#agregaC').removeClass('d-none');
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
                url: "../actividadReq?accion=ModificarActividad",
                type: metodo,
                data: {cuatrimestre1: c1, cuatrimestre2: c2, cuatrimestre3: c3, nombre: $('#txtnombreActividad' + data['id']).val(), responsable: $('#inpresponsable' + data['id']).val(),
                    porcentaje1: $('#porcentajei' + data['id']).val(), porcentaje2: $('#porcentajeii' + data['id']).val(), porcentaje3: $('#porcentajeiii' + data['id']).val(),
                    tipo: $('input:radio[name=tipofinanciamiento' + data['id'] + ']:checked').val(), actividad: $('#idActividadMod' + data['id']).val(), cedula: $('#cedulaProyecto').val()},
                dataType: 'json'
            })
                    .done(function (response) {
                        if (response === "Correcto") {
                            $('#contenedorActividad' + data['id']).removeClass('d-block');
                            $('#contenedorActividad' + data['id']).addClass('d-none');
                            $("#contenedorActividad" + data['id'])[0].reset();
                            listaActividad($('#idComponente' + data['id']).val(), data['id'], long);
                            listaRequerimiento($('#idComponente' + data['id']).val(), data['id']);
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
    }
});
function listarObjetivosOperativos(datos, objetivo_id) {
    //alert('Datos: ' + datos);
    $.ajax({
        url: "../persobj?accion=ListarObjetivos",
        data: {objetivo: datos},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#objobj').empty();
                //$('#listapolest').empty();
                //$('#actpresup').empty();
                //$('#frmAddProyecto').removeClass('d-block');
                //$('#frmAddProyecto').addClass('d-none');
                //$('#actpresup').selectpicker('refresh');
                //$("#frmAddProyecto")[0].reset();
                if (datos === 2 || datos === 3) {
                    $('#perfilInV').removeClass('d-none');
                    $('#perfilInV').addClass('d-block');
                    $('#integrantesInV').removeClass('d-none');
                    $('#integrantesInV').addClass('d-block');
                    $('#multiSelec').removeClass('d-none');
                    $('#multiSelec').addClass('d-block');
                    $('#coejeSelec').removeClass('d-block');
                    $('#coejeSelec').addClass('d-none');
                } else {
                    $('#perfilInV').removeClass('d-block');
                    $('#perfilInV').addClass('d-none');
                    $('#integrantesInV').removeClass('d-block');
                    $('#integrantesInV').addClass('d-none');
                    $('#multiSelec').removeClass('d-block');
                    $('#multiSelec').addClass('d-none');
                    $('#coejeSelec').removeClass('d-none');
                    $('#coejeSelec').addClass('d-block');
                }
                if (response.length > 0) {
                    $('#objobj').append('<option disabled>Seleccionar...</option>');
                    $.each(response, function () {
                        $('#objobj').append('<option value="' + this.objetivo_id + '">' + this.objetivo_nombre + '</option>');
                    });
                    $('#objobj option[value="' + objetivo_id + '"]').prop('selected', 'true');
                } else {
                    $('#objobj').html('<option selected="true" disabled>Sin registro</option>');
                }
//$('#objobj').addClass('d-block');
                $('#objobj').selectpicker('refresh');
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 0) {
                    console.log('Not connect: Verify Network.');
                } else if (jqXHR.status === 404) {
                    console.log('Requested page not found [404]');
                } else if (jqXHR.status === 500) {
                    console.log('Internal Server Error [500].');
                } else if (textStatus === 'parsererror') {
                    console.log('Requested JSON parse failed.');
                } else if (textStatus === 'timeout') {
                    console.log('Time out error.');
                } else if (textStatus === 'abort') {
                    console.log('Ajax request aborted.');
                } else {
                    console.log('Uncaught Error: ' + jqXHR.responseText);
                }
            })
            .always(function () {
                console.log('ejecutada');
            });
}

function listarActividadesPresupuestarias(objetivo_id, actividad_presupuestaria_id) {
    //var datos = $(this).val();
    //$('#idOEIForm').val(datos);
    var estado;

    if ($('#selectoranio').val() === '2020') {
        estado = $('#selectoranio').val();
    } else if ($('#selectoranio').val() === '2021' || $('#selectoranio').val() === '2022') {
        estado = 2021;
    } else {
        estado = $('#selectoranio').val();
    }

    $.ajax({
        url: "../persobj?accion=ListarActividadesP",
        data: {objetivo: objetivo_id, estado: estado},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#actpresup').empty();
                if (response.length > 0) {
                    $('#actpresup').append('<option disabled>Seleccionar...</option>');
                    $.each(response, function () {
                       $('#actpresup').append('<option value="' + this.ap_id + '">' + this.ap_nombre + '</option>');
                    });
                    $('#actpresup option[value="' + actividad_presupuestaria_id + '"]').prop('selected', 'true');
                } else {
                    $('#actpresup').html('<option selected="true" disabled>Sin registro</option>');
                }
                //$('#actpresup').addClass('d-block');
                $('#actpresup').selectpicker('refresh');
            })
            .fail(function () {

            })
            .always(function () {

            });
}

$('.reqgeneral').on('change', function () {
    let valor = $(this).val();
    let data = $(this).data();
    if (valor > 0) {
        $.ajax({
            url: "../requerimientosGenerales?accion=DetallesRequerimientosGenerales",
            type: 'POST',
            data: {reqgeneral: valor},
            dataType: 'json'
        })
                .done(function (response) {
                    $.each(response, function () {
                        $('#txtnombreReq' + data['id']).val(this.rg_nombre);
                        $('#txtdescripcionReq' + data['id']).val(this.rg_descripcion);
                        $('#intcosto' + data['id']).val(this.rg_costo_unitario);
                        $('#txtnombreReq' + data['id']).attr("readonly", "readonly");
                        $('#txtdescripcionReq' + data['id']).attr("readonly", "readonly");
                        $('#intcosto' + data['id']).attr("readonly", "readonly");
                    });
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    } else {
        $('#txtnombreReq' + data['id']).removeAttr("readonly");
        $('#txtdescripcionReq' + data['id']).removeAttr("readonly");
        $('#intcosto' + data['id']).removeAttr("readonly");
        $('#txtnombreReq' + data['id']).val('');
        $('#txtdescripcionReq' + data['id']).val('');
        $('#intcosto' + data['id']).val('');
    }
});
//Mostrar ingresar requerimiento
$('.pestania').on('click', '.tablaover .encabezado .encabezado_4 #agregarReq', function () {
    var data = $(this).data();
    $("#intcantidad" + data['valor']).val("").removeAttr("readonly");
    $('[name="radioiva' + data['valor'] + '"]').attr("disabled", false);
    $('[name="tipoReq' + data['valor'] + '"]').attr("disabled", false);
    $('#selfinan' + data['valor']).attr("disabled", false);
    $('#seltipoc' + data['valor']).attr("disabled", false);
    $('#selunidad' + data['valor']).attr("disabled", false);
    $('#selectReqeG' + data['valor']).attr("disabled", false);
    $("#inpcpc" + data['valor']).val("").removeAttr("readonly");
    $('#txtnombreReq' + data['valor']).val("").removeAttr("readonly");
    $('#txtdescripcionReq' + data['valor']).val("").removeAttr("readonly");
    $('#intcosto' + data['valor']).removeAttr("readonly");
    $('#programacionrequerimiento' + data['valor']).empty();
    $('#contenedorReqPac' + data['valor'])[0].reset();
    $('#idactividadreq' + data['valor']).val(data['id']);
    $('#programacionrequerimiento' + data['valor']).append('<label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center pr-4">Porcentajes:</label>');
    if (data['po1'] > 0) {
        $('#programacionrequerimiento' + data['valor']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="1" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq' + data['valor'] + '">I</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajeri' + data['valor'] + '" placeholder="%"></div></div>');
    }
    if (data['po2'] > 0) {
        $('#programacionrequerimiento' + data['valor']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="2" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq' + data['valor'] + '">II</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajerii' + data['valor'] + '" placeholder="%"></div></div>');
    }
    if (data['po3'] > 0) {
        $('#programacionrequerimiento' + data['valor']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="3" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq' + data['valor'] + '">III</div><div class="col-8"><input class="form-control" type="hidden" id="porcentajeriii' + data['valor'] + '" placeholder="%" min="0" max="100"></div></div>');
    }

    $('#porcentajeri' + data['valor']).attr("disabled", false);
    $('#porcentajerii' + data['valor']).attr("disabled", false);
    $('#porcentajeriii' + data['valor']).attr("disabled", false);
    $('#botonrequerimientog' + data['valor']).html('GUARDAR');
    $('#botonrequerimientog' + data['valor']).removeAttr("data-toggle", "modal");
    $('#botonrequerimientog' + data['valor']).removeAttr("data-target", "#eliminarModal");
    $('#contenedorReqPac' + data['valor']).removeClass('d-none');
    window.location.href = '#contenedorReqPac' + data['valor'];
    banRequerimiento = true;
    banRequerimientoElim = true;
    $.ajax({
        url: "../requerimientosGenerales?accion=ListarRequerimientosGeneralesAnio",
        data:{anio:$('#selectoranio').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selectReqeG' + data['valor']).empty();
                    $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selectReqeG' + data['valor']).append('<option value="0" selected="selected">Ninguno</option>');
                    $.each(response, function () {
                        $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selectReqeG' + data['valor']).append('<option value="' + this.rg_id + '">' + this.rg_nombre + '</option>');
                    });
                }
                $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selectReqeG' + data['valor']).selectpicker('refresh');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $.ajax({
        url: "../actividadReq?accion=ListarUnidad",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selunidad' + data['valor']).empty();
                    $.each(response, function () {
                        $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selunidad' + data['valor']).append('<option value="' + this.unidad_id + '">' + this.unidad_nombre + '</option>');
                    });
                }
                $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selunidad' + data['valor']).selectpicker('refresh');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $.ajax({
        url: "../actividadReq?accion=ListarTipoC",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#seltipoc' + data['valor']).empty();
                    $.each(response, function () {
                        $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#seltipoc' + data['valor']).append('<option value="' + this.tc_id + '">' + this.tc_nombre + '</option>');
                    });
                }
                $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#seltipoc' + data['valor']).selectpicker('refresh');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $.ajax({
        url: "../actividadReq?accion=ListarFinanciamiento",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response.length > 0) {
                    $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selfinan' + data['valor']).empty();
                    $.each(response, function () {
                        $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selfinan' + data['valor']).append('<option value="' + this.financiamiento_id + '">' + this.financiamiento_nombre + '</option>');
                    });
                }
                $('.pestania').children('.formulario').children('.form-row').children('.col-12').children('.row').children('#selfinan' + data['valor']).selectpicker('refresh');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});
//agregar y modificar requerimientos
$('.pestania').on('submit', '.contenedorRequerimientos', function (event) {
    event.preventDefault();
    let data = $(this).data();
    let cuat = [];
    let unidad, tipoc, cpc;
    if (banRequerimientoElim && !banAnio) {
        if ($('input[name=tipoReq' + data['id'] + ']:checked', '#contenedorReqPac' + data['id']).val() > 0) {

            $('#checkcuatreq' + data['id'] + ':checked').each(
                    function () {
                        cuat.push($(this).val());
                    }
            );
            var c = JSON.stringify(cuat);
            if ($('input:radio[name=tipoReq' + data['id'] + ']:checked').val() === "1") {
                unidad = $('#selunidad' + data['id']).val();
                tipoc = $('#seltipoc' + data['id']).val();
                cpc = $('#inpcpc' + data['id']).val();
            } else {
                unidad = 0;
                tipoc = 0;
                cpc = 0;
            }
            if (banRequerimiento) {
                $.ajax({
                    url: "../actividadReq?accion=IngresarRequerimiento",
                    type: 'POST',
                    data: {cuatrimestre: c, nombre: $('#txtnombreReq' + data['id']).val(), descripcion: $('#txtdescripcionReq' + data['id']).val(), componente: $('#idComponente' + data['id']).val(),
                        porcentaje1: $('#porcentajeri' + data['id']).val(), porcentaje2: $('#porcentajerii' + data['id']).val(), porcentaje3: $('#porcentajeriii' + data['id']).val(),
                        tipo: $('input:radio[name=tipoReq' + data['id'] + ']:checked').val(), cantidad: $('#intcantidad' + data['id']).val(), actividad: $('#idactividadreq' + data['id']).val(),
                        costo: $('#intcosto' + data['id']).val(), iva: $('input:radio[name=radioiva' + data['id'] + ']:checked').val(), financiamiento: $('#selfinan' + data['id']).val(), unidad: unidad,
                        tipocompra: tipoc, cpc: cpc, cedula: $('#cedulaProyecto').val(), reqgeneral: $('#selectReqeG' + data['id']).val(), ag: $('#idAgObEs').val()},
                    dataType: 'json'
                })
                        .done(function (response) {
                            if (response.result === "Correcto") {
                                $('#montoF').html(new Intl.NumberFormat("US", formateador()).format(response.monto));
                                listaActividad($('#idComponente' + data['id']).val(), data['id'], long);
                                listaRequerimiento($('#idComponente' + data['id']).val(), data['id']);
                            } else {
                                alert(response.result);
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
                    url: "../actividadReq?accion=ModificarRequerimiento",
                    type: 'POST',
                    data: {cuatrimestre: c, nombre: $('#txtnombreReq' + data['id']).val(), descripcion: $('#txtdescripcionReq' + data['id']).val(), componente: $('#idComponente' + data['id']).val(),
                        porcentaje1: $('#porcentajeri' + data['id']).val(), porcentaje2: $('#porcentajerii' + data['id']).val(), porcentaje3: $('#porcentajeriii' + data['id']).val(),
                        tipo: $('input:radio[name=tipoReq' + data['id'] + ']:checked').val(), cantidad: $('#intcantidad' + data['id']).val(), req: $('#idrequerimiento' + data['id']).val(),
                        costo: $('#intcosto' + data['id']).val(), iva: $('input:radio[name=radioiva' + data['id'] + ']:checked').val(), financiamiento: $('#selfinan' + data['id']).val(), unidad: $('#selunidad' + data['id']).val(),
                        tipocompra: $('#seltipoc' + data['id']).val(), cpc: $('#inpcpc' + data['id']).val(), verificacion: $('#reqverificacion' + data['id']).val(), cedula: $('#cedulaProyecto').val(), reqgeneral: $('#selectReqeG' + data['id']).val()},
                    dataType: 'json'
                })
                        .done(function (response) {
                            if (response.result === "Correcto") {
                                $('#montoF').html(new Intl.NumberFormat("US", formateador()).format(response.monto));
                                listaActividad($('#idComponente' + data['id']).val(), data['id'], long);
                                listaRequerimiento($('#idComponente' + data['id']).val(), data['id']);
                            } else {
                                alert(response.result);
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
            alert("Debe seleccionar el tipo de requerimiento");
        }
    } else if (banAnio) {
        $.ajax({
            url: "../actividadReq?accion=ModificarAnioReq",
            type: 'POST',
            data: {req: $('#idrequerimientoAn' + data['id']).val(), anio: $('input:radio[name=AnioReq' + data['id'] + ']:checked').val(), proy: proy},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response.length > 0) {
                        alert("Correcto");
                        $.each(response, function (indice, montos) {
                            $('#div' + montos.mp_anio).html('<label style="font-weight: bold">' + montos.mp_anio + ':</label>' + new Intl.NumberFormat("US", formateador()).format(montos.mp_monto));
                        });
                    } else {
                        alert(response.result);
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
//Listar requerimientos
function listaRequerimiento(comp, i) {
    $.ajax({
        url: "../actividadReq?accion=ListarRequerimientoRep" + "&comp=" + comp + "&tipo=" + 2,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                let  total2 = 0, color = 'rgba(0, 0, 0, 0.05);';
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
                                if (tusu === "15" && estadof === 3) {
                                    if (r.tc_id < 1) {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                                <i class="fas fa-trash-alt" id="elimRequerimiento" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i>';
                                    } else {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                                <i class="fas fa-trash-alt" id="elimRequerimiento" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + r.req_id + '"></i>';
                                    }
                                } else if ((tusu === "2" || tusu === "5" || tusu === "15" || tusu === "8" || tusu === "7" || tusu === "19") && (estadof === 0 || estadof === 3 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                                    if (r.tc_id < 1) {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                                <i class="fas fa-trash-alt" id="elimRequerimiento" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i>';
                                    } else {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                                <i class="fas fa-trash-alt" id="elimRequerimiento" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + r.req_id + '"></i>';
                                    }
                                } else if (tusu === "3" && (estadof === 0 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
                                    if (r.tc_id < 1) {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                                <i class="fas fa-trash-alt" id="elimRequerimiento" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i>';
                                    } else {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                                <i class="fas fa-trash-alt" id="elimRequerimiento" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i><i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + r.req_id + '"></i>';
                                    }
                                } else {
                                    if (tusu === "3" && (estadof === 6 || estadof === 17) && prio === 1) {
                                        pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="fas fa-edit" title="Editar Requerimiento" id="modReqAnio" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '"></i>';
                                    } else {
                                        if (r.tc_id < 1) {
                                            if (estadof === 16 || estadof === 17 || estadof === 18) {
                                                if (r.req_prioridad === 1) {
                                                    pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="fas fa-check"></i><i class="fas fa-money-check-alt m-2" id="kardex" data-req="' + this.req_id + '" data-valor="' + i + '" title="Visualizar ejecuci\u00f3n"></i><i class="far fa-id-card" title="Servicios Profesionales" data-req="' + this.req_id + '" data-valor="' + i + '" id="servprof"></i>';
                                                } else {
                                                    pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="fas fa-times"></i>';
                                                }
                                            } else {
                                                pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i>';
                                            }
                                        } else {
                                            if (r.req_verificacion === 2) {
                                                pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '" data-verificacion="3"></i>\n\
                                               \n\<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + r.req_id + '"></i>';
                                            } else {
                                                if (estadof === 16 || estadof === 17 || estadof === 18) {
                                                    if (r.req_prioridad === 1) {
                                                        pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + this.req_id + '"></i><i class="fas fa-check"></i><i class="fas fa-money-check-alt m-2" id="kardex" data-req="' + this.req_id + '" data-valor="' + i + '" title="Visualizar ejecuci\u00f3n"></i>';
                                                    } else {
                                                        pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + this.req_id + '"></i><i class="fas fa-times"></i>';
                                                    }
                                                } else {
                                                    pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + this.req_id + '"></i>';
                                                }
                                            }
                                        }
                                    }
                                }
                                $('#listaRequerimiento' + i).children('#listaact' + id + i).append('<div class="encabezado_2 estilobody text-justify" style="background-color:' + color + '">' + r.req_nombre + '</div><div class="estilobody encabezado_2 p-0" style="background-color:' + color + '" id="cuatrireq' + r.req_id + '"></div>\n\
                                <div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + r.req_cantidad + '</div><div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_sin_iva) + '</div>\n\
                                <div class="estilobody encabezado_5 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_total) + '</div><div class="encabezado_5 estilobody centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.ae_ejecucion) + '</div>\n\
                                <div class="estilobody encabezado_4" style="background-color:' + color + '" id="accion' + r.req_id + i + '">' + pac + '</div>\n\
                                <div class="estilobody encabezado_completo" id="desrequerimiento' + this.req_id + i + '" style="display:none; background-color:rgba(0,0,0,0.15);"></div>\n\
                                <div class="estilobody encabezado_completo_2" id="ejecucionreq' + this.req_id + i + '" style="display:none; background-color:rgba(0,0,0,0.15);"></div>\n\
                                <div class="estilobody encabezado_completo_2" id="serviciospro' + this.req_id + i + '" style="display:none; background-color:rgba(0,0,0,0.15);"></div>');
                                var pres, req = this.req_id;
                                if (prio === 1) {
                                    pres = 'año:' + r.actividad_id;
                                } else {
                                    pres = '';
                                }
                                if (r.tc_id < 1) {
                                    $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#desrequerimiento' + this.req_id + i).html('<div class="main__contenedor_listados_s_xs">NO PAC</div><div class="main__contenedor_listados_m_m">Descripcion: ' + r.req_descripcion + '</div><div class="main__contenedor_listados_s_m"> ' + r.financiamiento_nombre + '</div><div class="main__contenedor_listados_s_m">Costo unitario: ' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_unitario) + '</div><div class="main__contenedor_listados_s_m"> ' + pres + '</div>');
                                } else {
                                    $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#desrequerimiento' + this.req_id + i).html('<div class="main__contenedor_listados_s_xs">PAC</div><div class="main__contenedor_listados_m_m">Descripcion: ' + r.req_descripcion + '</div><div class="main__contenedor_listados_s_m"> ' + r.financiamiento_nombre + '</div><div class="main__contenedor_listados_s_m">Tipo de compra: ' + r.tc_nombre + '</div><div class="main__contenedor_listados_s_m">Unidad: ' + r.unidad_nombre + '</div>\n\
                                    <div class="main__contenedor_listados_s_m">CPC: ' + r.req_cpc + '</div><div class="main__contenedor_listados_s_m">Costo Unitario: ' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_unitario) + '</div><div class="main__contenedor_listados_s_m"> ' + pres + '</div>');
                                }
                                if (r.cuatri.length > 0) {
                                    $('#listaact' + id + i).children('#cuatrireq' + r.req_id).append('<div class="centro encabezado_6">' + po1.toFixed(2) + '%</div><div class="encabezado_6 centro">' + po2.toFixed(2) + '%</div><div class="encabezado_6 centro">' + po3.toFixed(2) + '%</div>');
                                }
                                $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#ejecucionreq' + req + i).append('<div class="encabezado_2" style="font-weight: bold; outline:1px solid black;">Tramite/Justificativo</div><div class="encabezado_2" style="font-weight: bold; outline:1px solid black;">Estado del Tramite</div><div class="encabezado_2" style="font-weight: bold; outline:1px solid black;">Total Presupuesto</div><div class="encabezado_2" style="font-weight: bold; outline:1px solid black;">Monto Tramite</div><div class="encabezado_2" style="font-weight: bold; outline:1px solid black;">Disponible</div>');
                                for (var ej = 0; ej < r.req.length; ej++) {
                                    var ref, descripcion, total, disponible, monto;
                                    if (r.req[ej].req_descripcion === "reforma") {
                                        if (ej === 0) {
                                            ref = "RA-002";
                                        } else {
                                            total = r.req[ej].req_costo_total;
                                            ref = "RA-" + r.req[ej].req_reforma;
                                        }
                                        if (ej === 0) {
                                            descripcion = "POA Aprobado";
                                            monto = r.req[ej].req_costo_total;
                                            total = 0.0;
                                        } else {
                                            monto = disponible;
                                            if (r.req[ej].req_costo_total > 0 && r.req[ej].req_costo_total < r.req[ej - 1].req_costo_total) {
                                                descripcion = "Disminuci\u00f3n";
                                            } else if (r.req[ej].req_costo_total < 0) {
                                                descripcion = "Disminuci\u00f3n";
                                            } else {
                                                descripcion = "Incremento";
                                            }
                                        }
                                    } else if (r.req[ej].req_descripcion === "Aprobado Analista Planificación") {
                                        ref = r.req[ej].req_reforma + "-STP-2020";
                                        descripcion = r.req[ej].req_descripcion;
                                        total = r.req[ej].req_costo_total;
                                        monto = disponible;
                                    } else {
                                        ref = r.req[ej].req_reforma + "-UCP-2020";
                                        descripcion = r.req[ej].req_descripcion;
                                        total = r.req[ej].req_costo_total;
                                        monto = disponible;
                                    }
                                    if (r.req[ej].req_costo_total < 0 && r.req[ej].req_descripcion !== "Anulado") {
                                        disponible = monto + total;
                                    } else if (r.req[ej].req_descripcion === "Anulado") {
                                        disponible = monto + total;
                                    } else {
                                        disponible = monto - total;
                                    }
                                    $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#ejecucionreq' + req + i).append('<div class="estilobody encabezado p-0" style="outline:1px solid black;"><div class="encabezado_2">' + ref + '</div><div class="encabezado_2">' + descripcion + '</div><div class="encabezado_2">' + new Intl.NumberFormat("US", formateador()).format(monto) + '</div>\n\
                                    <div class="encabezado_2">' + new Intl.NumberFormat("US", formateador()).format(total) + '</div><div class="encabezado_2">' + new Intl.NumberFormat("US", formateador()).format(disponible) + '</div></div>');
                                }
                                total2 += r.ae_ejecucion;
                                $.each(r.actividad_eval, function (indice, sp) {
                                    var tipo;
                                    if (sp.actividad_id === 3) {
                                        tipo = 'HONORARIO FIJO';
                                    } else if (sp.actividad_id === 2) {
                                        tipo = 'HONORARIO MENSUAL';
                                    } else if (sp.actividad_id === 1) {
                                        tipo = 'HORAS CLASE';
                                    }
                                    $("#listaRequerimiento" + i).children('#listaact' + id + i).children('#serviciospro' + r.req_id + i).append('<div class="estilobody encabezado p-0" style="outline:1px solid black;"><div class="encabezado_2">' + sp.req_nombre + ' ' + sp.req_descripcion + '</div><div class="encabezado_2">' + sp.actividad_nombre + '</div><div class="encabezado_2">' + tipo + '</div><div class="encabezado_2">' + new Intl.NumberFormat("US", formateador()).format(sp.req_costo_sin_iva) + '</div><div class="encabezado_2">' + new Intl.NumberFormat("US", formateador()).format(sp.req_costo_total) + '</div></div>');
                                });
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
                $("#listaRequerimiento" + i).append('<div class="encabezado_completo centro estilobody" style="background-color:' + color + '; font-weight:bold;">TOTAL: ' + new Intl.NumberFormat("US", formateador()).format(total2) + '</div>');
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
//Ver ejecucion requerimientos
$(".encabezado").on('click', '.encabezado_4 #kardex', function () {
    var data = $(this).data();
    $('#ejecucionreq' + data['req'] + data['valor']).slideToggle();
});
//Ver ejecucion requerimientos
$(".encabezado").on('click', '.encabezado_4 #servprof', function () {
    var data = $(this).data();
    $('#serviciospro' + data['req'] + data['valor']).slideToggle();
});
//Modificar componente
$(".row").on('click', '#btn_proyecto_componente_modificar', function () {
    var data = $(this).data();
    $("#inputComp" + data['id']).html('<input class="form-control" type="text" id="componenteF' + data['id'] + '" name="componenteF' + data['id'] + '" placeholder="Ingresar componente" value="' + data['componente'] + '"><input type="hidden" name="idComponente' + data['id'] + '" id="idComponente' + data['id'] + '" value="' + data['idcomponente'] + '">');
    $("#btn_comp" + data['id']).html('<i class="far fa-save" id="btn_modificar_componente" data-id="' + data['id'] + '" data-idcomponente="' + data['idcomponente'] + '"></i>');
});
$('.row').on('click', '.col-1 #btn_modificar_componente', function () {
    var data = $(this).data();
    $.ajax({
        url: "../componenteMeta?accion=ModificarComponente",
        type: 'POST',
        data: {idComponente: data['idcomponente'], txtnombreComp: $('#componenteF' + data['id']).val(), cedulaProyecto: $('#cedulaProyecto').val()},
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
//Modificar Meta
$(".row").on('click', '#btn_proyecto_meta_modificar', function () {
    var data = $(this).data();
    $("#inputMeta" + data['id']).html('<input class="form-control" type="text" id="metaF' + data['id'] + '" name="metaF' + data['id'] + '" value="' + data['meta'] + '"><input type="hidden" name="idMeta" id="idMeta' + data['id'] + '" value="' + data['idmeta'] + '">');
    $("#btn_meta" + data['id']).html('<i class="far fa-save" id="btn_modificar_meta" data-id="' + data['id'] + '" data-idmeta="' + data['idmeta'] + '" data-comp="' + data['comp'] + '"></i>');
});
$('.row').on('click', '.col-1 #btn_modificar_meta', function () {
    var data = $(this).data();
    $.ajax({
        url: "../componenteMeta?accion=ModificarMeta",
        type: 'POST',
        data: {idMeta: data['idmeta'], txtnombreMeta: $('#metaF' + data['id']).val(), cedulaProyecto: $('#cedulaProyecto').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    listaMeta(data['comp'], data['id']);
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
//Modificar Indicador
$(".pestania").on('click', '.col-12 #rowindicador .col-12 #modIndicador', function () {
    var data = $(this).data();
    $('#idIndicador' + data['id']).val(data['idindicador']);
    $('#txtnombreIndicador' + data['id']).val(data['nombre']).removeAttr("readonly");
    $('#txtdescipcionIndicador' + data['id']).val(data['descripcion']).removeAttr("readonly");
    $('[name="tipoIndicador' + data['id'] + '"][value="' + data['tipo'] + '"]').prop("checked", true);
    $('[name="tipoIndicador' + data['id'] + '"]').attr("disabled", false);
    if (data['tipo'] === "Cuantitativo") {
        $('#formulaIndicador' + data['id']).removeClass('d-none');
    } else {
        $('#formulaIndicador' + data['id']).addClass('d-none');
    }
    var eje, plan;
    if (data['ejecutado'] === "vacio") {
        eje = "";
    } else {
        eje = data['ejecutado'];
    }
    if (data['planificado'] === "vacio") {
        plan = "";
    } else {
        plan = data['planificado'];
    }
    $('#txtindicadorejecutadovalor' + data['id']).val(eje).removeAttr("readonly");
    $('#txtindicadorplanificadovalor' + data['id']).val(plan).removeAttr("readonly");
    if (data['valor'] <= 1) {
        $('[name="valorIndicador' + data['id'] + '"][value="2"]').prop("checked", true);
        $('#txtindicadorplanificado' + data['id']).val(data['valor'] * 100).removeAttr("readonly");
    } else {
        $('[name="valorIndicador' + data['id'] + '"][value="1"]').prop("checked", true);
        $('#txtindicadorplanificado' + data['id']).val(data['valor']).removeAttr("readonly");
    }
    $('#contenedorIndicador' + data['id']).removeClass('d-none');
    window.location.href = '#contenedorIndicador' + data['id'];
    $('#botonindicadorg' + data['id']).html('MODIFICAR');
    $('#botonindicadorg' + data['id']).removeAttr("data-toggle", "modal");
    $('#botonindicadorg' + data['id']).removeAttr("data-target", "#eliminarModal");
    banIndicador = false;
    banIndicaroEl = true;
});
//Eliminar Indicador
$(".pestania").on('click', '.col-12 #rowindicador .col-12 #elimIndicador', function () {
    var data = $(this).data();
    $('#idIndicador' + data['id']).val(data['idindicador']);
    $('#txtnombreIndicador' + data['id']).val(data['nombre']).attr("readonly", "readonly");
    $('#txtdescipcionIndicador' + data['id']).val(data['descripcion']).attr("readonly", "readonly");
    $('[name="tipoIndicador' + data['id'] + '"][value="' + data['tipo'] + '"]').prop("checked", true);
    $('[name="tipoIndicador' + data['id'] + '"]').attr("disabled", "true");
    if (data['tipo'] === "Cuantitativo") {
        $('#formulaIndicador' + data['id']).removeClass('d-none');
    } else {
        $('#formulaIndicador' + data['id']).addClass('d-none');
    }

    var eje, plan;
    if (data['ejecutado'] === "vacio") {
        eje = "";
    } else {
        eje = data['ejecutado'];
    }
    if (data['planificado'] === "vacio") {
        plan = "";
    } else {
        plan = data['planificado'];
    }

    $('#txtindicadorejecutadovalor' + data['id']).val(eje).attr("readonly", "readonly");
    $('#txtindicadorplanificadovalor' + data['id']).val(plan).attr("readonly", "readonly");
    if (data['valor'] <= 1) {
        $('[name="valorIndicador' + data['id'] + '"][value="2"]').prop("checked", true);
        $('#txtindicadorplanificado' + data['id']).val(data['valor'] * 100).attr("readonly", "readonly");
    } else {
        $('[name="valorIndicador' + data['id'] + '"][value="1"]').prop("checked", true);
        $('#txtindicadorplanificado' + data['id']).val(data['valor']).attr("readonly", "readonly");
    }
    $('#contenedorIndicador' + data['id']).removeClass('d-none');
    window.location.href = '#contenedorIndicador' + data['id'];
    $('#botonindicadorg' + data['id']).html('ELIMINAR');
    $('#botonindicadorg' + data['id']).attr("data-toggle", "modal");
    $('#botonindicadorg' + data['id']).attr("data-target", "#eliminarModal");
    //$('#eliminarModalBton').attr("data-tipo", "indicador");
    //$('#eliminarModalBton').attr("data-id", data['id']);
    //$('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el Indicador ' + data['nombre'] + '<input type="hidden" name="idindicadorelim" id="idindicadorelim" value="' + data['idindicador'] + '">');
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el Indicador ' + data['nombre'] + '<input type="hidden" name="idindicadorelim" id="idindicadorelim" value="' + data['idindicador'] + '"><input type="hidden" name="tipom" id="tipom" value="indicador"><input type="hidden" name="idinp" id="idinp" value="' + data['id'] + '">');
    banIndicaroEl = false;
});
//Eliminar Componente
$(".col-1").on('click', '#btn_proyecto_componente_eliminar', function () {
    var data = $(this).data();
    //$('#eliminarModalBton').attr("data-tipo", "componente");
    //$('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el componente?<input type="hidden" name="idcompelim" id="idcompelim" value="' + data['idcomponente'] + '">');
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el componente?<input type="hidden" name="idcompelim" id="idcompelim" value="' + data['idcomponente'] + '"><input type="hidden" name="tipom" id="tipom" value="componente">');
    $('#eliminarModal').modal();
});
//Modificar Actividad
$(".pestania").on('click', '.tablaover .listaActividad .encabezado .encabezado_4 #modActividad', function () {
    var data = $(this).data();
    $('#idActividadMod' + data['id']).val(data['idactividad']);
    $('#txtnombreActividad' + data['id']).val(data['nombre']).removeAttr("readonly");
    $('#inpresponsable' + data['id']).val(data['responsable']).removeAttr("readonly");
    var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    var fecha1 = fi.split("-");
    var fecha2 = ff.split("-");
    var fia = new Date(fecha1[0], fecha1[1] - 1, fecha1[2]);
    var ffa = new Date(fecha2[0], fecha2[1] - 1, fecha2[2]);
    $('#programacionactividad' + data['id']).empty();
    var j = 1;
    for (var i = 0; i < meses.length; i++) {
        if ((j >= (fia.getMonth() + 1))) {
            if ((j <= (ffa.getMonth() + 1))) {
                $('#programacionactividad' + data['id']).append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes' + data['id'] + '" id="checkmes' + data['id'] + '"><label class="form-check-label"  style="font-weight: normal;">' + meses[i] + '</label></div>');
            }
        }
        j++;
    }

    if (data['mes'].length > 0) {
        for (var k = 0; k < data['mes'].length; k++) {
            $('[name="checkmes' + data['id'] + '"][value="' + data['mes'][k] + '"]').prop("checked", true);
        }

    }
    if (data['cuatri1'] > 0) {
        $('#pori' + data['id']).removeClass('d-none');
        $('#pori' + data['id']).addClass('d-flex');
        $('#porcentajei' + data['id']).val(data['cuatri1']).removeAttr("readonly");
    } else {
        $('#pori' + data['id']).removeClass('d-flex');
        $('#pori' + data['id']).addClass('d-none');
        $('#porcentajei' + data['id']).val("").removeAttr("readonly");
    }
    if (data['cuatri2'] > 0) {
        $('#porii' + data['id']).removeClass('d-none');
        $('#porii' + data['id']).addClass('d-flex');
        $('#porcentajeii' + data['id']).val(data['cuatri2']).removeAttr("readonly");
    } else {
        $('#porii' + data['id']).removeClass('d-flex');
        $('#porii' + data['id']).addClass('d-none');
        $('#porcentajeii' + data['id']).val("").removeAttr("readonly");
    }
    if (data['cuatri3'] > 0) {
        $('#poriii' + data['id']).removeClass('d-none');
        $('#poriii' + data['id']).addClass('d-flex');
        $('#porcentajeiii' + data['id']).val(data['cuatri3']).removeAttr("readonly");
    } else {
        $('#poriii' + data['id']).removeClass('d-flex');
        $('#poriii' + data['id']).addClass('d-none');
        $('#porcentajeiii' + data['id']).val("").removeAttr("readonly");
    }

    $('[name="tipofinanciamiento' + data['id'] + '"][value="' + data['tipo'] + '"]').prop("checked", true);
    $('[name="tipofinanciamiento' + data['id'] + '"]').attr("disabled", false);
    $('#contenedorActividad' + data['id']).removeClass('d-none');
    $('#contenedorActividad' + data['id']).addClass('d-block');
    window.location.href = '#contenedorActividad' + data['id'];
    $('#actividadBoton' + data['id']).html('MODIFICAR');
    $('#actividadBoton' + data['id']).removeAttr("data-toggle", "modal");
    $('#actividadBoton' + data['id']).removeAttr("data-target", "#eliminarModal");
    banActividad = false;
    banActividadElim = true;
});
//Eliminar Actividad
$(".encabezado").on('click', '.encabezado_4 #elimActividad', function () {
    var data = $(this).data();
    $('#idActividadMod' + data['id']).val(data['idactividad']);
    $('#txtnombreActividad' + data['id']).val(data['nombre']).attr("readonly", "readonly");
    $('#inpresponsable' + data['id']).val(data['responsable']).attr("readonly", "readonly");
    var meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    var fecha1 = fi.split("-");
    var fecha2 = ff.split("-");
    var fia = new Date(fecha1[0], fecha1[1] - 1, fecha1[2]);
    var ffa = new Date(fecha2[0], fecha2[1] - 1, fecha2[2]);
    $('#programacionactividad' + data['id']).empty();
    var j = 1;
    for (var i = 0; i < meses.length; i++) {
        if ((j >= (fia.getMonth() + 1))) {
            if ((j <= (ffa.getMonth() + 1))) {
                $('#programacionactividad' + data['id']).append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes' + data['id'] + '" id="checkmes' + data['id'] + '" disabled="true"><label class="form-check-label"  style="font-weight: normal;">' + meses[i] + '</label></div>');
            }
        }
        j++;
    }

    if (data['mes'].length > 0) {
        for (var k = 0; k < data['mes'].length; k++) {
            $('[name="checkmes' + data['id'] + '"][value="' + data['mes'][k] + '"]').prop("checked", true);
        }

    }
    if (data['cuatri1'] > 0) {
        $('#pori' + data['id']).removeClass('d-none');
        $('#pori' + data['id']).addClass('d-flex');
        $('#porcentajei' + data['id']).val(data['cuatri1']).attr("readonly", "readonly");
    } else {
        $('#pori' + data['id']).removeClass('d-flex');
        $('#pori' + data['id']).addClass('d-none');
        $('#porcentajei' + data['id']).val("").attr("readonly", "readonly");
    }
    if (data['cuatri2'] > 0) {
        $('#porii' + data['id']).removeClass('d-none');
        $('#porii' + data['id']).addClass('d-flex');
        $('#porcentajeii' + data['id']).val(data['cuatri2']).attr("readonly", "readonly");
    } else {
        $('#porii' + data['id']).removeClass('d-flex');
        $('#porii' + data['id']).addClass('d-none');
        $('#porcentajeii' + data['id']).val("").attr("readonly", "readonly");
    }
    if (data['cuatri3'] > 0) {
        $('#poriii' + data['id']).removeClass('d-none');
        $('#poriii' + data['id']).addClass('d-flex');
        $('#porcentajeiii' + data['id']).val(data['cuatri3']).attr("readonly", "readonly");
    } else {
        $('#poriii' + data['id']).removeClass('d-flex');
        $('#poriii' + data['id']).addClass('d-none');
        $('#porcentajeiii' + data['id']).val("").attr("readonly", "readonly");
    }

    $('[name="tipofinanciamiento' + data['id'] + '"][value="' + data['tipo'] + '"]').prop("checked", true);
    $('[name="tipofinanciamiento' + data['id'] + '"]').attr("disabled", "true");
    $('#contenedorActividad' + data['id']).removeClass('d-none');
    $('#contenedorActividad' + data['id']).addClass('d-block');
    $('#actividadBoton' + data['id']).html('ELIMINAR');
    $('#actividadBoton' + data['id']).attr("data-toggle", "modal");
    $('#actividadBoton' + data['id']).attr("data-target", "#eliminarModal");
    //$('#eliminarModalBton').attr("data-tipo", "actividad");
    //$('#eliminarModalBton').attr("data-id", data['id']);
    //$('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar la Actividad ' + data['nombre'] + '<input type="hidden" name="idindicadorelim" id="idactividadelim" value="' + data['idactividad'] + '">');
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar la Actividad ' + data['nombre'] + '<input type="hidden" name="idindicadorelim" id="idactividadelim" value="' + data['idactividad'] + '"><input type="hidden" name="tipom" id="tipom" value="actividad"><input type="hidden" name="idinp" id="idinp" value="' + data['id'] + '">');
    window.location.href = '#contenedorActividad' + data['id'];
    banActividadElim = false;
});
//Modificar Requerimiento
$(".encabezado").on('click', '.encabezado_4 #modReq', function () {
    var data = $(this).data();
    $('#txtnombreReq' + data['id']).val(data['nombre']).removeAttr("readonly");
    $('#txtdescripcionReq' + data['id']).val(data['descripcion']).removeAttr("readonly");
    $('#idrequerimiento' + data['id']).val(data['req']);
    $('#idactividadreq' + data['id']).val(data['actividad']);
    $('#programacionrequerimiento' + data['id']).empty();
    $('#reqverificacion' + data['id']).val(data['verificacion']);
    $.ajax({
        url: "../actividadReq?accion=ListarCuatrimestreActividad" + "&actividad=" + data['actividad'],
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    $('#programacionrequerimiento' + data['id']).append('<label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center pr-4">Porcentajes:</label>');
                    if (this.c1 > 0) {
                        if (data['po1'] > 0) {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="1" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '" checked>I</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajeri' + data['id'] + '" placeholder="%"></div></div>');
                            if (data['po1'] < 100) {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).attr('type', 'text').removeAttr("readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).val(data['po1']).removeAttr("readonly");
                            } else {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).attr('type', 'hidden').removeAttr("readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).val('').removeAttr("readonly");
                            }
                        } else {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="1" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '">I</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajeri' + data['id'] + '" placeholder="%"></div></div>');
                        }
                    }
                    if (this.c2 > 0) {
                        if (data['po2'] > 0) {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="2" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '" checked>II</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajerii' + data['id'] + '" placeholder="%"></div></div>');
                            if (data['po2'] < 100) {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).attr('type', 'text').removeAttr("readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).val(data['po2']).removeAttr("readonly");
                            } else {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).attr('type', 'hidden').removeAttr("readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).val('').removeAttr("readonly");
                            }
                        } else {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="2" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '">II</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajerii' + data['id'] + '" placeholder="%"></div></div>');
                        }
                    }
                    if (this.c3 > 0) {
                        if (data['po3'] > 0) {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="3" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq' + data['id'] + '" checked>III</div><div class="col-8"><input class="form-control" type="hidden" id="porcentajeriii' + data['id'] + '" placeholder="%" min="0" max="100"></div></div>');
                            if (data['po3'] < 100) {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).attr('type', 'text').removeAttr("readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).val(data['po3']).removeAttr("readonly");
                            } else {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).attr('type', 'hidden').removeAttr("readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).val('').removeAttr("readonly");
                            }
                        } else {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="3" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq' + data['id'] + '">III</div><div class="col-8"><input class="form-control" type="hidden" id="porcentajeriii' + data['id'] + '" placeholder="%" min="0" max="100"></div></div>');
                        }
                    }
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $("#intcantidad" + data['id']).val(data['cantidad']).removeAttr("readonly");
    $("#intcosto" + data['id']).val(data['costou']).removeAttr("readonly");
    $('[name="radioiva' + data['id'] + '"]').attr("disabled", false);
    $('[name="tipoReq' + data['id'] + '"]').attr("disabled", false);
    if (data['iva'] === 1) {
        $('[name="radioiva' + data['id'] + '"][value="1"]').prop("checked", true);
    } else {
        $('[name="radioiva' + data['id'] + '"][value="0"]').prop("checked", true);
    }
    $('#selfinan' + data['id']).attr("disabled", false);
    $('#seltipoc' + data['id']).attr("disabled", false);
    $('#selunidad' + data['id']).attr("disabled", false);
    $('#selectReqeG' + data['id']).attr("disabled", false);
    $('#selfinan' + data['id']).find('option[value="' + data['financiamiento'] + '"]').remove();
    $('#selfinan' + data['id']).append('<option value="' + data['financiamiento'] + '" selected="selected">' + data['financiamientonombre'] + '</option>');
    $('#selfinan' + data['id']).selectpicker('refresh');
    $("#inpcpc" + data['id']).removeAttr("readonly");
    if (data['tc'] > 0) {
        $('[name="tipoReq' + data['id'] + '"][value="1"]').prop("checked", true);
        $('#tipoC' + data['id']).removeClass('d-none');
        $('#seltipoc' + data['id']).find('option[value="' + data['tc'] + '"]').remove();
        $('#seltipoc' + data['id']).append('<option value="' + data['tc'] + '" selected="selected">' + data['tcnombre'] + '</option>');
        $('#seltipoc' + data['id']).selectpicker('refresh');
        $('#unidadM' + data['id']).removeClass('d-none');
        $('#selunidad' + data['id']).find('option[value="' + data['unidad'] + '"]').remove();
        $('#selunidad' + data['id']).append('<option value="' + data['unidad'] + '" selected="selected">' + data['unidadnombre'] + '</option>');
        $('#selunidad' + data['id']).selectpicker('refresh');
        $('#reqgeneral' + data['id']).removeClass('d-none');
        $('#selectReqeG' + data['id']).find('option[value="' + data['rg'] + '"]').remove();
        $('#selectReqeG' + data['id']).append('<option value="' + data['rg'] + '" selected="selected">' + data['rgn'] + '</option>');
        $('#selectReqeG' + data['id']).selectpicker('refresh');
        $("#cpc" + data['id']).removeClass('d-none');
        $("#inpcpc" + data['id']).val(data['cpc']);
        if (data['rg'] > 0) {
            $('#txtnombreReq' + data['id']).attr("readonly", "readonly");
            $('#txtdescripcionReq' + data['id']).attr("readonly", "readonly");
            $('#intcosto' + data['id']).attr("readonly", "readonly");
        } else {
            $('#txtnombreReq' + data['id']).removeAttr("readonly");
            $('#txtdescripcionReq' + data['id']).removeAttr("readonly");
            $('#intcosto' + data['id']).removeAttr("readonly");
        }
    } else {
        $('[name="tipoReq' + data['id'] + '"][value="2"]').prop("checked", true);
        $('#tipoC' + data['id']).addClass('d-none');
        $('#unidadM' + data['id']).addClass('d-none');
        $('#cpc' + data['id']).addClass('d-none');
        $('#reqgeneral' + data['id']).addClass('d-none');
        $('#txtnombreReq' + data['id']).removeAttr("readonly");
        $('#txtdescripcionReq' + data['id']).removeAttr("readonly");
        $('#intcosto' + data['id']).removeAttr("readonly");
    }
    $('#contenedorReqPac' + data['id']).removeClass('d-none');
    $('#contenedorReqPac' + data['id']).removeClass('d-none');
    $('#botonrequerimientog' + data['id']).html('MODIFICAR');
    $('#botonrequerimientog' + data['id']).removeAttr("data-toggle", "modal");
    $('#botonrequerimientog' + data['id']).removeAttr("data-target", "#eliminarModal");
    window.location.href = '#contenedorReqPac' + data['id'];
    banRequerimiento = false;
    banRequerimientoElim = true;
});
//Modificar Requerimiento
$(".encabezado").on('click', '.encabezado_4 #modReqAnio', function () {
    var data = $(this).data();
    $('#idrequerimientoAn' + data['id']).val(data['req']);
    $('#nombrereqTit' + data['id']).html(data['nombre']);
    $('#contenedorReqMod' + data['id']).removeClass('d-none');
    window.location.href = '#contenedorReqMod' + data['id'];
    banAnio = true;
});
//Eliminar Requerimiento
$(".encabezado").on('click', '.encabezado_4 #elimRequerimiento', function () {
    var data = $(this).data();
    $('#txtnombreReq' + data['id']).val(data['nombre']).attr("readonly", "readonly");
    $('#txtdescripcionReq' + data['id']).val(data['descripcion']).attr("readonly", "readonly");
    $('#idrequerimiento' + data['id']).val(data['req']);
    $('#idactividadreq' + data['id']).val(data['actividad']);
    $('#programacionrequerimiento' + data['id']).empty();
    $('#reqverificacion' + data['id']).val(data['verificacion']);
    $.ajax({
        url: "../actividadReq?accion=ListarCuatrimestreActividad" + "&actividad=" + data['actividad'],
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    $('#programacionrequerimiento' + data['id']).append('<label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center pr-4">Porcentajes:</label>');
                    if (this.c1 > 0) {
                        if (data['po1'] > 0) {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="1" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '" disabled checked>I</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajeri' + data['id'] + '" placeholder="%"></div></div>');
                            if (data['po1'] < 100) {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).attr('type', 'text').attr("readonly", "readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).val(data['po1']).attr("readonly", "readonly");
                            } else {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).attr('type', 'hidden').attr("readonly", "readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeri" + data['id']).val('').attr("readonly", "readonly");
                            }
                        } else {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="1" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '" disabled>I</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajeri' + data['id'] + '" placeholder="%"></div></div>');
                        }
                    }
                    if (this.c2 > 0) {
                        if (data['po2'] > 0) {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="2" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '" disabled checked>II</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajerii' + data['id'] + '" placeholder="%"></div></div>');
                            if (data['po2'] < 100) {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).attr('type', 'text').attr("readonly", "readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).val(data['po2']).attr("readonly", "readonly");
                            } else {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).attr('type', 'hidden').attr("readonly", "readonly");
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajerii" + data['id']).val('').attr("readonly", "readonly");
                            }
                        } else {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="2" name="checkcuatreq' + data['id'] + '" id="checkcuatreq' + data['id'] + '" disabled>II</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajerii' + data['id'] + '" placeholder="%"></div></div>');
                        }
                    }
                    if (this.c3 > 0) {
                        if (data['po3'] > 0) {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="3" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq' + data['id'] + '" disabled checked>III</div><div class="col-8"><input class="form-control" type="hidden" id="porcentajeriii' + data['id'] + '" placeholder="%" min="0" max="100"></div></div>');
                            if (data['po3'] < 100) {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).attr('type', 'text');
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).val(data['po3']);
                            } else {
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).attr('type', 'hidden');
                                $('#programacionrequerimiento' + data['id']).children('.form-group').children('.col-8').children("#porcentajeriii" + data['id']).val('');
                            }
                        } else {
                            $('#programacionrequerimiento' + data['id']).append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="3" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq' + data['id'] + '" disabled>III</div><div class="col-8"><input class="form-control" type="hidden" id="porcentajeriii' + data['id'] + '" placeholder="%" min="0" max="100"></div></div>');
                        }
                    }
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $("#intcantidad" + data['id']).val(data['cantidad']).attr("readonly", "readonly");
    $("#intcosto" + data['id']).val(data['costou']).attr("readonly", "readonly");
    $('[name="radioiva' + data['id'] + '"]').attr("disabled", true);
    if (data['iva'] === 1) {
        $('[name="radioiva' + data['id'] + '"][value="1"]').prop("checked", true);
    } else {
        $('[name="radioiva' + data['id'] + '"][value="0"]').prop("checked", true);
    }
    $('#selfinan' + data['id']).attr("disabled", true);
    $('#selfinan' + data['id']).find('option[value="' + data['financiamiento'] + '"]').remove();
    $('#selfinan' + data['id']).append('<option value="' + data['financiamiento'] + '" selected="selected">' + data['financiamientonombre'] + '</option>');
    $('#selfinan' + data['id']).selectpicker('refresh');
    $('[name="tipoReq' + data['id'] + '"]').attr("disabled", true);
    $('#seltipoc' + data['id']).attr("disabled", true);
    $('#selunidad' + data['id']).attr("disabled", true);
    $('#selectReqeG' + data['id']).attr("disabled", true);
    if (data['tc'] > 0) {
        $('[name="tipoReq' + data['id'] + '"][value="1"]').prop("checked", true);
        $('#tipoC' + data['id']).removeClass('d-none');
        $('#seltipoc' + data['id']).find('option[value="' + data['tc'] + '"]').remove();
        $('#seltipoc' + data['id']).append('<option value="' + data['tc'] + '" selected="selected">' + data['tcnombre'] + '</option>');
        $('#seltipoc' + data['id']).selectpicker('refresh');
        $('#unidadM' + data['id']).removeClass('d-none');
        $('#selunidad' + data['id']).find('option[value="' + data['unidad'] + '"]').remove();
        $('#selunidad' + data['id']).append('<option value="' + data['unidad'] + '" selected="selected">' + data['unidadnombre'] + '</option>');
        $('#selunidad' + data['id']).selectpicker('refresh');
        $('#reqgeneral' + data['id']).removeClass('d-none');
        $('#selectReqeG' + data['id']).find('option[value="' + data['rg'] + '"]').remove();
        $('#selectReqeG' + data['id']).append('<option value="' + data['rg'] + '" selected="selected">' + data['rgn'] + '</option>');
        $('#selectReqeG' + data['id']).selectpicker('refresh');
        $("#cpc" + data['id']).removeClass('d-none');
        $("#inpcpc" + data['id']).val(data['cpc']).attr("readonly", "readonly");
        if (data['rg'] > 0) {
            $('#txtnombreReq' + data['id']).attr("readonly", "readonly");
            $('#txtdescripcionReq' + data['id']).attr("readonly", "readonly");
            $('#intcosto' + data['id']).attr("readonly", "readonly");
        }
    } else {
        $('[name="tipoReq' + data['id'] + '"][value="2"]').prop("checked", true);
        $('#tipoC' + data['id']).addClass('d-none');
        $('#unidadM' + data['id']).addClass('d-none');
        $('#cpc' + data['id']).addClass('d-none');
        $('#reqgeneral' + data['id']).addClass('d-none');
    }
    $('#contenedorReqPac' + data['id']).removeClass('d-none');
    $('#botonrequerimientog' + data['id']).html('ELIMINAR');
    $('#botonrequerimientog' + data['id']).attr("data-toggle", "modal");
    $('#botonrequerimientog' + data['id']).attr("data-target", "#eliminarModal");
    //$('#eliminarModalBton').removeProp("data-tipo");
    //$('#eliminarModalBton').attr("data-tipo", "requerimiento");
    //$('#eliminarModalBton').attr("data-id", data['id']);
    //$('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el requerimiento ' + data['nombre'] + '<input type="hidden" name="idrequerimientoelim" id="idrequerimientoelim" value="' + data['req'] + '">');
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el requerimiento ' + data['nombre'] + '<input type="hidden" name="idrequerimientoelim" id="idrequerimientoelim" value="' + data['req'] + '"><input type="hidden" name="tipom" id="tipom" value="requerimiento"><input type="hidden" name="idinp" id="idinp" value="' + data['id'] + '">');
    window.location.href = '#contenedorReqPac' + data['id'];
    banRequerimientoElim = false;
});
//Eliminar Proyecto
$("#btn_proyecto_eliminar").on('click', function (event) {
    event.preventDefault();
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el proyecto<input type="hidden" name="tipom" id="tipom" value="proyecto">');
});
//Eliminar 
$('#eliminarModalBton').on('click', function () {
    let tipo = $('#tipom').val();
    let id = $('#idinp').val();
    if (tipo === "indicador") {
        $.ajax({
            url: "../componenteMeta?accion=EliminarIndicador",
            type: 'POST',
            data: {idIndicador: $("#idindicadorelim").val(), cedulaProyecto: $('#cedulaProyecto').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listarIndicador($("#idMeta" + id).val(), id);
                        $("#contenedorIndicador" + id).removeClass('d-none');
                        $('#eliminarModal').modal('toggle');
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
    } else if (tipo === "actividad") {
        $.ajax({
            url: "../actividadReq?accion=EliminarActividad",
            type: 'POST',
            data: {idactividad: $("#idactividadelim").val(), cedulaProyecto: $('#cedulaProyecto').val(), componente: $('#idComponente' + id).val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response.result === "Correcto") {
                        $('#contenedorActividad' + id).removeClass('d-block');
                        $('#contenedorActividad' + id).addClass('d-none');
                        $("#contenedorActividad" + id)[0].reset();
                        $('#montoF').html(new Intl.NumberFormat("US", formateador()).format(response.monto));
                        listaActividad($('#idComponente' + id).val(), id, long);
                        listaRequerimiento($('#idComponente' + id).val(), id);
                        $('#eliminarModal').modal('toggle');
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
    } else if (tipo === "requerimiento") {
        $.ajax({
            url: "../actividadReq?accion=EliminarRequerimiento",
            type: 'POST',
            data: {idrequerimiento: $("#idrequerimientoelim").val(), cedulaProyecto: $('#cedulaProyecto').val(), componente: $('#idComponente' + id).val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response.result === "Correcto") {
                        if (response.result === "Correcto") {
                            $('#montoF').html(new Intl.NumberFormat("US", formateador()).format(response.monto));
                            listaActividad($('#idComponente' + id).val(), id, long);
                            listaRequerimiento($('#idComponente' + id).val(), id);
                            $('#contenedorReqPac' + id).removeClass('d-block');
                            $('#contenedorReqPac' + id).addClass('d-none');
                            $('#eliminarModal').modal('toggle');
                        } else {
                            alert(response.result);
                        }
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
    } else if (tipo === "proyecto") {
        $.ajax({
            url: "../proyecto?accion=EliminarProyecto",
            type: 'POST',
            data: {idproyecto: $("#idProy").val(), cedulaProyecto: $('#cedulaProyecto').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.href = "pListaProyectos.jsp";
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
    } else if (tipo === "componente") {
        $.ajax({
            url: "../componenteMeta?accion=EliminarComponente",
            type: 'POST',
            data: {idcomponente: $("#idcompelim").val(), cedulaProyecto: $('#cedulaProyecto').val()},
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
    } else if (tipo === "articulacion") {
        $.ajax({
            url: "../proyecto?accion=EliminarActividadProceso",
            type: 'POST',
            data: {actividad: $('#idactividadart').val(), proyecto: $('#idProy').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listaProcesosAr();
                        $('#eliminarModal').modal('toggle');
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
$('#btn_proyecto_enviar').on('click', function (event) {
    event.preventDefault();
    let proy;
    if ($('#tipousuario').val() === "15") {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 1;
    } else if (($('#tipousuario').val() === "2" || $('#tipousuario').val() === "5" || $('#tipousuario').val() === "7" || $('#tipousuario').val() === "8") && (estadof === 0 || estadof === 5 || estadof === 7 || estadof === 12 || estadof === 13 || estadof === 14 || estadof === 20 || estadof === 25 || estadof === 26)) {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 2;
    } else if ($('#tipousuario').val() === "3") {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 8;
    } else if ($('#tipousuario').val() === "19") {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 27;
    } else if (($('#tipousuario').val() === "2" || $('#tipousuario').val() === "5" || $('#tipousuario').val() === "7" || $('#tipousuario').val() === "8") && estadof === 1) {
        $('#aprobarRadios').val(2);
        $('#modificarRadios').val(3);
    } else if (($('#tipousuario').val() === "5") && (estadof === 1 || estadof === 2 || estadof === 8)) {
        $('#aprobarRadios').val(2);
        $('#modificarRadios').val(3);
    } else if ($('#tipousuario').val() === "11") {
        $('#aprobarRadios').val(15);
        $('#modificarRadios').val(14);
    } else if ($('#tipousuario').val() === "26") {
        $('#aprobarRadios').val(52);
    } else if ($('#tipousuario').val() === "1") {
        $('#aprobarRadios').val(17);
        $('#modificarRadios').val(14);
    }

    $('#estadoproy').val(proy);
});
$('#enviarModalBton').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EnviarProyecto",
        type: 'POST',
        data: {estado: $("#estadoproy").val(), cedulaProyecto: $('#cedulaProyecto').val(), proyecto: $('#idProy').val(), observacion: "Sin observacion", tipou: $('#tipousuario').val()},
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
        url: "../proyecto?accion=EnviarProyecto",
        type: 'POST',
        data: {estado: $('input:radio[name=verificarRadios]:checked').val(), cedulaProyecto: $('#cedulaProyecto').val(), proyecto: $('#idProy').val(), observacion: $('#observacionEnviar').val(), tipou: $('#tipousuario').val()},
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
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict';
    window.addEventListener('load', function () {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function (form) {
            form.addEventListener('submit', function (event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                } else {
                    //alert('validado');
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();