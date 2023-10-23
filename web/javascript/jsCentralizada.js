var alerta = document.getElementById('alertProyecto');

$('.col-12').on('click', '.row .row .col-1#buscarCed',function () {
    $.ajax({
        url: "../usuario?accion=BuscarCedulaCentralizada",
        data: {cedula: $('#textResponsableCed').val()},
        type: 'POST',
        dataType: 'json'
    }).
            done(function (response) {
                if (response.per_id == 'undefined' || response.per_id == null || response.per_id == "") {
                    alertaM(mensajeError, "No existe el n\u00FAmero de c\u00E9dula ingresado.", error, alerta, 'fa-times-circle');
                } else {
                    $('#textResponsable').val(response.per_completos);
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

$('.row').on('click', '.row .integrantes#buscarCed', function () {
    $.ajax({
        url: "../usuario?accion=BuscarCedulaCentralizada",
        data: {cedula: $('#textIntegranteCed').val()},
        type: 'POST',
        dataType: 'json'
    }).
            done(function (response) {
                if (response.per_id == 'undefined' || response.per_id == null || response.per_id == "") {
                    alertaM(mensajeError, "No existe el n\u00FAmero de c\u00E9dula ingresado.", error, alerta, 'fa-times-circle');
                } else {
                    $('#nombreIntegrante').val(response.per_nombres);
                    $('#apellidoIntegrante').val(response.per_apellidos);
                    $('#sexoIntegrante').val(response.sexo);
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