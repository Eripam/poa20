<%-- 
    Document   : pGenerarJ
    Created on : 28-ene-2020, 14:10:33
    Author     : Erika Arévalo
--%>

<%@page import="poa.acceso.adRequerimientosGenerales"%>
<%@page import="poa.acceso.adEjecucion"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="plantillas/sesiones.jsp" %> 
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/datatables.min.css">
    <body>
        <div class="modal fade bd-example-modal-xl" id="generarJ" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">GENERAR JUSTIFICATIVO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Centro de costo:</label>
                                        <input type="text" class="form-control" id="centro" name="centro">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Nombre custodio:</label>
                                        <input type="text" class="form-control" name="nombrecustodio" id="nombrecustodio">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Dirigido a:</label>
                                        <select class="form-control" name="rectoring" id="rectoring">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <% ResultSet rsv;
                                                rsv = adEjecucion.ListaAutoridades();
                                                while (rsv.next()) {
                                            %>
                                            <option style="width: 85%; height: auto" value="<%= rsv.getString("autoridades_id")%>"><%=rsv.getString("autoridades_nombre")%> - <%=rsv.getString("autoridades_cargo")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cédula custodio:</label>
                                        <input type="text" class="form-control" name="cedulacustodio" id="cedulacustodio">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">

                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cargo custodio:</label>
                                        <input type="text" class="form-control" id="cargocustodio" name="cargocustodio">
                                    </div>
                                </div>
                            </div>
                            <table class="table-hover table-striped" style="width: 100%">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
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
        <div class="modal fade bd-example-modal-lg" id="enviarReq" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" name="enviarid" id="enviarid">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabelE" style="color:#133351"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
                                        <th class="text-center align-middle">COSTO TOTAL</th>
                                        <th class="text-center align-middle"><i class="fas fa-check"></i></th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosSolReq"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJustEnv">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="unificarR" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <input type="hidden" id="requnifid" name="requnifid">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">UNIFICAR REQUERIMIENTO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Nombre:</label>
                                        <textarea class="form-control" id="nombreunif" name="nombreunif"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Descripción:</label>
                                        <textarea class="form-control" name="descunif" id="descunif"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cantidad:</label>
                                        <input type="number" class="form-control" readonly name="cantidadunif" id="cantidadunif">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Costo Unitario:</label>
                                        <input type="number" class="form-control" name="costounif" id="costounif">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Unidad:</label>
                                        <select class="form-control" name="selunidaduni" id="selunidaduni">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <% ResultSet rsv2;
                                                rsv2 = adRequerimientosGenerales.ListaUnidadSelect();
                                                while (rsv2.next()) {
                                            %>
                                            <option style="width: 85%; height: auto" value="<%= rsv2.getString("unidad_id")%>"><%=rsv2.getString("unidad_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <%if (IntIdAreaGestion == 68) {%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12">Partes y Piezas:</label>
                                        <div class="col-12 ml-5">
                                            <input type="checkbox" id="partesid" value="1" name="partesid" checked>
                                            <label for="defaultChecked2">SI</label>
                                        </div>
                                    </div>
                                </div>
                                <%}else if (IntIdAreaGestion == 60) {%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12">Suministros de oficina o limpieza:</label>
                                        <div class="col-12 ml-5">
                                            <input type="checkbox" id="partesid" value="1" name="partesid" checked>
                                            <label for="defaultChecked2">SI</label>
                                        </div>
                                    </div>
                                </div>
                                <%}else if (IntIdAreaGestion == 57 || IntIdAreaGestion == 69 || IntIdAreaGestion == 107) {%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12">Suma totales</label>
                                        <div class="col-12 ml-5">
                                            <input type="checkbox" id="partesid" value="1" name="partesid" checked>
                                            <label for="defaultChecked2">SI</label>
                                        </div>
                                    </div>
                                </div>
                                <%}%>
                            </div>
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0" style="width: 100%">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
                                        <th class="text-center align-middle">COSTO TOTAL</th>
                                        <th class="text-center align-middle"><i class="fas fa-check"></i></th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosUnificaReq"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJustUnific">GUARDAR</button>
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
                    <div class="modal-body">
                        ¿Esta seguro que desea eliminar el requerimiento unificado?
                        <input type="hidden" id="reqidunif">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalElSolicitud">ELIMINAR</button>
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
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <p class="titulo2 mt-2 mb-3">LISTADO DE REQUERIMIENTOS - PAC</p>
                        <table class="table-hover" id="example" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>OEI</th>
                                    <th>NOMBRE REQ.</th>
                                    <th>DESCRIPCIÓN REQ.</th>
                                    <th>CANTIDAD</th>
                                    <th>COSTO UNITARIO</th>
                                    <th>COSTO SIN IVA</th>
                                    <th>COSTO TOTAL</th>
                                    <th><i class="fas fa-check"></i></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>OEI</th>
                                    <th>NOMBRE REQ.</th>
                                    <th>DESCRIPCIÓN REQ.</th>
                                    <th>Cantidad</th>
                                    <th>Costo Unitario</th>
                                    <th>Costo Sin IVA</th>
                                    <th>Costo Total</th>
                                    <th><i class="fas fa-check"></i></th>
                                </tr>
                            </tfoot>
                        </table> 
                        <% if(adEjecucion.fechaporAño(intAnio)){%>
                        <button class="btn bton mt-4" id="modalGuardarJustificativoG">GENERAR JUSTIFICATIVO</button>    
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../javascript/jsRequerimientosRU.js" type="text/javascript"></script>
    </body>
</html>
