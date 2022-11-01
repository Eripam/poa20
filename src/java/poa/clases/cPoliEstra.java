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
public class cPoliEstra {

    private Integer politica_id;
    private String politica_nombre;
    private Integer estrategia_id;
    private String estrategia_nombre;
    private List<cPoliEstra> pol;
    private Integer objetivo_id;
    private String objetivo_codigo;
    private String objetivo_nombre;
    private Integer politica_estado;
    private String estado_nombre;

    public Integer getPolitica_id() {
        return politica_id;
    }

    public void setPolitica_id(Integer politica_id) {
        this.politica_id = politica_id;
    }

    public String getPolitica_nombre() {
        return politica_nombre;
    }

    public void setPolitica_nombre(String politica_nombre) {
        this.politica_nombre = politica_nombre;
    }

    public Integer getEstrategia_id() {
        return estrategia_id;
    }

    public void setEstrategia_id(Integer estrategia_id) {
        this.estrategia_id = estrategia_id;
    }

    public String getEstrategia_nombre() {
        return estrategia_nombre;
    }

    public void setEstrategia_nombre(String estrategia_nombre) {
        this.estrategia_nombre = estrategia_nombre;
    }

    public List<cPoliEstra> getPol() {
        return pol;
    }

    public void setPol(List<cPoliEstra> pol) {
        this.pol = pol;
    }

    public Integer getObjetivo_id() {
        return objetivo_id;
    }

    public void setObjetivo_id(Integer objetivo_id) {
        this.objetivo_id = objetivo_id;
    }

    public String getObjetivo_codigo() {
        return objetivo_codigo;
    }

    public void setObjetivo_codigo(String objetivo_codigo) {
        this.objetivo_codigo = objetivo_codigo;
    }

    public String getObjetivo_nombre() {
        return objetivo_nombre;
    }

    public void setObjetivo_nombre(String objetivo_nombre) {
        this.objetivo_nombre = objetivo_nombre;
    }

    public Integer getPolitica_estado() {
        return politica_estado;
    }

    public void setPolitica_estado(Integer politica_estado) {
        this.politica_estado = politica_estado;
    }

    public String getEstado_nombre() {
        return estado_nombre;
    }

    public void setEstado_nombre(String estado_nombre) {
        this.estado_nombre = estado_nombre;
    }
}
