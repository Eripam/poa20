<%-- 
    Document   : pDeudas
    Created on : 02-ener-2020, 10:50:35
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
        <!--Modal aprobar-->
        <div class="modal fade" id="enviarModalVer" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ENVIAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="aprobarRadios">
                                <input type="hidden" name="estadoproy" id="estadoproy">
                                <label class="form-check-label" for="exampleRadios1">Aprobar</label>
                            </div>
                            <div class="form-check col-6">
                                <input class="form-check-input" type="radio" name="verificarRadios" id="modificarRadios">
                                <label class="form-check-label" for="exampleRadios2">Enviar a Modificar</label>
                            </div>
                            <div class="form-group">
                                <label for="message-text" class="col-form-label">Observación:</label>
                                <textarea class="form-control" id="observacionEnviar" placeholder="Debe ingresar observación solo si va a enviar a modificar el proyecto"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarEnviar">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- MODAL Fechas-->
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
                            <div class="card-body"  id="inputEnviarReq" >
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
         <div class="modal fade bd-example-modal-xl" id="eliminarDeuda" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">ELIMINAR ÚLTIMO ESTADO</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form class="modal-body" id="frmIngresarCP">
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <div class="col-12" style="font-weight: bold" id="nombrePE"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Estados:</label>
                                        <div class="col-12" style="font-weight: bold" id="estadoP"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalEliminarEstados">ELIMINAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
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
                        <p class="titulo mb-0"><u>VALORES PENDIENTES DE PAGO</u></p>
                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs">
                        <input type="hidden" value="<%=intIdTipoUsuario%>" id="idTipoUsu">
                        <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" id="selectaniof" name="selectaniof" value="<%=intAnio%>">
                        <div class="row align-content-center align-items-center align-self-center">
                            <div class="col-3">
                                <label>Unidad:</label>
                                <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" data-selected-text-format="count > 6" id="selectareas" name="selectareas">
                                    <option disabled selected>Seleccionar..</option>
                                    <%if (intIdTipoUsuario != 11) {%>
                                    <option value="0">Todos</option>
                                    <%}
                                        ResultSet rs = adAreaGestion.listaAreaGestionDeudas(intAnio);
                                        while (rs.next()) {
                                    %>
                                    <option title="<%=rs.getString("ag_alias")%>" value="<%= rs.getString("ag_id")%>"><%=rs.getString("ag_nombre")%></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive">
                            <thead class="table-azul">
                                <tr>
                                    <th>OEI</th>
                                    <th>PROYECTO</th>
                                    <th>NOMBRE PROCESO(REQ)</th>
                                    <th># CONTRATO</th>
                                    <th>T. CONTRATACIÓN</th>
                                    <th>FINANCIAMIENTO</th>
                                    <th>TIPO PRESUPUESTO</th>
                                    <th>TIPO</th>
                                    <th>MONTO CONTR.</th>
                                    <th>MONTO IVA PEND</th>
                                    <th>MONTO ANTICIPO</th>
                                    <th>MONTO PENDIENTE</th>
                                    <th>ESTADO</th>
                                    <th>ACCIÓN</th>
                                </tr>
                            </thead>
                            <tbody id="proyectosForm">
                            </tbody>
                        </table>
                        <script>
                            // Example starter JavaScript for disabling form submissions if there are invalid fields
                            (function () {
                                'use strict';
                                window.addEventListener('load', function () {
                                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                    var forms = document.getElementsByClassName('needs-validation');
                                    // Loop over them and prevent submission
                                    var validation = Array.prototype.filter.call(forms, function (form) {
                                        form.addEventListener('submit', function (event) {
                                            if (form.checkValidity() === false) {
                                                event.preventDefault();
                                                event.stopPropagation();
                                            } else {
                                                // alert('validado');
                                            }
                                            form.classList.add('was-validated');
                                        }, false);
                                    });
                                }, false);
                            })();
                        </script>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../javascript/jsDeudasL.js"></script>
    </body>
</html>