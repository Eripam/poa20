bantecho = true, bantechouni = true;
$(document).ready(function () {
    $('#tipoun').selectpicker();
    listaTechosIns();
    listaTechosUni();
});

function listaTechosIns() {
    $.ajax({
        url: "../techo?accion=ListarTechoIns",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#techoinst').empty();
                $.each(response, function () {
                    $('#techoinst').append('<tr><td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_inicial) + '</td><td>' + this.techo_fecha + '</td><td>' + this.techo_reforma + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_total) + '</td><td><i class="fas fa-edit" id="techoIns" data-id="' + this.techo_fecha + '" data-inicial="' + this.techo_inicial + '"></i></td></tr>');
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

function listaTechosUni() {
    $.ajax({
        url: "../techo?accion=ListarTechoUni",
        data: {anio: $('#selectanio').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#techouni').empty();
                $.each(response, function () {
                    var row = this.techo.length + 1;
                    var id = this.techo_ag, nombre = this.ag_nombre;
                    $('#techouni').append('<tr><td class="align-middle" style="text-align:start;" rowspan="' + row + '">' + this.ag_nombre + '</td></tr>');
                    $.each(this.techo, function (indice, techo) {
                        $('#techouni').append('<tr><td>' + techo.tp_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(techo.techo_inicial) + '</td><td>' + techo.techo_fecha + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", formateador()).format(techo.techo_reforma) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(techo.techo_total) + '</td><td><i class="m-1 fas fa-edit"  data-tipo="' + techo.techo_tp + '" data-inicial="' + techo.techo_inicial + '" data-fecha="' + techo.techo_fecha + '" data-id="' + id + '" data-nombre="' + nombre + '" data-tnombre="' + techo.tp_nombre + '" title="Editar" id="editarTechoUni"></i></td></tr>');
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

$('#ingresarTechoIn').on('click', function () {
    var url;
    if (bantecho) {
        url = "../techo?accion=IngresarTechoIns";
    } else {
        url = "../techo?accion=ModificarTechoIns";
    }
    $.ajax({
        url: url,
        data: $('#frmTechosIns').serialize(),
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    $("#frmTechosIns")[0].reset();
                    listaTechosIns();
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
});

$('#ingresarTechoUni').on('click', function () {
    if (bantechouni) {
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
            url: "../techo?accion=ModificarTechoUni",
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

$('#techoinst').on('click', 'tr td #techoIns', function () {
    var data = $(this).data();
    $('#anioin').val(data['id']).attr("readonly", true);
    $('#cantidadin').val(data['inicial']);
    bantecho = false;
});

$('#techouni').on('click', 'tr td #editarTechoUni', function () {
    var data = $(this).data();
    $('#aniou').find('option[value="' + data['fecha'] + '"]').remove();
    $('#txtaniou').val(data['fecha']);
    $('#aniou').append('<option value="' + data['fecha'] + '" selected="selected">' + data['fecha'] + '</option>').attr("disabled", true);
    $('#aniou').selectpicker('refresh');
    $('#tipoun').find('option[value="' + data['id'] + '"]').remove();
    $('#txttipoun').val(data['id']);
    $('#tipoun').append('<option value="' + data['id'] + '" selected="selected">' + data['nombre'] + '</option>').attr("disabled", true);
    $('#tipoun').selectpicker('refresh');
    $('#unidadun').find('option[value="' + data['tipo'] + '"]').remove();
    $('#txtunidadun').val(data['tipo']);
    $('#unidadun').append('<option value="' + data['tipo'] + '" selected="selected">' + data['tnombre'] + '</option>').attr("disabled", true);
    $('#unidadun').selectpicker('refresh');
    $('#cantidadun').val(data['inicial']);
    window.location.href = "#frmTechosUnid";
    bantechouni = false;
});

$('#cancelarTecho').on('click', function () {
    $('#anioin').val('').attr("disabled", false);
    $('#cantidadin').val('');
    bantecho = true;
});

