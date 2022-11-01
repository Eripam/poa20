<%-- 
    Document   : pTechos
    Created on : 07-oct-2019, 15:16:15
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adTecho"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap4.min.css">
    <body>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo">LISTA DE PROYECTOS</p>
                        <input type="hidden" name="tipoAg" id="tipoAg" value="<%=intIdTipoAreaGestion%>">
                        <input type="hidden" name="areaPadre" id="areaPadre" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tipoUsuario" id="tipoUsuario" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" id="selectoranio" name="selectoranio" value="<%=intAnio%>">
                        <div class="row align-content-center align-items-center align-self-center">
                            <%if (intIdTipoAreaGestion == 2) {%>
                            <div class="col-3">
                                <label>Unidad:</label>
                                <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                    <option value="0" selected>Todos</option>
                                    <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                    <%
                                        ResultSet rs = adAreaGestion.listaAreaGestionHijos(IntIdAreaGestion);
                                        while (rs.next()) {
                                    %>
                                    <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <%} else if (adAreaGestion.numeroAreaGestion(IntIdAreaGestion) > 0 && (intIdTipoUsuario != 15 && IntIdAreaGestion != 45)) {%>
                            <div class="col-3">
                                <label>Unidad:</label>
                                <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                    <option value="0" selected>Todos</option>
                                    <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                    <%
                                        ResultSet rs = adAreaGestion.listaAreaGestionHijos(IntIdAreaGestion);
                                        while (rs.next()) {
                                    %>
                                    <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <%}%>
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
                                    <th>ACCIÓN</th>
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
        <script src="../javascript/jsProyectoLista.js" type="text/javascript"></script>
    </body>
</html>
