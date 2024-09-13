<%-- 
    Document   : pFormulacion
    Created on : 07-oct-2019, 13:50:35
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@page import="poa.acceso.adProyecto"%>
<%@page import="poa.acceso.adTecho"%>
<%@page import="poa.acceso.adPerspectivaObj"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <body>
        <%@include file="plantillas/header.jsp"%> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %>
                <div class="content col-11 container-fluid">
                    <!--<div class="content container-fluid m-3">
                       Tab panes 
                       <div class="container-fluid pestania"><br>-->
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo mb-0"><u><%=strNombreAreaGestion%></u></p>
                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs">
                        <input type="hidden" value="<%=intIdTipoUsuario%>" id="idTipoUsu">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <p class="titulo2 mt-2">ALINEACIÓN A LA PLANIFICACIÓN ESTRATEGICA</p>
                        <div class="container p-2 formulario pt-3">
                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <label class="col-12 col-xs-12 col-md-4 col-lg-3 col-xl-3">Objetivos Estratégicos: </label>
                                <select class="col-12 col-xs-12 col-md-8 col-lg-9 col-xl-9 selectpicker p-0 m-0" id="objest" name="objest">
                                    <option selected="true" disabled>Seleccionar...</option>
                                    <%
                                        ResultSet rs = adPerspectivaObj.listaPerspectiva();
                                        while (rs.next()) {
                                    %>
                                    <option value="<%= rs.getString("perspectiva_id")%>" disabled="true"><%=rs.getString("perspectiva_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <label class="col-12 col-xs-12 col-md-4 col-lg-3 col-xl-3 justify-content-lg-end">Objetivos Operativos: </label>
                                <select class="d-none col-12 col-xs-12 col-md-8 col-lg-9 col-xl-9 p-0 m-0" id="objobj" name="objobj">
                                </select>
                            </div>
                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 mt-3 justify-content-center" id="poliestr">
                                <div class="col-12 titulo3">
                                    <%if (intAnio <= 2022) {%>
                                    <u>Politicas y Estrategias</u>
                                        <%} else {%>
                                    <u>Politicas</u
                                        <%}%>
                                </div>
                                <div class="row col-12 p-0 m-0 justify-content-center" id="listapolest"></div>
                            </div>
                        </div>
                        <%if (intAnio <= 2022) {%>
                        <div class="d-none container p-2 formulario pt-3 mt-4 mb-3" id="actpres">
                            <p class="titulo2">ALINEACIÓN A PRODUCTO INSTITUCIONAL</p>
                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <label class="col-12 col-xs-12 col-md-4 col-lg-3 col-xl-3 justify-content-lg-end">PRODUCTO INSTITUCIONAL: </label>
                                <select class="d-none col-12 col-xs-12 col-md-8 col-lg-9 col-xl-9 p-0 m-0" id="actpresup" name="actpresup">
                                </select>
                            </div>
                        </div>
                        <%} else {%>
                        <div class="d-none container p-2 pt-3 mt-4 mb-3" id="actpres">
                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 col-xl-12">
                                <label class="col-12 col-xs-12 col-md-4 col-lg-3 col-xl-3 justify-content-lg-end">PROYECTO INSTITUCIONAL: </label>
                                <select class="d-none col-12 col-xs-12 col-md-8 col-lg-9 col-xl-9 p-0 m-0" id="actpresup" name="actpresup">
                                </select>
                            </div>
                        </div>
                        <%}%>
                    </div>
                    <%if (intIdTipoAreaGestion == 4) {%>
                    <button class="btn bton d-none" id="generarproy">GENERAR PROYECTO</button>     
                    <button class="btn bton d-none" id="ingresarproy">INGRESAR PROYECTO</button>     
                    <%}%>
                    <form class="container p-2 formulario pt-3 mt-4 needs-validation d-none"  method="POST" action="../proyecto?accion=IngresarProyecto" id="frmAddProyecto" novalidate enctype="multipart/form-data" accept-charset="UTF-8">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgFormulacion" name="idAgFormulacion">
                        <input type="hidden" value="<%=strNombreAreaGestion%>" id="stringNombreAg" name="stringNombreAg">
                        <input type="hidden" value="<%=strAliasAreaGestion%>" id="aliasAgFormulacion" name="aliasAgFormulacion">
                        <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" id="idActividadPres" name="idActividadPres">
                        <input type="hidden" id="txtanio" name="txtanio" value="<%=intAnio%>">
                        <input type="hidden" id="idOEIForm" name="idOEIForm">
                        <p class="titulo2">INGRESAR PROYECTO</p>
                        <div class="form-row">
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                <div class="row justify-content-center">
                                    <div class="alert alert-warning col-9" role="alert">
                                        <i class="fas fa-exclamation-triangle"></i>
                                        Por favor no ingresar con tildes ni caracteres especiales, y revisar la gramática
                                    </div>
                                </div>
                                <!--<div class="row main-center cross-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Proyectos 2019: </label>
                                    <select class="col-10 col-xs-10 col-md-8 p-0 selectpicker" id="selectServicios" name="selectServicios">
                                        <option selected disabled>Seleccionar..</option>
                                <%
                                    ResultSet rs5 = adProyecto.listarProcesosAutoeval(3);
                                    while (rs5.next()) {
                                %>
                                <option value="<%= rs5.getString("proceso_codigo")%>"><%=rs5.getString("proceso_nombre")%></option>
                                <%}%>
                            </select>
                        </div>-->
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="textNombre" name="textNombre" required minlength="1"></textarea>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Fin del proyecto:</label>
                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="textFinP" name="textFinP" required minlength="1"></textarea>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Propósito:</label>
                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="textProposito" name="textProposito" required minlength="2"></textarea>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                    <div class="row col-9 col-xs-9 col-md-8 p-0 m-0">
                                        <input type="text" class="form-control col-10 col-xs-10 col-md-10" id="textResponsableCed" name="textResponsableCed" placeholder="Cédula" required maxlength="10" pattern="[0-9]+" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                        <div class="col-1 col-xs-1 col-md-1" id="buscarCed"><i class="fas fa-search"></i></div>
                                        <input type="text" class="form-control col-12 col-xs-12 col-md-12 mt-1" id="textResponsable" name="textResponsable" required readonly>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Fecha Inicio:</label>
                                    <input type="text" class="form-control col-10 col-xs-10 col-md-8" id="textFechaI" name="textFechaI" readonly="true" required>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Fecha Fin:</label>
                                    <input type="text" class="form-control col-10 col-xs-10 col-md-8" id="textFechaF" name="textFechaF" readonly="true" required>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3" id="perfilInV">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Perfil:</label>
                                    <input type="file" class="form-control-file col-10 col-xs-10 col-md-8 p-0" id="filePerfil" name="filePerfil">
                                </div>
                            </div>
                            <!--<div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="integrantesInV">
                                 <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Integrantes:</label>
                                    <div class="row col-9 col-xs-9 col-md-8 p-0 m-0" id="integrantesMos">
                                        <input type="text" class="form-control col-10 col-xs-10 col-md-10" id="textIntegranteCed" name="textIntegranteCed[]" placeholder="Cédula" required maxlength="10" pattern="[0-9]+" oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                        <div class="col-1 col-xs-1 col-md-1 m-0 p-0"><i class="fas fa-search" id="buscarCedInt"></i><i class="fas fa-plus" id="iconoplusint"></i></div>
                                        <input type="text" class="form-control col-12 col-xs-12 col-md-12 mt-1" id="textIntegrantes" name="textIntegrantes[]" required readonly>
                                    </div>
                                </div>
                                
                                <!--<div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Integrantes:</label>
                                    <div class="col-10 col-xs-10 col-md-9" id="integrantesMos">
                                        <div class="row container cross-center">
                                            <div class="col-10 col-xs-10 col-md-9">
                                                <textarea class="form-control" id="textIntegrantes" name="textIntegrantes[]" placeholder="Integrante de proyecto"></textarea>
                                            </div>
                                            <i class="fas fa-plus" id="iconoplusint"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>-->
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="coejeSelec">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Coejecución:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" multiple data-selected-text-format="count > 6" id="selectAgC" name="selectAgC">
                                        <option value="0">Ninguno</option>
                                        <%
                                            ResultSet rs6 = adAreaGestion.listaAreaGestionUnidadesAdminTotas(intAnio);
                                            while (rs6.next()) {
                                        %>
                                        <option title="<%=rs6.getString("ag_alias")%>" value="<%=rs6.getString("ag_id")%>"><%=rs6.getString("ag_nombre")%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <%if (intIdTipoUsuario == 18) {%>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="multiSelec">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Multidisciplinario:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" multiple data-selected-text-format="count > 6" id="selectAgM" name="selectAgM">
                                        <option value="0">Ninguno</option>
                                        <%
                                            ResultSet rs2 = adAreaGestion.listaAreaGestionMulti(intAnio);
                                            while (rs2.next()) {
                                        %>
                                        <option title="<%=rs2.getString("ag_alias")%>" value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <%}%>
                        </div>
                        <%if (intAnio < 2022) {%>
                        <p class="titulo2">ARTICULACIÓN CON PROCESOS DE ASEGURAMIENTO DE LA CALIDAD</p>
                        <div class="form-row">
                            <%if (intAnio == 2022) {%>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3">
                                <div class="row main-center cross-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Proceso de aseguramiento de la calidad:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selectProceso" name="selectProceso">
                                        <option selected disabled>Seleccionar..</option>
                                        <%
                                            ResultSet rs3 = adProyecto.listarProcesosAutoeval(4);
                                            while (rs3.next()) {
                                        %>
                                        <option value="<%= rs3.getString("proceso_codigo")%>"><%=rs3.getString("proceso_nombre")%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12  mb-3">
                                <div class="row main-center cross-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Actividades:</label>
                                    <div class="col-10 col-xs-10 col-md-9" id="evalinst">
                                        <div class="row container cross-center">
                                            <div class="col-10 col-xs-10 col-md-9">
                                                <textarea class="form-control" id="accmejins" name="accmejins[]" placeholder="Registre las acciones de mejora identificadas en el informe del proceso de autoevaluación seleccionado que se cumplirán con la ejecución del proyecto. Registrar en el mismo orden presentado en el informe."></textarea>
                                            </div>
                                            <i class="fas fa-plus" id="iconoplusins"></i> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%} else {%>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center cross-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Proceso de autoevaluación de Institucional:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selectProceso" name="selectProceso">
                                        <option selected disabled>Seleccionar..</option>
                                        <%
                                            ResultSet rs3 = adProyecto.listarProcesosAutoeval(1);
                                            while (rs3.next()) {
                                        %>
                                        <option value="<%= rs3.getString("proceso_codigo")%>"><%=rs3.getString("proceso_nombre")%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center cross-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Proceso de autoevaluación de Carreras</label>
                                    <select class="col-10 col-xs-10 col-md-8 p-0 selectpicker" id="selectProcesoCar" name="selectProcesoCar">
                                        <option selected disabled>Seleccionar..</option>
                                        <%
                                            ResultSet rs4 = adProyecto.listarProcesosAutoeval(3);
                                            while (rs4.next()) {
                                        %>
                                        <option value="<%= rs4.getString("proceso_codigo")%>"><%=rs4.getString("proceso_nombre")%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center cross-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Acciones de Mejora Institucional:</label>
                                    <div class="col-10 col-xs-10 col-md-9" id="evalinst">
                                        <div class="row container cross-center">
                                            <div class="col-10 col-xs-10 col-md-9">
                                                <textarea class="form-control" id="accmejins" name="accmejins[]" placeholder="Registre las acciones de mejora identificadas en el informe del proceso de autoevaluación seleccionado que se cumplirán con la ejecución del proyecto. Registrar en el mismo orden presentado en el informe."></textarea>
                                            </div>
                                            <i class="fas fa-plus" id="iconoplusins"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                <div class="row main-center cross-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Acciones de Mejora:</label>
                                    <div class="col-10 col-xs-10 col-md-9" id="evalcar">
                                        <div class="row container cross-center">
                                            <div class="col-10 col-xs-10 col-md-9">
                                                <textarea class="form-control" id="accmejca" name="accmejca[]" placeholder="Registre las acciones de mejora identificadas en el informe del proceso de autoevaluación seleccionado que se cumplirán con la ejecución del proyecto. Registrar en el mismo orden presentado en el informe."></textarea>
                                            </div>
                                            <i class="fas fa-plus" id="iconopluscar"></i> 
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                        </div>
                        <%} else {%>
                        <!--<div class="row">
                            <div class="col-12 form-row">
                                <div class="col-12 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-2 justify-content-center justify-content-md-end cross-center">Planes:</label>
                                        <select class="selectpicker col-10 p-0" id="selectProceso" name="selectProceso">
                                            <option disabled>Seleccionar..</option>
                                            <option value="0" selected>Sin Articulación</option>
                        <%
                            /*ResultSet rs3 = adProyecto.listarProcesosAutoevalActivos();
                            while (rs3.next()) {*/
                        %>
                        <option value="<%/*=rs3.getString("proceso_codigo")*/%>"><%/*=rs3.getString("proceso_nombre")*/%></option>
                        <%/*}*/%>
                    </select>
                </div>
            </div>
        </div>
        <div class="col-12 form-row">
            <div class="col-12 mb-3">
                <div class="row main-center">
                    <label for="validationCustom01" class="col-2 justify-content-center justify-content-md-end cross-center">Actividades:</label>
                    <select class="selectpicker col-10 p-0" id="selectAcciones" name="selectAcciones[]" data-live-search="true" multiple data-selected-text-format="count > 6">
                    </select>
                </div>
            </div>
        </div>
    </div>-->
                        <%}%>
                        <button class="btn bton" type="submit">GUARDAR</button>
                        <div id="alertProyecto">
                        </div>
                    </form>
                    <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm">
                        <thead class="table-azul">
                            <tr>
                                <th>NOMBRE</th>
                                <th>RESPONSABLE</th>
                                <th>FECHA INICIO</th>
                                <th>FECHA FIN</th>
                                <th>ACCIÓN</th>
                            </tr>
                        </thead>
                        <tbody id="proyectosForm">
                        </tbody>
                    </table>
                    <script>
                        // Example starter JavaScript for disabling form submissions if there are invalid fields
                        (function () {
                            'use strict';
                            window.addEventListener('load', function () {
                                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                var forms = document.getElementsByClassName('needs-validation');
                                // Loop over them and prevent submission
                                var validation = Array.prototype.filter.call(forms, function (form) {
                                    form.addEventListener('submit', function (event) {
                                        if (form.checkValidity() === false) {
                                            event.preventDefault();
                                            event.stopPropagation();
                                        } else {
                                            // alert('validado');
                                        }
                                        form.classList.add('was-validated');
                                    }, false);
                                });
                            }, false);
                        })();
                    </script>
                </div>
            </div>
        </div>
    </div>
    <%@include file="plantillas/footer.jsp" %>
    <script src="../javascript/jsPerspObjetivo.js" type="text/javascript"></script>
    <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
    <script src="../javascript/jsFormulacion.js"></script>
    <script src="../javascript/jsCentralizada.js"></script>
</body>
</html>