/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
});

$(document).ready(function () {
    $('#frmAddRequerimientoGeneral')[0].reset();
    $("#bandera").val('1');
    $('#frmAddRequerimientoGeneral').attr('action', '../requerimientosGenerales?accion=IngresarRequerimientoGeneral');
    listarRequerimientosGenerales();
});

function listarRequerimientosGenerales() {
    $('#requerimientosGeneralesForm').empty();

    $.ajax({
        url: "../requerimientosGenerales?accion=ListarRequerimientoGenerales",
        data: {ag: $('#idAgObEs').val(), anio: $('#anioSe').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                var div, cpc, unidad;
                $.each(response, function () {
                    if (this.rg_cpc == null || this.rg_cpc == "undefined") {
                        cpc = '---';
                    } else {
                        cpc = this.rg_cpc;
                    }
                    if (this.rg_unidad == null || this.rg_unidad == "undefined" || this.rg_unidad == 0) {
                        unidad = '---';
                    } else {
                        unidad = this.unidad_nombre;
                    }
                    if (($('#validacion').val() == "true" && $('#anioSe').val() == this.ag_id) || ($('#validacion').val() == "false") && $('#anioSe').val() > this.ag_id) {
                        div = 'NO SE PUEDE EDITAR PORQUE HAY UN POA APROBADO';
                    } else {
                        div = '<button class="dataInfo"  data-id="' + this.rg_id + '" data-nombre="' + this.rg_nombre + '" data-descripcion="' + this.rg_descripcion + '" data-costo="' + this.rg_costo_unitario + '" data-anio="' + this.ag_id + '" data-cpc="' + this.rg_cpc + '" data-unidad="' + this.unidad_nombre + '" data-rgunidad="' + this.rg_unidad + '">Editar</button></td>';
                    }
                    $('#requerimientosGeneralesForm').append('<tr>\n\
                                                       <td class="text-justify">' + this.rg_nombre + '</td>\n\
                                                       <td class="text-justify">' + this.rg_descripcion + '</td>\n\
                                                       <td>' + formatter.format(this.rg_costo_unitario) + '</td>\n\
                                                       <td>' + cpc + '</td>\n\
                                                       <td>' + unidad + '</td>\n\
                                                       <td>' + this.ag_id + '</td>\n\
                                                       <td>' + div + '</tr>');
                });
            })
            .fail(function (e) {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('.table').on('click', '#requerimientosGeneralesForm .dataInfo ', function () {
    var data = $(this).data();
    $('#frmAddRequerimientoGeneral')[0].reset();
    $("#idRequerimientoGeneral").val(data['id']);
    $("#nombreRequerimientoGeneral").val(data['nombre']);
    $("#descripcionRequerimientoGeneral").val(data['descripcion']);
    $("#precioRequerimientoGeneral").val(data['costo']);
    $("#cpcRequerimientoGeneral").val(data['cpc']);
    $('#selAnio').find('option[value="' + data['anio'] + '"]').remove();
    $('#selAnio').append('<option value="' + data['anio'] + '" selected="selected">' + data['anio'] + '</option>');
    $('#selunidad1').find('option[value="' + data['rgunidad'] + '"]').remove();
    $('#selunidad1').append('<option value="' + data['rgunidad'] + '" selected="selected">' + data['unidad'] + '</option>');
    $('#selunidad1').selectpicker('refresh');
    $('#frmAddRequerimientoGeneral').attr('action', '../requerimientosGenerales?accion=ModificarRequerimientoGeneral');
    $("#bandera").val('2');
    $('#frmAddRequerimientoGeneral').removeClass('d-none');  
    var $this = $(this);
    var col = $this.index();
    var row = $this.closest('tr').index();
    listarAreasGestionDependientes(data['id'], col, row);
    window.location.href = '#frmAddRequerimientoGeneral';
});

$('#requerimientoIngresar').on('click', function () {
    $('#frmAddRequerimientoGeneral')[0].reset();
    $("#bandera").val('1');
    $("#nombreRequerimientoGeneral").val('');
    $("#descripcionRequerimientoGeneral").val('');
    $("#precioRequerimientoGeneral").val('');
    $("#cpcRequerimientoGeneral").val('');
    $("#idRequerimientoGeneral").val('');
    $('#frmAddRequerimientoGeneral').attr('action', '../requerimientosGenerales?accion=IngresarRequerimientoGeneral');
    $('#frmAddRequerimientoGeneral').removeClass('d-none');   
    
});

$('#frmAddRequerimientoGeneral').submit(function (event) {
    var bandera = $("#bandera").val();
    event.preventDefault();
    var metodo = $(this).attr("method");
    var accion = $(this).attr("action");
    $.ajax({
        url: accion,
        type: metodo,
        dataType: 'json',
        cache: false,
        data: $('#frmAddRequerimientoGeneral').serialize()
    })
            .done(function (response) {
                listarRequerimientosGenerales();
                $('#frmAddRequerimientoGeneral').addClass('d-none');   
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });


});

function listarAreasGestionDependientes(id, col, row) {
    $('#areasGestionDependientes').empty();
    $.ajax({
        url: "../requerimientosGenerales?accion=ListarAreasGestionDependientes",
        type: 'POST',
        dataType: 'json',
        data: {idRequerimientoGeneral: id},
    })
            .done(function (response) {
                if (!$.isEmptyObject(response))
                {
                    var fila = $('<tr>');
                    var texto = $('<p>Este requerimiento ha sido formulado por las siguientes unidades:</p>');
                    var columna = $('<td class="text-justify" colspan="4"></td>');
                    var contenido = $('<ol class="lista"></ol>');
                    $.each(response, function () {
                        contenido.append($('<li >' + this.ag_nombre + '</li>'));
                    });
                    columna.append(texto);
                    columna.append(contenido);
                    fila.append(columna)
                    fila.insertBefore($('#requerimientosGeneralesForm tr:nth(' + (row + 1) + ')'));
                }

            })
            .fail(function (e) {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });


}

