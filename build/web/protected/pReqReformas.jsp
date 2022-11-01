<%@page import="poa.acceso.adRequerimientosGenerales"%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adUsuario"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap4.min.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <body>
        <%@include file="plantillas/header.jsp" %> 
        <div class="modal fade bd-example-modal-xl" id="modificarReq" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR REFORMA</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmModificarRef">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="container-fluid">
                            <div class="row">
                                <label class="col-12 col-xs-12 col-md-5 justify-content-center justify-content-md-end cross-center">Necesita Contratación Pública?</label>
                                <div class="form-check col-12 col-xs-12 col-md-3 tipoRequerimiento">
                                    <input class="form-check-input" type="radio" id="tipoReq" name="tipoReq" value="1">
                                    <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                </div>
                                <div class="form-check col-12 col-xs-12 col-md-3 tipoRequerimiento">
                                    <input class="form-check-input" type="radio" id="tipoReq" name="tipoReq" value="2">
                                    <label class="form-check-label" style="font-weight: normal;">NO</label>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">ID:</label>
                                        <input type="text" readonly class="form-control col-12 col-md-9" name="intID" id="intID">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Nombre:</label>
                                        <textarea type="text" class="form-control col-12 col-md-9" name="txtNombre" id="txtNombre"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Descripción:</label>
                                        <textarea type="text" class="form-control col-12 col-md-9" name="txtDescripcion" id="txtDescripcion"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Cantidad:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="dblCantidad" id="dblCantidad">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Costo Unitario:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="dblCostoU" id="dblCostoU">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Necesita IVA?</label>
                                        <div class="form-check col-12 col-xs-12 col-md-4 tipoActividad">
                                            <input class="form-check-input" type="radio" id="radioiva" name="radioiva" value="1">
                                            <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                        </div>
                                        <div class="form-check col-12 col-xs-12 col-md-5 tipoActividad">
                                            <input class="form-check-input" type="radio" id="radioiva" name="radioiva" value="0">
                                            <label class="form-check-label" style="font-weight: normal;">NO</label>
                                        </div>
                                    </div>
                                </div>
                                <div class=" row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 pr-4 reqprogramacion" id="programacionrequerimiento"></div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento: </label>
                                        <select class="selectpicker col-10 col-xs-10 col-md-9 p-0" id="selfinan" name="selfinan">
                                            <%
                                                ResultSet rs5 = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                                while (rs5.next()) {
                                            %>
                                            <option value="<%= rs5.getString("financiamiento_id")%>"><%=rs5.getString("financiamiento_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="unidadM">
                                    <div class="row main-center">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Unidad Med: </label>
                                        <select class="selectpicker col-10 col-xs-10 col-md-9 p-0" data-live-search="true" id="selunidad" name="selunidad">
                                            <%
                                                ResultSet rs6 = adRequerimientosGenerales.ListaUnidadSelect();
                                                while (rs6.next()) {
                                            %>
                                            <option value="<%= rs6.getString("unidad_id")%>"><%=rs6.getString("unidad_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="tipoC">
                                    <div class="row main-center">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de compra: </label>
                                        <select class="selectpicker col-10 col-xs-10 col-md-9 p-0" id="seltipoc" name="seltipoc">
                                            <%
                                                ResultSet rs7 = adRequerimientosGenerales.ListaTipoCompraSelect();
                                                while (rs7.next()) {
                                            %>
                                            <option value="<%= rs7.getString("tc_id")%>"><%=rs7.getString("tc_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="cpc">
                                    <div class="row main-center">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">CPC:</label>
                                        <input type="text" class="col-12 col-xs-12 col-md-9 form-control" id="inpcpc" name="inpcpc">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Reforma:</label>
                                        <input type="text" class="col-12 col-xs-12 col-md-9 form-control" id="intReforma" name="intReforma">
                                    </div>
                                </div>
                                <div class="col-12" style="font-weight: bold;">ESTRUCTURA PRESUPUESTARIA</div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Ejercicio:</label>
                                        <input type="hidden" class="form-control col-12 col-md-9" name="txtPresupuestoid" id="txtPresupuestoid">
                                        <input type="text" class="form-control col-12 col-md-9" name="txtEjercicio" id="txtEjercicio">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Entidad:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtEntidad" id="txtEntidad">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Unidad Eje:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtUnidadE" id="txtUnidadE">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Unidad Desc:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtUnidadD" id="txtUnidadD">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Programa:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtPrograma" id="txtPrograma">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Subprograma:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtSubprograma" id="txtSubprograma">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Proyecto:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtProyecto" id="txtProyecto">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Actividad:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtActividad" id="txtActividad">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Obras:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtObra" id="txtObra">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Geografico:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtGeografico" id="txtGeografico">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Renglo:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtRenglo" id="txtRenglo">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Renglo Aux:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtRengloA" id="txtRengloA">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Fuente:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtFuente" id="txtFuente">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Organismo:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtOrganismo" id="txtOrganismo">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Correlativo:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtCorrelativo" id="txtCorrelativo">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Presupuesto:</label>
                                        <select class="selectpicker col-10 col-xs-10 col-md-9 p-0" id="selPresupuesto" name="selPresupuesto">
                                            <option value="CORRIENTE">CORRIENTE</option>
                                            <option value="INVERSION">INVERSION</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Año:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtAnioR" id="txtAnioR">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Observación:</label>
                                        <textarea type="text" class="form-control col-12 col-md-9" name="txtObservacion" id="txtObservacion" placeholder="Se debe ingresar una breve descripción del porque se realiza esta acción"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarReq">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="eliminarProy" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">ELIMINAR REQUERIMIENTO DE REFORMA</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmIngresarCP">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <div class="col-12" style="font-weight: bold" id="nombrePE"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Reforma:</label>
                                        <div class="col-12" style="font-weight: bold" id="estadoP"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalEliminarEstados">ELIMINAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="modSaldos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR REQUERIMIENTO DE SALDO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmIngresarCP">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">ID:</label>
                                        <input type="text" readonly class="form-control col-12 col-md-9" name="intIDS" id="intIDS">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Nombre:</label>
                                        <textarea class="form-control col-12 col-md-9" name="txtNombreS" id="txtNombreS" readonly></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Reforma:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtReformaS" id="txtReformaS" readonly>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Saldo:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="dblSaldo" id="dblSaldo">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Observación:</label>
                                        <textarea type="text" class="form-control col-12 col-md-9" name="txtObservacionS" id="txtObservacionS" placeholder="Se debe ingresar una breve descripción del porque se realiza esta acción"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalModificarSaldo">GUARDAR</button>
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
        <div class="container-fluid p-0 main">
            <div class="row mt-6">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid ">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo2 mt-2">LISTA REFORMAS</p>
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" value="<%=intAnio%>" id="anio" name="anio">
                        <div class="row p-0 m-0">
                            <div class="row col-lg-4 col-12 p-0">
                                <label class="col-4" style="font-weight: bold;">BUSCAR:</label>
                                <input type="text" class="form-control col-8" id="codigoreq" placeholder="Ingresar el código">
                            </div>
                            <div class="col-lg-3 col-12 p-0">
                                <button class="btn bton" id="buscarCod">BUSCAR POR CÓDIGO</button>      
                            </div>
                        </div>
                        <p class="titulo2 mt-2">REFORMAS NORMALES</p>
                        <div class="tablaover mb-4">
                            <div class="table mt-2 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="listadoRequerimiento5">
                                <div class="table-azul encabezado p-0" style="font-size: 0.85em">
                                    <div class="estilo encabezado_8">ID</div>
                                    <div class="p-0 estilo encabezado_4">NOMBRE</div>
                                    <div class="p-0 estilo encabezado_5">DESCRIPCIÓN</div>
                                    <div class="estilo encabezado_4">COSTO TOTAL SIN IVA</div>
                                    <div class="estilo encabezado_4">COSTO TOTAL CON IVA</div>
                                    <div class="p-0 encabezado_2">
                                        <div class="estilo encabezado_completo">CUATRIMESTRE</div>
                                        <div class="estilo encabezado_6">I</div>
                                        <div class="estilo encabezado_6">II</div>
                                        <div class="estilo encabezado_6">III</div>
                                    </div>
                                    <div class="estilo encabezado_4">REFORMA</div>
                                    <div class="estilo encabezado_4">TIPO REFORMA</div>
                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                </div>
                                <div id="listaRequerimiento" class="align-self-center encabezado p-0" style="font-size: 0.85em"></div>
                            </div>
                        </div>
                        <p class="titulo2 mt-2">REFORMAS SOLO SALDOS</p>
                        <div class="tablaover mb-4">
                            <div class="table mt-2 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="listadoRequerimiento5">
                                <div class="table-azul encabezado p-0" style="font-size: 0.85em">
                                    <div class="p-0 estilo encabezado_5">NOMBRE</div>
                                    <div class="p-0 estilo encabezado_5">DESCRIPCIÓN</div>
                                    <div class="estilo encabezado_4">COSTO TOTAL SIN IVA</div>
                                    <div class="estilo encabezado_4">COSTO TOTAL IVA</div>
                                    <div class="estilo encabezado_4">TIPO</div>
                                    <div class="estilo encabezado_4">DISMINUCIÓN</div>
                                    <div class="estilo encabezado_4">TOTAL</div>
                                    <div class="estilo encabezado_4">REFORMA</div>
                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                </div>
                                <div id="listaRequerimientoS" class="align-self-center encabezado p-0" style="font-size: 0.85em"></div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsReqReformas.js" type="text/javascript"></script>
        <!--Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    </body>
</html>
