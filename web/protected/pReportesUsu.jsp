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
                    <!--<div class="content container-fluid m-3">
                       Tab panes 
                       <div class="container-fluid pestania"><br>-->
                    <div class="tab-content ml-5 mr-5 pestania">
                        <%if (intIdTipoUsuario != 16 && intIdTipoUsuario != 17 && intIdTipoUsuario != 14) {%>
                        <p class="titulo mb-0"><u>REPORTES POA</u></p>
                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs">
                        <input type="hidden" value="<%=intIdTipoUsuario%>" id="idTipoUsu">
                        <div class="container p-2 formulario pt-3">
                            <p class="titulo2 mb-0"><u>PDF</u></p>
                            <div class="row main-center">
                                <div class="col-5 main-end">Descargar POA <%=intAnio%></div>
                                <div class="col-5">
                                    <form method="POST" action="../controladorReportePDF" id="FrmDescargarPOA" class="instructivos_archivos_icono" target="_blank">
                                        <input type="hidden" value="reportePOAUnidades" name="accion">
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoa">
                                        <input type="hidden" value="<%=strNombreAreaGestion%>" name="agnombrepoa">
                                        <input type="hidden" value="<%=intAnio%>" name="anioreportePOA">
                                        <a href="#" onclick="activarPOA();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <p class="titulo2 mb-0"><u>Excel</u></p>
                            <div class="row main-center">
                                <div class="col-5 main-end">Descargar Excel</div>
                                <div class="col-5">
                                    <form method="POST" action="../reporteExcel" id="FrmDescargarExcel" class="instructivos_archivos_icono">
                                        <%if(intAnio==2020){%>
                                        <input type="hidden" value="reporteExcelUnidades" name="accion">
                                        <%}else{%>
                                        <input type="hidden" value="reporteExcelUnidades21" name="accion">
                                        <%}%>
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoaEx">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoaEx">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoaEx">
                                        <input type="hidden" value="<%=strNombreAreaGestion%>" name="agnombrepoaEx">
                                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" name="tipoAgEx">
                                        <input type="hidden" value="<%=intAnio%>" name="anioExcel">
                                        <a href="#" onclick="activarEx();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%}%>
                        <p class="titulo mb-0"><u>REPORTES EVALUACIÓN</u></p>
                        <div class="container p-2 formulario pt-3">
                            <div class="row main-center">
                                <div class="col-12">
                                    <form method="POST" action="../controladorReportePDF" id="FrmDescargarEvaluacion" class="instructivos_archivos_icono row" target="_blank">
                                        <%if (intIdTipoUsuario == 14 || intIdTipoUsuario == 16 || intIdTipoUsuario == 17) {%>
                                        <input type="hidden" value="reportePOAEvalAdmin" name="accion">
                                        <%} else {%>
                                        <input type="hidden" value="reportePOAEvalUnidades" name="accion">
                                        <%}%>
                                        <div class="col-4">Descargar PDF Evaluación</div>
                                        <div class="col-2">
                                            <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                                <%
                                                    ResultSet rs;
                                                    if (intIdTipoUsuario == 16 || intIdTipoUsuario == 17) {%>
                                                <option value="0">Todos</option>
                                                <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                                <%
                                                    rs = adAreaGestion.listaAreaGestionFE();
                                                    while (rs.next()) {
                                                %>
                                                <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                                <%}
                                                } else if (intIdTipoUsuario == 14) {%>
                                                <option value="0">Todos</option>  
                                                <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                                <%
                                                    rs = adAreaGestion.listaAreaGestionDeudas();
                                                    while (rs.next()) {
                                                %>
                                                <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                                <%}
                                                } else if (intIdTipoUsuario != 5 && intIdTipoUsuario != 19) {%>
                                                <option value="0">Todos</option>
                                                <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>    
                                                <%rs = adAreaGestion.listaAreaGestionHijos(IntIdAreaGestion);
                                                    while (rs.next()) {
                                                %>
                                                <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                                <%}%>
                                                <%} else {%>
                                                <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-2">
                                            <% if (intIdTipoUsuario != 16 && intIdTipoUsuario != 17) {%>
                                            <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selecttipo" name="selecttipo">
                                                <option value="0">Todos</option>
                                                <%
                                                    ResultSet rs3 = adTecho.listaTipoProyecto();

                                                    while (rs3.next()) {
                                                %>
                                                <option value="<%= rs3.getString("tp_id")%>"><%=rs3.getString("tp_nombre")%></option>
                                                <%}%>
                                            </select>
                                            <%} else {
                                                int tp;
                                                if (intIdTipoUsuario == 16) {
                                                    tp = 2;
                                                } else {
                                                    tp = 3;
                                                }
                                            %>
                                            <input type="hidden" name="selecttipo" id="selecttipo" value="<%=tp%>">
                                            <%}%>
                                        </div>
                                        <div class="col-2">
                                            <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="cuatrim" name="cuatrim">
                                                <option value="1">I Cuatrimestre</option>
                                                <option value="2">II Cuatrimestre</option>
                                                <option value="3">III Cuatrimestre</option>
                                                <option value="0">Completo</option>
                                            </select>
                                        </div>
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoa">
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoa">
                                        <input type="hidden" value="<%=strNombreAreaGestion%>" name="agnombrepoa">
                                        <input type="hidden" value="<%=intIdTipoUsuario%>" name="tipusuarioeval">
                                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                                        <a href="#" onclick="activarPOAE();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <%if (intIdTipoUsuario == 14) {%>
                            <div class="row">
                                <div class="col-4">Descargar Excel Evaluación</div>
                                <div class="col-4">
                                    <form method="POST" action="../reporteExcel" id="FrmReporteEval" class="instructivos_archivos_icono">
                                        <input type="hidden" value="reporteEvaluacion" name="accion">
                                        <input type="hidden" id="selectanio1" name="selectanio1" value="<%=intAnio%>">
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="cuatrimestre" name="cuatrimestre">
                                            <option value="1">I Cuatrimestre</option>
                                            <option value="2">II Cuatrimestre</option>
                                            <option value="3">III Cuatrimestre</option>
                                        </select>
                                        <a href="#" onclick="activarEvalu();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-4">Descargar Excel Evaluación 2 </div>
                                <div class="col-4">
                                    <form method="POST" action="../reporteExcel" id="FrmReporteEval2" class="instructivos_archivos_icono">
                                        <input type="hidden" value="reporteEvaluacion2" name="accion">
                                        <input type="hidden" id="selectanio2" name="selectanio2" value="<%=intAnio%>">
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="cuatrimestre2" name="cuatrimestre2">
                                            <option value="1">I Cuatrimestre</option>
                                            <option value="2">II Cuatrimestre</option>
                                            <option value="3">III Cuatrimestre</option>
                                            <option value="0">Final</option>
                                        </select>
                                        <a href="#" onclick="activarEvalu2();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../javascript/jsGeneral.js" type="text/javascript"></script>
        <script src="../javascript/jsExcel.js" type="text/javascript"></script>
    </body>
</html>