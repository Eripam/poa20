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
        <div class="modal fade bd-example-modal-xl" id="modalJustVis" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
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
                                        <div for="recipient-name" class="form-control" id="autoridadvis"></div>
                                    </div>
                                </div>                               
                            </div>
                            <table class="table table-sm mt-5 table-bordered table-hover table-responsive p-0" style="width: 100% !important">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">ID</th>
                                        <th class="text-center align-middle">OEI</th>
                                        <th class="text-center align-middle">CANTIDAD</th>
                                        <th class="text-center align-middle">UNIDAD MED.</th>
                                        <th class="text-center align-middle">NOMBRE REQ.</th>
                                        <th class="text-center align-middle">DESCRIPCIÓN REQ.</th>
                                        <th class="text-center align-middle">COSTO UNITARIO</th>
                                        <th class="text-center align-middle">COSTO SIN IVA</th>
                                        <th class="text-center align-middle">COSTO TOTAL</th>
                                        <th class="text-center align-middle">ACCIÓN</th>
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
        <div class="modal fade bd-example-modal-xl" id="eliminarJustificativo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">ELIMINAR ÚLTIMO ESTADO</h5>
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
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Estados:</label>
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
        <div class="modal fade" id="regresarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ENTREGAR JUSTIFICATIVO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 mb-3">
                                <label for="recipient-name" class="col-form-label col-12" id="centroCreg"></label>
                                <label for="recipient-name" class="col-form-label col-12" id="montoreg"></label>
                                <label for="recipient-name" class="col-form-label col-12" style="font-weight: bold">OBSERVACIÓN:</label>
                                <input type="hidden" class="col-12" name="idreqregresar" id="idreqregresarj">
                                <textarea class="form-control" name="observacionreg" id="observacionreg"></textarea>
                            </div>
                        </div>
                        <input type="hidden" id="idsolicitudenviar">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton intrevisar" data-estado="36">ENTREGAR</button>
                        <button type="button" class="btn bton intrevisar" data-estado="39">DEV. A COMPRAS</button>
                        <button type="button" class="btn bton intrevisar" data-estado="35">DEV. C. JUST.</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="regresarModalVer" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">VERIFICAR JUSTIFICATIVO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-12 mb-3">
                                <label for="recipient-name" class="col-form-label col-12" id="centroCregV"></label>
                                <label for="recipient-name" class="col-form-label col-12" id="montoregV"></label>
                                <label for="recipient-name" class="col-form-label col-12">OBSERVACIÓN:</label>
                                <input type="hidden" name="idreqregresar" id="idreqregresarjv">
                                <textarea class="form-control" name="observacionregv" id="observacionregv"></textarea>
                            </div>
                        </div>
                        <input type="hidden" id="idsolicitudenviar">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton intrevisarv" data-estado="34">RECIBIR</button>
                        <button type="button" class="btn bton intrevisarv" data-estado="39">DEV. A COMPRAS</button>
                        <button type="button" class="btn bton intrevisarv" data-estado="35">DEV. C. JUST.</button>
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
                            <div class="row">
                                <div class="col-12 mb-3">
                                    <label for="recipient-name" class="col-form-label col-12" id="centroCregF"></label>
                                    <label for="recipient-name" class="col-form-label col-12" id="montoregF"></label>
                                </div>
                            </div>
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
        <div class="cross-center main-center d-none" id="loader" style="background: rgba(0,0,0,.1); z-index: 100000; position: absolute; width: 100%; min-height: 100vh;">
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
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaP" name="cedulaP">
                        <input type="hidden" value="1" id="tipolista" name="tipolista">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
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
        <script src="../javascript/jsJustificativosAdm.js" type="text/javascript"></script>
    </body>
</html>
