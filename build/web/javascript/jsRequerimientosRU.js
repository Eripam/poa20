var groupColumn = 0;
var estado, ag = $('#agjustificativo').val();
if (ag === '68') {
    estado = 28;
} else if (ag === '54') {
    estado = 30;
} else if (ag === '60') {
    estado = 29;
} else if (ag === '57') {
    estado = 50;
} else if (ag === '69') {
    estado = 53;
}
var t = $('#example').DataTable({
    "columnDefs": [
        {"visible": false},
        {"width": "3em", targets: 0},
        {"width": "11em", targets: 1},
        {"width": "11em", targets: 2},
        {"width": "6em", targets: 3},
        {"width": "9em", targets: 4},
        {"width": "9em", targets: 5},
        {"width": "6em", targets: 6}],
    "order": [[1, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]]
}).clear().draw();


POAPLAN(t);

//Listado del POA Director
function POAPLAN(t) {
    $('#idcargando').css({"display": "flex"});
    $.ajax({
        url: "../solicitud?accion=ListRequeUnificados",
        type: 'POST',
        data: {estado: estado, anio: $('#selectanio').val()},
        dataType: 'json',
        dataSrc: ''
    })
            .done(function (response) {
                var options2 = {style: "currency", currency: "USD"};
                $.each(response, function () {
                    var addID, total;
                    if (this.req_iva === 1) {
                        total = this.req_costo_sin_iva * 1.12;
                    } else {
                        total = this.req_costo_sin_iva;
                    }
                    addID = t.row.add(['<div>OEI-0' + this.perspectiva_id + '</div>', '<div>' + this.req_nombre + '</div>', '<div>' + this.req_descripcion + '</div>', '<div style="text-align:center;">' + this.req_cantidad + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</div>',
                        '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(total) + '</div>', '<div class="dat" data-id="' + this.req_id + '" data-cantidad="' + this.req_cantidad + '" data-costou="' + this.req_costo_unitario + '" data-costosin="' + this.req_costo_sin_iva + '" data-nombre="' + this.req_nombre + '" data-oei="' + this.perspectiva_id + '" data-unidad="' + this.unidad_nombre + '" data-iva="' + this.req_iva + '">\n\
                        <input type="checkbox" name="reqfinmod" id="reqfinmod"><a href="#" title="Editar Requerimiento" class="icon_sol" id="modReqUni" data-cantidad="' + this.req_cantidad + '" data-id="' + this.req_id + '" data-costou="' + this.req_costo_unitario + '" data-costosin="' + this.req_costo_sin_iva + '" data-oei="' + this.perspectiva_id + '" data-unidad="' + this.unidad_nombre + '" data-nombre="' + this.req_nombre + '"\n\
                        data-descripcion="' + this.req_descripcion + '" data-unidadid="' + this.unidad_id + '" data-iva="' + this.req_iva + '"><i class="fas fa-pencil-alt"></i></a><a href="#" title="Eliminar Requerimiento" class="icon_sol" id="elimReqUni" data-id="' + this.req_id + '"><i class="fas fa-trash-alt"></i></a></div>']).draw(false);
                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                    theNode.setAttribute('id', this.act_id);
                });
                $('#idcargando').css({"display": "none"});
            })
            .fail(function () {
                console.log('error en el controlador');
                $('#idcargando').css({"display": "none"});
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function formToObjetJson(formArray) {//serialize data function
    returnArray = {};
    for (var i = 0; i < formArray.length; i++) {
        returnArray[formArray[i]['name']] = formArray[i]['value'];
    }
    return returnArray;
}

$('#example tbody').on('click', 'tr', function () {
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
    } else {
        t.$('tr.selected').removeClass('selected');
        $(this).addClass('selected');
    }
});


$('#example').on('click', 'tbody tr td .dat #modReqUni', function () {
    var data = $(this).data();
    var sum = 0, sumto = 0, total = 0, sumci = 0;
    $('#requnifid').val(data['id']);
    $('#nombreunif').val(data['nombre']);
    $('#descunif').val(data['descripcion']);
    $('#cantidadunif').val(data['cantidad']);
    $('#costounif').val(data['costou']);
    $('#selunidaduni').find('option[value="' + data['unidadid'] + '"]').remove();
    $('#selunidaduni').append('<option value="' + data['unidadid'] + '" selected="selected">' + data['unidad'] + '</option>');
    $('#tituloEnviarUnif').html("Modificar Unificacion");
    $('#listaRequerimientosUnificaReq').empty();
    $.ajax({
        url: "../solicitud?accion=ListaRequerimientosU",
        data: {req: data['id']},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var options2 = {style: "currency", currency: "USD"};
                $.each(response, function () {
                    var iva;
                    if (this.req_iva === 1) {
                        iva = this.req_costo_sin_iva * 1.12;
                    } else {
                        iva = this.req_costo_sin_iva;
                    }
                    $('#listaRequerimientosUnificaReq').append('<tr><td class="text-center">' + this.perspectiva_id + '</td>\n\
                        <td class="text-center">' + this.req_cantidad + '</td>\n\
                        <td>' + this.unidad_nombre + '</td>\n\
                        <td>' + this.req_nombre + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_unitario) + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_sin_iva) + '</td>\n\
                        <td class="text-center">' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td class="text-center"><input type="checkbox" value="' + this.req_id + '" name="rem" id="rem" checked="checked" data-cantidad="' + this.req_cantidad + '" data-costou="' + this.req_costo_unitario + '" data-costos="'+this.req_costo_sin_iva+'"></td></tr>\n\
                    </div>');
                    sumto = sumto + this.req_cantidad;
                    sum = sum + this.req_costo_unitario;
                    sumci = sumci + this.req_costo_sin_iva;
                    total = total + iva;
                });
                $('#listaRequerimientosUnificaReq').append('<tr><td colspan="4" class="text-center">TOTAL</td><td>' + new Intl.NumberFormat("US", options2).format(sum) + '</td><td>' + new Intl.NumberFormat("US", options2).format(sumci) + '</td><td>' + new Intl.NumberFormat("US", options2).format(total) + '</td></tr>');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    $('#unificarR').modal();
});

$('#example').on('click', 'tbody tr td .dat #elimReqUni', function () {
    var data = $(this).data();
    $('#reqidunif').val(data['id']);
    $('#eliminarModal').modal();
});

$('#modalElSolicitud').on('click', function () {
    var id = $('#reqidunif').val();
    $.ajax({
        url: "../solicitud?accion=EliminarRequerimientoUnif",
        type: 'POST',
        data: {req: id},
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

$('#modalGuardarJustUnific').on('click', function () {
    //Modificar
    var requerimiento = $('input[name=requnifid]').val();
    var costo = $('input[name=costounif]').val();
    var nombre = $('textarea[name=nombreunif]').val();
    var descripcion = $('textarea[name=descunif]').val();
    var table = $('#example').DataTable();
    var unidad = $('select[name=selunidaduni]').val();
    var re = [];
    $("input[name=rem]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(data['cantidad']);
        rem.push(data['costou']);
        rem.push(data['costos']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (costo === "" && $("input[name=partesid]:checked").val() === "undefined") {
        alert("Debe ingresar el costo unitario");
    } else if (nombre === "") {
        alert("Debe ingresar el nombre");
    } else if (descripcion === "") {
        alert("Debe ingresar la descripcion");
    } else {
        var formData = new FormData();
        formData.append('costo', costo);
        formData.append('req', req);
        formData.append('nombre', nombre);
        formData.append('descripcion', descripcion);
        formData.append('unidad', unidad);
        formData.append('requerimiento', requerimiento);
        formData.append('estado', estado);
        formData.append('partes', $("input[name=partesid]:checked").val());
        $.ajax({
            url: "../solicitud?accion=ModificarUnificacion",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        table
                                .clear()
                                .draw();
                        POAPLAN(t);
                        $('#unificarR').modal('hide');
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

$('#modalGuardarJustificativoG').on('click', function () {
    $('#listaProyectos').empty();
    $('input[name=centro]').val("");
    $('select[name=rectoring]').val(0);
    $('#cedulacustodio').val("");
    $('#nombrecustodio').val("");
    $('#cargocustodio').val("");

    var array = [];
    var sum = 0, sumto = 0;
    var options2 = {style: "currency", currency: "USD"};
    $('table#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'id': data.id,
                'nombre': data.nombre,
                'cantidad': data.cantidad,
                'costou': data.costou,
                'costoiva': data.costoiva,
                'costosin': data.costosin,
                'oei': data.oei,
                'iva': data.iva,
                'unidad': data.unidad});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var iva;
        if (array[i].iva === 1) {
            iva = array[i].costosin * 1.12;
        } else {
            iva = array[i].costosin;
        }
        $('#listaProyectos').append('<tr><td class="text-center">' + array[i].oei + '</td>\n\
                        <td>' + array[i].cantidad + '<input type="hidden" id="cantidadun' + array[i].id + '" name="cantidadun' + array[i].id + '" value="' + array[i].cantidad + '"></td>\n\
                        <td>' + array[i].unidad + '<input type="hidden" id="oeiuni' + array[i].id + '" name="oeiuni' + array[i].id + '" value="' + array[i].oei + '"></td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costou) + '<input type="hidden" id="costouun' + array[i].id + '" name="costouun' + array[i].id + '" value="' + array[i].costou + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costosin) + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-oei="' + array[i].oei + '"></td>\n\
                    </tr>');
        sumto = sumto + array[i].cantidad;
        sum = sum + array[i].costosin;
    }
    $('#listaProyectos').append('<tr><td colspan="6">TOTAL</td><td>' + new Intl.NumberFormat("US", options2).format(sum) + '</td></tr>');
    $('#generarJ').modal();
});

$('#modalGuardarJust').on('click', function () {
    var centrocosto = $('input[name=centro]').val();
    var ag = $('input[name=agsol]').val();
    var table = $('#example').DataTable();
    var autoridades = $('select[name=rectoring]').val();
    var cedula = $('#cedulacustodio').val();
    var nombre = $('#nombrecustodio').val();
    var cargo = $('#cargocustodio').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(data['oei']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (centrocosto === "") {
        alert("Debe ingresar el centro de costo.");
    } else if (cedula === "") {
        alert("Debe ingresar el numero de cedula del custodio.");
    } else if (nombre === "") {
        alert("Debe ingresar el nombre del custodio.");
    } else if (cargo === "") {
        alert("Debe ingresar el cargo del custodio.");
    } else {
        var formData = new FormData();
        formData.append('centro', centrocosto);
        formData.append('req', req);
        formData.append('ag', ag);
        formData.append('autoridades', autoridades);
        formData.append('cedula', cedula);
        formData.append('nombre', nombre);
        formData.append('cargo', cargo);
        formData.append('anio', $('#selectanio').val());
        $.ajax({
            url: "../solicitud?accion=IngresarSolicitudUnifi",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        table
                                .clear()
                                .draw();
                        POAPLAN(t);
                        $('#generarJ').modal('hide');
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