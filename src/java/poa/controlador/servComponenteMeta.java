/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import poa.acceso.adComponenteMeta;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cComponenteMeta;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
public class servComponenteMeta extends HttpServlet {

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
                case "IngresarComponente":
                    IngresarComponente(request, response, intIdAreaGestion);
                    break;
                case "IngresarComponenteRep":
                    IngresarComponenteRep(request, response, intIdAreaGestion);
                    break;
                case "IngresarMeta":
                    IngresarMeta(request, response, intIdAreaGestion);
                    break;
                case "IngresarIndicador":
                    IngresarIndicador(request, response, intIdAreaGestion);
                    break;
                case "IngresarIndicadorRep":
                    IngresarIndicadorRep(request, response, intIdAreaGestion);
                    break;
                case "ListarComponentes":
                    ListarComponentes(request, response);
                    break;
                case "ListarComponentesRep":
                    ListarComponentesRep(request, response);
                    break;
                case "ListaComponentesLogros":
                    ListaComponentesLogros(request, response);
                    break;
                case "ListarComponentesMult":
                    ListarComponentesMult(request, response);
                    break;
                case "ListarMeta":
                    ListarMeta(request, response);
                    break;
                case "ListarMetaRep":
                    ListarMetaRep(request, response);
                    break;
                case "ListarIndicador":
                    ListarIndicador(request, response);
                    break;
                case "ListarIndicadorRep":
                    ListarIndicadorRep(request, response);
                    break;
                case "ModificarComponente":
                    ModificarComponente(request, response, intIdAreaGestion);
                    break;
                case "IngresarComponenteLogros":
                    IngresarComponenteLogros(request, response, intIdAreaGestion);
                    break;
                case "ModificarMeta":
                    ModificarMeta(request, response, intIdAreaGestion);
                    break;
                case "IngresarMetaRep":
                    IngresarMetaRep(request, response, intIdAreaGestion);
                    break;
                case "ModificarIndicador":
                    ModificarIndicador(request, response, intIdAreaGestion);
                    break;
                case "IngresarIndicadorReprog":
                    IngresarIndicadorReprog(request, response, intIdAreaGestion);
                    break;
                case "EliminarIndicador":
                    EliminarIndicador(request, response, intIdAreaGestion);
                    break;
                case "EliminarIndicadorRep":
                    EliminarIndicadorRep(request, response, intIdAreaGestion);
                    break;
                case "EliminarComponente":
                    EliminarComponente(request, response, intIdAreaGestion);
                    break;
            }
        }
    }

    private void IngresarComponente(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String proy = request.getParameter("idProyCompleto");
        String componente = request.getParameter("txtnombreComp");
        String ag = request.getParameter("idAgComp");
        String cedula = request.getParameter("cedulaProyecto");
        JsonObject objJson = new JsonObject();
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        if (componente.isEmpty()) {
            result = "Debe ingresar el nombre del componente";
        } else {
            Integer idComponente = aComp.codigoSiguienteComponente();
            cComp.setComponente_nombre(componente);
            cComp.setComponente_proyecto(Integer.parseInt(proy));
            cComp.setComponente_id(idComponente);
            cComp.setComponente_ag(Integer.parseInt(ag));
            objJson.addProperty("componente", componente);
            objJson.addProperty("idComponente", idComponente);
            result = aComp.IngresarComponente(cComp);
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El componente con código: \"" + cComp.getComponente_id() + "\" se ingresó correctamente en el proyecto: \"" + cComp.getComponente_proyecto() + "\"");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(1);
                ingresarTransaccion(objTransaccion);
            }
        }

        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void IngresarMeta(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String meta = request.getParameter("txtnombreMeta");
        String componente = request.getParameter("idComponenteMeta");
        String cedula = request.getParameter("cedulaProyecto");
        JsonObject objJson = new JsonObject();
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        Integer idMeta = aComp.codigoSiguienteMeta();

        if (meta.isEmpty()) {
            result = "Debe ingresar el nombre de la meta";
        } else {
            cComp.setMeta_nombre(meta);
            cComp.setMeta_id(idMeta);
            cComp.setComponente_id(Integer.parseInt(componente));
            result = aComp.IngresarMeta(cComp);
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("La meta con código: \"" + cComp.getMeta_id() + "\" se ingresó correctamente en el componente: \"" + cComp.getComponente_id() + "\"");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(1);
                ingresarTransaccion(objTransaccion);
            }
            objJson.addProperty("meta", meta);
            objJson.addProperty("idMeta", idMeta);
        }
        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void IngresarIndicador(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String meta = request.getParameter("idMetaIndicador");
        String nombre = request.getParameter("txtnombreIndicador");
        String descripcion = request.getParameter("txtdescipcionIndicador");
        String tipo = request.getParameter("tipoIndicador");
        String ejecu = request.getParameter("txtindicadorejecutadovalor");
        String plan = request.getParameter("txtindicadorplanificadovalor");
        String valplan = request.getParameter("txtindicadorplanificado");
        String tiponum = request.getParameter("valorIndicador");
        String cedula = request.getParameter("cedulaProyecto");
        JsonObject objJson = new JsonObject();
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        Integer idInd = aComp.codigoSiguienteIndicador();

        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del indicador.";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descripción del indicador";
        } else {
            cComp.setIndicador_id(idInd);
            cComp.setIndicador_nombre(nombre);
            cComp.setIndicador_descripcion(descripcion);
            cComp.setIndicador_tipo(tipo);
            cComp.setMeta_id(Integer.parseInt(meta));
            objJson.addProperty("indicador", nombre);
            objJson.addProperty("idIndicador", idInd);
            objJson.addProperty("descripcion", descripcion);
            objJson.addProperty("tipo", tipo);
            if (tipo.equals("Cuantitativo")) {
                if (ejecu.isEmpty()) {
                    result = "Debe ingresar la meta ejecutada";
                } else if (plan.isEmpty()) {
                    result = "Debe ingresar la meta planificada";
                } else if (valplan.isEmpty()) {
                    result = "Debe ingresar el valor planificado";
                } else {
                    cComp.setIndicador_ejecutado(ejecu);
                    cComp.setIndicador_planificado(plan);
                    if (tiponum.equals("1")) {
                        cComp.setIndicador_numero(Double.parseDouble(valplan) / 100);
                    } else {
                        cComp.setIndicador_numero(Double.parseDouble(valplan));
                    }
                    objJson.addProperty("ejecutado", ejecu);
                    objJson.addProperty("planificado", plan);
                    objJson.addProperty("valor", valplan);
                    result = aComp.IngresarIndicador(cComp);
                }
            } else {
                cComp.setIndicador_ejecutado("vacio");
                cComp.setIndicador_planificado("vacio");
                cComp.setIndicador_numero(0.0);
                result = aComp.IngresarIndicador(cComp);
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El indicador con código: \"" + idInd + "\" se ingresó correctamente en la meta: \"" + cComp.getMeta_id() + "\"");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(1);
                ingresarTransaccion(objTransaccion);
            }
        }

        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void IngresarIndicadorRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String meta = request.getParameter("idMetaIndicador");
        String nombre = request.getParameter("txtnombreIndicador");
        String descripcion = request.getParameter("txtdescipcionIndicador");
        String tipo = request.getParameter("tipoIndicador");
        String ejecu = request.getParameter("txtindicadorejecutadovalor");
        String plan = request.getParameter("txtindicadorplanificadovalor");
        String valplan = request.getParameter("txtindicadorplanificado");
        String tiponum = request.getParameter("valorIndicador");
        String cedula = request.getParameter("cedulaProyecto");
        JsonObject objJson = new JsonObject();
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        Integer idInd = aComp.codigoSiguienteIndicador();

        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del indicador.";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descripción del indicador";
        } else {
            cComp.setIndicador_id(idInd);
            cComp.setIndicador_nombre(nombre);
            cComp.setIndicador_descripcion(descripcion);
            cComp.setIndicador_tipo(tipo);
            cComp.setMeta_id(Integer.parseInt(meta));
            objJson.addProperty("indicador", nombre);
            objJson.addProperty("idIndicador", idInd);
            objJson.addProperty("descripcion", descripcion);
            objJson.addProperty("tipo", tipo);
            if (tipo.equals("Cuantitativo")) {
                if (ejecu.isEmpty()) {
                    result = "Debe ingresar la meta ejecutada";
                } else if (plan.isEmpty()) {
                    result = "Debe ingresar la meta planificada";
                } else if (valplan.isEmpty()) {
                    result = "Debe ingresar el valor planificado";
                } else {
                    cComp.setIndicador_ejecutado(ejecu);
                    cComp.setIndicador_planificado(plan);
                    if (tiponum.equals("1")) {
                        cComp.setIndicador_numero(Double.parseDouble(valplan) / 100);
                    } else {
                        cComp.setIndicador_numero(Double.parseDouble(valplan));
                    }
                    objJson.addProperty("ejecutado", ejecu);
                    objJson.addProperty("planificado", plan);
                    objJson.addProperty("valor", valplan);
                    result = aComp.IngresarIndicadorRep(cComp);
                }
            } else {
                cComp.setIndicador_ejecutado("vacio");
                cComp.setIndicador_planificado("vacio");
                cComp.setIndicador_numero(0.0);
                result = aComp.IngresarIndicadorRep(cComp);
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El indicador con código: \"" + idInd + "\" se ingresó correctamente en la meta: \"" + cComp.getMeta_id() + "\"");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(1);
                ingresarTransaccion(objTransaccion);
            }
        }

        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void ListarComponentes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("proy");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.ListarComponentes(Integer.parseInt(proy));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarComponentesRep(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("proy");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.ListarComponentesRep(Integer.parseInt(proy));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaComponentesLogros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("componente");
        String anio = request.getParameter("anio");
        String cuatrimestre = request.getParameter("cuatrimestre");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        if (anio.equals("2020")) {
            result = aComp.ListarComponentesLogros(Integer.parseInt(proy));
        } else {
            result = aComp.ListarComponentesLogrosNodos(Integer.parseInt(proy), Integer.parseInt(cuatrimestre));
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarComponentesMult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String proy = request.getParameter("proy");
        String area = request.getParameter("area");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.ListarComponentesMul(Integer.parseInt(proy), Integer.parseInt(area));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarMeta(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.ListarMetas(Integer.parseInt(comp));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarMetaRep(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.ListarMetasRep(Integer.parseInt(comp));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarIndicador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String meta = request.getParameter("meta");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.ListarIndicador(Integer.parseInt(meta));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarIndicadorRep(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String meta = request.getParameter("meta");
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.ListarIndicadorRep(Integer.parseInt(meta));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarComponente(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String componente = request.getParameter("txtnombreComp");
        String id = request.getParameter("idComponente");
        String cedula = request.getParameter("cedulaProyecto");
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        if (componente.isEmpty()) {
            result = "Debe ingresar el nombre del componente";
        } else {
            cComp.setComponente_nombre(componente);
            cComp.setComponente_id(Integer.parseInt(id));
            result = aComp.ModificarComponente(cComp);
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El componente con código: \"" + componente + "\" se modificó correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarComponenteLogros(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String id = request.getParameter("componente");
        String logros = request.getParameter("logros");
        String nudos = request.getParameter("nudos");
        String anio = request.getParameter("anio");
        String cuatrimestre = request.getParameter("cuatrimestre");
        Integer idL;
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        idL = aComp.existeNodos(Integer.parseInt(id), Integer.parseInt(cuatrimestre));
        if (logros.isEmpty()) {
            result = "Debe ingresar los logros alcanzados";
        } else if (nudos.isEmpty()) {
            result = "Debe ingresar los nudos criticos";
        } else {
            cComp.setComponente_nombre(nudos);
            cComp.setComponente_id(Integer.parseInt(id));
            cComp.setMeta_nombre(logros);
            cComp.setMe_cuatrimestre(Integer.parseInt(cuatrimestre));
            if (anio.equals("2020")) {
                result = aComp.ModificarComponenteLogros(cComp);
            } else {
                if (idL == null || idL == 0) {
                    cComp.setMeta_id(aComp.codigoSiguienteNodosL());
                    result = aComp.IngresarLogrosNodos(cComp);
                } else {
                    cComp.setMeta_id(idL);
                    result = aComp.ModificarLogrosNodos(cComp);
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarComponenteRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String componente = request.getParameter("txtnombreComp");
        String id = request.getParameter("idComponente");
        String cedula = request.getParameter("cedulaProyecto");
        String componenteid = request.getParameter("componenteidid");
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        Integer idComponente = aComp.codigoSiguienteComponente();
        if (componente.isEmpty()) {
            result = "Debe ingresar el nombre del componente";
        } else {
            cComp.setComponente_nombre(componente);
            cComp.setComponente_ag(intIdAreaGestion);
            if (componenteid.equals("0")) {
                cComp.setComponente_id(idComponente);
                cComp.setComponente_proyecto(Integer.parseInt(id));
                result = aComp.IngresarComponenteRep(cComp);
            } else {
                cComp.setComponente_id(Integer.parseInt(componenteid));
                result = aComp.ModificarComponente(cComp);
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El componente con código: \"" + componente + "\" se reprogramo correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarMeta(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String meta = request.getParameter("txtnombreMeta");
        String idmeta = request.getParameter("idMeta");
        String cedula = request.getParameter("cedulaProyecto");
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();

        if (meta.isEmpty()) {
            result = "Debe ingresar el nombre de la meta";
        } else {
            cComp.setMeta_nombre(meta);
            cComp.setMeta_id(Integer.parseInt(idmeta));
            result = aComp.ModificarMeta(cComp);
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("La meta con código: \"" + idmeta + "\" se modificó correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarMetaRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String meta = request.getParameter("txtnombreMeta");
        String idmeta = request.getParameter("idMeta");
        String cedula = request.getParameter("cedulaProyecto");
        String metaid = request.getParameter("metaidid");
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();
        Integer idMeta = aComp.codigoSiguienteMeta();

        if (meta.isEmpty()) {
            result = "Debe ingresar el nombre de la meta";
        } else {
            cComp.setMeta_nombre(meta);
            if (metaid.equals("0")) {
                cComp.setMeta_id(idMeta);
                cComp.setComponente_id(Integer.parseInt(idmeta));
                result = aComp.IngresarMetaRep(cComp);
            } else {
                cComp.setMeta_id(Integer.parseInt(metaid));
                result = aComp.ModificarMeta(cComp);
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("La meta con código: \"" + idmeta + "\" se reprogramó correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarIndicador(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String indicador = request.getParameter("idIndicador");
        String nombre = request.getParameter("txtnombreIndicador");
        String descripcion = request.getParameter("txtdescipcionIndicador");
        String tipo = request.getParameter("tipoIndicador");
        String ejecu = request.getParameter("txtindicadorejecutadovalor");
        String plan = request.getParameter("txtindicadorplanificadovalor");
        String valplan = request.getParameter("txtindicadorplanificado");
        String tiponum = request.getParameter("valorIndicador");
        String cedula = request.getParameter("cedulaProyecto");
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();

        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del indicador.";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descripción del indicador";
        } else {
            cComp.setIndicador_nombre(nombre);
            cComp.setIndicador_descripcion(descripcion);
            cComp.setIndicador_tipo(tipo);
            cComp.setIndicador_id(Integer.parseInt(indicador));
            if (tipo.equals("Cuantitativo")) {
                cComp.setIndicador_ejecutado(ejecu);
                cComp.setIndicador_planificado(plan);
                if (tiponum.equals("1")) {
                    cComp.setIndicador_numero(Double.parseDouble(valplan) / 100);
                } else {
                    cComp.setIndicador_numero(Double.parseDouble(valplan));
                }
                result = aComp.ModificarIndicadorFormula(cComp);
            } else {
                cComp.setIndicador_ejecutado("vacio");
                cComp.setIndicador_planificado("vacio");
                cComp.setIndicador_numero(0.0);
                result = aComp.ModificarIndicadorFormula(cComp);
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El indicador con código: \"" + indicador + "\" se modificó correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarIndicadorReprog(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String indicador = request.getParameter("idIndicador");
        String nombre = request.getParameter("txtnombreIndicador");
        String descripcion = request.getParameter("txtdescipcionIndicador");
        String tipo = request.getParameter("tipoIndicador");
        String ejecu = request.getParameter("txtindicadorejecutadovalor");
        String plan = request.getParameter("txtindicadorplanificadovalor");
        String valplan = request.getParameter("txtindicadorplanificado");
        String tiponum = request.getParameter("valorIndicador");
        String cedula = request.getParameter("cedulaProyecto");
        String ididIndicador = request.getParameter("ididIndicador");
        cComponenteMeta cComp = new cComponenteMeta();
        adComponenteMeta aComp = new adComponenteMeta();

        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del indicador.";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descripción del indicador";
        } else {
            Integer idInd = aComp.codigoSiguienteIndicador();
            cComp.setIndicador_nombre(nombre);
            cComp.setIndicador_descripcion(descripcion);
            cComp.setIndicador_tipo(tipo);
            if (tipo.equals("Cuantitativo")) {
                cComp.setIndicador_ejecutado(ejecu);
                cComp.setIndicador_planificado(plan);
                if (tiponum.equals("1")) {
                    cComp.setIndicador_numero(Double.parseDouble(valplan) / 100);
                } else {
                    cComp.setIndicador_numero(Double.parseDouble(valplan));
                }
                if (ididIndicador.equals("0")) {
                    cComp.setIndicador_id(idInd);
                    cComp.setMeta_id(Integer.parseInt(indicador));
                    result = aComp.IngresarIndicadorRepro(cComp);
                } else {
                    cComp.setIndicador_id(Integer.parseInt(ididIndicador));
                    result = aComp.ModificarIndicadorFormula(cComp);
                }
            } else {
                cComp.setIndicador_ejecutado("vacio");
                cComp.setIndicador_planificado("vacio");
                cComp.setIndicador_numero(0.0);
                if (ididIndicador.equals("0")) {
                    cComp.setIndicador_id(idInd);
                    cComp.setMeta_id(Integer.parseInt(indicador));
                    result = aComp.IngresarIndicadorRepro(cComp);
                } else {
                    cComp.setIndicador_id(Integer.parseInt(ididIndicador));
                    result = aComp.ModificarIndicadorFormula(cComp);
                }
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El indicador con código: \"" + indicador + "\" se reprogramó correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarIndicador(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String indicador = request.getParameter("idIndicador");
        String cedula = request.getParameter("cedulaProyecto");
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.EliminarIndicador(Integer.parseInt(indicador));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El indicador con código: \"" + indicador + "\" se eliminó correctamente.");
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

    private void EliminarIndicadorRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String indicador = request.getParameter("idIndicador");
        String cedula = request.getParameter("cedulaProyecto");
        String ididIndicador = request.getParameter("ididIndicador");
        adComponenteMeta aComp = new adComponenteMeta();

        Integer ind;
        if (ididIndicador.equals(0)) {
            ind = Integer.parseInt(indicador);
        } else {
            ind = Integer.parseInt(ididIndicador);
        }
        result = aComp.EliminarIndicadorReq(ind);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El indicador con código: \"" + indicador + "\" se reprogramó correctamente.");
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

    private void EliminarComponente(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String indicador = request.getParameter("idcomponente");
        String cedula = request.getParameter("cedulaProyecto");
        adComponenteMeta aComp = new adComponenteMeta();

        result = aComp.EliminarComponente(Integer.parseInt(indicador));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El componente con código: \"" + indicador + "\" se eliminó correctamente.");
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
}
