/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import poa.acceso.adActividadRequerimiento;
import poa.acceso.adProyecto;
import static poa.acceso.adProyecto.obtenerProyectoPorId;
import poa.acceso.adTecho;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cActividadRequerimiento;
import poa.clases.cProcesoAcciones;
import poa.clases.cProyecto;
import poa.clases.cRequerimientosGenerales;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
@MultipartConfig
public class servProyecto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            HttpSession sesionOk = request.getSession(false);
            String strCedula = (String) sesionOk.getAttribute("cedulaUsuario");
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            Integer intTipoUsuario = (Integer) sesionOk.getAttribute("idTipoUsuario");
            switch (strAccion) {
                case "ListarFecha":
                    ListarFecha(request, response);
                    break;
                case "ListarProyecto":
                    ListarProyecto(request, response);
                    break;
                case "ListarProyectoCom":
                    ListarProyectoCom(request, response);
                    break;
                case "ListarProyectoComEv":
                    ListarProyectoComEv(request, response);
                    break;
                case "ListarActividadProceso":
                    ListarActividadProceso(request, response);
                    break;
                case "ListarProyectoMul":
                    ListarProyectoMul(request, response);
                    break;
                case "ListarDeudas":
                    ListarDeudas(request, response);
                    break;
                case "ListarDeudasEval":
                    ListarDeudasEval(request, response);
                    break;
                case "ListarDeudasEvalL":
                    ListarDeudasEvalL(request, response);
                    break;
                case "ListarDeudasL":
                    ListarDeudasL(request, response);
                    break;
                case "ListaActividadesProceso":
                    ListaActividadesProceso(request, response);
                    break;
                case "IngresarProyecto":
                    IngresarProyecto(request, response, intIdAreaGestion);
                    break;
                case "AgregarArticulacion":
                    AgregarArticulacion(request, response, intIdAreaGestion, strCedula, intTipoUsuario);
                    break;
                case "EliminarActividadProceso":
                    EliminarActividadProceso(request, response, intIdAreaGestion, strCedula);
                    break;
                case "ValidarArticulacion":
                    ValidarArticulacion(request, response, intIdAreaGestion, strCedula);
                    break;
                case "ListaProyectoCarreras":
                    ListaProyectoCarreras(request, response);
                    break;
                case "ListaProyectoUnidades":
                    ListaProyectoUnidades(request, response);
                    break;
                case "ListarProyectoCompleto":
                    ListarProyectoCompleto(request, response);
                    break;
                case "ModificarProyecto":
                    ModificarProyecto(request, response, intIdAreaGestion);
                    break;
                case "ModificarProyectoRep":
                    ModificarProyectoRep(request, response, intIdAreaGestion);
                    break;
                case "IngresarProyectoGenerado":
                    IngresarProyectoGenerado(request, response, intIdAreaGestion);
                    break;
                case "ProyectoGenerado":
                    ProyectoGenerado(request, response);
                    break;
                case "EnviarProyecto":
                    EnviarProyecto(request, response);
                    break;
                case "EliminarProyecto":
                    EliminarProyecto(request, response, intIdAreaGestion);
                    break;
                case "VerificaProyecto":
                    VerificaProyecto(request, response);
                    break;
                case "Tiempo":
                    Tiempo(request, response);
                    break;
                case "TiempoE":
                    TiempoE(request, response);
                    break;
                case "VerificacionEnvios":
                    VerificacionEnvios(request, response);
                    break;
                case "VerificacionReprogramado":
                    VerificacionReprogramado(request, response);
                    break;
                case "VerificacionEnviosE":
                    VerificacionEnviosE(request, response);
                    break;
                case "VerificacionEnviosET":
                    VerificacionEnviosET(request, response);
                    break;
                case "VerificacionEnviosEje":
                    VerificacionEnviosEje(request, response);
                    break;
                case "IngresarMontosProy":
                    IngresarMontosProy(request, response);
                    break;
                case "IngresarDeudas":
                    IngresarDeudas(request, response);
                    break;
                case "ModificarDeudas":
                    ModificarDeudas(request, response);
                    break;
                case "ListarFechasDeudas":
                    ListarFechasDeudas(request, response);
                    break;
                case "ListarFechasDeudasEval":
                    ListarFechasDeudasEval(request, response);
                    break;
                case "ActividadPresupuestaria":
                    ActividadPresupuestaria(request, response);
                    break;
                case "EnviarDeuda":
                    EnviarDeuda(request, response);
                    break;
                case "EnviarDeudaEval":
                    EnviarDeudaEval(request, response);
                    break;
                case "ListaProyectoEvaluacion":
                    ListaProyectoEvaluacion(request, response);
                    break;
                case "ListaProyectoEvaluacionL":
                    ListaProyectoEvaluacionL(request, response);
                    break;
                case "ListarProyectoCompEvaluacion":
                    ListarProyectoCompEvaluacion(request, response);
                    break;
                case "ListaEstadoAsignacion":
                    ListaEstadoAsignacion(request, response);
                    break;
                case "EliminarDeudas":
                    EliminarDeudas(request, response, intIdAreaGestion);
                    break;
                case "EliminarDeudasEvidencia":
                    EliminarDeudasEvidencia(request, response, intIdAreaGestion);
                    break;
                case "IngresarEstado":
                    IngresarEstado(request, response);
                    break;
                case "ResponsableProy":
                    ResponsableProy(request, response);
                    break;
                case "EliminarEstado":
                    EliminarEstado(request, response);
                    break;
                case "EliminarEstadoDeuda":
                    EliminarEstadoDeuda(request, response);
                    break;
                case "IngresarEstadoEv":
                    IngresarEstadoEv(request, response);
                    break;
                case "EliminarEstadoEv":
                    EliminarEstadoEv(request, response);
                    break;
                case "ModificarDeudasRef":
                    ModificarDeudasRef(request, response);
                    break;
                case "ModificarDeudasRefEst":
                    ModificarDeudasRefEst(request, response);
                    break;
                case "IngresarDeudasRefEst":
                    IngresarDeudasRefEst(request, response);
                    break;
                case "EliminarEstructuraD":
                    EliminarEstructuraD(request, response);
                    break;
                case "EjecutarFuncionMontos":
                    EjecutarFuncionMontos(request, response);
                    break;
            }
        }
    }

    private void IngresarProyecto(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException, ServletException {
        String actpres = request.getParameter("idActividadPres");
        String nombre = request.getParameter("textNombre");
        String fin = request.getParameter("textFinP");
        String proposito = request.getParameter("textProposito");
        String fi = request.getParameter("textFechaI");
        String ff = request.getParameter("textFechaF");
        String responsable = request.getParameter("textResponsable");
        String integrantes[] = request.getParameterValues("textIntegrantes[]");
        String oei = request.getParameter("idOEIForm");
        String alias = request.getParameter("aliasAgFormulacion");
        String ag = request.getParameter("idAgFormulacion");
        String servicio = request.getParameter("proyServicio");
        String autcar = request.getParameter("selectProcesoCar");
        String autins = request.getParameter("selectProceso");
        String proceso = request.getParameter("selectProceso");
        String evalins[] = request.getParameterValues("accmejins[]");
        String actividades[] = request.getParameterValues("selectAcciones[]");
        String eval[] = request.getParameterValues("accmejca[]");
        String multi[] = request.getParameterValues("selectAgM");
        String coe[] = request.getParameterValues("selectAgC");
        String strCedula = request.getParameter("cedulaProyecto");
        String anio = request.getParameter("txtanio");

        cProyecto oProy = new cProyecto();
        cProcesoAcciones cPa = new cProcesoAcciones();
        adProyecto aProy = new adProyecto();
        Integer proye = aProy.codigoSiguienteProy();

        Part filePart = request.getPart("filePerfil");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String img = proye + "-" + fileName;

        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String result = null;
        if (actpres.isEmpty()) {
            result = "Debe seleccionar la actividad presuuestaria.";
        } else if ((oei.equals("2") || oei.equals("3")) && fileName.equals("")) {
            result = "Debe ingresar el perfil del proyecto.";
        } else if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del proyecto.";
        } else if (fin.isEmpty()) {
            result = "Debe ingresar el fin del proyecto.";
        } else if (proposito.isEmpty()) {
            result = "Debe ingresar el proposito del proyecto.";
        } else if (fi.isEmpty()) {
            result = "Debe ingresar la fecha de inicio del proyecto.";
        } else if (ff.isEmpty()) {
            result = "Debe ingresar la fecha de fin del proyecto.";
        } else if (responsable.isEmpty()) {
            result = "Debe ingresar el responsable del proyecto.";
        } else {
            oProy.setProyecto_id(proye);
            oProy.setProyecto_nombre(nombre);
            oProy.setProyecto_proposito(proposito);
            oProy.setProyecto_fin(fin);
            oProy.setProyecto_ap(Integer.parseInt(actpres));
            oProy.setProyecto_codigo(alias + "_0" + oei + "_" + proye);
            oProy.setProyecto_fi(fi);
            oProy.setProyecto_ff(ff);
            oProy.setTi_fecha(Integer.parseInt(anio));
            oProy.setProyecto_responsable(responsable);
            if (servicio == null) {
                oProy.setProyecto_servicio(0);
            } else {
                oProy.setProyecto_servicio(Integer.parseInt(servicio));
            }

            if (fileName.equals("")) {
                oProy.setProyecto_doc("vacio");
            } else {
                String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "docs";
                File uploads = new File(path); //Carpeta donde se guardan los archivos
                uploads.mkdirs(); //Crea los directorios necesarios
                File file = new File(uploads, proye + "-" + fileName);
                oProy.setProyecto_doc(img);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            if (multi == null && coe == null) {
                oProy.setProyecto_multi(false);
                if ((integrantes.length == 0 || (integrantes.length==1 && integrantes[0].isEmpty())) && (oei.equals("2") || oei.equals("3"))) {
                    result = "Debe ingresar los integrantes del proyecto.";
                } else {
                    result = aProy.IngresarProyecto(oProy, Integer.parseInt(ag));
                }
            } else if (coe == null && multi != null) {
                if (multi[0].equals("0")) {
                    oProy.setProyecto_multi(false);
                    if (integrantes.length == 0 || (integrantes.length==1 && integrantes[0].isEmpty()) && (oei.equals("2") || oei.equals("3"))) {
                        result = "Debe ingresar los integrantes del proyecto.";
                    } else {
                        result = aProy.IngresarProyecto(oProy, Integer.parseInt(ag));
                    }
                } else {
                    oProy.setProyecto_multi(true);
                    if (integrantes.length == 0 || (integrantes.length==1 && integrantes[0].isEmpty())) {
                        result = "Debe ingresar los integrantes del proyecto.";
                    } else {
                        result = aProy.IngresarProyecto(oProy, Integer.parseInt(ag));
                        if (result.equals("Correcto")) {
                            for (String proy_are1 : multi) {
                                oProy.setProyecto_id(proye);
                                oProy.setProyecto_ag(Integer.parseInt(proy_are1));
                                result = aProy.IngresarAreasProyecto(oProy);
                            }
                        }
                    }
                }
            } else if (multi == null && coe != null) {
                if (coe[0].equals("0")) {
                    oProy.setProyecto_multi(false);
                    result = aProy.IngresarProyecto(oProy, Integer.parseInt(ag));
                } else {
                    oProy.setProyecto_multi(true);
                    result = aProy.IngresarProyecto(oProy, Integer.parseInt(ag));
                    if (result.equals("Correcto")) {
                        for (String proy_are1 : coe) {
                            oProy.setProyecto_id(proye);
                            oProy.setProyecto_ag(Integer.parseInt(proy_are1));
                            result = aProy.IngresarAreasProyecto(oProy);
                        }
                    }
                }
            }
        }
        if (result.equals("Correcto")) {
            if (Integer.parseInt(anio) < 2022) {
                if (eval != null && eval.length > 0) {
                    for (String eval1 : eval) {
                        oProy.setProyecto_id(proye);
                        cPa.setProceso_codigo(autcar);
                        cPa.setAm_nombre(eval1);
                        cPa.setAm_id(aProy.codigoSiguienteAcciones().toString());
                        oProy.setProac(cPa);
                        if (!eval1.isEmpty()) {
                            result = aProy.IngresarAcciones(oProy);
                        }
                    }
                }
                if (evalins.length > 0) {
                    for (String evalins1 : evalins) {
                        oProy.setProyecto_id(proye);
                        cPa.setProceso_codigo(autins);
                        cPa.setAm_nombre(evalins1);
                        cPa.setAm_id(aProy.codigoSiguienteAcciones().toString());
                        oProy.setProac(cPa);
                        if (!evalins1.isEmpty()) {
                            result = aProy.IngresarAcciones(oProy);
                        }
                    }
                }
            } else {
                Integer sum = 0;
                if (proceso != null) {
                    if (actividades != null && actividades.length > 0) {
                        for (String act : actividades) {
                            oProy.setProyecto_proceso(proceso);
                            oProy.setProyecto_acciones(act);
                            oProy.setProyecto_id(proye);

                            if (aProy.ValidarAcciones(oProy)) {
                                sum++;
                            } else {
                                result = aProy.IngresarAccionesCarreraProyecto(oProy, false);
                            }

                            if (result.equals("Correcto")) {
                                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                                objTransaccion.setTransaccion_descripcion("Al proyecto con código: \"" + proye + "\" se ingresó correctamente la actividad \"" + act + "\" de articulación de aseguramiento.");
                                ingresarTransaccion(objTransaccion);
                            }
                        }
                    } else if (proceso.equals("0")) {
                        result = aProy.EliminarAcciones(proye);
                        if (result.equals("Correcto")) {
                            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                            objTransaccion.setTransaccion_descripcion("Al proyecto con código: \"" + proye + "\" se eliminó correctamente las actividades de articulación de aseguramiento.");
                            ingresarTransaccion(objTransaccion);
                        }
                    } else {
                        result = "Debe seleccionar actividades";
                    }
                }

                if (sum > 0) {
                    result = "No se ingresaron " + sum + ", porque ya fueron ingresadas";
                }
            }
            if (integrantes.length > 0) {
                for (String integrante1 : integrantes) {
                    oProy.setProyecto_id(proye);
                    oProy.setProyecto_integrantes(integrante1);
                    if (!integrante1.isEmpty()) {
                        result = aProy.IngresarIntegrantes(oProy);
                    }
                }
            }
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_cedula(strCedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(1);
            objTransaccion.setTransaccion_descripcion("El proyecto con código: \"" + oProy.getProyecto_codigo() + "\" se ingresó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarProyectoGenerado(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException, ServletException {
        String actpres = request.getParameter("idActividadPres");
        String oei = request.getParameter("idOEIForm");
        String alias = request.getParameter("aliasAgFormulacion");
        String ag = request.getParameter("idAgFormulacion");
        String strCedula = request.getParameter("cedulaProyecto");
        String agnombre = request.getParameter("agnombre");
        String anio = request.getParameter("anio");

        cProyecto oProy = new cProyecto();
        cProcesoAcciones cPa = new cProcesoAcciones();
        adProyecto aProy = new adProyecto();
        Integer proye = aProy.codigoSiguienteProy();

        String result = null;
        if (actpres.isEmpty()) {
            result = "Debe seleccionar la actividad presuuestaria.";
        } else {
            oProy.setProyecto_id(proye);
            oProy.setProyecto_nombre("FORTALECIMIENTO DE LA GESTIÓN DE " + agnombre + " DE LA ESPOCH.");
            oProy.setProyecto_proposito("FORTALECER LA GESTIÓN ADMINISTRATIVA INSTITUCIONAL DE LA ESPOCH.");
            oProy.setProyecto_fin("EL FORTALECIMIENTO DE LA GESTIÓN A TRAVÉS DE LA EJECUCIÓN DE LOS PROCESOS Y FUNCIONES CORRESPONDIENTES A " + agnombre);
            oProy.setProyecto_ap(Integer.parseInt(actpres));
            oProy.setProyecto_codigo(alias + "_0" + oei + "_" + proye);
            oProy.setProyecto_fi(anio + "-01-01");
            oProy.setProyecto_ff(anio + "-12-31");
            oProy.setProyecto_responsable("DIRECTOR/A DE " + agnombre);
            oProy.setProyecto_ag(Integer.parseInt(ag));
            oProy.setProyecto_multi(false);
            oProy.setProyecto_anio(Integer.parseInt(anio));

            result = aProy.IngresarProyectoGenerado(oProy);

        }
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_cedula(strCedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(1);
            objTransaccion.setTransaccion_descripcion("El proyecto con código: \"" + oProy.getProyecto_codigo() + "\" se ingresó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarFecha(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer result;
        adProyecto oProy = new adProyecto();
        result = oProy.listaFechaTechoIns();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarProyecto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyecto(Integer.parseInt(ag), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarProyectoCom(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoC(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarProyectoComEv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("anio");
        String cuatrimestre = request.getParameter("cuatrimestre");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoCEval(Integer.parseInt(anio), Integer.parseInt(cuatrimestre));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarProyectoMul(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoMulti(Integer.parseInt(ag), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarDeudas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarDeudas(Integer.parseInt(ag), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarDeudasEval(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarDeudasEval(Integer.parseInt(ag), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarDeudasEvalL(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarDeudasEvalL(Integer.parseInt(ag), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarDeudasL(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        if (ag.equals("0")) {
            result = aProy.ListarDeudasLA(Integer.parseInt(anio));
        } else {
            result = aProy.ListarDeudasL(Integer.parseInt(ag), Integer.parseInt(anio));
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaActividadesProceso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proceso = request.getParameter("proceso");
        List<cProcesoAcciones> result = new ArrayList<cProcesoAcciones>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarActividadProceso(proceso);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaProyectoCarreras(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipousuario = request.getParameter("tipousuario");
        String areapadre = request.getParameter("areapadre");
        String area = request.getParameter("area");
        String tipoproyecto = request.getParameter("tipoproyecto");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoAreasTipoU(Integer.parseInt(tipousuario), Integer.parseInt(areapadre), Integer.parseInt(area), Integer.parseInt(tipoproyecto), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaProyectoUnidades(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipousuario = request.getParameter("tipousuario");
        String areapadre = request.getParameter("areapadre");
        String area = request.getParameter("area");
        String tipoproyecto = request.getParameter("tipoproyecto");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoAreasUnidades(Integer.parseInt(tipousuario), Integer.parseInt(areapadre), Integer.parseInt(area), Integer.parseInt(tipoproyecto), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarProyectoCompleto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("proy");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoCompleto(Integer.parseInt(proy));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarActividadProceso(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("proy");
        List<cProcesoAcciones> result = new ArrayList<cProcesoAcciones>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProcesoActividad(Integer.parseInt(proy), 1);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ProyectoGenerado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("area");
        String anio = request.getParameter("anio");
        Boolean result;
        adProyecto aProy = new adProyecto();

        result = aProy.VerificacionProyecto(Integer.parseInt(proy), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void EjecutarFuncionMontos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("anio");
        String result;
        adProyecto aProy = new adProyecto();

        result = aProy.ejecutarActualizarMontos(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarProyecto(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException, ServletException {
//        String obj = request.getParameter("inpObjEstra");
        try {
            String nombre = request.getParameter("nombre-mod");
            String fin = request.getParameter("finp-mod");
            String proposito = request.getParameter("prop-mod");
            String fi = request.getParameter("fini-mod");
            String ff = request.getParameter("ffin-mod");
            String responsable = request.getParameter("res-mod");
            String multi = request.getParameter("ismulti");
            String oei = request.getParameter("objest");
            String oop = request.getParameter("objobj");
            String actp = request.getParameter("actpresup");
            String evalins[] = request.getParameterValues("accmejins[]");
            String eval[] = request.getParameterValues("accmejca[]");
            String strCedula = request.getParameter("cedulaProyecto");
            String integrantes[] = request.getParameterValues("textIntegrantes[]");
            //String tipousuario = request.getParameter("tipoUsuario");
            String proye = request.getParameter("idProy");
            cProyecto objProyectoBuscar = obtenerProyectoPorId(Integer.valueOf(proye));
            cProyecto oProy = new cProyecto();
            adProyecto aProy = new adProyecto();

            Part filePart = request.getPart("permod");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String img = proye + "-" + fileName;

            String result;
            if (nombre.isEmpty()) {
                result = "Debe ingresar el nombre del proyecto.";
            } else if (fin.isEmpty()) {
                result = "Debe ingresar el fin del proyecto.";
            } else if (proposito.isEmpty()) {
                result = "Debe ingresar el proposito del proyecto.";
            } else if (fi.isEmpty()) {
                result = "Debe ingresar la fecha de inicio del proyecto.";
            } else if (ff.isEmpty()) {
                result = "Debe ingresar la fecha de fin del proyecto.";
            } else if (responsable.isEmpty()) {
                result = "Debe ingresar el responsable del proyecto.";
            } else {
                oProy.setProyecto_id(Integer.parseInt(proye));
                oProy.setProyecto_nombre(nombre);
                oProy.setProyecto_proposito(proposito);
                oProy.setProyecto_fin(fin);
                oProy.setProyecto_fi(fi);
                oProy.setProyecto_ff(ff);
                oProy.setProyecto_responsable(responsable);
                oProy.setProyecto_ap(Integer.parseInt(actp));

                if (fileName.equals("")) {
                    oProy.setProyecto_doc("vacio");
                } else {
                    //String arch = "D:/" + request.getParameter("inpmodificarPerfil");
                    String arch = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "docs" + File.separator;
                    File fichero = new File(arch + request.getParameter("inpmodificarPerfil"));
                    fichero.delete();

                    fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

                    //InputStream fileContent = filePart.getInputStream(); //Lo transforma en InputStream
                    String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "docs";
                    //String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "spoa" + File.separator + "formulacion" + File.separator + "docs";
                    File uploads = new File(path); //Carpeta donde se guardan los archivos
                    uploads.mkdirs(); //Crea los directorios necesarios
                    oProy.setProyecto_doc(img);
                    File file = new File(uploads, proye + "-" + fileName);
                    //File file = File.createTempFile("serv-", "-"+fileName, uploads); //Evita que hayan dos archivos con el mismo nombre

                    try (InputStream input = filePart.getInputStream()) {
                        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
                if (multi.equals("")) {
                    oProy.setProyecto_multi(false);
                    try {
                        if ((integrantes.length == 0 || integrantes == null) && (oei.equals("2") || oei.equals("3"))) {
                            result = "Debe ingresar los integrantes del proyecto.";
                        } else {
                            result = aProy.ModificarProyecto(oProy);
                        }
                    } catch (Exception e) {
                        result = aProy.ModificarProyecto(oProy);
                    }
                } else {
                    String proy_are[] = request.getParameterValues("selectAgC");
                    oProy.setProyecto_multi(true);
                    if (proy_are == null) {
                        result = "Debe ingresar las unidades que conformen el proyecto.";
                    } else if ((integrantes == null || integrantes.length == 0) && (oei.equals("2") || oei.equals("3"))) {
                        result = "Debe ingresar los integrantes del proyecto.";
                    } else {
                        result = aProy.ModificarProyecto(oProy);
                        if (result.equals("Correcto")) {
                            result = aProy.EliminarAreas(Integer.parseInt(proye));
                            if (result.equals("Correcto")) {
                                for (String proy_are1 : proy_are) {
                                    oProy.setProyecto_id(Integer.parseInt(proye));
                                    oProy.setProyecto_ag(Integer.parseInt(proy_are1));
                                    result = aProy.IngresarAreasProyecto(oProy);
                                }
                            }
                        }
                    }
                }
            }
            if (result.equals("Correcto")) {
                /*result = aProy.EliminarAcciones(Integer.parseInt(proye));
                if (result.equals("Correcto")) {
                    if (eval!=null && eval.length > 0 ) {
                        for (String eval1 : eval) {
                            oProy.setProyecto_id(Integer.parseInt(proye));
                            oProy.setProyecto_acciones(eval1);
                            oProy.setProyecto_proceso("AEC2020");
                            if (!eval1.isEmpty()) {
                                result = aProy.IngresarAccionesCarreraProyecto(oProy);
                            }
                        }
                    }
                    if (evalins.length > 0) {
                        for (String evalins1 : evalins) {
                            oProy.setProyecto_id(Integer.parseInt(proye));
                            oProy.setProyecto_acciones(evalins1);
                            oProy.setProyecto_proceso("EE2019");
                            if (!evalins1.isEmpty()) {
                                result = aProy.IngresarAccionesCarreraProyecto(oProy);
                            }
                        }
                    }
                }*/
                if (result.equals("Correcto")) {
                    try {
                        if (integrantes.length > 0) {
                            result = aProy.EliminarIntegrantes(Integer.parseInt(proye));
                            for (String integrante1 : integrantes) {
                                oProy.setProyecto_id(Integer.parseInt(proye));
                                oProy.setProyecto_integrantes(integrante1);
                                if (!integrante1.isEmpty()) {
                                    result = aProy.IngresarIntegrantes(oProy);
                                }
                            }
                        }
                    } catch (Exception e) {
                    }
                }
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
                objTransaccion.setTransaccion_descripcion("El proyecto con código: \"" + objProyectoBuscar.getProyecto_codigo() + "\" se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }

            String json = new Gson().toJson(result);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

        } catch (Exception e) {
            String json = new Gson().toJson(e.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    private void AgregarArticulacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion, String strCedula, Integer TipoUsuario) throws IOException {
        String proyecto = request.getParameter("proyectoArt");
        String actividades[] = request.getParameterValues("selectAcciones[]");
        String proceso = request.getParameter("selectProceso");
        
        String result = "Error";
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();
        Integer sum = 0;

        if (actividades != null && actividades.length > 0) {
            for (String act : actividades) {
                cProy.setProyecto_proceso(proceso);
                cProy.setProyecto_acciones(act);
                cProy.setProyecto_id(Integer.parseInt(proyecto));

                if (aProy.ValidarAcciones(cProy)) {
                    sum++;
                } else {
                    if(TipoUsuario==26){
                        result = aProy.IngresarAccionesCarreraProyecto(cProy, true);
                    }else{
                        result = aProy.IngresarAccionesCarreraProyecto(cProy, false);
                    }
                }

                if (result.equals("Correcto")) {
                    cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                    objTransaccion.setTransaccion_descripcion("Al proyecto con código: \"" + proyecto + "\" se ingresó correctamente la actividad \"" + act + "\" de articulación de aseguramiento.");
                    ingresarTransaccion(objTransaccion);
                }
            }
        } else if (proceso.equals("0")) {
            result = aProy.EliminarAcciones(Integer.parseInt(proyecto));
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                objTransaccion.setTransaccion_descripcion("Al proyecto con código: \"" + proyecto + "\" se eliminó correctamente las actividades de articulación de aseguramiento.");
                ingresarTransaccion(objTransaccion);
            }
        } else {
            result = "Debe seleccionar actividades";
        }

        if (sum > 0) {
            result = "No se ingresaron " + sum + ", porque ya fueron ingresadas";
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

    }

    private void EnviarProyecto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String cedula = request.getParameter("cedulaProyecto");
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");
        String tipo = request.getParameter("tipou");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setEstado_observacion(observacion);
        if ((observacion.equals("Sin observacion") || observacion.isEmpty()) && (estado.equals("1") || estado.equals("2") || estado.equals("4") || estado.equals("6") || estado.equals("8") || estado.equals("10") || estado.equals("11") || estado.equals("15") || estado.equals("16") || estado.equals("17") || estado.equals("18") || estado.equals("21") || estado.equals("22") || estado.equals("23") || estado.equals("24") || estado.equals("52"))) {
            result = aProy.EnviarProyecto(cProy);
        } else if (observacion == null || observacion.isEmpty() || observacion.equals("")) {
            result = "Debe ingresar una observación";
        } else {
            cProy.setEstado_observacion(observacion);
            result = aProy.EnviarProyectoObserv(cProy);
        }

        if (tipo.equals("3") && (estado.equals("4") || estado.equals("8"))) {
            List<cProyecto> requerimientos = new ArrayList<cProyecto>();
            requerimientos = aProy.ListarRequerimientoProyecto(Integer.parseInt(proyecto));
            for (int i = 0; i < requerimientos.size(); i++) {
                cProy.setProyecto_id(requerimientos.get(i).getProyecto_id());
                cProy.setEstado_id(1);
                cProy.setUsuario_nombre(cedula);
                cProy.setProyecto_responsable("Sin observación");
                result = aProy.EnviarRequerimiento(cProy);
            }
        }
        if ((tipo.equals("5") || tipo.equals("7") || tipo.equals("8")) && (estado.equals("2"))) {
            List<cProyecto> requerimientos = new ArrayList<cProyecto>();
            requerimientos = aProy.ListarRequerimientoProyecto(Integer.parseInt(proyecto));
            for (int i = 0; i < requerimientos.size(); i++) {
                cProy.setProyecto_id(requerimientos.get(i).getProyecto_id());
                cProy.setEstado_id(1);
                cProy.setUsuario_nombre(cedula);
                result = aProy.EnviarRequerimiento(cProy);
            }
        }

        if ((estado.equals("17") || estado.equals("18")) && result.equals("Correcto")) {
            adProyecto aPrio = new adProyecto();
            result = aPrio.IngresarPrioProyecto(Integer.parseInt(proyecto), Integer.parseInt(estado));
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarEstado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String cedula = request.getParameter("cedulaProyecto");
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setEstado_observacion(observacion);

        result = aProy.EnviarProyectoObserv(cProy);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ResponsableProy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String cedula = request.getParameter("cedulaProyecto");
        String responsable = request.getParameter("responsable");
        String observacion = request.getParameter("observacion");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setProyecto_responsable(responsable);

        result = aProy.modificarResponsable(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El responsable del proyecto \"" + proyecto + "\" se modificó con la observación: \"" + observacion + "\".");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(1);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarEstado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String cedulaad = request.getParameter("cedulaProyecto");
        String cedula = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String fecha = request.getParameter("fecha");
        String observacion = request.getParameter("observacion");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setDeudas_contrato(fecha);

        result = aProy.EliminarEstado(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El estado \"" + estado + "\" del proyecto con código: \"" + proyecto + "\" con observacion: \"" + observacion + "\" se elimino correctamente");
            objTransaccion.setTransaccion_cedula(cedulaad);
            objTransaccion.setTransaccion_ag(1);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void EliminarEstadoDeuda(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("deuda");
        String cedulaad = request.getParameter("cedulaProyecto");
        String cedula = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String fecha = request.getParameter("fecha");
        String observacion = request.getParameter("observacion");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setDeudas_contrato(fecha);

        result = aProy.EliminarEstadoDeudas(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El estado \"" + estado + "\" del valor pendiente con código: \"" + proyecto + "\" con observacion: \"" + observacion + "\" se elimino correctamente");
            objTransaccion.setTransaccion_cedula(cedulaad);
            objTransaccion.setTransaccion_ag(1);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarEstadoEv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String cedula = request.getParameter("cedulaProyecto");
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");
        String cuatrimestre = request.getParameter("cuatrimestre");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setProy_cuatrimestre(Integer.parseInt(cuatrimestre));
        cProy.setEstado_observacion(observacion);

        result = aProy.EnviarProyectoObservEv(cProy);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarEstadoEv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String cedulaad = request.getParameter("cedulaProyecto");
        String cedula = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String fecha = request.getParameter("fecha");
        String observacion = request.getParameter("observacion");
        String cuatrimestre = request.getParameter("cuatrimestre");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setDeudas_contrato(fecha);
        cProy.setProy_cuatrimestre(Integer.parseInt(cuatrimestre));

        result = aProy.EliminarEstadoEv(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El estado \"" + estado + "\" del proyecto con código: \"" + proyecto + "\" con observacion: \"" + observacion + "\" se elimino correctamente");
            objTransaccion.setTransaccion_cedula(cedulaad);
            objTransaccion.setTransaccion_ag(1);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarActividadProceso(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion, String strCedula) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String actividad = request.getParameter("actividad");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setProyecto_acciones(actividad);

        result = aProy.EliminarAccionesProceso(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La actividad \"" + actividad + "\" de articulación del proyecto con código: \"" + proyecto + "\" se elimino correctamente");
            objTransaccion.setTransaccion_cedula(strCedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void ValidarArticulacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion, String strCedula) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String actividad = request.getParameter("id");
        String validar = request.getParameter("validar");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setProyecto_ag(Integer.parseInt(actividad));
        cProy.setProyecto_multi(Boolean.parseBoolean(validar));

        result = aProy.ValidarAccionesProceso(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La actividad \"" + actividad + "\" de articulación del proyecto con código: \"" + proyecto + "\" se valido correctamente");
            objTransaccion.setTransaccion_cedula(strCedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarProyectoRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        try {
            String proyecto = request.getParameter("idProyP");
            String cedula = request.getParameter("cedulaProyectoP");
            String fi = request.getParameter("fiproy");
            String ff = request.getParameter("ffproy");
            Part filePart = request.getPart("perfrep");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String img = proyecto + "-" + fileName;

            String result;
            adProyecto aProy = new adProyecto();
            cProyecto cProy = new cProyecto();

            cProy.setProyecto_id(Integer.parseInt(proyecto));
            cProy.setProyecto_fi(fi);
            cProy.setProyecto_ff(ff);

            if (fileName.equals("")) {
                result = aProy.ModificarProyectoRep(cProy);
            } else {
                String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "formulacion" + File.separator + "docs";
                File uploads = new File(path); //Carpeta donde se guardan los archivos
                uploads.mkdirs(); //Crea los directorios necesarios
                File file = new File(uploads, proyecto + "-" + fileName);
                cProy.setProyecto_doc(img);
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                result = aProy.ModificarProyectoRepP(cProy);
            }

            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El proyecto con código: \"" + proyecto + "\" se reprogramo correctamente");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAg);
                objTransaccion.setTransaccion_tipo(1);
                ingresarTransaccion(objTransaccion);
            }

            String json = new Gson().toJson(result);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            String json = new Gson().toJson(e.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    private void EnviarDeuda(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("deuda");
        String cedula = request.getParameter("cedulaProyecto");
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setEstado_observacion(observacion);
        if ((observacion.equals("Sin observacion") || observacion.isEmpty()) && ((estado.equals("1") || estado.equals("22")))) {
            result = aProy.EnviarDeuda(cProy);
        } else if (observacion == null || observacion.isEmpty() || observacion.equals("")) {
            result = "Debe ingresar una observación";
        } else {
            cProy.setEstado_observacion(observacion);
            result = aProy.EnviarDeudasObserv(cProy);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarDeudaEval(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("deuda");
        String cedula = request.getParameter("cedulaProyecto");
        String estado = request.getParameter("estado");
        String observacion = request.getParameter("observacion");
        String result;
        adProyecto aProy = new adProyecto();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proyecto));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setEstado_observacion(observacion);
        if ((observacion.equals("Sin observacion") || observacion.isEmpty()) && ((estado.equals("1") || estado.equals("22")))) {
            result = aProy.EnviarDeudaEval(cProy);
        } else if (observacion == null || observacion.isEmpty() || observacion.equals("")) {
            result = "Debe ingresar una observación";
        } else {
            cProy.setEstado_observacion(observacion);
            result = aProy.EnviarDeudasObservEval(cProy);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarProyecto(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String proyecto = request.getParameter("idproyecto");
        String cedula = request.getParameter("cedulaProyecto");
        adProyecto aComp = new adProyecto();

        result = aComp.EliminarProyecto(Integer.parseInt(proyecto));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El proyecto con código: \"" + proyecto + "\" se eliminó correctamente.");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarDeudas(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String deuda = request.getParameter("deuda");
        String cedula = request.getParameter("cedula");
        adProyecto aComp = new adProyecto();

        result = aComp.EliminarDeuda(Integer.parseInt(deuda));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La deuda con código: \"" + deuda + "\" se eliminó correctamente.");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarDeudasEvidencia(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String deuda = request.getParameter("deuda");
        String cedula = request.getParameter("cedula");
        String codigo = request.getParameter("codigo");
        adProyecto aComp = new adProyecto();

        result = aComp.EliminarDeudaEvidencia(Integer.parseInt(codigo));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La evidencia con código: \"" + codigo + "\" se eliminó correctamente de la deuda con código \"" + deuda + "\".");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void VerificaProyecto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        JsonObject objJson = new JsonObject();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        objJson.addProperty("numrequerimientos", aComp.numRequerimiento(Integer.parseInt(proyecto)));
        objJson.addProperty("numrequerimientosverificado", aComp.numRequerimientosVerificados(Integer.parseInt(proyecto)));
        objJson.addProperty("numregistrosProyecto", aComp.numRegistrosCompras(Integer.parseInt(proyecto)));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void Tiempo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject objJson = new JsonObject();
        adProyecto adProy = new adProyecto();

        objJson.addProperty("tiempo", adProy.tiemposVerificacion().toString());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void TiempoE(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject objJson = new JsonObject();
        adProyecto adProy = new adProyecto();

        objJson.addProperty("tiempo", adProy.tiemposVerificacionE().toString());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void VerificacionEnvios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject objJson = new JsonObject();
        String proyecto = request.getParameter("proyecto");
        adProyecto adProy = new adProyecto();

        objJson.addProperty("estado", adProy.VerificacionEnvios(Integer.parseInt(proyecto)));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void VerificacionReprogramado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject objJson = new JsonObject();
        String proyecto = request.getParameter("proyecto");
        adProyecto adProy = new adProyecto();

        objJson.addProperty("estado", adProy.VerificacionReprogramado(Integer.parseInt(proyecto)));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void VerificacionEnviosE(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject objJson = new JsonObject();
        String proyecto = request.getParameter("proyecto");
        String cuatrimestre = request.getParameter("cuatrimestre");
        adProyecto adProy = new adProyecto();

        objJson.addProperty("estado", adProy.VerificacionEnviosE(Integer.parseInt(proyecto), Integer.parseInt(cuatrimestre)));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void VerificacionEnviosET(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        JsonObject objJson = new JsonObject();
        adProyecto adProy = new adProyecto();

        objJson.addProperty("tiempo", adProy.VerificacionEnviosETiempo(Integer.parseInt(tipo)));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void VerificacionEnviosEje(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject objJson = new JsonObject();
        adProyecto adProy = new adProyecto();

        objJson.addProperty("tiempo", adProy.VerificacionEnviosETiempoEje());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void IngresarMontosProy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String proy = request.getParameter("proy");
        String v20 = request.getParameter("v20");
        String v21 = request.getParameter("v21");
        adProyecto adProy = new adProyecto();
        cProyecto pProy = new cProyecto();

        if (v20.isEmpty() || v20 == null) {
            result = "Debe ingresar un valor";
        } else if (v21.isEmpty() || v21 == null) {
            result = "Debe ingresar un valor";
        } else {
            if (adProy.VerificacionMontosIngresados(Integer.parseInt(proy), 2020)) {
                pProy.setProyecto_id(Integer.parseInt(proy));
                pProy.setMp_monto(Double.parseDouble(v20));
                pProy.setMp_anio(2020);
                result = adProy.modificarMontos(pProy);
            } else {
                pProy.setProyecto_id(Integer.parseInt(proy));
                pProy.setMp_monto(Double.parseDouble(v20));
                pProy.setMp_anio(2020);
                result = adProy.ingresarMontos(pProy);
            }

            if (adProy.VerificacionMontosIngresados(Integer.parseInt(proy), 2021)) {
                pProy.setProyecto_id(Integer.parseInt(proy));
                pProy.setMp_monto(Double.parseDouble(v21));
                pProy.setMp_anio(2021);
                result = adProy.modificarMontos(pProy);
            } else {
                pProy.setProyecto_id(Integer.parseInt(proy));
                pProy.setMp_monto(Double.parseDouble(v21));
                pProy.setMp_anio(2021);
                result = adProy.ingresarMontos(pProy);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarDeudas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedulaProyecto");
        String ag = request.getParameter("idAgFormulacion");
        String oei = request.getParameter("nmbOEI");
        String proyecto = request.getParameter("textNombreProy");
        String proceso = request.getParameter("txtNomProceso");
        String contrato = request.getParameter("textContrato");
        String tipocontr = request.getParameter("selectTipoCon");
        String financiamiento = request.getParameter("selectFin");
        String tipopresupuesto = request.getParameter("selectTipoPres");
        String montoContrato = request.getParameter("montoContrato");
        String montoIva = request.getParameter("montoIva");
        String tipo = request.getParameter("tipoDeuda");
        String agid = request.getParameter("tipoun");
        String pendiente = request.getParameter("montoPendiente");
        String anticipo = request.getParameter("montoAnticipo");
        String anio = request.getParameter("selectaniof");

        adProyecto adProy = new adProyecto();
        cProyecto pProy = new cProyecto();
        Integer deuda = adProy.codigoSiguienteDeudas();

        if (oei.isEmpty() || oei.equals("") || oei == null) {
            result = "Debe ingresar el OEI, solo el número";
        } else if (proyecto.equals("") || proyecto.isEmpty() || proyecto == null) {
            result = "Debe ingresar el nombre del proyecto";
        } else if (proceso.isEmpty() || proceso.equals("") || proceso == null) {
            result = "Debe ingresar el nombre del proceso";
        } else if (contrato.isEmpty() || contrato.equals("") || contrato == null) {
            result = "Debe ingresar el número de contrato";
        } else if (tipocontr.isEmpty() || tipocontr == null || tipocontr.equals("")) {
            result = "Debe seleccionar el tipo de contrato";
        } else if (financiamiento.equals("") || financiamiento.isEmpty() || financiamiento == null) {
            result = "Debe seleccionar el financiamiento";
        } else if (tipopresupuesto.isEmpty() || tipopresupuesto.equals("") || tipopresupuesto == null) {
            result = "Debe seleccionar el tipo de presupuesto";
        } else if ((montoContrato.isEmpty() || montoContrato.equals("") || montoContrato == null) && (pendiente.isEmpty() || pendiente.equals("") || pendiente == null)) {
            result = "Debe ingresar el monto de contrato";
        } else {
            pProy.setDeudas_id(deuda);
            pProy.setProyecto_nombre(proyecto);
            pProy.setDeudas_oei(Integer.parseInt(oei));
            pProy.setDeudas_proceso(proceso);
            pProy.setDeudas_contrato(contrato);
            pProy.setDeudas_tcon(Integer.parseInt(tipocontr));
            pProy.setDeudas_financiamiento(Integer.parseInt(financiamiento));
            pProy.setDeudas_presupuesto(tipopresupuesto);
            pProy.setProyecto_ag(Integer.parseInt(ag));
            pProy.setProyecto_anio(Integer.parseInt(anio));
            if (montoIva.isEmpty() || montoIva.equals("") || Double.parseDouble(montoIva) == 0.0 || Double.parseDouble(montoIva) < 0.0) {
                pProy.setDeuda_monto_iva(0.0);
            } else {
                pProy.setDeuda_monto_iva(Double.parseDouble(montoIva));
            }
            pProy.setTp_id(Integer.parseInt(tipo));
            pProy.setDeuda_monto_contrato(Double.parseDouble(montoContrato));
            pProy.setDeudas_monto_pendiente(Double.parseDouble(pendiente));

            if (anticipo.isEmpty() || anticipo.equals("") || Double.parseDouble(anticipo) == 0.0 || Double.parseDouble(anticipo) < 0.0) {
                pProy.setDeudas_anticipo(0.0);
            } else {
                pProy.setDeudas_anticipo(Double.parseDouble(anticipo));
            }
            result = adProy.IngresarDeudas(pProy);
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(Integer.parseInt(ag));
                objTransaccion.setTransaccion_tipo(1);
                objTransaccion.setTransaccion_descripcion("La deuda con código: \"" + deuda + "\" se ingresó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarDeudas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedulaProyecto");
        String ag = request.getParameter("idAgFormulacion");
        String oei = request.getParameter("nmbOEI");
        String deuda = request.getParameter("iddeudas");
        String proyecto = request.getParameter("textNombreProy");
        String proceso = request.getParameter("txtNomProceso");
        String contrato = request.getParameter("textContrato");
        String tipocontr = request.getParameter("selectTipoCon");
        String financiamiento = request.getParameter("selectFin");
        String tipopresupuesto = request.getParameter("selectTipoPres");
        String montoContrato = request.getParameter("montoContrato");
        String montoIva = request.getParameter("montoIva");
        String tipo = request.getParameter("tipoDeuda");
        String agid = request.getParameter("tipoun");
        String pendiente = request.getParameter("montoPendiente");
        String anticipo = request.getParameter("montoAnticipo");

        adProyecto adProy = new adProyecto();
        cProyecto pProy = new cProyecto();

        if (oei.isEmpty() || oei.equals("") || oei == null) {
            result = "Debe ingresar el OEI, solo el número";
        } else if (proyecto.equals("") || proyecto.isEmpty() || proyecto == null) {
            result = "Debe ingresar el nombre del proyecto";
        } else if (proceso.isEmpty() || proceso.equals("") || proceso == null) {
            result = "Debe ingresar el nombre del proceso";
        } else if (contrato.isEmpty() || contrato.equals("") || contrato == null) {
            result = "Debe ingresar el número de contrato";
        } else if (tipocontr.isEmpty() || tipocontr == null || tipocontr.equals("")) {
            result = "Debe seleccionar el tipo de contrato";
        } else if (financiamiento.equals("") || financiamiento.isEmpty() || financiamiento == null) {
            result = "Debe seleccionar el financiamiento";
        } else if (tipopresupuesto.isEmpty() || tipopresupuesto.equals("") || tipopresupuesto == null) {
            result = "Debe seleccionar el tipo de presupuesto";
        } else if ((montoContrato.isEmpty() || montoContrato.equals("") || montoContrato == null) && (pendiente.isEmpty() || pendiente.equals("") || pendiente == null)) {
            result = "Debe ingresar el monto de contrato";
        } else {
            pProy.setDeudas_id(Integer.parseInt(deuda));
            pProy.setProyecto_ag(Integer.parseInt(ag));
            pProy.setProyecto_nombre(proyecto);
            pProy.setDeudas_oei(Integer.parseInt(oei));
            pProy.setDeudas_proceso(proceso);
            pProy.setDeudas_contrato(contrato);
            pProy.setDeudas_tcon(Integer.parseInt(tipocontr));
            pProy.setDeudas_financiamiento(Integer.parseInt(financiamiento));
            pProy.setDeudas_presupuesto(tipopresupuesto);
            if (montoIva.isEmpty() || montoIva.equals("") || Double.parseDouble(montoIva) == 0.0 || Double.parseDouble(montoIva) < 0.0) {
                pProy.setDeuda_monto_iva(0.0);
            } else {
                pProy.setDeuda_monto_iva(Double.parseDouble(montoIva));
            }
            pProy.setDeuda_monto_contrato(Double.parseDouble(montoContrato));
            pProy.setDeudas_monto_pendiente(Double.parseDouble(pendiente));
            pProy.setTp_id(Integer.parseInt(tipo));

            if (anticipo.isEmpty() || anticipo.equals("") || Double.parseDouble(anticipo) == 0.0 || Double.parseDouble(anticipo) < 0.0) {
                pProy.setDeudas_anticipo(0.0);
            } else {
                pProy.setDeudas_anticipo(Double.parseDouble(anticipo));
            }

            try {
                if (agid.isEmpty() || agid.equals("") || Integer.parseInt(agid) == 0.0) {
                    result = adProy.ModificarDeudas(pProy);
                } else {
                    pProy.setEstado_id(Integer.parseInt(agid));
                    result = adProy.ModificarDeudasAg(pProy);
                }
            } catch (Exception e) {
                result = adProy.ModificarDeudas(pProy);
            }

            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(Integer.parseInt(ag));
                objTransaccion.setTransaccion_tipo(2);
                objTransaccion.setTransaccion_descripcion("La deuda con código: \"" + deuda + "\" se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarDeudasRef(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedulaProyecto");
        String ag = request.getParameter("idAgFormulacion");
        String oei = request.getParameter("nmbOEI");
        String deuda = request.getParameter("iddeudas");
        String proyecto = request.getParameter("textNombreProy");
        String proceso = request.getParameter("txtNomProceso");
        String contrato = request.getParameter("textContrato");
        String tipocontr = request.getParameter("selectTipoCon");
        String financiamiento = request.getParameter("selectFin");
        String tipopresupuesto = request.getParameter("selectTipoPres");
        String montoContrato = request.getParameter("montoContrato");
        String montoIva = request.getParameter("montoIva");
        String tipo = request.getParameter("tipoDeuda");
        String pendiente = request.getParameter("montoPendiente");
        String anticipo = request.getParameter("montoAnticipo");
        String reforma = request.getParameter("deudareforma");
        String estado = request.getParameter("selectEstadoD");

        adProyecto adProy = new adProyecto();
        cProyecto pProy = new cProyecto();

        if (oei.isEmpty() || oei.equals("") || oei == null) {
            result = "Debe ingresar el OEI, solo el número";
        } else if (proyecto.equals("") || proyecto.isEmpty() || proyecto == null) {
            result = "Debe ingresar el nombre del proyecto";
        } else if (proceso.isEmpty() || proceso.equals("") || proceso == null) {
            result = "Debe ingresar el nombre del proceso";
        } else if (contrato.isEmpty() || contrato.equals("") || contrato == null) {
            result = "Debe ingresar el número de contrato";
        } else if (tipocontr.isEmpty() || tipocontr == null || tipocontr.equals("")) {
            result = "Debe seleccionar el tipo de contrato";
        } else if (financiamiento.equals("") || financiamiento.isEmpty() || financiamiento == null) {
            result = "Debe seleccionar el financiamiento";
        } else if (tipopresupuesto.isEmpty() || tipopresupuesto.equals("") || tipopresupuesto == null) {
            result = "Debe seleccionar el tipo de presupuesto";
        } else if ((montoContrato.isEmpty() || montoContrato.equals("") || montoContrato == null) && (pendiente.isEmpty() || pendiente.equals("") || pendiente == null)) {
            result = "Debe ingresar el monto de contrato";
        } else {
            pProy.setDeudas_id(Integer.parseInt(deuda));
            pProy.setProyecto_ag(Integer.parseInt(ag));
            pProy.setProyecto_nombre(proyecto);
            pProy.setDeudas_oei(Integer.parseInt(oei));
            pProy.setDeudas_proceso(proceso);
            pProy.setDeudas_contrato(contrato);
            pProy.setDeudas_tcon(Integer.parseInt(tipocontr));
            pProy.setDeudas_financiamiento(Integer.parseInt(financiamiento));
            pProy.setDeudas_presupuesto(tipopresupuesto);
            if (montoIva.isEmpty() || montoIva.equals("") || Double.parseDouble(montoIva) == 0.0 || Double.parseDouble(montoIva) < 0.0) {
                pProy.setDeuda_monto_iva(0.0);
            } else {
                pProy.setDeuda_monto_iva(Double.parseDouble(montoIva));
            }
            pProy.setDeuda_monto_contrato(Double.parseDouble(montoContrato));
            pProy.setDeudas_monto_pendiente(Double.parseDouble(pendiente));
            pProy.setTp_id(Integer.parseInt(tipo));

            if (anticipo.isEmpty() || anticipo.equals("") || Double.parseDouble(anticipo) == 0.0 || Double.parseDouble(anticipo) < 0.0) {
                pProy.setDeudas_anticipo(0.0);
            } else {
                pProy.setDeudas_anticipo(Double.parseDouble(anticipo));
            }

            pProy.setEstado_id(Integer.parseInt(estado));
            pProy.setDeudas_reforma(Integer.parseInt(reforma));
            result = adProy.ModificarDeudasReforma(pProy);

            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(Integer.parseInt(ag));
                objTransaccion.setTransaccion_tipo(2);
                objTransaccion.setTransaccion_descripcion("La deuda con código: \"" + deuda + "\" se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarDeudasRefEst(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedulaProyecto2");
        String ag = request.getParameter("idAgFormulacion2");
        String entidad = request.getParameter("txtEntidad");
        String ejercicio = request.getParameter("txtEjercicio");
        String unidadeje = request.getParameter("txtUnidadE");
        String unidaddes = request.getParameter("txtUnidadD");
        String programa = request.getParameter("txtPrograma");
        String subprograma = request.getParameter("txtSubprograma");
        String proyecto = request.getParameter("txtProyecto");
        String actividad = request.getParameter("txtActividad");
        String obras = request.getParameter("txtObra");
        String geografico = request.getParameter("txtGeografico");
        String renglo = request.getParameter("txtRenglo");
        String rengloaux = request.getParameter("txtRengloA");
        String fuente = request.getParameter("txtFuente");
        String organismo = request.getParameter("txtOrganismo");
        String correlativo = request.getParameter("txtCorrelativo");
        String tipo = request.getParameter("slTipoEst");
        String deuda = request.getParameter("txtPresupuestoid");

        adProyecto adProy = new adProyecto();

        if (entidad.isEmpty() || entidad.equals("") || entidad == null || ejercicio.isEmpty() || ejercicio.equals("") || ejercicio == null || unidadeje.isEmpty() || unidadeje.equals("") || unidadeje == null) {
            result = "Los campos deben estar llenos";
        } else if (unidaddes.isEmpty() || unidaddes.equals("") || unidaddes == null || programa.isEmpty() || programa.equals("") || programa == null || subprograma.isEmpty() || subprograma.equals("") || subprograma == null) {
            result = "Los campos deben estar llenos";
        } else if (proyecto.isEmpty() || proyecto.equals("") || proyecto == null || actividad.isEmpty() || actividad.equals("") || actividad == null || obras.isEmpty() || obras.equals("") || obras == null) {
            result = "Los campos deben estar llenos";
        } else if (geografico.isEmpty() || geografico.equals("") || geografico == null || renglo.isEmpty() || renglo.equals("") || renglo == null || rengloaux.isEmpty() || rengloaux.equals("") || rengloaux == null) {
            result = "Los campos deben estar llenos";
        } else if (fuente.isEmpty() || fuente.equals("") || fuente == null || organismo.isEmpty() || organismo.equals("") || organismo == null || correlativo.isEmpty() || correlativo.equals("") || correlativo == null) {
            result = "Los campos deben estar llenos";
        } else {
            cActividadRequerimiento cAct = new cActividadRequerimiento();
            cAct.setPresupuesto_id(Integer.parseInt(tipo));
            cAct.setPresupuesto_ejercicio(Integer.parseInt(ejercicio));
            cAct.setPresupuesto_entidad(Integer.parseInt(entidad));
            cAct.setPresupuesto_unidad_ejec(Integer.parseInt(unidadeje));
            cAct.setPresupuesto_unidad_desc(unidaddes);
            cAct.setPresupuesto_programa(programa);
            cAct.setPresupuesto_subprograma(subprograma);
            cAct.setPresupuesto_proyecto(proyecto);
            cAct.setPresupuesto_actividad(actividad);
            cAct.setPresupuesto_obra(obras);
            cAct.setPresupuesto_geografico(geografico);
            cAct.setPresupuesto_renglo(Integer.parseInt(renglo));
            cAct.setPresupuesto_renglo_aux(rengloaux);
            cAct.setPresupuesto_fuente(fuente);
            cAct.setPresupuesto_organismo(organismo);
            cAct.setPresupuesto_correlativo(correlativo);
            cAct.setReq_id(Integer.parseInt(deuda));
            result = adProy.ModificarDeudasEstructura(cAct);

            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(Integer.parseInt(ag));
                objTransaccion.setTransaccion_tipo(2);
                objTransaccion.setTransaccion_descripcion("La estructura tipo \"" + tipo + "\" de la deuda con código: \"" + deuda + "\" se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarDeudasRefEst(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedulaProyecto2");
        String ag = request.getParameter("idAgFormulacion2");
        String entidad = request.getParameter("txtEntidad");
        String ejercicio = request.getParameter("txtEjercicio");
        String unidadeje = request.getParameter("txtUnidadE");
        String unidaddes = request.getParameter("txtUnidadD");
        String programa = request.getParameter("txtPrograma");
        String subprograma = request.getParameter("txtSubprograma");
        String proyecto = request.getParameter("txtProyecto");
        String actividad = request.getParameter("txtActividad");
        String obras = request.getParameter("txtObra");
        String geografico = request.getParameter("txtGeografico");
        String renglo = request.getParameter("txtRenglo");
        String rengloaux = request.getParameter("txtRengloA");
        String fuente = request.getParameter("txtFuente");
        String organismo = request.getParameter("txtOrganismo");
        String correlativo = request.getParameter("txtCorrelativo");
        String tipo = request.getParameter("selectTipoEs");
        String deuda = request.getParameter("txtPresupuestoid");

        adProyecto adProy = new adProyecto();

        if (entidad.isEmpty() || entidad.equals("") || entidad == null || ejercicio.isEmpty() || ejercicio.equals("") || ejercicio == null || unidadeje.isEmpty() || unidadeje.equals("") || unidadeje == null) {
            result = "Los campos deben estar llenos";
        } else if (unidaddes.isEmpty() || unidaddes.equals("") || unidaddes == null || programa.isEmpty() || programa.equals("") || programa == null || subprograma.isEmpty() || subprograma.equals("") || subprograma == null) {
            result = "Los campos deben estar llenos";
        } else if (proyecto.isEmpty() || proyecto.equals("") || proyecto == null || actividad.isEmpty() || actividad.equals("") || actividad == null || obras.isEmpty() || obras.equals("") || obras == null) {
            result = "Los campos deben estar llenos";
        } else if (geografico.isEmpty() || geografico.equals("") || geografico == null || renglo.isEmpty() || renglo.equals("") || renglo == null || rengloaux.isEmpty() || rengloaux.equals("") || rengloaux == null) {
            result = "Los campos deben estar llenos";
        } else if (fuente.isEmpty() || fuente.equals("") || fuente == null || organismo.isEmpty() || organismo.equals("") || organismo == null || correlativo.isEmpty() || correlativo.equals("") || correlativo == null) {
            result = "Los campos deben estar llenos";
        } else {
            cActividadRequerimiento cAct = new cActividadRequerimiento();
            cAct.setPresupuesto_id(Integer.parseInt(tipo));
            cAct.setPresupuesto_ejercicio(Integer.parseInt(ejercicio));
            cAct.setPresupuesto_entidad(Integer.parseInt(entidad));
            cAct.setPresupuesto_unidad_ejec(Integer.parseInt(unidadeje));
            cAct.setPresupuesto_unidad_desc(unidaddes);
            cAct.setPresupuesto_programa(programa);
            cAct.setPresupuesto_subprograma(subprograma);
            cAct.setPresupuesto_proyecto(proyecto);
            cAct.setPresupuesto_actividad(actividad);
            cAct.setPresupuesto_obra(obras);
            cAct.setPresupuesto_geografico(geografico);
            cAct.setPresupuesto_renglo(Integer.parseInt(renglo));
            cAct.setPresupuesto_renglo_aux(rengloaux);
            cAct.setPresupuesto_fuente(fuente);
            cAct.setPresupuesto_organismo(organismo);
            cAct.setPresupuesto_correlativo(correlativo);
            cAct.setReq_id(Integer.parseInt(deuda));
            result = adProy.IngresarDeudasEstructura(cAct);

            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(Integer.parseInt(ag));
                objTransaccion.setTransaccion_tipo(1);
                objTransaccion.setTransaccion_descripcion("La estructura tipo \"" + tipo + "\" de la deuda con código: \"" + deuda + "\" se ingresó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarEstructuraD(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedulaProyecto");
        String tipo = request.getParameter("tipo");
        String deuda = request.getParameter("deuda");

        adProyecto adProy = new adProyecto();

        cActividadRequerimiento cAct = new cActividadRequerimiento();
        cAct.setReq_id(Integer.parseInt(deuda));
        cAct.setTc_id(Integer.parseInt(tipo));
        result = adProy.EliminarDeudasEstructura(cAct);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(1);
            objTransaccion.setTransaccion_tipo(3);
            objTransaccion.setTransaccion_descripcion("La estructura tipo \"" + tipo + "\" de la deuda con código: \"" + deuda + "\" se eliminó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarFechasDeudas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String req = request.getParameter("deuda");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarDeudasEstados(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarFechasDeudasEval(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String req = request.getParameter("deuda");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarDeudasEstadosEval(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ActividadPresupuestaria(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipo = request.getParameter("tipo");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarActividadPresupuestaria(Integer.parseInt(tipo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaProyectoEvaluacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipousuario = request.getParameter("tipousuario");
        String areapadre = request.getParameter("areapadre");
        String area = request.getParameter("area");
        String tipoproyecto = request.getParameter("tipoproyecto");
        String cuatrimestre = request.getParameter("cuatrimestre");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoEvaluacion(Integer.parseInt(tipousuario), Integer.parseInt(areapadre), Integer.parseInt(area), Integer.parseInt(tipoproyecto), Integer.parseInt(cuatrimestre), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaProyectoEvaluacionL(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tipousuario = request.getParameter("tipousuario");
        String area = request.getParameter("area");
        String tipoproyecto = request.getParameter("tipoproyecto");
        String cuatrimestre = request.getParameter("cuatrimestre");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoEvaluacionL(Integer.parseInt(tipousuario), Integer.parseInt(area), Integer.parseInt(tipoproyecto), Integer.parseInt(cuatrimestre), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarProyectoCompEvaluacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("proy");
        String cuatri = request.getParameter("cuatrimestre");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarProyectoEval(Integer.parseInt(proy), Integer.parseInt(cuatri));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaEstadoAsignacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("proyecto");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarEstadoAsign(Integer.parseInt(proy));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
