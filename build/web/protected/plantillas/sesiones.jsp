<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession sesionOk = request.getSession();
    Integer intIdTipoUsuario = null;
    String strNombreTipoUsuario = null;
    Integer IntIdAreaGestion = null;
    String strNombreAreaGestion = null;
    String strAliasAreaGestion = null;
    Integer intIdTipoAreaGestion = null;
    String strNombreTipoAreaGestion = null;
    String strCedulaUsuario = null;
    Integer intAnio = null;
    Integer intAgEstado= null;
    //ArrayList<cLogin> tipos = null;
    if (sesionOk == null) {
        response.sendRedirect("../index.jsp");
    } else if (sesionOk.getAttribute("idTipoUsuario") == null) {
        response.sendRedirect("../index.jsp");
    } else {
        intIdTipoUsuario = (Integer) sesionOk.getAttribute("idTipoUsuario");
        strNombreTipoUsuario = (String) sesionOk.getAttribute("nombreTipoUsuario");
        IntIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
        strNombreAreaGestion = (String) sesionOk.getAttribute("nombreAreaGestion");
        strAliasAreaGestion = (String) sesionOk.getAttribute("aliasAreaGestion");
        intIdTipoAreaGestion = (Integer) sesionOk.getAttribute("idTipoAreaGestion");
        strNombreTipoAreaGestion = (String) sesionOk.getAttribute("nombreTipoAreaGestion");
        strCedulaUsuario = (String) sesionOk.getAttribute("cedulaUsuario");
        intAnio = (Integer) sesionOk.getAttribute("anioplan");
        intAgEstado = (Integer) sesionOk.getAttribute("agestado");
                
        sesionOk.setAttribute("idTipoUsuario", intIdTipoUsuario);
        sesionOk.setAttribute("nombreTipoUsuario", strNombreTipoUsuario);
        sesionOk.setAttribute("idAreaGestion", IntIdAreaGestion);
        sesionOk.setAttribute("nombreAreaGestion", strNombreAreaGestion);
        sesionOk.setAttribute("aliasAreaGestion", strAliasAreaGestion);
        sesionOk.setAttribute("idTipoAreaGestion", intIdTipoAreaGestion);
        sesionOk.setAttribute("nombreTipoAreaGestion", strNombreTipoAreaGestion);
        sesionOk.setAttribute("cedulaUsuario", strCedulaUsuario);
        sesionOk.setAttribute("anioplan", intAnio);
        sesionOk.setAttribute("agestado", intAgEstado);
    }
%>