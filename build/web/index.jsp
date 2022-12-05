<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<!--<html lang="en">
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
<%/*
    if (null == session) {
        session.invalidate();
    }*/
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
</html>-->
<!DOCTYPE html>
<html lang="es" dir="ltr" prefix="content: http://purl.org/rss/1.0/modules/content/  dc: http://purl.org/dc/terms/  foaf: http://xmlns.com/foaf/0.1/  og: http://ogp.me/ns#  rdfs: http://www.w3.org/2000/01/rdf-schema#  schema: http://schema.org/  sioc: http://rdfs.org/sioc/ns#  sioct: http://rdfs.org/sioc/types#  skos: http://www.w3.org/2004/02/skos/core#  xsd: http://www.w3.org/2001/XMLSchema# ">
    <head>
        <meta charset="utf-8" />
        <meta name="title" content="SIPLAN | ESPOCH" />
        <meta name="Generator" content="Drupal 8 (https://www.drupal.org)" />
        <meta name="MobileOptimized" content="width" />
        <meta name="HandheldFriendly" content="true" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="SIPLAN, Escuela Superior Polit�cnica de Chimborazo." />
        <link rel="shortcut icon" href="dtic/fnherstal/favicon.ico" type="image/vnd.microsoft.icon" />
        <title>SIPLAN | ESPOCH</title>
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/ajax-progress.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/align.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/autocomplete-loading.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/fieldgroup.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/container-inline.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/clearfix.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/details.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/hidden.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/item-list.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/js.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/nowrap.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/position-container.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/progress.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/reset-appearance.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/resize.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/sticky-header.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/system-status-counterd9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/system-status-report-countersd9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/system-status-report-general-infod9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/tabledrag.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/tablesort.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/core/modules/system/css/components/tree-child.moduled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/modules/bbw/homeslider/css/masterslider/style/mastersliderd9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/modules/bbw/homeslider/css/masterslider/skins/default/styled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/modules/simple_megamenu/css/styled9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.css" media="all" />
        <link rel="stylesheet" href="dtic/maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" media="all" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Abel|Oswald:300,400,600|Advent+Pro:300,400,500" media="all" />
        <link rel="stylesheet" href="dtic/cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" media="all" />
        <link rel="stylesheet" href="dtic/fnherstal/css/maind9aa.css?pox64u" media="all" />
        <link rel="stylesheet" href="dtic/fnherstal/css/libsd9aa.css?pox64u" media="all" />



    </head>
    <body>
        <div class="dialog-off-canvas-main-canvas" data-off-canvas-main-canvas>
            <div id="preloader" class="">
                <div class="bar"></div>
                <div id="status"></div>
            </div>
            <div class="wrapper clearfix">
                <div class="headerfull scrollnav">
                    <a href="pres.jsp" class="brand-logo" title="ESPOCH Logo"><img src="dtic/images/icons/logo_espoch.png" class="img-responsive" alt="ESPOCH"></a>
                    <span class="alt-logo"></span>
                </div>
                <header>
                    <div>
                        <div id="block-homesliderblock">
                            <div class="header" id="header">
                                <!-- masterslider -->
                                <a href="protected/pVerificacion.jsp" class="btn btn-cta"> Iniciar</a>
                                <div class="master-slider ms-skin-default" id="masterslider">
                                    <!-- new slide -->
                                    <div class="ms-slide" data-delay="5">
                                        <!-- slide background -->
                                        <img class="img-slider" src="dtic/images/1.jpg" data-src="dtic/images/1.jpg"/>

                                        <div class="line-title ms-layer"></div>

                                        <div class="ms-layer ms-layer-mask container-title">
                                            <h2 class="title-slider">SISTEMA INSTITUCIONAL DE PLANIFICACIÓN</h2>
                                        </div>
                                        <div class="ms-layer ms-layer-mask container-title2">
                                            <h2 class="title-slider">SIPLAN</h2>
                                        </div>
                                    </div>
                                    <div class="ms-slide" data-delay="5">
                                        <!-- slide background -->
                                        <img class="img-slider" src="dtic/images/2.jpg" data-src="dtic/images/2.jpg" alt="Notificaciones ESPOCH"/>
                                        <div class="line-title ms-layer"></div>
                                        <div class="ms-layer ms-layer-mask container-title">
                                            <h2 class="title-slider">MANTENGA SIEMPRE ACTUALIZADO</h2>
                                        </div>
                                        <div class="ms-layer ms-layer-mask container-title2">
                                            <h2 class="title-slider">SU NAVEGADOR WEB</h2>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </header>
                <!-- PAGE CONTENT //////////////////////// -->
                <main id="page-content" class="page-content">
                    <div id="legal-banner" class="legal-banner banner-overlay hidden">
                        <div class="overlay-container col-md-6 col-sm-12 col-xs-12">
                            <div class="overlay-content center relative">
                                <div class="head circle center">
                                    <img src="dtic/images/icons/espoch.png" alt="Home" >
                                </div>
                                <div class="row">
                                    <h2 class="title">BIENVENIDOS</h2>
                                    <p class="col-md-8 col-sm-8 col-xs-8 col-md-offset-2 center">La Dirección de Planificación le da la más cordial bienvenida a su sitio de gestión de proyectos de nuestra Escuela Superior Politécnica de Chimborazo, también ofrecemos un mejor servicio e información en cuanto a ejecución y evaluación de proyectos POA.</p>
                                </div>
                                <a href="#0" id="legal-accept" class="btn">Continuar.</a>
                            </div>
                        </div>
                        <div class="overlay-alt"></div>
                    </div>
                </main>
                <footer>
                    <div id="fn-cookie-bar" class="alert text-center no-pad hidden" style="background-color: #CE2017;">
                        <p>Escuela Superior Politécnica de Chimborazo © 2022 </p>
                    </div>
                </footer>
            </div>
        </div>
        <script type="application/json" data-drupal-selector="drupal-settings-json">{"path":{"baseUrl":"\/","scriptPath":null,"pathPrefix":"en\/","currentPath":"home","currentPathIsAdmin":false,"isFront":true,"currentLanguage":"es"},"pluralDelimiter":"\u0003","google_analytics":{"trackOutbound":true,"trackMailto":true,"trackDownload":true,"trackDownloadExtensions":"7z|aac|arc|arj|asf|asx|avi|bin|csv|doc(x|m)?|dot(x|m)?|exe|flv|gif|gz|gzip|hqx|jar|jpe?g|js|mp(2|3|4|e?g)|mov(ie)?|msi|msp|pdf|phps|png|ppt(x|m)?|pot(x|m)?|pps(x|m)?|ppam|sld(x|m)?|thmx|qtm?|ra(m|r)?|sea|sit|tar|tgz|torrent|txt|wav|wma|wmv|wpd|xls(x|m|b)?|xlt(x|m)|xlam|xml|z|zip"},"user":{"uid":0,"permissionsHash":"d151406359417331b856022ff2a5d26fbfe46e9e83635c561444d33b6c145c8b"}}</script>
        <script src="dtic/core/assets/vendor/domready/ready.min7016.js?v=1.0.8"></script>
        <script src="dtic/core/assets/vendor/jquery/jquery.minf77b.js?v=3.2.1"></script>
        <script src="dtic/modules/bbw/homeslider/js/jquery-1.12.1.min4f96.js?v=1.12.1"></script>
        <script src="dtic/core/misc/drupalSettingsLoader92f9.js?v=8.6.13"></script>
        <script src="dtic/sites/default/files/languages/en_P2T9yVvPVSyv4Gs2mmhVE9l4vOe2SLozeAF6FvX0EIEd9aa.js?pox64u"></script>
        <script src="dtic/core/misc/drupal92f9.js?v=8.6.13"></script>
        <script src="dtic/core/misc/drupal.init92f9.js?v=8.6.13"></script>
        <script src="dtic/modules/contrib/google_analytics/js/google_analytics92f9.js?v=8.6.13"></script>
        <script src="dtic/maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="dtic/fnherstal/js/vendor/jarallax.min92f9.js?v=8.6.13"></script>
        <script src="dtic/fnherstal/js/main92f9.js?v=8.6.13"></script>
        <script src="dtic/fnherstal/js/init92f9.js?v=8.6.13"></script>
        <script src="dtic/fnherstal/js/function92f9.js?v=8.6.13"></script>
        <script src="dtic/modules/bbw/homeslider/js/masterslider/masterslider.min92f9.js?v=8.6.13"></script>
        <script src="dtic/modules/bbw/homeslider/js/home92f9.js?v=8.6.13"></script>
        <script src="dtic/modules/simple_megamenu/js/simple_megamenud9aa.js?pox64u"></script>
        <script src="dtic/fnherstal/js/dtic.js"></script>
    </body>
    <!-- Mirrored from www.fnherstal.com/ by HTTrack Website Copier/3.x [XR&CO'2014], Tue, 26 Mar 2019 16:20:29 GMT -->
</html>