
<div class="menuicono pl-2 ml-3 iconomen" ><i class="fas fa-align-justify fa-2x p-0 m-0 iconomen" ></i><p class="pl-2 mb-0 align-self-center iconomen">MENÚ</p></div>
<div class="menuprueba align-items-center cross-center mt-7" style="display: none">
    <br><br>
    <div class="main-end mb-2" id="cerrarM"><i class="fas fa-times fa-2x"></i></div>
    <p class="text-center h5 tituloM mb-3"><%=strNombreTipoUsuario%>-<%=strAliasAreaGestion%></p>
    <ul class="menuUl">
        <%if (intIdTipoUsuario == 1) {%>
        <li><a href="pTechos.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="pListaProyectosU.jsp"><label class="h6">Listar Proyectos</label></a></li>
        <li><a href="pReportesAdmin.jsp"><label class="h6">Reportes</label></a></li>
            <% } else if (intIdTipoUsuario == 2) {%>
        <li><a href="pTechosFacultad.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulacion</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <li><a href="pUsuariosDir.jsp"><label class="h6">Usuarios</label></a></li>
            <% } else if (intIdTipoUsuario == 5 && IntIdAreaGestion != 57 && IntIdAreaGestion != 68 && IntIdAreaGestion != 54 && IntIdAreaGestion != 60 && IntIdAreaGestion != 69) {%>
        <li><a href="pTechosFacultad.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulacion</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos</a></li>
                <li><a href="pDeudas.jsp">Obligaciones Pendientes</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Ejecución</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pGenerarJ.jsp">Requerimientos Unidad PAC</a></li>
                <li><a href="pListarJ.jsp">Lista Justificativos Unidad</a></li>
                <li><a href="pGenerarNP.jsp">Requerimientos NO PAC</a></li>
                <li><a href="pListarNP.jsp">Lista Solicitud NO PAC</a></li>
                 <li><a href="pGenerarOPC.jsp">Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pListarOPC.jsp">Lista Solicitud Obligaciones P y Comprometidos N.D.</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <li><a href="pUsuariosDir.jsp"><label class="h6">Usuarios</label></a></li>
            <% } else if (intIdTipoUsuario == 5 && IntIdAreaGestion == 57) {%>
        <li><a href="pTechosFacultad.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulacion</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos</a></li>
                <li><a href="pDeudas.jsp">Obligaciones Pendientes</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Ejecución</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pGenerarJ.jsp">Requerimientos Unidad</a></li>
                <li><a href="pListarJ.jsp">Lista Justificativos Unidad</a></li>
                <li><a href="pGenerarNP.jsp">Requerimientos NO PAC</a></li>
                <li><a href="pListarNP.jsp">Lista Solicitud NO PAC</a></li>
                <li><a href="pGenerarOPC.jsp">Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pListarOPC.jsp">Lista Solicitud Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pGenerarJU.jsp">Requerimientos a Unificar</a></li>
                <li><a href="pGenerarRU.jsp">Lista Requerimientos Unificados</a></li>
                <li><a href="pListarJUnif.jsp">Lista Justificativos Unificados</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <li><a href="pUsuariosDir.jsp"><label class="h6">Usuarios</label></a></li>
            <% } else if (intIdTipoUsuario == 5 && (IntIdAreaGestion == 68 || IntIdAreaGestion == 54 || IntIdAreaGestion == 60 || IntIdAreaGestion == 69)) {%>
        <li><a href="pTechosFacultad.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos</a></li>
                <li><a href="pDeudas.jsp">Obligaciones Pendientes</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Ejecución</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pGenerarJ.jsp">Requerimientos Unidad</a></li>
                <li><a href="pListarJ.jsp">Lista Justificativos Unidad</a></li>
                <li><a href="pGenerarNP.jsp">Requerimientos NO PAC</a></li>
                <li><a href="pListarNP.jsp">Lista Solicitud NO PAC</a></li>
                <li><a href="pGenerarOPC.jsp">Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pListarOPC.jsp">Lista Solicitud Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pGenerarJU.jsp">Requerimientos a Unificar</a></li>
                <li><a href="pGenerarRU.jsp">Lista Requerimientos Unificados</a></li>
                <li><a href="pListarJUnif.jsp">Lista Justificativos Unificados</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <li><a href="pUsuariosDir.jsp"><label class="h6">Usuarios</label></a></li>
            <% } else if (intIdTipoUsuario == 3 && IntIdAreaGestion != 68) {%>
        <li><a href="pTechosFacultad.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulacion</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pDeudas.jsp">Obligaciones Pendientes</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos POA</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Ejecución</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pGenerarJ.jsp">Generar Justificativo</a></li>
                <li><a href="pListarJ.jsp">Listar Justificativos</a></li>
                <li><a href="pGenerarNP.jsp">Requerimientos NO PAC</a></li>
                <li><a href="pListarNP.jsp">Lista Solicitud NO PAC</a></li>
                <li><a href="pGenerarOPC.jsp">Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pListarOPC.jsp">Lista Solicitud Obligaciones P y Comprometidos N.D.</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <% } else if (intIdTipoUsuario == 3 && IntIdAreaGestion == 68) {%>
        <li><a href="#"><label class="h6">Formulacion</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pDeudas.jsp">Obligaciones Pendientes</a></li>
                <li><a href="pRequerimientosGenerales.jsp">Requerimientos Generales</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos POA</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Ejecución</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pGenerarJ.jsp">Requerimientos Unidad</a></li>
                <li><a href="pListarJ.jsp">Lista Justificativos Unidad</a></li>
                <li><a href="pGenerarNP.jsp">Requerimientos NO PAC</a></li>
                <li><a href="pListarNP.jsp">Lista Solicitud NO PAC</a></li>
                <li><a href="pGenerarOPC.jsp">Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pListarOPC.jsp">Lista Solicitud Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pGenerarJU.jsp">Requerimientos a Unificar</a></li>
                <li><a href="pGenerarRU.jsp">Lista Requerimientos Unificados</a></li>
                <li><a href="pListarJUnif.jsp">Lista Justificativos Unificados</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <%} else if (intIdTipoUsuario == 4) {%>
        <li><a href="pTechosDecano.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos POA</a></li>
                <!--<li><a href="pRequerimientosGenerales.jsp">Lista Proyectos Multidisciplinarios</a></li>-->
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <li><a href="pUsuariosIDV.jsp"><label class="h6">Usuarios</label></a></li>
        <%} else if (intIdTipoUsuario == 7 || intIdTipoUsuario == 8) {%>
        <li><a href="pTechosFacultad.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulacion</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos POA</a></li>
                <li><a href="pListaProyectosU.jsp">Lista Proyectos Unidades</a></li>
                <li><a href="pDeudas.jsp">Obligaciones Pendientes</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Ejecución</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pGenerarJ.jsp">Requerimientos Unidad</a></li>
                <li><a href="pListarJ.jsp">Lista Justificativos Unidad</a></li>
                <li><a href="pGenerarNP.jsp">Requerimientos NO PAC</a></li>
                <li><a href="pListarNP.jsp">Lista Solicitud NO PAC</a></li>
                <li><a href="pGenerarOPC.jsp">Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pListarOPC.jsp">Lista Solicitud Obligaciones P y Comprometidos N.D.</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <li><a href="pUsuariosIDV.jsp"><label class="h6">Usuarios</label></a></li>
            <%} else if (intIdTipoUsuario == 9) {%>
        <li><a href="pUsuarios.jsp"><label class="h6">Usuarios</label></a></li>
        <li><a href="pRequerimientos.jsp?tipo=1"><label class="h6">Lista Requerimientos Facultades y Extensiones</label></a></li>
        <li><a href="pRequerimientos.jsp?tipo=2"><label class="h6">Lista Requerimientos Unidades Administrativas</label></a></li>
        <li><a href="pListarJC.jsp"><label class="h6">Lista Justificativos</label></a></li>
            <%} else if (intIdTipoUsuario == 10) {%>
        <li><a href="pRequerimientosAnalista.jsp"><label class="h6">Lista Requerimientos</label></a></li>
            <%} else if (intIdTipoUsuario == 15) {%>
        <li><a href="#"><label class="h6">Formulación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos POA</a></li>
                <!--<li><a href="pRequerimientosGenerales.jsp">Lista Proyectos Multidisciplinarios</a></li>-->
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <%} else if (intIdTipoUsuario == 11 || intIdTipoUsuario == 16 || intIdTipoUsuario == 17) {%>
        <li><a href="pListaProyectosU.jsp"><label class="h6">Lista Proyectos POA</label></a></li>
            <%} else if (intIdTipoUsuario == 18) {%>
        <li><a href="#"><label class="h6">Formulación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos POA</a></li>
                <!--<li><a href="pRequerimientosGenerales.jsp">Lista Proyectos Multidisciplinarios</a></li>-->
            </ul>
        </li>
        <%} else if (intIdTipoUsuario == 19) {%>
        <li><a href="pTechosDecano.jsp"><label class="h6">Techo Presupuestario</label></a></li>
        <li><a href="#"><label class="h6">Formulación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pFormulacion.jsp">Formulación Proyectos</a></li>
                <li><a href="pListaProyectos.jsp">Lista Proyectos POA</a></li>
                <li><a href="pListaProyectosU.jsp">Lista Proyectos Institucionales POA</a></li>
                <li><a href="pDeudas.jsp">Obligaciones Pendientes</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Ejecución</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pGenerarJ.jsp">Requerimientos Unidad</a></li>
                <li><a href="pListarJ.jsp">Lista Justificativos Unidad</a></li>   
                <li><a href="pGenerarNP.jsp">Requerimientos NO PAC</a></li>
                <li><a href="pListarNP.jsp">Lista Solicitud NO PAC</a></li>
                <li><a href="pGenerarOPC.jsp">Obligaciones P y Comprometidos N.D.</a></li>
                <li><a href="pListarOPC.jsp">Lista Solicitud Obligaciones P y Comprometidos N.D.</a></li>
            </ul>
        </li>
        <li><a href="#"><label class="h6">Evaluación</label><i class="fas fa-angle-down"></i></a>
            <ul class="submenuUl">
                <li><a href="pListaProyectosEvaluacion.jsp">Lista Proyectos</a></li>
            </ul>
        </li>
        <li><a href="pUsuariosDir.jsp"><label class="h6">Usuarios</label></a></li>
            <%} else if (intIdTipoUsuario == 20) {%>
        <li><a href="pListarJC.jsp"><label class="h6">Lista Justificativos</label></a></li>
        <li><a href="pRequerimientosAnalista.jsp"><label class="h6">Lista Requerimientos</label></a></li>
            <%} else if (intIdTipoUsuario == 21) {%>
        <li><a href="pGenerarJU.jsp">Requerimientos a Unificar</a></li>
        <li><a href="pGenerarRU.jsp">Lista Requerimientos Unificados</a></li>
            <%} else if (intIdTipoUsuario == 22) {%>
        <li><a href="pListaProyectos.jsp">Listado de Proyectos</a></li>
        <li><a href="pListarJCAE.jsp">Lista Justificativos</a></li>
        <li><a href="pListarJCAEU.jsp">Lista Justificativos Unificados</a></li>
            <%} else if (intIdTipoUsuario == 23) {%>
        <li><a href="pGenerarNPTH.jsp">Listado de Requerimientos Enviados</a></li>
        <li><a href="pListarNP.jsp">Lista Solicitudes</a></li>
            <%}%>
    </ul>
</div>