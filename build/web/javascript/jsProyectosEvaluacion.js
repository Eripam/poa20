var options2 = {style: "currency", currency: "USD"};


$('#selectcuatrimestre').on('change', function () {
    var cu;
    if ($('#selectcuatrimestre').val() === '1') {
        cu = "I";
    } else if ($('#selectcuatrimestre').val() === '2') {
        cu = "II";
    } else if ($('#selectcuatrimestre').val() === '3') {
        cu = "III";
    }
    $('#cuatrititulo').html(cu + ' CUATRIMESTRE');
    $('#cuatrieval').val($('#selectcuatrimestre').val());
    $('#cuatrimestreeval').val($('#selectcuatrimestre').val());
    listarProyectoEvaluacion(parseInt($("#tipousuarioinput").val()), parseInt($("#areapadreinput").val()), $('#cuatrieval').val(), $('#selectanio').val());
    drawChart();
});

function listarProyectoEvaluacion(tipo, areapadre, cuatri, anio) {
    $('#listaProyectos').empty();
    $.ajax({
        url: "../proyecto?accion=ListaProyectoEvaluacion",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areapadre, area: 0, tipoproyecto: 0, cuatrimestre: cuatri, anio: anio},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0, enlace;
                $.each(response, function () {
                    var estado
                    if (this.estado_nombre == null) {
                        estado = '-----';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if ((this.ag.ag_id === areapadre) && (tipo === 2 || tipo === 3 || tipo === 5 || tipo === 7 || tipo === 8 || tipo === 15 || tipo === 19)) {
                        enlace = '<a href="pProyectoEval.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else if ((this.ag.ag_id !== areapadre) && areapadre === 68 && (tipo === 2 || tipo === 3 || tipo === 5 || tipo === 7 || tipo === 8 || tipo === 15 || tipo === 19)) {
                        enlace = '<a href="pProyectoEval.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else {
                        enlace = '<a href="pProyectoEvalL.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    }
                    total += this.proyecto_monto;
                    $('#listaProyectos').append('<tr><td class="text-justify col-2">' + this.ag.ag_nombre + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td class="text-justify">' + this.proyecto_responsable + '</td><td>' + this.tp_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.proyecto_monto) + '</td><td>' + estado + '</td><td>' + enlace + '</td></tr>');
                    num++;
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

$('#selectareas').change(function () {
    $('#listaProyectos').empty();
    var tipo, tp = parseInt($('#tipousuarioinput').val());
    if ($('#selectortipoplan').val() == null || $('#selectortipoplan').val() == 'undefined') {
        tipo = 0;
    } else {
        tipo = $('#selectortipoplan').val();
    }

    $.ajax({
        url: "../proyecto?accion=ListaProyectoEvaluacion",
        type: 'POST',
        data: {tipousuario: tp, areapadre: $('#areapadreinput').val(), area: $(this).val(), tipoproyecto: tipo, cuatrimestre: $('#cuatrieval').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0, enlace;
                $.each(response, function () {
                    var estado, area2 = parseInt($("#areapadreinput").val());
                    if (this.estado_nombre == null) {
                        estado = '----';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.ag.ag_id === area2 && (tp === 2 || tp === 3 || tp === 5 || tp === 7 || tp === 8 || tp === 15 || tp === 19)) {
                        enlace = '<a href="pProyectoEval.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else {
                        enlace = '<a href="pProyectoEvalL.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
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

$('#selectortipo').change(function () {
    $('#listaProyectos').empty();
    var area, tipo = parseInt($('#tipousuarioinput').val());
    if ($('#selectareas').val() == null || $('#selectareas').val() === 'undefined') {
        area = 0;
    } else {
        area = $('#selectareas').val();
    }
    $.ajax({
        url: "../proyecto?accion=ListaProyectoEvaluacion",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: $('#areapadreinput').val(), area: area, tipoproyecto: $(this).val(), cuatrimestre: $('#cuatrieval').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0, enlace;
                $.each(response, function () {
                    var estado, area2 = parseInt($("#areapadreinput").val());
                    if (this.estado_nombre == null) {
                        estado = '----';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.ag.ag_id === area2 && (tipo === 2 || tipo === 3 || tipo === 5 || tipo === 7 || tipo === 8 || tipo === 15 || tipo === 19)) {
                        enlace = '<a href="pProyectoEval.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else {
                        enlace = '<a href="pProyectoEvalL.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
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