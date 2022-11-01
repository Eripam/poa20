<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adTecho"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap4.min.css">
    <body>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid ">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo"><%=strNombreAreaGestion%></p>
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <input type="hidden" name="tipoAg" id="tipoAg" value="<%=intIdTipoAreaGestion%>">
                        <input type="hidden" name="areaPadre" id="areaPadre" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tipoUsuario" id="tipoUsuario" value="<%=intIdTipoUsuario%>">
                        <%if (intIdTipoUsuario == 3 || intIdTipoUsuario == 4) {%>
                        <p class="titulo2">TECHOS PRESUPUESTARIOS TOTAL</p>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm" id="listarTechosTotal">
                            <thead class="table-azul">
                                <tr>
                                    <th>TIPO</th>
                                    <th>ASIGNADO</th>
                                    <th>ASIGNADO A CARRERAS</th>
                                    <th>PLANIFICADO</th>
                                    <th>PLANIFICADO VALORES PENDIENTES</th>
                                    <th>DISPONIBLE</th>
                                </tr>
                            </thead>
                            <tbody id="cuerpoListarTechosTotal">
                            </tbody>
                        </table>
                        <%}%>
                    </div>
                    <div class="tab-content ml-5 mr-5 pestania">
                        <%if (intIdTipoUsuario == 3 || intIdTipoUsuario == 4) {%><p class="titulo"><%=strAliasAreaGestion%></p><%}%>
                        <p class="titulo2">TECHOS PRESUPUESTARIOS - <%=strAliasAreaGestion%></p>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm" id="listarTechosFacultad">
                            <thead class="table-azul">
                                <tr>
                                    <th>TIPO</th>
                                    <th>ASIGNADO</th>
                                    <th>PLANIFICADO</th>
                                    <th>PLANIFICADO VALORES PENDIENTES</th>
                                    <th>DISPONIBLE</th>
                                </tr>
                            </thead>
                            <tbody id="cuerpoListarTechoAdicional">
                            </tbody>
                        </table>

                    </div>
                </div>
                <div class="content col-11 container-fluid contenedorCarreras">
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsTechosFacultad.js" type="text/javascript"></script>
    </body>
</html>
