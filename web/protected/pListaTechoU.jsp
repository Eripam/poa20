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
                        <div class="row align-content-center align-items-center align-self-center">
                            <div class="col-3">
                                <label>Unidad:</label>
                                <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                    <option disabled selected>Seleccionar..</option>                                  
                                    <option value="1">FACULTADES / EXTENSIONES</option>                                    
                                    <option value="2">UNIDADES ADMINISTRATIVAS</option>                                    
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                        <div class="content col-11 container-fluid">
                            <div class="tab-content ml-5 mr-5 pestania">
                                <p class="titulo">TECHOS PRESUPUESTARIOS - PRESUPUESTO INSTITUCIONAL</p>
                                <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm">
                                    <thead class="table-azul contenedorCarreras"></thead>
                                    <tbody id="contenedorBody"></tbody>
                                </table>
                            </div>
                        </div>
                        <div class="content col-11 container-fluid">
                            <div class="tab-content ml-5 mr-5 pestania">
                                <p class="titulo">AUTO GESTIÓN</p>
                                <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm">
                                    <thead class="table-azul contenedorCarrerasA"></thead>
                                    <tbody id="contenedorBodyA"></tbody>
                                </table>
                            </div>
                        </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsTechosFacultadUnidad.js" type="text/javascript"></script>
    </body>
</html>
