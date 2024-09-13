/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cActividadRequerimiento;
import poa.clases.cAreaGestion;
import poa.clases.cComponenteMeta;
import poa.clases.cProyecto;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adActividadRequerimiento {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Codigo Siguiente actividad
    public Integer codigoSiguienteActividad() {
        Integer result = null;
        String SQL = "SELECT (MAX(actividad_id)) as codigo FROM actividad;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("codigo");
                    if (result.equals(null)) {
                        result = 1;
                    } else {
                        result = result + 1;
                    }
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Codigo Siguiente servicios profesional
    public Integer codigoSiguienteServicioP() {
        Integer result = null;
        String SQL = "SELECT (MAX(sp_id)) as codigo FROM servicios_profesionales;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("codigo");
                    if (result.equals(null)) {
                        result = 1;
                    } else {
                        result = result + 1;
                    }
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Porcentaje requerimientos
    public Double porcentjaeReque(Integer actividad, Integer cuatrimestre) {
        Double result = null;
        String SQL = "select sum(rm_porcentaje) from f_listarrequerimientosform('" + actividad + "', 2) inner join requerimiento_cuatrimestre on reqid=rm_req where rm_mes='" + cuatrimestre + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getDouble("sum");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Requerimientos 
    public Boolean existeReq(Integer actividad) {
        Boolean result = false;
        String SQL = "select exists(select * from f_listarrequerimientosform('" + actividad + "', 2));";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Existe evaluación 
    public Boolean existeEval(Integer actividad) {
        Boolean result = false;
        String SQL = "select exists(select * from actividad_evaluacion where ae_actividad='" + actividad + "');";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Ingresar Actividad
    public String IngresarActividad(cActividadRequerimiento cAct) {
        String result = "Error al ingresar la actividad";
        String SQL = "INSERT INTO public.actividad(\n"
                + "	actividad_id, actividad_nombre, actividad_responsable, actividad_tipo, actividad_componente)\n"
                + "	VALUES (?, ?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateActividad(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar Actividad
    public String IngresarActividadRepr(cActividadRequerimiento cAct) {
        String result = "Error al ingresar la actividad";
        String SQL = "INSERT INTO public.actividad(\n"
                + "	actividad_id, actividad_nombre, actividad_responsable, actividad_tipo, act_actividad_id, actividad_monto, actividad_prioridad)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?,?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateActividadRep(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar Actividad
    public String IngresarActividadRep(cActividadRequerimiento cAct) {
        String result = "Error al ingresar la actividad";
        String SQL = "INSERT INTO public.actividad(\n"
                + "	actividad_id, actividad_nombre, actividad_responsable, actividad_tipo, actividad_componente, actividad_fecha, actividad_prioridad)\n"
                + "	VALUES (?, ?, ?, ?, ?, now(),1);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateActividad(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar Actividad-Mes
    public String IngresarActividadMes(cActividadRequerimiento cAct) {
        String result = "Error al ingresar la actividad";
        String SQL = "INSERT INTO public.actividad_mes(\n"
                + "	am_actividad, am_mes, am_porcentaje)\n"
                + "	VALUES (?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateActividadMes(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar servicios profesionales
    public String EliminarServicios(cActividadRequerimiento cAct) {
        String result = "Error al eliminar";
        String SQL = "DELETE FROM servicios_profesionales WHERE sp_id='" + cAct.getActividad_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar servicios profesionales
    public String EliminarServiciosO(cActividadRequerimiento cAct) {
        String result = "Error al eliminar";
        String SQL = "DELETE FROM servicios_profesionales_op WHERE spo_id='" + cAct.getActividad_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Enviar servicios profesionales
    public String EnviarServicios(cActividadRequerimiento cAct) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.requerimiento_estado(\n"
                + "	reqestado_estado, reqestado_req, reqestado_fecha, reqestado_usuario, reqestado_cantidad, reqestado_costo_unitario, reqestado_costo_total, reqestado_iva)\n"
                + "	VALUES ('" + cAct.getAg_id() + "', '" + cAct.getActividad_id() + "', now(), '" + cAct.getSolicitud_cedula() + "', 0, 0, 0, 0);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Enviar servicios profesionales no req
    public String EnviarServiciosProfesionales(cActividadRequerimiento cAct) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.servicios_profesionales_estado(\n"
                + "	spe_estado, spe_serviciosp, spe_fecha, spe_usuario)\n"
                + "	VALUES ('" + cAct.getAg_id() + "', '" + cAct.getActividad_id() + "', now(), '" + cAct.getSolicitud_cedula() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Enviar servicios profesionales no req obligaciones pendientes
    public String EnviarServiciosProfesionalesOP(cActividadRequerimiento cAct) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.servicios_profesionales_op_estado(\n"
                + "	speop_estado, speop_serviciosp, speop_fecha, speop_usuario)\n"
                + "	VALUES ('" + cAct.getAg_id() + "', '" + cAct.getActividad_id() + "', now(), '" + cAct.getSolicitud_cedula() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar ingreso de solicitud para servicios profesionales
    public String ModificarSPSolicitud(cActividadRequerimiento cAct) {
        String result = "Error al actualizar";
        String SQL = "UPDATE servicios_profesionales set sp_solicitud='" + cAct.getSolicitud_id() + "' where sp_id='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar ingreso de solicitud para servicios profesionales dispo
    public String ModificarSPSolicitudDispo(cActividadRequerimiento cAct) {
        String result = "Error al actualizar";
        String SQL = "UPDATE servicios_profesionales set sp_solicitud_cert='" + cAct.getSolicitud_id() + "' where sp_id='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar dispo
    public String EliminarEstadoDispo(cActividadRequerimiento cAct) {
        String result = "Error al actualizar";
        String SQL = "UPDATE servicios_profesionales set sp_estado_dispo=null where sp_id='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar ingreso de solicitud para servicios profesionales disponibilidad
    public String ModificarSPSolicitudDis(cActividadRequerimiento cAct, Integer estado) {
        String result = "Error al actualizar";
        String SQL = "UPDATE servicios_profesionales set sp_estadp='" + estado + "' where sp_id='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar ingreso de solicitud para servicios profesionales disponibilidad fechas
    public String ModificarSPSolicitudDisF(cActividadRequerimiento cAct, Integer estado) {
        String result = "Error al actualizar";
        String SQL = "UPDATE servicios_profesionales set sp_estadp='" + estado + "', sp_fecha_fin='" + cAct.getFecha_fin() + "' where sp_id='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar ingreso de solicitud para servicios profesionales disponibilidad 2
    public String ModificarSPSolicitudDis2(cActividadRequerimiento cAct, Integer estado) {
        String result = "Error al actualizar";
        String SQL = "UPDATE servicios_profesionales set sp_estado_dispo=1 where sp_id='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar ingreso de solicitud para servicios profesionales
    public String ModificarSPSolicitudOP(cActividadRequerimiento cAct) {
        String result = "Error al actualizar";
        String SQL = "UPDATE servicios_profesionales_op set spo_solicitud='" + cAct.getSolicitud_id() + "' where spo_id='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar solicitud de enviados
    public String ModificarSolicitudEnv(cActividadRequerimiento cAct) {
        String result = "Error al actualizar";
        String SQL = "UPDATE requerimiento_estado set reqestado_solicitud='" + cAct.getSolicitud_id() + "' where reqestado_req='" + cAct.getReq_id() + "' and reqestado_fecha='" + cAct.getSolestado_fecha() + "' and reqestado_estado='" + cAct.getReq_estado() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar solicitud de enviados
    public String ModificarSolicitudEnvOP(cActividadRequerimiento cAct) {
        String result = "Error al actualizar";
        String SQL = "UPDATE obpend_estado set obpendestado_solicitud='" + cAct.getSolicitud_id() + "' where obpendestado_req='" + cAct.getReq_id() + "' and obpendestado_fecha='" + cAct.getSolestado_fecha() + "' and obpendestado_estado='" + cAct.getReq_estado() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Enviar servicios profesionales
    public String EnviarServiciosPO(cActividadRequerimiento cAct) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.obpend_estado(\n"
                + "	obpendestado_estado, obpendestado_req, obpendestado_fecha, obpendestado_usuario)\n"
                + "	VALUES ('" + cAct.getAg_id() + "', '" + cAct.getActividad_id() + "', now(), '" + cAct.getSolicitud_cedula() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Listar Actividad
    public List<cActividadRequerimiento> ListarActividad(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from actividad inner join componente on actividad_componente=componente_id join proyecto on componente_proyecto=proyecto_id where actividad_componente=? and \n"
                + "((((actividad_estado=1 and actividad_fecha < '2020-06-29') or (actividad_estado=0 and actividad_fecha > '2020-06-29')) and proyecto_anio=2020) \n"
                + " or (((actividad_estado=1 and actividad_fecha < '2021-05-29') or (actividad_estado=0 and actividad_fecha > '2021-05-29')) and proyecto_anio=2021)\n"
                + "or (((actividad_estado=1 and actividad_fecha < '2022-05-01') or (actividad_estado=0 and actividad_fecha > '2022-05-01')) and proyecto_anio=2022)) order by actividad_id asc";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, componente) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("actividad_id"));
                        cComp.setActividad_nombre(rsComp.getString("actividad_nombre"));
                        cComp.setActividad_responsable(rsComp.getString("actividad_responsable"));
                        cComp.setActividad_tipo(rsComp.getInt("actividad_tipo"));
                        cComp.setActividad_monto(rsComp.getDouble("actividad_monto"));
                        cComp.setActividad_prioridad(rsComp.getInt("actividad_prioridad"));
                        cComp.setCuatri(ListarCuatrimestre(cComp));
                        cComp.setMes(ListarMeses(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar Actividad
    public List<cActividadRequerimiento> ListarActividadRep(Integer componente, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listaactividad('" + componente + "','" + tipo + "') order by actividadid;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setActividad_responsable(rsComp.getString("actividadresponsable"));
                        cComp.setActividad_tipo(rsComp.getInt("actividadtipo"));
                        cComp.setActividad_monto(rsComp.getDouble("actividadmonto"));
                        cComp.setActividad_prioridad(rsComp.getInt("actividadprioridad"));
                        cComp.setAe_tiempo(rsComp.getInt("actividadidid"));
                        cComp.setAutoridades_id(maxevaluacion(rsComp.getInt("actividadid")));
                        if (rsComp.getInt("actividadidid") == 0) {
                            cComp.setCuatri(ListarCuatrimestreRep(rsComp.getInt("actividadid")));
                            cComp.setMes(ListarMesesRep(rsComp.getInt("actividadid")));
                        } else {
                            cComp.setCuatri(ListarCuatrimestreRep(rsComp.getInt("actividadidid")));
                            cComp.setMes(ListarMesesRep(rsComp.getInt("actividadidid")));
                        }
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar unidad
    public List<cActividadRequerimiento> ListarUnidad() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from unidad where unidad_estado=1 order by unidad_id;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setUnidad_id(rsComp.getInt("unidad_id"));
                        cComp.setUnidad_nombre(rsComp.getString("unidad_nombre"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public List<cActividadRequerimiento> listaFinanciamiento() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from financiamiento where financiamiento_estado=1 order by financiamiento_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsReg = ad.getRs();
                    while (rsReg.next()) {
                        cActividadRequerimiento oReg = new cActividadRequerimiento();
                        oReg.setFinanciamiento_id(rsReg.getInt("financiamiento_id"));
                        oReg.setFinanciamiento_nombre(rsReg.getString("financiamiento_nombre"));
                        result.add(oReg);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public List<cActividadRequerimiento> ListarTipoC() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from tipo_compra where tc_estado=1 order by tc_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsReg = ad.getRs();
                    while (rsReg.next()) {
                        cActividadRequerimiento oReg = new cActividadRequerimiento();
                        oReg.setTc_id(rsReg.getInt("tc_id"));
                        oReg.setTc_nombre(rsReg.getString("tc_nombre"));
                        result.add(oReg);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar Componentes
    public List<cComponenteMeta> ListarComponentesRep(Integer proy) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from f_listaComponente(?) order by componenteid;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setComponente_ag(rsComp.getInt("componenteag"));
                        cComp.setComponente_id(rsComp.getInt("componenteid"));
                        cComp.setComponente_nombre(rsComp.getString("componentenombre"));
                        cComp.setComponente_proyecto(rsComp.getInt("componenteidid"));
                        cComp.setActividad(ListarActividadRep(rsComp.getInt("componenteid"), 1));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar Actividades proyectos admin
    public List<cActividadRequerimiento> ListarActividadesProy(String codigo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vproyecto where (estado_id=17 or estado_id=18) and proyecto_codigo like '" + codigo + "' order by proyecto_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cActividadRequerimiento oProy = new cActividadRequerimiento();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setAg_id(rsProy.getInt("ag_id"));
                        oProy.setAg_nombre(rsProy.getString("ag_nombre"));
                        if (rsProy.getString("proyecto_fi_rep") == null) {
                            oProy.setFecha_inicio(rsProy.getString("proyecto_fi"));
                        } else {
                            oProy.setFecha_inicio(rsProy.getString("proyecto_fi_rep"));
                        }
                        if (rsProy.getString("proyecto_ff_rep") == null) {
                            oProy.setFecha_fin(rsProy.getString("proyecto_ff"));
                        } else {
                            oProy.setFecha_fin(rsProy.getString("proyecto_ff_rep"));
                        }
                        oProy.setComponente(ListarComponentesRep(rsProy.getInt("proyecto_id")));
                        result.add(oProy);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar cuatrimestre
    public List<cActividadRequerimiento> ListarCuatrimestre(cActividadRequerimiento componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select (select sum(am_porcentaje) from actividad_mes inner join mes on am_mes=mes_id where mes_cuatrimestre=1 and am_actividad='" + componente.getActividad_id() + "' and am_estado=1) as I, \n"
                + "                (select sum(am_porcentaje) from actividad_mes inner join mes on am_mes=mes_id where mes_cuatrimestre=2 and am_actividad='" + componente.getActividad_id() + "' and am_estado=1) as II,\n"
                + "                (select sum(am_porcentaje) from actividad_mes inner join mes on am_mes=mes_id where mes_cuatrimestre=3 and am_actividad='" + componente.getActividad_id() + "' and am_estado=1) as III;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setC1(rsComp.getDouble("i"));
                        cComp.setC2(rsComp.getDouble("ii"));
                        cComp.setC3(rsComp.getDouble("iii"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //actividad id
    public Integer Actividadid(Integer req) {
        Integer result = 0;
        String SQL = "select req_actividad from requerimiento where req_id='" + req + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("req_actividad");
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //actividad id
    public Integer ActividadidP(Integer req) {
        Integer result = 0;
        String SQL = "select rq.req_actividad from requerimiento r inner join requerimiento rq on r.req_req_id=rq.req_id where r.req_id='" + req + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("req_actividad");
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar cuatrimestre
    public List<cActividadRequerimiento> ListarCuatrimestreRep(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select (select sum(am_porcentaje) from actividad_mes inner join mes on am_mes=mes_id where mes_cuatrimestre=1 and am_actividad='" + componente + "' and am_estado=1) as I, \n"
                + "                (select sum(am_porcentaje) from actividad_mes inner join mes on am_mes=mes_id where mes_cuatrimestre=2 and am_actividad='" + componente + "' and am_estado=1) as II,\n"
                + "                (select sum(am_porcentaje) from actividad_mes inner join mes on am_mes=mes_id where mes_cuatrimestre=3 and am_actividad='" + componente + "' and am_estado=1) as III;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setC1(rsComp.getDouble("i"));
                        cComp.setC2(rsComp.getDouble("ii"));
                        cComp.setC3(rsComp.getDouble("iii"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar cuatrimestre
    public List<cActividadRequerimiento> ListarMeses(cActividadRequerimiento componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from actividad_mes where am_actividad='" + componente.getActividad_id() + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setMes_id(rsComp.getInt("am_mes"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar cuatrimestre
    public List<cActividadRequerimiento> ListarMesesRep(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from actividad_mes where am_actividad='" + componente + "' and am_estado=1;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setMes_id(rsComp.getInt("am_mes"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Integer codigoSiguienteRequerimiento() {
        Integer result = null;
        String SQL = "SELECT (MAX(req_id)) as codigo FROM requerimiento;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("codigo");
                    if (result.equals(null)) {
                        result = 1;
                    } else {
                        result = result + 1;
                    }
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    public Integer maxevaluacion(Integer actividad) {
        Integer result = null;
        String SQL = "select max(ae_cuatrimestre) from actividad_evaluacion where ae_actividad='" + actividad + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("max");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    public Boolean Comprobarevaluacion(Integer actividad, Integer cuatrimestre) {
        Boolean result = null;
        String SQL = "select exists(select * from actividad inner join componente on actividad_componente=componente_id join proyecto on componente_proyecto=proyecto_id\n"
                + "			 join proyecto_evaluacion on proyecto_id=pe_proyecto where actividad_id='" + actividad + "' and pe_cuatrimestre='" + cuatrimestre + "')";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    public Boolean ComprobarevaluacionAct(Integer actividad, Integer cuatrimestre) {
        Boolean result = null;
        String SQL = "select exists(select * from actividad inner join actividad_evaluacion on actividad_id=ae_actividad where actividad_id='" + actividad + "' and ae_cuatrimestre='" + cuatrimestre + "')";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    public Integer ComprobarPrioridadActividad(Integer actividad) {
        Integer result = null;
        String SQL = "select actividad_prioridad_eval from actividad where actividad_id='" + actividad + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("actividad_prioridad_eval");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    public Boolean ComprobarevaluacionComp(Integer actividad) {
        Boolean result = null;
        String SQL = "select exists(select * from componente inner join proyecto on componente_proyecto=proyecto_id\n"
                + "			 join proyecto_evaluacion on proyecto_id=pe_proyecto where componente_id='" + actividad + "' and pe_cuatrimestre=1)";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    public Boolean ComprobarRequerimientos(Integer actividad) {
        Boolean result = null;
        String SQL = "select exists(select * from f_listarrequerimientosform('" + actividad + "',2));";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Ingresar Requerimiento
    public String IngresarRequerimiento(cActividadRequerimiento cAct, Integer ag) {
        String result = "Error al ingresar el requerimiento";
        String SQL = "SELECT public.f_ingresar_requerimiento('" + cAct.getReq_nombre() + "', \n"
                + "	'" + cAct.getReq_descripcion() + "', '" + cAct.getReq_cantidad() + "', \n"
                + "	'" + cAct.getReq_costo_unitario() + "','" + cAct.getFinanciamiento_id() + "', \n"
                + "	'" + cAct.getReq_iva() + "', '" + cAct.getReq_tipo() + "', \n"
                + "	'" + cAct.getActividad_id() + "','" + cAct.getReq_cpc() + "', \n"
                + "	'" + cAct.getUnidad_id() + "', '" + cAct.getTc_id() + "', \n"
                + "	'" + cAct.getActividad_componente() + "','" + cAct.getReq_id() + "', '" + cAct.getReq_rg() + "', '" + ag + "', '" + cAct.getAe_tiempo() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_ingresar_requerimiento");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String IngresarServiciosP(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el servicio profesional";
        String SQL = "SELECT public.ingresar_servicios_profesionales('" + cAct.getReq_cpc() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "','" + cAct.getFecha_inicio() + "', \n"
                + "	'" + cAct.getFecha_fin() + "', '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_descripcion() + "', "
                + "'" + cAct.getReq_iva() + "', '" + cAct.getSolicitud_cedula() + "','" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("ingresar_servicios_profesionales");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public String IngresarServiciosPF(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el servicio profesional";
        String SQL = "SELECT public.ingresar_servicios_profesionales('" + cAct.getReq_cpc() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "','" + cAct.getFecha_inicio() + "', \n"
                + "	null, '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_descripcion() + "', "
                + "'" + cAct.getReq_iva() + "', '" + cAct.getSolicitud_cedula() + "','" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("ingresar_servicios_profesionales");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String IngresarServiciosPO(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el servicio profesional";
        String SQL = "SELECT public.ingresar_servicios_profesionalesO('" + cAct.getReq_cpc() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "','" + cAct.getFecha_inicio() + "', \n"
                + "	'" + cAct.getFecha_fin() + "', '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_descripcion() + "', '" + cAct.getReq_iva() + "', '" + cAct.getSolicitud_cedula() + "', '" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("ingresar_servicios_profesionalesO");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String ModificarServiciosP(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el servicio profesional";
        String SQL = "SELECT public.modificar_servicios_profesionales('" + cAct.getReq_descripcion() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "','" + cAct.getFecha_inicio() + "', \n"
                + "	'" + cAct.getFecha_fin() + "', '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_rgnombre() + "','" + cAct.getSolicitud_cedula() + "','" + cAct.getActividad_id() + "', '" + cAct.getReq_iva() + "', '" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("modificar_servicios_profesionales");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Liquidar solicitud
    public String LiquidarSolicitud(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el servicio profesional";
        String SQL = "SELECT public.liquidar_solicitud('" + cAct.getReq_id() + "', '" + cAct.getFecha_inicio() + "', '" + cAct.getFecha_fin() + "', '" + cAct.getReq_costo_unitario() + "', '" + cAct.getActividad_tipo() + "', '" + cAct.getActividad_nombre() + "', '" + cAct.getActividad_responsable() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("liquidar_solicitud");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Liquidar solicitud
    public String LiquidarSolicitudTipo(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el servicio profesional";
        String SQL = "SELECT public.liquidar_solicitud('" + cAct.getReq_id() + "', null, null, '" + cAct.getReq_costo_unitario() + "', '" + cAct.getActividad_tipo() + "', null, null)";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("liquidar_solicitud");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String ModificarServiciosPO(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el servicio profesional";
        String SQL = "SELECT public.modificar_servicios_profesionalesO('" + cAct.getReq_descripcion() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "','" + cAct.getFecha_inicio() + "', \n"
                + "	'" + cAct.getFecha_fin() + "', '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_rgnombre() + "','" + cAct.getSolicitud_cedula() + "','" + cAct.getActividad_id() + "', '" + cAct.getReq_iva() + "', '" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("modificar_servicios_profesionalesO");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional pago fijo
    public String IngresarServiciosPFE(cActividadRequerimiento cAct) {
        String result = "El monto ingresado sobrepasa el planificado, por favor compruebe los valores.";
        String SQL = "SELECT public.ingresar_servicios_profesionales('" + cAct.getReq_cpc() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "','" + cAct.getFecha_inicio() + "', \n"
                + "	null, '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_descripcion() + "', '" + cAct.getReq_iva() + "',"
                + " '" + cAct.getSolicitud_cedula() + "', '" + cAct.getSolestado_observacion() + "', '" + cAct.getAutoridades_cedula() + "',"
                + " '" + cAct.getAutoridades_nombre() + "', '" + cAct.getAutoridades_cargo() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("ingresar_servicios_profesionales");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String IngresarServiciosPFO(cActividadRequerimiento cAct) {
        String result = "El monto ingresado sobrepasa el planificado, por favor compruebe los valores.";
        String SQL = "SELECT public.ingresar_servicios_profesionalesO('" + cAct.getReq_cpc() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "',null, \n"
                + "	null, '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_descripcion() + "', '" + cAct.getReq_iva() + "', '" + cAct.getSolicitud_cedula() + "', '" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("ingresar_servicios_profesionalesO");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public Boolean VerificacionServicioProfesional(cActividadRequerimiento cAct) {
        Boolean result = false;
        String SQL = "select exists(select * from servicios_profesionales where sp_cedula like '" + cAct.getSolicitud_cedula() + "' and sp_cedula_estudiante like '" + cAct.getAutoridades_cedula() + "' and sp_estadp<>0 and estadp<>4)";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public Boolean VerificacionServicioProfesionalMod(cActividadRequerimiento cAct) {
        Boolean result = false;
        String SQL = "select exists(select * from servicios_profesionales where sp_cedula like '" + cAct.getSolicitud_cedula() + "' and sp_cedula_estudiante like '" + cAct.getAutoridades_cedula() + "' and sp_estadp<>0 and sp_id<>'" + cAct.getActividad_id() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String ModificarServiciosPF(cActividadRequerimiento cAct) {
        String result = "El monto ingresado sobrepasa el planificado, por favor compruebe los valores.";
        String SQL = "SELECT public.modificar_servicios_profesionales('" + cAct.getReq_descripcion() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "',null, \n"
                + "	null, '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_rgnombre() + "','" + cAct.getSolicitud_cedula() + "','" + cAct.getActividad_id() + "', '" + cAct.getReq_iva() + "', '" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("modificar_servicios_profesionales");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String ModificarServiciosPFE(cActividadRequerimiento cAct) {
        String result = "El monto ingresado sobrepasa el planificado, por favor compruebe los valores.";
        String SQL = "SELECT public.modificar_servicios_profesionales('" + cAct.getReq_descripcion() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "','" + cAct.getFecha_inicio() + "', \n"
                + "	'" + cAct.getFecha_fin() + "', '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_rgnombre() + "','" + cAct.getSolicitud_cedula() + "','" + cAct.getActividad_id() + "', '" + cAct.getReq_iva() + "', '" + cAct.getSolestado_observacion() + "', '" + cAct.getAutoridades_cedula() + "', '" + cAct.getAutoridades_nombre() + "', '" + cAct.getAutoridades_cargo() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("modificar_servicios_profesionales");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public String ModificarServiciosPFO(cActividadRequerimiento cAct) {
        String result = "El monto ingresado sobrepasa el planificado, por favor compruebe los valores.";
        String SQL = "SELECT public.modificar_servicios_profesionalesO('" + cAct.getReq_descripcion() + "', \n"
                + "	'" + cAct.getReq_nombre() + "', " + cAct.getN_horas() + ", \n"
                + "	'" + cAct.getReq_id() + "',null, \n"
                + "	null, '" + cAct.getCantidad() + "', '" + cAct.getTc_id() + "', '" + cAct.getReq_rgnombre() + "','" + cAct.getSolicitud_cedula() + "','" + cAct.getActividad_id() + "', '" + cAct.getReq_iva() + "', '" + cAct.getSolestado_observacion() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("modificar_servicios_profesionalesO");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar servicios profesional
    public Boolean codigoSolitudDispo(Integer sol) {
        Boolean result = false;
        String SQL = "select exists(select *, (select solnpac_codigo from solicitud_nopac where solnpac_id=sp_solicitud) from servicios_profesionales where sp_solicitud_cert='" + sol + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar Requerimiento-Cuatrimestre
    public String IngresarRequerimientoCuatrimestre(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el requerimiento";
        String SQL = "INSERT INTO public.requerimiento_cuatrimestre(\n"
                + "	rm_mes, rm_req, rm_porcentaje)\n"
                + "	VALUES (?, ?,?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertReqCuatrimestre(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Listar requerimiento
    public List<cActividadRequerimiento> ListarRequerimiento(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from actividad inner join componente on actividad_componente=componente_id join proyecto on componente_proyecto=proyecto_id where actividad_componente=? and actividad_tipo=1 and \n"
                + "((((actividad_estado=1 and actividad_fecha < '2020-06-29') or (actividad_estado=0 and actividad_fecha > '2020-06-29')) and proyecto_anio=2020) \n"
                + " or (((actividad_estado=1 and actividad_fecha < '2021-05-29') or (actividad_estado=0 and actividad_fecha > '2021-05-29')) and proyecto_anio=2021)\n"
                + "or (((actividad_estado=1 and actividad_fecha < '2022-05-01') or (actividad_estado=0 and actividad_fecha > '2022-05-01')) and proyecto_anio=2022)) order by actividad_id asc";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, componente) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("actividad_id"));
                        cComp.setActividad_nombre(rsComp.getString("actividad_nombre"));
                        cComp.setCuatri(ListarCuatrimestre(cComp));
                        cComp.setReq(ListarRequerimientoActP(cComp, 1));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento codigo
    public List<cActividadRequerimiento> ListarcodigosSTP(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on (solnpac_codigo) solnpac_codigo, solnpac_anio from (select *, (select solnpac_codigo from solicitud_nopac where solnpac_id=sp_solicitud), (select solnpac_anio from solicitud_nopac where solnpac_id=sp_solicitud) from servicios_profesionales where sp_solicitud_cert='" + sol + "') as con";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        cComp.setReq_anio(rsComp.getInt("solnpac_anio"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento
    public List<cActividadRequerimiento> ListarRequerimientoRepr(Integer componente, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listaactividad('" + componente + "', '" + tipo + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setActividad_componente(rsComp.getInt("actividadidid"));
                        cComp.setCuatri(ListarCuatrimestre(cComp));
                        cComp.setReq(ListarRequerimientoAct(cComp, 1));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento a servicios profesionales
    public List<cActividadRequerimiento> ListarRequerimientoSerProf(Integer ag, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarequerimientosexcelunid('" + ag + "', '" + ag + "', '" + anio + "') where paccpc is null;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento reporte
    public List<cActividadRequerimiento> ListarRequerimientoRep(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select actividad_id, actividad_nombre from \n"
                + "actividad join \n"
                + "componente on actividad_componente=componente_id where componente_id=? and actividad_tipo=1 and actividad_estado=1 and (actividad_prioridad is null or actividad_prioridad=1 or actividad_prioridad=2) order by actividad_id asc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, componente) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("actividad_id"));
                        cComp.setActividad_nombre(rsComp.getString("actividad_nombre"));
                        cComp.setCuatri(ListarCuatrimestre(cComp));
                        cComp.setReq(ListarRequerimientoAct(cComp, 2));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento
    public List<cActividadRequerimiento> ListarRequerimientoAct(cActividadRequerimiento componente, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarrequerimientosform('" + componente.getActividad_id() + "', '" + tipo + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setFinanciamiento_id(rsComp.getInt("reqfinanciamiento"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setUnidad_id(rsComp.getInt("pacunidad"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_id(rsComp.getInt("pactc"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setReq_verificacion(rsComp.getInt("pacverificacion"));
                        cComp.setReq_prioridad(rsComp.getInt("reqprioridad"));
                        cComp.setCuatri(ListarCuatrimestreReq(rsComp.getInt("reqid")));
                        cComp.setReq_rg(rsComp.getInt("reqrg"));
                        cComp.setReq_rgnombre(rsComp.getString("reqrgn"));
                        cComp.setActividad_id(rsComp.getInt("reqanio"));
                        cComp.setAe_ejecucion(rsComp.getDouble("totalsaldos"));
                        cComp.setReq(ListarRequerimientoEjecucion(rsComp.getInt("reqid")));
                        cComp.setActividad_eval(ListarPersonal(rsComp.getInt("reqhijo")));
                        cComp.setReq_iva2(rsComp.getString("reqgraba"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento ejecucion
    public List<cActividadRequerimiento> ListarRequerimientoEjecucion(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT * from public.f_listarejecucionref('" + req + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_reforma(rsComp.getInt("reqreformacod"));
                        cComp.setReq_costo_total(rsComp.getDouble("reqcostot"));
                        cComp.setActividad_nombre(rsComp.getString("reqprioridadfecha"));
                        cComp.setReq_descripcion(rsComp.getString("rnombre"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento
    public List<cActividadRequerimiento> ListarRequerimientoActP(cActividadRequerimiento componente, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarrequerimientosform('" + componente.getActividad_id() + "', '" + tipo + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setFinanciamiento_id(rsComp.getInt("reqfinanciamiento"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setUnidad_id(rsComp.getInt("pacunidad"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_id(rsComp.getInt("pactc"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setReq_verificacion(rsComp.getInt("pacverificacion"));
                        cComp.setReq_prioridad(rsComp.getInt("reqprioridad"));
                        cComp.setCuatri(ListarCuatrimestreReqRep(rsComp.getInt("reqid")));
                        cComp.setReq_rg(rsComp.getInt("reqrg"));
                        cComp.setReq_rgnombre(rsComp.getString("reqrgn"));
                        cComp.setActividad_id(rsComp.getInt("reqanio"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar proyecto requerimientos analista
    public List<cActividadRequerimiento> ListarProyectosRequerimientosNPT(Integer area, Integer tipo, Integer req, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *, (select proyecto_id from proyecto where proyecto_nombre like proyectonombre and proyecto_anio='" + anio + "' and proyecto_estado=1)  from f_listarequeejecucionNP('" + area + "', '" + tipo + "', '" + anio + "') where reqid='" + req + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_id(rsComp.getInt("agid"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setEstado_nombre(rsComp.getString("estadonombre"));
                        cComp.setSolicitud_estado(rsComp.getInt("estadoid"));
                        cComp.setSolestado_observacion(rsComp.getString("estadoobservacion"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("fechaestado"));
                        cComp.setUsuario_nombre(rsComp.getString("usuarionombre"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        //cComp.setActividad_nombre(estadonombreReq(cComp));
                        cComp.setSolicitud_id(rsComp.getInt("reqsolicitud"));
                        cComp.setProyecto_id(rsComp.getInt("proyecto_id"));
                        //cComp.setActividad_id(rsComp.getInt("reqestado_estado"));
                        cComp.setCuatri(ListarPersonal(req));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarRequerimientoSP(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on(reqid) reqid, reqnombre, reqdescripcion, req_cantidadanual, req_costounitario, req_costo_siniva, req_costototal, reqiva, reqestado_observacion, reqestado_fecha,\n"
                + "reqestado_estado, estado_nombre, reqestado_solicitud from (select * from f_listarequerimientosexcel() left join requerimiento_estado on reqid=reqestado_req left join estado on reqestado_estado=estado_id where reqid='" + req + "' order by reqid, reqestado_fecha desc) as con;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setActividad_id(rsComp.getInt("reqestado_estado"));
                        cComp.setSolicitud_id(rsComp.getInt("reqestado_solicitud"));
                        cComp.setCuatri(ListarPersonal(req));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarRequerimientoSPO(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on(deudas_id) deudas_id, deudas_nombre_proceso, deudas_proyecto, deudas_monto, deudas_iva, deudas_anticipo, obpendestado_observacion, obpendestado_fecha,\n"
                + "obpendestado_estado, estado_nombre from (select * from deudas left join obpend_estado on deudas_id=obpendestado_req left join estado on obpendestado_estado=estado_id where deudas_id='" + req + "' order by deudas_id, obpendestado_fecha desc) as con;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudas_id"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_descripcion(rsComp.getString("deudas_proyecto"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudas_monto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudas_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudas_anticipo"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setActividad_id(rsComp.getInt("obpendestado_estado"));
                        cComp.setCuatri(ListarPersonalO(req));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonal(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from(select distinct on(sp_id) sp_id, sp_nombre, sp_apellido, sp_horas, sp_fecha_inicio, sp_fecha_fin, sp_valor_unitario, sp_valor_total, sp_tipo, "
                + "sp_cargo, sp_cedula, sp_estadp, spe_estado, estado_nombre, sp_solicitud, sp_observacion, sp_valor_sin_iva, sp_verificacion, sp_cedula_estudiante, sp_apellido_estudiante, sp_nombre_estudiante, sp_estado_dispo, solnpac_codigo from (select * from servicios_profesionales left join servicios_profesionales_estado on sp_id=spe_serviciosp left join estado on spe_estado=estado_id left join solicitud_nopac on sp_solicitud=solnpac_id where sp_req='" + req + "' and (sp_estadp=1 or sp_estadp=3) order by sp_id, spe_fecha desc)as con) as con2;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("sp_id"));
                        cComp.setReq_nombre(rsComp.getString("sp_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("sp_apellido"));
                        cComp.setN_horas(rsComp.getInt("sp_horas"));
                        cComp.setFecha_inicio(rsComp.getString("sp_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("sp_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("sp_valor_unitario"));
                        cComp.setReq_costo_total(rsComp.getDouble("sp_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("sp_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("sp_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("sp_cedula"));
                        cComp.setSolicitud_estado(rsComp.getInt("spe_estado"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setSolicitud_id(rsComp.getInt("sp_solicitud"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("sp_valor_sin_iva"));
                        cComp.setSolestado_observacion(rsComp.getString("sp_observacion"));
                        cComp.setSolestado_numero(rsComp.getInt("sp_verificacion"));
                        cComp.setAutoridades_cedula(rsComp.getString("sp_cedula_estudiante"));
                        cComp.setAutoridades_nombre(rsComp.getString("sp_nombre_estudiante"));
                        cComp.setAutoridades_cargo(rsComp.getString("sp_apellido_estudiante"));
                        cComp.setAp_id(rsComp.getInt("sp_estado_dispo"));
                        cComp.setReq_estado(rsComp.getInt("sp_estadp"));
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonal2(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from(select distinct on(sp_id) sp_id, sp_nombre, sp_apellido, sp_horas, sp_fecha_inicio, sp_fecha_fin, sp_valor_unitario, sp_valor_total, sp_tipo, "
                + "sp_cargo, sp_cedula, sp_estadp, spe_estado, estado_nombre, sp_solicitud, sp_observacion, sp_valor_sin_iva, sp_verificacion, sp_cedula_estudiante, sp_apellido_estudiante, sp_nombre_estudiante, sp_estado_dispo from (select * from servicios_profesionales left join servicios_profesionales_estado on sp_id=spe_serviciosp left join estado on spe_estado=estado_id where sp_req='" + req + "' and (sp_estadp=1 or sp_estadp=3 or sp_estadp=4) order by sp_id, spe_fecha desc)as con) as con2;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("sp_id"));
                        cComp.setReq_nombre(rsComp.getString("sp_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("sp_apellido"));
                        cComp.setN_horas(rsComp.getInt("sp_horas"));
                        cComp.setFecha_inicio(rsComp.getString("sp_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("sp_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("sp_valor_unitario"));
                        cComp.setReq_costo_total(rsComp.getDouble("sp_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("sp_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("sp_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("sp_cedula"));
                        cComp.setSolicitud_estado(rsComp.getInt("spe_estado"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setSolicitud_id(rsComp.getInt("sp_solicitud"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("sp_valor_sin_iva"));
                        cComp.setSolestado_observacion(rsComp.getString("sp_observacion"));
                        cComp.setSolestado_numero(rsComp.getInt("sp_verificacion"));
                        cComp.setAutoridades_cedula(rsComp.getString("sp_cedula_estudiante"));
                        cComp.setAutoridades_nombre(rsComp.getString("sp_nombre_estudiante"));
                        cComp.setAutoridades_cargo(rsComp.getString("sp_apellido_estudiante"));
                        cComp.setAp_id(rsComp.getInt("sp_estado_dispo"));
                        cComp.setReq_estado(rsComp.getInt("sp_estadp"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonalV(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from(select distinct on(sp_id) sp_id, sp_nombre, sp_apellido, sp_horas, sp_fecha_inicio, sp_fecha_fin, sp_valor_unitario, sp_valor_total,  sp_tipo, "
                + "sp_cargo, sp_cedula, spe_estado, estado_nombre, sp_solicitud, sp_observacion, sp_valor_sin_iva, sp_verificacion, sp_estado_dispo, (select solnpac_codigo from solicitud_nopac where solnpac_id=sp_solicitud), (select solnpac_anio from solicitud_nopac where solnpac_id=sp_solicitud) from (select * from servicios_profesionales left join servicios_profesionales_estado on sp_id=spe_serviciosp left join estado on spe_estado=estado_id where (sp_req='" + req + "' and (sp_estadp=1 or sp_estadp=3) and sp_solicitud is null) or (sp_req='" + req + "' and sp_estadp=1 and sp_solicitud is not null and sp_solicitud_cert is null and sp_estado_dispo=1) order by sp_id, spe_fecha desc)as con) as con2;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("sp_id"));
                        cComp.setReq_nombre(rsComp.getString("sp_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("sp_apellido"));
                        cComp.setN_horas(rsComp.getInt("sp_horas"));
                        cComp.setFecha_inicio(rsComp.getString("sp_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("sp_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("sp_valor_unitario"));
                        cComp.setReq_costo_total(rsComp.getDouble("sp_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("sp_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("sp_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("sp_cedula"));
                        cComp.setSolicitud_estado(rsComp.getInt("spe_estado"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setSolicitud_id(rsComp.getInt("sp_solicitud"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("sp_valor_sin_iva"));
                        cComp.setSolestado_observacion(rsComp.getString("sp_observacion"));
                        cComp.setSolestado_numero(rsComp.getInt("sp_verificacion"));
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        cComp.setReq_anio(rsComp.getInt("solnpac_anio"));
                        cComp.setSolicitud_id(rsComp.getInt("sp_estado_dispo"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonalSol(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select proyecto_nombre from (select distinct on(proyecto_id) proyecto_id, proyecto_nombre from(select * from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente join requerimiento on actividad_id=req_actividad join sol_req_deudas on req_id=solnpd_req where solnpd_req=sp_req)as con2)as con),\n"
                + "(select ag_nombre from(select distinct on(ag_id) ag_id, ag_nombre from(select * from area_gestion inner join proyecto on ag_id=proyecto_ag inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente join requerimiento on actividad_id=req_actividad join sol_req_deudas on req_id=solnpd_req where solnpd_req=sp_req)as con)as con2) from servicios_profesionales where (sp_solicitud='" + req + "' and (sp_estadp=1 or sp_estadp=3) and sp_solicitud_cert is null) or (sp_solicitud_cert='" + req + "' and sp_estadp=1);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("sp_id"));
                        cComp.setReq_nombre(rsComp.getString("sp_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("sp_apellido"));
                        cComp.setN_horas(rsComp.getInt("sp_horas"));
                        cComp.setFecha_inicio(rsComp.getString("sp_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("sp_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("sp_valor_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("sp_valor_sin_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("sp_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("sp_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("sp_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("sp_cedula"));
                        cComp.setProyecto_nombre(rsComp.getString("proyecto_nombre"));
                        cComp.setAg_nombre(rsComp.getString("ag_nombre"));
                        cComp.setAutoridades_cedula(rsComp.getString("sp_cedula_estudiante"));
                        cComp.setAutoridades_nombre(rsComp.getString("sp_nombre_estudiante"));
                        cComp.setAutoridades_cargo(rsComp.getString("sp_apellido_estudiante"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonalSolEst(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on (sp_cedula_estudiante) sp_cedula_estudiante, sp_nombre_estudiante, sp_apellido_estudiante from servicios_profesionales where ((sp_solicitud='" + req + "' and (sp_estadp=1 or sp_estadp=3)) or sp_solicitud_cert='" + req + "' and sp_estadp=1) and sp_cedula_estudiante is not null;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_cedula(rsComp.getString("sp_cedula_estudiante"));
                        cComp.setSolicitud_nombre(rsComp.getString("sp_nombre_estudiante"));
                        cComp.setSolicitud_cargo(rsComp.getString("sp_apellido_estudiante"));
                        cComp.setActividad_eval(ListarPersonalSol(req));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

//Listar requerimiento para servicios profesionales OP
    public List<cActividadRequerimiento> ListarPersonalSolOP(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select deudas_proyecto from deudas where deudas_id=spo_deudas),\n"
                + "(select ag_nombre from area_gestion inner join deudas on ag_id=deudas_ag where deudas_id=spo_deudas) from servicios_profesionales_op where spo_solicitud='" + req + "' and spo_estadp=1;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("spo_id"));
                        cComp.setReq_nombre(rsComp.getString("spo_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("spo_apellido"));
                        cComp.setN_horas(rsComp.getInt("spo_horas"));
                        cComp.setFecha_inicio(rsComp.getString("spo_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("spo_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("spo_valor_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("spo_valor_sin_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("spo_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("spo_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("spo_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("spo_cedula"));
                        cComp.setProyecto_nombre(rsComp.getString("deudas_proyecto"));
                        cComp.setAg_nombre(rsComp.getString("ag_nombre"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonalSolReq(Integer req, Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select proyecto_nombre from (select distinct on(proyecto_id) proyecto_id, proyecto_nombre from(select * from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente join requerimiento on actividad_id=req_actividad join sol_req_deudas on req_id=solnpd_req where solnpd_req=sp_req)as con2)as con),\n"
                + "(select ag_nombre from(select distinct on(ag_id) ag_id, ag_nombre from(select * from area_gestion inner join proyecto on ag_id=proyecto_ag inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente join requerimiento on actividad_id=req_actividad join sol_req_deudas on req_id=solnpd_req where solnpd_req=sp_req)as con)as con2), "
                + "(select solnpac_codigo from solicitud_nopac where solnpac_id=sp_solicitud), (select solnpac_anio from solicitud_nopac where solnpac_id=sp_solicitud), (select tcp_nombre from (select distinct on(cpsp_servprof) cpsp_servprof, cpsp_codigo, cpsp_valor, cpsp_fecha, cpsp_tipo, cpsp_estado, tcp_id, tcp_nombre from(select * from certificacion_presupuestaria_sp inner join tipo_cp on cpsp_tipo=tcp_id where cpsp_servprof=sp_id order by cpsp_servprof, cpsp_fecha_ingreso desc) as con) as con2) as estadocp from servicios_profesionales where (sp_solicitud='" + sol + "' and sp_req='" + req + "' and (sp_estadp=1 or sp_estadp=3)) or (sp_solicitud_cert='" + sol + "' and sp_req='" + req + "' and sp_estadp=1);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("sp_id"));
                        cComp.setReq_nombre(rsComp.getString("sp_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("sp_apellido"));
                        cComp.setN_horas(rsComp.getInt("sp_horas"));
                        cComp.setFecha_inicio(rsComp.getString("sp_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("sp_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("sp_valor_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("sp_valor_sin_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("sp_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("sp_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("sp_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("sp_cedula"));
                        cComp.setProyecto_nombre(rsComp.getString("proyecto_nombre"));
                        cComp.setAg_nombre(rsComp.getString("ag_nombre"));
                        cComp.setSolestado_numero(rsComp.getInt("sp_verificacion"));
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        cComp.setReq_anio(rsComp.getInt("solnpac_anio"));
                        cComp.setSolicitud_id(rsComp.getInt("sp_estado_dispo"));
                        cComp.setCuatri(ListarCertificacionesSP(rsComp.getInt("sp_id")));
                        cComp.setEstado_nombre(rsComp.getString("estadocp"));
                        cComp.setAg_alias(rsComp.getString("sp_nombre_estudiante") + " " + rsComp.getString("sp_apellido_estudiante"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento fechas
    public List<cActividadRequerimiento> ListaReqfechas(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from (select distinct on(reqestado_req) reqestado_req, reqestado_fecha, reqestado_solicitud, reqestado_estado from (select * from requerimiento_estado where reqestado_req='" + req + "' and reqestado_solicitud is null and reqestado_estado=32 order by reqestado_req, reqestado_fecha asc)as con) as con2;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolestado_fecha(rsComp.getTimestamp("reqestado_fecha"));
                        cComp.setReq_estado(rsComp.getInt("reqestado_estado"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento fechas
    public List<cActividadRequerimiento> ListaReqfechasop(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on(obpendestado_req) obpendestado_req, obpendestado_fecha, obpendestado_solicitud, obpendestado_estado from obpend_estado where obpendestado_req='" + req + "' order by obpendestado_req, obpendestado_fecha desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolestado_fecha(rsComp.getTimestamp("obpendestado_fecha"));
                        cComp.setReq_estado(rsComp.getInt("obpendestado_estado"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonalO(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from(select distinct on(spo_id) spo_id, spo_nombre, spo_apellido, spo_horas, spo_fecha_inicio, spo_fecha_fin, spo_valor_unitario, spo_valor_total,  spo_tipo, spo_valor_sin_iva, "
                + "spo_cargo, spo_cedula, speop_estado, estado_nombre, spo_solicitud, spo_observacion, spo_verificacion from (select * from servicios_profesionales_op left join servicios_profesionales_op_estado on spo_id=speop_serviciosp left join estado on speop_estado=estado_id where spo_deudas='" + req + "' and spo_estadp=1 order by spo_id, speop_fecha desc)as con) as con2;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("spo_id"));
                        cComp.setReq_nombre(rsComp.getString("spo_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("spo_apellido"));
                        cComp.setN_horas(rsComp.getInt("spo_horas"));
                        cComp.setFecha_inicio(rsComp.getString("spo_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("spo_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("spo_valor_unitario"));
                        cComp.setReq_costo_total(rsComp.getDouble("spo_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("spo_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("spo_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("spo_cedula"));
                        cComp.setSolicitud_estado(rsComp.getInt("speop_estado"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setSolicitud_id(rsComp.getInt("spo_solicitud"));
                        cComp.setSolestado_observacion(rsComp.getString("spo_observacion"));
                        cComp.setSolestado_numero(rsComp.getInt("spo_verificacion"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("spo_valor_sin_iva"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para servicios profesionales
    public List<cActividadRequerimiento> ListarPersonalOSol(Integer req, Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select tcp_nombre from (select distinct on(cpspo_servprof) cpspo_servprof, cpspo_codigo, cpspo_valor, cpspo_fecha, cpspo_tipo, cpspo_estado, tcp_id, tcp_nombre from(select * from certificacion_presupuestaria_sp_op inner join tipo_cp on cpspo_tipo=tcp_id where cpspo_servprof=spo_id order by cpspo_servprof, cpspo_fecha_ingreso desc) as con) as con2) as estadocp from(select distinct on(spo_id) spo_id, spo_nombre, spo_apellido, spo_horas, spo_fecha_inicio, spo_fecha_fin, spo_valor_unitario, spo_valor_total,  spo_tipo, spo_valor_sin_iva, "
                + "spo_cargo, spo_cedula, speop_estado, estado_nombre, spo_solicitud, spo_observacion, spo_verificacion from (select * from servicios_profesionales_op left join servicios_profesionales_op_estado on spo_id=speop_serviciosp left join estado on speop_estado=estado_id where spo_deudas='" + req + "' and spo_solicitud='" + sol + "' and spo_estadp=1 order by spo_id, speop_fecha desc)as con) as con2;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("spo_id"));
                        cComp.setReq_nombre(rsComp.getString("spo_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("spo_apellido"));
                        cComp.setN_horas(rsComp.getInt("spo_horas"));
                        cComp.setFecha_inicio(rsComp.getString("spo_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("spo_fecha_fin"));
                        cComp.setCantidad(rsComp.getDouble("spo_valor_unitario"));
                        cComp.setReq_costo_total(rsComp.getDouble("spo_valor_total"));
                        cComp.setActividad_id(rsComp.getInt("spo_tipo"));
                        cComp.setActividad_nombre(rsComp.getString("spo_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("spo_cedula"));
                        cComp.setSolicitud_estado(rsComp.getInt("speop_estado"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setAe_observacion(rsComp.getString("estadocp"));
                        cComp.setSolicitud_id(rsComp.getInt("spo_solicitud"));
                        cComp.setSolestado_observacion(rsComp.getString("spo_observacion"));
                        cComp.setSolestado_numero(rsComp.getInt("spo_verificacion"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("spo_valor_sin_iva"));
                        cComp.setCuatri(ListarCertificacionesSPOP(rsComp.getInt("spo_id")));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para certificaciones presupuestarias
    public List<cActividadRequerimiento> ListarCertificacionesP(Integer solicitud, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select tcp_nombre from (select distinct on(cp_req) cp_req, cp_codigo, cp_valor, cp_fecha, cp_tipo, cp_req, cp_estado, tcp_id, tcp_nombre, cp_fecha_ingreso from(select * from certificacion_presupuestaria inner join tipo_cp on cp_tipo=tcp_id where cp_req=reqid and cp_solicitud='" + solicitud + "' order by cp_req, cp_fecha_ingreso desc) as con) as con2) as estadocp from f_listarequerimientosolicitud('" + solicitud + "', 1, '" + anio + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("solreq_cantidad"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("solreq_costo_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("solreq_costo_sin_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("solreq_costo_iva"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setTc_nombre(rsComp.getString("estadocp"));
                        cComp.setReq(ListarCertificaciones(rsComp.getInt("reqid"), solicitud));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para certificaciones presupuestarias
    public List<cActividadRequerimiento> ListarCertificacionesPNP(Integer solicitud, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select tcp_nombre from (select distinct on(cp_req) cp_req, cp_codigo, cp_valor, cp_fecha, cp_tipo, cp_req, cp_estado, tcp_id, tcp_nombre, cp_fecha_ingreso from(select * from certificacion_presupuestaria inner join tipo_cp on cp_tipo=tcp_id where cp_req=reqid and cp_solicitud='" + solicitud + "' order by cp_req, cp_fecha_ingreso desc) as con) as con2) as estadocp from f_listarequerimientosolicitud('" + solicitud + "', 2, '" + anio + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("solreq_cantidad"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("solreq_costo_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("solreq_costo_sin_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("solreq_costo_iva"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setTc_nombre(rsComp.getString("estadocp"));
                        cComp.setReq(ListarCertificaciones(rsComp.getInt("reqid"), solicitud));
                        cComp.setActividad_eval(ListarPersonalSolReq(rsComp.getInt("reqid"), solicitud));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar deudas para certificaciones presupuestarias
    public List<cActividadRequerimiento> ListarCertificacionesPOP(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select deudas_id, deudas_nombre_proceso, \n"
                + "deudas_monto, deudas_iva, deudas_anticipo, soldeudasl_costo_total, soldeudasl_costo_iva, soldeudasl_costo_anticipo, (select tcp_nombre from (select distinct on(cpv_deuda) cpv_deuda, cpv_codigo, cpv_valor_monto, cpv_fecha, cpv_tipo, cpv_estado, tcp_id, tcp_nombre from(select * from certificacion_presupuestaria_valores inner join tipo_cp on cpv_tipo=tcp_id where cpv_deuda=deudas_id and cpv_solicitud='" + solicitud + "' order by cpv_deuda, cpv_fecha desc) as con) as con2) as estadocp\n"
                + "from deudas inner join sol_deudas_lista on deudas_id=soldeudasl_deudas where soldeudasl_solicitud='" + solicitud + "'";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudas_id"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("soldeudasl_costo_total"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("soldeudasl_costo_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("soldeudasl_costo_anticipo"));
                        cComp.setTc_nombre(rsComp.getString("estadocp"));
                        cComp.setReq(ListarCertificacionesOP(rsComp.getInt("deudas_id"), solicitud));
                        cComp.setActividad_eval(ListarEstructuraDeudas(rsComp.getInt("deudas_id")));
                        cComp.setCuatri(ListarPersonalOSol(rsComp.getInt("deudas_id"), solicitud));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para certificaciones presupuestarias
    public List<cActividadRequerimiento> ListarCertificaciones(Integer req, Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from certificacion_presupuestaria inner join tipo_cp on cp_tipo=tcp_id where cp_req='" + req + "' and cp_estado=1 and cp_solicitud='" + solicitud + "' order by cp_id";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("cp_id"));
                        cComp.setReq_nombre(rsComp.getString("cp_codigo"));
                        cComp.setReq_costo_total(rsComp.getDouble("cp_valor"));
                        cComp.setTc_id(rsComp.getInt("cp_tipo"));
                        cComp.setTc_nombre(rsComp.getString("tcp_nombre"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("cp_porcentaje"));
                        cComp.setAe_observacion(rsComp.getString("cp_observacion"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("cp_valor_porcentaje"));
                        cComp.setAe_tiempo(rsComp.getInt("cp_recurrente"));
                        cComp.setFecha_inicio(rsComp.getString("cp_fecha_ingreso"));
                        cComp.setUnidad_id(rsComp.getInt("cp_liquidacion"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para certificaciones presupuestarias
    public List<cActividadRequerimiento> ListarCertificacionesSP(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from certificacion_presupuestaria_sp inner join tipo_cp on cpsp_tipo=tcp_id where cpsp_servprof='" + req + "' and cpsp_estado=1 order by cpsp_id";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("cpsp_id"));
                        cComp.setReq_nombre(rsComp.getString("cpsp_codigo"));
                        cComp.setReq_costo_total(rsComp.getDouble("cpsp_valor"));
                        cComp.setTc_id(rsComp.getInt("cpsp_tipo"));
                        cComp.setTc_nombre(rsComp.getString("tcp_nombre"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("cpsp_porcentaje"));
                        cComp.setAe_observacion(rsComp.getString("cpsp_observacion"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("cpsp_valor_porcentaje"));
                        cComp.setAe_tiempo(rsComp.getInt("cpsp_recurrente"));
                        cComp.setFecha_inicio(rsComp.getString("cpsp_fecha_ingreso"));
                        cComp.setUnidad_id(rsComp.getInt("cpsp_liquidacion"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para certificaciones presupuestarias op
    public List<cActividadRequerimiento> ListarCertificacionesSPOP(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from certificacion_presupuestaria_sp_op inner join tipo_cp on cpspo_tipo=tcp_id where cpspo_servprof='" + req + "' and cpspo_estado=1 order by cpspo_id";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("cpspo_id"));
                        cComp.setReq_nombre(rsComp.getString("cpspo_codigo"));
                        cComp.setReq_costo_total(rsComp.getDouble("cpspo_valor"));
                        cComp.setTc_id(rsComp.getInt("cpspo_tipo"));
                        cComp.setTc_nombre(rsComp.getString("tcp_nombre"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("cpspo_porcentaje"));
                        cComp.setAe_observacion(rsComp.getString("cpspo_observacion"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("cpspo_valor_porcentaje"));
                        cComp.setAe_tiempo(rsComp.getInt("cpspo_recurrente"));
                        cComp.setFecha_inicio(rsComp.getString("cpspo_fecha_ingreso"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar deudas estructura 
    public List<cActividadRequerimiento> ListarEstructuraDeudas(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from deudas_estructura where de_deudas='" + solicitud + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setPresupuesto_fuente(rsComp.getString("de_fuente"));
                        cComp.setPresupuesto_actividad(rsComp.getString("de_actividad"));
                        cComp.setPresupuesto_programa(rsComp.getString("de_programa"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("de_proyecto"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("de_renglo"));
                        cComp.setActividad_tipo(rsComp.getInt("de_iva"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimiento para certificaciones presupuestarias
    public List<cActividadRequerimiento> ListarCertificacionesOP(Integer req, Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from certificacion_presupuestaria_valores inner join tipo_cp on cpv_tipo=tcp_id where cpv_deuda='" + req + "' and cpv_estado=1 and cpv_solicitud='" + solicitud + "' order by cpv_id";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("cpv_id"));
                        cComp.setReq_nombre(rsComp.getString("cpv_codigo"));
                        cComp.setReq_costo_total(rsComp.getDouble("cpv_valor_monto"));
                        cComp.setTc_id(rsComp.getInt("cpv_tipo"));
                        cComp.setTc_nombre(rsComp.getString("tcp_nombre"));
                        cComp.setAe_observacion(rsComp.getString("cpv_observacion"));
                        cComp.setAe_tiempo(rsComp.getInt("cpv_recurrente"));
                        cComp.setFecha_inicio(rsComp.getString("cpv_fecha_ingreso"));
                        cComp.setUnidad_id(rsComp.getInt("cpv_liquidacion"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar cuatrimestre requerimientos
    public List<cActividadRequerimiento> ListarCuatrimestreReq(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT rm_mes, rm_porcentaje FROM requerimiento_cuatrimestre where rm_estado=1 and rm_req=? order by rm_mes;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, componente) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setMes_id(rsComp.getInt("rm_mes"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("rm_porcentaje"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar cuatrimestre requerimientos
    public List<cActividadRequerimiento> ListarCuatrimestreReqRep(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT rm_mes, rm_porcentaje FROM requerimiento_cuatrimestre where ((rm_estado=1 and rm_fecha< '2020-06-30') or (rm_estado=0 and rm_fecha> '2020-06-29')) and rm_req=? order by rm_mes;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, componente) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setMes_id(rsComp.getInt("rm_mes"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("rm_porcentaje"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Eliminar requerimientos
    public String EliminarRequerimientoAct(Integer cAct) {
        String result = "Error al eliminar el requerimiento";
        String SQL = "DELETE FROM public.requerimiento\n"
                + "	WHERE req_actividad=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar Actividad
    public String ModificarActividad(cActividadRequerimiento cAct) {
        String result = "Error al modificar la actividad";
        String SQL = "UPDATE public.actividad\n"
                + "	SET actividad_nombre=?, actividad_responsable=?, actividad_fecha=now(), actividad_tipo=?\n"
                + "	WHERE actividad_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateActividadMod(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar meses-actividades
    public String EliminarActividadMes(Integer cAct) {
        String result = "Error al eliminar la actividad";
        String SQL = "DELETE FROM public.actividad_mes\n"
                + "	WHERE am_actividad=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Cambiar estados meses-actividades
    public String ModificarEstadoActMes(Integer cAct) {
        String result = "Error al eliminar los meses";
        String SQL = "UPDATE actividad_mes set am_estado=0 where am_actividad=?";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Cambiar responsable actividad
    public String ModificarResponsableAct(cActividadRequerimiento cAct) {
        String result = "Error al eliminar los meses";
        String SQL = "UPDATE actividad set actividad_responsable='" + cAct.getActividad_responsable() + "' where actividad_id='" + cAct.getActividad_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar saldo
    public String ModificarSaldo(cActividadRequerimiento cAct) {
        String result = "Error";
        String SQL = "UPDATE saldos set saldos_total='" + cAct.getReq_costo_total() + "' where saldos_req='" + cAct.getReq_id() + "' and saldos_reforma='" + cAct.getReq_reforma() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modiifcar Requerimiento
    public String ModificarRequerimiento(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el requerimiento";
        String SQL = "SELECT public.f_modificar_requerimiento('" + cAct.getReq_nombre() + "', '" + cAct.getReq_descripcion() + "', \n"
                + "	'" + cAct.getReq_cantidad() + "', '" + cAct.getReq_costo_unitario() + "', '" + cAct.getFinanciamiento_id() + "', \n"
                + "	'" + cAct.getReq_iva() + "', '" + cAct.getReq_tipo() + "', '" + cAct.getReq_cpc() + "', '" + cAct.getUnidad_id() + "', \n"
                + "	'" + cAct.getTc_id() + "', '" + cAct.getReq_id() + "', '" + cAct.getReq_rg() + "', '" + cAct.getAe_tiempo() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_modificar_requerimiento");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Validación de cuatrimestre con requerimiento
    public int ValidarRequerimientoCu(int actividad, int cuatrimestre) {
        int result = 0;
        String SQL = "select sum(rm_porcentaje) from requerimiento inner join requerimiento_cuatrimestre on req_id=rm_req where req_actividad='" + actividad + "' and rm_mes='" + cuatrimestre + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("sum");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modiifcar Requerimiento reforma
    public String ModificarRequerimientoR(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el requerimiento";
        String SQL = "SELECT public.f_modificar_requerimiento_reforma('" + cAct.getReq_nombre() + "', '" + cAct.getReq_descripcion() + "', \n"
                + "	'" + cAct.getReq_cantidad() + "', '" + cAct.getReq_costo_unitario() + "', '" + cAct.getFinanciamiento_id() + "', \n"
                + "	'" + cAct.getReq_iva() + "', '" + cAct.getReq_tipo() + "', '" + cAct.getReq_cpc() + "', '" + cAct.getUnidad_id() + "', \n"
                + "	'" + cAct.getTc_id() + "', '" + cAct.getReq_id() + "', '" + cAct.getReq_reforma() + "', '" + cAct.getReq_anio() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_modificar_requerimiento_reforma");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modiifcar Requerimiento Anio
    public String ModificarRequerimientoAnio(cActividadRequerimiento cAct) {
        String result = "Error al ingresar el requerimiento";
        String SQL = "SELECT public.f_modificar_requerimientoanio('" + cAct.getReq_id() + "', '" + cAct.getActividad_id() + "')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_modificar_requerimientoanio");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar meses-requerimientos
    public String EliminarRequerimienbtoCuatri(Integer cAct) {
        String result = "Error al eliminar la actividad";
        String SQL = "DELETE FROM public.requerimiento_cuatrimestre\n"
                + "	WHERE rm_req=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar meses-requerimientos
    public String EliminarRequerimienbtoCuatriRep(Integer cAct) {
        String result = "Error al eliminar la actividad";
        String SQL = "UPDATE requerimiento_cuatrimestre set rm_estado=0, rm_fecha=now() where rm_req=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cAct) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public List<cProyecto> ListarProyectosCompras(Integer area, Integer tipo, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listarequerimiento('" + area + "', '" + tipo + "', '" + anio + "') as datos(proyecto_id integer,proyecto_nombre text, proyecto_esponsable text, proyecto_monto numeric, estado_nombre character varying, tp_nombre character varying, ag_nombre text, estado_id integer, ag_id integer, are_ag_id integer, tp_id integer, ag_tag integer);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setProyecto_id(rsComp.getInt("proyecto_id"));
                        cComp.setProyecto_nombre(rsComp.getString("proyecto_nombre"));
                        cComp.setRequerimientos(ListarRequerimientoCompras(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimientos compras
    public List<cActividadRequerimiento> ListarRequerimientoCompras(cProyecto oProy) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on (req_id) req_id, req_nombre, req_descripcion, req_cantidad_anual, req_costo_unitario, req_iva, pac_unidad, pac_tc, pac_cpc, paces_estado, paces_usuario, paces_fecha, \n"
                + "                paces_observacion, estado_id, estado_nombre, pac_verificacion, unidad_nombre, tc_nombre, req_iva2 from(select req_id, req_nombre, req_descripcion, req_cantidad_anual, req_costo_unitario, \n"
                + "                req_iva, pac_unidad, pac_tc, pac_cpc, paces_estado, paces_usuario, paces_fecha, paces_observacion, estado_id, estado_nombre, pac_verificacion, unidad_nombre, tc_nombre, req_iva2 \n"
                + "                from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente join requerimiento \n"
                + "                on actividad_id=req_actividad inner join pac on req_id=pac_req inner join pac_estado on pac_req=paces_pac join estado on paces_estado=estado_id join unidad on pac_unidad=unidad_id \n"
                + "                join tipo_compra on pac_tc=tc_id where proyecto_id='" + oProy.getProyecto_id() + "' and (pac_verificacion is null or pac_verificacion=0 or pac_verificacion=2 or pac_verificacion=3) and actividad_estado=1 and req_estado=1 and componente_estado=1 order by paces_fecha desc) as su union all \n"
                + "				(select req_id, req_nombre, req_descripcion, req_cantidad_anual, req_costo_unitario, \n"
                + "                req_iva, pac_unidad, pac_tc, pac_cpc, paces_estado, paces_usuario, paces_fecha, paces_observacion, estado_id, estado_nombre, pac_verificacion, unidad_nombre, tc_nombre, req_iva2\n"
                + "                from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente join requerimiento \n"
                + "                on actividad_id=req_actividad join pac on req_id=pac_req left join pac_estado on pac_req=paces_pac left join estado on paces_estado=estado_id join unidad on pac_unidad=unidad_id \n"
                + "                join tipo_compra on pac_tc=tc_id where proyecto_id='" + oProy.getProyecto_id() + "' and (pac_verificacion is null or pac_verificacion=0 or pac_verificacion=2 or pac_verificacion=3) and actividad_estado=1 and req_estado=1 and componente_estado=1 and paces_estado is null order by paces_fecha desc);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_id"));
                        cComp.setReq_nombre(rsComp.getString("req_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("req_descripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidad_anual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costo_unitario"));
                        cComp.setReq_iva(rsComp.getInt("req_iva"));
                        cComp.setUnidad_id(rsComp.getInt("pac_unidad"));
                        cComp.setTc_id(rsComp.getInt("pac_tc"));
                        cComp.setReq_cpc(rsComp.getString("pac_cpc"));
                        cComp.setUnidad_nombre(rsComp.getString("unidad_nombre"));
                        cComp.setTc_nombre(rsComp.getString("tc_nombre"));
                        cComp.setReq_verificacion(rsComp.getInt("pac_verificacion"));
                        cComp.setReq_iva2(rsComp.getString("req_iva2"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar requerimientos compras
    public List<cActividadRequerimiento> ListarReformasPorcodigo(Integer codigo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from (select * from requerimiento left join pac on req_id=pac_req left join unidad on pac_unidad=unidad_id left join tipo_compra on pac_tc=tc_id inner join financiamiento on req_financiamiento=financiamiento_id where req_id='" + codigo + "' union all\nselect * from requerimiento left join pac on req_id=pac_req left join unidad on pac_unidad=unidad_id left join tipo_compra on pac_tc=tc_id inner join financiamiento on req_financiamiento=financiamiento_id where req_req_id='" + codigo + "') as con order by req_id;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_id"));
                        cComp.setReq_nombre(rsComp.getString("req_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("req_descripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidad_anual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costo_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_sin_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costo_total"));
                        cComp.setReq_estado(rsComp.getInt("req_estado"));
                        cComp.setReq_reforma(rsComp.getInt("req_reforma"));
                        cComp.setReq_iva(rsComp.getInt("req_iva"));
                        cComp.setActividad_id(rsComp.getInt("req_actividad"));
                        cComp.setUnidad_id(rsComp.getInt("pac_unidad"));
                        cComp.setTc_id(rsComp.getInt("pac_tc"));
                        cComp.setReq_cpc(rsComp.getString("pac_cpc"));
                        cComp.setUnidad_nombre(rsComp.getString("unidad_nombre"));
                        cComp.setTc_nombre(rsComp.getString("tc_nombre"));
                        cComp.setReq(ListarPresupuesto(rsComp.getInt("req_id")));
                        cComp.setFinanciamiento_id(rsComp.getInt("financiamiento_id"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamiento_nombre"));
                        cComp.setReq_anio(rsComp.getInt("req_anio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar deudas por código
    public List<cProyecto> ListarReformasPorcodigoDeudas(Integer codigo) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from (select distinct on(deudas_id) deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_contrato, deudas_tcon, deudas_financiamiento, deudas_monto, deudas_ag, deudas_estado, deudas_iva, deudas_presupuesto, \n"
                + "deudas_tipo, deudas_anticipo, deudas_area, deudas_reforma, deudas_anio, deudas_monto_pendiente, de_usuario, de_estado, financiamiento_nombre, tcon_nombre, ag_nombre from (select * from deudas inner join deudas_estado on deudas_id=de_deudas join financiamiento on deudas_financiamiento=financiamiento_id join tipo_contratacion on deudas_tcon=tcon_id join area_gestion on deudas_ag=ag_id order by de_deudas, de_fecha desc) as con)as con \n"
                + "where deudas_id='" + codigo + "' and de_estado=22";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cAreaGestion cAg = new cAreaGestion();
                        cComp.setDeudas_id(rsComp.getInt("deudas_id"));
                        cComp.setDeudas_oei(rsComp.getInt("deudas_oei"));
                        cComp.setProyecto_nombre(rsComp.getString("deudas_proyecto"));
                        cComp.setDeudas_proceso(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setDeudas_contrato(rsComp.getString("deudas_contrato"));
                        cComp.setDeudas_tcon(rsComp.getInt("deudas_tcon"));
                        cComp.setDeudas_financiamiento(rsComp.getInt("deudas_financiamiento"));
                        cComp.setDeuda_monto_contrato(rsComp.getDouble("deudas_monto"));
                        cComp.setDeuda_monto_iva(rsComp.getDouble("deudas_iva"));
                        cComp.setDeudas_presupuesto(rsComp.getString("deudas_presupuesto"));
                        cComp.setTp_id(rsComp.getInt("deudas_tipo"));
                        cComp.setDeudas_anticipo(rsComp.getDouble("deudas_anticipo"));
                        cComp.setDeudas_reforma(rsComp.getInt("deudas_reforma"));
                        cComp.setProyecto_anio(rsComp.getInt("deudas_anio"));
                        cComp.setDeudas_monto_pendiente(rsComp.getDouble("deudas_monto_pendiente"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamiento_nombre"));
                        cComp.setTcon_nombre(rsComp.getString("tcon_nombre"));
                        cComp.setRequerimientos(ListarPresupuestoDeudas(codigo));
                        cComp.setEstado_id(rsComp.getInt("deudas_estado"));
                        cAg.setAg_id(rsComp.getInt("deudas_ag"));
                        cAg.setAg_nombre(rsComp.getString("ag_nombre"));
                        cComp.setAg(cAg);
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar cuatrimestre requerimientos
    public List<cActividadRequerimiento> ListarCuatrimestreReq(cActividadRequerimiento componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT rm_mes, rm_porcentaje FROM requerimiento_cuatrimestre where rm_estado=1 and rm_req='" + componente.getReq_id() + "' order by rm_mes;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setMes_id(rsComp.getInt("rm_mes"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("rm_porcentaje"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar saldos
    public List<cActividadRequerimiento> ListarSaldos(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from requerimiento left join pac on req_id=pac_req inner join saldos on req_id=saldos_req where saldos_req='" + req + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_nombre(rsComp.getString("req_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("req_descripcion"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_sin_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costo_total"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("saldos_total"));
                        cComp.setUnidad_id(rsComp.getInt("pac_unidad"));
                        cComp.setReq_reforma(rsComp.getInt("saldos_reforma"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar presupuesto 
    public List<cActividadRequerimiento> ListarPresupuesto(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarpresupuesto('" + req + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setPresupuesto_id(rsComp.getInt("idpres"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Listar presupuesto deudas
    public List<cActividadRequerimiento> ListarPresupuestoDeudas(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from deudas_estructura where de_deudas='" + req + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("de_unidad_desc"));
                        cComp.setPresupuesto_programa(rsComp.getString("de_programa"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("de_subprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("de_proyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("de_actividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("de_obra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("de_geografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("de_renglo_aux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("de_fuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("de_organismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("de_correlativo"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("de_ejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("de_entidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("de_unidad_ejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("de_renglo"));
                        cComp.setPresupuesto_id(rsComp.getInt("de_iva"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Eliminar Actividad
    public String EliminarActividad(Integer cIndicador) {
        String result = "Error al eliminar la actividad";
        String SQL = "UPDATE actividad SET actividad_estado=0 where actividad_id=?";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cIndicador) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar Actividad
    public String EliminarActividadRep(Integer cIndicador) {
        String result = "Error al eliminar la actividad";
        String SQL = "UPDATE actividad SET actividad_estado=0, actividad_fecha=now() where actividad_id=?";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cIndicador) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar requerimientos
    public String EliminarRequerimiento(Integer req) {
        String result = "Error al eliminar el requerimiento";
        String SQL = "DELETE FROM public.requerimiento\n"
                + "	WHERE req_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD

                if (ad.executeDelete(SQL, req) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar requerimientos saldos
    public String EliminarRequerimientoSaldo(Integer req, Integer saldo) {
        String result = "Error al eliminar el requerimiento";
        String SQL = "DELETE FROM public.saldos\n"
                + "	WHERE saldos_req='" + req + "' and saldos_reforma='" + saldo + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD

                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar Requerimiento Cpc
    public String ModificarReqCompras(cActividadRequerimiento cAct) {
        String result = "Error al modificar la actividad";
        String SQL = "UPDATE public.pac\n"
                + "	SET pac_cpc='" + cAct.getReq_cpc() + "'\n"
                + "	WHERE pac_req='" + cAct.getReq_id() + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD

                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar Requerimiento-Estado
    public String IngresarRequerimientoEstado(cProyecto cAct) {
        String result = "Error al ingresar el requerimiento";
        String SQL = "INSERT INTO public.pac_estado(\n"
                + "	paces_pac, paces_estado, paces_usuario)\n"
                + "	VALUES ('" + cAct.getProyecto_id() + "', '" + cAct.getEstado_id() + "', '" + cAct.getProyecto_responsable() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Listar requerimientos para eliminar verificado
    public List<cProyecto> ListarRequerimientoEstadosVerificado(Integer req) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select paces_fecha, paces_estado from pac_estado where paces_pac='" + req + "' ORDER BY paces_fecha desc limit 1;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setEstado_id(rsComp.getInt("paces_estado"));
                        cComp.setEstado_fecha(rsComp.getTimestamp("paces_fecha"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

//Eliminar Requerimiento-Estado
    public String EliminarRequerimientoEstado(cProyecto cAct) {
        String result = "Error al ingresar el requerimiento";
        String SQL = "DELETE FROM public.pac_estado\n"
                + "	WHERE paces_estado='" + cAct.getEstado_id() + "' and paces_fecha='" + cAct.getEstado_fecha() + "' and paces_pac='" + cAct.getProyecto_id() + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Enviar requerimiento con observacion
    public String EnviarReqObservacion(cProyecto oProy) {
        String result = "Error al ingresar acciones";
        String SQL = "INSERT INTO public.pac_estado(\n"
                + "	paces_pac, paces_estado, paces_usuario, paces_observacion)\n"
                + "	VALUES ('" + oProy.getProyecto_id() + "', '" + oProy.getEstado_id() + "', '" + oProy.getProyecto_responsable() + "', '" + oProy.getEstado_observacion() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar Verificacion
    public String ModificarReqVerificacion(cActividadRequerimiento cAct) {
        String result = "Error al modificar la actividad";
        String SQL = "UPDATE public.pac\n"
                + "	SET pac_verificacion='" + cAct.getReq_verificacion() + "'\n"
                + "	WHERE pac_req='" + cAct.getReq_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Numero de requerimientos
    public Integer numRequerimiento(Integer proyecto) {
        Integer result = null;
        String SQL = "select count(req_id) from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente\n"
                + "join requerimiento on actividad_id=req_actividad join pac on req_id=pac_req where proyecto_id='" + proyecto + "' and req_estado=1 and actividad_estado=1 and componente_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("count");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Numero de requerimientos verificados
    public Integer numRequerimientosVerificados(Integer proyecto) {
        Integer result = null;
        String SQL = "select count(req_id) from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente\n"
                + "join requerimiento on actividad_id=req_actividad join pac on req_id=pac_req where proyecto_id='" + proyecto + "' and req_estado=1 and actividad_estado=1 and componente_estado=1 and pac_verificacion=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("count");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Enviar proyecto
    public String EnviarProyecto(cProyecto oProy) {
        String result = "Error al ingresar acciones";
        String SQL = "INSERT INTO public.proyecto_estado(\n"
                + "	pe_estado, pe_proyecto, pe_usuario, pe_fecha)\n"
                + "	VALUES ('" + oProy.getEstado_id() + "', '" + oProy.getProyecto_id() + "', '" + oProy.getUsuario_nombre() + "', now());";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Verifica si existe o no registros con verificacion de compras publicas
    public Boolean numRegistrosCompras(Integer proyecto) {
        Boolean result = null;
        String SQL = "select exists(select * from proyecto_estado where pe_proyecto=? and pe_estado=9) as verifica";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proyecto) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("verifica");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Ingresar Priorizacion en actividad
    public String IngresarPrioActividad(Integer actividad, Integer prioridad) {
        String result = "Error al ingresar el proyecto";
        String SQL = "SELECT public.f_priorizar_actividad('" + actividad + "', '" + prioridad + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_priorizar_actividad");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Priorizar requerimiento
    public String PriorizarRequerimiento(Integer req, Integer prioridad) {
        String result = "Error al ingresar prioridad";
        String SQL;
        SQL = "SELECT public.f_priorizar_requerimiento('" + req + "', '" + prioridad + "');";

        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_priorizar_requerimiento");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Priorizar requerimiento
    public String PriorizarActividadP(Integer actividad, Integer prioridad) {
        String result = "Error al ingresar prioridad";
        String SQL;
        SQL = "UPDATE actividad SET actividad_prioridad='" + prioridad + "', req_prioridad_fecha=now() WHERE actividad_id='" + actividad + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Lista para reporte excel
    public List<cActividadRequerimiento> ListarRequerimientosExcel() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vSalvaguardar;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_estado(rsComp.getInt("reqestado"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReq_prioridad_fecha(rsComp.getDate("reqprioridad_fecha"));
                        cComp.setReq_reforma(rsComp.getInt("reqreforma"));
                        cComp.setReq_req_id(rsComp.getInt("reqid"));
                        cComp.setReq_anio(rsComp.getInt("reqanio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        cComp.setActividad_monto(rsComp.getDouble("verificados"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("verificadosu"));
                        cComp.setC1(rsComp.getDouble("verificadosnpac"));
                        cComp.setSolestado_observacion(rsComp.getString("estado"));
                        cComp.setSolicitud_cargo(rsComp.getString("estadouni"));
                        cComp.setReq_salvaguardar(rsComp.getBoolean("reqsalv"));
                        cComp.setReq_reforma2(rsComp.getInt("reqreforma2"));
                        cComp.setReq_incremento(rsComp.getDouble("reqincremento"));
                        cComp.setPresupuesto_reforma(rsComp.getInt("prereforma"));
                        cComp.setSolicitud_codigo(rsComp.getString("justificativo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("justificativouni"));
                        cComp.setSolicitud_nombre(rsComp.getString("justificativonpac"));
                        cComp.setCp_valor(rsComp.getDouble("valorcertificacion"));
                        cComp.setCp_tipo(rsComp.getString("tipocert"));
                        cComp.setAe_observacion(rsComp.getString("codigocp"));
                        if (rsComp.getInt("reqiva") == 1) {
                            BigDecimal bd = new BigDecimal(rsComp.getDouble("verificados") * 1.12);
                            bd = bd.setScale(2, RoundingMode.HALF_UP);
                            BigDecimal bd2 = new BigDecimal(rsComp.getDouble("verificadosu") * 1.12);
                            bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
                            BigDecimal bd3 = new BigDecimal(rsComp.getDouble("verificadosnpac") * 1.12);
                            bd3 = bd3.setScale(2, RoundingMode.HALF_UP);
                            cComp.setVerificado_iva(bd.doubleValue());
                            cComp.setVerificado_uni_iva(bd2.doubleValue());
                            if (rsComp.getDouble("sp_total") > 0) {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                            } else {
                                cComp.setVerificado_uni_npac(bd3.doubleValue());
                            }
                        } else {
                            cComp.setVerificado_iva(rsComp.getDouble("verificados"));
                            cComp.setVerificado_uni_iva(rsComp.getDouble("verificadosu"));
                            if (rsComp.getDouble("sp_total") > 0) {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                            } else {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("verificadosnpac"));
                            }
                        }
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel
    public List<cActividadRequerimiento> ListarRequerimientosExcel21() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vSalvaguardar21;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_estado(rsComp.getInt("reqestado"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReq_prioridad_fecha(rsComp.getDate("reqprioridad_fecha"));
                        cComp.setReq_reforma(rsComp.getInt("reqreforma"));
                        cComp.setReq_req_id(rsComp.getInt("reqid"));
                        cComp.setReq_anio(rsComp.getInt("reqanio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        cComp.setActividad_eval(ListarFechasRequerimientos(rsComp.getInt("req_reqid")));
                        cComp.setMes(ListarCertificacionespac(rsComp.getInt("req_reqid")));
                        cComp.setReq(ListarCertificacionesnopac(rsComp.getInt("req_reqid")));
                        cComp.setFechanopac(ListarFechasRequerimientosNP(rsComp.getInt("req_reqid")));
                        cComp.setActividad_monto(rsComp.getDouble("verificados"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("verificadosu"));
                        cComp.setC1(rsComp.getDouble("verificadosnpac"));
                        cComp.setSolestado_observacion(rsComp.getString("estado"));
                        cComp.setSolicitud_cargo(rsComp.getString("estadouni"));
                        cComp.setReq_salvaguardar(rsComp.getBoolean("reqsalv"));
                        cComp.setReq_reforma2(rsComp.getInt("reqreforma2"));
                        if (rsComp.getInt("reqreforma") > rsComp.getInt("reqreforma2")) {
                            cComp.setReq_incremento(0.0);
                        } else {
                            cComp.setReq_incremento(rsComp.getDouble("reqincremento"));
                        }
                        cComp.setPresupuesto_reforma(rsComp.getInt("prereforma"));
                        cComp.setSolicitud_codigo(rsComp.getString("justificativo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("justificativouni"));
                        cComp.setSolicitud_nombre(rsComp.getString("justificativonpac"));
                        cComp.setCp_valor(rsComp.getDouble("valorcertificacion"));
                        cComp.setCp_tipo(rsComp.getString("tipocert"));
                        cComp.setAe_observacion(rsComp.getString("codigocp"));
                        cComp.setAp_id(rsComp.getInt("apid"));
                        cComp.setObjetivo_nombre(rsComp.getString("apr_nombre"));
                        if (rsComp.getInt("reqiva") == 1) {
                            BigDecimal bd = new BigDecimal(rsComp.getDouble("verificados") * 1.12);
                            bd = bd.setScale(2, RoundingMode.HALF_UP);
                            BigDecimal bd2 = new BigDecimal(rsComp.getDouble("verificadosu") * 1.12);
                            bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
                            BigDecimal bd3 = new BigDecimal(rsComp.getDouble("verificadosnpac") * 1.12);
                            bd3 = bd3.setScale(2, RoundingMode.HALF_UP);
                            cComp.setVerificado_iva(bd.doubleValue());
                            cComp.setVerificado_uni_iva(bd2.doubleValue());
                            if (rsComp.getDouble("sp_total") > 0) {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                            } else {
                                cComp.setVerificado_uni_npac(bd3.doubleValue());
                            }
                        } else {
                            cComp.setVerificado_iva(rsComp.getDouble("verificados"));
                            cComp.setVerificado_uni_iva(rsComp.getDouble("verificadosu"));
                            if (rsComp.getDouble("sp_total") > 0) {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                            } else {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("verificadosnpac"));
                            }
                        }
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel
    public List<cActividadRequerimiento> ListarRequerimientosExcel22() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vSalvaguardar22;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_estado(rsComp.getInt("reqestado"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReq_prioridad_fecha(rsComp.getDate("reqprioridad_fecha"));
                        cComp.setReq_reforma(rsComp.getInt("reqreforma"));
                        cComp.setReq_req_id(rsComp.getInt("reqid"));
                        cComp.setReq_anio(rsComp.getInt("reqanio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarFechasRequerimientos(rsComp.getInt("req_reqid")));
                        //cComp.setMes(ListarCertificacionespac(rsComp.getInt("req_reqid")));
                        //cComp.setReq(ListarCertificacionesnopac(rsComp.getInt("req_reqid")));
                        //cComp.setFechanopac(ListarFechasRequerimientosNP(rsComp.getInt("req_reqid")));

                        cComp.setActividad_monto(rsComp.getDouble("verificados"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("verificadosu"));
                        cComp.setC1(rsComp.getDouble("verificadosnpac"));
                        cComp.setSolestado_observacion(rsComp.getString("estado"));
                        cComp.setSolicitud_cargo(rsComp.getString("estadouni"));
                        cComp.setReq_salvaguardar(rsComp.getBoolean("reqsalv"));
                        cComp.setReq_reforma2(rsComp.getInt("reqreforma2"));
                        if (rsComp.getInt("reqreforma") > rsComp.getInt("reqreforma2")) {
                            cComp.setReq_incremento(0.0);
                        } else {
                            cComp.setReq_incremento(rsComp.getDouble("reqincremento"));
                        }
                        cComp.setPresupuesto_reforma(rsComp.getInt("prereforma"));
                        cComp.setSolicitud_codigo(rsComp.getString("justificativo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("justificativouni"));
                        cComp.setSolicitud_nombre(rsComp.getString("justificativonpac"));
                        cComp.setCp_valor(rsComp.getDouble("valorcertificacion"));
                        cComp.setCp_tipo(rsComp.getString("tipocert"));
                        cComp.setAe_observacion(rsComp.getString("codigocp"));
                        cComp.setAp_id(rsComp.getInt("apid"));
                        cComp.setObjetivo_nombre(rsComp.getString("apr_nombre"));
                        cComp.setVerificado_iva(rsComp.getDouble("verificados"));
                        if (rsComp.getDouble("sp_total") > 0) {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                        } else {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("verificadosnpac"));
                        }
                        if (rsComp.getInt("reqiva") == 1) {
                            BigDecimal bd2 = new BigDecimal(rsComp.getDouble("verificadosu") * 1.12);
                            bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
                            cComp.setVerificado_uni_iva(bd2.doubleValue());
                        } else {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("verificadosu"));
                        }
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel
    public List<cActividadRequerimiento> ListarRequerimientosExcel23() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vSalvaguardar23;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_estado(rsComp.getInt("reqestado"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReq_prioridad_fecha(rsComp.getDate("reqprioridad_fecha"));
                        cComp.setReq_reforma(rsComp.getInt("reqreforma"));
                        cComp.setReq_req_id(rsComp.getInt("reqid"));
                        cComp.setReq_anio(rsComp.getInt("reqanio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarFechasRequerimientos(rsComp.getInt("req_reqid")));
                        //cComp.setMes(ListarCertificacionespac(rsComp.getInt("req_reqid")));
                        //cComp.setReq(ListarCertificacionesnopac(rsComp.getInt("req_reqid")));
                        //cComp.setFechanopac(ListarFechasRequerimientosNP(rsComp.getInt("req_reqid")));

                        cComp.setActividad_monto(rsComp.getDouble("verificados"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("verificadosu"));
                        cComp.setC1(rsComp.getDouble("verificadosnpac"));
                        cComp.setSolestado_observacion(rsComp.getString("estado"));
                        cComp.setSolicitud_cargo(rsComp.getString("estadouni"));
                        cComp.setReq_salvaguardar(rsComp.getBoolean("reqsalv"));
                        cComp.setReq_reforma2(rsComp.getInt("reqreforma2"));
                        if (rsComp.getInt("reqreforma") > rsComp.getInt("reqreforma2")) {
                            cComp.setReq_incremento(0.0);
                        } else {
                            cComp.setReq_incremento(rsComp.getDouble("reqincremento"));
                        }
                        cComp.setPresupuesto_reforma(rsComp.getInt("prereforma"));
                        cComp.setSolicitud_codigo(rsComp.getString("justificativo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("justificativouni"));
                        cComp.setSolicitud_nombre(rsComp.getString("justificativonpac"));
                        cComp.setCp_valor(rsComp.getDouble("valorcertificacion"));
                        cComp.setCp_tipo(rsComp.getString("tipocert"));
                        cComp.setAe_observacion(rsComp.getString("codigocp"));
                        //cComp.setAp_id(rsComp.getInt("apid"));
                        cComp.setObjetivo_nombre(rsComp.getString("apr_nombre"));
                        cComp.setVerificado_iva(rsComp.getDouble("verificados"));
                        if (rsComp.getDouble("sp_total") > 0) {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                        } else {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("verificadosnpac"));
                        }
                        if (rsComp.getInt("reqiva") == 1) {
                            BigDecimal bd2 = new BigDecimal(rsComp.getDouble("verificadosu") * 1.12);
                            bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
                            cComp.setVerificado_uni_iva(bd2.doubleValue());
                        } else {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("verificadosu"));
                        }
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

//Lista para reporte excel
    public List<cActividadRequerimiento> ListarRequerimientosExcel24() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vSalvaguardar24;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_estado(rsComp.getInt("reqestado"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReq_prioridad_fecha(rsComp.getDate("reqprioridad_fecha"));
                        cComp.setReq_reforma(rsComp.getInt("reqreforma"));
                        cComp.setReq_req_id(rsComp.getInt("reqid"));
                        cComp.setReq_anio(rsComp.getInt("reqanio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarFechasRequerimientos(rsComp.getInt("req_reqid")));
                        //cComp.setMes(ListarCertificacionespac(rsComp.getInt("req_reqid")));
                        //cComp.setReq(ListarCertificacionesnopac(rsComp.getInt("req_reqid")));
                        //cComp.setFechanopac(ListarFechasRequerimientosNP(rsComp.getInt("req_reqid")));

                        cComp.setActividad_monto(rsComp.getDouble("verificados"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("verificadosu"));
                        cComp.setC1(rsComp.getDouble("verificadosnpac"));
                        cComp.setSolestado_observacion(rsComp.getString("estado"));
                        cComp.setSolicitud_cargo(rsComp.getString("estadouni"));
                        cComp.setReq_salvaguardar(rsComp.getBoolean("reqsalv"));
                        cComp.setReq_reforma2(rsComp.getInt("reqreforma2"));
                        if (rsComp.getInt("reqreforma") > rsComp.getInt("reqreforma2")) {
                            cComp.setReq_incremento(0.0);
                        } else {
                            cComp.setReq_incremento(rsComp.getDouble("reqincremento"));
                        }
                        cComp.setPresupuesto_reforma(rsComp.getInt("prereforma"));
                        cComp.setSolicitud_codigo(rsComp.getString("justificativo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("justificativouni"));
                        cComp.setSolicitud_nombre(rsComp.getString("justificativonpac"));
                        cComp.setCp_valor(rsComp.getDouble("valorcertificacion"));
                        cComp.setCp_tipo(rsComp.getString("tipocert"));
                        cComp.setAe_observacion(rsComp.getString("codigocp"));
                        //cComp.setAp_id(rsComp.getInt("apid"));
                        cComp.setObjetivo_nombre(rsComp.getString("apr_nombre"));
                        cComp.setVerificado_iva(rsComp.getDouble("verificados"));
                        if (rsComp.getDouble("sp_total") > 0) {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                        } else {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("verificadosnpac"));
                        }
                        if (rsComp.getInt("reqiva") == 1) {
                            BigDecimal bd2 = new BigDecimal(rsComp.getDouble("verificadosu") * 1.12);
                            bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
                            cComp.setVerificado_uni_iva(bd2.doubleValue());
                        } else {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("verificadosu"));
                        }
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte fechas justificativos
    public List<cActividadRequerimiento> ListarFechasRequerimientos(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarFechas('" + req + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setFecha_inicio(rsComp.getString("fechaenvio"));
                        cComp.setFecha_fin(rsComp.getString("fechaaprobc"));
                        cComp.setReq_nombre(rsComp.getString("fecharecibidoc"));
                        cComp.setReq_descripcion(rsComp.getString("fechaplanificacion"));
                        cComp.setReq_cpc(rsComp.getString("fechafinanciero"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte fechas stp
    public List<cActividadRequerimiento> ListarFechasRequerimientosNP(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarfechasnopac('" + req + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setFecha_inicio(rsComp.getString("fechaenvio"));
                        cComp.setReq_descripcion(rsComp.getString("fechaplanificacion"));
                        cComp.setReq_cpc(rsComp.getString("fechafinanciero"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte certificaciones pac
    public List<cActividadRequerimiento> ListarCertificacionespac(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_certificacion('" + req + "', 1);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_codigo(rsComp.getString("codigoc"));
                        cComp.setSolicitud_nombre(rsComp.getString("tipoc"));
                        cComp.setActividad_monto(rsComp.getDouble("montocert"));
                        cComp.setFecha_inicio(rsComp.getString("fechaaprobcer"));
                        cComp.setTc_nombre(rsComp.getString("tipodeb"));
                        cComp.setActividad_nombre(rsComp.getString("codigodeb"));
                        cComp.setAe_porcentaje(rsComp.getDouble("montodeb"));
                        cComp.setFecha_fin(rsComp.getString("fechaaprodeb"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte certificaciones no pac
    public List<cActividadRequerimiento> ListarCertificacionesnopac(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_certificacion('" + req + "', 2);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_codigo(rsComp.getString("codigoc"));
                        cComp.setSolicitud_nombre(rsComp.getString("tipoc"));
                        cComp.setActividad_monto(rsComp.getDouble("montocert"));
                        cComp.setFecha_inicio(rsComp.getString("fechaaprobcer"));
                        cComp.setTc_nombre(rsComp.getString("tipodeb"));
                        cComp.setActividad_nombre(rsComp.getString("codigodeb"));
                        cComp.setAe_porcentaje(rsComp.getDouble("montodeb"));
                        cComp.setFecha_fin(rsComp.getString("fechaaprodeb"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte deudas
    public List<cActividadRequerimiento> ListarRequerimientosExcelDeudas2(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listardeudas('" + anio + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudasid"));
                        cComp.setReq_nombre(rsComp.getString("deudasnombre"));
                        cComp.setReq_descripcion(rsComp.getString("deudasnombre"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudasmonto"));
                        cComp.setActividad_nombre("____");
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudasoei"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setReq_reforma(rsComp.getInt("deudareforma"));
                        cComp.setActividad_responsable(rsComp.getString("tipo"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte deudas
    public List<cActividadRequerimiento> ListarRequerimientosExcelDeudas21(Integer ag, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listardeudas('" + anio + "') where agid='" + ag + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudasid"));
                        cComp.setReq_nombre(rsComp.getString("deudasnombre"));
                        cComp.setReq_descripcion(rsComp.getString("deudasnombre"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudasmonto"));
                        cComp.setActividad_nombre("____");
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudasoei"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setReq_reforma(rsComp.getInt("deudareforma"));
                        cComp.setActividad_responsable(rsComp.getString("tipo"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel
    public List<cActividadRequerimiento> ListarRequerimientosExcelCompras() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vSalvaguardar21 where paccpc is not null;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_estado(rsComp.getInt("reqestado"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReq_prioridad_fecha(rsComp.getDate("reqprioridad_fecha"));
                        cComp.setReq_reforma(rsComp.getInt("reqreforma"));
                        cComp.setReq_req_id(rsComp.getInt("reqid"));
                        cComp.setReq_anio(rsComp.getInt("reqanio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        cComp.setActividad_monto(rsComp.getDouble("verificados"));
                        cComp.setActividad_porcentaje(rsComp.getDouble("verificadosu"));
                        cComp.setC1(rsComp.getDouble("verificadosnpac"));
                        cComp.setSolestado_observacion(rsComp.getString("estado"));
                        cComp.setSolicitud_cargo(rsComp.getString("estadouni"));
                        cComp.setReq_salvaguardar(rsComp.getBoolean("reqsalv"));
                        cComp.setReq_reforma2(rsComp.getInt("reqreforma2"));
                        cComp.setReq_incremento(rsComp.getDouble("reqincremento"));
                        cComp.setPresupuesto_reforma(rsComp.getInt("prereforma"));
                        cComp.setSolicitud_codigo(rsComp.getString("justificativo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("justificativouni"));
                        cComp.setSolicitud_nombre(rsComp.getString("justificativonpac"));
                        cComp.setCp_valor(rsComp.getDouble("valorcertificacion"));
                        cComp.setCp_tipo(rsComp.getString("tipocert"));
                        cComp.setAe_observacion(rsComp.getString("codigocp"));
                        cComp.setAp_id(rsComp.getInt("apid"));
                        cComp.setObjetivo_nombre(rsComp.getString("apr_nombre"));
                        if (rsComp.getInt("reqiva") == 1) {
                            BigDecimal bd = new BigDecimal(rsComp.getDouble("verificados") * 1.12);
                            bd = bd.setScale(2, RoundingMode.HALF_UP);
                            BigDecimal bd2 = new BigDecimal(rsComp.getDouble("verificadosu") * 1.12);
                            bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
                            BigDecimal bd3 = new BigDecimal(rsComp.getDouble("verificadosnpac") * 1.12);
                            bd3 = bd3.setScale(2, RoundingMode.HALF_UP);
                            cComp.setVerificado_iva(bd.doubleValue());
                            cComp.setVerificado_uni_iva(bd2.doubleValue());
                            if (rsComp.getDouble("sp_total") > 0) {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                            } else {
                                cComp.setVerificado_uni_npac(bd3.doubleValue());
                            }
                        } else {
                            cComp.setVerificado_iva(rsComp.getDouble("verificados"));
                            cComp.setVerificado_uni_iva(rsComp.getDouble("verificadosu"));
                            if (rsComp.getDouble("sp_total") > 0) {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("sp_total"));
                            } else {
                                cComp.setVerificado_uni_npac(rsComp.getDouble("verificadosnpac"));
                            }
                        }
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Observacion solicitud
    public String observacionJustificativoEstado(Integer cC) {
        String result = "Sin acción";
        String SQL = "select solestado_observacion from vReqJustif where solreq_requerimiento='" + cC + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("solestado_observacion");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Observacion solicitud unificados
    public String observacionJustificativoUnifEstado(Integer cC) {
        String result = "Enviado a la unidad unificadora";
        String SQL = "select soluniestado_observacion from vReqJustifUnif where unificado_pac='" + cC + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("soluniestado_observacion");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Lista para reporte excel unidades
    public List<cActividadRequerimiento> ListarRequerimientosExcelUnidad(Integer ag, Integer agp, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL;
        if (anio == 2020) {
            SQL = "select * from f_listarequerimientosexcel() where agid='" + ag + "' or agidp='" + agp + "';";
        } else if (anio == 2021) {
            SQL = "select * from f_listarequerimientosexcel21() where agid='" + ag + "' or agidp='" + agp + "';";
        } else if (anio == 2022) {
            SQL = "select * from f_listarequerimientosexcel22() where agid='" + ag + "' or agidp='" + agp + "';";
        } else if(anio==2023){
            SQL = "select * from f_listarequerimientosexcel23() where agid='" + ag + "' or agidp='" + agp + "';";
        } else {
            SQL = "select * from f_listarequerimientosexcel24() where agid='" + ag + "' or agidp='" + agp + "';";
        }
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("req_reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_estado(rsComp.getInt("reqestado"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReq_prioridad_fecha(rsComp.getDate("reqprioridad_fecha"));
                        cComp.setReq_reforma(rsComp.getInt("reqreforma"));
                        cComp.setReq_req_id(rsComp.getInt("reqid"));
                        cComp.setReq_anio(rsComp.getInt("reqanio"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarCuatrimestre(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel deudas
    public List<cActividadRequerimiento> ListarRequerimientosExcelDeudas(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select sum(soldeudasl_costo_total) from sol_deudas_lista where soldeudasl_deudas=vdeudasestr.deudas_id and deudas_anio='" + anio + "') as monto,\n"
                + "                (select sum(soldeudasl_costo_iva) from sol_deudas_lista where soldeudasl_deudas=vdeudasestr.deudas_id and deudas_anio='" + anio + "') as montoiva,\n"
                + "                (select sum(soldeudasl_costo_anticipo) from sol_deudas_lista where soldeudasl_deudas=vdeudasestr.deudas_id and deudas_anio='" + anio + "') as montoanticipo,\n"
                + "                 ( SELECT string_agg(soldeudas_codigo::text, ','::text) AS solicitudcodigo\n"
                + "                           FROM ( select * from solicitud_deudas inner join sol_deudas_lista on soldeudas_id=soldeudasl_solicitud\n"
                + "                                 WHERE soldeudasl_deudas = vdeudasestr.deudas_id and deudas_anio='" + anio + "'\n"
                + "                                 ORDER BY soldeudas_codigo) jus) AS solicitudcodigo\n"
                + "                 from vdeudasestr where deudas_anio='" + anio + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudas_id"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_descripcion(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudamonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudamonto"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudamonto"));
                        cComp.setProyecto_nombre(rsComp.getString("deudas_proyecto"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("deudaunidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("deudaprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("deudasubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("deudaproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("deudaactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("deudaobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("deudageografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("deudarengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("deudafuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("deudaorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("deudacorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("deudas_presupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("deudaejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("deudaentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("deudaunidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("deudarenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamiento_nombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudas_oei"));
                        cComp.setAg_alias(rsComp.getString("agnombrepadre"));
                        cComp.setDeu_iva(rsComp.getInt("deudaiva"));
                        cComp.setReq_reforma(rsComp.getInt("deudas_reforma"));
                        cComp.setVerificado_iva(rsComp.getDouble("monto"));
                        cComp.setVerificado_uni_iva(rsComp.getDouble("montoiva"));
                        cComp.setVerificado_uni_npac(rsComp.getDouble("montoanticipo"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        //cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarCuatrimestre(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel deudas
    public List<cActividadRequerimiento> ListarRequerimientosExcelDeudas21() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (SELECT public.f_montocertificaciondeb(deudasid, tipoid)) as montocert, (select tipoc from f_certificaciontipodeb(deudasid, tipoid) as tipoc), (select codigoc from f_certificaciontipodeb(deudasid, tipoid) as codigoc) from vdeudasmatriz;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudasid"));
                        cComp.setReq_nombre(rsComp.getString("deudasnombre"));
                        cComp.setReq_descripcion(rsComp.getString("deudasnombre"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudasmonto"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudasoei"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setReq_reforma(rsComp.getInt("deudareforma"));
                        if (rsComp.getDouble("montocert") > 0) {
                            cComp.setVerificado_iva(rsComp.getDouble("montocert"));
                        } else {
                            cComp.setVerificado_iva(rsComp.getDouble("monto"));
                        }

                        if (rsComp.getDouble("montocert") > 0) {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("montocert"));
                        } else {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("montoiva"));
                        }

                        if (rsComp.getDouble("montocert") > 0) {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("montocert"));
                        } else {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("montoanticipo"));
                        }

                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        cComp.setDeu_iva(rsComp.getInt("tipoid"));
                        cComp.setUsuario_titulo(rsComp.getString("tipo"));
                        cComp.setTc_nombre(rsComp.getString("tipoc"));
                        cComp.setSolicitud_nombre(rsComp.getString("codigoc"));
                        //cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarCuatrimestre(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel deudas
    public List<cActividadRequerimiento> ListarRequerimientosExcelDeudas22(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL;
        if (anio == 2022) {
            SQL = "select *, (SELECT public.f_montocertificaciondeb(deudasid, tipoid)) as montocert, (select tipoc from f_certificaciontipodeb(deudasid, tipoid) as tipoc), (select codigoc from f_certificaciontipodeb(deudasid, tipoid) as codigoc) from vdeudasmatriz22;";
        }else if (anio == 2023) {
            SQL = "select *, (SELECT public.f_montocertificaciondeb(deudasid, tipoid)) as montocert, (select tipoc from f_certificaciontipodeb(deudasid, tipoid) as tipoc), (select codigoc from f_certificaciontipodeb(deudasid, tipoid) as codigoc) from vdeudasmatriz23;";
        }else{
            SQL = "select *, (SELECT public.f_montocertificaciondeb(deudasid, tipoid)) as montocert, (select tipoc from f_certificaciontipodeb(deudasid, tipoid) as tipoc), (select codigoc from f_certificaciontipodeb(deudasid, tipoid) as codigoc) from vdeudasmatriz24;";
        }
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudasid"));
                        cComp.setReq_nombre(rsComp.getString("deudasnombre"));
                        cComp.setReq_descripcion(rsComp.getString("deudasnombre"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudasmonto"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudasoei"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setReq_reforma(rsComp.getInt("deudareforma"));
                        if (rsComp.getDouble("montocert") > 0) {
                            cComp.setVerificado_iva(rsComp.getDouble("montocert"));
                        } else if (rsComp.getDouble("montocert") == 0 && rsComp.getString("codigoc") != null) {
                            cComp.setVerificado_iva(rsComp.getDouble("montocert"));
                        } else {
                            cComp.setVerificado_iva(rsComp.getDouble("monto"));
                        }

                        if (rsComp.getDouble("montocert") > 0) {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("montocert"));
                        } else if (rsComp.getDouble("montocert") == 0 && rsComp.getString("codigoc") != null) {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("montocert"));
                        } else {
                            cComp.setVerificado_uni_iva(rsComp.getDouble("montoiva"));
                        }

                        if (rsComp.getDouble("montocert") > 0) {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("montocert"));
                        } else {
                            cComp.setVerificado_uni_npac(rsComp.getDouble("montoanticipo"));
                        }

                        cComp.setSolicitud_nombre(rsComp.getString("codigoc"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        cComp.setDeu_iva(rsComp.getInt("tipoid"));
                        cComp.setUsuario_titulo(rsComp.getString("tipo"));
                        cComp.setTc_nombre(rsComp.getString("tipoc"));
                        //cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarCuatrimestre(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel comprometidos
    public List<cActividadRequerimiento> ListarRequerimientosExcelComprometidos(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = " select *, (select sum(soldeudasl_costo_total) from sol_deudas_lista where soldeudasl_deudas=f_listarcomprometidos.deudasid) as monto,\n"
                + "(select sum(soldeudasl_costo_iva) from sol_deudas_lista where soldeudasl_deudas=f_listarcomprometidos.deudasid) as montoiva,\n"
                + "(select sum(soldeudasl_costo_anticipo) from sol_deudas_lista where soldeudasl_deudas=f_listarcomprometidos.deudasid) as montoanticipo,\n"
                + " ( SELECT string_agg(soldeudas_codigo::text, ','::text) AS solicitudcodigo\n"
                + "           FROM ( select * from solicitud_deudas inner join sol_deudas_lista on soldeudas_id=soldeudasl_solicitud\n"
                + "                  WHERE soldeudasl_deudas = f_listarcomprometidos.deudasid\n"
                + "                  ORDER BY soldeudas_codigo) jus) AS solicitudcodigo\n"
                + "from f_listarcomprometidos('" + anio + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudasid"));
                        cComp.setReq_nombre(rsComp.getString("deudasproceso"));
                        cComp.setReq_descripcion(rsComp.getString("deudasproceso"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudasmonto"));
                        cComp.setProyecto_nombre(rsComp.getString("deudasproyecto"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudasoei"));
                        cComp.setAg_alias(rsComp.getString("agnombrepadre"));
                        cComp.setReq_reforma(rsComp.getInt("deudareforma"));
                        cComp.setVerificado_iva(rsComp.getDouble("monto"));
                        cComp.setVerificado_uni_iva(rsComp.getDouble("montoiva"));
                        cComp.setVerificado_uni_npac(rsComp.getDouble("montoanticipo"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel deudas por unidades
    public List<cActividadRequerimiento> ListarRequerimientosExcelDeudasUnidades(Integer ag) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vdeudasestr where ag_id='" + ag + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudas_id"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_descripcion(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudamonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudamonto"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudamonto"));
                        cComp.setProyecto_nombre(rsComp.getString("deudas_proyecto"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("deudaunidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("deudaprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("deudasubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("deudaproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("deudaactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("deudaobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("deudageografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("deudarengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("deudafuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("deudaorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("deudacorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("deudas_presupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("deudaejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("deudaentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("deudaunidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("deudarenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamiento_nombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudas_oei"));
                        cComp.setAg_alias(rsComp.getString("agnombrepadre"));
                        cComp.setDeu_iva(rsComp.getInt("deudaiva"));
                        //cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarCuatrimestre(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel deudas
    public List<cActividadRequerimiento> ListarRequerimientosExcelCompr() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vdeudasc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("deudas_id"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_descripcion(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_cantidad(1.0);
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudas_monto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudas_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudas_anticipo"));
                        cComp.setProyecto_nombre(rsComp.getString("deudas_proyecto"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("de_unidad_desc"));
                        cComp.setPresupuesto_programa(rsComp.getString("de_programa"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("de_subprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("de_proyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("de_actividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("de_obra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("de_geografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("de_renglo_aux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("de_fuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("de_organismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("de_correlativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("deudas_presupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("de_ejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("de_entidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("de_unidad_ejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("de_renglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamiento_nombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudas_oei"));
                        cComp.setAg_alias(rsComp.getString("agnombrepadre"));
                        //cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        //cComp.setActividad_eval(ListarCuatrimestre(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Ingresar Presupuesto
    public Integer IngresarPresupuesto(cActividadRequerimiento cComp) {
        Integer result = 0;
        String SQL = "SELECT public.f_ingresar_presupuesto_pres('" + cComp.getReq_nombre() + "', '" + cComp.getReq_descripcion() + "', "
                + "'" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', "
                + "'" + cComp.getReq_costo_total() + "', '" + cComp.getFinanciamiento_nombre() + "', "
                + "'" + cComp.getReq_id() + "', '" + cComp.getPresupuesto_ejercicio() + "', '" + cComp.getPresupuesto_entidad() + "', "
                + "'" + cComp.getPresupuesto_unidad_ejec() + "', '" + cComp.getPresupuesto_unidad_desc() + "', '" + cComp.getPresupuesto_programa() + "',"
                + "'" + cComp.getPresupuesto_subprograma() + "', '" + cComp.getPresupuesto_proyecto() + "', '" + cComp.getPresupuesto_actividad() + "', "
                + "'" + cComp.getPresupuesto_obra() + "', '" + cComp.getPresupuesto_renglo_aux() + "', '" + cComp.getPresupuesto_fuente() + "', "
                + "'" + cComp.getPresupuesto_presupuesto() + "', '" + cComp.getPresupuesto_renglo() + "', '" + cComp.getPresupuesto_geografico() + "', '"+cComp.getPresupuesto_organismo()+"', '"+cComp.getPresupuesto_correlativo()+"')";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("f_ingresar_presupuesto_pres");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar estructura reforma
    public String ModReformaEstructura(cActividadRequerimiento cComp) {
        String result = null;
        String SQL = "SELECT public.f_ingresar_presupuesto_reforma('" + cComp.getReq_id() + "', '" + cComp.getPresupuesto_id() + "','" + cComp.getPresupuesto_ejercicio() + "', '" + cComp.getPresupuesto_entidad() + "', "
                + "'" + cComp.getPresupuesto_unidad_ejec() + "', '" + cComp.getPresupuesto_unidad_desc() + "', '" + cComp.getPresupuesto_programa() + "',"
                + "'" + cComp.getPresupuesto_subprograma() + "', '" + cComp.getPresupuesto_proyecto() + "', '" + cComp.getPresupuesto_actividad() + "', "
                + "'" + cComp.getPresupuesto_obra() + "', '" + cComp.getPresupuesto_renglo_aux() + "', '" + cComp.getPresupuesto_fuente() + "', "
                + "'" + cComp.getPresupuesto_presupuesto() + "', '" + cComp.getPresupuesto_renglo() + "', '" + cComp.getPresupuesto_geografico() + "', '" + cComp.getPresupuesto_organismo() + "', '" + cComp.getPresupuesto_correlativo() + "')";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_ingresar_presupuesto_reforma");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar Presupuesto Deudas
    public Integer IngresarPresupuestoDeudas(cActividadRequerimiento cComp) {
        Integer result = 0;
        String SQL = "SELECT public.f_ingresar_presupuesto_deudas('" + cComp.getReq_id() + "', '" + cComp.getPresupuesto_ejercicio() + "', '" + cComp.getPresupuesto_entidad() + "', "
                + "'" + cComp.getPresupuesto_unidad_ejec() + "', '" + cComp.getPresupuesto_unidad_desc() + "', '" + cComp.getPresupuesto_programa() + "',"
                + "'" + cComp.getPresupuesto_subprograma() + "', '" + cComp.getPresupuesto_proyecto() + "', '" + cComp.getPresupuesto_actividad() + "', "
                + "'" + cComp.getPresupuesto_obra() + "', '" + cComp.getPresupuesto_renglo_aux() + "', '" + cComp.getPresupuesto_fuente() + "', '" + cComp.getPresupuesto_renglo() + "')";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("f_ingresar_presupuesto_deudas");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar comprometidos
    public String IngresaComprometidos(cActividadRequerimiento oProy, Integer anio) {
        String result = "Error al ingresar comprometidos";
        String SQL = "INSERT INTO public.deudas(\n"
                + "	deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_contrato, deudas_tcon, deudas_financiamiento, "
                + "     deudas_monto, deudas_ag, deudas_presupuesto, deudas_tipo, deudas_anticipo, deudas_monto_pendiente, deudas_iva, deudas_anio)\n"
                + "	VALUES ('" + oProy.getReq_id() + "', '" + oProy.getPerspectiva_id() + "', '" + oProy.getProyecto_nombre() + "', '" + oProy.getReq_nombre() + "', '" + oProy.getActividad_nombre() + "', 20, 1, "
                + "     '" + oProy.getReq_costo_unitario() + "', '" + oProy.getAg_id() + "', '" + oProy.getPresupuesto_presupuesto() + "', 2, '" + oProy.getReq_costo_sin_iva() + "', '" + oProy.getReq_costo_total() + "', '" + oProy.getReq_cantidad() + "','" + anio + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar comprometidos estructura
    public String IngresaComprometidosestructura(cActividadRequerimiento oProy) {
        String result = "Error al ingresar comprometidos estructura";
        String SQL = "INSERT INTO public.deudas_estructura(\n"
                + "	de_deudas, de_certificacion, de_programa, de_subprograma, de_proyecto, "
                + "     de_actividad, de_renglo, de_geografico, de_fuente, de_organismo, "
                + "     de_correlativo, de_ejercicio, de_entidad, de_unidad_desc, de_unidad_ejec, "
                + "     de_obra, de_renglo_aux, de_iva)\n"
                + "	VALUES ('" + oProy.getReq_id() + "', 0, '" + oProy.getPresupuesto_programa() + "', '" + oProy.getPresupuesto_subprograma() + "', '" + oProy.getPresupuesto_proyecto() + "', "
                + "     '" + oProy.getPresupuesto_actividad() + "', '" + oProy.getPresupuesto_renglo() + "', '" + oProy.getPresupuesto_geografico() + "', '" + oProy.getPresupuesto_fuente() + "', '" + oProy.getPresupuesto_organismo() + "', "
                + "     '" + oProy.getPresupuesto_correlativo() + "', '" + oProy.getPresupuesto_ejercicio() + "', '" + oProy.getPresupuesto_entidad() + "', '" + oProy.getPresupuesto_unidad_desc() + "', '" + oProy.getPresupuesto_unidad_ejec() + "', "
                + "     '" + oProy.getPresupuesto_obra() + "', '" + oProy.getPresupuesto_renglo_aux() + "', '" + oProy.getTc_id() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Update comprometidos estructura
    public String UpdateComprometidosestructura(cActividadRequerimiento oProy) {
        String result = "Error al ingresar comprometidos estructura";
        String SQL = "Update deudas_estructura set \n"
                + "	de_programa='" + oProy.getPresupuesto_programa() + "', de_subprograma='" + oProy.getPresupuesto_subprograma() + "', de_proyecto='" + oProy.getPresupuesto_proyecto() + "', "
                + "     de_actividad='" + oProy.getPresupuesto_actividad() + "', de_renglo='" + oProy.getPresupuesto_renglo() + "', de_geografico='" + oProy.getPresupuesto_geografico() + "', de_fuente='" + oProy.getPresupuesto_fuente() + "', de_organismo='" + oProy.getPresupuesto_organismo() + "', "
                + "     de_correlativo='" + oProy.getPresupuesto_correlativo() + "', de_ejercicio='" + oProy.getPresupuesto_ejercicio() + "', de_entidad='" + oProy.getPresupuesto_entidad() + "', de_unidad_desc='" + oProy.getPresupuesto_unidad_desc() + "', de_unidad_ejec='" + oProy.getPresupuesto_unidad_ejec() + "', "
                + "     de_obra='" + oProy.getPresupuesto_obra() + "', de_renglo_aux='" + oProy.getPresupuesto_renglo_aux() + "'\n"
                + "     where de_deudas='" + oProy.getReq_id() + "' and de_iva='" + oProy.getTc_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Lista para reporte excel
    public List<cActividadRequerimiento> ListarRequerimientosExcelPacSolicitud(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarequerimientoCompras('" + sol + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_cpc(rsComp.getString("paccpc"));
                        cComp.setPac_catalogo(rsComp.getString("paccatalogo"));
                        cComp.setPac_tipo_producto(rsComp.getString("pac_tipoproducto"));
                        cComp.setPac_num_operacion(rsComp.getInt("pac_numoperacion"));
                        cComp.setPac_codigo_proyecto(rsComp.getInt("pac_codigoproyecto"));
                        cComp.setPac_tipo_regimen(rsComp.getString("pac_tiporegimen"));
                        cComp.setPac_procedimiento_sug(rsComp.getString("pac_procedimientosug"));
                        cComp.setPac_fondo_bid(rsComp.getString("pac_fondobid"));
                        cComp.setTc_nombre(rsComp.getString("tcnombre"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setPresupuesto_unidad_desc(rsComp.getString("presupuesto_unidaddesc"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_subprograma(rsComp.getString("presupuestosubprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_obra(rsComp.getString("presupuestoobra"));
                        cComp.setPresupuesto_geografico(rsComp.getString("presupuestogeografico"));
                        cComp.setPresupuesto_renglo_aux(rsComp.getString("presupuesto_rengloaux"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_organismo(rsComp.getString("presupuestoorganismo"));
                        cComp.setPresupuesto_correlativo(rsComp.getString("presupuestocorrelativo"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setPresupuesto_ejercicio(rsComp.getInt("presupuestoejercicio"));
                        cComp.setPresupuesto_entidad(rsComp.getInt("presupuestoentidad"));
                        cComp.setPresupuesto_unidad_ejec(rsComp.getInt("presupuesto_unidadejec"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("financiamientonombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setAg_alias(rsComp.getString("agnombre_p"));
                        cComp.setActividad_id(rsComp.getInt("actividadid"));
                        cComp.setReqestado_cantidad(rsComp.getDouble("reqestadocantidad"));
                        cComp.setReqestado_costo_unitario(rsComp.getDouble("reqestado_costounitario"));
                        cComp.setReqestado_costo_total(rsComp.getDouble("reqestado_costototal"));
                        cComp.setReqestado_iva(rsComp.getInt("reqestadoiva"));
                        cComp.setReqeje_nombre(rsComp.getString("reqejenombre"));
                        cComp.setReqeje_descripcion(rsComp.getString("reqejedescripcion"));
                        cComp.setReqeje_cantidad_anual(rsComp.getDouble("reqeje_cantidadanual"));
                        cComp.setReqeje_costo_unitario(rsComp.getDouble("reqeje_costounitario"));
                        cComp.setReqeje_costo_sin_iva(rsComp.getDouble("reqeje_costo_siniva"));
                        cComp.setReqeje_oei(rsComp.getInt("reqejeoei"));
                        cComp.setReqeje_iva(rsComp.getInt("reqejeiva"));
                        cComp.setReqeje_unidad(rsComp.getInt("reqejeunidad"));
                        cComp.setSolicitud_codigo(rsComp.getString("solunicodigo"));
                        cComp.setCuatri(ListarCuatrimestreReq(cComp));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel evaluacion
    public List<cProyecto> ListarEvaluacionExcel(Integer cuatrimestre, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "SELECT * from public.f_listarevaluacionproyectosgr('" + cuatrimestre + "', '" + anio + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setProyecto_acciones(rsComp.getString("agpadrenombre"));
                        cComp.setProyecto_nombre(rsComp.getString("agnombre"));
                        cComp.setProyecto_planificados(rsComp.getInt("nplanificados2"));
                        cComp.setProy_cuatrimestre(rsComp.getInt("ncuatrimestre2"));
                        cComp.setProy_enviados(rsComp.getInt("enviados2"));
                        cComp.setProy_evaluados(rsComp.getInt("evaluados2"));
                        cComp.setPe_eficacia(rsComp.getDouble("peeficacia2"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peeficiencia2"));
                        cComp.setPe_efectividad(rsComp.getDouble("peefectividad2"));
                        cComp.setPe_ejecucion(rsComp.getDouble("peejecucion2"));
                        cComp.setProyecto_monto(rsComp.getDouble("peplanificado"));
                        cComp.setDeudas_proceso(rsComp.getString("tiponombre"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Lista para reporte excel evaluacion 2
    public List<cActividadRequerimiento> ListarEvaluacionExcel2(Integer anio, Integer cuatrimestre) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL;
        if (cuatrimestre == 0) {
            SQL = "select * from f_listaproyectosevalcom('" + anio + "');";
        } else {
            SQL = "SELECT * from f_listaproyectoseval('" + anio + "', '" + cuatrimestre + "')";
        }
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setTc_nombre(rsComp.getString("oei"));
                        cComp.setObjetivo_nombre(rsComp.getString("objetivop"));
                        cComp.setAg_nombre(rsComp.getString("dependencia"));
                        cComp.setAg_alias(rsComp.getString("unidad"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setPe_eficacia(rsComp.getDouble("peficacia"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peficiencia"));
                        cComp.setPe_efectividad(rsComp.getDouble("pefectividad"));
                        cComp.setPe_ejecucion(rsComp.getDouble("pejecucion"));
                        cComp.setFinanciamiento_nombre(rsComp.getString("componentenombre"));
                        cComp.setLogro_nombre(rsComp.getString("logros"));
                        cComp.setNudo_nombre(rsComp.getString("nudos"));
                        cComp.setAre_archivo(rsComp.getString("metanombre"));
                        cComp.setReq_cantidad(rsComp.getDouble("tmeficacia"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("tmeficiencia"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("tmefectividad"));
                        cComp.setReq_costo_total(rsComp.getDouble("tmejecucion"));
                        cComp.setUnidad_nombre(rsComp.getString("indicadornombre"));
                        cComp.setActividad_nombre(rsComp.getString("actividadnombre"));
                        cComp.setAe_autoeval(rsComp.getDouble("aeficacia"));
                        cComp.setAe_eval(rsComp.getDouble("aeficiencia"));
                        cComp.setAe_evaluacion(rsComp.getDouble("aefectividad"));
                        cComp.setActividad_monto(rsComp.getDouble("actividadmonto"));
                        cComp.setAe_ejecucion(rsComp.getDouble("aejecucion"));
                        cComp.setAe_observacion(rsComp.getString("aobservacion"));
                        cComp.setSueldo_total(rsComp.getDouble("tmplanificado"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    //Codigo tipo producto
    public Integer idcodigocompra(String tprod) {
        Integer result = 0;
        String SQL = "select tc_id from tipo_compra where tc_nombre like '" + tprod + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("tc_id");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Codigo tipo producto
    public Integer ingresoDeudas(cActividadRequerimiento cReq) {
        Integer result = 0;
        String SQL = "select * from f_ingresar_obligaciones('" + cReq.getReq_nombre() + "', '" + cReq.getReq_costo_total() + "', '" + cReq.getFinanciamiento_nombre() + "', '" + cReq.getTc_nombre() + "', '" + cReq.getProyecto_nombre() + "', '" + cReq.getPresupuesto_presupuesto() + "', '" + cReq.getAg_nombre() + "')";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("f_ingresar_obligaciones");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //sI EXISTE O NO ESTRUCTURA INGRESADA
    public Boolean DeudasEstructura(Integer deuda, Integer iva) {
        Boolean result = false;
        String SQL = "select exists(select * from deudas_estructura where de_deudas='" + deuda + "' and de_iva='" + iva + "')";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("exists");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Codigo unidad
    public Integer idunidad(String unidad) {
        Integer result = 0;
        String SQL = "select unidad_id from unidad where unidad_nombre like '" + unidad + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("unidad_id");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Codigo area gestion
    public Integer idAg(String ag) {
        Integer result = 0;
        String SQL = "select ag_id from area_gestion where ag_nombre like '" + ag + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("ag_id");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Ingresar Reforma
    public Integer IngresarReqReforma(cActividadRequerimiento cComp) {
        Integer result = 0;
        String SQL = "SELECT public.f_ingresar_requerimientos_reforma('" + cComp.getReq_nombre() + "', '" + cComp.getReq_descripcion() + "', "
                + "'" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', "
                + "'" + cComp.getReq_costo_total() + "', '" + cComp.getFinanciamiento_nombre() + "','" + cComp.getActividad_nombre() + "', "
                + "'" + cComp.getReq_id() + "', '" + cComp.getPresupuesto_ejercicio() + "', '" + cComp.getPresupuesto_entidad() + "', "
                + "'" + cComp.getPresupuesto_unidad_ejec() + "', '" + cComp.getPresupuesto_unidad_desc() + "', '" + cComp.getPresupuesto_programa() + "',"
                + "'" + cComp.getPresupuesto_subprograma() + "', '" + cComp.getPresupuesto_proyecto() + "', '" + cComp.getPresupuesto_actividad() + "', "
                + "'" + cComp.getPresupuesto_obra() + "', '" + cComp.getPresupuesto_renglo_aux() + "', '" + cComp.getPresupuesto_fuente() + "', "
                + "'" + cComp.getPresupuesto_presupuesto() + "', '" + cComp.getPresupuesto_renglo() + "', '" + cComp.getReq_reforma() + "', '" + cComp.getReq_tipo() + "', "
                + "'" + cComp.getUnidad_id() + "', '" + cComp.getPac_tipo_producto() + "','" + cComp.getPac_catalogo() + "', '" + cComp.getPac_procedimiento_sug() + "', '" + cComp.getPac_fondo_bid() + "',"
                + "'" + cComp.getPac_num_operacion() + "', '" + cComp.getPac_codigo_proyecto() + "', '" + cComp.getPac_tipo_regimen() + "', '" + cComp.getReq_cpc() + "', '" + cComp.getTc_id() + "', "
                + "'" + cComp.getReq_verificacion() + "', '" + cComp.getProyecto_nombre() + "', '" + cComp.getAg_id() + "', '" + cComp.getPresupuesto_geografico() + "', '" + cComp.getPresupuesto_correlativo() + "', '" + cComp.getPresupuesto_organismo() + "')";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("f_ingresar_requerimientos_reforma");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar Reforma presupuesto
    public Integer IngresarReqReformaPres(cActividadRequerimiento cComp) {
        Integer result = 0;
        String SQL = "SELECT public.f_ingresar_requerimientos_reforma_presupuesto('" + cComp.getReq_id() + "', '" + cComp.getPresupuesto_ejercicio() + "', '" + cComp.getPresupuesto_entidad() + "', "
                + "'" + cComp.getPresupuesto_unidad_ejec() + "', '" + cComp.getPresupuesto_unidad_desc() + "', '" + cComp.getPresupuesto_programa() + "',"
                + "'" + cComp.getPresupuesto_subprograma() + "', '" + cComp.getPresupuesto_proyecto() + "', '" + cComp.getPresupuesto_actividad() + "', "
                + "'" + cComp.getPresupuesto_obra() + "', '" + cComp.getPresupuesto_renglo_aux() + "', '" + cComp.getPresupuesto_fuente() + "', "
                + "'" + cComp.getPresupuesto_presupuesto() + "', '" + cComp.getPresupuesto_renglo() + "', '" + cComp.getReq_estado() + "', '" + cComp.getPresupuesto_geografico() + "', '" + cComp.getPresupuesto_correlativo() + "', '" + cComp.getPresupuesto_organismo() + "')";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("f_ingresar_requerimientos_reforma_presupuesto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar reforma saldos 
    public String IngresaSaldos(cActividadRequerimiento oProy) {
        String result = "Error al ingresar saldos";
        String SQL = "INSERT INTO public.saldos(\n"
                + "	saldos_req, saldos_reforma, saldos_total)\n"
                + "	VALUES ('" + oProy.getReq_id() + "', '" + oProy.getReq_reforma() + "', round('" + oProy.getReq_costo_total() + "',2));";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }
}
