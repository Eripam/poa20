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
                        <p class="titulo mb-0"><u>REPORTES</u></p>
                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs">
                        <input type="hidden" value="<%=intIdTipoUsuario%>" id="idTipoUsu">
                        <div class="container p-2 formulario pt-3">
                            <p class="titulo2 mb-0"><u>Excel</u></p>
                            <div class="row">
                                <div class="col-10">Descargar Excel</div>
                                <div class="col-1">
                                    <form method="POST" action="../reporteExcel" id="FrmDescargarExcel" class="instructivos_archivos_icono">
                                        <input type="hidden" value="<%=intAnio%>" name="anioS" id="anioS">
                                        <%if (intAnio == 2020 && intIdTipoUsuario != 9) {%>
                                        <input type="hidden" value="reporteExcel" name="accion">
                                        <%} else if ((intAnio == 2021 || intAnio==2022) && intIdTipoUsuario != 9) {%>
                                        <input type="hidden" value="reporteExcel21" name="accion">
                                        <%} else if ((intAnio == 2021 || intAnio==2022) && intIdTipoUsuario == 9) {%>
                                        <input type="hidden" value="reporteExcelCompras" name="accion">
                                        <%}%>
                                        <a href="#" onclick="activarEx();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <%if (intIdTipoUsuario != 9) {%>
                            <div class="row">
                                <div class="col-10">Descargar Excel Verificados</div>
                                <div class="col-1">
                                    <form method="POST" action="../reporteExcel" id="FrmDescargarExcelV" class="instructivos_archivos_icono">
                                        <input type="hidden" value="<%=intAnio%>" id="anioS" name="anioS">
                                        <%if (intAnio == 2020) {%>
                                        <input type="hidden" value="reporteExcelVer" name="accion">
                                        <%} else {%>
                                        <input type="hidden" value="reporteExcelVer21" name="accion">
                                        <%}%>
                                        <a href="#" onclick="activarExv();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10">Descargar Excel Verificados Final</div>
                                <div class="col-1">
                                    <form method="POST" action="../reporteExcel2" id="FrmDescargarExcelVC" class="instructivos_archivos_icono">
                                        <input type="hidden" value="<%=intAnio%>" id="anioS" name="anioS">
                                        <input type="hidden" value="reporteExcelVerFinal" name="accion">
                                        <a href="#" onclick="activarExvc();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <!--<div class="row">
                                <div class="col-10">Descargar Excel Obligaciones</div>
                                <div class="col-1">
                                    <form method="POST" action="../reporteExcel" id="FrmDescargarExcelDeudas" class="instructivos_archivos_icono">
                                        <input type="hidden" value="reporteExcelDeudas" name="accion">
                                        <a href="#" onclick="activarExDeudas();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10">Descargar Excel Comprometidos</div>
                                <div class="col-1">
                                    <form method="POST" action="../reporteExcel" id="FrmDescargarExcelComprometidos" class="instructivos_archivos_icono">
                                        <input type="hidden" value="reporteExcelComprometidos" name="accion">
                                        <a href="#" onclick="activarExCompr();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>-->
                            <div class="row">
                                <div class="col-10">Descargar Techos Priorizados</div>
                                <div class="col-1">
                                    <form method="POST" action="../controladorReportePDF2" id="FrmTechoPrior" class="instructivos_archivos_icono" target="_blank">
                                        <%if (intAnio != 2020) {%>
                                        <input type="hidden" value="reportePresupuestoPriorizado" name="accion">
                                        <%} else {%>
                                        <input type="hidden" value="reportePresupuestoPriorizado20" name="accion">
                                        <%}%>
                                        <input type="hidden" value="<%=intAnio%>" name="anio">
                                        <a href="#" onclick="activar();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-10">Descargar POA <%=intAnio%></div>
                                <div class="col-1">
                                    <form method="POST" action="../controladorReportePDF" id="FrmDescargarPOA" class="instructivos_archivos_icono" target="_blank">
                                        <input type="hidden" value="<%=intAnio%>" id="anioS" name="anioS">
                                        <%if (intAnio != 2020) {%>
                                        <input type="hidden" value="reportePOA" name="accion">
                                        <%} else {%>
                                        <input type="hidden" value="reportePOA20" name="accion">
                                        <%}%>
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoa">
                                        <input type="hidden" value="<%=intAnio%>" name="anioreporte">
                                        <a href="#" onclick="activarPOA();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">Subir Excel</div>
                                <div class="col-7">
                                    <form method="POST" id="FrmSubirExcel" enctype="multipart/form-data" class="instructivos_archivos_archivos">
                                        <input type="hidden" value="1" id="tipoUsuarioId" name="tipoUsuarioId">
                                        <input type="hidden" value="<%=intAnio%>" name="anioreporteexcel" id="anioreporteexcel">
                                        <input type="hidden" value="<%=cedula%>" name="cedulaexcel" id="cedulaexcel">
                                        <input type="file" name="excel" id="excel">
                                        <a href="#" onclick="subirExcel();" title="Subir excel"><i class="fas fa-file-upload"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">Subir Excel Deudas</div>
                                <div class="col-7">
                                    <form method="POST" id="FrmSubirExcelD" enctype="multipart/form-data" class="instructivos_archivos_archivos">
                                        <input type="hidden" value="2" id="tipoUsuarioIdD" name="tipoUsuarioIdD">
                                        <input type="hidden" value="<%=cedula%>" name="cedulaexceld" id="cedulaexceld">
                                        <input type="file" name="excel" id="excel">
                                        <a href="#" onclick="subirExcelD();" title="Subir excel"><i class="fas fa-file-upload"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">Subir Excel Comprometidos</div>
                                <div class="col-7">
                                    <form method="POST" id="FrmSubirExcelC" enctype="multipart/form-data" class="instructivos_archivos_archivos">
                                        <input type="file" name="excel" id="excel">
                                        <input type="hidden" value="<%=intAnio%>" name="anioreporteexcelC" id="anioreporteexcelC">
                                        <input type="hidden" value="<%=cedula%>" name="cedulaexcelc" id="cedulaexcelc">
                                        <a href="#" onclick="subirExcelC();" title="Subir excel"><i class="fas fa-file-upload"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">Subir Excel Reformas</div>
                                <div class="col-7">
                                    <form method="POST" id="FrmSubirExcelR" enctype="multipart/form-data" class="instructivos_archivos_archivos">
                                        <input type="file" name="excelRe" id="excelRe">
                                        <input type="hidden" value="<%=cedula%>" name="cedulaexcelr" id="cedulaexcelr">
                                        <a href="#" onclick="subirExcelRe();" title="Subir excel"><i class="fas fa-file-upload"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">Subir Excel Reformas Saldos</div>
                                <div class="col-7">
                                    <form method="POST" id="FrmSubirExcelRS" enctype="multipart/form-data" class="instructivos_archivos_archivos">
                                        <input type="file" name="excelS" id="excelS">
                                        <input type="hidden" value="<%=cedula%>" name="cedulaexcels" id="cedulaexcels">
                                        <a href="#" onclick="subirExcelReS();" title="Subir excel"><i class="fas fa-file-upload"></i></a>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-5">Subir Excel Reformas Presupuestario</div>
                                <div class="col-7">
                                    <form method="POST" id="FrmSubirExcelRSP" enctype="multipart/form-data" class="instructivos_archivos_archivos">
                                        <input type="file" name="excelSP" id="excelSP">
                                        <input type="hidden" value="<%=cedula%>" name="cedulaexcelp" id="cedulaexcelp">
                                        <a href="#" onclick="subirExcelReSP();" title="Subir excel"><i class="fas fa-file-upload"></i></a>
                                    </form>
                                </div>
                            </div>
                            <%}%>
                        </div>
                        <%if (intIdTipoUsuario != 9) {%>
                        <p class="titulo mb-0"><u>REPORTES EVALUACIÓN</u></p>
                        <div class="container p-2 formulario pt-3">
                            <p class="titulo2 mb-0"><u>PDF</u></p>
                            <div class="row main-center">
                                <div class="col-12">
                                    <form method="POST" action="../controladorReportePDF" id="FrmDescargarEvaluacion" class="instructivos_archivos_icono row" target="_blank">
                                        <input type="hidden" value="reportePOAEvalAdmin" name="accion">
                                        <input type="hidden" value="<%=intIdTipoUsuario%>" name="tipusuarioeval">
                                        <div class="col-3">
                                            <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                                <option value="0">Todos</option>
                                                <option value="<%=IntIdAreaGestion%>"><%=strNombreAreaGestion%></option>
                                                <%
                                                    ResultSet rs = adAreaGestion.listaAreaGestionDeudas();
                                                    while (rs.next()) {
                                                %>
                                                <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="col-3">
                                            <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selecttipo" name="selecttipo">
                                                <option value="0">Todos</option>
                                                <%
                                                    ResultSet rs3 = adTecho.listaTipoProyecto();
                                                    while (rs3.next()) {
                                                %>
                                                <option value="<%= rs3.getString("tp_id")%>"><%=rs3.getString("tp_nombre")%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoa">
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoa">
                                        <input type="hidden" value="1" name="cuatrim">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoa">
                                        <input type="hidden" value="<%=strNombreAreaGestion%>" name="agnombrepoa">
                                        <a href="#" onclick="activarPOAE();"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%}%>
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