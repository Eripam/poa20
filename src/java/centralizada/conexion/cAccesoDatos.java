/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralizada.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author erika.arevalo
 */
public class cAccesoDatos {
     private Connection cnn;
    private ResultSet rs;
    private Exception error;

    public cAccesoDatos() {
        this.cnn = null;
        this.error = null;
    }

    /**
     * @return the error
     */
    public Exception getError() {
        return error;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    public Byte conectar() {
        Byte result = 0;
        try {
            Class.forName(poa.conexion.cConexion.driverClass);
            result = 1;
            this.cnn = DriverManager.getConnection(centralizada.conexion.cConexion.databaseURL, centralizada.conexion.cConexion.usuarioDB, centralizada.conexion.cConexion.claveDB);
            result = 2;
            //System.out.print("todo ok");
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            //System.out.print(e.toString());
            this.error = e;
            return result = 0;
        } finally {
            return result;
        }
    }

    public Byte desconectar() {
        Byte result = 0;
        try {
            this.cnn.close();
            this.cnn = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());

            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte ejecutarSelectCen(String SQL) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            this.rs = smt.executeQuery();
            desconectar();
            if (rs != null) {
                result = 1;
            }

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());

            this.error = e;
        } finally {
            return result;
        }
    }
}
