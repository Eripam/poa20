<%-- 
    Document   : pTechos
    Created on : 07-oct-2019, 15:16:15
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adEvaluacion"%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adTecho"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap4.min.css">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <body>
        <div class="modal fade" id="modalGrafico" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">GRÁFICO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body container-fluid justify-content-center main-center">
                        <div id="graficonombre" style="font-weight: bold;" class="col-12 text-center mt-2 mb-3"></div>
                        <div id="columnchart_values"></div>
                        <div id="ejecucioneval" style="font-weight: bold;" class="col-12 text-center mt-2"></div>
                        <div id="nplanificados" style="font-weight: bold;" class="col-12 text-center"></div>
                        <div id="ncuatrimestre" style="font-weight: bold;" class="col-12 text-center"></div>
                        <div id="nenviados" style="font-weight: bold;" class="col-12 text-center"></div>
                        <div id="nevaluados" style="font-weight: bold;" class="col-12 text-center"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <%
                            Integer cua = adEvaluacion.numeroCuatrimestre(intAnio);
                            Integer sum = adEvaluacion.sumaCuatrimestre(intAnio);
                            if (sum > 0) {
                                if (sum > 1) {
                        %>
                        <div class="row align-content-center align-items-center align-self-center justify-content-end">
                            <select name="selectcuatrimestre" id="selectcuatrimestre" class="selectpicker col-3 col-xs-3 col-md-3 p-0">
                                <option value="<%=cua%>" selected>Cuatrimestre <%=cua%></option>
                                <%
                                    ResultSet rs3 = adEvaluacion.ListaCuatriSelect(cua, intAnio);
                                    while (rs3.next()) {
                                %>
                                <option value="<%= rs3.getString("tiempos_cuatrimestre")%>">Cuatrimestre <%=rs3.getString("tiempos_cuatrimestre")%></option>
                                <%}%>
                            </select>
                        </div>
                        <%}%>
                        <%if (cua == 1) {%>
                        <p class="titulo p-0 m-0" id="cuatrititulo">EVALUACIÓN I CUATRIMESTRE</p>
                        <%} else if (cua == 2) {%>
                        <p class="titulo p-0 m-0" id="cuatrititulo">EVALUACIÓN II CUATRIMESTRE</p>
                        <%} else {%>
                        <p class="titulo p-0 m-0" id="cuatrititulo">EVALUACIÓN III CUATRIMESTRE</p>
                        <%}%>
                        <div class="col-12 p-0 m-0" id="colorelip"><i class="fas fa-ellipsis-h fa-2x"></i></div>
                        <input type="hidden" name="tipoAg" id="tipoAg" value="<%=intIdTipoAreaGestion%>">
                        <input type="hidden" name="areaPadre" id="areaPadre" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="nombrepadre" id="nombrepadre" value="<%=strNombreAreaGestion%>">
                        <input type="hidden" name="tipoUsuario" id="tipoUsuario" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" name="tipousuario" id="tipousuario" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs" name="idAgObEs">
                        <input type="hidden" name="cuatrimestreeval" id="cuatrimestreeval" value="<%=cua%>">
                        <div class="row align-content-center align-items-center align-self-center">
                            <%if (intIdTipoAreaGestion == 2) {%>
                            <div class="col-3">
                                <label>Unidad:</label>
                                <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                    <option disabled selected>Seleccionar..</option>
                                    <option value="0">Todos</option>
                                    <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                    <%
                                        ResultSet rs = adAreaGestion.listaAreaGestionHijos(IntIdAreaGestion, intAnio);
                                        while (rs.next()) {
                                    %>
                                    <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <%} else if (adAreaGestion.numeroAreaGestion(IntIdAreaGestion, intAnio) > 0 && intIdTipoUsuario != 15 && intIdTipoUsuario != 19) {%>
                            <div class="col-3">
                                <label>Unidad:</label>
                                <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                    <option disabled selected>Seleccionar..</option>
                                    <option value="0">Todos</option>
                                    <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                    <%
                                        ResultSet rs = adAreaGestion.listaAreaGestionHijos(IntIdAreaGestion, intAnio);
                                        while (rs.next()) {
                                    %>
                                    <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <%}%>
                            <div class="col-3">
                                <input type="hidden" value="<%=IntIdAreaGestion%>" name="areapadreinput" id="areapadreinput">
                                <input type="hidden" value="<%=intIdTipoUsuario%>" name="tipousuarioinput" id="tipousuarioinput">
                                <input type="hidden" value="<%=cua%>" name="cuatrieval" id="cuatrieval">
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
                        <%} else {%>
                        <p class="titulo p-0 m-0" id="cuatrititulo">NO SE INICIADO PROCESO DE EVALUACIÓN PARA EL AÑO <%=intAnio%></p>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsProyectosEvaluacion.js" type="text/javascript"></script>
        <script>listarProyectoEvaluacion(<%=intIdTipoUsuario%>,<%=IntIdAreaGestion%>, <%=cua%>, <%=intAnio%>);</script>
        <script src="../javascript/jsGrafico.js" type="text/javascript"></script>
    </body>
</html>
