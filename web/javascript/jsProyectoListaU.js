let tipo = $('#tipoAg').val();
$(document).ready(function () {
    let t, area = 0;
    if ($("#tipoUsuario").val() === "7" || $("#tipoUsuario").val() === "8" || $("#tipoUsuario").val() === "19") {
        if ($("#tipoUsuario").val() === "7") {
            t = 2;
        } else if ($("#tipoUsuario").val() === "8") {
            t = 3;
        } else if ($("#tipoUsuario").val() === "19") {
            t = 0;
        }
        listarProyectoCarreras($("#tipoUsuario").val(), $("#areaPadre").val(), t, area, $('#selectoranio').val());
    } else {
        listarProyectoCarreras($("#tipoUsuario").val(), $("#areaPadre").val(), 0, area, $('#selectoranio').val());
    }
});

function listarProyectoCarreras(tipou, areapadre, ti, area, anio) {
    $('#listaProyectos').empty();
    $.ajax({
        url: "../proyecto?accion=ListaProyectoUnidades",
        type: 'POST',
        data: {tipousuario: tipou, areapadre: areapadre, area: area, tipoproyecto: ti, anio: anio},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0, enlace;
                $.each(response, function () {
                    var estado, area = $("#areaPadre").val();
                    if (this.estado_nombre == null) {
                        estado = 'Planificando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.ag.ag_id.toString() === area && tipou !== "2" && tipou !== "5" && $("#tipoUsuario").val() !== "4" && $("#tipoUsuario").val() !== "26" && this.proyecto_multi === false) {
                        enlace = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (tipou === "1" && this.proyecto_multi === false) {
                        enlace = '<a href="pProyectoPrio.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if ((tipou === "1" || tipou === "11" || tipou === "26") && this.proyecto_multi === true) {
                        enlace = '<a href="pProyectoMulti.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else {
                        enlace = '<a href="pProyectoL.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    }
                    total += this.proyecto_monto;
                    $('#listaProyectos').append('<tr><td class="text-justify col-2">' + this.ag.ag_nombre + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td class="text-justify">' + this.proyecto_responsable + '</td><td>' + this.tp_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto) + '</td><td>' + estado + '</td><td>' + enlace + '</td></tr>');
                });
                $('#listaProyectos').append('<tr><td class="text-center" colspan="7">' + new Intl.NumberFormat("US", formateador()).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#selectareas').on('change', function () {
    let tip;
    if ($('#tipoUsuario').val() === "16" || $("#tipoUsuario").val() === "7" || $("#tipoUsuario").val() === "19") {
        tip = 2;
    } else if ($('#tipoUsuario').val() === "17") {
        tip = 3;
    } else {
        tip = $('#selectortipo').val();
    }
    if ($("#tipoUsuario").val() === "1" || $("#tipoUsuario").val() === "7" || $("#tipoUsuario").val() === "8" || $("#tipoUsuario").val() === "19" || $("#tipoUsuario").val() === "11" || $("#tipoUsuario").val() === "26") {
        listarProyectoCarreras($("#tipoUsuario").val(), $("#areaPadre").val(), tip, $(this).val(), $('#selectoranio').val());
    } else {
        $('#listaProyectos').empty();
        $.ajax({
            url: "../proyecto?accion=ListaProyectoCarreras",
            type: 'POST',
            data: {tipousuario: $('#tipoUsuario').val(), areapadre: $('#areaPadre').val(), area: $(this).val(), tipoproyecto: tip, anio: $('#selectoranio').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    var total = 0, enlace;
                    $.each(response, function () {
                        var estado, area = $("#areaPadre").val();
                        if (this.estado_nombre == null) {
                            estado = 'Planificando';
                        } else {
                            estado = this.estado_nombre;
                        }
                        if (this.ag.ag_id.toString() === area && tipo !== "2" && tipo === "5") {
                            enlace = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                        } else {
                            enlace = '<a href="pProyectoL.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                        }
                        total += this.proyecto_monto;
                        $('#listaProyectos').append('<tr><td class="text-justify col-2">' + this.ag.ag_nombre + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td class="text-justify">' + this.proyecto_responsable + '</td><td>' + this.tp_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto) + '</td><td>' + estado + '</td><td>' + enlace + '</td></tr>');
                    });
                    $('#listaProyectos').append('<tr><td class="text-center" colspan="7">' + new Intl.NumberFormat("US", formateador()).format(total) + '</td></tr>');
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
}
);
$('#selectortipo').change(function () {
    $('#listaProyectos').empty();
    var area;
    if ($('#selectareas').val() == null) {
        area = 0;
    } else {
        area = $('#selectareas').val();
    }
    if (($("#tipoUsuario").val() === "1" || $("#tipoUsuario").val() === "7" || $("#tipoUsuario").val() === "8" || $("#tipoUsuario").val() === "19" || $("#tipoUsuario").val() === "11" || $("#tipoUsuario").val() === "26") && $('#selectareas').val() == null) {
        alert("Debe seleccionar la unidad");
    } else if (($("#tipoUsuario").val() === "1" || $("#tipoUsuario").val() === "7" || $("#tipoUsuario").val() === "8" || $("#tipoUsuario").val() === "19" || $("#tipoUsuario").val() === "11" || $("#tipoUsuario").val() === "26") && $('#selectareas').val() != null) {
        listarProyectoCarreras($("#tipoUsuario").val(), $("#areaPadre").val(), $(this).val(), area, $('#selectoranio').val());
    } else {
        $.ajax({
            url: "../proyecto?accion=ListaProyectoCarreras",
            type: 'POST',
            data: {tipousuario: $('#tipoUsuario').val(), areapadre: $('#areaPadre').val(), area: area, tipoproyecto: $(this).val(), anio: $('#selectoranio').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    var total = 0, enlace;
                    $.each(response, function () {
                        var estado, area = $("#areaPadre").val();
                        if (this.estado_nombre == null) {
                            estado = 'Planificando';
                        } else {
                            estado = this.estado_nombre;
                        }
                        if (this.ag.ag_id.toString() === area && tipo !== "4") {
                            enlace = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                        } else {
                            enlace = '<a href="pProyectoL.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                        }
                        total += this.proyecto_monto;
                        $('#listaProyectos').append('<tr><td class="text-justify col-2">' + this.ag.ag_nombre + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td class="text-justify">' + this.proyecto_responsable + '</td><td>' + this.tp_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto) + '</td><td>' + estado + '</td><td>' + enlace + '</td></tr>');
                    });
                    $('#listaProyectos').append('<tr><td class="text-center" colspan="7">' + new Intl.NumberFormat("US", formateador()).format(total) + '</td></tr>');
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
});