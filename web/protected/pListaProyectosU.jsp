<%-- 
    Document   : pTechos
    Created on : 07-oct-2019, 15:16:15
    Author     : Erika Ar�valo
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adTecho"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap4.min.css">
    <body>
        <div class="cross-center main-center d-none" id="loader" style="background: rgba(0,0,0,.1); z-index: 1000; position: absolute; width: 100%; min-height: 100vh;">
            <div class="spinner-border text-info" style="width: 5rem; height: 5rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <%if (intIdTipoUsuario == 1) {%>
                        <div class="row main-end">
                            <div class="col-2 main-end" style="font-weight: bold">Techo Asignado:</div>
                            <%
                                adTecho aT = new adTecho();
                                DecimalFormat formateador = new DecimalFormat("####,###,###.##");
                            %>
                            <div class="col-2">$ <%=formateador.format(aT.techoInstitucional(intAnio))%></div>
                        </div>
                        <div class="row main-end">
                            <div class="col-2 main-end" style="font-weight: bold">Obligaciones:</div>
                            <div class="col-2">$ <%=formateador.format(aT.techoObligaciones(intAnio))%></div>
                        </div>
                        <div class="row main-end">
                            <div class="col-2 main-end" style="font-weight: bold">Techo Disponible:</div>
                            <div class="col-2">$ <%=formateador.format(aT.techoInstitucional(intAnio) - (aT.techoPriorizado(intAnio) + aT.techoObligaciones(intAnio)))%></div>
                        </div>
                        <%}%>
                        <p class="titulo">LISTA DE PROYECTOS</p>
                        <input type="hidden" name="tipoAg" id="tipoAg" value="<%=intIdTipoAreaGestion%>">
                        <input type="hidden" name="areaPadre" id="areaPadre" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tipoUsuario" id="tipoUsuario" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" id="selectoranio" name="selectoranio" value="<%=intAnio%>">
                        <div class="row align-content-center align-items-center align-self-center">
                            <div class="col-3">
                                <label>Unidad:</label>
                                <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                    <option disabled selected>Seleccionar..</option>
                                    <%if (intIdTipoUsuario != 11 && intIdTipoUsuario != 26) {%>
                                    <option value="0">Todos</option>
                                    <%}
                                        ResultSet rs;
                                        if (intIdTipoUsuario == 11 || intIdTipoUsuario == 16 || intIdTipoUsuario == 17 || intIdTipoUsuario == 26) {
                                            rs = adAreaGestion.listaAreaGestionAsignadas(cedula);
                                        } else if (intIdTipoUsuario == 6 || intIdTipoUsuario == 7 || intIdTipoUsuario == 19) {
                                            rs = adAreaGestion.listaAreaGestionFE();
                                        } else {
                                            rs = adAreaGestion.listaAreaGestionDeudas();
                                        }
                                        while (rs.next()) {
                                    %>
                                    <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <%if (intIdTipoUsuario != 7 && intIdTipoUsuario != 8 && intIdTipoUsuario != 16 && intIdTipoUsuario != 17 && intIdTipoUsuario != 19) {%>
                            <div class="col-3">
                                <label>Tipo:</label>
                                <select class="col-9 selectpicker p-0 m-0" id="selectortipo" name="selectortipo">
                                    <option selected disabled>Seleccionar...</option>
                                    <option value="0" selected >Todos</option>
                                    <%
                                        ResultSet rs3 = adTecho.listaTipoProyecto();
                                        while (rs3.next()) {
                                    %>
                                    <option value="<%= rs3.getString("tp_id")%>"><%=rs3.getString("tp_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <%}%>
                            <div class="col-3">
                                <button id="actualizarM">Actualizar Montos</button>
                            </div>
                        </div>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm" id="listaProyectoTable">
                            <thead class="table-azul">
                                <tr>
                                    <th>UNIDAD</th>
                                    <th>PROYECTO</th>
                                    <th>RESPONSABLE</th>
                                    <th>TIPO</th>
                                    <th>TOTAL</th>
                                    <th>ESTADO</th>
                                    <th>ACCI�N</th>
                                </tr>
                            </thead>
                            <tbody id="listaProyectos">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsProyectoListaU.js" type="text/javascript"></script>
    </body>
</html>
