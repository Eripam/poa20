var banDeuda = false, banAuto = false;
$(document).ready(function () {
    listarDeudas();
});

$('#FrmArchivosEvidencias').submit(function (event) {
    event.preventDefault();
    if ($('#descarchivo').val() == null || $('#descarchivo').val() == '') {
        alert("Debe ingresar la descripci\u00f3n del archivo.");
    } else if (banDeuda) {
        var archivo = document.getElementById("archivoactividad");
        // the file is the first element in the files property
        var file = archivo.files[0];
        if (file == null) {
            alert("Debe ingresar el archivo");
        } else {
            var doc = document.getElementById('archivoactividad').files[0].name;
            if (comprueba_extensionE(doc) === 1) {
                $.ajax({
                    url: "../evaluacion?accion=IngresarEvidenciasDeudas",
                    type: 'POST',
                    dataType: 'json',
                    cache: false,
                    processData: false,
                    data: new FormData($('#FrmArchivosEvidencias')[0]),
                    contentType: false
                })
                        .done(function (response) {
                            if (response === "Correcto") {
                                listarDeudas();
                                $('#FrmArchivosEvidencias').modal('hide');
                                $("#FrmArchivosEvidencias")[0].reset();
                                $('#FrmArchivosEvidencias').addClass('d-none');
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
            } else {
                alert("Verifique que el archivo sea .pdf, .rar o .zip");
            }
        }
    } else {
        $.ajax({
            url: "../evaluacion?accion=ModificarEvidenciasDeudas",
            type: 'POST',
            data: {id: $('#iddeudas').val(), descripcion: $('#descarchivo').val(), cedulaProyecto: $('#cedulaProyecto').val()},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listarDeudas();
                        $('#FrmArchivosEvidencias').modal('hide');
                        $("#FrmArchivosEvidencias")[0].reset();
                        $('#FrmArchivosEvidencias').addClass('d-none');
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



//Comprobar extension
function comprueba_extensionE(archivo) {
    $('#archivoactividad').empty();
    var extensiones_permitidas = new Array(".pdf", ".rar", ".zip");
    //recupero la extensión de este nombre de archivo 
    var extension = (archivo.substring(archivo.lastIndexOf("."))).toLowerCase();
    //compruebo si la extensión está entre las permitidas 
    var permitida = false;
    for (var i = 0; i < extensiones_permitidas.length; i++) {

        if (extensiones_permitidas[i] === extension) {
            permitida = true;
            break;
        }
    }
    if (!permitida) {
        alert("Compruebe la extensi\u00f3n de los archivos a subir.");
    } else {
        return 1;
    }
    return 0;
}

function listarDeudas() {
    $.ajax({
        url: "../proyecto?accion=ListarDeudasEval" + "&ag=" + $('#idAgObEs').val() + "&anio=" + $('#selectanio').val(),
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#proyectosForm').empty();
                var estado, div, st = 0, siva = 0, santicipo = 0, spendiente = 0, stotalpe = 0, tipo, div2, estadoid;
                $.each(response, function () {
                    estadoid = this.estado_id;
                    if (this.estado_id === 0) {
                        estado = '----';
                    } else {
                        estado = this.estado_nombre;
                    }

                    if (this.tp_id === 1) {
                        tipo = "Obligaciones Pendientes";
                    } else {
                        tipo = "Comprometidos No Deveng";
                    }

                    if (this.estado_id === 0 || this.estado_id === 3) {
                        div = '<i class="fas fa-plus m-1" title="Agregar Evidencia" id="modDeudas" data-id="' + this.deudas_id + '" data-oei="' + this.deudas_oei + '" data-proyecto="' + this.proyecto_nombre + '" \n\
                    data-proceso="' + this.deudas_proceso + '" data-contrato="' + this.deudas_contrato + '" data-tcon="' + this.deudas_tcon + '"></i>\n\
                    <i class="far fa-paper-plane m-1" title="Enviar Deuda" id="enviarDeuda" data-deuda="' + this.deudas_id + '"></i><i class="fas fa-angle-down m-1" title="Visualizar Archivos" id="elimDeudas" data-id="' + this.deudas_id + '"></i>\n\
                    <i class="far fa-eye m-1" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i><i class="fas fa-clipboard-list" id="autoevaluar" data-deuda="' + this.deudas_id + '" data-monto="' + this.deudas_monto_pendiente + '" data-nombre="' + this.deudas_proceso + '" data-iva="' + this.deuda_monto_iva + '" data-anticipo="' + this.deudas_anticipo + '" data-estado="' + estadoid + '"></i>';
                    } else {
                        div = '<i class="far fa-eye m-1" id="verestadosreq" data-deuda="' + this.deudas_id + '"></i><i class="fas fa-angle-down m-1" title="Visualizar Archivos" id="elimDeudas" data-id="' + this.deudas_id + '"></i><i class="fas fa-clipboard-list" id="autoevaluar" data-deuda="' + this.deudas_id + '" data-monto="' + this.deudas_monto_pendiente + '" data-nombre="' + this.deudas_proceso + '" data-iva="' + this.deuda_monto_iva + '" data-anticipo="' + this.deudas_anticipo + '" data-estado="' + estadoid + '"></i>';
                    }
                    $('#proyectosForm').append('<tr><td class="text-justify">OEI-0' + this.deudas_oei + '</td><td class="text-justify">' + this.proyecto_nombre + '</td><td>' + this.deudas_proceso + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_contrato) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(this.deuda_monto_iva) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_anticipo) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.deudas_monto_pendiente) + '</td><td>' + tipo + '</td><td>' + estado + '</td><td>' + div + '</td></tr><tr><td colspan="10" id="evidencias' + this.deudas_id + '" style="display:none;"></td></tr>');
                    $.each(this.estado, function (indice, archivos) {
                        if (estadoid === 0 || estadoid === 3) {
                            div2 = '<i class="fas fa-edit" title="Editar Evidencia" id="modDeudasEv" data-id="' + archivos.deudas_id + '" data-deudas="' + archivos.deudas_oei + '" data-nombre="' + archivos.deudas_contrato + '" data-archivos="' + archivos.deudas_proceso + '"></i><i class="fas fa-trash" title="Eliminar Evidencia" id="elimDeudasEv" data-id="' + archivos.deudas_id + '" data-deudas="'+archivos.deudas_oei+'"></i>';
                        } else {
                            div2 = '';
                        }
                        $('#proyectosForm').children('tr').children('td#evidencias' + archivos.deudas_oei).append('<tr><td colspan="4" style="font-size:.75em;" class="text-justify">' + archivos.deudas_contrato + '</td><td colspan="4" style="font-size:.75em;" class="text-justify">' + archivos.deudas_proceso + '</td><td colspan="2">' + div2 + '</td></tr>')
                    });
                    st = st + this.deuda_monto_contrato;
                    siva = siva + this.deuda_monto_iva;
                    santicipo = santicipo + this.deudas_anticipo;
                    spendiente = spendiente + this.deudas_monto_pendiente;
                    stotalpe = stotalpe + (this.deuda_monto_iva + this.deudas_monto_pendiente);
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$("table").on('click', 'tbody tr td #elimDeudas', function () {
    var data = $(this).data();
    $('#proyectosForm').children('tr').children('td#evidencias' + data['id']).slideToggle();
});

//Agregar autoevaluacion
$("table").on('click', 'tbody tr td #autoevaluar', function () {
    $('.card').children('#inputEvaluar').empty();
    var data = $(this).data();
    var suma = data['monto'] + data['iva'] + data['anticipo'];
    var ejecutado;
    $.ajax({
        url: "../evaluacion?accion=ListarAutoevaluacionDuedas",
        type: 'POST',
        data: {"deuda": data['deuda']},
        dataType: 'json',
        async: false

    })
            .done(function (response) {
                if (response.length > 0) {
                    banAuto = true;
                    $.each(response, function () {
                        ejecutado = this.ae_autoeval;
                    })
                } else {
                    banAuto = false;
                    ejecutado = '';
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $('.card').children('#inputEvaluar').append('<div class="row"><div class="col-12 text-justify mb-1" style="font-weight:bold;">' + data['nombre'] + '</div>\n\
        <label class="col-6">Monto Planificado:</label><div class="col-5">' + new Intl.NumberFormat("US", formateador()).format(suma) + '</div>\n\
        <label class="col-6">Monto Ejecutado:</label><input type="number" class="form-control col-5" id="valorevaluacion" value="' + ejecutado + '"></div><input type="hidden" value="' + data['deuda'] + '" id="deudaideval"><input type="hidden" id="sumadeuda" value="' + suma + '">');
    if (data['estado'] === 0 || data['estado'] === 3) {
        $('#valorevaluacion').attr("readonly", false);
        $('#guardarEvaluacion').removeClass('d-none');
    } else {
        $('#valorevaluacion').attr("readonly", true);
        $('#guardarEvaluacion').addClass('d-none');
    }
    $('#evaluarModal').modal();
});

$('#guardarEvaluacion').on('click', function () {
    $.ajax({
        url: "../evaluacion?accion=AutoevaluacionDuedas",
        type: 'POST',
        data: {"deuda": $('#deudaideval').val(), "suma": $('#sumadeuda').val(), "ejecucion": $('#valorevaluacion').val(), bandera: banAuto},
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

//Agregar evidencias
$("table").on('click', 'tbody tr td #modDeudas', function () {
    var data = $(this).data();
    $('#iddeudas').val(data['id']);
    $('#FrmArchivosEvidencias').removeClass('d-none');
    $('#archivosDeudas').removeClass('d-none');
    window.location.href = '#FrmArchivosEvidencias';
    banDeuda = true;
});

//Modificar evidencias
$("table").on('click', 'tbody tr td tr #modDeudasEv', function () {
    var data = $(this).data();
    $('#iddeudas').val(data['id']);
    $('#archivoactividad').attr("readonly", true);
    $('#FrmArchivosEvidencias').removeClass('d-none');
    $('#descarchivo').val(data['nombre']);
    $('#archivosDeudas').addClass('d-none');
    window.location.href = '#FrmArchivosEvidencias';
    banDeuda = false;
});

//Eliminar Evidencias
$("table").on('click', 'tbody tr td tr #elimDeudasEv', function () {
    var data = $(this).data();
    $('#iddeudas').val(data['id']);
    $('#eliminarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('<input type="hidden" name="iddeudasEl" id="iddeudasEl" value="' + data['id'] + '"><input type="hidden" name="iddeudasElim" id="iddeudasElim" value="' + data['deudas'] + '">Esta seguro que desea eliminar?');
    $('#eliminarModal').modal();
});


$('#eliminarModalBton').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EliminarDeudasEvidencia",
        type: 'POST',
        data: {"codigo": $('#iddeudasEl').val(), "cedula": $('#cedulaProyecto').val(), "deuda":$('#iddeudasElim').val()},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
                } else {
                    alert(responde);
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

//Ver modal listado de deudas
$("table").on('click', 'tbody tr td #verestadosreq', function () {
    var data = $(this).data();
    $('.card').children('#inputEnviarReq').empty();
    var options = {
        weekday: "long", year: "numeric", month: "short",
        day: "numeric", hour: "2-digit", minute: "2-digit"
    };
    $.ajax({
        url: "../proyecto?accion=ListarFechasDeudasEval",
        type: 'POST',
        data: {"deuda": data['deuda']},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    var date = new Date(this.estado_fecha);
                    var observacion;
                    if (this.estado_observacion == null) {
                        observacion = '';
                    } else {
                        observacion = ',' + this.estado_observacion;
                    }
                    $('.card').children('#inputEnviarReq').append('<p class="card-text">' + this.estado_nombre + ' por ' + this.usuario_nombre + ' el ' + date.toLocaleTimeString("es-ES", options) + observacion + '</p>');
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

$("table").on('click', 'tbody tr td #enviarDeuda', function () {
    var data = $(this).data();
    $('#enviarModal').children('.modal-dialog').children('.modal-content').children('.modal-body').html('\u00bfEsta seguro que desea enviar?<input type="hidden" name="estadoproy" id="estadoproy" value="' + data['deuda'] + '">');
    $('#enviarModal').modal();
});

$('#enviarModalBton').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=EnviarDeudaEval",
        type: 'POST',
        data: {estado: 1, cedulaProyecto: $('#cedulaProyecto').val(), deuda: $('#estadoproy').val(), observacion: "Sin observacion"},
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