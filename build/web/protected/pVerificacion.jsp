<%-- 
    Document   : pWelcome
    Created on : 03-oct-2019, 11:54:38
    Author     : Erika Arévalo
--%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<!DOCTYPE html>
<html>
    <%@include file="plantillas/head.jsp" %> 
    <script src="../js/jquery-3.3.js"></script>
    <body>
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-12 d-block d-xs-block d-sm-none d-md-none d-lg-none d-xl-none">
                    <div class="col-12 main-center">
                        <img src="../img/DPlanificacion.png" alt="Direcciòn de Planificaciòn" class="w-75 plani">
                    </div>
                    <div class="col-12 main-center"><hr class="w-75"></div>
                </div>
                <div class="col-12 d-none d-xs-none d-sm-block d-md-none d-lg-none d-xl-none">
                    <div class="col-12 main-center">
                        <img src="../img/DPlanificacion.png" alt="Direcciòn de Planificaciòn" class="w-50 plani">
                    </div>
                    <div class="col-12 main-center"><hr class="w-50"></div>
                </div>
                <div class="col-12 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block">
                    <div class="col-12 main-center">
                        <img src="../img/DPlanificacion.png" alt="Direcciòn de Planificaciòn" class="w-25 plani">
                    </div>
                    <div class="col-12 main-center"><hr class="w-25"></div>
                </div>
                <div class="col-12 main-center m-2"><h2>Verificación de Usuario</h2></div>
                <div class="col-5 m-5">
                    <div class="progress">
                        <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
                    </div>
                    <div class="texto"></div>
                    <p class="main-center m-3">
                        <%
                            if (request.getUserPrincipal() != null) {
                                AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
                                final Map attributes = principal.getAttributes();
                                if (attributes != null) {
                        %>
                        <b><%=attributes.get("name")%></b> 
                    </p>
                    <p class="main-center"><a href="https://seguridad.espoch.edu.ec/cas/logout?service=https%3A%2F%2Fseguridad.espoch.edu.ec%2Fcas%2Flogin" title="Cerrar sesión">Cerrar Sesión</a></p>
                    <%

                        out.println("<input type='hidden' value='" + attributes.get("cedula") + "' id='cedulausuario'><input type='hidden' value='" + attributes.get("name") + "' id='nombreusuario'>");
                    } else {
                    %>
                    <b>No se encontro ningun usuario</b> 
                    <%}
                        }%>
                </div>
            </div>
        </div>
        <script src="../javascript/jsVerificacion.js" type="text/javascript"></script>
    </body>
</html>
