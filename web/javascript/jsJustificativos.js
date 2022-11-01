var options2 = {style: "currency", currency: "USD"};
var groupColumn = 0;
var t = $('#example').DataTable({
    "columnDefs": [
        {"visible": true, "targets": groupColumn}],
    "order": [[groupColumn, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]]
}).clear().draw();

var groupColumnM = 0;
var tm = $('#modalexample').DataTable({
    "columnDefs": [
        {"visible": false, "targets": groupColumnM}],
    "order": [[groupColumnM, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]],
    "drawCallback": function (settings) {
        var api = this.api();
        var rows = api.rows({page: 'current'}).nodes();
        var last = null;
        api.column(groupColumnM, {page: 'current'}).data().each(function (group, i) {
            if (last !== group) {
                $(rows).eq(i).before(
                        '<tr class="group"><td colspan="10">' + group + '</td></tr>'
                        );
                last = group;
            }
        });
    }
}).clear().draw();

$('#modalexample tbody').on('click', 'tr.group', function () {
    var currentOrder = t.order()[0];
    if (currentOrder[0] === groupColumnM && currentOrder[1] === 'asc') {
        tm.order([groupColumnM, 'desc']).draw();
    } else {
        tm.order([groupColumnM, 'asc']).draw();
    }
});

POAPLANM(tm);

//Listado del POA Director
function listaSolicitud(t) {
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudes",
        type: 'POST',
        data: {area: $('#areaPadre').val(), anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var addID, estado, codigo, observacion;
                    if (this.estado_nombre == null) {
                        estado = 'Realizando';
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.solestado_observacion == null) {
                        observacion = 'Sin observaciones'
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    if (this.solicitud_codigo == null || this.solicitud_codigo === "undefined") {
                        codigo = '----';
                    } else {
                        codigo = this.solicitud_codigo + '-UCP-'+$('#selectanio').val();
                    }
                    var div;
                    if (this.solicitud_estado === 0 || this.solicitud_estado === 19) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-cedula="' + this.solicitud_cedula + '" data-nombre="' + this.solicitud_nombre + '" data-cargo="' + this.solicitud_cargo + '"><i class="fas fa-eye"></i></a><a href="#" title="Agregar Requerimientos" class="icon_sol" id="agregReq" data-sol="' + this.solicitud_id + '"><i class="fas fa-plus-circle"></i></a>\n\
                        <a href="#" title="Editar Justificativo" class="icon_sol" id="modJusti" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-cedula="' + this.solicitud_cedula + '" data-nombre="' + this.solicitud_nombre + '" data-cargo="' + this.solicitud_cargo + '"><i class="fas fa-pencil-alt"></i></a><a href="#" title="Eliminar Justificativo" class="icon_sol" id="elimSol" data-solicitud="' + this.solicitud_id + '"><i class="fas fa-trash-alt"></i></a><a href="#" title="Enviar justificativo" class="icon_sol" id="enviarSol" data-solicitud="' + this.solicitud_id + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-location-arrow"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a>';
                    } else if (this.solicitud_estado === 31 || this.solicitud_estado === 37 || this.solicitud_estado === 38) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-cedula="' + this.solicitud_cedula + '" data-nombre="' + this.solicitud_nombre + '" data-cargo="' + this.solicitud_cargo + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a><form method="POST" action="../controladorReportePDF" id="FrmDescargarJust' + this.solicitud_id + '" class="instructivos_archivos_icono" target="_blank" style="margin:0; padding:0;" onclick="activarPDF(' + this.solicitud_id + ');"><input type="hidden" value="reporteJustificativo" name="accion"><input type="hidden" name="idjustificativo" id="idjustificativo" value="' + this.solicitud_id + '"><input type="hidden" name="idestadojust" id="idestadojust" value="' + this.solicitud_estado + '"><input type="hidden" name="anioid" id="anioid" value="'+$('#selectanio').val()+'"><a href="#" title="Descargar Justificativo" class="icon_sol" id="descargarJust" data-sol="' + this.solicitud_id + '"><i class="fas fa-file-download"></i></a></form>';
                    } else if (this.solicitud_estado === 33 || this.solicitud_estado === 35) {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-cedula="' + this.solicitud_cedula + '" data-nombre="' + this.solicitud_nombre + '" data-cargo="' + this.solicitud_cargo + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Editar Justificativo" class="icon_sol" id="modJustiInfo" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-cedula="' + this.solicitud_cedula + '" data-nombre="' + this.solicitud_nombre + '" data-cargo="' + this.solicitud_cargo + '"><i class="fas fa-pencil-alt"></i></a><a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a><a href="#" title="Enviar justificativo" class="icon_sol" id="enviarSol" data-solicitud="' + this.solicitud_id + '" data-estado="' + this.solicitud_estado + '"><i class="fas fa-location-arrow"></i></a>';
                    } else {
                        div = '<a href="#" title="Visualizar el Justificativo" class="icon_sol" id="visualReq" data-sol="' + this.solicitud_id + '" data-centro="' + this.solicitud_centro_costo + '" data-autoridades="' + this.solicitud_autoridades + '" data-autoridadesnombre="' + this.autoridades_nombre + '" data-cedula="' + this.solicitud_cedula + '" data-nombre="' + this.solicitud_nombre + '" data-cargo="' + this.solicitud_cargo + '"><i class="fas fa-eye"></i></a>\n\
                        <a href="#" title="Listar Fechas de Envio" class="icon_sol" id="listarFechas" data-sol="' + this.solicitud_id + '"><i class="far fa-calendar-alt"></i></a>';
                    }
                    total += this.actividad_monto;
                    addID = t.row.add(['<tr><td>' + codigo + '</td>', '<td>' + this.autoridades_nombre + '</td>', '<td>' + this.solicitud_centro_costo + '</td>',
                        '<td>' + new Intl.NumberFormat("US", options2).format(this.actividad_monto) + '</td>', '<td>' + estado + '</td>',
                        '<td>' + observacion + '</td>', '<td>' + div + '</td></tr>']).draw(false);
                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                    theNode.setAttribute('id', this.solicitud_id);
                });
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('error en el controlador');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function activarPDF(id) {
    document.getElementById("FrmDescargarJust" + id).submit();
}

