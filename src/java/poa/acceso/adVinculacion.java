/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import centralizada.conexion.cAccesoDatos;
import poa.clases.cUsuario;

/**
 *
 * @author erika.arevalo
 */
public class adVinculacion {

    private Exception error;

    public Exception getError() {
        return error;
    }

    public cUsuario obtenerPersonasCedula(String cedula) {
        cUsuario result = new cUsuario();
        String SQL = "select * from central.persona p inner join central.\"documentoPersonal\" d on p.per_id=d.per_id inner join central.sexo ON sexo.sex_id = p.sex_id where d.pid_valor like '" + cedula + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectCen(SQL) != 0) {
                    ResultSet rsUsuario = ad.getRs();
                    while (rsUsuario.next()) {
                        cUsuario oUsu = new cUsuario();
                        oUsu.setPer_id(rsUsuario.getInt("per_id"));
                        oUsu.setPer_cedula(rsUsuario.getString("pid_valor"));
                        oUsu.setPer_nombres(rsUsuario.getString("per_nombres"));
                        oUsu.setPer_apellidos(rsUsuario.getString("per_primerApellido")+" "+rsUsuario.getString("per_segundoApellido"));
                        oUsu.setPer_correo(rsUsuario.getString("per_email"));
                        oUsu.setSexo(rsUsuario.getString("sex_nombre"));
                        oUsu.setPer_completos(rsUsuario.getString("per_nombres")+" "+rsUsuario.getString("per_primerApellido")+" "+rsUsuario.getString("per_segundoApellido"));
                        result=oUsu;
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
}
