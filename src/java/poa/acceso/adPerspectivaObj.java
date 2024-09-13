/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cPerspectivaObjetivo;
import poa.clases.cPoliEstra;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adPerspectivaObj {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Listar Objetivos estrategicos
    static public ResultSet listaPerspectiva() {
        ResultSet rs = null;
        String SQL = "select * from perspectiva inner join vision on perspectiva_vision=vision_id where perspectiva_estado=1 and vision_estado=1 order by perspectiva_id asc";
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

    //Listar Visión
    static public ResultSet listaVision() {
        ResultSet rs = null;
        String SQL = "select * from vision where vision_estado=1 order by vision_id asc";
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

    //Listar Tipo Proyecto
    static public ResultSet listaTipoProyecto() {
        ResultSet rs = null;
        String SQL = "select * from tipo_proyecto where tp_estado=1 order by tp_id asc";
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
    
    //Listar Objetivos Operativos
    static public ResultSet listaObjetivosO() {
        ResultSet rs = null;
        String SQL = "select * from objetivo inner join perspectiva on perspectiva_id=objetivo_perspectiva where perspectiva_estado=1 and objetivo_estado=1 order by objetivo_id asc";
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
    
    //Listar Politicas
    static public ResultSet listaPoliticas() {
        ResultSet rs = null;
        String SQL = "select * from politicas inner join objetivo on politicas_objetivo=objetivo_id where objetivo_estado=1 and politicas_estado=1 order by politicas_id asc";
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

    //Listar Objetivos Operativos
    public List<cPerspectivaObjetivo> listarObjetivosEstrategicos() {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select *, case when perspectiva_estado=1 then 'Activo' when perspectiva_estado=0 then 'Inactivo' end as estado from perspectiva inner join tipo_proyecto on perspectiva_tp=tp_id join vision on perspectiva_vision=vision_id order by perspectiva_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPersp = new cPerspectivaObjetivo();
                        oPersp.setPerspectiva_id(rsPersp.getInt("perspectiva_id"));
                        oPersp.setPerspectiva_nombre(rsPersp.getString("perspectiva_nombre"));
                        oPersp.setPerspectiva_estado(rsPersp.getInt("perspectiva_estado"));
                        oPersp.setEstado_nombre(rsPersp.getString("estado"));
                        oPersp.setVision_nombre(rsPersp.getString("vision_nombre"));
                        oPersp.setVision_id(rsPersp.getInt("vision_id"));
                        oPersp.setTo_id(rsPersp.getInt("tp_id"));
                        oPersp.setTo_nombre(rsPersp.getString("tp_nombre"));
                        oPersp.setPerspectiva_codigo(rsPersp.getString("perspectiva_codigo"));
                        result.add(oPersp);
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

    //Listar Objetivos Operativos
    public List<cPerspectivaObjetivo> listarObjetivosOperativos(Integer obj) {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select * from objetivo where objetivo_perspectiva=? and objetivo_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, obj) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPersp = new cPerspectivaObjetivo();
                        oPersp.setObjetivo_id(rsPersp.getInt("objetivo_id"));
                        oPersp.setObjetivo_nombre(rsPersp.getString("objetivo_nombre"));
                        result.add(oPersp);
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

    //Listar Objetivos Operativos sin oei
    public List<cPerspectivaObjetivo> listarObjetivosOperativos() {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select *, case when objetivo_estado=1 then 'Activo' when objetivo_estado=0 then 'Inactivo' end as estado from objetivo inner join perspectiva on objetivo_perspectiva=perspectiva_id where perspectiva_estado=1 order by objetivo_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPersp = new cPerspectivaObjetivo();
                        oPersp.setObjetivo_id(rsPersp.getInt("objetivo_id"));
                        oPersp.setObjetivo_nombre(rsPersp.getString("objetivo_nombre"));
                        oPersp.setObjetivo_codigo(rsPersp.getString("objetivo_codigo"));
                        oPersp.setPerspectiva_codigo(rsPersp.getString("perspectiva_codigo"));
                        oPersp.setPerspectiva_id(rsPersp.getInt("perspectiva_id"));
                        oPersp.setPerspectiva_nombre(rsPersp.getString("perspectiva_nombre"));
                        oPersp.setObjetivo_estado(rsPersp.getInt("objetivo_estado"));
                        oPersp.setEstado_nombre(rsPersp.getString("estado"));
                        result.add(oPersp);
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

    //Listar Objetivos por Areas
    public List<cPerspectivaObjetivo> listarObjetivosAreas(Integer area) {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select * from objetivo_area inner join perspectiva on obja_objetivo=perspectiva_id where obja_area=? and perspectiva_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, area) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPersp = new cPerspectivaObjetivo();
                        oPersp.setObjetivo_id(rsPersp.getInt("obja_objetivo"));
                        result.add(oPersp);
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

    //Listar Tipo de perspectiva
    public List<cPerspectivaObjetivo> listarTipoPerspectiva(Integer area) {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select * from perspectiva where perspectiva_id=?;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, area) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPersp = new cPerspectivaObjetivo();
                        oPersp.setTo_id(rsPersp.getInt("perspectiva_tp"));
                        result.add(oPersp);
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
    
    //Listar Objetivos por Areas
    public List<cPerspectivaObjetivo> listarObjetivosAreas() {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select * from objetivo_area inner join area_gestion on obja_area=ag_id join perspectiva on obja_objetivo=perspectiva_id where perspectiva_estado=1 order by ag_tag, ag_id, perspectiva_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPersp = new cPerspectivaObjetivo();
                        oPersp.setObjetivo_id(rsPersp.getInt("obja_objetivo"));
                        oPersp.setAp_id(rsPersp.getInt("ag_id"));
                        oPersp.setAp_nombre(rsPersp.getString("ag_nombre"));
                        oPersp.setObjetivo_nombre(rsPersp.getString("perspectiva_nombre"));
                        oPersp.setPerspectiva_codigo(rsPersp.getString("perspectiva_codigo"));
                        result.add(oPersp);
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

    //Listar Politicas Estrategias
    public List<cPoliEstra> listarPoliticasEst(Integer obj) {
        List<cPoliEstra> result = new ArrayList<cPoliEstra>();
        String SQL = "select * from politicas where politicas_objetivo=? and politicas_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, obj) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPoliEstra oPol = new cPoliEstra();
                        oPol.setPolitica_id(rsPersp.getInt("politicas_id"));
                        oPol.setPolitica_nombre(rsPersp.getString("politicas_nombre"));
                        oPol.setPol(listarEstrategias(oPol));
                        result.add(oPol);
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
    
    //Listar Politicas
    public List<cPoliEstra> listarPoliticas() {
        List<cPoliEstra> result = new ArrayList<cPoliEstra>();
        String SQL = "select *, case when politicas_estado=1 then 'Activo' when politicas_estado=0 then 'Inactivo' end as estado from politicas inner join objetivo on politicas_objetivo=objetivo_id where objetivo_estado=1 order by objetivo_id, politicas_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPoliEstra oPol = new cPoliEstra();
                        oPol.setPolitica_id(rsPersp.getInt("politicas_id"));
                        oPol.setPolitica_nombre(rsPersp.getString("politicas_nombre"));
                        oPol.setPolitica_estado(rsPersp.getInt("politicas_estado"));
                        oPol.setEstado_nombre(rsPersp.getString("estado"));
                        oPol.setObjetivo_id(rsPersp.getInt("objetivo_id"));
                        oPol.setObjetivo_nombre(rsPersp.getString("objetivo_nombre"));
                        oPol.setObjetivo_codigo(rsPersp.getString("objetivo_codigo"));
                        result.add(oPol);
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

    //Listar Estrategias
    public List<cPoliEstra> listarEstrategias(cPoliEstra obj) {
        List<cPoliEstra> result = new ArrayList<cPoliEstra>();
        String SQL = "select * from estrategia where estrategia_politica=? and estrategia_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, obj.getPolitica_id()) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPoliEstra oPol = new cPoliEstra();
                        oPol.setEstrategia_id(rsPersp.getInt("estrategia_id"));
                        oPol.setEstrategia_nombre(rsPersp.getString("estrategia_nombre"));
                        result.add(oPol);
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
    
    //Listar Estrategias
    public List<cPoliEstra> listarEstrategias() {
        List<cPoliEstra> result = new ArrayList<cPoliEstra>();
        String SQL = "select *, case when estrategia_estado=1 then 'Activo' when estrategia_estado=0 then 'Inactivo' end as estado from estrategia inner join politicas on estrategia_politica=politicas_id where politicas_estado=1 order by politicas_id, estrategia_id;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPoliEstra oPol = new cPoliEstra();
                        oPol.setEstrategia_id(rsPersp.getInt("estrategia_id"));
                        oPol.setEstrategia_nombre(rsPersp.getString("estrategia_nombre"));
                        oPol.setEstado_nombre(rsPersp.getString("estado"));
                        oPol.setPolitica_estado(rsPersp.getInt("estrategia_estado"));
                        oPol.setPolitica_id(rsPersp.getInt("politicas_id"));
                        oPol.setPolitica_nombre(rsPersp.getString("politicas_nombre"));
                        result.add(oPol);
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

    //Listar Actividades Presupuestarias
    public List<cPerspectivaObjetivo> listarActividades(Integer obj, Integer estado) {
        Integer anio;
        if(estado==2024){
            anio=2023;
        }else{
            anio=estado;
        }
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select * from actividad_presupuestaria where ap_objetivo='" + obj + "' and ap_estado='" + anio + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oAct = new cPerspectivaObjetivo();
                        oAct.setAp_id(rsPersp.getInt("ap_id"));
                        oAct.setAp_nombre(rsPersp.getString("ap_nombre"));
                        result.add(oAct);
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
    
    //Listar Actividades Presupuestarias
    public List<cPerspectivaObjetivo> listarActividadesPres() {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select *, case when ap_estado_ap=1 then 'Activo' when ap_estado_ap=0 then 'Inactivo' end as estado from actividad_presupuestaria inner join objetivo on ap_objetivo=objetivo_id where objetivo_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oAct = new cPerspectivaObjetivo();
                        oAct.setAp_id(rsPersp.getInt("ap_id"));
                        oAct.setAp_nombre(rsPersp.getString("ap_nombre"));
                        oAct.setObjetivo_codigo(rsPersp.getString("objetivo_codigo"));
                        oAct.setAp_codigo(rsPersp.getString("ap_codigo"));
                        oAct.setEstado_nombre(rsPersp.getString("estado"));
                        oAct.setTo_id(rsPersp.getInt("ap_estado"));
                        oAct.setObjetivo_estado(rsPersp.getInt("ap_estado_ap"));
                        oAct.setObjetivo_id(rsPersp.getInt("objetivo_id"));
                        oAct.setObjetivo_nombre(rsPersp.getString("objetivo_nombre"));
                        result.add(oAct);
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

    //Listar Misióny visión
    public List<cPerspectivaObjetivo> listarVisionMision() {
        List<cPerspectivaObjetivo> result = new ArrayList<cPerspectivaObjetivo>();
        String SQL = "select *, case when vision_estado=1 then 'Activo' when vision_estado=0 then 'Inactivo' end as estado from vision;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oAct = new cPerspectivaObjetivo();
                        oAct.setVision_id(rsPersp.getInt("vision_id"));
                        oAct.setVision_nombre(rsPersp.getString("vision_nombre"));
                        oAct.setVision_ag(rsPersp.getInt("vision_ag"));
                        oAct.setVision_mision(rsPersp.getString("vision_mision"));
                        oAct.setVision_fi(rsPersp.getString("vision_fi"));
                        oAct.setVision_ff(rsPersp.getString("vision_ff"));
                        oAct.setVision_estado(rsPersp.getInt("vision_estado"));
                        oAct.setEstado_nombre(rsPersp.getString("estado"));
                        result.add(oAct);
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

    //Ingresar Vision
    public String IngresarVision(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar la visión";
        String SQL = "INSERT INTO public.vision(\n"
                + "	vision_id, vision_nombre, vision_mision, vision_fi, vision_ff, vision_ag)\n"
                + "	VALUES ((select max(vision_id) from vision)+1,'" + cAct.getVision_nombre() + "', '" + cAct.getVision_mision() + "', '" + cAct.getVision_fi() + "', '" + cAct.getVision_ff() + "', 1);";

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

    //Modificar Vision
    public String ModificarVision(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar la visión";
        String SQL = "UPDATE public.vision SET\n"
                + "	vision_nombre='" + cAct.getVision_nombre() + "', vision_mision='" + cAct.getVision_mision() + "', vision_fi='" + cAct.getVision_fi() + "', vision_ff='" + cAct.getVision_ff() + "', vision_estado='" + cAct.getVision_estado() + "' where vision_id='" + cAct.getVision_id() + "';";

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

    //Ingresar OEI
    public String IngresarOEI(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar la visión";
        String SQL = "INSERT INTO public.perspectiva(\n"
                + "	perspectiva_id, perspectiva_nombre, perspectiva_vision, perspectiva_tp, perspectiva_codigo)\n"
                + "	VALUES ((select max(perspectiva_id) from perspectiva)+1,'" + cAct.getPerspectiva_nombre() + "', '" + cAct.getVision_id() + "', '" + cAct.getTo_id() + "', '" + cAct.getPerspectiva_codigo() + "');";

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

    //Modificar OEI
    public String ModificarOEI(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar la visión";
        String SQL = "UPDATE public.perspectiva SET\n"
                + "	perspectiva_nombre='" + cAct.getPerspectiva_nombre() + "', perspectiva_codigo='" + cAct.getPerspectiva_codigo() + "', perspectiva_estado='" + cAct.getPerspectiva_estado() + "', perspectiva_vision='" + cAct.getVision_id() + "' where perspectiva_id='" + cAct.getPerspectiva_id() + "';";

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

    //Ingresar OO
    public String IngresarOO(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar el objetivo operativo";
        String SQL = "INSERT INTO public.objetivo(\n"
                + "	objetivo_id, objetivo_nombre, objetivo_perspectiva, objetivo_codigo)\n"
                + "	VALUES ((select max(objetivo_id) from objetivo)+1,'" + cAct.getObjetivo_nombre() + "', '" + cAct.getPerspectiva_id()+ "', '" + cAct.getObjetivo_codigo()+ "');";

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

    //Modificar OO
    public String ModificarOO(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar el objetivo operativo";
        String SQL = "UPDATE public.objetivo SET\n"
                + "	objetivo_nombre='" + cAct.getObjetivo_nombre() + "', objetivo_codigo='" + cAct.getObjetivo_codigo() + "', objetivo_estado='" + cAct.getObjetivo_estado() + "', objetivo_perspectiva='" + cAct.getPerspectiva_id()+ "' where objetivo_id='" + cAct.getObjetivo_id() + "';";

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
    
    //Ingresar Programa
    public String IngresarPro(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar el programa";
        String SQL = "INSERT INTO public.actividad_presupuestaria(\n"
                + "	ap_id, ap_nombre, ap_objetivo, ap_codigo, ap_estado)\n"
                + "	VALUES ((select max(ap_id) from actividad_presupuestaria)+1,'" + cAct.getAp_nombre()+ "', '" + cAct.getObjetivo_id()+ "', '" + cAct.getAp_codigo()+ "', '"+cAct.getTo_id()+"');";

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

    //Modificar Pro
    public String ModificarPro(cPerspectivaObjetivo cAct) {
        String result = "Error al modificar programa";
        String SQL = "UPDATE public.actividad_presupuestaria SET\n"
                + "	ap_nombre='" + cAct.getAp_nombre()+ "', ap_codigo='" + cAct.getAp_codigo() + "', ap_estado_ap='" + cAct.getObjetivo_estado() + "', ap_objetivo='" + cAct.getObjetivo_id()+ "', ap_estado='"+cAct.getTo_id()+"' where ap_id='" + cAct.getAp_id()+ "';";

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
    
    //Ingresar objetivo unidad
    public String IngresarObejtivoUnidad(cPerspectivaObjetivo cAct) {
        String result = "Error al ingresar objetivo unidad";
        String SQL = "INSERT INTO public.objetivo_area(obja_objetivo, obja_area)\n"
                + "	values('" + cAct.getPerspectiva_id()+ "', '"+cAct.getAp_id()+"');";

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
    
    //Eliminar objetivo unidad
    public String EliminarObejtivoUnidad(cPerspectivaObjetivo cAct) {
        String result = "Error al eliminar objetivo unidad";
        String SQL = "DELETE FROM public.objetivo_area WHERE obja_objetivo='" + cAct.getPerspectiva_id()+ "' and obja_area='"+cAct.getAp_id()+"';";

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
    
    //Ingresar Politica
    public String IngresarPolitica(cPoliEstra cAct) {
        String result = "Error al ingresar el politica";
        String SQL = "INSERT INTO public.politicas(\n"
                + "	politicas_id, politicas_nombre, politicas_objetivo)\n"
                + "	VALUES ((select max(politicas_id) from politicas)+1,'" + cAct.getPolitica_nombre()+ "', '" + cAct.getObjetivo_id()+ "');";

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

    //Modificar Politica
    public String ModificarPolitica(cPoliEstra cAct) {
        String result = "Error al modificar politica";
        String SQL = "UPDATE public.politicas SET\n"
                + "	politicas_nombre='" + cAct.getPolitica_nombre()+ "', politicas_estado='" + cAct.getPolitica_estado()+ "', politicas_objetivo='" + cAct.getObjetivo_id()+ "' where politicas_id='" + cAct.getPolitica_id()+ "';";

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
    //Ingresar Estrategia
    public String IngresarEstrategia(cPoliEstra cAct) {
        String result = "Error al ingresar la estrategia";
        String SQL = "INSERT INTO public.estrategia(\n"
                + "	estrategia_id, estrategia_nombre, estrategia_politica)\n"
                + "	VALUES ((select max(estrategia_id) from estrategia)+1,'" + cAct.getEstrategia_nombre()+ "', '" + cAct.getPolitica_id()+ "');";

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

    //Modificar Estrategia
    public String ModificarEstrategia(cPoliEstra cAct) {
        String result = "Error al modificar la estrategia";
        String SQL = "UPDATE public.estrategia SET\n"
                + "	estrategia_nombre='" + cAct.getEstrategia_nombre()+ "', estrategia_estado='" + cAct.getPolitica_estado()+ "', estrategia_politica='" + cAct.getPolitica_id()+ "' where estrategia_id='" + cAct.getEstrategia_id()+ "';";

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
