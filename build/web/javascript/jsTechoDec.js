var banTecho = true;
$(document).ready(function () {
    $('#tipoun').selectpicker();
    listaTechosUni();
});

function listaTechosUni() {
    $.ajax({
        url: "../techo?accion=ListarTechoU",
        type: 'POST',
        data: {area: $('#areaPadre').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                $('#techouni').empty();
                $.each(response, function () {
                    var row = this.techo.length + 1;
                    var id = this.techo_ag;
                    var nombre = this.ag_nombre;
                    $('#techouni').append('<tr><td class="align-middle" style="text-align:start;" rowspan="' + row + '">' + this.ag_nombre + '</td></tr>');
                    $.each(this.techo, function (indice, techo) {
                        $('#techouni').append('<tr><td>' + techo.tp_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(techo.techo_inicial) + '</td><td>' + techo.techo_fecha + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(techo.techo_total) + '</td><td><i class="m-1 fas fa-edit"  title="Editar" id="editarTecho" data-cantidad="' + techo.techo_inicial + '"\n\
                            data-reforma="' + techo.techo_reforma + '" data-signo="' + techo.techo_signo + '" data-fecha="' + techo.techo_fecha + '" data-tipo="' + techo.techo_tp + '" data-tnombre="' + techo.tp_nombre + '" data-ag="' + id + '"\n\
                            data-agnombre="' + nombre + '"></i><i class="m-1 fas fa-trash"  title="Eliminar"></i></td></tr>');
                    });
                });
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
}

$('#techouni').on('click', 'tr td #editarTecho', function () {
    var data = $(this).data();
    $('#cantidadun').val(data['cantidad']);
    $('#aghidden').val(data['ag']);
    $('#tipohidden').val(data['tipo']);
    $('#unidadun').find('option[value="' + data['tipo'] + '"]').remove();
    $('#unidadun').append('<option value="' + data['tipo'] + '" selected="selected">' + data['tnombre'] + '</option>');
    $('#unidadun').selectpicker('refresh');
    $('#tipoun').find('option[value="' + data['ag'] + '"]').remove();
    $('#tipoun').append('<option value="' + data['ag'] + '" selected="selected">' + data['agnombre'] + '</option>');
    $('#tipoun').selectpicker('refresh');
    $('#aniou').find('option[value="' + data['fecha'] + '"]').remove();
    $('#aniou').append('<option value="' + data['fecha'] + '" selected="selected">' + data['fecha'] + '</option>');
    $('#aniou').selectpicker('refresh');
    banTecho = false;
});

$('#ingresarTechoUni').on('click', function () {
    if (banTecho) {
        $.ajax({
            url: "../techo?accion=IngresarTechoUni",
            data: $('#frmTechosUnid').serialize(),
            type: 'POST',
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        $("#frmTechosUnid")[0].reset();
                        $("#unidadun").val('0');
                        $("#unidadun").selectpicker("refresh");
                        $("#aniou").val('0');
                        $("#aniou").selectpicker("refresh");
                        $("#tipoun").val('0');
                        $("#tipoun").selectpicker("refresh");
                        listaTechosUni();
                    } else {
                        alert(response);
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
    } else {
        $.ajax({
            url: "../techo?accion=ModificarTechoU",
            data: $('#frmTechosUnid').serialize(),
            type: 'POST',
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        $("#frmTechosUnid")[0].reset();
                        $("#unidadun").val('0');
                        $("#unidadun").selectpicker("refresh");
                        $("#aniou").val('0');
                        $("#aniou").selectpicker("refresh");
                        $("#tipoun").val('0');
                        $("#tipoun").selectpicker("refresh");
                        listaTechosUni();
                    } else {
                        alert(response);
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
    }
});

