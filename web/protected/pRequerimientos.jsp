<%-- 
    Document   : pFormulacion
    Created on : 07-oct-2019, 13:50:35
    Author     : Erika ArÃ©valo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="poa.acceso.adAreaGestion"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@page import="poa.acceso.adTecho"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <link rel="stylesheet" href="../css/jquery-ui.css">
    <link rel="stylesheet" href="../css/dataTables.bootstrap4.min.css">
    <body>
        <!-- MODAL DEVOLVER-->
        <div class="modal fade" id="devolverModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Modificar Requerimientos</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="reqinputenvia" name="reqinputenvia"/>
                        <input type="hidden" id="reqenviaproy" name="reqenviaproy"/>
                        <div class="card">
                            <div class="card-body"  id="inputEnviarReq" >

                            </div>
                        </div>

                        <textarea class="form-control" rows="5" id="reqtextareaenvia">
                            
                        </textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn bton" id="devolverModalBton">GUARDAR</button>
                        <button type="button" class="btn bton" data-dismiss="modal">CANCELAR</button>
                    </div>
                </div>
            </div>
        </div>
        <!--FIN DEVOLVER -->
        <div class="cross-center main-center d-none" id="loader" style="background: rgba(0,0,0,.1); z-index: 1000; position: absolute; width: 100%; min-height: 100vh;">
            <div class="spinner-border text-info" style="width: 5rem; height: 5rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <%@include file="plantillas/header.jsp" %> 
        <div class="container-fluid p-0 main">
            <div class="row mt-3">
                <%@include file="plantillas/menu.jsp" %> 
                <div class="content col-11 container-fluid ">
                    <div class="tab-content ml-5 mr-5 pestania">
                        <p class="titulo" id="titulo">Escuela Superior Politécnica de Chimborazo</p>
                        <input type="hidden" name="tipoAg" id="tipoAg" value="<%=intIdTipoAreaGestion%>">
                        <input type="hidden" name="areaPadre" id="areaPadre" value="<%=IntIdAreaGestion%>">
                        <input type="hidden" name="tipoUsuario" id="tipoUsuario" value="<%=intIdTipoUsuario%>">
                        <input type="hidden" name="selectoranio" id="selectoranio" value="<%=intAnio%>">
                        <input type="hidden" value="<%=strCedulaUsuario%>" name="usuarioenviar" id="usuarioenviar">
                    </div>
                </div>

                <div class="content col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12 mb-3">
                    <div class="row main-center">
                        <input type="hidden" value="<%=request.getParameter("tipo")%>" id="tipoarg">
                        <label for="areasGestion" class="col-12 col-xs-12 col-md-1 justify-content-center justify-content-md-end ">Unidad:</label>
                        <select class="selectpicker my-select" data-live-search="true" data-width="67%" name="ag" id="ag">
                            <option value="0">Todos</option>
                            <%
                                ResultSet rs2;
                                if (request.getParameter("tipo").equals("1")) {
                                    rs2 = adAreaGestion.listaAreaGestionFE(intAnio);
                                } else {
                                    rs2 = adAreaGestion.listaAreaGestionUnidadesAdmin(intAnio);
                                }
                                while (rs2.next()) {
                            %>
                            <option title="<%=rs2.getString("ag_nombre")%>" value="<%= rs2.getString("ag_id")%>"><%=rs2.getString("ag_nombre")%></option>
                            <%}%>
                        </select>

                    </div>
                </div>       
                <div class="content col-11 container-fluid contenedorRequerimientos" id="contenedorRequerimientos">

                </div>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <!--<script src="../javascript/jsPerspObjetivo.js" type="text/javascript"></script>-->
        <script src="../js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="../js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="../js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script src="../javascript/jsRequerimientos.js"></script>

    </body>
</html>