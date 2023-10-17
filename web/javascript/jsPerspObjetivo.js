$(document).ready(function () {
    let ag = $('#idAgObEs').val();
    let tipo = $('#tipoAg').val();
    //$("#objest option[value='1']").attr("disabled", true);
    listaObjetivoAreas(ag);
    /*if (ag === "39" || ag === "41" || ag === "68" || ag === "69" || ag === "76") {
     $("#objest option[value='2']").attr("disabled", true);
     $("#objest option[value='3']").attr("disabled", true);
     } else if (ag === "42" || ag === "44") {
     $("#objest option[value='2']").attr("disabled", true);
     $("#objest option[value='3']").attr("disabled", true);
     $("#objest option[value='4']").attr("disabled", true);
     } else if (ag === "43") {
     $("#objest option[value='2']").attr("disabled", true);
     $("#objest option[value='4']").attr("disabled", true);
     } else if (ag === "44") {
     $("#objest option[value='1']").attr("disabled", true);
     } else if ($('#idTipoUsu').val() === "18") {
     $("#objest option[value='1']").attr("disabled", true);
     $("#objest option[value='4']").attr("disabled", true);
     } else if (ag === "45" || ag === "47" || ag === "71"  || ag==='82') {
     $("#objest option[value='1']").attr("disabled", true);
     $("#objest option[value='3']").attr("disabled", true);
     } else if (ag === "46") {
     $("#objest option[value='2']").attr("disabled", true);
     } else if (ag === "48" || ag==="77") {
     $("#objest option[value='1']").attr("disabled", true);
     $("#objest option[value='2']").attr("disabled", true);
     } else if (ag === "65") {
     $("#objest option[value='1']").attr("disabled", false);
     $("#objest option[value='2']").attr("disabled", false);
     $("#objest option[value='3']").attr("disabled", false);
     $("#objest option[value='4']").attr("disabled", false);
     } else if (tipo === "4") {
     $("#objest option[value='2']").attr("disabled", true);
     $("#objest option[value='3']").attr("disabled", true);
     } else {
     $("#objest option[value='1']").attr("disabled", true);
     $("#objest option[value='2']").attr("disabled", true);
     $("#objest option[value='3']").attr("disabled", true);
     }*/
});


