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
        <!-- Modal -->
        <div class="modal fade" id="eliminarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Eliminar Usuarios</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <!-- inicio body-->
                <input type="hidden" name="tipou" id="tipou" value="10">
                <input type="hidden" name="agu" id="agu" value="<%=IntIdAreaGestion%>">
                    <div class="row main-center">
                        <p>¿Está seguro que desea eliminar al siguiente usuario?</p>
                        <label for="cedulaUsuarioModal" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-center cross-center">Cédula:</label>
                        <input type="text" id="cedulaUsuarioModal" name="cedulaUsuarioModal" readonly class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center" readonly/>
                        <label for="nombreUsuarioModal" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Nombres y Apellidos:</label>
                        <input type="text" id="nombreUsuarioModal" name="nombreUsuarioModal" class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center" readonly/>
                                
                    </div>
                <!-- fin body -->
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="eliminarModalBtn">Eliminar</button>
              </div>
            </div>
          </div>
        </div>
        <!-- Fin Modal -->
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-6">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid ">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo2 mt-2">USUARIOS</p>
                        <form class="container p-2 formulario pt-3 mt-4 needs-validation"  method="POST" action="../usuario?accion=IngresarUsuario" id="frmAddUsuarioCompras" novalidate  accept-charset="UTF-8">
                            <div class="form-row">
                                <input type="hidden" name="tipou" id="tipou" value="10">
                                <input type="hidden" name="agu" id="agu" value="<%=IntIdAreaGestion%>">
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
                                <input type="hidden" value="1" name="selectEstadoU" id="selectEstadoU">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="nombreUsuario" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Título:</label>
                                        <input type="text" id="tituloUsuario" name="tituloUsuario" placeholder="Ingresar solo las siglas ejemplo: ING., ARQ., LIC., DR." class="col-12 col-xs-12 col-md-8 justify-content-center justify-content-md-end cross-center"/>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center">
                                        <label for="areasGestion" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end ">Unidad:</label>
                                        <select class="selectpicker my-select" multiple data-width="67%" data-live-search="true" name="ag" id="ag">
                                            <%
                                                ResultSet rs2 = adAreaGestion.listaAreaGestion();
                                                while (rs2.next()) {
                                            %>
                                            <option title="<%=rs2.getString("ag_alias")%>" value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <button class="btn bton" type="submit">GUARDAR</button>
                        </form>
                        
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm tabla">
                            <thead class="table-azul">
                                <tr>
                                    <th>CÉDULA </th>
                                    <th>NOMBRE</th>
                                    <th>UNIDADES</th>
                                    <th>ACCIÓN</th>
                                </tr>
                            </thead>
                            <tbody id="listaUsuarios">
                            </tbody>
                        </table>
                        
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsUsuarios.js" type="text/javascript"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

        <!-- (Optional) Latest compiled and minified JavaScript translation files -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/i18n/defaults-*.min.js"></script>
        <script type="text/javascript">
            $('.my-select').selectpicker();
        </script>
    </body>
</html>
