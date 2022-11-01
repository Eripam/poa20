<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adRequerimientosGenerales"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap4.min.css">
    <body>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-6">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid ">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo mb-0"><u><%=strNombreAreaGestion%></u></p>
                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg">
                        <input type="hidden" value="<%=intAnio%>" id="anioSe">
                        <input type="hidden" value="<%=adRequerimientosGenerales.validacionProyectosExistentes(intAnio)%>" id="validacion" name="validacion">
                        <p class="titulo2 mt-2">REQUERIMIENTOS GENERALES</p>
                        <form class="container p-2 formulario pt-3 mt-4 needs-validation d-none"  method="POST" action="../requerimientosGenerales?accion=IngresarRequerimientoGeneral" id="frmAddRequerimientoGeneral" novalidate accept-charset="UTF-8">
                            <div class="form-row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs" name="idAgObEs">
                                        <label for="nombreRequerimientoGeneral" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-center cross-center">Nombre:</label>
                                        <input type="hidden" id="bandera" name="bandera" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center" value="1" />
                                        <input type="hidden" id="idRequerimientoGeneral" name="idRequerimientoGeneral" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                        <input type="text" id="nombreRequerimientoGeneral" name="nombreRequerimientoGeneral" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="descripcionRequerimientoGeneral" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Descripción:</label>
                                        <textarea class="form-control col-10 col-xs-10 col-md-8" id="descripcionRequerimientoGeneral" name="descripcionRequerimientoGeneral" required="" ></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="cpcRequerimientoGeneral" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-center cross-center">CPC:</label>
                                        <input type="text" id="cpcRequerimientoGeneral" name="cpcRequerimientoGeneral" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="unidadRequerimientoGeneral" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-center cross-center">Unidad de medida:</label>
                                        <select class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center selectpicker p-0 m-0" data-live-search="true" id="selunidad1" name="selunidad1">
                                            <%
                                                ResultSet rs6 = adRequerimientosGenerales.ListaUnidadSelect();
                                                while (rs6.next()) {
                                            %>
                                            <option value="<%= rs6.getString("unidad_id")%>"><%=rs6.getString("unidad_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="precioRequerimientoGeneral" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-center cross-center">Precio:</label>
                                        <input type="text" id="precioRequerimientoGeneral" name="precioRequerimientoGeneral" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="precioRequerimientoGeneral" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-center cross-center">Año:</label>
                                        <select name="selAnio" id="selAnio" class="col-12 col-xs-12 col-md-8  form-control justify-content-center justify-content-md-end cross-center">
                                            <option value="<%=intAnio%>"><%=intAnio%></option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <button class="btn bton" type="submit">GUARDAR</button>
                        </form>
                        <div class="col-12 mt-3 main-start">
                            <button class="btn bton btn_indicador_detalle" id="requerimientoIngresar" data-id="2">INGRESAR REQUERIMIENTOS</button>
                        </div>
                        <div class="row" id="areasGestionDependientes"></div>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm tabla">
                            <thead class="table-azul">
                                <tr>
                                    <th>NOMBRE </th>
                                    <th>DESCRIPCIÓN</th>
                                    <th>COSTO UNITARIO</th>
                                    <th>CPC</th>
                                    <th>UNIDAD MEDIDA</th>
                                    <th>AÑO</th>
                                    <th>OPCIÓN</th>
                                </tr>
                            </thead>
                            <tbody id="requerimientosGeneralesForm">
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsRequerimientosGenerales.js" type="text/javascript"></script>
    </body>
</html>
