<%-- 
    Document   : pTechos
    Created on : 07-oct-2019, 15:16:15
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adTecho"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <body>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid">
                    <ul class="nav nav-tabs  ml-5" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#techoi">TECHOS FACULTAD/DIRECCIÓN</a>
                        </li>
                        <%if (IntIdAreaGestion != 36 && IntIdAreaGestion != 37) {%>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#techou">TECHO UNIDADES</a>
                        </li>
                        <%}%>
                    </ul>
                    <div class="tab-content ml-5 mr-5">
                        <div id="techoi" class="container-fluid tab-pane active pestania"><br>
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
                                                <th>VALORES PENDIENTES</th>
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
                                                <th>VALORES PENDIENTES</th>
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
                        <%if (IntIdAreaGestion != 36 && IntIdAreaGestion != 37) {%>
                        <div id="techou" class="container tab-pane fade pestania"><br>
                            <p class="titulo">TECHO PRESUPUESTARIO POR UNIDADES</p>
                            <form method="POST" class="container formulario p-2" id="frmTechosUnid">
                                <div class="row mt-3">
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 mb-2">
                                        <label class="col-3 main-end">AÑO:</label>
                                        <input type="hidden" name="aghidden" id="aghidden">
                                        <input type="hidden" name="tipohidden" id="tipohidden">
                                        <select class="col-9 selectpicker p-0 m-0" id="aniou" name="aniou">
                                            <option value="<%=intAnio%>"><%=intAnio%></option>
                                        </select>
                                    </div>
                                    <input type="hidden" name="hiddenAnio">
                                    <input type="hidden" name="hiddenArea">
                                    <input type="hidden" name="hiddenTipo">
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                                        <label class="col-3 main-end">UNIDAD:</label>
                                        <select class="col-9 selectpicker p-0 m-0" data-live-search="true" id="tipoun" name="tipoun">
                                            <option selected disabled>Seleccionar...</option>
                                            <%
                                                ResultSet rs2 = adAreaGestion.listaAreaGestionHijos(IntIdAreaGestion, intAnio);
                                                while (rs2.next()) {
                                            %>
                                            <option value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                                        <label class="col-3 main-end">TIPO:</label>
                                        <select class="col-9 selectpicker p-0 m-0" id="unidadun" name="unidadun">
                                            <option value="0" selected disabled>Seleccionar...</option>
                                            <%
                                                ResultSet rs3 = adTecho.ListaTipoSelectTecho(IntIdAreaGestion, intAnio);
                                                while (rs3.next()) {
                                            %>
                                            <option value="<%= rs3.getString("tp_id")%>"><%=rs3.getString("tp_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                                        <label class="col-3 main-end">CANTIDAD: </label>
                                        <input class="col-9" type="text" id="cantidadun" name="cantidadun">
                                    </div>
                                    <div class="row col-12 mt-3">
                                        <div class="col-12 col-xs-12 col-sm-12 main-center"><a class="bton" id="ingresarTechoUni">GUARDAR</a></div>
                                    </div>
                                </div>
                            </form>
                            <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm">
                                <thead class="table-azul">
                                    <tr>
                                        <th>UNIDAD</th>
                                        <th>TIPO</th>
                                        <th>CANTIDAD</th>
                                        <th>AÑO</th>
                                        <th>TOTAL</th>
                                        <th>ACCIÓN</th>
                                    </tr>
                                </thead>
                                <tbody id="techouni">
                                </tbody>
                            </table>
                        </div>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../javascript/jsTechoDec.js" type="text/javascript"></script>
        <script src="../javascript/jsTechosFacultad.js" type="text/javascript"></script>
    </body>
</html>
