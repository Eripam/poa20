/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import poa.acceso.adAreaGestion;
import poa.acceso.adPerspectivaObj;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cAreaGestion;
import poa.clases.cPerspectivaObjetivo;
import poa.clases.cPoliEstra;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
public class servPerspectivaObjetivo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            HttpSession sesionOk = request.getSession(false);
            String strCedula = (String) sesionOk.getAttribute("cedulaUsuario");
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            switch (strAccion) {
                case "ListarObjetivosEstrategico":
                    ListarObjetivosEstrategico(request, response);
                    break;
                case "ListarObjetivos":
                    ListarObjetivos(request, response);
                    break;
                case "ListarObjetivosSOEI":
                    ListarObjetivosSOEI(request, response);
                    break;
                case "ListarObjetivosAreas":
                    ListarObjetivosAreas(request, response);
                    break;
                case "ListarObjetivosAreasI":
                    ListarObjetivosAreasI(request, response);
                    break;
                case "ListarPoliticas":
                    ListarPoliticas(request, response);
                    break;
                case "ListarPoliticasC":
                    ListarPoliticasC(request, response);
                    break;
                case "ListarEstrategias":
                    ListarEstrategias(request, response);
                    break;
                case "ListarActividadesP":
                    ListarActividadesP(request, response);
                    break;
                case "ListarActividadesPres":
                    ListarActividadesPres(request, response);
                    break;
                case "ListarTipoOEI":
                    ListarTipoOEI(request, response);
                    break;
                case "ListaVision":
                    ListarVision(request, response);
                    break;
                case "IngresarVision":
                    IngresarVision(request, response, strCedula, intIdAreaGestion);
                    break;
                case "ModificarVision":
                    ModificarVision(request, response, strCedula, intIdAreaGestion);
                    break;
                case "IngresarObjetivoOEI":
                    IngresarObjetivoOEI(request, response, strCedula, intIdAreaGestion);
                    break;
                case "ModificarObjetivoOEI":
                    ModificarObjetivoOEI(request, response, strCedula, intIdAreaGestion);
                    break;
                case "IngresarObjetivoOO":
                    IngresarObjetivoOO(request, response, strCedula, intIdAreaGestion);
                    break;
                case "ModificarObjetivoOO":
                    ModificarObjetivoOO(request, response, strCedula, intIdAreaGestion);
                    break;
                case "IngresarPrograma":
                    IngresarPrograma(request, response, strCedula, intIdAreaGestion);
                    break;
                case "IngresarObjUnidad":
                    IngresarOU(request, response, strCedula, intIdAreaGestion);
                    break;
                case "ModificarPrograma":
                    ModificarPrograma(request, response, strCedula, intIdAreaGestion);
                    break;
                case "EliminarObjetivoU":
                    EliminarObjetivoU(request, response, strCedula, intIdAreaGestion);
                    break;
                case "IngresarPolitica":
                    IngresarPolitica(request, response, strCedula, intIdAreaGestion);
                    break;
                case "ModificarPolitica":
                    ModificarPolitica(request, response, strCedula, intIdAreaGestion);
                    break;
                case "IngresarEstrategia":
                    IngresarEstrategia(request, response, strCedula, intIdAreaGestion);
                    break;
                case "ModificarEstrategia":
                    ModificarEstrategia(request, response, strCedula, intIdAreaGestion);
                    break;
            }
        }
    }

    private void ListarObjetivosEstrategico(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarObjetivosEstrategicos();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarTipoOEI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oei = request.getParameter("oei");
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarTipoPerspectiva(Integer.parseInt(oei));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarObjetivos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String objetivo = request.getParameter("objetivo");
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarObjetivosOperativos(Integer.parseInt(objetivo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarObjetivosSOEI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarObjetivosOperativos();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarVision(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarVisionMision();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarVision(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String vision = request.getParameter("txtVision");
        String mision = request.getParameter("txtMision");
        String fi = request.getParameter("fechaIVision");
        String ff = request.getParameter("fechaFVision");
        cPers.setVision_nombre(vision);
        cPers.setVision_mision(mision);
        cPers.setVision_fi(fi);
        cPers.setVision_ff(ff);
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.IngresarVision(cPers);

        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                objTransaccion.setTransaccion_descripcion("La vision con datos: nombre: \"" + vision + "\",  mision: \"" + mision + "\", fecha inicio:  \"" + fi + "\" y fecha fin:  \"" + ff + "\"    se ingresó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarVision(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String id = request.getParameter("txtID");
        String vision = request.getParameter("txtVision");
        String mision = request.getParameter("txtMision");
        String fi = request.getParameter("fechaIVision");
        String ff = request.getParameter("fechaFVision");
        String estado = request.getParameter("slcVision");
        cPers.setVision_nombre(vision);
        cPers.setVision_mision(mision);
        cPers.setVision_fi(fi);
        cPers.setVision_ff(ff);
        cPers.setVision_id(Integer.parseInt(id));
        cPers.setVision_estado(Integer.parseInt(estado));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.ModificarVision(cPers);

        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
                objTransaccion.setTransaccion_descripcion("La vision con datos: nombre: \"" + vision + "\",  mision: \"" + mision + "\", fecha inicio:  \"" + fi + "\", fecha fin:  \"" + ff + "\" y estado:  \"" + estado + "\"    se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarObjetivoOEI(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String vision = request.getParameter("slcVisionO");
        String tipo = request.getParameter("slcTipo");
        String codigo = request.getParameter("txtCodigoO");
        String nombre = request.getParameter("txtNombreO");
        cPers.setVision_id(Integer.parseInt(vision));
        cPers.setTo_id(Integer.parseInt(tipo));
        cPers.setPerspectiva_nombre(nombre);
        cPers.setPerspectiva_codigo(codigo);
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.IngresarOEI(cPers);

        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                objTransaccion.setTransaccion_descripcion("El objetivo estratégico con datos: nombre: \"" + nombre + "\",  vision: \"" + vision + "\", tipo:  \"" + tipo + "\" y código:  \"" + codigo + "\"    se ingresó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarObjetivoOEI(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String id = request.getParameter("txtOEI");
        String vision = request.getParameter("slcVisionO");
        String tipo = request.getParameter("slcTipo");
        String codigo = request.getParameter("txtCodigoO");
        String nombre = request.getParameter("txtNombreO");
        String estado = request.getParameter("slcOEIEstado");
        cPers.setVision_id(Integer.parseInt(vision));
        cPers.setTo_id(Integer.parseInt(tipo));
        cPers.setPerspectiva_nombre(nombre);
        cPers.setPerspectiva_codigo(codigo);
        cPers.setPerspectiva_id(Integer.parseInt(id));
        cPers.setPerspectiva_estado(Integer.parseInt(estado));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.ModificarOEI(cPers);

        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
                objTransaccion.setTransaccion_descripcion("El ojetivo estratégico con datos: id: \"" + id + "\", nombre: \"" + nombre + "\",  vision: \"" + vision + "\", tipo:  \"" + tipo + "\", código:  \"" + codigo + "\" y estado:  \"" + estado + "\"    se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarObjetivoOO(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String oei = request.getParameter("slcObjE");
        String codigo = request.getParameter("txtCodigoOO");
        String nombre = request.getParameter("txtNombreOO");
        cPers.setPerspectiva_id(Integer.parseInt(oei));
        cPers.setObjetivo_nombre(nombre);
        cPers.setObjetivo_codigo(codigo);
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.IngresarOO(cPers);

        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                objTransaccion.setTransaccion_descripcion("El objetivo operativo con datos: nombre: \"" + nombre + "\",  perspectiva: \"" + oei + "\", y código:  \"" + codigo + "\"    se ingresó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarObjetivoOO(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String id = request.getParameter("txtOO");
        String oei = request.getParameter("slcObjE");
        String codigo = request.getParameter("txtCodigoOO");
        String nombre = request.getParameter("txtNombreOO");
        String estado = request.getParameter("slcOOEstado");
        cPers.setPerspectiva_id(Integer.parseInt(oei));
        cPers.setObjetivo_nombre(nombre);
        cPers.setObjetivo_codigo(codigo);
        cPers.setObjetivo_id(Integer.parseInt(id));
        cPers.setObjetivo_estado(Integer.parseInt(estado));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.ModificarOO(cPers);

        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
                objTransaccion.setTransaccion_descripcion("El ojetivo operativo con datos: id: \"" + id + "\", nombre: \"" + nombre + "\",  perspectiva: \"" + oei + "\", código:  \"" + codigo + "\" y estado:  \"" + estado + "\"    se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarPrograma(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String oo = request.getParameter("slcObjOp");
        String codigo = request.getParameter("txtCodigoPro");
        String nombre = request.getParameter("txtNombrePro");
        String anio = request.getParameter("txtAnioA");
        cPers.setObjetivo_id(Integer.parseInt(oo));
        cPers.setAp_nombre(nombre);
        cPers.setAp_codigo(codigo);
        cPers.setTo_id(Integer.parseInt(anio));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.IngresarPro(cPers);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
            objTransaccion.setTransaccion_descripcion("El programa con datos: nombre: \"" + nombre + "\",  objetivo:: \"" + oo + "\",  año:  \"" + anio + "\" y código:  \"" + codigo + "\"    se ingresó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarObjetivoU(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String oei = request.getParameter("slcObjUniE");
        String area = request.getParameter("slAgE");
        cPers.setPerspectiva_id(Integer.parseInt(oei));
        cPers.setAp_id(Integer.parseInt(area));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.EliminarObejtivoUnidad(cPers);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 3);
            objTransaccion.setTransaccion_descripcion("El Objetivo \"" + oei + "\", se elimino correctamente de la unidad  \"" + area + "\".");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarOU(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result = "";
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String oei = request.getParameter("slcObjUni");
        String anio = request.getParameter("anioActivo");
        String ag[] = request.getParameterValues("slAg");
        List<cAreaGestion> resultA = new ArrayList<cAreaGestion>();

        adPerspectivaObj adPersp = new adPerspectivaObj();
        adAreaGestion adAg = new adAreaGestion();

        for (String ag1 : ag) {
            cPers.setPerspectiva_id(Integer.parseInt(oei));
            cPers.setAp_id(Integer.parseInt(ag1));
            result = adPersp.IngresarObejtivoUnidad(cPers);
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                objTransaccion.setTransaccion_descripcion("El la unidad de codigo: \"" + ag1 + "\",  se ingreso correctamente al oei código:  \"" + oei + "\".");
                ingresarTransaccion(objTransaccion);
            }
            if (result.equals("Correcto") && (adAg.tipoAreaG(Integer.parseInt(ag1)).equals("2") || adAg.tipoAreaG(Integer.parseInt(ag1)).equals("3") || adAg.tipoAreaG(Integer.parseInt(ag1)).equals("5"))) {
                resultA = adAreaGestion.obtenerAreasGestionHijas(Integer.parseInt(ag1), Integer.parseInt(anio));
                for (int i = 0; i < resultA.size(); i++) {
                    cPers.setPerspectiva_id(Integer.parseInt(oei));
                    cPers.setAp_id(resultA.get(i).getAg_id());
                    result = adPersp.IngresarObejtivoUnidad(cPers);
                    if (result.equals("Correcto")) {
                        cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
                        objTransaccion.setTransaccion_descripcion("El la unidad de codigo: \"" + resultA.get(i).getAg_id() + "\",  se ingreso correctamente al oei código:  \"" + oei + "\".");
                        ingresarTransaccion(objTransaccion);
                    }
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarPrograma(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPerspectivaObjetivo cPers = new cPerspectivaObjetivo();
        String id = request.getParameter("txtPro");
        String oo = request.getParameter("slcObjOp");
        String codigo = request.getParameter("txtCodigoPro");
        String nombre = request.getParameter("txtNombrePro");
        String estado = request.getParameter("slcProEstado");
        String anio = request.getParameter("txtAnioA");
        cPers.setObjetivo_id(Integer.parseInt(oo));
        cPers.setAp_nombre(nombre);
        cPers.setAp_codigo(codigo);
        cPers.setAp_id(Integer.parseInt(id));
        cPers.setObjetivo_estado(Integer.parseInt(estado));
        cPers.setTo_id(Integer.parseInt(anio));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.ModificarPro(cPers);

        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
                objTransaccion.setTransaccion_descripcion("El programa con datos: id: \"" + id + "\", nombre: \"" + nombre + "\",  objetivo operativo: \"" + oo + "\", código:  \"" + codigo + "\" , año:  \"" + anio + "\" y estado:  \"" + estado + "\"    se modificó correctamente.");
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarPolitica(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPoliEstra cPers = new cPoliEstra();
        String oo = request.getParameter("slcPol");
        String nombre = request.getParameter("txtNombrePol");
        cPers.setObjetivo_id(Integer.parseInt(oo));
        cPers.setPolitica_nombre(nombre);
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.IngresarPolitica(cPers);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
            objTransaccion.setTransaccion_descripcion("La politica con datos: nombre: \"" + nombre + "\",  objetivo:: \"" + oo + "\" se ingresó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarPolitica(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPoliEstra cPers = new cPoliEstra();
        String id = request.getParameter("txtPol");
        String oo = request.getParameter("slcPol");
        String nombre = request.getParameter("txtNombrePol");
        String estado = request.getParameter("slcPolEstado");
        cPers.setObjetivo_id(Integer.parseInt(oo));
        cPers.setPolitica_nombre(nombre);
        cPers.setPolitica_id(Integer.parseInt(id));
        cPers.setPolitica_estado(Integer.parseInt(estado));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.ModificarPolitica(cPers);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
            objTransaccion.setTransaccion_descripcion("La politica con datos: id: \"" + id + "\", nombre: \"" + nombre + "\",  objetivo operativo: \"" + oo + "\" y estado:  \"" + estado + "\"    se modificó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void IngresarEstrategia(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPoliEstra cPers = new cPoliEstra();
        String pol = request.getParameter("slcEst");
        String nombre = request.getParameter("txtNombreEst");
        cPers.setPolitica_id(Integer.parseInt(pol));
        cPers.setEstrategia_nombre(nombre);
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.IngresarEstrategia(cPers);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 1);
            objTransaccion.setTransaccion_descripcion("La estrategia con datos: nombre: \"" + nombre + "\",  politica: \"" + pol + "\" se ingresó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarEstrategia(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        cPoliEstra cPers = new cPoliEstra();
        String id = request.getParameter("txtEst");
        String pol = request.getParameter("slcEst");
        String nombre = request.getParameter("txtNombreEst");
        String estado = request.getParameter("slcEstEstado");
        cPers.setPolitica_id(Integer.parseInt(pol));
        cPers.setEstrategia_nombre(nombre);
        cPers.setEstrategia_id(Integer.parseInt(id));
        cPers.setPolitica_estado(Integer.parseInt(estado));
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.ModificarEstrategia(cPers);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
            objTransaccion.setTransaccion_descripcion("La estrategia con datos: id: \"" + id + "\", nombre: \"" + nombre + "\",  politica: \"" + pol + "\" y estado:  \"" + estado + "\"    se modificó correctamente.");
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarObjetivosAreas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String area = request.getParameter("area");
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarObjetivosAreas(Integer.parseInt(area));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarObjetivosAreasI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarObjetivosAreas();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarPoliticas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String objetivo = request.getParameter("objetivo");
        List<cPoliEstra> result = new ArrayList<cPoliEstra>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarPoliticasEst(Integer.parseInt(objetivo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarPoliticasC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cPoliEstra> result = new ArrayList<cPoliEstra>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarPoliticas();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void ListarEstrategias(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cPoliEstra> result = new ArrayList<cPoliEstra>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarEstrategias();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarActividadesP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String objetivo = request.getParameter("objetivo");
        String estado = request.getParameter("estado");
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarActividades(Integer.parseInt(objetivo), Integer.parseInt(estado));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarActividadesPres(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        adPerspectivaObj adPersp = new adPerspectivaObj();

        result = adPersp.listarActividadesPres();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

}
