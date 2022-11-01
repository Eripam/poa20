<%-- 
    Document   : pServiciosProfesionales
    Created on : 28-abr-2020, 16:16:21
    Author     : Erika Arévalo
--%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="poa.acceso.adActividadRequerimiento"%>
<%@page import="poa.clases.cActividadRequerimiento"%>
<%@page import="com.google.gson.Gson"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/datatables.min.css">
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <body>
        <div class="modal fade" id="alertaMensaje" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="z-index:1000000">
            <div class="modal-dialog" role="document">
                <div class="modal-content" style="background: #F5F5F5;">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel" style="font-weight: bold"><i class="fas fa-exclamation-triangle mr-1" style="color:yellow"></i>MENSAJE DE ALERTA</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" row>
                        <div class="col-12 row" id="alertaMensajeDiv">
                        </div> 
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
                    <input type="hidden" name="idserveli" id="idserveli">
                    <div class="modal-body">¿Está seguro que desea eliminar el dato seleccionado?</div>
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
                    <input type="hidden" name="idservenv" id="idservenv">
                    <div class="modal-body">¿Está seguro que desea enviar el dato seleccionado?</div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="enviarModalBton">ENVIAR</button>
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
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">LIQUIDAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">OBSERVACIÓN:</label>
                                        <textarea type="text" class="form-control" name="nombrecustodio" id="nombrecustodio"></textarea>
                                    </div>
                                </div>
                            </div>
                            <table class="table-hover table-striped" style="width: 100%">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">CÉDULA</th>
                                        <th class="text-center align-middle">NOMBRE</th>
                                        <th class="text-center align-middle">TIPO</th>
                                        <th class="text-center align-middle">FECHA INICIO</th>
                                        <th class="text-center align-middle">FECHA FIN</th>
                                        <th class="text-center align-middle">COSTO TOTAL</th>
                                        <th class="text-center align-middle"><i class="fas fa-check"></i></th>
                                    </tr>
                                </thead>
                                <tbody id="listaProyectos"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJust">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
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
                    <div class="tab-content ml-5 mr-5 pestania m-0 p-0">     
                        <p class="titulo2">LIQUIDACIÓN PARCIAL DE SOLICITUDES</p>
                        <p class="titulo3 mt-2 mb-3" style="text-align: left !important;">SOLICITUD: <%=request.getParameter("codigo")%>-STP-<%=intAnio%></p>
                        <input type="hidden" id="solicitudidl" name="solicitudidl" value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="agjustificativo" id="agjustificativo" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tagjustificativo" id="tagjustificativo" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" name="selectanio" id="selectanio" value="<%=intAnio%>">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="tablaover">
                            <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="example">
                                <div class="table-azul encabezado p-0">
                                    <div class="p-0 estilo encabezado_2">REQ. NOMBRE</div>
                                    <div class="p-0 estilo encabezado_2">REQ. DESCRIPCIÓN</div>
                                    <div class="p-0 estilo encabezado_4">CANTIDAD</div>
                                    <div class="p-0 estilo encabezado_5">COSTO UNITARIO</div>
                                    <div class="estilo encabezado_5">COSTO SIN IVA</div>
                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                    <div class="estilo encabezado_8">ACCIÓN</div>
                                </div>
                                <div id="listaServicios" class="align-self-center encabezado p-0"></div>
                            </div>
                        </div>
                        <button class="btn bton mt-4" id="btnGenerarJ">LIQUIDAR</button>    
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../javascript/jsServiciosPLIQ.js" type="text/javascript"></script>
    </body>
</html>
