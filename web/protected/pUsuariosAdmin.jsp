<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adUsuario"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap4.min.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
    <body>
        <%@include file="plantillas/header.jsp" %> 
        <div class="modal fade bd-example-modal-xl" id="enviarReq" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" style="color:#133351">DESACTIVAR USUARIO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" id="bodyModal"></div>
                    <div class="modal-footer">
                        <button class="btn bton" id="modalGuardarJustEnv">GUARDAR</button>
                        <button class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="cross-center main-center d-none" id="loader" style="background: rgba(0,0,0,.1); z-index: 1000; position: absolute; width: 100%; min-height: 100vh;">
            <div class="spinner-border text-info" style="width: 5rem; height: 5rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <div class="container-fluid p-0 main">
            <div class="row mt-6">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid ">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo2 mt-2">USUARIOS</p>
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <form class="container p-2 formulario pt-3 mt-4 mb-3 needs-validation"  method="POST" action="../usuario?accion=IngresarUsuario" id="frmAddUsuarioCompras" novalidate  accept-charset="UTF-8">
                            <div class="form-row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="cedulaUsuario" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-center cross-center">Cédula:</label>
                                        <input type="text" id="cedulaUsuario" name="cedulaUsuario" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="nombreUsuario" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Nombres y Apellidos:</label>
                                        <input type="text" id="nombreUsuario" name="nombreUsuario" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="nombreUsuario" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Título:</label>
                                        <input type="text" id="tituloUsuario" name="tituloUsuario" placeholder="Ingresar solo las siglas ejemplo: ING., ARQ., LIC., DR." class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="areasGestion" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Tipo Usuario:</label>
                                        <select class="selectpicker my-select" data-width="67%" name="tipou" id="tipou" data-live-search="true">
                                            <%
                                                ResultSet rs3;
                                                rs3 = adAreaGestion.listaTipoUsuarios();
                                                while (rs3.next()) {
                                            %>
                                            <option value="<%= rs3.getString("tu_id")%>"><%=rs3.getString("tu_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="areasGestion" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Unidad:</label>
                                        <select class="selectpicker my-select" data-width="67%" data-live-search="true" name="agu" id="agu">
                                            <%
                                                ResultSet rs2;
                                                rs2 = adAreaGestion.listaAreasGestion();
                                                while (rs2.next()) {
                                            %>
                                            <option title="<%=rs2.getString("ag_alias")%>" value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="selectEstado">
                                    <div class="row main-center">
                                        <label for="areasGestion" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Estado:</label>
                                        <select class="selectpicker my-select" data-width="67%" name="selectEstadoU" id="selectEstadoU">
                                            <option value="1">Activo</option>
                                            <option value="0">Inactivo</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 d-none" id="selecAgU">
                                    <div class="row main-center">
                                        <label for="areasGestion" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Unidades para revisión:</label>
                                        <select class="selectpicker my-select" multiple data-width="67%" data-live-search="true" name="ag" id="ag">
                                            <%
                                                ResultSet rs4;
                                                rs4 = adAreaGestion.listaAreaGestionDeudas();
                                                while (rs4.next()) {
                                            %>
                                            <option title="<%=rs4.getString("ag_alias")%>" value="<%= rs4.getString("ag_id")%>"><%=rs4.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <button class="btn bton" type="submit">GUARDAR</button>
                        </form>

                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm tabla" id="example" style="width:100%">
                            <thead class="table-azul">
                                <tr>
                                    <th>CÉDULA </th>
                                    <th>NOMBRE</th>
                                    <th>DEPENDENCIA</th>
                                    <th>UNIDADES (REVISIÓN)</th>
                                    <th>TIPO USUARIO</th>
                                    <th>ESTADO</th>
                                    <th>ACCIÓN</th>
                                </tr>
                            </thead>
                        </table>

                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsUsuariosAdm.js" type="text/javascript"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

        <!-- (Optional) Latest compiled and minified JavaScript translation files -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>
        <script type="text/javascript">
            $('.my-select').selectpicker();
        </script>
    </body>
</html>
