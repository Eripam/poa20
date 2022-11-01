<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>SIPLAN</title>
    <link rel="stylesheet" href="css/login.css"/>
  </head>
  <body class="login">
    <header class="header">
      <div class="header__content">
          <div class="header__content_item btn-login"><a href="protected/pVerificacion.jsp"><img src="img/btn-login.png" alt="login"/></a></div>
      </div>
        <%
            if (null == session) {
                session.invalidate();
            }
        %>
    </header>
  </body>
  <main class="main">
    <div class="main__content">
      <h1 class="main__content_item title">SISTEMA INSTITUCIONAL DE PLANIFICACIÓN - SIPLAN</h1>
      <div class="main__content_item logo"><img src="img/logo-poapac.png" alt="poa pac"/></div>
      <div class="main__content_item welcome">Bienvenido(a)</div>
    </div>
  </main>
  <footer class="footer">
    <div class="footer__content">
      <div class="footer__content_item logo"><a href="https://www.espoch.edu.ec" target="_blank"> <img class="img-espoch" src="img/espoch color.svg" alt="ESPOCH"/></a></div>
      <div class="footer__content_item logo"><a href="https://www.espoch.edu.ec/index.php/component/k2/item/869.html" target="_blank"> <img class="img-planificacion" src="img/DPlanificacion.png" alt="Planificación ESPOCH"/></a></div>
    </div>
  </footer>
</html>