$(document).ready(function () {
    $.ajax({
        url: "../usuario?accion=VerificacionUsuario",
        data: {cedula: $("#cedulausuario").val(), nombre:$("#nombreusuario").val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response) {
                    window.location.href = "pWelcome.jsp";
                } else {
                    $('.progress').css({"display":"none"});
                    $('.texto').html("Usted no se encuentra registrado para acceder al Sistema de Planificaci\u00f3n, para mayor informaci\u00f3n por favor comuniquese con el administrador.");
                }
            })
            .fail(function (jqXHR, textStatus, errorThrown) {

                if (jqXHR.status === 0) {

                    console.log('Not connect: Verify Network.');

                } else if (jqXHR.status == 404) {

                    console.log('Requested page not found [404]');

                } else if (jqXHR.status == 500) {

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

