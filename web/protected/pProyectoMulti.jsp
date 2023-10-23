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
    <body onload="tiempos('fechacierre')">
        <div class="modal fade" id="eliminarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ELIMINAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="eliminarModalBton">ELIMINAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="enviarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ENVIAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="enviarModalBton">ENVIAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
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
                            <input type="hidden" id="selectoranio" name="selectoranio" value="<%=intAnio%>">
                            <input type="hidden" id="fechacierre">
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
                                        <%if (intAnio <= 2022) {%>
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Proceso: </label>
                                        <%} else {%>
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Proyecto PEDI: </label>
                                        <%}%>
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
                                        <input type="file" class="form-control-file col-10 col-xs-10 col-md-8 d-none" id="permod" name="permod">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Integrantes:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="integrantesF"></div>
                                        <div class="col-10 col-xs-10 col-md-8" >
                                            <div class="row container cross-center d-none" id="inpintegrantes"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Monto:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="montoF"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3 d-none" id="coejeSelec">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Coejecución:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="multiCarrerasA"></div>
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0 d-none" data-live-search="true" multiple data-selected-text-format="count > 6" id="selectAgC" name="selectAgC">
                                            <option value="0">Ninguno</option>
                                            <%
                                                ResultSet rsd = adAreaGestion.listaAreaGestionUnidadesAdminTotas();
                                                while (rsd.next()) {
                                            %>
                                            <option title="<%=rsd.getString("ag_alias")%>" value="<%=rsd.getString("ag_id")%>"><%=rsd.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3" id="multiSelec">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Multidisciplinario:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="multiCarreras"></div>
                                        <!--<select class="selectpicker col-10 col-xs-10 col-md-8 d-none" data-live-search="true" multiple data-selected-text-format="count > 6" id="mul-mod" name="mul-mod">
                                            <option value="0">Ninguno</option>
                                        <%
                                            ResultSet rs2 = adAreaGestion.listaAreaGestionMulti();
                                            while (rs2.next()) {
                                        %>
                                        <option title="<%=rs2.getString("ag_alias")%>" value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                        <%}%>
                                    </select>-->
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
                                        <div class="row col-10 col-xs-10 col-md-8 main-center" id="plurianualdiv">
                                            <div class="col-12 col-xs-12 col-md-6" id="div2020"></div>
                                            <div class="col-12 col-xs-12 col-md-6" id="div2021"></div>
                                        </div>
                                        <!-- <div class="row col-10 col-xs-10 col-md-8 main-center d-none" id="plurianualtext">
                                              <input type="text" class="col-12 col-xs-12 col-md-6" placeholder="2020" id="txt2020">
                                              <input type="text" class="col-12 col-xs-12 col-md-6" placeholder="2021" id="txt2021">
                                              <i class="fa fa-save m-2" title="Guardar" id="gPlurianual"></i>
                                          </div>-->
                                    </div>
                                </div>
                            </div>
                            <%if (intAnio < 2022) {%>
                            <p class="titulo2">ARTICULACIÓN CON PROCESOS DE ASEGURAMIENTO DE LA CALIDAD</p>
                            <div class="form-row d-flex justify-content-center">
                                <%/*if (intAnio == 2022) {*/%>
                                <!--<div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3">
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
                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3">
                        <div class="row main-center cross-center">
                            <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Actividades:</label>
                            <div class="col-10 col-xs-10 col-md-8" id="evalinst">
                                <div class="row container main-justify cross-center">
                                    <div class="col-10 col-xs-10 col-md-9" id="modAccionInstitucional"></div>
                                    <div class="col-10 col-xs-10 col-md-9" id="modAccionInstitucionalMod" style="display:none">  
                                    </div>
                                </div>
                            </div>                                       
                        </div>
                    </div>-->
                                <%/*} else {*/%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
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
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
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
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center cross-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Acciones de Mejora Institucional:</label>
                                        <div class="col-10 col-xs-10 col-md-8" id="evalinst">
                                            <div class="row container main-justify cross-center">
                                                <div class="col-10 col-xs-10 col-md-9" id="modAccionInstitucional"></div>
                                                <div class="col-10 col-xs-10 col-md-9" id="modAccionInstitucionalMod" style="display:none">  
                                                </div>
                                            </div>
                                        </div>                                       
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center cross-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Acciones de Mejora de Carrera:</label>
                                        <div class="col-10 col-xs-10 col-md-8" id="evalcar">
                                            <div class="row container main-justify cross-center">
                                                <div class="col-10 col-xs-10 col-md-9" id="modAccionCarrera"></div>
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
                            <button class="btn bton" id="btn-modificar">MODIFICAR</button>
                            <button class="btn bton d-none" type="submit" id="btn-guardar">GUARDAR</button>
                            <a href="pProyectoPlan.jsp?id=<%=request.getParameter("id")%>" class="btn bton mr-3" id="btn_proyecto_planificado">PLANIFICADO</a>
                            <button class="btn bton" id="btn_proyecto_enviar" data-toggle="modal" data-target="#enviarModal">ENVIAR</button>
                            <button class="btn bton" id="btn_proyecto_eliminar" data-toggle="modal" data-target="#eliminarModal">ELIMINAR</button>
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
                                        <div class="estilo encabezado_4">NUM. ACT / NUM. IND</div>
                                        <div class="estilo encabezado_7">META</div>
                                        <div class="estilo encabezado_7">ACTIVIDAD / INDICADOR</div>
                                        <div class="estilo encabezado_5">RESPONSABLE</div>
                                        <div class="estilo encabezado_8">X</div>
                                    </div>
                                    <div id="listaAccionesM" class="align-self-center encabezado p-0"></div>
                                </div>
                            </div>
                        </div>
                        <%}%>
                        <div class="row mt-3">
                            <ul class="nav nav-tabs  ml-5" role="tablist" id="cuerpoMenu"></ul>
                            <div class="tab-content ml-5 mr-5 container-fluid" id="cuerpoDetalle">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <!--<script src="../javascript/jsPerspObjetivo.js" type="text/javascript"></script>-->
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsProyectoCompletoMul.js"></script>
        <script src="../javascript/jsProyectoDetalle.js"></script>
    </body>
</html>