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
                            <a class="nav-link active" data-toggle="tab" href="#techoi">TECHO INSTITUCIONAL</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#techou">TECHO UNIDADES</a>
                        </li>
                    </ul>
                    <div class="tab-content ml-5 mr-5">
                        <div id="techoi" class="container-fluid tab-pane active pestania"><br>
                            <p class="titulo">TECHO PRESUPUESTARIO INSTITUCIONAL</p>
                            <form method="POST" class="container formulario p-2" id="frmTechosIns">
                                <div class="row mt-3">
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                                        <label class="col-4">AÑO:</label>
                                        <input class="col-8" type="text" id="anioin" name="anioin">
                                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                                    </div>
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                                        <label class="col-4">CANTIDAD: </label>
                                        <input class="col-8" type="text" id="cantidadin" name="cantidadin">
                                    </div>
                                    <div class="row col-12 mt-3">
                                        <div class="col-12 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 main-end"><a class="bton" id="ingresarTechoIn">GUARDAR</a></div>
                                        <div class="col-12 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 main-start"><a class="bton" id="cancelarTecho">CANCELAR</a></div>
                                    </div>
                                </div>
                            </form>
                            <table class="container-fluid table mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm">
                                <thead class="table-azul">
                                    <tr>
                                        <th>CANTIDAD</th>
                                        <th>AÑO</th>
                                        <th>REFORMA</th>
                                        <th>TOTAL</th>
                                        <th>ACCIÓN</th>
                                    </tr>
                                </thead>
                                <tbody id="techoinst"></tbody>
                            </table>
                        </div>
                        <div id="techou" class="container tab-pane fade pestania"><br>
                            <p class="titulo">TECHO PRESUPUESTARIO POR UNIDADES</p>
                            <form method="POST" class="container formulario p-2" id="frmTechosUnid">
                                <div class="row mt-3">
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6 mb-2">
                                        <label class="col-3 main-end">AÑO:</label>
                                        <input type="hidden" name="txtaniou" id="txtaniou">
                                        <select class="col-9 selectpicker p-0 m-0" id="aniou" name="aniou">
                                            <option selected disabled>Seleccionar...</option>
                                            <%                                                ResultSet rs = adTecho.listaAnioInstitucional();
                                                while (rs.next()) {
                                            %>
                                            <option value="<%= rs.getString("ti_fecha")%>"><%=rs.getString("ti_fecha")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                                        <label class="col-3 main-end">UNIDAD:</label>
                                        <input type="hidden" name="txttipoun" id="txttipoun">
                                        <select class="col-9 selectpicker p-0 m-0" data-live-search="true" id="tipoun" name="tipoun">
                                            <option selected disabled>Seleccionar...</option>
                                            <%
                                                ResultSet rs2 = adAreaGestion.listaFaculAdmin(intAnio);
                                                while (rs2.next()) {
                                            %>
                                            <option value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                    <div class="row col-12 col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xl-6">
                                        <label class="col-3 main-end">TIPO:</label>
                                        <input type="hidden" name="txtunidadun" id="txtunidadun">
                                        <select class="col-9 selectpicker p-0 m-0" id="unidadun" name="unidadun">
                                            <option value="0" selected disabled>Seleccionar...</option>
                                            <%
                                                ResultSet rs3 = adTecho.listaTipoProyecto();
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
                                        <div class="col-12 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 main-end"><a class="bton" id="ingresarTechoUni">GUARDAR</a></div>
                                        <div class="col-12 col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xl-6 main-start"><a class="bton">LIMPIAR</a></div>
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
                                        <th>REFORMA</th>
                                        <th>TOTAL</th>
                                        <th>ACCIÓN</th>
                                    </tr>
                                </thead>
                                <tbody id="techouni">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../javascript/jsTecho.js" type="text/javascript"></script>
    </body>
</html>
