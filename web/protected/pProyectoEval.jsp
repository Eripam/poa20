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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <body onload="tiempos('fechacierre')">
        <div class="modal fade bd-example-modal-xl" id="archivosS" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">AGREGAR ARCHIVOS</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <form class="row" id="FrmArchivosEvidencias" >
                                <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                                <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                                <input type="hidden" name="actividadarchivo" id="actividadarchivo">
                                <input type="hidden" name="cuatrimestreevalact" id="cuatrimestreevalact">
                                <input type="hidden" name="componentearchivo" id="componentearchivo">
                                <input type="hidden" name="pestania" id="pestania">
                                <input type="hidden" name="cuateval" id="cuateval">
                                <input type="hidden" name="estadoeval" id="estadoeval">
                                <input type="hidden" name="idevalmod" id="idevalmod">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Descripción:</label>
                                        <textarea type="text" class="form-control" id="descarchivo" name="descarchivo"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Archivo:</label>
                                        <input type="file" class="form-control" name="archivoactividad" id="archivoactividad">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">NOTA: Subir archivos pdf o comprimidos de máximo 5 mb</label>
                                    </div>
                                </div>
                            </form>                            
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarArc">GUARDAR</button>
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
                        <div id="columnchart_values"></div>
                        <div id="ejecucioneval" style="font-weight: bold;"></div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="eliminarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ELIMINAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">¿Esta seguro que desea eliminar la evidencia?</div>
                    <div id="inputElim"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="eliminarModalBton">ELIMINAR</button>
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
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="aprobarRadios">
                                <label class="form-check-label" for="exampleRadios1">Enviar/Aprobar</label>
                            </div>
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="modificarRadios">
                                <label class="form-check-label" for="exampleRadios2">Enviar a Modificar</label>
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
        <div class="modal fade bd-example-modal-xl" id="generarJ" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">REQUERIMIENTOS</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="table-hover table-striped" style="width: 100%">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">REQUERIMIENTO</th>
                                        <th class="text-center align-middle">REFORMAS</th>
                                        <th class="text-center align-middle">JUSTIFICATIVOS/SOLICITUDES</th>
                                        <th class="text-center align-middle">CERTIFICACIONES</th>
                                        <th class="text-center align-middle">DEVENGADOS MONTO</th>
                                    </tr>
                                </thead>
                                <tbody id="listaProyectos"></tbody>
                            </table>
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
                        <%if (cua == 1) {%>
                        <p class="titulo p-0 m-0" id="cuatrititulo">EVALUACIÓN I CUATRIMESTRE</p>
                        <%} else if (cua == 2) {%>
                        <p class="titulo p-0 m-0" id="cuatrititulo">EVALUACIÓN II CUATRIMESTRE</p>
                        <%} else {%>
                        <p class="titulo p-0 m-0" id="cuatrititulo">EVALUACIÓN III CUATRIMESTRE</p>
                        <%}%>
                        <div class="col-12 p-0 m-0" id="colorelip"><i class="fas fa-ellipsis-h fa-2x"></i></div>
                        <form class="container-fluid p-3 formulario pt-3 mt-4 needs-validation"  method="POST" action="/controladorPrueba" id="frmModProyecto" novalidate enctype="multipart/form-data" accept-charset="UTF-8">
                            <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs" name="idAgObEs">
                            <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg" name="tipoAg">
                            <input type="hidden" value="<%=intIdTipoUsuario%>" id="tipousuario" name="tipousuario">
                            <input type="hidden" name="cuatrimestreeval" id="cuatrimestreeval" value="<%=cua%>">
                            <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                            <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                            <input type="hidden" id="fechacierre">
                            <input type="hidden" id="idActividadPres" name="idActividadPres">
                            <input type="hidden" id="idProy" name="idProy" value="<%=request.getParameter("id")%>">
                            <input type="hidden" id="ismulti" name="ismulti">
                            <p class="titulo2" id="tituloAg"></p>
                            <div class="form-row d-flex justify-content-center">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                        <div class="col-10 col-xs-10 col-md-8 text-justify input-vista" id="proyectoNombreF"></div>
                                        <textarea class="form-control col-10 col-xs-10 col-md-10 d-none" id="nombre-mod" name="nombre-mod" required minlength="1"></textarea>
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
                        </form>
                        <div class="form-row d-flex justify-content-center mt-3" id="btnEvalRep">
                            <button class="btn bton" id="btn_proyecto_enviar" data-toggle="modal" data-target="#enviarModal">ENVIAR</button>
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
                                <li class="nav-item d-none" id="pestania5">
                                    <a class="nav-link" data-toggle="tab" href="#componente5">Componente 5</a>
                                </li>
                            </ul>
                            <div class="tab-content ml-5 mr-5 container-fluid">
                                <div id="componente1" class="container-fluid tab-pane active pestania"><br>
                                    <div class="row mt-3 align-items-center">
                                        <input type="hidden" name="idAgComp" id="idAgComp1" value="<%=IntIdAreaGestion%>">
                                        <input type="hidden" name="idComponente1" id="idComponente1">
                                        <div class="col-10" id="inputComp1"></div>
                                        <div class="col-2" id="btn_comp1" style="font-weight: bold;">Eficacia</div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta1">
                                        <div class="col-10" id="inputMeta1">
                                            <input class="form-control" type="text" id="metaF1" name="metaF1" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta" id="idMeta1">
                                        </div>
                                        <div class="col-2" id="btn_meta1"></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador1"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton d-none btn_indicador_detalle" id="detalleIndicador1" data-id="1">DETALLES INDICADORES</button>
                                    </div>
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
                                    <div class="row mt-3 p-3 align-items-center d-none" id="contenedorlogros1" style="background: rgba(19,51,81,0.1)">
                                        <div class="col-12 titulopes text-justify">Logros Alcanzados</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los logros alcanzados" maxlength="500" name="txtLogros1" id="txtLogros1"></textarea></div>
                                        <div class="col-12 m-1 titulopes text-justify">Nudos Críticos</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los nudos críticos" maxlength="500" name="txtNudos1" id="txtNudos1"></textarea></div>
                                        <div class="col-12 align-content-center mt-1" id="botonLog1"><button class="btn bton" id="guardarComp1" data-id="1">GUARDAR</button></div>
                                    </div>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades1">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_2">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_5">MONTO</div>
                                                <div class="estilo encabezado_5">% EVALUACIÓN</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades1" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
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
                                                    <div class="estilo encabezado_4">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
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
                                        <input type="hidden" name="idComponente2" id="idComponente2">
                                        <div class="col-10" id="inputComp2"></div>
                                        <div class="col-2" id="btn_comp2" style="font-weight: bold;">Eficacia</div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta2">
                                        <div class="col-10" id="inputMeta2">
                                            <input class="form-control" type="text" id="metaF2" name="metaF2" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta2" id="idMeta2">
                                        </div>
                                        <div class="col-2" id="btn_meta2"></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador2"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton d-none btn_indicador_detalle" id="detalleIndicador2" data-id="2">DETALLES INDICADORES</button>
                                    </div>
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
                                    <div class="row mt-3 p-3 align-items-center d-none" id="contenedorlogros2" style="background: rgba(19,51,81,0.1)">
                                        <div class="col-12 titulopes text-justify">Logros Alcanzados</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los logros alcanzados" maxlength="500" name="txtLogros2" id="txtLogros2"></textarea></div>
                                        <div class="col-12 m-1 titulopes text-justify">Nudos Críticos</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los nudos críticos" maxlength="500" name="txtNudos2" id="txtNudos2"></textarea></div>
                                        <div class="col-12 align-content-center mt-1" id="botonLog2"><button class="btn bton" id="guardarComp2" data-id="2">GUARDAR</button></div>
                                    </div>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades2">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_2">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_5">MONTO</div>
                                                <div class="estilo encabezado_5">% EVALUACIÓN</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades2" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
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
                                                    <div class="estilo encabezado_4">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
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
                                        <div class="col-10" id="inputComp3"></div>
                                        <div class="col-2" id="btn_comp3" style="font-weight: bold;">Eficacia</div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta3">
                                        <div class="col-10" id="inputMeta3">
                                            <input class="form-control" type="text" id="metaF3" name="metaF3" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta3" id="idMeta3">
                                        </div>
                                        <div class="col-2" id="btn_meta3"></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador3"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton d-none btn_indicador_detalle" data-id="3" id="detalleIndicador3">DETALLES INDICADORES</button>
                                    </div>
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
                                    <div class="row mt-3 p-3 align-items-center d-none" id="contenedorlogros3" style="background: rgba(19,51,81,0.1)">
                                        <div class="col-12 titulopes text-justify">Logros Alcanzados</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los logros alcanzados" maxlength="500" name="txtLogros3" id="txtLogros3"></textarea></div>
                                        <div class="col-12 m-1 titulopes text-justify">Nudos Críticos</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los nudos críticos" maxlength="500" name="txtNudos3" id="txtNudos3"></textarea></div>
                                        <div class="col-12 align-content-center mt-1" id="botonLog3"><button class="btn bton" id="guardarComp3" data-id="3">GUARDAR</button></div>
                                    </div>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades3">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_2">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_5">MONTO</div>
                                                <div class="estilo encabezado_5">% EVALUACIÓN</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades3" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
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
                                        <div class="col-10" id="inputComp4"></div>
                                        <div class="col-2" id="btn_comp4" style="font-weight: bold;">Eficacia</div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta4">
                                        <div class="col-10" id="inputMeta4">
                                            <input class="form-control" type="text" id="metaF4" name="metaF4" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta4" id="idMeta4">
                                        </div>
                                        <div class="col-2" id="btn_meta4"></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador4"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton btn_indicador_detalle" data-id="4" id="detalleIndicador4">DETALLES INDICADORES</button>
                                    </div>
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
                                    <div class="row mt-3 p-3 align-items-center d-none" id="contenedorlogros4" style="background: rgba(19,51,81,0.1)">
                                        <div class="col-12 titulopes text-justify">Logros Alcanzados</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los logros alcanzados" maxlength="500" name="txtLogros4" id="txtLogros4"></textarea></div>
                                        <div class="col-12 m-1 titulopes text-justify">Nudos Críticos</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los nudos críticos" maxlength="500" name="txtNudos4" id="txtNudos4"></textarea></div>
                                        <div class="col-12 align-content-center mt-1" id="botonLog4"><button class="btn bton btn_guardar_logros" id="guardarComp4" data-id="4">GUARDAR</button></div>
                                    </div>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades4">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_2">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_5">MONTO</div>
                                                <div class="estilo encabezado_5">% EVALUACIÓN</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades4" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
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
                                                    <div class="estilo encabezado_4">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
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
                                        <div class="col-10" id="inputComp5"></div>
                                        <div class="col-2" id="btn_comp5" style="font-weight: bold;">Eficacia</div>
                                    </div>
                                    <div class="row mt-3 align-items-center d-none" id="contenedorMeta5">
                                        <div class="col-10" id="inputMeta5">
                                            <input class="form-control" type="text" id="metaF5" name="metaF5" placeholder="Ingresar meta">
                                            <input type="hidden" name="idMeta5" id="idMeta5">
                                        </div>
                                        <div class="col-2" id="btn_meta5"></div>
                                    </div>
                                    <div class="row mt-3 align-items-center" id="lisIndicador5"></div>
                                    <div class="col-12 mt-3 main-end">
                                        <button class="btn bton btn_indicador_detalle" data-id="5" id="detalleIndicador5">DETALLES INDICADORES</button>
                                    </div>
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
                                    <div class="row mt-3 p-3 align-items-center d-none" id="contenedorlogros5" style="background: rgba(19,51,81,0.1)">
                                        <div class="col-12 titulopes text-justify">Logros Alcanzados</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los logros alcanzados" maxlength="500" name="txtLogros5" id="txtLogros5"></textarea></div>
                                        <div class="col-12 m-1 titulopes text-justify">Nudos Críticos</div>
                                        <div class="col-12"><textarea class="form-control" placeholder="Ingresar los nudos críticos" maxlength="500" name="txtNudos5" id="txtNudos5"></textarea></div>
                                        <div class="col-12 align-content-center mt-1" id="botonLog5"><button class="btn bton btn_guardar_logros" id="guardarComp5" data-id="5">GUARDAR</button></div>
                                    </div>
                                    <!--Lista Actividades-->
                                    <div class="tablaover">
                                        <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm d-none tablasdiv listaActividad" id="listadoActividades5">
                                            <div class="table-azul encabezado p-0">
                                                <div class="encabezado_completo estilo">ACTIVIDADES</div>
                                                <div class="p-0 estilo encabezado_2">NOMBRE</div>
                                                <div class="p-0 estilo encabezado_2">RESPONSABLE</div>
                                                <div class="p-0 encabezado_2">
                                                    <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                                    <div class="estilo encabezado_6">I</div>
                                                    <div class="estilo encabezado_6">II</div>
                                                    <div class="estilo encabezado_6">III</div>
                                                </div>
                                                <div class="estilo encabezado_5">MONTO</div>
                                                <div class="estilo encabezado_5">% EVALUACIÓN</div>
                                                <div class="estilo encabezado_4">ACCIÓN</div>
                                            </div>
                                            <div id="listaActividades5" class="align-self-center encabezado p-0"></div>
                                        </div>
                                    </div>
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
                                                    <div class="estilo encabezado_4">COSTO SIN IVA</div>
                                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                                    <div class="estilo encabezado_5">TOTAL REFORMADO</div>
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
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsProyectoCompletoEval.js"></script>
        <script src="../javascript/jsProyectoDetalle.js"></script>
        <script src="../javascript/jsGrafico.js" type="text/javascript"></script>
    </body>
</html>