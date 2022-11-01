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
    $.ajax({
        url: "../solicitud?accion=ListaReqEnviadosO",
        type: 'POST',
        data: {req: a, anio: $('#selectanio').val()},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                $.each(response, function () {
                    var req, totalp = this.req_costo_total + this.req_costo_unitario + this.req_costo_sin_iva, div;
                    req = this.req_id;
                    if (this.actividad_monto > 0 && this.solicitud_estado === 32) {
                        div = '<div class="dat" data-id="' + this.req_id + '" data-costot="' + this.actividad_monto + '" data-deudasiva="'+this.req_costo_sin_iva+'"\n\
                            data-nombre="' + this.req_nombre + '" data-ag="' + this.ag_nombre + '" data-oei="' + this.perspectiva_id + '" data-prproyecto="' + this.presupuesto_proyecto + '" data-siva="' + this.ae_ejecucion + '"><i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i><input type="checkbox" name="reqfinmod" id="reqfinmod" class="m-1"><i class="fas fa-check-square m-1" title="Verificar" id="devolvereq" data-req="' + this.req_id + '" data-estado="' + this.solicitud_estado + '"></i></div>';
                    } else if (this.actividad_monto > 0 && this.solicitud_estado === 1) {
                        div = '<div class="dat" data-id="' + this.req_id + '" data-costot="' + this.actividad_monto + '" \n\
                            data-nombre="' + this.req_nombre + '" data-ag="' + this.ag_nombre + '" data-oei="' + this.perspectiva_id + '" data-prproyecto="' + this.presupuesto_proyecto + '" data-siva="' + this.ae_ejecucion + '"><i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i><i class="fas fa-check-square m-1" title="Verificar" id="devolvereq" data-req="' + this.req_id + '" data-estado="' + this.solicitud_estado + '"></i></div>';
                    } else {
                        div = '<i class="fas fa-chevron-circle-down m-1" id="listaS" data-req="' + this.req_id + '" title="Listar"></i>';
                    }
                    $('#listaServicios').append('<div class="encabezado_5 estilobody text-justify">' + this.ag_nombre + '</div><div class="encabezado_4 estilobody justify-content-center">OEI-0' + this.perspectiva_id + '</div><div class="encabezado_2 estilobody text-justify">' + this.proyecto_nombre + '</div>\n\
                    <div class="encabezado_5 estilobody text-justify">' + this.req_nombre + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(totalp) + '</div><div class="encabezado_5 estilobody justify-content-center">' + new Intl.NumberFormat("US", options2).format(this.actividad_monto) + '</div>\n\
                    <div class="encabezado_4 estilobody justify-content-center">' + div + '</div>\n\
                    <div style="display:none; background:rgba(0, 0, 0, 0.05);" id="lserv' + this.req_id + '" class="align-self-center encabezado p-0"></div>');
                    total = total + totalp;
                    if (this.cuatri.length > 0) {
                        $.each(this.cuatri, function (indice, sp) {
                            var tipo;
                            if (sp.actividad_id === 3) {
                                tipo = 'HONORARIO FIJO';
                            } else if (sp.actividad_id === 2) {
                                tipo = 'HONORATIOS MENSUALES';
                            } else if (sp.actividad_id === 1) {
                                tipo = 'HORAS CLASE';
                            }
                            $('#listaServicios').children('div#lserv' + req).append('<div class="encabezado_2 estilobody text-justify">NOMBRE: ' + sp.req_nombre + '</div><div class="encabezado_2 estilobody text-justify">APELLIDO: ' + sp.req_descripcion + '</div><div class="encabezado_2 estilobody text-justify">CARGO: ' + sp.actividad_nombre + '</div>\n\
                            <div class="encabezado_5 estilobody justify-content-center">TIPO: ' + tipo + '</div><div class="encabezado_5 estilobody justify-content-center">TOTAL: ' + new Intl.NumberFormat("US", options2).format(sp.req_costo_total) + '</div><div class="encabezado_4 estilobody justify-content-center"><i class="fas fa-edit" id="modserv" data-id="' + sp.req_id + '" \n\
                            data-tipo="' + sp.actividad_id + '" data-horas="' + sp.n_horas + '" data-fi="' + sp.fecha_inicio + '" data-ff="' + sp.fecha_fin + '" data-cantidad="' + sp.cantidad + '" data-cargo="' + sp.actividad_nombre + '" data-nombre="' + sp.req_nombre + '" data-apellido="' + sp.req_descripcion + '" data-cedula="' + sp.solicitud_cedula + '" data-total="' + sp.req_costo_total + '" data-req="' + req + '" data-observacion="' + sp.solestado_observacion + '" data-verificacion="' + sp.solestado_numero + '"></i></div>');
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
    if (data['estado'] === 1) {
        $('#modalEnvRecibir').addClass('d-block');
        $('#modalEnvRecibir').removeClass('d-none');
    } else {
        $('#modalEnvRecibir').removeClass('d-block');
        $('#modalEnvRecibir').addClass('d-none');
    }
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
                'siva': data.siva,
                'deudasiva':data.deudasiva});
            array.push(art);
        }
    });
    for (var i = 0; i < array.length; i++) {
        var tot = 0, iva = 0, total=0, tiva=0;
        $.ajax({
            url: "../solicitud?accion=ListaServicioProfesionalOP",
            type: 'POST',
            data: {req: array[i].id},
            dataType: 'json',
            async: false
        })
                .done(function (response) {
                    $.each(response, function () {
                        if (this.solicitud_id === 0 && this.solestado_numero === 1) {
                            tot = tot + this.req_costo_sin_iva;
                            iva = iva + (this.req_costo_total - this.req_costo_sin_iva);
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
                if(array[i].deudasiva>0){
                    total=tot;
                    tiva=iva;
                }else{
                    total=tot+iva;
                    tiva=0.0;
                }
        $('#listaProyectos').append('<tr><td>' + array[i].ag + '</td>\n\
                        <td class="justify-content-center">OEI-0' + array[i].oei + '</td>\n\
                        <td>' + array[i].nombre + '</td>\n\
                        <td class="justify-content-center text-center">' + new Intl.NumberFormat("US", options2).format(total) + '</td>\n\
                        <td class="justify-content-center text-center">' + new Intl.NumberFormat("US", options2).format(tiva) + '</td>\n\
                        <td class="justify-content-center text-center">$ 0,0</td>\n\
                        <td><input type="checkbox" value="' + array[i].id + '" name="re" id="re" checked="checked" data-siva="' + total + '" data-monto="' + tiva + '" data-proyecto="' + array[i].prproyecto + '"></td>\n\
                    </tr>');
        sum = sum + total+tiva;
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
        var m = data['siva'];
        var mo, iv, an;
        if (m == null || m === null || m === 'undefined') {
            mo = 0;
        } else {
            mo = m;
        }
        var i = data['monto'];
        if (i == null || i === null || i === 'undefined') {
            iv = 0;
        } else {
            iv = i;
        }
        an = 0;

        rem.push(mo);
        rem.push(iv);
        rem.push(an);
        rem.push(data['proyecto']);
        re.push(rem);
    });
    var req = JSON.stringify(re);
    if (cargo === "") {
        alert("Debe ingresar la descripci\u00f3n.");
    } else {
        var formData = new FormData();
        formData.append('req', req);
        formData.append('ag', ag);
        formData.append('rector', rector);
        formData.append('cargo', cargo);
        formData.append('cedulausuario', $('#cedulaProyecto').val());
        formData.append('anio', anio);
        $.ajax({
            url: "../solicitud?accion=IngresarSolicitudOPC",
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
        $('#fehchai').addClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').addClass('d-none');
        $('#nhoras').val(data['horas']);
        $('#sueldoH').val(data['cantidad']);
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
    } else if (id === 3) {
        $('#horas').addClass('d-none');
        $('#shoras').addClass('d-none');
        $('#fehchai').addClass('d-none');
        $('#fechaf').addClass('d-none');
        $('#personalmed').addClass('d-none');
        $('#sueldo').addClass('d-none');
        $('#tpagas').removeClass('d-none');
        var d = data['total'] / 1.12;
        $('#totalpagar').val(d.toFixed(2));
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
    if ($('input:checkbox[name=personalm]:checked').val() == 'undefiden' || $('input:checkbox[name=personalm]:checked').val() == null || $('input:checkbox[name=personalm]:checked').val() == '') {
        iva = 0;
    } else {
        iva = $('input:checkbox[name=personalm]:checked').val();
    }
    $.ajax({
        url: "../actividadReq?accion=ModificarServiciosPO",
        type: 'POST',
        data: {horas: horas, shoras: shoras, fechai: fechai, fechaf: fechaf, sueldoMensual: sueldoMensual,
            totalpagar: totalpagar, nombre: nombre, req: req, cedula: cedula, tipo: tipo, servicio: serv, apellido: apellido, cargo: cargo, cedulaser: cedulaser, iva: iva, observacion: observacion},
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
            url: "../solicitud?accion=EnviarRequerimientosRegOP",
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
        url: "../solicitud?accion=VerificarServicioPO",
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