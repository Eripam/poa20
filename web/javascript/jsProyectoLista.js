let tipo = $('#tipoAg').val();
$(document).ready(function () {
    listarProyectoCarreras($("#tipoUsuario").val(), $("#areaPadre").val(), $('#selectoranio').val());
});

function listarProyectoCarreras(tipo, areapadre, anio) {
    $('#listaProyectos').empty();
    $.ajax({
        url: "../proyecto?accion=ListaProyectoCarreras",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areapadre, area: 0, tipoproyecto: 0, anio: anio},
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
                    if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "4" || tipo === "5" || tipo === "8" || tipo === "15" || tipo === "19") && this.proyecto_multi) {
                        enlace = '<a href="pProyectoMulti.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "5" || tipo === "8" || tipo === "15" || tipo === "19") && !this.proyecto_multi) {
                        enlace = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() !== area && this.proyecto_multi) {
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

function listarProyectoPlanFac(tipo, areapadre) {
    $('#listaProyectos').empty();
    $.ajax({
        url: "proyecto?accion=ListaProyectoCarreras",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areapadre, area: 0, tipoproyecto: 0},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    var estado;
                    if (this.estado_nombre == null) {
                        estado = 'Planificando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    var color;
                    if (num % 2 === 0) {
                        color = 'rgba(19, 51, 81, .25)';
                    } else {
                        color = '#fff';
                    }
                    total += this.proyecto_monto;
                    $('#listaProyectos').append('<div class="main__contenedor_listados_lista main__contenedor_listados_s_xxl main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_responsable + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '">' + this.tp_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + estado + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '"><a href="InfoProy.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a></div>');
                    num++;
                });
                $('#listaProyectos').append('<div class="main__contenedor_listados_encabezado main__contenedor_listados_l_xxl main__contenedor_listados_centro">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listarProyectoPlanCar(tipo, areapadre) {
    $('#listaProyectos').empty();
    $.ajax({
        url: "proyecto?accion=ListaProyectoCarreras",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areapadre, area: 0, tipoproyecto: 0},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    var estado;
                    if (this.estado_nombre == null) {
                        estado = 'Planificando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    var color;
                    if (num % 2 === 0) {
                        color = 'rgba(19, 51, 81, .25)';
                    } else {
                        color = '#fff';
                    }
                    total += this.proyecto_monto;
                    $('#listaProyectos').append('<div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.ag_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.proyecto_nombre + '</div>\n\
                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_responsable + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '">' + this.tp_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</div>\n\
                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + estado + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xxs main__contenedor_listados_centro" style="background:' + color + '"><a href="InfoProyVis.jsp?id=' + this.proyecto_id + '&nombre=' + this.ag_nombre + '&ag=' + this.ag_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a></div>');
                    num++;
                });
                $('#listaProyectos').append('<div class="main__contenedor_listados_encabezado main__contenedor_listados_l_xxl main__contenedor_listados_centro">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listarProyectoVice(tipo, tvice) {
    $('#listaProyectos').empty();
    $.ajax({
        url: "proyecto?accion=ListaProyectoCarreras",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: 0, area: 0, tipoproyecto: tvice},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    var estado;
                    if (this.estado_nombre == null) {
                        estado = 'Planificando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    var color;
                    if (num % 2 === 0) {
                        color = 'rgba(19, 51, 81, .25)';
                    } else {
                        color = '#fff';
                    }
                    total += this.proyecto_monto;
                    $('#listaProyectos').append('<div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.ag_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.proyecto_nombre + '</div>\n\
                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_responsable + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '">' + this.tp_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</div>\n\
                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + estado + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xxs main__contenedor_listados_centro" style="background:' + color + '"><a href="InfoProyVis.jsp?id=' + this.proyecto_id + '&nombre=' + this.ag_nombre + '&ag=' + this.ag_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a></div>');
                    num++;
                });
                $('#listaProyectos').append('<div class="main__contenedor_listados_encabezado main__contenedor_listados_l_xxl main__contenedor_listados_centro">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#selectareas').on('change', function () {
    $('#listaProyectos').empty();
    $.ajax({
        url: "../proyecto?accion=ListaProyectoCarreras",
        type: 'POST',
        data: {tipousuario: $('#tipoUsuario').val(), areapadre: $('#areaPadre').val(), area: $(this).val(), tipoproyecto: $('#selectortipo').val(), anio: $('#selectoranio').val()},
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
                    if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "4" || tipo === "5" || tipo === "7" || tipo === "8" || tipo === "15" || tipo === "19") && this.proyecto_multi) {
                        enlace = '<a href="pProyectoMulti.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "5" || tipo === "7" || tipo === "8" || tipo === "15" || tipo === "19") && !this.proyecto_multi) {
                        enlace = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() !== area && this.proyecto_multi) {
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
);

$('#selectortipo').change(function () {
    $('#listaProyectos').empty();
    var area;
    if ($('#selectareas').val() == null) {
        area = $("#areaPadre").val();
    } else {
        area = $('#selectareas').val();
    }
    $.ajax({
        url: "../proyecto?accion=ListaProyectoCarreras",
        type: 'POST',
        data: {tipousuario: $('#tipoUsuario').val(), areapadre: $('#areaPadre').val(), area: area, tipoproyecto: $(this).val(), anio: $('#selectoranio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0, enlace;
                $.each(response, function () {
                    var estado;
                    if (this.estado_nombre == null) {
                        estado = 'Planificando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "4" || tipo === "5" || tipo === "7" || tipo === "8" || tipo === "15" || tipo === "19") && this.proyecto_multi) {
                        enlace = '<a href="pProyectoMulti.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "5" || tipo === "7" || tipo === "8" || tipo === "15" || tipo === "19") && !this.proyecto_multi) {
                        enlace = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() !== area && this.proyecto_multi) {
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
});

$('#selectoranio').change(function () {
    $('#listaProyectos').empty();
    var area, tipo2;
    if ($('#selectareas').val() == null) {
        area = $("#areaPadre").val();
    } else {
        area = $('#selectareas').val();
    }
    if ($('#selectortipo').val() == null) {
        tipo2 = 0;
    } else {
        tipo2 = $('#selectortipo').val();
    }
    $.ajax({
        url: "../proyecto?accion=ListaProyectoCarreras",
        type: 'POST',
        data: {tipousuario: $('#tipoUsuario').val(), areapadre: $('#areaPadre').val(), area: area, tipoproyecto: tipo2, anio: $(this).val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0, enlace;
                $.each(response, function () {
                    var estado;
                    if (this.estado_nombre == null) {
                        estado = 'Planificando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "4" || tipo === "5" || tipo === "7" || tipo === "8" || tipo === "15" || tipo === "19") && this.proyecto_multi) {
                        enlace = '<a href="pProyectoMulti.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() === area && (tipo === "2" || tipo === "3" || tipo === "5" || tipo === "7" || tipo === "8" || tipo === "15" || tipo === "19") && !this.proyecto_multi) {
                        enlace = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if (this.ag.ag_id.toString() !== area && this.proyecto_multi) {
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
});
//$('#selectareasfac').change(function () {
//    $('#listaProyectos').empty();
//    $.ajax({
//        url: "proyecto?accion=ListaProyectoCarreras",
//        type: 'POST',
//        data: {tipousuario: 0, areapadre: $('#areapadreinput').val(), area: $(this).val(), tipoproyecto: $('#selectortipo').val()},
//        dataType: 'json'
//    })
//            .done(function (response) {
//                var num = 1, total = 0;
//                $.each(response, function () {
//                    var estado;
//                    if (this.estado_nombre == null) {
//                        estado = 'Planificando';
//                    } else {
//                        estado = this.estado_nombre;
//                    }
//                    var color;
//                    if (num % 2 === 0) {
//                        color = 'rgba(19, 51, 81, .25)';
//                    } else {
//                        color = '#fff';
//                    }
//                    total += this.proyecto_monto;
//                    $('#listaProyectos').append('<div class="main__contenedor_listados_lista main__contenedor_listados_s_xxl main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_responsable + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '">' + this.tp_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + estado + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '"><a href="InfoProy.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a></div>');
//                    num++;
//                });
//                $('#listaProyectos').append('<div class="main__contenedor_listados_encabezado main__contenedor_listados_l_xxl main__contenedor_listados_centro">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
//            })
//            .fail(function () {
//                console.log('No existe conexión con la base de datos.');
//            })
//            .always(function () {
//                console.log('Se completo correctamente');
//            });
//});
//
//$('#selectortipofac').change(function () {
//    $('#listaProyectos').empty();
//    var area;
//    if ($('#selectareas').val() == null) {
//        area = 0;
//    } else {
//        area = $('#selectareas').val();
//    }
//    $.ajax({
//        url: "proyecto?accion=ListaProyectoCarreras",
//        type: 'POST',
//        data: {tipousuario: 0, areapadre: $('#areapadreinput').val(), area: area, tipoproyecto: $(this).val()},
//        dataType: 'json'
//    })
//            .done(function (response) {
//                var num = 1, total = 0;
//                $.each(response, function () {
//                    var estado;
//                    if (this.estado_nombre == null) {
//                        estado = 'Planificando';
//                    } else {
//                        estado = this.estado_nombre;
//                    }
//                    var color;
//                    if (num % 2 === 0) {
//                        color = 'rgba(19, 51, 81, .25)';
//                    } else {
//                        color = '#fff';
//                    }
//                    total += this.proyecto_monto;
//                    $('#listaProyectos').append('<div class="main__contenedor_listados_lista main__contenedor_listados_s_xxl main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_responsable + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '">' + this.tp_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + estado + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '"><a href="InfoProy.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a></div>');
//                    num++;
//                });
//                $('#listaProyectos').append('<div class="main__contenedor_listados_encabezado main__contenedor_listados_l_xxl main__contenedor_listados_centro">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
//            })
//            .fail(function () {
//                console.log('No existe conexión con la base de datos.');
//            })
//            .always(function () {
//                console.log('Se completo correctamente');
//            });
//});
//
//$('#selectareasplan').change(function () {
//    $('#listaProyectos').empty();
//    $.ajax({
//        url: "proyecto?accion=ListaProyectoCarreras",
//        type: 'POST',
//        data: {tipousuario: $('#tipousuarioinput').val(), areapadre: $('#areapadreinput').val(), area: $(this).val(), tipoproyecto: $('#selectortipoplan').val()},
//        dataType: 'json'
//    })
//            .done(function (response) {
//                var num = 1, total = 0;
//                $.each(response, function () {
//                    var estado;
//                    if (this.estado_nombre == null) {
//                        estado = 'Planificando';
//                    } else {
//                        estado = this.estado_nombre;
//                    }
//                    var color;
//                    if (num % 2 === 0) {
//                        color = 'rgba(19, 51, 81, .25)';
//                    } else {
//                        color = '#fff';
//                    }
//                    total += this.proyecto_monto;
//                    $('#listaProyectos').append('<div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.ag_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.proyecto_nombre + '</div>\n\
//                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_responsable + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '">' + this.tp_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</div>\n\
//                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + estado + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xxs main__contenedor_listados_centro" style="background:' + color + '"><a href="InfoProyVis.jsp?id=' + this.proyecto_id + '&nombre=' + this.ag_nombre + '&ag=' + this.ag_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a></div>');
//                    num++;
//                });
//                $('#listaProyectos').append('<div class="main__contenedor_listados_encabezado main__contenedor_listados_l_xxl main__contenedor_listados_centro">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
//            })
//            .fail(function () {
//                console.log('No existe conexión con la base de datos.');
//            })
//            .always(function () {
//                console.log('Se completo correctamente');
//            });
//});
//
//$('#selectortipoplan').change(function () {
//    $('#listaProyectos').empty();
//    var area;
//    if ($('#selectareasplan').val() == null) {
//        area = 0;
//    } else {
//        area = $('#selectareasplan').val();
//    }
//    $.ajax({
//        url: "proyecto?accion=ListaProyectoCarreras",
//        type: 'POST',
//        data: {tipousuario: $('#tipousuarioinput').val(), areapadre: $('#areapadreinput').val(), area: area, tipoproyecto: $(this).val()},
//        dataType: 'json'
//    })
//            .done(function (response) {
//                var num = 1, total = 0;
//                $.each(response, function () {
//                    var estado;
//                    if (this.estado_nombre == null) {
//                        estado = 'Planificando';
//                    } else {
//                        estado = this.estado_nombre;
//                    }
//                    var color;
//                    if (num % 2 === 0) {
//                        color = 'rgba(19, 51, 81, .25)';
//                    } else {
//                        color = '#fff';
//                    }
//                    total += this.proyecto_monto;
//                    $('#listaProyectos').append('<div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.ag_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_m main__contenedor_listados_inicio" style="background:' + color + '; padding-left:.6em">' + this.proyecto_nombre + '</div>\n\
//                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_inicio" style="background:' + color + '">' + this.proyecto_responsable + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xs main__contenedor_listados_centro" style="background:' + color + '">' + this.tp_nombre + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</div>\n\
//                    <div class="main__contenedor_listados_lista main__contenedor_listados_s_s main__contenedor_listados_centro" style="background:' + color + '">' + estado + '</div><div class="main__contenedor_listados_lista main__contenedor_listados_s_xxs main__contenedor_listados_centro" style="background:' + color + '"><a href="InfoProyVis.jsp?id=' + this.proyecto_id + '&nombre=' + this.ag_nombre + '&ag=' + this.ag_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a></div>');
//                    num++;
//                });
//                $('#listaProyectos').append('<div class="main__contenedor_listados_encabezado main__contenedor_listados_l_xxl main__contenedor_listados_centro">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
//            })
//            .fail(function () {
//                console.log('No existe conexión con la base de datos.');
//            })
//            .always(function () {
//                console.log('Se completo correctamente');
//            });
//});