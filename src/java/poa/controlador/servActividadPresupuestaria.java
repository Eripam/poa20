/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import poa.acceso.adActividadPresupuestaria;
import poa.clases.cActividadPresupuestaria;

/**
 *
 * @author Desarrollo
 */
public class servActividadPresupuestaria extends HttpServlet {

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

            switch (strAccion) {
                case "ListarActividadesPresu":
                    listarActividadesPresu(request, response);
                    break;
            }
        }
    }

    private void listarActividadesPresu(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String objetivo = request.getParameter("objetivo");
        String estado = request.getParameter("estado");
        List<cActividadPresupuestaria> result = new ArrayList<cActividadPresupuestaria>();
        adActividadPresupuestaria adActPresu = new adActividadPresupuestaria();

        result = adActPresu.listarActividadesPresupuestarias(Integer.parseInt(objetivo), Integer.parseInt(estado));
        String json = new Gson().toJson(result);      
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
