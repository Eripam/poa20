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
                    <form class="container p-2 formulario pt-3 mt-4 needs-validation"  method="POST" id="frmAddProyecto" novalidate accept-charset="UTF-8">
                        <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgFormulacion" name="idAgFormulacion">
                        <input type="hidden" id="selectaniof" name="selectaniof" value="<%=intAnio%>">
                        <input type="hidden" id="iddeudas" name="iddeudas">
                        <div class="form-row">
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                <div class="row main-center" id="tipoDeudaD">
                                    <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo:</label>
                                    <div class="form-check col-12 col-xs-12 col-md-3">
                                        <input class="form-check-input" type="radio" id="tipoDeuda" name="tipoDeuda" value="1">
                                        <label class="form-check-label"  style="font-weight: normal;">Obligaciones Pendientes</label>
                                    </div>
                                    <div class="form-check col-12 col-xs-12 col-md-3">
                                        <input class="form-check-input" type="radio" id="tipoDeuda" name="tipoDeuda" value="2">
                                        <label class="form-check-label" style="font-weight: normal;">Comprometidos No Devendagos</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">OEI:</label>
                                    <input class="form-control col-10 col-xs-10 col-md-8" id="nmbOEI" name="nmbOEI" required type="number">
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Proyecto:</label>
                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="textNombreProy" name="textNombreProy" required minlength="1"></textarea>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre proceso (Req):</label>
                                    <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtNomProceso" name="txtNomProceso" required minlength="2"></textarea>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center"># contrato u orden compra:</label>
                                    <input type="text" class="form-control col-10 col-xs-10 col-md-8" id="textContrato" name="textContrato" required maxlength="50" pattern="[A-ZÑÁÉÍÓÚ, A-ZÑÁÉÍÓÚ]{1,50}">
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de contratación:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selectTipoCon" name="selectTipoCon">
                                        <%
                                            ResultSet rs2 = adAreaGestion.listarTipoContratacion();
                                            while (rs2.next()) {
                                        %>
                                        <option value="<%= rs2.getString("tcon_id")%>"><%=rs2.getString("tcon_nombre")%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selectFin" name="selectFin">
                                        <%
                                            ResultSet rs5 = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                            while (rs5.next()) {
                                        %>
                                        <option value="<%= rs5.getString("financiamiento_id")%>"><%=rs5.getString("financiamiento_nombre")%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo Presupuesto:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selectTipoPres" name="selectTipoPres">
                                        <option value="CORRIENTE">CORRIENTE</option>
                                        <option value="INVERSION">INVERSIÓN</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="montoCon">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Monto Contrato:</label>
                                    <input class="form-control col-10 col-xs-10 col-md-8" id="montoContrato" name="montoContrato" required maxlength="50" type="number">
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Monto Iva:</label>
                                    <input class="form-control col-10 col-xs-10 col-md-8" id="montoIva" name="montoIva" required maxlength="50" type="number">
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="montoAnt">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Monto Anticipo:</label>
                                    <input class="form-control col-10 col-xs-10 col-md-8" id="montoAnticipo" name="montoAnticipo" required maxlength="50" type="number">
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 " id="montoPend">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Pendiente de Pago:</label>
                                    <input class="form-control col-10 col-xs-10 col-md-8" readonly id="montoPendiente" name="montoPendiente" required maxlength="50" type="number">
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 ">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Reforma:</label>
                                    <input class="form-control col-10 col-xs-10 col-md-8" id="deudareforma" name="deudareforma" required maxlength="50" type="number">
                                </div>
                            </div>
                            <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                <div class="row main-center">
                                    <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Estado:</label>
                                    <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selectEstadoD" name="selectEstadoD">
                                        <option value="1">MODIFICAR</option>
                                        <option value="0">ELIMINAR</option>
                                    </select>
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
        <div class="modal fade bd-example-modal-xl" id="modificarEstructura" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="titleEstructura" style="color:#133351"></h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmModificarRefEst">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto2">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgFormulacion" name="idAgFormulacion2">
                        <div class="container-fluid">
                            <div class="row">
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
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">T. Estructura:</label>
                                        <input type="hidden" id="slTipoEst" name="slTipoEst" id="slTipoEst">
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" id="selectTipoEs" name="selectTipoEs">
                                            <option value="0">Fuente Efectiva</option>
                                            <option value="1">Fuente del IVA</option>
                                            <option value="2">Fuente Anticipo</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarEstr">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="eliminarProy" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">ELIMINAR ESTRUCTURA</h5>
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
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Fuente:</label>
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
                        <p class="titulo2 mt-2">VALORES PENDIENTES DE PAGO</p>
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" value="<%=intAnio%>" id="anio" name="anio">
                        <div class="row p-0 m-0">
                            <div class="row col-lg-4 col-12 p-0">
                                <label class="col-4" style="font-weight: bold;">CÓDIGO:</label>
                                <input type="text" class="form-control col-8" id="codigoreq" placeholder="Ingresar el código">
                            </div>
                            <div class="col-lg-3 col-12 p-0">
                                <button class="btn bton" id="buscarCod">BUSCAR POR CÓDIGO</button>      
                            </div>
                        </div>
                        <p class="titulo2 mt-2">DATOS DEL VALOR PENDIENTE DE PAGO</p>
                        <div class="tablaover mb-4">
                            <div class="table mt-2 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="listadoRequerimiento5">
                                <div class="table-azul encabezado p-0" style="font-size: 0.85em">
                                    <div class="estilo encabezado_8">ID</div>
                                    <div class="p-0 estilo encabezado_5">NOMBRE PROCESO</div>
                                    <div class="estilo encabezado_4">DEPENDENCIA</div>
                                    <div class="estilo encabezado_4">MONTO</div>
                                    <div class="estilo encabezado_4">MONTO IVA</div>
                                    <div class="estilo encabezado_4">MONTO ANTICIPO</div>
                                    <div class="estilo encabezado_4">MONTO PENDIENTE</div>
                                    <div class="estilo encabezado_4">REFORMA</div>
                                    <div class="estilo encabezado_4">TIPO REFORMA</div>
                                    <div class="estilo encabezado_4">ACCIÓN</div>
                                </div>
                                <div id="listaRequerimiento" class="align-self-center encabezado p-0" style="font-size: 0.85em"></div>
                            </div>
                        </div>
                        <p class="titulo2 mt-2">ESTRUCTURA PRESUPUESTARIA</p>
                        <div class="col-12 p-0 justify-content-start text-justify">
                            <button class="btn bton" id="ingresarEst">+ ESTRUCTURA</button>      
                        </div>
                        <div class="tablaover mb-4">
                            <div class="table mt-2 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="listadoRequerimiento5">
                                <div class="table-azul encabezado p-0" style="font-size: 0.85em">
                                    <div class="p-0 estilo encabezado_4">ENTIDAD</div>
                                    <div class="p-0 estilo encabezado_4">EJERCICIO</div>
                                    <div class="estilo encabezado_4">PROGRAMA</div>
                                    <div class="estilo encabezado_4">PROYECTO</div>
                                    <div class="estilo encabezado_4">ACTIVIDAD</div>
                                    <div class="estilo encabezado_4">OBRA</div>
                                    <div class="estilo encabezado_4">RENGLO</div>
                                    <div class="estilo encabezado_4">FUENTE</div>
                                    <div class="estilo encabezado_4">TIPO</div>
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
        <script src="../javascript/jsReqReformasD.js" type="text/javascript"></script>
        <!--Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    </body>
</html>
