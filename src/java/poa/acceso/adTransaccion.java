/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import poa.clases.cTransaccion;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adTransaccion {

    public static Integer codigoSiguiente() {
        Integer result = null;
        String SQL = "SELECT (MAX(transaccion_id)) as codigo FROM transaccion;";
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
            System.out.println("Error: " + e.getMessage());
        } finally {
            return result;
        }
    }

    /**
     * Metodo para insertar una transacción
     *
     * @param objTransaccion
     * @return
     */
    public static Boolean ingresarTransaccion(cTransaccion objTransaccion) {
        Boolean blnResultado = false;
        Integer intIdTransaccion = codigoSiguiente();
        String SQL = "INSERT INTO public.transaccion(transaccion_id, transaccion_cedula, transaccion_ag, transaccion_descripcion, transaccion_tt)\n"
                + "VALUES (" + intIdTransaccion + ", '" + objTransaccion.getTransaccion_cedula()+ "', '" + objTransaccion.getTransaccion_ag()+ "','" + objTransaccion.getTransaccion_descripcion()+ "', " + objTransaccion.getTransaccion_tipo()+ ");";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    blnResultado = true;
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        }
        return blnResultado;
    }
}
