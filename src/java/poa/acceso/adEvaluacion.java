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
import poa.clases.cComponenteMeta;
import poa.clases.cProyecto;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adEvaluacion {

    private Exception error;

    public Exception getError() {
        return error;
    }

    /*Numero de Cuatrimestre
    Verifica en que cuatrimestre se encuentra la fecha actual y retorna el cuatrimestre*/
    public static Integer numeroCuatrimestre(Integer anio) {
        Integer rs = null;
        String SQL = "SELECT tiempos_cuatrimestre FROM tiempo\n"
                + "WHERE tiempos_ff >= now()::timestamp\n"
                + "AND tiempos_fi <= now()::timestamp and tiempos_anio='" + anio + "' order by tiempos_cuatrimestre desc;";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getInt("tiempos_cuatrimestre");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    /*Numero de cuatrimestres existentes*/
    public static Integer sumaCuatrimestre(Integer anio) {
        Integer rs = null;
        String SQL = "SELECT count(tiempos_cuatrimestre) from tiempo where tiempos_anio='" + anio + "';";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getInt("count");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    static public ResultSet ListaCuatriSelect(Integer cua, Integer anio) {
        ResultSet rs = null;
        String SQL = "select * from tiempo where tiempos_cuatrimestre<> '" + cua + "' and tiempos_anio='" + anio + "' order by tiempos_cuatrimestre desc;";
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

    /*Porcentaje de cuatrimestre
    Porcentaje general por cuatrimestre de cada componente
     */
    public Double porcentajeCuatrimestre(Integer componente, Integer cuatrimestre) {
        Double rs = null;
        String SQL = "select * from f_listaactividadesmeses('" + componente + "','" + cuatrimestre + "');";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getDouble("f_listaactividadesmeses");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Listar Actividad
    public List<cActividadRequerimiento> ListarActividad(Integer componente, Integer cuatri, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listaactividad('" + componente + "')";
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
                        cComp.setActividad_prioridad(rsComp.getInt("actividadprioridad"));
                        if (rsComp.getInt("actividadidid") == 0) {
                            cComp.setCuatri(ListarCuatrimestreRep(rsComp.getInt("actividadid")));
                            cComp.setMes(ListarMesesRep(rsComp.getInt("actividadid")));
                        } else {
                            cComp.setCuatri(ListarCuatrimestreRep(rsComp.getInt("actividadidid")));
                            cComp.setMes(ListarMesesRep(rsComp.getInt("actividadidid")));
                        }

                        cComp.setReq(ListarArchivosActividad(cComp, cuatri));
                        cComp.setActividad_eval(ListarActividadEval(cComp, cuatri));
                        cComp.setDeudas(ListarRequerimientoActe(rsComp.getInt("actividadid"), tipo));
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

    public List<cActividadRequerimiento> ListarMesesRep(Integer componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from actividad_mes where am_actividad='" + componente + "';";
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

    //Listar requerimiento
    public List<cActividadRequerimiento> ListarRequerimientoActe(Integer componente, Integer tipo) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listarrequerimientosform('" + componente + "', '" + tipo + "');";
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
    public List<cActividadRequerimiento> ListarArchivosActividad(cActividadRequerimiento componente, Integer cuatri) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from archivos_evaluacion where are_actividad='" + componente.getActividad_id() + "' and are_cuatrimestre='" + cuatri + "';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("are_actividad"));
                        cComp.setAre_id(rsComp.getInt("are_id"));
                        cComp.setAre_archivo(rsComp.getString("are_archivo"));
                        cComp.setAre_descripcion(rsComp.getString("are_descripcion"));
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

    //Listar actividad evaluación
    public List<cActividadRequerimiento> ListarActividadEval(cActividadRequerimiento componente, Integer cuatri) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "Select * from actividad_evaluacion where ae_cuatrimestre='" + cuatri + "' and ae_actividad='" + componente.getActividad_id() + "'";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setAe_ejecucion(rsComp.getDouble("ae_ejecucion"));
                        cComp.setActividad_nombre(rsComp.getString("ae_porcentaje"));
                        cComp.setAe_tiempo(rsComp.getInt("ae_tiempo"));
                        cComp.setAe_autoeval(rsComp.getDouble("ae_autoeval"));
                        cComp.setAe_observacion(rsComp.getString("ae_observacion"));
                        cComp.setAe_eval(rsComp.getDouble("ae_eval"));
                        cComp.setAe_evaluacion(rsComp.getDouble("ae_eval"));
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

    //Codigo Siguiente archivo
    public Integer codigoSiguienteArchivoEv() {
        Integer result = null;
        String SQL = "SELECT (MAX(are_id)) as codigo FROM archivos_evaluacion;";
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

    //Codigo Siguiente archivo
    public Integer codigoSiguienteArchivoEvDeudas() {
        Integer result = null;
        String SQL = "SELECT (MAX(de_id)) as codigo FROM deudas_evidencias;";
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

    //Ingresar Archivos
    public String IngresarArchivosEval(cActividadRequerimiento oProy) {
        String result = "Error al ingresar archivos";
        String SQL = "INSERT INTO public.archivos_evaluacion(\n"
                + "	are_id, are_actividad, are_cuatrimestre, are_descripcion, are_archivo)\n"
                + "	VALUES ('" + oProy.getAre_id() + "', '" + oProy.getActividad_id() + "', '" + oProy.getMes_id() + "','" + oProy.getAre_descripcion() + "', '" + oProy.getAre_archivo() + "');";

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

    //Ingresar Archivos Deudas
    public String IngresarArchivosEvalDeudas(cActividadRequerimiento oProy) {
        String result = "Error al ingresar archivos";
        String SQL = "INSERT INTO public.deudas_evidencias(\n"
                + "	de_id, de_deudas, de_nombre, de_archivo)\n"
                + "	VALUES  ('" + oProy.getAre_id() + "', '" + oProy.getActividad_id() + "', '" + oProy.getAre_descripcion() + "', '" + oProy.getAre_archivo() + "');";

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

    //Modificar Archivos
    public String ModificarArchivos(cActividadRequerimiento oProy) {
        String result = "Error al modificar archivos";
        String SQL = "UPDATE archivos_evaluacion SET are_descripcion='" + oProy.getAre_descripcion() + "' where are_id='" + oProy.getAre_id() + "';";

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

    public String ModificarArchivosEval(cActividadRequerimiento oProy) {
        String result = "Error al modificar";
        String SQL = "UPDATE deudas_evidencias SET de_nombre='" + oProy.getAre_descripcion() + "' where de_id='" + oProy.getAre_id() + "';";

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

    //Eliminar Archivos
    public String EliminarArchivos(cActividadRequerimiento oProy) {
        String result = "Error al eliminar archivos";
        String SQL = "DELETE FROM public.archivos_evaluacion\n"
                + "	WHERE are_id='" + oProy.getAre_id() + "';";

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

    //Ingresar Porcentaje de autoevaluacion
    public String IngresarAutoEvaluacion(cActividadRequerimiento oProy) {
        String result = "Error al ingresar archivos";
        String SQL = "INSERT INTO public.actividad_evaluacion(\n"
                + "	ae_actividad, ae_cuatrimestre, ae_autoeval)\n"
                + "	VALUES ('" + oProy.getActividad_id() + "', '" + oProy.getMe_cuatrimestre() + "', '" + oProy.getAe_autoeval() + "');";

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

    //Ingresar Porcentaje de autoevaluacion
    public String IngresarAutoEvaluacionDeudas(cActividadRequerimiento oProy) {
        String result = "Error al ingresar autoevaluación";
        String SQL = "INSERT INTO public.deudas_evaluacion(\n"
                + "	dev_deudas, dev_suma, dev_autoeval)\n"
                + "	VALUES ('" + oProy.getActividad_id() + "', '" + oProy.getActividad_monto() + "', '" + oProy.getAe_autoeval() + "');";

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

    //Modificar Porcentaje de autoevaluacion
    public String ModificarAutoEvaluacionDeudas(cActividadRequerimiento oProy) {
        String result = "Error al ingresar autoevaluación";
        String SQL = "UPDATE deudas_evaluacion\n"
                + "	set dev_suma='" + oProy.getActividad_monto() + "', dev_autoeval='" + oProy.getAe_autoeval() + "'\n"
                + "	WHERE dev_deudas='" + oProy.getActividad_id() + "';";

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

    //Modificar Porcentaje de evaluación
    public String ModificarEvaluacionDeudas(cActividadRequerimiento oProy) {
        String result = "Error al ingresar autoevaluación";
        String SQL = "UPDATE deudas_evaluacion\n"
                + "	set dev_evaluacion='" + oProy.getAe_evaluacion() + "', dev_porcentaje='" + oProy.getAe_porcentaje() + "'\n"
                + "	WHERE dev_deudas='" + oProy.getActividad_id() + "';";

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

    //lISTAR Porcentaje de autoevaluacion
    public List<cActividadRequerimiento> ListarAutoEvaluacionDeudas(cActividadRequerimiento oProy) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "Select * from deudas_evaluacion where dev_deudas='" + oProy.getActividad_id() + "';";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setActividad_id(rsComp.getInt("dev_deudas"));
                        cComp.setAe_autoeval(rsComp.getDouble("dev_autoeval"));
                        cComp.setAe_evaluacion(rsComp.getDouble("dev_evaluacion"));
                        cComp.setActividad_responsable(rsComp.getString("dev_porcentaje"));
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

    //Modificar Porcentaje de autoevaluacion
    public String ModificarAutoEvaluacion(cActividadRequerimiento oProy) {
        String result = "Error al modificar autoevaluación";
        String SQL = "UPDATE actividad_evaluacion SET ae_autoeval='" + oProy.getAe_autoeval() + "'\n"
                + "	WHERE ae_actividad='" + oProy.getActividad_id() + "' and ae_cuatrimestre='" + oProy.getMe_cuatrimestre() + "';";

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

    //Listar Metas
    public List<cComponenteMeta> ListarMetas(Integer componente, Integer cuatri) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from f_listametas('" + componente + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setMeta_id(rsComp.getInt("metaid"));
                        cComp.setMeta_nombre(rsComp.getString("metanombre"));
                        cComp.setMes(ListarMetasEval(cComp, cuatri));
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

    //Listar Evaluaciones
    public List<cComponenteMeta> ListarMetasEval(cComponenteMeta cCom, Integer cuatri) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from meta_evaluacion where me_meta='" + cCom.getMeta_id() + "' and me_cuatrimestre='" + cuatri + "'";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setMe_cuatrimestre(rsComp.getInt("me_cuatrimestre"));
                        cComp.setMe_porcentaje(rsComp.getDouble("me_porcentaje"));
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

    //Enviar proyectos
    public String EnviarProyectoEval(cProyecto oProy) {
        String result = "Error al ingresar archivos";
        String SQL = "INSERT INTO public.estado_evaluacion(\n"
                + "	ee_proyecto, ee_estado, ee_fecha, ee_usuario, ee_cuatrimestre)\n"
                + "	VALUES ('" + oProy.getProyecto_id() + "', '" + oProy.getEstado_id() + "', now(), '" + oProy.getUsuario_nombre() + "', '" + oProy.getDeudas_id() + "');";

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

    //Ingresar Observacion de evaluacion
    public String IngresarObservacionEval(cActividadRequerimiento oProy) {
        String result = "Error al ingresar archivos";
        String SQL = "UPDATE actividad_evaluacion SET ae_observacion='" + oProy.getAe_observacion() + "' where ae_actividad='" + oProy.getActividad_id() + "' and ae_cuatrimestre='" + oProy.getMe_cuatrimestre() + "'";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                } else {
                    String SQL2 = "INSERT INTO public.actividad_evaluacion(\n"
                            + "	ae_actividad, ae_cuatrimestre, ae_observacion)\n"
                            + "	VALUES ('" + oProy.getActividad_id() + "', '" + oProy.getMe_cuatrimestre() + "', '" + oProy.getAe_observacion() + "');";
                    if (ad.executeUpdate(SQL2) != 0) {
                        result = "Correcto";
                    }
                }
            }
            ad.desconectar();

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Listar Proyecto Cuatrimestre Evaluacion
    public List<cProyecto> ListarProyectoEvaluacion(Integer proyecto, Integer cuatri, Integer ag, Integer tu, Integer agpadre, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listarevaluaciongrafico('" + proyecto + "','" + cuatri + "','" + ag + "','" + tu + "','" + agpadre + "', '" + anio + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setPe_ejecucion(rsComp.getDouble("peejecucion"));
                        cComp.setPe_eficacia(rsComp.getDouble("peeficacia"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peeficiencia"));
                        cComp.setPe_efectividad(rsComp.getDouble("peefectividad"));
                        cComp.setProyecto_planificados(rsComp.getInt("nplanificados"));
                        cComp.setProy_cuatrimestre(rsComp.getInt("ncuatrimestre"));
                        cComp.setProy_enviados(rsComp.getInt("enviados"));
                        cComp.setProy_evaluados(rsComp.getInt("evaluados"));
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

    //Listar Para ejecución
    public List<cActividadRequerimiento> ListarSolicitudEjecucion(Integer actividad, Integer cuatrimestre) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select req_id, req_nombre from requerimiento inner join requerimiento_cuatrimestre on req_id=rm_req where req_actividad='" + actividad + "' and req_prioridad=1 and req_estado=1 and rm_mes='" + cuatrimestre + "';";
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
                        cComp.setDeudas(ListarEjecucionReq(rsComp.getInt("req_id")));
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

    //Listar ejecucion por requerimientos
    public List<cActividadRequerimiento> ListarEjecucionReq(Integer req) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select * from f_listadetallereq('" + req + "');";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cActividadRequerimiento cComp = new cActividadRequerimiento();
                        cComp.setSolicitud_codigo(rsComp.getString("justificativo"));
                        cComp.setSolestado_observacion(rsComp.getString("reqreforma"));
                        cComp.setCp_tipo(rsComp.getString("tipocp"));
                        cComp.setCp_valor(rsComp.getDouble("certificacionmonto"));
                        cComp.setSolicitud_centro_costo(rsComp.getString("certificacion"));
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

    //Ingresar evaluacion
    public String IngresarEvaluacion(cActividadRequerimiento oProy) {
        String result = "Error al evaluar";
        String SQL = "select * from f_ingresar_evaluacion('" + oProy.getActividad_id() + "', '" + oProy.getMe_cuatrimestre() + "', '" + oProy.getAe_autoeval() + "', '" + oProy.getAe_ejecucion() + "', '" + oProy.getAe_tiempo() + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_ingresar_evaluacion");
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
