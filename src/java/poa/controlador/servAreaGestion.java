/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import poa.acceso.adAreaGestion;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cAreaGestion;
import poa.clases.cProyecto;
import poa.clases.cTransaccion;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author PC-D2
 */
public class servAreaGestion extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            HttpSession sesionOk = request.getSession(false);
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");

            switch (strAccion) {
                case "ListarAreasGestionHijas":
                    ListarAreasGestionHijas(request, response);
                    break;
                case "ListarArchivos":
                    ListarArchivos(request, response);
                    break;
                case "NombreAg":
                    NombreAg(request, response);
                    break;
                case "ListarActivar":
                    ListarActivar(request, response);
                    break;
                case "ModificarFechas":
                    ModificarFechas(request, response, intIdAreaGestion);
                    break;
                case "IngresarFechas":
                    IngresarFechas(request, response, intIdAreaGestion);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void ListarAreasGestionHijas(HttpServletRequest request, HttpServletResponse response) {
        List<cAreaGestion> lstAreasGestionHijas = new ArrayList<cAreaGestion>();
        try {
            String areaPadre = request.getParameter("areapadre");
            Integer intAreaGestionPadre = Integer.parseInt(areaPadre);
            lstAreasGestionHijas = obtenerAreasGestionHijas(intAreaGestionPadre);
            String json = new Gson().toJson(lstAreasGestionHijas);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void ListarArchivos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cProyecto> lstAreasGestionHijas = new ArrayList<cProyecto>();
        adAreaGestion ad = new adAreaGestion();
        lstAreasGestionHijas = ad.listarTipoArchivos();

        String json = new Gson().toJson(lstAreasGestionHijas);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarActivar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cProyecto> lstAreasGestionHijas = new ArrayList<cProyecto>();
        adAreaGestion ad = new adAreaGestion();
        lstAreasGestionHijas = ad.listarActivar();

        String json = new Gson().toJson(lstAreasGestionHijas);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void NombreAg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String ag = request.getParameter("ag");
        adAreaGestion ad = new adAreaGestion();
        result = ad.nombreAreaG(Integer.parseInt(ag));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static List<cAreaGestion> obtenerAreasGestionHijas(Integer intCodigoUnidad) {
        List<cAreaGestion> result = new ArrayList<cAreaGestion>();
        String SQL = "SELECT *\n"
                + "FROM public.area_gestion\n"
                + "JOIN tipo_area_gestion ON ag_tag=tag_id\n"
                + "WHERE are_ag_id=" + intCodigoUnidad + " and tag_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cAreaGestion oAreaGestion = new cAreaGestion();
                        oAreaGestion.setAg_id(rsTecho.getInt("ag_id"));
                        oAreaGestion.setAg_nombre(rsTecho.getString("ag_nombre"));
                        oAreaGestion.setAg_estado(rsTecho.getInt("ag_estado"));
                        oAreaGestion.setAg_alias(rsTecho.getString("ag_alias"));
                        oAreaGestion.setTag_id(rsTecho.getInt("tag_id"));
                        oAreaGestion.setTag_nombre(rsTecho.getString("tag_nombre"));
                        oAreaGestion.setTag_estado(rsTecho.getInt("tag_estado"));
                        result.add(oAreaGestion);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        } finally {
            return result;
        }
    }

    private void ModificarFechas(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String cedula = request.getParameter("cedula");
        String tipo = request.getParameter("tipo");
        String fecha = request.getParameter("fecha");
        String observacion = request.getParameter("observacion");
        String nombre = request.getParameter("nombreFecha");
        String anio = request.getParameter("anio");
        String eje=request.getParameter("ejecucion");
        String result;

        cProyecto cProy = new cProyecto();
        adAreaGestion ad = new adAreaGestion();

        cProy.setTp_id(Integer.parseInt(tipo));
        cProy.setDeudas_contrato(fecha);
        cProy.setEstado_observacion(observacion);
        cProy.setTp_nombre(nombre);
        cProy.setProyecto_anio(Integer.parseInt(anio));
        cProy.setDeudas_id(Integer.parseInt(eje));
        result = ad.modificarFechas(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("Se modificó la fecha del tipo: \"" + tipo + "\" con observación: \"" + observacion + "\".");
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
    
    private void IngresarFechas(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String cedula = request.getParameter("cedula");
        String tipo = request.getParameter("tipo");
        String fecha = request.getParameter("fecha");
        String observacion = request.getParameter("observacion");
        String anio=request.getParameter("selAnio");
        String eje=request.getParameter("ejecucion");
        String result;

        cProyecto cProy = new cProyecto();
        adAreaGestion ad = new adAreaGestion();

        cProy.setTp_id(ad.codigoSiguienteFechas());
        cProy.setDeudas_contrato(fecha);
        cProy.setEstado_observacion(observacion);
        cProy.setTp_nombre(tipo);
        cProy.setProyecto_anio(Integer.parseInt(anio));
        cProy.setDeudas_id(Integer.parseInt(eje));
        result = ad.ingresarFechas(cProy);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("Se ingreso la fecha del tipo: \"" + tipo + "\" con observación: \"" + observacion + "\".");
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

}
