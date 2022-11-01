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
import poa.clases.cProyecto;
import poa.clases.cRequerimientosGenerales;
import poa.clases.cTecho;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author PC-D2
 */
public class adRequerimientosGenerales {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Codigo Siguiente proyecto
    public Integer codigoSiguienteRequerimientos() {
        Integer result = null;
        String SQL = "SELECT (MAX(rg_id)) as codigo FROM requerimientos_generales;";
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
    
    //Existen proyectos
    static public Boolean validacionProyectosExistentes(Integer anio) {
        Boolean result = false;
        String SQL = "select exists(select * from vproyecto where proyecto_anio='"+anio+"' and (estado_id=17 or estado_id=18))";
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

    //Ingresar Areas de gestion
    public String ingresarRequerimientosGenerales(cRequerimientosGenerales oRG) {
        String blnResultado = null;
        int codigoRG = codigoSiguienteRequerimientos();
        oRG.setRg_id(codigoRG);
        String result = "Error al ingresar el requerimiento general.";
        String SQL = "INSERT INTO public.requerimientos_generales(\n"
                + "	rg_id, rg_nombre, rg_descripcion, rg_costo_unitario, rg_anio, rg_ag, rg_unidad, rg_cpc)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeInsertarRequerimientoGeneral(SQL, oRG) != 0) {
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

    public List<cRequerimientosGenerales> listarRequerimientosGenerales(Integer ag, Integer anio) {
        List<cRequerimientosGenerales> result = new ArrayList<cRequerimientosGenerales>();
        String SQL = "select * from requerimientos_generales left join unidad on rg_unidad=unidad_id where rg_estado=1 and rg_ag='"+ag+"' and rg_anio='"+anio+"' order by rg_nombre asc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsReg = ad.getRs();
                    while (rsReg.next()) {
                        cRequerimientosGenerales oReg = new cRequerimientosGenerales();
                        oReg.setRg_id(rsReg.getInt("rg_id"));
                        oReg.setRg_nombre(rsReg.getString("rg_nombre"));
                        oReg.setRg_descripcion(rsReg.getString("rg_descripcion"));
                        oReg.setRg_costo_unitario(rsReg.getDouble("rg_costo_unitario"));
                        oReg.setAg_id(rsReg.getInt("rg_anio"));
                        oReg.setRg_cpc(rsReg.getString("rg_cpc"));
                        oReg.setRg_unidad(rsReg.getInt("rg_unidad"));
                        oReg.setUnidad_nombre(rsReg.getString("unidad_nombre"));
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
    
    public List<cRequerimientosGenerales> listarRequerimientosGeneralesAnio(Integer anio) {
        List<cRequerimientosGenerales> result = new ArrayList<cRequerimientosGenerales>();
        String SQL = "select * from requerimientos_generales where rg_estado=1 and rg_anio='"+anio+"' order by rg_nombre asc;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsReg = ad.getRs();
                    while (rsReg.next()) {
                        cRequerimientosGenerales oReg = new cRequerimientosGenerales();
                        oReg.setRg_id(rsReg.getInt("rg_id"));
                        oReg.setRg_nombre(rsReg.getString("rg_nombre"));
                        oReg.setRg_descripcion(rsReg.getString("rg_descripcion"));
                        oReg.setRg_costo_unitario(rsReg.getDouble("rg_costo_unitario"));
                        oReg.setAg_id(rsReg.getInt("rg_anio"));
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

    public String modificarRequerimientosGenerales(cRequerimientosGenerales oRG) {
        String blnResultado = null;
        String result = "Error al ingresar el requerimiento general.";
        String SQL = "UPDATE requerimientos_generales SET "
                + "rg_nombre='" + oRG.getRg_nombre() + "', "
                + "rg_descripcion='" + oRG.getRg_descripcion() + "', "
                + "rg_costo_unitario='" + oRG.getRg_costo_unitario() + "', rg_anio='"+oRG.getAg_id()+"', rg_unidad='"+oRG.getRg_unidad()+"', rg_cpc='"+oRG.getRg_cpc()+"' "
                + "where rg_id= " + oRG.getRg_id();
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    result = "Modificado correctamente..";
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public List<cRequerimientosGenerales> listarAreasGestionDependientes(cRequerimientosGenerales oRG) {
        List<cRequerimientosGenerales> result = new ArrayList<cRequerimientosGenerales>();
        String SQL = "Select distinct (area_gestion.ag_id), area_gestion.ag_nombre \n"
                + "from componente \n"
                + "JOIN actividad ON componente_id=actividad_componente \n"
                + "JOIN requerimiento ON actividad_id=req_actividad \n"
                + "JOIN area_gestion ON ag_id=componente_ag WHERE requerimiento.req_rg ='" + oRG.getRg_id() + "'  ;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsReg = ad.getRs();
                    while (rsReg.next()) {
                        cRequerimientosGenerales oReg = new cRequerimientosGenerales();
                        oReg.setAg_id(rsReg.getInt("ag_id"));
                        oReg.setAg_nombre(rsReg.getString("ag_nombre"));
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

    //Mostrar requerimiento general
    static public ResultSet listaRequerimientoGeneral(Integer anio) {
        ResultSet rs = null;
        String SQL = "select * from requerimientos_generales where rg_estado=1 and rg_anio='"+anio+"' order by rg_id asc";
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
    
    //Mostrar requerimiento general detalle
    public List<cRequerimientosGenerales> listaRequerimientoGeneralDetalle(Integer reqg) {
        List<cRequerimientosGenerales> result = new ArrayList<cRequerimientosGenerales>();
        String SQL = "select * from requerimientos_generales inner join unidad on rg_unidad=unidad_id where rg_estado=1 and rg_id=? order by rg_id asc";
         try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, reqg) != 0) {
                    ResultSet rsReg = ad.getRs();
                    while (rsReg.next()) {
                        cRequerimientosGenerales oReg = new cRequerimientosGenerales();
                        oReg.setRg_id(rsReg.getInt("rg_id"));
                        oReg.setRg_nombre(rsReg.getString("rg_nombre"));
                        oReg.setRg_descripcion(rsReg.getString("rg_descripcion"));
                        oReg.setRg_costo_unitario(rsReg.getDouble("rg_costo_unitario"));
                        oReg.setRg_cpc(rsReg.getString("rg_cpc"));
                        oReg.setRg_unidad(rsReg.getInt("rg_unidad"));
                        oReg.setUnidad_nombre(rsReg.getString("unidad_nombre"));
                        result.add(oReg);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
     
    static public ResultSet ListaFinanciamientoSelect() {
        ResultSet rs = null;
        String SQL = "select * from financiamiento where financiamiento_estado=1 order by financiamiento_id;";
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

    static public ResultSet ListaTipoCompraSelect() {
        ResultSet rs = null;
        String SQL = "select * from tipo_compra where tc_estado=1 order by tc_id;";
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

    static public ResultSet ListaUnidadSelect() {
        ResultSet rs = null;
        String SQL = "select * from unidad where unidad_estado=1 order by unidad_id;";
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
}
