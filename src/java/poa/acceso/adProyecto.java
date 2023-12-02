/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cActividadRequerimiento;
import poa.clases.cAreaGestion;
import poa.clases.cPerspectivaObjetivo;
import poa.clases.cProcesoAcciones;
import poa.clases.cProyecto;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adProyecto {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Listar Fecha de techo institucional
    public Integer listaFechaTechoIns() {
        Integer result = 0;
        String SQL = "Select max(ti_fecha) from techo_institucional";

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
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Mostrar Procesos
    static public ResultSet listarProcesosAutoeval(Integer tipo) {
        ResultSet rs = null;
        String SQL = "select * from proceso where proceso_estado=1 and proceso_tipo=? order by proceso_codigo asc";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelectPersObj(SQL, tipo) != 0) {
                    rs = ad.getRs();
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Ejecutar montos
    public String ejecutarActualizarMontos(Integer anio) {
        String rs = "Error";
        String SQL = "select * from f_actualizarmontosproy2021(?);";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelectPersObj(SQL, anio) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    rs = rsCodigo.getString("f_actualizarmontosproy2021");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //Mostrar Procesos
    static public ResultSet listarProcesosAutoevalActivos() {
        ResultSet rs = null;
        String SQL = "select * from proceso where proceso_estado=1 order by proceso_id asc";
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

    //Codigo Siguiente proyecto
    public Integer codigoSiguienteProy() {
        Integer result = null;
        String SQL = "SELECT (MAX(proyecto_id)) as codigo FROM proyecto;";
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

    //Codigo Siguiente integrantes
    public Integer codigoSiguienteIntegrantes() {
        Integer result = null;
        String SQL = "SELECT (MAX(integrante_id)) as codigo FROM integrantes;";
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

    //Ingresar Evaluacion carrera
    public String IngresarAcciones(cProyecto oProy) {
        String result = "Error al ingresar acciones";
        String SQL = "INSERT INTO acciones_mejora(\n"
                + "	am_proyecto, am_nombre, am_proceso, am_id)\n"
                + "	VALUES (?, ?, ? , ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSQLAcciones(SQL, oProy) != 0) {
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

    //Ingresar Integrantes
    public String IngresarIntegrantes(cProyecto oProy) {
        String result = "Verifique si el integrante no esta duplicado y que las fechas esten dentro del rango de fechas del proyecto";
        String SQL = "INSERT INTO public.integrantes(\n"
                + "	integrante_id, integrante_nombre, integrante_proyecto, integrante_cedula, integrante_tipo, integrante_fechai, integrante_fechaf, integrante_sexo, integrante_Tcontrato)\n"
                + "	VALUES ('" + codigoSiguienteIntegrantes() + "', '" + oProy.getProyecto_integrantes() + "', '" + oProy.getProyecto_id() + "', '"+oProy.getProyecto_responsable_ced()+"', '"+oProy.getIntegrante_tipo()+"', '"+oProy.getProyecto_fi()+"', '"+oProy.getProyecto_ff()+"', '"+oProy.getIntegrante_sexo()+"', '"+oProy.getIntegrante_tcontrato()+"');";

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
    
    //Ingresar Integrantes eliminados
    public String IngresarIntegrantesEliminados(cProyecto oProy) {
        String result = "Se debe actualizar las fechas";
        String SQL = "INSERT INTO public.integrantes_eliminados(\n"
                + "	intelim_nombre, intelim_proyecto, intelim_cedula, intelim_tipo, intelim_fechai, intelim_fechaf, intelim_sexo, intelim_Tcontrato, intelim_cedula_usuario)\n"
                + "	VALUES ('" + oProy.getProyecto_integrantes() + "', '" + oProy.getProyecto_id() + "', '"+oProy.getProyecto_responsable_ced()+"', '"+oProy.getIntegrante_tipo()+"', '"+oProy.getProyecto_fi()+"', '"+oProy.getProyecto_ff()+"', '"+oProy.getIntegrante_sexo()+"', '"+oProy.getIntegrante_tcontrato()+"', '"+oProy.getProyecto_responsable()+"');";

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

    //Codigo Siguiente proyecto
    public Integer codigoSiguienteAcciones() {
        Integer result = null;
        String SQL = "SELECT (MAX(am_id)) as codigo FROM acciones_mejora;";
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

    //Ingresar Proyecto
    public String IngresarProyecto(cProyecto cProy, Integer area) {
        String result = "Error al ingresar el proyecto";
        String SQL = "SELECT public.f_ingresar_proyecto('" + cProy.getProyecto_nombre() + "', '" + cProy.getProyecto_proposito() + "', '" + cProy.getProyecto_fin() + "', \n"
                + "	'" + cProy.getProyecto_fi() + "', '" + cProy.getProyecto_ff() + "', '" + cProy.getProyecto_doc() + "', '" + cProy.getProyecto_responsable() + "', '" + cProy.getProyecto_integrantes() + "', \n"
                + "	'" + cProy.getProyecto_ap() + "','{" + area + "}','" + cProy.getProyecto_multi() + "', '" + cProy.getProyecto_servicio() + "', '" + cProy.getProyecto_codigo() + "', '" + cProy.getProyecto_id() + "', '" + cProy.getTi_fecha() + "', '"+cProy.getProyecto_responsable_ced()+"');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_ingresar_proyecto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Verificacion de proyecto generado
    public Boolean VerificacionProyecto(Integer area, Integer anio) {
        Boolean result = false;
        String SQL = "select exists(select * from proyecto where proyecto_nombre like 'FORTALECIMIENTO DE LA GESTIÓN DE%' and proyecto_ag='" + area + "' and proyecto_estado=1 and proyecto_anio='" + anio + "');";

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

    //Ingresar Areas de gestion
    public String IngresarAreasProyecto(cProyecto oProy) {
        String result = "Error al ingresar area";
        String SQL = "INSERT INTO public.ag_proyecto(\n"
                + "	agp_proyecto, agp_ag)\n"
                + "	VALUES (?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateAreaPro(SQL, oProy) != 0) {
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

    //Ingresar reprogramación
    public String ModificarProyectoRep(cProyecto oProy) {
        String result = "Error al ingresar area";
        String SQL = "UPDATE proyecto SET proyecto_fi_rep='" + oProy.getProyecto_fi() + "', proyecto_ff_rep='" + oProy.getProyecto_ff() + "' where proyecto_id='" + oProy.getProyecto_id() + "';";

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

    //Ingresar reprogramación
    public String ModificarProyectoRepP(cProyecto oProy) {
        String result = "Error al ingresar area";
        String SQL = "UPDATE proyecto SET proyecto_fi_rep='" + oProy.getProyecto_fi() + "', proyecto_ff_rep='" + oProy.getProyecto_ff() + "', proyecto_perfil_rep='" + oProy.getProyecto_doc() + "' where proyecto_id='" + oProy.getProyecto_id() + "';";

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

    //Ingresar proyecto generado
    public String IngresarProyectoGenerado(cProyecto oProy) {
        String result = "Error al ingresar area";
        String SQL = "INSERT INTO public.proyecto(\n"
                + "	proyecto_id, proyecto_ap, proyecto_nombre, proyecto_fin, "
                + "proyecto_fi, proyecto_ff, proyecto_responsable, proyecto_multidiscip, "
                + "proyecto_proposito, proyecto_codigo, proyecto_ag, proyecto_anio)\n"
                + "	VALUES ('" + oProy.getProyecto_id() + "', '" + oProy.getProyecto_ap() + "', '" + oProy.getProyecto_nombre() + "', '" + oProy.getProyecto_fin() + "', "
                + "'" + oProy.getProyecto_fi() + "', '" + oProy.getProyecto_ff() + "', '" + oProy.getProyecto_responsable() + "', '" + oProy.getProyecto_multi() + "', "
                + "'" + oProy.getProyecto_proposito() + "', '" + oProy.getProyecto_codigo() + "', '" + oProy.getProyecto_ag() + "', '" + oProy.getProyecto_anio() + "');";

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

    //Listar Proyecto
    public List<cProyecto> ListarProyecto(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from proyecto  where proyecto_ag='" + area + "' and proyecto_anio='" + anio + "' and proyecto_estado=1 order by proyecto_id asc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_fin(rsProy.getString("proyecto_fin"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setProyecto_fi(rsProy.getString("proyecto_fi"));
                        oProy.setProyecto_ff(rsProy.getString("proyecto_ff"));
                        oProy.setProyecto_multi(rsProy.getBoolean("proyecto_multidiscip"));
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

    //Listar actividades proceso
    public List<cProcesoAcciones> ListarProcesoActividad(Integer proyecto, Integer tipo) {
        List<cProcesoAcciones> result = new ArrayList<cProcesoAcciones>();
        String SQL;
        if (tipo == 1) {
            SQL = "select * from acciones_mejora inner join proceso on am_proceso=proceso_codigo join actividad_proceso on am_nombre=actproceso_id where am_proyecto='" + proyecto + "';";
        } else {
            SQL = "select * from acciones_mejora inner join proceso on am_proceso=proceso_codigo join actividad_proceso on am_nombre=actproceso_id where am_proyecto='" + proyecto + "' and am_validar=true;";
        }
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProcesoAcciones oProy = new cProcesoAcciones();
                        oProy.setProceso_codigo(rsProy.getString("proceso_codigo"));
                        oProy.setProceso_nombre(rsProy.getString("proceso_nombre"));
                        oProy.setAm_codigo(rsProy.getString("actproceso_codigo"));
                        oProy.setAm_id(rsProy.getString("actproceso_id"));
                        oProy.setAm_meta(rsProy.getString("actproceso_meta"));
                        oProy.setAm_nombre(rsProy.getString("actproceso_nombre"));
                        oProy.setAm_responsable(rsProy.getString("actproceso_responsable"));
                        oProy.setAm_validar(rsProy.getBoolean("am_validar"));
                        oProy.setProceso_tipo(rsProy.getInt("am_id"));
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

    //Listar Actividad
    public List<cProcesoAcciones> ListarActividadProceso(String proceso) {
        List<cProcesoAcciones> result = new ArrayList<cProcesoAcciones>();
        String SQL = "select * from actividad_proceso where actproceso_proceso  like '" + proceso + "' and actproceso_estado=1 order by actproceso_codigo asc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProcesoAcciones oProy = new cProcesoAcciones();
                        oProy.setAm_id(rsProy.getString("actproceso_id"));
                        oProy.setAm_nombre(rsProy.getString("actproceso_nombre"));
                        oProy.setAm_meta(rsProy.getString("actproceso_meta"));
                        oProy.setAm_codigo(rsProy.getString("actproceso_codigo"));
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

    //Listar Proyecto completo
    public List<cProyecto> ListarProyectoC(Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select distinct on(proyecto_id) proyecto_id, proyecto_nombre, proyecto_doc, proyecto_fin, proyecto_monto, proyecto_responsable, pe_fecha, pe_usuario, estado_id, ag_id, ag_nombre, estado_nombre, pe_observacion from (select * from proyecto inner join area_gestion on proyecto_ag=ag_id left join proyecto_estado on proyecto_id=pe_proyecto left join estado on pe_estado=estado_id where proyecto_estado=1 and proyecto_anio='" + anio + "' order by proyecto_id, pe_fecha desc)as con";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_fin(rsProy.getString("proyecto_fin"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setEstado_id(rsProy.getInt("estado_id"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setProyecto_ag(rsProy.getInt("ag_id"));
                        oProy.setProyecto_fin(rsProy.getString("ag_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setIntegrantes(ListaIntegrantes(rsProy.getInt("proyecto_id")));
                        oProy.setProyecto_doc(rsProy.getString("proyecto_doc"));
                        oProy.setEstado_observacion(rsProy.getString("pe_observacion"));
                        oProy.setDeudas_contrato(rsProy.getString("pe_fecha"));
                        oProy.setUsuario_nombre(rsProy.getString("pe_usuario"));
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

    //Listar Proyecto completo evalucion
    public List<cProyecto> ListarProyectoCEval(Integer anio, Integer cuatrimestre) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL;
        if (cuatrimestre > 0) {
            SQL = "select distinct on(proyecto_id) proyecto_id, proyecto_nombre, ee_cuatrimestre, proyecto_responsable, proyecto_monto, ag_nombre, ee_usuario, ee_fecha, estado_id, estado_nombre, ee_cuatrimestre, ee_observacion from \n"
                    + "(select proyecto_id, proyecto_nombre, proyecto_responsable, proyecto_monto, ag_nombre, ee_usuario, ee_fecha, es.estado_id, es.estado_nombre, ee_cuatrimestre, ee_observacion from vproyecto pr left join estado_evaluacion on pr.proyecto_id=ee_proyecto left join estado es on ee_estado=es.estado_id where (pr.estado_id=17 or pr.estado_id=18) and proyecto_anio='" + anio + "' and (ee_cuatrimestre='" + cuatrimestre + "') order by pr.proyecto_id, ee_fecha desc) as con";
        } else {
            SQL = "select distinct on(proyecto_id) proyecto_id, proyecto_nombre, ee_cuatrimestre, proyecto_responsable, proyecto_monto, ag_nombre, ee_usuario, ee_fecha, estado_id, estado_nombre, ee_cuatrimestre, ee_observacion from \n"
                    + "(select proyecto_id, proyecto_nombre, proyecto_responsable, proyecto_monto, ag_nombre, ee_usuario, ee_fecha, es.estado_id, es.estado_nombre, ee_cuatrimestre, ee_observacion from vproyecto pr left join estado_evaluacion on pr.proyecto_id=ee_proyecto left join estado es on ee_estado=es.estado_id where (pr.estado_id=17 or pr.estado_id=18) and proyecto_anio='" + anio + "' order by pr.proyecto_id, ee_fecha desc) as con";
        }
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setEstado_id(rsProy.getInt("estado_id"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setProyecto_fin(rsProy.getString("ag_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setEstado_observacion(rsProy.getString("ee_observacion"));
                        oProy.setDeudas_contrato(rsProy.getString("ee_fecha"));
                        oProy.setUsuario_nombre(rsProy.getString("ee_usuario"));
                        oProy.setProy_cuatrimestre(rsProy.getInt("ee_cuatrimestre"));
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

    //Listar Proyecto
    public List<cProyecto> ListarProyectoMulti(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from proyecto where proyecto_ag='" + area + "' and proyecto_anio='" + anio + "' and proyecto_estado=1 and proyecto_multidiscip is true order by proyecto_id asc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_fin(rsProy.getString("proyecto_fin"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setProyecto_fi(rsProy.getString("proyecto_fi"));
                        oProy.setProyecto_ff(rsProy.getString("proyecto_ff"));
                        oProy.setProyecto_multi(rsProy.getBoolean("proyecto_multidiscip"));
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

    //Listar Deudas
    public List<cProyecto> ListarDeudas(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select distinct on(deudas_id) deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_contrato, deudas_tcon, \n"
                + "deudas_financiamiento, deudas_monto, deudas_ag, deudas_iva, deudas_presupuesto, financiamiento_nombre, tcon_nombre, de_estado, estado_nombre, deudas_tipo, deudas_anticipo, deudas_area, deudas_anio, deudas_monto_pendiente \n"
                + "from(select * from deudas inner join financiamiento on deudas_financiamiento=financiamiento_id join tipo_contratacion on deudas_tcon=tcon_id \n"
                + " left join deudas_estado on de_deudas=deudas_id left join estado on de_estado=estado_id where deudas_ag='" + area + "' and deudas_estado=1 and deudas_anio='" + anio + "' order by deudas_id asc, de_fecha desc) as con;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setDeudas_id(rsProy.getInt("deudas_id"));
                        oProy.setProyecto_nombre(rsProy.getString("deudas_proyecto"));
                        oProy.setDeudas_oei(rsProy.getInt("deudas_oei"));
                        oProy.setDeudas_proceso(rsProy.getString("deudas_nombre_proceso"));
                        oProy.setDeudas_contrato(rsProy.getString("deudas_contrato"));
                        oProy.setDeudas_tcon(rsProy.getInt("deudas_tcon"));
                        oProy.setDeudas_financiamiento(rsProy.getInt("deudas_financiamiento"));
                        oProy.setFinanciamiento_nombre(rsProy.getString("financiamiento_nombre"));
                        oProy.setTcon_nombre(rsProy.getString("tcon_nombre"));
                        oProy.setDeuda_monto_contrato(rsProy.getDouble("deudas_monto"));
                        oProy.setDeuda_monto_iva(rsProy.getDouble("deudas_iva"));
                        oProy.setDeudas_presupuesto(rsProy.getString("deudas_presupuesto"));
                        oProy.setEstado_id(rsProy.getInt("de_estado"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setDeudas_anticipo(rsProy.getDouble("deudas_anticipo"));
                        oProy.setTp_id(rsProy.getInt("deudas_tipo"));
                        oProy.setProyecto_id(rsProy.getInt("deudas_area"));
                        oProy.setDeudas_monto_pendiente(rsProy.getDouble("deudas_monto_pendiente"));
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

    //Listar Deudas evaluacion
    public List<cProyecto> ListarDeudasEval(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select distinct on(deudas_id) deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_monto, agnombre, deudas_iva, ag_id, deudas_tipo, deudas_anticipo, \n"
                + "deudas_monto_pendiente, dee_estado, estado_nombre from (select * from vdeudas left join deudas_estado_eval on deudas_id=dee_deudas left join estado on dee_estado=estado_id \n"
                + "where deudas_anio='" + anio + "' and ag_id='" + area + "' and deudas_estado=1 order by deudas_id, dee_fecha desc) as con;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setDeudas_id(rsProy.getInt("deudas_id"));
                        oProy.setProyecto_nombre(rsProy.getString("deudas_proyecto"));
                        oProy.setDeudas_oei(rsProy.getInt("deudas_oei"));
                        oProy.setDeudas_proceso(rsProy.getString("deudas_nombre_proceso"));
                        oProy.setDeuda_monto_contrato(rsProy.getDouble("deudas_monto"));
                        oProy.setDeuda_monto_iva(rsProy.getDouble("deudas_iva"));;
                        oProy.setEstado_id(rsProy.getInt("dee_estado"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setDeudas_anticipo(rsProy.getDouble("deudas_anticipo"));
                        oProy.setTp_id(rsProy.getInt("deudas_tipo"));
                        oProy.setProyecto_id(rsProy.getInt("ag_id"));
                        oProy.setDeudas_monto_pendiente(rsProy.getDouble("deudas_monto_pendiente"));
                        oProy.setEstado(ListarDeudasArchivos(rsProy.getInt("deudas_id")));
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

    //Listar Deudas evaluacion
    public List<cProyecto> ListarDeudasEvalL(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL;
        if (area != 0) {
            SQL = "select distinct on(deudas_id) deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_monto, agnombre, deudas_iva, ag_id, deudas_tipo, deudas_anticipo, \n"
                    + "deudas_monto_pendiente, dee_estado, estado_nombre from (select * from vdeudas inner join deudas_estado_eval on deudas_id=dee_deudas inner join estado on dee_estado=estado_id \n"
                    + "where deudas_anio='" + anio + "' and ag_id='" + area + "' and deudas_estado=1 order by deudas_id, dee_fecha desc) as con;";
        } else {
            SQL = "select distinct on(deudas_id) deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_monto, agnombre, deudas_iva, ag_id, deudas_tipo, deudas_anticipo, \n"
                    + "deudas_monto_pendiente, dee_estado, estado_nombre from (select * from vdeudas inner join deudas_estado_eval on deudas_id=dee_deudas inner join estado on dee_estado=estado_id \n"
                    + "where deudas_anio='" + anio + "' and deudas_estado=1 order by deudas_id, dee_fecha desc) as con;";
        }
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setDeudas_id(rsProy.getInt("deudas_id"));
                        oProy.setProyecto_nombre(rsProy.getString("deudas_proyecto"));
                        oProy.setDeudas_oei(rsProy.getInt("deudas_oei"));
                        oProy.setDeudas_proceso(rsProy.getString("deudas_nombre_proceso"));
                        oProy.setDeuda_monto_contrato(rsProy.getDouble("deudas_monto"));
                        oProy.setDeuda_monto_iva(rsProy.getDouble("deudas_iva"));;
                        oProy.setEstado_id(rsProy.getInt("dee_estado"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setDeudas_anticipo(rsProy.getDouble("deudas_anticipo"));
                        oProy.setTp_id(rsProy.getInt("deudas_tipo"));
                        oProy.setProyecto_id(rsProy.getInt("ag_id"));
                        oProy.setDeudas_monto_pendiente(rsProy.getDouble("deudas_monto_pendiente"));
                        oProy.setEstado(ListarDeudasArchivos(rsProy.getInt("deudas_id")));
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

    //Listar Deudas apobadas
    public List<cProyecto> ListarDeudasAprobadas(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from vdeudas where ag_id='" + area + "' and deudas_anio='" + anio + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setDeudas_id(rsProy.getInt("deudas_id"));
                        oProy.setProyecto_nombre(rsProy.getString("deudas_proyecto"));
                        oProy.setDeudas_oei(rsProy.getInt("deudas_oei"));
                        oProy.setDeudas_proceso(rsProy.getString("deudas_nombre_proceso"));
                        oProy.setDeudas_contrato(rsProy.getString("deudas_contrato"));
                        oProy.setDeudas_tcon(rsProy.getInt("deudas_tcon"));
                        oProy.setFinanciamiento_nombre(rsProy.getString("financiamiento_nombre"));
                        oProy.setDeuda_monto_contrato(rsProy.getDouble("deudas_monto_pendiente"));
                        oProy.setDeuda_monto_iva(rsProy.getDouble("deudas_iva"));
                        oProy.setDeudas_anticipo(rsProy.getDouble("deudas_anticipo"));
                        oProy.setDeudas_presupuesto(rsProy.getString("deudas_presupuesto"));
                        oProy.setDeudas_monto_pendiente(rsProy.getDouble("deudas_monto"));
                        oProy.setEstado_id(rsProy.getInt("de_estado"));
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

    //Listar archivos deudas
    public List<cProyecto> ListarDeudasArchivos(Integer deudas) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from deudas_evidencias where de_deudas='" + deudas + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setDeudas_id(rsProy.getInt("de_id"));
                        oProy.setDeudas_oei(rsProy.getInt("de_deudas"));
                        oProy.setDeudas_contrato(rsProy.getString("de_nombre"));
                        oProy.setDeudas_proceso(rsProy.getString("de_archivo"));
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

    //Listar Deudas administrativa
    public List<cProyecto> ListarDeudasL(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select distinct on(deudas_id) deudas_id, deudas_oei, deudas_proyecto, deudas_tipo, deudas_nombre_proceso, deudas_contrato, deudas_tcon, \n"
                + "deudas_financiamiento, deudas_monto, deudas_ag, deudas_iva, deudas_presupuesto, financiamiento_nombre, tcon_nombre, de_estado, estado_nombre, deudas_anticipo, deudas_monto_pendiente \n"
                + "from(select * from deudas inner join financiamiento on deudas_financiamiento=financiamiento_id join tipo_contratacion on deudas_tcon=tcon_id \n"
                + " inner join deudas_estado on de_deudas=deudas_id inner join estado on de_estado=estado_id where deudas_ag='" + area + "' and deudas_anio='" + anio + "' and deudas_estado=1 order by deudas_id asc, de_fecha desc) as con;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setDeudas_id(rsProy.getInt("deudas_id"));
                        oProy.setProyecto_nombre(rsProy.getString("deudas_proyecto"));
                        oProy.setDeudas_oei(rsProy.getInt("deudas_oei"));
                        oProy.setDeudas_proceso(rsProy.getString("deudas_nombre_proceso"));
                        oProy.setDeudas_contrato(rsProy.getString("deudas_contrato"));
                        oProy.setDeudas_tcon(rsProy.getInt("deudas_tcon"));
                        oProy.setDeudas_financiamiento(rsProy.getInt("deudas_financiamiento"));
                        oProy.setFinanciamiento_nombre(rsProy.getString("financiamiento_nombre"));
                        oProy.setTcon_nombre(rsProy.getString("tcon_nombre"));
                        oProy.setDeuda_monto_contrato(rsProy.getDouble("deudas_monto"));
                        oProy.setDeuda_monto_iva(rsProy.getDouble("deudas_iva"));
                        oProy.setDeudas_presupuesto(rsProy.getString("deudas_presupuesto"));
                        oProy.setEstado_id(rsProy.getInt("de_estado"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setDeudas_anticipo(rsProy.getDouble("deudas_anticipo"));
                        oProy.setDeudas_monto_pendiente(rsProy.getDouble("deudas_monto_pendiente"));
                        oProy.setTp_id(rsProy.getInt("deudas_tipo"));
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

    //Listar Deudas administrativa
    public List<cProyecto> ListarDeudasLA(Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select distinct on(deudas_id) deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_contrato, deudas_tcon, \n"
                + "deudas_financiamiento, deudas_monto, deudas_ag, deudas_iva, deudas_presupuesto, financiamiento_nombre, tcon_nombre, de_estado, estado_nombre, de_fecha, deudas_monto_pendiente, deudas_tipo, deudas_anticipo, \n"
                + "de_observacion, de_usuario from(select * from deudas inner join financiamiento on deudas_financiamiento=financiamiento_id join tipo_contratacion on deudas_tcon=tcon_id \n"
                + " inner join deudas_estado on de_deudas=deudas_id inner join estado on de_estado=estado_id where deudas_estado=1 and deudas_anio='" + anio + "' order by deudas_id asc, de_fecha desc) as con;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setDeudas_id(rsProy.getInt("deudas_id"));
                        oProy.setProyecto_nombre(rsProy.getString("deudas_proyecto"));
                        oProy.setDeudas_oei(rsProy.getInt("deudas_oei"));
                        oProy.setDeudas_proceso(rsProy.getString("deudas_nombre_proceso"));
                        oProy.setDeudas_contrato(rsProy.getString("deudas_contrato"));
                        oProy.setDeudas_tcon(rsProy.getInt("deudas_tcon"));
                        oProy.setDeudas_financiamiento(rsProy.getInt("deudas_financiamiento"));
                        oProy.setFinanciamiento_nombre(rsProy.getString("financiamiento_nombre"));
                        oProy.setTcon_nombre(rsProy.getString("tcon_nombre"));
                        oProy.setDeuda_monto_contrato(rsProy.getDouble("deudas_monto"));
                        oProy.setDeuda_monto_iva(rsProy.getDouble("deudas_iva"));
                        oProy.setDeudas_presupuesto(rsProy.getString("deudas_presupuesto"));
                        oProy.setEstado_id(rsProy.getInt("de_estado"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setDeudas_contrato(rsProy.getString("de_fecha"));
                        oProy.setEstado_observacion(rsProy.getString("de_observacion"));
                        oProy.setUsuario_nombre(rsProy.getString("de_usuario"));
                        oProy.setTp_id(rsProy.getInt("deudas_tipo"));
                        oProy.setDeudas_anticipo(rsProy.getDouble("deudas_anticipo"));
                        oProy.setDeudas_monto_pendiente(rsProy.getDouble("deudas_monto_pendiente"));
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

    //Listar Proyectos Plurianuales
    public List<cProyecto> ListarPlurianuales(Integer ag) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from vproyecto where (estado_id=17 or estado_id=18) and ag_id='" + ag + "' and proyecto_plurianual=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
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

    //Listar Proyectos por areas por tipos de usuario
    public List<cProyecto> ListarProyectoAreasTipoU(Integer tipou, Integer areapadre, Integer area, Integer tipop, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listaProyecto('" + tipou + "','" + areapadre + "','" + area + "','" + tipop + "', '" + anio + "') as datos(proyecto_id integer,proyecto_nombre text, proyecto_responsable text, proyecto_monto numeric, estado_nombre character varying, tp_nombre character varying, ag_nombre text, estado_id integer, ag_id integer, proyecto_multidiscip boolean) union all"
                + "   select * from f_listaProyectomul('" + tipou + "','" + areapadre + "','" + area + "','" + tipop + "', '" + anio + "') as datos(proyecto_id integer,proyecto_nombre text, proyecto_responsable text, proyecto_monto numeric, estado_nombre character varying, tp_nombre character varying, ag_nombre text, estado_id integer, ag_id integer, proyecto_multidiscip boolean);";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        cAreaGestion cAg = new cAreaGestion();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setTp_nombre(rsProy.getString("tp_nombre"));
                        cAg.setAg_nombre(rsProy.getString("ag_nombre"));
                        cAg.setAg_id(rsProy.getInt("ag_id"));
                        oProy.setProyecto_multi(rsProy.getBoolean("proyecto_multidiscip"));
                        oProy.setAg(cAg);
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

    //Listar Proyectos por areas por tipos de usuario
    public List<cProyecto> ListarProyectoAreasUnidades(Integer tipou, Integer areapadre, Integer area, Integer tipop, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listaProyectoUnidades('" + tipou + "','" + areapadre + "','" + area + "','" + tipop + "', '" + anio + "') as datos(proyecto_id integer,proyecto_nombre text, proyecto_responsable text, proyecto_monto numeric, estado_nombre character varying, tp_nombre character varying, ag_nombre text, estado_id integer, ag_id integer, proyecto_multidiscip boolean);";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        cAreaGestion cAg = new cAreaGestion();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setTp_nombre(rsProy.getString("tp_nombre"));
                        cAg.setAg_nombre(rsProy.getString("ag_nombre"));
                        cAg.setAg_id(rsProy.getInt("ag_id"));
                        oProy.setProyecto_multi(rsProy.getBoolean("proyecto_multidiscip"));
                        oProy.setAg(cAg);
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

    //Listar Proyecto Completo
    public List<cProyecto> ListarProyectoCompleto(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select distinct on (proyecto_id) proyecto_id, perspectiva_id, perspectiva_tp, perspectiva_nombre, objetivo_id, objetivo_nombre, tp_id, tp_nombre, \n"
                + "proyecto_nombre, proyecto_fin, proyecto_fi, proyecto_ff, proyecto_responsable, proyecto_multidiscip, proyecto_proposito, proyecto_doc, \n"
                + "proyecto_integrantes, proyecto_estado, proyecto_servicio, proyecto_monto, proyecto_ag, pe_estado, estado_nombre, pe_fecha, ap_id, ap_nombre, proyecto_plurianual, ag_nombre, proyecto_fi_rep, proyecto_ff_rep, proyecto_perfil_rep, proyecto_anio, ag_alias, ap_estado, proyecto_responsable_ced, perspectiva_tp from(select perspectiva_id, perspectiva_tp, perspectiva_nombre, objetivo_id, objetivo_nombre, tp_id, tp_nombre, proyecto_id, \n"
                + "proyecto_nombre, proyecto_fin, proyecto_fi, proyecto_ff, proyecto_responsable, proyecto_multidiscip, proyecto_proposito, proyecto_doc, \n"
                + "proyecto_integrantes, proyecto_estado, proyecto_servicio, proyecto_monto, proyecto_ag, pe_estado, estado_nombre, pe_fecha, ap_id, ap_nombre, proyecto_plurianual, ag_nombre, proyecto_fi_rep, proyecto_ff_rep, proyecto_perfil_rep, proyecto_anio, ag_alias, ap_estado, proyecto_responsable_ced from perspectiva inner join objetivo on perspectiva_id=objetivo_perspectiva join tipo_proyecto \n"
                + "on perspectiva_tp=tp_id join actividad_presupuestaria on objetivo_id=ap_objetivo join proyecto on ap_id=proyecto_ap join area_gestion on proyecto_ag=ag_id left join proyecto_estado on proyecto_id=pe_proyecto left join estado on pe_estado=estado_id where proyecto_id=? order by pe_fecha desc) as con;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        cAreaGestion cAg = new cAreaGestion();
                        cPerspectivaObjetivo oPers = new cPerspectivaObjetivo();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_fin(rsProy.getString("proyecto_fin"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setProyecto_fi(rsProy.getString("proyecto_fi"));
                        oProy.setProyecto_ff(rsProy.getString("proyecto_ff"));
                        oProy.setProyecto_fi_rep(rsProy.getString("proyecto_fi_rep"));
                        oProy.setProyecto_ff_rep(rsProy.getString("proyecto_ff_rep"));
                        oProy.setProyecto_doc(rsProy.getString("proyecto_doc"));
                        oProy.setProyecto_integrantes(rsProy.getString("proyecto_integrantes"));
                        oPers.setPerspectiva_id(rsProy.getInt("perspectiva_id"));
                        oPers.setPerspectiva_nombre(rsProy.getString("perspectiva_nombre"));
                        oPers.setPerspectiva_estado(rsProy.getInt("perspectiva_tp"));
                        oPers.setTo_id(rsProy.getInt("perspectiva_tp"));
                        oPers.setObjetivo_id(rsProy.getInt("objetivo_id"));
                        oPers.setObjetivo_nombre(rsProy.getString("objetivo_nombre"));
                        oPers.setAp_id(rsProy.getInt("ap_id"));
                        oPers.setAp_nombre(rsProy.getString("ap_nombre"));
                        oProy.setProyecto_proposito(rsProy.getString("proyecto_proposito"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setProyecto_multi(rsProy.getBoolean("proyecto_multidiscip"));
                        oProy.setEstado_id(rsProy.getInt("pe_estado"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setProyecto_proceso(rsProy.getString("proyecto_perfil_rep"));
                        cAg.setAg_id(rsProy.getInt("proyecto_ag"));
                        cAg.setAg_nombre(rsProy.getString("ag_nombre"));
                        cAg.setAg_alias(rsProy.getString("ag_alias"));
                        oProy.setProyecto_plurianual(rsProy.getInt("proyecto_plurianual"));
                        oProy.setProyecto_anio(rsProy.getInt("proyecto_anio"));
                        oProy.setProyecto_ag(rsProy.getInt("ap_estado"));
                        oProy.setProyecto_responsable_ced(rsProy.getString("proyecto_responsable_ced"));
                        oProy.setAg(cAg);
                        oProy.setEstado(ListarProyectoEstados(proy));
                        oProy.setAreas(ListarProyectoAreas(proy));
                        if (rsProy.getInt("proyecto_anio") >= 2022) {
                            oProy.setProceso(ListarProcesoActividad(proy, 2));
                        } else {
                            oProy.setProceso(ListarProyectoAcciones(proy));
                        }
                        oProy.setPer(oPers);
                        oProy.setMonto_proy(ListarMontosProyectos(proy));
                        oProy.setIntegrantes(ListaIntegrantes(proy));
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

    //Listar Estados
    public List<cProyecto> ListarProyectoEstados(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from proyecto_estado inner join estado on pe_estado=estado_id join usuario on pe_usuario=usuario_cedula where pe_proyecto=? order by pe_fecha desc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setEstado_id(rsProy.getInt("estado_id"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setEstado_observacion(rsProy.getString("pe_observacion"));
                        oProy.setEstado_fecha(rsProy.getTimestamp("pe_fecha"));
                        oProy.setUsuario_nombre(rsProy.getString("usuario_nombre"));
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

    //Listar integrantes
    public List<cProyecto> ListaIntegrantes(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from integrantes inner join tipo_integrante ON tipo_integrante.tin_id = integrantes.integrante_tipo inner join tipo_contrato on tcont_id=integrante_tcontrato where integrante_proyecto=? and integrante_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setEstado_id(rsProy.getInt("integrante_id"));
                        oProy.setProyecto_integrantes(rsProy.getString("integrante_nombre"));
                        oProy.setProyecto_responsable_ced(rsProy.getString("integrante_cedula"));
                        oProy.setProyecto_fi(rsProy.getString("integrante_fechai"));
                        oProy.setProyecto_ff(rsProy.getString("integrante_fechaf"));
                        oProy.setIntegrante_tipo(rsProy.getInt("integrante_tipo"));
                        oProy.setIntegrante_tipo_nombre(rsProy.getString("tin_descripcion"));
                        oProy.setIntegrante_sexo(rsProy.getString("integrante_sexo"));
                        oProy.setIntegrante_tipo_contrato(rsProy.getString("tcont_nombre"));
                        oProy.setIntegrante_tcontrato(rsProy.getInt("integrante_tcontrato"));
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

    //Listar Monto proyecto
    public List<cProyecto> ListarMontosProyectos(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from (select 2020 as anio, (select sum(req_costo_total) from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente \n"
                + "join requerimiento on actividad_id=req_actividad where componente_estado=1 and actividad_estado=1 and req_estado=1 and req_anio=2020 and proyecto_id='" + proy + "') as monto) as con union all\n"
                + "select * from (select 2021 as anio, (select sum(req_costo_total) from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente \n"
                + "join requerimiento on actividad_id=req_actividad where componente_estado=1 and actividad_estado=1 and req_estado=1 and req_anio=2021 and proyecto_id='" + proy + "') as monto) as con2;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setMp_anio(rsProy.getInt("anio"));
                        oProy.setMp_monto(rsProy.getDouble("monto"));
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

    //Listar Area gestion proyecto
    public List<cAreaGestion> ListarProyectoAreas(Integer proy) {
        List<cAreaGestion> result = new ArrayList<cAreaGestion>();
        String SQL = "(select ag_id, ag_nombre, ag_alias from perspectiva inner join objetivo on perspectiva_id=objetivo_perspectiva join actividad_presupuestaria on objetivo_id=ap_objetivo join proyecto on ap_id=proyecto_ap join area_gestion on proyecto_ag=ag_id where (perspectiva_tp=1 or perspectiva_tp=4) and proyecto_id='" + proy + "')\n"
                + "union all (select ag_id, ag_nombre, ag_alias from proyecto inner join ag_proyecto on proyecto_id=agp_proyecto join area_gestion on agp_ag=ag_id where proyecto_id='" + proy + "') ";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cAreaGestion oProy = new cAreaGestion();
                        oProy.setAg_id(rsProy.getInt("ag_id"));
                        oProy.setAg_nombre(rsProy.getString("ag_nombre"));
                        oProy.setAg_alias(rsProy.getString("ag_alias"));
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

    //Listar Acciones de mejora
    public List<cProcesoAcciones> ListarProyectoAcciones(Integer proy) {
        List<cProcesoAcciones> result = new ArrayList<cProcesoAcciones>();
        String SQL = "select * from proyecto inner join acciones_mejora on am_proyecto=proyecto_id join proceso on am_proceso=proceso_codigo \n"
                + "where proyecto_id=?;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProcesoAcciones oProy = new cProcesoAcciones();
                        oProy.setProceso_codigo(rsProy.getString("proceso_codigo"));
                        oProy.setAm_nombre(rsProy.getString("am_nombre"));
                        oProy.setAm_id(rsProy.getString("am_id"));
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

    public static cProyecto obtenerProyectoPorId(Integer intIdProyecto) {
        cProyecto objProyecto = new cProyecto();
        String SQL = "SELECT *\n"
                + "FROM public.proyecto\n"
                + "WHERE proyecto_id=" + intIdProyecto + ";";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        objProyecto.setProyecto_id(rsProy.getInt("proyecto_id"));
                        objProyecto.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        objProyecto.setProyecto_fin(rsProy.getString("proyecto_fin"));
                        objProyecto.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        objProyecto.setProyecto_fi(rsProy.getString("proyecto_fi"));
                        objProyecto.setProyecto_ff(rsProy.getString("proyecto_ff"));
                        objProyecto.setProyecto_codigo(rsProy.getString("proyecto_codigo"));
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        } finally {
            return objProyecto;
        }
    }

    public String ModificarProyecto(cProyecto cProy) {
        String result = "Error al ingresar el proyecto";
        String SQL = "SELECT public.f_modificar_proyecto(\n"
                + "	'" + cProy.getProyecto_nombre() + "', '" + cProy.getProyecto_proposito() + "', \n"
                + "	'" + cProy.getProyecto_fin() + "', '" + cProy.getProyecto_fi() + "', \n"
                + "	'" + cProy.getProyecto_ff() + "', '" + cProy.getProyecto_doc() + "', \n"
                + "	'" + cProy.getProyecto_responsable() + "','" + cProy.getProyecto_integrantes() + "', \n"
                + "	'" + cProy.getProyecto_multi() + "','" + cProy.getProyecto_id() + "', '" + cProy.getProyecto_ap() + "', '"+cProy.getProyecto_responsable_ced()+"')";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_modificar_proyecto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public Boolean ValidarAcciones(cProyecto cProy) {
        Boolean result = false;
        String SQL = "select exists(select * from acciones_mejora where am_nombre like '" + cProy.getProyecto_acciones() + "' and am_proceso like '" + cProy.getProyecto_proceso() + "' and am_proyecto='" + cProy.getProyecto_id() + "');";

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

    public String EliminarAreas(Integer proy) {
        String result = "Error al eliminar";
        String SQL = "DELETE FROM public.ag_proyecto\n"
                + "	WHERE agp_proyecto='" + proy + "';";

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
    //Eliminar Proyecto

    public String EliminarAcciones(Integer proy) {
        String result = "Error al eliminar";
        String SQL = "DELETE FROM public.acciones_mejora\n"
                + "	WHERE am_proyecto='" + proy + "';";

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

    public String EliminarAccionesProceso(cProyecto oProy) {
        String result = "Error al eliminar";
        String SQL = "DELETE FROM public.acciones_mejora\n"
                + "	WHERE am_proyecto='" + oProy.getProyecto_id() + "' and am_nombre='" + oProy.getProyecto_acciones() + "';";

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

    public String ValidarAccionesProceso(cProyecto oProy) {
        String result = "Error al validar";
        String SQL = "UPDATE public.acciones_mejora SET am_validar='" + oProy.getProyecto_multi() + "'\n"
                + "	WHERE am_proyecto='" + oProy.getProyecto_id() + "' and am_id='" + oProy.getProyecto_ag() + "';";

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

    public String EliminarIntegrantes(Integer proy) {
        String result = "Error al eliminar";
        String SQL = "DELETE FROM public.integrantes\n"
                + "	WHERE integrante_id='" + proy + "';";

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

    public String IngresarAccionesCarreraProyecto(cProyecto oProy, Boolean Validar) {
        String result = "Error al ingresar acciones";
        String SQL = "INSERT INTO public.acciones_mejora(\n"
                + "	am_proceso, am_nombre, am_id, am_proyecto, am_validar)\n"
                + "	VALUES ('" + oProy.getProyecto_proceso() + "', '" + oProy.getProyecto_acciones() + "', '" + codigoSiguienteacciones() + "' , '" + oProy.getProyecto_id() + "', '" + Validar + "');";

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
    //Codigo Siguiente acciones

    public Integer codigoSiguienteacciones() {
        Integer result = null;
        String SQL = "select max(am_id) as codigo from acciones_mejora;";
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

    public Double montoProyecto(Integer componente) {
        Double result = null;
        String SQL = "select proyecto_monto from componente join proyecto on componente_proyecto=proyecto_id where componente_id=?;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, componente) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getDouble("proyecto_monto");
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
        String result = "Error al enviar el proyecto";
        String SQL = "INSERT INTO public.proyecto_estado(\n"
                + "	pe_estado, pe_proyecto, pe_usuario)\n"
                + "	VALUES (?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertEnviar(SQL, oProy) != 0) {
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

    //Enviar deuda
    public String EnviarDeuda(cProyecto oProy) {
        String result = "Error al enviar el proyecto";
        String SQL = "INSERT INTO public.deudas_estado(\n"
                + "	de_estado, de_deudas, de_usuario)\n"
                + "	VALUES (?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertEnviar(SQL, oProy) != 0) {
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

    //Enviar deuda
    public String EnviarDeudaEval(cProyecto oProy) {
        String result = "Error al enviar el proyecto";
        String SQL = "INSERT INTO public.deudas_estado_eval(\n"
                + "	dee_estado, dee_deudas, dee_usuario)\n"
                + "	VALUES (?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertEnviar(SQL, oProy) != 0) {
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

    //Enviar proyecto Observacion
    public String EnviarProyectoObserv(cProyecto oProy) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.proyecto_estado(\n"
                + "	pe_estado, pe_proyecto, pe_usuario, pe_observacion)\n"
                + "	VALUES (?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertEnviarObserv(SQL, oProy) != 0) {
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

    //Modificar responsable
    public String modificarResponsable(cProyecto oProy) {
        String result = "Error";
        String SQL = "update proyecto set proyecto_responsable='" + oProy.getProyecto_responsable() + "' where proyecto_id='" + oProy.getProyecto_id() + "';";

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

    //Enviar proyecto Observacion evaluación
    public String EnviarProyectoObservEv(cProyecto oProy) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.estado_evaluacion(\n"
                + "	ee_estado, ee_proyecto, ee_usuario, ee_observacion, ee_cuatrimestre, ee_fecha)\n"
                + "	VALUES ('" + oProy.getEstado_id() + "', '" + oProy.getProyecto_id() + "', '" + oProy.getUsuario_nombre() + "', '" + oProy.getEstado_observacion() + "', '" + oProy.getProy_cuatrimestre() + "', now());";

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

    //Enviar proyecto Observacion
    public String EliminarEstado(cProyecto oProy) {
        String result = "Error";
        String SQL = "DELETE FROM public.proyecto_estado\n"
                + "	WHERE pe_proyecto='" + oProy.getProyecto_id() + "' and pe_fecha='" + oProy.getDeudas_contrato() + "' and pe_usuario='" + oProy.getUsuario_nombre() + "' and pe_estado='" + oProy.getEstado_id() + "';";

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
    
    //Enviar deudas Observacion
    public String EliminarEstadoDeudas(cProyecto oProy) {
        String result = "Error";
        String SQL = "DELETE FROM public.deudas_estado\n"
                + "	WHERE de_deudas='" + oProy.getProyecto_id() + "' and de_fecha='" + oProy.getDeudas_contrato() + "' and de_usuario='" + oProy.getUsuario_nombre() + "' and de_estado='" + oProy.getEstado_id() + "';";

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

    public String EliminarEstadoEv(cProyecto oProy) {
        String result = "Error";
        String SQL = "DELETE FROM public.estado_evaluacion\n"
                + "	WHERE ee_proyecto='" + oProy.getProyecto_id() + "' and ee_fecha='" + oProy.getDeudas_contrato() + "' and ee_usuario='" + oProy.getUsuario_nombre() + "' and ee_estado='" + oProy.getEstado_id() + "' and ee_cuatrimestre='" + oProy.getProy_cuatrimestre() + "';";

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

    //Enviar deudas Observacion
    public String EnviarDeudasObserv(cProyecto oProy) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.deudas_estado(\n"
                + "	de_estado, de_deudas, de_usuario, de_observacion)\n"
                + "	VALUES (?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertEnviarObserv(SQL, oProy) != 0) {
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

    //Enviar deudas Observacion
    public String EnviarDeudasObservEval(cProyecto oProy) {
        String result = "Error al enviar";
        String SQL = "INSERT INTO public.deudas_estado(\n"
                + "	dee_estado, dee_deudas, dee_usuario, dee_observacion)\n"
                + "	VALUES (?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertEnviarObserv(SQL, oProy) != 0) {
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

    //Listar Proyecto
    public List<cProyecto> ListarRequerimientoProyecto(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select pac_req from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente "
                + "   join requerimiento on actividad_id=req_actividad join pac on req_id=pac_req where req_estado=1 and proyecto_id=?";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setProyecto_id(rsProy.getInt("pac_req"));
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

    //Enviar requerimiento
    public String EnviarRequerimiento(cProyecto oProy) {
        String result = "Error al ingresar acciones";
        String SQL = "INSERT INTO public.pac_estado(\n"
                + "	paces_pac, paces_estado, paces_usuario, paces_observacion)\n"
                + "	VALUES (?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertEnviarReq(SQL, oProy) != 0) {
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

    //Ingreso de motnso
    public String ingresarMontos(cProyecto oProy) {
        String result = "Error al ingresar acciones";
        String SQL = "INSERT INTO public.monto_proyectos(\n"
                + "	mp_ano, mp_proyecto, mp_monto)\n"
                + "	VALUES (?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertMontos(SQL, oProy) != 0) {
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

    //Modificar de montos
    public String modificarMontos(cProyecto oProy) {
        String result = "Error al ingresar acciones";
        String SQL = "UPDATE public.monto_proyectos SET \n"
                + "	mp_monto=?\n"
                + "	WHERE mp_ano=? and mp_proyecto=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateMontos(SQL, oProy) != 0) {
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

    //Listar Estados
    public List<cProyecto> ListarReqEstados(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from pac_estado inner join estado on paces_estado=estado_id join usuario on paces_usuario=usuario_cedula where paces_pac='" + proy + "' order by paces_fecha desc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setEstado_id(rsProy.getInt("estado_id"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setEstado_observacion(rsProy.getString("paces_observacion"));
                        oProy.setEstado_fecha(rsProy.getTimestamp("paces_fecha"));
                        oProy.setUsuario_nombre(rsProy.getString("usuario_nombre"));
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

    //Listar Estados Deudas
    public List<cProyecto> ListarDeudasEstados(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from deudas_estado inner join estado on de_estado=estado_id join usuario on de_usuario=usuario_cedula where de_deudas='" + proy + "' order by de_fecha desc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setEstado_id(rsProy.getInt("estado_id"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setEstado_observacion(rsProy.getString("de_observacion"));
                        oProy.setEstado_fecha(rsProy.getTimestamp("de_fecha"));
                        oProy.setUsuario_nombre(rsProy.getString("usuario_nombre"));
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

    //Listar Estados Deudas
    public List<cProyecto> ListarDeudasEstadosEval(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from deudas_estado_eval inner join estado on dee_estado=estado_id join usuario on dee_usuario=usuario_cedula where dee_deudas='" + proy + "' order by dee_fecha desc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setEstado_id(rsProy.getInt("estado_id"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setEstado_observacion(rsProy.getString("dee_observacion"));
                        oProy.setEstado_fecha(rsProy.getTimestamp("dee_fecha"));
                        oProy.setUsuario_nombre(rsProy.getString("usuario_nombre"));
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

    //Listar Actividad Presupuestaria
    public List<cProyecto> ListarActividadPresupuestaria(Integer tipo) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from actividad_pres_req where apr_tipo='" + tipo + "' and apr_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setEstado_id(rsProy.getInt("apr_id"));
                        oProy.setEstado_nombre(rsProy.getString("apr_nombre"));
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

    //Eliminar estado
    public String EliminarEstado(Integer proy) {
        String result = "Error al eliminar";
        String SQL = "DELETE FROM public.proyecto_estado\n"
                + "	WHERE pe_proyecto='" + proy + "' and pe_estado=9 and pe_fecha=(select max(pe_fecha) from proyecto_estado where pe_proyecto='" + proy + "' and pe_estado=9);";

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

    //Eliminar Actividad
    public String EliminarProyecto(Integer cIndicador) {
        String result = "Error al eliminar el proyecto";
        String SQL = "UPDATE proyecto SET proyecto_estado=0 where proyecto_id=?";

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

    //Eliminar Deuda
    public String EliminarDeuda(Integer deuda) {
        String result = "Error al eliminar";
        String SQL = "UPDATE deudas SET deudas_estado=0 where deudas_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, deuda) != 0) {
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

    //Eliminar Evidencia deuda
    public String EliminarDeudaEvidencia(Integer deuda) {
        String result = "Error al eliminar";
        String SQL = "Delete from deudas_evidencias where de_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, deuda) != 0) {
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
    public Timestamp tiemposVerificacion() {
        Timestamp result = null;
        String SQL = "select tiempos_fecha from tiempos where tiempos_tipo=1";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getTimestamp("tiempos_fecha");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Numero de requerimientos
    public Timestamp tiemposVerificacionE() {
        Timestamp result = null;
        String SQL = "select tiempos_fecha from tiempos where tiempos_tipo=2;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getTimestamp("tiempos_fecha");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Verificacion de envios
    public Boolean VerificacionEnvios(Integer proy) {
        Boolean result = false;
        String SQL = "select exists(select * from proyecto inner join proyecto_estado on proyecto_id=pe_proyecto where (pe_estado=2 or pe_estado=8 or pe_estado=4) and proyecto_id='" + proy + "');";
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

    //Verificacion de reprogramado
    public Boolean VerificacionReprogramado(Integer proy) {
        Boolean result = false;
        String SQL = "select exists(select * from proyecto inner join estado_evaluacion on proyecto_id=ee_proyecto where ee_estado=45 and proyecto_id='" + proy + "');";
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

    //Verificacion de envios
    public Boolean VerificacionEnviosE(Integer proy, Integer cuatrimestre) {
        Boolean result = false;
        String SQL = "select exists(select * from proyecto inner join estado_evaluacion on proyecto_id=ee_proyecto inner join area_gestion on proyecto_ag=ag_id where ((ee_estado=2 and ag_tag=4 and ag_id<>47 and ag_id<>45) or ((ee_estado=4 or ee_estado=8) and (ag_tag=2 or ag_tag=3 or ag_tag=5)) or (ee_estado=27 and ag_id=45) \n"
                + "                    or (ee_estado=1 and ag_id=47)) and proyecto_id='" + proy + "' and ee_cuatrimestre='" + cuatrimestre + "');";
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

    //Verificacion de envios
    public Boolean VerificacionEnviosETiempo(Integer tipo) {
        Boolean result = false;
        String SQL = "select exists(select * from tiempos where tiempos_fecha>=now() and tiempos_id='" + tipo + "');";
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

    //Verificacion de envios
    public Boolean VerificacionEnviosETiempoEje() {
        Boolean result = false;
        String SQL = "select exists(select * from tiempos where tiempos_fecha>=now() and tiempos_id=3);";
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

    //Verificacion de montos ingresados
    public Boolean VerificacionMontosIngresados(Integer proy, Integer ano) {
        Boolean result = false;
        String SQL = "select exists(select * from monto_proyectos where mp_proyecto='" + proy + "' and mp_ano='" + ano + "');";
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

    //Ingresar Priorizacion en actividad
    public String IngresarPrioProyecto(Integer proyecto, Integer estado) {
        String result = "Error al ingresar el proyecto";
        String SQL = "SELECT public.f_priorizar_proyecto('" + proyecto + "', '" + estado + "');";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("f_priorizar_proyecto");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Verificar proyectos
    public boolean ExisteProyectos(Integer ag, Integer anio) {
        boolean result = false;
        String SQL = "select exists(select * from vproyectopdf where (estado_id=17 or estado_id=18) and (ag_id='" + ag + "' or are_ag_id='" + ag + "') and proyecto_anio='" + anio + "');";

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

    //Lista para reporte poa
    public List<cProyecto> ListaProyectoPOA(Integer area, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from vproyectopdf where (estado_id=17 or estado_id=18) and ag_id='" + area + "' and proyecto_anio='" + anio + "';";
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
                        cComp.setProyecto_responsable(rsComp.getString("proyecto_responsable"));
                        cComp.setProyecto_monto(rsComp.getDouble("proyecto_monto"));
                        cComp.setProyecto_codigo(rsComp.getString("proyecto_codigo"));
                        cComp.setTp_nombre(rsComp.getString("tp_nombre"));
                        cComp.setCuatri(ListarCuatrimestreProyecto(cComp));
                        cComp.setProyecto_plurianual(rsComp.getInt("proyecto_plurianual"));
                        cComp.setTp_id(rsComp.getInt("tp_id"));
                        //cComp.setMp_monto(montoproyectoPluri(area, rsComp.getInt("proyecto_id"), anio));
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

    //Lista para reporte poa evaluación
    public List<cProyecto> ListaProyectoPOAEval(Integer area, Integer tipo, Integer cuatrimestre, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listarevaluacionproyectosunid('" + area + "','" + tipo + "','" + cuatrimestre + "', '" + anio + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setProyecto_nombre(rsComp.getString("agnombre"));
                        cComp.setProyecto_planificados(rsComp.getInt("nplanificados2"));
                        cComp.setProyecto_plurianual(rsComp.getInt("ncuatrimestre2"));
                        cComp.setPp_id(rsComp.getInt("evaluados2"));
                        cComp.setPe_eficacia(rsComp.getDouble("peeficacia2"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peeficiencia2"));
                        cComp.setPe_efectividad(rsComp.getDouble("peefectividad2"));
                        cComp.setProyecto_monto(rsComp.getDouble("peplanificado2"));
                        if (rsComp.getDouble("peejecucion2") >= 0) {
                            cComp.setPe_ejecucion(rsComp.getDouble("peejecucion2"));
                        } else {
                            cComp.setPe_ejecucion(0.0);
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

    //Lista para reporte poa evaluación admin
    public List<cProyecto> ListaProyectoPOAEvalAdmin(Integer area, Integer tipo, Integer cuatrimestre, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listarevaluacionproyectosunidadm('" + area + "','" + tipo + "','" + cuatrimestre + "', '" + anio + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setProyecto_nombre(rsComp.getString("agnombre"));
                        cComp.setProyecto_planificados(rsComp.getInt("nplanificados2"));
                        cComp.setProyecto_plurianual(rsComp.getInt("ncuatrimestre2"));
                        cComp.setPp_id(rsComp.getInt("evaluados2"));
                        cComp.setPe_eficacia(rsComp.getDouble("peeficacia2"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peeficiencia2"));
                        cComp.setPe_efectividad(rsComp.getDouble("peefectividad2"));
                        cComp.setProyecto_monto(rsComp.getDouble("peplanificados"));
                        if (rsComp.getDouble("peejecucion2") >= 0) {
                            cComp.setPe_ejecucion(rsComp.getDouble("peejecucion2"));
                        } else {
                            cComp.setPe_ejecucion(0.0);
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

    //Lista para reporte poa evaluación admin
    public List<cProyecto> ListaProyectoPOAEvalAdminComp(Integer area, Integer tipo, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listarevaluacionproyectosunidadmcom('" + area + "','" + tipo + "','" + anio + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setProyecto_nombre(rsComp.getString("agnombre"));
                        cComp.setProyecto_planificados(rsComp.getInt("nplanificados2"));
                        cComp.setProyecto_plurianual(rsComp.getInt("ncuatrimestre2"));
                        cComp.setPp_id(rsComp.getInt("evaluados2"));
                        cComp.setPe_eficacia(rsComp.getDouble("peeficacia2"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peeficiencia2"));
                        cComp.setPe_efectividad(rsComp.getDouble("peefectividad2"));
                        cComp.setProyecto_monto(rsComp.getDouble("peplanificados"));
                        if (rsComp.getDouble("peejecucion2") >= 0) {
                            cComp.setPe_ejecucion(rsComp.getDouble("peejecucion2"));
                        } else {
                            cComp.setPe_ejecucion(0.0);
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

    //Lista para reporte poa evaluación por proyectos
    public List<cProyecto> ListaProyectoPOAEvalProy(Integer area, Integer tipo, Integer cuatrimestre, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listarevaluacionproyectosind('" + area + "','" + tipo + "','" + cuatrimestre + "', '" + anio + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setProyecto_codigo(rsComp.getString("proyectocodigo"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setTp_nombre(rsComp.getString("tipoproy"));
                        cComp.setPe_eficacia(rsComp.getDouble("peeficacia2"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peeficiencia2"));
                        cComp.setPe_efectividad(rsComp.getDouble("peefectividad2"));
                        cComp.setProyecto_monto(rsComp.getDouble("peplanificado"));
                        if (rsComp.getDouble("peejecucion2") >= 0) {
                            cComp.setPe_ejecucion(rsComp.getDouble("peejecucion2"));
                        } else {
                            cComp.setPe_ejecucion(0.0);
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

    //Lista para reporte poa evaluación por proyectos
    public List<cProyecto> ListaProyectoPOAEvalProyComp(Integer area, Integer tipo, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listarevaluacionproyectosindcomp('" + area + "','" + tipo + "', '" + anio + "')";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cProyecto cComp = new cProyecto();
                        cComp.setProyecto_codigo(rsComp.getString("proyectocodigo"));
                        cComp.setProyecto_nombre(rsComp.getString("proyectonombre"));
                        cComp.setTp_nombre(rsComp.getString("tipoproy"));
                        cComp.setPe_eficacia(rsComp.getDouble("peeficacia2"));
                        cComp.setPe_eficiencia(rsComp.getDouble("peeficiencia2"));
                        cComp.setPe_efectividad(rsComp.getDouble("peefectividad2"));
                        cComp.setProyecto_monto(rsComp.getDouble("peplanificado"));
                        if (rsComp.getDouble("peejecucion2") >= 0) {
                            cComp.setPe_ejecucion(rsComp.getDouble("peejecucion2"));
                        } else {
                            cComp.setPe_ejecucion(0.0);
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

    //Listar cuatrimestre
    public List<cActividadRequerimiento> ListarCuatrimestreProyecto(cProyecto componente) {
        List<cActividadRequerimiento> result = new ArrayList<cActividadRequerimiento>();
        String SQL = "select (select sum(am_porcentaje) from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente \n"
                + "		 join actividad_mes on actividad_id=am_actividad join mes on am_mes=mes_id where mes_cuatrimestre=1 and proyecto_id='" + componente.getProyecto_id() + "' and am_estado=1 and actividad_estado=1 and componente_estado=1) as I,\n"
                + "(select sum(am_porcentaje) from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente \n"
                + "		 join actividad_mes on actividad_id=am_actividad join mes on am_mes=mes_id where mes_cuatrimestre=2 and proyecto_id='" + componente.getProyecto_id() + "' and am_estado=1 and actividad_estado=1 and componente_estado=1) as II,\n"
                + "(select sum(am_porcentaje) from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente \n"
                + "		 join actividad_mes on actividad_id=am_actividad join mes on am_mes=mes_id where mes_cuatrimestre=3 and proyecto_id='" + componente.getProyecto_id() + "' and am_estado=1 and actividad_estado=1 and componente_estado=1) as III;";
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

    //Numero de actividades por proyecto
    public Integer numActividadesProy(Integer proyecto) {
        Integer result = null;
        String SQL = "select count(actividad_id) as actividad_id from proyecto inner join componente on proyecto_id=componente_proyecto join actividad on componente_id=actividad_componente \n"
                + "		 where proyecto_id='" + proyecto + "' and actividad_estado=1 and componente_estado=1";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("actividad_id");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Monto proyecto plurianual
    public Double montoproyectoPluri(Integer ag, Integer proyecto, Integer anio) {
        Double result = null;
        String SQL = "select sum(req_costototal) from f_listarequerimientosexcelunid('" + ag + "',0,'" + anio + "') where proyectoid='" + proyecto + "' and reqanio='" + anio + "';";
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

    //Ingresar Deudas
    public String IngresarDeudas(cProyecto oProy) {
        String result = "Error al ingresar";
        String SQL = "INSERT INTO public.deudas(\n"
                + "	deudas_id, deudas_oei, deudas_proyecto, deudas_nombre_proceso, deudas_contrato, deudas_tcon, deudas_financiamiento, deudas_monto, deudas_ag, deudas_iva, deudas_presupuesto, deudas_tipo, deudas_anticipo, deudas_anio, deudas_monto_pendiente)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSQLDeudas(SQL, oProy) != 0) {
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

    //Modificar Deudas
    public String ModificarDeudas(cProyecto oProy) {
        String result = "Error al ingresar";
        String SQL = "UPDATE public.deudas\n"
                + "	SET deudas_oei=?, deudas_proyecto=?, deudas_nombre_proceso=?, deudas_contrato=?, deudas_tcon=?, deudas_financiamiento=?, deudas_monto=?, deudas_iva=?, deudas_presupuesto=?, deudas_tipo=?, deudas_anticipo=?, deudas_monto_pendiente=?\n"
                + "	WHERE deudas_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSQLDeudasUpdate(SQL, oProy) != 0) {
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

    //Modificar Deudas
    public String ModificarDeudasAg(cProyecto oProy) {
        String result = "Error al ingresar";
        String SQL = "UPDATE public.deudas\n"
                + "	SET deudas_oei=?, deudas_proyecto=?, deudas_nombre_proceso=?, deudas_contrato=?, deudas_tcon=?, deudas_financiamiento=?, deudas_monto=?, deudas_iva=?, deudas_presupuesto=?, deudas_tipo=?, deudas_anticipo=?, deudas_area=?, deudas_monto_pendiente=?\n"
                + "	WHERE deudas_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSQLDeudasUpdateAg(SQL, oProy) != 0) {
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

    //Modificar Deudas
    public String ModificarDeudasReforma(cProyecto oProy) {
        String result = "Error al ingresar";
        String SQL = "UPDATE public.deudas\n"
                + "	SET deudas_oei=?, deudas_proyecto=?, deudas_nombre_proceso=?, deudas_contrato=?, deudas_tcon=?, deudas_financiamiento=?, deudas_monto=?, deudas_iva=?, deudas_presupuesto=?, deudas_tipo=?, deudas_anticipo=?, deudas_reforma=?, deudas_estado=?, deudas_monto_pendiente=?\n"
                + "	WHERE deudas_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSQLDeudasUpdateDe(SQL, oProy) != 0) {
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

    //Modificar Deudas estructura
    public String ModificarDeudasEstructura(cActividadRequerimiento cAct) {
        String result = "Error al ingresar";
        String SQL = "UPDATE public.deudas_estructura\n"
                + "	SET de_programa='" + cAct.getPresupuesto_programa() + "', de_subprograma='" + cAct.getPresupuesto_subprograma() + "', de_proyecto='" + cAct.getPresupuesto_proyecto() + "', de_actividad='" + cAct.getPresupuesto_actividad() + "', de_renglo='" + cAct.getPresupuesto_renglo() + "', "
                + "     de_geografico='" + cAct.getPresupuesto_geografico() + "', de_fuente='" + cAct.getPresupuesto_fuente() + "', de_organismo='" + cAct.getPresupuesto_organismo() + "', de_correlativo='" + cAct.getPresupuesto_correlativo() + "', de_ejercicio='" + cAct.getPresupuesto_ejercicio() + "', "
                + "     de_entidad='" + cAct.getPresupuesto_entidad() + "', de_unidad_desc='" + cAct.getPresupuesto_unidad_desc() + "', de_unidad_ejec='" + cAct.getPresupuesto_unidad_ejec() + "', de_obra='" + cAct.getPresupuesto_obra() + "', de_renglo_aux='" + cAct.getPresupuesto_renglo_aux() + "'\n"
                + "	WHERE de_deudas='" + cAct.getReq_id() + "' and de_iva='" + cAct.getPresupuesto_id() + "';";

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

    //Ingresar Deudas estructura
    public String IngresarDeudasEstructura(cActividadRequerimiento cAct) {
        String result = "Error al ingresar";
        String SQL = "INSERT INTO public.deudas_estructura(de_programa, de_subprograma, de_proyecto, de_actividad, de_renglo, de_geografico, de_fuente, de_organismo, de_correlativo, de_ejercicio,"
                + "     de_entidad, de_unidad_desc, de_unidad_ejec, de_obra, de_renglo_aux, de_deudas, de_iva)\n"
                + "	 values('" + cAct.getPresupuesto_programa() + "', '" + cAct.getPresupuesto_subprograma() + "', '" + cAct.getPresupuesto_proyecto() + "', '" + cAct.getPresupuesto_actividad() + "', '" + cAct.getPresupuesto_renglo() + "', "
                + "     '" + cAct.getPresupuesto_geografico() + "', '" + cAct.getPresupuesto_fuente() + "', '" + cAct.getPresupuesto_organismo() + "', '" + cAct.getPresupuesto_correlativo() + "', '" + cAct.getPresupuesto_ejercicio() + "', "
                + "     '" + cAct.getPresupuesto_entidad() + "', '" + cAct.getPresupuesto_unidad_desc() + "', '" + cAct.getPresupuesto_unidad_ejec() + "', '" + cAct.getPresupuesto_obra() + "', '" + cAct.getPresupuesto_renglo_aux() + "',\n"
                + "	'" + cAct.getReq_id() + "', '" + cAct.getPresupuesto_id() + "');";

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

    //Eliminar Deudas estructura
    public String EliminarDeudasEstructura(cActividadRequerimiento cAct) {
        String result = "Error al ingresar";
        String SQL = "DELETE FROM public.deudas_estructura where de_deudas='" + cAct.getReq_id() + "' and de_iva='" + cAct.getTc_id() + "';";

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

    public Integer codigoSiguienteDeudas() {
        Integer result = null;
        String SQL = "select max(deudas_id) as codigo from deudas;";
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

    //Listar Proyectos por areas por tipos de usuario para evaluacion
    public List<cProyecto> ListarProyectoEvaluacion(Integer tipou, Integer areapadre, Integer area, Integer tipop, Integer cuatrimestre, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listaproyectoevaluacion('" + tipou + "', '" + areapadre + "', '" + area + "', '" + tipop + "', '" + cuatrimestre + "', '" + anio + "') as datos(proyecto_id integer,proyecto_nombre text, proyecto_responsable text, proyecto_monto numeric, estado_nombre character varying, tp_id integer, tp_nombre character varying, ag_nombre text, estado_id integer, ag_id integer, ee_cuatrimestre integer) UNION ALL "
                + " select * from f_listaproyectomuleval('" + tipou + "', '" + areapadre + "', '" + area + "', '" + tipop + "', '" + cuatrimestre + "', '" + anio + "') as datos(proyecto_id integer,proyecto_nombre text, proyecto_responsable text, proyecto_monto numeric, estado_nombre character varying, tp_id integer, tp_nombre character varying, ag_nombre text, estado_id integer, ag_id integer, ee_cuatrimestre integer);";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        cAreaGestion cAg = new cAreaGestion();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setTp_nombre(rsProy.getString("tp_nombre"));
                        cAg.setAg_nombre(rsProy.getString("ag_nombre"));
                        cAg.setAg_id(rsProy.getInt("ag_id"));
                        oProy.setPp_id(rsProy.getInt("ee_cuatrimestre"));
                        oProy.setAg(cAg);
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

    //Listar Proyectos por areas por tipos de usuario para evaluacion
    public List<cProyecto> ListarProyectoEvaluacionL(Integer tipou, Integer area, Integer tipop, Integer cuatrimestre, Integer anio) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from f_listaproyectoevaluacionL('" + tipou + "', '" + area + "', '" + tipop + "', '" + cuatrimestre + "', '" + anio + "') as datos(proyecto_id integer,proyecto_nombre text, proyecto_responsable text, proyecto_monto numeric, estado_nombre character varying, tp_id integer, tp_nombre character varying, ag_nombre text, estado_id integer, ag_id integer, ee_cuatrimestre integer);";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        cAreaGestion cAg = new cAreaGestion();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setTp_nombre(rsProy.getString("tp_nombre"));
                        cAg.setAg_nombre(rsProy.getString("ag_nombre"));
                        cAg.setAg_id(rsProy.getInt("ag_id"));
                        oProy.setPp_id(rsProy.getInt("ee_cuatrimestre"));
                        oProy.setAg(cAg);
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

    //Listar Proyecto Evaluacion
    public List<cProyecto> ListarProyectoEval(Integer proy, Integer cuatrimestre) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from proyecto inner join area_gestion on proyecto_ag=ag_id join actividad_presupuestaria on proyecto_ap=ap_id join objetivo on ap_objetivo=objetivo_id "
                + " join perspectiva on objetivo_perspectiva=perspectiva_id where proyecto_id='" + proy + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        cAreaGestion oAg = new cAreaGestion();
                        oProy.setProyecto_id(rsProy.getInt("proyecto_id"));
                        oProy.setProyecto_nombre(rsProy.getString("proyecto_nombre"));
                        oProy.setProyecto_monto(rsProy.getDouble("proyecto_monto"));
                        oProy.setProyecto_fi(rsProy.getString("proyecto_fi"));
                        oProy.setPp_id(rsProy.getInt("perspectiva_tp"));
                        oProy.setProyecto_ff(rsProy.getString("proyecto_ff"));
                        oProy.setProyecto_fin(rsProy.getString("proyecto_fin"));
                        oProy.setProyecto_fi_rep(rsProy.getString("proyecto_fi_rep"));
                        oProy.setProyecto_ff_rep(rsProy.getString("proyecto_ff_rep"));
                        oProy.setProyecto_responsable(rsProy.getString("proyecto_responsable"));
                        oProy.setProyecto_proposito(rsProy.getString("proyecto_proposito"));
                        oProy.setProyecto_doc(rsProy.getString("proyecto_doc"));
                        oProy.setProyecto_proceso(rsProy.getString("proyecto_perfil_rep"));
                        oProy.setEstado(ListarProyectoEstadosEval(proy, cuatrimestre));
                        oProy.setEstado_id(estadoidevaluacon(cuatrimestre, proy));
                        oProy.setEstado_nombre(estadonombreevaluacon(cuatrimestre, proy));
                        oAg.setAg_id(rsProy.getInt("ag_id"));
                        oAg.setAg_nombre(rsProy.getString("ag_nombre"));
                        oProy.setAg(oAg);
                        oProy.setTp_id(estadocuatrimestreevaluacon(cuatrimestre, proy));
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

    //Listar Proyecto
    public List<cProyecto> ListarEstadoAsign(Integer proy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select distinct on (pe_proyecto) pe_proyecto, au_ag, au_usuario from (select * from proyecto_estado inner join asignar_usuario on pe_usuario=au_usuario where pe_proyecto='" + proy + "' order by pe_fecha desc)as con;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setProyecto_id(rsProy.getInt("pe_proyecto"));
                        oProy.setProyecto_ag(rsProy.getInt("au_ag"));
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

    //Estado id
    public Integer estadoidevaluacon(Integer cuatrimestre, Integer proyecto) {
        Integer result = null;
        String SQL = "select ee_estado from(select distinct on (ee_cuatrimestre) ee_cuatrimestre, estado_nombre, ee_estado from (select * from proyecto inner join estado_evaluacion on proyecto_id=ee_proyecto \n"
                + "join estado on ee_estado=estado_id join usuario on ee_usuario=usuario_cedula where ee_proyecto='" + proyecto + "' and ee_cuatrimestre='" + cuatrimestre + "' order by ee_fecha desc) as con)as con2;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("ee_estado");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Estados nombre
    public String estadonombreevaluacon(Integer cuatrimestre, Integer proyecto) {
        String result = null;
        String SQL = "select estado_nombre from(select distinct on (ee_cuatrimestre) ee_cuatrimestre, estado_nombre, ee_estado from (select * from proyecto inner join estado_evaluacion on proyecto_id=ee_proyecto \n"
                + "join estado on ee_estado=estado_id join usuario on ee_usuario=usuario_cedula where ee_proyecto='" + proyecto + "' and ee_cuatrimestre='" + cuatrimestre + "' order by ee_fecha desc) as con)as con2;";
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

    //Estado cuatrimestre
    public Integer estadocuatrimestreevaluacon(Integer cuatrimestre, Integer proyecto) {
        Integer result = null;
        String SQL = "select ee_cuatrimestre from(select distinct on (ee_cuatrimestre) ee_cuatrimestre, estado_nombre, ee_estado from (select * from proyecto inner join estado_evaluacion on proyecto_id=ee_proyecto \n"
                + "join estado on ee_estado=estado_id join usuario on ee_usuario=usuario_cedula where ee_proyecto='" + proyecto + "' and ee_cuatrimestre='" + cuatrimestre + "' order by ee_fecha desc) as con)as con2;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("ee_cuatrimestre");
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Listar Estados
    public List<cProyecto> ListarProyectoEstadosEval(Integer proy, Integer cuatrimestre) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from estado_evaluacion inner join estado on ee_estado=estado_id join usuario on ee_usuario=usuario_cedula where ee_proyecto='" + proy + "' and ee_cuatrimestre='" + cuatrimestre + "' order by ee_fecha desc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsProy = ad.getRs();
                    while (rsProy.next()) {
                        cProyecto oProy = new cProyecto();
                        oProy.setEstado_id(rsProy.getInt("estado_id"));
                        oProy.setEstado_nombre(rsProy.getString("estado_nombre"));
                        oProy.setEstado_fecha(rsProy.getTimestamp("ee_fecha"));
                        oProy.setUsuario_nombre(rsProy.getString("usuario_nombre"));
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
}
