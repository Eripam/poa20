var formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD'
});

$(document).ready(function () {
    listarTechoFacultad($("#tipoUsuario").val(), $("#areaPadre").val(), $('#selectanio').val());
    listarTechoAdicional($("#tipoUsuario").val(), $("#areaPadre").val(), $('#selectanio').val());
    listarUnidadesHijas($("#tipoUsuario").val(), $("#areaPadre").val(), $('#selectanio').val());
});

/*lsitar unidades hijas con sus techos*/
function listarUnidadesHijas(tipo, areaGestion, anio) {
    $('#cuerpoListarTechosTotal').empty();
    $('.contenedorCarreras').empty();
    $.ajax({
        url: "../areaGestion?accion=ListarAreasGestionHijas",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areaGestion, area: 0, tipoproyecto: 0},
        dataType: 'json'
    })
            .done(function (response) {
                $.each(response, function () {
                    $('.contenedorCarreras').append(
                            '<div class="tab-content ml-5 mr-5 pestania">\n\
                                <p class="titulo">' + this.ag_nombre + '</p>\n\
                                <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm">\n\
                                    <thead class="table-azul">\n\
                                        <tr>\n\
                                            <th>TIPO</th>\n\
                                            <th>ASIGNADO</th>\n\
                                            <th>PLANIFICADO</th>\n\
                                            <th>DISPONIBLE</th>\n\
                                        </tr>\n\
                                    </thead>\n\
                                    <tbody id="cuerpoListarTechoAG' + this.ag_id + '">\n\
                                        \n\
                                    </tbody>\n\
                                </table>\n\
                                </div>'
                            );
                    listarTechoUnidadHija(tipo, this.ag_id, anio)

                });
            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

/*listar los techos del hijo*/
function listarTechoUnidadHija(tipo, areaGestion, anio) {
    //alert(areaGestion);
    $.ajax({
        url: "../techo?accion=ListarTechoAdicional",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areaGestion, area: 0, tipoproyecto: 0, anio: anio},
        dataType: 'json'
    })
            .done(function (response) {
                var total_techo_asignado = 0;
                var total_techo_planificado = 0;
                var total_disponible = 0;
                var tabla_area_gestion = $('#cuerpoListarTechoAG' + areaGestion);
                $.each(response, function () {
                    //alert(response);
                    total_techo_asignado += this.techo_total;
                    total_techo_planificado += this.techo_planificado;
                    total_disponible += (this.techo_total - this.techo_planificado);
                    tabla_area_gestion.append(
                            '<tr><td>' + this.tp_nombre + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_total) + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_planificado) + '</td>\n\
                        <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_total - this.techo_planificado) + '</td></tr>'

                            );
                });

            })
            .fail(function () {
                console.error('ERROR en el controlador.');
            })
            .always(function () {
                console.info('Se completo correctamente. Techos Unidad  Hija');
            });
}

/*listat el total de techo*/
function listarTechoFacultad(tipo, areapadre, anio) {
    $('#cuerpoListarTechosTotal').empty();
    $.ajax({
        url: "../techo?accion=ListarTechoPresupuestario",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areapadre, area: 0, tipoproyecto: 0, anio: anio},
        dataType: 'json'
    })
            .done(function (response) {
                var total = 0;
                var total_techo_asignado = 0;
                var total_techo_asignado_carreras = 0;
                var total_techo_planificado = 0;
                var total_techo_planificado_deudas = 0;
                var total_techo_disponible = 0;
                var val;
                $.each(response, function () {
                    total_techo_asignado += this.techo_total;
                    total_techo_asignado_carreras += this.techo_asignado_carrera;
                    total_techo_planificado += this.techo_planificado;
                    if(this.techo_reforma==null || this.techo_reforma== '' || this.techo_reforma == 'undefined'){
                        val=0;
                    }else{
                        val=this.techo_reforma;
                    }
                    total_techo_planificado_deudas += val;
                    total_techo_disponible += (this.techo_total - this.techo_planificado - val);
                    $('#cuerpoListarTechosTotal').append('<tr>\n\
                                                               <td>' + this.tp_nombre + '</td>\n\
                                                               <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_total) + '</td>\n\
                                                               <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_asignado_carrera) + '</td>\n\
                                                               <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_planificado) + '</td>\n\
                                                               <td>' + new Intl.NumberFormat("US", formateador()).format(val) + '</td>\n\
                                                               <td>' + new Intl.NumberFormat("US", formateador()).format((this.techo_total - this.techo_planificado - val)) + '</td></tr>');
                });
                $('#cuerpoListarTechosTotal').append('<tr>\n\
                                                           <td >TOTAL</td>\n\
                                                           <td>' + new Intl.NumberFormat("US", formateador()).format(total_techo_asignado) + '</td>\n\
                                                           <td >' + new Intl.NumberFormat("US", formateador()).format(total_techo_asignado_carreras) + '</td>\n\
                                                           <td >' + new Intl.NumberFormat("US", formateador()).format(total_techo_planificado) + '</td>\n\
                                                           <td >' + new Intl.NumberFormat("US", formateador()).format(total_techo_planificado_deudas) + '</td>\n\
                                                           <td >' + new Intl.NumberFormat("US", formateador()).format(total_techo_disponible) + '</td></tr>');

            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completo correctamente');
            });
}

/*listar techo adicional*/
function listarTechoAdicional(tipo, areapadre, anio) {
    $('#cuerpoListarTechoAdicional').empty();
    $.ajax({
        url: "../techo?accion=ListarTechoAdicional",
        type: 'POST',
        data: {tipousuario: tipo, areapadre: areapadre, area: 0, tipoproyecto: 0, anio: anio},
        dataType: 'json'
    })
            .done(function (response) {

                var total = 0;
                var total_techo_asignado = 0;
                var total_techo_asignado_carreras = 0;
                var total_techo_planificado = 0;
                var total_techo_planificado_deudas = 0;
                var total_techo_disponible = 0;
                $.each(response, function () {
                    total_techo_asignado += this.techo_total;
                    total_techo_asignado_carreras += this.techo_asignado_carrera;
                    total_techo_planificado += this.techo_planificado;
                    total_techo_planificado_deudas += this.techo_reforma;
                    total_techo_disponible += (this.techo_total - this.techo_planificado - this.techo_reforma);
                    $('#cuerpoListarTechoAdicional').append('<tr>\n\
                                                       <td class="text-justify">' + this.tp_nombre + '</td>\n\
                                                       <td class="justify-content-end">' + new Intl.NumberFormat("US", formateador()).format(this.techo_total) + '</td>\n\
                                                       <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_planificado) + '</td>\n\
                                                       <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_reforma) + '</td>\n\
                                                       <td>' + new Intl.NumberFormat("US", formateador()).format(this.techo_total - this.techo_planificado - this.techo_reforma) + '</td></tr>');
                });
                $('#cuerpoListarTechoAdicional').append('<tr><td>TOTAL</td><td>' + new Intl.NumberFormat("US", formateador()).format(total_techo_asignado) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(total_techo_planificado) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(total_techo_planificado_deudas) + '</td><td>' + new Intl.NumberFormat("US", formateador()).format(total_techo_disponible) + '</td></tr>');

            })
            .fail(function () {
                console.log('No existe conexión con la base de datos.');
            })
            .always(function () {
                console.log('Se completó correctamente');
            });
}



