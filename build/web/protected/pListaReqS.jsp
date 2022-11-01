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
    <body>
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
                        <input type="hidden" id="selectaniof" name="selectaniof" value="<%=intAnio%>">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <p class="titulo2 mt-2 mb-3">LISTADO DE REQUERIMIENTOS</p>
                        <table class="table-hover" id="example">
                            <thead>
                                <tr>
                                    <th>Unidad</th>
                                    <th>ID REQ</th>
                                    <th>OEI</th>
                                    <th>PROYECTO</th>
                                    <th>REQUERIMIENTO N</th>
                                    <th>REQUERIMIENTO D</th>
                                    <th>COSTO TOTAL</th>
                                    <th>VERIFICADO</th>
                                    <th>DISPONIBLE</th>
                                    <th>ESTADO</th>
                                    <th><i class="fas fa-check"></i></th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Unidad</th>
                                    <th>ID REQ</th>
                                    <th>OEI</th>
                                    <th>PROYECTO</th>
                                    <th>REQUERIMIENTO N</th>
                                    <th>REQUERIMIENTO D</th>
                                    <th>COSTO TOTAL</th>
                                    <th>VERIFICADO</th>
                                    <th>DISPONIBLE</th>
                                    <th>ESTADO</th>
                                    <th><i class="fas fa-check"></i></th>
                                </tr>
                            </tfoot>
                        </table>   
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/datatables.min.js" type="text/javascript"></script>
        <script src="../javascript/jsRequerimientosSal.js" type="text/javascript"></script>
    </body>
</html>
