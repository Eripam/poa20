<%-- 
    Document   : pListarNP
    Created on : 19-mar-2020, 09:16:15
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adEjecucion"%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adTecho"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/datatables.min.css">
    <body>
        <div class="modal fade bd-example-modal-lg" id="modalJustVis" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" name="idsolvist" id="idsolvist">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">SOLICITUD</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Asunto:</label>
                                        <input class="form-control" type="text" name="autoridadvis" id="autoridadvis" readonly>
                                    </div>
                                </div>  
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Motivación:</label>
                                        <textarea type="text" class="form-control" id="centrovis" name="centrovis" readonly disabled="true"></textarea>
                                    </div>
                                </div>                             
                            </div>
                            <table class="table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0" style="width: 100% !important">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">TIPO</th>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">MONTO</th>
                                        <th class="text-center align-middle">MONTO IVA</th>
                                        <th class="text-center align-middle">MONTO ANTICIPO (998)</th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosSolVis"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="modalJustAgregar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="solicag" id="solicag">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">SOLICITUD</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="table-hover" style="width: 100%; font-size: .8em;" id="modalexample">
                                <thead>
                                    <tr>
                                        <th>Unidad</th>
                                        <th>TIPO</th>
                                        <th>OEI</th>
                                        <th>PROYECTO</th>
                                        <th>REQUERIMIENTO</th>
                                        <th>MONTO</th>
                                        <th>MONTO IVA</th>
                                        <th>MONTO ANTICIPO (998)</th>
                                        <th>FUENTE</th>
                                        <th><i class="fas fa-check"></i></th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <th>Unidad</th>
                                        <th>TIPO</th>
                                        <th>OEI</th>
                                        <th>PROYECTO</th>
                                        <th>REQUERIMIENTO</th>
                                        <th>MONTO</th>
                                        <th>MONTO IVA</th>
                                        <th>MONTO ANTICIPO (998)</th>
                                        <th>FUENTE</th>
                                        <th><i class="fas fa-check"></i></th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJustAgreg">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-lg" id="generarJ" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <input type="hidden" id="solidmod" name="solidmod">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR SOLICITUD</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Asunto:</label>
                                        <select class="form-control" name="rectoringmod" id="rectoringmod">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <% ResultSet rsv;
                                                rsv = adEjecucion.ListaTipoSolicitud();
                                                while (rsv.next()) {
                                            %>
                                            <option style="width: 85%; height: auto" value="<%= rsv.getString("ts_id")%>"><%=rsv.getString("ts_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Motivación:</label>
                                        <textarea type="text" class="form-control" name="descripcionsmod" id="descripcionsmod"></textarea>
                                    </div>
                                </div>
                            </div>
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">TIPO</th>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">MONTO</th>
                                        <th class="text-center align-middle">MONTO IVA</th>
                                        <th class="text-center align-middle">MONTO ANTICIPO (998)</th>
                                        <th class="text-center align-middle"><i class="fas fa-check"></i></th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosSol"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJustMod">GUARDAR</button>
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
                        ¿Esta seguro que desea eliminar la Solicitud?
                        <input type="hidden" id="solicitudid">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalElSolicitud">ELIMINAR</button>
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
                    <div class="modal-body">
                        ¿Esta seguro que desea enviar la Solicitud?
                        <input type="hidden" id="idsolicitudenviar">
                        <input type="hidden" id="idestadoenviar">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalEnvJust">ENVIAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
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
                            <div class="card-body"  id="fechaenvioreq" >
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="modalServProf" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" name="idsolvist" id="idsolvist">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">LISTA PERSONAL</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">                            
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label col-12">
                                            <div style='font-weight: bold'>REQUERIMIENTO </div><div id="reqnom"></div>
                                        </label>
                                    </div>
                                </div>                             
                            </div>
                            <table class="table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0" style="width: 100% !important">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">APELLIDOS Y NOMBRES</th>
                                        <th class="text-center align-middle">CARGO</th>
                                        <th class="text-center align-middle">TIPO</th>
                                        <th class="text-center align-middle">FECHA INI.</th>
                                        <th class="text-center align-middle">FECHA FIN</th>
                                        <th class="text-center align-middle">TOTSL SIN IVA</th>
                                        <th class="text-center align-middle">IVA</th>
                                        <th class="text-center align-middle">TOTAL</th>
                                        <th class="text-center align-middle"> SOL. DISPONIBILIDAD</th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosServProf"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
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
                        <p class="titulo">LISTA SOLICITUD OBLIGACIONES PENDIENTES Y COMPROMETIDOS NO DEVENGADOS</p>
                        <input type="hidden" name="tipoAg" id="tipoAg" value="<%=intIdTipoAreaGestion%>">
                        <input type="hidden" name="areaPadre" id="areaPadre" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tipoUsuario" id="tipoUsuario" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaP" name="cedulaP">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <table class="table-hover" id="example" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>COD. TRAMITE</th>
                                    <th>DEPENDENCIA</th>
                                    <th>ASUNTO</th>
                                    <th>MONTO</th>
                                    <th>MOTIVACIÓN</th>
                                    <th>ESTADO</th>
                                    <th>ACCIÓN</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>COD. TRAMITE</th>
                                    <th>DEPENDENCIA</th>
                                    <th>ASUNTO</th>
                                    <th>MONTO</th>
                                    <th>MOTIVACIÓN</th>
                                    <th>ESTADO</th>
                                    <th>ACCIÓN</th>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../javascript/jsSolicitudesOPC.js" type="text/javascript"></script>
    </body>
</html>
