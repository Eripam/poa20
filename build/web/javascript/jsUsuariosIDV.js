/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $("#frmAddUsuarioCompras")[0].reset();
    listarUsuarioIDV($('#agu').val());
});

$('#tipou').on('change', function () {
    let val = $(this).val();
    if (val === "15" || val === "18") {
        $('#selecAgU').addClass('d-none');
    } else {
        $('#selecAgU').removeClass('d-none');
    }
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

function listarUsuarioIDV(tp) {
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../usuario?accion=ListarUsuarioIDV" + "&tipo=" + tp,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaUsuarios').empty();
                var color, num = 1;
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
                    $('#listaUsuarios').append('<tr><td>' + this.login_cedula + '</td>\n\
                                            <td>' + this.login_nombre + '</td>\n\
                                            <td id="area-' + this.login_cedula + this.tu_id + '"></td>\n\
                                            <td>' + this.tu_nombre + '</td>\n\
                                            <td>' + estado + '</td>\n\
                                            <td><i class="fas fa-edit dataInfo"  title="Editar Usuario" id="modUsuario"  \n\
                                            data-cedula="' + this.login_cedula + '" data-nombre="' + this.login_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '" data-tu="' + this.tu_id + '" data-tunombre="' + this.tu_nombre + '"\n\
                                             data-estado="' + this.usuario_estado + '" data-estadonombre="' + estado + '" data-titulo="'+this.usuario_titulo+'"></i>\n\
                                             <i class="fas fa-trash-alt dataInfo"  title="Eliminar Usuario" id="eliUsuario"  \n\
                                            data-cedula="' + this.login_cedula + '" data-nombre="' + this.login_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '" data-tipo="'+this.tu_id+'" data-area="'+this.anio+'"\n\
                                            ></i></td>\n\
                                            </tr>');
                    var cedula = this.login_cedula, tu = this.tu_id;
                    if (tu === 16 || tu === 17) {
                        $.each(this.areas, function () {
                            $('#area-' + cedula + tu).append('<p>' + this.ag.ag_nombre + '</p>');
                        });
                    }
                });
                $('#frmAddUsuarioCompras').attr('action', '../usuario?accion=IngresarUsuario');
                $('#loader').addClass('d-none');
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
    $("#selectEstado").removeClass('d-none');
    $('#selectEstadoU').find('option[value="' + data['estado'] + '"]').remove();
    $('#selectEstadoU').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#selectEstadoU').selectpicker('refresh');
    $('#frmAddUsuarioCompras').attr('action', '../usuario?accion=ModificarUsuario');
    $('#tipou').find('option[value="' + data['tu'] + '"]').remove();
    $('#tipou').append('<option value="' + data['tu'] + '" selected="selected">' + data['tunombre'] + '</option>');
    $('#tipou').selectpicker('refresh');
    if (data['tu'] === 16 || data['tu'] === 17) {
        $('#selecAgU').removeClass('d-none');
    } else {
        $('#selecAgU').addClass('d-none');
    }
    $("#ag option:selected").each(function () {
        $(this).removeAttr('selected');
    });

    $.each(data['areas'], function () {
        $('#ag option[value=' + this + ']').attr('selected', 'selected');
        //$('#ag').selectpicker('val', this);
    });
    $('#ag').selectpicker('refresh');
        window.location.href = '#frmAddUsuarioCompras';
});


$('.table').on('click', 'tbody tr td #eliUsuario ', function () {
    var data = $(this).data();
    $('#frmAddUsuarioCompras')[0].reset();
    $('#bodyModal').html('ESTA SEGURO QUE DESEA DESACTIVAR AL USUARIO <p style="font-weight:bold">' + data['nombre'] + ' </p></p><input type="hidden" id="ceduladesac" value="' + data['cedula'] + '">\n\
    <input type="hidden" id="areadesac" value="' + data['area'] + '"><input type="hidden" id="tipodesac" value="' + data['tipo'] + '">');
    $('#enviarReq').modal();
});

$('#modalGuardarJustEnv').on('click', function () {
    $.ajax({
        url: '../usuario?accion=DesactivarUsuaurio',
        type: 'POST',
        dataType: 'json',
        cache: false,
        data: {cedula: $('#ceduladesac').val(), area: $('#areadesac').val(), tipo: $('#tipodesac').val(), cedulauser: $('#cedulaProyecto').val()}
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

