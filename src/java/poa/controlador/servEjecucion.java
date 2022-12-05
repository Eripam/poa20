/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import poa.acceso.adActividadRequerimiento;
import poa.acceso.adEjecucion;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cActividadRequerimiento;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
@MultipartConfig
public class servEjecucion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            /*Inicio Para transacciones*/
            HttpSession sesionOk = request.getSession(false);
            String strCedula = (String) sesionOk.getAttribute("cedulaUsuario");
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            /*Fin Para transacciones*/

            switch (strAccion) {
                case "ListRequeUnReq":
                    ListRequeUnReq(request, response);
                    break;
                case "ListRequeUnReqNP":
                    ListRequeUnReqNP(request, response);
                    break;
                case "ListaServiciosProf":
                    ListaServiciosProf(request, response);
                    break;
                case "ListaReqEnviados":
                    ListaReqEnviados(request, response);
                    break;
                case "ValidacionEjecucion":
                    ValidacionEjecucion(request, response);
                    break;
                case "ListaReqEnviadosO":
                    ListaReqEnviadosO(request, response);
                    break;
                case "ListRequeUnReqOP":
                    ListRequeUnReqOP(request, response);
                    break;
                case "IngresarSolicitud":
                    IngresarSolicitud(request, response, intIdAreaGestion);
                    break;
                case "IngresarSolicitudNP":
                    IngresarSolicitudNP(request, response, intIdAreaGestion);
                    break;
                case "IngresarSolicitudOPC":
                    IngresarSolicitudOPC(request, response, intIdAreaGestion);
                    break;
                case "ListaSolicitudes":
                    ListaSolicitudes(request, response);
                    break;
                case "ListaSolicitudesNP":
                    ListaSolicitudesNP(request, response);
                    break;
                case "ListaSolicitudesNPE":
                    ListaSolicitudesNPE(request, response);
                    break;
                case "ListaSolicitudesOPC":
                    ListaSolicitudesOPC(request, response);
                    break;
                case "ListaSolicitudesOPCE":
                    ListaSolicitudesOPCE(request, response);
                    break;
                case "ListaSolicitudesC":
                    ListaSolicitudesC(request, response);
                    break;
                case "ListaSolicitudesCU":
                    ListaSolicitudesCU(request, response);
                    break;
                case "ListaSolicitudesCAE":
                    ListaSolicitudesCAE(request, response);
                    break;
                case "ListaSolicitudesCAEU":
                    ListaSolicitudesCAEU(request, response);
                    break;
                case "ListaSolicitudesSolicitud":
                    ListaSolicitudesSolicitud(request, response);
                    break;
                case "ListaSolicitudesSolicitudNP":
                    ListaSolicitudesSolicitudNP(request, response);
                    break;
                case "ListarSolicitudSolicitudOPC":
                    ListarSolicitudSolicitudOPC(request, response);
                    break;
                case "ListaSolicitudesSolicitudUnif":
                    ListaSolicitudesSolicitudUnif(request, response);
                    break;
                case "AgregarRequerimientos":
                    AgregarRequerimientos(request, response);
                    break;
                case "AgregarRequerimientosNP":
                    AgregarRequerimientosNP(request, response);
                    break;
                case "AgregarRequerimientosOPC":
                    AgregarRequerimientosOPC(request, response);
                    break;
                case "EnviarRequerimientos":
                    EnviarRequerimientos(request, response);
                    break;
                case "ModificarSolicitud":
                    ModificarSolicitud(request, response, intIdAreaGestion);
                    break;
                case "ModificarSolicitudNP":
                    ModificarSolicitudNP(request, response, intIdAreaGestion);
                    break;
                case "ModificarSolicitudOPC":
                    ModificarSolicitudOPC(request, response, intIdAreaGestion);
                    break;
                case "ModificarSolicitudInformacion":
                    ModificarSolicitudInformacion(request, response, intIdAreaGestion);
                    break;
                case "ModificarSolicitudInformacionUnif":
                    ModificarSolicitudInformacionUnif(request, response, intIdAreaGestion);
                    break;
                case "EliminarSolicitud":
                    EliminarSolicitud(request, response, intIdAreaGestion);
                    break;
                case "EliminarSolicitudNP":
                    EliminarSolicitudNP(request, response, intIdAreaGestion);
                    break;
                case "EliminarSolicitudOPC":
                    EliminarSolicitudOPC(request, response, intIdAreaGestion);
                    break;
                case "EnviarSolicitud":
                    EnviarSolicitud(request, response);
                    break;
                case "EnviarSolicitudNP":
                    EnviarSolicitudNP(request, response);
                    break;
                case "EnviarSolicitudOPC":
                    EnviarSolicitudOPC(request, response);
                    break;
                case "GuardarSalvaguardado":
                    GuardarSalvaguardado(request, response);
                    break;
                case "ListaEnvios":
                    ListaEnvios(request, response);
                    break;
                case "ListaEnviosNP":
                    ListaEnviosNP(request, response);
                    break;
                case "ModificarAutoridades":
                    ModificarAutoridades(request, response);
                    break;
                case "ListRequeUnionReq":
                    ListRequeUnionReq(request, response);
                    break;
                case "EnviarRequerimientosReg":
                    EnviarRequerimientosReg(request, response);
                    break;
                case "EnviarRequerimientosRegOP":
                    EnviarRequerimientosRegOP(request, response);
                    break;
                case "EnviarSolicitudJus":
                    EnviarSolicitudJus(request, response);
                    break;
                case "IngresarUnificacion":
                    IngresarUnificacion(request, response, intIdAreaGestion);
                    break;
                case "ListRequeUnificados":
                    ListRequeUnificados(request, response);
                    break;
                case "ListaRequerimientosU":
                    ListaRequerimientosU(request, response);
                    break;
                case "ModificarUnificacion":
                    ModificarUnificacion(request, response, intIdAreaGestion);
                    break;
                case "EliminarRequerimientoUnif":
                    EliminarRequerimientoUnif(request, response, intIdAreaGestion);
                    break;
                case "IngresarSolicitudUnifi":
                    IngresarSolicitudUnifi(request, response, intIdAreaGestion);
                    break;
                case "ListaSolicitudesUnif":
                    ListaSolicitudesUnif(request, response);
                    break;
                case "ModificarSolicitudUnif":
                    ModificarSolicitudUnif(request, response, intIdAreaGestion);
                    break;
                case "EliminarSolicitudUnif":
                    EliminarSolicitudUnif(request, response, intIdAreaGestion);
                    break;
                case "EliminarCertificacion":
                    EliminarCertificacion(request, response, intIdAreaGestion);
                    break;
                case "EliminarCertificacionSP":
                    EliminarCertificacionSP(request, response, intIdAreaGestion);
                    break;
                case "EliminarCertificacionSPOP":
                    EliminarCertificacionSPOP(request, response, intIdAreaGestion);
                    break;
                case "EliminarCertificacionVP":
                    EliminarCertificacionVP(request, response, intIdAreaGestion);
                    break;
                case "EnviarSolicitudUnif":
                    EnviarSolicitudUnif(request, response, intIdAreaGestion);
                    break;
                case "ModificarAutoridadesUnif":
                    ModificarAutoridadesUnif(request, response, intIdAreaGestion);
                    break;
                case "ListaRequerimientosUnificadosA":
                    ListaRequerimientosUnificadosA(request, response);
                    break;
                case "IngresarCustodios":
                    IngresarCustodios(request, response, intIdAreaGestion);
                    break;
                case "ListaSolicitudesPlanificador":
                    ListaSolicitudesPlanificador(request, response);
                    break;
                case "ListaRequerimientosSalvaguardar":
                    ListaRequerimientosSalvaguardar(request, response);
                    break;
                case "ValidarReq":
                    ValidarReq(request, response);
                    break;
                case "ListarServiciosSol":
                    ListarServiciosSol(request, response);
                    break;
                case "ListarServiciosReq":
                    ListarServiciosReq(request, response);
                    break;
                case "VerificarServicioP":
                    VerificarServicioP(request, response);
                    break;
                case "VerificarServicioPO":
                    VerificarServicioPO(request, response);
                    break;
                case "ListaServicioProfesional":
                    ListaServicioProfesional(request, response);
                    break;
                case "ListaServicioProfesionalOP":
                    ListaServicioProfesionalOP(request, response);
                    break;
                case "ListaCertificacionP":
                    ListaCertificacionP(request, response);
                    break;
                case "ListaCertificacionPNP":
                    ListaCertificacionPNP(request, response);
                    break;
                case "ListaCertificacionPOP":
                    ListaCertificacionPOP(request, response);
                    break;
                case "IngresarCertificacion":
                    IngresarCertificacion(request, response, intIdAreaGestion, strCedula);
                    break;
                case "IngresarCertificacionSP":
                    IngresarCertificacionSP(request, response, intIdAreaGestion, strCedula);
                    break;
                case "IngresarCertificacionSPOP":
                    IngresarCertificacionSPOP(request, response, intIdAreaGestion, strCedula);
                    break;
                case "IngresarCertificacionOP":
                    IngresarCertificacionOP(request, response, intIdAreaGestion, strCedula);
                    break;
                case "IngresarCertificacionOPC":
                    IngresarCertificacionOPC(request, response, intIdAreaGestion, strCedula);
                    break;
                case "IngresarCertificacionPG":
                    IngresarCertificacionPG(request, response, intIdAreaGestion, strCedula);
                    break;
                case "IngresarCertificacionPGU":
                    IngresarCertificacionPGU(request, response, intIdAreaGestion, strCedula);
                    break;
                case "ModificarCertificacion":
                    ModificarCertificacion(request, response, intIdAreaGestion, strCedula);
                    break;
                case "ModificarCertificacionSP":
                    ModificarCertificacionSP(request, response, intIdAreaGestion, strCedula);
                    break;
                case "ModificarCertificacionSPOP":
                    ModificarCertificacionSPOP(request, response, intIdAreaGestion, strCedula);
                    break;
                case "ModificarCertificacionOP":
                    ModificarCertificacionOP(request, response, intIdAreaGestion, strCedula);
                    break;
                case "AnularSolicitud":
                    AnularSolicitud(request, response);
                    break;
                case "AnularSolicitudOP":
                    AnularSolicitudOP(request, response);
                    break;
                case "EnviarServiciosProf":
                    EnviarServiciosProf(request, response);
                    break;
                case "IngresarSolicitudNPTH":
                    IngresarSolicitudNPTH(request, response, intIdAreaGestion);
                    break;
                case "ListarServiciosSolicitud":
                    ListarServiciosSolicitud(request, response);
                    break;
                case "ListarPersonalO":
                    ListarPersonalO(request, response);
                    break;
            }
        }
    }

    private void ListRequeUnReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String area = request.getParameter("area");
        String tipo = request.getParameter("tipo");
        String anio = request.getParameter("anio");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjec = new adEjecucion();

        result = aEjec.ListarProyectosComprasAnalista(Integer.parseInt(area), Integer.parseInt(tipo), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ValidacionEjecucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String anio = request.getParameter("anio");
        Boolean resultado;

        resultado = adEjecucion.fechaporAño(Integer.parseInt(anio));

        String json = new Gson().toJson(resultado);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListRequeUnReqNP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String area = request.getParameter("area");
        String tipo = request.getParameter("tipo");
        String anio = request.getParameter("anio");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjec = new adEjecucion();

        result = aEjec.ListarProyectosRequerimientosNP(Integer.parseInt(area), Integer.parseInt(tipo), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaServiciosProf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String area = request.getParameter("area");
        String tipo = request.getParameter("tipo");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjec = new adEjecucion();

        if (tipo.equals("1")) {
            result = aEjec.ListarServiciosIPEC(Integer.parseInt(area));
        } else {
            result = aEjec.ListarServiciosIPECTH();
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaReqEnviados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjec = new adEjecucion();

        result = aEjec.ListarProyectosRequerimientoETH(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaServicioProfesional(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adA = new adActividadRequerimiento();
        String req = request.getParameter("req");

        result = adA.ListarPersonal(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaServicioProfesionalOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adA = new adActividadRequerimiento();
        String req = request.getParameter("req");

        result = adA.ListarPersonalO(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaCertificacionP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adA = new adActividadRequerimiento();
        String solicitud = request.getParameter("solicitud");
        String anio = request.getParameter("anio");

        result = adA.ListarCertificacionesP(Integer.parseInt(solicitud), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaCertificacionPNP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adA = new adActividadRequerimiento();
        String solicitud = request.getParameter("solicitud");
        String anio = request.getParameter("anio");

        result = adA.ListarCertificacionesPNP(Integer.parseInt(solicitud), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaCertificacionPOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adA = new adActividadRequerimiento();
        String solicitud = request.getParameter("solicitud");

        result = adA.ListarCertificacionesPOP(Integer.parseInt(solicitud));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaReqEnviadosO(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjec = new adEjecucion();

        result = aEjec.ListarProyectosRequerimientoETHO(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListRequeUnReqOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String area = request.getParameter("area");
        String tipo = request.getParameter("tipo");
        String anio = request.getParameter("anio");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjec = new adEjecucion();

        result = aEjec.ListarProyectosRequerimientosOBC(Integer.parseInt(area), Integer.parseInt(tipo), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void IngresarSolicitud(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String centro = request.getParameter("centro");
        String ag = request.getParameter("ag");
        String req = request.getParameter("req");
        String cedulau = request.getParameter("cedulausuario");
        String rector = request.getParameter("rector");
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        String anio = request.getParameter("anio");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        Integer con1 = 0, con2 = 0;

        cCom.setSolicitud_centro_costo(centro);
        cCom.setAg_id(Integer.parseInt(ag));
        cCom.setSolicitud_id(aEje.codigoSiguienteSolicitud());
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cedula(cedula);
        cCom.setSolicitud_nombre(nombre);
        cCom.setSolicitud_cargo(cargo);
        cCom.setSolicitud_estado(Integer.parseInt(anio));

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][4].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (con1 == req_id.length || con2 == req_id.length) {
                result = aEje.IngresarSolicitud(cCom);
                if (result.equals("Correcto")) {
                    for (int i = 0; i < req_id.length; i++) {
                        if (result.equals("Correcto")) {
                            cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                            double cant = Double.parseDouble(req_id[i][1]), costou = Double.parseDouble(req_id[i][3]);
                            double valor = costou * cant;
                            cCom.setReq_cantidad(cant);
                            cCom.setReq_costo_unitario(costou);
                            cCom.setReq_costo_sin_iva(valor);
                            if (req_id[i][5].equals("1")) {
                                double total = (valor * 1.12);
                                cCom.setReq_costo_total(total);
                            } else {
                                cCom.setReq_costo_total(valor);
                            }
                            result = aEje.InsertarSolicitudReq(cCom);
                        } else {
                            i = req_id.length + 1;
                            result = aEje.EliminarSolicitud(cCom);
                            if (result.equals("Correcto")) {
                                result = "Error al ingresar la solicitud de requerimiento, verifique las cantidades y vuelva a intentarlo.";
                            }
                        }
                    }
                }
            } else {
                result = "Existen requerimientos que no pueden ser ingresados en este justificativo, verifique la estructura presupuestaria.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud con código: \"" + cCom.getSolicitud_id() + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedulau);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void IngresarSolicitudNP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String ag = request.getParameter("ag");
        String req = request.getParameter("req");
        String cedulau = request.getParameter("cedulausuario");
        String rector = request.getParameter("rector");
        String cargo = request.getParameter("cargo");
        String anio = request.getParameter("anio");
        List<cActividadRequerimiento> resultSP = new ArrayList<cActividadRequerimiento>();
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adActividadRequerimiento adAct = new adActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        Integer con1 = 0, con2 = 0;

        Integer codigo = aEje.codigoSiguienteSolicitudNP();
        cCom.setAg_id(Integer.parseInt(ag));
        cCom.setSolicitud_id(codigo);
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cargo(cargo);
        cCom.setSolicitud_estado(Integer.parseInt(anio));

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][4].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (con1 == req_id.length || con2 == req_id.length) {
                result = aEje.IngresarSolicitudNP(cCom);
                if (result.equals("Correcto")) {
                    for (int i = 0; i < req_id.length; i++) {
                        if (result.equals("Correcto")) {
                            resultSP = adAct.ListarPersonal(Integer.parseInt(req_id[i][0]));
                            if (rector.equals("4") && resultSP.size() > 0) {
                                for (int j = 0; j < resultSP.size(); j++) {
                                    if (resultSP.get(j).getSolicitud_id() == 0 && resultSP.get(j).getSolestado_numero() == 1) {
                                        cCom.setSolicitud_id(codigo);
                                        cCom.setReq_id(resultSP.get(j).getReq_id());
                                        result = adAct.ModificarSPSolicitudDis(cCom, 3);
                                    }
                                }
                                if (result.equals("Correcto")) {
                                    cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                                    double cant = Double.parseDouble(req_id[i][1]), costou = Double.parseDouble(req_id[i][3]);
                                    double valor = costou * cant;
                                    cCom.setReq_cantidad(cant);
                                    cCom.setReq_costo_unitario(costou);
                                    cCom.setReq_costo_sin_iva(valor);
                                    cCom.setReq_costo_total(Double.parseDouble(req_id[i][5]));
                                    result = aEje.InsertarSolicitudReqNP(cCom);
                                } else {
                                    i = req_id.length + 1;
                                    result = aEje.EliminarSolicitudNP(cCom);
                                }
                            } else if (rector.equals("4") && resultSP.size() < 1) {
                                i = req_id.length + 1;
                                result = aEje.EliminarSolicitudNP(cCom);
                                result = "Disponibilidad solo se puede generar con servicios profesionales";
                            } else {
                                cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                                double cant = Double.parseDouble(req_id[i][1]), costou = Double.parseDouble(req_id[i][3]);
                                double valor = costou * cant;
                                cCom.setReq_cantidad(cant);
                                cCom.setReq_costo_unitario(costou);
                                cCom.setReq_costo_sin_iva(valor);
                                if (req_id[i][5].equals("1")) {
                                    double total = (valor * 1.12);
                                    cCom.setReq_costo_total(total);
                                } else if (req_id[i][5].equals("0")) {
                                    cCom.setReq_costo_total(valor);
                                } else {
                                    cCom.setReq_costo_total(Double.parseDouble(req_id[i][5]));
                                }
                                result = aEje.InsertarSolicitudReqNP(cCom);
                            }
                        } else {
                            i = req_id.length + 1;
                            result = aEje.EliminarSolicitudNP(cCom);
                            if (result.equals("Correcto")) {
                                result = "Error al ingresar la solicitud de requerimiento, verifique las cantidades y vuelva a intentarlo.";
                            }
                        }
                    }
                }
            } else {
                result = "Existen requerimientos que no pueden ser ingresados en este justificativo, verifique la estructura presupuestaria.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud no pac con código: \"" + cCom.getSolicitud_id() + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedulau);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void IngresarSolicitudNPTH(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String ag = request.getParameter("ag");
        String req = request.getParameter("req");
        String cedulau = request.getParameter("cedulausuario");
        String rector = request.getParameter("rector");
        String cargo = request.getParameter("cargo");
        String anio = request.getParameter("anio");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adActividadRequerimiento adAct = new adActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        List<String> lista = new ArrayList<String>();

        Integer codigo = aEje.codigoSiguienteSolicitudNP();
        cCom.setAg_id(Integer.parseInt(ag));
        cCom.setSolicitud_id(codigo);
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cargo(cargo);
        cCom.setSolicitud_estado(Integer.parseInt(anio));

        if (req_id.length > 0) {
            result = aEje.IngresarSolicitudNP(cCom);
            for (int i = 0; i < req_id.length; i++) {
                lista.add(req_id[i][1]);
            }
            Set<String> miSet = new HashSet<String>(lista);
            for (String s : miSet) {
                double siniva = 0.0;
                int count = 0, count2 = 0;
                for (int j = 0; j < req_id.length; j++) {
                    if (req_id[j][1].equals(s)) {
                        count2++;
                        if (req_id[j][4].equals("1")) {
                            siniva += Double.parseDouble(req_id[j][2]);
                        } else {
                            siniva += Double.parseDouble(req_id[j][3]);
                        }
                    }
                }
                if (result.equals("Correcto")) {
                    for (int k = 0; k < req_id.length; k++) {
                        if (req_id[k][1].equals(s)) {
                            cCom.setReq_id(Integer.parseInt(req_id[k][0]));
                            result = adAct.ModificarSPSolicitudDis2(cCom, 1);
                            if (result.equals("Correcto")) {
                                count++;
                            }
                        }
                    }
                    if (result.equals("Correcto") && count2 == count) {
                        cCom.setReq_id(Integer.parseInt(s));
                        cCom.setReq_cantidad(1.0);
                        cCom.setReq_costo_unitario(siniva);
                        cCom.setReq_costo_sin_iva(siniva);
                        result = aEje.InsertarSolicitudReqNP(cCom);
                    } else {
                        for (int k = 0; k < req_id.length; k++) {
                            cCom.setReq_id(Integer.parseInt(req_id[k][0]));
                            result = adAct.EliminarEstadoDispo(cCom);
                        }
                        result = aEje.EliminarSolicitudNP(cCom);
                        result = "Error al ingresar STP";
                    }
                }
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud no pac con código: \"" + cCom.getSolicitud_id() + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedulau);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void IngresarSolicitudOPC(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String ag = request.getParameter("ag");
        String req = request.getParameter("req");
        String cedulau = request.getParameter("cedulausuario");
        String rector = request.getParameter("rector");
        String cargo = request.getParameter("cargo");
        String anio = request.getParameter("anio");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        Integer con1 = 0, con2 = 0;

        cCom.setAg_id(Integer.parseInt(ag));
        cCom.setSolicitud_id(aEje.codigoSiguienteSolicitudOPC());
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cargo(cargo);
        cCom.setSolicitud_estado(Integer.parseInt(anio));

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][4].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (con1 == req_id.length || con2 == req_id.length) {
                result = aEje.IngresarSolicitudOPC(cCom);
                if (result.equals("Correcto")) {
                    for (int i = 0; i < req_id.length; i++) {
                        if (result.equals("Correcto")) {
                            cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                            cCom.setReq_costo_unitario(Double.parseDouble(req_id[i][1]));
                            cCom.setReq_costo_sin_iva(Double.parseDouble(req_id[i][2]));
                            cCom.setReq_costo_total(Double.parseDouble(req_id[i][3]));
                            result = aEje.InsertarSolicitudReqOPC(cCom);
                        } else {
                            i = req_id.length + 1;
                            result = aEje.EliminarSolicitudOPC(cCom);
                            if (result.equals("Correcto")) {
                                result = "Error al ingresar la solicitud de requerimiento, verifique las cantidades y vuelva a intentarlo.";
                            }
                        }
                    }
                }
            } else {
                result = "Existen requerimientos que no pueden ser ingresados en este justificativo, verifique la estructura presupuestaria.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud de obligaciones pendientes/comprometidos con código: \"" + cCom.getSolicitud_id() + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedulau);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String area = request.getParameter("area");
        String anio = request.getParameter("anio");

        result = aEjecucion.ListarSolicitudAreas(Integer.parseInt(area), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void VerificarServicioP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        adEjecucion aEjecucion = new adEjecucion();
        String serv = request.getParameter("serv");
        String verificacion = request.getParameter("verificacion");

        result = aEjecucion.VerificarServiciosP(Integer.parseInt(serv), Integer.parseInt(verificacion));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void VerificarServicioPO(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        adEjecucion aEjecucion = new adEjecucion();
        String serv = request.getParameter("serv");
        String verificacion = request.getParameter("verificacion");

        result = aEjecucion.VerificarServiciosPO(Integer.parseInt(serv), Integer.parseInt(verificacion));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesNP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String area = request.getParameter("area");
        String anio = request.getParameter("anio");

        result = aEjecucion.ListarSolicitudNPAreas(Integer.parseInt(area), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesNPE(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjecucion = new adEjecucion();

        result = aEjecucion.ListarSolicitudNPAreasE(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesOPC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String area = request.getParameter("area");
        String anio = request.getParameter("anio");

        result = aEjecucion.ListarSolicitudOPCAreas(Integer.parseInt(area), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesOPCE(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjecucion = new adEjecucion();

        result = aEjecucion.ListarSolicitudOPCE(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjecucion = new adEjecucion();

        result = aEjecucion.ListarSolicitudAreasC(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesCU(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjecucion = new adEjecucion();

        result = aEjecucion.ListarSolicitudComprasUnif(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesCAE(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjecucion = new adEjecucion();

        result = aEjecucion.ListarSolicitudAreasCAE(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesCAEU(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String anio = request.getParameter("anio");
        adEjecucion aEjecucion = new adEjecucion();

        result = aEjecucion.ListarSolicitudAreasCAEU(Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String solicitud = request.getParameter("solicitud");

        result = aEjecucion.ListarSolicitudSolicitud(Integer.parseInt(solicitud));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesSolicitudNP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String solicitud = request.getParameter("solicitud");

        result = aEjecucion.ListarSolicitudSolicitudNP(Integer.parseInt(solicitud));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarServiciosSol(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adAct = new adActividadRequerimiento();
        String solicitud = request.getParameter("solicitud");
        String req = request.getParameter("req");

        result = adAct.ListarPersonalSolReq(Integer.parseInt(req), Integer.parseInt(solicitud));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarServiciosReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adAct = new adActividadRequerimiento();
        String req = request.getParameter("req");

        result = adAct.ListarPersonalV(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarSolicitudSolicitudOPC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String solicitud = request.getParameter("solicitud");

        result = aEjecucion.ListarSolicitudSolicitudOPC(Integer.parseInt(solicitud));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarServiciosSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aAct = new adActividadRequerimiento();
        String solicitud = request.getParameter("solicitud");
        String requerimiento = request.getParameter("requerimiento");

        result = aAct.ListarPersonalOSol(Integer.parseInt(requerimiento), Integer.parseInt(solicitud));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarPersonalO(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aAct = new adActividadRequerimiento();
        String requerimiento = request.getParameter("requerimiento");

        result = aAct.ListarPersonalO(Integer.parseInt(requerimiento));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesSolicitudUnif(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String solicitud = request.getParameter("solicitud");

        result = aEjecucion.ListarSolicitudSolicitudUnificados(Integer.parseInt(solicitud));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void AgregarRequerimientos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;
        String solic = request.getParameter("solc");
        String req = request.getParameter("req");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        List<cActividadRequerimiento> reql = new ArrayList<cActividadRequerimiento>();

        adEjecucion aEje = new adEjecucion();
        reql = aEje.ListarSolicitudSolicitud(Integer.parseInt(solic));
        Integer con1 = 0, con2 = 0;

        cCom.setSolicitud_id(Integer.parseInt(solic));

        if (req_id.length > 0) {
            for (int i = 0; i < reql.size(); i++) {
                if (reql.get(i).getPresupuesto_proyecto().equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            for (int j = 0; j < req_id.length; j++) {
                if (req_id[j][5].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (con1 == (reql.size() + req_id.length) || con2 == (reql.size() + req_id.length)) {
                for (int i = 0; i < req_id.length; i++) {
                    cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                    double cant = Double.parseDouble(req_id[i][1]), costou = Double.parseDouble(req_id[i][2]);
                    cCom.setReq_cantidad(cant);
                    cCom.setReq_costo_unitario(costou);
                    cCom.setReq_costo_sin_iva(costou * cant);
                    result = aEje.AgregarRequerimientos(cCom);
                }
            } else {
                result = "Existen requerimientos que no pueden ser ingresados en este justificativo, verifique la estructura presupuestaria.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void AgregarRequerimientosNP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;
        String solic = request.getParameter("solc");
        String req = request.getParameter("req");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        List<cActividadRequerimiento> reql = new ArrayList<cActividadRequerimiento>();

        adEjecucion aEje = new adEjecucion();
        reql = aEje.ListarSolicitudSolicitudNP(Integer.parseInt(solic));
        Integer con1 = 0, con2 = 0;

        cCom.setSolicitud_id(Integer.parseInt(solic));

        if (req_id.length > 0) {
            for (int i = 0; i < reql.size(); i++) {
                if (reql.get(i).getPresupuesto_proyecto().equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            for (int j = 0; j < req_id.length; j++) {
                if (req_id[j][5].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (con1 == (reql.size() + req_id.length) || con2 == (reql.size() + req_id.length)) {
                for (int i = 0; i < req_id.length; i++) {
                    cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                    double cant = Double.parseDouble(req_id[i][1]), costou = Double.parseDouble(req_id[i][2]);
                    cCom.setReq_cantidad(cant);
                    cCom.setReq_costo_unitario(costou);
                    cCom.setReq_costo_sin_iva(costou * cant);
                    result = aEje.AgregarRequerimientosNP(cCom);
                }
            } else {
                result = "Existen requerimientos que no pueden ser ingresados en este justificativo, verifique la estructura presupuestaria.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void AgregarRequerimientosOPC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;
        String solic = request.getParameter("solc");
        String req = request.getParameter("req");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        List<cActividadRequerimiento> reql = new ArrayList<cActividadRequerimiento>();

        adEjecucion aEje = new adEjecucion();
        reql = aEje.ListarSolicitudSolicitudOPC(Integer.parseInt(solic));
        Integer con1 = 0, con2 = 0;

        cCom.setSolicitud_id(Integer.parseInt(solic));

        if (req_id.length > 0) {
            for (int i = 0; i < reql.size(); i++) {
                if (reql.get(i).getPresupuesto_proyecto().equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            for (int j = 0; j < req_id.length; j++) {
                if (req_id[j][8].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (con1 == (reql.size() + req_id.length) || con2 == (reql.size() + req_id.length)) {
                for (int i = 0; i < req_id.length; i++) {
                    cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                    cCom.setReq_costo_unitario(Double.parseDouble(req_id[i][2]));
                    cCom.setReq_costo_sin_iva(Double.parseDouble(req_id[i][3]));
                    cCom.setReq_costo_total(Double.parseDouble(req_id[i][4]));
                    result = aEje.AgregarRequerimientosOPC(cCom);
                }
            } else {
                result = "Existen requerimientos que no pueden ser ingresados en este justificativo, verifique la estructura presupuestaria.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarRequerimientos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio los requerimientos, vuelva a intentarlo";
        adEjecucion aEjecucion = new adEjecucion();
        String estado = request.getParameter("enviar");
        String usuario = request.getParameter("usuario");
        String req = request.getParameter("req");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cComp = new cActividadRequerimiento();
        Integer can = 0;
        Double t = 0.0, to = 0.0, val = 0.0;

        if (req_id.length > 0) {
            for (String[] req_id1 : req_id) {
                t = Double.parseDouble(req_id1[1]) * Double.parseDouble(req_id1[3]);
                to = (Math.round(t * 100) / 100d);
                val = (Math.round(Double.parseDouble(req_id1[4]) * 100) / 100d);
                if (Double.parseDouble(req_id1[1]) <= Double.parseDouble(req_id1[2])) {
                    if (val >= to) {
                        can++;
                    } else {
                        result = "El monto sobrepasa el planificado.";
                        break;
                    }
                } else {
                    result = "La cantidad sobrepasa la planificada.";
                    break;
                }
            }
            if (can == req_id.length) {
                for (String[] req_id1 : req_id) {
                    cComp.setSolicitud_estado(Integer.parseInt(estado));
                    cComp.setReq_id(Integer.parseInt(req_id1[0]));
                    cComp.setUsuario_nombre(usuario);
                    cComp.setSolestado_observacion(null);
                    cComp.setReq_cantidad(Double.parseDouble(req_id1[1]));
                    cComp.setReq_costo_unitario(Double.parseDouble(req_id1[3]));
                    cComp.setReq_costo_sin_iva(Double.parseDouble(req_id1[1]) * Double.parseDouble(req_id1[3]));
                    cComp.setReq_iva(Integer.parseInt(req_id1[5]));
                    result = aEjecucion.EnviarRequerimientosUnidades(cComp);
                }
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarSolicitud(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String centro = request.getParameter("centro");
        String solicitud = request.getParameter("solicitud");
        String req = request.getParameter("req");
        String rector = request.getParameter("rector");
        String cedula = request.getParameter("cedula");
        String cedulac = request.getParameter("cedulac");
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        Integer co = 0, con1 = 0, con2 = 0;

        cCom.setSolicitud_centro_costo(centro);
        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cedula(cedulac);
        cCom.setSolicitud_nombre(nombre);
        cCom.setSolicitud_cargo(cargo);

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                double cant = Math.round(Double.parseDouble(req_id[i][1]) * 100) / 100d, costou = Double.parseDouble(req_id[i][2]);
                cCom.setReq_cantidad(cant);
                cCom.setReq_costo_unitario(costou);
                cCom.setReq_costo_sin_iva(Math.round((costou * cant) * 100) / 100d);
                if (aEje.VerificarSolicitud(cCom)) {
                    co++;
                }
            }

            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][3].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (co == req_id.length) {
                if (con1 == req_id.length || con2 == req_id.length) {
                    result = aEje.ModificarSolicitud(cCom);
                    if (result.equals("Correcto")) {
                        result = aEje.EliminarReqSolicitud(cCom);
                        if (result.equals("Correcto")) {
                            for (int i = 0; i < req_id.length; i++) {
                                if (result.equals("Correcto")) {
                                    cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                                    double cant = Math.round(Double.parseDouble(req_id[i][1]) * 100) / 100d, costou = Math.round(Double.parseDouble(req_id[i][2]) * 100) / 100d;
                                    double costo = Math.round((costou * cant) * 100) / 100d;
                                    cCom.setReq_cantidad(cant);
                                    cCom.setReq_costo_unitario(costou);
                                    cCom.setReq_costo_sin_iva(costo);
                                    if (req_id[i][4].equals("1")) {
                                        cCom.setReq_costo_total(Math.round((costo * 1.12) * 100) / 100d);
                                    } else {
                                        cCom.setReq_costo_total(costo);
                                    }
                                    result = aEje.InsertarSolicitudReq(cCom);
                                }
                            }
                        }
                    }
                } else {
                    result = "Verifique la estructura presupuestaria de los requerimiento, no se puede unificar el OEI-2 con el OEI-1, OEI-3 y OEI-4";
                }
            } else {
                result = "Verifique las cantidades y costos.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud con código: \"" + solicitud + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarSolicitudNP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String descripcion = request.getParameter("descripcion");
        String solicitud = request.getParameter("solicitud");
        String req = request.getParameter("req");
        String rector = request.getParameter("rector");
        String cedula = request.getParameter("cedula");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adActividadRequerimiento adAct = new adActividadRequerimiento();
        List<cActividadRequerimiento> resultSP = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEje = new adEjecucion();
        Integer co = 0, con1 = 0, con2 = 0;

        cCom.setSolicitud_centro_costo(descripcion);
        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                double cant = Math.round(Double.parseDouble(req_id[i][1]) * 100) / 100d, costou = Double.parseDouble(req_id[i][2]);
                cCom.setReq_cantidad(cant);
                cCom.setReq_costo_unitario(costou);
                cCom.setReq_costo_sin_iva(Math.round((costou * cant) * 100) / 100d);
                if (aEje.VerificarSolicitudNP(cCom)) {
                    co++;
                }
            }

            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][3].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (co == req_id.length) {
                if (con1 == req_id.length || con2 == req_id.length) {
                    result = aEje.ModificarSolicitudNP(cCom);
                    if (result.equals("Correcto")) {
                        result = aEje.EliminarReqSolicitudNP(cCom);
                        if (result.equals("Correcto")) {
                            for (int i = 0; i < req_id.length; i++) {
                                if (result.equals("Correcto")) {
                                    resultSP = adAct.ListarPersonal(Integer.parseInt(req_id[i][0]));
                                    if (rector.equals("4") && resultSP.size() > 0) {
                                        for (int j = 0; j < resultSP.size(); j++) {
                                            if (resultSP.get(j).getSolicitud_id() == 0 && resultSP.get(j).getSolestado_numero() == 1) {
                                                cCom.setSolicitud_id(Integer.parseInt(solicitud));
                                                cCom.setReq_id(resultSP.get(j).getReq_id());
                                                result = adAct.ModificarSPSolicitudDis(cCom, 3);
                                            }
                                        }
                                        if (result.equals("Correcto")) {
                                            cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                                            double cant = Math.round(Double.parseDouble(req_id[i][1]) * 100) / 100d, costou = Math.round(Double.parseDouble(req_id[i][2]) * 100) / 100d;
                                            double costo = Math.round((costou * cant) * 100) / 100d;
                                            cCom.setReq_cantidad(cant);
                                            cCom.setReq_costo_unitario(costou);
                                            cCom.setReq_costo_sin_iva(costo);
                                            if (req_id[i][4].equals("1")) {
                                                cCom.setReq_costo_total(Math.round((costo * 1.12) * 100) / 100d);
                                            } else {
                                                cCom.setReq_costo_total(costo);
                                            }
                                            result = aEje.InsertarSolicitudReqNP(cCom);
                                        } else {
                                            i = req_id.length + 1;
                                            result = aEje.EliminarSolicitudNP(cCom);
                                        }
                                    } else if (rector.equals("4") && resultSP.size() < 1) {
                                        i = req_id.length + 1;
                                        result = aEje.EliminarSolicitudNP(cCom);
                                        result = "Disponibilidad solo se puede generar con servicios profesionales";
                                    } else {
                                        for (int j = 0; j < resultSP.size(); j++) {
                                            if (resultSP.get(j).getSolicitud_id() == 0 && resultSP.get(j).getSolestado_numero() == 1) {
                                                cCom.setSolicitud_id(Integer.parseInt(solicitud));
                                                cCom.setReq_id(resultSP.get(j).getReq_id());
                                                result = adAct.ModificarSPSolicitudDis(cCom, 1);
                                            }
                                        }
                                        if (result.equals("Correcto")) {
                                            cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                                            double cant = Math.round(Double.parseDouble(req_id[i][1]) * 100) / 100d, costou = Math.round(Double.parseDouble(req_id[i][2]) * 100) / 100d;
                                            double costo = Math.round((costou * cant) * 100) / 100d;
                                            cCom.setReq_cantidad(cant);
                                            cCom.setReq_costo_unitario(costou);
                                            cCom.setReq_costo_sin_iva(costo);
                                            if (req_id[i][4].equals("1")) {
                                                cCom.setReq_costo_total(Math.round((costo * 1.12) * 100) / 100d);
                                            } else if (req_id[i][4].equals("0")) {
                                                cCom.setReq_costo_total(costo);
                                            } else {
                                                double total = 0;
                                                for (int j = 0; j < resultSP.size(); j++) {
                                                    total += resultSP.get(i).getReq_costo_total();
                                                }
                                                cCom.setReq_costo_total(total);
                                            }
                                            result = aEje.InsertarSolicitudReqNP(cCom);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    result = "Verifique la estructura presupuestaria de los requerimiento, no se puede unificar el OEI-2 con el OEI-1, OEI-3 y OEI-4";
                }
            } else {
                result = "Verifique las cantidades y costos.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud no pac con código: \"" + solicitud + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarSolicitudOPC(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String descripcion = request.getParameter("descripcion");
        String solicitud = request.getParameter("solicitud");
        String req = request.getParameter("req");
        String rector = request.getParameter("rector");
        String cedula = request.getParameter("cedula");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        Integer co = 0, con1 = 0, con2 = 0;

        cCom.setSolicitud_centro_costo(descripcion);
        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                cCom.setReq_costo_unitario(Double.parseDouble(req_id[i][1]));
                cCom.setReq_costo_sin_iva(Double.parseDouble(req_id[i][2]));
                cCom.setReq_costo_total(Double.parseDouble(req_id[i][3]));
                if (aEje.VerificarSolicitudOPC(cCom)) {
                    co++;
                }
            }

            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][4].equals("000")) {
                    con1++;
                } else {
                    con2++;
                }
            }
            if (co == req_id.length) {
                if (con1 == req_id.length || con2 == req_id.length) {
                    result = aEje.ModificarSolicitudOPC(cCom);
                    if (result.equals("Correcto")) {
                        result = aEje.EliminarReqSolicitudOPC(cCom);
                        if (result.equals("Correcto")) {
                            for (int i = 0; i < req_id.length; i++) {
                                if (result.equals("Correcto")) {
                                    cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                                    cCom.setReq_costo_unitario(Double.parseDouble(req_id[i][1]));
                                    cCom.setReq_costo_sin_iva(Double.parseDouble(req_id[i][2]));
                                    cCom.setReq_costo_total(Double.parseDouble(req_id[i][3]));
                                    result = aEje.InsertarSolicitudReqOPC(cCom);
                                }
                            }
                        }
                    }
                } else {
                    result = "Verifique la estructura presupuestaria de los requerimiento, no se puede unificar el OEI-2 con el OEI-1, OEI-3 y OEI-4";
                }
            } else {
                result = "Verifique las cantidades y costos.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud obligaciones pendientes/comprometidos con código: \"" + solicitud + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarSolicitudInformacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String centro = request.getParameter("centro");
        String solicitud = request.getParameter("solicitud");
        String rector = request.getParameter("rector");
        String cedula = request.getParameter("cedula");
        String cedulac = request.getParameter("cedulac");
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        Integer co = 0, con1 = 0, con2 = 0;

        cCom.setSolicitud_centro_costo(centro);
        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cedula(cedulac);
        cCom.setSolicitud_nombre(nombre);
        cCom.setSolicitud_cargo(cargo);

        result = aEje.ModificarSolicitud(cCom);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud con código: \"" + solicitud + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarSolicitudInformacionUnif(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String centro = request.getParameter("centro");
        String solicitud = request.getParameter("solicitud");
        String rector = request.getParameter("rector");
        String cedula = request.getParameter("cedula");
        String cedulac = request.getParameter("cedulac");
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();
        Integer co = 0, con1 = 0, con2 = 0;

        cCom.setSolicitud_centro_costo(centro);
        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cedula(cedulac);
        cCom.setSolicitud_nombre(nombre);
        cCom.setSolicitud_cargo(cargo);

        result = aEje.ModificarSolicitudUnif(cCom);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud con código: \"" + solicitud + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarSolicitud(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("solicitud");
        String cedula = request.getParameter("cedula");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        result = aEje.EliminarSolicitud(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud con código: \"" + solicitud + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarSolicitudNP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("solicitud");
        String cedula = request.getParameter("cedula");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();
        List<cActividadRequerimiento> resultSP = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adAct = new adActividadRequerimiento();
        List<cActividadRequerimiento> resultE = new ArrayList<cActividadRequerimiento>();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        resultE = aEje.ListarSolicitudSolicitudNP(Integer.parseInt(solicitud));
        for (int i = 0; i < resultE.size(); i++) {
            resultSP = adAct.ListarPersonal(resultE.get(i).getReq_id());
            for (int j = 0; j < resultSP.size(); j++) {
                if (resultSP.get(j).getSolicitud_id() > 0 && resultSP.get(j).getAp_id() == 1) {
                    cComp.setReq_id(resultSP.get(j).getReq_id());
                    result = adAct.EliminarEstadoDispo(cComp);
                }
            }
        }

        result = aEje.EliminarSolicitudNP(cComp);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud no pac con código: \"" + solicitud + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarSolicitudOPC(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("solicitud");
        String cedula = request.getParameter("cedula");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        result = aEje.EliminarSolicitudOPC(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud obligaciones pendientes/comprometidos con código: \"" + solicitud + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio el justificativo.";
        List<cActividadRequerimiento> result2 = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String usuario = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String solicitud = request.getParameter("idSolicitud");
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setUsuario_nombre(usuario);

        result2 = aEjecucion.VerificarSolicitud(Integer.parseInt(solicitud), 1);
        if (Objects.equals(result2.get(0).getActividad_id(), result2.get(0).getSolicitud_id())) {
            result = aEjecucion.EnviarSolicitud(cComp);
        } else {
            result = "No se puede enviar porque se aplicó una reforma en disminución, por favor verifique.";
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarSolicitudNP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio la solicitud.";
        List<cActividadRequerimiento> result2 = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        adActividadRequerimiento adAct = new adActividadRequerimiento();
        String usuario = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String solicitud = request.getParameter("idSolicitud");
        String observacion = request.getParameter("observacion");
        String anio = request.getParameter("anio");
        cActividadRequerimiento cComp = new cActividadRequerimiento();
        List<cActividadRequerimiento> resultE = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> resultEF = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> resultSP = new ArrayList<cActividadRequerimiento>();

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setUsuario_nombre(usuario);
        if (estado.equals("1") || estado.equals("15")) {
            result2 = aEjecucion.VerificarSolicitud(Integer.parseInt(solicitud), 2);
            if (Objects.equals(result2.get(0).getActividad_id(), result2.get(0).getSolicitud_id())) {
                result = aEjecucion.EnviarSolicitudnp(cComp);
            } else {
                result = "No se puede enviar porque se aplicó una reforma en disminución, por favor verifique.";
            }
        } else {
            cComp.setSolestado_observacion(observacion);
            result = aEjecucion.EnviarSolicitudnpOb(cComp);
        }
        if (result.equals("Correcto") && estado.equals("15")) {
            Integer sol = aEjecucion.codigoSolicitud(Integer.parseInt(anio));
            Integer solOPC = aEjecucion.codigoSolicitudOPC(Integer.parseInt(anio));
            if (sol > solOPC) {
                cComp.setSolicitud_codigo(sol.toString());
            } else {
                cComp.setSolicitud_codigo(solOPC.toString());
            }
            if (!aEjecucion.comporbacionCodigo(Integer.parseInt(solicitud))) {
                result = aEjecucion.ingresarCodigoNP(cComp);
            }
            resultE = aEjecucion.ListarSolicitudSolicitudNP(Integer.parseInt(solicitud));
            int count = 0;
            for (int i = 0; i < resultE.size(); i++) {
                resultSP = adAct.ListarPersonal(resultE.get(i).getReq_id());
                for (int j = 0; j < resultSP.size(); j++) {
                    if (resultSP.get(j).getSolicitud_id() == 0 && resultSP.get(j).getSolestado_numero() == 1) {
                        cComp.setSolicitud_id(Integer.parseInt(solicitud));
                        cComp.setReq_id(resultSP.get(j).getReq_id());
                        result = adAct.ModificarSPSolicitud(cComp);
                    } else if (resultSP.get(j).getSolicitud_id() > 0 && resultSP.get(j).getAp_id() == 1) {
                        cComp.setSolicitud_id(Integer.parseInt(solicitud));
                        cComp.setReq_id(resultSP.get(j).getReq_id());
                        result = adAct.ModificarSPSolicitudDispo(cComp);
                        count++;
                    }
                }

                resultEF = adAct.ListaReqfechas(resultE.get(i).getReq_id());
                if (resultEF.size() > 0 && count == 0) {
                    cComp.setSolicitud_id(Integer.parseInt(solicitud));
                    cComp.setReq_id(resultE.get(i).getReq_id());
                    cComp.setSolestado_fecha(resultEF.get(0).getSolestado_fecha());
                    cComp.setReq_estado(resultEF.get(0).getReq_estado());
                    result = adAct.ModificarSolicitudEnv(cComp);
                } else {
                    for (int j = 0; j < resultSP.size(); j++) {
                        cComp.setSolicitud_estado(32);
                        cComp.setReq_id(resultE.get(i).getReq_id());
                        cComp.setUsuario_nombre(usuario);
                        cComp.setSolicitud_id(Integer.parseInt(solicitud));
                        cComp.setSolestado_observacion("VERIFICADO EN DISPONIBILIDAD");
                        result = aEjecucion.EnviarRequerimientosUnidadesRegDispo(cComp);
                    }
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarSolicitudOPC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio la soliciutd.";
        adEjecucion aEjecucion = new adEjecucion();
        String usuario = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String solicitud = request.getParameter("idSolicitud");
        String observacion = request.getParameter("observacion");
        String anio = request.getParameter("anio");
        cActividadRequerimiento cComp = new cActividadRequerimiento();
        List<cActividadRequerimiento> resultE = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> resultEF = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> resultSP = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento adAct = new adActividadRequerimiento();

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setUsuario_nombre(usuario);

        if ((estado.equals("1") || estado.equals("15")) && observacion.isEmpty()) {
            result = aEjecucion.EnviarSolicitudOPC(cComp);
        } else {
            cComp.setSolestado_observacion(observacion);
            //VERIFICAR LA OBSERVACION
            result = aEjecucion.EnviarSolicitudOPCOBS(cComp);
        }
        if (result.equals("Correcto") && estado.equals("15")) {
            Integer sol = aEjecucion.codigoSolicitud(Integer.parseInt(anio));
            Integer solOPC = aEjecucion.codigoSolicitudOPC(Integer.parseInt(anio));
            if (sol > solOPC) {
                cComp.setSolicitud_codigo(sol.toString());
            } else {
                cComp.setSolicitud_codigo(solOPC.toString());
            }
            if (!aEjecucion.comporbacionCodigoOPC(Integer.parseInt(solicitud))) {
                result = aEjecucion.ingresarCodigoOPC(cComp);
            }
            resultE = aEjecucion.ListarSolicitudSolicitudOPC(Integer.parseInt(solicitud));
            for (int i = 0; i < resultE.size(); i++) {
                resultEF = adAct.ListaReqfechasop(resultE.get(i).getReq_id());
                if (resultEF.size() > 0) {
                    cComp.setSolicitud_id(Integer.parseInt(solicitud));
                    cComp.setReq_id(resultE.get(i).getReq_id());
                    cComp.setSolestado_fecha(resultEF.get(0).getSolestado_fecha());
                    cComp.setReq_estado(resultEF.get(0).getReq_estado());
                    result = adAct.ModificarSolicitudEnvOP(cComp);
                }
                resultSP = adAct.ListarPersonalO(resultE.get(i).getReq_id());
                for (int j = 0; j < resultSP.size(); j++) {
                    if (resultSP.get(j).getSolicitud_id() == 0 && resultSP.get(j).getSolestado_numero() == 1) {
                        cComp.setSolicitud_id(Integer.parseInt(solicitud));
                        cComp.setReq_id(resultSP.get(j).getReq_id());
                        result = adAct.ModificarSPSolicitudOP(cComp);
                    }
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void GuardarSalvaguardado(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se guardo.";
        adEjecucion aEjecucion = new adEjecucion();
        String req = request.getParameter("req");
        String salv = request.getParameter("salv");
        String cedulau = request.getParameter("usuario");

        result = aEjecucion.GuardarSalvaguardar(Boolean.parseBoolean(salv), Integer.parseInt(req));

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + req + "\" se salvaguardo correctamente");
            objTransaccion.setTransaccion_cedula(cedulau);
            objTransaccion.setTransaccion_ag(1);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaEnvios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String solicitud = request.getParameter("solicitud");
        String tipo = request.getParameter("tipo");

        if (tipo.equals("1")) {
            result = aEjecucion.ListarSolicitudEnviados(Integer.parseInt(solicitud));
        } else {
            result = aEjecucion.ListarSolicitudEnviadosUni(Integer.parseInt(solicitud));
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaEnviosNP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String solicitud = request.getParameter("solicitud");
        String tipo = request.getParameter("tipo");

        if (tipo.equals("1")) {
            result = aEjecucion.ListarSolicitudEnviadosNP(Integer.parseInt(solicitud));
        } else {
            result = aEjecucion.ListarSolicitudEnviadosOPC(Integer.parseInt(solicitud));
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarAutoridades(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;
        String solicitud = request.getParameter("idSolicitud");
        String autoridad = request.getParameter("autoridad");
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();

        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(autoridad));

        result = aEje.ModificarAutoridades(cCom);

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ValidarReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;
        String req = request.getParameter("req");
        String validar = request.getParameter("validar");
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();

        cCom.setReq_id(Integer.parseInt(req));
        cCom.setReq_disponible(Boolean.parseBoolean(validar));

        result = aEje.ModificarDisponibilidad(cCom);

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListRequeUnionReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String estado = request.getParameter("estado");
        String anio = request.getParameter("anio");

        result = aEjecucion.ListarRequerimientosUnion(Integer.parseInt(estado), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarRequerimientosReg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio los requerimientos, vuelva a intentarlo";
        adEjecucion aEjecucion = new adEjecucion();
        String usuario = request.getParameter("usuario");
        String req = request.getParameter("req");
        String observacion = request.getParameter("observacion");
        String estado = request.getParameter("estado");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        List<cActividadRequerimiento> cActres = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setReq_id(Integer.parseInt(req));
        cComp.setUsuario_nombre(usuario);
        cComp.setSolestado_observacion(observacion);
        result = aEjecucion.EnviarRequerimientosUnidadesReg(cComp);
        if (result.equals("Correcto")) {
            cActres = aComp.ListarPersonal(Integer.parseInt(req));
            for (int i = 0; i < cActres.size(); i++) {
                if (cActres.get(i).getSolicitud_id() == 0) {
                    if (estado.equals("32") && cActres.get(i).getSolestado_numero() == 1) {
                        cComp.setActividad_id(cActres.get(i).getReq_id());
                        cComp.setAg_id(Integer.parseInt(estado));
                        cComp.setSolicitud_cedula(usuario);
                        result = aComp.EnviarServiciosProfesionales(cComp);
                    } else if (!estado.equals("32")) {
                        cComp.setActividad_id(cActres.get(i).getReq_id());
                        cComp.setAg_id(Integer.parseInt(estado));
                        cComp.setSolicitud_cedula(usuario);
                        result = aComp.EnviarServiciosProfesionales(cComp);
                    }
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarRequerimientosRegOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio los requerimientos, vuelva a intentarlo";
        adEjecucion aEjecucion = new adEjecucion();
        String usuario = request.getParameter("usuario");
        String req = request.getParameter("req");
        String observacion = request.getParameter("observacion");
        String estado = request.getParameter("estado");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        List<cActividadRequerimiento> cActres = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setReq_id(Integer.parseInt(req));
        cComp.setUsuario_nombre(usuario);
        cComp.setSolestado_observacion(observacion);
        result = aEjecucion.EnviarRequerimientosUnidadesRegOP(cComp);
        if (result.equals("Correcto")) {
            cActres = aComp.ListarPersonalO(Integer.parseInt(req));
            for (int i = 0; i < cActres.size(); i++) {
                if (cActres.get(i).getSolicitud_id() == 0) {
                    if (estado.equals("32") && cActres.get(i).getSolestado_numero() == 1) {
                        cComp.setActividad_id(cActres.get(i).getReq_id());
                        cComp.setAg_id(Integer.parseInt(estado));
                        cComp.setSolicitud_cedula(usuario);
                        result = aComp.EnviarServiciosProfesionalesOP(cComp);
                    } else if (!estado.equals("32")) {
                        cComp.setActividad_id(cActres.get(i).getReq_id());
                        cComp.setAg_id(Integer.parseInt(estado));
                        cComp.setSolicitud_cedula(usuario);
                        result = aComp.EnviarServiciosProfesionalesOP(cComp);
                    }
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarServiciosProf(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio los requerimientos, vuelva a intentarlo";
        String usuario = request.getParameter("usuario");
        String req = request.getParameter("req");
        String estado = request.getParameter("estado");
        String[][] req_id = new Gson().fromJson(req, String[][].class);

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        adEjecucion aEj = new adEjecucion();

        cComp.setAg_id(Integer.parseInt(estado));
        cComp.setSolicitud_cedula(usuario);

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][1].equals(req_id[i][2])) {
                    cComp.setActividad_id(Integer.parseInt(req_id[i][0]));
                    cComp.setReq_id(Integer.parseInt(req_id[i][0]));
                    cComp.setFecha_fin(req_id[i][4]);
                    result = aComp.ModificarSPSolicitudDisF(cComp, 1);
                    if (result.equals("Correcto")) {
                        result = aComp.EnviarServiciosProfesionales(cComp);
                    }
                } else {
                    //Año diferente
                    cComp.setReq_id(Integer.parseInt(req_id[i][0]));
                    cComp.setFecha_fin(req_id[i][4]);
                    cComp.setAutoridades_cedula(req_id[i][5]);
                    cComp.setAutoridades_nombre(req_id[i][6]);
                    cComp.setAutoridades_cargo(req_id[i][7]);
                    cComp.setReq_descripcion(req_id[i][8]);
                    cComp.setReq_costo_total(Double.parseDouble(req_id[i][9]));
                    cComp.setSolicitud_cedula(req_id[i][10]);
                    cComp.setReq_nombre(req_id[i][11]);
                    cComp.setReq_cpc(req_id[i][12]);
                    cComp.setSolestado_observacion("Requerimiento por disponibilidad en solicitud " + req_id[i][13] + "-STP-" + req_id[i][1]);
                    cComp.setTc_id(Integer.parseInt(req_id[i][14]));
                    cComp.setFecha_inicio(req_id[i][15]);
                    cComp.setReq_costo_sin_iva(Double.parseDouble(req_id[i][17]));
                    cComp.setAg_id(Integer.parseInt(req_id[i][18]));
                    cComp.setSolicitud_id(aComp.codigoSiguienteServicioP());
                    cComp.setN_horas(null);
                    cComp.setCantidad(1.0);
                    if (req_id[i][14].equals("3") && aComp.VerificacionServicioProfesional(cComp)) {
                        result = "Estudiante";
                    } else {
                        result = aEj.InsertarServiciosP(cComp);
                    }
                    //result = aComp.EnviarServiciosProfesionales(cComp);
                }
            }
        } else {
            result = "Debe seleccionar";
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarSolicitudJus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "No se envio el justificativo.", result2 = "Incorrecto";
        adEjecucion aEjecucion = new adEjecucion();
        String usuario = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String solicitud = request.getParameter("idSolicitud");
        String observacion = request.getParameter("observacion");
        String tipo = request.getParameter("tipo");
        String anio = request.getParameter("anio");
        String proceso = request.getParameter("proceso");
        String observacionpr = request.getParameter("observacionpr");
        List<cActividadRequerimiento> result3 = new ArrayList<cActividadRequerimiento>();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        List<cActividadRequerimiento> req = new ArrayList<cActividadRequerimiento>();

        req = aEjecucion.ListarSolicitudSolicitud(Integer.parseInt(solicitud));
        Integer sol, soluni;

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setUsuario_nombre(usuario);
        cComp.setSolestado_observacion(observacion);
        sol = aEjecucion.codigoJustificativo(Integer.parseInt(anio));
        soluni = aEjecucion.codigoJustificativoUnificados(Integer.parseInt(anio));
        if (sol > soluni) {
            cComp.setSolicitud_codigo(sol.toString());
        } else {
            cComp.setSolicitud_codigo(soluni.toString());
        }

        if (estado.equals("31")) {
            if (tipo.equals("1")) {
                result = aEjecucion.EnviarSolicitudObser(cComp);
                if (result.equals("Correcto")) {
                    cComp.setTc_id(Integer.parseInt(proceso));
                    if (proceso.equals("7") && observacionpr.isEmpty()) {
                        result = "Debe ingresar el nombre del otro proceso.";
                    } else if (proceso.equals("7") && !observacionpr.isEmpty()) {
                        cComp.setActividad_responsable(observacionpr);
                        result = aEjecucion.CodigoJustificativoObserv(cComp);
                    } else {
                        result = aEjecucion.CodigoJustificativo(cComp);
                    }
                }
            } else {
                result = aEjecucion.EnviarSolicitudObserUnif(cComp);
                if (result.equals("Correcto")) {
                    result = aEjecucion.CodigoJustificativoUnificado(cComp);
                }
            }
            if (!result.equals("Correcto")) {
                if (tipo.equals("1")) {
                    result = aEjecucion.EliminarFechaEnvio(cComp);
                } else {
                    result = aEjecucion.EliminarFechaEnvioUnifi(cComp);
                }
                if (proceso.equals("7") && observacionpr.isEmpty()) {
                    result = "Debe ingresar el nombre del otro proceso.";
                } else {
                    result = "Error al ingresar codigo";
                }
            }
        } else if (estado.equals("40")) {
            Integer num = 0;
            if (req.size() > 0) {
                for (int i = 0; i < req.size(); i++) {
                    if (result2.equals("Incorrecto") || result2.equals("Correcto")) {
                        num++;
                        cComp.setReq_id(req.get(i).getReq_id());
                        cComp.setReq_cantidad(req.get(i).getReq_cantidad());
                        cComp.setReq_costo_unitario(req.get(i).getReq_costo_unitario());
                        cComp.setReq_costo_sin_iva(req.get(i).getReq_costo_sin_iva());
                        cComp.setSolicitud_id(Integer.parseInt(solicitud));
                        result2 = aEjecucion.InsertAnulados(cComp);
                    }
                }
                if (result2.equals("Correcto") && num == req.size()) {
                    result = aEjecucion.EliminarReqSolicitud(cComp);
                    if (result.equals("Correcto")) {
                        result = aEjecucion.EnviarSolicitudObser(cComp);
                    }
                }
            }
        } else {
            Integer oficio, oficiouni, oficio2;
            if ((estado.equals("33") || estado.equals("35")) && tipo.equals("1")) {
                oficio = aEjecucion.codigoSiguienteOficio(Integer.parseInt(estado));
                oficio2 = aEjecucion.codigoJustificativoUnificadosNumero(Integer.parseInt(estado));
                if (oficio > oficio2) {
                    oficiouni = oficio;
                } else {
                    oficiouni = oficio2;
                }
                cComp.setSolestado_numero(oficiouni);
                result = aEjecucion.EnviarSolicitudObserN(cComp);
            } else if ((!estado.equals("33") && !estado.equals("35")) && tipo.equals("1")) {
                result3 = aEjecucion.VerificarSolicitud(Integer.parseInt(solicitud), 1);
                if (Objects.equals(result3.get(0).getActividad_id(), result3.get(0).getSolicitud_id())) {
                    result = aEjecucion.EnviarSolicitudObser(cComp);
                } else {
                    result = "No se puede enviar porque se aplicó una reforma en disminución, por favor verifique.";
                }

            } else if ((estado.equals("33") || estado.equals("35")) && !tipo.equals("1")) {
                oficio = aEjecucion.codigoSiguienteOficio(Integer.parseInt(estado));
                oficio2 = aEjecucion.codigoJustificativoUnificadosNumero(Integer.parseInt(estado));
                if (oficio > oficio2) {
                    oficiouni = oficio;
                } else {
                    oficiouni = oficio2;
                }
                cComp.setSolestado_numero(oficiouni);
                result = aEjecucion.EnviarSolicitudObserUnifNum(cComp);
            } else {
                result = aEjecucion.EnviarSolicitudObserUnif(cComp);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarUnificacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = "No se unifico, vuelva a intentarlo";
        adEjecucion aEjecucion = new adEjecucion();
        String nombre = request.getParameter("nombre");
        String costouni = request.getParameter("costouni");
        String estado = request.getParameter("estado");
        String descripcion = request.getParameter("descripcion");
        String unidad = request.getParameter("unidad");
        String req = request.getParameter("req");
        String partes = request.getParameter("partes");
        String cedula = request.getParameter("cedula");
        String anio = request.getParameter("anio");
        Double cantidad = 0.0, cantidad1 = 0.0, cantidad2 = 0.0, cantidad3 = 0.0, cantidad4 = 0.0, cos = 0.0, cos1 = 0.0, cos2 = 0.0, cos3 = 0.0, cos4 = 0.0;
        Integer id1 = 0, id2 = 0, id3 = 0, id4 = 0;
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cComp = new cActividadRequerimiento();
        Integer con = 0, con1 = 0, con2 = 0, conun = 1;
        cComp.setMes_id(Integer.parseInt(anio));

        if (req_id.length > 0) {
            if ((estado.equals("28") && !partes.equals("1")) || (estado.equals("29") && !partes.equals("1")) || (estado.equals("50") && !partes.equals("1"))) {
                for (int i = 0; i < req_id.length; i++) {
                    if (Double.parseDouble(costouni) <= Double.parseDouble(req_id[i][1])) {
                        cantidad += Double.parseDouble(req_id[i][2]);
                        con++;
                        if (req_id[i][4].equals("1")) {
                            con1++;
                        } else {
                            con2++;
                        }
                        if (i == 0) {
                            for (int j = 1; j < req_id.length; j++) {
                                if (req_id[i][5].equals(req_id[j][5])) {
                                    conun++;
                                }
                            }
                        }
                    }
                }
                if (con == req_id.length && conun == req_id.length) {
                    if (con1 == req_id.length || con2 == req_id.length) {
                        if (cantidad > 0) {
                            for (String[] req_id1 : req_id) {
                                if (req_id1[3].equals("1") && (Double.parseDouble(costouni) <= Double.parseDouble(req_id1[1]))) {
                                    cantidad1 += Double.parseDouble(req_id1[2]);
                                }
                            }
                            if (cantidad1 > 0) {
                                id1 = aEjecucion.codigoSiguienteUnificado();
                                cComp.setReq_nombre(nombre);
                                cComp.setReq_id(id1);
                                cComp.setReq_costo_unitario(Double.parseDouble(costouni));
                                cComp.setReq_costo_sin_iva(cantidad1 * (Double.parseDouble(costouni)));
                                cComp.setPerspectiva_id(1);
                                cComp.setReq_cantidad(cantidad1);
                                cComp.setReq_descripcion(descripcion);
                                cComp.setReq_verificacion(Integer.parseInt(estado));
                                cComp.setUnidad_id(Integer.parseInt(unidad));
                                if (con1 > 0) {
                                    cComp.setReq_iva(1);
                                }
                                result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                                if (result.equals("Correcto")) {
                                    cTransaccion objTransaccion = new cTransaccion();
                                    objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id1 + "\" se ingresò correctamente");
                                    objTransaccion.setTransaccion_cedula(cedula);
                                    objTransaccion.setTransaccion_ag(intIdAg);
                                    objTransaccion.setTransaccion_tipo(1);
                                    ingresarTransaccion(objTransaccion);
                                }
                            }
                            for (String[] req_id1 : req_id) {
                                if (req_id1[3].equals("2") && (Double.parseDouble(costouni) <= Double.parseDouble(req_id1[1]))) {
                                    cantidad2 += Double.parseDouble(req_id1[2]);
                                }
                            }
                            if (cantidad2 > 0) {
                                id2 = aEjecucion.codigoSiguienteUnificado();
                                cComp.setReq_nombre(nombre);
                                cComp.setReq_id(id2);
                                cComp.setReq_costo_unitario(Double.parseDouble(costouni));
                                cComp.setReq_costo_sin_iva(cantidad2 * (Double.parseDouble(costouni)));
                                cComp.setPerspectiva_id(2);
                                cComp.setReq_cantidad(cantidad2);
                                cComp.setReq_descripcion(descripcion);
                                cComp.setReq_verificacion(Integer.parseInt(estado));
                                cComp.setUnidad_id(Integer.parseInt(unidad));
                                if (con1 > 0) {
                                    cComp.setReq_iva(1);
                                }
                                result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                                if (result.equals("Correcto")) {
                                    cTransaccion objTransaccion = new cTransaccion();
                                    objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id2 + "\" se ingresò correctamente");
                                    objTransaccion.setTransaccion_cedula(cedula);
                                    objTransaccion.setTransaccion_ag(intIdAg);
                                    objTransaccion.setTransaccion_tipo(1);
                                    ingresarTransaccion(objTransaccion);
                                }
                            }
                            for (String[] req_id1 : req_id) {
                                if (req_id1[3].equals("3") && (Double.parseDouble(costouni) <= Double.parseDouble(req_id1[1]))) {
                                    cantidad3 += Double.parseDouble(req_id1[2]);
                                }
                            }
                            if (cantidad3 > 0) {
                                id3 = aEjecucion.codigoSiguienteUnificado();
                                cComp.setReq_nombre(nombre);
                                cComp.setReq_id(id3);
                                cComp.setReq_costo_unitario(Double.parseDouble(costouni));
                                cComp.setReq_costo_sin_iva(cantidad3 * (Double.parseDouble(costouni)));
                                cComp.setPerspectiva_id(3);
                                cComp.setReq_cantidad(cantidad3);
                                cComp.setReq_descripcion(descripcion);
                                cComp.setReq_verificacion(Integer.parseInt(estado));
                                cComp.setUnidad_id(Integer.parseInt(unidad));
                                if (con1 > 0) {
                                    cComp.setReq_iva(1);
                                }
                                result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                                if (result.equals("Correcto")) {
                                    cTransaccion objTransaccion = new cTransaccion();
                                    objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id3 + "\" se ingresò correctamente");
                                    objTransaccion.setTransaccion_cedula(cedula);
                                    objTransaccion.setTransaccion_ag(intIdAg);
                                    objTransaccion.setTransaccion_tipo(1);
                                    ingresarTransaccion(objTransaccion);
                                }
                            }
                            for (String[] req_id1 : req_id) {
                                if (req_id1[3].equals("4") && (Double.parseDouble(costouni) <= Double.parseDouble(req_id1[1]))) {
                                    cantidad4 += Double.parseDouble(req_id1[2]);
                                }
                            }
                            if (cantidad4 > 0) {
                                id4 = aEjecucion.codigoSiguienteUnificado();
                                cComp.setReq_nombre(nombre);
                                cComp.setReq_id(id4);
                                cComp.setReq_costo_unitario(Double.parseDouble(costouni));
                                cComp.setReq_costo_sin_iva(cantidad4 * (Double.parseDouble(costouni)));
                                cComp.setPerspectiva_id(4);
                                cComp.setReq_cantidad(cantidad4);
                                cComp.setReq_descripcion(descripcion);
                                cComp.setReq_verificacion(Integer.parseInt(estado));
                                cComp.setUnidad_id(Integer.parseInt(unidad));
                                if (con1 > 0) {
                                    cComp.setReq_iva(1);
                                }
                                result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                                if (result.equals("Correcto")) {
                                    cTransaccion objTransaccion = new cTransaccion();
                                    objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id4 + "\" se ingresò correctamente");
                                    objTransaccion.setTransaccion_cedula(cedula);
                                    objTransaccion.setTransaccion_ag(intIdAg);
                                    objTransaccion.setTransaccion_tipo(1);
                                    ingresarTransaccion(objTransaccion);
                                }
                            }
                            if (result.equals("Correcto")) {
                                for (String[] req_id1 : req_id) {
                                    if (Double.parseDouble(costouni) <= Double.parseDouble(req_id1[1])) {
                                        if (req_id1[3].equals("1")) {
                                            cComp.setReq_id(id1);
                                        } else if (req_id1[3].equals("2")) {
                                            cComp.setReq_id(id2);
                                        } else if (req_id1[3].equals("3")) {
                                            cComp.setReq_id(id3);
                                        } else if (req_id1[3].equals("4")) {
                                            cComp.setReq_id(id4);
                                        }
                                        cComp.setActividad_id(Integer.parseInt(req_id1[0]));
                                        result = aEjecucion.IngresarRequerimientosUnificadosRelacionado(cComp);
                                    }
                                }
                            }
                        }
                    } else {
                        result = "No se puede unificar los requerimientos, verifique el iva y las unidades de medida.";
                    }
                } else {
                    result = "No se puede unificar, porque el costo de uno de los requerimientos seleccionados es inferior.";
                }
            } else {
                for (int i = 0; i < req_id.length; i++) {
//                    if (req_id[i][4].equals("1")) {
//                        con1++;
//                    } else {
//                        con2++;
//                    }
                    if (i == 0) {
                        for (int j = 1; j < req_id.length; j++) {
                            if (req_id[i][5].equals(req_id[j][5])) {
                                conun++;
                            }
                        }
                    }
                }
//                if ((con1 == req_id.length || con2 == req_id.length) && conun == req_id.length) {
                if (conun == req_id.length) {
                    for (String[] req_id1 : req_id) {
                        if (req_id1[3].equals("1")) {
                            //cantidad1 += Double.parseDouble(req_id1[2]);
                            cantidad1 = 1.0;
                            cos1 += Double.parseDouble(req_id1[6]);
                        }
                    }
                    if (cantidad1 > 0) {
                        id1 = aEjecucion.codigoSiguienteUnificado();
                        cComp.setReq_nombre(nombre);
                        cComp.setReq_id(id1);
                        cComp.setReq_costo_unitario(cos1);
                        cComp.setReq_costo_sin_iva(cantidad1 * cos1);
                        cComp.setPerspectiva_id(1);
                        cComp.setReq_cantidad(cantidad1);
                        cComp.setReq_descripcion(descripcion);
                        cComp.setReq_verificacion(Integer.parseInt(estado));
                        cComp.setUnidad_id(Integer.parseInt(unidad));
//                        if (con1 > 0) {
                        cComp.setReq_iva(1);
//                        }
                        result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                        if (result.equals("Correcto")) {
                            cTransaccion objTransaccion = new cTransaccion();
                            objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id1 + "\" se ingresò correctamente");
                            objTransaccion.setTransaccion_cedula(cedula);
                            objTransaccion.setTransaccion_ag(intIdAg);
                            objTransaccion.setTransaccion_tipo(1);
                            ingresarTransaccion(objTransaccion);
                        }
                    }
                    for (String[] req_id1 : req_id) {
                        if (req_id1[3].equals("2")) {
                            //cantidad2 += Double.parseDouble(req_id1[2]);
                            cantidad2 = 1.0;
                            cos2 += Double.parseDouble(req_id1[6]);
                        }
                    }
                    if (cantidad2 > 0) {
                        id2 = aEjecucion.codigoSiguienteUnificado();
                        cComp.setReq_nombre(nombre);
                        cComp.setReq_id(id2);
                        cComp.setReq_costo_unitario(cos2);
                        cComp.setReq_costo_sin_iva(cantidad2 * cos2);
                        cComp.setPerspectiva_id(2);
                        cComp.setReq_cantidad(cantidad2);
                        cComp.setReq_descripcion(descripcion);
                        cComp.setReq_verificacion(Integer.parseInt(estado));
                        cComp.setUnidad_id(Integer.parseInt(unidad));
//                        if (con1 > 0) {
                        cComp.setReq_iva(1);
//                        }
                        result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                        if (result.equals("Correcto")) {
                            cTransaccion objTransaccion = new cTransaccion();
                            objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id2 + "\" se ingresò correctamente");
                            objTransaccion.setTransaccion_cedula(cedula);
                            objTransaccion.setTransaccion_ag(intIdAg);
                            objTransaccion.setTransaccion_tipo(1);
                            ingresarTransaccion(objTransaccion);
                        }
                    }
                    for (String[] req_id1 : req_id) {
                        if (req_id1[3].equals("3")) {
                            //cantidad3 += Double.parseDouble(req_id1[2]);
                            cantidad3 = 1.0;
                            cos3 += Double.parseDouble(req_id1[6]);
                        }
                    }
                    if (cantidad3 > 0) {
                        id3 = aEjecucion.codigoSiguienteUnificado();
                        cComp.setReq_nombre(nombre);
                        cComp.setReq_id(id3);
                        cComp.setReq_costo_unitario(cos3);
                        cComp.setReq_costo_sin_iva(cantidad3 * cos3);
                        cComp.setPerspectiva_id(3);
                        cComp.setReq_cantidad(cantidad3);
                        cComp.setReq_descripcion(descripcion);
                        cComp.setReq_verificacion(Integer.parseInt(estado));
                        cComp.setUnidad_id(Integer.parseInt(unidad));
//                        if (con1 > 0) {
                        cComp.setReq_iva(1);
//                        }
                        result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                        if (result.equals("Correcto")) {
                            cTransaccion objTransaccion = new cTransaccion();
                            objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id3 + "\" se ingresò correctamente");
                            objTransaccion.setTransaccion_cedula(cedula);
                            objTransaccion.setTransaccion_ag(intIdAg);
                            objTransaccion.setTransaccion_tipo(1);
                            ingresarTransaccion(objTransaccion);
                        }
                    }
                    for (String[] req_id1 : req_id) {
                        if (req_id1[3].equals("4")) {
                            //cantidad4 += Double.parseDouble(req_id1[2]);
                            cantidad4 = 1.0;
                            cos4 += Double.parseDouble(req_id1[6]);
                        }
                    }
                    if (cantidad4 > 0) {
                        id4 = aEjecucion.codigoSiguienteUnificado();
                        cComp.setReq_nombre(nombre);
                        cComp.setReq_id(id4);
                        cComp.setReq_costo_unitario(cos4);
                        cComp.setReq_costo_sin_iva(cantidad4 * cos4);
                        cComp.setPerspectiva_id(4);
                        cComp.setReq_cantidad(cantidad4);
                        cComp.setReq_descripcion(descripcion);
                        cComp.setReq_verificacion(Integer.parseInt(estado));
                        cComp.setUnidad_id(Integer.parseInt(unidad));
//                        if (con1 > 0) {
                        cComp.setReq_iva(1);
//                        }
                        result = aEjecucion.IngresarRequerimientosUnificados(cComp);
                        if (result.equals("Correcto")) {
                            cTransaccion objTransaccion = new cTransaccion();
                            objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + id4 + "\" se ingresò correctamente");
                            objTransaccion.setTransaccion_cedula(cedula);
                            objTransaccion.setTransaccion_ag(intIdAg);
                            objTransaccion.setTransaccion_tipo(1);
                            ingresarTransaccion(objTransaccion);
                        }
                    }
                    if (result.equals("Correcto")) {
                        for (String[] req_id1 : req_id) {
                            if (req_id1[3].equals("1")) {
                                cComp.setReq_id(id1);
                            } else if (req_id1[3].equals("2")) {
                                cComp.setReq_id(id2);
                            } else if (req_id1[3].equals("3")) {
                                cComp.setReq_id(id3);
                            } else if (req_id1[3].equals("4")) {
                                cComp.setReq_id(id4);
                            }
                            cComp.setActividad_id(Integer.parseInt(req_id1[0]));
                            result = aEjecucion.IngresarRequerimientosUnificadosRelacionado(cComp);
                        }
                    }
                } else {
                    result = "No se puede unificar los requerimientos, verifique el iva y las unidades de medida.";
                }
            }
        } else {
            result = "Debe seleccionar requerimientos.";
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListRequeUnificados(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String estado = request.getParameter("estado");
        String anio = request.getParameter("anio");

        result = aEjecucion.ListarRequerimientosUnificados(Integer.parseInt(estado), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void AnularSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "Correcto";
        String observacion = request.getParameter("observacion");
        String estado = request.getParameter("estado");
        String solicitud = request.getParameter("solicitud");
        String usuario = request.getParameter("cedula");

        adEjecucion aEjecucion = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        List<cActividadRequerimiento> req = new ArrayList<cActividadRequerimiento>();

        req = aEjecucion.ListarSolicitudSolicitudNP(Integer.parseInt(solicitud));

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setUsuario_nombre(usuario);
        cComp.setSolestado_observacion(observacion);
        Integer num = 0;
        if (req.size() > 0) {
            for (int i = 0; i < req.size(); i++) {
                if (result.equals("Correcto")) {
                    num++;
                    cComp.setReq_id(req.get(i).getReq_id());
                    cComp.setReq_cantidad(req.get(i).getReq_cantidad());
                    cComp.setReq_costo_unitario(req.get(i).getReq_costo_unitario());
                    cComp.setReq_costo_sin_iva(req.get(i).getReq_costo_sin_iva());
                    cComp.setSolicitud_id(Integer.parseInt(solicitud));
                    result = aEjecucion.InsertAnulados(cComp);
                    if (req.get(i).getActividad_eval().size() > 0) {
                        for (int j = 0; j < req.get(i).getActividad_eval().size(); j++) {
                            if (req.get(i).getActividad_eval().get(j).getSolicitud_id() == Integer.parseInt(solicitud)) {
                                result = aEjecucion.AnularerviciosP(Integer.parseInt(solicitud), req.get(i).getActividad_eval().get(j).getReq_id());
                            }
                        }
                        result = aEjecucion.EliminarSolAnulados(cComp);
                    }
                }
            }
            if (result.equals("Correcto") && num == req.size()) {

                result = aEjecucion.EliminarReqSolicitudNP(cComp);
                if (result.equals("Correcto")) {
                    result = aEjecucion.EnviarSolicitudnpOb(cComp);
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void AnularSolicitudOP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = "Correcto";
        String observacion = request.getParameter("observacion");
        String estado = request.getParameter("estado");
        String solicitud = request.getParameter("solicitud");
        String usuario = request.getParameter("cedula");

        adEjecucion aEjecucion = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        List<cActividadRequerimiento> req = new ArrayList<cActividadRequerimiento>();

        req = aEjecucion.ListarSolicitudSolicitudOPC(Integer.parseInt(solicitud));

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setUsuario_nombre(usuario);
        cComp.setSolestado_observacion(observacion);
        Integer num = 0;
        if (req.size() > 0) {
            for (int i = 0; i < req.size(); i++) {
                if (result.equals("Correcto")) {
                    num++;
                    cComp.setReq_id(req.get(i).getReq_id());
                    cComp.setReq_costo_unitario(req.get(i).getReq_costo_total());
                    cComp.setReq_costo_sin_iva(req.get(i).getReq_costo_sin_iva());
                    cComp.setReq_costo_total(req.get(i).getReq_costo_total());
                    cComp.setSolicitud_id(Integer.parseInt(solicitud));
                    result = aEjecucion.InsertAnuladosOP(cComp);
                    if (result.equals("Correcto")) {
                        result = aEjecucion.EliminarReqSolicitudOPC(cComp);
                        if (result.equals("Correcto")) {
                            result = aEjecucion.EnviarSolicitudOPCOBS(cComp);
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

    private void ListaRequerimientosU(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String estado = request.getParameter("req");

        result = aEjecucion.ListarRequerimientosUnificadosUnion(Integer.parseInt(estado));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarUnificacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = "No se unifico, vuelva a intentarlo";
        adEjecucion aEjecucion = new adEjecucion();
        String nombre = request.getParameter("nombre");
        String costouni = request.getParameter("costo");
        String descripcion = request.getParameter("descripcion");
        String unidad = request.getParameter("unidad");
        String req = request.getParameter("req");
        String requerimiento = request.getParameter("requerimiento");
        String partes = request.getParameter("partes");
        String estado = request.getParameter("estado");
        Double cantidad = 0.0;
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cComp = new cActividadRequerimiento();
        Integer con = 0;

        if (req_id.length > 0) {
            if ((estado.equals("28") && !partes.equals("1")) || (estado.equals("29") && !partes.equals("1")) || (estado.equals("50") && !partes.equals("1"))) {
                for (String[] req_id1 : req_id) {
                    if (Double.parseDouble(costouni) <= Double.parseDouble(req_id1[2])) {
                        cantidad += Double.parseDouble(req_id1[1]);
                        con++;
                    }
                }

                if (con == req_id.length) {
                    if (cantidad > 0) {
                        cComp.setReq_nombre(nombre);
                        cComp.setReq_id(Integer.parseInt(requerimiento));
                        cComp.setReq_costo_unitario(Double.parseDouble(costouni));
                        cComp.setReq_costo_sin_iva(cantidad * (Double.parseDouble(costouni)));
                        cComp.setReq_cantidad(cantidad);
                        cComp.setReq_descripcion(descripcion);
                        cComp.setUnidad_id(Integer.parseInt(unidad));
                        result = aEjecucion.ModificarRequerimientosUnificados(cComp);

                        if (result.equals("Correcto")) {
                            result = aEjecucion.EliminarRequerimientosUnificadosRelacionado(cComp);
                            if (result.equals("Correcto")) {
                                for (String[] req_id1 : req_id) {
                                    cComp.setReq_id(Integer.parseInt(requerimiento));
                                    cComp.setActividad_id(Integer.parseInt(req_id1[0]));
                                    result = aEjecucion.IngresarRequerimientosUnificadosRelacionado(cComp);
                                }
                            }
                        }
                    } else {
                        result = "Debe seleccionar requerimientos.";
                    }
                } else {
                    result = "No se puede unificar porque existen requerimientos que no se ajusta al costo unitario.";
                }
            } else {
                Double costo = 0.0;
                for (String[] req_id1 : req_id) {
                    cantidad = 1.0;
                    costo += Double.parseDouble(req_id1[3]);
                    con++;
                }

                if (con == req_id.length) {
                    if (cantidad > 0) {
                        cComp.setReq_nombre(nombre);
                        cComp.setReq_id(Integer.parseInt(requerimiento));
                        cComp.setReq_costo_unitario(costo);
                        cComp.setReq_costo_sin_iva(cantidad * costo);
                        cComp.setReq_cantidad(cantidad);
                        cComp.setReq_descripcion(descripcion);
                        cComp.setUnidad_id(Integer.parseInt(unidad));
                        result = aEjecucion.ModificarRequerimientosUnificados(cComp);

                        if (result.equals("Correcto")) {
                            result = aEjecucion.EliminarRequerimientosUnificadosRelacionado(cComp);
                            if (result.equals("Correcto")) {
                                for (String[] req_id1 : req_id) {
                                    cComp.setReq_id(Integer.parseInt(requerimiento));
                                    cComp.setActividad_id(Integer.parseInt(req_id1[0]));
                                    result = aEjecucion.IngresarRequerimientosUnificadosRelacionado(cComp);
                                }
                            }
                        }
                    } else {
                        result = "Debe seleccionar requerimientos.";
                    }
                } else {
                    result = "Verifique los requerimientos seleccionados.";
                }
            }
        } else {
            result = "Debe seleccionar requerimientos.";
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarRequerimientoUnif(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("req");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(solicitud));

        result = aEje.EliminarRequerimientosUnif(cComp);
//        if (result.equals("Correcto")) {
//            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAg, 3);
//            objTransaccion.setStrDescripcion("El requerimiento unificado con el código: \"" + cComp.getSolicitud_id() + "\" se eliminó correctamente.");
//            ingresarTransaccion(objTransaccion);
//        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void IngresarSolicitudUnifi(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String centro = request.getParameter("centro");
        String ag = request.getParameter("ag");
        String req = request.getParameter("req");
        String autoridades = request.getParameter("autoridades");
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        String anio = request.getParameter("anio");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();

        cCom.setSolicitud_centro_costo(centro);
        cCom.setAg_id(Integer.parseInt(ag));
        cCom.setSolicitud_id(aEje.codigoSiguienteUnificadosSol());
        cCom.setSolicitud_autoridades(Integer.parseInt(autoridades));
        cCom.setSolicitud_cedula(cedula);
        cCom.setSolicitud_nombre(nombre);
        cCom.setSolicitud_cargo(cargo);
        cCom.setSolicitud_estado(Integer.parseInt(anio));
        Integer c = 0, c2 = 0;

        if (req_id.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                if (req_id[i][1].equals("2")) {
                    c2++;
                } else {
                    c++;
                }
            }
            if (c == req_id.length || c2 == req_id.length) {
                result = aEje.IngresarSolicitudUnificados(cCom);
                if (result.equals("Correcto")) {
                    for (int i = 0; i < req_id.length; i++) {
                        if (result.equals("Correcto")) {
                            cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                            result = aEje.InsertarSolicitudReqEjecu(cCom);
                        } else {
                            i = req_id.length + 1;
                            result = aEje.EliminarSolicitudUnif(cCom);
                            if (result.equals("Correcto")) {
                                result = "Error al ingresar la solicitud de requerimiento, vuelva a intentarlo.";
                            }
                        }
                    }
                }
            } else {
                result = "No puede mezclar requerimientos del OEI-2 con OEI1, OEI3 y OEI4.";
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

//        if (result.equals("Correcto")) {
//            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAg, 1);
//            objTransaccion.setStrDescripcion("La solicutud con el código: \"" + cCom.getSolicitud_id() + "\" se ingresó correctamente.");
//            ingresarTransaccion(objTransaccion);
//        }
        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaSolicitudesUnif(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String area = request.getParameter("area");
        String anio = request.getParameter("anio");

        result = aEjecucion.ListarSolicitudAreasUnificados(Integer.parseInt(area), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarSolicitudUnif(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String centro = request.getParameter("centro");
        String solicitud = request.getParameter("solicitud");
        String req = request.getParameter("req");
        String rector = request.getParameter("rector");
        String cedula = request.getParameter("cedula");
        String nombre = request.getParameter("nombre");
        String cargo = request.getParameter("cargo");
        String cedulau = request.getParameter("cedula");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();

        cCom.setSolicitud_centro_costo(centro);
        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(rector));
        cCom.setSolicitud_cedula(cedula);
        cCom.setSolicitud_nombre(nombre);
        cCom.setSolicitud_cargo(cargo);

        if (req_id.length > 0) {
            result = aEje.ModificarSolicitudUnif(cCom);
            if (result.equals("Correcto")) {
                result = aEje.EliminarReqSolicitudUnif(cCom);
                if (result.equals("Correcto")) {
                    for (int i = 0; i < req_id.length; i++) {
                        if (result.equals("Correcto")) {
                            cCom.setReq_id(Integer.parseInt(req_id[i][0]));
                            result = aEje.InsertarSolicitudReqEjecu(cCom);
                        }
                    }
                }
            }
        } else {
            result = "Debe seleccionar los requerimientos";
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud con código: \"" + cCom.getSolicitud_id() + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedulau);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarSolicitudUnif(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("solicitud");
        String cedula = request.getParameter("cedula");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        result = aEje.EliminarSolicitudUnif(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La solicitud con código: \"" + cComp.getSolicitud_id() + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarCertificacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("id");
        String cedula = request.getParameter("usuario");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        result = aEje.EliminarCertificacion(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + cComp.getSolicitud_id() + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarCertificacionSP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("id");
        String cedula = request.getParameter("usuario");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        result = aEje.EliminarCertificacionSP(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + cComp.getSolicitud_id() + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarCertificacionSPOP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("id");
        String cedula = request.getParameter("usuario");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        result = aEje.EliminarCertificacionSPOP(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + cComp.getSolicitud_id() + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void EliminarCertificacionVP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("id");
        String cedula = request.getParameter("usuario");
        adEjecucion aEje = new adEjecucion();
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));

        result = aEje.EliminarCertificacionVP(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + cComp.getSolicitud_id() + "\" se eliminó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarSolicitudUnif(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = "No se envio el justificativo.";
        adEjecucion aEjecucion = new adEjecucion();
        String usuario = request.getParameter("cedula");
        String estado = request.getParameter("estado");
        String solicitud = request.getParameter("idSolicitud");
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        cComp.setSolicitud_estado(Integer.parseInt(estado));
        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setUsuario_nombre(usuario);
        result = aEjecucion.EnviarSolicitudUnif(cComp);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void ModificarAutoridadesUnif(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = null;
        String solicitud = request.getParameter("idSolicitud");
        String autoridad = request.getParameter("autoridad");
        cActividadRequerimiento cCom = new cActividadRequerimiento();
        adEjecucion aEje = new adEjecucion();

        cCom.setSolicitud_id(Integer.parseInt(solicitud));
        cCom.setSolicitud_autoridades(Integer.parseInt(autoridad));

        result = aEje.ModificarAutoridadesUnif(cCom);

        String json = new Gson().toJson(result);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaRequerimientosUnificadosA(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String estado = request.getParameter("estado");
        String ag = request.getParameter("ag");
        String tag = request.getParameter("tpag");
        String anio = request.getParameter("anio");
        Integer area, area2;
        if (estado.equals("28")) {
            area = 68;
        } else if (estado.equals("30")) {
            area = 54;
        } else if (estado.equals("29")) {
            area = 60;
        } else if (estado.equals("50")) {
            area = 57;
        } else {
            area = 59;
        }

        if (tag.equals("4")) {
            area2 = 0;
        } else {
            area2 = Integer.parseInt(ag);
        }
        result = aEjecucion.ListarProyectosUnifAprobados(Integer.parseInt(ag), area2, area, Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCustodios(HttpServletRequest request, HttpServletResponse response, Integer intIdAg) throws IOException {
        String result = "No se ingresaron los custodios";
        adEjecucion aEjecucion = new adEjecucion();
        String req = request.getParameter("req");
        String req2 = request.getParameter("req2");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        String[][] req_id2 = new Gson().fromJson(req2, String[][].class);
        cActividadRequerimiento cComp = new cActividadRequerimiento();

        if (req_id.length > 0 && req_id2.length > 0) {
            for (int i = 0; i < req_id.length; i++) {
                cComp.setSolicitud_cedula(req_id[i][1]);
                cComp.setSolicitud_nombre(req_id[i][2]);
                cComp.setSolicitud_cargo(req_id[i][3]);
                cComp.setActividad_nombre(req_id[i][4]);
                if (aEjecucion.custodioExiste(req_id[i][1])) {
                    result = "Correcto";
                } else {
                    result = aEjecucion.IngresarCustodioUnificados(cComp);
                }
                if (result.equals("Correcto")) {
                    for (int j = 0; j < req_id2.length; j++) {
                        if (req_id2[j].length > 2) {
                            for (int k = 0; k < req_id2[j].length; k += 2) {
                                if (req_id2[j][k].equals(req_id[i][0])) {
                                    cComp.setProyecto_id(Integer.parseInt(req_id2[j][k]));
                                    cComp.setReq_id(Integer.parseInt(req_id2[j][k + 1]));
                                    cComp.setSolicitud_cedula(req_id[i][1]);
                                    cComp.setSolicitud_estado(Integer.parseInt(req_id[i][5]));
                                    result = aEjecucion.IngresarRequerimientosCustodios(cComp);
                                }
                            }
                        } else {
                            if (req_id2[j][0].equals(req_id[i][0])) {
                                cComp.setProyecto_id(Integer.parseInt(req_id2[j][0]));
                                cComp.setReq_id(Integer.parseInt(req_id2[j][1]));
                                cComp.setSolicitud_cedula(req_id[i][1]);
                                cComp.setSolicitud_estado(Integer.parseInt(req_id[i][5]));
                                result = aEjecucion.IngresarRequerimientosCustodios(cComp);
                            }
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

    private void ListaSolicitudesPlanificador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adEjecucion aEjecucion = new adEjecucion();
        String solicitud = request.getParameter("ag");
        String areapadre = request.getParameter("agp");
        String anio = request.getParameter("anio");

        result = aEjecucion.ListaSolicitudesPlanificador(Integer.parseInt(solicitud), Integer.parseInt(areapadre), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaRequerimientosSalvaguardar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aEjecucion = new adActividadRequerimiento();
        String anio = request.getParameter("anio");

        if (anio.equals("2020")) {
            result = aEjecucion.ListarRequerimientosExcel();
        } else if (anio.equals("2021")) {
            result = aEjecucion.ListarRequerimientosExcel21();
        } else {
            result = aEjecucion.ListarRequerimientosExcel22();
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCertificacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result;
        String req = request.getParameter("reqidcp");
        String tipo = request.getParameter("rectoring");
        String codigo = request.getParameter("codigocp");
        String valor = request.getParameter("valorcp");
        String codigoiva = request.getParameter("codigocpiva");
        String valoriva = request.getParameter("valorcpiva");
        String solicitud = request.getParameter("solicitudidcp");
        String observacion = request.getParameter("txtobservacion");
        String porcentaje = request.getParameter("porcanticipo");
        String recu = request.getParameter("recurrenteCert");
        String liq = request.getParameter("liquCert");
        String fecha = request.getParameter("fechain");
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        Integer codigoid = 0;
        Double total;
        String obs;
        if (codigo.isEmpty()) {
            result = "Debe ingresar código";
        } else if (valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            result = "Debe ingresar el monto";
        } else if (tipo.equals("2") && porcentaje.isEmpty()) {
            result = "Debe ingresar el porcentaje de anticipo";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            codigoid = adEj.codigoSiguienteCP();
            if (codigoiva.isEmpty()) {
                total = Double.parseDouble(valor) + Double.parseDouble(valoriva);
            } else {
                total = Double.parseDouble(valor);
            }
            if (observacion.isEmpty()) {
                obs = "Sin observación";
            } else {
                obs = observacion;
            }
            cAct.setAe_tiempo(Integer.parseInt(recu));
            cAct.setUnidad_id(Integer.parseInt(liq));
            cAct.setActividad_id(codigoid);
            cAct.setReq_id(Integer.parseInt(req));
            cAct.setReq_costo_total(redondearDecimales(total, 2));
            cAct.setReq_nombre(codigo);
            cAct.setSolicitud_id(Integer.parseInt(solicitud));
            cAct.setTc_id(Integer.parseInt(tipo));
            cAct.setAe_observacion(obs);
            cAct.setFecha_inicio(fecha);

            if (tipo.equals("1")) {
                result = adEj.IngresarCertificacionPRec(cAct);
            } else if (tipo.equals("2")) {
                double anticipo;
                anticipo = Double.parseDouble(valor) * (Double.parseDouble(porcentaje) / 100);
                cAct.setActividad_porcentaje(Double.parseDouble(porcentaje));
                cAct.setReq_costo_sin_iva(anticipo);
                result = adEj.IngresarCertificacionPComp(cAct);
            } else {
                result = adEj.IngresarCertificacionP(cAct);
            }

            if (result.equals("Correcto") && !codigoiva.isEmpty()) {
                codigoid = adEj.codigoSiguienteCP();
                cAct.setActividad_id(codigoid);
                cAct.setReq_id(Integer.parseInt(req));
                cAct.setReq_costo_total(Double.parseDouble(valoriva));
                cAct.setReq_nombre(codigoiva);
                cAct.setSolicitud_id(Integer.parseInt(solicitud));
                cAct.setTc_id(Integer.parseInt(tipo));
                cAct.setAe_observacion(obs);
                result = adEj.IngresarCertificacionP(cAct);
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + codigoid + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCertificacionSP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result;
        String req = request.getParameter("reqidcpsp");
        String tipo = request.getParameter("rectoringsp");
        String codigo = request.getParameter("codigocpsp");
        String valor = request.getParameter("valorcpsp");
        String codigoiva = request.getParameter("codigocpivasp");
        String valoriva = request.getParameter("valorcpivasp");
        String solicitud = request.getParameter("solicitudidcpsp");
        String observacion = request.getParameter("txtobservacionsp");
        String porcentaje = request.getParameter("porcanticiposp");
        String recu = request.getParameter("recurrenteCertsp");
        String fecha = request.getParameter("fechainsp");
        String liq=request.getParameter("liquCertsp");
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        Integer codigoid = 0;
        Double total;
        String obs;
        if (codigo.isEmpty()) {
            result = "Debe ingresar código";
        } else if (valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            result = "Debe ingresar el monto";
        } else if (tipo.equals("2") && porcentaje.isEmpty()) {
            result = "Debe ingresar el porcentaje de anticipo";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            codigoid = adEj.codigoSiguienteCPSP();
            if (codigoiva.isEmpty()) {
                total = Double.parseDouble(valor) + Double.parseDouble(valoriva);
            } else {
                total = Double.parseDouble(valor);
            }
            if (observacion.isEmpty()) {
                obs = "Sin observación";
            } else {
                obs = observacion;
            }
            cAct.setAe_tiempo(Integer.parseInt(recu));
            cAct.setActividad_id(codigoid);
            cAct.setReq_id(Integer.parseInt(req));
            cAct.setReq_costo_total(redondearDecimales(total, 2));
            cAct.setReq_nombre(codigo);
            cAct.setSolicitud_id(Integer.parseInt(solicitud));
            cAct.setTc_id(Integer.parseInt(tipo));
            cAct.setAe_observacion(obs);
            cAct.setFecha_inicio(fecha);
            cAct.setUnidad_id(Integer.parseInt(liq));

            if (tipo.equals("1")) {
                result = adEj.IngresarCertificacionPRecSP(cAct);
            } else if (tipo.equals("2")) {
                double anticipo;
                anticipo = Double.parseDouble(valor) * (Double.parseDouble(porcentaje) / 100);
                cAct.setActividad_porcentaje(Double.parseDouble(porcentaje));
                cAct.setReq_costo_sin_iva(anticipo);
                result = adEj.IngresarCertificacionPCompSP(cAct);
            } else {
                result = adEj.IngresarCertificacionPSP(cAct);
            }

            if (result.equals("Correcto") && !codigoiva.isEmpty()) {
                codigoid = adEj.codigoSiguienteCPSP();
                cAct.setActividad_id(codigoid);
                cAct.setReq_id(Integer.parseInt(req));
                cAct.setReq_costo_total(Double.parseDouble(valoriva));
                cAct.setReq_nombre(codigoiva);
                cAct.setSolicitud_id(Integer.parseInt(solicitud));
                cAct.setTc_id(Integer.parseInt(tipo));
                cAct.setAe_observacion(obs);
                result = adEj.IngresarCertificacionPSP(cAct);
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + codigoid + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCertificacionSPOP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result;
        String req = request.getParameter("reqidcpsp");
        String tipo = request.getParameter("rectoringsp");
        String codigo = request.getParameter("codigocpsp");
        String valor = request.getParameter("valorcpsp");
        String codigoiva = request.getParameter("codigocpivasp");
        String valoriva = request.getParameter("valorcpivasp");
        String solicitud = request.getParameter("solicitudidcpsp");
        String observacion = request.getParameter("txtobservacionsp");
        String porcentaje = request.getParameter("porcanticiposp");
        String recu = request.getParameter("recurrenteCertsp");
        String fecha = request.getParameter("fechainsp");
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        Integer codigoid = 0;
        Double total;
        String obs;
        if (codigo.isEmpty()) {
            result = "Debe ingresar código";
        } else if (valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            result = "Debe ingresar el monto";
        } else if (tipo.equals("2") && porcentaje.isEmpty()) {
            result = "Debe ingresar el porcentaje de anticipo";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            codigoid = adEj.codigoSiguienteCPSPOP();
            if (codigoiva.isEmpty()) {
                total = Double.parseDouble(valor) + Double.parseDouble(valoriva);
            } else {
                total = Double.parseDouble(valor);
            }
            if (observacion.isEmpty()) {
                obs = "Sin observación";
            } else {
                obs = observacion;
            }
            cAct.setAe_tiempo(Integer.parseInt(recu));
            cAct.setActividad_id(codigoid);
            cAct.setReq_id(Integer.parseInt(req));
            cAct.setReq_costo_total(redondearDecimales(total, 2));
            cAct.setReq_nombre(codigo);
            cAct.setSolicitud_id(Integer.parseInt(solicitud));
            cAct.setTc_id(Integer.parseInt(tipo));
            cAct.setAe_observacion(obs);
            cAct.setFecha_inicio(fecha);

            if (tipo.equals("1")) {
                result = adEj.IngresarCertificacionPRecSPOP(cAct);
            } else if (tipo.equals("2")) {
                double anticipo;
                anticipo = Double.parseDouble(valor) * (Double.parseDouble(porcentaje) / 100);
                cAct.setActividad_porcentaje(Double.parseDouble(porcentaje));
                cAct.setReq_costo_sin_iva(anticipo);
                result = adEj.IngresarCertificacionPCompSPOP(cAct);
            } else {
                result = adEj.IngresarCertificacionPSPOP(cAct);
            }

            if (result.equals("Correcto") && !codigoiva.isEmpty()) {
                codigoid = adEj.codigoSiguienteCPSPOP();
                cAct.setActividad_id(codigoid);
                cAct.setReq_id(Integer.parseInt(req));
                cAct.setReq_costo_total(Double.parseDouble(valoriva));
                cAct.setReq_nombre(codigoiva);
                cAct.setSolicitud_id(Integer.parseInt(solicitud));
                cAct.setTc_id(Integer.parseInt(tipo));
                cAct.setAe_observacion(obs);
                result = adEj.IngresarCertificacionPSPOP(cAct);
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + codigoid + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCertificacionOP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result = "Error";
        String req = request.getParameter("reqidcp");
        String tipo = request.getParameter("rectoring");
        String codigo = request.getParameter("codigocp");
        String valor = request.getParameter("valorcp");
        String valorh = request.getParameter("valorcph");
        String codigoiva = request.getParameter("codigocpiva");
        String valoriva = request.getParameter("valorcpiva");
        String valorivah = request.getParameter("valorcpivah");
        String codigoant = request.getParameter("codigocpAn");
        String valorant = request.getParameter("valorcpAn");
        String valoranth = request.getParameter("valorcpAnh");
        String solicitud = request.getParameter("solicitudidcp");
        String observacion = request.getParameter("txtobservacion");
        String recu = request.getParameter("recurrenteCert");
        String fecha = request.getParameter("fechain");
        String liqu = request.getParameter("liquCert");
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        Integer codigoid = 0;
        String obs;
        if (codigo.isEmpty() && (Double.parseDouble(valorh) > 0 || valorh.isEmpty())) {
            result = "Debe llenar todos los campos.";
        } else if (codigoiva.isEmpty() && (Double.parseDouble(valorivah) > 0 || valorivah.isEmpty())) {
            result = "Debe llenar todos los campos.";
        } else if (codigoant.isEmpty() && (Double.parseDouble(valoranth) > 0 || valoranth.isEmpty()) && !tipo.equals("4")) {
            result = "Debe llenar todos los campos.";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación.";
        } else {
            if (observacion.isEmpty()) {
                obs = "Sin observación";
            } else {
                obs = observacion;
            }
            if (!codigo.isEmpty()) {
                codigoid = adEj.codigoSiguienteCPV();
                cAct.setActividad_id(codigoid);
                cAct.setReq_id(Integer.parseInt(req));
                cAct.setReq_costo_total(Double.parseDouble(valor));
                cAct.setReq_nombre(codigo);
                cAct.setSolicitud_id(Integer.parseInt(solicitud));
                cAct.setTc_id(Integer.parseInt(tipo));
                cAct.setAe_observacion(obs);
                cAct.setAe_tiempo(Integer.parseInt(recu));
                cAct.setFecha_inicio(fecha);
                cAct.setUnidad_id(Integer.parseInt(liqu));
                if (tipo.equals("1")) {
                    result = adEj.IngresarCertificacionPVRec(cAct, 1);
                } else {
                    result = adEj.IngresarCertificacionPV(cAct, 1);
                }
            }

            if (!codigoiva.isEmpty()) {
                codigoid = adEj.codigoSiguienteCPV();
                cAct.setActividad_id(codigoid);
                cAct.setReq_id(Integer.parseInt(req));
                cAct.setReq_costo_total(Double.parseDouble(valoriva));
                cAct.setReq_nombre(codigoiva);
                cAct.setSolicitud_id(Integer.parseInt(solicitud));
                cAct.setTc_id(Integer.parseInt(tipo));
                cAct.setAe_observacion(obs);
                cAct.setAe_tiempo(Integer.parseInt(recu));
                cAct.setFecha_inicio(fecha);
                cAct.setUnidad_id(Integer.parseInt(liqu));
                if (tipo.equals("1")) {
                    result = adEj.IngresarCertificacionPVRec(cAct, 2);
                } else {
                    result = adEj.IngresarCertificacionPV(cAct, 2);
                }
            }
            if (!codigoant.isEmpty()) {
                codigoid = adEj.codigoSiguienteCPV();
                cAct.setActividad_id(codigoid);
                cAct.setReq_id(Integer.parseInt(req));
                cAct.setReq_costo_total(Double.parseDouble(valorant));
                cAct.setReq_nombre(codigoant);
                cAct.setSolicitud_id(Integer.parseInt(solicitud));
                cAct.setTc_id(Integer.parseInt(tipo));
                cAct.setAe_observacion(obs);
                cAct.setAe_tiempo(Integer.parseInt(recu));
                cAct.setFecha_inicio(fecha);
                cAct.setUnidad_id(Integer.parseInt(liqu));
                if (tipo.equals("1")) {
                    result = adEj.IngresarCertificacionPVRec(cAct, 3);
                } else {
                    result = adEj.IngresarCertificacionPV(cAct, 3);
                }
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + codigoid + "\" se ingresó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(1);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCertificacionOPC(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result = "Error";
        String tipo = request.getParameter("tipo");
        String codigo = request.getParameter("codigomonto");
        String codigoiva = request.getParameter("codigoiva");
        String codigoant = request.getParameter("codigoanticipo");
        String solicitud = request.getParameter("solicitud");
        String observacion = request.getParameter("observacion");
        String req = request.getParameter("req");
        String fecha = request.getParameter("fechain");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        Integer codigoid = 0;
        String obs;
        if (req_id.length > 1) {
            for (int i = 0; i < req_id.length; i++) {
                if (codigo.isEmpty() && req_id[i][i].isEmpty()) {
                    result = "Debe llenar todos los campos.";
                } else if (codigoiva.isEmpty() && req_id[i][2].isEmpty()) {
                    result = "Debe llenar todos los campos.";
                } else if (codigoant.isEmpty() && req_id[i][3].isEmpty()) {
                    result = "Debe llenar todos los campos.";
                } else if (fecha.isEmpty()) {
                    result = "Debe ingresar la fecha de aprobación.";
                } else {
                    if (observacion.isEmpty()) {
                        obs = "Sin observación";
                    } else {
                        obs = observacion;
                    }
                    if (!codigo.isEmpty()) {
                        codigoid = adEj.codigoSiguienteCPV();
                        cAct.setActividad_id(codigoid);
                        cAct.setReq_id(Integer.parseInt(req_id[i][0]));
                        cAct.setReq_costo_total(Double.parseDouble(req_id[i][1]));
                        cAct.setReq_nombre(codigo);
                        cAct.setSolicitud_id(Integer.parseInt(solicitud));
                        cAct.setTc_id(Integer.parseInt(tipo));
                        cAct.setAe_observacion(obs);
                        cAct.setFecha_inicio(fecha);
                        result = adEj.IngresarCertificacionPV(cAct, 1);
                    }

                    if (!codigoiva.isEmpty()) {
                        codigoid = adEj.codigoSiguienteCPV();
                        cAct.setActividad_id(codigoid);
                        cAct.setReq_id(Integer.parseInt(req_id[i][0]));
                        cAct.setReq_costo_total(Double.parseDouble(req_id[i][2]));
                        cAct.setReq_nombre(codigoiva);
                        cAct.setSolicitud_id(Integer.parseInt(solicitud));
                        cAct.setTc_id(Integer.parseInt(tipo));
                        cAct.setAe_observacion(obs);
                        cAct.setFecha_inicio(fecha);
                        result = adEj.IngresarCertificacionPV(cAct, 2);
                    }
                    if (!codigoant.isEmpty()) {
                        codigoid = adEj.codigoSiguienteCPV();
                        cAct.setActividad_id(codigoid);
                        cAct.setReq_id(Integer.parseInt(req_id[i][0]));
                        cAct.setReq_costo_total(Double.parseDouble(req_id[i][3]));
                        cAct.setReq_nombre(codigoant);
                        cAct.setSolicitud_id(Integer.parseInt(solicitud));
                        cAct.setTc_id(Integer.parseInt(tipo));
                        cAct.setAe_observacion(obs);
                        cAct.setFecha_inicio(fecha);
                        result = adEj.IngresarCertificacionPV(cAct, 3);
                    }
                }
                if (result.equals("Correcto")) {
                    cTransaccion objTransaccion = new cTransaccion();
                    objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + codigoid + "\" se ingresó correctamente");
                    objTransaccion.setTransaccion_cedula(cedula);
                    objTransaccion.setTransaccion_ag(intIdAg);
                    objTransaccion.setTransaccion_tipo(1);
                    ingresarTransaccion(objTransaccion);
                }
            }
        } else {
            result = "Debe seleccionar a menos dos requerimientos";
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCertificacionPG(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result = "Error";
        String req = request.getParameter("req");
        String tipo = request.getParameter("tipo");
        String codigo = request.getParameter("codigo");
        String codigoiva = request.getParameter("codigoi");
        String solicitud = request.getParameter("sol");
        String observacion = request.getParameter("observacion");
        String porcentaje = request.getParameter("porcentaje");
        String fecha = request.getParameter("fechain");
        String[][] req_id = new Gson().fromJson(req, String[][].class);

        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        Integer codigoid = 0;
        Double total;
        String obs;
        if (codigo.isEmpty()) {
            result = "Debe ingresar código.";
        } else if (req_id.length <= 0) {
            result = "Debe seleccionar los requerimientos.";
        } else if (tipo.equals("2") && porcentaje.isEmpty()) {
            result = "Debe ingresar el porcentaje de anticipo";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            for (int i = 0; i < req_id.length; i++) {
                codigoid = adEj.codigoSiguienteCP();
                if (codigoiva.isEmpty()) {
                    total = redondearDecimales(Double.parseDouble(req_id[i][2]), 2);
                } else {
                    total = redondearDecimales(Double.parseDouble(req_id[i][1]), 2);
                }
                if (observacion.isEmpty()) {
                    obs = "Sin observación";
                } else {
                    obs = observacion;
                }

                cAct.setActividad_id(codigoid);
                cAct.setReq_id(Integer.parseInt(req_id[i][0]));
                cAct.setReq_costo_total(total);
                cAct.setReq_nombre(codigo);
                cAct.setSolicitud_id(Integer.parseInt(solicitud));
                cAct.setTc_id(Integer.parseInt(tipo));
                cAct.setAe_observacion(obs);
                cAct.setFecha_inicio(fecha);

                if (tipo.equals("2")) {
                    double anticipo;
                    anticipo = Double.parseDouble(req_id[i][1]) * Double.parseDouble(porcentaje);
                    cAct.setActividad_porcentaje(Double.parseDouble(porcentaje));
                    cAct.setReq_costo_sin_iva(anticipo);
                    result = adEj.IngresarCertificacionPComp(cAct);
                } else {
                    result = adEj.IngresarCertificacionP(cAct);
                }
                if (result.equals("Correcto") && !codigoiva.isEmpty()) {
                    Double iva = Double.parseDouble(req_id[i][2]) - Double.parseDouble(req_id[i][1]);
                    codigoid = adEj.codigoSiguienteCP();
                    cAct.setActividad_id(codigoid);
                    cAct.setReq_id(Integer.parseInt(req_id[i][0]));
                    cAct.setReq_costo_total(redondearDecimales(iva, 2));
                    cAct.setReq_nombre(codigoiva);
                    cAct.setSolicitud_id(Integer.parseInt(solicitud));
                    cAct.setTc_id(Integer.parseInt(tipo));
                    result = adEj.IngresarCertificacionP(cAct);
                }
                if (result.equals("Correcto")) {
                    cTransaccion objTransaccion = new cTransaccion();
                    objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + codigoid + "\" se ingresó correctamente");
                    objTransaccion.setTransaccion_cedula(cedula);
                    objTransaccion.setTransaccion_ag(intIdAg);
                    objTransaccion.setTransaccion_tipo(1);
                    ingresarTransaccion(objTransaccion);
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarCertificacionPGU(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result = "Error";
        String req = request.getParameter("req");
        String tipo = request.getParameter("tipo");
        String codigo = request.getParameter("codigo");
        String codigoiva = request.getParameter("codigoi");
        String solicitud = request.getParameter("sol");
        String observacion = request.getParameter("observacion");
        String porcentaje = request.getParameter("porcentaje");
        String fecha = request.getParameter("fechain");
        String recurrente = request.getParameter("recurrente");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        List<cActividadRequerimiento> listareq = new ArrayList<cActividadRequerimiento>();

        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        Integer codigoid = 0;
        Double total;
        String obs;
        if (codigo.isEmpty()) {
            result = "Debe ingresar código.";
        } else if (req_id.length <= 0) {
            result = "Debe seleccionar los requerimientos.";
        } else if (tipo.equals("2") && porcentaje.isEmpty()) {
            result = "Debe ingresar el porcentaje de anticipo";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            for (int i = 0; i < req_id.length; i++) {
                if (observacion.isEmpty()) {
                    obs = "Sin observación";
                } else {
                    obs = observacion;
                }

                listareq = adEj.ListarRequerimientosUnificadosUnion(Integer.parseInt(req_id[i][0]));

                for (int j = 0; j < listareq.size(); j++) {
                    if (codigoiva.isEmpty()) {
                        total = listareq.get(j).getReq_costo_total();
                    } else {
                        total = listareq.get(j).getReq_costo_sin_iva();
                    }
                    codigoid = adEj.codigoSiguienteCP();
                    cAct.setActividad_id(codigoid);
                    cAct.setAe_tiempo(Integer.parseInt(recurrente));
                    cAct.setReq_id(listareq.get(j).getReq_id());
                    cAct.setReq_costo_total(total);
                    cAct.setReq_nombre(codigo);
                    cAct.setSolicitud_id(Integer.parseInt(solicitud));
                    cAct.setTc_id(Integer.parseInt(tipo));
                    cAct.setAe_observacion(obs);
                    cAct.setFecha_inicio(fecha);

                    if (tipo.equals("1")) {
                        result = adEj.IngresarCertificacionPRec(cAct);
                    } else if (tipo.equals("2")) {
                        double anticipo;
                        anticipo = (listareq.get(j).getReq_costo_sin_iva()) * Double.parseDouble(porcentaje);
                        cAct.setActividad_porcentaje(Double.parseDouble(porcentaje));
                        cAct.setReq_costo_sin_iva(anticipo);
                        result = adEj.IngresarCertificacionPComp(cAct);
                    } else {
                        result = adEj.IngresarCertificacionP(cAct);
                    }
                    if (result.equals("Correcto") && !codigoiva.isEmpty()) {
                        Double iva = listareq.get(j).getReq_costo_total() - listareq.get(j).getReq_costo_sin_iva();
                        codigoid = adEj.codigoSiguienteCP();
                        cAct.setActividad_id(codigoid);
                        cAct.setReq_id(listareq.get(j).getReq_id());
                        cAct.setReq_costo_total(redondearDecimales(iva, 2));
                        cAct.setReq_nombre(codigoiva);
                        cAct.setSolicitud_id(Integer.parseInt(solicitud));
                        cAct.setTc_id(Integer.parseInt(tipo));
                        result = adEj.IngresarCertificacionP(cAct);
                    }
                    if (result.equals("Correcto")) {
                        cTransaccion objTransaccion = new cTransaccion();
                        objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + codigoid + "\" se ingresó correctamente");
                        objTransaccion.setTransaccion_cedula(cedula);
                        objTransaccion.setTransaccion_ag(intIdAg);
                        objTransaccion.setTransaccion_tipo(1);
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

    private void ModificarCertificacion(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result;
        String certpid = request.getParameter("certpid");
        String tipo = request.getParameter("rectoring");
        String codigo = request.getParameter("codigocp");
        String valor = request.getParameter("valorcp");
        String observacion = request.getParameter("observacion");
        String rec = request.getParameter("recurrenteCert");
        String fecha = request.getParameter("fechain");
        String liq=request.getParameter("liquCert");
        String obs;
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        if (codigo.isEmpty()) {
            result = "Debe ingresar código";
        } else if (valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            result = "Debe ingresar el monto";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            try {
                if (observacion == null || observacion.isEmpty()) {
                    obs = "Sin observación";
                } else {
                    obs = observacion;
                }
            } catch (Exception e) {
                obs = "Sin observación";
            }
            cAct.setActividad_id(Integer.parseInt(certpid));
            cAct.setAe_tiempo(Integer.parseInt(rec));
            cAct.setUnidad_id(Integer.parseInt(liq));
            cAct.setReq_costo_total(Double.parseDouble(valor));
            cAct.setReq_nombre(codigo);
            cAct.setTc_id(Integer.parseInt(tipo));
            cAct.setAe_observacion(obs);
            cAct.setFecha_inicio(fecha);
            if (tipo.equals("1")) {
                result = adEj.ModificarCertificacionPRec(cAct);
            } else {
                result = adEj.ModificarCertificacionP(cAct);
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + certpid + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarCertificacionSP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result;
        String certpid = request.getParameter("certpidsp");
        String tipo = request.getParameter("rectoringsp");
        String codigo = request.getParameter("codigocpsp");
        String valor = request.getParameter("valorcpsp");
        String observacion = request.getParameter("observacionsp");
        String rec = request.getParameter("recurrenteCertsp");
        String fecha = request.getParameter("fechainsp");
        String liq=request.getParameter("liquCertsp");
        String obs;
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        if (codigo.isEmpty()) {
            result = "Debe ingresar código";
        } else if (valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            result = "Debe ingresar el monto";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            try {
                if (observacion == null || observacion.isEmpty()) {
                    obs = "Sin observación";
                } else {
                    obs = observacion;
                }
            } catch (Exception e) {
                obs = "Sin observación";
            }
            cAct.setActividad_id(Integer.parseInt(certpid));
            cAct.setAe_tiempo(Integer.parseInt(rec));
            cAct.setReq_costo_total(Double.parseDouble(valor));
            cAct.setReq_nombre(codigo);
            cAct.setTc_id(Integer.parseInt(tipo));
            cAct.setAe_observacion(obs);
            cAct.setFecha_inicio(fecha);
            cAct.setUnidad_id(Integer.parseInt(liq));
            if (tipo.equals("1")) {
                result = adEj.ModificarCertificacionPRecSP(cAct);
            } else {
                result = adEj.ModificarCertificacionPSP(cAct);
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + certpid + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarCertificacionSPOP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result;
        String certpid = request.getParameter("certpidsp");
        String tipo = request.getParameter("rectoringsp");
        String codigo = request.getParameter("codigocpsp");
        String valor = request.getParameter("valorcpsp");
        String observacion = request.getParameter("observacionsp");
        String rec = request.getParameter("recurrenteCertsp");
        String fecha = request.getParameter("fechainsp");
        String obs;
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        if (codigo.isEmpty()) {
            result = "Debe ingresar código";
        } else if (valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            result = "Debe ingresar el monto";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación";
        } else {
            try {
                if (observacion == null || observacion.isEmpty()) {
                    obs = "Sin observación";
                } else {
                    obs = observacion;
                }
            } catch (Exception e) {
                obs = "Sin observación";
            }
            cAct.setActividad_id(Integer.parseInt(certpid));
            cAct.setAe_tiempo(Integer.parseInt(rec));
            cAct.setReq_costo_total(Double.parseDouble(valor));
            cAct.setReq_nombre(codigo);
            cAct.setTc_id(Integer.parseInt(tipo));
            cAct.setAe_observacion(obs);
            cAct.setFecha_inicio(fecha);
            if (tipo.equals("1")) {
                result = adEj.ModificarCertificacionPRecSPOP(cAct);
            } else {
                result = adEj.ModificarCertificacionPSPOP(cAct);
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + certpid + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarCertificacionOP(HttpServletRequest request, HttpServletResponse response, Integer intIdAg, String cedula) throws IOException {
        String result;
        String certpid = request.getParameter("certpidm");
        String tipo = request.getParameter("rectoringm");
        String codigo = request.getParameter("codigocpm");
        String valor = request.getParameter("valorcpm");
        String observacion = request.getParameter("txtobservacionm");
        String rec = request.getParameter("recurrenteCertm");
        String fecha = request.getParameter("fechainm");
        String liq = request.getParameter("liquCertm");
        String obs;
        adEjecucion adEj = new adEjecucion();
        cActividadRequerimiento cAct = new cActividadRequerimiento();
        if (codigo.isEmpty()) {
            result = "Debe ingresar código";
        } else if (valor.isEmpty() || Double.parseDouble(valor) <= 0) {
            result = "Debe ingresar el monto";
        } else if (fecha.isEmpty()) {
            result = "Debe ingresar la fecha de aprobación.";
        } else {
            try {
                if (observacion == null || observacion.isEmpty()) {
                    obs = "Sin observación";
                } else {
                    obs = observacion;
                }
            } catch (Exception e) {
                obs = "Sin observación";
            }
            cAct.setActividad_id(Integer.parseInt(certpid));
            cAct.setAe_tiempo(Integer.parseInt(rec));
            cAct.setReq_costo_total(Double.parseDouble(valor));
            cAct.setReq_nombre(codigo);
            cAct.setTc_id(Integer.parseInt(tipo));
            cAct.setAe_observacion(obs);
            cAct.setFecha_inicio(fecha);
            cAct.setUnidad_id(Integer.parseInt(liq));
            if (tipo.equals("1")) {
                result = adEj.ModificarCertificacionPRecVP(cAct);
            } else {
                result = adEj.ModificarCertificacionPVP(cAct);
            }
        }

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La certificación con código: \"" + certpid + "\" se modificó correctamente");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAg);
            objTransaccion.setTransaccion_tipo(2);
            ingresarTransaccion(objTransaccion);
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado = (resultado - parteEntera) * Math.pow(10, numeroDecimales);
        resultado = Math.round(resultado);
        resultado = (resultado / Math.pow(10, numeroDecimales)) + parteEntera;
        return resultado;
    }
}
