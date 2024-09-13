/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cTecho;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adTecho {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Ingresar Techo
    public String ingresarTechoInstitucional(cTecho oTecho) {
        String result = "Error al ingresar el techo, compruebe si el año que está ingresando no está ingresado";
        String SQL = "INSERT INTO public.techo_institucional(\n"
                + "	ti_monto_inicial, ti_fecha, ti_total)\n"
                + "	VALUES ('" + oTecho.getTecho_inicial() + "', '" + oTecho.getTecho_fecha() + "', '" + oTecho.getTecho_total() + "');";

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

    //Modificar Techo
    public String modificarTechoInstitucional(cTecho oTecho) {
        String result = "Error";
        String SQL = "UPDATE techo_institucional set ti_monto_inicial='" + oTecho.getTecho_inicial() + "', ti_total='" + oTecho.getTecho_total() + "' where ti_fecha='" + oTecho.getTecho_fecha() + "';";

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

    //Listar Techo
    public List<cTecho> listarTechoIns() {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "select * from techo_institucional;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_fecha(rsTecho.getInt("ti_fecha"));
                        oTecho.setTecho_inicial(rsTecho.getDouble("ti_monto_inicial"));
                        oTecho.setTecho_reforma(rsTecho.getDouble("ti_reforma"));
                        oTecho.setTecho_signo(rsTecho.getString("ti_signo"));
                        oTecho.setTecho_total(rsTecho.getDouble("ti_total"));
                        result.add(oTecho);
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

    //Mostrar año
    static public ResultSet listaAnioInstitucional() {
        ResultSet rs = null;
        String SQL = "select ti_fecha from techo_institucional order by ti_fecha desc";
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

    //Mostrar tipo proyecto
    static public ResultSet listaTipoProyecto() {
        ResultSet rs = null;
        String SQL = "select * from tipo_proyecto order by tp_id asc";
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

    //Ingresar Techo Unidad
    public String ingresarTechoUnidad(cTecho oTecho) {
        String result = "Error al ingresar, compruebe si el tipo ya fue ingresado para esa unidad o si la cantidad ingresada esta disponible";
        String SQL = "SELECT public.f_ingresartecho(\n"
                + "	'" + oTecho.getTecho_inicial() + "', '" + oTecho.getTecho_fecha() + "', '" + oTecho.getTecho_ag() + "', '" + oTecho.getTecho_tp() + "')";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_ingresartecho");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar Techo Unidad
    public String modificarTechoUnidad(cTecho oTecho) {
        String result = "Error al modificar, compruebe si el tipo ya fue ingresado para esa unidad o si la cantidad ingresada esta disponible";
        String SQL = "UPDATE techo set techo_monto_inicial='" + oTecho.getTecho_inicial() + "', techo_total='" + oTecho.getTecho_inicial() + "' where techo_ti_fecha='" + oTecho.getTecho_fecha() + "' and techo_ag='" + oTecho.getTecho_ag() + "' and techo_tp='" + oTecho.getTecho_tp() + "';";

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

    //Listar Area gestion
    public List<cTecho> listarAreaGestionTecho(Integer area, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL;
        if (area == 1 && anio>2023) {
            SQL = "SELECT distinct(ag_id) ag_id, ag_nombre FROM public.techo inner join area_gestion on techo_ag=ag_id and (ag_tag=2 or ag_tag=4 or ag_tag=5) and techo_ti_fecha='"+anio+"' and  ag_estado=1 order by ag_id;";
        } else {
            SQL = "SELECT distinct(ag_id) ag_id, ag_nombre FROM public.techo inner join area_gestion on techo_ag=ag_id and are_ag_id=" + area + " order by ag_id;";
        }
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_ag(rsTecho.getInt("ag_id"));
                        oTecho.setAg_nombre(rsTecho.getString("ag_nombre"));
                        oTecho.setTecho(listarTechoporUnidad(oTecho, anio));
                        result.add(oTecho);
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

    //Listar Techos por unidades
    public List<cTecho> listarTechoporUnidad(cTecho oTechoU, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT * FROM public.techo inner join area_gestion on techo_ag=ag_id join tipo_proyecto on techo_tp=tp_id where techo_ag='" + oTechoU.getTecho_ag() + "' and techo_ti_fecha='" + anio + "' order by techo_tp;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_fecha(rsTecho.getInt("techo_ti_fecha"));
                        oTecho.setTecho_inicial(rsTecho.getDouble("techo_monto_inicial"));
                        oTecho.setTecho_reforma(rsTecho.getDouble("techo_reforma"));
                        oTecho.setTecho_signo(rsTecho.getString("techo_signo"));
                        oTecho.setTecho_total(rsTecho.getDouble("techo_total"));
                        oTecho.setTecho_ag(rsTecho.getInt("techo_ag"));
                        oTecho.setTecho_tp(rsTecho.getInt("techo_tp"));
                        oTecho.setTp_nombre(rsTecho.getString("tp_nombre"));
                        result.add(oTecho);
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

    public static List<cTecho> ListarTechoTotalPresupuestarioPorUnidad(Integer intCodigoUnidad, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT techo.techo_tp, tipo_proyecto.tp_nombre, techo.techo_total\n"
                + "	FROM public.techo\n"
                + "	LEFT JOIN tipo_proyecto ON tipo_proyecto.tp_id=techo.techo_tp\n"
                + "	WHERE techo.techo_ag=" + intCodigoUnidad + " and techo.techo_ti_fecha=" + anio + "";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_tp(rsTecho.getInt("techo_tp"));
                        oTecho.setTp_nombre(rsTecho.getString("tp_nombre"));
                        oTecho.setTecho_total(rsTecho.getDouble("techo_total"));
                        result.add(oTecho);
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

    public static List<cTecho> ListarTechoAsignadoAcarreraPorUnidad(Integer intCodigoUnidad, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT techo.techo_tp, SUM (techo.techo_total) AS \"asignado_carrera\"\n"
                + "FROM public.techo\n"
                + "JOIN area_gestion ON area_gestion.ag_id= techo.techo_ag\n"
                + "JOIN tipo_proyecto ON tipo_proyecto.tp_id = techo.techo_tp\n"
                + "WHERE area_gestion.are_ag_id=" + intCodigoUnidad + " and techo_ti_fecha=" + anio + "\n"
                + "GROUP BY techo.techo_tp, tipo_proyecto.tp_nombre";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_tp(rsTecho.getInt("techo_tp"));
                        oTecho.setTecho_asignado_carrera(rsTecho.getDouble("asignado_carrera"));
//                        System.out.println("Tipo:"+ oTecho.getTecho_tp() +"; Asignado Carrera: "+oTecho.getAsignado_carrera());
                        result.add(oTecho);
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

    public static List<cTecho> ListarTechoPlanificadoPorUnidad(Integer intCodigoUnidad, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT tp_id, (SELECT SUM(deudas_iva+deudas_monto_pendiente) AS planificadodeudas\n"
                + "                FROM deudas WHERE deudas_tipo=1 and deudas_ag='" + intCodigoUnidad + "' AND deudas_anio='" + anio + "' and deudas_financiamiento=1 AND deudas_estado=1 AND deudas_presupuesto like 'CORRIENTE' AND deudas_oei=tp_id) as planificadodeudas,\n"
                + "				(select SUM(req_costo_total) AS planificado from perspectiva \n"
                + "                                JOIN objetivo ON perspectiva_id=objetivo_perspectiva \n"
                + "                                JOIN actividad_presupuestaria ON objetivo_id=ap_objetivo \n"
                + "                                JOIN proyecto ON ap_id=proyecto_ap\n"
                + "                                JOIN componente ON proyecto_id=componente_proyecto\n"
                + "                                JOIN actividad ON componente_id=actividad_componente \n"
                + "                                JOIN requerimiento ON actividad_id=req_actividad \n"
                + "                                JOIN area_gestion ON ag_id=componente_ag\n"
                + "                                WHERE ag_id='" + intCodigoUnidad + "' AND proyecto_anio='" + anio + "' and perspectiva_tp=tp_id and req_financiamiento=1 AND actividad_estado=1 AND proyecto_estado=1 AND (req_estado=1 or req_estado=3) AND componente_estado=1 AND (proyecto_plurianual is null or (proyecto_plurianual=1 and req_anio='" + anio + "')))\n"
                + "                               FROM tipo_proyecto;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_tp(rsTecho.getInt("tp_id"));
                        oTecho.setTecho_planificado(rsTecho.getDouble("planificado"));
                        oTecho.setTecho_reforma(rsTecho.getDouble("planificadodeudas"));
                        result.add(oTecho);
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

    public static List<cTecho> ListarTechoPlanificadoPorUnidadPadreHijos(Integer intCodigoUnidad, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT tp_id, (SELECT SUM(deudas_iva+deudas_monto_pendiente) AS planificadodeudas\n"
                + "FROM deudas inner join area_gestion on deudas_ag=ag_id WHERE (ag_id='" + intCodigoUnidad + "' or are_ag_id='" + intCodigoUnidad + "') AND deudas_tipo=1 and deudas_anio='" + anio + "' and deudas_financiamiento=1 AND deudas_estado=1 AND deudas_presupuesto like 'CORRIENTE' AND deudas_oei=tp_id), SUM(req_costo_total) AS planificado\n"
                + "                                   FROM tipo_proyecto \n"
                + "                                        JOIN perspectiva ON tp_id=perspectiva_tp \n"
                + "                                       JOIN objetivo ON perspectiva_id=objetivo_perspectiva \n"
                + "                                       JOIN actividad_presupuestaria ON objetivo_id=ap_objetivo \n"
                + "                                          JOIN proyecto ON ap_id=proyecto_ap\n"
                + "                                               JOIN componente ON proyecto_id=componente_proyecto\n"
                + "                                               JOIN actividad ON componente_id=actividad_componente \n"
                + "                                               JOIN requerimiento ON actividad_id=req_actividad \n"
                + "                                               JOIN area_gestion ON ag_id=componente_ag\n"
                + "                                               WHERE (ag_id='" + intCodigoUnidad + "' OR are_ag_id='" + intCodigoUnidad + "') AND proyecto_anio='" + anio + "' and req_financiamiento=1 AND (req_estado=1 or req_estado=3) AND actividad_estado=1 AND proyecto_estado=1 AND componente_estado=1 AND (proyecto_plurianual is null or (proyecto_plurianual=1 and req_anio='" + anio + "'))\n"
                + "                                               GROUP BY tp_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_tp(rsTecho.getInt("tp_id"));
                        oTecho.setTecho_planificado(rsTecho.getDouble("planificado"));
                        oTecho.setTecho_reforma(rsTecho.getDouble("planificadodeudas"));
                        result.add(oTecho);
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

    public List<cTecho> ListarPresupuestoPriorizado(Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL;
        if (anio == 2020) {
            SQL = "SELECT * FROM public.vpresupuesto";
        } else if (anio == 2021) {
            SQL = "SELECT * FROM public.vpresupuesto2";
        } else if (anio == 2022) {
            SQL = "SELECT * FROM public.vpresupuesto3";
        } else if (anio == 2023) {
            SQL = "SELECT * FROM public.vpresupuesto4";
        } else {
            SQL = "SELECT * FROM public.vpresupuesto5";
        }
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_inicial(rsTecho.getDouble("institucional"));
                        oTecho.setTecho_planificado(rsTecho.getDouble("gestion"));
                        oTecho.setTecho_reforma(rsTecho.getDouble("otras"));
                        oTecho.setPresupuesto_aca(rsTecho.getDouble("plurianual"));
                        oTecho.setPresupuesto_ges(rsTecho.getDouble("obligaciones"));
                        oTecho.setPresupuesto_inv(rsTecho.getDouble("fuente998"));
                        if (anio == 2020) {
                            oTecho.setPresupuesto_vin(rsTecho.getDouble("sum"));
                        }
                        result.add(oTecho);
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

    public List<cTecho> ListarPresupuestoPriorizadoporUnidades(Integer tipo, Integer financiamiento) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT * from public.f_listatechos('" + tipo + "', '" + financiamiento + "', 2020) as (areaid integer, area text, asignado numeric, planificado numeric, academico numeric, investigacion numeric, vinculacion numeric, gestion numeric, total numeric, disponible numeric);";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setAg_nombre(rsTecho.getString("area"));
                        oTecho.setTecho_total(rsTecho.getDouble("asignado"));
                        oTecho.setTecho_planificado(rsTecho.getDouble("planificado"));
                        oTecho.setPresupuesto_aca(rsTecho.getDouble("academico"));
                        oTecho.setPresupuesto_inv(rsTecho.getDouble("investigacion"));
                        oTecho.setPresupuesto_vin(rsTecho.getDouble("vinculacion"));
                        oTecho.setPresupuesto_ges(rsTecho.getDouble("gestion"));
                        oTecho.setPresupuesto_total(rsTecho.getDouble("total"));
                        oTecho.setTecho_inicial(rsTecho.getDouble("disponible"));
                        result.add(oTecho);
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

    public List<cTecho> ListarPresupuestoPriorizadoporUnidadesR(Integer tipo, Integer financiamiento, Integer anio, String pres) {
        List<cTecho> result = new ArrayList<cTecho>();
        String presu;
        if (pres.equals("CORRIENTE")) {
            presu = "de_proyecto like '000'";
        } else {
            presu = "de_proyecto not like '000'";
        }
        String SQL = "SELECT *, (select sum(deudaacademico+deudainvestigacion+deudavinculacion+deudagestion) as deudas from (\n"
                + "select case when deudaacademico is null then 0.0 when deudaacademico is not null then deudaacademico end as deudaacademico,\n"
                + "case when deudainvestigacion is null then 0.0 when deudainvestigacion is not null then deudainvestigacion end as deudainvestigacion, \n"
                + "case when deudavinculacion is null then 0.0 when deudavinculacion is not null then deudavinculacion end as deudavinculacion,\n"
                + "case when deudagestion is null then 0.0 when deudagestion is not null then deudagestion end as deudagestion from (SELECT\n"
                + "                    distinct on (vd.ag_id) vd.ag_id, vd.agnombre,\n"
                + "                        CASE\n"
                + "                            WHEN vd.deudas_oei = 1 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio='" + anio + "' AND deudas_tipo=1)\n"
                + "                        END AS deudaacademico,\n"
                + "                		CASE\n"
                + "                            WHEN vd.deudas_oei = 2 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio='" + anio + "' AND deudas_tipo=1)\n"
                + "                        END AS deudainvestigacion,\n"
                + "                		CASE\n"
                + "                            WHEN vd.deudas_oei = 3 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio='" + anio + "' AND deudas_tipo=1)\n"
                + "                        END AS deudavinculacion,\n"
                + "                		CASE\n"
                + "                           WHEN vd.deudas_oei = 4 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio='" + anio + "' AND deudas_tipo=1)\n"
                + "                       END AS deudagestion\n"
                + "                  FROM vdeudas vd where deudas_anio='" + anio + "' and ag_id=areaid and deudas_tipo=1 order by vd.ag_id) as con) as con2) from public.f_listatechos('" + tipo + "', '" + financiamiento + "', '" + anio + "')  \n"
                + "								 as (areaid integer, area text, asignado numeric, planificado numeric, academico numeric, investigacion numeric, vinculacion numeric, gestion numeric, total numeric, disponible numeric);";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setAg_nombre(rsTecho.getString("area"));
                        oTecho.setTecho_total(rsTecho.getDouble("asignado"));
                        oTecho.setTecho_planificado(rsTecho.getDouble("planificado"));
                        oTecho.setPresupuesto_aca(rsTecho.getDouble("academico"));
                        oTecho.setPresupuesto_inv(rsTecho.getDouble("investigacion"));
                        oTecho.setPresupuesto_vin(rsTecho.getDouble("vinculacion"));
                        oTecho.setPresupuesto_ges(rsTecho.getDouble("gestion"));
                        oTecho.setPresupuesto_total(rsTecho.getDouble("total"));
                        oTecho.setTecho_inicial(rsTecho.getDouble("disponible"));
                        oTecho.setTecho_reforma(rsTecho.getDouble("deudas"));
                        result.add(oTecho);
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

    public Double ListarPresupuestoOEI(String tipo, Integer anio) {
        Double result = 0.0;
        String SQL;
        if (anio == 2022) {
            SQL = "select sum(sum) from(select sum(req_costototal) from f_listarequerimientosexcel22() where presupuestoprograma like '" + tipo + "' and (reqanio=" + anio + " or reqanio is null) union all \n"
                    + "SELECT sum(deudasmonto) FROM vdeudasmatriz22 where presupuestoprograma like '" + tipo + "' and presupuestofuente not like '998') as con";
        } else if (anio == 2023) {
            SQL = "select sum(sum) from(select sum(req_costototal) from f_listarequerimientosexcel23() where presupuestoprograma like '" + tipo + "' and (reqanio=" + anio + " or reqanio is null) union all \n"
                    + "SELECT sum(deudasmonto) FROM vdeudasmatriz23 where presupuestoprograma like '" + tipo + "' and presupuestofuente not like '998') as con";
        } else {
            SQL = "select sum(sum) from(select sum(req_costototal) from f_listarequerimientosexcel24() where presupuestoprograma like '" + tipo + "' and (reqanio=" + anio + " or reqanio is null) union all \n"
                    + "SELECT sum(deudasmonto) FROM vdeudasmatriz24 where presupuestoprograma like '" + tipo + "' and presupuestofuente not like '998') as con";
        }         
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
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        } finally {
            return result;
        }
    }

    public List<cTecho> ListarPresupuestoPriorizadoporUnidadesAuto(Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "select * from ((SELECT * from public.f_listatechos('1', '2', '" + anio + "') as (areaid integer, area text, asignado numeric, planificado numeric, academico numeric, investigacion numeric, vinculacion numeric, gestion numeric, total numeric, disponible numeric)) union all\n"
                + "			   SELECT * from public.f_listatechos('2', '2', '" + anio + "') as (areaid integer, area text, asignado numeric, planificado numeric, academico numeric, investigacion numeric, vinculacion numeric, gestion numeric, total numeric, disponible numeric))as con;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setAg_nombre(rsTecho.getString("area"));
                        oTecho.setTecho_total(rsTecho.getDouble("asignado"));
                        oTecho.setTecho_planificado(rsTecho.getDouble("planificado"));
                        oTecho.setPresupuesto_aca(rsTecho.getDouble("academico"));
                        oTecho.setPresupuesto_inv(rsTecho.getDouble("investigacion"));
                        oTecho.setPresupuesto_vin(rsTecho.getDouble("vinculacion"));
                        oTecho.setPresupuesto_ges(rsTecho.getDouble("gestion"));
                        oTecho.setPresupuesto_total(rsTecho.getDouble("total"));
                        oTecho.setTecho_inicial(rsTecho.getDouble("disponible"));
                        result.add(oTecho);
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

    //Obligaciones pendientes
    public List<cTecho> ListarPresupuestoObligaciones() {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = " SELECT\n"
                + "    distinct on (vd.ag_id) vd.ag_id, vd.agnombre,\n"
                + "        CASE\n"
                + "            WHEN vd.deudas_oei = 1 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre)\n"
                + "        END AS deudaacademico,\n"
                + "		CASE\n"
                + "            WHEN vd.deudas_oei = 2 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre)\n"
                + "        END AS deudainvestigacion,\n"
                + "		CASE\n"
                + "            WHEN vd.deudas_oei = 3 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre)\n"
                + "        END AS deudavinculacion,\n"
                + "		CASE\n"
                + "            WHEN vd.deudas_oei = 4 THEN (select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where vd.agnombre=agnombre)\n"
                + "        END AS deudagestion\n"
                + "   FROM vdeudas vd order by vd.ag_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setAg_nombre(rsTecho.getString("agnombre"));
                        oTecho.setPresupuesto_aca(rsTecho.getDouble("deudaacademico"));
                        oTecho.setPresupuesto_inv(rsTecho.getDouble("deudainvestigacion"));
                        oTecho.setPresupuesto_vin(rsTecho.getDouble("deudavinculacion"));
                        oTecho.setPresupuesto_ges(rsTecho.getDouble("deudagestion"));
                        result.add(oTecho);
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

    //Obligaciones pendientes
    public List<cTecho> ListarPresupuestoObligaciones20() {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = " SELECT\n"
                + "    distinct on (vd.ag_id) vd.ag_id, vd.agnombre,\n"
                + "        CASE\n"
                + "            WHEN vd.deudas_oei = 1 THEN (select sum(deudas_monto+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio=2020)\n"
                + "        END AS deudaacademico,\n"
                + "		CASE\n"
                + "            WHEN vd.deudas_oei = 2 THEN (select sum(deudas_monto+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio=2020)\n"
                + "        END AS deudainvestigacion,\n"
                + "		CASE\n"
                + "            WHEN vd.deudas_oei = 3 THEN (select sum(deudas_monto+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio=2020)\n"
                + "        END AS deudavinculacion,\n"
                + "		CASE\n"
                + "            WHEN vd.deudas_oei = 4 THEN (select sum(deudas_monto+deudas_iva) from vdeudas where vd.agnombre=agnombre AND deudas_anio=2020)\n"
                + "        END AS deudagestion\n"
                + "   FROM vdeudas vd where deudas_anio=2020 order by vd.ag_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setAg_nombre(rsTecho.getString("agnombre"));
                        oTecho.setPresupuesto_aca(rsTecho.getDouble("deudaacademico"));
                        oTecho.setPresupuesto_inv(rsTecho.getDouble("deudainvestigacion"));
                        oTecho.setPresupuesto_vin(rsTecho.getDouble("deudavinculacion"));
                        oTecho.setPresupuesto_ges(rsTecho.getDouble("deudagestion"));
                        result.add(oTecho);
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

    //Obligaciones pendientes
    static public Double ListarPresupuestoObligacionesA(Integer anio, Integer ag, String oei, Integer tipo) {
        Double result = null;
        String SQL;
        if (tipo == 1) {
            if (anio == 2022) {
                SQL = "SELECT sum(deudasmonto) FROM vdeudasmatriz22 where agid='" + ag + "' and presupuestoprograma like '" + oei + "' and presupuestofuente not like '998'";
            } else if (anio == 2023) {
                SQL = "SELECT sum(deudasmonto) FROM vdeudasmatriz23 where agid='" + ag + "' and presupuestoprograma like '" + oei + "' and presupuestofuente not like '998'";
            } else {
                SQL = "SELECT sum(deudasmonto) FROM vdeudasmatriz24 where agid='" + ag + "' and presupuestoprograma like '" + oei + "' and presupuestofuente not like '998'";
            }
        } else {
            if (anio == 2022) {
                SQL = "SELECT sum(deudasmonto) FROM vdeudasmatriz22 where agid='" + ag + "' and presupuestofuente like '998'";
            } else if (anio == 2023) {
                SQL = "SELECT sum(deudasmonto) FROM vdeudasmatriz23 where agid='" + ag + "' and presupuestofuente like '998'";
            } else {
                SQL = "SELECT sum(deudasmonto) FROM vdeudasmatriz24 where agid='" + ag + "' and presupuestofuente like '998'";
            }
        }
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    if (rsCodigo.next()) {
                        //rsCodigo.next();
                        result = rsCodigo.getDouble("sum");
                    } else {
                        result = 0.0;
                    }
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    //Plurianuales
    public List<cTecho> ListarPresupuestoPlurianuales(Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL;
        if (anio == 2020) {
            SQL = "select distinct on(vs.agid) agid, vs.agnombre_i, (select sum(req_costototal) from vsalvaguardar where reqanio=2021 and agid=vs.agid) from vsalvaguardar as vs where reqanio=2021";
        } else if (anio == 2021) {
            SQL = "select distinct on(vs.agid) agid, vs.agnombre_i, (select sum(req_costototal) from vsalvaguardar21 where reqanio>'" + anio + "' and agid=vs.agid) from vsalvaguardar21 as vs where reqanio>'" + anio + "'";
        } else if (anio == 2022) {
            SQL = "select distinct on(vs.agid) agid, vs.agnombre_i, (select sum(req_costototal) from vsalvaguardar22 where reqanio>'" + anio + "' and agid=vs.agid) from vsalvaguardar22 as vs where reqanio>'" + anio + "'";
        } else if (anio == 2023) {
            SQL = "select distinct on(vs.agid) agid, vs.agnombre_i, (select sum(req_costototal) from vsalvaguardar23 where reqanio>'" + anio + "' and agid=vs.agid) from vsalvaguardar23 as vs where reqanio>'" + anio + "'";
        } else {
            SQL = "select distinct on(vs.agid) agid, vs.agnombre_i, (select sum(req_costototal) from vsalvaguardar24 where reqanio>'" + anio + "' and agid=vs.agid) from vsalvaguardar24 as vs where reqanio>'" + anio + "'";
        }
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setAg_nombre(rsTecho.getString("agnombre_i"));
                        oTecho.setPresupuesto_total(rsTecho.getDouble("sum"));
                        result.add(oTecho);
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

    //Fecha de techo
    public Integer FechaTecho() {
        Integer rs = null;
        String SQL = "Select max(tf_fecha) as fecha from techo_financiamiento;";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getInt("fecha");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    public Double DeudaPlanificada(String ag, Integer anio) {
        Double rs = null;
        String SQL = "select sum(deudas_iva+deudas_monto_pendiente) from deudas inner join area_gestion on area_gestion.ag_id = deudas.deudas_ag where ag_nombre like '" + ag + "' and deudas_anio=" + anio + " and deudas_tipo=1";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getDouble("sum");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //FTecho asignado Institucional
    static public Double techoInstitucional(Integer anio) {
        Double rs = null;
        String SQL = "select ti_total from techo_institucional where ti_fecha='" + anio + "'";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getDouble("ti_total");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Techo asignado con priorizaciones
    static public Double techoPriorizado(Integer anio) {
        Double rs = null;
        String SQL = "select * from f_listatechostipos(4, 0,0,0, '" + anio + "');";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getDouble("f_listatechostipos");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Techo asignado con priorizaciones
    static public Double techoPriorizadoTipo(Integer tipo, Integer anio) {
        Double rs = null;
        String SQL = "select * from f_listatechostipos(" + tipo + "," + anio + ")";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getDouble("f_listatechostipos");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Techo asignado con priorizaciones
    static public Double obgligacionesComprometidos(Integer tipo, Integer anio) {
        Double rs = null;
        String SQL;
        if (anio == 2023) {
            if (tipo == 1) {
                SQL = " SELECT sum(vdeudasmatriz23.deudasmonto) AS sum\n"
                        + "           FROM vdeudasmatriz23\n"
                        + "          WHERE vdeudasmatriz23.presupuestofuente::text !~~ '998'::text and tipo like 'OBLIGACIONES PENDIENTES';";
            } else if (tipo == 2) {
                SQL = "SELECT sum(vdeudasmatriz23.deudasmonto) AS sum\n"
                        + "           FROM vdeudasmatriz23\n"
                        + "          WHERE vdeudasmatriz23.presupuestofuente::text !~~ '998'::text and tipo like 'COMPROMETIDOS';";
            } else {
                SQL = "SELECT sum(vdeudasmatriz23.deudasmonto) AS sum\n"
                        + "           FROM vdeudasmatriz23\n"
                        + "          WHERE vdeudasmatriz23.presupuestofuente::text ~~ '998'::text";
            }
        } else {
            if (tipo == 1) {
                SQL = " SELECT sum(vdeudasmatriz24.deudasmonto) AS sum\n"
                        + "           FROM vdeudasmatriz24\n"
                        + "          WHERE vdeudasmatriz24.presupuestofuente::text !~~ '998'::text and tipo like 'OBLIGACIONES PENDIENTES';";
            } else if (tipo == 2) {
                SQL = "SELECT sum(vdeudasmatriz24.deudasmonto) AS sum\n"
                        + "           FROM vdeudasmatriz24\n"
                        + "          WHERE vdeudasmatriz24.presupuestofuente::text !~~ '998'::text and tipo like 'COMPROMETIDOS';";
            } else {
                SQL = "SELECT sum(vdeudasmatriz24.deudasmonto) AS sum\n"
                        + "           FROM vdeudasmatriz24\n"
                        + "          WHERE vdeudasmatriz24.presupuestofuente::text ~~ '998'::text";
            }
        }
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getDouble("sum");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Techo obligaciones pendientes
    static public Double techoObligaciones(Integer anio) {
        Double rs = null;
        String SQL = "select sum(deudas_monto_pendiente+deudas_iva) from vdeudas where deudas_anio='" + anio + "';";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getDouble("sum");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    static public ResultSet ListaTipoSelectTecho(Integer ag, Integer anio) {
        ResultSet rs = null;
        String SQL = "select * from tipo_proyecto inner join techo on tp_id=techo_tp where tp_estado=1 and techo_ag='" + ag + "' and techo_ti_fecha='" + anio + "';";
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

    //Listar Techo unidad
    public List<cTecho> ListarTechoUnidad(Integer area, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT distinct(ag_id) ag_id, ag_nombre FROM area_gestion where are_ag_id=? order by ag_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, area) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_ag(rsTecho.getInt("ag_id"));
                        oTecho.setAg_nombre(rsTecho.getString("ag_nombre"));
                        oTecho.setTecho(ListarTechoporUnidad(oTecho, anio));
                        result.add(oTecho);
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

    //Listar Techos por unidades
    public List<cTecho> ListarTechoporUnidad(cTecho oTechoU, Integer anio) {
        List<cTecho> result = new ArrayList<cTecho>();
        String SQL = "SELECT * FROM public.techo inner join area_gestion on techo_ag=ag_id join tipo_proyecto on techo_tp=tp_id where techo_ag='" + oTechoU.getTecho_ag() + "' and techo_ti_fecha='" + anio + "' order by techo_tp;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cTecho oTecho = new cTecho();
                        oTecho.setTecho_fecha(rsTecho.getInt("techo_ti_fecha"));
                        oTecho.setTecho_inicial(rsTecho.getDouble("techo_monto_inicial"));
                        oTecho.setTecho_reforma(rsTecho.getDouble("techo_reforma"));
                        oTecho.setTecho_signo(rsTecho.getString("techo_signo"));
                        oTecho.setTecho_total(rsTecho.getDouble("techo_total"));
                        oTecho.setTecho_ag(rsTecho.getInt("techo_ag"));
                        oTecho.setTecho_tp(rsTecho.getInt("techo_tp"));
                        oTecho.setTp_nombre(rsTecho.getString("tp_nombre"));
                        result.add(oTecho);
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

    //Modificar Techo unidad
    public String ModificarTechoUnidad(cTecho oTecho, Integer ag, Integer tipo) {
        String result = "Error al modificar el techo";
        String SQL = "UPDATE public.techo\n"
                + "	SET techo_monto_inicial='" + oTecho.getTecho_inicial() + "', techo_reforma='" + oTecho.getTecho_reforma() + "', "
                + "techo_signo='" + oTecho.getTecho_signo() + "', techo_total='" + oTecho.getTecho_total() + "', techo_tp='" + oTecho.getTecho_tp() + "', techo_ag='" + oTecho.getTecho_ag() + "' WHERE  techo_ti_fecha='" + oTecho.getTecho_fecha() + "' and techo_tp='" + tipo + "' and techo_ag='" + ag + "';";

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
