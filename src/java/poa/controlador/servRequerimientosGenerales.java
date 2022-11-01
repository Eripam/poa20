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
import poa.acceso.adRequerimientosGenerales;
import poa.clases.cRequerimientosGenerales;

/**
 *
 * @author PC-D2
 */
public class servRequerimientosGenerales extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            HttpSession sesionOk = request.getSession(false);
            String strCedula = (String) sesionOk.getAttribute("Cedula");
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            switch (strAccion) {
                case "IngresarRequerimientoGeneral":
                    IngresarRequerimientoGeneral(request, response);
                    break;
                case "ListarRequerimientoGenerales":
                    ListarRequerimientosGenerales(request, response);
                    break;
                case "ListarRequerimientosGeneralesAnio":
                    ListarRequerimientosGeneralesAnio(request, response);
                    break;
                case "ModificarRequerimientoGeneral":
                    ModificarRequerimientoGeneral(request, response);
                    break;
                case "ListarAreasGestionDependientes":
                    ListarAreasGestionDependientes(request, response);
                    break;
                case "DetallesRequerimientosGenerales":
                    DetallesRequerimientosGenerales(request, response);
                    break;
            }
        }
    }

    private void IngresarRequerimientoGeneral(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombreRequerimientoGeneral");
        String descripcion = request.getParameter("descripcionRequerimientoGeneral");
        String cpc = request.getParameter("cpcRequerimientoGeneral");
        double precio = Double.parseDouble(request.getParameter("precioRequerimientoGeneral"));
        Integer anio = Integer.parseInt(request.getParameter("selAnio"));
        Integer ag = Integer.parseInt(request.getParameter("idAgObEs"));
        Integer unidad = Integer.parseInt(request.getParameter("selunidad1"));
        String result;

        cRequerimientosGenerales oRequerimiento = new cRequerimientosGenerales();
        adRequerimientosGenerales aRequerimiento = new adRequerimientosGenerales();
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descipción";
        } else if (cpc.isEmpty()) {
            result = "Debe ingresar el cpc";
        } else if (precio == 0) {
            result = "Debe ingresar el precio";
        } else {

            oRequerimiento.setRg_nombre(nombre);
            oRequerimiento.setRg_descripcion(descripcion);
            oRequerimiento.setRg_costo_unitario(precio);
            oRequerimiento.setRg_anio(anio);
            oRequerimiento.setAg_id(ag);
            oRequerimiento.setRg_cpc(cpc);
            oRequerimiento.setRg_unidad(unidad);
            result = aRequerimiento.ingresarRequerimientosGenerales(oRequerimiento);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarRequerimientosGenerales(HttpServletRequest request, HttpServletResponse response) {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cRequerimientosGenerales> lstRequerimientosGenerales = new ArrayList<>();
        try {
            adRequerimientosGenerales adr = new adRequerimientosGenerales();
            lstRequerimientosGenerales = adr.listarRequerimientosGenerales(Integer.parseInt(ag), Integer.parseInt(anio));

            String json = new Gson().toJson(lstRequerimientosGenerales);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println("errrror" + e.getMessage());
        }
    }

    private void ListarRequerimientosGeneralesAnio(HttpServletRequest request, HttpServletResponse response) {
        String anio = request.getParameter("anio");
        List<cRequerimientosGenerales> lstRequerimientosGenerales = new ArrayList<>();
        try {
            adRequerimientosGenerales adr = new adRequerimientosGenerales();
            lstRequerimientosGenerales = adr.listarRequerimientosGeneralesAnio(Integer.parseInt(anio));

            String json = new Gson().toJson(lstRequerimientosGenerales);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println("errrror" + e.getMessage());
        }
    }

    private void ModificarRequerimientoGeneral(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombreRequerimientoGeneral");
        String descripcion = request.getParameter("descripcionRequerimientoGeneral");
        double precio = Double.parseDouble(request.getParameter("precioRequerimientoGeneral"));
        int id = Integer.parseInt(request.getParameter("idRequerimientoGeneral"));
        String cpc = request.getParameter("cpcRequerimientoGeneral");
        Integer unidad = Integer.parseInt(request.getParameter("selunidad1"));
        int anio = Integer.parseInt(request.getParameter("selAnio"));
        String result;

        cRequerimientosGenerales oRequerimiento = new cRequerimientosGenerales();
        adRequerimientosGenerales aRequerimiento = new adRequerimientosGenerales();
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descipción";
        } else if (cpc.isEmpty()) {
            result = "Debe ingresar el cpc";
        } else if (precio == 0) {
            result = "Debe ingresar el precio";
        } else {
            oRequerimiento.setRg_id(id);
            oRequerimiento.setRg_nombre(nombre);
            oRequerimiento.setRg_descripcion(descripcion);
            oRequerimiento.setRg_costo_unitario(precio);
            oRequerimiento.setAg_id(anio);
            oRequerimiento.setRg_unidad(unidad);
            oRequerimiento.setRg_cpc(cpc);
            result = aRequerimiento.modificarRequerimientosGenerales(oRequerimiento);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarAreasGestionDependientes(HttpServletRequest request, HttpServletResponse response) {
        List<cRequerimientosGenerales> lstAreasGestionDependientes = new ArrayList<>();
        int id = Integer.parseInt(request.getParameter("idRequerimientoGeneral"));
        cRequerimientosGenerales oRequerimiento = new cRequerimientosGenerales();
        oRequerimiento.setRg_id(id);
        try {
            adRequerimientosGenerales adr = new adRequerimientosGenerales();
            lstAreasGestionDependientes = adr.listarAreasGestionDependientes(oRequerimiento);

            String json = new Gson().toJson(lstAreasGestionDependientes);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println("errrror" + e.getMessage());
        }
    }

    private void DetallesRequerimientosGenerales(HttpServletRequest request, HttpServletResponse response) {
        List<cRequerimientosGenerales> lstAreasGestionDependientes = new ArrayList<cRequerimientosGenerales>();
        int id = Integer.parseInt(request.getParameter("reqgeneral"));
        try {
            adRequerimientosGenerales adr = new adRequerimientosGenerales();
            lstAreasGestionDependientes = adr.listaRequerimientoGeneralDetalle(id);

            String json = new Gson().toJson(lstAreasGestionDependientes);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            System.out.println("errrror" + e.getMessage());
        }
    }

}
