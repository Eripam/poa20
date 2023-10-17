/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#btn-modificar').on('click', function (e) {
        e.preventDefault();
        $(':text,textarea, .dropdown').removeClass('d-none');
        $(':text, textarea, .dropdown').css({display: 'flex'});
        $('.input-vista').addClass('d-none');
        $(this).addClass('d-none');
        $('#btn-guardar').removeClass('d-none');
        $('#btn-guardar').css({display: 'flex'});
        $('#btn-cross-perfil').removeClass('d-none');
        $('#res-mod').removeClass('d-none');

        $('#modAccionInstitucional').addClass('d-none');
        $('#modAccionCarrera').addClass('d-none');
        $('#inpintegrantes').removeClass('d-none');
        $('#modAccionInstitucionalMod').css({display: 'block'});
        $('#modAccionCarreraMod').css({display: 'block'});

    });
    let tipo = $('#tipoAg').val();
    let ag = $('#idAgObEs').val();
    /*if (tipo === "2" || tipo === "3" || tipo === "5") {
     $("#objest option[value='4']").attr("disabled", true);
     } else if (ag === "39" || ag === "41" || ag === "68" || ag === "69" || ag === "76") {
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
     } else if (ag === "45" || ag === "47" || ag === "71") {
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
    listaObjetivoAreas(ag);
    listaFechaActual();
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
    // $('#idOEIForm').val(datos);
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
                $('#actpresup').empty();
                //$('#listapolest').empty();
                //$('#actpresup').empty();
                //$('#frmAddProyecto').removeClass('d-block');
                //$('#frmAddProyecto').addClass('d-none');
                //$('#actpresup').selectpicker('refresh');
                //$("#frmAddProyecto")[0].reset();
                if (tipo == "2" || tipo == "3") {
                    $('#perfilInV').removeClass('d-none');
                    $('#perfilInV').addClass('d-block');
                    $('#integrantesInV').removeClass('d-none');
                    $('#integrantesInV').addClass('d-block');
                    $('#multiSelec').removeClass('d-none');
                    $('#multiSelec').addClass('d-block');
                    $('#coejeSelec').addClass('d-none');
                } else {
                    $('#perfilInV').removeClass('d-block');
                    $('#perfilInV').addClass('d-none');
                    $('#integrantesInV').removeClass('d-block');
                    $('#integrantesInV').addClass('d-none');
                    $('#multiSelec').removeClass('d-block');
                    $('#multiSelec').addClass('d-none');
                    $('#coejeSelec').removeClass('d-none');
                }
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
});


function listaFechaActual() {
    $.ajax({
        url: "../proyecto?accion=ListarFecha",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#fini-mod').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
                $('#ffin-mod').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
                $('#fechaIIntegrante').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
                $('#fechaFIntegrante').datepicker({
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
}

$('#objobj').on('change', function () {
    var datos = $(this).val(), estado;
    //$('#idOEIForm').val(datos);
    if ($('#selectoranio').val() === '2020') {
        estado = $('#selectoranio').val();
    } else if ($('#selectoranio').val() === '2021' || $('#selectoranio').val() === '2022') {
        estado = 2021;
    } else {
        estado = $('#selectoranio').val();
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
                    $('#actpresup').append('<option disabled selected="true">Seleccionar...</option>');
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
});

/*Guardar lo modificado */
$('#btn-guardar').on('click', function (e) {
    e.preventDefault();
    $(this).addClass('d-none');
    $(this).attr('disabled');

    /*  $('#btn-modificar').attr('disabled', 'true');
     $('#btn_proyecto_enviar').attr('disabled', 'true');
     $('#btn_proyecto_eliminar').attr('disabled', 'true');*/
    //$('#btn-modificar').removeClass('d-none');
    //$('#btn-modificar').css({display: 'flex'});
    var archivo = document.getElementById("permod");
    // the file is the first element in the files property
    var file = archivo.files[0];
    console.info("file: " + file);
    if (file == null) {
        $.ajax({
            url: "../proyecto?accion=ModificarProyecto",
            type: 'POST',
            dataType: 'json',
            cache: false,
            processData: false,
            data: new FormData($('#frmModProyecto')[0]),
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
                    } else {
                        $('#btn-guardar').removeClass('d-none');
                        $('#btn-guardar').css({display: 'flex'});
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
        //alert("Entró al no");
        var doc = document.getElementById('permod').files[0].name;
        if (comprueba_extension(doc) === 1) {
            $.ajax({
                url: "../proyecto?accion=ModificarProyecto",
                type: 'POST',
                dataType: 'json',
                cache: false,
                processData: false,
                data: new FormData($('#frmModProyecto')[0]),
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
                        console.log('error en el controlador');
                    })
                    .always(function () {
                        console.log('Se completo correctamente');

                    });
        }
    }
});

$('#perfilF').on('click', '#btn-cross-perfil', function (event) {
    event.preventDefault();
    $('#perfilF').addClass('d-none');
    $('#permod').removeClass('d-none');
    $('#permod').css({display: 'flex'})
});

//Agregar textarea institucional
$('.col-12').on('click', '.row #evalinst .row #iconoplusins', function () {
    $('#evalinst').append('<div class="row container main-center cross-center" id="acmins"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="accmejins" name="accmejins[]" placeholder="Registre las acciones de mejora identificadas en el informe del proceso de autoevaluaci\u00f3n seleccionado que se cumplir\u00E1n con la ejecuci\u00f3n del proyecto. Registrar en el mismo orden presentado en el informe."></textarea></div><i class="fas fa-minus" id="iconoremoveins"></i></div>');
});

$(document).on('click', '#iconoremoveins', function () {
    $(this).closest('#acmins').remove();
});

//Agregar textarea
$('.col-12').on('click', '.row #evalcar .row #iconopluscar', function () {
    $('#evalcar').append('<div class="row container main-center cross-center" id="acmcar"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="accmejca" name="accmejca[]" placeholder="Registre las acciones de mejora identificadas en el informe del proceso de autoevaluaci\u00f3n seleccionado que se cumplir\u00E1n con la ejecuci\u00f3n del proyecto. Registrar en el mismo orden presentado en el informe."></textarea></div><i class="fas fa-minus" id="iconoremovecar"></i></div>');
});

$(document).on('click', '#iconoremovecar', function () {
    $(this).closest('#acmcar').remove();
});

//Agregar integrantes
$('.col-12').on('click', '.row .col-10 #inpintegrantes .row #iconoplusint', function () {
    $('#inpintegrantes').append('<div class="row container cross-center" id="acminte"><div class="col-10 col-xs-10 col-md-9"><textarea class="form-control" id="textIntegrantes" name="textIntegrantes[]" placeholder="Integrantes de proyecto"></textarea></div><i class="fas fa-minus" id="iconoremoveinte"></i></div>');
});

$(document).on('click', '#iconoremoveinte', function () {
    $(this).closest('#acminte').remove();
});

function comprueba_extension(archivo) {
    $('#permod').empty();
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