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
        <div class="modal fade bd-example-modal-xl" id="modificarFecha" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR FECHAS</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmModificarRef">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Tipo:</label>
                                        <input type="hidden" class="form-control col-12 col-md-9" name="tipoFecha" id="tipoFecha">
                                        <input type="text" class="form-control col-12 col-md-9" name="nombreFecha" id="nombreFecha">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Fecha:</label>
                                        <input type="datetime-local" class="form-control col-12 col-md-9" name="horareunion" id="horareunion">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año: </label>
                                        <select name="selAniom" id="selAniom" class="col-4 col-md-3 col-lg-2 col-xl-2 form-control">
                                            <%
                                                ResultSet rs2 = adAreaGestion.listaTechosF();
                                                while (rs2.next()) {
                                            %>
                                            <option value="<%= rs2.getString("ti_fecha")%>"><%=rs2.getString("ti_fecha")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Observación:</label>
                                        <textarea class="form-control" id="txtObservacionF"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-5 justify-content-center justify-content-md-end cross-center">Cierre ejecución?</label>
                                        <input type="checkbox" name="ckbCierreM" id="ckbCierreM">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalModificarFec">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="AgregarFecha" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">AGREGAR FECHAS PARA CIERRE DE AÑO EN EJECUCIÓN</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmModificarRef">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center" id="codigolbl">Tipo o Módulo:</label>
                                        <input type="text" class="form-control col-12 col-md-9" name="txtNombre" id="txtNombre"></label>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Fecha:</label>
                                        <input type="datetime-local" class="form-control col-12 col-md-9" name="horareunionF" id="horareunionF">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Año: </label>
                                        <select name="selAnio" id="selAnio" class="col-4 col-md-3 col-lg-2 col-xl-2 form-control">
                                            <%
                                                ResultSet rs3 = adAreaGestion.listaTechosF();
                                                while (rs3.next()) {
                                            %>
                                            <option value="<%= rs3.getString("ti_fecha")%>"><%=rs3.getString("ti_fecha")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-3 justify-content-center justify-content-md-end cross-center">Observación:</label>
                                        <textarea id="txtObservacionFF" class="form-control col-12 col-md-9"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12 col-md-5 justify-content-center justify-content-md-end cross-center">Cierre ejecución?</label>
                                        <input type="checkbox" name="ckbCierre" id="ckbCierre">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarFechas">GUARDAR</button>
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
                        <p class="titulo2 mt-2">ACTIVAR FECHAS</p>
                        <div class="row col-12 inicio"><button class="btn bton" id="btnAgregarF">Agregar Fechas</button></div>
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" value="<%=intAnio%>" id="anio" name="anio">
                        <div class="tablaover mb-4">
                            <div class="table mt-2 table-bordered table-striped table-responsive-md table-responsive-sm tablasdiv" id="listadoRequerimiento5">
                                <div class="table-azul encabezado p-0" style="font-size: 0.85em">
                                    <div class="p-0 estilo encabezado_2">MÓDULO</div>
                                    <div class="p-0 estilo encabezado_7">FECHA</div>
                                    <div class="p-0 estilo encabezado_4">AÑO</div>
                                    <div class="estilo encabezado_9">OBSERVACIÓN</div>
                                    <div class="estilo encabezado_5">ACCIÓN</div>
                                </div>
                                <div id="listaRequerimiento" class="align-self-center encabezado p-0" style="font-size: 0.85em"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsActivarF.js" type="text/javascript"></script>
        <!--Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    </body>
</html>
