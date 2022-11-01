/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.clases;

import java.util.List;

/**
 *
 * @author Erika Arévalo
 */
public class cComponenteMeta {
    private Integer componente_id;
    private String componente_nombre;
    private Integer meta_id;
    private String meta_nombre;
    private Integer componente_proyecto;
    private Integer componente_ag;
    private Integer indicador_id;
    private String indicador_nombre;
    private String indicador_descripcion;
    private String indicador_ejecutado;
    private String indicador_planificado;
    private String indicador_tipo;
    private Double indicador_numero;
     private Integer me_cuatrimestre;
    private Double me_porcentaje;
    private List<cComponenteMeta> mes;
    private List<cActividadRequerimiento> actividad;

    public Integer getComponente_id() {
        return componente_id;
    }

    public void setComponente_id(Integer componente_id) {
        this.componente_id = componente_id;
    }

    public String getComponente_nombre() {
        return componente_nombre;
    }

    public void setComponente_nombre(String componente_nombre) {
        this.componente_nombre = componente_nombre;
    }

    public Integer getMeta_id() {
        return meta_id;
    }

    public void setMeta_id(Integer meta_id) {
        this.meta_id = meta_id;
    }

    public String getMeta_nombre() {
        return meta_nombre;
    }

    public void setMeta_nombre(String meta_nombre) {
        this.meta_nombre = meta_nombre;
    }

    public Integer getComponente_proyecto() {
        return componente_proyecto;
    }

    public void setComponente_proyecto(Integer componente_proyecto) {
        this.componente_proyecto = componente_proyecto;
    }

    public Integer getComponente_ag() {
        return componente_ag;
    }

    public void setComponente_ag(Integer componente_ag) {
        this.componente_ag = componente_ag;
    }

    public Integer getIndicador_id() {
        return indicador_id;
    }

    public void setIndicador_id(Integer indicador_id) {
        this.indicador_id = indicador_id;
    }

    public String getIndicador_nombre() {
        return indicador_nombre;
    }

    public void setIndicador_nombre(String indicador_nombre) {
        this.indicador_nombre = indicador_nombre;
    }

    public String getIndicador_descripcion() {
        return indicador_descripcion;
    }

    public void setIndicador_descripcion(String indicador_descripcion) {
        this.indicador_descripcion = indicador_descripcion;
    }

    public String getIndicador_ejecutado() {
        return indicador_ejecutado;
    }

    public void setIndicador_ejecutado(String indicador_ejecutado) {
        this.indicador_ejecutado = indicador_ejecutado;
    }

    public String getIndicador_planificado() {
        return indicador_planificado;
    }

    public void setIndicador_planificado(String indicador_planificado) {
        this.indicador_planificado = indicador_planificado;
    }

    public String getIndicador_tipo() {
        return indicador_tipo;
    }

    public void setIndicador_tipo(String indicador_tipo) {
        this.indicador_tipo = indicador_tipo;
    }

    public Double getIndicador_numero() {
        return indicador_numero;
    }

    public void setIndicador_numero(Double indicador_numero) {
        this.indicador_numero = indicador_numero;
    }

    public Integer getMe_cuatrimestre() {
        return me_cuatrimestre;
    }

    public void setMe_cuatrimestre(Integer me_cuatrimestre) {
        this.me_cuatrimestre = me_cuatrimestre;
    }

    public Double getMe_porcentaje() {
        return me_porcentaje;
    }

    public void setMe_porcentaje(Double me_porcentaje) {
        this.me_porcentaje = me_porcentaje;
    }

    public List<cComponenteMeta> getMes() {
        return mes;
    }

    public void setMes(List<cComponenteMeta> mes) {
        this.mes = mes;
    }

    public List<cActividadRequerimiento> getActividad() {
        return actividad;
    }

    public void setActividad(List<cActividadRequerimiento> actividad) {
        this.actividad = actividad;
    }
}
