/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.clases;

/**
 *
 * @author Desarrollo
 */
public class cActividadPresupuestaria {
    private Integer intIdActividadPresupuestaria;
    private String strNombreActividadPresupuestaria;
    private cPerspectivaObjetivo PerspectivaObjetivo;
    private Integer intEstadoActividadPresupuestaria;

    public cActividadPresupuestaria() {
        this.intIdActividadPresupuestaria = 0;
        this.strNombreActividadPresupuestaria="";
        this.PerspectivaObjetivo=null;
        this.intEstadoActividadPresupuestaria=0;
    }

    public Integer getIntIdActividadPresupuestaria() {
        return intIdActividadPresupuestaria;
    }

    public void setIntIdActividadPresupuestaria(Integer intIdActividadPresupuestaria) {
        this.intIdActividadPresupuestaria = intIdActividadPresupuestaria;
    }

    public String getStrNombreActividadPresupuestaria() {
        return strNombreActividadPresupuestaria;
    }

    public void setStrNombreActividadPresupuestaria(String strNombreActividadPresupuestaria) {
        this.strNombreActividadPresupuestaria = strNombreActividadPresupuestaria;
    }

    public cPerspectivaObjetivo getPerspectivaObjetivo() {
        return PerspectivaObjetivo;
    }

    public void setPerspectivaObjetivo(cPerspectivaObjetivo PerspectivaObjetivo) {
        this.PerspectivaObjetivo = PerspectivaObjetivo;
    }

    public Integer getIntEstadoActividadPresupuestaria() {
        return intEstadoActividadPresupuestaria;
    }

    public void setIntEstadoActividadPresupuestaria(Integer intEstadoActividadPresupuestaria) {
        this.intEstadoActividadPresupuestaria = intEstadoActividadPresupuestaria;
    }            
}
