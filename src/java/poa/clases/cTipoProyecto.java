/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.clases;

/**
 *
 * @author Cristian
 */
public class cTipoProyecto {

    private Integer intId;
    private String strNombre;
    private Integer intEstado;

    public cTipoProyecto() {
        this.intId = 0;
        this.strNombre = "";
        this.intEstado = 0;
    }

    public Integer getIntId() {
        return intId;
    }

    public void setIntId(Integer intId) {
        this.intId = intId;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public Integer getIntEstado() {
        return intEstado;
    }

    public void setIntEstado(Integer intEstado) {
        this.intEstado = intEstado;
    }
    

}
