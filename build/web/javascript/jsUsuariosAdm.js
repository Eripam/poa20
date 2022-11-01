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

$(document).ready(function () {
    $("#frmAddUsuarioCompras")[0].reset();
    listarUsuarioIDV($('#agu').val());
});

$('#tipou').on('change', function () {
    let val = $(this).val();
    if (val !== "10" && val !== "11" && val !== "16" && val !== "17" && val!=="26") {
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
                    window.location.reload();
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

function listarUsuarioIDV() {
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../usuario?accion=ListarUsuariosAdm",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaUsuarios').empty();
                var addID, color, num = 1, ag;
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
                    if (this.tu_id == 16 || this.tu_id == 17 || this.tu_id == 10 || this.tu_id == 11 || this.tu_id==26) {
                        ag = this.ag_nombres;
                    } else {
                        ag = " ";
                    }
                    addID = t.row.add(['<tr><td style="text-align:justify;">' + this.login_cedula + '</td>', '<td>' + this.login_nombre + '</td>', '<td>' + this.ag_nombre + '</td>',
                        '<td>' + ag + '</td>', '<td>' + this.tu_nombre + '</td>', '<td>' + estado + '</td>', '<td><i class="fas fa-edit dataInfo"  title="Editar Usuario" id="modUsuario"  \n\
                                            data-cedula="' + this.login_cedula + '" data-nombre="' + this.login_nombre + '" data-titulo="' + this.usuario_titulo + '" data-agid="' + this.anio + '" data-agnombre="' + this.ag_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '" data-tu="' + this.tu_id + '" \n\
                                            data-tunombre="' + this.tu_nombre + '" data-estado="' + this.usuario_estado + '" data-estadonombre="'+estado+'"></i>\n\
                                             <i class="fas fa-trash-alt dataInfo"  title="Eliminar Usuario" id="eliUsuario"  \n\
                                            data-cedula="' + this.login_cedula + '" data-titulo="' + this.usuario_titulo + '" data-nombre="' + this.login_nombre + '" data-area="' + this.anio + '" data-agnombre="' + this.ag_nombre + '" data-areas="' + JSON.stringify(listaAreas) + '" data-tipo="' + this.tu_id + '"\n\
                                            ></i></td></tr>']).draw(false);
                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                    theNode.setAttribute('id', this.login_cedula);
                    var cedula = this.login_cedula, tu = this.tu_id;
                    if (this.tu_id == 16 || this.tu_id == 17 || this.tu_id == 10 || this.tu_id == 11 || this.tu_id == 26) {
                        $.each(this.areas, function () {
                            $('#area-' + cedula + tu).append('<p>' + this.ag.ag_nombre + '</p>');
                        });
                    }
                });
                $('form').children(".bton").html('Ingresar');
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

$('.table').on('click', 'tbody tr td #modUsuario', function () {
    var data = $(this).data();
    $('#frmAddUsuarioCompras')[0].reset();
    $("#cedulaUsuario").val(data['cedula']);
    $("#nombreUsuario").val(data['nombre']);
    $("#tituloUsuario").val(data['titulo']);
    $("#selectEstado").removeClass('d-none');
    $('#frmAddUsuarioCompras').attr('action', '../usuario?accion=ModificarUsuario');
    $('#tipou').find('option[value="' + data['tu'] + '"]').remove();
    $('#tipou').append('<option value="' + data['tu'] + '" selected="selected">' + data['tunombre'] + '</option>');
    $('#tipou').selectpicker('refresh');
    $('#selectEstadoU').find('option[value="' + data['estado'] + '"]').remove();
    $('#selectEstadoU').append('<option value="' + data['estado'] + '" selected="selected">' + data['estadonombre'] + '</option>');
    $('#selectEstadoU').selectpicker('refresh');
    $('#agu').find('option[value="' + data['agid'] + '"]').remove();
    $('#agu').append('<option value="' + data['agid'] + '" selected="selected">' + data['agnombre'] + '</option>');
    $('#agu').selectpicker('refresh');
    if (data['tu'] === 16 || data['tu'] === 17 || data['tu'] === 11 || data['tu'] === 10 || data['tu'] === 26) {
        $('#selecAgU').removeClass('d-none');
    } else {
        $('#selecAgU').addClass('d-none');
    }
    $("#ag option:selected").each(function () {
        $(this).removeAttr('selected');
    });

    $.each(data['areas'], function () {
        $('#ag option[value=' + this + ']').attr('selected', 'selected');
    });
    $('#ag').selectpicker('refresh');
    $(".bton").html('Modificar');
    window.location.href = '#frmAddUsuarioCompras';
});

$('.table').on('click', 'tbody tr td #eliUsuario ', function () {
    var data = $(this).data();
    $('#frmAddUsuarioCompras')[0].reset();
    $('#bodyModal').html('ESTA SEGURO QUE DESEA DESACTIVAR AL USUARIO <p style="font-weight:bold">' + data['nombre'] + ' </p>DE LA DEPENDENCIA <p style="font-weight:bold">' + data['agnombre'] + '</p><input type="hidden" id="ceduladesac" value="' + data['cedula'] + '">\n\
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
