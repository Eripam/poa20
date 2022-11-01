/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import poa.acceso.adEvaluacion;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cActividadRequerimiento;
import poa.clases.cComponenteMeta;
import poa.clases.cProyecto;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
@MultipartConfig
public class servEvaluacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            /*Inicio Para transacciones*/
            HttpSession sesionOk = request.getSession(false);
            //String strCedula = (String) sesionOk.getAttribute("cedula");
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            /*Fin Para transacciones*/

            switch (strAccion) {
                case "ConsultarPorcentaje":
                    ConsultarPorcentaje(request, response);
                    break;
                case "ListarActividad":
                    ListarActividad(request, response);
                    break;
                case "IngresarEvidencias":
                    IngresarEvidencias(request, response, intIdAreaGestion);
                    break;
                case "IngresarEvidenciasDeudas":
                    IngresarEvidenciasDeudas(request, response, intIdAreaGestion);
                    break;
                case "ModificarArchivos":
                    ModificarArchivos(request, response, intIdAreaGestion);
                    break;
                case "ModificarEvidenciasDeudas":
                    ModificarEvidenciasDeudas(request, response, intIdAreaGestion);
                    break;
                case "EliminarArchivos":
                    EliminarArchivos(request, response, intIdAreaGestion);
                    break;
                case "IngresarAutoEvaluacion":
                    IngresarAutoEvaluacion(request, response, intIdAreaGestion);
                    break;
                case "AutoevaluacionDuedas":
                    AutoevaluacionDuedas(request, response, intIdAreaGestion);
                    break;
                case "EvaluacionDuedas":
                    EvaluacionDuedas(request, response, intIdAreaGestion);
                    break;
                case "ModificarAutoEvaluacion":
                    ModificarAutoEvaluacion(request, response, intIdAreaGestion);
                    break;
                case "ListarMeta":
                    ListarMeta(request, response);
                    break;
                case "EnviarProyecto":
                    EnviarProyecto(request, response);
                    break;
                case "IngresarObservacion":
                    IngresarObservacion(request, response);
                    break;
                case "IngresarEvaluacion":
                    IngresarEvaluacion(request, response);
                    break;
                case "ListarEvaluacionProyecto":
                    ListarEvaluacionProyecto(request, response);
                    break;
                case "ListaReqEjec":
                    ListaReqEjec(request, response);
                    break;
                case "ListarRequerimiento":
                    ListarRequerimiento(request, response);
                    break;
                case "ListarAutoevaluacionDuedas":
                    ListarAutoevaluacionDuedas(request, response);
                    break;
            }
        }
    }

    private void ConsultarPorcentaje(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Double result;
        String componente = request.getParameter("componente");
        String cuatrimestre = request.getParameter("cuatrimestre");
        adEvaluacion aEval = new adEvaluacion();

        result = aEval.porcentajeCuatrimestre(Integer.parseInt(componente), Integer.parseInt(cuatrimestre));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarActividad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        String cuatri = request.getParameter("cuatri");
        String tipo = request.getParameter("tipo");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEvaluacion aComp = new adEvaluacion();

        result = aComp.ListarActividad(Integer.parseInt(comp), Integer.parseInt(cuatri), Integer.parseInt(tipo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarEvidencias(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException, ServletException {
        String cuatrimestre = request.getParameter("cuatrimestreevalact");
        String actividad = request.getParameter("actividadarchivo");
        String descripcion = request.getParameter("descarchivo");
        String cedula = request.getParameter("cedulaProyecto");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        Integer archivo = aEval.codigoSiguienteArchivoEv();

        Part filePart = request.getPart("archivoactividad");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String img = archivo + "-" + fileName;
        long tama = filePart.getSize();

        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String result;
        if (((tama / 1024) / 1024) < 10) {
            if (descripcion.isEmpty()) {
                result = "Debe ingresar la descripcion del archivo.";
            } else {
                cCom.setActividad_id(Integer.parseInt(actividad));
                cCom.setAre_id(archivo);
                cCom.setAre_descripcion(descripcion);
                cCom.setMes_id(Integer.parseInt(cuatrimestre));

                String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "evaluacion" + File.separator + cuatrimestre;
                File uploads = new File(path); //Carpeta donde se guardan los archivos
                uploads.mkdirs(); //Crea los directorios necesarios
                File file = new File(uploads, archivo + "-" + fileName);
                cCom.setAre_archivo(img);

                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                result = aEval.IngresarArchivosEval(cCom);
            }
        } else {
            result = "El tamaño del archivo sobrepasa el permitido que es de 10 mb";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La evidencia con código: \"" + cCom.getAre_id() + "\" se ingresó correctamente en la actividad \"" + actividad + "\".");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(ag);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarEvidenciasDeudas(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException, ServletException {
        String deudas = request.getParameter("iddeudas");
        String descripcion = request.getParameter("descarchivo");
        String cedula = request.getParameter("cedulaProyecto");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        Integer archivo = aEval.codigoSiguienteArchivoEvDeudas();

        Part filePart = request.getPart("archivoactividad");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String img = archivo + "-" + fileName;
        long tama = filePart.getSize();

        fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        String result;
        if (((tama / 1024) / 1024) < 10) {
            if (descripcion.isEmpty()) {
                result = "Debe ingresar la descripcion del archivo.";
            } else {
                cCom.setActividad_id(Integer.parseInt(deudas));
                cCom.setAre_id(archivo);
                cCom.setAre_descripcion(descripcion);

                String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "evaluacion" + File.separator + "deudas";
                File uploads = new File(path); //Carpeta donde se guardan los archivos
                uploads.mkdirs(); //Crea los directorios necesarios
                File file = new File(uploads, archivo + "-" + fileName);
                cCom.setAre_archivo(img);

                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                result = aEval.IngresarArchivosEvalDeudas(cCom);
            }
        } else {
            result = "El tamaño del archivo sobrepasa el permitido que es de 10 mb";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La evidencia con código: \"" + cCom.getAre_id() + "\" se ingresó correctamente en la deuda \"" + deudas + "\".");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(ag);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarArchivos(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException {
        String result;
        String id = request.getParameter("id");
        String descripcion = request.getParameter("descripcion");
        String cedula = request.getParameter("cedulaProyecto");

        adEvaluacion aEval = new adEvaluacion();
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        cCom.setAre_id(Integer.parseInt(id));
        cCom.setAre_descripcion(descripcion);
        result = aEval.ModificarArchivos(cCom);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La evidencia con código: \"" + cCom.getAre_id() + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(ag);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarEvidenciasDeudas(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException {
        String result;
        String id = request.getParameter("id");
        String descripcion = request.getParameter("descripcion");
        String cedula = request.getParameter("cedulaProyecto");

        adEvaluacion aEval = new adEvaluacion();
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        cCom.setAre_id(Integer.parseInt(id));
        cCom.setAre_descripcion(descripcion);
        result = aEval.ModificarArchivosEval(cCom);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La evidencia con código: \"" + cCom.getAre_id() + "\" se modificó correctamente en la deuda");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(ag);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarArchivos(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException {
        String result;
        String id = request.getParameter("id");
        String archivo = request.getParameter("archivo");
        String cuatrimestre = request.getParameter("cuat");
        String cedulaProyecto = request.getParameter("cedulaProyecto");

        adEvaluacion aEval = new adEvaluacion();
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        cCom.setAre_id(Integer.parseInt(id));
        result = aEval.EliminarArchivos(cCom);

        if (result.equals("Correcto")) {
            String path = File.separator + "var" + File.separator + "www" + File.separator + "html" + File.separator + "sip" + File.separator + "evaluacion" + File.separator + cuatrimestre + File.separator;
            File fichero = new File(path + archivo);
            fichero.delete();

            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La evidencia con código: \"" + cCom.getAre_id() + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedulaProyecto);
            objTransaccion.setTransaccion_ag(ag);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarAutoEvaluacion(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException, ServletException {
        String result;
        String cuatrimestre = request.getParameter("cuatrimestre");
        String actividad = request.getParameter("actividad");
        String porcentaje = request.getParameter("porcentaje");
        String cedulaProyecto = request.getParameter("cedulaProyecto");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        cCom.setMe_cuatrimestre(Integer.parseInt(cuatrimestre));
        cCom.setActividad_id(Integer.parseInt(actividad));
        cCom.setAe_autoeval(Double.parseDouble(porcentaje));

        result = aEval.IngresarAutoEvaluacion(cCom);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La autoevaluación de la actividad con código: \"" + actividad + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedulaProyecto);
            objTransaccion.setTransaccion_ag(ag);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void AutoevaluacionDuedas(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException, ServletException {
        String result;
        String deuda = request.getParameter("deuda");
        String suma = request.getParameter("suma");
        String ejecucion = request.getParameter("ejecucion");
        String bandera = request.getParameter("bandera");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        cCom.setActividad_id(Integer.parseInt(deuda));
        cCom.setActividad_monto(Double.parseDouble(suma));
        cCom.setAe_autoeval(Double.parseDouble(ejecucion));

        if (bandera.equals("true")) {
            result = aEval.ModificarAutoEvaluacionDeudas(cCom);
        } else {
            result = aEval.IngresarAutoEvaluacionDeudas(cCom);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EvaluacionDuedas(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException, ServletException {
        String result;
        String deuda = request.getParameter("deuda");
        String suma = request.getParameter("suma");
        String ejecucion = request.getParameter("ejecucion");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        cCom.setActividad_id(Integer.parseInt(deuda));
        cCom.setActividad_monto(Double.parseDouble(suma));
        cCom.setAe_evaluacion(Double.parseDouble(ejecucion));
        Double porcentaje;

        porcentaje = (Double.parseDouble(ejecucion) * 100) / Double.parseDouble(suma);
        cCom.setAe_porcentaje(porcentaje);

        result = aEval.ModificarEvaluacionDeudas(cCom);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarAutoevaluacionDuedas(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String deuda = request.getParameter("deuda");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        cCom.setActividad_id(Integer.parseInt(deuda));

        result = aEval.ListarAutoEvaluacionDeudas(cCom);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarAutoEvaluacion(HttpServletRequest request, HttpServletResponse response, Integer ag) throws IOException, ServletException {
        String result;
        String cuatrimestre = request.getParameter("cuatrimestre");
        String actividad = request.getParameter("actividad");
        String porcentaje = request.getParameter("porcentaje");
        String cedulaProyecto = request.getParameter("cedulaProyecto");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        cCom.setMe_cuatrimestre(Integer.parseInt(cuatrimestre));
        cCom.setActividad_id(Integer.parseInt(actividad));
        cCom.setAe_autoeval(Double.parseDouble(porcentaje));

        result = aEval.ModificarAutoEvaluacion(cCom);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La autoevaluación de la actividad con código: \"" + actividad + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedulaProyecto);
            objTransaccion.setTransaccion_ag(ag);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarMeta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        String cuatri = request.getParameter("cuatri");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adEvaluacion aEval = new adEvaluacion();

        result = aEval.ListarMetas(Integer.parseInt(comp), Integer.parseInt(cuatri));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarProyecto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String proy = request.getParameter("idProyecto");
        String cedula = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String cuatri = request.getParameter("cuatrimestre");
        adEvaluacion aComp = new adEvaluacion();
        cProyecto cProy = new cProyecto();

        cProy.setProyecto_id(Integer.parseInt(proy));
        cProy.setEstado_id(Integer.parseInt(estado));
        cProy.setUsuario_nombre(cedula);
        cProy.setDeudas_id(Integer.parseInt(cuatri));

        result = aComp.EnviarProyectoEval(cProy);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarObservacion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result;
        String cuatrimestre = request.getParameter("cuatrimestre");
        String actividad = request.getParameter("actividad");
        String observacion = request.getParameter("observacion");

        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEvaluacion aEval = new adEvaluacion();
        cCom.setMe_cuatrimestre(Integer.parseInt(cuatrimestre));
        cCom.setActividad_id(Integer.parseInt(actividad));
        cCom.setAe_observacion(observacion);

        result = aEval.IngresarObservacionEval(cCom);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarEvaluacionProyecto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proyecto = request.getParameter("proyecto");
        String cuatrimestre = request.getParameter("cuatrimestre");
        String ag = request.getParameter("ag");
        String tu = request.getParameter("tu");
        String agpadre = request.getParameter("agpadre");
        String anio = request.getParameter("anio");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adEvaluacion aComp = new adEvaluacion();

        result = aComp.ListarProyectoEvaluacion(Integer.parseInt(proyecto), Integer.parseInt(cuatrimestre), Integer.parseInt(ag), Integer.parseInt(tu), Integer.parseInt(agpadre), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaReqEjec(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String actividad = request.getParameter("act");
        String cuatrimestre = request.getParameter("cuatrimestre");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEvaluacion aComp = new adEvaluacion();

        result = aComp.ListarSolicitudEjecucion(Integer.parseInt(actividad), Integer.parseInt(cuatrimestre));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarEvaluacion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String result;
        String cuatrimestre = request.getParameter("cuatrimestre");
        String actividad = request.getParameter("actividad");
        String autoeval = request.getParameter("autoeval");
        String dinero = request.getParameter("dinero");
        String tiempo = request.getParameter("tiempo");

        double ev = Double.parseDouble(autoeval), din = Double.parseDouble(dinero);
        Integer tie = Integer.parseInt(tiempo);
        if (ev < -1 || ev > 100) {
            result = "El valor ingreso debe estar entre 0 y 100";
        } else if (din < -1) {
            result = "El valor ingresado debe ser mayor o igual que cero o -1";
        } else if (tie < 0 || tie > 1) {
            result = "El valor deber ser 0 o 1";
        } else {
            cActividadRequerimiento cCom = new cActividadRequerimiento();
            adEvaluacion aEval = new adEvaluacion();
            cCom.setMe_cuatrimestre(Integer.parseInt(cuatrimestre));
            cCom.setActividad_id(Integer.parseInt(actividad));
            cCom.setAe_autoeval(ev);
            cCom.setAe_ejecucion(din);
            cCom.setAe_tiempo(tie);

            result = aEval.IngresarEvaluacion(cCom);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarRequerimiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        String cuatri = request.getParameter("cuatri");
        String tipo = request.getParameter("tipo");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEvaluacion aComp = new adEvaluacion();

        result = aComp.ListarActividad(Integer.parseInt(comp), Integer.parseInt(cuatri), Integer.parseInt(tipo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
