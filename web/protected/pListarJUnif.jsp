<%-- 
    Document   : pTechos
    Created on : 07-oct-2019, 15:16:15
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
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">JUSTIFICATIVO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Centro de costo:</label>
                                        <input type="text" class="form-control" id="centrovis" name="centrovis" readonly disabled="true">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Nombre custodio:</label>
                                        <input type="text" class="form-control" id="custodiovis" name="custodiovis" readonly disabled="true">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Dirigido a:</label>
                                        <select class="form-control" name="autoridadvis" id="autoridadvis">
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
                            </div>
                            <table class="table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0" style="width: 100% !important">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
                                        <th class="text-center align-middle">VER</th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosSolVis"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJustVis">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="modalJustAgregar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="solic" id="solic">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">JUSTIFICATIVO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="table-hover" style="width: 100%; font-size: .8em;" id="modalexample">
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
                                        <th>CANTIDAD</th>
                                        <th>COSTO UNITARIO</th>
                                        <th>COSTO SIN IVA</th>
                                        <th>COSTO TOTAL</th>
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
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR JUSTIFICATIVO</h5>
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
                                        <input type="text" class="form-control" id="centromod" name="centromod">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Nombre custodio:</label>
                                        <input type="text" class="form-control" name="nombrecustodiomod" id="nombrecustodiomod">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Dirigido a:</label>
                                        <select class="form-control" name="rectoringmod" id="rectoringmod">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <% ResultSet rsv2;
                                                rsv2 = adEjecucion.ListaAutoridades();
                                                while (rsv2.next()) {
                                            %>
                                            <option style="width: 85%; height: auto" value="<%= rsv2.getString("autoridades_id")%>"><%=rsv2.getString("autoridades_nombre")%> - <%=rsv2.getString("autoridades_cargo")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cédula custodio:</label>
                                        <input type="text" class="form-control" name="cedulacustodiomod" id="cedulacustodiomod">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">

                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cargo custodio:</label>
                                        <input type="text" class="form-control" id="cargocustodiomod" name="cargocustodiomod">
                                    </div>
                                </div>
                            </div>
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
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
        <div class="modal fade bd-example-modal-lg" id="modificarInfo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsoli" id="agsoli" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyectoi" name="cedulaProyectoi">
                    <input type="hidden" id="solidmodi" name="solidmodi">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR JUSTIFICATIVO</h5>
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
                                        <input type="text" class="form-control" id="centromodi" name="centromodi">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Nombre custodio:</label>
                                        <input type="text" class="form-control" name="nombrecustodiomodi" id="nombrecustodiomodi">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Dirigido a:</label>
                                        <select class="form-control" name="rectoringmodi" id="rectoringmodi">
                                            <option selected value="0" disabled>Seleccionar...</option>
                                            <% ResultSet rsv3;
                                                rsv3 = adEjecucion.ListaAutoridades();
                                                while (rsv3.next()) {
                                            %>
                                            <option style="width: 85%; height: auto" value="<%= rsv3.getString("autoridades_id")%>"><%=rsv3.getString("autoridades_nombre")%> - <%=rsv3.getString("autoridades_cargo")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cédula custodio:</label>
                                        <input type="text" class="form-control" name="cedulacustodiomodi" id="cedulacustodiomodi">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">

                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cargo custodio:</label>
                                        <input type="text" class="form-control" id="cargocustodiomodi" name="cargocustodiomodi">
                                    </div>
                                </div>
                            </div>
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosSoli"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJustModi">GUARDAR</button>
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
                        ¿Esta seguro que desea eliminar el Justificativo?
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
                        ¿Esta seguro que desea enviar el Justificativo?
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
        <div class="modal fade bd-example-modal-xl" id="unificarR" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <input type="hidden" id="requnifid" name="requnifid">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">REQUERIMIENTOS UNIFICADOS</h5>
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
                                        <textarea class="form-control" id="nombreunif" name="nombreunif" placeholder disabled></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Descripción:</label>
                                        <textarea class="form-control" name="descunif" id="descunif" placeholder disabled></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Cantidad:</label>
                                        <input type="number" class="form-control" readonly name="cantidadunif" id="cantidadunif" placeholder disabled>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3"> 
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Costo Unitario:</label>
                                        <input type="number" class="form-control" name="costounif" id="costounif" placeholder disabled>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label">Unidad:</label>
                                        <input type="text" class="form-control" name="unidadinp" id="unidadinp" placeholder disabled>
                                    </div>
                                </div>
                            </div>
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0" style="width: 100%">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">DEPENDENCIA</th>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
                                        <th class="text-center align-middle">COSTO TOTAL</th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosUnificaReq"></tbody>
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
                        <p class="titulo">LISTA JUSTIFICATIVOS</p>
                        <input type="hidden" name="tipoAg" id="tipoAg" value="<%=intIdTipoAreaGestion%>">
                        <input type="hidden" name="areaPadre" id="areaPadre" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tipoUsuario" id="tipoUsuario" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaP" name="cedulaP">
                        <table class="table-hover" id="example" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>CÓDIGO</th>
                                    <th>AUTORIDAD</th>
                                    <th>CENTRO DE COSTO</th>
                                    <th>MONTO</th>
                                    <th>ESTADO</th>
                                    <th>OBSERVACIÓN</th>
                                    <th>ACCIÓN</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>CÓDIGO</th>
                                    <th>AUTORIDAD</th>
                                    <th>CENTRO DE COSTO</th>
                                    <th>MONTO</th>
                                    <th>ESTADO</th>
                                    <th>OBSERVACIÓN</th>
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
        <script src="../javascript/jsJustificativosUnif.js" type="text/javascript"></script>
    </body>
</html>
