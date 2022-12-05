/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var options2 = {style: "currency", currency: "USD"};
var banReforma;

$('#frmAddUsuarioCompras').submit(function (event) {
    event.preventDefault();
    var metodo = $(this).attr("method");
    var accion = $(this).attr("action");
    $.ajax({
        url: accion,
        type: metodo,
        dataType: 'json',
        cache: false,
        data: $('#frmAddUsuarioCompras').serialize()
    })
            .done(function (response) {
                //listarRequerimientosGenerales();
                //$("#bandera").val('1');
                if (response === "Correcto") {
                    alert("Se insert\u00f3 correctamente..");
                    $("#frmAddUsuarioCompras")[0].reset();
                    listarUsuarioIDV($('#agu').val());
                } else {
                    alert(response);
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completó correctamente');
            });


});

$('#buscarCod').on('click', function () {
    $('#loader').removeClass('d-none');
    $("#listaRequerimiento").empty();
    var color = 'rgba(0, 0, 0, 0.05);';
    $.ajax({
        url: "../actividadReq?accion=ListarReqReformaID",
        data: {req: $('#codigoreq').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var estado, estado, div, reforma, id, num = 1, unidad, servicio, cpc, tipo, actividad;
                var ejercicio, entidad, unidad_eje, unidad_desc, programa, subprograma, proyecto, actividadp, obra, geografico, renglo, renglo_aux, fuente, organismo, correlativo, presupuesto, presupuestoid;
                $.each(response, function () {
                    var c1 = "0", c2 = "0", c3 = "0";
                    if (this.actividad_id > 0) {
                        actividad = this.actividad_id;
                    }

                    if (this.req_reforma == "0") {
                        reforma = "INICIAL";
                    } else {
                        reforma = "RA-" + this.req_reforma;
                    }

                    id = this.req_id;

                    if (this.cuatri.length > 0) {
                        $.each(this.cuatri, function (indice, cua) {
                            if (cua.mes_id == "1") {
                                if (cua.actividad_porcentaje == null || cua.actividad_porcentaje == "undefined") {
                                    c1 = "0";
                                } else {
                                    c1 = cua.actividad_porcentaje;
                                }
                            } else if (cua.mes_id == "2") {
                                if (cua.actividad_porcentaje == null || cua.actividad_porcentaje == "undefined") {
                                    c2 = "0";
                                } else {
                                    c2 = cua.actividad_porcentaje;
                                }
                            } else if (cua.mes_id == "3") {
                                if (cua.actividad_porcentaje == null || cua.actividad_porcentaje == "undefined") {
                                    c3 = "0";
                                } else {
                                    c3 = cua.actividad_porcentaje;
                                }
                            }
                        });
                    }

                    $.each(this.req, function (indice, pres) {
                        ejercicio = pres.presupuesto_ejercicio;
                        entidad = pres.presupuesto_entidad;
                        unidad_eje = pres.presupuesto_unidad_ejec;
                        unidad_desc = pres.presupuesto_unidad_desc;
                        programa = pres.presupuesto_programa;
                        subprograma = pres.presupuesto_subprograma;
                        proyecto = pres.presupuesto_proyecto;
                        actividadp = pres.presupuesto_actividad;
                        obra = pres.presupuesto_obra;
                        geografico = pres.presupuesto_geografico;
                        renglo = pres.presupuesto_renglo;
                        renglo_aux = pres.presupuesto_renglo_aux;
                        fuente = pres.presupuesto_fuente;
                        organismo = pres.presupuesto_organismo;
                        correlativo = pres.presupuesto_correlativo;
                        presupuesto = pres.presupuesto_presupuesto;
                        presupuestoid = pres.presupuesto_id;
                    });

                    if ($('#codigoreq').val() == this.req_id && this.req_reforma == "0") {
                        div = '<i class="fas fa-eye" id="verDatos" title="Ver datos completos" data-id="' + this.req_id + '"></i><i class="fas fa-list-alt" title="Ver estructura presupuestaria" id="verEstructura" data-id="' + this.req_id + '"></i>';
                    }  else {
                        div = '<i class="fas fa-edit dataInfo" data-id="' + this.req_id + '" data-nombre="' + this.req_nombre + '" data-descripcion="' + this.req_descripcion + '" data-cantidad="' + this.req_cantidad + '" data-costo="' + this.req_costo_unitario + '" data-iva="' + this.req_iva + '" data-reforma="' + this.req_reforma + '"\n\
                        data-financiamientoid="' + this.financiamiento_id + '" data-financiamienton="' + this.financiamiento_nombre + '" data-tc="' + this.tc_id + '" data-tcnombre="' + this.tc_nombre + '" data-unidad="' + this.unidad_id + '" data-unidadnombre="' + this.unidad_nombre + '" data-cpc="' + this.req_cpc + '" data-actividad="' + actividad + '" \n\
                        data-po1="' + c1 + '" data-po2="' + c2 + '" data-po3="' + c3 + '" data-ejercicio="' + ejercicio + '" data-entidad="' + entidad + '" data-unidad_eje="' + unidad_eje + '" data-unidad_desc="' + unidad_desc + '" data-programa="' + programa + '" data-subprograma="' + subprograma + '" data-proyecto="' + proyecto + '" data-actividadp="' + actividadp + '" data-obra="' + obra + '" data-geografico="' + geografico + '" \n\
                        data-renglo="' + renglo + '" data-renglo_aux="' + renglo_aux + '" data-fuente="' + fuente + '" data-organismo="' + organismo + '" data-correlativo="' + correlativo + '" data-presupuesto="' + presupuesto + '" data-presupuestoid="' + presupuestoid + '" data-anio="' + this.req_anio + '" title="Editar reforma" id="editarReforma"></i>\n\
                        <i class="fas fa-eye" id="verDatos" title="Ver datos completos" data-id="' + this.req_id + '"></i><i class="fas fa-list-alt" title="Ver estructura presupuestaria" id="verEstructura" data-id="' + this.req_id + '"></i>\n\
                    <i class="fas fa-trash-alt dataInfo" data-req="' + this.req_id + '" data-nombre="' + this.req_nombre + '" data-reforma="' + this.req_reforma + '" title="Eliminar reforma" id="eliReforma"></i>';
                    }

                    if (this.unidad_id > 0) {
                        unidad = this.unidad_nombre;
                        servicio = this.tc_nombre;
                        cpc = this.req_cpc;
                        tipo = 'PAC';
                    } else {
                        unidad = '---';
                        servicio = '---';
                        cpc = '---';
                        tipo = 'NO PAC';
                    }
                    if (this.req_estado == "2") {
                        estado = "ELIMINADO";
                    } else if (this.req_estado != "2" && (this.actividad_id == null || this.actividad_id == "undefined" || this.actividad_id == "0")) {
                        estado = "MODIFICADO";
                    } else {
                        estado = "INGRESADO";
                    }

                    if (num % 2 === 0) {
                        color = 'rgba(0, 0, 0, 0.05);';
                    } else {
                        color = '#fff';
                    }

                    $("#listaRequerimiento").append('<div class="encabezado_8 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + this.req_id + '</div><div class="encabezado_4 estilobody text-justify" style="background-color:' + color + ';">' + this.req_nombre + '</div><div class="encabezado_5 estilobody text-justify" style="background-color:' + color + ';">' + this.req_descripcion + '</div>\n\
                    <div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>\n\
                    <div class="p-0 encabezado_2"><div class="encabezado_6 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + c1 + '%</div><div class="encabezado_6 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + c2 + '%</div><div class="encabezado_6 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + c3 + '%</div></div><div class="encabezado_4 estilobody text-justify" style="background-color:' + color + '; justify-content:center;">' + reforma + '</div>\n\
                    <div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + estado + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + ';">' + div + '</div><div class="encabezado_completo estilobody text-center" style="background-color:rgba(0,0,0,0.15); display:none" id="datos' + this.req_id + '"></div><div class="encabezado_completo estilobody text-center" style="background-color:rgba(0,0,0,0.15); display:none" id="pres' + this.req_id + '"></div>');

                    $('#datos' + id).append('<div>Cantidad: ' + this.req_cantidad + '</div><div>Costo Uni: ' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div><div>TIPO: ' + tipo + '</div><div>Unidad: ' + unidad + '</div><div>Tipo compra: ' + servicio + '</div><div>CPC: ' + cpc + '</div>');
                    if (this.req.length > 0) {
                        $.each(this.req, function (indice, pres) {
                            $('#pres' + id).append('<div>' + pres.presupuesto_ejercicio + '</div><div>' + pres.presupuesto_entidad + '</div><div>' + pres.presupuesto_unidad_ejec + '</div><div>' + pres.presupuesto_unidad_desc + '</div><div>Programa: ' + pres.presupuesto_programa + '</div><div>Subprograma: ' + pres.presupuesto_subprograma + '</div><div>Proyecto: ' + pres.presupuesto_proyecto + '</div><div>Actividad: ' + pres.presupuesto_actividad + '</div><div>Obra: ' + pres.presupuesto_obra + '</div><div>' + pres.presupuesto_geografico + '</div><div>Renglo: ' + pres.presupuesto_renglo + '</div>\n\
                            <div>Renglo Aux: ' + pres.presupuesto_renglo_aux + '</div><div>Fuente: ' + pres.presupuesto_fuente + '</div><div>Organismo: ' + pres.presupuesto_organismo + '</div><div>Correlativo: ' + pres.presupuesto_correlativo + '</div>');
                        });
                    }
                    num++;
                });
                saldos($('#codigoreq').val());
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

function saldos(id) {
    $('#loader').removeClass('d-none');
    $("#listaRequerimientoS").empty();
    var color = 'rgba(0, 0, 0, 0.05);';
    $.ajax({
        url: "../actividadReq?accion=ListarSaldos",
        data: {req: id},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var div, reforma, num = 1, tipo, total;
                $.each(response, function (indice, saldo) {
                    if ($('#codigoreq').val() == this.req_id && this.req_reforma == "0") {
                        div = '---';
                    } else {
                        div = '<i class="fas fa-edit dataInfo" data-req="' + id + '" data-nombre="' + this.req_nombre + '" data-saldo="' + this.req_costo_unitario + '" data-reforma="' + this.req_reforma + '" title="Editar saldo" id="editarSaldo"></i>\n\
                    <i class="fas fa-trash-alt dataInfo" data-req="' + id + '" data-nombre="' + this.req_nombre + '" data-reforma="' + this.req_reforma + '"  title="Eliminar saldo" id="eliSaldo"></i>';
                    }

                    if (this.req_reforma == "0") {
                        reforma = "INICIAL";
                    } else {
                        reforma = "RA-" + this.req_reforma;
                    }

                    if (this.unidad_id > 0) {
                        tipo = 'PAC';
                    } else {
                        tipo = 'NO PAC';
                    }

                    if (num % 2 === 0) {
                        color = 'rgba(0, 0, 0, 0.05);';
                    } else {
                        color = '#fff';
                    }

                    num++;

                    total = saldo.req_costo_total + saldo.req_costo_unitario;

                    $('#listaRequerimientoS').append('<div class="encabezado_5 estilobody text-justify" style="background-color:' + color + ';">' + saldo.req_nombre + '</div><div class="encabezado_5 estilobody text-justify" style="background-color:' + color + ';">' + saldo.req_descripcion + '</div><div class="encabezado_4 estilobody" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(saldo.req_costo_sin_iva) + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(saldo.req_costo_total) + '</div>\n\
                            <div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + tipo + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(saldo.req_costo_unitario) + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(total) + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">RA-0' + saldo.req_reforma + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + ';">' + div + '</div>');

                });
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

//Ver descripcion requerimientos
$(".encabezado").on('click', '.encabezado_4 #verEstructura', function () {
    var data = $(this).data();
    $('#pres' + data['id']).slideToggle();
});

//Ver descripcion requerimientos
$(".encabezado").on('click', '.encabezado_4 #verDatos', function () {
    var data = $(this).data();
    $('#datos' + data['id']).slideToggle();
});

$('#listaRequerimiento').on('click', '.encabezado_4 #editarReforma', function () {
    var data = $(this).data();
    $('#intID').val(data['id']);
    $('#txtNombre').val(data['nombre']);
    $('#txtDescripcion').val(data['descripcion']);
    $('#dblCantidad').val(data['cantidad']);
    $('#dblCostoU').val(data['costo']);
    $('#intReforma').val(data['reforma']);
    if (data['iva'] === 1) {
        $('[name="radioiva"][value="1"]').prop("checked", true);
    } else {
        $('[name="radioiva"][value="0"]').prop("checked", true);
    }
    $('#selfinan').find('option[value="' + data['financiamientoid'] + '"]').remove();
    $('#selfinan').append('<option value="' + data['financiamientoid'] + '" selected="selected">' + data['financiamienton'] + '</option>');
    if (data['tc'] > 0) {
        $('[name="tipoReq"][value="1"]').prop("checked", true);
        $('#tipoC').removeClass('d-none');
        $('#seltipoc').find('option[value="' + data['tc'] + '"]').remove();
        $('#seltipoc').append('<option value="' + data['tc'] + '" selected="selected">' + data['tcnombre'] + '</option>');
        $('#seltipoc').selectpicker('refresh');
        $('#unidadM').removeClass('d-none');
        $('#selunidad').find('option[value="' + data['unidad'] + '"]').remove();
        $('#selunidad').append('<option value="' + data['unidad'] + '" selected="selected">' + data['unidadnombre'] + '</option>');
        $('#selunidad').selectpicker('refresh');
        $("#cpc").removeClass('d-none');
        $("#inpcpc").val(data['cpc']);
    } else {
        $('[name="tipoReq"][value="2"]').prop("checked", true);
        $('#tipoC').addClass('d-none');
        $('#unidadM').addClass('d-none');
        $('#cpc').addClass('d-none');
    }

    $('#programacionrequerimiento').empty();
    $.ajax({
        url: "../actividadReq?accion=ListarCuatrimestreActividad" + "&actividad=" + data['actividad'],
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    $('#programacionrequerimiento').append('<label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center pr-4">Porcentajes:</label>');
                    if (this.c1 > 0) {
                        if (data['po1'] > 0) {
                            $('#programacionrequerimiento').append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="1" name="checkcuatreq" id="checkcuatreq" checked>I</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajeri" placeholder="%"></div></div>');
                            if (data['po1'] < 100) {
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").attr('type', 'text').removeAttr("readonly");
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").val(data['po1']).removeAttr("readonly");
                            } else {
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").attr('type', 'hidden').removeAttr("readonly");
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").val('').removeAttr("readonly");
                            }
                        } else {
                            $('#programacionrequerimiento').append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="1" name="checkcuatreq" id="checkcuatreq">I</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajeri" placeholder="%"></div></div>');
                        }
                    }
                    if (this.c2 > 0) {
                        if (data['po2'] > 0) {
                            $('#programacionrequerimiento').append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="2" name="checkcuatreq" id="checkcuatreq" checked>II</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajerii" placeholder="%"></div></div>');
                            if (data['po2'] < 100) {
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").attr('type', 'text').removeAttr("readonly");
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").val(data['po2']).removeAttr("readonly");
                            } else {
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").attr('type', 'hidden').removeAttr("readonly");
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").val('').removeAttr("readonly");
                            }
                        } else {
                            $('#programacionrequerimiento').append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="2" name="checkcuatreq" id="checkcuatreq">II</div><div class="col-8"><input class="form-control" type="hidden" min="0" max="100" id="porcentajerii" placeholder="%"></div></div>');
                        }
                    }
                    if (this.c3 > 0) {
                        if (data['po3'] > 0) {
                            $('#programacionrequerimiento').append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="3" name="checkcuatreq" id="checkcuatreq" checked>III</div><div class="col-8"><input class="form-control" type="hidden" id="porcentajeriii" placeholder="%" min="0" max="100"></div></div>');
                            if (data['po3'] < 100) {
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").attr('type', 'text').removeAttr("readonly");
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").val(data['po3']).removeAttr("readonly");
                            } else {
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").attr('type', 'hidden').removeAttr("readonly");
                                $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").val('').removeAttr("readonly");
                            }
                        } else {
                            $('#programacionrequerimiento').append('<div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3"><div class="col-2 align-items-center align-self-center"><input class="form-check-input" type="checkbox" value="3" name="checkcuatreq' + data['valor'] + '" id="checkcuatreq">III</div><div class="col-8"><input class="form-control" type="hidden" id="porcentajeriii" placeholder="%" min="0" max="100"></div></div>');
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
    $('#selfinan').selectpicker('refresh');
    $('#txtEjercicio').val(data['ejercicio']);
    $('#txtEntidad').val(data['entidad']);
    $('#txtUnidadE').val(data['unidad_eje']);
    $('#txtUnidadD').val(data['unidad_desc']);
    $('#txtPrograma').val(data['programa']);
    $('#txtSubprograma').val(data['subprograma']);
    $('#txtProyecto').val(data['proyecto']);
    $('#txtActividad').val(data['actividadp']);
    $('#txtObra').val(data['obra']);
    $('#txtGeografico').val(data['geografico']);
    $('#txtRenglo').val(data['renglo']);
    $('#txtRengloA').val(data['renglo_aux']);
    $('#txtFuente').val(data['fuente']);
    $('#txtAnioR').val(data['anio']);
    $('#txtOrganismo').val(data['organismo']);
    $('#txtCorrelativo').val(data['correlativo']);
    $('#selPresupuesto').find('option[value="' + data['presupuesto'] + '"]').remove();
    $('#selPresupuesto').append('<option value="' + data['presupuesto'] + '" selected="selected">' + data['presupuesto'] + '</option>');
    $('#selPresupuesto').selectpicker('refresh');
    $('#txtPresupuestoid').val(data['presupuestoid']);
    $('#modificarReq').modal();
});

$('#listaRequerimiento').on('click', '.encabezado_4 #eliReforma', function () {
    var data = $(this).data();
    $('#nombrePE').html('<input type="hidden" name="nomreReq" id="nomreReq" value="' + data['nombre'] + '">' + data['nombre'])
    $('#estadoP').html('<input type="hidden" name="idreqR" id="idreqR" value="' + data['req'] + '"><input type="hidden" name="reformaR" id="reformaR" value="' + data['reforma'] + '">RA-0' + data['reforma'])
    $('#eliminarProy').modal();
    banReforma = true;
});

$('#listaRequerimientoS').on('click', '.encabezado_4 #eliSaldo', function () {
    var data = $(this).data();
    $('#nombrePE').html('<input type="hidden" name="nomreReq" id="nomreReq" value="' + data['nombre'] + '">' + data['nombre'])
    $('#estadoP').html('<input type="hidden" name="idreqR" id="idreqR" value="' + data['req'] + '"><input type="hidden" name="reformaR" id="reformaR" value="' + data['reforma'] + '">RA-0' + data['reforma'])
    $('#eliminarProy').modal();
    banReforma = false;
});

$('#listaRequerimientoS').on('click', '.encabezado_4 #editarSaldo', function () {
    var data = $(this).data();
    $('#intIDS').val(data['req']);
    $('#txtNombreS').val(data['nombre']);
    $('#txtReformaS').val(data['reforma']);
    $('#dblSaldo').val(data['saldo']);
    $('#modSaldos').modal();
});

//Radio bottom requerimiento
$('.tipoRequerimiento').on('change', function () {
    if ($('input[name=tipoReq]:checked', '#frmModificarRef').val() === "2") {
        $('#unidadM').addClass('d-none');
        $('#tipoC').addClass('d-none');
        $('#cpc').addClass('d-none');
    } else {
        $('#unidadM').removeClass('d-none');
        $('#tipoC').removeClass('d-none');
        $('#cpc').removeClass('d-none');
    }
});

$('.reqprogramacion').on('change', function () {
    var suma = 0, p1 = 0, p2 = 0, p3 = 0;
    $('#checkcuatreq:checked').each(
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
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").attr('type', 'text');
        } else {
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").attr('type', 'hidden');
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").val('');
        }
        if (p2 > 0) {
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").attr('type', 'text');
        } else {
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").attr('type', 'hidden');
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").val('');
        }
        if (p3 > 0) {
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").attr('type', 'text');
        } else {
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").attr('type', 'hidden');
            $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").val('');
        }
    } else {
        $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").attr('type', 'hidden');
        $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").attr('type', 'hidden');
        $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").attr('type', 'hidden');
        $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeri").val('');
        $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajerii").val('');
        $('#programacionrequerimiento').children('.form-group').children('.col-8').children("#porcentajeriii").val('');
    }
});

$('#modalGuardarReq').on('click', function () {
    let cuat = [];
    if ($('#txtObservacion').val() == null || $('#txtObservacion').val() == "" || $('#txtObservacion').val() == "undefined") {
        alert("Se debe agregar un breve comentario del porque se modifica");
    } else if ($('#intReforma').val() == null || $('#intReforma').val() == "" || $('#intReforma').val() == "undefined") {
        alert("Se debe agregar el numero de reforma");
    } else {
        $('#checkcuatreq:checked').each(
                function () {
                    cuat.push($(this).val());
                }
        );
        var c = JSON.stringify(cuat);
        $.ajax({
            url: '../actividadReq?accion=ModificarReforma',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: {intID: $('#intID').val(), cedulaProyecto: $('#cedulaProyecto').val(), tipoReq: $('input:radio[name=tipoReq]:checked').val(), txtNombre: $('#txtNombre').val(), txtDescripcion: $('#txtDescripcion').val(), dblCantidad: $('#dblCantidad').val(), dblCostoU: $('#dblCostoU').val(), radioiva: $('input:radio[name=radioiva]:checked').val(), selfinan: $('#selfinan').val(), selunidad: $('#selunidad').val(), seltipoc: $('#seltipoc').val(), inpcpc: $('#inpcpc').val(),
                porcentajei: $('#porcentajeri').val(), porcentajeii: $('#porcentajerii').val(), porcentajeiii: $('#porcentajeriii').val(), checkcuatreq: c, txtPresupuestoid: $('#txtPresupuestoid').val(), txtEjercicio: $('#txtEjercicio').val(), txtEntidad: $('#txtEntidad').val(), txtUnidadE: $('#txtUnidadE').val(), txtUnidadD: $('#txtUnidadD').val(), txtPrograma: $('#txtPrograma').val(), txtSubprograma: $('#txtSubprograma').val(), txtProyecto: $('#txtProyecto').val(), txtActividad: $('#txtActividad').val(),
                txtObra: $('#txtObra').val(), txtGeografico: $('#txtGeografico').val(), txtRenglo: $('#txtRenglo').val(), txtRengloA: $('#txtRengloA').val(), txtFuente: $('#txtFuente').val(), txtOrganismo: $('#txtOrganismo').val(), txtCorrelativo: $('#txtCorrelativo').val(), selPresupuesto: $('#selPresupuesto').val(), txtObservacion: $('#txtObservacion').val(), reforma: $('#intReforma').val(), anio:$('#txtAnioR').val()}
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

$('#modalModificarSaldo').on('click', function () {
    if ($('#txtObservacionS').val() == null || $('#txtObservacionS').val() == "" || $('#txtObservacionS').val() == "undefined") {
        alert("Se debe agregar un breve comentario del porque se modifica");
    } else if ($('#txtReformaS').val() == null || $('#txtReformaS').val() == "" || $('#txtReformaS').val() == "undefined") {
        alert("Se debe agregar el numero de reforma");
    } else if ($('#dblSaldo').val() > 0) {
        alert("El valor debe ser menor a 0 porque es saldo.");
    } else {
        $.ajax({
            url: '../actividadReq?accion=ModificarReformaSaldo',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: {intID: $('#intIDS').val(), cedulaProyecto: $('#cedulaProyecto').val(), txtObservacion: $('#txtObservacionS').val(), reforma: $('#txtReformaS').val(), dblSaldo: $('#dblSaldo').val()}
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

$('#modalEliminarEstados').on('click', function () {
    if (banReforma) {
        $.ajax({
            url: '../actividadReq?accion=EliminarReforma',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: {cedulaProyecto: $('#cedulaProyecto').val(), req: $('#idreqR').val(), reforma: $('#reformaR').val(), reqcodigo: $('#codigoreq').val()}
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
        $.ajax({
            url: '../actividadReq?accion=EliminarSaldo',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: {cedulaProyecto: $('#cedulaProyecto').val(), req: $('#idreqR').val(), reforma: $('#reformaR').val(), reqcodigo: $('#codigoreq').val()}
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