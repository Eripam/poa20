/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.clases;

/**
 *
 * @author Erika Arévalo
 */
public class cTransaccion {

    private Integer transaccion_id;
    private String transaccion_cedula;
    private Integer transaccion_ag;
    private String transaccion_descripcion;
    private String transaccion_fecha;
    private Integer transaccion_tipo;

    public cTransaccion() {
        this.transaccion_id=0;
        this.transaccion_cedula="";
        this.transaccion_ag=0;
        this.transaccion_descripcion="";
        this.transaccion_fecha="";
        this.transaccion_tipo=0;
    }

    
    public cTransaccion(String transaccion_cedula, Integer transaccion_ag, Integer transaccion_tipo) {
        this.transaccion_cedula = transaccion_cedula;
        this.transaccion_ag = transaccion_ag;
        this.transaccion_tipo = transaccion_tipo;
    }   
       
    public Integer getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(Integer transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public String getTransaccion_cedula() {
        return transaccion_cedula;
    }

    public void setTransaccion_cedula(String transaccion_cedula) {
        this.transaccion_cedula = transaccion_cedula;
    }

    public Integer getTransaccion_ag() {
        return transaccion_ag;
    }

    public void setTransaccion_ag(Integer transaccion_ag) {
        this.transaccion_ag = transaccion_ag;
    }

    public String getTransaccion_descripcion() {
        return transaccion_descripcion;
    }

    public void setTransaccion_descripcion(String transaccion_descripcion) {
        this.transaccion_descripcion = transaccion_descripcion;
    }

    public String getTransaccion_fecha() {
        return transaccion_fecha;
    }

    public void setTransaccion_fecha(String transaccion_fecha) {
        this.transaccion_fecha = transaccion_fecha;
    }

    public Integer getTransaccion_tipo() {
        return transaccion_tipo;
    }

    public void setTransaccion_tipo(Integer transaccion_tipo) {
        this.transaccion_tipo = transaccion_tipo;
    }
}
