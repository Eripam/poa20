var formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
});

$('#selectareas').on('change', function () {
    $('.contenedorCarreras').empty();
    $('#contenedorBody').empty();
    $('.contenedorCarrerasA').empty();
    $('#contenedorBodyA').empty();
    listarUnidadesHijas($(this).val(), $('#selectanio').val(), 2, 'CORRIENTE');
    listarUnidadesHijas($(this).val(), $('#selectanio').val(), 1, 'CORRIENTE');
});

/*Lista de techos por unidad*/
function listarUnidadesHijas(tipo, anio, financiamiento, pres) {
    $('#cuerpoListarTechosTotal').empty();
    $.ajax({
        url: "../techo?accion=ListarAreasGestionUnidades",
        type: 'POST',
        data: {tipo: tipo, anio: anio, financiamiento: financiamiento, presupuesto: pres},
        async: false,
        dataType: 'json'
    })
            .done(function (response) {
                var div, div2, totalas = 0, totalpl = 0, totalac = 0, totalin = 0, totalvin = 0, totalges = 0, totalpr = 0, totaldis = 0, totaldeudas = 0;
                if (tipo != 2 && financiamiento == 1) {
                    $('.contenedorCarreras').empty();
                    $('#contenedorBody').empty();
                    div = '<th>FACULTAD/EXTENSION</th><th>ASIGNADO</th>\n\
                                            <th>PLANIFICADO</th>\n\
                                            <th>ACADEMICO</th>\n\
                                            <th>INVESTIGACION</th>\n\
                                            <th>VINCULACION</th>\n\
                                            <th>TOTAL PRIORIZADO</th>\n\
                                            <th>DEUDAS</th>\n\
                                            <th>DISPONIBLE</th>';
                } else if (tipo != 2 && financiamiento == 2) {
                    $('.contenedorCarrerasA').empty();
                    $('#contenedorBodyA').empty();
                    div = '<th>FACULTAD/EXTENSION</th><th>PLANIFICADO</th>\n\
                                            <th>ACADEMICO</th>\n\
                                            <th>INVESTIGACION</th>\n\
                                            <th>VINCULACION</th>\n\
                                            <th>TOTAL PRIORIZADO</th>';
                } else if (tipo == 2 && financiamiento == 1) {
                    $('.contenedorCarreras').empty();
                    $('#contenedorBody').empty();
                    div = '<th>UNIDAD ADMINISTRATIVA</th><th>PLANIFICADO</th>\n\
                                            <th>ACADEMICO</th>\n\
                                            <th>INVESTIGACION</th>\n\
                                            <th>VINCULACION</th>\n\
                                            <th>GESTION</th>\n\
                                            <th>TOTAL PRIORIZADO</th><th>TOTAL OBLIG. PEND.</th>';
                } else {
                    $('.contenedorCarreras').empty();
                    $('#contenedorBody').empty();
                    div = '<th>UNIDAD ADMINISTRATIVA</th><th>PLANIFICADO</th>\n\
                                            <th>ACADEMICO</th>\n\
                                            <th>INVESTIGACION</th>\n\
                                            <th>VINCULACION</th>\n\
                                            <th>GESTION</th>\n\
                                            <th>TOTAL PRIORIZADO</th>';
                }
                if (financiamiento === 1) {
                    $('.contenedorCarreras').html('<tr>' + div + '</tr>');
                } else {
                    $('.contenedorCarrerasA').html('<tr>' + div + '</tr>');
                }
                $.each(response, function () {
                    totalas = totalas + this.techo_total;
                    totalpl = totalpl + this.techo_planificado;
                    totalac = totalac + this.presupuesto_aca;
                    totalin = totalin + this.presupuesto_inv;
                    totalvin = totalvin + this.presupuesto_vin;
                    totalpr = totalpr + this.presupuesto_total;
                    totaldis = totaldis + (this.techo_inicial - this.techo_reforma);
                    totalges = totalges + this.presupuesto_ges;
                    totaldeudas = totaldeudas + this.techo_reforma;
                    if (this.techo_planificado > 0) {
                        if (tipo != 2 && financiamiento == 1) {
                            div2 = '<td class="text-justify">' + this.ag_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_total) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_planificado) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_aca) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_inv) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_vin) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_total) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_reforma) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_inicial - this.techo_reforma) + '</td>';
                        } else if (tipo != 2 && financiamiento == 2) {
                            div2 = '<td class="text-justify">' + this.ag_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_planificado) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_aca) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_inv) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_vin) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_total) + '</td>';
                        } else if (tipo == 2 && financiamiento == 1) {
                            div2 = '<td class="text-justify">' + this.ag_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_planificado) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_aca) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_inv) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_vin) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_ges) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_total) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_reforma) + '</td>';
                        } else {
                            div2 = '<td class="text-justify">' + this.ag_nombre + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_planificado) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_aca) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_inv) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_vin) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_ges) + '</td>\n\
                                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.presupuesto_total) + '</td>';
                        }
                        if (financiamiento === 1) {
                            $('#contenedorBody').append('<tr>' + div2 + '</tr>');
                        } else {
                            $('#contenedorBodyA').append('<tr>' + div2 + '</tr>');
                        }
                    }
                });
                var div3;
                if (tipo != 2 && financiamiento == 1) {
                    div3 = '<tr style="font-weight: bold"><td>TOTAL</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalas) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalpl) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalac) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalin) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalvin) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(totalpr) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totaldeudas) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totaldis) + '</td></tr>';
                } else if (tipo != 2 && financiamiento == 2) {
                    div3 = '<tr style="font-weight: bold"><td>TOTAL</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalpl) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalac) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalin) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalvin) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(totalpr) + '</td></tr>';
                } else if (tipo == 2 && financiamiento == 1) {
                    div3 = '<tr style="font-weight: bold"><td>TOTAL</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalpl) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalac) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalin) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalvin) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(totalges) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalpr) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totaldeudas) + '</td></tr>';
                } else {
                    div3 = '<tr style="font-weight: bold"><td>TOTAL</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalpl) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalac) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalin) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalvin) + '</td>\n\
                    <td>' + new Intl.NumberFormat("US", formateador()).format(totalges) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(totalpr) + '</td></tr>';
                }
                if (financiamiento === 1) {
                    $('#contenedorBody').append('<tr>' + div3 + '</tr>');
                } else {
                    $('#contenedorBodyA').append('<tr>' + div3 + '</tr>');
                }
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}