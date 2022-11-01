/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var options2 = {style: "currency", currency: "USD"};
$(document).ready(function () {
    if ($('#tipoUsuario').val() === "9") {
        listarRequerimientos(0, $('#selectoranio').val());
    }
});
$('#ag').on('change', function () {
    listarRequerimientos(this.value, $('#selectoranio').val());
    $('#titulo').html($("#ag option:selected").text());
});

function listarRequerimientos(ag, anio) {
    $('#contenedorRequerimientos').empty();
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../actividadReq?accion=ListarRequerimientosCompras",
        type: 'POST',
        dataType: 'json',
        data: {area: ag , tipoa:$('#tipoarg').val(), anio:anio}
    })
            .done(function (response) {
                if (response.length > 0) {
                    $.each(response, function () {
                        var tabla = '<table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm tabla" id="tabla-' + this.proyecto_id + '"></table>';
                        var cabecera = '<thead class="table-azul">TR><TD rowspan=2>T. COMPRA</TD><TD rowspan=2>CPC</TD><TD rowspan=2>NOMBRE</TD><TD rowspan=2>DESCRIPCION</TD><TD rowspan=2>CANT</TD><TD rowspan=2>C. UNI</TD><TD rowspan=2>IVA</TD><TD rowspan=2>UN. MED</TD><TD COLSPAN=3>CUATRIMESTRE</TD><TD rowspan=2>ACCION</TD></TR><TR ><TD>I</TD> <TD>II</TD> <TD>III</TD></TR></thead>';
                        $('#contenedorRequerimientos').append('<p><strong>Proyecto:&nbsp</strong>' + this.proyecto_nombre + '</p>');
                        $('#contenedorRequerimientos').append(tabla);
                        $('#tabla-' + this.proyecto_id).append(cabecera);
                        var idProyecto = this.proyecto_id;
                        var num = 1;
                        if (this.requerimientos.length > 0) {
                            $.each(this.requerimientos, function (indice, req) {
                                var iva, re = req.req_id;
                                if (req.req_iva === 1) {
                                    iva = "SI";
                                } else {
                                    iva = "NO";
                                }
                                var cuatrimestre = "", cuatrimestre2 = "", cuatrimestre3 = "", div;
                                $.each(req.cuatri, function (indice, cu) {
                                    if (cu.mes_id === 1) {
                                        cuatrimestre = 'S';
                                    } else if (cu.mes_id === 2) {
                                        cuatrimestre2 = 'S';
                                    } else if (cu.mes_id === 3) {
                                        cuatrimestre3 = 'S';
                                    }
                                });
                                if (req.req_verificacion === 2) {
                                    div = '<i class="fas fa-edit dataInfo"  title="Editar Requerimiento" id="modRequerimiento" data-id="' + req.req_id + '" data-cpc="' + req.req_cpc + '"></i>\n\
                                            <a id="btnDevolverRequerimiento" data-toggle="modal" href="#devolverModal" data-id="' + req.req_id + '" data-proy="' + idProyecto + '" data-cpc="' + req.req_cpc + '"><i class="fas fa-share-square dataInfo"  title="Devolver Observación" id="devRequerimiento" ></i></a>';
                                } else {
                                    div = '<i class="fas fa-edit dataInfo"  title="Editar Requerimiento" id="modRequerimiento" data-id="' + req.req_id + '" data-cpc="' + req.req_cpc + '"></i>\n\
                                            <a id="btnDevolverRequerimiento" data-toggle="modal" href="#devolverModal" data-id="' + req.req_id + '" data-proy="' + idProyecto + '" data-cpc="' + req.req_cpc + '"><i class="fas fa-share-square dataInfo"  title="Devolver Observación" id="devRequerimiento" ></i></a>\n\
                                            <input type="checkbox" name="verificar" id="checkveri' + req.req_id + '" onchange="guardarverificacion(' + req.req_id + ', ' + idProyecto + ')">';
                                }
                                var filas = '<tr><td>' + req.tc_nombre + '</td><td id="cpc' + req.req_id + '">' + req.req_cpc + '</td><td>' + req.req_nombre + '</td>\n\
                                            <td>' + req.req_descripcion + '</td><td>' + req.req_cantidad + '</td><td>' + req.req_costo_unitario + '</td>\n\
                                            <td>' + iva + '</td><td>' + req.unidad_nombre + '</td><td>' + cuatrimestre + '</td><td>' + cuatrimestre2 + '</td>\n\
                                            <td>' + cuatrimestre3 + '</td><td><center>' + div + '</center></td>\n\
                                        </tr>';
                                $('#tabla-' + idProyecto).append(filas);
                            });
                        } else
                        {
                            $('#contenedorRequerimientos').append('<div class="alert alert-info" role="alert">No se encontraron resultados.</div>');
                        }
                    });
                } else
                {
                    $('#contenedorRequerimientos').append('<div class="alert alert-info" role="alert">No se encontraron resultados.</div>');
                }
                $('#loader').addClass('d-none');
            })
            .fail(function (e) {
                console.log('No existe conexión con la base de datos.' + e);
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#contenedorRequerimientos').on('click', '.tabla #modRequerimiento', function () {
    var data = $(this).data();
    $('#cpc' + data['id']).html('<input type="text" name="cpc" id="cpcrequ' + data['id'] + '" value="' + data['cpc'] + '">');
    $('#modRequerimiento').attr("class", "fas fa-save dataInfo");
    $('#modRequerimiento').attr("title", "Guardar Requerimiento");
    $("#modRequerimiento").data("id", data['id']);
    $('#modRequerimiento').attr("id", "guardarReq");
});

