let proy, mul, fi, ff, long, banIndicador = true, banActividad = true, banRequerimiento = true, estadof, proynombre, persp, banIndicaroEl = true, banActividadElim = true, banRequerimientoElim = true;
let tusu = $('#tipousuario').val();
let tiempo = $('#fechacierre').val();
let m, prio, banAnio = false, tp;
var options2 = {style: "currency", currency: "USD"};

$('#selectcuatrimestre').on('change', function () {
    $('#cuatrimestreeval').val($('#selectcuatrimestre').val());
    $('#cuatrieval').val($('#selectcuatrimestre').val());
    listaProyectosRep($("#idProy").val(), $('#selectcuatrimestre').val());
});

$(document).ready(function () {
    var proye = $("#idProy").val(), cuatrimestre = $('#cuatrimestreeval').val();
    listaProyectosRep(proye, cuatrimestre);
});

function listaProyectosRep(proye, cuatrimestre) {
    $.ajax({
        url: "../proyecto?accion=ListarProyectoCompEvaluacion",
        data: {proy: proye, cuatrimestre: cuatrimestre},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var estado, cuatri, perfil;
                $('#fechaestados').empty();
                $.each(response, function () {
                    proy = this.proyecto_id;
                    estado = this.estado_id;
                    cuatri = this.tp_id;
                    $('#modNombreProy').html(this.proyecto_nombre);
                    $('#modMontoProy').html(new Intl.NumberFormat("US", options2).format(this.proyecto_monto));
                    tp = this.pp_id;
                    if (tp !== 2 && tp !== 3) {
                        $('#perfilInV').addClass('d-none');
                        $('#perfilInV').removeClass('d-block');
                    } else {
                        $('#perfilInV').addClass('d-block');
                        $('#perfilInV').removeClass('d-none');
                    }
                    if (this.proyecto_proceso == null) {
                        perfil = '<a href="https://planificacion.espoch.edu.ec/sip/formulacion/docs/' + this.proyecto_doc + '" download="' + this.proyecto_doc + '">' + this.proyecto_doc + '</a>';
                    } else {
                        perfil = '<a href="https://planificacion.espoch.edu.ec/sip/formulacion/docs/' + this.proyecto_proceso + '" download="' + this.proyecto_proceso + '">' + this.proyecto_proceso + '</a>';
                    }
                    proynombre = this.proyecto_nombre;
                    $('#perfilF').html(perfil);
                    $('#tituloAg').html(this.ag.ag_nombre);
                    $('#finProyectoF').html(this.proyecto_fin);
                    $('#propositoF').html(this.proyecto_proposito);
                    $('#responsableF').html(this.proyecto_responsable);
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
                if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                    $('#btn_proyecto_modificar').css({"display": "flex"});
                    $("#btn_proyecto_enviar").attr("data-target", "#enviarModal");
                } else if ((tusu === "14" || tusu === "16" || tusu === "17") && estadof === 44) {
                    $('#btn_proyecto_enviar').css({"display": "flex"});
                    $('#btn_proyecto_modificar').addClass('d-none');
                    $("#btn_proyecto_enviar").attr("data-target", "#enviarModalVer");
                } else {
                    $('#btn_proyecto_enviar').addClass('d-none');
                    $('#btn_proyecto_modificar').addClass('d-none');
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

$('#btn_proyecto_modificar').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=ListarFecha",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#fiproy').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
                $('#ffproy').datepicker({
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

    $('#fiproy').val(fi);
    $('#ffproy').val(ff);
    if (tp === 2 || tp === 3) {
        $('#repperfil').addClass('d-block');
        $('#repperfil').removeClass('d-none');
    } else {
        $('#repperfil').addClass('d-none');
        $('#repperfil').removeClass('d-block');
    }
    $('#generarJ').modal();
});

$('#modalGuardarJust').on('click', function () {
    var pro = $("#idProy").val();
    var archivo = document.getElementById("perfrep");
    var file = archivo.files[0];
    if (file == null) {
        $.ajax({
            url: "../proyecto?accion=ModificarProyectoRep",
            type: 'POST',
            dataType: 'json',
            cache: false,
            processData: false,
            data: new FormData($('#frmAddProyecto')[0]),
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
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    } else {
        var doc = document.getElementById('perfrep').files[0].name;
        if (comprueba_extensionR(doc, "perfrep") === 1) {
            $.ajax({
                url: "../proyecto?accion=ModificarProyectoRep",
                type: 'POST',
                dataType: 'json',
                cache: false,
                processData: false,
                data: new FormData($('#frmAddProyecto')[0]),
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
                        console.log('No existe conexión con la base de datos.');
                    })
                    .always(function () {
                        console.log('Se completo correctamente');
                    });
        }
    }
});

function comprueba_extensionR(archivo, nombre) {
    $('#' + nombre).empty();
    var extensiones_permitidas = new Array(".pdf");
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
        alert("Compruebe la extensión de los archivos a subir.");
    } else {
        return 1;
    }
    return 0;
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
    } else {
        $('#agregaC').addClass('d-none');
    }
});

//Listar componente
function listarComponente(proy) {
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
                            $("#idComponente" + i).val(this.componente_id);
                            $("#inputComp" + i).html('<div class="row"><div class="col-12 col-xs-12 col-sm-3 col-md-2 titulopes text-justify">Componente:</div><div class="col-12 col-xs-12 col-sm-9 col-md-10 text-justify">' + this.componente_nombre + '</div></div>');
                            if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
                                $('#componente' + i).children('.row').children("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '" data-componenteidid="' + this.componente_proyecto + '"></i>');
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
                            if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
                                if (sm < 2) {
                                    $("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '" data-componenteidid="' + this.componente_proyecto + '" title="Editar componente"></i>');
                                } else {
                                    $("#btn_comp" + i).html('<i class="fas fa-edit" id="btn_proyecto_componente_modificar" data-id="' + i + '" data-idcomponente="' + this.componente_id + '" data-componente="' + this.componente_nombre + '" data-componenteidid="' + this.componente_proyecto + '" title="Editar componente"></i>');
                                }
                            } else {
                                $('#agregaC').addClass('d-none');
                                $("#btn_comp" + i).empty();
                            }
                            listaMeta(this.componente_id, i);
                            long = response.length;
                            listaActividad(this.componente_id, i, long);
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
                if (response.length === 2) {
                    $("#pestania2").removeClass('d-none');
                } else if (response.length === 3) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                } else if (response.length === 4) {
                    $("#pestania2").removeClass('d-none');
                    $("#pestania3").removeClass('d-none');
                    $("#pestania4").removeClass('d-none');
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
                        if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
                            $("#btn_meta" + i).html('<i class="fas fa-edit" id="btn_proyecto_meta_modificar" data-id="' + i + '" data-meta="' + this.meta_nombre + '" data-idmeta="' + this.meta_id + '" data-metaidid="' + this.componente_id + '" data-comp="' + comp + '" title="Editar meta"></i>');
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
                    if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
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
                        if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
                            $('#lisIndicador' + i).append('<div class="col-12 col-xs-12 col-sm-12 col-md-2 titulopes text-justify">' + indic + '</div><div class="col-12 col-xs-12 col-sm-12 col-md-10 text-justify"><div class="row" id="rowindicador"><li class="col-12 col-sm-11 p-0 text-justify">' + this.indicador_nombre + '</li><div class="col-12 col-sm-1 p-0 text-end" id="btnBotonIndicador' + i + '"><i class="fas fa-edit" title="Editar Indicador" id="modIndicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" \n\
                data-nombre="' + this.indicador_nombre + '" data-descripcion="' + this.indicador_descripcion + '" data-tipo="' + this.indicador_tipo + '" data-ejecutado="' + this.indicador_ejecutado + '" data-planificado="' + this.indicador_planificado + '" data-valor="' + this.indicador_numero + '" data-indicadoridid="' + this.componente_id + '"></i><i class="fas fa-trash-alt" id="elimIndicador" title="Eliminar indicador" data-id="' + i + '" data-idindicador="' + this.indicador_id + '" data-indicadoridid="' + this.componente_id + '" \n\
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
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '" data-actividadidid="' + this.ae_tiempo + '" data-monto="' + this.actividad_monto + '" data-prioridad="' + this.actividad_prioridad + '"></i><i class="fas fa-trash-alt" id="elimActividad" title="Eliminar actividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '" data-actividadidid="' + this.ae_tiempo + '"  data-monto="' + this.actividad_monto + '" data-prioridad="' + this.actividad_prioridad + '"></i>';
                        } else {
                            monto = new Intl.NumberFormat("US", formateador()).format(this.actividad_monto);
                            div = '<i class="fas fa-edit" title="Editar Actividad" id="modActividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '" data-actividadidid="' + this.ae_tiempo + '" data-monto="' + this.actividad_monto + '" data-prioridad="' + this.actividad_prioridad + '"></i><i class="fas fa-trash-alt" id="elimActividad" title="Eliminar actividad" data-idactividad="' + this.actividad_id + '" data-id="' + i + '"\n\
                                data-nombre="' + this.actividad_nombre + '" data-responsable="' + this.actividad_responsable + '" data-tipo="' + this.actividad_tipo + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '" data-actividadidid="' + this.ae_tiempo + '" data-monto="' + this.actividad_monto + '" data-prioridad="' + this.actividad_prioridad + '"></i>';
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
                        if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
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
$('.col-1').on('click', '#ingresarMeta', function () {
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
$('.tipoIndicador').on('change', function () {
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
$('.tipoRequerimiento').on('change', function () {
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
$('.contenedorIndicador').submit(function (event) {
    event.preventDefault();
    var data = $(this).data();
    if (banIndicaroEl) {
        if (banIndicador) {
            $.ajax({
                url: "../componenteMeta?accion=IngresarIndicadorRep",
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
                url: "../componenteMeta?accion=IngresarIndicadorReprog",
                type: 'POST',
                data: {idIndicador: $("#idIndicador" + data['id']).val(), txtnombreIndicador: $("#txtnombreIndicador" + data['id']).val(),
                    txtdescipcionIndicador: $("#txtdescipcionIndicador" + data['id']).val(), tipoIndicador: $('input:radio[name=tipoIndicador' + data['id'] + ']:checked').val(),
                    txtindicadorejecutadovalor: $("#txtindicadorejecutadovalor" + data['id']).val(), txtindicadorplanificadovalor: $("#txtindicadorplanificadovalor" + data['id']).val(),
                    txtindicadorplanificado: $("#txtindicadorplanificado" + data['id']).val(), valorIndicador: $('input:radio[name=valorIndicador' + data['id'] + ']:checked').val(), cedulaProyecto: $('#cedulaProyecto').val(), ididIndicador: $('#ididIndicador' + data['id']).val()},
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

$('.cancelarIndicador').on('click', function () {
    var data = $(this).data();
    $('#contenedorIndicador' + data['id']).removeClass('d-block');
    $('#contenedorIndicador' + data['id']).addClass('d-none');
});

$('.cancelarActividad').on('click', function (event) {
    event.preventDefault();
    var data = $(this).data();
    $('#contenedorActividad' + data['id']).removeClass('d-block');
    $('#contenedorActividad' + data['id']).addClass('d-none');
});

$('.cancelarRequerimiento').on('click', function (event) {
    event.preventDefault();
    var data = $(this).data();
    $('#contenedorReqPac' + data['id']).removeClass('d-block');
    $('#contenedorReqPac' + data['id']).addClass('d-none');
});

//Mostrar contenedor de indicador
$('.btn_proyecto_agregar_indicador').on('click', function () {
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
$('.btn_indicador_detalle').on('click', function () {
    var data = $(this).data();
    $('#listaIndicadorTabla' + data['id']).slideToggle();
});

//Mostrar contenedor de actividad 
$(".btn_proyecto_actividad").on('click', function () {
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

$('.btn_proyecto_agregar_indicador').on('click', function () {
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

$('.btn_indicador_detalle').on('click', function () {
    var data = $(this).data();
    //$('#listaIndicadorTabla' + data['id']).slideToggle();
    if ($('#listaIndicadorTabla' + data['id']).hasClass('d-none')) {
        $('#listaIndicadorTabla' + data['id']).removeClass('d-none');
    } else {
        $('#listaIndicadorTabla' + data['id']).addClass('d-none');
    }
});

$(".btn_proyecto_actividad").on('click', function () {
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
$('.mesactividad').on('change', function () {
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
$('.contenedorActividad').submit(function (event) {
    let data = $(this).data();
    event.preventDefault();
    let metodo = $(this).attr("method");
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
                url: "../actividadReq?accion=IngresarActividadRep",
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
                url: "../actividadReq?accion=IngresarActividadReprog",
                type: metodo,
                data: {cuatrimestre1: c1, cuatrimestre2: c2, cuatrimestre3: c3, nombre: $('#txtnombreActividad' + data['id']).val(), responsable: $('#inpresponsable' + data['id']).val(),
                    porcentaje1: $('#porcentajei' + data['id']).val(), porcentaje2: $('#porcentajeii' + data['id']).val(), porcentaje3: $('#porcentajeiii' + data['id']).val(),
                    tipo: $('input:radio[name=tipofinanciamiento' + data['id'] + ']:checked').val(), actividad: $('#idActividadMod' + data['id']).val(), cedula: $('#cedulaProyecto').val(), idActividadModid: $('#idActividadModid' + data['id']).val(), monto: $('#montoActividad' + data['id']).val(), prioridad: $('#prioridadActividad' + data['id']).val(), cuatrimestre:$('#selectcuatrimestre').val(), cuatrimestrecod: $('#cuatrimestreeval').val()},
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
                } else {
                    $('#perfilInV').removeClass('d-block');
                    $('#perfilInV').addClass('d-none');
                    $('#integrantesInV').removeClass('d-block');
                    $('#integrantesInV').addClass('d-none');
                    $('#multiSelec').removeClass('d-block');
                    $('#multiSelec').addClass('d-none');
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
$('.tablaover').on('click', '.encabezado .encabezado_4 #agregarReq', function () {
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
});

//agregar y modificar requerimientos
$('.contenedorRequerimientos').submit(function (event) {
    event.preventDefault();
    let data = $(this).data();
    let cuat = [];
    let unidad, tipoc, cpc;
    if (banRequerimientoElim && !banAnio) {
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
                url: "../actividadReq?accion=ModificarRequerimientoRep",
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
                            $('#contenedorReqPac' + data['id']).removeClass('d-block');
                            $('#contenedorReqPac' + data['id']).addClass('d-none');
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
        url: "../actividadReq?accion=ListarRequerimientoRep" + "&comp=" + comp + "&tipo=2",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                let  total = 0, color = 'rgba(0, 0, 0, 0.05);';
                $("#listaRequerimiento" + i).empty();
                if (response.length > 0) {
                    $('#listadoRequerimiento' + i).removeClass('d-none');
                    $('#contenedorReqPac' + i)[0].reset();
                    var id, num = 1, num = 0, id2;
                    $.each(response, function () {
                        id = this.actividad_id;
                        id2 = this.actividad_componente;
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
                                if (((tusu === "15" || tusu === "3" || tusu === "2" || tusu === "5") && (estadof === 43 || estadof === 46)) || (tusu === "14" && estadof === 42)) {
                                    if (r.tc_id < 1) {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-actividadid="' + id2 + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                               <i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i>';
                                    } else {
                                        pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-actividadid="' + id2 + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '"></i>\n\
                                <i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + r.req_id + '"></i>';
                                    }
                                } else {
                                    if (tusu === "3" && (estadof === 6 || estadof === 17) && prio === 1) {
                                        pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="fas fa-edit" title="Editar Requerimiento" id="modReqAnio" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '"></i>';
                                    } else {
                                        if (r.tc_id < 1) {
                                            if (estadof === 16 || estadof === 17 || estadof === 18) {
                                                if (r.req_prioridad === 1) {
                                                    pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="fas fa-check"></i>';
                                                } else {
                                                    pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="fas fa-times"></i>';
                                                }
                                            } else {
                                                pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i>';
                                            }
                                        } else {
                                            if (r.req_verificacion === 2) {
                                                pac = '<i class="fas fa-edit" title="Editar Requerimiento" id="modReq" data-id="' + i + '" data-req="' + r.req_id + '" data-nombre="' + r.req_nombre + '" data-descripcion="' + r.req_descripcion + '" data-costou="' + r.req_costo_unitario + '" data-cantidad="' + r.req_cantidad + '" data-financiamiento="' + r.financiamiento_id + '"\n\
                                                data-financiamientonombre="' + r.financiamiento_nombre + '" data-tc="' + r.tc_id + '" data-tcnombre="' + r.tc_nombre + '" data-unidad="' + r.unidad_id + '" data-unidadnombre="' + r.unidad_nombre + '" data-cpc="' + r.req_cpc + '" data-po1="' + po1 + '" data-po2="' + po2 + '" data-po3="' + po3 + '" data-actividad="' + id + '" data-actividadid="' + id2 + '" data-iva="' + r.req_iva + '" data-rg="' + r.req_rg + '" data-rgn="' + rg + '" data-verificacion="3"></i>\n\
                                               \n\<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + r.req_id + '"></i>';
                                            } else {
                                                if (estadof === 16 || estadof === 17 || estadof === 18) {
                                                    if (r.req_prioridad === 1) {
                                                        pac = '<i class="far fa-eye" title="Ver Requerimientos" id="verReq" data-valor="' + i + '" data-req="' + this.req_id + '"></i><i class="far fa-paper-plane" id="verestadosreq" data-req="' + this.req_id + '"></i><i class="fas fa-check"></i>';
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
                                <div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + r.req_cantidad + '</div><div class="estilobody encabezado_4 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_unitario) + '</div>\n\
                                <div class="estilobody encabezado_5 centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_sin_iva) + '</div><div class="encabezado_5 estilobody centro" style="background-color:' + color + '">' + new Intl.NumberFormat("US", formateador()).format(r.req_costo_total) + '</div>\n\
                                <div class="estilobody encabezado_4" style="background-color:' + color + '" id="accion' + r.req_id + i + '">' + pac + '</div>\n\
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

//Modificar componente
$(".row").on('click', '#btn_proyecto_componente_modificar', function () {
    var data = $(this).data();
    $("#inputComp" + data['id']).html('<input class="form-control" type="text" id="componenteF' + data['id'] + '" name="componenteF' + data['id'] + '" placeholder="Ingresar componente" value="' + data['componente'] + '"><input type="hidden" name="idComponente' + data['id'] + '" id="idComponente' + data['id'] + '" value="' + data['idcomponente'] + '">');
    $("#btn_comp" + data['id']).html('<i class="far fa-save" id="btn_modificar_componente" data-id="' + data['id'] + '" data-idcomponente="' + data['idcomponente'] + '" data-componenteidid="' + data['componenteidid'] + '"></i>');
});

$('.col-1').on('click', '#btn_modificar_componente', function () {
    var data = $(this).data();
    $.ajax({
        url: "../componenteMeta?accion=IngresarComponenteRep",
        type: 'POST',
        data: {idComponente: data['idcomponente'], txtnombreComp: $('#componenteF' + data['id']).val(), cedulaProyecto: $('#cedulaProyecto').val(), componenteidid: data['componenteidid']},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    listarComponente(proy);
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
    $("#btn_meta" + data['id']).html('<i class="far fa-save" id="btn_modificar_meta" data-id="' + data['id'] + '" data-idmeta="' + data['idmeta'] + '" data-comp="' + data['comp'] + '" data-metaidid="' + data['metaidid'] + '"></i>');
});

$('.col-1').on('click', '#btn_modificar_meta', function () {
    var data = $(this).data();
    $.ajax({
        url: "../componenteMeta?accion=IngresarMetaRep",
        type: 'POST',
        data: {idMeta: data['idmeta'], txtnombreMeta: $('#metaF' + data['id']).val(), cedulaProyecto: $('#cedulaProyecto').val(), metaidid: data['metaidid']},
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
    $('#ididIndicador' + data['id']).val(data['indicadoridid']);
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
    $('#ididIndicador' + data['id']).val(data['indicadoridid']);
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
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar el Indicador ' + data['nombre'] + '<input type="hidden" name="idindicadorelim" id="idindicadorelim" value="' + data['idindicador'] + '"><input type="hidden" name="tipom" id="tipom" value="indicador"><input type="hidden" name="idinp" id="idinp" value="' + data['id'] + '"><input type="hidden" name="ididindi" id="ididindi" value="' + data['indicadoridid'] + '">');
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
$(".encabezado").on('click', '.encabezado_4 #modActividad', function () {
    var data = $(this).data();
    $('#idActividadMod' + data['id']).val(data['idactividad']);
    $('#idActividadModid' + data['id']).val(data['actividadidid']);
    $('#montoActividad' + data['id']).val(data['monto']);
    $('#prioridadActividad' + data['id']).val(data['prioridad']);
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
    $('[name="tipofinanciamiento' + data['id'] + '"]').attr("disabled", true);
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
    $('#idActividadModid' + data['id']).val(data['actividadidid']);
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
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('Esta seguro que desea eliminar la Actividad ' + data['nombre'] + '<input type="hidden" name="idindicadorelim" id="idactividadelim" value="' + data['idactividad'] + '"><input type="hidden" name="tipom" id="tipom" value="actividad"><input type="hidden" name="idinp" id="idinp" value="' + data['id'] + '"><input type="hidden" name="idactividadid" id="idactividadid" value="' + data['actividadidid'] + '">');
    window.location.href = '#contenedorActividad' + data['id'];
    banActividadElim = false;
});

//Modificar Requerimiento
$(".encabezado").on('click', '.encabezado_4 #modReq', function () {
    var data = $(this).data(), id;
    $('#txtnombreReq' + data['id']).val(data['nombre']).removeAttr("readonly");
    $('#txtdescripcionReq' + data['id']).val(data['descripcion']).removeAttr("readonly");
    $('#idrequerimiento' + data['id']).val(data['req']);
    $('#idactividadreq' + data['id']).val(data['actividad']);
    $('#programacionrequerimiento' + data['id']).empty();
    $('#reqverificacion' + data['id']).val(data['verificacion']);
    if (data['actividadid'] === 0) {
        id = data['actividad'];
    } else {
        id = data['actividadid'];
    }
    $.ajax({
        url: "../actividadReq?accion=ListarCuatrimestreActividad" + "&actividad=" + id,
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

    $('#txtnombreReq' + data['id']).attr("readonly", "readonly");
    $('#txtdescripcionReq' + data['id']).attr("readonly", "readonly");
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
            url: "../componenteMeta?accion=EliminarIndicadorRep",
            type: 'POST',
            data: {idIndicador: $("#idindicadorelim").val(), cedulaProyecto: $('#cedulaProyecto').val(), ididIndicador: $('#ididindi').val()},
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
            url: "../actividadReq?accion=EliminarActividadRep",
            type: 'POST',
            data: {idactividad: $("#idactividadelim").val(), cedulaProyecto: $('#cedulaProyecto').val(), componente: $('#idComponente' + id).val(), idActividadModid: $('#idactividadid').val()},
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
                        alert(response.result);
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
    }
});

$('#btn_proyecto_enviar').on('click', function (event) {
    event.preventDefault();
    let proy;
    if ($('#tipousuario').val() === "15" || $('#tipousuario').val() === "2" || $('#tipousuario').val() === "3" || $('#tipousuario').val() === "5" || $('#tipousuario').val() === "7" || $('#tipousuario').val() === "8" || $('#tipousuario').val() === "19") {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 44;
    } else if ($('#tipousuario').val() === "14" && estadof !== 44) {
        $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar a guardar el proyecto?<input type="hidden" name="estadoproy" id="estadoproy">');
        proy = 45;
    } else if (($('#tipousuario').val() === "14" || $('#tipousuario').val() === "17" || $('#tipousuario').val() === "16") && estadof === 44) {
        $('#enviarModalVer').children('.modal-dialog').children('.modal-content').children('.modal-body').children('#frmenpro').children('.form-check').children('#aprobarRadiosR').val(45);
        $('#enviarModalVer').children('.modal-dialog').children('.modal-content').children('.modal-body').children('#frmenpro').children('.form-check').children('#modificarRadiosR').val(46);
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