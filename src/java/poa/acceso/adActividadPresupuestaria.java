/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cActividadPresupuestaria;
import poa.clases.cPerspectivaObjetivo;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Desarrollo
 */
public class adActividadPresupuestaria {
    private Exception error;

    public Exception getError() {
        return error;
    }
    
    public List<cActividadPresupuestaria> listarActividadesPresupuestarias(Integer intObjetivoOperativo, Integer estado) {
        List<cActividadPresupuestaria> result = new ArrayList<cActividadPresupuestaria>();
        String SQL = "SELECT ap_id, ap_nombre, ap_estado, objetivo_id, objetivo_nombre\n"
                + "FROM public.actividad_presupuestaria\n"
                + "JOIN public.objetivo ON actividad_presupuestaria.ap_objetivo = objetivo.objetivo_id\n"
                + "WHERE ap_objetivo = '"+intObjetivoOperativo+"' and ap_estado='"+estado+"';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPerspectivaObjetivo = new cPerspectivaObjetivo();
                        cActividadPresupuestaria oActPresu = new cActividadPresupuestaria();
                        oActPresu.setIntIdActividadPresupuestaria(rsPersp.getInt("ap_id"));
                        oActPresu.setStrNombreActividadPresupuestaria(rsPersp.getString("ap_nombre"));
                        oPerspectivaObjetivo.setObjetivo_id(rsPersp.getInt("objetivo_id"));
                        oPerspectivaObjetivo.setObjetivo_nombre(rsPersp.getString("objetivo_nombre"));
                        oActPresu.setPerspectivaObjetivo(oPerspectivaObjetivo);
                        result.add(oActPresu);
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
    
    public List<cActividadPresupuestaria> ListarRequerimientosCompras(Integer intObjetivoOperativo) {
        List<cActividadPresupuestaria> result = new ArrayList<cActividadPresupuestaria>();
        String SQL = "SELECT ap_id, ap_nombre, ap_estado, objetivo_id, objetivo_nombre\n"
                + "FROM public.actividad_presupuestaria\n"
                + "JOIN public.objetivo ON actividad_presupuestaria.ap_objetivo = objetivo.objetivo_id\n"
                + "WHERE ap_objetivo = ? and ap_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, intObjetivoOperativo) != 0) {
                    ResultSet rsPersp = ad.getRs();
                    while (rsPersp.next()) {
                        cPerspectivaObjetivo oPerspectivaObjetivo = new cPerspectivaObjetivo();
                        cActividadPresupuestaria oActPresu = new cActividadPresupuestaria();
                        oActPresu.setIntIdActividadPresupuestaria(rsPersp.getInt("ap_id"));
                        oActPresu.setStrNombreActividadPresupuestaria(rsPersp.getString("ap_nombre"));
                        oPerspectivaObjetivo.setObjetivo_id(rsPersp.getInt("objetivo_id"));
                        oPerspectivaObjetivo.setObjetivo_nombre(rsPersp.getString("objetivo_nombre"));
                        oActPresu.setPerspectivaObjetivo(oPerspectivaObjetivo);
                        result.add(oActPresu);
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
