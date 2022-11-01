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
    listarUsuarioIDV();
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

function listarUsuarioIDV() {
    $('#loader').removeClass('d-none');
    $.ajax({
        url: "../proyecto?accion=ListarProyectoCom",
        data: {anio: $('#anio').val()},
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#listaUsuarios').empty();
                var addID, estado, observacion;
                $.each(response, function () {
                    if (this.estado_nombre == null || this.estado_nombre == '' || this.estado_nombre == "undefined") {
                        estado = "---";
                    } else {
                        estado = this.estado_nombre;
                    }
                    if (this.estado_observacion == null || this.estado_observacion == '' || this.estado_observacion == "undefined") {
                        observacion = "---";
                    } else {
                        observacion = this.estado_observacion;
                    }
                    addID = t.row.add(['<tr><td style="text-align:justify;">' + this.proyecto_id + '</td>', '<td>' + this.proyecto_fin + '</td>', '<td>' + this.proyecto_nombre + '</td>', '<td>' + this.proyecto_responsable + '</td>',
                        '<td>' + new Intl.NumberFormat("US", options2).format(this.proyecto_monto) + '</td>', '<td>' + estado + '</td>', '<td>' + observacion + '</td>', '<td><i class="fas fa-plus dataInfo" data-proyecto="' + this.proyecto_id + '" data-nombre="' + this.proyecto_nombre + '" data-responsable="' + this.proyecto_responsable + '" data-integrantes="' + this.integrantes + '" title="Agregar estado" id="agregarEstado"></i>  \n\
                    <i class="fas fa-trash-alt dataInfo" data-proyecto="' + this.proyecto_id + '" data-nombre="' + this.proyecto_nombre + '" data-fecha="' + this.deudas_contrato + '" data-usuario="' + this.usuario_nombre + '" data-estadoid="' + this.estado_id + '" data-estado="' + estado + '" data-observacion="'+observacion+'" title="Eliminar Ultimo Estado" id="eliProyecto"></i><a href="pProyectoL.jsp?id=' + this.proyecto_id + '" class="ml-1" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a><i class="fas fa-user" data-proyecto="' + this.proyecto_id + '" data-nombre="' + this.proyecto_nombre + '" data-usuario="' + this.proyecto_responsable + '" id="userProyecto" title="Cambiar responsable"></i></td></tr>']).draw(false);
                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                    theNode.setAttribute('id', this.proyecto_id);
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

$('.table').on('click', 'tbody tr td #agregarEstado', function () {
    var data = $(this).data();
    $('#nombreP').html('<input type="hidden" name="idproy" id="idproy" value="' + data['proyecto'] + '">' + data['nombre']);
    $('#modificarProy').modal();
});

$('.table').on('click', 'tbody tr td #userProyecto', function () {
    var data = $(this).data();
    $('#nombrePR').html('<input type="hidden" name="idproyr" id="idproyr" value="' + data['proyecto'] + '">' + data['nombre']);
    $('#txtResponsable').val(data['usuario']);
    $('#responsableProy').modal();
});

$('.table').on('click', 'tbody tr td #eliProyecto', function () {
    var data = $(this).data();
    if (data['estado'] == "---") {
        alert("No se puede eliminar un estado en este proyecto, porque no ha sido enviado");
    } else {
        $('#nombrePE').html('<input type="hidden" name="idproye" id="idproye" value="' + data['proyecto'] + '">' + data['nombre'])
        $('#estadoP').html('<input type="hidden" name="idestado" id="idestado" value="' + data['estadoid'] + '"><input type="hidden" name="fechae" id="fechae" value="' + data['fecha'] + '"><input type="hidden" name="usuarioe" id="usuarioe" value="' + data['usuario'] + '"><input type="hidden" name="observacione" id="observacione" value="' + data['observacion'] + '">' + data['estado'])
        $('#eliminarProy').modal();
    }
});

$('#modalGuardarEstados').on('click', function () {
    if ($('#txtObservacionP').val() == null || $('#txtObservacionP').val() == "" || $('#txtObservacionP').val() == "undefined") {
        alert("Se debe agregar un breve comentario del porque se agrega un estado");
    } else {
        $.ajax({
            url: '../proyecto?accion=IngresarEstado',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: {cedulaProyecto: $('#cedulaProyecto').val(), proyecto: $('#idproy').val(), estado: $('#estadosP').val(), observacion: $('#txtObservacionP').val()}
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

$('#modalGuardarResponsable').on('click', function () {
    if ($('#txtObservacionPR').val() == null || $('#txtObservacionPR').val() == "" || $('#txtObservacionPR').val() == "undefined") {
        alert("Se debe agregar un breve comentario porque se modificar el responsable.");
    } else  if ($('#txtResponsable').val() == null || $('#txtResponsable').val() == "" || $('#txtResponsable').val() == "undefined")  {
        alert("Debe ingresar el responsable del proyecto");
    }else{
        $.ajax({
            url: '../proyecto?accion=ResponsableProy',
            type: 'POST',
            dataType: 'json',
            cache: false,
            data: {cedulaProyecto: $('#cedulaProyecto').val(), proyecto: $('#idproyr').val(), observacion: $('#txtObservacionPR').val(), responsable:$('#txtResponsable').val()}
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
            data: {cedulaProyecto: $('#cedulaProyecto').val(), proyecto: $('#idproye').val(), estado: $('#idestado').val(), fecha:$('#fechae').val(), cedula:$('#usuarioe').val(), observacion:$('#observacione').val()}
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
