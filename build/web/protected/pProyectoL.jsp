<%-- 
    Document   : pFormulacion
    Created on : 07-oct-2019, 13:50:35
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adRequerimientosGenerales"%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@page import="poa.acceso.adProyecto"%>
<%@page import="poa.acceso.adTecho"%>
<%@page import="poa.acceso.adPerspectivaObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <link rel="stylesheet" href="../css/dataTables.bootstrap4.min.css">
    <body>
        <div class="modal fade" id="enviarModalVer" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ENVIAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <%if (intIdTipoUsuario == 26) {%>
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="aprobarRadios">
                                <label class="form-check-label" for="exampleRadios1">Validar</label>
                            </div>
                            <%} else {%>
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="aprobarRadios">
                                <label class="form-check-label" for="exampleRadios1">Enviar/Aprobar</label>
                            </div>
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="modificarRadios">
                                <label class="form-check-label" for="exampleRadios2">Enviar a Modificar</label>
                            </div>
                            <%}%>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Observación:</label>
                                <textarea class="form-control" id="observacionEnviar" placeholder="Debe ingresar observación solo si va a enviar a modificar el proyecto"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarEnviar">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- MODAL Fechas-->
        <div class="modal fade" id="devolverModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Fechas de Envio</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="card">
                            <div class="card-body"  id="inputEnviarReq" >
                            </div>
                        </div>
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
                    <!--<div class="content container-fluid m-3">
                       Tab panes 
                       <div class="container-fluid pestania"><br>-->
                    <div class="tab-content ml-5 mr-5 pestania m-0 p-0">  
                        <p class="titulo2">INFORMACIÓN PROYECTO</p>
                        <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation"  method="POST" action="/controladorPrueba" id="frmModProyecto" novalidate enctype="multipart/form-data" accept-charset="UTF-8">
                            <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs" name="idAgObEs">
                            <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg" name="tipoAg">
                            <input type="hidden" value="<%=intIdTipoUsuario%>" id="tipousuario" name="tipousuario">
                            <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                            <input type="hidden" value="<%=intAnio%>" id="stlAnio" name="stlAnio">
                            <input type="hidden" id="idActividadPres" name="idActividadPres">
                            <input type="hidden" id="idProy" name="idProy" value="<%=request.getParameter("id")%>">
                            <input type="hidden" id="ismulti" name="ismulti">
                            <p class="titulo2" id="tituloAg"></p>
                            <div class="form-row d-flex justify-content-center">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Objetivo Estratégico: </label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="objEstartF"> </div>
                                        <select class="col-10 col-xs-10 col-md-8  selectpicker p-0 m-0 d-none" id="objest" name="objest">
                                            <option selected="true" disabled>Seleccionar...</option>
                                            <%
                                                ResultSet rs = adPerspectivaObj.listaPerspectiva();
                                                while (rs.next()) {
                                            %>
                                            <option value="<%= rs.getString("perspectiva_id")%>"><%=rs.getString("perspectiva_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Objetivo Operativo: </label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="objObjF"></div>
                                        <select class="col-10 col-xs-10 col-md-8 selectpicker p-0 m-0 d-none" id="objobj" name="objobj">
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Proyecto PEDI: </label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="actividadPresF"></div>
                                        <select class="col-10 col-xs-10 col-md-8 selectpicker p-0 m-0 d-none" id="actpresup" name="actpresup">
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="proyectoNombreF"></div>
                                        <textarea class="form-control col-10 col-xs-10 col-md-8 d-none" id="nombre-mod" name="nombre-mod" required minlength="1"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Fin del proyecto:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="finProyectoF"></div>
                                        <textarea class="form-control col-10 col-xs-10 col-md-8 d-none" id="finp-mod" name="finp-mod" required minlength="1"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Propósito:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="propositoF"></div>
                                        <textarea class="form-control col-10 col-xs-10 col-md-8 d-none" id="prop-mod" name="prop-mod" required minlength="2"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="responsableF"></div>
                                        <input type="text" class="form-control col-10 col-xs-10 col-md-8 d-none" id="res-mod" name="res-mod" required maxlength="50" pattern="[A-ZÑÁÉÍÓÚ, A-ZÑÁÉÍÓÚ]{1,50}">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Fecha Inicio:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="fechaInicioF"></div>
                                        <input type="text" class="form-control col-10 col-xs-10 col-md-8 d-none" id="fini-mod" name="fini-mod" readonly="true" required>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Fecha Fin:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="fechaFinF"></div>
                                        <input type="text" class="form-control col-10 col-xs-10 col-md-8 d-none" id="ffin-mod" name="ffin-mod" readonly="true" required>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3" id="perfilInV">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Perfil:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="perfilF"></div>
                                        <input type="hidden" class="input" id="inpmodificarPerfil" name="inpmodificarPerfil">
                                        <input type="file" class="form-control-file col-10 col-xs-10 col-md-8 d-none" id="per-mod">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Integrantes:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="integrantesF"></div>
                                        <input type="text" class="form-control col-10 col-xs-10 col-md-8 d-none" id="int-mod" name="int-mod" required>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Monto:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="montoF"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3" id="multiSelec">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Multidisciplinario:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="multiCarreras"></div>
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 d-none" data-live-search="true" multiple data-selected-text-format="count > 6" id="mul-mod" name="mul-mod">
                                            <option value="0">Ninguno</option>
                                            <%
                                                ResultSet rs2 = adAreaGestion.listaAreaGestionMulti();
                                                while (rs2.next()) {
                                            %>
                                            <option title="<%=rs2.getString("ag_alias")%>" value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Estado:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="estadoF"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Fechas de Envíos</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="fechaestados" style="max-height: 70px; overflow-y: scroll;"></div>                                        
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 d-none mb-3" id="pluriAnualV">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Plurianual:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="plurianual"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 d-none mb-3" id="montoPluri">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Monto plurianual:</label>
                                        <div class="row col-10 col-xs-10 col-md-8 main-center" id="plurianual">
                                            <div class="col-12 col-xs-12 col-md-6" id="div2020"><label style="font-weight: bold">2020:</label> $200, 00</div>
                                            <div class="col-12 col-xs-12 col-md-6" id="div2021"><label style="font-weight: bold">2021:</label> $300,5</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%if (intAnio < 2022) {%>
                            <p class="titulo2">ARTICULACIÓN CON PROCESOS DE ASEGURAMIENTO DE LA CALIDAD</p>
                            <div class="form-row d-flex justify-content-center">
                                <%/*if (intAnio == 2022) {*/%>
                                <!--<div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3" style="border-right: 1px solid black">
                                    <div class="row main-center cross-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Proceso de aseguramiento de la calidad:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="proceEvalInstitucional"></div>
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 d-none" id="selectProceso" name="selectProceso">
                                            <option disabled>Seleccionar..</option>
                                <%/*
                                    ResultSet rs3 = adProyecto.listarProcesosAutoeval(4);
                                    while (rs3.next()) {*/
                                %>
                                <option selected="true" value="<%/*= rs3.getString("proceso_codigo")*/%>"><%/*=rs3.getString("proceso_nombre")*/%></option>
                                <%/*}*/%>
                            </select>
                        </div>
                    </div>
                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3" style="border-right: 1px solid black">
                        <div class="row main-center cross-center">
                            <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Actividades:</label>
                            <div class="col-10 col-xs-10 col-md-8 p-0 text-justify" id="evalinst">
                                <div class="row container main-justify cross-center">
                                    <div class="col-12" style="font-size: 0.9em;" id="modAccionInstitucional"></div>
                                    <div class="col-10 col-xs-10 col-md-9" id="modAccionInstitucionalMod" style="display:none">  
                                    </div>
                                </div>
                            </div>                                       
                        </div>
                    </div>-->
                                <%/*} else {*/%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" style="border-right: 1px solid black">
                                    <div class="row main-center cross-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Proceso de autoevaluación de Institucional:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="proceEvalInstitucional"></div>
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 d-none" id="selectProceso" name="selectProceso">
                                            <option disabled>Seleccionar..</option>
                                            <%
                                                ResultSet rs3 = adProyecto.listarProcesosAutoeval(1);
                                                while (rs3.next()) {
                                            %>
                                            <option selected="true" value="<%= rs3.getString("proceso_codigo")%>"><%=rs3.getString("proceso_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center cross-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Proceso de autoevaluación de Carreras</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="proceEvalCarrera"></div>
                                        <select class="col-10 col-xs-10 col-md-8 p-0 selectpicker d-none" id="selectProcesoCar" name="selectProcesoCar">
                                            <option disabled>Seleccionar..</option>
                                            <%
                                                ResultSet rs4 = adProyecto.listarProcesosAutoeval(3);
                                                while (rs4.next()) {
                                            %>
                                            <option selected="true" value="<%= rs4.getString("proceso_codigo")%>"><%=rs4.getString("proceso_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" style="border-right: 1px solid black">
                                    <div class="row main-center cross-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Acciones de Mejora Institucional:</label>
                                        <div class="col-10 col-xs-10 col-md-8 p-0 text-justify" id="evalinst">
                                            <div class="row container main-justify cross-center">
                                                <div class="col-12" style="font-size: 0.9em;" id="modAccionInstitucional"></div>
                                                <div class="col-10 col-xs-10 col-md-9" id="modAccionInstitucionalMod" style="display:none">  
                                                </div>
                                            </div>
                                        </div>                                       
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center cross-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Acciones de Mejora de Carrera:</label>
                                        <div class="col-10 col-xs-10 col-md-8 p-0 text-justify" id="evalcar">
                                            <div class="row container main-justify cross-center">
                                                <div class="col-12" style="font-size: 0.9em;" id="modAccionCarrera"></div>
                                                <div class="col-10 col-xs-10 col-md-9" id="modAccionCarreraMod" style="display:none">

                                                </div>

                                            </div>
                                        </div>                                          
                                    </div>
                                </div>
                                <%/*}*/%>
                            </div>  
                            <%}%>
                        </form>
                        <div class="form-row d-flex justify-content-center mt-3">                                
                            <button class="btn bton" id="btn_proyecto_enviar" data-toggle="modal" data-target="#enviarModal">ENVIAR</button>
                            <a href="pProyectoPlan.jsp?id=<%=request.getParameter("id")%>" class="btn bton mr-3" id="btn_proyecto_planificado">PLANIFICADO</a>
                            <form method="POST" action="../controladorReportePDF" target="_blank">
                                <input type="hidden" value="<%=strNombreAreaGestion%>" id="agnombrepdf" name="agnombrepdf"> 
                                <input type="hidden" value="<%=IntIdAreaGestion%>" id="agidpdf" name="agidpdf"> 
                                <input type="hidden" value="<%=request.getParameter("id")%>" id="proyidpdf" name="proyidpdf"> 
                                <input type="hidden" value="reporteProyectoCompleto" name="accion">
                                <input type="submit" class="btn bton" value="DESCARGAR" id="btn_proyecto_descargar">
                            </form>
                        </div>
                        <%if (intAnio >= 2022) {%>
                        <div class="row justify-content-center">
                            <div class="col-12 row justify-content-center mt-5 mb-2 align-items-center"><p class="col-9 titulo2 p-0 m-0">ARTICULACIÓN CON PLANES DE ASEGURAMIENTO DE LA CALIDAD Y DE MEJORAS INSTITUCIONALES</p></div>
                            <div class="row col-12"><button class="btn bton" id="btn_proyecto_articulacion">ARTICULAR PROYECTO <i class="fa fa-plus"></i></button></div>
                            <form class="col-10 container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none" novalidate accept-charset="UTF-8" id="formArticulacion">
                                <p class="titulo2">ARTICULACIÓN DE PROYECTO</p>
                                <input type="hidden" name="proyectoArt" id="proyectoArt" value="<%=request.getParameter("id")%>">
                                <div class="form-row">
                                    <div class="col-12 mb-3">
                                        <div class="row main-center">
                                            <label for="validationCustom01" class="col-2 justify-content-center justify-content-md-end cross-center">Planes:</label>
                                            <select class="selectpicker col-10 p-0" id="selectProceso" name="selectProceso">
                                                <option disabled>Seleccionar..</option>
                                                <option value="0" selected>Sin Articulación</option>
                                                <%
                                                    ResultSet rs3 = adProyecto.listarProcesosAutoevalActivos();
                                                    while (rs3.next()) {
                                                %>
                                                <option value="<%=rs3.getString("proceso_codigo")%>"><%=rs3.getString("proceso_nombre")%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-12 mb-3">
                                        <div class="row main-center">
                                            <label for="validationCustom01" class="col-2 justify-content-center justify-content-md-end cross-center">Actividades:</label>
                                            <select class="selectpicker col-10 p-0" id="selectAcciones" name="selectAcciones[]" data-live-search="true" multiple data-selected-text-format="count > 6">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <button class="btn bton" id="btnGuardarArti">GUARDAR</button>
                                <button class="btn bton" id="btncancelarArticu">CANCELAR</button>
                            </form>
                            <div class="tablaover">
                                <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaArticulacion">
                                    <div class="table-azul encabezado p-0">
                                        <div class="p-0 estilo encabezado_4">COD. PLAN</div>
                                        <div class="p-0 estilo encabezado_4">PLAN</div>
                                        <div class="estilo encabezado_4">NUM. ACT</div>
                                        <div class="estilo encabezado_2">META</div>
                                        <div class="estilo encabezado_7">ACTIVIDAD</div>
                                        <div class="estilo encabezado_5">RESPONSABLE</div>
                                        <div class="estilo encabezado_4">VALIDACIÓN</div>
                                    </div>
                                    <div id="listaAccionesM" class="align-self-center encabezado p-0"></div>
                                </div>
                            </div>
                        </div>
                        <%}%>
                        <div class="row mt-3">
                            <ul class="nav nav-tabs  ml-5" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active" data-toggle="tab" href="#componente1">Componente 1</a>
                                </li>
                                <li class="nav-item d-none" id="pestania2">
                                    <a class="nav-link" data-toggle="tab" href="#componente2">Componente 2</a>
                                </li>
                                <li class="nav-item d-none" id="pestania3">
                                    <a class="nav-link" data-toggle="tab" href="#componente3">Componente 3</a>
                                </li>
                                <li class="nav-item d-none" id="pestania4">
                                    <a class="nav-link" data-toggle="tab" href="#componente4">Componente 4</a>
                                </li>
                                <li class="nav-item d-none" id="pestania5">
                                    <a class="nav-link" data-toggle="tab" href="#componente5">Componente 5</a>
                                </li>
                            </ul>
                            <div class="tab-content ml-5 mr-5 container-fluid">
                                <div id="componente1" class="container-fluid tab-pane active pestania"><br>
                                    <div class="row mt-3 align-items-center">
                                        <input type="hidden" name="idAgComp" id="idAgComp1" value="<%=IntIdAreaGestion%>">
                                        <input type="hidden" name="idComponente1" id="idComponente1">
                                        <div class="col-11" id="inputComp1"><input class="form-control" type="text" id="componenteF1" name="componenteF1" placeholder="Ingresar componente"></div>
                                        <div class="col-1" id="btn_comp1"><i class="fas fa-save" style="cursor: pointer" id="ingresarComponente" data-id="1"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta1">
                                        <div class="col-11" id="inputMeta1">
                                            <input class="form-control" type="text" id="metaF1" name="metaF1" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta" id="idMeta1">
                                        </div>
                                        <div class="col-1" id="btn_meta1"><i class="fas fa-save" style="cursor: pointer" id="ingresarMeta" data-id="1"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador1"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton d-none btn_indicador_detalle" id="detalleIndicador1" data-id="1">DETALLES INDICADORES</button>
                                    </div>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none contenedorIndicador"  method="POST" id="contenedorIndicador1" data-id="1" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">INDICADOR</p>
                                        <input type="hidden" name="idIndicador1" id="idIndicador1">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreIndicador1" name="txtnombreIndicador1" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescipcionIndicador1" name="txtdescipcionIndicador1" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Tipo:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="1">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador1" name="tipoIndicador1" value="Cualitativo" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">Cualitativo</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="1">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador1" name="tipoIndicador1" value="Cuantitativo">
                                                        <label class="form-check-label" style="font-weight: normal;">Cuantitativo</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="formulaIndicador1">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Fórmula:</label>
                                                    <div class="col-12 col-xs-12 col-md-10 row">
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta ejecutada" name="txtindicadorejecutadovalor1" id="txtindicadorejecutadovalor1">=<input type="text" class="col-3" placeholder="% ejecutado" readonly>
                                                        </div> 
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta planificada" name="txtindicadorplanificadovalor1" id="txtindicadorplanificadovalor1">=<input type="text" class="col-3" placeholder="% planificado" name="txtindicadorplanificado1" id="txtindicadorplanificado1">
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador1" id="valorIndicador1" value="1" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">%</label>
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador1" id="valorIndicador1" value="2" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">#</label>
                                                        </div> 
                                                    </div>                                                                                                       
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonindicadorg1">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista indicadores-->
                                    <table class="container-fluid table mt-5 table-bordered table-hover table-striped table-responsive-md table-responsive-sm tablas d-none" id="listaIndicadorTabla1">
                                        <thead class="table-azul">
                                            <tr>
                                                <th colspan="4" class="p-0">INDICADORES</th>
                                            </tr>
                                            <tr>
                                                <th class="p-0">NOMBRE</th>
                                                <th class="p-0">DESCRIPCIÓN</th>
                                                <th class="p-0">TIPO</th>
                                                <th class="p-0">FORMULA</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listaIndicadores1"></tbody>
                                    </table>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorActividad d-none"  method="POST" action="../actividadReq?accion=IngresarActividad"  id="contenedorActividad1" data-id="1" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">ACTIVIDAD</p>
                                        <input type="hidden" name="idActividadMod1" id="idActividadMod1">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreActividad1" name="txtnombreActividad1" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpresponsable1" name="inpresponsable1">
                                                </div>
                                            </div>
                                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 mesactividad" data-id="1">
                                                <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Programación:</label>
                                                <div class="row col-10 text-justify" id="programacionactividad1"></div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row">
                                                    <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Porcentajes:</label>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="pori1">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajei1" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">I</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="porii1">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeii1" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">II</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="poriii1">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeiii1" required>
                                                        </div>
                                                        <label class="col-2 col-form-label p-0 align-self-center text-center">III</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita financiamiento:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="1">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento1" name="tipofinanciamiento1" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="1">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento1" name="tipofinanciamiento1" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="actividadBoton1">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades1">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_7">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_7">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_2">MONTO</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades1" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                    <!--Ingresar requerimientos-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=IngresarRequerimiento"  id="contenedorReqPac1" data-id="1" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idactividadreq1" id="idactividadreq1">
                                                    <input type="hidden" name="idrequerimiento1" id="idrequerimiento1">
                                                    <input type="hidden" name="reqverificacion1" id="reqverificacion1">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita Contratación Pública?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="1">
                                                        <input class="form-check-input" type="radio" id="tipoReq1" name="tipoReq1" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="1">
                                                        <input class="form-check-input" type="radio" id="tipoReq1" name="tipoReq1" value="2">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3" id="reqgeneral1">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Requerimientos precargados: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 reqgeneral" data-live-search="true" id="selectReqeG1" name="selectReqeG1" data-id="1">
                                                        <option value="0" selected>Ninguno</option>
                                                        <%
                                                            ResultSet rs1 = adRequerimientosGenerales.listaRequerimientoGeneral(intAnio);
                                                            while (rs1.next()) {
                                                        %>
                                                        <option value="<%= rs1.getString("rg_id")%>"><%=rs1.getString("rg_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreReq1" name="txtnombreReq1" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescripcionReq1" name="txtdescripcionReq1" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Cantidad Anual:</label>
                                                    <input type="number" id="intcantidad1" name="intcantidad1" class="col-12 col-xs-12 col-md-8">
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Costo Unitario:</label>
                                                    <input type="number" class="col-12 col-xs-12 col-md-8" id="intcosto1" name="intcosto1">
                                                </div>
                                            </div>
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento1" data-id="1"></div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita IVA?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="1">
                                                        <input class="form-check-input" type="radio" id="radioiva1" name="radioiva1" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="1">
                                                        <input class="form-check-input" type="radio" id="radioiva11" name="radioiva1" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selfinan1" name="selfinan1">
                                                        <%
                                                            ResultSet rs5 = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                                            while (rs5.next()) {
                                                        %>
                                                        <option value="<%= rs5.getString("financiamiento_id")%>"><%=rs5.getString("financiamiento_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="unidadM1">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Unidad de medida: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selunidad1" name="selunidad1">
                                                        <%
                                                            ResultSet rs6 = adRequerimientosGenerales.ListaUnidadSelect();
                                                            while (rs6.next()) {
                                                        %>
                                                        <option value="<%= rs6.getString("unidad_id")%>"><%=rs6.getString("unidad_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="tipoC1">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de compra: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="seltipoc1" name="seltipoc1">
                                                        <%
                                                            ResultSet rs7 = adRequerimientosGenerales.ListaTipoCompraSelect();
                                                            while (rs7.next()) {
                                                        %>
                                                        <option value="<%= rs7.getString("tc_id")%>"><%=rs7.getString("tc_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="cpc1">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">CPC:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpcpc1" name="inpcpc1">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog1">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista requerimientos-->
                                    <div class="tablaover mb-4">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv" id="listadoRequerimiento1">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">REQUERIMIENTOS</div>
                                                <div class="estilo encabezado_2">ACTIVIDADES</div>
                                                <div class="p-0 encabezado_3">
                                                    <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                    <div class="p-0 encabezado_2">
                                                        <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                        <div class="estilo encabezado_6">I</div>
                                                        <div class="estilo encabezado_6">II</div>
                                                        <div class="estilo encabezado_6">III</div>
                                                    </div>
                                                    <div class="estilo encabezado_4">CANTIDAD</div>
                                                    <%if (intAnio <= 2022) {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <%} else {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <div class="estilo encabezado_5">GRABA IVA?</div>
                                                    <%}%>
                                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                                </div>
                                            </div>
                                            <div id="listaRequerimiento1" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                </div>
                                <div id="componente2" class="container-fluid tab-pane fade pestania"><br>
                                    <div class="row mt-3 align-items-center">
                                        <input type="hidden" name="idAgComp" id="idAgComp2" value="<%=IntIdAreaGestion%>">
                                        <input type="hidden" name="idComponente1" id="idComponente2">
                                        <div class="col-11" id="inputComp2"><input class="form-control" type="text" id="componenteF2" name="componenteF2" placeholder="Ingresar componente"></div>
                                        <div class="col-1" id="btn_comp2"><i class="fas fa-save" style="cursor: pointer" id="ingresarComponente" data-id="2"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta2">
                                        <div class="col-11" id="inputMeta2">
                                            <input class="form-control" type="text" id="metaF2" name="metaF2" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta2" id="idMeta2">
                                        </div>
                                        <div class="col-1" id="btn_meta2"><i class="fas fa-save" style="cursor: pointer" id="ingresarMeta" data-id="2"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador2"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton d-none btn_indicador_detalle" id="detalleIndicador2" data-id="2">DETALLES INDICADORES</button>
                                        <button class="btn bton d-none btn_proyecto_agregar_indicador" id="botonIndicador2" data-id="2">+ INDICADOR</button>
                                        <button class="btn bton d-none btn_proyecto_actividad" id="botonActividad2" data-id="2">+ ACTIVIDAD</button>
                                    </div>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none contenedorIndicador"  method="POST" id="contenedorIndicador2" data-id="2" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">INDICADOR</p>
                                        <input type="hidden" name="idIndicador2" id="idIndicador2">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreIndicador2" name="txtnombreIndicador2" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescipcionIndicador2" name="txtdescipcionIndicador2" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Tipo:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="2">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador2" name="tipoIndicador2" value="Cualitativo" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">Cualitativo</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="2">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador2" name="tipoIndicador2" value="Cuantitativo">
                                                        <label class="form-check-label" style="font-weight: normal;">Cuantitativo</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="formulaIndicador2">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Fórmula:</label>
                                                    <div class="col-12 col-xs-12 col-md-10 row">
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta ejecutada" name="txtindicadorejecutadovalor2" id="txtindicadorejecutadovalor2">=<input type="text" class="col-3" placeholder="% ejecutado" readonly>
                                                        </div> 
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta planificada" name="txtindicadorplanificadovalor2" id="txtindicadorplanificadovalor2">=<input type="text" class="col-3" placeholder="% planificado" name="txtindicadorplanificado2" id="txtindicadorplanificado2">
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador2" id="valorIndicador2" value="1" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">%</label>
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador2" id="valorIndicador2" value="2" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">#</label>
                                                        </div> 
                                                    </div>                                                                                                       
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonindicadorg2">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista indicadores-->
                                    <table class="container-fluid table mt-5 table-bordered table-hover table-striped table-responsive-md table-responsive-sm tablas d-none" id="listaIndicadorTabla2">
                                        <thead class="table-azul">
                                            <tr>
                                                <th colspan="4" class="p-0">INDICADORES</th>
                                            </tr>
                                            <tr>
                                                <th class="p-0">NOMBRE</th>
                                                <th class="p-0">DESCRIPCIÓN</th>
                                                <th class="p-0">TIPO</th>
                                                <th class="p-0">FORMULA</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listaIndicadores2"></tbody>
                                    </table>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorActividad d-none"  method="POST" action="../actividadReq?accion=IngresarActividad"  id="contenedorActividad2" data-id="2" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">ACTIVIDAD</p>
                                        <input type="hidden" name="idActividadMod2" id="idActividadMod2">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreActividad2" name="txtnombreActividad2" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpresponsable2" name="inpresponsable2">
                                                </div>
                                            </div>
                                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 mesactividad" data-id="2">
                                                <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Programación:</label>
                                                <div class="row col-10 text-justify" id="programacionactividad2"></div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row">
                                                    <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Porcentajes:</label>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="pori2">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajei2" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">I</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="porii2">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeii2" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">II</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="poriii2">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeiii2" required>
                                                        </div>
                                                        <label class="col-2 col-form-label p-0 align-self-center text-center">III</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita financiamiento:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="2">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento2" name="tipofinanciamiento2" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="2">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento2" name="tipofinanciamiento2" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="actividadBoton2">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades2">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_7">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_7">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_2">MONTO</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades2" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                    <!--Ingresar requerimientos-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=IngresarRequerimiento"  id="contenedorReqPac2" data-id="2" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idactividadreq2" id="idactividadreq2">
                                                    <input type="hidden" name="idrequerimiento2" id="idrequerimiento2">
                                                    <input type="hidden" name="reqverificacion2" id="reqverificacion2">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita Contratación Pública?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="2">
                                                        <input class="form-check-input" type="radio" id="tipoReq2" name="tipoReq2" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="2">
                                                        <input class="form-check-input" type="radio" id="tipoReq2" name="tipoReq2" value="2">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3" id="reqgeneral2">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Requerimientos precargados: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 reqgeneral" data-live-search="true" id="selectReqeG2" name="selectReqeG2" data-id="2">
                                                        <option value="0" selected>Ninguno</option>
                                                        <%
                                                            ResultSet rs8 = adRequerimientosGenerales.listaRequerimientoGeneral(intAnio);
                                                            while (rs8.next()) {
                                                        %>
                                                        <option value="<%= rs8.getString("rg_id")%>"><%=rs8.getString("rg_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreReq2" name="txtnombreReq2" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescripcionReq2" name="txtdescripcionReq2" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Cantidad Anual:</label>
                                                    <input type="number" id="intcantidad2" name="intcantidad2" class="col-12 col-xs-12 col-md-8">
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Costo Unitario:</label>
                                                    <input type="number" class="col-12 col-xs-12 col-md-8" id="intcosto2" name="intcosto2">
                                                </div>
                                            </div>
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento2" data-id="2"></div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita IVA?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="2">
                                                        <input class="form-check-input" type="radio" id="radioiva2" name="radioiva2" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="2">
                                                        <input class="form-check-input" type="radio" id="radioiva2" name="radioiva2" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selfinan2" name="selfinan2">
                                                        <%
                                                            ResultSet rs9 = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                                            while (rs9.next()) {
                                                        %>
                                                        <option value="<%= rs9.getString("financiamiento_id")%>"><%=rs9.getString("financiamiento_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="unidadM2">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Unidad de medida: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selunidad2" name="selunidad2">
                                                        <%
                                                            ResultSet rs10 = adRequerimientosGenerales.ListaUnidadSelect();
                                                            while (rs10.next()) {
                                                        %>
                                                        <option value="<%= rs10.getString("unidad_id")%>"><%=rs10.getString("unidad_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="tipoC2">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de compra: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="seltipoc2" name="seltipoc2">
                                                        <%
                                                            ResultSet rst = adRequerimientosGenerales.ListaTipoCompraSelect();
                                                            while (rst.next()) {
                                                        %>
                                                        <option value="<%= rst.getString("tc_id")%>"><%=rst.getString("tc_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="cpc2">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">CPC:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpcpc2" name="inpcpc2">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog2">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista requerimientos-->
                                    <div class="tablaover mb-4">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv" id="listadoRequerimiento2">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">REQUERIMIENTOS</div>
                                                <div class="estilo encabezado_2">ACTIVIDADES</div>
                                                <div class="p-0 encabezado_3">
                                                    <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                    <div class="p-0 encabezado_2">
                                                        <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                        <div class="estilo encabezado_6">I</div>
                                                        <div class="estilo encabezado_6">II</div>
                                                        <div class="estilo encabezado_6">III</div>
                                                    </div>
                                                    <div class="estilo encabezado_4">CANTIDAD</div>
                                                    <%if (intAnio <= 2022) {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <%} else {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <div class="estilo encabezado_5">GRABA IVA?</div>
                                                    <%}%>
                                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                                </div>
                                            </div>
                                            <div id="listaRequerimiento2" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                </div>
                                <div id="componente3" class="container-fluid tab-pane fade pestania"><br>
                                    <div class="row mt-3 align-items-center">
                                        <input type="hidden" name="idAgComp" id="idAgComp3" value="<%=IntIdAreaGestion%>">
                                        <input type="hidden" name="idComponente3" id="idComponente3">
                                        <div class="col-11" id="inputComp3"><input class="form-control" type="text" id="componenteF3" name="componenteF3" placeholder="Ingresar componente"></div>
                                        <div class="col-1" id="btn_comp3"><i class="fas fa-save" style="cursor: pointer" id="ingresarComponente" data-id="3"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta3">
                                        <div class="col-11" id="inputMeta3">
                                            <input class="form-control" type="text" id="metaF3" name="metaF3" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta3" id="idMeta3">
                                        </div>
                                        <div class="col-1" id="btn_meta3"><i class="fas fa-save" style="cursor: pointer" id="ingresarMeta" data-id="3"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador3"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton d-none btn_indicador_detalle" data-id="3" id="detalleIndicador3">DETALLES INDICADORES</button>
                                        <button class="btn bton d-none btn_proyecto_agregar_indicador" id="botonIndicador3" data-id="3">+ INDICADOR</button>
                                        <button class="btn bton d-none btn_proyecto_actividad" id="botonActividad3" data-id="3">+ ACTIVIDAD</button>
                                    </div>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none contenedorIndicador"  method="POST" id="contenedorIndicador3" data-id="3" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">INDICADOR</p>
                                        <input type="hidden" name="idIndicador3" id="idIndicador3">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreIndicador3" name="txtnombreIndicador3" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescipcionIndicador3" name="txtdescipcionIndicador3" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Tipo:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="3">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador3" name="tipoIndicador3" value="Cualitativo" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">Cualitativo</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="3">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador3" name="tipoIndicador3" value="Cuantitativo">
                                                        <label class="form-check-label" style="font-weight: normal;">Cuantitativo</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="formulaIndicador3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Fórmula:</label>
                                                    <div class="col-12 col-xs-12 col-md-10 row">
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta ejecutada" name="txtindicadorejecutadovalor3" id="txtindicadorejecutadovalor3">=<input type="text" class="col-3" placeholder="% ejecutado" readonly>
                                                        </div> 
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta planificada" name="txtindicadorplanificadovalor3" id="txtindicadorplanificadovalor3">=<input type="text" class="col-3" placeholder="% planificado" name="txtindicadorplanificado3" id="txtindicadorplanificado3">
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador3" id="valorIndicador3" value="1" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">%</label>
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador3" id="valorIndicador3" value="2" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">#</label>
                                                        </div> 
                                                    </div>                                                                                                       
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonindicadorg3">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista indicadores-->
                                    <table class="container-fluid table mt-5 table-bordered table-hover table-striped table-responsive-md table-responsive-sm tablas d-none" id="listaIndicadorTabla3">
                                        <thead class="table-azul">
                                            <tr>
                                                <th colspan="4" class="p-0">INDICADORES</th>
                                            </tr>
                                            <tr>
                                                <th class="p-0">NOMBRE</th>
                                                <th class="p-0">DESCRIPCIÓN</th>
                                                <th class="p-0">TIPO</th>
                                                <th class="p-0">FORMULA</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listaIndicadores3"></tbody>
                                    </table>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorActividad d-none"  method="POST" action="../actividadReq?accion=IngresarActividad"  id="contenedorActividad3" data-id="3" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">ACTIVIDAD</p>
                                        <input type="hidden" name="idActividadMod3" id="idActividadMod3">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreActividad3" name="txtnombreActividad3" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpresponsable3" name="inpresponsable3">
                                                </div>
                                            </div>
                                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 mesactividad" data-id="3">
                                                <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Programación:</label>
                                                <div class="row col-10 text-justify" id="programacionactividad3"></div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row">
                                                    <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Porcentajes:</label>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="pori3">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajei3" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">I</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="porii3">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeii3" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">II</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="poriii3">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeiii3" required>
                                                        </div>
                                                        <label class="col-2 col-form-label p-0 align-self-center text-center">III</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita financiamiento:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="3">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento3" name="tipofinanciamiento3" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="3">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento3" name="tipofinanciamiento3" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="actividadBoton3">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades3">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_7">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_7">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_2">MONTO</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades3" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                    <!--Ingresar requerimientos-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=IngresarRequerimiento"  id="contenedorReqPac3" data-id="3" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idactividadreq3" id="idactividadreq3">
                                                    <input type="hidden" name="idrequerimiento3" id="idrequerimiento3">
                                                    <input type="hidden" name="reqverificacion3" id="reqverificacion3">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita Contratación Pública?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="3">
                                                        <input class="form-check-input" type="radio" id="tipoReq3" name="tipoReq3" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="3">
                                                        <input class="form-check-input" type="radio" id="tipoReq3" name="tipoReq3" value="2">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3" id="reqgeneral3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Requerimientos precargados: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 reqgeneral" data-live-search="true" id="selectReqeG3" name="selectReqeG3" data-id="3">
                                                        <option value="0" selected>Ninguno</option>
                                                        <%
                                                            ResultSet rsr4 = adRequerimientosGenerales.listaRequerimientoGeneral(intAnio);
                                                            while (rsr4.next()) {
                                                        %>
                                                        <option value="<%= rsr4.getString("rg_id")%>"><%=rsr4.getString("rg_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreReq3" name="txtnombreReq3" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescripcionReq3" name="txtdescripcionReq3" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Cantidad Anual:</label>
                                                    <input type="number" id="intcantidad3" name="intcantidad3" class="col-12 col-xs-12 col-md-8">
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Costo Unitario:</label>
                                                    <input type="number" class="col-12 col-xs-12 col-md-8" id="intcosto3" name="intcosto3">
                                                </div>
                                            </div>
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento3" data-id="3"></div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita IVA?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="3">
                                                        <input class="form-check-input" type="radio" id="radioiva3" name="radioiva3" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="3">
                                                        <input class="form-check-input" type="radio" id="radioiva3" name="radioiva3" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selfinan3" name="selfinan3">
                                                        <%
                                                            ResultSet rsrf = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                                            while (rsrf.next()) {
                                                        %>
                                                        <option value="<%= rsrf.getString("financiamiento_id")%>"><%=rsrf.getString("financiamiento_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="unidadM3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Unidad de medida: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selunidad3" name="selunidad3">
                                                        <%
                                                            ResultSet rsru = adRequerimientosGenerales.ListaUnidadSelect();
                                                            while (rsru.next()) {
                                                        %>
                                                        <option value="<%= rsru.getString("unidad_id")%>"><%=rsru.getString("unidad_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="tipoC3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de compra: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="seltipoc3" name="seltipoc3">
                                                        <%
                                                            ResultSet rsrtc = adRequerimientosGenerales.ListaTipoCompraSelect();
                                                            while (rsrtc.next()) {
                                                        %>
                                                        <option value="<%= rsrtc.getString("tc_id")%>"><%=rsrtc.getString("tc_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="cpc3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">CPC:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpcpc3" name="inpcpc3">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog3">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista requerimientos-->
                                    <div class="tablaover mb-4">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv" id="listadoRequerimiento3">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">REQUERIMIENTOS</div>
                                                <div class="estilo encabezado_2">ACTIVIDADES</div>
                                                <div class="p-0 encabezado_3">
                                                    <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                    <div class="p-0 encabezado_2">
                                                        <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                        <div class="estilo encabezado_6">I</div>
                                                        <div class="estilo encabezado_6">II</div>
                                                        <div class="estilo encabezado_6">III</div>
                                                    </div>
                                                    <div class="estilo encabezado_4">CANTIDAD</div>
                                                    <%if (intAnio <= 2022) {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <%} else {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <div class="estilo encabezado_5">GRABA IVA?</div>
                                                    <%}%>
                                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                                </div>
                                            </div>
                                            <div id="listaRequerimiento3" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                </div>
                                <div id="componente4" class="container-fluid tab-pane fade pestania"><br>
                                    <div class="row mt-3 align-items-center">
                                        <input type="hidden" name="idAgComp" id="idAgComp4" value="<%=IntIdAreaGestion%>">
                                        <input type="hidden" name="idComponente4" id="idComponente4">
                                        <div class="col-11" id="inputComp4"><input class="form-control" type="text" id="componenteF4" name="componenteF4" placeholder="Ingresar componente"></div>
                                        <div class="col-1" id="btn_comp4"><i class="fas fa-save" style="cursor: pointer" id="ingresarComponente" data-id="4"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta4">
                                        <div class="col-11" id="inputMeta4">
                                            <input class="form-control" type="text" id="metaF4" name="metaF4" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta4" id="idMeta4">
                                        </div>
                                        <div class="col-1" id="btn_meta4"><i class="fas fa-save" style="cursor: pointer" id="ingresarMeta" data-id="4"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador4"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton btn_indicador_detalle" data-id="4" id="detalleIndicador4">DETALLES INDICADORES</button>
                                        <button class="btn bton btn_proyecto_agregar_indicador" id="botonIndicador4" data-id="4">+ INDICADOR</button>
                                        <button class="btn bton d-none btn_proyecto_actividad" id="botonActividad4" data-id="4">+ ACTIVIDAD</button>
                                    </div>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none contenedorIndicador"  method="POST" id="contenedorIndicador4" data-id="4" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">INDICADOR</p>
                                        <input type="hidden" name="idIndicador4" id="idIndicador4">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreIndicador4" name="txtnombreIndicador4" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescipcionIndicador4" name="txtdescipcionIndicador4" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Tipo:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="4">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador4" name="tipoIndicador4" value="Cualitativo" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">Cualitativo</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="4">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador4" name="tipoIndicador4" value="Cuantitativo">
                                                        <label class="form-check-label" style="font-weight: normal;">Cuantitativo</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="formulaIndicador4">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Fórmula:</label>
                                                    <div class="col-12 col-xs-12 col-md-10 row">
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta ejecutada" name="txtindicadorejecutadovalor4" id="txtindicadorejecutadovalor4">=<input type="text" class="col-3" placeholder="% ejecutado" readonly>
                                                        </div> 
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta planificada" name="txtindicadorplanificadovalor4" id="txtindicadorplanificadovalor4">=<input type="text" class="col-3" placeholder="% planificado" name="txtindicadorplanificado4" id="txtindicadorplanificado4">
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador4" id="valorIndicador4" value="1" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">%</label>
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador4" id="valorIndicador4" value="2" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">#</label>
                                                        </div> 
                                                    </div>                                                                                                       
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonindicadorg4">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista indicadores-->
                                    <table class="container-fluid table mt-5 table-bordered table-hover table-striped table-responsive-md table-responsive-sm tablas d-none" id="listaIndicadorTabla4">
                                        <thead class="table-azul">
                                            <tr>
                                                <th colspan="4" class="p-0">INDICADORES</th>
                                            </tr>
                                            <tr>
                                                <th class="p-0">NOMBRE</th>
                                                <th class="p-0">DESCRIPCIÓN</th>
                                                <th class="p-0">TIPO</th>
                                                <th class="p-0">FORMULA</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listaIndicadores4"></tbody>
                                    </table>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorActividad d-none"  method="POST" action="../actividadReq?accion=IngresarActividad"  id="contenedorActividad4" data-id="4" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">ACTIVIDAD</p>
                                        <input type="hidden" name="idActividadMod4" id="idActividadMod4">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreActividad4" name="txtnombreActividad4" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpresponsable4" name="inpresponsable4">
                                                </div>
                                            </div>
                                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 mesactividad" data-id="4">
                                                <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Programación:</label>
                                                <div class="row col-10 text-justify" id="programacionactividad4"></div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row">
                                                    <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Porcentajes:</label>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="pori4">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajei4" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">I</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="porii4">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeii4" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">II</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="poriii4">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeiii4" required>
                                                        </div>
                                                        <label class="col-2 col-form-label p-0 align-self-center text-center">III</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita financiamiento:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="4">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento4" name="tipofinanciamiento4" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="4">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento4" name="tipofinanciamiento4" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="actividadBoton4">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades4">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_7">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_7">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_2">MONTO</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades4" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                    <!--Ingresar requerimientos-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=IngresarRequerimiento"  id="contenedorReqPac4" data-id="4" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idactividadreq4" id="idactividadreq4">
                                                    <input type="hidden" name="idrequerimiento4" id="idrequerimiento4">
                                                    <input type="hidden" name="reqverificacion4" id="reqverificacion4">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita Contratación Pública?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="4">
                                                        <input class="form-check-input" type="radio" id="tipoReq4" name="tipoReq4" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="4">
                                                        <input class="form-check-input" type="radio" id="tipoReq4" name="tipoReq4" value="2">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3" id="reqgeneral4">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Requerimientos precargados: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 reqgeneral" data-live-search="true" id="selectReqeG4" name="selectReqeG4" data-id="4">
                                                        <option value="0" selected>Ninguno</option>
                                                        <%
                                                            ResultSet rsrr = adRequerimientosGenerales.listaRequerimientoGeneral(intAnio);
                                                            while (rsrr.next()) {
                                                        %>
                                                        <option value="<%= rsrr.getString("rg_id")%>"><%=rsrr.getString("rg_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreReq4" name="txtnombreReq4" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescripcionReq4" name="txtdescripcionReq4" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Cantidad Anual:</label>
                                                    <input type="number" id="intcantidad4" name="intcantidad4" class="col-12 col-xs-12 col-md-8">
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Costo Unitario:</label>
                                                    <input type="number" class="col-12 col-xs-12 col-md-8" id="intcosto4" name="intcosto4">
                                                </div>
                                            </div>
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento4" data-id="4"></div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita IVA?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="4">
                                                        <input class="form-check-input" type="radio" id="radioiva4" name="radioiva4" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="4">
                                                        <input class="form-check-input" type="radio" id="radioiva4" name="radioiva4" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selfinan4" name="selfinan4">
                                                        <%
                                                            ResultSet rsf = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                                            while (rsf.next()) {
                                                        %>
                                                        <option value="<%= rsf.getString("financiamiento_id")%>"><%=rsf.getString("financiamiento_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="unidadM4">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Unidad de medida: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selunidad4" name="selunidad4">
                                                        <%
                                                            ResultSet rsu = adRequerimientosGenerales.ListaUnidadSelect();
                                                            while (rsu.next()) {
                                                        %>
                                                        <option value="<%= rsu.getString("unidad_id")%>"><%=rsu.getString("unidad_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="tipoC4">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de compra: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="seltipoc4" name="seltipoc4">
                                                        <%
                                                            ResultSet rst4 = adRequerimientosGenerales.ListaTipoCompraSelect();
                                                            while (rst4.next()) {
                                                        %>
                                                        <option value="<%= rst4.getString("tc_id")%>"><%=rst4.getString("tc_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="cpc4">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">CPC:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpcpc4" name="inpcpc4">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog4">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista requerimientos-->
                                    <div class="tablaover mb-4">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv" id="listadoRequerimiento4">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">REQUERIMIENTOS</div>
                                                <div class="estilo encabezado_2">ACTIVIDADES</div>
                                                <div class="p-0 encabezado_3">
                                                    <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                    <div class="p-0 encabezado_2">
                                                        <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                        <div class="estilo encabezado_6">I</div>
                                                        <div class="estilo encabezado_6">II</div>
                                                        <div class="estilo encabezado_6">III</div>
                                                    </div>
                                                    <div class="estilo encabezado_4">CANTIDAD</div>
                                                    <%if (intAnio <= 2022) {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <%} else {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <div class="estilo encabezado_5">GRABA IVA?</div>
                                                    <%}%>
                                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                                </div>
                                            </div>
                                            <div id="listaRequerimiento4" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                </div>
                                <div id="componente5" class="container-fluid tab-pane fade pestania"><br>
                                    <div class="row mt-3 align-items-center">
                                        <input type="hidden" name="idAgComp" id="idAgComp5" value="<%=IntIdAreaGestion%>">
                                        <input type="hidden" name="idComponente5" id="idComponente5">
                                        <div class="col-11" id="inputComp5"><input class="form-control" type="text" id="componenteF5" name="componenteF5" placeholder="Ingresar componente"></div>
                                        <div class="col-1" id="btn_comp5"><i class="fas fa-save" style="cursor: pointer" id="ingresarComponente" data-id="5"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta5">
                                        <div class="col-11" id="inputMeta5">
                                            <input class="form-control" type="text" id="metaF5" name="metaF5" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta5" id="idMeta5">
                                        </div>
                                        <div class="col-1" id="btn_meta5"><i class="fas fa-save" style="cursor: pointer" id="ingresarMeta" data-id="5"></i></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador5"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton btn_indicador_detalle" data-id="5" id="detalleIndicador5">DETALLES INDICADORES</button>
                                        <button class="btn bton btn_proyecto_agregar_indicador" id="botonIndicador5" data-id="5">+ INDICADOR</button>
                                        <button class="btn bton d-none btn_proyecto_actividad" id="botonActividad5" data-id="5">+ ACTIVIDAD</button>
                                    </div>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none contenedorIndicador"  method="POST" id="contenedorIndicador5" data-id="5" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">INDICADOR</p>
                                        <input type="hidden" name="idIndicador5" id="idIndicador5">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreIndicador5" name="txtnombreIndicador5" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescipcionIndicador5" name="txtdescipcionIndicador5" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Tipo:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="5">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador5" name="tipoIndicador5" value="Cualitativo" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">Cualitativo</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoIndicador" data-id="5">
                                                        <input class="form-check-input" type="radio" id="tipoIndicador5" name="tipoIndicador5" value="Cuantitativo">
                                                        <label class="form-check-label" style="font-weight: normal;">Cuantitativo</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="formulaIndicador5">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Fórmula:</label>
                                                    <div class="col-12 col-xs-12 col-md-10 row">
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta ejecutada" name="txtindicadorejecutadovalor5" id="txtindicadorejecutadovalor5">=<input type="text" class="col-3" placeholder="% ejecutado" readonly>
                                                        </div> 
                                                        <div class="col-12 col-xs-12 col-md-8">
                                                            <input type="text" class="col-5" placeholder="Meta planificada" name="txtindicadorplanificadovalor5" id="txtindicadorplanificadovalor5">=<input type="text" class="col-3" placeholder="% planificado" name="txtindicadorplanificado5" id="txtindicadorplanificado5">
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador5" id="valorIndicador5" value="1" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">%</label>
                                                        </div> 
                                                        <div class="form-check col-12 col-xs-6 col-md-1">
                                                            <input class="form-check-input" type="radio" name="valorIndicador5" id="valorIndicador5" value="2" checked>
                                                            <label class="form-check-label"  style="font-weight: normal;">#</label>
                                                        </div> 
                                                    </div>                                                                                                       
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonindicadorg5">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista indicadores-->
                                    <table class="container-fluid table mt-5 table-bordered table-hover table-striped table-responsive-md table-responsive-sm tablas d-none" id="listaIndicadorTabla5">
                                        <thead class="table-azul">
                                            <tr>
                                                <th colspan="4" class="p-0">INDICADORES</th>
                                            </tr>
                                            <tr>
                                                <th class="p-0">NOMBRE</th>
                                                <th class="p-0">DESCRIPCIÓN</th>
                                                <th class="p-0">TIPO</th>
                                                <th class="p-0">FORMULA</th>
                                            </tr>
                                        </thead>
                                        <tbody id="listaIndicadores5"></tbody>
                                    </table>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorActividad d-none"  method="POST" action="../actividadReq?accion=IngresarActividad"  id="contenedorActividad5" data-id="5" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">ACTIVIDAD</p>
                                        <input type="hidden" name="idActividadMod5" id="idActividadMod5">
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreActividad5" name="txtnombreActividad5" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpresponsable5" name="inpresponsable5">
                                                </div>
                                            </div>
                                            <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 mesactividad" data-id="5">
                                                <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Programación:</label>
                                                <div class="row col-10 text-justify" id="programacionactividad4"></div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row">
                                                    <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Porcentajes:</label>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="pori5">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajei5" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">I</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="porii5">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeii5" required>
                                                        </div>
                                                        <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">II</label>
                                                    </div>
                                                    <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="poriii5">
                                                        <div class="col-10">
                                                            <input class="form-control" type="number" min="0" max="100" id="porcentajeiii5" required>
                                                        </div>
                                                        <label class="col-2 col-form-label p-0 align-self-center text-center">III</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita financiamiento:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="5">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento5" name="tipofinanciamiento5" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoActividad" data-id="5">
                                                        <input class="form-check-input" type="radio" id="tipofinanciamiento5" name="tipofinanciamiento5" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="actividadBoton5">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades5">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_7">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_7">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_2">MONTO</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades5" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                    <!--Ingresar requerimientos-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=IngresarRequerimiento"  id="contenedorReqPac5" data-id="5" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Actividad Presupuestaria: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 selActPre" data-live-search="true" id="selcActPres5" name="selcActPres5" data-id="5"></select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idactividadreq5" id="idactividadreq5">
                                                    <input type="hidden" name="idrequerimiento5" id="idrequerimiento5">
                                                    <input type="hidden" name="reqverificacion5" id="reqverificacion5">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita Contratación Pública?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="5">
                                                        <input class="form-check-input" type="radio" id="tipoReq5" name="tipoReq5" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="5">
                                                        <input class="form-check-input" type="radio" id="tipoReq5" name="tipoReq5" value="2">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3" id="reqgeneral5">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Requerimientos precargados: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 reqgeneral" data-live-search="true" id="selectReqeG5" name="selectReqeG5" data-id="5">
                                                        <option value="0" selected>Ninguno</option>
                                                        <%
                                                            ResultSet rsrr5 = adRequerimientosGenerales.listaRequerimientoGeneral(intAnio);
                                                            while (rsrr5.next()) {
                                                        %>
                                                        <option value="<%= rsrr5.getString("rg_id")%>"><%=rsrr5.getString("rg_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtnombreReq5" name="txtnombreReq5" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Descripción:</label>
                                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtdescripcionReq5" name="txtdescripcionReq5" required minlength="1"></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Cantidad Anual:</label>
                                                    <input type="number" id="intcantidad5" name="intcantidad5" class="col-12 col-xs-12 col-md-8">
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Costo Unitario:</label>
                                                    <input type="number" class="col-12 col-xs-12 col-md-8" id="intcosto5" name="intcosto5">
                                                </div>
                                            </div>
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento5" data-id="5"></div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita IVA?</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="5">
                                                        <input class="form-check-input" type="radio" id="radioiva5" name="radioiva5" value="1" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad" data-id="5">
                                                        <input class="form-check-input" type="radio" id="radioiva5" name="radioiva5" value="0">
                                                        <label class="form-check-label" style="font-weight: normal;">NO</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selfinan5" name="selfinan5">
                                                        <%
                                                            ResultSet rsf5 = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                                            while (rsf5.next()) {
                                                        %>
                                                        <option value="<%= rsf5.getString("financiamiento_id")%>"><%=rsf5.getString("financiamiento_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="unidadM5">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Unidad de medida: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selunidad5" name="selunidad5">
                                                        <%
                                                            ResultSet rsu5 = adRequerimientosGenerales.ListaUnidadSelect();
                                                            while (rsu5.next()) {
                                                        %>
                                                        <option value="<%= rsu5.getString("unidad_id")%>"><%=rsu5.getString("unidad_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="tipoC5">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de compra: </label>
                                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="seltipoc5" name="seltipoc5">
                                                        <%
                                                            ResultSet rst5 = adRequerimientosGenerales.ListaTipoCompraSelect();
                                                            while (rst5.next()) {
                                                        %>
                                                        <option value="<%= rst5.getString("tc_id")%>"><%=rst5.getString("tc_nombre")%></option>
                                                        <%}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="cpc5">
                                                <div class="row main-center">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">CPC:</label>
                                                    <input type="text" class="col-12 col-xs-12 col-md-8" id="inpcpc5" name="inpcpc5">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog5">GUARDAR</button>
                                        <button class="btn bton">CANCELAR</button>
                                    </form>
                                    <!--Modificar año-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=ModificarAnioReq"  id="contenedorReqMod5" data-id="5" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <p id="nombrereqTit5"></p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idrequerimientoAn4" id="idrequerimientoAn5">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año de planificación:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="5">
                                                        <input class="form-check-input" type="radio" id="AnioReq5" name="AnioReq5" value="2021" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">2021</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="5">
                                                        <input class="form-check-input" type="radio" id="AnioReq5" name="AnioReq5" value="2022">
                                                        <label class="form-check-label" style="font-weight: normal;">2022</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientoAnio5">GUARDAR</button>
                                    </form>
                                    <!--Lista requerimientos-->
                                    <div class="tablaover mb-4">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv" id="listadoRequerimiento5">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">REQUERIMIENTOS</div>
                                                <div class="estilo encabezado_2">ACTIVIDADES</div>
                                                <div class="p-0 encabezado_3">
                                                    <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                    <div class="p-0 encabezado_2">
                                                        <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                        <div class="estilo encabezado_6">I</div>
                                                        <div class="estilo encabezado_6">II</div>
                                                        <div class="estilo encabezado_6">III</div>
                                                    </div>
                                                    <div class="estilo encabezado_4">CANTIDAD</div>
                                                    <%if (intAnio <= 2022) {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <%} else {%>
                                                    <div class="estilo encabezado_4">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
                                                    <div class="estilo encabezado_5">GRABA IVA?</div>
                                                    <%}%>
                                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                                </div>
                                            </div>
                                            <div id="listaRequerimiento5" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
                                                //alert('validado');
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
        <!--<script src="../javascript/jsPerspObjetivo.js" type="text/javascript"></script>-->
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsProyectoCompletoLRep.js"></script>
    </body>
</html>