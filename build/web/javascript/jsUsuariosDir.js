/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#frmAddUsuarioCompras")[0].reset();
    listarUsuarioDirector($('#agu').val());
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
                if (response === "Correcto") {
                    alert("Ejecutada correctamente..");
                    $("#frmAddUsuarioCompras")[0].reset();
                    listarUsuarioDirector($('#agu').val());
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

function listarUsuarioDirector(tp) {
    $.ajax({
        url: "../usuario?accion=ListarUsuarioIDV" + "&tipo=" + tp,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaUsuarios').empty();
                var color, num = 1, ag;
                $.each(response, function () {
                    var listaAreas = [], estado;
                    $.each(this.areas, function (indice, area) {
                        //alert(area.ag.ag_nombre);
                        listaAreas.push(area.ag.ag_id);
                    });
                    if (this.usuario_estado === 1) {
                        estado = 'Activo';
                    } else {
                        estado = 'Inactivo';
                    }
                    if (this.tu_id == 16 || this.tu_id == 17 || this.tu_id == 10 || this.tu_id == 11 || this.tu_id == 26) {
                        ag = this.ag_nombres;
                    } else {
                        ag = " ";
                    }
                    $('#listaUsuarios').append('<tr><td>' + this.login_cedula + '</td>\n\
                                            <td>' + this.login_nombre + '</td>\n\
                                            <td>' + this.tu_nombre + '</td>\n\
                                            <td>' + ag + '</td>\n\
                                            <td>' + estado + '</td>\n\
                                            <td><i class="fas fa-edit dataInfo"  title="Editar Usuario" id="modUsuario"  \n\
                                            data-cedula="' + this.login_cedula + '" data-estado="' + this.usuario_estado + '" data-estadonombre="' + estado + '" data-nombre="' + this.login_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '" data-tu="' + this.tu_id + '" data-titulo="' + this.usuario_titulo + '" data-tunombre="' + this.tu_nombre + '"\n\
                                            ></i>\n\
                                             <i class="fas fa-trash-alt dataInfo"  title="Eliminar Usuario" id="eliUsuario"  \n\
                                            data-cedula="' + this.login_cedula + '" data-nombre="' + this.login_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '" \n\
                                            ></i></td>\n\
                                            </tr>');
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
    $('#tipou').find('option[value="' + data['tu'] + '"]').remove();
    $('#tipou').append('<option value="' + data['tu'] + '" selected="selected">' + data['tunombre'] + '</option>');
    $('#tipou').selectpicker('refresh');
    if (data['tu'] === 16 || data['tu'] === 17 || data['tu'] === 11 || data['tu'] === 10 || data['tu'] === 26) {
        $('#selecAgU').removeClass('d-none');
    } else {
        $('#selecAgU').addClass('d-none');
    }
    $("#selectEstado").removeClass('d-none');
    $('#selectEstadoU').find('option[value="' + data['estado'] + '"]').remove();
    $('#selectEstadoU').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#selectEstadoU').selectpicker('refresh');
    $("#ag option:selected").each(function () {
        $(this).removeAttr('selected');
    });

    $.each(data['areas'], function () {
        $('#ag option[value=' + this + ']').attr('selected', 'selected');
    });
    $('#ag').selectpicker('refresh');
    $('#frmAddUsuarioCompras').attr('action', '../usuario?accion=ModificarUsuario');
    window.location.href = '#frmAddUsuarioCompras';
    $(".bton").html('Modificar');
});

$('.table').on('click', '#listaUsuarios #eliUsuario ', function () {
    var data = $(this).data();
    $('#frmAddUsuarioCompras')[0].reset();
    $("#cedulaUsuario").val(data['cedula']);
    $("#nombreUsuario").val(data['nombre']);
    $('#frmAddUsuarioCompras').attr('action', '../usuario?accion=EliminarUsuario');
    $(".bton").html('Eliminar');
    /**/;
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
                listarUsuarioDirector(15);
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    /**/
});

$('#tipou').on('change', function () {
    let val = $(this).val();
    if (val === "26") {
        $('#selecAgU').removeClass('d-none');
    } else {
        $('#selecAgU').addClass('d-none');
    }
});
