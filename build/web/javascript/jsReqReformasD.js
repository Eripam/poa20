/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var options2 = {style: "currency", currency: "USD"};
var banReforma;

function listarReformaD(deudas) {
    $('#loader').removeClass('d-none');
    $("#listaRequerimiento").empty();
    $('#listaRequerimientoS').empty();
    var color = 'rgba(0, 0, 0, 0.05);';
    $.ajax({
        url: "../actividadReq?accion=ListarReqReformaIDeudas",
        data: {req: deudas},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var estado, div, reforma, id, num = 1;
                $.each(response, function () {

                    if (this.deudas_reforma == "0") {
                        reforma = "INICIAL";
                    } else {
                        reforma = "RA-" + this.deudas_reforma;
                    }

                    id = this.deudas_id;
                    div = '<i class="fas fa-edit dataInfo" title="Editar" id="editarReforma" data-id="' + id + '" data-oei="' + this.deudas_oei + '" data-proyecto="' + this.proyecto_nombre + '" data-proceso="' + this.deudas_proceso + '" \n\
                    data-contrato="' + this.deudas_contrato + '" data-tconid="' + this.deudas_tcon + '" data-financiamientoid="' + this.deudas_financiamiento + '" data-montocontrato="' + this.deuda_monto_contrato + '" data-montoiva="' + this.deuda_monto_iva + '"\n\
                    data-montoanticipo="' + this.deudas_anticipo + '" data-presupuesto="' + this.deudas_presupuesto + '" data-tipo="' + this.tp_id + '" data-reforma="' + this.deudas_reforma + '" data-montopendiente="' + this.deudas_monto_pendiente + '" data-finnombre="' + this.financiamiento_nombre + '"\n\
                    data-tconnombre="' + this.tcon_nombre + '" data-estado="' + this.estado_id + '"></i>\n\
                           <i class="fas fa-eye" id="verDatos" title="Ver datos completos" data-id="' + id + '"></i>';


                    if (this.estado_id == "2" || this.estado_id == "0") {
                        estado = "ELIMINADO";
                    } else if (this.estado_id == "1" && (this.deudas_reforma != null || this.deudas_reforma != "0")) {
                        estado = "MODIFICADO";
                    } else {
                        estado = "INGRESADO";
                    }

                    var tipo;
                    if (this.tp_id == "1") {
                        tipo = 'Obligaciones Pendientes';
                    } else {
                        tipo = 'Comprometidos';
                    }

                    if (num % 2 === 0) {
                        color = 'rgba(0, 0, 0, 0.05);';
                    } else {
                        color = '#fff';
                    }

                    $("#listaRequerimiento").append('<div class="encabezado_8 estilobody centro" style="background-color:' + color + ';">' + this.deudas_id + '</div><div class="encabezado_5 estilobody text-justify" style="background-color:' + color + ';">' + this.deudas_proceso + '</div><div class="encabezado_4 estilobody text-justify" style="background-color:' + color + ';">' + this.ag.ag_nombre + '</div>\n\
                    <div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(this.deuda_monto_contrato) + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(this.deuda_monto_iva) + '</div>\n\
                    <div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(this.deudas_anticipo) + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + new Intl.NumberFormat("US", options2).format(this.deudas_monto_pendiente) + '</div>\n\
                    <div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">RA-' + this.deudas_reforma + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + '; justify-content:center;">' + estado + '</div><div class="encabezado_4 estilobody text-center" style="background-color:' + color + ';">' + div + '</div><div class="encabezado_completo estilobody text-center" style="background-color:rgba(0,0,0,0.15); display:none" id="datos' + this.deudas_id + '"></div>');
                    $('#datos' + id).append('<div>Contrato: ' + this.deudas_contrato + '</div><div>Proyecto: ' + this.proyecto_nombre + '</div><div>OEI: ' + this.deudas_oei + '</div><div>Financiamiento: ' + this.financiamiento_nombre + '</div><div>Tipo contrato: ' + this.tcon_nombre + '</div><div>Tipo: ' + tipo + '</div>');
                    num++;
                    if (this.requerimientos.length > 0) {
                        var num2 = 0, color2, tipoi;
                        $.each(this.requerimientos, function (indice, pre) {
                            if (num2 % 2 === 0) {
                                color2 = 'rgba(0, 0, 0, 0.05);';
                            } else {
                                color2 = '#fff';
                            }
                            if (this.presupuesto_id == "0") {
                                tipoi = "Fuente Efectiva";
                            } else if (this.presupuesto_id == "1") {
                                tipoi = "Fuente del iva";
                            } else {
                                tipoi = "Fuente anticipo";
                            }
                            $('#listaRequerimientoS').append('<div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_entidad + '</div><div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_ejercicio + '</div><div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_programa + '</div><div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_proyecto + '</div>\n\
                            <div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_actividad + '</div><div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_obra + '</div><div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_renglo + '</div><div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + pre.presupuesto_fuente + '</div><div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';">' + tipoi + '</div>\n\
                            <div class="encabezado_4 estilobody centro" style="background-color:' + color2 + ';"><i class="fas fa-edit dataInfo" title="Editar" id="editarEstructura" data-id="' + id + '" data-entidad="' + pre.presupuesto_entidad + '" data-ejercicio="' + pre.presupuesto_ejercicio + '" data-unidaddesc="' + pre.presupuesto_unidad_desc + '" data-unidadeje="' + pre.presupuesto_unidad_ejec + '" data-obra="' + pre.presupuesto_obra + '" data-rengloa="' + pre.presupuesto_renglo_aux + '" data-programa="' + pre.presupuesto_programa + '" data-subprograma="' + pre.presupuesto_subprograma + '" data-proyecto="' + pre.presupuesto_proyecto + '" data-actividad="' + pre.presupuesto_actividad + '"\n\
                            data-renglo="' + pre.presupuesto_renglo + '" data-geografico="' + pre.presupuesto_geografico + '" data-fuente="' + pre.presupuesto_fuente + '" data-organismo="' + pre.presupuesto_organismo + '" data-correlativo="' + pre.presupuesto_correlativo + '" data-tipoi="' + tipoi + '" data-iva="' + pre.presupuesto_id + '"></i><i class="fas fa-trash" id="eliminarEst" data-id="' + id + '" data-tipo="' + pre.presupuesto_id + '" data-tipoi="' + tipoi + '"></i></div>');
                            num2++;
                        });
                    }
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
    var codigo = $('#codigoreq').val();
    if (codigo == null || codigo == "undefined" || codigo == "") {
        alert("Debe ingresar el codigo a buscar");
    } else {
        listarReformaD(codigo);
    }
});

//Ver descripcion requerimientos
$(".encabezado").on('click', '.encabezado_4 #verDatos', function () {
    var data = $(this).data();
    $('#datos' + data['id']).slideToggle();
});

$('#montoAnticipo').keyup(function () {
    var iv = $("#montoContrato").val() - $('#montoAnticipo').val();
    $('#montoPendiente').val(iv.toFixed(2));
});

$('#listaRequerimiento').on('click', '.encabezado_4 #editarReforma', function () {
    var data = $(this).data();
    $('#iddeudas').val(data['id']);
    $('#nmbOEI').val(data['oei']);
    $('#textNombreProy').val(data['proyecto']);
    $('#txtNomProceso').val(data['proceso']);
    $('#textContrato').val(data['contrato']);
    $('#montoContrato').val(data['montocontrato']);
    $('#montoIva').val(data['montoiva']);
    $('#montoAnticipo').val(data['montoanticipo']);
    $('#montoPendiente').val(data['montopendiente']);
    $('#deudareforma').val(data['reforma']);
    if (data['tipo'] === 1) {
        $('[name="tipoDeuda"][value="1"]').prop("checked", true);
    } else {
        $('[name="tipoDeuda"][value="2"]').prop("checked", true);
    }
    $('#selectFin').find('option[value="' + data['financiamientoid'] + '"]').remove();
    $('#selectFin').append('<option value="' + data['financiamientoid'] + '" selected="selected">' + data['finnombre'] + '</option>');
    $('#selectTipoPres').find('option[value="' + data['presupuesto'] + '"]').remove();
    $('#selectTipoPres').append('<option value="' + data['presupuesto'] + '" selected="selected">' + data['presupuesto'] + '</option>');
    $('#selectTipoCon').find('option[value="' + data['tconid'] + '"]').remove();
    $('#selectTipoCon').append('<option value="' + data['tconid'] + '" selected="selected">' + data['tconnombre'] + '</option>');

    $('#modificarReq').modal();
});

$('#listaRequerimientoS').on('click', '.encabezado_4 #editarEstructura', function () {
    $('#titleEstructura').html('MODIFICAR ESTRUCTURA');
    var data = $(this).data();
    $('#txtPresupuestoid').val(data['id']);
    $('#txtEjercicio').val(data['ejercicio']);
    $('#txtUnidadE').val(data['unidadeje']);
    $('#txtUnidadD').val(data['unidaddesc']);
    $('#txtEntidad').val(data['entidad']);
    $('#txtPrograma').val(data['programa']);
    $('#txtSubprograma').val(data['subprograma']);
    $('#txtProyecto').val(data['proyecto']);
    $('#txtActividad').val(data['actividad']);
    $('#txtObra').val(data['obra']);
    $('#txtGeografico').val(data['geografico']);
    $('#txtRenglo').val(data['renglo']);
    $('#txtRengloA').val(data['rengloa']);
    $('#txtFuente').val(data['fuente']);
    $('#txtOrganismo').val(data['organismo']);
    $('#txtCorrelativo').val(data['correlativo']);
    $('#slTipoEst').val(data['iva']);
    $('#selectTipoEs').find('option[value="' + data['iva'] + '"]').remove();
    $('#selectTipoEs').append('<option value="' + data['iva'] + '" selected="selected">' + data['tipoi'] + '</option>');
    $('#selectTipoEs').prop("disabled", true);
    $('#selectTipoEs').selectpicker('refresh');
    $('#modificarEstructura').modal();
});

$('#listaRequerimientoS').on('click', '.encabezado_4 #eliminarEst', function () {
    var data = $(this).data();
    $('#estadoP').html('<input type="hidden" name="idDeudas" id="idDeudas" value="' + data['id'] + '"><input type="hidden" name="tipodeuda" id="tipodeuda" value="' + data['tipo'] + '">' + data['tipoi'])
    $('#eliminarProy').modal();
});

$('#ingresarEst').on('click', function () {
    $('#titleEstructura').html('INGRESAR ESTRUCTURA');
    $('#txtPresupuestoid').val($('#codigoreq').val());
    $('#txtEjercicio').val("");
    $('#txtUnidadE').val("");
    $('#txtUnidadD').val("");
    $('#txtEntidad').val("");
    $('#txtPrograma').val("");
    $('#txtSubprograma').val("");
    $('#txtProyecto').val("");
    $('#txtActividad').val("");
    $('#txtObra').val("");
    $('#txtGeografico').val("");
    $('#txtRenglo').val("");
    $('#txtRengloA').val("");
    $('#txtFuente').val("");
    $('#txtOrganismo').val("");
    $('#txtCorrelativo').val("");
    $('#slTipoEst').val("");
    $('#selectTipoEs').prop("disabled", false);
    $('#selectTipoEs').selectpicker('refresh');
    $('#modificarEstructura').modal();
});

$('#modalGuardarReq').on('click', function () {
    $.ajax({
        url: '../proyecto?accion=ModificarDeudasRef',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: $('#frmAddProyecto').serialize()
    })
            .done(function (response) {
                if (response === "Correcto") {
                    $('#modificarReq').modal('hide');
                    listarReformaD($('#codigoreq').val());
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

$('#modalGuardarEstr').on('click', function () {
    if ($('#slTipoEst').val() == "" || $('#slTipoEst').val() == null || $('#slTipoEst').val() == 'undefined') {
        $.ajax({
            url: '../proyecto?accion=IngresarDeudasRefEst',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: $('#frmModificarRefEst').serialize()
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        $('#modificarEstructura').modal('hide');
                        listarReformaD($('#codigoreq').val());
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
            url: '../proyecto?accion=ModificarDeudasRefEst',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: $('#frmModificarRefEst').serialize()
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        $('#modificarEstructura').modal('hide');
                        listarReformaD($('#codigoreq').val());
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
    $.ajax({
        url: '../proyecto?accion=EliminarEstructuraD',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: {cedulaProyecto: $('#cedulaProyecto').val(), deuda: $('#idDeudas').val(), tipo: $('#tipodeuda').val()}
    })
            .done(function (response) {
                if (response === "Correcto") {
                    $('#eliminarProy').modal('hide');
                    listarReformaD($('#codigoreq').val());
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