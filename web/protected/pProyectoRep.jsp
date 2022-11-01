<%-- 
    Document   : pFormulacion
    Created on : 07-oct-2019, 13:50:35
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adEvaluacion"%>
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
                        <form id="frmenpro">
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="aprobarRadiosR">
                                <label class="form-check-label" for="exampleRadios1">Enviar/Aprobar</label>
                            </div>
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="modificarRadiosR">
                                <label class="form-check-label" for="exampleRadios2">Enviar a Modificar</label>
                            </div>
                            <!-- <div class="form-group">
                                 <label for="message-text" class="col-form-label">Observación:</label>
                                 <textarea class="form-control" id="observacionEnviar" placeholder="Debe ingresar observación solo si va a enviar a modificar el proyecto"></textarea>
                             </div>-->
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
        <div class="modal fade bd-example-modal-xl" id="generarJ" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR PROYECTO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form  class="container-fluid" id="frmAddProyecto" enctype="multipart/form-data" accept-charset="UTF-8">
                            <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyectoP" name="cedulaProyectoP">
                            <input type="hidden" id="idProyP" name="idProyP" value="<%=request.getParameter("id")%>">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Fecha Inicio:</label>
                                        <input type="text" class="form-control" id="fiproy" name="fiproy" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Fecha Fin:</label>
                                        <input type="text" class="form-control" name="ffproy" id="ffproy" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="repperfil">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Perfil reprogramado</label>
                                        <input type="file" class="form-control" name="perfrep" id="perfrep">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJust">GUARDAR</button>
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
                        <%
                            Integer cua = adEvaluacion.numeroCuatrimestre(intAnio);
                            Integer sum = adEvaluacion.sumaCuatrimestre(intAnio);
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
                        <p class="titulo2">INFORMACIÓN PROYECTO</p>
                        <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation"  method="POST" action="/controladorPrueba" id="frmModProyecto" novalidate enctype="multipart/form-data" accept-charset="UTF-8">
                            <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs" name="idAgObEs">
                            <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg" name="tipoAg">
                            <input type="hidden" value="<%=intIdTipoUsuario%>" id="tipousuario" name="tipousuario">
                            <input type="hidden" name="cuatrimestreeval" id="cuatrimestreeval" value="<%=cua%>">
                            <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                            <input type="hidden" id="fechacierre">
                            <input type="hidden" id="idActividadPres" name="idActividadPres">
                            <input type="hidden" id="idProy" name="idProy" value="<%=request.getParameter("id")%>">
                            <input type="hidden" id="ismulti" name="ismulti">
                            <p class="titulo2" id="tituloAg"></p>
                            <div class="form-row d-flex justify-content-center">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="proyectoNombreF"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Fin del proyecto:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="finProyectoF"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Propósito:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="propositoF"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Responsable:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="responsableF"></div>
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
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-5  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-4 justify-content-center justify-content-md-end cross-center">Monto:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="montoF"></div>
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
                                        <div class="col-10 col-xs-10 col-md-8 text-justify" id="fechaestados"></div>                                        
                                    </div>
                                </div>
                            </div>           
                        </form>
                        <div class="form-row d-flex justify-content-center mt-3">
                            <button class="btn bton" id="btn_proyecto_modificar" data-toggle="modal">MODIFICAR</button>
                            <button class="btn bton" id="btn_proyecto_enviar" data-toggle="modal" data-target="#enviarModal">ENVIAR</button>
                            <form method="POST" action="../controladorReportePDF" target="_blank">
                                <input type="hidden" value="<%=strNombreAreaGestion%>" id="agnombrepdf" name="agnombrepdf"> 
                                <input type="hidden" value="<%=IntIdAreaGestion%>" id="agidpdf" name="agidpdf"> 
                                <input type="hidden" value="<%=request.getParameter("id")%>" id="proyidpdf" name="proyidpdf"> 
                                <input type="hidden" value="reporteProyectoCompleto" name="accion">
                                <input type="submit" class="btn bton" value="DESCARGAR" id="btn_proyecto_descargar">
                            </form>
                        </div>
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
                                        <button class="btn bton d-none btn_proyecto_agregar_indicador" id="botonIndicador1" data-id="1">+ INDICADOR</button>
                                        <button class="btn bton d-none btn_proyecto_actividad" id="botonActividad1" data-id="1">+ ACTIVIDAD</button>
                                    </div>
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation d-none contenedorIndicador"  method="POST" id="contenedorIndicador1" data-id="1" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">INDICADOR</p>
                                        <input type="hidden" name="idIndicador1" id="idIndicador1">
                                        <input type="hidden" name="ididIndicador1" id="ididIndicador1">
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
                                        <button class="btn bton cancelarIndicador" data-id="1">CANCELAR</button>
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
                                        <input type="hidden" name="idActividadModid1" id="idActividadModid1">
                                        <input type="hidden" name="montoActividad1" id="montoActividad1">
                                        <input type="hidden" name="prioridadActividad1" id="prioridadActividad1">
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
                                        <button class="btn bton cancelarActividad" data-id="1">CANCELAR</button>
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
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento1" data-id="1"></div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog1">GUARDAR</button>
                                        <button class="btn bton cancelarRequerimiento" data-id="1">CANCELAR</button>
                                    </form>
                                    <!--Modificar año-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=ModificarAnioReq"  id="contenedorReqMod1" data-id="1" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <p id="nombrereqTit1"></p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idrequerimientoAn1" id="idrequerimientoAn1">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año de planificación:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="1">
                                                        <input class="form-check-input" type="radio" id="AnioReq1" name="AnioReq1" value="2020" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">2020</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="1">
                                                        <input class="form-check-input" type="radio" id="AnioReq1" name="AnioReq1" value="2021">
                                                        <label class="form-check-label" style="font-weight: normal;">2021</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientoAnio1">GUARDAR</button>
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
                                                    <div class="estilo encabezado_4">COSTO U</div>
                                                    <div class="estilo encabezado_5">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
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
                                        <input type="hidden" name="ididIndicador2" id="ididIndicador2">
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
                                        <button class="btn bton cancelarIndicador" data-id="2">CANCELAR</button>
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
                                        <input type="hidden" name="idActividadModid2" id="idActividadModid2">
                                        <input type="hidden" name="montoActividad2" id="montoActividad2">
                                        <input type="hidden" name="prioridadActividad2" id="prioridadActividad2">
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
                                        <button class="btn bton cancelarActividad" data-id="2">CANCELAR</button>
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
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento2" data-id="2"></div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog2">GUARDAR</button>
                                        <button class="btn bton cancelarRequerimiento" data-id="2">CANCELAR</button>
                                    </form>
                                    <!--Modificar año-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=ModificarAnioReq"  id="contenedorReqMod2" data-id="2" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <p id="nombrereqTit2"></p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idrequerimientoAn2" id="idrequerimientoAn2">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año de planificación:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="2">
                                                        <input class="form-check-input" type="radio" id="AnioReq2" name="AnioReq2" value="2020" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">2020</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="2">
                                                        <input class="form-check-input" type="radio" id="AnioReq2" name="AnioReq2" value="2021">
                                                        <label class="form-check-label" style="font-weight: normal;">2021</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientoAnio2">GUARDAR</button>
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
                                                    <div class="estilo encabezado_4">COSTO U</div>
                                                    <div class="estilo encabezado_5">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
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
                                        <input type="hidden" name="ididIndicador3" id="ididIndicador3">
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
                                        <button class="btn bton cancelarIndicador" data-id="3">CANCELAR</button>
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
                                        <input type="hidden" name="idActividadModid3" id="idActividadModid3">
                                        <input type="hidden" name="montoActividad3" id="montoActividad3">
                                        <input type="hidden" name="prioridadActividad3" id="prioridadActividad3">
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
                                        <button class="btn bton cancelarActividad" data-id="3">CANCELAR</button>
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
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento3" data-id="3"></div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog3">GUARDAR</button>
                                        <button class="btn bton cancelarRequerimiento" data-id="3">CANCELAR</button>
                                    </form>
                                    <!--Modificar año-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=ModificarAnioReq"  id="contenedorReqMod3" data-id="3" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <p id="nombrereqTit3"></p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idrequerimientoAn1" id="idrequerimientoAn3">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año de planificación:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="3">
                                                        <input class="form-check-input" type="radio" id="AnioReq3" name="AnioReq3" value="2020" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">2020</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="3">
                                                        <input class="form-check-input" type="radio" id="AnioReq3" name="AnioReq3" value="2021">
                                                        <label class="form-check-label" style="font-weight: normal;">2021</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientoAnio3">GUARDAR</button>
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
                                                    <div class="estilo encabezado_4">COSTO U</div>
                                                    <div class="estilo encabezado_5">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
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
                                        <input type="hidden" name="ididIndicador4" id="ididIndicador4">
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
                                        <button class="btn bton cancelarIndicador" data-id="4">CANCELAR</button>
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
                                        <input type="hidden" name="idActividadModid4" id="idActividadModid4">
                                        <input type="hidden" name="montoActividad4" id="montoActividad4">
                                        <input type="hidden" name="prioridadActividad4" id="prioridadActividad4">
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
                                        <button class="btn bton cancelarActividad" data-id="4">CANCELAR</button>
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
                                            <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento4" data-id="4"></div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientog4">GUARDAR</button>
                                        <button class="btn bton cancelarRequerimiento" data-id="4">CANCELAR</button>
                                    </form>
                                    <!--Modificar año-->
                                    <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation contenedorRequerimientos d-none"  method="POST" action="../actividadReq?accion=ModificarAnioReq"  id="contenedorReqMod4" data-id="4" novalidate accept-charset="UTF-8">
                                        <p class="titulo2">REQUERIMIENTO</p>
                                        <p id="nombrereqTit4"></p>
                                        <div class="form-row">
                                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                                <div class="row main-center">
                                                    <input type="hidden" name="idrequerimientoAn4" id="idrequerimientoAn4">
                                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año de planificación:</label>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="4">
                                                        <input class="form-check-input" type="radio" id="AnioReq4" name="AnioReq4" value="2020" checked>
                                                        <label class="form-check-label"  style="font-weight: normal;">2020</label>
                                                    </div>
                                                    <div class="form-check col-12 col-xs-12 col-md-2 tipoRequerimiento" data-id="4">
                                                        <input class="form-check-input" type="radio" id="AnioReq4" name="AnioReq4" value="2021">
                                                        <label class="form-check-label" style="font-weight: normal;">2021</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn bton" type="submit" id="botonrequerimientoAnio4">GUARDAR</button>
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
                                                    <div class="estilo encabezado_4">COSTO U</div>
                                                    <div class="estilo encabezado_5">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                                </div>
                                            </div>
                                            <div id="listaRequerimiento4" class="align-self-center encabezado p-0"></div>
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
        <script src="../javascript/jsProyectoCompletoRep.js"></script>
        <script src="../javascript/jsProyectoDetalle.js"></script>
    </body>
</html>