/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var groupColumn = 0;
var options2 = {style: "currency", currency: "USD"};
var t = $('#example').DataTable({
    "order": [[groupColumn, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]]
}).clear().draw();

$('#frmAddUsuarioCompras').submit(function (event) {
    event.preventDefault();
    var metodo = $(this).attr("method");
    var accion = $(this).attr("action");
    $.ajax({
        url: accion,
        type: metodo,
        dataType: 'json',
        cache: false,
        data: $('#frmAddUsuarioCompras').serialize()
    })
            .done(function (response) {
                //listarRequerimientosGenerales();
                //$("#bandera").val('1');
                if (response === "Correcto") {
                    alert("Se insert\u00f3 correctamente..");
                    $("#frmAddUsuarioCompras")[0].reset();
                    listarUsuarioIDV($('#agu').val());
                } else {
                    alert(response);
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completó correctamente');
            });


});

$('#buscarCod').on('click', function () {
    t
            .clear()
            .draw();
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../actividadReq?accion=ListarActividadesProy",
        data: {codigo: $('#codigoP').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaUsuarios').empty();
                var addID;
                $.each(response, function (indice, proyecto) {
                    if (proyecto.componente.length > 0) {
                        $.each(this.componente, function (indice, componente) {
                            if (componente.actividad.length > 0) {
                                $.each(componente.actividad, function (indice, actividad) {
                                    var po1 = 0, po2 = 0, po3 = 0;
                                    if (this.cuatri.length > 0) {
                                        $.each(this.cuatri, function (indice, c) {
                                            po1 = c.c1;
                                            po2 = c.c2;
                                            po3 = c.c3;
                                        });
                                    }
                                    var mesac = [];
                                    $.each(this.mes, function (indice, mes) {
                                        mesac.push(mes.mes_id);
                                    });

                                    addID = t.row.add(['<tr><td style="text-align:justify;">' + proyecto.ag_nombre + '</td>', '<td style="text-align:left;">' + proyecto.proyecto_nombre + '</td>', '<td style="text-align:left;">' + componente.componente_nombre + '</td>', '<td class="text-left">' + actividad.actividad_nombre + '</td>','<td>'+actividad.actividad_responsable+'</td>',
                                        '<td>' + new Intl.NumberFormat("US", options2).format(actividad.actividad_monto) + '</td>', '<td><i class="fas fa-edit dataInfo" data-actividad="' + actividad.actividad_id + '" data-actividadid="' + actividad.ae_tiempo + '" data-nombre="' + actividad.actividad_nombre + '" data-cuatri1="' + po1 + '" data-cuatri2="' + po2 + '" data-cuatri3="' + po3 + '" data-mes="' + JSON.stringify(mesac) + '" data-fi="' + proyecto.fecha_inicio + '" data-ff="' + proyecto.fecha_fin + '" \n\
                                    data-cuatrimestre="' + actividad.autoridades_id + '" title="Modificar Cuatrimestre" id="modActividad"></i>  \n\
                                    <i class="fas fa-trash-alt dataInfo" data-actividad="' + actividad.actividad_id + '" data-actividadid="' + actividad.ae_tiempo + '" data-nombre="' + actividad.actividad_nombre + '"  title="Eliminar Actividad" id="eliActividad"></i><i class="fas fa-user" id="resAct" data-actividad="' + actividad.actividad_id + '" data-actividadid="' + actividad.ae_tiempo + '" data-responsable="'+actividad.actividad_responsable+'" data-nombre="'+actividad.actividad_nombre+'" title="Cambiar responsable"></i></td></tr>']).draw(false);
                                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                                    theNode.setAttribute('id', this.proyecto_id);
                                });
                            }
                        });
                    }
                });
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

$('.table').on('click', 'tbody tr td #eliActividad', function () {
    var data = $(this).data();
    $('#nombrePE').html('<input type="hidden" name="actelim" id="actelim" value="' + data['actividad'] + '">' + data['nombre']);
    $('#eliminarProy').modal();
});

$('.table').on('click', 'tbody tr td #resAct', function () {
    var data = $(this).data();
    $('#nombrePER').html('<input type="hidden" name="actresp" id="actresp" value="' + data['actividad'] + '"><input type="hidden" name="actrespid" id="actrespid" value="' + data['actividadid'] + '">' + data['nombre']);
    $('#inpResponsableAc').val(data['responsable']);
    $('#responsableAct').modal();
});

$('.table').on('click', 'tbody tr td #modActividad', function () {
    var data = $(this).data();
    var fi = data['fi'];
    var ff = data['ff'];
    var cuatrimestre = data['cuatrimestre'];
    $('#nombreP').html('<input type="hidden" name="idact" id="idact" value="' + data['actividad'] + '"><input type="hidden" name="ididact" id="ididact" value="' + data['actividadid'] + '">' + data['nombre'])
    var mes = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
    var fecha1 = fi.split("-");
    var fecha2 = ff.split("-");
    var fia = new Date(fecha1[0], fecha1[1] - 1, fecha1[2]);
    var ffa = new Date(fecha2[0], fecha2[1] - 1, fecha2[2]);
    $('#programacionactividad').empty();
    $('#pori').css({"display": "none"});
    $('#porii').css({"display": "none"});
    $('#poriii').css({"display": "none"});
    var j = 1;
    for (var i = 0; i < mes.length; i++) {
        if ((j >= (fia.getMonth() + 1))) {
            if ((j <= (ffa.getMonth() + 1))) {
                console.log(cuatrimestre);
                if(cuatrimestre==0 && i<4){
                    $('#frmIngresarCP').children('.container-fluid').children('.row').children('.mesactividad').children('#programacionactividad').append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes" id="checkmes"><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
                }else if(cuatrimestre==0 && i>3 && i<8){
                    $('#frmIngresarCP').children('.container-fluid').children('.row').children('.mesactividad').children('#programacionactividad').append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes" id="checkmes"><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
                }else if(cuatrimestre==0 && i>7){
                    $('#frmIngresarCP').children('.container-fluid').children('.row').children('.mesactividad').children('#programacionactividad').append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes" id="checkmes"><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
                }else if (cuatrimestre == 2 && i > 7) {
                    $('#frmIngresarCP').children('.container-fluid').children('.row').children('.mesactividad').children('#programacionactividad').append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes" id="checkmes"><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
                } else if (cuatrimestre == 1 && i > 3) {
                    $('#frmIngresarCP').children('.container-fluid').children('.row').children('.mesactividad').children('#programacionactividad').append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes" id="checkmes"><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
                } else {
                    $('#frmIngresarCP').children('.container-fluid').children('.row').children('.mesactividad').children('#programacionactividad').append('<div class="form-check col-12 col-xs-12 col-md-3"><input class="form-check-input" type="checkbox" value="' + j + '" name="checkmes" id="checkmes" disabled><label class="form-check-label"  style="font-weight: normal;">' + mes[i] + '</label></div>');
                }
            }
        }
        j++;
    }

    if (data['mes'].length > 0) {
        for (var k = 0; k < data['mes'].length; k++) {
            $('[name="checkmes"][value="' + data['mes'][k] + '"]').prop("checked", true);
        }

    }
    if (data['cuatri1'] > 0) {
        $('#pori').removeClass('d-none');
        $('#pori').addClass('d-flex');
        $('#porcentajei').val(data['cuatri1'].toFixed(2)).removeAttr("readonly");
    } else {
        $('#pori').removeClass('d-flex');
        $('#pori').addClass('d-none');
        $('#porcentajei').val("").removeAttr("readonly");
    }
    if (data['cuatri2'] > 0) {
        $('#porii').removeClass('d-none');
        $('#porii').addClass('d-flex');
        $('#porcentajeii').val(data['cuatri2'].toFixed(2)).removeAttr("readonly");
    } else {
        $('#porii').removeClass('d-flex');
        $('#porii').addClass('d-none');
        $('#porcentajeii').val("").removeAttr("readonly");
    }
    if (data['cuatri3'] > 0) {
        $('#poriii').removeClass('d-none');
        $('#poriii').addClass('d-flex');
        $('#porcentajeiii').val(data['cuatri3'].toFixed(2)).removeAttr("readonly");
    } else {
        $('#poriii').removeClass('d-flex');
        $('#poriii').addClass('d-none');
        $('#porcentajeiii').val("").removeAttr("readonly");
    }

    if (cuatrimestre == 3) {
        $('#porcentajeiii').attr("readonly", true);
        $('#porcentajeii').attr("readonly", true);
        $('#porcentajei').attr("readonly", true);
    } else if (cuatrimestre == 2) {
        $('#porcentajeiii').attr("readonly", false);
        $('#porcentajeii').attr("readonly", true);
        $('#porcentajei').attr("readonly", true);
    } else if (cuatrimestre == 1) {
        $('#porcentajeiii').attr("readonly", false);
        $('#porcentajeii').attr("readonly", false);
        $('#porcentajei').attr("readonly", true);
    } else {
        $('#porcentajeiii').attr("readonly", false);
        $('#porcentajeii').attr("readonly", false);
        $('#porcentajei').attr("readonly", false);
    }

    $('#modificarProy').modal();
});

//Check
$('#frmIngresarCP').on('change', '.container-fluid .row .mesactividad',function () {
    var data = $(this).data();
    var i = [1, 2, 3, 4];
    var ii = [5, 6, 7, 8];
    var iii = [9, 10, 11, 12];
    var cont1 = 0, cont2 = 0, cont3 = 0;
    $('#checkmes:checked').each(
            function () {
                for (var k = 0; k < i.length; k++) {
                    if (i[k] == $(this).val()) {
                        cont1++;
                    }
                }
                for (var k = 0; k < ii.length; k++) {
                    if (ii[k] == $(this).val()) {
                        cont2++;
                    }
                }
                for (var k = 0; k < iii.length; k++) {
                    if (iii[k] == $(this).val()) {
                        cont3++;
                    }
                }
            }
    );
    if (cont1 > 0) {
        $('#pori').removeClass('d-none');
        $('#pori').addClass('d-flex');
    } else {
        $('#pori').removeClass('d-flex');
        $('#pori').addClass('d-none');
        $('#porcentajei').val("");
    }
    if (cont2 > 0) {
        $('#porii').removeClass('d-none');
        $('#porii').addClass('d-flex');
    } else {
        $('#porii').removeClass('d-flex');
        $('#porii').addClass('d-none');
        $('#porcentajeii').val("");
    }
    if (cont3 > 0) {
        $('#poriii').removeClass('d-none');
        $('#poriii').addClass('d-flex');
    } else {
        $('#poriii').removeClass('d-flex');
        $('#poriii').addClass('d-none');
        $('#porcentajeiii').val("");
    }
});

$('#modalGuardarEstados').on('click', function () {
    var cua1 = [];
    var cua2 = [];
    var cua3 = [];
    for (var i = 1; i <= 4; i++) {
        $('#checkmes:checked').each(
                function () {
                    if (i == $(this).val()) {
                        cua1.push($(this).val());
                    }
                }
        );
    }
    for (var i = 5; i <= 8; i++) {
        $('#checkmes:checked').each(
                function () {
                    if (i == $(this).val()) {
                        cua2.push($(this).val());
                    }
                }
        );
    }
    for (var i = 9; i <= 12; i++) {
        $('#checkmes:checked').each(
                function () {
                    if (i == $(this).val()) {
                        cua3.push($(this).val());
                    }
                }
        );
    }

    var c1 = JSON.stringify(cua1);
    var c2 = JSON.stringify(cua2);
    var c3 = JSON.stringify(cua3);
    $.ajax({
        url: '../actividadReq?accion=ModificarActividadCuatr',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: {cuatrimestre1: c1, cuatrimestre2: c2, cuatrimestre3: c3, porcentaje1: $('#porcentajei').val(), porcentaje2: $('#porcentajeii').val(), porcentaje3: $('#porcentajeiii').val(),
            actividad: $('#idact').val(), actividadid: $('#ididact').val(), cedula: $('#cedulaProyecto').val()}
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

$('#modalResponsableA').on('click', function () {
    $.ajax({
        url: '../actividadReq?accion=ModificarActividadResp',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: {actividad: $('#actresp').val(), actividadid: $('#actrespid').val(), cedula: $('#cedulaProyecto').val(), responsable:$('#inpResponsableAc').val(), observacion:$('#txtObservElR').val()}
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

$('#modalEliminarEstados').on('click', function () {
    if ($('#txtObservEl').val() == null || $('#txtObservEl').val() == "undefined" || $('#txtObservEl').val() == "") {
        alert("Debe ingresar la observaci\u00f3n.");
    } else {
        $.ajax({
            url: '../actividadReq?accion=EliminarActividadCuatr',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: {actividad: $('#actelim').val(), cedula: $('#cedulaProyecto').val()}
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

$('#modalEliminarEstados').on('click', function () {
    $.ajax({
        url: '../proyecto?accion=EliminarEstado',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: {cedulaProyecto: $('#cedulaProyecto').val(), proyecto: $('#idproye').val(), estado: $('#idestado').val(), fecha: $('#fechae').val(), cedula: $('#usuarioe').val(), observacion: $('#observacione').val()}
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
})