//Listado requerimientos para agregar
function POAPLANM(tm) {
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListRequeUnReq",
        type: 'POST',
        data: {area: $('#areaPadre').val(), tipo: $('#tipoUsuario').val(), anio:$('#selectanio').val()},
        dataType: 'json',
        dataSrc: '',
        async: false
    })
            .done(function (response) {
                $.each(response, function () {
                    var addID, observacion, iva, estado;
                    if (this.solestado_observacion === "null" || this.solestado_observacion == null) {
                        observacion = '----';
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    if (this.req_iva === 1) {
                        iva = 'SI';
                    } else {
                        iva = 'NO';
                    }
                    if (this.actividad_nombre === "null" || this.actividad_nombre == null) {
                        estado = "-----";
                    } else {
                        estado = this.actividad_nombre;
                    }
                    if (this.solicitud_estado === 28 || this.solicitud_estado === 29 || this.solicitud_estado === 30) {
                        addID = tm.row.add(['<div>' + this.ag_nombre + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div>' + this.proyecto_nombre + '</div>',
                            '<div>' + this.req_nombre + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                            '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + observacion + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat"></div>']).draw(false);
                        var theNode = $('#modalexample').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                        theNode.setAttribute('id', this.act_id);
                        $(theNode).css({"background": "#B8BDB5"});
                    } else if (this.solicitud_estado === 3) {
                        addID = tm.row.add(['<div>' + this.ag_nombre + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div>' + this.proyecto_nombre + '</div>',
                            '<div>' + this.req_nombre + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                            '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + observacion + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat" data-id="' + this.req_id + '" data-cantidad="' + this.req_cantidad + '" data-costou="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_total + '" data-costosin="' + this.req_costo_sin_iva + '" \n\
                            data-nombre="' + this.req_nombre + '" data-oei="' + this.perspectiva_id + '" data-unidad="' + this.unidad_nombre + '" data-iva="' + this.req_iva + '" data-prproyecto="' + this.presupuesto_proyecto + '"><input type="checkbox" name="reqfinmod" id="reqfinmod"></div>']).draw(false);
                        var theNode = $('#modalexample').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                        theNode.setAttribute('id', this.act_id);
                        $(theNode).css({"background": "#F9BFC1"});
                    } else {
                        addID = tm.row.add(['<div>' + this.ag_nombre + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div class="text-justify">' + this.proyecto_nombre + '</div>',
                            '<div>' + this.req_nombre + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                            '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div>', '<div style="text-align:center;">' + observacion + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat"><input type="checkbox" name="reqfinmod" id="reqfinmod" data-id="' + this.req_id + '" data-cantidad="' + this.req_cantidad + '" data-costou="' + this.req_costo_unitario + '" data-costoiva="' + this.req_costo_total + '" data-costosin="' + this.req_costo_sin_iva + '" \n\
                            data-nombre="' + this.req_nombre + '" data-oei="' + this.perspectiva_id + '" data-unidad="' + this.unidad_nombre + '" data-iva="' + this.req_iva + '" data-prproyecto="' + this.presupuesto_proyecto + '"></div>']).draw(false);
                        var theNode = $('#modalexample').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                        theNode.setAttribute('id', this.act_id);
                    }
                });
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('error en el controlador');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    listaSolicitud(t);
}

$('#example').on('click', 'tr td #visualReq', function () {
    var data = $(this).data();
    $('#centrovis').val(data['centro']);
    $('#idsolvist').val(data['sol']);
    $('#custodiovis').val(data['nombre']);
    $('#autoridadvis').find('option[value="' + data['autoridades'] + '"]').remove();
    $('#autoridadvis').append('<option value="' + data['autoridades'] + '" selected="selected">' + data['autoridadesnombre'] + '</option>');
    $('#listaRequerimientosSolVis').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitud",
        type: 'POST',
        data: {solicitud: data['sol']},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    total += this.req_costo_sin_iva;
                    $('#listaRequerimientosSolVis').append('<tr><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td class="text-center">' + this.req_cantidad + '</td>\n\
                    <td class="text-center">' + this.unidad_nombre + '</td><td>' + this.req_nombre + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</td>\n\
                    <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td></tr>');
                    num++;
                });
                $('#listaRequerimientosSolVis').append('<tr><td colspan="5" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    $("#modalJustVis").modal();
});


$('#example').on('click', 'tr td #agregReq', function () {
    var data = $(this).data();
    $('#solic').val(data['sol']);
    $('#modalJustAgregar').modal();
});

//Agregar Requerimientos
$('#modalGuardarJustAgreg').on('click', function () {
    var solic = $('input[name=solic]').val();
    var re = [];
    $("input[name=reqfinmod]:checked").each(function () {
        var rem = [];
        var d = $(this).data();
        rem.push(d['id']);
        rem.push(d['cantidad']);
        rem.push(d['costou']);
        rem.push(d['costosin']);
        rem.push(d['costoiva']);
        rem.push(d['prproyecto']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    var formData = new FormData();
    formData.append('req', req);
    formData.append('solc', solic);
    $.ajax({
        url: "../solicitud?accion=AgregarRequerimientos",
        type: 'POST',
        data: formData,
        dataType: 'json',
        cache: false,
        processData: false,
        contentType: false
    })
            .done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
                } else {
                    alert(response);
                }
            })
            .fail(function () {
                console.log('error en el controlador');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

function listaSolicitudModificar(sol) {
    $('#listaRequerimientosSol').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitud",
        type: 'POST',
        data: {solicitud: sol},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    var color;
                    if (num % 2 === 0) {
                        color = 'rgba(19, 51, 81, .25)';
                    } else {
                        color = '#fff';
                    }
                    total += this.req_costo_sin_iva;
                    $('#listaRequerimientosSol').append('<tr><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td><input type="text" class="form-control"  name="canti' + this.req_id + '" class="input" id="canti' + this.req_id + '" value="' + this.req_cantidad + '"></td>\n\
                    <td class="text-center">' + this.unidad_nombre + '</td>\n\
                    <td>' + this.req_nombre + '</td>\n\
                    <td><input type="text" class="form-control"  name="costouni' + this.req_id + '" id="costouni' + this.req_id + '" class="input" value="' + this.req_costo_unitario + '"></td>\n\
                    <td>' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                    <td><input type="hidden" value="' + this.presupuesto_proyecto + '" name="proypres' + this.req_id + '" id="proypres' + this.req_id + '"><input type="checkbox" value="' + this.req_id + '" data-iva="'+this.req_iva+'" name="re" id="re" checked="checked"></td></tr>');
                    num++;
                });
                $('#listaRequerimientosSol').append('<tr><td colspan="6" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listaSolicitudInformacion(sol) {
    $('#listaRequerimientosSoli').empty();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesSolicitud",
        type: 'POST',
        data: {solicitud: sol},
        dataType: 'json'
    })
            .done(function (response) {
                var num = 1, total = 0;
                $.each(response, function () {
                    var color;
                    if (num % 2 === 0) {
                        color = 'rgba(19, 51, 81, .25)';
                    } else {
                        color = '#fff';
                    }
                    total += this.req_costo_sin_iva;
                    $('#listaRequerimientosSoli').append('<tr><td class="text-center">OEI-0' + this.perspectiva_id + '</td><td>' + this.req_cantidad + '</td>\n\
                    <td class="text-center">' + this.unidad_nombre + '</td>\n\
                    <td>' + this.req_nombre + '</td>\n\
                    <td>' + this.req_costo_unitario + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td></tr>');
                    num++;
                });
                $('#listaRequerimientosSoli').append('<tr><td colspan="5" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#example').on('click', 'tr td #modJusti', function () {
    var data = $(this).data();
    $('#solidmod').val(data['sol']);
    $('#centromod').val(data['centro']);
    $('#cedulacustodiomod').val(data['cedula']);
    $('#nombrecustodiomod').val(data['nombre']);
    $('#cargocustodiomod').val(data['cargo']);
    $('#rectoringmod').find('option[value="' + data['autoridades'] + '"]').remove();
    $('#rectoringmod').append('<option value="' + data['autoridades'] + '" selected="selected">' + data['autoridadesnombre'] + '</option>');
    listaSolicitudModificar(data['sol']);
    $('#generarJ').modal();
});

$('#example').on('click', 'tr td #modJustiInfo', function () {
    var data = $(this).data();
    $('#solidmodi').val(data['sol']);
    $('#centromodi').val(data['centro']);
    $('#cedulacustodiomodi').val(data['cedula']);
    $('#nombrecustodiomodi').val(data['nombre']);
    $('#cargocustodiomodi').val(data['cargo']);
    $('#rectoringmodi').find('option[value="' + data['autoridades'] + '"]').remove();
    $('#rectoringmodi').append('<option value="' + data['autoridades'] + '" selected="selected">' + data['autoridadesnombre'] + '</option>');
    listaSolicitudInformacion(data['sol']);
    $('#modificarInfo').modal();
});

//Modificar solicitud
$('#modalGuardarJustMod').on('click', function () {
    var centrocosto = $('input[name=centromod]').val();
    var solicitud = $('input[name=solidmod]').val();
    var rector = $('select[name=rectoringmod]').val();
    var cedula = $('#cedulacustodiomod').val();
    var nombre = $('#nombrecustodiomod').val();
    var cargo = $('#cargocustodiomod').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        rem.push($(this).val());
        rem.push($('input[name=canti' + $(this).val() + ']').val());
        rem.push($('input[name=costouni' + $(this).val() + ']').val());
        rem.push($('input[name=proypres' + $(this).val() + ']').val());
        rem.push($(this).data('iva'));
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (centrocosto === "") {
        alert("Debe ingresar el centro de costo");
    } else {
        var formData = new FormData();
        formData.append('centro', centrocosto);
        formData.append('req', req);
        formData.append('solicitud', solicitud);
        formData.append('rector', rector);
        formData.append('cedula', $('#cedulaProyecto').val());
        formData.append('cedulac', cedula);
        formData.append('nombre', nombre);
        formData.append('cargo', cargo);
        $.ajax({
            url: "../solicitud?accion=ModificarSolicitud",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
                    } else {
                        alert(response);
                    }
                })
                .fail(function () {
                    console.log('error en el controlador');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
});

//Modificar solicitud Informacion
$('#modalGuardarJustModi').on('click', function () {
    var centrocosto = $('input[name=centromodi]').val();
    var solicitud = $('input[name=solidmodi]').val();
    var rector = $('select[name=rectoringmodi]').val();
    var cedula = $('#cedulacustodiomodi').val();
    var nombre = $('#nombrecustodiomodi').val();
    var cargo = $('#cargocustodiomodi').val();
    if (centrocosto === "") {
        alert("Debe ingresar el centro de costo");
    } else {
        var formData = new FormData();
        formData.append('centro', centrocosto);
        formData.append('solicitud', solicitud);
        formData.append('rector', rector);
        formData.append('cedula', $('#cedulaProyectoi').val());
        formData.append('cedulac', cedula);
        formData.append('nombre', nombre);
        formData.append('cargo', cargo);
        $.ajax({
            url: "../solicitud?accion=ModificarSolicitudInformacion",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
                    } else {
                        alert(response);
                    }
                })
                .fail(function () {
                    console.log('error en el controlador');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
    }
});

$('#example').on('click', 'tr td  #elimSol', function () {
    var data = $(this).data();
    $('#solicitudid').val(data['solicitud']);
    $('#eliminarModal').modal();
});

$('#modalElSolicitud').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EliminarSolicitud",
        type: 'POST',
        data: {solicitud: $('#solicitudid').val(), cedula: $('#cedulaP').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('#example').on('click', 'tr td #enviarSol', function () {
    var data = $(this).data();
    $('#idsolicitudenviar').val(data['solicitud']);
    if (data['estado'] === 33) {
        $('#idestadoenviar').val(37);
    } else if (data['estado'] === 35) {
        $('#idestadoenviar').val(38);
    } else {
        $('#idestadoenviar').val(1);
    }
    $('#enviarModal').modal();
});

//Enviar solicitud
$('#modalEnvJust').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=EnviarSolicitud",
        type: 'POST',
        data: {idSolicitud: $('#idsolicitudenviar').val(), cedula: $('#cedulaP').val(), estado: $('#idestadoenviar').val()},
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
});

$('#example').on('click', 'tr td #listarFechas', function () {
    var data = $(this).data();
    $('#fechaenvioreq').empty();
    $.ajax({
        url: "../solicitud?accion=ListaEnvios",
        type: 'POST',
        data: {solicitud: data['sol'], tipo: 1},
        dataType: 'json'
    })
            .done(function (response) {
                var options = {
                    weekday: "long", year: "numeric", month: "short",
                    day: "numeric", hour: "2-digit", minute: "2-digit"
                };
                $.each(response, function () {
                    var date = new Date(this.solestado_fecha);
                    var observacion;
                    if (this.solestado_observacion == null || this.solestado_observacion === "" || this.solestado_observacion === "undefined") {
                        observacion = "";
                    } else {
                        observacion = this.solestado_observacion;
                    }
                    $('#fechaenvioreq').append('<div style="width:100%">' + this.estado_nombre + ' por ' + this.usuario_nombre + ' el ' + date.toLocaleTimeString("es-ES", options) + ', ' + observacion + '</div>');
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    $('#devolverModal').modal();
});

//Guardar autoridad de solicitud
$('#modalGuardarJustVis').on('click', function () {
    $.ajax({
        url: "../solicitud?accion=ModificarAutoridades",
        type: 'POST',
        data: {idSolicitud: $('#idsolvist').val(), autoridad: $('#autoridadvis').val()},
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
});