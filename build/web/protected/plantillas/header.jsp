<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poa.acceso.adUsuario"%>
<%@page import="poa.clases.cUsuario"%>
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

<%
    String cedula = null;
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

<header class="container-fluid p-2">
    <div class="row">
        <div class="col-2 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block col-md-1 col-lg-1 col-xl-1 align-self-center align-items-center">
            <img src="../img/Logos/logo.png" alt="Logo Espoch" id="imagenespoch" style="height: 50px">
        </div>
        <div class="row col-12 col-xs-12 col-sm-12 col-md-10 col-lg-10 col-xl-10">
            <p class="col-12 align-self-end font-weight-bold m-0">SISTEMA INSTITUCIONAL DE PLANIFICACIÓN</p>
            <p class="col-12 align-self-start font-weight-bold m-0"><%=strNombreAreaGestion%></p>
        </div>
        <div class="col-1 d-none d-xs-none d-sm-none d-md-block d-lg-block d-xl-block col-md-1 col-lg-1 col-xl-1 nav-item dropdown dropdown-menu-large">
            <div class="p-0 m-0 align-self-center justify-content-center nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user-circle fa-3x"></i></div>
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
                <a class="dropdown-item" href="../usuario?accion=Redirigir&idTipoUsuario=<%=objLogin.getTu_id()%>&nombreTipoUsuario=<%=objLogin.getTu_nombre()%>&idAreaGestion=<%=objLogin.getAg().getAg_id()%>&nombreAreaGestion=<%=objLogin.getAg().getAg_nombre()%>&aliasAreaGestion=<%=objLogin.getAg().getAg_alias()%>&idTipoAG=<%=objLogin.getAg().getTag_id()%>&nombreTipoAG=<%=objLogin.getAg().getTag_nombre()%>&anioplan=<%=objLogin.getAnio()%>&agestado=<%=objLogin.getAg().getAg_estado()%>"><%=objLogin.getTu_nombre()%> - <%=objLogin.getAg().getAg_alias()%></a>
                <%
                        }
                    }

                %>
                <a class="dropdown-item" href="../usuario?accion=CerrarSesion" >Cerrar Sesión</a>
            </div>
        </div>
    </div>
</header>
