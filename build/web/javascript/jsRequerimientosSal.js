var groupColumn = 0;
var options2 = {style: "currency", currency: "USD"};
var t = $('#example').DataTable({
    "columnDefs": [
        {"visible": false, "targets": groupColumn}],
    "order": [[groupColumn, 'asc']],
    responsive: true,
    "scrollX": true,
    "processing": true,
    "select": {
        style: 'multi'
    },
    "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "Todos"]],
    "drawCallback": function (settings) {
        var api = this.api();
        var rows = api.rows({page: 'current'}).nodes();
        var last = null;
        api.column(groupColumn, {page: 'current'}).data().each(function (group, i) {
            if (last !== group) {
                $(rows).eq(i).before(
                        '<tr class="group"><td colspan="10">' + group + '</td></tr>'
                        );
                last = group;
            }
        });
    }
}).clear().draw();

$('#example tbody').on('click', 'tr.group', function () {
    var currentOrder = t.order()[0];
    if (currentOrder[0] === groupColumn && currentOrder[1] === 'asc') {
        t.order([groupColumn, 'desc']).draw();
    } else {
        t.order([groupColumn, 'asc']).draw();
    }
});

POAPLAN(t);

//Listado del POA Director
function POAPLAN(t) {
    $('#loader').removeClass("d-none");
    $.ajax({
        url: "../solicitud?accion=ListaRequerimientosSalvaguardar",
        type: 'POST',
        dataType: 'json',
        data: {anio: $('#selectaniof').val()},
        dataSrc: ''
    })
            .done(function (response) {
                $.each(response, function () {
                    var addID, verificado = 0.0, estado, disponible, visto, to;
                    to=this.req_costo_total + this.req_incremento;
//                    if ((this.verificado_iva === null || this.verificado_iva == null || this.verificado_iva === '' || this.verificado_iva === 'undefined' || this.verificado_iva === 0) && this.verificado_uni_iva > 0) {
//                        verificado = this.verificado_uni_iva;  
//                    } else {
//                        verificado = this.verificado_iva;
//                    }

                    if (this.actividad_monto == 0 && this.actividad_porcentaje > 0 && this.c1 == 0 && this.cp_valor == 0) {
                        //Estado
                        if (this.solicitud_cargo == null) {
                            estado = "";
                        } else {
                            if (this.solicitud_cargo == "Entregado Financiero") {
                                estado = "Dirección Financiera";
                            } else if (this.solicitud_cargo == "Aprobado Compras") {
                                estado = "Documento Aprobado";
                            } else if (this.solicitud_cargo == "Recibido") {
                                estado = "Compras Públicas";
                            } else if (this.solicitud_cargo == "Recibido Planificación") {
                                estado = "Dirección de Planificación";
                            } else if (this.solicitud_cargo == "Enviado por Devolución Compras") {
                                estado = "Devuelto a la unidad";
                            } else if (this.solicitud_cargo == "Anulado") {
                                estado = "Anulado";
                            } else {
                                estado = "";
                            }
                        }
                        verificado = this.verificado_uni_iva;
                        disponible = to - this.verificado_uni_iva;
                    } else if (this.actividad_monto == 0 && this.actividad_porcentaje > 0 && this.c1 == 0 && this.cp_valor > 0) {
                        //Estado
                        if (this.solicitud_cargo == null) {
                            estado = "";
                        } else {
                            if (this.solicitud_cargo == "Entregado Financiero") {
                                estado = "Dirección Financiera";
                            } else if (this.solicitud_cargo == "Aprobado Compras") {
                                estado = "Documento Aprobado";
                            } else if (this.solicitud_cargo == "Recibido") {
                                estado = "Compras Públicas";
                            } else if (this.solicitud_cargo == "Recibido Planificación") {
                                estado = "Dirección de Planificación";
                            } else if (this.solicitud_cargo == "Enviado por Devolución Compras") {
                                estado = "Devuelto a la unidad";
                            } else if (this.solicitud_cargo == "Anulado") {
                                estado = "Anulado";
                            } else {
                                estado = "";
                            }
                        }
                        verificado = this.cp_valor;
                        disponible = to - this.cp_valor;
                    } else if (this.ctividad_monto > 0 && this.actividad_porcentaje == 0 && this.c1 > 0 && this.cp_valor == 0) {
                        //Estado
                        if (this.solestado_observacion == null) {
                            estado = "";
                        } else {
                            if (this.solestado_observacion == "Entregado Financiero") {
                                estado = "Dirección Financiera";
                            } else if (this.solestado_observacion == "Aprobado Compras") {
                                estado = "Documento Aprobado";
                            } else if (this.solestado_observacion == "Recibido") {
                                estado = "Compras Públicas";
                            } else if (this.solestado_observacion == "Recibido Planificación") {
                                estado = "Dirección de Planificación";
                            } else if (this.solestado_observacion == "Enviado por Devolución Compras") {
                                estado = "Devuelto a la unidad";
                            } else if (this.solestado_observacion == "Anulado") {
                                estado = "Anulado";
                            } else {
                                estado = "";
                            }
                        }
                        verificado = this.verificado_iva + this.verificado_uni_npac;
                        disponible = to - (this.verificado_iva + this.verificado_uni_npac);
                    } else if (this.actividad_monto > 0 && this.actividad_porcentaje == 0 && this.c1 > 0 && this.cp_valor > 0) {
                        //Estado
                        if (this.solestado_observacion == null) {
                            estado = "";
                        } else {
                            switch (this.solestado_observacion) {
                                case "Entregado Financiero":
                                    estado = "Dirección Financiera";
                                    break;
                                case "Aprobado Compras":
                                    etsado = "Documento Aprobado";
                                    break;
                                case "Recibido":
                                    estado = "Compras Públicas";
                                    break;
                                case "Recibido Planificación":
                                    estado = "Dirección de Planificación";
                                    break;
                                case "Enviado por Devolución Compras":
                                    estado = "Devuelto a la unidad";
                                    break;
                                case "Anulado":
                                    estado = "Anulado";
                                    break;
                                default:
                                    estado = "";
                                    break;
                            }
                        }
                        verificado = this.cp_valor;
                        disponible = ((to) - (this.cp_valor));
                    } else if (this.actividad_monto > 0 && this.actividad_porcentaje == 0 && this.c1 == 0 && this.cp_valor == 0) {
                        //Estado
                        if (this.solestado_observacion == null) {
                            estado = "";
                        } else {
                            switch (this.solestado_observacion) {
                                case "Entregado Financiero":
                                    estado = "Dirección Financiera";
                                    break;
                                case "Aprobado Compras":
                                    estado = "Documento Aprobado";
                                    break;
                                case "Recibido":
                                    estado = "Compras Públicas";
                                    break;
                                case "Recibido Planificación":
                                    estado = "Dirección de Planificación";
                                    break;
                                case "Enviado por Devolución Compras":
                                    estado = "Devuelto a la unidad";
                                    break;
                                case "Anulado":
                                    estado = "Anulado";
                                    break;
                                default:
                                    estado = "";
                                    break;
                            }
                        }
                        verificado = this.verificado_iva;
                        disponible = (to - this.verificado_iva);
                    } else if (this.actividad_monto > 0 && this.actividad_porcentaje == 0 && this.c1 == 0 && this.cp_valor > 0) {
                        //Estado
                        if (this.solestado_observacion == null) {
                            estado = "";
                        } else {
                            switch (this.solestado_observacion) {
                                case "Entregado Financiero":
                                    estado = "Dirección Financiera";
                                    break;
                                case "Aprobado Compras":
                                    estado = "Documento Aprobado";
                                    break;
                                case "Recibido":
                                    estado = "Compras Públicas";
                                    break;
                                case "Recibido Planificación":
                                    estado = "Dirección de Planificación";
                                    break;
                                case "Enviado por Devolución Compras":
                                    estado = "Devuelto a la unidad";
                                    break;
                                case "Anulado":
                                    estado = "Anulado";
                                    break;
                                default:
                                    estado = "";
                                    break;
                            }
                        }
                        verificado = this.cp_valor;
                        disponible = (to - this.cp_valor);
                    } else if (this.actividad_monto > 0 && this.actividad_porcentaje > 0 && this.c1 == 0 && this.cp_valor > 0) {
                        //Estado
                        if (this.solestado_observacion == null) {
                            estado = "";
                        } else {
                            switch (this.solestado_observacion) {
                                case "Entregado Financiero":
                                    estado = "Dirección Financiera";
                                    break;
                                case "Aprobado Compras":
                                    estado = "Documento Aprobado";
                                    break;
                                case "Recibido":
                                    estado = "Compras Públicas";
                                    break;
                                case "Recibido Planificación":
                                    estado = "Dirección de Planificación";
                                    break;
                                case "Enviado por Devolución Compras":
                                    estado = "Devuelto a la unidad";
                                    break;
                                case "Anulado":
                                    estado = "Anulado";
                                    break;
                                default:
                                    estado = "";
                                    break;
                            }
                        }
                        verificado = this.cp_valor;
                        disponible = (to - this.cp_valor);
                    } else if (this.actividad_monto > 0 && this.actividad_porcentaje > 0 && this.c1 == 0 && this.cp_valor == 0) {
                        //Estado
                        if (this.solestado_observacion == null) {
                            estado = "";
                        } else {
                            switch (this.solestado_observacion) {
                                case "Entregado Financiero":
                                    estado = "Dirección Financiera";
                                    break;
                                case "Aprobado Compras":
                                    estado = "Documento Aprobado";
                                    break;
                                case "Recibido":
                                    estado = "Compras Públicas";
                                    break;
                                case "Recibido Planificación":
                                    estado = "Dirección de Planificación";
                                    break;
                                case "Enviado por Devolución Compras":
                                    estado = "Devuelto a la unidad";
                                    break;
                                default:
                                    estado = "";
                                    break;
                            }
                        }
                        verificado = this.cp_valor;
                        disponible = (to - this.cp_valor);
                    } else if (this.actividad_monto == 0 && this.actividad_porcentaje == 0 && this.c1 == 0 && this.cp_valor == 0 && this.solicitud_codigo != null) {
                        if (this.solestado_observacion == null) {
                            estado = "";
                        } else {
                            switch (this.solestado_observacion) {
                                case "Entregado Financiero":
                                    estado = "Dirección Financiera";
                                    break;
                                case "Aprobado Compras":
                                    estado = "Documento Aprobado";
                                    break;
                                case "Recibido":
                                    estado = "Compras Públicas";
                                    break;
                                case "Recibido Planificación":
                                    estado = "Dirección de Planificación";
                                    break;
                                case "Enviado por Devolución Compras":
                                    estado = "Devuelto a la unidad";
                                    break;
                                case "Anulado":
                                    estado = "Anulado";
                                    break;
                                default:
                                    estado = "";
                                    break;
                            }
                        }
                        verificado = this.cp_valor;
                        disponible = (to - this.cp_valor);
                    } else if (this.actividad_monto == 0 && this.actividad_porcentaje == 0 && this.c1 > 0 && this.cp_valor == 0) {
                        //Estado
                        estado = "Dirección Financiera";
                        verificado = this.verificado_uni_npac;
                        disponible = (to - this.verificado_uni_npac);
                    } else if (this.actividad_monto == 0 && this.actividad_porcentaje == 0 && this.c1 > 0 && this.cp_valor > 0) {
                        //Estado
                        estado = "Dirección Financiera";
                        //Costo Verificado IVA
                        disponible = this.cp_valor;
                        disponible = (to - this.cp_valor);
                    } else if (this.actividad_monto == 0 && this.actividad_porcentaje == 0 && this.c1 == 0 && this.cp_valor == 0 && this.solicitud_nombre != null) {
                        //Estado
                        estado = "Documento Aprobado";
                        estado = 0;
                        disponible = to;
                    } else if (this.actividad_monto == 0 && this.actividad_porcentaje == 0 && this.c1 == 0 && this.cp_valor == 0 && this.solicitud_nombre == null) {
                        //Estado
                        estado = "Sin Acción";
                        verificado = 0;
                        disponible = to;
                    }


                    if (this.req_salvaguardar) {
                        visto = '<input type="checkbox" name="reqfinmod" id="reqfinmod' + this.req_req_id + '" checked onchange="guardarverificacion(' + this.req_req_id + ')">';
                    } else {
                        visto = '<input type="checkbox" name="reqfinmod" id="reqfinmod' + this.req_req_id + '"  onchange="guardarverificacion(' + this.req_req_id + ')">';
                    }
                    addID = t.row.add(['<div style="text-align:justify;">' + this.ag_nombre + '</div>', '<div>' + this.req_req_id + '</div>', '<div>OEI-' + this.perspectiva_id + '</div>', '<div class="text-justify">' + this.proyecto_nombre + '</div>',
                        '<div>' + this.req_nombre + '</div>', '<div>' + this.req_descripcion + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(to) + '</div>', '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(verificado) + '</div>',
                        '<div style="text-align:center;">' + new Intl.NumberFormat("US", options2).format(disponible) + '</div>', '<div class="dat">' + estado + '</div>', '<div class="dat">' + visto + '</div>']).draw(false);
                    var theNode = $('#example').dataTable().fnSettings().aoData[addID[0]].nTr; //Poniendo Id al tr(fila o row) que se acaba de añadir.
                    theNode.setAttribute('id', this.req_id);
                });
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('error en el controlador');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function guardarverificacion(req) {
    var sal;
    if ($('#reqfinmod' + req).is(':checked')) {
        sal = true;
    } else {
        sal = false;
    }
    $.ajax({
        url: "../solicitud?accion=GuardarSalvaguardado",
        type: 'POST',
        data: {req: req, salv: sal, usuario: $('#cedulaProyecto').val()},
        dataType: 'json',
        cache: false
    })
            .done(function (response) {
                alert(response);
            })
            .fail(function () {
                console.log('error en el controlador');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('.main').on('click', '.row .content .pestania #btnGenerarJ', function () {
    $('#listaProyectos').empty();
    $('#reqsol').empty();
    $('#tcosto').empty();
    $('#tcostoiva').empty();
    $('input[name=centro]').val("");
    $('select[name=rectoring]').val(0);
    $('#cedulacustodio').val("");
    $('#nombrecustodio').val("");
    $('#cargocustodio').val("");
    var array = [];
    var sum = 0;
    var options2 = {style: "currency", currency: "USD"};
    $('table#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'id': data.id,
                'nombre': data.nombre,
                'cantidad': data.cantidad,
                'costou': data.costou,
                'costoiva': data.costoiva,
                'costosin': data.costosin,
                'oei': data.oei,
                'iva': data.iva,
                'prproyecto': data.prproyecto,
                'unidad': data.unidad});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var iva;
        if (array[i].iva === 1) {
            iva = array[i].costosin * 1.12;
        } else {
            iva = array[i].costosin;
        }
        $('#listaProyectos').append('<tr><td>' + array[i].oei + '</td>\n\
                        <td><input type="text" class="form-control" name="can' + array[i].id + '" value="' + array[i].cantidad + '"></td>\n\
                        <td>' + array[i].unidad + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td><input type="text" class="form-control" name="costu' + array[i].id + '" class="input" value="' + array[i].costou + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costosin) + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-proyecto="' + array[i].prproyecto + '" data-iva="' + array[i].iva + '"></td>\n\
                    </tr>');
        sum = sum + array[i].costosin;
    }
    $('#listaProyectos').append('<tr><td colspan="8" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sum) + '</td>');
    $('#generarJ').modal();
});


//Ingresar compra
$('#modalGuardarJust').on('click', function () {
    var centrocosto = $('input[name=centro]').val();
    var ag = $('input[name=agsol]').val();
    var rector = $('select[name=rectoring]').val();
    var table = $('#example').DataTable();
    var cedula = $('#cedulacustodio').val();
    var nombre = $('#nombrecustodio').val();
    var cargo = $('#cargocustodio').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push($('input[name=can' + $(this).val() + ']').val());
        rem.push($('input[name=nomb' + $(this).val() + ']').val());
        rem.push($('input[name=costu' + $(this).val() + ']').val());
        rem.push(data['proyecto']);
        rem.push(data['iva']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (centrocosto === "") {
        alert("Debe ingresar el centro de costo.");
    } else if (cedula === "") {
        alert("Debe ingresar el numero de cedula del custodio.");
    } else if (nombre === "") {
        alert("Debe ingresar el nombre del custodio.");
    } else if (cargo === "") {
        alert("Debe ingresar el cargo del custodio.");
    } else {
        var formData = new FormData();
        formData.append('centro', centrocosto);
        formData.append('req', req);
        formData.append('ag', ag);
        formData.append('rector', rector);
        formData.append('cedula', cedula);
        formData.append('nombre', nombre);
        formData.append('cargo', cargo);
        formData.append('cedulausuario', $('#cedulaProyecto').val());
        $.ajax({
            url: "../solicitud?accion=IngresarSolicitud",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        table
                                .clear()
                                .draw();
                        POAPLAN(t);
                        $('#generarJ').modal('hide');
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

$('.modalEnviarReqUnid').on('click', function () {
    $('#listaRequerimientosSolReq').empty();
    var data = $(this).data();
    if (data['id'] === 28) {
        $('#exampleModalLabelE').html('Requerimientos al DTIC');
    } else if (data['id'] === 30) {
        $('#exampleModalLabelE').html('Requerimientos a DIRCOM');
    } else if (data['id'] === 29) {
        $('#exampleModalLabelE').html('Requerimientos a Bodega');
    }

    var array = [];
    var sum = 0, sumto = 0;
    $('table#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'id': data.id,
                'nombre': data.nombre,
                'cantidad': data.cantidad,
                'costou': data.costou,
                'costoiva': data.costoiva,
                'costosin': data.costosin,
                'oei': data.oei,
                'iva': data.iva,
                'unidad': data.unidad});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var iva;
        if (array[i].iva === 1) {
            iva = array[i].costosin * 1.12;
        } else {
            iva = array[i].costosin;
        }
        $('#listaRequerimientosSolReq').append('<tr><td>' + array[i].oei + '</td>\n\
                        <td><input type="text" class="form-control" name="canenv' + array[i].id + '" value="' + array[i].cantidad + '"><input type="hidden" value="' + array[i].cantidad + '" name="cantenvoc' + array[i].id + '"></td>\n\
                        <td>' + array[i].unidad + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td><input type="text" class="form-control" name="cosenv' + array[i].id + '" value="' + array[i].costou + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costosin) + '<input type="hidden" value="' + array[i].costosin + '" name="totalenv' + array[i].id + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" data-iva="' + array[i].iva + '" checked="checked"></td>\n\
                    </div>');
        sum = sum + array[i].costosin;
    }
    $('#listaRequerimientosSolReq').append('<tr><td colspan="9" class="text-center">TOTAL:' + new Intl.NumberFormat("US", options2).format(sum) + '</td></tr>');
    $('#enviarid').val(data['id']);
    $('#enviarReq').modal();
});

$('#modalGuardarJustEnv').on('click', function () {
    var usuario = $('input[name=cedulaProyecto]').val();
    var enviar = $('input[name=enviarid]').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push($('input[name=canenv' + $(this).val() + ']').val());
        rem.push($('input[name=cantenvoc' + $(this).val() + ']').val());
        rem.push($('input[name=cosenv' + $(this).val() + ']').val());
        rem.push($('input[name=totalenv' + $(this).val() + ']').val());
        rem.push(data['iva']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    var formData = new FormData();
    formData.append('usuario', usuario);
    formData.append('req', req);
    formData.append('enviar', enviar);
    $.ajax({
        url: "../solicitud?accion=EnviarRequerimientos",
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
});