$('#contenedorRequerimientos').on('click', '.tabla  #guardarReq', function () {
    var data = $(this).data();
    var cpc = $('#cpcrequ' + data['id']).val();
    $.ajax({
        url: "../actividadReq?accion=ModificarRequerimientosCompras",
        type: 'POST',
        data: {"req": data['id'], "cpc": cpc},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    $("#cpcrequ" + data['id']).empty();
                    $('#cpc' + data['id']).html(cpc);//columna de la tabla con cpc
                    $('#guardarReq').attr("class", "fas fa-edit dataInfo");
                    $('#guardarReq').attr("title", "Editar Requerimiento");
                    $("#guardarReq").data("id", data['id']);
                    $('#guardarReq').attr("id", "modRequerimiento");
                    $("#modRequerimiento").removeData("greeting");

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

//Guardar verificacion
function guardarverificacion(req, proy) {
    if ($('#checkveri' + req).is(':checked')) {
        $.ajax({
            url: "../actividadReq?accion=IngresarVerificacion",
            type: 'POST',
            data: {"req": req, "verificacion": 1, "estado": 9, "usuario": $('#usuarioenviar').val(), proye: proy},
            dataType: 'json'
        })
                .done(function (response) {
                    alert("Se ha enviado a verificación correctamente.");
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    } else {
        $.ajax({
            url: "../actividadReq?accion=EliminarVerificacion",
            type: 'POST',
            data: {"req": req, proye: proy},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response !== "Correcto") {
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

/*Listar fechas requerimientos*/
$('#contenedorRequerimientos').on('click', '.tabla #btnDevolverRequerimiento', function () {
    var data = $(this).data();
    $('#reqinputenvia').val(data['id']);
    $('#reqenviaproy').val(data['proy']);
    $('#reqtextareaenvia').val("");
    $('#inputEnviarReq').empty();
    var options = {
        weekday: "long", year: "numeric", month: "short",
        day: "numeric", hour: "2-digit", minute: "2-digit"
    };
    $.ajax({
        url: "../actividadReq?accion=ListarFechasRequerimientos",
        type: 'POST',
        data: {"req": data['id']},
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
                    $('#inputEnviarReq').append('<p class="card-text">' + this.estado_nombre + ' por ' + this.usuario_nombre + ' el ' + date.toLocaleTimeString("es-ES", options) + observacion + '</p>');
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('#devolverModalBton').on('click', function () {
    if ($('#reqtextareaenvia').val() !== "") {
        $.ajax({
            url: "../actividadReq?accion=IngresarVerificacion",
            type: 'POST',
            data: {"req": $('#reqinputenvia').val(), estado: 19, usuario: $('#usuarioenviar').val(), observacion: $('#reqtextareaenvia').val(), verificacion: 2, proye: $('#reqenviaproy').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    $('#devolverModal').modal('hide');
                    alert("Observación realizada correctamente!");
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    } else {
        alert("Debe ingresar la observacion");
    }
});