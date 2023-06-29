<%@page import="poa.acceso.adAreaGestion"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="poa.acceso.adUsuario"%>
<%@page import="poa.clases.cUsuario"%>
<%@include file="plantillas/sesiones.jsp" %> 
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>

<%    String cedula = null;
    if (request.getUserPrincipal() != null) {
        AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
        final Map attributes = principal.getAttributes();

        if (attributes != null) {
            cedula = attributes.get("cedula").toString();
        } else {
            response.sendRedirect("pVerificacion.jsp");
        }
    } else {
        response.sendRedirect("pVerificacion.jsp");
    }
%>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <body>
        <div class="container-fluid mt-4">
            <div class="row justify-content-center">
                <div class="col-12 d-block d-xs-block d-sm-none d-md-none d-lg-none d-xl-none">
                    <div class="col-12 main-center">
                        <img src="../img/Logos/logo2.png" alt="Direcciòn de Planificaciòn" style="width: 100px">
                        <img src="../img/DPlanificacion2.png" alt="Direcciòn de Planificaciòn" class="w-75">
                    </div>
                </div>
                <div class="col-12 d-none d-xs-none d-sm-block d-md-none d-lg-none d-xl-none">
                    <div class="col-12 main-center">
                        <img src="../img/Logos/logo2.png" alt="Direcciòn de Planificaciòn" style="width: 100px">
                        <img src="../img/DPlanificacion2.png" alt="Direcciòn de Planificaciòn" class="w-50">
                    </div>
                </div>
                <div class="col-12 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block">
                    <div class="col-12 main-center">
                        <img src="../img/Logos/logo2.png" alt="Direcciòn de Planificaciòn" style="width: 100px">
                        <img src="../img/DPlanificacion2.png" alt="Direcciòn de Planificaciòn" class="w-20">
                    </div>
                </div>
                <div class="col-12 main-center m-4">
                    <%  if (request.getUserPrincipal() != null) {
                            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
                            final Map attributes = principal.getAttributes();
                            if (attributes != null) {
                    %>
                    <h3>BIENVENIDO/A <%=attributes.get("name")%></h3>
                    <%      }
                        }%>
                </div>
                <i class="fas fa-user fa-2x" class="p-0 m-0 align-self-center justify-content-center nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i>
                <div class="dropdown-menu dropdown-menu-large row mr-6 mr-sm-8 mr-md-7">
                    <%
                        List<cUsuario> lstTiposUsuario = new ArrayList<cUsuario>();
                        adUsuario objAccesoUsuario = new adUsuario();
                        lstTiposUsuario = objAccesoUsuario.ListaTiposUsuarioLogin(cedula);
                        if (lstTiposUsuario.isEmpty()) {
                            response.sendRedirect("pVerificacion.jsp");
                        }
                        for (cUsuario objLogin : lstTiposUsuario) {
                            if (lstTiposUsuario.size() == 1) {
                    %>
                    <a href="#" class="dropdown-item"><%=objLogin.getTu_nombre()%> - <%=objLogin.getAg().getAg_alias()%></a>
                    <%
                    } else {
                    %>
                    <a class="dropdown-item" href="../usuario?accion=Redirigir&idTipoUsuario=<%=objLogin.getTu_id()%>&nombreTipoUsuario=<%=objLogin.getTu_nombre()%>&idAreaGestion=<%=objLogin.getAg().getAg_id()%>&nombreAreaGestion=<%=objLogin.getAg().getAg_nombre()%>&aliasAreaGestion=<%=objLogin.getAg().getAg_alias()%>&idTipoAG=<%=objLogin.getAg().getTag_id()%>&nombreTipoAG=<%=objLogin.getAg().getTag_nombre()%>&anioplan=<%=objLogin.getAnio()%>"><%=objLogin.getTu_nombre()%> - <%=objLogin.getAg().getAg_alias()%></a>
                    <%
                            }
                        }

                    %>
                    <a class="dropdown-item" href="../usuario?accion=CerrarSesion" >Cerrar Sesión</a>
                </div>
                <div class="col-12 main-center m-4">
                    <input type="hidden" value="<%=IntIdAreaGestion%>" id="idAgObEs" name="idAgObEs">
                    <input type="hidden" value="<%=intIdTipoAreaGestion%>" id="tipoAg" name="tipoAg">
                    <input type="hidden" value="<%=intIdTipoUsuario%>" id="tipousuario" name="tipousuario">
                    <input type="hidden" value="<%=strNombreTipoUsuario%>" id="Ntipousuario" name="Ntipousuario">
                    <input type="hidden" value="<%=strNombreAreaGestion%>" id="nAreaG" name="nAreaG">
                    <input type="hidden" value="<%=strAliasAreaGestion%>" id="aliasAg" name="aliasAg">
                    <input type="hidden" value="<%=strNombreTipoAreaGestion%>" id="nTipoAg" name="nTipoAg">
                    <input type="hidden" value="<%=strCedulaUsuario%>" id="cedulaUs" name="cedulaUs">
                    <input type="hidden" value="<%=intAnio%>" id="anioplan" name="anioplan">
                    <select name="selAnio" id="selAnio" class="col-4 col-md-3 col-lg-2 col-xl-2 form-control">
                        <%
                            ResultSet rs2 = adAreaGestion.listaTechosF();
                            while (rs2.next()) {
                        %>
                        <option value="<%= rs2.getString("ti_fecha")%>"><%=rs2.getString("ti_fecha")%></option>
                        <%}%>
                    </select>
                </div>
                <%if (intIdTipoUsuario == 24) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techo"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoU"><i class="fas fa-money-check-alt"></i></span><p class="col-10 p-0 m-0">LISTA TECHOS PRESUPUESTARIOS UNIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariosAdmin"><i class="fas fa-user"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="objetivoE"><i class="fas fa-network-wired"></i></span><p class="col-10 p-0 m-0">PLAN ESTRATÉGICO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="proyectoAdmin"><i class="fas fa-file-alt"></i></span><p class="col-10 p-0 m-0">ESTADOS PROYECTOS FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="proyectoAdminEval"><i class="fas fa-file-alt"></i></span><p class="col-10 p-0 m-0">ESTADOS PROYECTOS EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="actividadesAdmin"><i class="fas fa-clipboard-list"></i></span><p class="col-10 p-0 m-0">REPRORAMACIÓN DE ACTIVIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reqReformas"><i class="fas fa-clipboard-list"></i></span><p class="col-10 p-0 m-0">LISTAR REFORMAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudasLR"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">REFORMAS VALORES PENDIENTES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="activarProy"><i class="fas fa-calendar-check"></i></span><p class="col-10 p-0 m-0">ACTIVAR FECHAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustAdm"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudasL"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">LISTA OBLIGACIONES PENDIENTES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="Lsalvaguardar"><i class="fas fa-lock"></i></span><p class="col-10 p-0 m-0">SALVAGUARDAR</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesAdmin"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techo"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-check-alt fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA TECHOS PRESUPUESTARIOS UNIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariosAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="objetivoE"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-network-wired fa-3x"></i></span><p class="col-12 p-0 m-0">PLAN ESTRATÉGICO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="proyectoAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-alt fa-3x"></i></span><p class="col-12 p-0 m-0">ESTADOS PROYECTOS FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="proyectoAdminEval"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-alt fa-3x"></i></span><p class="col-12 p-0 m-0">ESTADOS PROYECTOS EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="actividadesAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">REPROGRAMACIÓN DE ACTIVIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reqReformas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">LISTAR REFORMAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudasLR"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">REFORMAS VALORES PENDIENTES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="activarProy"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-calendar-check fa-3x"></i></span><p class="col-12 p-0 m-0">ACTIVAR FECHAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustAdm"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudasL"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA OBLIGACIONES PENDIENTES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="Lsalvaguardar"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-lock fa-3x"></i></span><p class="col-12 p-0 m-0">SALVAGUARDAR</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                <%}else if (intIdTipoUsuario == 25) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariosAdmin"><i class="fas fa-user"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="proyectoAdminEval"><i class="fas fa-file-alt"></i></span><p class="col-10 p-0 m-0">ESTADOS PROYECTOS EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="actividadesAdmin"><i class="fas fa-clipboard-list"></i></span><p class="col-10 p-0 m-0">REPRORAMACIÓN DE ACTIVIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="activarProy"><i class="fas fa-calendar-check"></i></span><p class="col-10 p-0 m-0">ACTIVAR FECHAS</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariosAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="proyectoAdminEval"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-alt fa-3x"></i></span><p class="col-12 p-0 m-0">ESTADOS PROYECTOS EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="actividadesAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">REPROGRAMACIÓN DE ACTIVIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="activarProy"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-calendar-check fa-3x"></i></span><p class="col-12 p-0 m-0">ACTIVAR FECHAS</p></div>
                <%}else if (intIdTipoUsuario == 1) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoU"><i class="fas fa-money-check-alt"></i></span><p class="col-10 p-0 m-0">LISTA TECHOS PRESUPUESTARIOS UNIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosU"><i class="fas fa-list-ul"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reqReformas"><i class="fas fa-clipboard-list"></i></span><p class="col-10 p-0 m-0">LISTAR REFORMAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudasL"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">LISTA OBLIGACIONES PENDIENTES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="Lsalvaguardar"><i class="fas fa-lock"></i></span><p class="col-10 p-0 m-0">SALVAGUARDAR</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesAdmin"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-check-alt fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA TECHOS PRESUPUESTARIOS UNIDADES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosU"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosF"><i class="fas fa-list-ul fa-3x"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reqReformas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">LISTAR REFORMAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudasL"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA OBLIGACIONES PENDIENTES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="Lsalvaguardar"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-lock fa-3x"></i></span><p class="col-12 p-0 m-0">SALVAGUARDAR</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 2) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoPlanificadora"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariodir"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoPlanificadora"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariodir"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 5 && IntIdAreaGestion != 57 && IntIdAreaGestion != 68 && IntIdAreaGestion != 54 && IntIdAreaGestion != 60 && IntIdAreaGestion != 69) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoPlanificadora"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariodir"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJust"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                        <%if (IntIdAreaGestion == 46) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="srvProf"><i class="fas fa-user-check fa-3x"></i></span><p class="col-10 p-0 m-0">SERVICIOS PROFESIONALES DISPONIBILIDAD</p></div>
                        <%}%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingCust"><i class="fas fa-user-plus fa-3x"></i></span><p class="col-10 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="evalDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoPlanificadora"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariodir"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                        <%if (IntIdAreaGestion == 46) {%>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="srvProf"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-check fa-3x"></i></i></span><p class="col-12 p-0 m-0">SERVICIOS PROFESIONALES DISPONIBILIDAD</p></div>
                        <%}%>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingCust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-plus fa-3x"></i></span><p class="col-12 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="evalDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 5 && IntIdAreaGestion == 57) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoPlanificadora"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariodir"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJust"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustU"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingCust"><i class="fas fa-user-plus fa-3x"></i></span><p class="col-10 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="evalDeudas"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoPlanificadora"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariodir"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingCust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-plus fa-3x"></i></i></span><p class="col-12 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="evalDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 5 && (IntIdAreaGestion == 68 || IntIdAreaGestion == 54 || IntIdAreaGestion == 60 || IntIdAreaGestion == 69)) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoPlanificadora"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariodir"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-clipboard-list"></i></span><p class="col-10 p-0 m-0">REQUERIMIENTOS GENERALES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJust"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustU"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingCust"><i class="fas fa-user-plus fa-3x"></i></span><p class="col-10 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="evalDeudas"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoPlanificadora"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariodir"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarReq"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">REQUERIMIENTOS GENERALES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingCust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-plus fa-3x"></i></i></span><p class="col-12 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="evalDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 3 && IntIdAreaGestion != 68) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoPlanificadora"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJust"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingCust"><i class="fas fa-user-plus fa-3x"></i></span><p class="col-10 p-0 m-0">CUSTODIOS</p></div>
                        <%if (IntIdAreaGestion == 46) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="srvProf"><i class="fas fa-user-check fa-3x"></i></span><p class="col-10 p-0 m-0">SERVICIOS PROFESIONALES</p></div>
                        <%}%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="evalDeudas"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoPlanificadora"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingCust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-plus fa-3x"></i></i></span><p class="col-12 p-0 m-0">CUSTODIOS</p></div>
                        <%if (IntIdAreaGestion == 46) {%>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="srvProf"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-check fa-3x"></i></i></span><p class="col-12 p-0 m-0">SERVICIOS PROFESIONALES</p></div>
                        <%}%>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="evalDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 3 && IntIdAreaGestion == 68) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-clipboard-list"></i></span><p class="col-10 p-0 m-0">REQUERIMIENTOS GENERALES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJust"><i class="fas fa-shopping-cart"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustU"><i class="fas fa-shopping-cart"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingCust"><i class="fas fa-user-plus fa-3x"></i></span><p class="col-10 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="evalDeudas"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarReq"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">REQUERIMIENTOS GENERALES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingCust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-plus fa-3x"></i></i></span><p class="col-12 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="evalDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 7 || intIdTipoUsuario == 8) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoPlanificadora"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariosIDV"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS UNIDAD POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS REVISIÓN POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJust"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingCust"><i class="fas fa-user-plus fa-3x"></i></span><p class="col-10 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacionF"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN FACULTADES Y SEDES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoPlanificadora"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariosIDV"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS UNIDAD POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS REVISIÓN POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingCust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-user-plus fa-3x"></i></i></span><p class="col-12 p-0 m-0">CUSTODIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacionF"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN FACULTADES Y SEDES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 9) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariosCompras"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="requerimientosCompras"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">LISTA DE REQUERIMIENTOS FACULTADES Y EXTENSIONES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="requerimientosComprasUni"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">LISTA DE REQUERIMIENTOS UNIDADES ADMINISTRATIVAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustC"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesAdmin"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariosCompras"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="requerimientosCompras"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA DE REQUERIMIENTOS FACULTADES Y EXTENSIONES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="requerimientosComprasUni"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA DE REQUERIMIENTOS UNIDADES ADMINISTRATIVAS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesAdmin"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 10) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="requerimientosComprasU"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">LISTA DE REQUERIMIENTOS</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="requerimientosComprasU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA DE REQUERIMIENTOS</p></div>
                        <%} else if (intIdTipoUsuario == 4) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoDecano"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariodir"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoDecano"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariodir"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 15) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-list-ul"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosF"><i class="fas fa-list-ul fa-3x"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                        <%} else if (intIdTipoUsuario == 11) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosU"><i class="fas fa-list-ul"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosU"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosF"><i class="fas fa-list-ul fa-3x"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                        <%} else if (intIdTipoUsuario == 14) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacionF"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacionVP"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacionF"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacionVP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 16 || intIdTipoUsuario == 17) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosU"><i class="fas fa-list-ul"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacionF"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN FACULTADES Y SEDES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosU"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosF"><i class="fas fa-list-ul fa-3x"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacionF"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN FACULTADES Y SEDES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 18) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-list-ul"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosF"><i class="fas fa-list-ul fa-3x"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                        <%} else if (intIdTipoUsuario == 19) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="techoDecano"><i class="fas fa-money-bill-alt"></i></span><p class="col-10 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="formulacionP"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="usuariodir"><i class="fas fa-users"></i></span><p class="col-10 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosC"><i class="fas fa-file-invoice-dollar"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-hand-holding-usd"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJust"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaEvaluacion"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="evalDeudas"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-10 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="techoDecano"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-money-bill-alt fa-3x"></i></span><p class="col-12 p-0 m-0">TECHOS PRESUPUESTARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="formulacionP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">FORMULACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="usuariodir"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">USUARIOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-file-invoice-dollar fa-3x"></i></span><p class="col-12 p-0 m-0">LISTA PROYECTOS POA</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-hand-holding-usd fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJust"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIDAD</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaEvaluacion"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="evalDeudas"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-check fa-3x"></i></span><p class="col-12 p-0 m-0">EVALUACIÓN VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                        <%} else if (intIdTipoUsuario == 20) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustC"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustUC"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS</p></div>        
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustUC"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>        
                        <%} else if (intIdTipoUsuario == 21) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="ingresarDeudas"><i class="fas fa-clipboard-list"></i></span><p class="col-10 p-0 m-0">REQUERIMIENTOS GENERALES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaReqUnif"><i class="fas fa-shopping-cart"></i></span><p class="col-10 p-0 m-0">REQUERIMIENTOS A UNIFICAR</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustU"><i class="fas fa-shopping-cart"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="ingresarReq"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">REQUERIMIENTOS GENERALES</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaReqUnif"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">REQUERIMIENTOS A UNIFICAR</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-shopping-cart fa-3x"></i></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                        <%} else if (intIdTipoUsuario == 22) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosU"><i class="fas fa-list-ul"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustCAE"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaJustUCU"><i class="fas fa-shopping-cart fa-3x"></i></span><p class="col-10 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNPE"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCNE"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosU"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosF"><i class="fas fa-list-ul fa-3x"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div> 
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustCAE"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS</p></div>        
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaJustUCU"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-users fa-3x"></i></span><p class="col-12 p-0 m-0">JUSTIFICATIVOS UNIFICADOS</p></div>         
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNPE"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCNE"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                        <%} else if (intIdTipoUsuario == 23) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaReqEnv"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">REQUERIMIENTOS ENVIADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaReqEnvO"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">VALORES PENDIENTES DE PAGO ENVIADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaNP"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaOPCN"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="servProfDispo"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-10 p-0 m-0">SERVICIOS PROFESIONALES DE DISPONIBILIDAD</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaReqEnv"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">REQUERIMIENTOS ENVIADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaReqEnvO"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">VALORES PENDIENTES DE PAGO ENVIADOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaNP"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD NO PAC</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaOPCN"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SOLICITUD VALORES PENDIENTES DE PAGO</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="servProfDispo"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-clipboard-list fa-3x"></i></span><p class="col-12 p-0 m-0">SERVICIOS PROFESIONALES DE DISPONIBILIDAD</p></div>
                  <%} else if (intIdTipoUsuario == 26) {%>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosU"><i class="fas fa-list-ul"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>
                <div class="row col-9 col-xs-9 col-sm-5 d-md-none d-lg-none d-xl-none botonmain"><span class="col-2 p-0 m-0 align-self-center" id="reportesUsu"><i class="fas fa-chart-line"></i></span><p class="col-10 p-0 m-0">REPORTES</p></div>

                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="listaProyectosU"><span class="col-2 p-0 m-0 align-self-center" id="listaProyectosF"><i class="fas fa-list-ul fa-3x"></i></span><p class="col-10 p-0 m-0">LISTA PROYECTOS</p></div>       
                <div class="row col-9 col-xs-9 col-sm-5 col-md-3 col-lg-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block botonmainr" id="reportesUsu"><span class="col-12 p-0 m-0 align-self-center"><i class="fas fa-chart-line fa-3x"></i></span><p class="col-12 p-0 m-0">REPORTES</p></div>  
                <%}%>
            </div>
        </div>
        <%@include file="plantillas/footer.jsp" %>
        <script>
            $('.row').on('click', '#techo', function () {
                window.location = "pTechos.jsp";
            });

            $('.row').on('click', '#techoPlanificadora', function () {
                window.location = "pTechosFacultad.jsp";
            });
            $('.row').on('click', '#formulacionP', function () {
                window.location = "pFormulacion.jsp";
            });

            $('.row').on('click', '#listaProyectosF', function () {
                window.location = "pListaProyectos.jsp";
            });

            $('.row').on('click', '#usuariosCompras', function () {
                window.location = "pUsuarios.jsp";
            });
            $('.row').on('click', '#techoDecano', function () {
                window.location = "pTechosDecano.jsp";
            });

            $('.row').on('click', '#requerimientosCompras', function () {
                window.location = "pRequerimientos.jsp?tipo=1";
            });

            $('.row').on('click', '#requerimientosComprasUni', function () {
                window.location = "pRequerimientos.jsp?tipo=2";
            });

            $('.row').on('click', '#requerimientosComprasU', function () {
                window.location = "pRequerimientosAnalista.jsp";
            });

            $('.row').on('click', '#usuariodir', function () {
                window.location = "pUsuariosDir.jsp";
            });

            $('.row').on('click', '#listaProyectosC', function () {
                window.location = "pListaProyectos.jsp";
            });

            $('.row').on('click', '#listaProyectosU', function () {
                window.location = "pListaProyectosU.jsp";
            });

            $('.row').on('click', '#usuariosIDV', function () {
                window.location = "pUsuariosIDV.jsp";
            });
            $('.row').on('click', '#ingresarDeudas', function () {
                window.location = "pDeudas.jsp";
            });
            $('.row').on('click', '#ingresarDeudasL', function () {
                window.location = "pDeudasL.jsp";
            });
            $('.row').on('click', '#ingresarDeudasLR', function () {
                window.location = "pDeudasReformas.jsp";
            });
            $('.row').on('click', '#reportesAdmin', function () {
                window.location = "pReportesAdmin.jsp";
            });
            $('.row').on('click', '#reportesUsu', function () {
                window.location = "pReportesUsu.jsp";
            });
            $('.row').on('click', '#listaJust', function () {
                window.location = "pListarJ.jsp";
            });
            $('.row').on('click', '#listaNP', function () {
                window.location = "pListarNP.jsp";
            });
            $('.row').on('click', '#listaOPCN', function () {
                window.location = "pListarOPC.jsp";
            });
            $('.row').on('click', '#listaOPCNE', function () {
                window.location = "pListarOPCE.jsp";
            });
            $('.row').on('click', '#listaJustU', function () {
                window.location = "pListarJUnif.jsp";
            });
            $('.row').on('click', '#listaJustC', function () {
                window.location = "pListarJC.jsp";
            });
            $('.row').on('click', '#listaJustUC', function () {
                window.location = "pListarJCU.jsp";
            });
            $('.row').on('click', '#ingresarReq', function () {
                window.location = "pRequerimientosGenerales.jsp";
            });
            $('.row').on('click', '#listaReqUnif', function () {
                window.location = "pGenerarJU.jsp";
            });
            $('.row').on('click', '#listaJustCAE', function () {
                window.location = "pListarJCAE.jsp";
            });
            $('.row').on('click', '#listaJustUCU', function () {
                window.location = "pListarJCAEU.jsp";
            });
            $('.row').on('click', '#ingCust', function () {
                window.location = "pCustodios.jsp";
            });
            $('.row').on('click', '#Lsalvaguardar', function () {
                window.location = "pListaReqS.jsp";
            });
            $('.row').on('click', '#listaReqEnv', function () {
                window.location = "pGenerarNPTH.jsp";
            });
            $('.row').on('click', '#listaReqEnvO', function () {
                window.location = "pGenerarOBTH.jsp";
            });
            $('.row').on('click', '#listaEvaluacion', function () {
                window.location = "pListaProyectosEvaluacion.jsp";
            });
            $('.row').on('click', '#evalDeudas', function () {
                window.location = "pDeudasEval.jsp";
            });
            $('.row').on('click', '#listaEvaluacionF', function () {
                window.location = "pListaProyectosEvaluacionL.jsp";
            });
            $('.row').on('click', '#listaEvaluacionVP', function () {
                window.location = "pDeudasEvalL.jsp";
            });
            $('.row').on('click', '#listaNPE', function () {
                window.location = "pListarSolE.jsp";
            });
            $('.row').on('click', '#techoU', function () {
                window.location = "pListaTechoU.jsp";
            });
            $('.row').on('click', '#srvProf', function () {
                window.location = "pListaServP.jsp";
            });
            $('.row').on('click', '#servProfDispo', function () {
                window.location = "pListaServPTH.jsp";
            });
            $('.row').on('click', '#usuariosAdmin', function () {
                window.location = "pUsuariosAdmin.jsp";
            });
            $('.row').on('click', '#proyectoAdmin', function () {
                window.location = "pProyectosAdmin.jsp";
            });
            $('.row').on('click', '#proyectoAdminEval', function () {
                window.location = "pProyectosAdminEv.jsp";
            });
            $('.row').on('click', '#actividadesAdmin', function () {
                window.location = "pActividadesAdmin.jsp";
            });
            $('.row').on('click', '#reqReformas', function () {
                window.location = "pReqReformas.jsp";
            });
            $('.row').on('click', '#activarProy', function () {
                window.location = "pActivarFechas.jsp";
            });
            $('.row').on('click', '#objetivoE', function () {
                window.location = "pObjetivos.jsp";
            });
            $('.row').on('click', '#listaJustAdm', function () {
                window.location = "pListaJusAdm.jsp";
            });

            $("#selAnio option[value=" + $('#anioplan').val() + "]").attr("selected", true);
            $('#selAnio').change(function () {
                var idAgObEs = $('#idAgObEs').val(), tipoAg = $('#tipoAg').val(), tipousuario = $('#tipousuario').val(), Ntipousuario = $('#Ntipousuario').val(),
                        nAreaG = $('#nAreaG').val(), aliasAg = $('#aliasAg').val(), nTipoAg = $('#nTipoAg').val();
                window.location.href = "../usuario?accion=Redirigir&idTipoUsuario=" + tipousuario + "&nombreTipoUsuario=" + Ntipousuario + "&idAreaGestion=" + idAgObEs + "&nombreAreaGestion=" + nAreaG + "&aliasAreaGestion=" + aliasAg + "&idTipoAG=" + tipoAg + "&nombreTipoAG=" + nTipoAg + "&anioplan=" + $(this).val();
            });
        </script>
    </body>
</html>
