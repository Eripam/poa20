$(document).ready(function () {
    listaActivar();
});

function listaActivar() {
    $.ajax({
        url: "../areaGestion?accion=ListarActivar",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaRequerimiento').empty();
                var observacion;
                $.each(response, function () {
                    if (this.estado_observacion == null || this.estado_observacio == "undefined") {
                        observacion = "---";
                    } else {
                        observacion = this.estado_observacion;
                    }

                    $('#listaRequerimiento').append('<div class="estilobody encabezado_2 centro">' + this.tp_nombre + '</div><div class="estilobody encabezado_7 centro">' + this.deudas_contrato + '</div><div class="estilobody encabezado_4 centro">' + this.ti_fecha + '</div><div class="estilobody encabezado_9 centro">' + observacion + '</div>\n\
                    <div class="estilobody encabezado_5 centro"><i class="fas fa-edit" id="modFecha" data-fecha="' + this.deudas_contrato + '" data-tipo="' + this.tp_id + '" data-tipon="' + this.tp_nombre + '" data-anio="' + this.ti_fecha + '" data-ejecucion="' + this.deudas_id + '"></i></div>');

                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#listaRequerimiento').on('click', '.estilobody #modFecha', function () {
    var data = $(this).data();
    $('#horareunion').val(data['fecha']);
    $('#tipoFecha').val(data['tipo']);
    $('#nombreFecha').val(data['tipon']);
    if (data['ejecucion'] == 1) {
        $('#ckbCierreM').prop("checked", true);
    } else {
        $('#ckbCierreM').prop("checked", false);
    }
    $('#selAniom').find('option[value="' + data['anio'] + '"]').remove();
    $('#selAniom').append('<option value="' + data['anio'] + '" selected="selected">' + data['anio'] + '</option>');
    $('#modificarFecha').modal();
});

$('#btnAgregarF').on('click', function () {
    $('#txtNombre').val('');
    $('#horareunionF').val('');
    $('#txtObservacionF').val('');
    $('#AgregarFecha').modal();
});


$('#modalModificarFec').on('click', function () {
    if ($('#txtObservacionF').val() == null || $('#txtObservacionF').val() == "" || $('#txtObservacionF').val() == "undefined") {
        alert('Debe ingresar la observaci\u00f3n,');
    } else {
        var tipo = 0;
        if (document.getElementById('ckbCierreM').checked) {
            tipo = 1;
        }
        $.ajax({
            url: "../areaGestion?accion=ModificarFechas",
            type: 'POST',
            data: {fecha: $('#horareunion').val(), cedula: $('#cedulaProyecto').val(), observacion: $('#txtObservacionF').val(), tipo: $('#tipoFecha').val(), nombreFecha: $('#nombreFecha').val(), anio: $('#selAniom').val(), ejecucion: tipo},
            dataType: 'json'
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

$('#modalGuardarFechas').on('click', function () {
    if ($('#txtObservacionFF').val() == null || $('#txtObservacionFF').val() == "" || $('#txtObservacionFF').val() == "undefined") {
        alert('Debe ingresar la observaci\u00f3n.');
    } else if ($('#txtNombre').val() == null || $('#txtNombre').val() == "" || $('#txtNombre').val() == "undefined") {
        alert('Debe ingresar el tipo o m\u00f3dulo');
    } else {
        var tipo = 0;
        if (document.getElementById('ckbCierre').checked) {
            tipo = 1;
        }
        $.ajax({
            url: "../areaGestion?accion=IngresarFechas",
            type: 'POST',
            data: {fecha: $('#horareunionF').val(), cedula: $('#cedulaProyecto').val(), observacion: $('#txtObservacionFF').val(), tipo: $('#txtNombre').val(), selAnio: $('#selAnio').val(), ejecucion: tipo},
            dataType: 'json'
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