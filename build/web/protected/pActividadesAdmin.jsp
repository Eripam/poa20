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
        <div class="modal fade bd-example-modal-xl" id="modificarProy" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">MODIFICAR ACTIVIDAD</h5>
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
                                        <div class="col-12" style="font-weight: bold" id="nombreP"></div>
                                    </div>
                                </div>
                                <div class="row col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3 mesactividad" data-id="1">
                                    <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Programación:</label>
                                    <div class="row col-10 text-justify" id="programacionactividad"></div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <label class="col-12 col-xs-12 col-md-2 justify-content-center justify-content-md-end cross-center">Porcentajes:</label>
                                        <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="pori">
                                            <div class="col-10">
                                                <input class="form-control" type="number" min="0" max="100" id="porcentajei" required>
                                            </div>
                                            <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">I</label>
                                        </div>
                                        <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="porii">
                                            <div class="col-10">
                                                <input class="form-control" type="number" min="0" max="100" id="porcentajeii" required>
                                            </div>
                                            <label for="example-number-input" class="col-2 col-form-label p-0 align-self-center text-center">II</label>
                                        </div>
                                        <div class="form-group row col-6 col-xs-6 col-sm-6 col-md-3 d-none" id="poriii">
                                            <div class="col-10">
                                                <input class="form-control" type="number" min="0" max="100" id="porcentajeiii" required>
                                            </div>
                                            <label class="col-2 col-form-label p-0 align-self-center text-center">III</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                                    <div class="row">
                                        <div class="col-12" style="font-weight: bold">NOTA: Los cuatrimestres que tienen una evaluación o autoevaluación, no se puede modificar los valores.</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalGuardarEstados">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="eliminarProy" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">ELIMINAR ACTIVIDAD</h5>
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
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Observación:</label>
                                        <div class="col-12" style="font-weight: bold"><textarea class="form-control" name="txtObservEl" id="txtObservEl"></textarea></div>
                                    </div>
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">NOTA: Si la actividad fue evaluada o tiene requerimientos no podrá ser eliminada</label>
                                        <div class="col-12" style="font-weight: bold" id="estadoP"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalEliminarEstados">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade bd-example-modal-xl" id="responsableAct" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-xl" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title main-center" id="exampleModalLabel" style="color:#133351">RESPONSABLE ACTIVIDAD</h5>
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
                                        <div class="col-12" style="font-weight: bold" id="nombrePER"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Responsable:</label>
                                        <div class="col-12" style="font-weight: bold"><input class="form-control" name="inpResponsableAc" id="inpResponsableAc"></div>
                                    </div>
                                </div>
                                <div class="col-12 col-xs-12 col-sm-12 col-md-12 mb-3">
                                    <div class="row">
                                        <label for="recipient-name" class="col-form-label" id="codigolbl">Observación:</label>
                                        <div class="col-12" style="font-weight: bold"><textarea class="form-control" name="txtObservElR" id="txtObservElR"></textarea></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="modalResponsableA">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
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
                        <p class="titulo2 mt-2">ACTIVIDADES</p>
                        <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaProyecto" name="cedulaProyecto">
                        <input type="hidden" value="<%=intAnio%>" id="anio" name="anio">
                        <div class="row p-0 m-0">
                            <div class="row col-lg-4 col-12 p-0">
                                <label class="col-4" style="font-weight: bold;">BUSCAR:</label>
                                <input type="text" class="form-control col-8" id="codigoP" placeholder="Ingresar el código del proyecto">
                            </div>
                            <div class="col-lg-3 col-12 p-0">
                                <button class="btn bton" id="buscarCod">BUSCAR POR CÓDIGO PROYECTO</button>      
                            </div>
                        </div>
                        <table class="container-fluid table table-sm mt-5 table-bordered table-hover table-striped  table-responsive-md table-responsive-sm tabla" id="example" style="width:100%">
                            <thead class="table-azul">
                                <tr>
                                    <th>DEPENDENCIA</th>
                                    <th>PROYECTO</th>
                                    <th>COMPONENTE</th>
                                    <th>ACTIVIDAD</th>
                                    <th>RESPONSABLE ACT</th>
                                    <th>MONTO</th>
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
        <script src="../javascript/jsActividadAdm.js" type="text/javascript"></script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
    </body>
</html>
