$(document).ready(function () {
    listarVision();
    listarObjetivoE();
    listarObjetivoO();
    ListarActividadesPres();
    ListarObjetivoAreas();
    ListarPoliticas();
    ListarEstrategia();
});

function listarVision() {
    $.ajax({
        url: "../persobj?accion=ListaVision",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#listarVision').empty()
                if (response.length > 0) {
                    $.each(response, function () {
                        $("#listarVision").append('<div class="p-0 estilobody encabezado_7">' + this.vision_nombre + '</div><div class="p-0 estilobody encabezado_7">' + this.vision_mision + '</div><div class="estilobody encabezado_5 centro">' + this.vision_fi + '</div><div class="estilobody encabezado_5 centro">' + this.vision_ff + '</div><div class="estilobody encabezado_4 centro">' + this.estado_nombre + '</div><div class="estilobody encabezado_4 centro"><i class="fas fa-edit" title="Editar Vision" id="modVision" data-id="' + this.vision_id + '" data-vision="' + this.vision_nombre + '" data-mision="' + this.vision_mision + '" data-fi="' + this.vision_fi + '" data-ff="' + this.vision_ff + '" data-estado="' + this.vision_estado + '" data-estadonombre="' + this.estado_nombre + '"></i></div>')
                    });
                }
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

function listarObjetivoE() {
    $.ajax({
        url: "../persobj?accion=ListarObjetivosEstrategico",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#listarObjetivosEs').empty()
                if (response.length > 0) {
                    $.each(response, function () {
                        $("#listarObjetivosEs").append('<div class="p-0 estilobody encabezado_5 centro">' + this.perspectiva_codigo + '</div><div class="p-0 estilobody encabezado_7 text-justify">' + this.vision_nombre + '</div><div class="p-0 estilobody encabezado_7 text-justify">' + this.perspectiva_nombre + '</div><div class="estilobody encabezado_5 centro">' + this.to_nombre + '</div><div class="estilobody encabezado_4 centro">' + this.estado_nombre + '</div><div class="estilobody encabezado_4 centro"><i class="fas fa-edit" title="Editar Vision" id="modOEI" data-id="' + this.perspectiva_id + '" data-nombre="' + this.perspectiva_nombre + '" data-vision="' + this.vision_id + '" data-visionnombre="' + this.vision_nombre + '" data-codigo="' + this.perspectiva_codigo + '" data-tipo="' + this.to_id + '" data-tiponombre="' + this.to_nombre + '" data-estado="' + this.perspectiva_estado + '" data-estadonombre="' + this.estado_nombre + '"></i></div>')
                    });
                }
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

function listarObjetivoO() {
    $.ajax({
        url: "../persobj?accion=ListarObjetivosSOEI",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#listarObjetivosOo').empty()
                if (response.length > 0) {
                    $.each(response, function () {
                        $("#listarObjetivosOo").append('<div class="p-0 estilobody encabezado_2 centro">' + this.objetivo_codigo + '</div><div class="p-0 estilobody encabezado_2 centro">' + this.perspectiva_codigo + '</div><div class="p-0 estilobody encabezado_10 text-justify">' + this.objetivo_nombre + '</div><div class="estilobody encabezado_5 centro">' + this.estado_nombre + '</div><div class="estilobody encabezado_4 centro"><i class="fas fa-edit" title="Editar Vision" id="modOO" data-id="' + this.objetivo_id + '" data-nombre="' + this.objetivo_nombre + '" data-oei="' + this.perspectiva_id + '" data-oeinombre="' + this.perspectiva_nombre + '" data-codigo="' + this.objetivo_codigo + '" data-estado="' + this.objetivo_estado + '" data-estadonombre="' + this.estado_nombre + '"></i></div>')
                    });
                }
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

function ListarActividadesPres() {
    $.ajax({
        url: "../persobj?accion=ListarActividadesPres",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#listarPrograma').empty()
                if (response.length > 0) {
                    $.each(response, function () {
                        var codigo;
                        if (this.ap_codigo == 'undefined' || this.ap_codigo == null) {
                            codigo = 'Sin c\u00F3digo';
                        } else {
                            codigo = this.ap_codigo;
                        }
                        $("#listarPrograma").append('<div class="p-0 estilobody encabezado_2 centro">' + codigo + '</div><div class="p-0 estilobody encabezado_2 centro">' + this.objetivo_codigo + '</div><div class="p-0 estilobody encabezado_10 text-justify">' + this.ap_nombre + '</div><div class="estilobody encabezado_5 centro">' + this.estado_nombre + '</div><div class="estilobody encabezado_4 centro"><i class="fas fa-edit" title="Editar Programa/Proyecto" id="modPrograma" data-id="' + this.ap_id + '" data-nombre="' + this.ap_nombre + '" data-oo="' + this.objetivo_id + '" data-oonombre="' + this.objetivo_nombre + '" data-codigo="' + this.ap_codigo + '" data-oocodigo="' + this.objetivo_codigo + '" data-estado="' + this.objetivo_estado + '" data-estadonombre="' + this.estado_nombre + '" data-anio="' + this.to_id + '"></i></div>')
                    });
                }
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

function ListarObjetivoAreas() {
    $.ajax({
        url: "../persobj?accion=ListarObjetivosAreasI",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#listarOA').empty()
                if (response.length > 0) {
                    $.each(response, function () {
                        $("#listarOA").append('<div class="p-0 estilobody encabezado_4 centro">' + this.perspectiva_codigo + '</div><div class="p-0 estilobody encabezado_11 text-justify">' + this.objetivo_nombre + '</div><div class="p-0 estilobody encabezado_11 text-justify">' + this.ap_nombre + '</div><div class="estilobody encabezado_4 centro"><i class="fas fa-trash" title="Eliminar" id="modObjUn" data-perspectiva="' + this.objetivo_id + '" data-codigo="' + this.perspectiva_codigo + '" data-nombre="' + this.objetivo_nombre + '" data-ag="' + this.ap_id + '" data-agnombre="' + this.ap_nombre + '"></i></div>')
                    });
                }
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

function ListarPoliticas() {
    $.ajax({
        url: "../persobj?accion=ListarPoliticasC",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#listarPolitica').empty()
                if (response.length > 0) {
                    $.each(response, function () {
                        $("#listarPolitica").append('<div class="p-0 estilobody encabezado_4 centro">' + this.objetivo_codigo + '</div><div class="p-0 estilobody encabezado_9 text-justify">' + this.objetivo_nombre + '</div><div class="p-0 estilobody encabezado_10 text-justify">' + this.politica_nombre + '</div><div class="p-0 estilobody encabezado_5 centro">' + this.estado_nombre + '</div><div class="estilobody encabezado_4 centro"><i class="fas fa-edit" title="Modificar politica" id="modPol" data-objetivo="' + this.objetivo_id + '" data-codigo="' + this.objetivo_codigo + '" data-nombre="' + this.objetivo_nombre + '" data-politica="' + this.politica_id + '" data-politicanombre="' + this.politica_nombre + '" data-estado="' + this.politica_estado + '" data-estadonombre="' + this.estado_nombre + '"></i></div>')
                    });
                }
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

function ListarEstrategia() {
    $.ajax({
        url: "../persobj?accion=ListarEstrategias",
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#listarEstrategia').empty()
                if (response.length > 0) {
                    $.each(response, function () {
                        $("#listarEstrategia").append('<div class="p-0 estilobody encabezado_11 text-justify">' + this.politica_nombre + '</div><div class="p-0 estilobody encabezado_11 text-justify">' + this.estrategia_nombre + '</div><div class="p-0 estilobody encabezado_4 centro">' + this.estado_nombre + '</div><div class="estilobody encabezado_4 centro"><i class="fas fa-edit" title="Modificar" id="modEst" data-estrategia="' + this.estrategia_id + '" data-nombre="' + this.estrategia_nombre + '" data-politica="' + this.politica_id + '" data-politicanombre="' + this.politica_nombre + '" data-estado="' + this.politica_estado + '" data-estadonombre="' + this.estado_nombre + '"></i></div>')
                    });
                }
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

//Ingresar visión 
$('#agregarvision').on('click', function () {
    $('#exampleModalLabel').html('INGRESAR VISI\u00D3N');
    $('#txtID').val('');
    $('#txtVision').val('');
    $('#txtMision').val('');
    $('#fechaIVision').val('');
    $('#fechaFVision').val('');
    $('#fechaIVision').datepicker();
    $('#fechaFVision').datepicker();
    $('.select').addClass('d-none');
    $('#modificarVision').modal();
});

//Modificar Visión
$(".encabezado").on('click', '.encabezado_4 #modVision', function () {
    var data = $(this).data();
    $('#exampleModalLabel').html('MODIFICAR VISI\u00D3N');
    $('#txtID').val(data['id']);
    $('#txtVision').val(data['vision']);
    $('#txtMision').val(data['mision']);
    $('#fechaIVision').val(data['fi']);
    $('#fechaFVision').val(data['ff']);
    $('#fechaIVision').datepicker();
    $('#fechaFVision').datepicker();
    $('.select').removeClass('d-none');
    $('#slcVision').find('option[value="' + data['estado'] + '"]').remove();
    $('#slcVision').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#modificarVision').modal();
});

//Modificar OEI
$(".encabezado").on('click', '.encabezado_4 #modOEI', function () {
    var data = $(this).data();
    $('#exampleModalLabelOEI').html('MODIFICAR OBJETIVO ESTRATEGICO');
    $('#txtOEI').val(data['id']);
    $('#txtCodigoO').val(data['codigo']);
    $('#txtNombreO').val(data['nombre']);
    $('.selectOE').removeClass('d-none');
    $('#slcVisionO').find('option[value="' + data['vision'] + '"]').remove();
    $('#slcVisionO').append('<option value="' + data['vision'] + '" selected="selected">' + data['visionnombre'] + '</option>');
    $('#slcTipo').find('option[value="' + data['tipo'] + '"]').remove();
    $('#slcTipo').append('<option value="' + data['tipo'] + '" selected="selected">' + data['tiponombre'] + '</option>');
    $('#slcOEIEstado').find('option[value="' + data['estado'] + '"]').remove();
    $('#slcOEIEstado').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#modalObjetivosEs').modal();
});

//Modificar OO
$(".encabezado").on('click', '.encabezado_4 #modOO', function () {
    var data = $(this).data();
    $('#exampleModalLabelOO').html('MODIFICAR OBJETIVO OPERATIVO');
    $('#txtOO').val(data['id']);
    $('#txtCodigoOO').val(data['codigo']);
    $('#txtNombreOO').val(data['nombre']);
    $('.selectOO').removeClass('d-none');
    $('#slcObjE').find('option[value="' + data['oei'] + '"]').remove();
    $('#slcObjE').append('<option value="' + data['oei'] + '" selected="selected">' + data['oeinombre'] + '</option>');
    $('#slcOOEstado').find('option[value="' + data['estado'] + '"]').remove();
    $('#slcOOEstado').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#modalObjetivosO').modal();
});

//Modificar Programa
$(".encabezado").on('click', '.encabezado_4 #modPrograma', function () {
    var data = $(this).data();
    $('#exampleModalLabelPro').html('MODIFICAR');
    $('#txtPro').val(data['id']);
    $('#txtCodigoPro').val(data['codigo']);
    $('#txtNombrePro').val(data['nombre']);
    $('#txtAnioA').val(data['anio']);
    $('.selectPro').removeClass('d-none');
    $('#slcObjOp').find('option[value="' + data['oo'] + '"]').remove();
    $('#slcObjOp').append('<option value="' + data['oo'] + '" selected="selected">' + data['oocodigo'] + '-.' + data['oonombre'] + '</option>');
    $('#slcProEstado').find('option[value="' + data['estado'] + '"]').remove();
    $('#slcProEstado').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#modalPrograma').modal();
});

//Modificar Politica
$(".encabezado").on('click', '.encabezado_4 #modPol', function () {
    var data = $(this).data();
    $('#exampleModalLabelPol').html('MODIFICAR POLITICA');
    $('#txtPol').val(data['politica']);
    $('#txtNombrePol').val(data['politicanombre']);
    $('.selectPol').removeClass('d-none');
    $('#slcPol').find('option[value="' + data['objetivo'] + '"]').remove();
    $('#slcPol').append('<option value="' + data['objetivo'] + '" selected="selected">' + data['codigo'] + '-.' + data['nombre'] + '</option>');
    $('#slcPolEstado').find('option[value="' + data['estado'] + '"]').remove();
    $('#slcPolEstado').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#modalPolitica').modal();
});

//Modificar Politica
$(".encabezado").on('click', '.encabezado_4 #modEst', function () {
    var data = $(this).data();
    $('#exampleModalLabelEst').html('MODIFICAR');
    $('#txtEst').val(data['estrategia']);
    $('#txtNombreEst').val(data['nombre']);
    $('.selectEst').removeClass('d-none');
    $('#slcEst').find('option[value="' + data['politica'] + '"]').remove();
    $('#slcEst').append('<option value="' + data['politica'] + '" selected="selected">' + data['politicanombre'] + '</option>');
    $('#slcEstEstado').find('option[value="' + data['estado'] + '"]').remove();
    $('#slcEstEstado').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#modalEstrategia').modal();
});

//Eliminar Objetivo Unidad
$(".encabezado").on('click', '.encabezado_4 #modObjUn', function () {
    var data = $(this).data();
    $('#slcObjUniE').empty();
    $('#slcObjUniE').find('option[value="' + data['perspectiva'] + '"]').remove();
    $('#slcObjUniE').append('<option value="' + data['perspectiva'] + '" selected="selected">' + data['codigo'] + '-.' + data['nombre'] + '</option>');
    $('#slAgE').empty();
    $('#slAgE').find('option[value="' + data['ag'] + '"]').remove();
    $('#slAgE').append('<option value="' + data['ag'] + '" selected="selected">' + data['agnombre'] + '</option>');
    $('#modalObjetivoUnidadEl').modal();
});

$('#modificarVisionM').on('click', function () {
    var url;
    if ($('#txtVision').val() == null || $('#txtVision').val() == '') {
        alert('Debe ingresar la visi\u00F3n');
    } else if ($('#txtMision').val() == null || $('#txtMision').val() == '') {
        alert('Debe ingresar la misi\u00F3n');
    } else if ($('#fechaIVision').val() == null || $('#fechaIVision').val() == '' || $('#fechaFVision').val() == null || $('#fechaFVision').val() == '') {
        alert('Debe ingresar las fechas');
    } else {
        if ($('#txtID').val() === '' || $('#txtID').val() === null) {
            url = "../persobj?accion=IngresarVision";
        } else {
            url = "../persobj?accion=ModificarVision";
        }
        $.ajax({
            url: url,
            data: $('#frmVision').serialize(),
            type: 'POST',
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#modificarVision').modal('hide');
                        listarVision();
                        listarObjetivoE();
                        listarObjetivoO();
                        ListarActividadesPres();
                        ListarObjetivoAreas();
                        ListarPoliticas();
                    }
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
});

$('#agregarOEI').on('click', function () {
    $('#exampleModalLabelOEI').html('INGRESAR OBJETIVO ESTRATÉGICO');
    $('#txtCodigoO').val('');
    $('#txtNombreO').val('');
    $('#modalObjetivosEs').modal();
    $('.selectOE').addClass('d-none');
});

$('#agregarOO').on('click', function () {
    $('#exampleModalLabelOO').html('INGRESAR OBJETIVO OPERATIVO');
    $('#txtCodigoOO').val('');
    $('#txtNombreOO').val('');
    $('#modalObjetivosO').modal();
    $('.selectOO').addClass('d-none');
});

$('#agregarPP').on('click', function () {
    $('#exampleModalLabelPro').html('INGRESAR');
    $('#txtCodigoPro').val('');
    $('#txtNombrePro').val('');
    $('#txtAnioA').val('');
    $('#modalPrograma').modal();
    $('.selectPro').addClass('d-none');
});

$('#agregarEst').on('click', function () {
    $('#exampleModalLabelEst').html('INGRESAR');
    $('#txtNombreEst').val('');
    $('#modalEstrategia').modal();
    $('.selectEst').addClass('d-none');
});

$('#agregarPPol').on('click', function () {
    $('#exampleModalLabelPol').html('INGRESAR POLITICA');
    $('#txtNombrePol').val('');
    $('#modalPolitica').modal();
    $('.selectPol').addClass('d-none');
});

$('#agregarOA').on('click', function () {
    $('#exampleModalLabelOU').html('INGRESAR UNIDAD - OBJETIVO');
    $('#slAg').val('');
    $('#slAg').selectpicker('refresh');
    $('#modalObjetivoUnidad').modal();
});

$('#guardarOEI').on('click', function () {
    var url;
    if ($('#txtCodigoO').val() == null || $('#txtCodigoO').val() == '') {
        alert('Debe ingresar el c\u00F3digo');
    } else if ($('#txtNombreO').val() == null || $('#txtNombreO').val() == '') {
        alert('Debe ingresar el nombre');
    } else {
        if ($('#txtOEI').val() === '' || $('#txtOEI').val() === null) {
            url = "../persobj?accion=IngresarObjetivoOEI";
        } else {
            url = "../persobj?accion=ModificarObjetivoOEI";
        }
        $.ajax({
            url: url,
            data: $('#frmOEI').serialize(),
            type: 'POST',
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#modalObjetivosEs').modal('hide');
                        listarObjetivoE();
                        listarObjetivoO();
                        ListarActividadesPres();
                        ListarObjetivoAreas();
                        ListarPoliticas();
                        ListarEstrategia();
                    }
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
});

$('#guardarOO').on('click', function () {
    var url;
    if ($('#txtCodigoOO').val() == null || $('#txtCodigoOO').val() == '') {
        alert('Debe ingresar el c\u00F3digo');
    } else if ($('#txtNombreOO').val() == null || $('#txtNombreOO').val() == '') {
        alert('Debe ingresar el nombre');
    } else {
        if ($('#txtOO').val() === '' || $('#txtOO').val() === null) {
            url = "../persobj?accion=IngresarObjetivoOO";
        } else {
            url = "../persobj?accion=ModificarObjetivoOO";
        }
        $.ajax({
            url: url,
            data: $('#frmOO').serialize(),
            type: 'POST',
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#modalObjetivosO').modal('hide');
                        listarObjetivoO();
                        ListarActividadesPres();
                        ListarObjetivoAreas();
                        ListarPoliticas();
                        ListarEstrategia();
                    }
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
});

$('#guardarPro').on('click', function () {
    var url;
    if ($('#txtCodigoPro').val() == null || $('#txtCodigoPro').val() == '') {
        alert('Debe ingresar el c\u00F3digo');
    } else if ($('#txtNombrePro').val() == null || $('#txtNombrePro').val() == '') {
        alert('Debe ingresar el nombre');
    } else if ($('#txtAnioA').val() == null || $('#txtAnioA').val() == '') {
        alert('Debe ingresar el anio');
    } else {
        if ($('#txtPro').val() === '' || $('#txtPro').val() === null) {
            url = "../persobj?accion=IngresarPrograma";
        } else {
            url = "../persobj?accion=ModificarPrograma";
        }
        $.ajax({
            url: url,
            data: $('#frmPrograma').serialize(),
            type: 'POST',
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#modalPrograma').modal('hide');
                        ListarActividadesPres();
                    }
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
});

$('#guardarOU').on('click', function () {
    if ($('#slAg').val() == null || $('#slAg').val() == '') {
        alert('Debe seleccionar al menos una unidad');
    } else {
        $.ajax({
            url: "../persobj?accion=IngresarObjUnidad",
            data: $('#frmOU').serialize(),
            type: 'POST',
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#modalObjetivoUnidad').modal('hide');
                        ListarObjetivoAreas()();
                    }
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
});

$('#guardarOUE').on('click', function () {
    $.ajax({
        url: "../persobj?accion=EliminarObjetivoU",
        data: $('#frmOUEl').serialize(),
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                if (response === 'Correcto') {
                    $('#modalObjetivoUnidadEl').modal('hide');
                    ListarObjetivoAreas()();
                }
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
});

$('#guardarPol').on('click', function () {
    var url;
    if ($('#txtNombrePol').val() == null || $('#txtNombrePol').val() == '') {
        alert('Debe ingresar el nombre');
    } else {
        if ($('#txtPol').val() === '' || $('#txtPol').val() === null) {
            url = "../persobj?accion=IngresarPolitica";
        } else {
            url = "../persobj?accion=ModificarPolitica";
        }
        $.ajax({
            url: url,
            data: $('#frmPolitica').serialize(),
            type: 'POST',
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#modalPolitica').modal('hide');
                        ListarPoliticas();
                        ListarEstrategia();
                    }
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
});

$('#guardarEst').on('click', function () {
    var url;
    if ($('#txtNombreEst').val() == null || $('#txtNombreEst').val() == '') {
        alert('Debe ingresar el nombre');
    } else {
        if ($('#txtEst').val() === '' || $('#txtEst').val() === null) {
            url = "../persobj?accion=IngresarEstrategia";
        } else {
            url = "../persobj?accion=ModificarEstrategia";
        }
        $.ajax({
            url: url,
            data: $('#frmEstrategia').serialize(),
            type: 'POST',
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    if (response === 'Correcto') {
                        $('#modalEstrategia').modal('hide');
                        ListarEstrategia();
                    }
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
});