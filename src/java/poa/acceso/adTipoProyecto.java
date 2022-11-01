/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cTipoProyecto;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author PC-D2
 */
public class adTipoProyecto {
     public static List<cTipoProyecto> ListarTiposProyecto(Integer intIdAreaGestion, Integer anio) {
        List<cTipoProyecto> lstTiposProyecto = new ArrayList<cTipoProyecto>();
        String SQL = "select * \n"
                + "from tipo_proyecto \n"
                + "join techo on tp_id=techo_tp \n"
                + "where tp_estado=1 and techo_ag='"+intIdAreaGestion+"' and techo_ti_fecha='"+anio+"';";
        try {
            // Crear un AccesoDatos
             cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rslTipoProyecto = ad.getRs();
                    while (rslTipoProyecto.next()) {
                        cTipoProyecto objTipoProyecto = new cTipoProyecto();
                        objTipoProyecto.setIntId(rslTipoProyecto.getInt("tp_id"));
                        objTipoProyecto.setStrNombre(rslTipoProyecto.getString("tp_nombre"));
                        objTipoProyecto.setIntEstado(rslTipoProyecto.getInt("tp_estado"));
                        lstTiposProyecto.add(objTipoProyecto);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        } finally {
            return lstTiposProyecto;
        }
    }
    
}
