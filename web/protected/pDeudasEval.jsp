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
        <!-- Modal Evaluación-->
        <div class="modal fade" id="evaluarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Autoevaluación</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="card">
                            <div class="card-body"  id="inputEvaluar" >
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="guardarEvaluacion">GUARDAR</button>
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
                        <form class="d-none container p-2 formulario pt-3 mt-4 needs-validation"  method="POST" id="FrmArchivosEvidencias" novalidate accept-charset="UTF-8">
                            <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgFormulacion" name="idAgFormulacion">
                            <input type="hidden" value="<%=strNombreAreaGestion%>" id="stringNombreAg" name="stringNombreAg">
                            <input type="hidden" value="<%=strAliasAreaGestion%>" id="aliasAgFormulacion" name="aliasAgFormulacion">
                            <input type="hidden" value="<%=cedula%>" id="cedulaProyecto" name="cedulaProyecto">
                            <input type="hidden" id="selectaniof" name="selectaniof" value="<%=intAnio%>">
                            <input type="hidden" id="iddeudas" name="iddeudas">
                            <h4>Evidencias</h4>
                            <div class="form-row">
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6  mb-3">
                                    <div class="row main-center">
                                        <label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Nombre:</label>
                                        <textarea class="form-control col-10 col-xs-10 col-md-8" required id="descarchivo" name="descarchivo"></textarea>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-6 mb-3" id="archivosDeudas">
                                    <div class="row main-center">
                                        <div class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center"><label for="validationCustom01" class="col-12 col-xs-12 col-md-3 justify-content-center justify-content-md-end cross-center">Evidencias</label>
                                            <label for="validationCustom01" style="font-size: .75em">(Ingresar archivos de tipo PDF, RAR o ZIP, máximo de 10 mb)</label></div>
                                        <input class="form-control col-10 col-xs-10 col-md-8" id="archivoactividad" name="archivoactividad" required type="file">
                                    </div>
                                </div>
                            </div>
                            <button class="btn bton" type="submit">GUARDAR</button>
                        </form>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped table-responsive p-0">
                            <thead class="table-azul">
                                <tr>
                                    <th>OEI</th>
                                    <th>PROYECTO</th>
                                    <th>NOMBRE PROCESO(REQ)</th>
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
        <script src="../javascript/jsDeudasEval.js"></script>
    </body>
</html>