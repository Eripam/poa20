/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import poa.acceso.adTecho;
import poa.acceso.adTipoProyecto;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cTecho;
import poa.clases.cTipoProyecto;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
public class servTecho extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            HttpSession sesionOk = request.getSession(false);
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            String cedula = (String) sesionOk.getAttribute("cedulaUsuario");

            switch (strAccion) {
                case "IngresarTechoIns":
                    IngresarTechoIns(request, response);
                    break;
                case "ModificarTechoIns":
                    ModificarTechoIns(request, response, cedula, intIdAreaGestion);
                    break;
                case "IngresarTechoUni":
                    IngresarTechoUni(request, response);
                    break;
                case "ListarTechoIns":
                    ListarTechoIns(request, response);
                    break;
                case "ListarTechoPresupuestario":
                    ListarTechoPresupuestario(request, response);
                    break;
                case "ListarTechoAdicional":
                    ListarTechoAdicional(request, response);
                    break;
                case "ListarTechoU":
                    ListarTechoU(request, response);
                    break;
                case "ListarTechoUni":
                    ListarTechoUni(request, response);
                    break;
                case "ListarAreasGestionUnidades":
                    ListarAreasGestionUnidades(request, response);
                    break;
                case "ModificarTechoU":
                    ModificarTechoU(request, response);
                    break;
                case "ModificarTechoUni":
                    ModificarTechoUni(request, response);
                    break;
            }
        }
    }

    private void IngresarTechoIns(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("anioin");
        String cantidad = request.getParameter("cantidadin");
        String result;
        cTecho oTecho = new cTecho();
        adTecho aTecho = new adTecho();
        if (anio.isEmpty()) {
            result = "Debe ingresar el año";
        } else if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad";
        } else {
            oTecho.setTecho_fecha(Integer.parseInt(anio));
            oTecho.setTecho_inicial(Double.parseDouble(cantidad));
            oTecho.setTecho_total(Double.parseDouble(cantidad));
            result = aTecho.ingresarTechoInstitucional(oTecho);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarTechoIns(HttpServletRequest request, HttpServletResponse response, String cedula, Integer intIdAreaGestion) throws IOException {
        String anio = request.getParameter("anioin");
        String cantidad = request.getParameter("cantidadin");
        String result;
        cTecho oTecho = new cTecho();
        adTecho aTecho = new adTecho();
        if (anio.isEmpty()) {
            result = "Debe ingresar el año";
        } else if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad";
        } else {
            oTecho.setTecho_fecha(Integer.parseInt(anio));
            oTecho.setTecho_inicial(Double.parseDouble(cantidad));
            oTecho.setTecho_total(Double.parseDouble(cantidad));
            result = aTecho.modificarTechoInstitucional(oTecho);
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El techo del año: \"" + anio + "\" se modificó correctamente a la cantidad  \"" + cantidad + "\".");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarTechoUni(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("aniou");
        String ag = request.getParameter("tipoun");
        String tipo = request.getParameter("unidadun");
        String cantidad = request.getParameter("cantidadun");
        String result;
        cTecho oTecho = new cTecho();
        adTecho aTecho = new adTecho();
        if (anio.isEmpty()) {
            result = "Debe ingresar el año";
        } else if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad";
        } else if (ag.isEmpty()) {
            result = "Debe seleccionar la unidad";
        } else if (tipo.isEmpty()) {
            result = "Debe seleccionar el tipo de proyecto";
        } else {
            oTecho.setTecho_fecha(Integer.parseInt(anio));
            oTecho.setTecho_inicial(Double.parseDouble(cantidad));
            oTecho.setTecho_ag(Integer.parseInt(ag));
            oTecho.setTecho_tp(Integer.parseInt(tipo));
            result = aTecho.ingresarTechoUnidad(oTecho);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void ModificarTechoUni(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("txtaniou");
        String ag = request.getParameter("txttipoun");
        String tipo = request.getParameter("txtunidadun");
        String cantidad = request.getParameter("cantidadun");
        String result;
        cTecho oTecho = new cTecho();
        adTecho aTecho = new adTecho();
        if (anio.isEmpty()) {
            result = "Debe ingresar el año";
        } else if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad";
        } else if (ag.isEmpty()) {
            result = "Debe seleccionar la unidad";
        } else if (tipo.isEmpty()) {
            result = "Debe seleccionar el tipo de proyecto";
        } else {
            oTecho.setTecho_fecha(Integer.parseInt(anio));
            oTecho.setTecho_inicial(Double.parseDouble(cantidad));
            oTecho.setTecho_ag(Integer.parseInt(ag));
            oTecho.setTecho_tp(Integer.parseInt(tipo));
            result = aTecho.modificarTechoUnidad(oTecho);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarTechoIns(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cTecho> result = new ArrayList<cTecho>();
        adTecho aTecho = new adTecho();

        result = aTecho.listarTechoIns();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarTechoPresupuestario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cTecho> lstTechos = new ArrayList<cTecho>();
        try {
            String tipousuario = request.getParameter("tipousuario");
            String areapadre = request.getParameter("areapadre");
            String area = request.getParameter("area");
            String tipoproyecto = request.getParameter("tipoproyecto");
            String anio = request.getParameter("anio");
            int intAreaGestion = Integer.parseInt(areapadre);

            lstTechos = obtenerTechosAsignados(intAreaGestion, Integer.parseInt(anio));
            lstTechos = obtenerTechosAsignadosCarrera(lstTechos, intAreaGestion, Integer.parseInt(anio));
            lstTechos = obtenerTechosPlanificados(lstTechos, intAreaGestion, Integer.parseInt(anio));

            String json = new Gson().toJson(lstTechos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void ListarTechoAdicional(HttpServletRequest request, HttpServletResponse response) {
        List<cTecho> lstTechos = new ArrayList<cTecho>();
        try {
            String areapadre = request.getParameter("areapadre");
            String anio = request.getParameter("anio");
            int intAreaGestion = Integer.parseInt(areapadre);
            lstTechos = obtenerTechosAsignados(intAreaGestion, Integer.parseInt(anio));
            lstTechos = obtenerTechosAsignadosCarrera(lstTechos, intAreaGestion, Integer.parseInt(anio));
            lstTechos = obtenerTechosAdicionales(lstTechos, intAreaGestion, Integer.parseInt(anio));
            String json = new Gson().toJson(lstTechos);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<cTecho> obtenerTechosAsignados(Integer intIdAreaGestion, Integer anio) {
        List<cTecho> lstTechos = new ArrayList<cTecho>();
        List<cTipoProyecto> lstTiposProyecto = new ArrayList<cTipoProyecto>();
        lstTechos = adTecho.ListarTechoTotalPresupuestarioPorUnidad(intIdAreaGestion, anio);
        lstTiposProyecto = adTipoProyecto.ListarTiposProyecto(intIdAreaGestion, anio);
        if (!lstTiposProyecto.isEmpty()) {
            for (cTipoProyecto objTipoProyecto : lstTiposProyecto) {
                Boolean blnEncontrado = false;
                cTecho objNuevoTecho = new cTecho();
                if (!lstTechos.isEmpty()) {
                    for (cTecho objTecho : lstTechos) {
//                            blnEncontrado = false;
                        if (objTecho.getTecho_tp().equals(Integer.valueOf(objTipoProyecto.getIntId()))) {
                            blnEncontrado = true;
                            break;
                        }
                    }
                } else {
                    System.out.println("lista de Techos vacia");
                }
                if (!blnEncontrado) {
                    objNuevoTecho.setTecho_tp(objTipoProyecto.getIntId());
                    objNuevoTecho.setTp_nombre(objTipoProyecto.getStrNombre());
                    objNuevoTecho.setTecho_total(0.00);
                    lstTechos.add(objNuevoTecho);
                }
            }
        }

        return lstTechos;
    }

    /**
     * Método para obtener una listado de los techos Asiganados a una carrera
     * dado la carrera
     *
     * @param lstTechos
     * @return
     */
    public static List<cTecho> obtenerTechosAsignadosCarrera(List<cTecho> lstTechos, Integer intIdAreaGestion, Integer anio) {
        List<cTecho> lstTechosAsignadoCarreras = new ArrayList<cTecho>();
        lstTechosAsignadoCarreras = adTecho.ListarTechoAsignadoAcarreraPorUnidad(intIdAreaGestion, anio);
        if (!lstTechos.isEmpty()) {
            for (cTecho objTecho : lstTechos) {
                if (!lstTechosAsignadoCarreras.isEmpty()) {
                    for (cTecho objAsignadoCarrera : lstTechosAsignadoCarreras) {
                        if (objTecho.getTecho_tp() == Integer.valueOf(objAsignadoCarrera.getTecho_tp())) {
                            objTecho.setTecho_asignado_carrera(objAsignadoCarrera.getTecho_asignado_carrera());
                            break;
                        } else {
                            objTecho.setTecho_asignado_carrera(0.00);
                        }

                    }
                } else {
                    objTecho.setTecho_asignado_carrera(0.00);
                }
            }
        }
        return lstTechos;
    }

    /**
     * Método para obtener un listado de los Techos planificados dado una lista
     * y la unidad de gestion.
     *
     * @param lstTechos
     * @param intIdAreaGestion
     * @return
     */
    public static List<cTecho> obtenerTechosPlanificados(List<cTecho> lstTechos, Integer intIdAreaGestion, Integer anio) {
        List<cTecho> lstTechosPlanificados = new ArrayList<cTecho>();
        lstTechosPlanificados = adTecho.ListarTechoPlanificadoPorUnidadPadreHijos(intIdAreaGestion, anio);
        if (!lstTechos.isEmpty()) {
            for (cTecho objTecho : lstTechos) {
                if (!lstTechosPlanificados.isEmpty()) {
                    for (cTecho objTechoPlanificado : lstTechosPlanificados) {
                        if (objTechoPlanificado.getTecho_tp().equals(objTecho.getTecho_tp())) {
                            objTecho.setTecho_planificado(objTechoPlanificado.getTecho_planificado());
                            objTecho.setTecho_reforma(objTechoPlanificado.getTecho_reforma());
                            break;
                        } else {
                            objTecho.setTecho_planificado(0.00);
                        }
                    }
                } else {
                    objTecho.setTecho_planificado(0.00);

                }
            }
        }
        return lstTechos;
    }

    public static List<cTecho> obtenerTechosAdicionales(List<cTecho> lstTechos, Integer intIdAreaGestion, Integer anio) {
        List<cTecho> lstTechosPlanificados = new ArrayList<cTecho>();
        lstTechosPlanificados = adTecho.ListarTechoPlanificadoPorUnidad(intIdAreaGestion, anio);
        if (!lstTechos.isEmpty()) {
            for (cTecho objTecho : lstTechos) {
                Double dblTechoAdicional = objTecho.getTecho_total() - objTecho.getTecho_asignado_carrera();
                objTecho.setTecho_total(dblTechoAdicional);
                if (!lstTechosPlanificados.isEmpty()) {
                    for (cTecho objTechoPlanificado : lstTechosPlanificados) {
                        if (objTecho.getTecho_tp().equals(objTechoPlanificado.getTecho_tp())) {
                            objTecho.setTecho_planificado(objTechoPlanificado.getTecho_planificado());
                            objTecho.setTecho_reforma(objTechoPlanificado.getTecho_reforma());
                            break;
                        } else {
                            objTecho.setTecho_planificado(0.00);
                            objTecho.setTecho_reforma(0.00);
                        }
                    }
                } else {
                    objTecho.setTecho_planificado(0.00);
                }
            }
        }
        return lstTechos;
    }

    private void ListarTechoU(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String area = request.getParameter("area");
        String anio = request.getParameter("anio");
        List<cTecho> result = new ArrayList<cTecho>();
        adTecho aTecho = new adTecho();
        result = aTecho.ListarTechoUnidad(Integer.parseInt(area), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarTechoUni(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cTecho> result = new ArrayList<cTecho>();
        String anio = request.getParameter("anio");
        adTecho aTecho = new adTecho();

        result = aTecho.listarAreaGestionTecho(1, Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarAreasGestionUnidades(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cTecho> result = new ArrayList<cTecho>();
        String tipo = request.getParameter("tipo");
        String anio = request.getParameter("anio");
        String financiamiento = request.getParameter("financiamiento");
        String pres = request.getParameter("presupuesto");
        adTecho aTecho = new adTecho();

        result = aTecho.ListarPresupuestoPriorizadoporUnidadesR(Integer.parseInt(tipo), Integer.parseInt(financiamiento), Integer.parseInt(anio), pres);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarTechoU(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("aniou");
        String ag = request.getParameter("tipoun");
        String tipo = request.getParameter("unidadun");
        String cantidad = request.getParameter("cantidadun");
        String agh = request.getParameter("aghidden");
        String tipoh = request.getParameter("tipohidden");

//        String reforma = request.getParameter("reformaTU");
//        String signo = request.getParameter("signoTU");
        String result;
        cTecho oTecho = new cTecho();
        adTecho aTecho = new adTecho();
        if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad";
        } else {
            oTecho.setTecho_fecha(Integer.parseInt(anio));
            oTecho.setTecho_inicial(Double.parseDouble(cantidad));
            oTecho.setTecho_ag(Integer.parseInt(ag));
            oTecho.setTecho_tp(Integer.parseInt(tipo));
            oTecho.setTecho_total(Double.parseDouble(cantidad));
            oTecho.setTecho_reforma(0.0);
            oTecho.setTecho_signo("0");
//            if (signo == null) {
//                
//            } else {
//                oTecho.setTecho_reforma(Double.parseDouble(reforma));
//                oTecho.setTecho_signo(signo);
//                if (signo.equals("1")) {
//                    oTecho.setTecho_total(Double.parseDouble(cantidad) + Double.parseDouble(reforma));
//                } else if (signo.equals("2")) {
//                    oTecho.setTecho_total(Double.parseDouble(cantidad) - Double.parseDouble(reforma));
//                }
//            }
            result = aTecho.ModificarTechoUnidad(oTecho, Integer.parseInt(agh), Integer.parseInt(tipoh));
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
