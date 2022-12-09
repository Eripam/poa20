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
public class cProcesoAcciones {

    private String proceso_codigo;
    private String proceso_nombre;
    private Integer proceso_tipo;
    private String am_id;
    private String am_nombre;
    private String am_meta;
    private String am_codigo;
    private String am_responsable;
    private Boolean am_validar;

    public String getProceso_codigo() {
        return proceso_codigo;
    }

    public void setProceso_codigo(String proceso_codigo) {
        this.proceso_codigo = proceso_codigo;
    }

    public String getProceso_nombre() {
        return proceso_nombre;
    }

    public void setProceso_nombre(String proceso_nombre) {
        this.proceso_nombre = proceso_nombre;
    }

    public Integer getProceso_tipo() {
        return proceso_tipo;
    }

    public void setProceso_tipo(Integer proceso_tipo) {
        this.proceso_tipo = proceso_tipo;
    }

    public String getAm_id() {
        return am_id;
    }

    public void setAm_id(String am_id) {
        this.am_id = am_id;
    }

    public String getAm_nombre() {
        return am_nombre;
    }

    public void setAm_nombre(String am_nombre) {
        this.am_nombre = am_nombre;
    }

    public String getAm_meta() {
        return am_meta;
    }

    public void setAm_meta(String am_meta) {
        this.am_meta = am_meta;
    }

    public String getAm_codigo() {
        return am_codigo;
    }

    public void setAm_codigo(String am_codigo) {
        this.am_codigo = am_codigo;
    }

    public String getAm_responsable() {
        return am_responsable;
    }

    public void setAm_responsable(String am_responsable) {
        this.am_responsable = am_responsable;
    }

    public Boolean getAm_validar() {
        return am_validar;
    }

    public void setAm_validar(Boolean am_validar) {
        this.am_validar = am_validar;
    }
}
