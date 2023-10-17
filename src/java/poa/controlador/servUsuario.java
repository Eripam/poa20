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
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.acceso.adUsuario;
import poa.acceso.adVinculacion;
import poa.clases.cAreaGestion;
import poa.clases.cTransaccion;
import poa.clases.cUsuario;
import poa.conexion.cConexion;

/**
 *
 * @author Erika Arévalo
 */
public class servUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            HttpSession sesionOk = request.getSession(false);
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            String strCedula = (String) sesionOk.getAttribute("cedulaUsuario");

            switch (strAccion) {
                case "VerificacionUsuario":
                    VerificacionUsuario(request, response);
                    break;
                case "Redirigir":
                    RedirigirUsuario(request, response);
                    break;
                case "IngresarUsuario":
                    IngresarUsuario(request, response, strCedula, intIdAreaGestion);
                    break;
                case "ListarUsuario":
                    ListarUsuario(request, response);
                    break;
                case "ListarUsuarioIDV":
                    ListarUsuarioIDV(request, response);
                    break;
                case "ListarUsuariosAdm":
                    ListarUsuariosAdm(request, response);
                    break;
                case "ListarUsuarioDir":
                    ListarUsuarioDir(request, response);
                    break;
                case "listarTipoIntegrantes":
                    listarTipoIntegrantes(request, response);
                    break;
                case "ModificarUsuario":
                    ModificarUsuario(request, response);
                    break;
                case "EliminarUsuario":
                    EliminarUsuario(request, response);
                    break;
                case "DesactivarUsuaurio":
                    DesactivarUsuaurio(request, response, intIdAreaGestion);
                    break;
                case "BuscarCedulaCentralizada":
                    BuscarCedulaCentralizada(request, response, intIdAreaGestion);
                    break;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");

            switch (strAccion) {
                case "Redirigir":
                    RedirigirUsuario(request, response);
                    break;
                case "CerrarSesion":
                    CerrarSesion(request, response);
                    break;
            }
        }
    }

    private void VerificacionUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Boolean result;
        String strCedula = request.getParameter("cedula");
        String strNombre = request.getParameter("nombre");

        adUsuario aUsuario = new adUsuario();
        cUsuario cUsuario = new cUsuario();
        cUsuario objLogin = new cUsuario();

        result = aUsuario.obtenerUsuarioLogin(strCedula);

        if (result) {
            HttpSession sesionOk = request.getSession();

            List<cUsuario> lstTiposUsuario = new ArrayList<cUsuario>();
            lstTiposUsuario = aUsuario.ListaTiposUsuarioLogin(strCedula);
            if (!lstTiposUsuario.isEmpty()) {
                for (cUsuario obj : lstTiposUsuario) {
                    if (lstTiposUsuario.indexOf(obj) == 0) {
                        objLogin.setTu_id(obj.getTu_id());
                        objLogin.setTu_nombre(obj.getTu_nombre());
                        sesionOk.setAttribute("idTipoUsuario", objLogin.getTu_id());
                        sesionOk.setAttribute("nombreTipoUsuario", objLogin.getTu_nombre());
                        sesionOk.setAttribute("idAreaGestion", obj.getAg().getAg_id());
                        sesionOk.setAttribute("nombreAreaGestion", obj.getAg().getAg_nombre());
                        sesionOk.setAttribute("aliasAreaGestion", obj.getAg().getAg_alias());
                        sesionOk.setAttribute("idTipoAreaGestion", obj.getAg().getTag_id());
                        sesionOk.setAttribute("nombreTipoAreaGestion", obj.getAg().getTag_nombre());
                        sesionOk.setAttribute("cedulaUsuario", obj.getLogin_cedula());
                        sesionOk.setAttribute("anioplan", obj.getAnio());
                    }
                }
            }
        }

        cUsuario.setLogin_cedula(strCedula);
        cUsuario.setLogin_nombre(strNombre);

        aUsuario.ingresarLogin(cUsuario);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void RedirigirUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer intIdTipoUsuario = Integer.valueOf(request.getParameter("idTipoUsuario"));
            String strNombreTipoUsuario = String.valueOf(request.getParameter("nombreTipoUsuario"));
            Integer intIdAreaGestion = Integer.valueOf(request.getParameter("idAreaGestion"));
            String strNombreAreaGestion = String.valueOf(request.getParameter("nombreAreaGestion"));
            String strAliasAreaGestion = String.valueOf(request.getParameter("aliasAreaGestion"));
            Integer intIdTipoAreaGestion = Integer.valueOf(request.getParameter("idTipoAG"));
            String strNombreTipoAreaGestion = String.valueOf(request.getParameter("nombreTipoAG"));
            Integer anioplan = Integer.valueOf(request.getParameter("anioplan"));
            HttpSession sesionOk = request.getSession();
            if (intIdTipoUsuario != null && strNombreTipoUsuario != null) {
                sesionOk.setAttribute("idTipoUsuario", intIdTipoUsuario);
                sesionOk.setAttribute("nombreTipoUsuario", strNombreTipoUsuario);
                sesionOk.setAttribute("idAreaGestion", intIdAreaGestion);
                sesionOk.setAttribute("nombreAreaGestion", strNombreAreaGestion);
                sesionOk.setAttribute("aliasAreaGestion", strAliasAreaGestion);
                sesionOk.setAttribute("idTipoAreaGestion", intIdTipoAreaGestion);
                sesionOk.setAttribute("nombreTipoAreaGestion", strNombreTipoAreaGestion);
                sesionOk.setAttribute("anioplan", anioplan);
                response.sendRedirect("protected/pWelcome.jsp");
            } else {
                response.sendRedirect("protected/pVerificar.jsp");
            }
        } catch (Exception e) {
            System.out.println("Error en el controlador: " + e.getMessage() + "; clase: " + e.getClass());
        }
    }

    private void CerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession ssnUsuario = request.getSession(false);
        //HttpSession ssnUnidad = request.getSession(false);

        if (ssnUsuario != null) {
            ssnUsuario.invalidate();
            //if(ssnUnidad.getAttribute("objUnidadSesion")!=null){ssnUnidad.invalidate();}
            //request.getRequestDispatcher("inicio.jsp").include(request, response);  
            //response.sendRedirect("https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri=https://seguridad.espoch.edu.ec/cas/logout?service=http://localhost:8080/poa20/");
            response.sendRedirect("https://login.microsoftonline.com/common/oauth2/logout?post_logout_redirect_uri=https://seguridad.espoch.edu.ec/cas/logout?service=https://planificacion.espoch.edu.ec");
        }
    }

    private void IngresarUsuario(HttpServletRequest request, HttpServletResponse response, String strcedula, Integer intIdAreaGestion) throws IOException {
        String nombre = request.getParameter("nombreUsuario");
        String cedula = request.getParameter("cedulaUsuario");
        String titulo = request.getParameter("tituloUsuario");
        String agAsignadas[] = request.getParameterValues("ag");
        String tipo = request.getParameter("tipou");
        String ag = request.getParameter("agu");
        Boolean asignado;

        List<cUsuario> listaUsuarios = new ArrayList<>();

        cUsuario oUsuario = new cUsuario();
        adUsuario adUsuario = new adUsuario();
        cAreaGestion oArea = new cAreaGestion();
        String result;
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre";
        } else if (cedula.isEmpty()) {
            result = "Debe ingresar la cédula";
        } else if (titulo.isEmpty()) {
            result = "Debe ingresar el titulo";
        } else if (agAsignadas == null && (tipo.equals("10") || tipo.equals("11") || tipo.equals("16") || tipo.equals("17") || tipo.equals("26"))) {
            result = "Debe ingresar las unidades";
        } else {
            oUsuario.setLogin_nombre(nombre);
            oUsuario.setLogin_cedula(cedula);
            oUsuario.setTu_id(Integer.parseInt(tipo));
            oArea.setAg_id(Integer.parseInt(ag));
            oUsuario.setAg(oArea);
            oUsuario.setAg_nombre(titulo);
            listaUsuarios = adUsuario.ListarUsuarioComprasAreas(oUsuario);
            if (listaUsuarios.isEmpty()) {
                asignado = adUsuario.verificarUsuarioAsignar(oUsuario);
                if (!asignado) {
                    asignado = adUsuario.verificarUsuarioA(oUsuario);
                    if (!asignado) {
                        result = adUsuario.ingresarUsuario(oUsuario);
                        if (result.equals("Correcto")) {
                            result = adUsuario.AsignarUsuario(oUsuario);
                            if (result.equals("Correcto") && (tipo.equals("10") || tipo.equals("11") || tipo.equals("16") || tipo.equals("17")) || tipo.equals("26")) {
                                for (String agAsignadas1 : agAsignadas) {
                                    oArea.setAg_id(Integer.parseInt(agAsignadas1));
                                    oUsuario.setAg(oArea);
                                    oUsuario.setLogin_cedula(cedula);
                                    result = adUsuario.AsignarUsuarioAreas(oUsuario);
                                }
                            }
                        }
                    } else {
                        result = adUsuario.AsignarUsuario(oUsuario);
                        if (result.equals("Correcto") && (tipo.equals("10") || tipo.equals("11") || tipo.equals("16") || tipo.equals("17"))) {
                            for (String agAsignadas1 : agAsignadas) {
                                oArea.setAg_id(Integer.parseInt(agAsignadas1));
                                oUsuario.setAg(oArea);
                                oUsuario.setLogin_cedula(cedula);
                                result = adUsuario.AsignarUsuarioAreas(oUsuario);
                            }
                        }
                    }
                } else {
                    result = "Error usuario ya ingresado.";
                }
            } else {
                asignado = adUsuario.verificarUsuarioAsignar(oUsuario);
                if (!asignado) {
                    result = adUsuario.AsignarUsuario(oUsuario);
                    if (result.equals("Correcto") && (tipo.equals("10") || tipo.equals("16") || tipo.equals("17"))) {
                        for (String agAsignadas1 : agAsignadas) {
                            oArea.setAg_id(Integer.parseInt(agAsignadas1));
                            oUsuario.setAg(oArea);
                            oUsuario.setLogin_cedula(cedula);
                            result = adUsuario.AsignarUsuarioAreas(oUsuario);
                        }
                    }
                } else {
                    result = adUsuario.ModificarUsuario(oUsuario);
                    if (result.equals("Correcto") && (tipo.equals("10") || tipo.equals("16") || tipo.equals("17"))) {
                        for (String agAsignadas1 : agAsignadas) {
                            oArea.setAg_id(Integer.parseInt(agAsignadas1));
                            oUsuario.setAg(oArea);
                            oUsuario.setLogin_cedula(cedula);
                            result = adUsuario.AsignarUsuarioAreas(oUsuario);
                        }
                    }
                }
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_cedula(strcedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(1);
            objTransaccion.setTransaccion_descripcion("El usuario cédula: \"" + cedula + "\" se ingresó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adUsuario aUsuario = new adUsuario();
        String tipo = request.getParameter("tipo");
        List<cUsuario> result = new ArrayList<cUsuario>();
        result = aUsuario.ListarUsuarioCompras(Integer.parseInt(tipo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarUsuarioIDV(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adUsuario aUsuario = new adUsuario();
        String tipo = request.getParameter("tipo");
        List<cUsuario> result = new ArrayList<cUsuario>();
        result = aUsuario.ListarUsuarioIDV(Integer.parseInt(tipo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarUsuariosAdm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adUsuario aUsuario = new adUsuario();
        List<cUsuario> result = new ArrayList<cUsuario>();
        result = aUsuario.ListarUsuarioAdm();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarUsuarioDir(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adUsuario aUsuario = new adUsuario();
        String tipo = request.getParameter("tipo");
        String area = request.getParameter("area");
        List<cUsuario> result = new ArrayList<cUsuario>();
        result = aUsuario.ListarUsuarioDirector(Integer.parseInt(tipo), Integer.parseInt(area));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void listarTipoIntegrantes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        adUsuario aUsuario = new adUsuario();
        String objetivo = request.getParameter("objetivo");
        List<cUsuario> result = new ArrayList<cUsuario>();
        result = aUsuario.ListaTipoUsuarios(Integer.parseInt(objetivo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nombre = request.getParameter("nombreUsuario");
        String cedula = request.getParameter("cedulaUsuario");
        String agAsignadas[] = request.getParameterValues("ag");
        String titulo = request.getParameter("tituloUsuario");
        String tipo = request.getParameter("tipou");
        String ag = request.getParameter("agu");
        String estado=request.getParameter("selectEstadoU");
        String result;

        cUsuario oUsuario = new cUsuario();
        adUsuario adUsuario = new adUsuario();
        cAreaGestion oArea = new cAreaGestion();

        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre";
        } else if (cedula.isEmpty()) {
            result = "Debe ingresar la cédula";
        } else if (agAsignadas == null && (tipo.equals("10") || tipo.equals("16") || tipo.equals("11") || tipo.equals("17")|| tipo.equals("26"))) {
            result = "Debe ingresar las unidades";
        } else if (titulo.isEmpty()) {
            result = "Debe ingresar el titulo";
        } else {
            oUsuario.setLogin_nombre(nombre);
            oUsuario.setLogin_cedula(cedula);
            oUsuario.setTu_id(Integer.parseInt(tipo));
            oArea.setAg_id(Integer.parseInt(ag));
            oUsuario.setAg(oArea);
            oUsuario.setAnio(Integer.parseInt(ag));
            oUsuario.setUsuario_titulo(titulo);
            oUsuario.setUsuario_estado(Integer.parseInt(estado));
            result = adUsuario.ModificarUsuario(oUsuario);
            if(result.equals("Correcto")){
                result=adUsuario.DesactivarUsuario(oUsuario);
                if(result.equals("Correcto")){
                    result = adUsuario.AsignarUsuarioEstado(oUsuario);
                }
            }
            if (result.equals("Correcto") && (tipo.equals("10") || tipo.equals("11") || tipo.equals("16") || tipo.equals("17") || tipo.equals("26"))) {
                if (agAsignadas.length != 0) {
                    result = adUsuario.EliminarUsuarioComprasArea(oUsuario);
                    if (result.equals("Correcto")) {
                        for (String agAsignadas1 : agAsignadas) {
                            oArea.setAg_id(Integer.parseInt(agAsignadas1));
                            oUsuario.setAg(oArea);
                            oUsuario.setLogin_cedula(cedula);
                            result = adUsuario.AsignarUsuarioAreas(oUsuario);
                        }
                    }
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void DesactivarUsuaurio(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String cedula = request.getParameter("cedula");
        String tipo = request.getParameter("tipo");
        String area = request.getParameter("area");
        String cedulauser = request.getParameter("cedulauser");
        String result;

        cUsuario oUsuario = new cUsuario();
        adUsuario adUsuario = new adUsuario();

        oUsuario.setLogin_cedula(cedula);
        oUsuario.setTu_id(Integer.parseInt(tipo));
        oUsuario.setAnio(Integer.parseInt(area));
        result = adUsuario.DesactivarUsuario(oUsuario);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El usuario con cédula: \"" + cedula + "\" se ingresó correctamente en la unidad: \"" + area + "\" con tipo: \"" + tipo);
            objTransaccion.setTransaccion_cedula(cedulauser);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void BuscarCedulaCentralizada(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String cedula = request.getParameter("cedula");
        adVinculacion adVin = new adVinculacion();
        cUsuario result = new cUsuario();
        result = adVin.obtenerPersonasCedula(cedula);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    /*Eliminar Usuario*/
    private void EliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cedula = request.getParameter("cedulaUsuario");
        String agAsignadas[] = request.getParameterValues("ag");
        String tipo = request.getParameter("tipou");
        String ag = request.getParameter("agu");
        String result;
        Integer asignado;

        cUsuario oUsuario = new cUsuario();
        adUsuario adUsuario = new adUsuario();
        cAreaGestion oArea = new cAreaGestion();

        oUsuario.setLogin_cedula(cedula);
        oUsuario.setTu_id(Integer.parseInt(tipo));
        oArea.setAg_id(Integer.parseInt(ag));
        oUsuario.setAg(oArea);
        asignado = adUsuario.numeroUsuarioAsignar(oUsuario);
        if (asignado > 1) {
            result = adUsuario.EliminarAsignarUsuarios(oUsuario);
        } else {
            result = adUsuario.EliminarUsuarioCompras(oUsuario);
        }
        if (result.equals("Correcto") && tipo.equals("10")) {
            result = adUsuario.EliminarUsuarioCompras(oUsuario);
        }

        String json = new Gson().toJson(result);

        response.setContentType(
                "application/json");
        response.setCharacterEncoding(
                "UTF-8");
        response.getWriter()
                .write(json);
    }

}
