/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import poa.clases.cAreaGestion;
import poa.clases.cProyecto;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adAreaGestion {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Mostrar tipo proyecto
    static public ResultSet listaAreaGestion() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where are_ag_id=1 order by ag_id asc";
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

    //Mostrar estados
    static public ResultSet listaEstados() {
        ResultSet rs = null;
        String SQL = "select * from estado where estado_id<>17 and estado_id<>18 order by estado_id asc";
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
    static public ResultSet listaAreaGestionFE() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where ag_tag=2 or ag_tag=5 order by ag_id asc";
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
    static public ResultSet listaAreasGestion() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion order by ag_id asc";
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
    static public ResultSet listaAreasGestionActivas() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where ag_estado=1 order by ag_id asc";
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

    //Mostrar tipo usuario
    static public ResultSet listaTipoUsuarios() {
        ResultSet rs = null;
        String SQL = "select * from tipo_usuario where tu_id<>24 order by tu_id asc";
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
    static public ResultSet listaAreaGestionUnidadesAdmin() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where ag_tag=4 and are_ag_id=1 order by ag_id asc";
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
    static public ResultSet listaAreaGestionUnidadesAdminTotas() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where ag_tag=4 order by ag_id asc";
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
    static public ResultSet listaAreaGestionMulti() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where (ag_tag=3 or ag_tag=4 or ag_tag=5) order by ag_id asc";
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
    static public ResultSet listaAreaGestionHijos(Integer ag) {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where are_ag_id=? order by ag_id asc";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {
                if (ad.ejecutarSelectPersObj(SQL, ag) != 0) {
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
    static public ResultSet listaFaculAdmin() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where ag_tag=2 or ag_tag=4 or ag_tag=5 order by ag_id";
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

    public static List<cAreaGestion> obtenerAreasGestionHijas(Integer intCodigoUnidad) {
        List<cAreaGestion> result = new ArrayList<cAreaGestion>();
        String SQL = "SELECT *\n"
                + "FROM public.area_gestion\n"
                + "JOIN tipo_area_gestion ON ag_tag=tag_id\n"
                + "WHERE (are_ag_id=" + intCodigoUnidad + ") and tag_estado=1 order by ag_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cAreaGestion oAreaGestion = new cAreaGestion();
                        oAreaGestion.setAg_id(rsTecho.getInt("ag_id"));
                        oAreaGestion.setAg_nombre(rsTecho.getString("ag_nombre"));
                        oAreaGestion.setAg_estado(rsTecho.getInt("ag_estado"));
                        oAreaGestion.setAg_alias(rsTecho.getString("ag_alias"));
                        oAreaGestion.setTag_id(rsTecho.getInt("tag_id"));
                        oAreaGestion.setTag_nombre(rsTecho.getString("tag_nombre"));
                        oAreaGestion.setTag_estado(rsTecho.getInt("tag_estado"));
                        result.add(oAreaGestion);
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

    public static List<cAreaGestion> obtenerAreasGestionUnidades(Integer intCodigoUnidad) {
        List<cAreaGestion> result = new ArrayList<cAreaGestion>();
        String SQL = "SELECT *\n"
                + "FROM public.area_gestion\n"
                + "JOIN tipo_area_gestion ON ag_tag=tag_id\n"
                + "WHERE (ag_id=" + intCodigoUnidad + " or are_ag_id=" + intCodigoUnidad + ") and tag_estado=1 order by ag_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cAreaGestion oAreaGestion = new cAreaGestion();
                        oAreaGestion.setAg_id(rsTecho.getInt("ag_id"));
                        oAreaGestion.setAg_nombre(rsTecho.getString("ag_nombre"));
                        oAreaGestion.setAg_estado(rsTecho.getInt("ag_estado"));
                        oAreaGestion.setAg_alias(rsTecho.getString("ag_alias"));
                        oAreaGestion.setTag_id(rsTecho.getInt("tag_id"));
                        oAreaGestion.setTag_nombre(rsTecho.getString("tag_nombre"));
                        oAreaGestion.setTag_estado(rsTecho.getInt("tag_estado"));
                        result.add(oAreaGestion);
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

    public List<cAreaGestion> obtenerAreasGestionUnidadesEval() {
        List<cAreaGestion> result = new ArrayList<cAreaGestion>();
        String SQL = "select * from area_gestion where ag_tag=2 or ag_tag=4 or ag_tag=5 order by ag_id";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cAreaGestion oAreaGestion = new cAreaGestion();
                        oAreaGestion.setAg_id(rsTecho.getInt("ag_id"));
                        oAreaGestion.setAg_nombre(rsTecho.getString("ag_nombre"));
                        oAreaGestion.setAg_estado(rsTecho.getInt("ag_estado"));
                        oAreaGestion.setAg_alias(rsTecho.getString("ag_alias"));
                        oAreaGestion.setTag_id(rsTecho.getInt("ag_tag"));
//                        oAreaGestion.setTag_nombre(rsTecho.getString("tag_nombre"));
//                        oAreaGestion.setTag_estado(rsTecho.getInt("tag_estado"));
                        result.add(oAreaGestion);
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

    public List<cAreaGestion> obtenerAreasGestionUnidadesEvalIV(Integer area) {
        List<cAreaGestion> result = new ArrayList<cAreaGestion>();
        String SQL = "select * from area_gestion where (ag_tag=2 or ag_tag=5 or ag_id='" + area + "') and ag_estado=1 order by ag_id";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cAreaGestion oAreaGestion = new cAreaGestion();
                        oAreaGestion.setAg_id(rsTecho.getInt("ag_id"));
                        oAreaGestion.setAg_nombre(rsTecho.getString("ag_nombre"));
                        oAreaGestion.setAg_estado(rsTecho.getInt("ag_estado"));
                        oAreaGestion.setAg_alias(rsTecho.getString("ag_alias"));
                        oAreaGestion.setTag_id(rsTecho.getInt("ag_tag"));
//                        oAreaGestion.setTag_nombre(rsTecho.getString("tag_nombre"));
//                        oAreaGestion.setTag_estado(rsTecho.getInt("tag_estado"));
                        result.add(oAreaGestion);
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

    public List<cProyecto> listaDirectores(Integer area) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from usuario inner join asignar_usuario on usuario_cedula=au_usuario inner join tipo_usuario on au_tu=tu_id left join cargos on au_ag=cargo_ag where au_ag='" + area + "' \n"
                + "and (au_tu=2 or au_tu=4 or au_tu=5 or au_tu=7 or au_tu=8 or au_tu=19) and usuario_cedula not like '0606043867' and usuario_cedula not like '0603014739' \n"
                + "and usuario_cedula not like '0202104717' and usuario_cedula not like '0602556961' and usuario_cedula not like '1804150686' and au_estado=1";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cProyecto oAreaGestion = new cProyecto();
                        oAreaGestion.setProyecto_codigo(rsTecho.getString("usuario_titulo"));
                        oAreaGestion.setProyecto_nombre(rsTecho.getString("usuario_nombre"));
                        oAreaGestion.setProyecto_doc(rsTecho.getString("tu_nombre"));
                        oAreaGestion.setProyecto_integrantes(rsTecho.getString("cargo_nombre"));
                        result.add(oAreaGestion);
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

    //Mostrar area gestion asignadas
    static public ResultSet listaAreaGestionAsignadas(String ag_usuario) {
        ResultSet rs = null;
        String SQL = "select * from ag_usuario inner join area_gestion on agu_ag=ag_id where agu_usuario like '" + ag_usuario + "' order by agu_ag asc;";
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

    //Mostrar area gestion asignadas
    static public ResultSet listaAreaGestionDeudas() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where ag_tag=2 or ag_tag=4 or ag_tag=5 order by ag_id";
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
    //Mostrar area gestion asignadas
    static public ResultSet listaAreaGestionDeudasActivas() {
        ResultSet rs = null;
        String SQL = "select * from area_gestion where ag_estado=1 and (ag_tag=2 or ag_tag=4 or ag_tag=5) order by ag_id";
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
    
     //Mostrar area gestion asignadas
    static public ResultSet listaAreaGestionPOA(Integer anio) {
        ResultSet rs = null;
        String SQL = "select agid as ag_id, agnombre as ag_nombre, agalias as ag_alias, agtag as ag_tag, agestado as ag_estado, agareagid as are_ag_id from f_listaunidadespoa("+anio+")";
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

    //Listar fecha techos
    static public ResultSet listaTechosF() {
        ResultSet rs = null;
        String SQL = "select * from techo_institucional order by ti_fecha desc;";
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
    static public Integer numeroAreaGestion(Integer ag) {
        Integer result = null;
        String SQL = "SELECT count(ag_id) FROM area_gestion where are_ag_id=?;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, ag) != 0) {
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

    public List<cProyecto> listadoArchivos(cProyecto cProy) {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from archivos where archivos_ta='" + cProy.getTp_id() + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cProyecto oAreaGestion = new cProyecto();
                        oAreaGestion.setProyecto_id(rsTecho.getInt("archivos_id"));
                        oAreaGestion.setProyecto_nombre(rsTecho.getString("archivos_descripcion"));
                        oAreaGestion.setProyecto_doc(rsTecho.getString("archivos_doc"));
                        result.add(oAreaGestion);
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

    public List<cProyecto> listarTipoArchivos() {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from tipo_archivo where ta_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cProyecto oAreaGestion = new cProyecto();
                        oAreaGestion.setTp_id(rsTecho.getInt("ta_id"));
                        oAreaGestion.setTp_nombre(rsTecho.getString("ta_nombre"));
                        oAreaGestion.setArea_gestion(listadoArchivos(oAreaGestion));
                        result.add(oAreaGestion);
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

    public List<cProyecto> listarActivar() {
        List<cProyecto> result = new ArrayList<cProyecto>();
        String SQL = "select * from tiempos order by tiempos_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsTecho = ad.getRs();
                    while (rsTecho.next()) {
                        cProyecto oAreaGestion = new cProyecto();
                        oAreaGestion.setTp_id(rsTecho.getInt("tiempos_id"));
                        oAreaGestion.setDeudas_contrato(rsTecho.getString("tiempos_fecha"));
                        oAreaGestion.setEstado_observacion(rsTecho.getString("tiempos_observacion"));
                        oAreaGestion.setTi_fecha(rsTecho.getInt("tiempos_anio"));
                        oAreaGestion.setTp_nombre(rsTecho.getString("tiempos_nombre"));
                        oAreaGestion.setDeudas_id(rsTecho.getInt("tiempos_tipo"));
                        result.add(oAreaGestion);
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

//Listado dde tipo de contratación
    static public ResultSet listarTipoContratacion() {
        ResultSet rs = null;
        String SQL = "select * from tipo_contratacion where tcon_estado=1;";
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
    public String nombreAreaG(Integer ag) {
        String result = "Error.";
        String SQL = "Select * from area_gestion where ag_id='" + ag + "';";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("ag_nombre");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }
    
    //Lista tipo area
    public String tipoAreaG(Integer ag) {
        String result = "Error.";
        String SQL = "Select * from area_gestion where ag_id='" + ag + "';";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getString("ag_tag");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public String modificarFechas(cProyecto oProy) {
        String result = "Error";
        String SQL = "UPDATE tiempos set tiempos_fecha='" + oProy.getDeudas_contrato() + "', tiempos_observacion='"+oProy.getEstado_observacion()+"', tiempos_anio='"+oProy.getProyecto_anio()+"', tiempos_nombre='"+oProy.getTp_nombre()+"', tiempos_tipo='"+oProy.getDeudas_id()+"' where tiempos_id='" + oProy.getTp_id() + "';";

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
    
    //Codigo Siguiente archivo
    public Integer codigoSiguienteFechas() {
        Integer result = null;
        String SQL = "SELECT (MAX(tiempos_id)) as codigo FROM tiempos;";
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
    
    public String ingresarFechas(cProyecto oProy) {
        String result = "Error";
        String SQL = "INSERT INTO tiempos(tiempos_id, tiempos_fecha, tiempos_observacion, tiempos_anio, tiempos_nombre, tiempos_tipo) VALUES ('"+oProy.getTp_id()+"', '"+oProy.getDeudas_contrato()+"', '"+oProy.getEstado_observacion()+"', '"+oProy.getProyecto_anio()+"', '"+oProy.getTp_nombre()+"', '"+oProy.getDeudas_id()+"');";

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
