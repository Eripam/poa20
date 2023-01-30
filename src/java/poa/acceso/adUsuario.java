/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cAreaGestion;
import poa.clases.cUsuario;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adUsuario {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Verificar usuario
    public boolean obtenerUsuarioLogin(String cedula) {
        boolean result = false;
        String SQL = "select exists(select * from asignar_usuario where au_usuario like '" + cedula + "' and au_estado=1);";
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

    //Ingreso de registro de usuarios
    public String ingresarLogin(cUsuario objUsuario) {
        String blnResultado = null;
        String SQL = "INSERT INTO public.login(\n"
                + "	login_cedula, login_nombre)\n"
                + "	VALUES ('" + objUsuario.getLogin_cedula() + "', '" + objUsuario.getLogin_nombre() + "');";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    blnResultado = "Correcto";
                } else {
                    blnResultado = "Error";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        }
        return blnResultado;
    }

    //Listar Usuario login
    public static List<cUsuario> ListaTiposUsuarioLogin(String oLogin) {
        List<cUsuario> result = new ArrayList<cUsuario>();
        String SQL = "select *, 2023 as anio from asignar_usuario inner join tipo_usuario on au_tu=tu_id join area_gestion on au_ag=ag_id join tipo_area_gestion on tag_id=ag_tag where au_usuario='" + oLogin + "' and au_estado=1 order by tu_id asc";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsLogin = ad.getRs();
                    while (rsLogin.next()) {
                        cUsuario oLoginT = new cUsuario();
                        cAreaGestion cAg = new cAreaGestion();
                        oLoginT.setTu_id(rsLogin.getInt("tu_id"));
                        oLoginT.setTu_nombre(rsLogin.getString("tu_nombre"));
                        cAg.setAg_id(rsLogin.getInt("ag_id"));
                        cAg.setAg_nombre(rsLogin.getString("ag_nombre"));
                        cAg.setAg_alias(rsLogin.getString("ag_alias"));
                        cAg.setTag_id(rsLogin.getInt("tag_id"));
                        cAg.setTag_nombre(rsLogin.getString("tag_nombre"));
                        oLoginT.setAg(cAg);
                        oLoginT.setLogin_cedula(rsLogin.getString("au_usuario"));
                        oLoginT.setAnio(rsLogin.getInt("anio"));
                        result.add(oLoginT);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        }
        return result;
    }

    //Ingreso de registro de usuarios
    public String ingresarUsuario(cUsuario objUsuario) {
        String blnResultado = null;
        String SQL = "INSERT INTO public.usuario(\n"
                + "	usuario_cedula, usuario_nombre, usuario_titulo)\n"
                + "	VALUES ('" + objUsuario.getLogin_cedula() + "', '" + objUsuario.getLogin_nombre() + "', '"+objUsuario.getAg_nombre()+"');";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    blnResultado = "Correcto";
                } else {
                    blnResultado = "Error";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        }
        return blnResultado;
    }

    public String AsignarUsuario(cUsuario objUsuario) {
        String blnResultado = null;
        String SQL = "INSERT INTO public.asignar_usuario(\n"
                + "	au_ag, au_usuario, au_tu)\n"
                + "	VALUES ('" + objUsuario.getAg().getAg_id() + "', '" + objUsuario.getLogin_cedula() + "','" + objUsuario.getTu_id() + "');";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    blnResultado = "Correcto";
                } else {
                    blnResultado = "Error";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        }
        return blnResultado;
    }
    
    public String AsignarUsuarioEstado(cUsuario objUsuario) {
        String blnResultado = null;
        String SQL = "INSERT INTO public.asignar_usuario(\n"
                + "	au_ag, au_usuario, au_tu, au_estado)\n"
                + "	VALUES ('" + objUsuario.getAg().getAg_id() + "', '" + objUsuario.getLogin_cedula() + "','" + objUsuario.getTu_id() + "', '"+objUsuario.getUsuario_estado()+"');";
        try {
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    blnResultado = "Correcto";
                } else {
                    blnResultado = "Error";
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
        }
        return blnResultado;
    }

    public String AsignarUsuarioAreas(cUsuario cUsuario) {
        String result = "Error";
        String SQL = "INSERT INTO public.ag_usuario(\n"
                + "	agu_usuario, agu_ag)\n"
                + "	VALUES ('" + cUsuario.getLogin_cedula() + "', '" + cUsuario.getAg().getAg_id() + "');";

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

    public List<cUsuario> ListarUsuarioCompras(Integer tipo) {
        List<cUsuario> result = new ArrayList<cUsuario>();
        String SQL = "select * from usuario inner join asignar_usuario on usuario_cedula=au_usuario where au_tu='" + tipo + "' and usuario_estado=1 and usuario_cedula not like '0606043867';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsLogin = ad.getRs();
                    while (rsLogin.next()) {
                        cUsuario oUsuario = new cUsuario();
                        oUsuario.setLogin_cedula(rsLogin.getString("usuario_cedula"));
                        oUsuario.setLogin_nombre(rsLogin.getString("usuario_nombre"));
                        oUsuario.setUsuario_titulo(rsLogin.getString("usuario_titulo"));
                        oUsuario.setAreas(ListarUsuarioComprasAreas(oUsuario));
                        result.add(oUsuario);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public List<cUsuario> ListarUsuarioIDV(Integer tipo) {
        List<cUsuario> result = new ArrayList<cUsuario>();
        String SQL = "select *, (SELECT string_agg(ag_nombre::text, ' - '::text) AS ag\n"
                + "           FROM (select * from ag_usuario inner join area_gestion on agu_ag=ag_id  where agu_usuario=usuario_cedula) as con)  from usuario inner join asignar_usuario on usuario_cedula=au_usuario join tipo_usuario on au_tu=tu_id where au_ag=? and usuario_estado=1 and (au_tu<>7 and au_tu<>8 and au_tu<>5) \n"
                + "and usuario_cedula not like '0606043867' and usuario_cedula not like '0602556961' and usuario_cedula not like '0603884438' and usuario_cedula not like '0603014739' and usuario_cedula not like '1804150686';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, tipo) != 0) {
                    ResultSet rsLogin = ad.getRs();
                    while (rsLogin.next()) {
                        cUsuario oUsuario = new cUsuario();
                        oUsuario.setLogin_cedula(rsLogin.getString("usuario_cedula"));
                        oUsuario.setLogin_nombre(rsLogin.getString("usuario_nombre"));
                        oUsuario.setUsuario_titulo(rsLogin.getString("usuario_titulo"));
                        oUsuario.setTu_nombre(rsLogin.getString("tu_nombre"));
                        oUsuario.setTu_id(rsLogin.getInt("tu_id"));
                        oUsuario.setAg_nombres(rsLogin.getString("ag"));
                        oUsuario.setUsuario_estado(rsLogin.getInt("au_estado"));
                        oUsuario.setAreas(ListarUsuarioComprasAreas(oUsuario));
                        result.add(oUsuario);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public List<cUsuario> ListarUsuarioAdm() {
        List<cUsuario> result = new ArrayList<cUsuario>();
        String SQL = "select *, (SELECT string_agg(ag_nombre::text, ' - '::text) AS ag\n"
                + "           FROM (select * from ag_usuario inner join area_gestion on agu_ag=ag_id  where agu_usuario=usuario_cedula) as con) from usuario inner join asignar_usuario on usuario_cedula=au_usuario join tipo_usuario on au_tu=tu_id join area_gestion on au_ag=ag_id where usuario_estado=1 \n"
                + "and usuario_cedula not like '0606043867';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsLogin = ad.getRs();
                    while (rsLogin.next()) {
                        cUsuario oUsuario = new cUsuario();
                        oUsuario.setLogin_cedula(rsLogin.getString("usuario_cedula"));
                        oUsuario.setLogin_nombre(rsLogin.getString("usuario_nombre"));
                        oUsuario.setTu_nombre(rsLogin.getString("tu_nombre"));
                        oUsuario.setTu_id(rsLogin.getInt("tu_id"));
                        oUsuario.setAg_nombres(rsLogin.getString("ag"));
                        oUsuario.setAg_nombre(rsLogin.getString("ag_nombre"));
                        oUsuario.setAnio(rsLogin.getInt("ag_id"));
                        oUsuario.setUsuario_estado(rsLogin.getInt("au_estado"));
                        oUsuario.setAreas(ListarUsuarioComprasAreas(oUsuario));
                        oUsuario.setUsuario_titulo(rsLogin.getString("usuario_titulo"));
                        result.add(oUsuario);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public List<cUsuario> ListarUsuarioDirector(Integer tipo, Integer area) {
        List<cUsuario> result = new ArrayList<cUsuario>();
        String SQL = "select * from usuario inner join asignar_usuario on usuario_cedula=au_usuario where au_tu='" + tipo + "' and au_ag='" + area + "' and usuario_estado=1 ;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsLogin = ad.getRs();
                    while (rsLogin.next()) {
                        cUsuario oUsuario = new cUsuario();
                        oUsuario.setLogin_cedula(rsLogin.getString("usuario_cedula"));
                        oUsuario.setLogin_nombre(rsLogin.getString("usuario_nombre"));
                        oUsuario.setAreas(ListarUsuarioComprasAreas(oUsuario));
                        result.add(oUsuario);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Listar Usuario compras areas
    public List<cUsuario> ListarUsuarioComprasAreas(cUsuario oUsuario) {
        List<cUsuario> result = new ArrayList<cUsuario>();
        String SQL = "select * from ag_usuario inner join area_gestion on agu_ag=ag_id  where agu_usuario='" + oUsuario.getLogin_cedula() + "'";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsLogin = ad.getRs();
                    while (rsLogin.next()) {
                        cUsuario oLogin = new cUsuario();
                        cAreaGestion oAreas = new cAreaGestion();
                        oAreas.setAg_id(rsLogin.getInt("ag_id"));
                        oAreas.setAg_nombre(rsLogin.getString("ag_nombre"));
                        oAreas.setTag_id(rsLogin.getInt("ag_tag"));
                        oLogin.setAg(oAreas);

                        result.add(oLogin);
                    }
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Modificar registro de usuarios
    public String ModificarUsuario(cUsuario objUsuario) {
        String blnResultado = null;
        String result = "Error el usuario.";
        String SQL = "UPDATE usuario SET "
                + "usuario_nombre='" + objUsuario.getLogin_nombre() + "', usuario_titulo='"+objUsuario.getUsuario_titulo()+"',"
                + "usuario_estado= 1 "
                + "where usuario_cedula like '" + objUsuario.getLogin_cedula() + "' ";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }
    
    //Modificar registro de usuarios
    public String DesactivarUsuario(cUsuario objUsuario) {
        String blnResultado = null;
        String result = "Error.";
        String SQL = "delete from asignar_usuario where au_usuario like '" + objUsuario.getLogin_cedula() + "' and au_tu='"+objUsuario.getTu_id()+"' and au_ag='"+objUsuario.getAnio()+"';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    //Verificar usuario
    public boolean verificarUsuarioAsignar(cUsuario cUsuario) {
        boolean result = false;
        String SQL = "select exists(select * from asignar_usuario where au_usuario like '" + cUsuario.getLogin_cedula() + "' and au_tu='" + cUsuario.getTu_id() + "' and au_ag='" + cUsuario.getAg().getAg_id() + "' and au_estado=1);";
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

    //Verificar usuario
    public boolean verificarUsuarioA(cUsuario cUsuario) {
        boolean result = false;
        String SQL = "select exists(select * from usuario where usuario_cedula like '" + cUsuario.getLogin_cedula() + "');";
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

    //Contar usuario
    public Integer numeroUsuarioAsignar(cUsuario cUsuario) {
        Integer result = 0;
        String SQL = "select count(au_usuario) from asignar_usuario where au_usuario like '" + cUsuario.getLogin_cedula() + "' and au_estado=1;";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("count");
                }
            }
            ad.desconectar();
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public String ModificarUsuarioAreas(cUsuario cUsuario) {
        String result = "Error";
        String SQL = "UPDATE ag_usuario SET "
                + "agu_usuario='" + cUsuario.getLogin_cedula() + "' "
                + "where usuario_cedula= " + cUsuario.getAg().getAg_id();

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

    public String EliminarUsuarioComprasArea(cUsuario oUsuario) {
        String result = "Error";
        String SQL = "delete from ag_usuario where agu_usuario='" + oUsuario.getLogin_cedula() + "' ";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public String EliminarUsuarioCompras(cUsuario objUsuario) {
        String blnResultado = null;
        String result = "Error el usuario.";
        String SQL = "UPDATE usuario SET "
                + "usuario_estado=0"
                + "where usuario_cedula like '" + objUsuario.getLogin_cedula() + "' ";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

    public String EliminarAsignarUsuarios(cUsuario objUsuario) {
        String result = "Error el usuario.";
        String SQL = "DELETE FROM public.asignar_usuario\n"
                + "	WHERE au_usuario='" + objUsuario.getLogin_cedula() + "' and au_tu='" + objUsuario.getTu_id() + "' and au_ag='" + objUsuario.getAg().getAg_id() + "';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdate(SQL) != 0) {
                    result = "Correcto";
                    ad.desconectar();
                }
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        }
        return result;
    }

}
