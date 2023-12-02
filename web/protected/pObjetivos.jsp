<%-- 
    Document   : pObjetivos
    Created on : 08/06/2022, 12:23:34
    Author     : EriPam
--%>

<%@page import="poa.acceso.adAreaGestion"%>
<%@page import="poa.acceso.adPerspectivaObj"%>
<%@page import="poa.acceso.adEjecucion"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="plantillas/sesiones.jsp" %> 
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <link rel="stylesheet" href="../css/dataTables.bootstrap4.min.css">
    <body>
        <div class="cross-center main-center d-none" id="loader" style="background: rgba(0,0,0,.1); z-index: 1000; position: absolute; width: 100%; min-height: 100vh;">
            <div class="spinner-border text-info" style="width: 5rem; height: 5rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <div class="modal fade" id="modificarVision" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmVision">
                            <input type="hidden" name="txtID" id="txtID">
                            <div><label>Visión</label><textarea class="form-control" id="txtVision" name="txtVision"></textarea></div>
                            <div><label>Misión</label><textarea class="form-control" id="txtMision" name="txtMision"></textarea></div>
                            <div><label>Fecha Inicio:</label><input type="text" class="form-control" id="fechaIVision" name="fechaIVision"></div>
                            <div><label>Fecha Fin:</label><input type="text" class="form-control" id="fechaFVision" name="fechaFVision"></div>
                            <div class="select"><label>Estado:</label>
                                <select class="form-control" id="slcVision" name="slcVision">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modificarVisionM">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalObjetivosEs" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelOEI"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmOEI">
                            <input type="hidden" name="txtOEI" id="txtOEI">
                            <div><label>Visión:</label>
                                <select class="form-control" id="slcVisionO" name="slcVisionO">
                                    <%                                        
                                        ResultSet rs = adPerspectivaObj.listaVision();
                                        while (rs.next()) {
                                    %>
                                    <option value="<%= rs.getString("vision_id")%>"><%=rs.getString("vision_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div><label>Tipo:</label>
                                <select class="form-control" id="slcTipo" name="slcTipo">
                                    <%
                                        ResultSet rs2 = adPerspectivaObj.listaTipoProyecto();
                                        while (rs2.next()) {
                                    %>
                                    <option value="<%= rs2.getString("tp_id")%>"><%=rs2.getString("tp_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div><label>Código</label><input type="text" class="form-control" id="txtCodigoO" name="txtCodigoO"></div>
                            <div><label>Nombre:</label><textarea class="form-control" id="txtNombreO" name="txtNombreO"></textarea></div>
                            <div class="selectOE"><label>Estado:</label>
                                <select class="form-control" id="slcOEIEstado" name="slcOEIEstado">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarOEI">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalObjetivosO" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelOO"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmOO">
                            <input type="hidden" name="txtOO" id="txtOO">
                            <div><label>Objetivo Estratégico:</label>
                                <select class="form-control" id="slcObjE" name="slcObjE">
                                    <%                                        
                                        ResultSet rs3 = adPerspectivaObj.listaPerspectiva();
                                        while (rs3.next()) {
                                    %>
                                    <option value="<%= rs3.getString("perspectiva_id")%>"><%=rs3.getString("perspectiva_codigo")%>-.<%=rs3.getString("perspectiva_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div><label>Código</label><input type="text" class="form-control" id="txtCodigoOO" name="txtCodigoOO"></div>
                            <div><label>Nombre:</label><textarea class="form-control" id="txtNombreOO" name="txtNombreOO"></textarea></div>
                            <div class="selectOO"><label>Estado:</label>
                                <select class="form-control" id="slcOOEstado" name="slcOOEstado">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarOO">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalPrograma" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelPro"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmPrograma">
                            <input type="hidden" name="txtPro" id="txtPro">
                            <div><label>Objetivo Operativo:</label>
                                <select class="form-control" id="slcObjOp" name="slcObjOp">
                                    <%                                        
                                        ResultSet rs4 = adPerspectivaObj.listaObjetivosO();
                                        while (rs4.next()) {
                                    %>
                                    <option value="<%= rs4.getString("objetivo_id")%>"><%=rs4.getString("objetivo_codigo")%>-.<%=rs4.getString("objetivo_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div><label>Código</label><input type="text" class="form-control" id="txtCodigoPro" name="txtCodigoPro"></div>
                            <div><label>Nombre:</label><textarea class="form-control" id="txtNombrePro" name="txtNombrePro"></textarea></div>
                            <div><label>Año:</label><input type="text" class="form-control" id="txtAnioA" name="txtAnioA"></div>
                            <div class="selectPro"><label>Estado:</label>
                                <select class="form-control" id="slcProEstado" name="slcProEstado">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarPro">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalPolitica" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelPol"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmPolitica">
                            <input type="hidden" name="txtPol" id="txtPol">
                            <div><label>Objetivo Operativo:</label>
                                <select class="form-control" id="slcPol" name="slcPol">
                                    <%                                        
                                        ResultSet rs7 = adPerspectivaObj.listaObjetivosO();
                                        while (rs7.next()) {
                                    %>
                                    <option value="<%= rs7.getString("objetivo_id")%>"><%=rs7.getString("objetivo_codigo")%>-.<%=rs7.getString("objetivo_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div><label>Nombre:</label><textarea class="form-control" id="txtNombrePol" name="txtNombrePol"></textarea></div>
                            <div class="selectPol"><label>Estado:</label>
                                <select class="form-control" id="slcPolEstado" name="slcPolEstado">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarPol">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalEstrategia" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelEst"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmEstrategia">
                            <input type="hidden" name="txtEst" id="txtEst">
                            <div><label>Objetivo Operativo:</label>
                                <select class="form-control selectpicker" id="slcEst" name="slcEst" data-live-search="true">
                                    <%                                        
                                        ResultSet rs8 = adPerspectivaObj.listaPoliticas();
                                        while (rs8.next()) {
                                    %>
                                    <option value="<%= rs8.getString("politicas_id")%>"><%=rs8.getString("politicas_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div><label>Nombre:</label><textarea class="form-control" id="txtNombreEst" name="txtNombreEst"></textarea></div>
                            <div class="selectEst"><label>Estado:</label>
                                <select class="form-control" id="slcEstEstado" name="slcEstEstado">
                                    <option value="1">Activo</option>
                                    <option value="0">Inactivo</option>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarEst">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalObjetivoUnidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabelOU"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmOU">
                            <div><label>Objetivo Estratégico:</label>
                                <input type="hidden" name="anioActivo" id="anioActivo" value="<%=intAnio%>">
                                <select class="form-control" id="slcObjUni" name="slcObjUni">
                                    <%                                        
                                        ResultSet rs5 = adPerspectivaObj.listaPerspectiva();
                                        while (rs5.next()) {
                                    %>
                                    <option value="<%= rs5.getString("perspectiva_id")%>"><%=rs5.getString("perspectiva_codigo")%>-.<%=rs5.getString("perspectiva_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                            <div><label>Unidad/Dependencia:</label>
                                <select class="form-control selectpicker" id="slAg" name="slAg" data-live-search="true" multiple data-selected-text-format="count > 6">
                                    <%                                        
                                        ResultSet rs6 = adAreaGestion.listaFaculAdmin(intAnio);
                                        while (rs6.next()) {
                                    %>
                                    <option value="<%= rs6.getString("ag_id")%>" title="<%=rs6.getString("ag_alias")%>"><%=rs6.getString("ag_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarOU">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalObjetivoUnidadEl" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">¿ESTÁ SEGURO QUE DESEA ELIMINAR?</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="frmOUEl">
                            <div><label>Objetivo Estratégico:</label>
                                <select class="form-control" id="slcObjUniE" name="slcObjUniE" readonly></select>
                            </div>
                            <div><label>Unidad/Dependencia:</label>
                                <select class="form-control" id="slAgE" name="slAgE" readonly></select>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarOUE">SI</button>
                        <button type="button" class="btn bton" data-dismiss="modal">NO</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %>
                <div class="content col-12 container-fluid">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <input type="hidden" name="agjustificativo" id="agjustificativo" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tagjustificativo" id="tagjustificativo" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <p class="titulo2 mt-2 mb-3">PLAN ESTRATÉGICO</p>
                        <ul class="nav nav-tabs ml-5 mt-3" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#componente1">Visión - Misión</a>
                            </li>    
                            <li class="nav-item" id="pestania1">
                                <a class="nav-link" data-toggle="tab" href="#componente2">Objetivos Estratégicos</a>
                            </li>
                            <li class="nav-item" id="pestania2">
                                <a class="nav-link" data-toggle="tab" href="#componente3">Objetivos Operativos</a>
                            </li>
                            <li class="nav-item" id="pestania3">
                                <a class="nav-link" data-toggle="tab" href="#componente4">Producto (2022) / Proyecto Institucional (2023)</a>
                            </li>
                            <li class="nav-item" id="pestania4">
                                <a class="nav-link" data-toggle="tab" href="#componente5">Objetivos - Áreas</a>
                            </li>
                            <li class="nav-item" id="pestania5">
                                <a class="nav-link" data-toggle="tab" href="#componente6">Políticas</a>
                            </li>
                            <li class="nav-item" id="pestania6">
                                <a class="nav-link" data-toggle="tab" href="#componente7">Estratégias</a>
                            </li>
                        </ul>
                        <div class="tab-content ml-5 mr-5 container-fluid">
                            <div id="componente1" class="container-fluid tab-pane active pestania"><br>
                                <div class="main-center" style="font-weight: bold;">VISIÓN - MISIÓN</div>
                                <div class="col-12 mt-3 main-start">
                                    <button class="btn bton btn_indicador_detalle" id="agregarvision">Agregar Visión/Misión</button>
                                </div>
                                <div class="tablaover">
                                    <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaActividad" id="listadoActividades1">
                                        <div class="table-azul encabezado p-0">
                                            <div class="p-0 estilo encabezado_7">VISIÓN</div>
                                            <div class="p-0 estilo encabezado_7">MISIÓN</div>
                                            <div class="estilo encabezado_5">FECHA INICIO</div>
                                            <div class="estilo encabezado_5">FECHA FIN</div>
                                            <div class="estilo encabezado_4">ESTADO</div>
                                            <div class="estilo encabezado_4">ACCIÓN</div>
                                        </div>
                                        <div id="listarVision" class="align-self-center encabezado p-0"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="componente2" class="container-fluid tab-pane pestania"><br>
                                <div class="main-center" style="font-weight: bold;">OBJETIVOS ESTRATÉGICOS</div>
                                <div class="col-12 mt-3 main-start">
                                    <button class="btn bton btn_indicador_detalle" id="agregarOEI">Agregar OEI</button>
                                </div>
                                <div class="tablaover">
                                    <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaActividad" id="listadoActividades1">
                                        <div class="table-azul encabezado p-0">
                                            <div class="estilo encabezado_5">CÓDIGO</div>
                                            <div class="p-0 estilo encabezado_7">VISIÓN</div>
                                            <div class="p-0 estilo encabezado_7">NOMBRE</div>
                                            <div class="estilo encabezado_5">TIPO</div>
                                            <div class="estilo encabezado_4">ESTADO</div>
                                            <div class="estilo encabezado_4">ACCIÓN</div>
                                        </div>
                                        <div id="listarObjetivosEs" class="align-self-center encabezado p-0"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="componente3" class="container-fluid tab-pane pestania"><br>
                                <div class="main-center" style="font-weight: bold;">OBJETIVOS OPERATIVOS</div>
                                <div class="col-12 mt-3 main-start">
                                    <button class="btn bton btn_indicador_detalle" id="agregarOO">Agregar OO</button>
                                </div>
                                <div class="tablaover">
                                    <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaActividad" id="listadoActividades1">
                                        <div class="table-azul encabezado p-0">
                                            <div class="estilo encabezado_2">CÓDIGO</div>
                                            <div class="p-0 estilo encabezado_2">OEI</div>
                                            <div class="p-0 estilo encabezado_10">NOMBRE</div>
                                            <div class="estilo encabezado_5">ESTADO</div>
                                            <div class="estilo encabezado_4">ACCIÓN</div>
                                        </div>
                                        <div id="listarObjetivosOo" class="align-self-center encabezado p-0"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="componente4" class="container-fluid tab-pane pestania"><br>
                                <div class="main-center" style="font-weight: bold;">PROYECTO INSTITUCIONAL</div>
                                <div class="col-12 mt-3 main-start">
                                    <button class="btn bton btn_indicador_detalle" id="agregarPP">Agregar proyecto</button>
                                </div>
                                <div class="tablaover">
                                    <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaActividad" id="listadoActividades1">
                                        <div class="table-azul encabezado p-0">
                                            <div class="estilo encabezado_2">CÓDIGO</div>
                                            <div class="p-0 estilo encabezado_2">OO</div>
                                            <div class="p-0 estilo encabezado_10">NOMBRE</div>
                                            <div class="estilo encabezado_5">ESTADO</div>
                                            <div class="estilo encabezado_4">ACCIÓN</div>
                                        </div>
                                        <div id="listarPrograma" class="align-self-center encabezado p-0"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="componente5" class="container-fluid tab-pane pestania"><br>
                                <div class="main-center" style="font-weight: bold;">OBJETIVOS - AREAS</div>
                                <div class="col-12 mt-3 main-start">
                                    <button class="btn bton btn_indicador_detalle" id="agregarOA">Agregar</button>
                                </div>
                                <div class="tablaover">
                                    <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaActividad" id="listadoActividades1">
                                        <div class="table-azul encabezado p-0">
                                            <div class="estilo encabezado_4">CÓDIGO</div>
                                            <div class="estilo encabezado_11">NOMBRE OBJETIVO</div>
                                            <div class="p-0 estilo encabezado_11">UNIDAD</div>
                                            <div class="estilo encabezado_4">ACCIÓN</div>
                                        </div>
                                        <div id="listarOA" class="align-self-center encabezado p-0"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="componente6" class="container-fluid tab-pane pestania"><br>
                                <div class="main-center" style="font-weight: bold;">POLÍTICAS</div>
                                <div class="col-12 mt-3 main-start">
                                    <button class="btn bton btn_indicador_detalle" id="agregarPPol">Agregar Política</button>
                                </div>
                                <div class="tablaover">
                                    <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaActividad" id="listadoActividades1">
                                        <div class="table-azul encabezado p-0">
                                            <div class="estilo encabezado_4">CÓDIGO OO</div>
                                            <div class="p-0 estilo encabezado_9">NOMBRE OBJETIVO OPERATIVO</div>
                                            <div class="p-0 estilo encabezado_10">POLÍTICA</div>
                                            <div class="estilo encabezado_5">ESTADO</div>
                                            <div class="estilo encabezado_4">ACCIÓN</div>
                                        </div>
                                        <div id="listarPolitica" class="align-self-center encabezado p-0"></div>
                                    </div>
                                </div>
                            </div>
                            <div id="componente7" class="container-fluid tab-pane pestania"><br>
                                <div class="main-center" style="font-weight: bold;">ESTRATÉGIAS / PROYECTOS</div>
                                <div class="col-12 mt-3 main-start">
                                    <button class="btn bton btn_indicador_detalle" id="agregarEst">Agregar</button>
                                </div>
                                <div class="tablaover">
                                    <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv listaActividad" id="listadoActividades1">
                                        <div class="table-azul encabezado p-0">
                                            <div class="p-0 estilo encabezado_11">POLÍTICA</div>
                                            <div class="p-0 estilo encabezado_11">NOMBRE</div>
                                            <div class="estilo encabezado_4">ESTADO</div>
                                            <div class="estilo encabezado_4">ACCIÓN</div>
                                        </div>
                                        <div id="listarEstrategia" class="align-self-center encabezado p-0"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsObjetivos.js" type="text/javascript"></script>
    </body>
</html>

