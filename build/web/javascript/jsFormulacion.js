var alerta = document.getElementById('alertProyecto');

$(document).ready(function () {
    $('#textFechaI').datepicker({
        minDate: "01/01/" + $('#selectanio').val(),
        maxDate: "31/12/" + $('#selectanio').val()
    });
    $('#textFechaF').datepicker({
        minDate: "01/01/" + $('#selectanio').val(),
        maxDate: "31/12/" + $('#selectanio').val()
    });
    listarProyectos();
});

$('#frmAddProyecto').submit(function (event) {
    event.preventDefault();
    let metodo = $(this).attr("method");
    let accion = $(this).attr("action");
    var archivo = document.getElementById("filePerfil");
    var file = archivo.files[0];
    if ($('#idTipoUsu').val() === "18") {
        if ($('#selectAgM').val() == null || $('#selectAgM').val() == "") {
            alert("Debe ingresar las dependencias.");
        } else if (file == null) {
            alert("Debe ingresar el perfil del proyecto");
        } else {
            var doc = document.getElementById('filePerfil').files[0].name;
            if (comprueba_extension(doc, "filePerfil") === 1) {
                $.ajax({
                    url: accion,
                    type: metodo,
                    dataType: 'json',
                    cache: false,
                    processData: false,
                    data: new FormData($('#frmAddProyecto')[0]),
                    contentType: false
                })
                        .done(function (response) {
                            if (response === "Correcto") {
                                alertaM('Proyecto Ingresado', 'sucess');
                                resetform();
                                listarProyectos();
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
        }
    } else {
        if (file == null) {
            $.ajax({
                url: accion,
                type: metodo,
                dataType: 'json',
                cache: false,
                processData: false,
                data: new FormData($('#frmAddProyecto')[0]),
                contentType: false
            })
                    .done(function (response) {
                        if (response === "Correcto") {
                            alertaM(mensajeCorrecto, insertadoCorrecto + ' el proyecto', correcto, alerta, 'fa-check-circle');
                            resetform();
                            listarProyectos();
                        } else {
                            alertaM(mensajeError, response, error, alerta, 'fa-times-circle');
                        }
                    })
                    .fail(function () {
                        console.log('No existe conexión con la base de datos.');
                    })
                    .always(function () {
                        console.log('Se completo correctamente');
                    });
        } else {
            var doc = document.getElementById('filePerfil').files[0].name;
            if (comprueba_extension(doc, "filePerfil") === 1) {
                $.ajax({
                    url: accion,
                    type: metodo,
                    dataType: 'json',
                    cache: false,
                    processData: false,
                    data: new FormData($('#frmAddProyecto')[0]),
                    contentType: false
                })
                        .done(function (response) {
                            if (response === "Correcto") {
                                alertaM(mensajeCorrecto, insertadoCorrecto + ' el proyecto', correcto, alerta, 'fa-check-circle');
                                resetform();
                                listarProyectos();
                            } else {
                                alertaM(mensajeError, response, error, alerta, 'fa-times-circle');
                            }
                        })
                        .fail(function () {
                            console.log('No existe conexión con la base de datos.');
                        })
                        .always(function () {
                            console.log('Se completo correctamente');
                        });
            }
        }
    }
});

function resetform() {
    $("form input[type=text] , form textarea").each(function () {
        this.value = ''
    });
}
//Ingresar Proyecto
function comprueba_extension(archivo, nombre) {
    $('#' + nombre).empty();
    var extensiones_permitidas = new Array(".pdf");
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
        alertaM(mensajeError, 'Compruebe la extensión de los archivos a subir.', error, alerta, 'fa-times-circle');
    } else {
        return 1;
    }
    return 0;
}

function listarProyectos() {
    var tipo = $('#idTipoUsu').val(), url, anio = $('#selectanio').val();
    if (tipo === "18") {
        url = "../proyecto?accion=ListarProyectoMul" + "&ag=" + $('#idAgObEs').val() + "&anio=" + anio;
    } else {
        url = "../proyecto?accion=ListarProyecto" + "&ag=" + $('#idAgObEs').val() + "&anio=" + anio;
    }
    $.ajax({
        url: url,
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#proyectosForm').empty();
                $.each(response, function () {
                    var div;
                    if (this.proyecto_multi) {
                        div = '<a href="pProyectoMulti.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    } else {
                        div = '<a href="pProyecto.jsp?id=' + this.proyecto_id + '" title="Ir a proyecto"><i class="fas fa-external-link-alt"></i></a>';
                    }
                    $('#proyectosForm').append('<tr><td class="text-justify">' + this.proyecto_nombre + '</td><td class="text-justify">' + this.proyecto_responsable + '</td><td>' + this.proyecto_fi + '</td>\n\
                    <td>' + this.proyecto_ff + '</td><td>' + div + '</td></tr>');
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#actpresup').on('change', function () {
    let data = $(this).val();
    $('#idActividadPres').val(data);
    if ($('#tipoAg').val() === "4" && $('#idTipoUsu').val() !== "18") {
        $.ajax({
            url: "../proyecto?accion=ProyectoGenerado",
            data: {area: $('#idAgObEs').val(), anio: $('#selectanio').val()},
            type: 'POST',
            dataType: 'json'
        }).
                done(function (response) {
                    if (!response) {
                        $('#generarproy').removeClass('d-none');
                    }
                })
                .fail(function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 0) {
                        console.log('Not connect: Verify Network.');
                    } else if (jqXHR.status === 404) {
                        console.log('Requested page not found [404]');
                    } else if (jqXHR.status === 500) {
                        console.log('Internal Server Error [500].');
                    } else if (textStatus === 'parsererror') {
                        console.log('Requested JSON parse failed.');
                    } else if (textStatus === 'timeout') {
                        console.log('Time out error.');
                    } else if (textStatus === 'abort') {
                        console.log('Ajax request aborted.');
                    } else {
                        console.log('Uncaught Error: ' + jqXHR.responseText);
                    }
                })
                .always(function () {
                    console.log('ejecutada');
                });
        $('#ingresarproy').removeClass('d-none');
    } else {
        $('#frmAddProyecto').removeClass('d-none');
        $('#frmAddProyecto').addClass('d-block');
    }
});

$('#ingresarproy').on('click', function () {
    if ($('#frmAddProyecto').hasClass('d-none')) {
        $('#frmAddProyecto').removeClass('d-none');
        $('#frmAddProyecto').addClass('d-block');
    } else {
        $('#frmAddProyecto').addClass('d-none');
        $('#frmAddProyecto').removeClass('d-block');
    }
});

$('#generarproy').on('click', function () {
    $.ajax({
        url: "../proyecto?accion=IngresarProyectoGenerado",
        data: {idAgFormulacion: $('#idAgObEs').val(), cedulaProyecto: $('#cedulaProyecto').val(), aliasAgFormulacion: $('#aliasAgFormulacion').val(), idActividadPres: $('#idActividadPres').val(), idOEIForm: $('#idOEIForm').val(), agnombre: $('#stringNombreAg').val(), anio: $('#selectanio').val()},
        type: 'POST',
        dataType: 'json'
    }).
            done(function (response) {
                if (response === "Correcto") {
                    window.location.reload();
                } else {
                    alertaM(mensajeError, response, error, alerta, 'fa-times-circle');
                }
            })
            .fail(function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 0) {
                    console.log('Not connect: Verify Network.');
                } else if (jqXHR.status === 404) {
                    console.log('Requested page not found [404]');
                } else if (jqXHR.status === 500) {
                    console.log('Internal Server Error [500].');
                } else if (textStatus === 'parsererror') {
                    console.log('Requested JSON parse failed.');
                } else if (textStatus === 'timeout') {
                    console.log('Time out error.');
                } else if (textStatus === 'abort') {
                    console.log('Ajax request aborted.');
                } else {
                    console.log('Uncaught Error: ' + jqXHR.responseText);
                }
            })
            .always(function () {
                console.log('ejecutada');
            });
});

$('.formulario').on('change', '.row .form-row .col-12 .row #selectProceso', function () {
    var datos = $(this).val();
    $.ajax({
        url: "../proyecto?accion=ListaActividadesProceso",
        type: 'POST',
        data: {proceso: datos},
        dataType: 'json'
    })
            .done(function (response) {
                $('#selectAcciones').empty();
                $.each(response, function () {
                    $('#selectAcciones').append('<option value="' + this.am_id + '">' + this.am_codigo + '. ' + this.am_nombre + '</option>');
                });
                myFunction();
                $('#selectAcciones').selectpicker('refresh');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
});

function myFunction() {
    var options = document.getElementById("selectAcciones");
    var limite = 130;

    for (var i = 0; i < options.length; i++) {
        options[i].setAttribute('title', options[i].innerText); // para poder ver el texto completo en el hover del elemento antes de cortarlo
        options[i].innerText = options[i].innerText.slice(0, limite) + '...';
    }
}