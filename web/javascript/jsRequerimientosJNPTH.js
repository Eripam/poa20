var options2 = {style: "currency", currency: "USD"};
var banservicios = true;
$(document).ready(function () {
    listaServiciosP();
    listaFechaActual();
});

function listaFechaActual() {
    $.ajax({
        url: "../proyecto?accion=ListarFecha",
        type: 'POST',
        dataType: 'json'
    })
            .done(function (response) {
                $('#fechainicio').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
                $('#fechafin').datepicker({
                    minDate: "01/01/" + response,
                    maxDate: "31/12/" + response
                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

function listaServiciosP() {
    $('#loader').removeClass("d-none");
    $('#listaServicios').empty();
    var a = $('#array').val();
    var anio = $('#selectanio').val();
    $.ajax({
        url: "../solicitud?accion=ListaReqEnviados",
        type: 'POST',
        data: {req: a, anio: anio},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var req, div, agid;
                    req = this.req_id;
                    if (this.actividad_monto > 0 && this.solicitud_estado === 32 && this.ae_tiempo === this.actividad_prioridad) {
                        div = '<div class="dat" data-id="' + this.req_id + '" data-costot="' + this.actividad_monto + '" \n\
                            data-nombre="' + this.req_nombre + '" data-ag="' + this.ag_nombre + '" data-oei="' + this.perspectiva_id + '" data-prproyecto="' + this.presupuesto_proyecto + '" data-iva="' + this.req_iva + '"><i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i><input type="checkbox" name="reqfinmod" id="reqfinmod" class="m-1"><i class="fas fa-check-square m-1" title="Verificar" id="devolvereq" data-req="' + this.req_id + '" data-estado="' + this.solicitud_estado + '"></i></div>';
                    } else if (this.actividad_monto > 0 && this.solicitud_estado === 1) {
                        div = '<div class="dat" data-id="' + this.req_id + '" data-costot="' + this.actividad_monto + '" \n\
                            data-nombre="' + this.req_nombre + '" data-ag="' + this.ag_nombre + '" data-oei="' + this.perspectiva_id + '" data-prproyecto="' + this.presupuesto_proyecto + '" data-iva="' + this.req_iva + '"><i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i><i class="fas fa-check-square m-1" title="Verificar" id="devolvereq" data-req="' + this.req_id + '" data-estado="' + this.solicitud_estado + '"></i></div>';
                    } else {
                        div = '<i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i>';
                    }
                    agid = this.ag_id;
                    $('#listaServicios').append('<div class="encabezado_5 estilobody text-justify">' + this.ag_nombre + '</div><div class="encabezado_4 estilobody justify-content-center">OEI-0' + this.perspectiva_id + '</div><div class="encabezado_2 estilobody text-justify">' + this.proyecto_nombre + '</div>\n\
                    <div class="encabezado_5 estilobody text-justify">' + this.req_nombre + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.req_costo_total) + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.actividad_monto) + '</div>\n\
                    <div class="encabezado_4 estilobody justify-content-center">' + div + '</div>\n\
                    <div style="display:none; background:rgba(0, 0, 0, 0.05);" id="lserv' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    total = total + this.req_costo_total;
                    if (this.cuatri.length > 0) {
                        $.each(this.cuatri, function (indice, sp) {
                            var tipo;
                            if (this.solicitud_id === 0) {
                                if (sp.actividad_id === 3) {
                                    tipo = 'HONORARIO FIJO';
                                } else if (sp.actividad_id === 2) {
                                    tipo = 'HONORARIOS MENSUALES';
                                } else if (sp.actividad_id === 1) {
                                    tipo = 'HORAS CLASE';
                                }
                                $('#listaServicios').children('div#lserv' + req).append('<div class="encabezado_2 estilobody text-justify">NOMBRE: ' + sp.req_nombre + '</div><div class="encabezado_2 estilobody text-justify">APELLIDO: ' + sp.req_descripcion + '</div><div class="encabezado_2 estilobody text-justify">CARGO: ' + sp.actividad_nombre + '</div>\n\
                            <div class="encabezado_5 estilobody justify-content-center">TIPO: ' + tipo + '</div><div class="encabezado_5 estilobody justify-content-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sp.req_costo_total) + '</div><div class="encabezado_4 estilobody justify-content-center"><i class="fas fa-edit" id="modserv" data-id="' + sp.req_id + '" \n\
                            data-tipo="' + sp.actividad_id + '" data-fi="' + sp.fecha_inicio + '" data-ff="' + sp.fecha_fin + '" data-cantidad="' + sp.cantidad + '" data-cargo="' + sp.actividad_nombre + '" data-nombre="' + sp.req_nombre + '" data-apellido="' + sp.req_descripcion + '" data-cedula="' + sp.solicitud_cedula + '" data-horas="' + sp.n_horas + '" data-agid="' + agid + '" \n\
                            data-total="' + sp.req_costo_total + '" data-req="' + req + '" data-observacion="' + sp.solestado_observacion + '" data-verificacion="' + sp.solestado_numero + '" data-cedulaest="' + sp.autoridades_cedula + '" data-nombreest="' + sp.autoridades_nombre + '" data-apellidoest="' + sp.autoridades_cargo + '" data-siniva="'+sp.req_costo_sin_iva+'"></i></div>');
                            }
                        });
                    }
                });
                $('#listaServicios').append('<div class="encabezado_completo estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(total) + '</div>');
                $('#loader').addClass('d-none');
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
                $('#loader').addClass('d-none');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

$('#listaServicios').on('click', '.encabezado_4 #devolvereq', function () {
    var data = $(this).data();
    $('textarea[name=observacion]').val('');
    $('#idreqregresar').val(data['req']);
    $('#regresarModal').modal();
});

$('#example').on('click', 'div div #listaS', function () {
    var data = $(this).data();
    $('#lserv' + data['req']).slideToggle();
});

$('.main').on('click', '.row .content .pestania #btnGenerarJ', function () {
    $('#listaProyectos').empty();
    $('#reqsol').empty();
    $('#tcosto').empty();
    $('#tcostoiva').empty();
    $('input[name=descripcions]').val("");
    $('select[name=rectoring]').val(0);
    var array = [];
    var sum = 0;
    var monto;
    var options2 = {style: "currency", currency: "USD"};
    $('#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            art = ({'id': data.id,
                'nombre': data.nombre,
                'costo': data.costot,
                'oei': data.oei,
                'ag': data.ag,
                'prproyecto': data.prproyecto,
                'iva': data.iva});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var tot = 0;
        $.ajax({
            url: "../solicitud?accion=ListaServicioProfesional",
            type: 'POST',
            data: {req: array[i].id},
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    $.each(response, function () {
                        if (this.solicitud_id === 0 && this.solestado_numero === 1) {
                            tot = tot + this.req_costo_total;
                        }
                    });
                })
                .fail(function () {
                    console.log('No existe conexión con la base de datos.');
                    $('#loader').addClass('d-none');
                })
                .always(function () {
                    console.log('Se completo correctamente');
                });
        if (array[i].iva === 1) {
            monto = tot / 1.12;
        } else {
            monto = tot;
        }
        $('#listaProyectos').append('<tr><td>' + array[i].ag + '</td><td class="justify-content-center">OEI-0' + array[i].oei + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td class="justify-content-center"><input type="hidden" class="form-control" name="costot' + array[i].id + '" value="' + monto.toFixed(2) + '">' + new Intl.NumberFormat("US", options2).format(tot) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-proyecto="' + array[i].prproyecto + '" data-iva="' + tot + '"></td>\n\
                    </tr>');
        sum = sum + tot;
    }
    $('#listaProyectos').append('<tr><td colspan="8" class="text-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sum) + '</td>');
    $('#generarJ').modal();
});

//Ingresar compra
$('#modalGuardarJust').on('click', function () {
    var ag = $('input[name=agsol]').val();
    var rector = $('select[name=rectoring]').val();
    var cargo = $('#descripcions').val();
    var anio = $('#selectanio').val();
    var re = [];
    $("input[name=re]:checked").each(function () {
        var rem = [];
        var data = $(this).data();
        rem.push($(this).val());
        rem.push(1);
        rem.push($('input[name=nomb' + $(this).val() + ']').val());
        rem.push($('input[name=costot' + $(this).val() + ']').val());
        rem.push(data['proyecto']);
        rem.push(data['iva']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (cargo === "") {
        alert("Debe ingresar el centro de costo.");
    } else {
        var formData = new FormData();
        formData.append('req', req);
        formData.append('ag', ag);
        formData.append('rector', rector);
        formData.append('cargo', cargo);
        formData.append('anio', anio);
        formData.append('cedulausuario', $('#cedulaProyecto').val());
        $.ajax({
            url: "../solicitud?accion=IngresarSolicitudNP",
            type: 'POST',
            data: formData,
            dataType: 'json',
            cache: false,
            processData: false,
            contentType: false
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        listaServiciosP();
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

$('.modalEnviarReqUnid').on('click', function () {
    $('#listaRequerimientosSolReq').empty();
    var data = $(this).data();

    var array = [];
    var sum = 0, sumto = 0, re = [];
    $('table#example').find('div.dat').each(function () {
        var data = $(this).data();
        var art = [];
        var check = $(this).find('input[name=reqfinmod]');
        if (check.is(':checked')) {
            var rem = [];
            rem.push(data.id);
            re.push(rem);
            art = ({'id': data.id,
                'nombre': data.nombre,
                'cantidad': data.cantidad,
                'costou': data.costou,
                'costoiva': data.costoiva,
                'costosin': data.costosin,
                'oei': data.oei,
                'iva': data.iva});
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
                        <td>' + array[i].cantidad + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costou) + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(array[i].costosin) + '<input type="hidden" value="' + array[i].costosin + '" name="totalenv' + array[i].id + '"></td>\n\
                        <td>' + new Intl.NumberFormat("US", options2).format(iva) + '</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" data-iva="' + array[i].iva + '" checked="checked"></td>\n\
                    </div>');
        sum = sum + array[i].costosin;
    }
    var req = JSON.stringify(re);
    $('#listaRequerimientosSolReq').append('<tr><td colspan="9" class="text-center">TOTAL:' + new Intl.NumberFormat("US", options2).format(sum) + '</td></tr>');
    $('#enviarid').val(data['id']);
    $('#BtnEnviarTh').html('<a href="pServiciosProfesionales.jsp?id=' + req + '" class="btn bton" id="modalGuardarJustEnv">GUARDAR</a>\n\
    <a href="#" class="btn bton" data-dismiss="modal">CANCELAR</a>');
    $('#enviarReq').modal();
});

//Modificar servicios profesionales modal
$('#example').on('click', 'div div #modserv', function () {
    var data = $(this).data();
    var id = data['tipo'], tipo;
    $('#servId').val(data['id']);
    $('#cargo').val(data['cargo']);
    $('#nombreser').val(data['nombre']);
    $('#apellidose').val(data['apellido']);
    $('#cedulaser').val(data['cedula']);
    $('#reqidV').val(data['req']);
    $('#rectoringSer').find('option[value="' + data['tipo'] + '"]').remove();
    $('#observoficio').val(data['observacion']);
    if (data['verificacion'] === 1) {
        $('#verificarsp').attr('checked', true);
    } else {
        $('#verificarsp').removeAttr('checked');
    }
    if (id === 1) {
        tipo = "Horas Clase";
    } else if (id === 2) {
        tipo = "Honorarios Mensuales";
    } else if (id === 3) {
        tipo = "Honorario Fijo";
    }
    $('#rectoringSer').append('<option value="' + data['tipo'] + '" selected="selected">' + tipo + '</option>');
    if (id === 1) {
        $('#horas').removeClass('d-none');
        $('#shoras').removeClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').removeClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#nhoras').val(data['horas']);
        $('#sueldoH').val(data['cantidad']);
        $('#datosEstu').addClass('d-none');
        $('#fechainicio').val(data['fi']);
        $('#fechafin').val(data['ff']);
    } else if (id === 2) {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').removeClass('d-none');
        $('#personalmed').removeClass('d-none');
        $('#sueldo').removeClass('d-none');
        $('#fechainicio').val(data['fi']);
        $('#fechafin').val(data['ff']);
        $('#sueldoMensual').val(data['cantidad']);
        $('#datosEstu').addClass('d-none');
    } else if (id === 3 && (data['agid'] === 46 || data['agid'] === 103)) {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').removeClass('d-none');
        $('#fechainicio').val(data['fi']);
        $('#cedulaEstu').val(data['cedulaest']);
        $('#nombreEstu').val(data['nombreest']);
        $('#apellidoEstu').val(data['apellidoest']);
        $('#datosEstu').removeClass('d-none');
        $('#totalpagar').val(data['siniva'].toFixed(2));
    } else if (id === 3 && data['agid'] !== 46 && data['agid'] !== 103) {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#fehchai').removeClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').removeClass('d-none');
        $('#fechainicio').val(data['fi']);
        $('#totalpagar').val(data['siniva'].toFixed(2));
        $('#datosEstu').addClass('d-none');
    }
    $('#modificarSer').modal();
});

//Modificar servicios profesionales
$('#modalGuardarJustSer').on('click', function () {
    var iva;
    var horas = $('input[name=nhoras]').val();
    var shoras = $('input[name=sueldoH]').val();
    var fechai = $('input[name=fechainicio]').val();
    var fechaf = $('input[name=fechafin]').val();
    var sueldoMensual = $('input[name=sueldoMensual]').val();
    var totalpagar = $('input[name=totalpagar]').val();
    var tipo = $('select[name=rectoringSer]').val();
    var req = $('#reqidV').val();
    var nombre = $('#nombreser').val();
    var apellido = $('#apellidose').val();
    var cedula = $('#cedulaProyecto').val();
    var serv = $('#servId').val();
    var cargo = $('#cargo').val();
    var cedulaser = $('#cedulaser').val();
    var observacion = $('#observoficio').val();
    var cedulaEst = $('#cedulaEstu').val();
    var nombreEst = $('#nombreEstu').val();
    var apellidoEst = $('#apellidoEstu').val();
    if ($('input:checkbox[name=personalm]:checked').val() == 'undefiden' || $('input:checkbox[name=personalm]:checked').val() == null || $('input:checkbox[name=personalm]:checked').val() == '') {
        iva = 0;
    } else {
        iva = $('input:checkbox[name=personalm]:checked').val();
    }
    if (fechai == 'undefined' || fechai == null) {
        alert('Debe igresar la fecha de inicio.');
    } else if ((fechaf == 'undefined' || fechaf == null) && (tipo == 1 || tipo == 2)) {
        alert('Debe ingresar la feha fin');
    } else if (nombre == 'undefined' || nombre == null || apellido == 'undefined' || apellido == null || cedula == 'undefined' || cedula == null || cargo == 'undefined' || cargo == null) {
        alert('Debe llenar todos los campos');
    } else {
        $.ajax({
            url: "../actividadReq?accion=ModificarServiciosP",
            type: 'POST',
            data: {horas: horas, shoras: shoras, fechai: fechai, fechaf: fechaf, sueldoMensual: sueldoMensual,
                totalpagar: totalpagar, nombre: nombre, req: req, cedula: cedula, tipo: tipo, servicio: serv, apellido: apellido, cargo: cargo, cedulaser: cedulaser, iva: iva, observacion: observacion, apellidoEst: apellidoEst, nombreEst: nombreEst, cedulaEst: cedulaEst},
            dataType: 'json'
        })
        
        
                .done(function (response) {
                    if (response === "Correcto") {
                        $('#modificarSer').modal('hide');
                        listaServiciosP();
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

//Verificr servicio profesional
$('#modalGuardarVerificar').on('click', function () {
    var req = $('#reqidV').val();
    var serv = $('#servId').val();
    var ver;
    if ($('input:checkbox[name=verificarsp]:checked').val() == 'undefiden' || $('input:checkbox[name=verificarsp]:checked').val() == null || $('input:checkbox[name=verificarsp]:checked').val() == '') {
        ver = 0;
    } else {
        ver = $('input:checkbox[name=verificarsp]:checked').val();
    }
    $.ajax({
        url: "../solicitud?accion=VerificarServicioP",
        type: 'POST',
        data: {req: req, serv: serv, verificacion: ver},
        dataType: 'json'
    })
            .done(function (response) {
                if (response === "Correcto") {
                    $('#modificarSer').modal('hide');
                    listaServiciosP();
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

$('.modalEnvRegresar').on('click', function () {
    var data = $(this).data();
    var observacion = $('#observacionreg').val();
    if (observacion === "") {
        alert("Debe ingresar una observaci\xf3n");
    } else {
        $.ajax({
            url: "../solicitud?accion=EnviarRequerimientosReg",
            type: 'POST',
            data: {req: $('#idreqregresar').val(), observacion: observacion, usuario: $('#cedulaProyecto').val(), estado: data['estado']},
            dataType: 'json'
        })
                .done(function (response) {
                    if (response === "Correcto") {
                        window.location.reload();
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