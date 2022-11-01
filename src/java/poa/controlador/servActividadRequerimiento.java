/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.controlador;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import poa.acceso.adActividadRequerimiento;
import poa.acceso.adEjecucion;
import poa.acceso.adProyecto;
import static poa.acceso.adTransaccion.ingresarTransaccion;
import poa.clases.cActividadRequerimiento;
import poa.clases.cProyecto;
import poa.clases.cTransaccion;

/**
 *
 * @author Erika Arévalo
 */
@MultipartConfig
public class servActividadRequerimiento extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {

            String strAccion = request.getParameter("accion");
            /*Inicio Para transacciones*/
            HttpSession sesionOk = request.getSession(false);
            Integer intIdAreaGestion = (Integer) sesionOk.getAttribute("idAreaGestion");
            String strCedula = (String) sesionOk.getAttribute("Cedula");
            /*Fin Para transacciones*/

            switch (strAccion) {
                case "IngresarActividad":
                    IngresarActividad(request, response, intIdAreaGestion);
                    break;
                case "IngresarActividadRep":
                    IngresarActividadRep(request, response, intIdAreaGestion);
                    break;
                case "ListarActividad":
                    ListarActividad(request, response);
                    break;
                case "ListarActividadRep":
                    ListarActividadRep(request, response);
                    break;
                case "ListarUnidad":
                    ListarUnidad(request, response);
                    break;
                case "ListarFinanciamiento":
                    ListarFinanciamiento(request, response);
                    break;
                case "ListarTipoC":
                    ListarTipoC(request, response);
                    break;
                case "IngresarRequerimiento":
                    IngresarRequerimiento(request, response, intIdAreaGestion);
                    break;
                case "ModificarActividad":
                    ModificarActividad(request, response, intIdAreaGestion);
                    break;
                case "ModificarActividadCuatr":
                    ModificarActividadCuatr(request, response, intIdAreaGestion);
                    break;
                case "ModificarActividadResp":
                    ModificarActividadResp(request, response, intIdAreaGestion);
                    break;
                case "EliminarActividadCuatr":
                    EliminarActividadCuatr(request, response, intIdAreaGestion);
                    break;
                case "IngresarActividadReprog":
                    IngresarActividadReprog(request, response, intIdAreaGestion);
                    break;
                case "ModificarRequerimiento":
                    ModificarRequerimiento(request, response, intIdAreaGestion);
                    break;
                case "ModificarReforma":
                    ModificarReforma(request, response, intIdAreaGestion);
                    break;
                case "ModificarReformaSaldo":
                    ModificarReformaSaldo(request, response, intIdAreaGestion);
                    break;
                case "ModificarRequerimientoRep":
                    ModificarRequerimientoRep(request, response, intIdAreaGestion);
                    break;
                case "ModificarAnioReq":
                    ModificarAnioReq(request, response);
                    break;
                case "EliminarActividad":
                    EliminarActividad(request, response, intIdAreaGestion);
                    break;
                case "EliminarActividadRep":
                    EliminarActividadRep(request, response, intIdAreaGestion);
                    break;
                case "EliminarRequerimiento":
                    EliminarRequerimiento(request, response, intIdAreaGestion);
                    break;
                case "EliminarReforma":
                    EliminarReforma(request, response, intIdAreaGestion);
                    break;
                case "EliminarSaldo":
                    EliminarSaldo(request, response, intIdAreaGestion);
                    break;
                case "ListarRequerimiento":
                    ListarRequerimiento(request, response);
                    break;
                case "ListarCuatrimestreActividad":
                    ListarCuatrimestreActividad(request, response);
                    break;
                case "ListarRequerimientosCompras":
                    ListarRequerimientosCompras(request, response);
                    break;
                case "ModificarRequerimientosCompras":
                    ModificarRequerimientosCompras(request, response, strCedula, intIdAreaGestion);
                    break;
                case "IngresarVerificacion":
                    IngresarVerificacion(request, response);
                    break;
                case "ListarFechasRequerimientos":
                    ListarFechasRequerimientos(request, response);
                    break;
                case "EliminarVerificacion":
                    EliminarVerificacion(request, response);
                    break;
                case "PriorizarActividad":
                    PriorizarActividad(request, response, intIdAreaGestion);
                    break;
                case "PriorizarRequerimiento":
                    PriorizarRequerimiento(request, response, intIdAreaGestion);
                    break;
                case "ListaServicios":
                    ListaServicios(request, response);
                    break;
                case "ListaServiciosO":
                    ListaServiciosO(request, response);
                    break;
                case "IngresarServiciosP":
                    IngresarServiciosP(request, response, intIdAreaGestion);
                    break;
                case "IngresarServiciosPO":
                    IngresarServiciosPO(request, response, intIdAreaGestion);
                    break;
                case "ModificarServiciosP":
                    ModificarServiciosP(request, response, intIdAreaGestion);
                    break;
                case "LiquidarSolicitud":
                    LiquidarSolicitud(request, response);
                    break;
                case "ModificarServiciosPO":
                    ModificarServiciosPO(request, response, intIdAreaGestion);
                    break;
                case "EliminarServiciosP":
                    EliminarServiciosP(request, response, intIdAreaGestion);
                    break;
                case "EliminarServiciosPO":
                    EliminarServiciosPO(request, response, intIdAreaGestion);
                    break;
                case "EnviarServiciosP":
                    EnviarServiciosP(request, response);
                    break;
                case "EnviarServiciosPO":
                    EnviarServiciosPO(request, response);
                    break;
                case "ListarRequerimientoRep":
                    ListarRequerimientoRep(request, response);
                    break;
                case "ListarReqServicios":
                    ListarReqServicios(request, response);
                    break;
                case "ListarActividadesProy":
                    ListarActividadesProy(request, response);
                    break;
                case "ListarReqReformaID":
                    ListarReqReformaID(request, response);
                    break;
                case "ListarReqReformaIDeudas":
                    ListarReqReformaIDeudas(request, response);
                    break;
                case "ListarSaldos":
                    ListarSaldos(request, response);
                    break;
            }
        }
    }

    private void IngresarActividad(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String componente = request.getParameter("componente");
        String nombre = request.getParameter("nombre");
        String responsable = request.getParameter("responsable");
        String tipo = request.getParameter("tipo");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c1 = request.getParameter("cuatrimestre1");
        String c2 = request.getParameter("cuatrimestre2");
        String c3 = request.getParameter("cuatrimestre3");
        String cedula = request.getParameter("cedula");
        String[] cuatri1 = new Gson().fromJson(c1, String[].class);
        String[] cuatri2 = new Gson().fromJson(c2, String[].class);
        String[] cuatri3 = new Gson().fromJson(c3, String[].class);

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Integer idAct = aComp.codigoSiguienteActividad();
        Double suma = 0.0;
        if (!porcentaje1.isEmpty()) {
            suma += Double.parseDouble(porcentaje1);
        }
        if (!porcentaje2.isEmpty()) {
            suma += Double.parseDouble(porcentaje2);
        }
        if (!porcentaje3.isEmpty()) {
            suma += Double.parseDouble(porcentaje3);
        }
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre de la actividad.";
        } else if (responsable.isEmpty()) {
            result = "Debe ingresar el responsable de la actividad";
        } else {
            if (porcentaje1.isEmpty() && porcentaje3.isEmpty() && porcentaje2.isEmpty()) {
                result = "Debe ingresar el porcentaje por cuatrimestre";
            } else if (cuatri1.length < 1 && cuatri2.length < 1 && cuatri3.length < 1) {
                result = "Debe seleccionar los meses";
            } else if ((porcentaje1.isEmpty()) && cuatri1.length > 0) {
                result = "Debe ingresar el porcentaje para el primer cuatrimestre";
            } else if ((porcentaje2.isEmpty()) && cuatri2.length > 0) {
                result = "Debe ingresar el porcentaje para el segundo cuatrimestre";
            } else if ((porcentaje3.isEmpty()) && cuatri3.length > 0) {
                result = "Debe ingresar el porcentaje para el tercer cuatrimestre";
            } else if (suma != 100 || suma != 100.00) {
                result = "La suma de los porcentajes debe ser el 100%";
            } else {
                cComp.setActividad_id(idAct);
                cComp.setActividad_nombre(nombre);
                cComp.setActividad_responsable(responsable);
                cComp.setActividad_tipo(Integer.parseInt(tipo));
                cComp.setActividad_componente(Integer.parseInt(componente));
                result = aComp.IngresarActividad(cComp);
                if (result.equals("Correcto")) {
                    if (cuatri1.length > 0) {
                        for (int i = 0; i < cuatri1.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri1[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje1) / cuatri1.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri2.length > 0) {
                        for (int i = 0; i < cuatri2.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri2[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje2) / cuatri2.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri3.length > 0) {
                        for (int i = 0; i < cuatri3.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri3[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje3) / cuatri3.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (result.equals("Correcto")) {
                        cTransaccion objTransaccion = new cTransaccion();
                        objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + idAct + "\" se ingresó correctamente en el componente: \"" + cComp.getActividad_componente() + "\"");
                        objTransaccion.setTransaccion_cedula(cedula);
                        objTransaccion.setTransaccion_ag(intIdAreaGestion);
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

    private void IngresarActividadRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String componente = request.getParameter("componente");
        String nombre = request.getParameter("nombre");
        String responsable = request.getParameter("responsable");
        String tipo = request.getParameter("tipo");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c1 = request.getParameter("cuatrimestre1");
        String c2 = request.getParameter("cuatrimestre2");
        String c3 = request.getParameter("cuatrimestre3");
        String cedula = request.getParameter("cedula");
        String[] cuatri1 = new Gson().fromJson(c1, String[].class);
        String[] cuatri2 = new Gson().fromJson(c2, String[].class);
        String[] cuatri3 = new Gson().fromJson(c3, String[].class);

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Integer idAct = aComp.codigoSiguienteActividad();
        Double suma = 0.0;
        if (!porcentaje1.isEmpty()) {
            suma += Double.parseDouble(porcentaje1);
        }
        if (!porcentaje2.isEmpty()) {
            suma += Double.parseDouble(porcentaje2);
        }
        if (!porcentaje3.isEmpty()) {
            suma += Double.parseDouble(porcentaje3);
        }
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre de la actividad.";
        } else if (responsable.isEmpty()) {
            result = "Debe ingresar el responsable de la actividad";
        } else {
            if (porcentaje1.isEmpty() && porcentaje3.isEmpty() && porcentaje2.isEmpty()) {
                result = "Debe ingresar el porcentaje por cuatrimestre";
            } else if (cuatri1.length < 1 && cuatri2.length < 1 && cuatri3.length < 1) {
                result = "Debe seleccionar los meses";
            } else if ((porcentaje1.isEmpty() || Double.parseDouble(porcentaje1) < 0) && cuatri1.length > 0) {
                result = "Debe ingresar el porcentaje para el primer cuatrimestre";
            } else if ((porcentaje2.isEmpty() || Double.parseDouble(porcentaje2) < 0) && cuatri2.length > 0) {
                result = "Debe ingresar el porcentaje para el segundo cuatrimestre";
            } else if ((porcentaje3.isEmpty() || Double.parseDouble(porcentaje3) < 0) && cuatri3.length > 0) {
                result = "Debe ingresar el porcentaje para el tercer cuatrimestre";
            } else if (suma != 100 || suma != 100.00) {
                result = "La suma de los porcentajes debe ser el 100%";
            } else if (!porcentaje1.isEmpty() && cuatri1.length > 0) {
                if (Double.parseDouble(porcentaje1) >= 0) {
                    result = "No puede ingresar porcentajes en el primer cuatrimestre, porque el proyecto ya fue evaluado";
                } else {
                    result = "No puede seleccionar meses en el primer cuatrimestre, porque el proyecto ya fue evaluado";
                }
            } else {
                cComp.setActividad_id(idAct);
                cComp.setActividad_nombre(nombre);
                cComp.setActividad_responsable(responsable);
                cComp.setActividad_tipo(Integer.parseInt(tipo));
                cComp.setActividad_componente(Integer.parseInt(componente));
                result = aComp.IngresarActividadRep(cComp);
                if (result.equals("Correcto")) {
                    if (cuatri1.length > 0) {
                        for (int i = 0; i < cuatri1.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri1[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje1) / cuatri1.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri2.length > 0) {
                        for (int i = 0; i < cuatri2.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri2[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje2) / cuatri2.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri3.length > 0) {
                        for (int i = 0; i < cuatri3.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri3[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje3) / cuatri3.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (result.equals("Correcto")) {
                        cTransaccion objTransaccion = new cTransaccion();
                        objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + idAct + "\" se ingresó correctamente en el componente: \"" + cComp.getActividad_componente() + "\"");
                        objTransaccion.setTransaccion_cedula(cedula);
                        objTransaccion.setTransaccion_ag(intIdAreaGestion);
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

    private void IngresarServiciosP(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String horas = request.getParameter("horas");
        String shoras = request.getParameter("shoras");
        String fechai = request.getParameter("fechai");
        String fechaf = request.getParameter("fechaf");
        String sueldoMensual = request.getParameter("sueldoMensual");
        String totalpagar = request.getParameter("totalpagar");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String req = request.getParameter("req");
        String cedula = request.getParameter("cedula");
        String tipo = request.getParameter("tipo");
        String cargo = request.getParameter("cargo");
        String iva = request.getParameter("iva");
        String ced = request.getParameter("ced");
        String observacion = request.getParameter("observacion");
        String cedulaEst = request.getParameter("cedulaEst");
        String nombreEst = request.getParameter("nombreEst");
        String apellidoEst = request.getParameter("apellidoEst");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(req));
        cComp.setReq_nombre(nombre);
        cComp.setReq_cpc(apellido);
        cComp.setReq_descripcion(cargo);
        cComp.setReq_iva(Integer.parseInt(iva));
        cComp.setSolicitud_cedula(ced);
        cComp.setSolestado_observacion(observacion);
        if (tipo.equals("1")) {
            cComp.setN_horas(Integer.parseInt(horas));
            cComp.setCantidad(Double.parseDouble(shoras));
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(fechaf);
        } else if (tipo.equals("2")) {
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(fechaf);
            cComp.setCantidad(Double.parseDouble(sueldoMensual));
            cComp.setN_horas(null);
        } else if (tipo.equals("3")) {
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(null);
            cComp.setN_horas(null);
            cComp.setCantidad(Double.parseDouble(totalpagar));
            cComp.setAutoridades_cedula(cedulaEst);
            cComp.setAutoridades_nombre(nombreEst);
            cComp.setAutoridades_cargo(apellidoEst);
        }
        cComp.setTc_id(Integer.parseInt(tipo));
        if (((tipo.equals("3")) && intIdAreaGestion != 46)) {
            result = aComp.IngresarServiciosPF(cComp);
        } else if (tipo.equals("3") && intIdAreaGestion == 46 && !aComp.VerificacionServicioProfesional(cComp)) {
            result = aComp.IngresarServiciosPFE(cComp);
        } else if (tipo.equals("3") && intIdAreaGestion == 46 && aComp.VerificacionServicioProfesional(cComp)) {
            result = "Estudiante";
        } else {
            result = aComp.IngresarServiciosP(cComp);
        }
        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("Se agrego a la persona: \"" + nombre + "\" en el requerimiento: \"" + req + "\"");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(1);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarServiciosPO(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String horas = request.getParameter("horas");
        String shoras = request.getParameter("shoras");
        String fechai = request.getParameter("fechai");
        String fechaf = request.getParameter("fechaf");
        String sueldoMensual = request.getParameter("sueldoMensual");
        String totalpagar = request.getParameter("totalpagar");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String req = request.getParameter("req");
        String cedula = request.getParameter("cedula");
        String tipo = request.getParameter("tipo");
        String cargo = request.getParameter("cargo");
        String iva = request.getParameter("iva");
        String ced = request.getParameter("ced");
        String observacion = request.getParameter("observacion");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(req));
        cComp.setReq_nombre(nombre);
        cComp.setReq_cpc(apellido);
        cComp.setReq_descripcion(cargo);
        cComp.setReq_iva(Integer.parseInt(iva));
        cComp.setSolestado_observacion(observacion);
        cComp.setSolicitud_cedula(ced);
        if (tipo.equals("1")) {
            cComp.setN_horas(Integer.parseInt(horas));
            cComp.setCantidad(Double.parseDouble(shoras));
            cComp.setFecha_inicio(null);
            cComp.setFecha_fin(null);
        } else if (tipo.equals("2")) {
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(fechaf);
            cComp.setCantidad(Double.parseDouble(sueldoMensual));
            cComp.setN_horas(null);
        } else if (tipo.equals("3")) {
            cComp.setFecha_inicio(null);
            cComp.setFecha_fin(null);
            cComp.setN_horas(null);
            cComp.setCantidad(Double.parseDouble(totalpagar));
        }
        cComp.setTc_id(Integer.parseInt(tipo));
        if (tipo.equals("1") || tipo.equals("3")) {
            result = aComp.IngresarServiciosPFO(cComp);
        } else {
            result = aComp.IngresarServiciosPO(cComp);
        }
        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("Se agrego a la persona: \"" + nombre + "\" en la deuda: \"" + req + "\"");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(1);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarServiciosP(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String horas = request.getParameter("horas");
        String shoras = request.getParameter("shoras");
        String fechai = request.getParameter("fechai");
        String fechaf = request.getParameter("fechaf");
        String sueldoMensual = request.getParameter("sueldoMensual");
        String totalpagar = request.getParameter("totalpagar");
        String nombre = request.getParameter("nombre");
        String req = request.getParameter("req");
        String cedula = request.getParameter("cedula");
        String tipo = request.getParameter("tipo");
        String id = request.getParameter("servicio");
        String apellido = request.getParameter("apellido");
        String cargo = request.getParameter("cargo");
        String cedulaser = request.getParameter("cedulaser");
        String iva = request.getParameter("iva");
        String observacion = request.getParameter("observacion");
        String cedulaEst = request.getParameter("cedulaEst");
        String nombreEst = request.getParameter("nombreEst");
        String apellidoEst = request.getParameter("apellidoEst");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(req));
        cComp.setReq_nombre(nombre);
        cComp.setReq_descripcion(apellido);
        cComp.setReq_rgnombre(cargo);
        cComp.setSolicitud_cedula(cedulaser);
        cComp.setActividad_id(Integer.parseInt(id));
        cComp.setReq_iva(Integer.parseInt(iva));
        cComp.setSolestado_observacion(observacion);
        if (tipo.equals("1")) {
            cComp.setN_horas(Integer.parseInt(horas));
            cComp.setCantidad(Double.parseDouble(shoras));
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(fechaf);
        } else if (tipo.equals("2")) {
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(fechaf);
            cComp.setCantidad(Double.parseDouble(sueldoMensual));
            cComp.setN_horas(null);
        } else if (tipo.equals("3")) {
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(fechai);
            cComp.setN_horas(null);
            cComp.setCantidad(Double.parseDouble(totalpagar));
            cComp.setAutoridades_cedula(cedulaEst);
            cComp.setAutoridades_nombre(nombreEst);
            cComp.setAutoridades_cargo(apellidoEst);
        }
        cComp.setTc_id(Integer.parseInt(tipo));
        if (tipo.equals("3") && (intIdAreaGestion == 46 || intIdAreaGestion == 65) && cedulaEst != null && !aComp.VerificacionServicioProfesionalMod(cComp)) {
            result = aComp.ModificarServiciosPFE(cComp);
        } else if ((tipo.equals("1") || tipo.equals("3")) && (intIdAreaGestion != 46 && cedulaEst == null)) {
            result = aComp.ModificarServiciosP(cComp);
        } else if (tipo.equals("3") && intIdAreaGestion == 46 && aComp.VerificacionServicioProfesionalMod(cComp)) {
            result = "El estudiante ya se ingreso con el mismo miembro o tutor";
        } else {
            result = aComp.ModificarServiciosP(cComp);
        }
        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("Se modificó a la persona: \"" + nombre + "\" con código: \"" + id + "\" en el requerimiento: \"" + req + "\"");
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

    private void LiquidarSolicitud(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = null;
        String req = request.getParameter("req");
        String cedulau = request.getParameter("cedulausuario");
        String nombre = request.getParameter("nombre");
        String solicitud = request.getParameter("solicitud");
        String[][] req_id = new Gson().fromJson(req, String[][].class);

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        adEjecucion aEjecucion = new adEjecucion();

        cComp.setSolicitud_id(Integer.parseInt(solicitud));
        cComp.setSolestado_observacion(nombre);
        cComp.setUsuario_nombre(cedulau);
        cComp.setSolicitud_estado(49);

        if (req_id.length > 0) {
            result = aEjecucion.EnviarSolicitudnpOb(cComp);
            for (int i = 0; i < req_id.length; i++) {
                if (result.equals("Correcto")) {
                    cComp.setReq_id(Integer.parseInt(req_id[i][0]));
                    cComp.setFecha_inicio(req_id[i][1]);
                    cComp.setFecha_fin(req_id[i][2]);
                    cComp.setReq_costo_unitario(Double.parseDouble(req_id[i][3]));
                    cComp.setActividad_tipo(Integer.parseInt(req_id[i][4]));
                    cComp.setActividad_nombre(req_id[i][5]);
                    cComp.setActividad_responsable(req_id[i][6]);
                    if (req_id[i][4].equals("2")) {
                        result = aComp.LiquidarSolicitud(cComp);
                    } else {
                        result = aComp.LiquidarSolicitudTipo(cComp);
                    }
                }
            }
        } else {
            result = "Debe seleccionar los valores que va a liquidar";
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarServiciosPO(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String horas = request.getParameter("horas");
        String shoras = request.getParameter("shoras");
        String fechai = request.getParameter("fechai");
        String fechaf = request.getParameter("fechaf");
        String sueldoMensual = request.getParameter("sueldoMensual");
        String totalpagar = request.getParameter("totalpagar");
        String nombre = request.getParameter("nombre");
        String req = request.getParameter("req");
        String cedula = request.getParameter("cedula");
        String tipo = request.getParameter("tipo");
        String id = request.getParameter("servicio");
        String apellido = request.getParameter("apellido");
        String cargo = request.getParameter("cargo");
        String cedulaser = request.getParameter("cedulaser");
        String iva = request.getParameter("iva");
        String observacion = request.getParameter("observacion");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(req));
        cComp.setReq_nombre(nombre);
        cComp.setReq_descripcion(apellido);
        cComp.setReq_rgnombre(cargo);
        cComp.setSolicitud_cedula(cedulaser);
        cComp.setActividad_id(Integer.parseInt(id));
        cComp.setReq_iva(Integer.parseInt(iva));
        cComp.setSolestado_observacion(observacion);
        if (tipo.equals("1")) {
            cComp.setN_horas(Integer.parseInt(horas));
            cComp.setCantidad(Double.parseDouble(shoras));
            cComp.setFecha_inicio(null);
            cComp.setFecha_fin(null);
        } else if (tipo.equals("2")) {
            cComp.setFecha_inicio(fechai);
            cComp.setFecha_fin(fechaf);
            cComp.setCantidad(Double.parseDouble(sueldoMensual));
            cComp.setN_horas(null);
        } else if (tipo.equals("3")) {
            cComp.setFecha_inicio(null);
            cComp.setFecha_fin(null);
            cComp.setN_horas(null);
            cComp.setCantidad(Double.parseDouble(totalpagar));
        }
        cComp.setTc_id(Integer.parseInt(tipo));
        if (tipo.equals("1") || tipo.equals("3")) {
            result = aComp.ModificarServiciosPFO(cComp);
        } else {
            result = aComp.ModificarServiciosPO(cComp);
        }
        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("Se modificó a la persona: \"" + nombre + "\" con código: \"" + id + "\" en la deuda: \"" + req + "\"");
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

    private void EliminarServiciosP(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String cedula = request.getParameter("cedula");
        String id = request.getParameter("servicio");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setActividad_id(Integer.parseInt(id));
        result = aComp.EliminarServicios(cComp);
        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("Se eliminó a la persona con código: \"" + id + "\" correctamente");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(3);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarServiciosPO(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String cedula = request.getParameter("cedula");
        String id = request.getParameter("servicio");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setActividad_id(Integer.parseInt(id));
        result = aComp.EliminarServiciosO(cComp);
        if (result.equals("Correcto")) {
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("Se eliminó a la persona con código: \"" + id + "\" correctamente");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(3);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarServiciosP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedula");
        String id = request.getParameter("servicio");
        String estado = request.getParameter("estado");
        List<cActividadRequerimiento> cActres = new ArrayList<cActividadRequerimiento>();

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setActividad_id(Integer.parseInt(id));
        cComp.setSolicitud_cedula(cedula);
        cComp.setAg_id(Integer.parseInt(estado));
        result = aComp.EnviarServicios(cComp);
        if (result.equals("Correcto")) {
            cActres = aComp.ListarPersonal(Integer.parseInt(id));
            for (int i = 0; i < cActres.size(); i++) {
                if (cActres.get(i).getSolicitud_estado() == 0) {
                    cComp.setActividad_id(cActres.get(i).getReq_id());
                    cComp.setAg_id(Integer.parseInt(estado));
                    cComp.setSolicitud_cedula(cedula);
                    result = aComp.EnviarServiciosProfesionales(cComp);
                } else if (cActres.get(i).getSolicitud_id() == 0) {
                    cComp.setActividad_id(cActres.get(i).getReq_id());
                    cComp.setAg_id(Integer.parseInt(estado));
                    cComp.setSolicitud_cedula(cedula);
                    result = aComp.EnviarServiciosProfesionales(cComp);
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EnviarServiciosPO(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String cedula = request.getParameter("cedula");
        String id = request.getParameter("servicio");
        String estado = request.getParameter("estado");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        List<cActividadRequerimiento> cActres = new ArrayList<cActividadRequerimiento>();

        cComp.setActividad_id(Integer.parseInt(id));
        cComp.setSolicitud_cedula(cedula);
        cComp.setAg_id(Integer.parseInt(estado));
        result = aComp.EnviarServiciosPO(cComp);
        if (result.equals("Correcto")) {
            cActres = aComp.ListarPersonalO(Integer.parseInt(id));
            for (int i = 0; i < cActres.size(); i++) {
                if (cActres.get(i).getSolicitud_estado() == 0) {
                    cComp.setActividad_id(cActres.get(i).getReq_id());
                    cComp.setAg_id(Integer.parseInt(estado));
                    cComp.setSolicitud_cedula(cedula);
                    result = aComp.EnviarServiciosProfesionalesOP(cComp);
                } else if (cActres.get(i).getSolicitud_id() == 0) {
                    cComp.setActividad_id(cActres.get(i).getReq_id());
                    cComp.setAg_id(Integer.parseInt(estado));
                    cComp.setSolicitud_cedula(cedula);
                    result = aComp.EnviarServiciosProfesionalesOP(cComp);
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarRequerimiento(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String cedula = request.getParameter("cedula");
        String componente = request.getParameter("componente");
        String actividad = request.getParameter("actividad");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String tipo = request.getParameter("tipo");
        String cantidad = request.getParameter("cantidad");
        String costo = request.getParameter("costo");
        String iva = request.getParameter("iva");
        String financiamiento = request.getParameter("financiamiento");
        String unidad = request.getParameter("unidad");
        String tipocompra = request.getParameter("tipocompra");
        String cpc = request.getParameter("cpc");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c = request.getParameter("cuatrimestre");
        String rg = request.getParameter("reqgeneral");
        String ag = request.getParameter("ag");
        String ap = "0";
        String[] cuatri = new Gson().fromJson(c, String[].class);
        Double sum = 0.0;

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Integer idReq = aComp.codigoSiguienteRequerimiento();
        JsonObject objJson = new JsonObject();
        if (cuatri.length > 1) {
            try {
                if (!(porcentaje1.isEmpty())) {
                    sum += Double.parseDouble(porcentaje1);
                }
            } catch (Exception e) {
                sum += 0;
            }
            try {
                if (!(porcentaje2.isEmpty())) {
                    sum += Double.parseDouble(porcentaje2);
                }
            } catch (Exception e) {
                sum += 0;
            }
            try {
                if (!(porcentaje3.isEmpty())) {
                    sum += Double.parseDouble(porcentaje3);
                }
            } catch (Exception e) {
                sum += 0;
            }
        } else if (cuatri.length == 1) {
            sum = 100.0;
        }
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del requerimiento.";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descripcion del requerimiento";
        } else if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad anual";
        } else if (costo.isEmpty()) {
            result = "Debe ingresar el costo unitario";
        } else if (iva == null) {
            result = "Debe seleccionar si requiere o no IVA";
        } else if (financiamiento.equals("0")) {
            result = "Debe seleccionar el financiamiento";
        } else if (sum != 100 || sum != 100.0) {
            result = "El valor de los porcentajes debe ser el 100%";
        } else {
            cComp.setReq_id(idReq);
            cComp.setReq_nombre(nombre);
            cComp.setReq_descripcion(descripcion);
            cComp.setReq_cantidad(Double.parseDouble(cantidad));
            cComp.setReq_costo_unitario(Double.parseDouble(costo));
            cComp.setFinanciamiento_id(Integer.parseInt(financiamiento));
            if (iva.equals("SI")) {
                cComp.setReq_iva(1);
            } else {
                cComp.setReq_iva(0);
            }
            cComp.setReq_tipo(Integer.parseInt(tipo));
            cComp.setActividad_id(Integer.parseInt(actividad));
            cComp.setActividad_componente(Integer.parseInt(componente));
            cComp.setReq_cpc(cpc);
            cComp.setUnidad_id(Integer.parseInt(unidad));
            cComp.setTc_id(Integer.parseInt(tipocompra));
            cComp.setReq_rg(Integer.parseInt(rg));
            cComp.setAe_tiempo(Integer.parseInt(ap));
            if (tipo.equals("1")) {
                if (cpc.equals("0") || cpc.equals("") || cpc.isEmpty()) {
                    result = "Debe ingresar el cpc";
                } else if (unidad.equals("0")) {
                    result = "Debe seleccionar la unidad";
                } else if (tipocompra.equals("0")) {
                    result = "Debe ingresar el tipo de compra";
                } else {
                    result = aComp.IngresarRequerimiento(cComp, Integer.parseInt(ag));
                }
            } else {
                result = aComp.IngresarRequerimiento(cComp, Integer.parseInt(ag));
            }
            if (result.equals("Correcto")) {
                if (cuatri.length > 0) {
                    if (cuatri.length > 1) {
                        for (int i = 0; i < cuatri.length; i++) {
                            cComp.setReq_id(idReq);
                            cComp.setMes_id(Integer.parseInt(cuatri[i]));
                            switch (cuatri[i]) {
                                case "1":
                                    cComp.setActividad_porcentaje(Double.parseDouble(porcentaje1));
                                    break;
                                case "2":
                                    cComp.setActividad_porcentaje(Double.parseDouble(porcentaje2));
                                    break;
                                case "3":
                                    cComp.setActividad_porcentaje(Double.parseDouble(porcentaje3));
                                    break;
                                default:
                                    break;
                            }
                            result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                        }
                    } else {
                        cComp.setReq_id(idReq);
                        cComp.setMes_id(Integer.parseInt(cuatri[0]));
                        cComp.setActividad_porcentaje(100.00);
                        result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                    }
                }
                adProyecto aProy = new adProyecto();
                objJson.addProperty("monto", aProy.montoProyecto(Integer.parseInt(componente)));
                if (result.equals("Correcto")) {
                    cTransaccion objTransaccion = new cTransaccion();
                    objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + idReq + "\" se ingresó correctamente en la actividad: \"" + cComp.getActividad_id() + "\"");
                    objTransaccion.setTransaccion_cedula(cedula);
                    objTransaccion.setTransaccion_ag(intIdAreaGestion);
                    objTransaccion.setTransaccion_tipo(1);
                    ingresarTransaccion(objTransaccion);
                }
            }
        }
        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void ListarActividad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.ListarActividad(Integer.parseInt(comp));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarActividadRep(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        String tipo = request.getParameter("tipo");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.ListarActividadRep(Integer.parseInt(comp), Integer.parseInt(tipo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarUnidad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.ListarUnidad();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarFinanciamiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.listaFinanciamiento();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarTipoC(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.ListarTipoC();

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarRequerimiento(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.ListarRequerimiento(Integer.parseInt(comp));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarRequerimientoRep(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String comp = request.getParameter("comp");
        String tipo = request.getParameter("tipo");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.ListarRequerimientoRepr(Integer.parseInt(comp), Integer.parseInt(tipo));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarReqServicios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ag = request.getParameter("ag");
        String anio = request.getParameter("anio");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.ListarRequerimientoSerProf(Integer.parseInt(ag), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaServicios(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String req = request.getParameter("req");
        String area = request.getParameter("area");
        String tipo = request.getParameter("tipo");
        String anio = request.getParameter("anio");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> result2 = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        for (int i = 0; i < req_id.length; i++) {
            result = aComp.ListarProyectosRequerimientosNPT(Integer.parseInt(area), Integer.parseInt(tipo), Integer.parseInt(req_id[i][0]), Integer.parseInt(anio));
            cActividadRequerimiento lista = new cActividadRequerimiento();
            lista.setReq_id(result.get(0).getReq_id());
            lista.setReq_nombre(result.get(0).getReq_nombre());
            lista.setReq_descripcion(result.get(0).getReq_descripcion());
            lista.setReq_cantidad(result.get(0).getReq_cantidad());
            lista.setReq_costo_unitario(result.get(0).getReq_costo_unitario());
            lista.setReq_costo_sin_iva(result.get(0).getReq_costo_sin_iva());
            lista.setReq_costo_total(result.get(0).getReq_costo_total());
            lista.setReq_iva(result.get(0).getReq_iva());
            lista.setEstado_nombre(result.get(0).getEstado_nombre());
            lista.setActividad_id(result.get(0).getSolicitud_estado());
            lista.setSolicitud_id(result.get(0).getSolicitud_id());
            lista.setCuatri(result.get(0).getCuatri());
            result2.add(lista);
        }

        String json = new Gson().toJson(result2);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListaServiciosO(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String req = request.getParameter("req");
        String[][] req_id = new Gson().fromJson(req, String[][].class);
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        List<cActividadRequerimiento> result2 = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        for (int i = 0; i < req_id.length; i++) {
            result = aComp.ListarRequerimientoSPO(Integer.parseInt(req_id[i][0]));
            cActividadRequerimiento lista = new cActividadRequerimiento();
            lista.setReq_id(result.get(0).getReq_id());
            lista.setReq_nombre(result.get(0).getReq_nombre());
            lista.setReq_descripcion(result.get(0).getReq_descripcion());
            lista.setReq_costo_unitario(result.get(0).getReq_costo_unitario());
            lista.setReq_costo_sin_iva(result.get(0).getReq_costo_sin_iva());
            lista.setReq_costo_total(result.get(0).getReq_costo_total());
            lista.setEstado_nombre(result.get(0).getEstado_nombre());
            lista.setActividad_id(result.get(0).getActividad_id());
            lista.setCuatri(result.get(0).getCuatri());
            result2.add(lista);
        }

        String json = new Gson().toJson(result2);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarActividad(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result = "Correcto";
        String actividad = request.getParameter("actividad");
        String nombre = request.getParameter("nombre");
        String responsable = request.getParameter("responsable");
        String tipo = request.getParameter("tipo");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c1 = request.getParameter("cuatrimestre1");
        String c2 = request.getParameter("cuatrimestre2");
        String c3 = request.getParameter("cuatrimestre3");
        String cedula = request.getParameter("cedula");
        String[] cuatri1 = new Gson().fromJson(c1, String[].class);
        String[] cuatri2 = new Gson().fromJson(c2, String[].class);
        String[] cuatri3 = new Gson().fromJson(c3, String[].class);

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Double suma = 0.0;
        if (!porcentaje1.isEmpty()) {
            suma += Double.parseDouble(porcentaje1);
        }
        if (!porcentaje2.isEmpty()) {
            suma += Double.parseDouble(porcentaje2);
        }
        if (!porcentaje3.isEmpty()) {
            suma += Double.parseDouble(porcentaje3);
        }
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre de la actividad.";
        } else if (responsable.isEmpty()) {
            result = "Debe ingresar el responsable de la actividad";
        } else {
            if (porcentaje1.isEmpty() && porcentaje3.isEmpty() && porcentaje2.isEmpty()) {
                result = "Debe ingresar el porcentaje por cuatrimestre";
            } else if (cuatri1.length < 1 && cuatri2.length < 1 && cuatri3.length < 1) {
                result = "Debe seleccionar los meses";
            } else if ((porcentaje1.isEmpty()) && cuatri1.length > 0) {
                result = "Debe ingresar el porcentaje para el primer cuatrimestre";
            } else if ((porcentaje2.isEmpty()) && cuatri2.length > 0) {
                result = "Debe ingresar el porcentaje para el segundo cuatrimestre";
            } else if ((porcentaje3.isEmpty()) && cuatri3.length > 0) {
                result = "Debe ingresar el porcentaje para el tercer cuatrimestre";
            } else if (suma != 100 || suma != 100.00) {
                result = "La suma de los porcentajes debe ser el 100%";
            } else {
                cComp.setActividad_id(Integer.parseInt(actividad));
                cComp.setActividad_nombre(nombre);
                cComp.setActividad_responsable(responsable);
                cComp.setActividad_tipo(Integer.parseInt(tipo));
                if (tipo.equals("0")) {
                    result = aComp.EliminarRequerimientoAct(Integer.parseInt(actividad));
                }
                if (result.equals("Correcto")) {
                    result = aComp.ModificarActividad(cComp);
                    if (result.equals("Correcto")) {
                        cTransaccion objTransaccion = new cTransaccion();
                        objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + actividad + "\" se modificó correctamente.");
                        objTransaccion.setTransaccion_cedula(cedula);
                        objTransaccion.setTransaccion_ag(intIdAreaGestion);
                        objTransaccion.setTransaccion_tipo(2);
                        ingresarTransaccion(objTransaccion);
                        result = aComp.EliminarActividadMes(Integer.parseInt(actividad));
                    }
                    if (result.equals("Correcto")) {
                        if (cuatri1.length > 0) {
                            for (int i = 0; i < cuatri1.length; i++) {
                                cComp.setMes_id(Integer.parseInt(cuatri1[i]));
                                cComp.setActividad_id(Integer.parseInt(actividad));
                                cComp.setMes_porcentaje(Double.parseDouble(porcentaje1) / cuatri1.length);
                                result = aComp.IngresarActividadMes(cComp);
                            }
                        }
                        if (cuatri2.length > 0) {
                            for (int i = 0; i < cuatri2.length; i++) {
                                cComp.setMes_id(Integer.parseInt(cuatri2[i]));
                                cComp.setActividad_id(Integer.parseInt(actividad));
                                cComp.setMes_porcentaje(Double.parseDouble(porcentaje2) / cuatri2.length);
                                result = aComp.IngresarActividadMes(cComp);
                            }
                        }
                        if (cuatri3.length > 0) {
                            for (int i = 0; i < cuatri3.length; i++) {
                                cComp.setMes_id(Integer.parseInt(cuatri3[i]));
                                cComp.setActividad_id(Integer.parseInt(actividad));
                                cComp.setMes_porcentaje(Double.parseDouble(porcentaje3) / cuatri3.length);
                                result = aComp.IngresarActividadMes(cComp);
                            }
                        }
                    }
                } else {
                    result = "No se puede eliminar los requerimientos";
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarActividadCuatr(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result = "Correcto";
        String act;
        String actividad = request.getParameter("actividad");
        String actividadid = request.getParameter("actividadid");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c1 = request.getParameter("cuatrimestre1");
        String c2 = request.getParameter("cuatrimestre2");
        String c3 = request.getParameter("cuatrimestre3");
        String cedula = request.getParameter("cedula");
        String[] cuatri1 = new Gson().fromJson(c1, String[].class);
        String[] cuatri2 = new Gson().fromJson(c2, String[].class);
        String[] cuatri3 = new Gson().fromJson(c3, String[].class);

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Double suma = 0.0;
        if (!porcentaje1.isEmpty()) {
            suma += Double.parseDouble(porcentaje1);
        }
        if (!porcentaje2.isEmpty()) {
            suma += Double.parseDouble(porcentaje2);
        }
        if (!porcentaje3.isEmpty()) {
            suma += Double.parseDouble(porcentaje3);
        }

        if (porcentaje1.isEmpty() && porcentaje3.isEmpty() && porcentaje2.isEmpty()) {
            result = "Debe ingresar el porcentaje por cuatrimestre";
        } else if (cuatri1.length < 1 && cuatri2.length < 1 && cuatri3.length < 1) {
            result = "Debe seleccionar los meses";
        } else if ((porcentaje1.isEmpty()) && cuatri1.length > 0) {
            result = "Debe ingresar el porcentaje para el primer cuatrimestre";
        } else if ((porcentaje2.isEmpty()) && cuatri2.length > 0) {
            result = "Debe ingresar el porcentaje para el segundo cuatrimestre";
        } else if ((porcentaje3.isEmpty()) && cuatri3.length > 0) {
            result = "Debe ingresar el porcentaje para el tercer cuatrimestre";
        } else {
            if (actividadid.equals("0")) {
                act = actividad;
            } else {
                act = actividadid;
            }
            cComp.setActividad_id(Integer.parseInt(act));

            if (porcentaje1.equals("0") && aComp.porcentjaeReque(Integer.parseInt(actividad), 1) > 0) {
                result = "No se puede modificar los porcentajes porque existen requerimientos.";
            } else if (porcentaje2.equals("0") && aComp.porcentjaeReque(Integer.parseInt(actividad), 2) > 0) {
                result = "No se puede modificar los porcentajes porque existen requerimientos.";
            } else if (porcentaje3.equals("0") && aComp.porcentjaeReque(Integer.parseInt(actividad), 3) > 0) {
                result = "No se puede modificar los porcentajes porque existen requerimientos.";
            } else {
                result = aComp.ModificarEstadoActMes(Integer.parseInt(act));
                if (result.equals("Correcto")) {
                    cTransaccion objTransaccion = new cTransaccion();
                    objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + act + "\" se modificó correctamente los cuatrimestres.");
                    objTransaccion.setTransaccion_cedula(cedula);
                    objTransaccion.setTransaccion_ag(intIdAreaGestion);
                    objTransaccion.setTransaccion_tipo(2);
                    ingresarTransaccion(objTransaccion);
                }

                if (result.equals("Correcto")) {
                    if (cuatri1.length > 0) {
                        for (int i = 0; i < cuatri1.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri1[i]));
                            cComp.setActividad_id(Integer.parseInt(act));
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje1) / cuatri1.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri2.length > 0) {
                        for (int i = 0; i < cuatri2.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri2[i]));
                            cComp.setActividad_id(Integer.parseInt(act));
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje2) / cuatri2.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri3.length > 0) {
                        for (int i = 0; i < cuatri3.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri3[i]));
                            cComp.setActividad_id(Integer.parseInt(act));
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje3) / cuatri3.length);
                            result = aComp.IngresarActividadMes(cComp);
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

    private void ModificarActividadResp(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result = "Correcto";
        String act;
        String actividad = request.getParameter("actividad");
        String actividadid = request.getParameter("actividadid");
        String cedula = request.getParameter("cedula");
        String responsable = request.getParameter("responsable");
        String observacion = request.getParameter("observacion");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        if (actividadid.equals("0")) {
            act = actividad;
        } else {
            act = actividadid;
        }
        cComp.setActividad_id(Integer.parseInt(act));
        cComp.setActividad_responsable(responsable);

        result = aComp.ModificarResponsableAct(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + act + "\" se modificó correctamente el responsable con observación  \"" + observacion + "\".");
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

    private void EliminarActividadCuatr(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String actividad = request.getParameter("actividad");
        String cedula = request.getParameter("cedula");

        adActividadRequerimiento aComp = new adActividadRequerimiento();

        if (aComp.existeEval(Integer.parseInt(actividad)) || aComp.existeReq(Integer.parseInt(actividad))) {
            result = "No se puede eliminar la actividad porque tiene requerimientos o tiene una evaluación.";
        } else {
            result = aComp.EliminarActividadRep(Integer.parseInt(actividad));
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + actividad + "\" se eliminó correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(3);
                ingresarTransaccion(objTransaccion);
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarActividadReprog(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result = "Correcto";
        String actividad = request.getParameter("actividad");
        String nombre = request.getParameter("nombre");
        String responsable = request.getParameter("responsable");
        String tipo = request.getParameter("tipo");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c1 = request.getParameter("cuatrimestre1");
        String c2 = request.getParameter("cuatrimestre2");
        String c3 = request.getParameter("cuatrimestre3");
        String cuatrimestre = request.getParameter("cuatrimestre");
        String cuatrimestrec = request.getParameter("cuatrimestrecod");
        String cedula = request.getParameter("cedula");
        String monto = request.getParameter("monto");
        String prioridad = request.getParameter("prioridad");
        String idActividadModid = request.getParameter("idActividadModid");
        String[] cuatri1 = new Gson().fromJson(c1, String[].class);
        String[] cuatri2 = new Gson().fromJson(c2, String[].class);
        String[] cuatri3 = new Gson().fromJson(c3, String[].class);
        Integer cuat;

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Double suma = 0.0;
        if (!porcentaje1.isEmpty()) {
            suma += Double.parseDouble(porcentaje1);
        }
        if (!porcentaje2.isEmpty()) {
            suma += Double.parseDouble(porcentaje2);
        }
        if (!porcentaje3.isEmpty()) {
            suma += Double.parseDouble(porcentaje3);
        }
        if (cuatrimestre == null || cuatrimestre.isEmpty()) {
            cuat = Integer.parseInt(cuatrimestrec);
        } else {
            cuat = Integer.parseInt(cuatrimestre);
        }
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre de la actividad.";
        } else if (responsable.isEmpty()) {
            result = "Debe ingresar el responsable de la actividad";
        } else {
            if (porcentaje1.isEmpty() && porcentaje3.isEmpty() && porcentaje2.isEmpty()) {
                result = "Debe ingresar el porcentaje por cuatrimestre";
            } else if (cuatri1.length < 1 && cuatri2.length < 1 && cuatri3.length < 1) {
                result = "Debe seleccionar los meses";
            } else if ((porcentaje1.isEmpty() || Double.parseDouble(porcentaje1) < 0) && cuatri1.length > 0) {
                result = "Debe ingresar el porcentaje para el primer cuatrimestre";
            } else if ((porcentaje2.isEmpty() || Double.parseDouble(porcentaje2) < 0) && cuatri2.length > 0) {
                result = "Debe ingresar el porcentaje para el segundo cuatrimestre";
            } else if ((porcentaje3.isEmpty() || Double.parseDouble(porcentaje3) < 0) && cuatri3.length > 0) {
                result = "Debe ingresar el porcentaje para el tercer cuatrimestre";
            } else if (suma != 100 || suma != 100.00) {
                result = "La suma de los porcentajes debe ser el 100%";
            } else if (aComp.ComprobarevaluacionAct(Integer.parseInt(actividad), 1) && (porcentaje1.isEmpty() || Double.parseDouble(porcentaje1) <= 0) && cuatri1.length <= 0) {
                result = "Debe ingresar porcentaje en el 1 cuatrimestre, por que el proyecto ya fue evaluado.";
            } else if (!aComp.Comprobarevaluacion(Integer.parseInt(actividad), 1) && !porcentaje1.isEmpty() && cuatri1.length > 0 && cuat > 1) {
                if (Double.parseDouble(porcentaje1) >= 0) {
                    result = "No puede ingresar porcentajes en el primer cuatrimestre, porque el proyecto ya fue evaluado";
                } else {
                    result = "No puede seleccionar meses en el primer cuatrimestre, porque el proyecto ya fue evaluado";
                }
            } else if (aComp.ComprobarevaluacionAct(Integer.parseInt(actividad), 2) && (porcentaje2.isEmpty() || Double.parseDouble(porcentaje2) <= 0) && cuatri2.length <= 0) {
                result = "Debe ingresar porcentaje en el 2 cuatrimestre, por que el proyecto ya fue evaluado.";
            } else if (!aComp.Comprobarevaluacion(Integer.parseInt(actividad), 2) && !porcentaje2.isEmpty() && cuatri2.length > 0 && cuat > 2) {
                if (Double.parseDouble(porcentaje2) >= 0) {
                    result = "No puede ingresar porcentajes en el segundo cuatrimestre, porque el proyecto ya fue evaluado";
                } else {
                    result = "No puede seleccionar meses en el segundo cuatrimestre, porque el proyecto ya fue evaluado";
                }
            } else {
                Integer idAct;
                if (idActividadModid.equals("0")) {
                    idAct = aComp.codigoSiguienteActividad();
                } else {
                    idAct = Integer.parseInt(idActividadModid);
                }
                cComp.setActividad_nombre(nombre);
                cComp.setActividad_responsable(responsable);
                cComp.setActividad_tipo(Integer.parseInt(tipo));
                cComp.setActividad_id(idAct);
                cComp.setActividad_monto(Double.parseDouble(monto));
                cComp.setActividad_prioridad(Integer.parseInt(prioridad));
                if (idActividadModid.equals("0")) {
                    cComp.setActividad_componente(Integer.parseInt(actividad));
                    result = aComp.IngresarActividadRepr(cComp);
                } else {
                    result = aComp.ModificarActividad(cComp);
                }
                if (result.equals("Correcto")) {
                    cTransaccion objTransaccion = new cTransaccion();
                    objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + actividad + "\" se reprogramó correctamente.");
                    objTransaccion.setTransaccion_cedula(cedula);
                    objTransaccion.setTransaccion_ag(intIdAreaGestion);
                    objTransaccion.setTransaccion_tipo(2);
                    ingresarTransaccion(objTransaccion);
                    if (!idActividadModid.equals("0")) {
                        result = aComp.EliminarActividadMes(idAct);
                    }
                }
                if (result.equals("Correcto")) {
                    if (cuatri1.length > 0) {
                        for (int i = 0; i < cuatri1.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri1[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje1) / cuatri1.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri2.length > 0) {
                        for (int i = 0; i < cuatri2.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri2[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje2) / cuatri2.length);
                            result = aComp.IngresarActividadMes(cComp);
                        }
                    }
                    if (cuatri3.length > 0) {
                        for (int i = 0; i < cuatri3.length; i++) {
                            cComp.setMes_id(Integer.parseInt(cuatri3[i]));
                            cComp.setActividad_id(idAct);
                            cComp.setMes_porcentaje(Double.parseDouble(porcentaje3) / cuatri3.length);
                            result = aComp.IngresarActividadMes(cComp);
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

    private void ListarCuatrimestreActividad(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String actividad = request.getParameter("actividad");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        cComp.setActividad_id(Integer.parseInt(actividad));
        result = aComp.ListarCuatrimestre(cComp);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarRequerimiento(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String req = request.getParameter("req");
        String componente = request.getParameter("componente");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String tipo = request.getParameter("tipo");
        String cantidad = request.getParameter("cantidad");
        String costo = request.getParameter("costo");
        String iva = request.getParameter("iva");
        String financiamiento = request.getParameter("financiamiento");
        String unidad = request.getParameter("unidad");
        String tipocompra = request.getParameter("tipocompra");
        String cpc = request.getParameter("cpc");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c = request.getParameter("cuatrimestre");
        String cedula = request.getParameter("cedula");
        String rg = request.getParameter("reqgeneral");
        String ap = "0";
        String[] cuatri = new Gson().fromJson(c, String[].class);
        Double sum = 0.0;

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Integer iv;
        if (iva.equals("SI")) {
            iv = 1;
        } else {
            iv = 0;
        }
        JsonObject objJson = new JsonObject();
        if (cuatri.length > 1) {
            try {
                if (!porcentaje1.isEmpty()) {
                    sum += Double.parseDouble(porcentaje1);
                }
            } catch (Exception e) {
                sum += 0;
            }
            try {
                if (!porcentaje2.isEmpty()) {
                    sum += Double.parseDouble(porcentaje2);
                }
            } catch (Exception e) {
                sum += 0;
            }
            try {
                if (!porcentaje3.isEmpty()) {
                    sum += Double.parseDouble(porcentaje3);
                }
            } catch (Exception e) {
                sum += 0;
            }
        } else if (cuatri.length == 1) {
            sum = 100.0;
        }
        if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del requerimiento.";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descripcion del requerimiento";
        } else if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad anual";
        } else if (costo.isEmpty()) {
            result = "Debe ingresar el costo unitario";
        } else if (iva == null) {
            result = "Debe seleccionar si requiere o no IVA";
        } else if (financiamiento.equals("0")) {
            result = "Debe seleccionar el financiamiento";
        } else if (sum != 100 || sum != 100.0) {
            result = "El valor de los porcentajes debe ser el 100%";
        } else {
            cComp.setReq_id(Integer.parseInt(req));
            cComp.setReq_nombre(nombre);
            cComp.setReq_descripcion(descripcion);
            cComp.setReq_cantidad(Double.parseDouble(cantidad));
            cComp.setReq_costo_unitario(Double.parseDouble(costo));
            cComp.setFinanciamiento_id(Integer.parseInt(financiamiento));
            cComp.setReq_iva(iv);
            cComp.setReq_tipo(Integer.parseInt(tipo));
            cComp.setActividad_componente(Integer.parseInt(componente));
            cComp.setReq_cpc(cpc);
            cComp.setUnidad_id(Integer.parseInt(unidad));
            cComp.setTc_id(Integer.parseInt(tipocompra));
            cComp.setReq_rg(Integer.parseInt(rg));
            cComp.setAe_tiempo(Integer.parseInt(ap));
            if (tipo.equals("1")) {
                if (cpc.isEmpty() || cpc.equals("") || cpc.equals("0")) {
                    result = "Debe ingresar el cpc";
                } else if (unidad.equals("0")) {
                    result = "Debe seleccionar la unidad";
                } else if (tipocompra.equals("0")) {
                    result = "Debe ingresar el tipo de compra";
                } else {
                    result = aComp.ModificarRequerimiento(cComp);
                }
            } else {
                result = aComp.ModificarRequerimiento(cComp);
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + req + "\" se modificó correctamente.");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
                result = aComp.EliminarRequerimienbtoCuatri(Integer.parseInt(req));
                if (result.equals("Correcto")) {
                    if (cuatri.length > 0) {
                        if (cuatri.length > 1) {
                            for (int i = 0; i < cuatri.length; i++) {
                                cComp.setReq_id(Integer.parseInt(req));
                                cComp.setMes_id(Integer.parseInt(cuatri[i]));
                                switch (cuatri[i]) {
                                    case "1":
                                        cComp.setActividad_porcentaje(Double.parseDouble(porcentaje1));
                                        break;
                                    case "2":
                                        cComp.setActividad_porcentaje(Double.parseDouble(porcentaje2));
                                        break;
                                    case "3":
                                        cComp.setActividad_porcentaje(Double.parseDouble(porcentaje3));
                                        break;
                                    default:
                                        break;
                                }
                                result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                            }
                        } else {
                            cComp.setReq_id(Integer.parseInt(req));
                            cComp.setMes_id(Integer.parseInt(cuatri[0]));
                            cComp.setActividad_porcentaje(100.00);
                            result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                        }
                    }
                }
                adProyecto aProy = new adProyecto();
                objJson.addProperty("monto", aProy.montoProyecto(Integer.parseInt(componente)));
            }
        }
        try {
            if (request.getParameter("verificacion").equals("3") && tipo.equals("1")) {
                cProyecto cProy = new cProyecto();
                adProyecto aProy = new adProyecto();
                cProy.setProyecto_id(Integer.parseInt(req));
                cProy.setEstado_id(1);
                cProy.setUsuario_nombre(cedula);
                result = aProy.EnviarRequerimiento(cProy);
                if (result.equals("Correcto")) {
                    cComp.setReq_verificacion(Integer.parseInt(request.getParameter("verificacion")));
                    cComp.setReq_id(Integer.parseInt(req));
                    result = aComp.ModificarReqVerificacion(cComp);
                }
            }
        } catch (Exception e) {
        }
        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void ModificarReforma(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        List<cActividadRequerimiento> resultc = new ArrayList<cActividadRequerimiento>();
        String req = request.getParameter("intID");
        String nombre = request.getParameter("txtNombre");
        String descripcion = request.getParameter("txtDescripcion");
        String tipo = request.getParameter("tipoReq");
        String cantidad = request.getParameter("dblCantidad");
        String costo = request.getParameter("dblCostoU");
        String iva = request.getParameter("radioiva");
        String financiamiento = request.getParameter("selfinan");
        String unidad = request.getParameter("selunidad");
        String tipocompra = request.getParameter("seltipoc");
        String cpc = request.getParameter("inpcpc");
        String porcentaje1 = request.getParameter("porcentajei");
        String porcentaje2 = request.getParameter("porcentajeii");
        String porcentaje3 = request.getParameter("porcentajeiii");
        String c = request.getParameter("checkcuatreq");
        String cedula = request.getParameter("cedulaProyecto");
        String presupuestoid = request.getParameter("txtPresupuestoid");
        String ejercicio = request.getParameter("txtEjercicio");
        String entidad = request.getParameter("txtEntidad");
        String unidade = request.getParameter("txtUnidadE");
        String unidadd = request.getParameter("txtUnidadD");
        String programa = request.getParameter("txtPrograma");
        String subprograma = request.getParameter("txtSubprograma");
        String proyecto = request.getParameter("txtProyecto");
        String actividad = request.getParameter("txtActividad");
        String obra = request.getParameter("txtObra");
        String geografico = request.getParameter("txtGeografico");
        String renglo = request.getParameter("txtRenglo");
        String rengloa = request.getParameter("txtRengloA");
        String fuente = request.getParameter("txtFuente");
        String organismo = request.getParameter("txtOrganismo");
        String correlativo = request.getParameter("txtCorrelativo");
        String presupuesto = request.getParameter("selPresupuesto");
        String observacion = request.getParameter("txtObservacion");
        String reforma = request.getParameter("reforma");
        String anio = request.getParameter("anio");
        String[] cuatri = new Gson().fromJson(c, String[].class);
        Double sum = 0.0, por1 = 0.0, por2 = 0.0, por3 = 0.0;
        Integer ac;
        Boolean p1 = false, p2 = false, p3 = false;

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        if (cuatri.length > 1) {
            try {
                if (!porcentaje1.isEmpty()) {
                    sum += Double.parseDouble(porcentaje1);
                    por1 = Double.parseDouble(porcentaje1);
                }
            } catch (Exception e) {
                sum += 0;
                por1 = 0.0;
            }
            try {
                if (!porcentaje2.isEmpty()) {
                    sum += Double.parseDouble(porcentaje2);
                    por2 = Double.parseDouble(porcentaje2);
                }
            } catch (Exception e) {
                sum += 0;
                por2 = 0.0;
            }
            try {
                if (!porcentaje3.isEmpty()) {
                    sum += Double.parseDouble(porcentaje3);
                    por3 = Double.parseDouble(porcentaje3);
                }
            } catch (Exception e) {
                sum += 0;
                por3 = 0.0;
            }
        } else if (cuatri.length == 1) {
            sum = 100.0;
            if (null != cuatri[0]) {
                switch (cuatri[0]) {
                    case "1":
                        por1 = 100.0;
                        break;
                    case "2":
                        por2 = 100.0;
                        break;
                    case "3":
                        por3 = 100.0;
                        break;
                    default:
                        break;
                }
            }
        }
        if (aComp.Actividadid(Integer.parseInt(req)) == 0) {
            ac = aComp.ActividadidP(Integer.parseInt(req));
        } else {
            ac = aComp.Actividadid(Integer.parseInt(req));
        }
        resultc = aComp.ListarCuatrimestreReq(Integer.parseInt(req));

        for (int i = 0; i < resultc.size(); i++) {
            if (por1 == 0.0 && resultc.get(i).getMes_id() == 1 && aComp.ComprobarevaluacionAct(ac, 1)) {
                p1 = true;
            } else if (por2 == 0.0 && resultc.get(i).getMes_id() == 2 && aComp.ComprobarevaluacionAct(ac, 2)) {
                p2 = true;
            } else if (por3 == 0.0 && resultc.get(i).getMes_id() == 3 && aComp.ComprobarevaluacionAct(ac, 3)) {
                p3 = true;
            }
        }
        if (p1 || p2 || p3) {
            result = "No se puede cambiar la programación porque ya existe una evaluación.";
        } else if (nombre.isEmpty()) {
            result = "Debe ingresar el nombre del requerimiento.";
        } else if (descripcion.isEmpty()) {
            result = "Debe ingresar la descripcion del requerimiento";
        } else if (cantidad.isEmpty()) {
            result = "Debe ingresar la cantidad anual";
        } else if (costo.isEmpty()) {
            result = "Debe ingresar el costo unitario";
        } else if (iva == null) {
            result = "Debe seleccionar si requiere o no IVA";
        } else if (financiamiento.equals("0")) {
            result = "Debe seleccionar el financiamiento";
        } else if (sum != 100 || sum != 100.0) {
            result = "El valor de los porcentajes debe ser el 100%";
        } else {
            cComp.setReq_id(Integer.parseInt(req));
            cComp.setReq_nombre(nombre);
            cComp.setReq_descripcion(descripcion);
            cComp.setReq_cantidad(Double.parseDouble(cantidad));
            cComp.setReq_costo_unitario(Double.parseDouble(costo));
            cComp.setFinanciamiento_id(Integer.parseInt(financiamiento));
            cComp.setReq_iva(Integer.parseInt(iva));
            cComp.setReq_tipo(Integer.parseInt(tipo));
            cComp.setReq_cpc(cpc);
            cComp.setReq_reforma(Integer.parseInt(reforma));
            cComp.setUnidad_id(Integer.parseInt(unidad));
            cComp.setTc_id(Integer.parseInt(tipocompra));
            cComp.setPresupuesto_id(Integer.parseInt(presupuestoid));
            cComp.setPresupuesto_ejercicio(Integer.parseInt(ejercicio));
            cComp.setPresupuesto_entidad(Integer.parseInt(entidad));
            cComp.setPresupuesto_unidad_desc(unidadd);
            cComp.setPresupuesto_unidad_ejec(Integer.parseInt(unidade));
            cComp.setPresupuesto_programa(programa);
            cComp.setPresupuesto_subprograma(subprograma);
            cComp.setPresupuesto_proyecto(proyecto);
            cComp.setPresupuesto_actividad(actividad);
            cComp.setPresupuesto_obra(obra);
            cComp.setPresupuesto_geografico(geografico);
            cComp.setPresupuesto_renglo(Integer.parseInt(renglo));
            cComp.setPresupuesto_renglo_aux(rengloa);
            cComp.setPresupuesto_fuente(fuente);
            cComp.setPresupuesto_organismo(organismo);
            cComp.setPresupuesto_correlativo(correlativo);
            cComp.setPresupuesto_presupuesto(presupuesto);
            cComp.setReq_anio(Integer.parseInt(anio));
            if (tipo.equals("1")) {
                if (cpc.isEmpty() || cpc.equals("") || cpc.equals("0")) {
                    result = "Debe ingresar el cpc";
                } else if (unidad.equals("0")) {
                    result = "Debe seleccionar la unidad";
                } else if (tipocompra.equals("0")) {
                    result = "Debe ingresar el tipo de compra";
                } else {
                    result = aComp.ModificarRequerimientoR(cComp);
                }
            } else {
                result = aComp.ModificarRequerimientoR(cComp);
            }
            if (result.equals("Correcto")) {
                cTransaccion objTransaccion = new cTransaccion();
                objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + req + "\" se modificó correctamente con la observación \"" + observacion + "\".");
                objTransaccion.setTransaccion_cedula(cedula);
                objTransaccion.setTransaccion_ag(intIdAreaGestion);
                objTransaccion.setTransaccion_tipo(2);
                ingresarTransaccion(objTransaccion);
                result = aComp.EliminarRequerimienbtoCuatri(Integer.parseInt(req));
                if (result.equals("Correcto")) {
                    if (cuatri.length > 0) {
                        if (cuatri.length > 1) {
                            for (int i = 0; i < cuatri.length; i++) {
                                cComp.setReq_id(Integer.parseInt(req));
                                cComp.setMes_id(Integer.parseInt(cuatri[i]));
                                switch (cuatri[i]) {
                                    case "1":
                                        cComp.setActividad_porcentaje(Double.parseDouble(porcentaje1));
                                        break;
                                    case "2":
                                        cComp.setActividad_porcentaje(Double.parseDouble(porcentaje2));
                                        break;
                                    case "3":
                                        cComp.setActividad_porcentaje(Double.parseDouble(porcentaje3));
                                        break;
                                    default:
                                        break;
                                }
                                result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                            }
                        } else {
                            cComp.setReq_id(Integer.parseInt(req));
                            cComp.setMes_id(Integer.parseInt(cuatri[0]));
                            cComp.setActividad_porcentaje(100.00);
                            result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                        }
                    }
                    if (result.equals("Correcto")) {
                        result = aComp.ModReformaEstructura(cComp);
                    }
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarReformaSaldo(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String req = request.getParameter("intID");
        String cedula = request.getParameter("cedulaProyecto");
        String observacion = request.getParameter("txtObservacion");
        String reforma = request.getParameter("reforma");
        String saldo = request.getParameter("dblSaldo");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(req));
        cComp.setReq_reforma(Integer.parseInt(reforma));
        cComp.setReq_costo_total(Double.parseDouble(saldo));
        result = aComp.ModificarSaldo(cComp);

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El saldo del requerimiento con código: \"" + req + "\" se modificó correctamente con la observación \"" + observacion + "\" de la reforma \"" + reforma + "\".");
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

    private void ModificarRequerimientoRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String req = request.getParameter("req");
        String componente = request.getParameter("componente");
        String porcentaje1 = request.getParameter("porcentaje1");
        String porcentaje2 = request.getParameter("porcentaje2");
        String porcentaje3 = request.getParameter("porcentaje3");
        String c = request.getParameter("cuatrimestre");
        String cedula = request.getParameter("cedula");
        String[] cuatri = new Gson().fromJson(c, String[].class);
        Double sum = 0.0;

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        JsonObject objJson = new JsonObject();
        if (cuatri.length > 1) {
            try {
                if (!porcentaje1.isEmpty()) {
                    sum += Double.parseDouble(porcentaje1);
                }
            } catch (Exception e) {
                sum += 0;
            }
            try {
                if (!porcentaje2.isEmpty()) {
                    sum += Double.parseDouble(porcentaje2);
                }
            } catch (Exception e) {
                sum += 0;
            }
            try {
                if (!porcentaje3.isEmpty()) {
                    sum += Double.parseDouble(porcentaje3);
                }
            } catch (Exception e) {
                sum += 0;
            }
        } else if (cuatri.length == 1) {
            sum = 100.0;
        }
        if (sum != 100 || sum != 100.0) {
            result = "El valor de los porcentajes debe ser el 100%";
        } else {
            cComp.setReq_id(Integer.parseInt(req));

            result = aComp.EliminarRequerimienbtoCuatriRep(Integer.parseInt(req));
            if (result.equals("Correcto")) {
                if (cuatri.length > 0) {
                    if (cuatri.length > 1) {
                        for (int i = 0; i < cuatri.length; i++) {
                            cComp.setReq_id(Integer.parseInt(req));
                            cComp.setMes_id(Integer.parseInt(cuatri[i]));
                            switch (cuatri[i]) {
                                case "1":
                                    cComp.setActividad_porcentaje(Double.parseDouble(porcentaje1));
                                    break;
                                case "2":
                                    cComp.setActividad_porcentaje(Double.parseDouble(porcentaje2));
                                    break;
                                case "3":
                                    cComp.setActividad_porcentaje(Double.parseDouble(porcentaje3));
                                    break;
                                default:
                                    break;
                            }
                            result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                        }
                    } else {
                        cComp.setReq_id(Integer.parseInt(req));
                        cComp.setMes_id(Integer.parseInt(cuatri[0]));
                        cComp.setActividad_porcentaje(100.00);
                        result = aComp.IngresarRequerimientoCuatrimestre(cComp);
                    }
                }
                if (result.equals("Correcto")) {
                    cTransaccion objTransaccion = new cTransaccion();
                    objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + req + "\" se reprogramó correctamente.");
                    objTransaccion.setTransaccion_cedula(cedula);
                    objTransaccion.setTransaccion_ag(intIdAreaGestion);
                    objTransaccion.setTransaccion_tipo(2);
                    ingresarTransaccion(objTransaccion);
                }
            }
            adProyecto aProy = new adProyecto();
            objJson.addProperty("monto", aProy.montoProyecto(Integer.parseInt(componente)));
        }
        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void ModificarAnioReq(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String req = request.getParameter("req");
        String anio = request.getParameter("anio");
        String proy = request.getParameter("proy");

        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(req));
        cComp.setActividad_id(Integer.parseInt(anio));

        result = aComp.ModificarRequerimientoAnio(cComp);

        adProyecto aProy = new adProyecto();
        String json;
        if (result.equals("Correcto")) {
            List<cProyecto> montos = new ArrayList<cProyecto>();

            montos = aProy.ListarMontosProyectos(Integer.parseInt(proy));
            json = new Gson().toJson(montos);
        } else {
            json = new Gson().toJson(result);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarActividad(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String actividad = request.getParameter("idactividad");
        String componente = request.getParameter("componente");
        String cedula = request.getParameter("cedulaProyecto");
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.EliminarActividad(Integer.parseInt(actividad));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + actividad + "\" se eliminó correctamente.");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        JsonObject objJson = new JsonObject();
        adProyecto aProy = new adProyecto();
        objJson.addProperty("monto", aProy.montoProyecto(Integer.parseInt(componente)));
        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void EliminarActividadRep(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String actividad = request.getParameter("idactividad");
        String componente = request.getParameter("componente");
        String cedula = request.getParameter("cedulaProyecto");
        String idActividadModid = request.getParameter("idActividadModid");
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        if (aComp.ComprobarRequerimientos(Integer.parseInt(actividad))) {
            result = "La actividad no se puede eliminar porque tiene requerimientos.";
        } else if (aComp.ComprobarevaluacionAct(Integer.parseInt(actividad), 1) && aComp.ComprobarPrioridadActividad(Integer.parseInt(actividad)) == 1) {
            result = "No se puede eliminar la actividad porque ya fue evaluada en el primer cuatrimestre.";
        } else if (aComp.ComprobarevaluacionAct(Integer.parseInt(actividad), 2) && aComp.ComprobarPrioridadActividad(Integer.parseInt(actividad)) == 1) {
            result = "No se puede eliminar la actividad porque ya fue evaluada en el segundo cuatrimestre.";
        } else {
            if (idActividadModid.equals("0")) {
                result = aComp.EliminarActividadRep(Integer.parseInt(actividad));
            } else {
                result = aComp.EliminarActividadRep(Integer.parseInt(idActividadModid));
            }
        }
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La actividad con código: \"" + actividad + "\" se eliminó correctamente.");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        JsonObject objJson = new JsonObject();
        adProyecto aProy = new adProyecto();
        objJson.addProperty("monto", aProy.montoProyecto(Integer.parseInt(componente)));
        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void EliminarRequerimiento(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String componente = request.getParameter("componente");
        String req = request.getParameter("idrequerimiento");
        String cedula = request.getParameter("cedulaProyecto");
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        JsonObject objJson = new JsonObject();
        result = aComp.EliminarRequerimiento(Integer.parseInt(req));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El requerimiento con código: \"" + req + "\" se eliminó correctamente.");
            objTransaccion.setTransaccion_cedula(cedula);
            objTransaccion.setTransaccion_ag(intIdAreaGestion);
            objTransaccion.setTransaccion_tipo(3);
            ingresarTransaccion(objTransaccion);
        }

        adProyecto aProy = new adProyecto();
        objJson.addProperty("monto", aProy.montoProyecto(Integer.parseInt(componente)));
        objJson.addProperty("result", result);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objJson.toString());
    }

    private void EliminarReforma(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String req = request.getParameter("req");
        String cedula = request.getParameter("cedulaProyecto");
        String reforma = request.getParameter("reforma");
        String reqcodigo = request.getParameter("reqcodigo");
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        JsonObject objJson = new JsonObject();
        result = aComp.EliminarRequerimiento(Integer.parseInt(req));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El requerimiento: \"" + req + "\" se eliminó correctamente del requerimiento : \"" + reqcodigo + "\" de la reforma: \"" + reforma + "\".");
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

    private void EliminarSaldo(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException {
        String result;
        String req = request.getParameter("req");
        String cedula = request.getParameter("cedulaProyecto");
        String reforma = request.getParameter("reforma");
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        result = aComp.EliminarRequerimientoSaldo(Integer.parseInt(req), Integer.parseInt(reforma));
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("El saldo del requerimiento: \"" + req + "\" se eliminó correctamente de la reforma: \"" + reforma + "\".");
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

    private void ListarRequerimientosCompras(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cProyecto> result = new ArrayList<cProyecto>();
        adActividadRequerimiento aRequerimientos = new adActividadRequerimiento();
        String area = request.getParameter("area");
        String tipo = request.getParameter("tipoa");
        String anio = request.getParameter("anio");
        result = aRequerimientos.ListarProyectosCompras(Integer.parseInt(area), Integer.parseInt(tipo), Integer.parseInt(anio));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarReqReformaID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aRequerimientos = new adActividadRequerimiento();
        String req = request.getParameter("req");

        result = aRequerimientos.ListarReformasPorcodigo(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
    
    private void ListarReqReformaIDeudas(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cProyecto> result = new ArrayList<cProyecto>();
        adActividadRequerimiento aRequerimientos = new adActividadRequerimiento();
        String req = request.getParameter("req");

        result = aRequerimientos.ListarReformasPorcodigoDeudas(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarSaldos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aRequerimientos = new adActividadRequerimiento();
        String req = request.getParameter("req");

        result = aRequerimientos.ListarSaldos(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ModificarRequerimientosCompras(HttpServletRequest request, HttpServletResponse response, String strCedula, Integer intIdAreaGestion) throws IOException {
        String result;
        String req = request.getParameter("req");
        String cpc = request.getParameter("cpc");
        cActividadRequerimiento cComp = new cActividadRequerimiento();
        adActividadRequerimiento aComp = new adActividadRequerimiento();

        cComp.setReq_id(Integer.parseInt(req));
        cComp.setReq_cpc(cpc);
        result = aComp.ModificarReqCompras(cComp);
        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion(strCedula, intIdAreaGestion, 2);
            objTransaccion.setTransaccion_descripcion("El requerimiento compras con código: \"" + cComp.getReq_id() + "\" fue modificado correctamente.");
            //ingresarTransaccion(objTransaccion);
        }
        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void IngresarVerificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        String req = request.getParameter("req");
        String verificacion = request.getParameter("verificacion");
        String estado = request.getParameter("estado");
        String usuario = request.getParameter("usuario");
        String observacion = request.getParameter("observacion");
        String proyecto = request.getParameter("proye");

        cProyecto cProy = new cProyecto();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        adActividadRequerimiento aProy = new adActividadRequerimiento();

        cProy.setProyecto_id(Integer.parseInt(req));
        cProy.setProyecto_responsable(usuario);
        cProy.setEstado_id(Integer.parseInt(estado));

        try {
            if (observacion.isEmpty()) {
                result = aComp.IngresarRequerimientoEstado(cProy);
            } else {
                cProy.setEstado_observacion(observacion);
                List<cProyecto> reqver = new ArrayList<cProyecto>();
                reqver = aComp.ListarRequerimientoEstadosVerificado(Integer.parseInt(req));
                if (reqver.get(0).getEstado_id().equals(16)) {
                    cProy.setEstado_id(reqver.get(0).getEstado_id());
                    cProy.setEstado_fecha(reqver.get(0).getEstado_fecha());
                    cProy.setProyecto_id(Integer.parseInt(req));
                    result = aComp.EliminarRequerimientoEstado(cProy);
                    if (result.equals("Correcto")) {
                        cProy.setEstado_id(Integer.parseInt(estado));
                        cProy.setProyecto_id(Integer.parseInt(req));
                        cProy.setProyecto_responsable(usuario);
                        result = aProy.EnviarReqObservacion(cProy);
                    }
                } else {
                    result = aProy.EnviarReqObservacion(cProy);
                }
            }
        } catch (Exception e) {
            result = aComp.IngresarRequerimientoEstado(cProy);
        }

        if (result.equals("Correcto")) {
            cActividadRequerimiento cComp = new cActividadRequerimiento();
            cComp.setReq_id(Integer.parseInt(req));
            cComp.setReq_verificacion(Integer.parseInt(verificacion));
            result = aComp.ModificarReqVerificacion(cComp);
            if (result.equals("Correcto")) {
                Integer numreq = aComp.numRequerimiento(Integer.parseInt(proyecto));
                Integer numreqv = aComp.numRequerimientosVerificados(Integer.parseInt(proyecto));
                if (numreq.equals(numreqv)) {
                    cProy.setProyecto_id(Integer.parseInt(proyecto));
                    cProy.setEstado_id(9);
                    cProy.setUsuario_nombre(usuario);
                    result = aProy.EnviarProyecto(cProy);
                }
            }
        } else {
            result = "Error";
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void ListarFechasRequerimientos(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String req = request.getParameter("req");
        List<cProyecto> result = new ArrayList<cProyecto>();
        adProyecto aProy = new adProyecto();

        result = aProy.ListarReqEstados(Integer.parseInt(req));

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void EliminarVerificacion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result;
        List<cProyecto> reqver = new ArrayList<cProyecto>();
        String req = request.getParameter("req");
        String proyecto = request.getParameter("proye");

        cProyecto cProy = new cProyecto();
        adActividadRequerimiento aComp = new adActividadRequerimiento();
        Integer numreq = aComp.numRequerimiento(Integer.parseInt(proyecto));
        Integer numreqv = aComp.numRequerimientosVerificados(Integer.parseInt(proyecto));
        reqver = aComp.ListarRequerimientoEstadosVerificado(Integer.parseInt(req));
        cProy.setEstado_id(reqver.get(0).getEstado_id());
        cProy.setEstado_fecha(reqver.get(0).getEstado_fecha());
        cProy.setProyecto_id(Integer.parseInt(req));
        result = aComp.EliminarRequerimientoEstado(cProy);
        if (result.equals("Correcto")) {
            cActividadRequerimiento cAct = new cActividadRequerimiento();
            cAct.setReq_id(Integer.parseInt(req));
            cAct.setReq_verificacion(0);
            result = aComp.ModificarReqVerificacion(cAct);
            if (result.equals("Correcto")) {
                if (numreq.equals(numreqv)) {
                    adProyecto aProy = new adProyecto();
                    result = aProy.EliminarEstado(Integer.parseInt(proyecto));
                }
            }
        }

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void PriorizarActividad(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException, ServletException {
        String result;
        String actividad = request.getParameter("actividad");
        String prioridad = request.getParameter("prioridad");
        String cedula = request.getParameter("cedula");
        adActividadRequerimiento aPrio = new adActividadRequerimiento();

        result = aPrio.IngresarPrioActividad(Integer.parseInt(actividad), Integer.parseInt(prioridad));

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La priorización de la actividad con código: \"" + actividad + "\" se ingreso correctamente.");
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

    private void PriorizarRequerimiento(HttpServletRequest request, HttpServletResponse response, Integer intIdAreaGestion) throws IOException, ServletException {
        String result;
        String actividad = request.getParameter("req");
        String prioridad = request.getParameter("prioridad");
        String cedula = request.getParameter("cedula");
        adActividadRequerimiento aPrio = new adActividadRequerimiento();

        result = aPrio.PriorizarRequerimiento(Integer.parseInt(actividad), Integer.parseInt(prioridad));

        if (result.equals("Correcto")) {
            cTransaccion objTransaccion = new cTransaccion();
            objTransaccion.setTransaccion_descripcion("La priorización de la actividad con código: \"" + actividad + "\" se ingreso correctamente.");
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

    private void ListarActividadesProy(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String codigo = request.getParameter("codigo");
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        adActividadRequerimiento aProy = new adActividadRequerimiento();

        result = aProy.ListarActividadesProy(codigo);

        String json = new Gson().toJson(result);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
