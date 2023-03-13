/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cActividadRequerimiento;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adEjecucion {

    private Exception error;

    public Exception getError() {
        return error;
    }

    static public ResultSet ListaAutoridades() {
        ResultSet rs = null;
        String SQL = "select * from autoridades where autoridades_estado=1;";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    rs = ad.getRs();
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    static public ResultSet ListarProcesos() {
        ResultSet rs = null;
        String SQL = "select * from proceso_contratacion where pc_estado=1;";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    rs = ad.getRs();
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    static public ResultSet ListaTipoCP() {
        ResultSet rs = null;
        String SQL = "select * from tipo_cp where tcp_estado=1 order by tcp_id;";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    rs = ad.getRs();
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    static public ResultSet ListaTipoSolicitud() {
        ResultSet rs = null;
        String SQL = "select * from tipo_solicitud where ts_estado=1;";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    rs = ad.getRs();
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Listar proyecto requerimientos analista
    public List<cActividadRequerimiento> ListarProyectosComprasAnalista(Integer area, Integer tipo, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT* from f_listarequeejecucion('" + area + "', '" + tipo + "', '" + anio + "');";
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
                        cComp.setActividad_nombre(estadonombreReq(cComp));
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
    public List<cActividadRequerimiento> ListarProyectosRequerimientosNP(Integer area, Integer tipo, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT* from f_listarequeejecucionNP('" + area + "', '" + tipo + "', '" + anio + "');";
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
                        cComp.setActividad_nombre(estadonombreReq(cComp));
                        cComp.setSolicitud_id(rsComp.getInt("reqsolicitud"));
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

    //Listar ervicios profesionales IPEC
    public List<cActividadRequerimiento> ListarServiciosIPEC(Integer area) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on (sp_id) sp_id, solnpac_ts, ts_nombre, solnpac_codigo, solnpac_anio, solnpacestado_estado, estado_nombre, sp_apellido, sp_nombre, sp_req, sp_valor_sin_iva, sp_valor_total, sp_tipo, sp_cargo, sp_cedula, \n"
                + "sp_solicitud, sp_observacion, sp_verificacion, sp_cedula_estudiante, sp_fecha_inicio, sp_fecha_fin, sp_apellido_estudiante, sp_nombre_estudiante, req_id, spe_estado, sp_solicitud_cert, sp_estado_dispo, (select estado_nombre from estado where estado_id=spe_estado) as speestado, req_iva from (select * from (select distinct on (solnpac_id) solnpac_id, solnpac_ts, ts_nombre, solnpac_codigo, solnpac_anio, solnpacestado_estado, solnpacestado_fecha,\n"
                + "(select estado_nombre from estado where estado_id=solnpacestado_estado) from(select * from solicitud_nopac inner join solnpac_estado on solnpac_id=solnpacestado_solicitud join \n"
                + "tipo_solicitud on solnpac_ts=ts_id where solnpac_ts=4 order by solnpac_id, solnpacestado_fecha desc) as con)as con2 join \n"
                + "servicios_profesionales on solnpac_id=sp_solicitud join requerimiento on sp_req=req_id join actividad on req_actividad=actividad_id \n"
                + "join componente on actividad_componente=componente_id join servicios_profesionales_estado on sp_id=spe_serviciosp where componente_ag='" + area + "' and solnpacestado_estado=34 order by sp_id, spe_fecha desc)as con5";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setUsuario_cedula(rsComp.getString("sp_cedula_estudiante"));
                        cComp.setUsuario_nombre(rsComp.getString("sp_nombre_estudiante"));
                        cComp.setUsuario_titulo(rsComp.getString("sp_apellido_estudiante"));
                        cComp.setAutoridades_cargo(rsComp.getString("sp_cargo"));
                        cComp.setReq_costo_total(rsComp.getDouble("sp_valor_total"));
                        cComp.setAutoridades_cedula(rsComp.getString("sp_cedula"));
                        cComp.setAutoridades_nombre(rsComp.getString("sp_nombre"));
                        cComp.setActividad_responsable(rsComp.getString("sp_apellido"));
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        cComp.setTc_nombre(rsComp.getString("ts_nombre"));
                        cComp.setReq_anio(rsComp.getInt("solnpac_anio"));
                        cComp.setActividad_id(rsComp.getInt("sp_tipo"));
                        cComp.setEstado_nombre(rsComp.getString("speestado"));
                        cComp.setReq_id(rsComp.getInt("sp_id"));
                        cComp.setFecha_inicio(rsComp.getString("sp_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("sp_fecha_fin"));
                        cComp.setSolicitud_id(rsComp.getInt("req_id"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("sp_valor_sin_iva"));
                        cComp.setAe_tiempo(rsComp.getInt("spe_estado"));
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

    //Listar ervicios profesionales IPEC
    public List<cActividadRequerimiento> ListarServiciosIPECTH() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on (sp_id) sp_id, solnpac_ts, ts_nombre, solnpac_codigo, solnpac_anio, solnpacestado_estado, estado_nombre, sp_apellido, sp_nombre, sp_req, sp_valor_sin_iva, sp_valor_total, sp_tipo, sp_cargo, sp_cedula, \n"
                + "sp_solicitud, sp_observacion, sp_verificacion, sp_cedula_estudiante, sp_fecha_inicio, sp_fecha_fin, sp_apellido_estudiante, sp_nombre_estudiante, req_id, spe_estado, sp_solicitud_cert, sp_estado_dispo, (select estado_nombre from estado where estado_id=spe_estado) as speestado, req_iva from (select * from (select distinct on (solnpac_id) solnpac_id, solnpac_ts, ts_nombre, solnpac_codigo, solnpac_anio, solnpacestado_estado, solnpacestado_fecha,\n"
                + "(select estado_nombre from estado where estado_id=solnpacestado_estado) from(select * from solicitud_nopac inner join solnpac_estado on solnpac_id=solnpacestado_solicitud join \n"
                + "tipo_solicitud on solnpac_ts=ts_id where solnpac_ts=4 order by solnpac_id, solnpacestado_fecha desc) as con)as con2 join \n"
                + "servicios_profesionales on solnpac_id=sp_solicitud join requerimiento on sp_req=req_id join actividad on req_actividad=actividad_id \n"
                + "join componente on actividad_componente=componente_id join servicios_profesionales_estado on sp_id=spe_serviciosp where solnpacestado_estado=34 order by sp_id, spe_fecha desc)as con5 where spe_estado=48 and sp_solicitud_cert is null";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setUsuario_cedula(rsComp.getString("sp_cedula_estudiante"));
                        cComp.setUsuario_nombre(rsComp.getString("sp_nombre_estudiante"));
                        cComp.setUsuario_titulo(rsComp.getString("sp_apellido_estudiante"));
                        cComp.setAutoridades_cargo(rsComp.getString("sp_cargo"));
                        cComp.setReq_costo_total(rsComp.getDouble("sp_valor_total"));
                        cComp.setAutoridades_cedula(rsComp.getString("sp_cedula"));
                        cComp.setAutoridades_nombre(rsComp.getString("sp_nombre"));
                        cComp.setActividad_responsable(rsComp.getString("sp_apellido"));
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        cComp.setTc_nombre(rsComp.getString("ts_nombre"));
                        cComp.setReq_anio(rsComp.getInt("solnpac_anio"));
                        cComp.setActividad_id(rsComp.getInt("sp_tipo"));
                        cComp.setEstado_nombre(rsComp.getString("speestado"));
                        cComp.setReq_id(rsComp.getInt("sp_id"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("sp_valor_sin_iva"));
                        cComp.setSolicitud_id(rsComp.getInt("req_id"));
                        cComp.setReq_iva(rsComp.getInt("req_iva"));
                        cComp.setUnidad_id(rsComp.getInt("sp_estado_dispo"));
                        cComp.setFecha_inicio(rsComp.getString("sp_fecha_inicio"));
                        cComp.setFecha_fin(rsComp.getString("sp_fecha_fin"));
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

    //Listar proyecto requerimientos analista th
    public List<cActividadRequerimiento> ListarProyectosRequerimientoETH(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL;
        if (anio == 2020) {
            SQL = "select * from (select *, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid and sp_solicitud is null and sp_solicitud_anulado is null)as total, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid)as totalsol, \n"
                    + "                (select count(reqestado_solicitud) from(select distinct on(reqestado_solicitud) reqestado_solicitud from (select *from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud join requerimiento_estado on solnpd_req=reqestado_req where solnpd_req=reqid)as con where reqestado_solicitud is not null)as c)as tenviado, \n"
                    + "                							(select count(solnpac_id) from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud where solnpd_req=reqid) as totalge\n"
                    + "                                            from(select distinct on(reqid) reqid, reqnombre, reqdescripcion, req_costototal, reqestado_estado, proyectonombre, agid, agnombre_i, perspectivatp, presupuestoproyecto, reqiva, reqestado_fecha \n"
                    + "                                            from(SELECT * FROM f_listarequerimientosexcel() inner join requerimiento_estado on reqid=reqestado_req where paccpc is null order by reqid, reqestado_fecha desc) as con)as con2 where (reqestado_estado=1 or reqestado_estado=32) order by reqestado_fecha asc)as sel where total is not null and total>0;";
        } else if (anio == 2021) {
            SQL = "select * from (select *, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid and sp_solicitud is null and sp_solicitud_anulado is null)as total, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid)as totalsol, \n"
                    + "                (select count(reqestado_solicitud) from(select distinct on(reqestado_solicitud) reqestado_solicitud from (select *from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud join requerimiento_estado on solnpd_req=reqestado_req where solnpd_req=reqid)as con where reqestado_solicitud is not null)as c)as tenviado, \n"
                    + "                							(select count(solnpac_id) from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud where solnpd_req=reqid) as totalge\n"
                    + "                                            from(select distinct on(reqid) reqid, reqnombre, reqdescripcion, req_costototal, reqestado_estado, proyectonombre, agid, agnombre_i, perspectivatp, presupuestoproyecto, reqiva, reqestado_fecha \n"
                    + "                                            from(SELECT * FROM f_listarequerimientosexcel21() inner join requerimiento_estado on reqid=reqestado_req where paccpc is null order by reqid, reqestado_fecha desc) as con)as con2 where (reqestado_estado=1 or reqestado_estado=32) order by reqestado_fecha asc)as sel where total is not null and total>0;";
        } else if (anio == 2022) {
            SQL = "select * from (select *, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid and sp_solicitud is null and sp_solicitud_anulado is null)as total, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid)as totalsol, \n"
                    + "                (select count(reqestado_solicitud) from(select distinct on(reqestado_solicitud) reqestado_solicitud from (select *from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud join requerimiento_estado on solnpd_req=reqestado_req where solnpd_req=reqid)as con where reqestado_solicitud is not null)as c)as tenviado, \n"
                    + "                							(select count(solnpac_id) from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud where solnpd_req=reqid) as totalge\n"
                    + "                                            from(select distinct on(reqid) reqid, reqnombre, reqdescripcion, req_costototal, reqestado_estado, proyectonombre, agid, agnombre_i, perspectivatp, presupuestoproyecto, reqiva, reqestado_fecha \n"
                    + "                                            from(SELECT * FROM f_listarequerimientosexcel22() inner join requerimiento_estado on reqid=reqestado_req where paccpc is null order by reqid, reqestado_fecha desc) as con)as con2 where (reqestado_estado=1 or reqestado_estado=32) order by reqestado_fecha asc)as sel where total is not null and total>0;";
        } else {
            SQL = "select * from (select *, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid and sp_solicitud is null and sp_solicitud_anulado is null)as total, (select sum(sp_valor_total) from servicios_profesionales where sp_req=reqid)as totalsol, \n"
                    + "                (select count(reqestado_solicitud) from(select distinct on(reqestado_solicitud) reqestado_solicitud from (select *from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud join requerimiento_estado on solnpd_req=reqestado_req where solnpd_req=reqid)as con where reqestado_solicitud is not null)as c)as tenviado, \n"
                    + "                							(select count(solnpac_id) from solicitud_nopac inner join sol_req_deudas on solnpac_id=solnpd_solicitud where solnpd_req=reqid) as totalge\n"
                    + "                                            from(select distinct on(reqid) reqid, reqnombre, reqdescripcion, req_costototal, reqestado_estado, proyectonombre, agid, agnombre_i, perspectivatp, presupuestoproyecto, reqiva, reqestado_fecha \n"
                    + "                                            from(SELECT * FROM f_listarequerimientosexcel23th(" + anio + ") inner join requerimiento_estado on reqid=reqestado_req where paccpc is null order by reqid, reqestado_fecha desc) as con)as con2 where (reqestado_estado=1 or reqestado_estado=32) order by reqestado_fecha asc)as sel where total is not null and total>0;";
        }
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        adActividadRequerimiento adA = new adActividadRequerimiento();
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setAg_id(rsComp.getInt("agid"));
                        cComp.setAg_nombre(rsComp.getString("agnombre_i"));
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setSolicitud_estado(rsComp.getInt("reqestado_estado"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setActividad_monto(rsComp.getDouble("total"));
                        cComp.setCuatri(adA.ListarPersonal(rsComp.getInt("reqid")));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setSueldo_total(rsComp.getDouble("totalsol"));
                        cComp.setAe_tiempo(rsComp.getInt("tenviado"));
                        cComp.setActividad_prioridad(rsComp.getInt("totalge"));
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

    //Listar proyecto requerimientos analista th
    public List<cActividadRequerimiento> ListarProyectosRequerimientoETHO(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *, (select sum(spo_valor_sin_iva) from servicios_profesionales_op where spo_deudas=deudas_id and spo_solicitud is null)as totalsiv,(select sum(spo_valor_total) from servicios_profesionales_op where spo_deudas=deudas_id and spo_solicitud is null)as total, (select sum(spo_valor_total) from servicios_profesionales_op where spo_deudas=deudas_id)as totalsol   \n"
                + "                 from(select distinct on(deudas_id) deudas_id, deudas_nombre_proceso, deudas_proyecto, deudas_monto, deudas_iva, deudas_anticipo, obpendestado_observacion, obpendestado_fecha,\n"
                + "                obpendestado_estado, estado_nombre, ag_nombre, deudas_oei, ag_id from (select * from deudas inner join obpend_estado on deudas_id=obpendestado_req inner join estado on obpendestado_estado=estado_id \n"
                + "				inner join area_gestion on deudas_ag=ag_id where deudas_anio='" + anio + "' order by deudas_id, obpendestado_fecha desc) as con)as con2 left join sol_deudas_lista on\n"
                + "                con2.deudas_id=soldeudasl_deudas where (obpendestado_estado=1 or obpendestado_estado=32) and soldeudasl_deudas is null order by obpendestado_fecha asc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        adActividadRequerimiento adA = new adActividadRequerimiento();
                        cComp.setProyecto_nombre(rsComp.getString("deudas_proyecto"));
                        cComp.setAg_id(rsComp.getInt("ag_id"));
                        cComp.setAg_nombre(rsComp.getString("ag_nombre"));
                        cComp.setReq_id(rsComp.getInt("deudas_id"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudas_monto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudas_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudas_anticipo"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudas_oei"));
                        cComp.setSolicitud_estado(rsComp.getInt("obpendestado_estado"));
                        cComp.setActividad_monto(rsComp.getDouble("total"));
                        cComp.setCuatri(adA.ListarPersonalO(rsComp.getInt("deudas_id")));
                        cComp.setSueldo_total(rsComp.getDouble("totalsol"));
                        cComp.setAe_ejecucion(rsComp.getDouble("totalsiv"));
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

    //Listar obligaciones pendientes
    public List<cActividadRequerimiento> ListarProyectosRequerimientosOBC(Integer area, Integer tipo, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarequeejecucionopco('" + area + "', '" + tipo + "', '" + anio + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setProyecto_nombre(rsComp.getString("deudasproyecto"));
                        cComp.setAg_id(rsComp.getInt("agid"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setReq_id(rsComp.getInt("deudasid"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombreproceso"));
                        cComp.setReq_descripcion(rsComp.getString("deudas_nombreproceso"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudasoei"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("deudasmonto"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("deudasiva"));
                        cComp.setReq_costo_total(rsComp.getDouble("deudasanticipo"));
                        cComp.setTc_id(rsComp.getInt("deudastipo"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("deproyecto"));
                        cComp.setPresupuesto_fuente(rsComp.getString("defuente"));
                        cComp.setEstado_nombre(rsComp.getString("estadonombre"));
                        cComp.setSolicitud_estado(rsComp.getInt("estadoid"));
                        cComp.setSolestado_observacion(rsComp.getString("estadoobservacion"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("fechaestado"));
                        cComp.setUsuario_nombre(rsComp.getString("usuarionombre"));
                        cComp.setSolicitud_id(rsComp.getInt("deudassolicitud"));
                        cComp.setActividad_nombre(estadonombreReqOP(cComp));
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

    //Estado nombre de requerimiento
    public String estadonombreReq(cActividadRequerimiento req) {
        String result = null;
        String SQL = "select estado_nombre from(select distinct on (req_id) req_id, reqestado_estado, estado_nombre, reqestado_fecha from (select req_id, reqestado_estado, estado_nombre, reqestado_fecha from requerimiento inner join requerimiento_estado on req_id=reqestado_req join \n"
                + "estado on reqestado_estado=estado_id  where req_id='" + req.getReq_id() + "' order by reqestado_fecha desc)as con)as con2; ";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("estado_nombre");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Custodio
    public Boolean custodioExiste(String cedula) {
        Boolean result = false;
        String SQL = "select exists(select * from custodio where custodio_cedula like '" + cedula + "')";
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

    //Validación de fechas por año
    static public Boolean fechaporAño(Integer anio) {
        Boolean result = false;
        String SQL = "select exists(select * from tiempos where tiempos_anio='" + anio + "' and tiempos_tipo=1 and tiempos_fecha>now())";
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

    //Estado nombre de requerimiento
    public String estadonombreReqOP(cActividadRequerimiento req) {
        String result = null;
        String SQL = "select estado_nombre from(select distinct on (deudas_id) deudas_id, obpendestado_estado, estado_nombre, obpendestado_fecha from (select deudas_id, obpendestado_estado, estado_nombre, obpendestado_fecha from deudas inner join obpend_estado on deudas_id=obpendestado_req join \n"
                + "estado on obpendestado_estado=estado_id  where deudas_id='" + req.getReq_id() + "' order by obpendestado_fecha desc)as con)as con2; ";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("estado_nombre");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Codigo Siguiente solicitud
    public Integer codigoSiguienteSolicitud() {
        Integer result = null;
        String SQL = "SELECT (MAX(solicitud_id)) as codigo FROM solicitud;";
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

    //Codigo Siguiente solicitud
    public Integer codigoSiguienteCP() {
        Integer result = null;
        String SQL = "SELECT (MAX(cp_id)) as codigo FROM certificacion_presupuestaria;";
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

    //Codigo Siguiente certificación presupuestaria servicio profesional
    public Integer codigoSiguienteCPSP() {
        Integer result = null;
        String SQL = "SELECT (MAX(cpsp_id)) as codigo FROM certificacion_presupuestaria_sp;";
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

    //Codigo Siguiente certificación presupuestaria servicio profesional op
    public Integer codigoSiguienteCPSPOP() {
        Integer result = null;
        String SQL = "SELECT (MAX(cpspo_id)) as codigo FROM certificacion_presupuestaria_sp_op;";
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

    //Codigo Siguiente certificacion STP
    public Integer codigoSiguienteCPV() {
        Integer result = null;
        String SQL = "SELECT (MAX(cpv_id)) as codigo FROM certificacion_presupuestaria_valores;";
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

    //Codigo Siguiente solicitud no pac
    public Integer codigoSiguienteSolicitudNP() {
        Integer result = null;
        String SQL = "SELECT (MAX(solnpac_id)) as codigo FROM solicitud_nopac;";
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

    //Codigo Siguiente solicitud obligaciones pendientes
    public Integer codigoSiguienteSolicitudOPC() {
        Integer result = null;
        String SQL = "SELECT (MAX(soldeudas_id)) as codigo FROM solicitud_deudas;";
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

    //Codigo Siguiente oficio
    public Integer codigoSiguienteOficio(Integer estado) {
        Integer result = null;
        String SQL = "SELECT (MAX(solestado_numero)) as codigo FROM solicitud_estado where solestado_estado='" + estado + "';";
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

    //Ingresar Solicitud
    public String IngresarSolicitud(cActividadRequerimiento cComp) {
        String result = "Error al ingresar la solicitud";
        String SQL = "INSERT INTO public.solicitud(\n"
                + "	solicitud_id, solicitud_ag, solicitud_centro_costo, solicitud_autoridades, solicitud_custodio_cedula, solicitud_custodio_nombre, solicitud_custodio_cargo, solicitud_anio)\n"
                + "	VALUES ('" + cComp.getSolicitud_id() + "', '" + cComp.getAg_id() + "', '" + cComp.getSolicitud_centro_costo() + "', '" + cComp.getSolicitud_autoridades() + "',"
                + "     '" + cComp.getSolicitud_cedula() + "', '" + cComp.getSolicitud_nombre() + "', '" + cComp.getSolicitud_cargo() + "', '" + cComp.getSolicitud_estado() + "');";

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

    //Ingresar Solicitud NO PAC
    public String IngresarSolicitudNP(cActividadRequerimiento cComp) {
        String result = "Error al ingresar la solicitud";
        String SQL = "INSERT INTO public.solicitud_nopac(\n"
                + "	solnpac_id, solnpac_ag, solnpac_observacion, solnpac_ts, solnpac_anio)\n"
                + "	VALUES ('" + cComp.getSolicitud_id() + "', '" + cComp.getAg_id() + "', '" + cComp.getSolicitud_cargo() + "', '" + cComp.getSolicitud_autoridades() + "', '" + cComp.getSolicitud_estado() + "');";

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

    //Ingresar Solicitud obligaciones pendientes
    public String IngresarSolicitudOPC(cActividadRequerimiento cComp) {
        String result = "Error al ingresar la solicitud";
        String SQL = "INSERT INTO public.solicitud_deudas(\n"
                + "	soldeudas_id, soldeudas_ag, soldeudas_observacion, soldeudas_ts, soldeudas_anio)\n"
                + "	VALUES ('" + cComp.getSolicitud_id() + "', '" + cComp.getAg_id() + "', '" + cComp.getSolicitud_cargo() + "', '" + cComp.getSolicitud_autoridades() + "', '" + cComp.getSolicitud_estado() + "');";

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

    //Insertar Solicitud - Requerimientos
    public String InsertarSolicitudReq(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasado al planificado disponible. Por favor verifique el monto ingresado y la cantidad.";
        String SQL = "INSERT INTO public.solicitud_requerimiento(\n"
                + "	solreq_requerimiento, solreq_solicitud, solreq_cantidad, solreq_costo_unitario, solreq_costo_sin_iva, solreq_costo_iva)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', round('" + cComp.getReq_costo_sin_iva() + "',2), round('" + cComp.getReq_costo_total() + "', 2));";

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

    //Insertar Solicitud - Requerimientos NO PAC
    public String InsertarSolicitudReqNP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasado al planificado disponible. Por favor verifique el monto ingresado y la cantidad.";
        String SQL = "INSERT INTO public.sol_req_deudas(\n"
                + "	solnpd_req, solnpd_solicitud, solnpd_cantidad, solnpd_costo_unitario, solnpd_costo_total, solnpd_costo_iva)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', round('" + cComp.getReq_costo_total() + "',2));";

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

    //Insertar Solicitud - Obligaciones Pendientes comprometidos
    public String InsertarSolicitudReqOPC(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasado al planificado disponible. Por favor verifique el monto ingresado y la cantidad.";
        String SQL = "INSERT INTO public.sol_deudas_lista(\n"
                + "	soldeudasl_deudas, soldeudasl_solicitud, soldeudasl_costo_total, soldeudasl_costo_iva, soldeudasl_costo_anticipo)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getReq_costo_total() + "');";

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

    //Insertar Servicios profesionales
    public String InsertarServiciosP(cActividadRequerimiento cComp) {
        String result = "Error";
        String SQL = "INSERT INTO public.servicios_profesionales(\n"
                + "	sp_id, sp_apellido, sp_nombre, sp_fecha_inicio, sp_fecha_fin, sp_valor_sin_iva, sp_req, sp_valor_unitario, sp_valor_total, sp_tipo, sp_cargo, sp_cedula, sp_observacion, sp_cedula_estudiante, sp_nombre_estudiante, sp_apellido_estudiante)\n"
                + "	VALUES ('" + cComp.getSolicitud_id() + "', '" + cComp.getReq_descripcion() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getFecha_inicio() + "', '" + cComp.getFecha_fin() + "','" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getAg_id() + "', '" + cComp.getReq_cantidad() + "',"
                + "     '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "','" + cComp.getReq_cpc() + "', '" + cComp.getSolicitud_cedula() + "', '" + cComp.getSolestado_observacion() + "', '" + cComp.getAutoridades_cedula() + "', '" + cComp.getAutoridades_nombre() + "', '" + cComp.getAutoridades_cargo() + "');";

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

    //Eliminar Solicitud
    public String EliminarSolicitud(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la solicitud";
        String SQL = "DELETE FROM public.solicitud\n"
                + "	WHERE solicitud_id='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar Solicitud NO PAC
    public String EliminarSolicitudNP(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la solicitud";
        String SQL = "DELETE FROM public.solicitud_nopac\n"
                + "	WHERE solnpac_id='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar Solicitud Obligaciones Pendientes
    public String EliminarSolicitudOPC(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la solicitud";
        String SQL = "DELETE FROM public.solicitud_deudas\n"
                + "	WHERE soldeudas_id='" + cComp.getSolicitud_id() + "';";

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

    //Verificar servicios profesionales 
    public String VerificarServiciosP(Integer serv, Integer verf) {
        String result = "Error al verificar";
        String SQL = "UPDATE servicios_profesionales SET sp_verificacion='" + verf + "' WHERE sp_id='" + serv + "';";

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

    //Anular servicios profesionales 
    public String AnularerviciosP(Integer solicitud, Integer sp) {
        String result = "Error al verificar";
        String SQL = "UPDATE servicios_profesionales SET sp_solicitud_anulado='" + solicitud + "', sp_solicitud=null, sp_estadp=0 WHERE sp_id='" + sp + "';";

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

    //Verificar servicios profesionales obligaciones pendientes
    public String VerificarServiciosPO(Integer serv, Integer verf) {
        String result = "Error al verificar";
        String SQL = "UPDATE servicios_profesionales_op SET spo_verificacion='" + verf + "' WHERE spo_id='" + serv + "';";

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

    //Lista de solicitudes por area
    public List<cActividadRequerimiento> ListarSolicitudAreas(Integer area, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudes where solicitud_ag='" + area + "' and solicitud_anio='" + anio + "' order by solestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solicitud_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("solicitud_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitud_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("solestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("solestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("solicitud_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("solicitud_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitud_custodio_cargo"));
                        cComp.setActividad_monto(montoJustificativo(cComp));
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

    //Lista de servisios profesionales
    public List<cActividadRequerimiento> ListarServicioProfV(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solicitud_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("solicitud_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitud_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("solestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("solestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("solicitud_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("solicitud_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitud_custodio_cargo"));
                        cComp.setActividad_monto(montoJustificativo(cComp));
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

    //Lista de solicitudes no pac por area
    public List<cActividadRequerimiento> ListarSolicitudNPAreas(Integer area, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *, (select exists(select * from sol_req_deudas inner join servicios_profesionales on solnpd_req=sp_req where solnpd_solicitud=solnpac_id)) as serp FROM public.vsolitiudesnpac where solnpac_ag='" + area + "' and solnpac_estado=1 and solnpac_anio='" + anio + "' order by solnpacestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solnpac_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("ag_nombre"));
                        cComp.setSolicitud_estado(rsComp.getInt("solnpacestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solnpacestado_fecha"));
                        cComp.setActividad_nombre(rsComp.getString("solnpac_observacion"));
                        cComp.setSolestado_observacion(rsComp.getString("solnpacestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        cComp.setTc_id(rsComp.getInt("ts_id"));
                        cComp.setTc_nombre(rsComp.getString("ts_nombre"));
                        cComp.setReq_disponible(rsComp.getBoolean("serp"));
                        cComp.setActividad_monto(montoSolicitudNP(cComp));
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

    //Lista de solicitudes no pac por area
    public List<cActividadRequerimiento> ListarSolicitudNPAreasE(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudesnpaceje where solnpac_estado=1 and solnpac_anio='" + anio + "' order by solnpacestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solnpac_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("ag_nombre"));
                        cComp.setSolicitud_estado(rsComp.getInt("solnpacestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solnpacestado_fecha"));
                        cComp.setActividad_nombre(rsComp.getString("solnpac_observacion"));
                        cComp.setSolestado_observacion(rsComp.getString("solnpacestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_codigo(rsComp.getString("solnpac_codigo"));
                        cComp.setTc_id(rsComp.getInt("ts_id"));
                        cComp.setTc_nombre(rsComp.getString("ts_nombre"));
                        cComp.setActividad_monto(montoSolicitudNP(cComp));
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

    //Lista de solicitudes no pac completa
    public List<cActividadRequerimiento> ListarSolicitudNPCompleta(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listasolicitudNP('" + sol + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("idsolicitud"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        cComp.setTc_id(rsComp.getInt("tsid"));
                        cComp.setTc_nombre(rsComp.getString("tsnombre"));
                        cComp.setActividad_responsable(rsComp.getString("solicitudnombre"));
                        cComp.setSolestado_observacion(rsComp.getString("solobservacion"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitudcargo"));
                        cComp.setUsuario_nombre(rsComp.getString("usuarionombre"));
                        cComp.setSolestado_fecha(rsComp.getDate("solicitudfecha"));
                        cComp.setUsuario_titulo(rsComp.getString("usuariotitulo"));
                        cComp.setAg_alias(rsComp.getString("usuariocargo"));
                        cComp.setActividad_eval(ListarSolicitudSolicitudNP(sol));
                        cComp.setSolicitud_cedula(rsComp.getString("usuarioverificado"));
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

    //Lista de solicitudes no pac completa
    public List<cActividadRequerimiento> ListarSolicitudNPCompletaOP(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listasolicitudnpop('" + sol + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("idsolicitud"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        cComp.setTc_id(rsComp.getInt("tsid"));
                        cComp.setTc_nombre(rsComp.getString("tsnombre"));
                        cComp.setActividad_responsable(rsComp.getString("solicitudnombre"));
                        cComp.setSolestado_observacion(rsComp.getString("solobservacion"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitudcargo"));
                        cComp.setUsuario_nombre(rsComp.getString("usuarionombre"));
                        cComp.setSolestado_fecha(rsComp.getDate("solicitudfecha"));
                        cComp.setUsuario_titulo(rsComp.getString("usuariotitulo"));
                        cComp.setAg_alias(rsComp.getString("usuariocargo"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setActividad_eval(ListarSolicitudSolicitudOPC(sol));
                        cComp.setSolicitud_cedula(rsComp.getString("usuarioverificado"));
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

    //Lista de solicitudes obligaciones pendientes por area
    public List<cActividadRequerimiento> ListarSolicitudOPCAreas(Integer area, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudesopc where soldeudas_ag='" + area + "' and soldeudas_anio='" + anio + "' and soldeudas_estado=1 order by soldeudasestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soldeudas_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("ag_nombre"));
                        cComp.setSolicitud_estado(rsComp.getInt("soldeudasestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soldeudasestado_fecha"));
                        cComp.setActividad_nombre(rsComp.getString("soldeudas_observacion"));
                        cComp.setSolestado_observacion(rsComp.getString("soldeudasestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_codigo(rsComp.getString("soldeudas_codigo"));
                        cComp.setTc_id(rsComp.getInt("ts_id"));
                        cComp.setTc_nombre(rsComp.getString("ts_nombre"));
                        cComp.setActividad_monto(montoSolicitudOPC(cComp));
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

    //Lista de solicitudes obligaciones pendientes por area
    public List<cActividadRequerimiento> ListarSolicitudOPCE(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudesopce where soldeudas_estado=1 and soldeudas_anio='" + anio + "' order by soldeudasestado_fecha asc, soldeudasestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soldeudas_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("ag_nombre"));
                        cComp.setSolicitud_estado(rsComp.getInt("soldeudasestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soldeudasestado_fecha"));
                        cComp.setActividad_nombre(rsComp.getString("soldeudas_observacion"));
                        cComp.setSolestado_observacion(rsComp.getString("soldeudasestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_codigo(rsComp.getString("soldeudas_codigo"));
                        cComp.setTc_id(rsComp.getInt("ts_id"));
                        cComp.setTc_nombre(rsComp.getString("ts_nombre"));
                        cComp.setActividad_monto(montoSolicitudOPC(cComp));
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

    //Lista de solicitudes por area
    public List<cActividadRequerimiento> ListarSolicitudAreasC(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudesc left join proceso_contratacion on solicitud_pc=pc_id where solicitud_anio='" + anio + "' order by solestado_fecha asc, solestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solicitud_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("solicitud_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitud_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("solestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("solestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("solicitud_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("solicitud_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitud_custodio_cargo"));
                        cComp.setSolestado_numero(rsComp.getInt("solestado_numero"));
                        cComp.setPc_id(rsComp.getInt("pc_id"));
                        cComp.setPc_nombre(rsComp.getString("pc_nombre"));
                        cComp.setActividad_monto(montoJustificativo(cComp));
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

    //Lista de solicitudes para usuario ejecucion
    public List<cActividadRequerimiento> ListarSolicitudAreasCAE(Integer anio, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL;
        if (tipo == 1) {
            SQL = "SELECT *FROM public.vsolitiudesc where (solestado_estado<>1 and solestado_estado<>31 and solestado_estado<>33 and solestado_estado<>19) and solicitud_anio='" + anio + "' order by solestado_fecha asc, solestado_estado desc;";
        } else {
            SQL = "SELECT *FROM public.vsolitiudesc where solicitud_anio='" + anio + "' order by solicitud_codigo;";
        }
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solicitud_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("solicitud_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitud_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("solestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("solestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("solicitud_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("solicitud_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitud_custodio_cargo"));
                        cComp.setSolestado_numero(rsComp.getInt("solestado_numero"));
                        cComp.setActividad_monto(montoJustificativo(cComp));
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

    //Lista de solicitudes para usuario ejecucion
    public List<cActividadRequerimiento> ListarSolicitudAreasCAEU(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudescomprasunif where (soluniestado_estado<>1 and soluniestado_estado<>31 and soluniestado_estado<>33 and soluniestado_estado<>19) and soluni_anio='" + anio + "' order by soluniestado_fecha asc, soluniestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soluni_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("soluni_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("soluni_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("soluniestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soluniestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("soluniestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("soluni_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("soluni_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("soluni_custodio_cargo"));
                        cComp.setSolestado_numero(rsComp.getInt("soluniestado_numero"));
                        cComp.setActividad_monto(montoJustificativoUnif(cComp));
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

    //Monto del justificativo
    public double montoJustificativo(cActividadRequerimiento cC) {
        double result = 0.0;
        String SQL = "select sum(solreq_costo_sin_iva) as monto from solicitud_requerimiento where solreq_solicitud='" + cC.getSolicitud_id() + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getDouble("monto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Monto de la Solicitud no pac
    public double montoSolicitudNP(cActividadRequerimiento cC) {
        double result = 0.0;
        String SQL = "select sum(solnpd_costo_total) as monto from sol_req_deudas where solnpd_solicitud='" + cC.getSolicitud_id() + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getDouble("monto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Monto de la Solicitud obligacioness
    public double montoSolicitudOPC(cActividadRequerimiento cC) {
        double result = 0.0;
        String SQL = "select sum(soldeudasl_costo_total+soldeudasl_costo_iva+soldeudasl_costo_anticipo) as monto from sol_deudas_lista where soldeudasl_solicitud='" + cC.getSolicitud_id() + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getDouble("monto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Lista de requerimientos por solicitud
    public List<cActividadRequerimiento> ListarSolicitudSolicitud(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarequecompras('" + solicitud + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solicitud"));
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cpc(rsComp.getString("reqcpc"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("reqproyecto"));
                        cComp.setActividad_monto(montoJustificativo(cComp));
                        cComp.setDeudas(ListarEstructuraReq(rsComp.getInt("reqid")));
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

    //Lista de requerimientos por solicitud no pac
    public List<cActividadRequerimiento> ListarSolicitudSolicitudNP(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarequenpac('" + solicitud + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        adActividadRequerimiento adAct = new adActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solicitud"));
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_cantidad(rsComp.getDouble("req_cantidadanual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("req_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("req_costo_siniva"));
                        cComp.setReq_costo_total(rsComp.getDouble("reqtotal"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_iva(rsComp.getInt("reqiva"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("reqproyecto"));
                        cComp.setActividad_monto(montoSolicitudNP(cComp));
                        cComp.setDeudas(ListarEstructuraReq(rsComp.getInt("reqid")));
                        cComp.setActividad_eval(adAct.ListarPersonal(rsComp.getInt("reqid")));
                        cComp.setCuatri(adAct.ListarPersonal2(rsComp.getInt("reqid")));
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

    //Lista de requerimientos por solicitud obligaciones pendientes comprometidos
    public List<cActividadRequerimiento> ListarSolicitudSolicitudOPC(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vdeudas inner join\n"
                + "                sol_deudas_lista on deudas_id=soldeudasl_deudas where soldeudasl_solicitud='" + solicitud + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        adActividadRequerimiento adAct = new adActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soldeudasl_solicitud"));
                        cComp.setReq_id(rsComp.getInt("deudas_id"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("soldeudasl_costo_total"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("soldeudasl_costo_iva"));
                        cComp.setReq_costo_total(rsComp.getDouble("soldeudasl_costo_anticipo"));
                        cComp.setReq_nombre(rsComp.getString("deudas_nombre_proceso"));
                        cComp.setTc_id(rsComp.getInt("deudas_tipo"));
                        cComp.setPerspectiva_id(rsComp.getInt("deudas_oei"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("de_proyecto"));
                        cComp.setActividad_monto(montoSolicitudOPC(cComp));
                        cComp.setDeudas(ListarEstructuraReqOPC(rsComp.getInt("deudas_id")));
                        cComp.setActividad_eval(adAct.ListarPersonalO(rsComp.getInt("deudas_id")));
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

    //Agregar Requerimientos
    public String AgregarRequerimientos(cActividadRequerimiento cComp) {
        String result = "Error al ingresar el componente";
        String SQL = "Select * from f_agregarreqsol('" + cComp.getSolicitud_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getReq_costo_total() + "')";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_agregarreqsol");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Agregar Requerimientos
    public String AgregarRequerimientosNP(cActividadRequerimiento cComp) {
        String result = "Error al ingresar el componente";
        String SQL = "Select * from f_agregarreqsolnp('" + cComp.getSolicitud_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "')";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_agregarreqsolnp");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Agregar Obligaciones Pendientes
    public String AgregarRequerimientosOPC(cActividadRequerimiento cComp) {
        String result = "Error al ingresar el componente";
        String SQL = "Select * from f_agregarreqsolopc('" + cComp.getSolicitud_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getReq_costo_total() + "')";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_agregarreqsolopc");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public String EnviarRequerimientosUnidades(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.requerimiento_estado(\n"
                + "	reqestado_estado, reqestado_req, reqestado_fecha, reqestado_usuario, reqestado_observacion, reqestado_cantidad, reqestado_costo_unitario, reqestado_costo_total, reqestado_iva)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getReq_id() + "', now(), '" + cComp.getUsuario_nombre() + "', '" + cComp.getSolestado_observacion() + "', '" + cComp.getReq_cantidad() + "',"
                + "         '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', " + cComp.getReq_iva() + ");";

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

    //Modificar Solicitud
    public String ModificarSolicitud(cActividadRequerimiento cComp) {
        String result = "Error al modificar la solicitud";
        String SQL = "UPDATE solicitud set solicitud_custodio_cedula='" + cComp.getSolicitud_cedula() + "', solicitud_custodio_nombre='" + cComp.getSolicitud_nombre() + "', solicitud_custodio_cargo='" + cComp.getSolicitud_cargo() + "' ,solicitud_centro_costo='" + cComp.getSolicitud_centro_costo() + "', solicitud_autoridades='" + cComp.getSolicitud_autoridades() + "' where solicitud_id='" + cComp.getSolicitud_id() + "'";

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

    //Modificar Solicitud NO PAC
    public String ModificarSolicitudNP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la solicitud";
        String SQL = "UPDATE solicitud_nopac set solnpac_observacion='" + cComp.getSolicitud_centro_costo() + "', solnpac_ts='" + cComp.getSolicitud_autoridades() + "' where solnpac_id='" + cComp.getSolicitud_id() + "'";

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

    //Modificar Solicitud Obligaciones Pendientes 
    public String ModificarSolicitudOPC(cActividadRequerimiento cComp) {
        String result = "Error al modificar la solicitud";
        String SQL = "UPDATE solicitud_deudas set soldeudas_observacion='" + cComp.getSolicitud_centro_costo() + "', soldeudas_ts='" + cComp.getSolicitud_autoridades() + "' where soldeudas_id='" + cComp.getSolicitud_id() + "'";

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

    //Verificar solicitud requerimientos
    public Boolean VerificarSolicitud(cActividadRequerimiento cComp) {
        Boolean result = false;
        String SQL = "select * from verificar_requerimiento('" + cComp.getSolicitud_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_sin_iva() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("verificar_requerimiento");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Verificar solicitud requerimientos
    public Boolean VerificarSolicitudNP(cActividadRequerimiento cComp) {
        Boolean result = false;
        String SQL = "select * from verificar_requerimientonp('" + cComp.getSolicitud_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_sin_iva() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("verificar_requerimientonp");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Verificar solicitud obligaciones pendientes y comprometidos
    public Boolean VerificarSolicitudOPC(cActividadRequerimiento cComp) {
        Boolean result = false;
        String SQL = "select * from verificar_deudas('" + cComp.getSolicitud_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getReq_costo_total() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getBoolean("verificar_deudas");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar Requerimintos Solicitud
    public String EliminarReqSolicitud(cActividadRequerimiento cComp) {
        String result = "Error al eliminar los requerimientos";
        String SQL = "DELETE FROM public.solicitud_requerimiento\n"
                + "	WHERE solreq_solicitud='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar Requerimintos Solicitud NO PAC
    public String EliminarReqSolicitudNP(cActividadRequerimiento cComp) {
        String result = "Error al eliminar los requerimientos";
        String SQL = "DELETE FROM public.sol_req_deudas\n"
                + "	WHERE solnpd_solicitud='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar Requerimintos Solicitud OBLIGACIONES PENDIENTES
    public String EliminarReqSolicitudOPC(cActividadRequerimiento cComp) {
        String result = "Error al eliminar los requerimientos";
        String SQL = "DELETE FROM public.sol_deudas_lista\n"
                + "	WHERE soldeudasl_solicitud='" + cComp.getSolicitud_id() + "';";

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

    //Insertar Envio de solicitud
    public String EnviarSolicitud(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.solicitud_estado(\n"
                + "	solestado_estado, solestado_solicitud, solestado_usuario, solestado_fecha)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now());";

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

    //Modificar proceso de contratación de solicitud
    public String ModificarProceso(cActividadRequerimiento cComp) {
        String result = "Error al modificar el proceso de contratación";
        String SQL;
        if (cComp.getPc_id() == 7) {
            SQL = "UPDATE solicitud SET solicitud_pc='" + cComp.getPc_id() + "', solicitud_pc_observacion='" + cComp.getPc_nombre() + "' WHERE solicitud_id='" + cComp.getSolicitud_id() + "';";
        } else {
            SQL = "UPDATE solicitud SET solicitud_pc='" + cComp.getPc_id() + "', solicitud_pc_observacion=null WHERE solicitud_id='" + cComp.getSolicitud_id() + "';";
        }

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

    //Validar Justificativo
    public List<cActividadRequerimiento> VerificarSolicitud(Integer solicitud, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "Select * from f_verificacionenviojust('" + solicitud + "', '" + tipo + "');";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("numerosol"));
                        cComp.setSolicitud_id(rsComp.getInt("numerover"));
                        result.add(cComp);
                    }
                    ad.desconectar();
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Insertar Envio de solicitud no pac
    public String EnviarSolicitudnp(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.solnpac_estado(\n"
                + "	solnpacestado_estado, solnpacestado_solicitud, solnpacestado_usuario, solnpacestado_fecha)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now());";

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

    //Insertar Envio de solicitud no pac
    public String EnviarSolicitudnpOb(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.solnpac_estado(\n"
                + "	solnpacestado_estado, solnpacestado_solicitud, solnpacestado_usuario, solnpacestado_fecha, solnpacestado_observacion)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now(), '" + cComp.getSolestado_observacion() + "');";

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

    //Insertar Envio de solicitud obligaciones pendientes
    public String EnviarSolicitudOPC(cActividadRequerimiento cComp) {
        String result = "Error al enviar la solicitud";
        String SQL = "INSERT INTO public.soldeudas_estado(\n"
                + "	soldeudasestado_estado, soldeudasestado_solicitud, soldeudasestado_usuario, soldeudasestado_fecha)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now());";

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

    //Insertar Envio de solicitud obligaciones pendientes
    public String EnviarSolicitudOPCOBS(cActividadRequerimiento cComp) {
        String result = "Error al enviar la solicitud";
        String SQL = "INSERT INTO public.soldeudas_estado(\n"
                + "	soldeudasestado_estado, soldeudasestado_solicitud, soldeudasestado_usuario, soldeudasestado_fecha, soldeudasestado_observacion)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now(), '" + cComp.getSolestado_observacion() + "');";

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

    //Codigo solicitud no pac
    public String ingresarCodigoNP(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "UPDATE solicitud_nopac SET solnpac_codigo='" + cComp.getSolicitud_codigo() + "' where solnpac_id='" + cComp.getSolicitud_id() + "';";

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

    //Codigo solicitud obligaciones pendientes
    public String ingresarCodigoOPC(cActividadRequerimiento cComp) {
        String result = "Error al enviar la solicitud";
        String SQL = "UPDATE solicitud_deudas SET soldeudas_codigo='" + cComp.getSolicitud_codigo() + "' where soldeudas_id='" + cComp.getSolicitud_id() + "';";

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

    //Modificar requerimiento sal aguardar
    public String GuardarSalvaguardar(Boolean salvaguardado, Integer req) {
        String result = "Error al guardar";
        String SQL = "UPDATE requerimiento set req_salvaguardado='" + salvaguardado + "' where req_id='" + req + "'";

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

    //Lista de envios por solicitud
    public List<cActividadRequerimiento> ListarSolicitudEnviados(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *\n"
                + "	FROM public.solicitud_estado inner join estado on solestado_estado=estado_id JOIN usuario \n"
                + "	on solestado_usuario=usuario_cedula where solestado_solicitud='" + sol + "' order by solestado_fecha desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solestado_solicitud"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_cedula(rsComp.getString("usuario_cedula"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("solestado_observacion"));
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

    //Lista de envios por solicitud
    public List<cActividadRequerimiento> ListarSolicitudEnviadosNP(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *\n"
                + "	FROM public.solnpac_estado inner join estado on solnpacestado_estado=estado_id JOIN usuario \n"
                + "	on solnpacestado_usuario=usuario_cedula where solnpacestado_solicitud='" + sol + "' order by solnpacestado_fecha desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solnpacestado_solicitud"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_cedula(rsComp.getString("usuario_cedula"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("solnpacestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("solnpacestado_observacion"));
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

    //Lista de envios por solicitud Obligaciones
    public List<cActividadRequerimiento> ListarSolicitudEnviadosOPC(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *\n"
                + "	FROM public.soldeudas_estado inner join estado on soldeudasestado_estado=estado_id JOIN usuario \n"
                + "	on soldeudasestado_usuario=usuario_cedula where soldeudasestado_solicitud='" + sol + "' order by soldeudasestado_fecha desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soldeudasestado_solicitud"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_cedula(rsComp.getString("usuario_cedula"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soldeudasestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("soldeudasestado_observacion"));
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

    //Lista de estructura presupuestaria
    public List<cActividadRequerimiento> ListarEstructuraReq(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listaestructurareq('" + req + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqcantidad"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("reqcostouni"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("reqcostosin"));
                        cComp.setReq_costo_total(rsComp.getDouble("reqcostototal"));
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

    //Lista de estructura presupuestaria obligaciones pendientes
    public List<cActividadRequerimiento> ListarEstructuraReqOPC(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listaestructurareq('" + req + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setPresupuesto_actividad(rsComp.getString("presupuestoactividad"));
                        cComp.setPresupuesto_programa(rsComp.getString("presupuestoprograma"));
                        cComp.setPresupuesto_proyecto(rsComp.getString("presupuestoproyecto"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("presupuestorenglo"));
                        cComp.setPresupuesto_fuente(rsComp.getString("presupuestofuente"));
                        cComp.setPresupuesto_presupuesto(rsComp.getString("presupuestopresupuesto"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqcantidad"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("reqcostouni"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("reqcostosin"));
                        cComp.setReq_costo_total(rsComp.getDouble("reqcostototal"));
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

    //Lista de envios por solicitud
    public List<cActividadRequerimiento> ListarSolicitudEnviadosUni(Integer sol) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *\n"
                + "	FROM public.soluni_estado inner join estado on soluniestado_estado=estado_id JOIN usuario \n"
                + "	on soluniestado_usuario=usuario_cedula where soluniestado_solicitud='" + sol + "' order by soluniestado_fecha desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soluniestado_solicitud"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_cedula(rsComp.getString("usuario_cedula"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soluniestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("soluniestado_observacion"));
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

    //Modificar Autoridades Unificar
    public String ModificarAutoridades(cActividadRequerimiento cComp) {
        String result = "Error al modificar la solicitud";
        String SQL = "UPDATE solicitud set solicitud_autoridades='" + cComp.getSolicitud_autoridades() + "' where solicitud_id='" + cComp.getSolicitud_id() + "'";

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

    //Modificar Autoridades Unificar
    public String ModificarDisponibilidad(cActividadRequerimiento cComp) {
        String result = "Error al modificar el requerimiento";
        String SQL = "UPDATE requerimiento set req_disponible='" + cComp.getReq_disponible() + "' where req_id='" + cComp.getReq_id() + "'";

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

    //Listar proyecto requerimientos analista
    public List<cActividadRequerimiento> ListarRequerimientosUnion(Integer estado, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarequnidades('" + estado + "', '" + anio + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqestadocantidad"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("reqestado_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("reqestado_costototal"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setReq_iva(rsComp.getInt("reqestadoiva"));
                        cComp.setUnidad_id(rsComp.getInt("unidadid"));
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

    //Insertar Envio de requerimientos
    public String EnviarRequerimientosUnidadesReg(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.requerimiento_estado(\n"
                + "	reqestado_estado, reqestado_req, reqestado_fecha, reqestado_usuario, reqestado_observacion)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getReq_id() + "', now(), '" + cComp.getUsuario_nombre() + "', '" + cComp.getSolestado_observacion() + "');";

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

    //Insertar Envio de requerimientos
    public String EnviarRequerimientosUnidadesRegDispo(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.requerimiento_estado(\n"
                + "	reqestado_estado, reqestado_req, reqestado_fecha, reqestado_usuario, reqestado_observacion, reqestado_solicitud)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getReq_id() + "', now(), '" + cComp.getUsuario_nombre() + "', '" + cComp.getSolestado_observacion() + "', '" + cComp.getSolicitud_id() + "');";

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

    //Insertar Envio de requerimientos
    public String EnviarRequerimientosUnidadesRegOP(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.obpend_estado(\n"
                + "	obpendestado_estado, obpendestado_req, obpendestado_fecha, obpendestado_usuario, obpendestado_observacion)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getReq_id() + "', now(), '" + cComp.getUsuario_nombre() + "', '" + cComp.getSolestado_observacion() + "');";

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

    //Codigo de justificativos
    public Integer codigoJustificativo(Integer anio) {
        Integer result = null;
        String SQL = "SELECT (MAX(solicitud_codigo)) as codigo FROM solicitud where solicitud_anio='" + anio + "';";
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

    //Codigo de solicitud
    public Integer codigoSolicitud(Integer anio) {
        Integer result = null;
        String SQL = "SELECT (MAX(solnpac_codigo)) as codigo FROM solicitud_nopac where solnpac_anio='" + anio + "';";
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

    //Codigo de solicitud obligaciones pendientes
    public Integer codigoSolicitudOPC(Integer anio) {
        Integer result = null;
        String SQL = "SELECT (MAX(soldeudas_codigo)) as codigo FROM solicitud_deudas where soldeudas_anio='" + anio + "';";
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

    //Codigo de justificativos unificados
    public Integer codigoJustificativoUnificados(Integer anio) {
        Integer result = null;
        String SQL = "SELECT (MAX(soluni_codigo)) as codigo FROM solicitud_unificados where soluni_anio='" + anio + "';";
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

    //Codigo de justificativos unificados numero oficio
    public Integer codigoJustificativoUnificadosNumero(Integer estado) {
        Integer result = null;
        String SQL = "SELECT (MAX(soluniestado_numero)) as codigo FROM soluni_estado where soluniestado_estado='" + estado + "';";
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

    //Insertar Envio de solicitud observacion
    public String EnviarSolicitudObser(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.solicitud_estado(\n"
                + "	solestado_estado, solestado_solicitud, solestado_usuario, solestado_fecha, solestado_observacion)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now(), '" + cComp.getSolestado_observacion() + "');";

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

    //Insertar Envio de solicitud observacion numero
    public String EnviarSolicitudObserN(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.solicitud_estado(\n"
                + "	solestado_estado, solestado_solicitud, solestado_usuario, solestado_fecha, solestado_observacion, solestado_numero)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now(), '" + cComp.getSolestado_observacion() + "', '" + cComp.getSolestado_numero() + "');";

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

    //Insertar Envio de solicitud observacion
    public String EnviarSolicitudObserUnif(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.soluni_estado(\n"
                + "	soluniestado_estado, soluniestado_solicitud, soluniestado_usuario, soluniestado_fecha, soluniestado_observacion)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now(), '" + cComp.getSolestado_observacion() + "');";

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

    //Insertar Envio de solicitud observacion numero
    public String EnviarSolicitudObserUnifNum(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.soluni_estado(\n"
                + "	soluniestado_estado, soluniestado_solicitud, soluniestado_usuario, soluniestado_fecha, soluniestado_observacion, soluniestado_numero)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now(), '" + cComp.getSolestado_observacion() + "', '" + cComp.getSolestado_numero() + "');";

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

    //Update de codigo
    public String CodigoJustificativo(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "UPDATE solicitud set solicitud_codigo='" + cComp.getSolicitud_codigo() + "', solicitud_fecha=now(), solicitud_pc='" + cComp.getTc_id() + "' where solicitud_id='" + cComp.getSolicitud_id() + "'";

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

    //Update de codigo
    public String CodigoJustificativoObserv(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "UPDATE solicitud set solicitud_codigo='" + cComp.getSolicitud_codigo() + "', solicitud_fecha=now(), solicitud_pc='" + cComp.getTc_id() + "', solicitud_pc_observacion='" + cComp.getActividad_responsable() + "' where solicitud_id='" + cComp.getSolicitud_id() + "'";

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

    //Update de codigo unificado
    public String CodigoJustificativoUnificado(cActividadRequerimiento cComp) {
        String result = "Error al aprobar el justificativo";
        String SQL = "UPDATE solicitud_unificados set soluni_codigo='" + cComp.getSolicitud_codigo() + "', soluni_fecha=now() where soluni_id='" + cComp.getSolicitud_id() + "'";

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

    //Eliminar de codigo
    public String EliminarFechaEnvio(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "DELETE FROM solicitud_estado WHERE solestado_solicitud='" + cComp.getSolicitud_id() + "' and solestado_estado='" + cComp.getSolicitud_estado() + "';";

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

    //Eliminar de codigo
    public String EliminarFechaEnvioUnifi(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "DELETE FROM soluni_estado WHERE soluniestado_solicitud='" + cComp.getSolicitud_id() + "' and soluniestado_estado='" + cComp.getSolicitud_estado() + "';";

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

    //Lista datos solicitud
    public List<cActividadRequerimiento> ListarSolicitudDatos(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listajustificativo('" + solicitud + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("idsolicitud"));
                        cComp.setSolicitud_nombre(rsComp.getString("solicitudnombre"));
                        cComp.setSolicitud_cedula(rsComp.getString("solicitudcustodio"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitudcargo"));
                        cComp.setSolestado_fecha(rsComp.getDate("solicitudfecha"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridadesnombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridadescargo"));
                        cComp.setAutoridades_id(rsComp.getInt("autoridadesid"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("solicitudcentro"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setAg_alias(rsComp.getString("agalias"));
                        cComp.setAg_id(rsComp.getInt("solicitudag"));
                        cComp.setUsuario_nombre(rsComp.getString("usuarionombre"));
                        cComp.setUsuario_cedula(rsComp.getString("usuariocedula"));
                        cComp.setUsuario_titulo(rsComp.getString("usuariotitulo"));
                        cComp.setTc_nombre(rsComp.getString("usuariocargo"));
                        cComp.setAutoridades_cedula(rsComp.getString("autoridadescedula"));
                        cComp.setTc_id(rsComp.getInt("procesoid"));
                        cComp.setAe_observacion(rsComp.getString("procesoobservacion"));
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

    //Lista datos directores
    public List<cActividadRequerimiento> ListarDirectoresPlaniUniC(Integer ag) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select ag_nombre, ag_alias, cargo_nombre, usuario_cedula, usuario_nombre, usuario_titulo from area_gestion inner join cargos on ag_id=cargo_ag join asignar_usuario on ag_id=au_ag join usuario on \n"
                + "au_usuario=usuario_cedula where ag_id='" + ag + "' and au_tu=5 and usuario_cedula not like '0606043867' and usuario_cedula not like '0202104717';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setAg_nombre(rsComp.getString("ag_nombre"));
                        cComp.setAg_alias(rsComp.getString("ag_alias"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setUsuario_cedula(rsComp.getString("usuario_cedula"));
                        cComp.setUsuario_titulo(rsComp.getString("usuario_titulo"));
                        cComp.setTc_nombre(rsComp.getString("cargo_nombre"));
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

    //Lista datos solicitud justificativo unificado
    public List<cActividadRequerimiento> ListarSolicitudDatosUnif(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listajustificativoUnif('" + solicitud + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("idsolicitud"));
                        cComp.setSolicitud_nombre(rsComp.getString("solicitudnombre"));
                        cComp.setSolicitud_cedula(rsComp.getString("solicitudcustodio"));
                        cComp.setSolicitud_cargo(rsComp.getString("solicitudcargo"));
                        cComp.setSolestado_fecha(rsComp.getDate("solicitudfecha"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridadesnombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridadescargo"));
                        cComp.setAutoridades_id(rsComp.getInt("autoridadesid"));
                        cComp.setSolicitud_codigo(rsComp.getString("solicitudcodigo"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("solicitudcentro"));
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setAg_alias(rsComp.getString("agalias"));
                        cComp.setAg_id(rsComp.getInt("solicitudag"));
                        cComp.setUsuario_nombre(rsComp.getString("usuarionombre"));
                        cComp.setUsuario_cedula(rsComp.getString("usuariocedula"));
                        cComp.setUsuario_titulo(rsComp.getString("usuariotitulo"));
                        cComp.setTc_nombre(rsComp.getString("usuariocargo"));
                        cComp.setAutoridades_cedula(rsComp.getString("autoridadescedula"));
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

    //Codigo Siguiente unificado
    public Integer codigoSiguienteUnificado() {
        Integer result = null;
        String SQL = "SELECT (MAX(reqeje_id)) as codigo FROM requerimiento_ejecucion;";
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

    //Comprobación de código
    public Boolean comporbacionCodigo(Integer solicitud) {
        Boolean result = false;
        String SQL = "select exists(select * from solicitud_nopac where solnpac_id='" + solicitud + "' and solnpac_codigo is not null);";
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

    //Comprobación de código obligacions pendientes
    public Boolean comporbacionCodigoOPC(Integer solicitud) {
        Boolean result = false;
        String SQL = "select exists(select * from solicitud_deudas where soldeudas_id='" + solicitud + "' and soldeudas_codigo is not null);";
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

    //Ingresar requerimientos unificados
    public String IngresarRequerimientosUnificados(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.requerimiento_ejecucion(\n"
                + "	reqeje_id, reqeje_nombre, reqeje_cantidad_anual, reqeje_costo_unitario, reqeje_costo_sin_iva, reqeje_estado, reqeje_oei, reqeje_descripcion, reqeje_unidad, reqeje_iva, reqeje_anio)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', "
                + "     '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getReq_verificacion() + "', '" + cComp.getPerspectiva_id() + "', '" + cComp.getReq_descripcion() + "', '" + cComp.getUnidad_id() + "', " + cComp.getReq_iva() + ", " + cComp.getMes_id() + ");";

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

    //Ingresar requerimientos unificados relacionado
    public String IngresarRequerimientosUnificadosRelacionado(cActividadRequerimiento cComp) {
        String result = "Error al ingresar los requerimientos";
        String SQL = "INSERT INTO public.unificado(\n"
                + "	unificado_pac, unificado_reqeje)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_id() + "');";

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

    //Listar requerimientos despues de la unificacion
    public List<cActividadRequerimiento> ListarRequerimientosUnificados(Integer estado, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from requerimiento_ejecucion left join solicitud_reqeje on reqeje_id=solreqeje_requerimiento join unidad on reqeje_unidad=unidad_id\n"
                + "where reqeje_estado='" + estado + "' and solreqeje_requerimiento is null and reqeje_anio='" + anio + "' order by reqeje_id;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setReq_id(rsComp.getInt("reqeje_id"));
                        cComp.setReq_nombre(rsComp.getString("reqeje_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqeje_descripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqeje_cantidad_anual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("reqeje_costo_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("reqeje_costo_sin_iva"));
                        cComp.setPerspectiva_id(rsComp.getInt("reqeje_oei"));
                        cComp.setUnidad_nombre(rsComp.getString("unidad_nombre"));
                        cComp.setUnidad_id(rsComp.getInt("unidad_id"));
                        cComp.setReq_iva(rsComp.getInt("reqeje_iva"));
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

    //Listar requerimientos que fueron unificados
    public List<cActividadRequerimiento> ListarRequerimientosUnificadosUnion(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarequeunificados('" + req + "', 2)";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqestadocantidad"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("reqestado_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("reqestado_costototal"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setReq_iva(rsComp.getInt("reqestadoiva"));
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

    //Listar requerimientos que fueron unificados
    public List<cActividadRequerimiento> ListarRequerimientosUnificadosUnionCP(Integer req, Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select *,  (select tcp_nombre from (select distinct on(cp_req) cp_req, cp_codigo, cp_valor, cp_fecha, cp_tipo, cp_req, cp_estado, tcp_id, tcp_nombre, cp_fecha_ingreso from(select * from certificacion_presupuestaria inner join tipo_cp on cp_tipo=tcp_id where cp_req=reqid and cp_solicitud='" + solicitud + "' order by cp_req, cp_fecha_ingreso desc, cp_id desc) as con) as con2) as estadocp from f_listarequeunificados('" + req + "', '1')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        adActividadRequerimiento adAct = new adActividadRequerimiento();
                        cComp.setAg_nombre(rsComp.getString("agnombre"));
                        cComp.setReq_id(rsComp.getInt("reqid"));
                        cComp.setReq_nombre(rsComp.getString("reqnombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqdescripcion"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqestadocantidad"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("reqestado_costounitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("reqestado_costototal"));
                        cComp.setReq_costo_total(rsComp.getDouble("req_costototal"));
                        cComp.setPerspectiva_id(rsComp.getInt("perspectivatp"));
                        cComp.setUnidad_nombre(rsComp.getString("unidadnombre"));
                        cComp.setReq_iva(rsComp.getInt("reqestadoiva"));
                        cComp.setEstado_nombre(rsComp.getString("estadocp"));
                        cComp.setPresupuesto_fuente(rsComp.getString("fuente"));
                        cComp.setPresupuesto_renglo(rsComp.getInt("item"));
                        cComp.setReq(adAct.ListarCertificaciones(rsComp.getInt("reqid"), solicitud));
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

    //Modificar requerimientos unificados
    public String ModificarRequerimientosUnificados(cActividadRequerimiento cComp) {
        String result = "Error al modificar el requerimiento";
        String SQL = "UPDATE requerimiento_ejecucion SET\n"
                + "	reqeje_nombre='" + cComp.getReq_nombre() + "', reqeje_cantidad_anual='" + cComp.getReq_cantidad() + "', reqeje_costo_unitario='" + cComp.getReq_costo_unitario() + "', reqeje_costo_sin_iva='" + cComp.getReq_costo_sin_iva() + "', "
                + "     reqeje_descripcion='" + cComp.getReq_descripcion() + "', reqeje_unidad='" + cComp.getUnidad_id() + "'\n"
                + "	WHERE reqeje_id='" + cComp.getReq_id() + "';";

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

    //Eliminar requerimientos unificados relacionado
    public String EliminarRequerimientosUnificadosRelacionado(cActividadRequerimiento cComp) {
        String result = "Error al ingresar los requerimientos";
        String SQL = "DELETE from public.unificado where unificado_reqeje='" + cComp.getReq_id() + "';";

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

    //Eliminar Requerimientos Unificado
    public String EliminarRequerimientosUnif(cActividadRequerimiento cComp) {
        String result = "Error al eliminar los requerimientos";
        String SQL = "DELETE FROM public.requerimiento_ejecucion\n"
                + "	WHERE reqeje_id='" + cComp.getReq_id() + "';";

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

    //Codigo Siguiente solicitud unificados
    public Integer codigoSiguienteUnificadosSol() {
        Integer result = null;
        String SQL = "SELECT (MAX(soluni_id)) as codigo FROM solicitud_unificados;";
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

    //Ingresar Solicitud
    public String IngresarSolicitudUnificados(cActividadRequerimiento cComp) {
        String result = "Error al ingresar la solicitud";
        String SQL = "INSERT INTO public.solicitud_unificados(\n"
                + "	soluni_id, soluni_ag, soluni_centro_costo, soluni_autoridad, soluni_custodio_cedula, soluni_custodio_nombre, soluni_custodio_cargo, soluni_anio)\n"
                + "	VALUES ('" + cComp.getSolicitud_id() + "', '" + cComp.getAg_id() + "', '" + cComp.getSolicitud_centro_costo() + "', '" + cComp.getSolicitud_autoridades() + "', "
                + "     '" + cComp.getSolicitud_cedula() + "', '" + cComp.getSolicitud_nombre() + "', '" + cComp.getSolicitud_cargo() + "', '" + cComp.getSolicitud_estado() + "');";

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

    //Insertar Solicitud - Requerimientos Ejecutar
    public String InsertarSolicitudReqEjecu(cActividadRequerimiento cComp) {
        String result = "Error al ingresar";
        String SQL = "INSERT INTO public.solicitud_reqeje(\n"
                + "	solreqeje_requerimiento, solreqeje_solicitud)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "');";

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

    //Eliminar Solicitud Unificado
    public String EliminarSolicitudUnif(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la solicitud";
        String SQL = "DELETE FROM public.solicitud_unificados\n"
                + "	WHERE soluni_id='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar certificacón
    public String EliminarCertificacion(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la certificacion";
        String SQL = "DELETE FROM public.certificacion_presupuestaria\n"
                + "	WHERE cp_id='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar certificacón servicios profesionales
    public String EliminarCertificacionSP(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la certificacion";
        String SQL = "DELETE FROM public.certificacion_presupuestaria_sp\n"
                + "	WHERE cpsp_id='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar certificacón servicios profesionales op
    public String EliminarCertificacionSPOP(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la certificacion";
        String SQL = "DELETE FROM public.certificacion_presupuestaria_sp_op\n"
                + "	WHERE cpspo_id='" + cComp.getSolicitud_id() + "';";

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

    //Eliminar certificacón
    public String EliminarCertificacionVP(cActividadRequerimiento cComp) {
        String result = "Error al eliminar la certificacion";
        String SQL = "DELETE FROM public.certificacion_presupuestaria_valores\n"
                + "	WHERE cpv_id='" + cComp.getSolicitud_id() + "';";

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

    //Lista de solicitudes por area
    public List<cActividadRequerimiento> ListarSolicitudAreasUnificados(Integer area, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudesunificados where soluni_ag='" + area + "' and soluni_anio='" + anio + "' order by soluniestado_estado desc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soluni_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("soluni_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("soluni_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("soluniestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soluniestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("soluniestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setSolicitud_cedula(rsComp.getString("soluni_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("soluni_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("soluni_custodio_cargo"));
                        cComp.setActividad_monto(montoJustificativoUnif(cComp));
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

    //Monto del justificativo Unificados
    public double montoJustificativoUnif(cActividadRequerimiento cC) {
        double result = 0.0;
        String SQL = "select sum(reqeje_costo_sin_iva) as monto from requerimiento_ejecucion inner join solicitud_reqeje on \n"
                + "solreqeje_requerimiento=reqeje_id where solreqeje_solicitud='" + cC.getSolicitud_id() + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getDouble("monto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Lista de requerimientos por solicitud unificados
    public List<cActividadRequerimiento> ListarSolicitudSolicitudUnificados(Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from requerimiento_ejecucion inner join solicitud_reqeje on reqeje_id=solreqeje_requerimiento join unidad on reqeje_unidad=unidad_id\n"
                + "where solreqeje_solicitud='" + solicitud + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("solreqeje_solicitud"));
                        cComp.setReq_id(rsComp.getInt("reqeje_id"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqeje_cantidad_anual"));
                        cComp.setReq_costo_unitario(rsComp.getDouble("reqeje_costo_unitario"));
                        cComp.setReq_costo_sin_iva(rsComp.getDouble("reqeje_costo_sin_iva"));
                        cComp.setUnidad_nombre(rsComp.getString("unidad_nombre"));
                        cComp.setReq_nombre(rsComp.getString("reqeje_nombre"));
                        cComp.setPerspectiva_id(rsComp.getInt("reqeje_oei"));
                        cComp.setReq_descripcion(rsComp.getString("reqeje_descripcion"));
                        cComp.setReq_iva(rsComp.getInt("reqeje_iva"));
                        cComp.setActividad_monto(montoJustificativoUnif(cComp));
                        cComp.setReq(ListarRequerimientosUnificadosUnionCP(rsComp.getInt("reqeje_id"), solicitud));
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

    //Modificar Solicitud Unificado
    public String ModificarSolicitudUnif(cActividadRequerimiento cComp) {
        String result = "Error al modificar la solicitud";
        String SQL = "UPDATE solicitud_unificados set soluni_centro_costo='" + cComp.getSolicitud_centro_costo() + "', soluni_autoridad='" + cComp.getSolicitud_autoridades() + "', "
                + "soluni_custodio_cedula='" + cComp.getSolicitud_cedula() + "', soluni_custodio_nombre='" + cComp.getSolicitud_nombre() + "', soluni_custodio_cargo='" + cComp.getSolicitud_cargo() + "' where soluni_id='" + cComp.getSolicitud_id() + "'";

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

    //Eliminar Requerimintos Solicitud unificados
    public String EliminarReqSolicitudUnif(cActividadRequerimiento cComp) {
        String result = "Error al eliminar los requerimientos";
        String SQL = "DELETE FROM public.solicitud_reqeje\n"
                + "	WHERE solreqeje_solicitud='" + cComp.getSolicitud_id() + "';";

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

    //Insertar Envio de solicitud
    public String EnviarSolicitudUnif(cActividadRequerimiento cComp) {
        String result = "Error al enviar los requerimientos";
        String SQL = "INSERT INTO public.soluni_estado(\n"
                + "	soluniestado_estado, soluniestado_solicitud, soluniestado_usuario, soluniestado_fecha)\n"
                + "	VALUES ('" + cComp.getSolicitud_estado() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getUsuario_nombre() + "', now());";

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

    //Lista de solicitudes para compras unificados
    public List<cActividadRequerimiento> ListarSolicitudComprasUnif(Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudescomprasunif where soluni_anio='" + anio + "' order by soluni_codigo asc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soluni_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("soluni_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("soluni_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("soluniestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soluniestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("soluniestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_cedula(rsComp.getString("soluni_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("soluni_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("soluni_custodio_cargo"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setAg_nombre(rsComp.getString("ag_nombre"));
                        cComp.setActividad_monto(montoJustificativoUnif(cComp));
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

    //Lista de solicitudes para compras unificados
    public List<cActividadRequerimiento> ListarSolicitudComprasUnifE() {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "SELECT *FROM public.vsolitiudescomprasunif where (soluniestado_estado<>1 and soluniestado_estado<>31 and soluniestado_estado<>33 and soluniestado_estado<>19) order by soluniestado_fecha asc, soluniestado_estado desc";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soluni_id"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("soluni_centro_costo"));
                        cComp.setSolicitud_codigo(rsComp.getString("soluni_codigo"));
                        cComp.setSolicitud_estado(rsComp.getInt("soluniestado_estado"));
                        cComp.setSolestado_fecha(rsComp.getTimestamp("soluniestado_fecha"));
                        cComp.setSolestado_observacion(rsComp.getString("soluniestado_observacion"));
                        cComp.setEstado_nombre(rsComp.getString("estado_nombre"));
                        cComp.setUsuario_nombre(rsComp.getString("usuario_nombre"));
                        cComp.setSolicitud_cedula(rsComp.getString("soluni_custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("soluni_custodio_nombre"));
                        cComp.setSolicitud_cargo(rsComp.getString("soluni_custodio_cargo"));
                        cComp.setSolicitud_autoridades(rsComp.getInt("autoridades_id"));
                        cComp.setAutoridades_nombre(rsComp.getString("autoridades_nombre"));
                        cComp.setAutoridades_cargo(rsComp.getString("autoridades_cargo"));
                        cComp.setAg_nombre(rsComp.getString("ag_nombre"));
                        cComp.setActividad_monto(montoJustificativoUnif(cComp));
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

    //Modificar Autoridades Unificar
    public String ModificarAutoridadesUnif(cActividadRequerimiento cComp) {
        String result = "Error al modificar la solicitud";
        String SQL = "UPDATE solicitud_unificados set soluni_autoridad='" + cComp.getSolicitud_autoridades() + "' where soluni_id='" + cComp.getSolicitud_id() + "'";

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

    //Lista requerimientos aprobados unificados
    public List<cActividadRequerimiento> ListarProyectosUnifAprobados(Integer area, Integer area1, Integer areaunif, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct(proyecto_id) as proyecto_id, proyecto_nombre, proyecto_ag from(select * from vreqcustodios where soluniestado_estado=31 and proycus_req is null and (ag_id='" + area + "' or are_ag_id='" + area1 + "') and soluni_ag='" + areaunif + "' and soluni_anio='" + anio + "' order by soluniestado_fecha desc, soluni_id, req_id)as con;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setProyecto_id(rsComp.getInt("proyecto_id"));
                        cComp.setProyecto_nombre(rsComp.getString("proyecto_nombre"));
                        cComp.setAg_id(rsComp.getInt("proyecto_ag"));
                        cComp.setReq(ListarReqUnifAprobados(cComp, areaunif));
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

    //Lista requerimientos aprobados unificados
    public List<cActividadRequerimiento> ListarReqUnifAprobados(cActividadRequerimiento cCom, Integer areaunif) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from vreqcustodios where soluniestado_estado=31 and proycus_req is null and proyecto_id='" + cCom.getProyecto_id() + "' and soluni_ag='" + areaunif + "' order by soluniestado_fecha desc, soluni_id, req_id;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setProyecto_id(rsComp.getInt("proyecto_id"));
                        cComp.setReq_id(rsComp.getInt("req_id"));
                        cComp.setReq_nombre(rsComp.getString("req_nombre"));
                        cComp.setReq_descripcion(rsComp.getString("reqeje_nombre"));
                        cComp.setSolicitud_codigo(rsComp.getString("soluni_codigo"));
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

    //Ingresar custodios
    public String IngresarCustodioUnificados(cActividadRequerimiento cComp) {
        String result = "Error al ingresar los custodios";
        String SQL = "INSERT INTO public.custodio(\n"
                + "	custodio_cedula, custodio_nombre, custodio_cargo, custodio_ubicacion)\n"
                + "	VALUES ('" + cComp.getSolicitud_cedula() + "', '" + cComp.getSolicitud_nombre() + "', '" + cComp.getSolicitud_cargo() + "', '" + cComp.getActividad_nombre() + "');";

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

    //Ingresar requerimientos unificados relacionado
    public String IngresarRequerimientosCustodios(cActividadRequerimiento cComp) {
        String result = "Error al ingresar los requerimientos";
        String SQL = "INSERT INTO public.proyecto_custodio(\n"
                + "	proycus_req, proycus_proyecto, proycus_custodio, proycus_estado)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getProyecto_id() + "', '" + cComp.getSolicitud_cedula() + "', '" + cComp.getSolicitud_estado() + "');";

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

    //Lista solicitud unificados para custodios
    public List<cActividadRequerimiento> ListaSolicitudesPlanificador(Integer ag, Integer agp, Integer anio) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct(soluni_id) as soluni_id, soluni_codigo, soluni_custodio_nombre, soluni_custodio_cedula, soluni_custodio_cargo from solicitud_unificados inner join soluni_estado on soluni_id=soluniestado_solicitud \n"
                + "	join solicitud_reqeje on soluni_id=solreqeje_solicitud join requerimiento_ejecucion on solreqeje_requerimiento=reqeje_id \n"
                + "	join unificado on reqeje_id=unificado_reqeje join requerimiento on unificado_pac=req_id join actividad on req_actividad=actividad_id \n"
                + "	join componente on actividad_componente=componente_id join area_gestion on componente_ag=ag_id where soluni_ag='" + ag + "' and soluniestado_estado=31 and soluni_anio='" + anio + "' and (ag_id='" + agp + "' or are_ag_id='" + agp + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_id(rsComp.getInt("soluni_id"));
                        cComp.setSolicitud_nombre(rsComp.getString("soluni_custodio_nombre"));
                        cComp.setSolicitud_cedula(rsComp.getString("soluni_custodio_cedula"));
                        cComp.setSolicitud_cargo(rsComp.getString("soluni_custodio_cargo"));
                        cComp.setSolicitud_codigo(rsComp.getString("soluni_codigo"));
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

    //Lista de custodios proyectos
    public List<cActividadRequerimiento> ListarProyectosCuatodios(Integer area, Integer solicitud, Integer area2) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct(proyecto_id) as proyecto_id, proyecto_nombre, custodio_cedula, custodio_nombre, custodio_cedula, custodio_ubicacion from(select * from vreqcustodiosingresados where (ag_id='" + area + "' or are_ag_id='" + area2 + "') and soluni_id='" + solicitud + "' and soluniestado_estado=31) as con ;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setProyecto_id(rsComp.getInt("proyecto_id"));
                        cComp.setProyecto_nombre(rsComp.getString("proyecto_nombre"));
                        cComp.setSolicitud_cedula(rsComp.getString("custodio_cedula"));
                        cComp.setSolicitud_nombre(rsComp.getString("custodio_nombre"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("custodio_ubicacion"));
                        cComp.setReq(ListarProyectosCuatodiosRequerimientos(cComp, solicitud));
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

    //Lista de custodios proyectos requerimientos
    public List<cActividadRequerimiento> ListarProyectosCuatodiosRequerimientos(cActividadRequerimiento cCom, Integer solicitud) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select distinct on (req_id) req_id, proyecto_id, proyecto_nombre, proyecto_ag, req_nombre, reqeje_id, reqeje_nombre, soluni_id, soluni_ag, soluni_codigo, soluni_fecha, \n"
                + "soluniestado_estado, soluniestado_fecha, proycus_req, proycus_proyecto, proycus_custodio, proycus_fecha, custodio_cedula, custodio_nombre, ag_id,\n"
                + "are_ag_id, custodio_ubicacion, req_cantidad_anual, reqestado_estado, reqestado_req, reqestado_observacion, reqestado_fecha, reqestado_usuario, reqestado_cantidad, reqestado_costo_unitario, reqestado_costo_total, \n"
                + "reqestado_iva from (select * from vreqcustodiosingresados inner join requerimiento_estado on req_id=reqestado_req where proyecto_id='" + cCom.getProyecto_id() + "' and soluni_id='" + solicitud + "' and soluniestado_estado=31 order by req_id, reqestado_fecha desc)as con order by req_id;";
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
                        cComp.setSolicitud_codigo(rsComp.getString("soluni_codigo"));
                        cComp.setReq_descripcion(rsComp.getString("reqeje_nombre"));
                        cComp.setReq_cantidad(rsComp.getDouble("reqestado_cantidad"));
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

    //Insertar Anulados
    public String InsertAnulados(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasado al planificado disponible. Por favor verifique el monto ingresado y la cantidad.";
        String SQL = "INSERT INTO public.anulados(\n"
                + "	anulado_requerimiento, anulado_solicitud, anulado_cantidad, anulado_costo_unitario, anulado_costo_sin_iva)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getReq_cantidad() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                } else {
                    result = "Error al ingresar.";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Insertar Anulados valores pendientes
    public String InsertAnuladosOP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasado al planificado disponible. Por favor verifique el monto ingresado y la cantidad.";
        String SQL = "INSERT INTO public.anulador_deudas(\n"
                + "	anulado_deudas, anulado_solicitud, anulado_costo_total, anulado_costo_iva, anulado_costo_anticipo)\n"
                + "	VALUES ('" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getReq_costo_unitario() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getReq_costo_total() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                } else {
                    result = "Error al ingresar.";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Eliminar solicitud en estados
    public String EliminarSolAnulados(cActividadRequerimiento cComp) {
        String result = "Error";
        String SQL = "update requerimiento_estado set reqestado_solicitud=null where reqestado_req='" + cComp.getReq_id() + "' and reqestado_solicitud='" + cComp.getSolicitud_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                } else {
                    result = "Error al ingresar.";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Ingresar certificación presupuestaria
    public String IngresarCertificacionP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria(\n"
                + "	cp_id, cp_codigo, cp_valor, cp_tipo, cp_req, cp_solicitud, cp_observacion, cp_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getFecha_inicio() + "');";

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

    //Ingresar certificación presupuestaria servicios profesionales
    public String IngresarCertificacionPSP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_sp(\n"
                + "	cpsp_id, cpsp_codigo, cpsp_valor, cpsp_tipo, cpsp_servprof, cpsp_observacion, cpsp_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getFecha_inicio() + "');";

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

    //Ingresar certificación presupuestaria servicios profesionales op
    public String IngresarCertificacionPSPOP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_sp_op(\n"
                + "	cpspo_id, cpspo_codigo, cpspo_valor, cpspo_tipo, cpspo_servprof, cpspo_observacion, cpspo_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getFecha_inicio() + "');";

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

    //Ingresar certificación presupuestaria
    public String IngresarCertificacionPRec(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria(\n"
                + "	cp_id, cp_codigo, cp_valor, cp_tipo, cp_req, cp_solicitud, cp_observacion, cp_recurrente, cp_fecha_ingreso, cp_liquidacion)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getAe_tiempo() + "', '" + cComp.getFecha_inicio() + "', '" + cComp.getUnidad_id() + "');";

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

    //Ingresar certificación presupuestaria servicios profesionales
    public String IngresarCertificacionPRecSP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_sp(\n"
                + "	cpsp_id, cpsp_codigo, cpsp_valor, cpsp_tipo, cpsp_servprof, cpsp_observacion, cpsp_recurrente, cpsp_fecha_ingreso, cpsp_liquidacion)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getAe_tiempo() + "', '" + cComp.getFecha_inicio() + "', '" + cComp.getUnidad_id() + "');";

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

    //Ingresar certificación presupuestaria servicios profesionales op
    public String IngresarCertificacionPRecSPOP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_sp_op(\n"
                + "	cpspo_id, cpspo_codigo, cpspo_valor, cpspo_tipo, cpspo_servprof, cpspo_observacion, cpspo_recurrente, cpspo_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getAe_tiempo() + "', '" + cComp.getFecha_inicio() + "');";

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

    //Ingresar certificación presupuestaria valores pendientes
    public String IngresarCertificacionPV(cActividadRequerimiento cComp, Integer tipo) {
        String result = "Error al ingresar";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_valores(\n"
                + "	cpv_id, cpv_codigo, cpv_valor_monto, cpv_fecha, cpv_tipo, cpv_deuda, cpv_solicitud, cpv_observacion, cpv_tipov, cpv_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', now(), '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getAe_observacion() + "', '" + tipo + "', '" + cComp.getFecha_inicio() + "');";

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

    //Ingresar certificación presupuestaria valores pendientes
    public String IngresarCertificacionPVRec(cActividadRequerimiento cComp, Integer tipo) {
        String result = "Error al ingresar";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_valores(\n"
                + "	cpv_id, cpv_codigo, cpv_valor_monto, cpv_fecha, cpv_tipo, cpv_deuda, cpv_solicitud, cpv_observacion, cpv_recurrente, cpv_tipov, cpv_fecha_ingreso, cpv_liquidacion)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', now(), '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getAe_tiempo() + "', '" + tipo + "', '" + cComp.getFecha_inicio() + "', '" + cComp.getUnidad_id() + "');";

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

    //Ingresar certificación presupuestaria
    public String IngresarCertificacionPComp(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria(\n"
                + "	cp_id, cp_codigo, cp_valor, cp_tipo, cp_req, cp_solicitud, cp_observacion, cp_porcentaje, cp_valor_porcentaje, cp_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getSolicitud_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getActividad_porcentaje() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getFecha_inicio() + "');";

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

    //Ingresar certificación presupuestaria servicio profesional
    public String IngresarCertificacionPCompSP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_sp(\n"
                + "	cpsp_id, cpsp_codigo, cpsp_valor, cpsp_tipo, cpsp_servprof, cpsp_observacion, cpsp_porcentaje, cpsp_valor_porcentaje, cpsp_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getActividad_porcentaje() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getFecha_inicio() + "');";

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

    //Ingresar certificación presupuestaria servicio profesional op
    public String IngresarCertificacionPCompSPOP(cActividadRequerimiento cComp) {
        String result = "Error, valor sobrepasa el disponible. Por favor verifique el monto ingresado.";
        String SQL = "INSERT INTO public.certificacion_presupuestaria_sp_op(\n"
                + "	cpspo_id, cpspo_codigo, cpspo_valor, cpspo_tipo, cpspo_servprof, cpspo_observacion, cpspo_porcentaje, cpspo_valor_porcentaje, cpspo_fecha_ingreso)\n"
                + "	VALUES ('" + cComp.getActividad_id() + "', '" + cComp.getReq_nombre() + "', '" + cComp.getReq_costo_total() + "', '" + cComp.getTc_id() + "', '" + cComp.getReq_id() + "', '" + cComp.getAe_observacion() + "', '" + cComp.getActividad_porcentaje() + "', '" + cComp.getReq_costo_sin_iva() + "', '" + cComp.getFecha_inicio() + "');";

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

    //Modificar certificación presupuestaria
    public String ModificarCertificacionP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria SET\n"
                + "	cp_codigo='" + cComp.getReq_nombre() + "', cp_valor='" + cComp.getReq_costo_total() + "', cp_tipo='" + cComp.getTc_id() + "', cp_observacion='" + cComp.getAe_observacion() + "', cp_fecha_ingreso='" + cComp.getFecha_inicio() + "' where\n"
                + "	cp_id='" + cComp.getActividad_id() + "';";

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

    //Modificar certificación presupuestaria servicios profesionales
    public String ModificarCertificacionPSP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria_sp SET\n"
                + "	cpsp_codigo='" + cComp.getReq_nombre() + "', cpsp_valor='" + cComp.getReq_costo_total() + "', cpsp_tipo='" + cComp.getTc_id() + "', cpsp_observacion='" + cComp.getAe_observacion() + "', cpsp_fecha_ingreso='" + cComp.getFecha_inicio() + "' where\n"
                + "	cpsp_id='" + cComp.getActividad_id() + "';";

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

    //Modificar certificación presupuestaria servicios profesionales op
    public String ModificarCertificacionPSPOP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria_sp_op SET\n"
                + "	cpspo_codigo='" + cComp.getReq_nombre() + "', cpspo_valor='" + cComp.getReq_costo_total() + "', cpspo_tipo='" + cComp.getTc_id() + "', cpspo_observacion='" + cComp.getAe_observacion() + "', cpspo_fecha_ingreso='" + cComp.getFecha_inicio() + "' where\n"
                + "	cpspo_id='" + cComp.getActividad_id() + "';";

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

    //Modificar certificación presupuestaria valores
    public String ModificarCertificacionPVP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria_valores SET\n"
                + "	cpv_codigo='" + cComp.getReq_nombre() + "', cpv_valor_monto='" + cComp.getReq_costo_total() + "', cpv_tipo='" + cComp.getTc_id() + "', cpv_observacion='" + cComp.getAe_observacion() + "', cpv_fecha_ingreso='" + cComp.getFecha_inicio() + "' where\n"
                + "	cpv_id='" + cComp.getActividad_id() + "';";

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

    //Modificar certificación presupuestaria
    public String ModificarCertificacionPRec(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria SET\n"
                + "	cp_codigo='" + cComp.getReq_nombre() + "', cp_valor='" + cComp.getReq_costo_total() + "', cp_tipo='" + cComp.getTc_id() + "', cp_observacion='" + cComp.getAe_observacion() + "', cp_recurrente='" + cComp.getAe_tiempo() + "', cp_fecha_ingreso='" + cComp.getFecha_inicio() + "', cp_liquidacion='" + cComp.getUnidad_id() + "' where\n"
                + "	cp_id='" + cComp.getActividad_id() + "';";

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

    //Modificar certificación presupuestaria servicio profesionales
    public String ModificarCertificacionPRecSP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria_sp SET\n"
                + "	cpsp_codigo='" + cComp.getReq_nombre() + "', cpsp_valor='" + cComp.getReq_costo_total() + "', cpsp_tipo='" + cComp.getTc_id() + "', cpsp_observacion='" + cComp.getAe_observacion() + "', cpsp_recurrente='" + cComp.getAe_tiempo() + "', cpsp_fecha_ingreso='" + cComp.getFecha_inicio() + "', cpsp_liquidacion='" + cComp.getUnidad_id() + "' where\n"
                + "	cpsp_id='" + cComp.getActividad_id() + "';";

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

    //Modificar certificación presupuestaria servicio profesionales op
    public String ModificarCertificacionPRecSPOP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria_sp_op SET\n"
                + "	cpspo_codigo='" + cComp.getReq_nombre() + "', cpspo_valor='" + cComp.getReq_costo_total() + "', cpspo_tipo='" + cComp.getTc_id() + "', cpspo_observacion='" + cComp.getAe_observacion() + "', cpspo_recurrente='" + cComp.getAe_tiempo() + "', cpspo_fecha_ingreso='" + cComp.getFecha_inicio() + "' where\n"
                + "	cpspo_id='" + cComp.getActividad_id() + "';";

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

    //Modificar certificación presupuestaria valores pendientes
    public String ModificarCertificacionPRecVP(cActividadRequerimiento cComp) {
        String result = "Error al modificar la certificación presupuestaria";
        String SQL = "UPDATE certificacion_presupuestaria_valores SET\n"
                + "	cpv_codigo='" + cComp.getReq_nombre() + "', cpv_valor_monto='" + cComp.getReq_costo_total() + "', cpv_tipo='" + cComp.getTc_id() + "', cpv_observacion='" + cComp.getAe_observacion() + "', cpv_recurrente='" + cComp.getAe_tiempo() + "', cpv_fecha_ingreso='" + cComp.getFecha_inicio() + "', cpv_liquidacion='" + cComp.getUnidad_id() + "' where\n"
                + "	cpv_id='" + cComp.getActividad_id() + "';";

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
