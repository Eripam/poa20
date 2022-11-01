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
        <div class="modal fade bd-example-modal-xl" id="enviarReq" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <input type="hidden" name="agsol" id="agsol" value="<%=IntIdAreaGestion%>">
                    <input type="hidden" name="enviarid" id="enviarid" value="48">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" style="color:#133351">Enviar a Talento Humano</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive-lg p-0">
                                <thead class="table-azul">
                                    <tr>
                                        <th class="text-center align-middle">CI</th>
                                        <th class="text-center align-middle">NOMBRE</th>
                                        <th class="text-center align-middle">TOTAL</th>
                                        <th class="text-center align-middle">CI EST</th>
                                        <th class="text-center align-middle">NOMBRE EST</th>
                                        <th class="text-center align-middle">CÓDIGO STP</th>
                                        <th class="text-center align-middle">FECHA FIN</th>
                                        <th class="text-center align-middle"><i class="fas fa-check"></i></th>
                                    </tr>
                                </thead>
                                <tbody id="listaRequerimientosSolReq"></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer"><button class="btn bton" id="modalGuardarJustEnv">GUARDAR</button>
                        <button class="btn bton" data-dismiss="modal">CANCELAR</button></div>
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
                        <p class="titulo2 mt-2 mb-3">LISTADO DE SERVICIOS PROFESIONALES (DISPONIBILIDAD)</p>
                        <table class="container-fluid table-hover" id="example" style="width:100%">
                            <thead>
                                <tr>
                                    <th>CI SER. PROFESIONAL</th>
                                    <th>NOMBRE SER.PROFESIONAL</th>
                                    <th>CARGO</th>
                                    <th>FECHA INICIO</th>
                                    <th>FECHA FIN</th>
                                    <th>TOTAL</th>
                                    <th>TIPO HONORARIO</th>
                                    <th>CI ESTUDIANTE</th>
                                    <th>NOMBRE ESTUDIANTE</th>
                                    <th>CÓDIGO STP</th>
                                    <th>TIPO STP</th>
                                    <th>ESTADO</th>
                                    <th><i class="fas fa-check"></i></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>CI SER. PROFESIONAL</th>
                                    <th>NOMBRE SER.PROFESIONAL</th>
                                    <th>CARGO</th>
                                    <th>FECHA INICIO</th>
                                    <th>FECHA FIN</th>
                                    <th>TOTAL</th>
                                    <th>TIPO HONORARIO</th>
                                    <th>CI ESTUDIANTE</th>
                                    <th>NOMBRE ESTUDIANTE</th>
                                    <th>CÓDIGO STP</th>
                                    <th>TIPO STP</th>
                                    <th>ESTADO</th>
                                    <th><i class="fas fa-check"></i></th>
                                </tr>
                            </tfoot>
                        </table>  
                        <% if (adEjecucion.fechaporAño(intAnio)) {%>
                        <button class="btn bton mt-4 modalEnviarReqUnid">ENVIAR TALENTO HUMANO</button>    
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../javascript/jsListaServProfesional.js" type="text/javascript"></script>
    </body>
</html>