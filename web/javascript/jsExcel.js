function activarEx() {
    document.getElementById("FrmDescargarExcel").submit();
}

function activarExCompr() {
    document.getElementById("FrmDescargarExcelComprometidos").submit();
}

function activarExDeudas() {
    document.getElementById("FrmDescargarExcelDeudas").submit();
}

function activar() {
    document.getElementById("FrmTechoPrior").submit();
}

function activarPOA() {
    document.getElementById("FrmDescargarPOA").submit();
}

function activarExv() {
    document.getElementById("FrmDescargarExcelV").submit();
}

function activarExvc() {
    document.getElementById("FrmDescargarExcelVC").submit();
}

function activarPOAE() {
    document.getElementById("FrmDescargarEvaluacion").submit();
}

function activarEvalu() {
    document.getElementById("FrmReporteEval").submit();
}

function activarEvalu2() {
    document.getElementById("FrmReporteEval2").submit();
}

function subirExcel() {
    var tipo = $('#tipoUsuarioId').val();
    var anio = $('#anioreporteexcel').val();
    var urld;

    if (tipo === "1") {
        urld = "../reporteExcel?accion=subirExcelPresupuesto";
    } else if (tipo === "2") {
        urld = "../reporteExcel?accion=subirExcelPresupuestoDeudas";
    }
//    else {
//        urld = "../reporteExcel?accion=subirExcelPresupuesto21";
//    }

    $('#loader').removeClass('d-none');
    $.ajax({
        url: urld,
        type: 'POST',
        dataType: 'json',
        cache: false,
        processData: false,
        data: new FormData($('#FrmSubirExcel')[0]),
        contentType: false
    })
            .done(function (response) {
                $('#loader').addClass('d-none');
                alert("Se subi\u00F3 " + response);
            })
            .fail(function () {
                $('#loader').addClass('d-none');
                alert("Error al subir el excel, verifique si los campos estan correctos");
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function subirExcelD() {
    var tipo = $('#tipoUsuarioIdD').val();
    var urld;
    if (tipo === "1") {
        urld = "../reporteExcel?accion=subirExcelPresupuesto";
    } else if (tipo === "2") {
        urld = "../reporteExcel?accion=subirExcelPresupuestoDeudas";
    }

    $('#loader').removeClass('d-none');
    $.ajax({
        url: urld,
        type: 'POST',
        dataType: 'json',
        cache: false,
        processData: false,
        data: new FormData($('#FrmSubirExcelD')[0]),
        contentType: false
    })
            .done(function (response) {
                $('#loader').addClass('d-none');
                alert("Se subi\u00F3 correctamente " + response + " requerimientos.");
            })
            .fail(function () {
                $('#loader').addClass('d-none');
                alert("Error al subir el excel, verifique si los campos estan correctos");
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function subirExcelC() {
    $('#loader').removeClass('d-none');
    var url;
    if ($('#anioreporteexcelC').val() === 2020) {
        url = "../reporteExcel?accion=subirExcelComprometidos";
    } else if ($('#anioreporteexcelC').val() === 2021) {
        url = "../reporteExcel?accion=subirExcelComprometidos21";
    } else {
        url = "../reporteExcel?accion=subirExcelComprometidos22";
    }
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json',
        cache: false,
        processData: false,
        data: new FormData($('#FrmSubirExcelC')[0]),
        contentType: false
    })
            .done(function (response) {
                $('#loader').addClass('d-none');
                alert("Se subi\u00F3 correctamente " + response + " requerimientos.");
            })
            .fail(function () {
                $('#loader').addClass('d-none');
                alert("Error al subir el excel, verifique si los campos estan correctos");
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function subirExcelRe() {
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../reporteExcel?accion=subirExcelRe",
        type: 'POST',
        dataType: 'json',
        cache: false,
        processData: false,
        data: new FormData($('#FrmSubirExcelR')[0]),
        contentType: false
    })
            .done(function (response) {
                $('#loader').addClass('d-none');
                alert("Se subi\u00F3 correctamente " + response);
            })
            .fail(function () {
                $('#loader').addClass('d-none');
                alert("Error al subir el excel, verifique si los campos estan correctos");
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function subirExcelReS() {
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../reporteExcel?accion=subirExcelSaldos",
        type: 'POST',
        dataType: 'json',
        cache: false,
        processData: false,
        data: new FormData($('#FrmSubirExcelRS')[0]),
        contentType: false
    })
            .done(function (response) {
                $('#loader').addClass('d-none');
                alert("Se subi\u00F3 correctamente " + response + " requerimientos.");
            })
            .fail(function () {
                $('#loader').addClass('d-none');
                alert("Error al subir el excel, verifique si los campos estan correctos");
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function subirExcelReSP() {
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../reporteExcel?accion=subirExcelReformaP",
        type: 'POST',
        dataType: 'json',
        cache: false,
        processData: false,
        data: new FormData($('#FrmSubirExcelRSP')[0]),
        contentType: false
    })
            .done(function (response) {
                $('#loader').addClass('d-none');
                alert("Se subi\u00F3 correctamente " + response + " requerimientos.");
            })
            .fail(function () {
                $('#loader').addClass('d-none');
                alert("Error al subir el excel, verifique si los campos estan correctos");
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}