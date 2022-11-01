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
        <div class="modal fade bd-example-modal-xl" id="generarJ" style="overflow-y: auto" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">AGREGAR / MODIFICAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 row m-1" style="outline:1px solid #c6c8ca" id="certificacionMonto">
                                    <div class="col-12 titulopes p-0" style="font-size: 1.25em">DATOS PROFESIONAL A CONTRATAR</div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                        <div class="row">
                                            <label for="recipient-name" class="col-form-label">Cédula</label>
                                            <input type="text" class="form-control" name="cedulaser" id="cedulaser">
                                        </div>
                                    </div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                        <div class="row">
                                            <label for="recipient-name" class="col-form-label">Nombre</label>
                                            <input type="text" class="form-control" name="nombreser" id="nombreser">
                                        </div>
                                    </div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                        <div class="row">
                                            <label for="recipient-name" class="col-form-label">Apellido</label>
                                            <input type="text" class="form-control" name="apellidoI" id="apellidoI">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <input type="hidden" id="reqidV" name="reqidV">
                                    <input type="hidden" id="servId" name="servId">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Tipo:</label>
                                        <select class="form-control" name="rectoring" id="rectoring">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <option style="width: 85%; height: auto" value="1">Horas Clase</option>
                                            <option style="width: 85%; height: auto" value="2">Honorario Mensual</option>
                                            <option style="width: 85%; height: auto" value="3">Honorario Fijo</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cargo</label>
                                        <input type="text" class="form-control" name="cargo" id="cargo">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="horas">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Número de Horas</label>
                                        <input type="text" class="form-control" name="nhoras" id="nhoras">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="shoras">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Total por Hora (Favor verificar el valor aprobado en resolución)</label>
                                        <input type="text" class="form-control" name="sueldoH" id="sueldoH">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="fehchai">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Fecha Inicio</label>
                                        <input type="text" class="form-control" name="fechainicio" id="fechainicio" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="fechaf">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Fecha Fin</label>
                                        <input type="text" class="form-control" name="fechafin" id="fechafin" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="sueldo">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Sueldo Mensual Sin Iva</label>
                                        <input type="text" class="form-control" name="sueldoMensual" id="sueldoMensual">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="personalmed">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Personal Médico</label>
                                        <input type="checkbox" name="personalm" id="personalm" value="1">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="tpagas">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Total a Pagar Sin Iva</label>
                                        <input type="text" class="form-control" name="totalpagar" id="totalpagar">
                                    </div>
                                </div>
                                <%if (IntIdAreaGestion == 46) {%>
                                <div class="col-12 row m-1 d-none" style="outline:1px solid #c6c8ca" id="datosEstu">
                                    <div class="col-12 titulopes p-0" style="font-size: 1.25em">DATOS ESTUDIANTE</div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                        <div class="row">
                                            <label for="recipient-name" class="col-form-label">Cédula</label>
                                            <input type="text" class="form-control" name="cedulaEstu" id="cedulaEstu">
                                        </div>
                                    </div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                        <div class="row">
                                            <label for="recipient-name" class="col-form-label">Nombre</label>
                                            <input type="text" class="form-control" name="nombreEstu" id="nombreEstu">
                                        </div>
                                    </div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                        <div class="row">
                                            <label for="recipient-name" class="col-form-label">Apellido</label>
                                            <input type="text" class="form-control" name="apellidoEstu" id="apellidoEstu">
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3" id="tpagas">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Observación:</label>
                                        <textarea class="form-control" name="observoficio" id="observoficio" placeholder="Ingresar numero de oficio de solicitud del tramite"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJust">GUARDAR</button>
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
                        <p class="titulo2">SERVICIOS PROFESIONALES</p>
                        <input type="hidden" id="array" name="array" value="<%=request.getParameter("id")%>">
                        <input type="hidden" name="agjustificativo" id="agjustificativo" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tagjustificativo" id="tagjustificativo" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" name="selectanio" id="selectanio" value="<%=intAnio%>">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="tablaover">
                            <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="example">
                                <div class="table-azul encabezado p-0">
                                    <div class="p-0 estilo encabezado_5">REQ. NOMBRE</div>
                                    <div class="p-0 estilo encabezado_5">REQ. DESCRIPCIÓN</div>
                                    <div class="p-0 estilo encabezado_4">CANTIDAD</div>
                                    <div class="p-0 estilo encabezado_4">COSTO UNITARIO</div>
                                    <div class="estilo encabezado_5">COSTO SIN IVA</div>
                                    <div class="estilo encabezado_5">COSTO TOTAL</div>
                                    <div class="estilo encabezado_4">ESTADO</div>
                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                </div>
                                <div id="listaServicios" class="align-self-center encabezado p-0"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../javascript/jsServiciosP.js" type="text/javascript"></script>
    </body>
</html>
