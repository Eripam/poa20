var options2 = {style: "currency", currency: "USD"};
function activarPOA(idag, nombreag, id, nombre) {
    $('#ttsol').html('Listado de Justificativos aprobados ' + nombre);
    $('#fechaenvioreq').empty();
    var tag=$('#tipoAg').val();
    $.ajax({
        url: "../solicitud?accion=ListaSolicitudesPlanificador",
        type: 'POST',
        data: {ag: id, agp: idag, anio: $('#selectAnio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    $('#fechaenvioreq').append('<form method="POST" action="../controladorReportePDF" id="FrmDescargarPOA' + this.solicitud_codigo + '" class="instructivos_archivos_icono" target="_blank" style="width:100%; display:flex; justify-content: center">\n\
                    <input type="hidden" value="reporteCustodios" name="accion">\n\
                    <input type="hidden" value="' + idag + '" name="agidpoa">\n\
                    <input type="hidden" value="' + nombreag + '" name="agnombrepdf">\n\
                    <input type="hidden" value="' + id + '" name="agunificadora">\n\
                    <input type="hidden" value="' + nombre + '" name="agunificadoraalias">\n\
                    <input type="hidden" value="' + tag + '" name="tagunidad">\n\
                    <input type="hidden" value="' + this.solicitud_id + '" name="codigosol">\n\
                    <input type="hidden" value="' + $('#selectAnio').val() + '" name="anioselect">\n\
                    <a href="#" onclick="activarSolicitud(' + this.solicitud_codigo + ');">' + this.solicitud_codigo + '-UCP-'+$('#selectAnio').val()+'</a></form>');
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
    $("#devolverModal").modal();
}

function activarSolicitud(id) {
    document.getElementById("FrmDescargarPOA" + id).submit();
}

function agregarC(id, ag) {
    $('#estadotipo').val(id);
    if (id === 28) {
        $('#tituloCustodioReq').html('Custodio Requerimientos unificados por DTIC');
    } else if (id === 30) {
        $('#tituloCustodioReq').html('Custodio Requerimientos unificados por DIRCOM');
    } else if (id === 29) {
        $('#tituloCustodioReq').html('Custodio Requerimientos unificados por Bodega');
    } else if (id === 50) {
        $('#tituloCustodioReq').html('Custodio Requerimientos unificados por Dirección Administrativa');
    }

    $('#listaProyectosCustodio').empty();
    $.ajax({
        url: "../solicitud?accion=ListaRequerimientosUnificadosA",
        type: 'POST',
        data: {estado: id, ag: ag, tpag:$('#tpag').val(), anio: $('#selectAnio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    var proy = this.proyecto_id;
                    if (this.req.length > 0) {
                        $('#listaProyectosCustodio').append('<tr><td>' + this.proyecto_nombre + '</td>\n\
                    <td id="requ' + this.proyecto_id + '"></td>\n\
                    <td><input type="text" name="cedcustodio' + this.proyecto_id + '" id="cedcustodio' + this.proyecto_id + '"></td>\n\
                    <td><input type="text" name="nomcustodio' + this.proyecto_id + '" id="nomcustodio' + this.proyecto_id + '"></td>\n\
                    <td><input type="text" name="cargocustodio' + this.proyecto_id + '" id="cargocustodio' + this.proyecto_id + '"></td>\n\
                    <td><input type="text" name="ubicustodio' + this.proyecto_id + '" id="ubicustodio' + this.proyecto_id + '"></td>\n\
                    <td><input type="checkbox" name="pro" id="pro" value="' + this.proyecto_id + '" data-estado="' + id + '" disabled checked></td></tr>');
                        $.each(this.req, function (indice, r) {
                            $('#listaProyectosCustodio').children('tr').children('td#requ' + proy).append(r.req_nombre + '<div style="display:none"><input type="checkbox" name="requ' + proy + '" value="' + r.req_id + '" checked></div>');
                        });
                    }
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });

    $("#modalCustodio").modal();
}

//Guardar Custodios
$('#modalGuardarJustUnificCus').on('click', function () {
//    var centrocosto = $('input[name=centromod]').val();
//    var solicitud = $('input[name=solidmod]').val();
//    var rector = $('select[name=rectoringmod]').val();
    var re = [];
    var rp = [];
    var con = 0;
    $("input[name=pro]:checked").each(function () {
        var rem = [];
        var ru = [];
        var pr = $(this).val();
        var data = $(this).data();
        var cedula = $('input[name=cedcustodio' + $(this).val() + ']').val();
        rem.push($(this).val());
        rem.push($('input[name=cedcustodio' + $(this).val() + ']').val());
        rem.push($('input[name=nomcustodio' + $(this).val() + ']').val());
        rem.push($('input[name=cargocustodio' + $(this).val() + ']').val());
        rem.push($('input[name=ubicustodio' + $(this).val() + ']').val());
        rem.push(data['estado']);
        $("input[name=requ" + pr + "]:checked").each(function () {
            ru.push(pr);
            ru.push($(this).val());
        });
        rp.push(ru);
        re.push(rem);
        if ($('input[name=cedcustodio' + $(this).val() + ']').val() === "" || $('input[name=nomcustodio' + $(this).val() + ']').val() === "") {
            con++;
        }
    });
    var req = JSON.stringify(re);
    var req2 = JSON.stringify(rp);
    if (con > 0) {
        alert("Todos los campos deben estar llenos");
    } else {
        var formData = new FormData();
        formData.append('req', req);
        formData.append('req2', req2);
        $.ajax({
            url: "../solicitud?accion=IngresarCustodios",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
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