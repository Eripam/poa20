google.charts.load("current", {packages: ['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart() {
    var eficacia, efectividad, eficiencia, ejecucion, resultado, nplanificados, ncuatrimestre, nevaluados, nenviados;
    var proyecto = $('#idProy').val();
    if (proyecto == null || proyecto == '' || proyecto == 'undefined') {
        proyecto = 0;
    }
    $.ajax({
        url: "../evaluacion?accion=ListarEvaluacionProyecto",
        type: 'POST',
        data: {ag: 0, agpadre: $('#idAgObEs').val(), proyecto: proyecto, cuatrimestre: $('#cuatrimestreeval').val(), tu: $('#tipousuario').val(), anio: $('#selectanio').val()},
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                if (response == null || response == '') {
                    resultado = "Aun no se evalua el proyecto";
                } else {
                    $.each(response, function () {
                        eficacia = this.pe_eficacia;
                        eficiencia = this.pe_eficiencia;
                        efectividad = this.pe_efectividad;
                        ejecucion = this.pe_ejecucion;
                        nplanificados = this.proyecto_planificados;
                        ncuatrimestre = this.proy_cuatrimestre;
                        nenviados = this.proy_enviados;
                        nevaluados = this.proy_evaluados;
                    });
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    var color1, color2, color3;
    if (eficacia >= 0 && eficacia < 21) {
        color1 = "#F72C25";
    } else if (eficacia >= 21 && eficacia < 41) {
        color1 = "#FF7400";
    } else if (eficacia >= 41 && eficacia < 61) {
        color1 = "#FCF81F";
    } else if (eficacia >= 61 && eficacia < 81) {
        color1 = "#15A230";
    } else {
        color1 = "#0865AF";
    }
    if (eficiencia >= 0 && eficiencia < 21) {
        color2 = "#F72C25";
    } else if (eficiencia >= 21 && eficiencia < 41) {
        color2 = "#FF7400";
    } else if (eficiencia >= 41 && eficiencia < 61) {
        color2 = "#FCF81F";
    } else if (eficiencia >= 61 && eficiencia < 81) {
        color2 = "#15A230";
    } else {
        color2 = "#0865AF";
    }
    if (efectividad >= 0 && efectividad < 21) {
        color3 = "#F72C25";
    } else if (efectividad >= 21 && efectividad < 41) {
        color3 = "#FF7400";
    } else if (efectividad >= 41 && efectividad < 61) {
        color3 = "#FCF81F";
    } else if (efectividad >= 61 && efectividad < 81) {
        color3 = "#15A230";
    } else {
        color3 = "#0865AF";
    }

    if (resultado == null || resultado == '') {
        var data = google.visualization.arrayToDataTable([
            ["Element", "Valor", {role: "style"}],
            ["Eficacia", eficacia, 'stroke-color: ' + color1 + '; stroke-opacity: 0.8; stroke-width: 4; fill-color: ' + color1 + '; fill-opacity: 0.6'],
            ["Eficiencia", eficiencia, 'stroke-color: ' + color2 + '; stroke-opacity: 0.8; stroke-width: 4; fill-color: ' + color2 + '; fill-opacity: 0.6'],
            ["Efectividad", efectividad, 'stroke-color: ' + color3 + '; stroke-opacity: 0.8; stroke-width: 4; fill-color: ' + color3 + '; fill-opacity: 0.6']
        ]);

        $('#colorelip').css({'color': color3});
        var options2 = {style: "currency", currency: "USD"};
        $('#graficonombre').html($('#nombrepadre').val());
        $('#ejecucioneval').html('Ejecuci\u00f3n: ' + new Intl.NumberFormat("US", options2).format(ejecucion));
        $('#nplanificados').html('Proyectos planificados: ' + nplanificados);
        $('#ncuatrimestre').html('Proyectos a evaluar en cuatrimestre: ' + ncuatrimestre);
        $('#nenviados').html('Proyectos enviados: ' + nenviados);
        $('#nevaluados').html('Proyectos evaluados: ' + nevaluados);
        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            {calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation"},
            2]);

        var cuat;
        if ($('#cuatrimestreeval').val() === '1') {
            cuat = 'I';
        } else if ($('#cuatrimestreeval').val() === '2') {
            cuat = 'II';
        } else if ($('#cuatrimestreeval').val() === '3') {
            cuat = 'III';
        }
        var options = {
            title: "Gr\u00e1fico de evaluaci\u00f3n " + cuat + " cuatrimestre",
            chartArea: {width: '70%', height: '80%'},
            vAxis: {viewWindowMode: 'explicit', viewWindow: {min: 0}},
            bar: {groupWidth: "100%"},
            legend: {position: "none"}
        };
        var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
        chart.draw(view, options);
    } else {
        $('#columnchart_values').html(resultado);
        $('#colorelip').css({'color': '#A8A8A8'});
    }
}

$('.content').on('click', '.pestania #colorelip', function () {
    $('#modalGrafico').modal();
});

$('#selectareas').change(function () {
    google.charts.load("current", {packages: ['corechart']});
    google.charts.setOnLoadCallback(drawChart2);
});

function drawChart2() {
    var eficacia, efectividad, eficiencia, ejecucion, resultado, nplanificados, ncuatrimestre, nevaluados, nenviados;
    var proyecto = 0
    var ag = $('#selectareas').val();
    $.ajax({
        url: "../evaluacion?accion=ListarEvaluacionProyecto",
        type: 'POST',
        data: {ag: ag, agpadre: $('#idAgObEs').val(), proyecto: proyecto, cuatrimestre: $('#cuatrimestreeval').val(), tu: $('#tipousuario').val(), anio: $('#selectanio').val()},
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                if (response == null || response == '') {
                    resultado = "Aun no se evalua el proyecto";
                } else {
                    $.each(response, function () {
                        eficacia = this.pe_eficacia;
                        eficiencia = this.pe_eficiencia;
                        efectividad = this.pe_efectividad;
                        ejecucion = this.pe_ejecucion;
                        nplanificados = this.proyecto_planificados;
                        ncuatrimestre = this.proy_cuatrimestre;
                        nenviados = this.proy_enviados;
                        nevaluados = this.proy_evaluados;
                    });
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    //Area gestion
    $.ajax({
        url: "../areaGestion?accion=NombreAg",
        type: 'POST',
        data: {ag: ag},
        dataType: 'json',
        async: false
    })
            .done(function (response) {
                $('#graficonombre').html(response);
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    var color1, color2, color3;
    if (eficacia >= 0 && eficacia < 21) {
        color1 = "#F72C25";
    } else if (eficacia >= 21 && eficacia < 41) {
        color1 = "#FF7400";
    } else if (eficacia >= 41 && eficacia < 61) {
        color1 = "#FCF81F";
    } else if (eficacia >= 61 && eficacia < 81) {
        color1 = "#15A230";
    } else {
        color1 = "#0865AF";
    }
    if (eficiencia >= 0 && eficiencia < 21) {
        color2 = "#F72C25";
    } else if (eficiencia >= 21 && eficiencia < 41) {
        color2 = "#FF7400";
    } else if (eficiencia >= 41 && eficiencia < 61) {
        color2 = "#FCF81F";
    } else if (eficiencia >= 61 && eficiencia < 81) {
        color2 = "#15A230";
    } else {
        color2 = "#0865AF";
    }
    if (efectividad >= 0 && efectividad < 21) {
        color3 = "#F72C25";
    } else if (efectividad >= 21 && efectividad < 41) {
        color3 = "#FF7400";
    } else if (efectividad >= 41 && efectividad < 61) {
        color3 = "#FCF81F";
    } else if (efectividad >= 61 && efectividad < 81) {
        color3 = "#15A230";
    } else {
        color3 = "#0865AF";
    }

    if (resultado == null || resultado == '') {
        var data = google.visualization.arrayToDataTable([
            ["Element", "Valor", {role: "style"}],
            ["Eficacia", eficacia, 'stroke-color: ' + color1 + '; stroke-opacity: 0.8; stroke-width: 4; fill-color: ' + color1 + '; fill-opacity: 0.6'],
            ["Eficiencia", eficiencia, 'stroke-color: ' + color2 + '; stroke-opacity: 0.8; stroke-width: 4; fill-color: ' + color2 + '; fill-opacity: 0.6'],
            ["Efectividad", efectividad, 'stroke-color: ' + color3 + '; stroke-opacity: 0.8; stroke-width: 4; fill-color: ' + color3 + '; fill-opacity: 0.6']
        ]);

        $('#colorelip').css({'color': color3});
        var options2 = {style: "currency", currency: "USD"};
        $('#ejecucioneval').html('Ejecuci\u00f3n: ' + new Intl.NumberFormat("US", options2).format(ejecucion));
        $('#nplanificados').html('Proyectos planificados: ' + nplanificados);
        $('#ncuatrimestre').html('Proyectos a evaluar en cuatrimestre: ' + ncuatrimestre);
        $('#nenviados').html('Proyectos enviados: ' + nenviados);
        $('#nevaluados').html('Proyectos evaluados: ' + nevaluados);
        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
            {calc: "stringify",
                sourceColumn: 1,
                type: "string",
                role: "annotation"},
            2]);

        var cuat;
        if ($('#cuatrimestreeval').val() === '1') {
            cuat = 'I';
        } else if ($('#cuatrimestreeval').val() === '2') {
            cuat = 'II';
        } else if ($('#cuatrimestreeval').val() === '3') {
            cuat = 'III';
        }
        var options = {
            title: "Gr\u00e1fico de evaluaci\u00f3n " + cuat + " cuatrimestre",
            chartArea: {width: '50%', height: '75%'},
            vAxis: {viewWindowMode: 'explicit', viewWindow: {min: 0}},
            bar: {groupWidth: "85%"},
            legend: {position: "none"}
        };
        var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
        chart.draw(view, options);
    } else {
        $('#columnchart_values').html(resultado);
        $('#colorelip').css({'color': '#A8A8A8'});
    }
}