function listaObjetivoAreas(area) {
    $.ajax({
        url: "../persobj?accion=ListarObjetivosAreas",
        data: {area: area},
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                if (response.length > 0) {
                    $.each(response, function () {
                        $("#objest option[value='" + this.objetivo_id + "']").attr("disabled", false);
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

$('#objest').on('change', function () {
    var datos = $(this).val();
    var tipo;
    $.ajax({
        url: "../persobj?accion=ListarTipoOEI",
        data: {oei: datos},
        type: 'POST',
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                if (response.length > 0) {
                    $.each(response, function () {
                        tipo = this.to_id;
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


    $.ajax({
        url: "../persobj?accion=ListarObjetivos",
        data: {objetivo: datos},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#objobj').empty();
                $('#listapolest').empty();
                $('#actpresup').empty();
                $('#frmAddProyecto').removeClass('d-block');
                $('#frmAddProyecto').addClass('d-none');
                $('#actpresup').selectpicker('refresh');
                $("#frmAddProyecto")[0].reset();
                if (tipo == "2" || tipo == "3") {
                    $('#perfilInV').removeClass('d-none');
                    $('#perfilInV').addClass('d-block');
                    $('#integrantesInV').removeClass('d-none');
                    $('#integrantesInV').addClass('d-block');
                    $('#multiSelec').removeClass('d-none');
                    $('#multiSelec').addClass('d-block');
                    $('#coejeSelec').addClass('d-none');
                } else if($('#tipoAg').val()=="4"){
                    $('#perfilInV').removeClass('d-block');
                    $('#perfilInV').addClass('d-none');
                    $('#integrantesInV').removeClass('d-block');
                    $('#integrantesInV').addClass('d-none');
                    $('#multiSelec').removeClass('d-block');
                    $('#multiSelec').addClass('d-none');
                    $('#coejeSelec').removeClass('d-none');
                }else{
                    $('#perfilInV').removeClass('d-block');
                    $('#perfilInV').addClass('d-none');
                    $('#integrantesInV').removeClass('d-block');
                    $('#integrantesInV').addClass('d-none');
                    $('#multiSelec').removeClass('d-block');
                    $('#multiSelec').addClass('d-none');
                    $('#coejeSelec').addClass('d-none');
                }
                $('#idOEIForm').val(tipo);
                if (response.length > 0) {
                    $('#objobj').append('<option selected="true" disabled>Seleccionar...</option>');
                    $.each(response, function () {
                        $('#objobj').append('<option value="' + this.objetivo_id + '">' + this.objetivo_nombre + '</option>');
                    });
                } else {
                    $('#objobj').html('<option selected="true" disabled>Sin registro</option>');
                }
                $('#objobj').addClass('d-block');
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
});

$('#objobj').on('change', function () {
    var datos = $(this).val();
    $.ajax({
        url: "../persobj?accion=ListarPoliticas",
        data: {objetivo: datos},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listapolest').empty();
                $('#frmAddProyecto').removeClass('d-block');
                $('#frmAddProyecto').addClass('d-none');
                if (response.length > 0) {
                    var pol;
                    $.each(response, function () {
                        pol = this.politica_id;
                        $('#listapolest').append('<div class="row col-12 col-xs-12 col-md-6 col-lg-4 col-xl-4 mt-3"><div class="col-12 titulo4">' + this.politica_nombre + '</div><div class="row col-12 titulo5 main-start" id="objespol' + pol + '"></div></div>');
                        if (this.pol.length > 0) {
                            $.each(this.pol, function (indice, polit) {
                                $('#listapolest').children('.row').children('#objespol' + pol).append('<li class="col-12" style="text-align: justify;">' + polit.estrategia_nombre + '</li>')
                            });
                        }
                    });
                } else {
                    $('#listapolest').html('Sin datos que mostrar');
                }
                listaActividadPre(datos);
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

function listaActividadPre(datos) {
    var estado;
    $('#actpres').addClass('d-block');
    if ($('#selectanio').val() === '2020') {
        estado = $('#selectanio').val();
    } else if ($('#selectanio').val() === '2021' || $('#selectanio').val() === '2022') {
        estado = 2021;
    } else {
        estado = $('#selectanio').val();
    }

    $.ajax({
        url: "../persobj?accion=ListarActividadesP",
        data: {objetivo: datos, estado: estado},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#actpresup').empty();
                if (response.length > 0) {
                    $('#actpresup').append('<option selected="true" disabled>Seleccionar...</option>');
                    $.each(response, function () {
                        $('#actpresup').append('<option value="' + this.ap_id + '">' + this.ap_nombre + '</option>');
                    });
                } else {
                    $('#actpresup').html('<option selected="true" disabled>Sin registro</option>');
                }
                $('#actpresup').addClass('d-block');
                $('#actpresup').selectpicker('refresh');
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

$('#selectAgM').on('change', function () {
    if (this.value === "0") {
        $("#selectAgM option").each(function () {
            // Marcamos cada valor como NO seleccionado
            if (this.value !== "0") {
                $("#selectAgM option[value=" + this.value + "]").prop("selected", false);
            }
        });
    }
});

$('#selectAgC').on('change', function () {
    if (this.value === "0") {
        $("#selectAgC option").each(function () {
            // Marcamos cada valor como NO seleccionado
            if (this.value !== "0") {
                $("#selectAgC option[value=" + this.value + "]").prop("selected", false);
            }
        });
    }
});

//Agregar textarea institucional
$('.col-12').on('click', '.row #evalinst .row #iconoplusins', function () {
    $('#evalinst').append('<div class="row container cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="accmejins" name="accmejins[]" placeholder="Registre las acciones de mejora identificadas en el informe del proceso de autoevaluaci\u00f3n seleccionado que se cumplir\u00E1n con la ejecuci\u00f3n del proyecto. Registrar en el mismo orden presentado en el informe."></textarea></div><i class="fas fa-minus" id="iconoremoveins"></i></div>');
});

$(document).on('click', '#iconoremoveins', function () {
    $(this).closest('#acmins').remove();
});

//Agregar textarea
$('.col-12').on('click', '.row #evalcar .row #iconopluscar', function () {
    $('#evalcar').append('<div class="row container cross-center" id="acmcar"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="accmejca" name="accmejca[]" placeholder="Registre las acciones de mejora identificadas en el informe del proceso de autoevaluaci\u00f3n seleccionado que se cumplir\u00E1n con la ejecuci\u00f3n del proyecto. Registrar en el mismo orden presentado en el informe."></textarea></div><i class="fas fa-minus" id="iconoremovecar"></i></div>');
});

//Agregar integrantes
$('.col-12').on('click', '.row #integrantesMos .row #iconoplusint', function () {
    $('#integrantesMos').append('<div class="row container cross-center" id="acminte"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="accmejca" name="textIntegrantes[]" placeholder="Integrante de proyecto"></textarea></div><i class="fas fa-minus" id="iconoremoveinte"></i></div>');
});

//Agregar integrantes
$('.col-12').on('click', '.row #integrantesMos .col-1 #iconoplusint', function () {
    $('#integrantesMos').append('<div class="row container cross-center" id="acminte"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="accmejca" name="textIntegrantes[]" placeholder="Integrante de proyecto"></textarea></div><i class="fas fa-minus" id="iconoremoveinte"></i></div>');
    $('#integrantesMos').append('<div class="row container cross-center" id="acminte"><input type="text" class="form-control col-10 col-xs-10 col-md-10" id="textIntegranteCed" name="textIntegranteCed[]" placeholder="Cédula" required maxlength="10" pattern="[0-9]+" oninput="this.value = this.value.replace(/[^0-9.]/g, "").replace(/(\..*)\./g, "$1");"><div class="col-1 col-xs-1 col-md-1 m-0 p-0"><i class="fas fa-search" id="buscarCedInt"></i><i class="fas fa-plus" id="iconoplusint"></i></div><input type="text" class="form-control col-12 col-xs-12 col-md-12 mt-1" id="textIntegrantes" name="textIntegrantes[]" required readonly></div>');
});

$(document).on('click', '#iconoremovecar', function () {
    $(this).closest('#acmcar').remove();
});

$(document).on('click', '#iconoremoveinte', function () {
    $(this).closest('#acminte').remove();
});