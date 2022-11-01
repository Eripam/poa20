/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.acceso;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poa.clases.cComponenteMeta;
import poa.conexion.cAccesoDatos;

/**
 *
 * @author Erika Arévalo
 */
public class adComponenteMeta {

    private Exception error;

    public Exception getError() {
        return error;
    }

    //Codigo Siguiente componente
    public Integer codigoSiguienteComponente() {
        Integer result = null;
        String SQL = "SELECT (MAX(componente_id)) as codigo FROM componente;";
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

    //Codigo Siguiente meta
    public Integer codigoSiguienteMeta() {
        Integer result = null;
        String SQL = "SELECT (MAX(meta_id)) as codigo FROM meta;";
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

    //Codigo Siguiente indicador
    public Integer codigoSiguienteIndicador() {
        Integer result = null;
        String SQL = "SELECT (MAX(indicador_id)) as codigo FROM indicador;";
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
    
    //Codigo Siguiente nodos logros
    public Integer codigoSiguienteNodosL() {
        Integer result = null;
        String SQL = "SELECT (MAX(ln_id)) as codigo FROM logros_nudos;";
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
    
    //Existe nodos
    public Integer existeNodos(Integer componente, Integer cuatrimestre) {
        Integer result = null;
        String SQL = "select ln_id from logros_nudos where ln_componente='"+componente+"' and ln_cuatrimestre='"+cuatrimestre+"';";
        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsCodigo = ad.getRs();
                    rsCodigo.next();
                    result = rsCodigo.getInt("ln_id");
                     if (result.equals(null)) {
                        result = 0;
                    } else {
                        result = result;
                    }
                }
            }
            ad.desconectar();
        } catch (Exception e) {

        } finally {
            return result;
        }
    }

    //Ingresar Componente
    public String IngresarComponente(cComponenteMeta cComp) {
        String result = "Error al ingresar el componente";
        String SQL = "INSERT INTO public.componente(\n"
                + "	componente_id, componente_proyecto, componente_nombre, componente_ag)\n"
                + "	VALUES (?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateComponente(SQL, cComp) != 0) {
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
    
    //Ingresar logros nodos
    public String IngresarLogrosNodos(cComponenteMeta cComp) {
        String result = "Error al ingresar el componente";
        String SQL = "INSERT INTO public.logros_nudos(\n"
                + "	ln_id, ln_nodos, ln_logros, ln_componente, ln_cuatrimestre)\n"
                + "	VALUES ('"+cComp.getMeta_id()+"', '"+cComp.getComponente_nombre()+"', '"+cComp.getMeta_nombre()+"', '"+cComp.getComponente_id()+"', '"+cComp.getMe_cuatrimestre()+"');";

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
    
    //Modificar logros nodos
    public String ModificarLogrosNodos(cComponenteMeta cComp) {
        String result = "Error al ingresar el componente";
        String SQL = "UPDATE public.logros_nudos\n"
                + "	set ln_nodos='"+cComp.getComponente_nombre()+"', ln_logros='"+cComp.getMeta_nombre()+"'\n"
                + "	WHERE ln_id='"+cComp.getMeta_id()+"';";

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

    //Ingresar Componente
    public String IngresarComponenteRep(cComponenteMeta cComp) {
        String result = "Error al ingresar el componente";
        String SQL = "INSERT INTO public.componente(\n"
                + "	componente_id, com_componente_id, componente_nombre, componente_ag)\n"
                + "	VALUES (?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateComponente(SQL, cComp) != 0) {
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

    //Ingresar Meta
    public String IngresarMeta(cComponenteMeta cMeta) {
        String result = "Error al ingresar la meta";
        String SQL = "INSERT INTO public.meta(\n"
                + "	meta_componente, meta_id, meta_nombre)\n"
                + "	VALUES (?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateMeta(SQL, cMeta) != 0) {
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

    //Ingresar Meta
    public String IngresarMetaRep(cComponenteMeta cMeta) {
        String result = "Error al ingresar la meta";
        String SQL = "INSERT INTO public.meta(\n"
                + "	met_meta_id, meta_id, meta_nombre)\n"
                + "	VALUES (?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateMeta(SQL, cMeta) != 0) {
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

    //Ingresar Indicador
    public String IngresarIndicador(cComponenteMeta cIndicador) {
        String result = "Error al ingresar el indicador";
        String SQL = "INSERT INTO public.indicador(\n"
                + "	indicador_id, indicador_meta, indicador_nombre, indicador_descripcion, indicador_tipo, indicador_ejecutado, indicador_planificado, indicador_evaluacion)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateIndicador(SQL, cIndicador) != 0) {
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

    //Ingresar Indicador
    public String IngresarIndicadorRepro(cComponenteMeta cIndicador) {
        String result = "Error al ingresar el indicador";
        String SQL = "INSERT INTO public.indicador(\n"
                + "	indicador_id, ind_indicador_id, indicador_nombre, indicador_descripcion, indicador_tipo, indicador_ejecutado, indicador_planificado, indicador_evaluacion)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateIndicador(SQL, cIndicador) != 0) {
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

    //Ingresar Indicador
    public String IngresarIndicadorRep(cComponenteMeta cIndicador) {
        String result = "Error al ingresar el indicador";
        String SQL = "INSERT INTO public.indicador(\n"
                + "	indicador_id, indicador_meta, indicador_nombre, indicador_descripcion, indicador_tipo, indicador_ejecutado, indicador_planificado, indicador_evaluacion, indicador_fecha)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, now());";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateIndicador(SQL, cIndicador) != 0) {
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

    //Listar Componentes
    public List<cComponenteMeta> ListarComponentes(Integer proy) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from componente inner join area_gestion on componente_ag=ag_id where componente_proyecto=? and componente_estado=1 order by componente_id asc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setComponente_ag(rsComp.getInt("componente_ag"));
                        cComp.setComponente_id(rsComp.getInt("componente_id"));
                        cComp.setComponente_nombre(rsComp.getString("componente_nombre"));
                        //cComp.setAg_alias(rsComp.getString("ag_alias"));
                        result.add(cComp);
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

    //Listar Componentes
    public List<cComponenteMeta> ListarComponentesMul(Integer proy, Integer area) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from componente inner join area_gestion on componente_ag=ag_id where componente_proyecto='" + proy + "' and componente_ag='" + area + "' and componente_estado=1 order by componente_id asc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setComponente_ag(rsComp.getInt("componente_ag"));
                        cComp.setComponente_id(rsComp.getInt("componente_id"));
                        cComp.setComponente_nombre(rsComp.getString("componente_nombre"));
                        //cComp.setAg_alias(rsComp.getString("ag_alias"));
                        result.add(cComp);
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

    //Listar Componentes
    public List<cComponenteMeta> ListarComponentesRep(Integer proy) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from f_listaComponente(?);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, proy) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setComponente_ag(rsComp.getInt("componenteag"));
                        cComp.setComponente_id(rsComp.getInt("componenteid"));
                        cComp.setComponente_nombre(rsComp.getString("componentenombre"));
                        cComp.setComponente_proyecto(rsComp.getInt("componenteidid"));
                        result.add(cComp);
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

    //Listar Componentes logros
    public List<cComponenteMeta> ListarComponentesLogros(Integer comp) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from componente where componente_id=?;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, comp) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setComponente_id(rsComp.getInt("componente_id"));
                        cComp.setComponente_nombre(rsComp.getString("componente_logros"));
                        cComp.setMeta_nombre(rsComp.getString("componente_nudos"));
                        result.add(cComp);
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
    
    //Listar nodos y logros
    public List<cComponenteMeta> ListarComponentesLogrosNodos(Integer comp, Integer cuatrimestre) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from logros_nudos where ln_componente='"+comp+"' and ln_cuatrimestre='"+cuatrimestre+"';";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelect(SQL) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setComponente_id(rsComp.getInt("ln_id"));
                        cComp.setComponente_nombre(rsComp.getString("ln_logros"));
                        cComp.setMeta_nombre(rsComp.getString("ln_nodos"));
                        cComp.setComponente_proyecto(rsComp.getInt("ln_cuatrimestre"));
                        result.add(cComp);
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

    //Listar Metas
    public List<cComponenteMeta> ListarMetas(Integer comp) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from meta where meta_componente=? and meta_estado=1 order by meta_id asc;";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, comp) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setMeta_id(rsComp.getInt("meta_id"));
                        cComp.setMeta_nombre(rsComp.getString("meta_nombre"));
                        result.add(cComp);
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

    //Listar Metas
    public List<cComponenteMeta> ListarMetasRep(Integer comp) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from f_listametas(?);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, comp) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setMeta_id(rsComp.getInt("metaid"));
                        cComp.setMeta_nombre(rsComp.getString("metanombre"));
                        cComp.setComponente_id(rsComp.getInt("metaidid"));
                        result.add(cComp);
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

    //Listar Indicador
    public List<cComponenteMeta> ListarIndicador(Integer indicador) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from indicador where indicador_meta=? and ((indicador_estado=1 and indicador_fecha is null) or (indicador_estado=0 and indicador_fecha is not null)) order by indicador_id asc";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, indicador) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setIndicador_id(rsComp.getInt("indicador_id"));
                        cComp.setIndicador_nombre(rsComp.getString("indicador_nombre"));
                        cComp.setIndicador_descripcion(rsComp.getString("indicador_descripcion"));
                        cComp.setIndicador_ejecutado(rsComp.getString("indicador_ejecutado"));
                        cComp.setIndicador_planificado(rsComp.getString("indicador_planificado"));
                        cComp.setIndicador_tipo(rsComp.getString("indicador_tipo"));
                        cComp.setIndicador_numero(rsComp.getDouble("indicador_evaluacion"));
                        result.add(cComp);
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

    //Listar Indicador
    public List<cComponenteMeta> ListarIndicadorRep(Integer indicador) {
        List<cComponenteMeta> result = new ArrayList<cComponenteMeta>();
        String SQL = "select * from f_listaindicador(?);";
        try {
            //Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) {  //Solicitar conectar a la BD
                if (ad.ejecutarSelectPersObj(SQL, indicador) != 0) {
                    ResultSet rsComp = ad.getRs();
                    while (rsComp.next()) {
                        cComponenteMeta cComp = new cComponenteMeta();
                        cComp.setIndicador_id(rsComp.getInt("indicadorid"));
                        cComp.setIndicador_nombre(rsComp.getString("indicadornombre"));
                        cComp.setIndicador_descripcion(rsComp.getString("indicadordescripcion"));
                        cComp.setIndicador_ejecutado(rsComp.getString("indicadorejecutado"));
                        cComp.setIndicador_planificado(rsComp.getString("indicadorplanificado"));
                        cComp.setIndicador_tipo(rsComp.getString("indicadortipo"));
                        cComp.setIndicador_numero(rsComp.getDouble("indicadorevaluacion"));
                        cComp.setComponente_id(rsComp.getInt("indicadoridid"));
                        result.add(cComp);
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

    //Modificar Componente
    public String ModificarComponente(cComponenteMeta cComp) {
        String result = "Error al modificar el componente";
        String SQL = "UPDATE public.componente\n"
                + "	SET componente_nombre=?\n"
                + "	WHERE componente_id=?;";

        try {
            // Crear un AccesoDatos

            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateComponenteMod(SQL, cComp) != 0) {
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

    //Modificar Componente logros
    public String ModificarComponenteLogros(cComponenteMeta cComp) {
        String result = "Error";
        String SQL = "UPDATE public.componente\n"
                + "	SET componente_logros='" + cComp.getComponente_nombre() + "', componente_nudos='" + cComp.getMeta_nombre() + "'\n"
                + "	WHERE componente_id='" + cComp.getComponente_id() + "';";

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

    //Modificar Meta
    public String ModificarMeta(cComponenteMeta cMeta) {
        String result = "Error al modificar la meta";
        String SQL = "UPDATE public.meta\n"
                + "	SET meta_nombre=?\n"
                + "	WHERE meta_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateMetaMod(SQL, cMeta) != 0) {
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

    //Modificar Indicador formula
    public String ModificarIndicadorFormula(cComponenteMeta cIndicador) {
        String result = "Error al ingresar el indicador";
        String SQL = "UPDATE public.indicador\n"
                + "	SET indicador_nombre=?, indicador_descripcion=?, indicador_tipo=?, indicador_ejecutado=?, "
                + "     indicador_planificado=?, indicador_evaluacion=?\n"
                + "	WHERE indicador_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeUpdateIndicadorMod(SQL, cIndicador) != 0) {
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

    //Eliminar Indicador
    public String EliminarIndicador(Integer cIndicador) {
        String result = "Error al eliminar el indicador";
        String SQL = "DELETE FROM public.indicador\n"
                + "	WHERE indicador_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cIndicador) != 0) {
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

    //Eliminar Indicador
    public String EliminarIndicadorReq(Integer cIndicador) {
        String result = "Error al eliminar el indicador";
        String SQL = "UPDATE public.indicador set indicador_estado=0, indicador_fecha=now()\n"
                + "	WHERE indicador_id='" + cIndicador + "';";

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

    //Eliminar componente
    public String EliminarComponente(Integer cIndicador) {
        String result = "Error al eliminar el componente";
        String SQL = "UPDATE componente SET componente_estado=0\n"
                + "	WHERE componente_id=?;";

        try {
            // Crear un AccesoDatos
            cAccesoDatos ad = new cAccesoDatos();
            if (ad.conectar() != 0) { //  Solicitar conectar a la BD
                if (ad.executeDelete(SQL, cIndicador) != 0) {
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
