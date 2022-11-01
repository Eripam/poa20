<%-- 
    Document   : pDeudas
    Created on : 02-ener-2020, 10:50:35
    Author     : Erika Arévalo
--%>
<%@page import="poa.acceso.adRequerimientosGenerales"%>
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
        <!--Enviar deuda-->
        <div class="modal fade" id="enviarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ENVIAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="enviarModalBton">ENVIAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <!--Eliminar-->
        <div class="modal fade" id="eliminarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">ELIMINAR</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="eliminarModalBton">ELIMINAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %>
                <div class="content col-12 container-fluid">
                    <!--<div class="content container-fluid m-3">
                       Tab panes 
                       <div class="container-fluid pestania"><br>-->
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo mb-0"><u>VALORES PENDIENTES DE PAGO</u></p>
                        <p class="row mt-2">
                            <!--<a href="../archivos/listado-comprometidos.xlsx" download="listado-comprometidos.xlsx">Documento Dirección Financiera<i class="fas fa-download"></i></a>-->
                        </p>
                        <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg">
                        <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs">
                        <input type="hidden" value="<%=intIdTipoUsuario%>" id="idTipoUsu">
                        <input type="hidden" id="selectanio" name="selectanio" value="<%=intAnio%>">
                        <button class="btn bton mt-3" id="agregardeuda">AGREGAR VALORES PENDIENTES DE PAGO</button>     
                        <form class="d-none container p-2 formulario pt-3 mt-4 needs-validation"  method="POST" id="frmAddProyecto" novalidate accept-charset="UTF-8">
                            <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgFormulacion" name="idAgFormulacion">
                            <input type="hidden" value="<%=strNombreAreaGestion%>" id="stringNombreAg" name="stringNombreAg">
                            <input type="hidden" value="<%=strAliasAreaGestion%>" id="aliasAgFormulacion" name="aliasAgFormulacion">
                            <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                            <input type="hidden" id="selectaniof" name="selectaniof" value="<%=intAnio%>">
                            <input type="hidden" id="iddeudas" name="iddeudas">
                            <div class="form-row">
                                <div class="row col-12 justify-content-center">
                                    <div class="col-11 mb-3 row p-1" style="border:1px solid #000000; background: #EBF5FB">
                                        <div class="col-1 align-self-center"><i class="fas fa-info-circle fa-2x" style="color: #EC7063"></i></div>
                                        <div class="col-11 text-justify">Por favor, ingresar los procesos que no fueron entregados anticipios (Obligaciones pendientes).</div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row main-center" id="tipoDeudaD">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo:</label>
                                        <div class="form-check col-12 col-xs-12 col-md-3">
                                            <input class="form-check-input" type="radio" id="tipoDeuda" name="tipoDeuda" value="1" checked>
                                            <label class="form-check-label"  style="font-weight: normal;">Obligaciones Pendientes</label>
                                        </div>
                                        <!--<div class="form-check col-12 col-xs-12 col-md-3">
                                            <input class="form-check-input" type="radio" id="tipoDeuda" name="tipoDeuda" value="2">
                                            <label class="form-check-label" style="font-weight: normal;">Comprometidos No Devendagos</label>
                                        </div>-->
                                    </div>
                                </div>
                                <%if (IntIdAreaGestion == 57) {%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 d-none" id="selectAg">
                                    <div class="row main-center">
                                        <label class="col-3 main-end">UNIDAD:</label>
                                        <select class="col-10 col-xs-10 col-md-8 selectpicker p-0 m-0" data-live-search="true" id="tipoun" name="tipoun">
                                            <option selected disabled>Seleccionar...</option>
                                            <%
                                                ResultSet rs2 = adAreaGestion.listaAreaGestionDeudas();
                                                while (rs2.next()) {
                                            %>
                                            <option value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <%}%>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">OEI:</label>
                                        <input class="form-control col-10 col-xs-10 col-md-8" id="nmbOEI" name="nmbOEI" required type="number">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Proyecto:</label>
                                        <textarea class="form-control col-10 col-xs-10 col-md-8" id="textNombreProy" name="textNombreProy" required minlength="1"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre proceso (Req):</label>
                                        <textarea class="form-control col-10 col-xs-10 col-md-8" id="txtNomProceso" name="txtNomProceso" required minlength="2"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center"># contrato u orden compra:</label>
                                        <input type="text" class="form-control col-10 col-xs-10 col-md-8" id="textContrato" name="textContrato" required maxlength="50" pattern="[A-ZÑÁÉÍÓÚ, A-ZÑÁÉÍÓÚ]{1,50}">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo de contratación:</label>
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selectTipoCon" name="selectTipoCon">
                                            <%
                                                ResultSet rs2 = adAreaGestion.listarTipoContratacion();
                                                while (rs2.next()) {
                                            %>
                                            <option value="<%= rs2.getString("tcon_id")%>"><%=rs2.getString("tcon_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Financiamiento:</label>
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selectFin" name="selectFin">
                                            <%
                                                ResultSet rs5 = adRequerimientosGenerales.ListaFinanciamientoSelect();
                                                while (rs5.next()) {
                                            %>
                                            <option value="<%= rs5.getString("financiamiento_id")%>"><%=rs5.getString("financiamiento_nombre")%></option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Tipo Presupuesto:</label>
                                        <select class="selectpicker col-10 col-xs-10 col-md-8 p-0" data-live-search="true" id="selectTipoPres" name="selectTipoPres">
                                            <option value="CORRIENTE">CORRIENTE</option>
                                            <option value="INVERSION">INVERSIÓN</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Requiere IVA?</label>
                                        <div class="form-check col-12 col-xs-12 col-md-3">
                                            <input class="form-check-input" type="radio" id="rIVA" name="rIVA" value="1" checked>
                                            <label class="form-check-label"  style="font-weight: normal;">SI</label>
                                        </div>
                                        <div class="form-check col-12 col-xs-12 col-md-3">
                                            <input class="form-check-input" type="radio" id="rIVA" name="rIVA" value="0">
                                            <label class="form-check-label" style="font-weight: normal;">NO</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="montoCon">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Monto Contrato:</label>
                                        <input class="form-control col-10 col-xs-10 col-md-8" id="montoContrato" name="montoContrato" required maxlength="50" type="number">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Monto Iva:</label>
                                        <input class="form-control col-10 col-xs-10 col-md-8" id="montoIva" name="montoIva" required maxlength="50" type="number"><label style="font-size: 0.7em; color:red">Campo editable según requerimiento</label>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="montoAnt">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Monto Anticipo:</label>
                                        <input class="form-control col-10 col-xs-10 col-md-8" id="montoAnticipo" name="montoAnticipo" required maxlength="50" type="number">
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3 " id="montoPend">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Pendiente de Pago:</label>
                                        <input class="form-control col-10 col-xs-10 col-md-8" readonly id="montoPendiente" name="montoPendiente" required maxlength="50" type="number">
                                    </div>
                                </div>
                            </div>
                            <button class="btn bton" type="submit">GUARDAR</button>
                        </form>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive">
                            <thead class="table-azul">
                                <tr>
                                    <th>OEI</th>
                                    <th>PROYECTO</th>
                                    <th>NOMBRE PROCESO(REQ)</th>
                                    <th># CONTRATO</th>
                                    <th>T. CONTRATACIÓN</th>
                                    <th>FINANCIAMIENTO</th>
                                    <th>TIPO PRESUPUESTO</th>
                                    <th>MONTO CONTR.</th>
                                    <th>MONTO IVA PENDI.</th>
                                    <th>MONTO ANTI.</th>
                                    <th>MONTO PENDI.</th>
                                    <th>TIPO</th>
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
        <script src="../javascript/jsDeudas.js"></script>
    </body>
</html>