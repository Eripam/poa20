<%-- 
    Document   : pGenerarJ
    Created on : 28-ene-2020, 14:10:33
    Author     : Erika Arévalo
--%>

<%@page import="poa.acceso.adEjecucion"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="plantillas/sesiones.jsp" %> 
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/datatables.min.css">
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <body>
        <div class="modal fade bd-example-modal-xl" id="generarCP" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">CERTIFICACIÓN PRESUPUESTARIA</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmIngresarCP">
                        <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="reqidcp" id="reqidcp">
                        <input type="hidden" name="certpid" id="certpid">
                        <input type="hidden" name="solicitudidcp" id="solicitudidcp" value="<%=request.getParameter("id")%>">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Tipo:</label>
                                        <select class="form-control" name="rectoring" id="rectoring">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <% ResultSet rsv;
                                                rsv = adEjecucion.ListaTipoCP();
                                                while (rsv.next()) {
                                            %>
                                            <option style="width: 85%; height: auto" value="<%= rsv.getString("tcp_id")%>"><%=rsv.getString("tcp_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Código:</label>
                                        <input type="text" class="form-control" name="codigocp" id="codigocp">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="valorlbl">Valor:</label>
                                        <input type="text" class="form-control" name="valorcp" id="valorcp">
                                        <input type="hidden" class="form-control" name="valoriva" id="valoriva">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="porcentajecomp">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Porcentaje anticipo:</label>
                                        <input type="text" class="form-control" name="porcanticipo" id="porcanticipo">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="codigoivaCP">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Código:</label>
                                        <input type="text" class="form-control" name="codigocpiva" id="codigocpiva">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="montoIvaCP">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">IVA:</label>
                                        <input type="text" class="form-control" name="valorcpiva" id="valorcpiva" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Fecha de Aprobación:</label>
                                        <input type="text" class="form-control" name="fechain" id="fechain">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">OBSERVACIÓN:</label>
                                        <textarea name="txtobservacion" id="txtobservacion" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div id="recurrenteDiv" class="d-none row col-12">
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                        <div class="row">
                                            <div class="col-3">
                                                <label for="recipient-name" class="col-form-label">Tipo recurrente?</label>
                                            </div>
                                            <div class="col-9 row">
                                                <div class="col-2">
                                                    <label class="col-form-label mr-1">SI</label><input type="radio" value="1" name="recurrenteCert" id="recurrenteCert">   
                                                </div>
                                                <div class="col-2">
                                                    <label class="col-form-label mr-1">NO</label><input type="radio" value="0" name="recurrenteCert" id="recurrenteCert">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                        <div class="row">
                                            <div class="col-3">
                                                <label for="recipient-name" class="col-form-label">Necesita Liquidación?</label>
                                            </div>
                                            <div class="col-9 row">
                                                <div class="col-2">
                                                    <label class="col-form-label mr-1">SI</label><input type="radio" value="1" name="liquCert" id="liquCert">   
                                                </div>
                                                <div class="col-2">
                                                    <label class="col-form-label mr-1">NO</label><input type="radio" value="0" name="liquCert" id="liquCert">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarCP">GUARDAR</button>
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
                    <div class="modal-body">¿Esta seguro que desea eliminar?<input type="hidden" name="eliminarCPin" id="eliminarCPin"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="eliminarModalBton">ELIMINAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="generarJ" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" name="solicitudidcpg" id="solicitudidcpg" value="<%=request.getParameter("id")%>">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">CERTIFICACIÓN PRESUPUESTARIA</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Tipo:</label>
                                        <select class="form-control" name="rectoringg" id="rectoringg">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <% ResultSet rsv2;
                                                rsv2 = adEjecucion.ListaTipoCP();
                                                while (rsv2.next()) {
                                            %>
                                            <option style="width: 85%; height: auto" value="<%= rsv2.getString("tcp_id")%>"><%=rsv2.getString("tcp_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolblg">Código:</label>
                                        <input type="text" class="form-control" name="codigocpg" id="codigocpg">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="valorlblg">Valor:</label>
                                        <input type="text" class="form-control" name="valorcpg" id="valorcpg" readonly>
                                        <input type="hidden" class="form-control" name="valorivag" id="valorivag">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="porcentajecompg">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Porcentaje anticipo:</label>
                                        <input type="text" class="form-control" name="porcanticipog" id="porcanticipog">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Código:</label>
                                        <input type="text" class="form-control" name="codigocpivag" id="codigocpivag">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">IVA:</label>
                                        <input type="text" class="form-control" name="valorcpivag" id="valorcpivag" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Fecha de Aprobación:</label>
                                        <input type="text" class="form-control" name="fechaing" id="fechaing">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Observación:</label>
                                        <textarea name="txtobservaciong" id="txtobservaciong" class="form-control"></textarea>
                                    </div>
                                </div>
                            </div>
                            <table class="table-hover table-striped" style="width: 100%">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">REQ. ID</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">ITEM</th>
                                        <th class="text-center align-middle">FUENTE</th>
                                        <th class="text-center align-middle">COSTO TOTAL</th>
                                        <th class="text-center align-middle"><i class="fas fa-check"></i></th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientos"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarCPG">GUARDAR</button>
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
                    <div class="tab-content ml-5 mr-5 pestania">
                        <input type="hidden" name="agjustificativo" id="agjustificativo" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tagjustificativo" id="tagjustificativo" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" name="solicitudid" id="solicitudid" value="<%=request.getParameter("id")%>">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <p class="titulo2 mt-2 mb-3">CERTIFICACIÓN PRESUPUESTARIA</p>
                        <p class="titulo3 mt-2 mb-3" style="text-align: left !important;">JUSTIFICATIVO: <%=request.getParameter("codigo")%>-UCP-<%=intAnio%></p>
                        <div class="tablaover">
                            <div class="table mt-5 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="example">
                                <div class="table-azul encabezado p-0">
                                    <div class="p-0 estilo encabezado_4">REQ. ID</div>
                                    <div class="p-0 estilo encabezado_2">REQ. NOMBRE</div>
                                    <div class="p-0 estilo encabezado_4">ITEM</div>
                                    <div class="p-0 estilo encabezado_8">FUENTE</div>
                                    <div class="p-0 estilo encabezado_5">TOTAL SIN IVA</div>
                                    <div class="p-0 estilo encabezado_5">TOTAL</div>
                                    <div class="estilo encabezado_5">ESTADO</div>
                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                </div>
                                <div id="listaServicios" class="align-self-center encabezado p-0"></div>
                            </div>
                        </div>
                        <%if (adEjecucion.fechaporAño(intAnio)) {%>
                        <button class="btn bton mt-4" id="btnAgregarCP">AGREGAR CERTIFICACIÓN PRESUPUESTARIA</button> 
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../javascript/jsCertificacionP.js" type="text/javascript"></script>
    </body>
</html>
