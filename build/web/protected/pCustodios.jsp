<%-- 
    Document   : pCustodios
    Created on : 02-mar-2020, 13:35:35
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@page import="poa.acceso.adProyecto"%>
<%@page import="poa.acceso.adTecho"%>
<%@page import="poa.acceso.adPerspectivaObj"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <body>
        <div class="modal fade bd-example-modal-xl" id="modalCustodio" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" name="tpag" id="tpag" value="<%=intIdTipoAreaGestion%>">
                    <input type="hidden" name="selectAnio" id="selectAnio" value="<%=intAnio%>">
                    <input type="hidden" name="idsolvist" id="idsolvist">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="tituloCustodioReq" style="color:#133351">JUSTIFICATIVO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="table mt-5 table-bordered table-hover table-striped p-0">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">PROYECTO</th>
                                        <th class="text-center align-middle">REQUERIMIENTO</th>
                                        <th class="text-center align-middle">CÉDULA CUSTODIO</th>
                                        <th class="text-center align-middle">NOMBRE CUSTODIO</th>
                                        <th class="text-center align-middle">CARGO CUSTODIO</th>
                                        <th class="text-center align-middle">UBICACIÓN</th>
                                        <th class="text-center align-middle"></th>
                                    </tr>
                                </thead>
                                <tbody id="listaProyectosCustodio"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarJustUnificCus">GUARDAR</button>
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
                    <!--<div class="content container-fluid m-3">
                       Tab panes 
                       <div class="container-fluid pestania"><br>-->
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo mb-0"><u>CUSTODIOS</u></p>
                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs">
                        <input type="hidden" value="<%=intIdTipoUsuario%>" id="idTipoUsu">
                        <div class="container p-2 formulario pt-3 mb-3">
                            <p class="titulo2 mb-0"><u>UNIDAD DE CONTROL DE BIENES Y BODEGA</u></p>
                            <div class="row main-center">
                                <div class="col-5 main-end">Agregar Custodios</div>
                                <div class="col-5">
                                    <a href="#" onclick="agregarC(29, <%=IntIdAreaGestion%>);"><i class="fas fa-user-plus"></i></a>
                                </div>
                            </div>
                            <div class="row main-center">
                                <div class="col-5 main-end">Descargar Archivos</div>
                                <div class="col-5">
                                    <form method="POST" action="../controladorReportePDF" id="FrmDescargarPOA" class="instructivos_archivos_icono" target="_blank">
                                        <input type="hidden" value="reportePOAUnidades" name="accion">
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoa">
                                        <input type="hidden" value="<%=strNombreAreaGestion%>" name="agnombrepoa">
                                        <a href="#" onclick="activarPOA(<%=IntIdAreaGestion%>, '<%=strNombreAreaGestion%>', 60, 'BODEGA');"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="container p-2 formulario pt-3 mb-3">
                            <p class="titulo2 mb-0"><u>DIRECCIÓN DE TECNOLOGIAS DE LA INFORMACIÓN Y COMUNICACIÓN</u></p>
                            <div class="row main-center">
                                <div class="col-5 main-end">Agregar Custodios</div>
                                <div class="col-5">
                                    <a href="#" onclick="agregarC(28, <%=IntIdAreaGestion%>);"><i class="fas fa-user-plus"></i></a>
                                </div>
                            </div>
                            <div class="row main-center">
                                <div class="col-5 main-end">Descargar Archivos</div>
                                <div class="col-5">
                                    <form method="POST" action="../controladorReportePDF" id="FrmDescargarPOA" class="instructivos_archivos_icono" target="_blank">
                                        <input type="hidden" value="reportePOAUnidades" name="accion">
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoa">
                                        <input type="hidden" value="<%=strNombreAreaGestion%>" name="agnombrepoa">
                                        <a href="#" onclick="activarPOA(<%=IntIdAreaGestion%>, '<%=strNombreAreaGestion%>', 68, 'DTIC');"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="container p-2 formulario pt-3">
                            <p class="titulo2 mb-0"><u>DIRECCIÓN DE COMUNICACIÓN Y RELACIONES PÚBLICAS</u></p>
                            <div class="row main-center">
                                <div class="col-5 main-end">Agregar Custodios</div>
                                <div class="col-5">
                                    <a href="#" onclick="agregarC(30, <%=IntIdAreaGestion%>);"><i class="fas fa-user-plus"></i></a>
                                </div>
                            </div>
                            <div class="row main-center">
                                <div class="col-5 main-end">Descargar Archivos</div>
                                <div class="col-5">
                                    <form method="POST" action="../controladorReportePDF" id="FrmDescargarPOA" class="instructivos_archivos_icono" target="_blank">
                                        <input type="hidden" value="reportePOAUnidades" name="accion">
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" name="agidpoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agtitulopoa">
                                        <input type="hidden" value="<%=strAliasAreaGestion%>" name="agaliaspoa">
                                        <input type="hidden" value="<%=strNombreAreaGestion%>" name="agnombrepoa">
                                        <a href="#" onclick="activarPOA(<%=IntIdAreaGestion%>, '<%=strNombreAreaGestion%>', 54, 'DIRCOM');"><i class="fas fa-file-download"></i></a>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../javascript/jsGeneral.js" type="text/javascript"></script>
        <script src="../javascript/jsCustodios.js" type="text/javascript"></script>
    </body>
</html>