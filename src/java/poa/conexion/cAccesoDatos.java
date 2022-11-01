/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import poa.clases.cActividadRequerimiento;
import poa.clases.cComponenteMeta;
import poa.clases.cPerspectivaObjetivo;
import poa.clases.cProyecto;
import poa.clases.cRequerimientosGenerales;

/**
 *
 * @author Erika Arévalo
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
            this.cnn = DriverManager.getConnection(poa.conexion.cConexion.databaseURL, poa.conexion.cConexion.usuarioDB, poa.conexion.cConexion.claveDB);
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

    public Byte ejecutarSelect(String SQL) {
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

    public Byte ejecutarSQLAcciones(String SQL, cProyecto cProy) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            smt.setInt(1, cProy.getProyecto_id());
            smt.setString(2, cProy.getProac().getAm_nombre());
            smt.setString(3, cProy.getProac().getProceso_codigo());
            smt.setString(4, cProy.getProac().getAm_id());
            smt.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte ejecutarSQLDeudas(String SQL, cProyecto cProy) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            smt.setInt(1, cProy.getDeudas_id());
            smt.setInt(2, cProy.getDeudas_oei());
            smt.setString(3, cProy.getProyecto_nombre());
            smt.setString(4, cProy.getDeudas_proceso());
            smt.setString(5, cProy.getDeudas_contrato());
            smt.setInt(6, cProy.getDeudas_tcon());
            smt.setInt(7, cProy.getDeudas_financiamiento());
            smt.setDouble(8, cProy.getDeuda_monto_contrato());
            smt.setInt(9, cProy.getProyecto_ag());
            smt.setDouble(10, cProy.getDeuda_monto_iva());
            smt.setString(11, cProy.getDeudas_presupuesto());
            smt.setInt(12, cProy.getTp_id());
            smt.setDouble(13, cProy.getDeudas_anticipo());
            smt.setInt(14, cProy.getProyecto_anio());
            smt.setDouble(15, cProy.getDeudas_monto_pendiente());
            smt.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte ejecutarSQLDeudasUpdate(String SQL, cProyecto cProy) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            smt.setInt(1, cProy.getDeudas_oei());
            smt.setString(2, cProy.getProyecto_nombre());
            smt.setString(3, cProy.getDeudas_proceso());
            smt.setString(4, cProy.getDeudas_contrato());
            smt.setInt(5, cProy.getDeudas_tcon());
            smt.setInt(6, cProy.getDeudas_financiamiento());
            smt.setDouble(7, cProy.getDeuda_monto_contrato());
            smt.setDouble(8, cProy.getDeuda_monto_iva());
            smt.setString(9, cProy.getDeudas_presupuesto());
            smt.setInt(10, cProy.getTp_id());
            smt.setDouble(11, cProy.getDeudas_anticipo());
            smt.setDouble(12, cProy.getDeudas_monto_pendiente());
            smt.setInt(13, cProy.getDeudas_id());
            smt.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte ejecutarSQLDeudasUpdateAg(String SQL, cProyecto cProy) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            smt.setInt(1, cProy.getDeudas_oei());
            smt.setString(2, cProy.getProyecto_nombre());
            smt.setString(3, cProy.getDeudas_proceso());
            smt.setString(4, cProy.getDeudas_contrato());
            smt.setInt(5, cProy.getDeudas_tcon());
            smt.setInt(6, cProy.getDeudas_financiamiento());
            smt.setDouble(7, cProy.getDeuda_monto_contrato());
            smt.setDouble(8, cProy.getDeuda_monto_iva());
            smt.setString(9, cProy.getDeudas_presupuesto());
            smt.setInt(10, cProy.getTp_id());
            smt.setDouble(11, cProy.getDeudas_anticipo());
            smt.setInt(12, cProy.getEstado_id());
            smt.setDouble(13, cProy.getDeudas_monto_pendiente());
            smt.setInt(14, cProy.getDeudas_id());
            smt.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }
    
    public Byte ejecutarSQLDeudasUpdateDe(String SQL, cProyecto cProy) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            smt.setInt(1, cProy.getDeudas_oei());
            smt.setString(2, cProy.getProyecto_nombre());
            smt.setString(3, cProy.getDeudas_proceso());
            smt.setString(4, cProy.getDeudas_contrato());
            smt.setInt(5, cProy.getDeudas_tcon());
            smt.setInt(6, cProy.getDeudas_financiamiento());
            smt.setDouble(7, cProy.getDeuda_monto_contrato());
            smt.setDouble(8, cProy.getDeuda_monto_iva());
            smt.setString(9, cProy.getDeudas_presupuesto());
            smt.setInt(10, cProy.getTp_id());
            smt.setDouble(11, cProy.getDeudas_anticipo());
            smt.setInt(12, cProy.getDeudas_reforma());
            smt.setInt(13, cProy.getEstado_id());
            smt.setDouble(14, cProy.getDeudas_monto_pendiente());
            smt.setInt(15, cProy.getDeudas_id());
            smt.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte ejecutarSelectPersObj(String SQL, Integer obj) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            smt.setInt(1, obj);
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

    public Integer ejecutarComando(String SQL) {
        Integer result = 0;
        try {
            PreparedStatement smt = this.cnn.prepareStatement(SQL);
            result = smt.executeUpdate();
            desconectar();
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());

            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdate(String strSQL) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateAreaPro(String strSQL, cProyecto cProy) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cProy.getProyecto_id());
            stm.setInt(2, cProy.getProyecto_ag());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateComponente(String strSQL, cComponenteMeta cCom) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cCom.getComponente_id());
            stm.setInt(2, cCom.getComponente_proyecto());
            stm.setString(3, cCom.getComponente_nombre());
            stm.setInt(4, cCom.getComponente_ag());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateComponenteMod(String strSQL, cComponenteMeta cCom) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setString(1, cCom.getComponente_nombre());
            stm.setInt(2, cCom.getComponente_id());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateMeta(String strSQL, cComponenteMeta cCom) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cCom.getComponente_id());
            stm.setInt(2, cCom.getMeta_id());
            stm.setString(3, cCom.getMeta_nombre());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateMetaMod(String strSQL, cComponenteMeta cCom) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setString(1, cCom.getMeta_nombre());
            stm.setInt(2, cCom.getMeta_id());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateIndicador(String strSQL, cComponenteMeta cCom) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cCom.getIndicador_id());
            stm.setInt(2, cCom.getMeta_id());
            stm.setString(3, cCom.getIndicador_nombre());
            stm.setString(4, cCom.getIndicador_descripcion());
            stm.setString(5, cCom.getIndicador_tipo());
            stm.setString(6, cCom.getIndicador_ejecutado());
            stm.setString(7, cCom.getIndicador_planificado());
            stm.setDouble(8, cCom.getIndicador_numero());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateIndicadorMod(String strSQL, cComponenteMeta cCom) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setString(1, cCom.getIndicador_nombre());
            stm.setString(2, cCom.getIndicador_descripcion());
            stm.setString(3, cCom.getIndicador_tipo());
            stm.setString(4, cCom.getIndicador_ejecutado());
            stm.setString(5, cCom.getIndicador_planificado());
            stm.setDouble(6, cCom.getIndicador_numero());
            stm.setInt(7, cCom.getIndicador_id());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateActividad(String strSQL, cActividadRequerimiento cAct) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cAct.getActividad_id());
            stm.setString(2, cAct.getActividad_nombre());
            stm.setString(3, cAct.getActividad_responsable());
            stm.setInt(4, cAct.getActividad_tipo());
            stm.setInt(5, cAct.getActividad_componente());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }
    
    public Byte executeUpdateActividadRep(String strSQL, cActividadRequerimiento cAct) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cAct.getActividad_id());
            stm.setString(2, cAct.getActividad_nombre());
            stm.setString(3, cAct.getActividad_responsable());
            stm.setInt(4, cAct.getActividad_tipo());
            stm.setInt(5, cAct.getActividad_componente());
            stm.setDouble(6, cAct.getActividad_monto());
            stm.setInt(7, cAct.getActividad_prioridad());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateActividadMod(String strSQL, cActividadRequerimiento cAct) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setString(1, cAct.getActividad_nombre());
            stm.setString(2, cAct.getActividad_responsable());
            stm.setInt(3, cAct.getActividad_tipo());
            stm.setInt(4, cAct.getActividad_id());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateActividadMes(String strSQL, cActividadRequerimiento cAct) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cAct.getActividad_id());
            stm.setInt(2, cAct.getMes_id());
            stm.setDouble(3, cAct.getMes_porcentaje());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeInsertarRequerimientoGeneral(String strSQL, cRequerimientosGenerales cRg) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cRg.getRg_id());
            stm.setString(2, cRg.getRg_nombre());
            stm.setString(3, cRg.getRg_descripcion());
            stm.setDouble(4, cRg.getRg_costo_unitario());
            stm.setInt(5, cRg.getRg_anio());
            stm.setInt(6, cRg.getAg_id());
            stm.setInt(7, cRg.getRg_unidad());
            stm.setString(8, cRg.getRg_cpc());
            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeInsertReqCuatrimestre(String strSQL, cActividadRequerimiento cAct) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, cAct.getMes_id());
            stm.setInt(2, cAct.getReq_id());
            stm.setDouble(3, cAct.getActividad_porcentaje());

            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeInsertEnviar(String strSQL, cProyecto oProy) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, oProy.getEstado_id());
            stm.setInt(2, oProy.getProyecto_id());
            stm.setString(3, oProy.getUsuario_nombre());

            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeInsertEnviarObserv(String strSQL, cProyecto oProy) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, oProy.getEstado_id());
            stm.setInt(2, oProy.getProyecto_id());
            stm.setString(3, oProy.getUsuario_nombre());
            stm.setString(4, oProy.getEstado_observacion());

            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeInsertEnviarReq(String strSQL, cProyecto oProy) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, oProy.getProyecto_id());
            stm.setInt(2, oProy.getEstado_id());
            stm.setString(3, oProy.getUsuario_nombre());
            stm.setString(4, oProy.getProyecto_responsable());

            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeInsertMontos(String strSQL, cProyecto oProy) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, oProy.getMp_anio());
            stm.setDouble(2, oProy.getProyecto_id());
            stm.setDouble(3, oProy.getMp_monto());

            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeUpdateMontos(String strSQL, cProyecto oProy) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setDouble(1, oProy.getMp_monto());
            stm.setInt(2, oProy.getMp_anio());
            stm.setDouble(3, oProy.getProyecto_id());

            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte executeDelete(String strSQL, Integer id) {
        Byte result = 0;
        try {
            PreparedStatement stm = this.cnn.prepareStatement(strSQL);
            stm.setInt(1, id);

            stm.executeUpdate();
            desconectar();
            this.rs = null;
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " : " + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }
}
