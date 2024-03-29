/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#frmAddUsuarioCompras")[0].reset();
    listarUsuarioCompras(10);
});

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
                alert("Se modific\u00f3 correctamente..");
                $("#frmAddUsuarioCompras")[0].reset();
                listarUsuarioCompras(10);
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completó correctamente');
            });


});

function listarUsuarioCompras(tp) {
    $.ajax({
        url: "../usuario?accion=ListarUsuario" + "&tipo=" + tp,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaUsuarios').empty();
                var color, num = 1;
                $.each(response, function () {
                    var listaAreas = [];
                    $.each(this.areas, function (indice, area) {
                        //alert(area.ag.ag_nombre);
                        listaAreas.push(area.ag.ag_id);
                    });
                    $('#listaUsuarios').append('<tr><td>' + this.login_cedula + '</td>\n\
                                            <td>' + this.login_nombre + '</td>\n\
                                            <td id="area-' + this.login_cedula + '"></td>\n\
                                            <td><i class="fas fa-edit dataInfo"  title="Editar Usuario" id="modUsuario"  \n\
                                            data-cedula="' + this.login_cedula + '" data-titulo="'+this.usuario_titulo+'" data-nombre="' + this.login_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '" \n\
                                            ></i>\n\
                                            <a id="btnEliminarRequerimiento" data-toggle="modal" href="#eliminarModal" data-cedula="' + this.login_cedula + '" data-nombre="' + this.login_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '">\n\
                                                <i class="fas fa-trash-alt dataInfo"  title="Eliminar Usuario" id="eliUsuario"></i>\n\
                                            </a> \n\
                                            </td>\n\
                                            </tr>');
                    var cedula = this.login_cedula;
                    $.each(this.areas, function () {
                        $('#area-' + cedula).append('<p>' + this.ag.ag_nombre + '</p>');
                    });
                });
                $(".bton").html('Ingresar');
                $('#frmAddUsuarioCompras').attr('action', '../usuario?accion=IngresarUsuario');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('.table').on('click', '#listaUsuarios #modUsuario ', function () {
    var data = $(this).data();
    $('#frmAddUsuarioCompras')[0].reset();
    $("#cedulaUsuario").val(data['cedula']);
    $("#nombreUsuario").val(data['nombre']);
    $("#tituloUsuario").val(data['titulo']);
    $('#frmAddUsuarioCompras').attr('action', '../usuario?accion=ModificarUsuario');
    $("#ag option:selected").each(function () {
        $(this).removeAttr('selected');
    });

    $.each(data['areas'], function () {
        $('#ag option[value=' + this + ']').attr('selected', 'selected');
        //$('#ag').selectpicker('val', this);
    });
    $('#ag').selectpicker('refresh');
    $(".bton").html('Modificar');
    window.location.href = '#frmAddUsuarioCompras';
});

$('.table').on('click', '#listaUsuarios #btnEliminarRequerimiento ', function () {
    var data = $(this).data();
    $("#cedulaUsuarioModal").val(data['cedula']);
    $("#nombreUsuarioModal").val(data['nombre']);
    //alert(data['cedula']);
});

$('#eliminarModalBtn').on('click', function () {
    var cedula = $('#cedulaUsuarioModal').val();
    var tipo = $('#tipou').val();

    $.ajax({
        url: '../usuario?accion=EliminarUsuario',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: {cedulaUsuario: cedula, tipou: tipo}
    })
            .done(function (response) {
                listarUsuarioCompras(10);
                $('#eliminarModal').modal('hide');
                alert("Observación realizada correctamente!");
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});
