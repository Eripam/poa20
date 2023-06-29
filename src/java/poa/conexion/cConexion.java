/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.conexion;

/**
 *
 * @author Erika Arévalo
 */

public class cConexion {
    /*Base local*/
    public static String driverClass = "org.postgresql.Driver";
    //public static String databaseURL = "jdbc:postgresql://localhost:5432/sipserver-17-11-2022";
    public static String databaseURL = "jdbc:postgresql://localhost:5432/sipserver-22-06-202";
    public static String usuarioDB = "postgres";
    public static String claveDB = "123";
    
    /*Servidor institucional
    public static String driverClass = "org.postgresql.Driver";
    public static String databaseURL = "jdbc:postgresql://172.17.102.150:3311/sip";
    public static String usuarioDB = "user_sieb";
    public static String claveDB = "3sp0chPlanificacion";
      
    /*public static String driverClass = "org.postgresql.ds.PGSimpleDataSource";
    public static String databaseURL = "jdbc:postgresql://localhost/sip";
    public static String usuarioDB = "user_sieb";
    public static String claveDB = "dM<31B)8)I&Z";*/
    
    /*Servidor de pruebas
    public static String driverClass = "org.postgresql.ds.PGSimpleDataSource";
    public static String databaseURL = "jdbc:postgresql://172.17.103.25:5445/sip";
    public static String usuarioDB = "usiplan";
    public static String claveDB = "2022usiplan@@";*/
}
