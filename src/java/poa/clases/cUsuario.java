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
public class cUsuario {

    private String login_cedula;
    private String login_nombre;
    private Integer tu_id;
    private String tu_nombre;
    private cAreaGestion ag;
    private List<cUsuario> areas;
    private Integer anio;
    private String ag_nombres;
    private String ag_nombre;
    private String usuario_titulo;
    private Integer usuario_estado;
    private Integer per_id;
    private String per_nombres;
    private String per_apellidos;
    private String per_correo;
    private String per_cedula;
    private String per_completos;
    private String sexo;

    public String getLogin_cedula() {
        return login_cedula;
    }

    public void setLogin_cedula(String login_cedula) {
        this.login_cedula = login_cedula;
    }

    public String getLogin_nombre() {
        return login_nombre;
    }

    public void setLogin_nombre(String login_nombre) {
        this.login_nombre = login_nombre;
    }

    public Integer getTu_id() {
        return tu_id;
    }

    public void setTu_id(Integer tu_id) {
        this.tu_id = tu_id;
    }

    public String getTu_nombre() {
        return tu_nombre;
    }

    public void setTu_nombre(String tu_nombre) {
        this.tu_nombre = tu_nombre;
    }

    public cAreaGestion getAg() {
        return ag;
    }

    public void setAg(cAreaGestion ag) {
        this.ag = ag;
    }

    public List<cUsuario> getAreas() {
        return areas;
    }

    public void setAreas(List<cUsuario> areas) {
        this.areas = areas;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getAg_nombres() {
        return ag_nombres;
    }

    public void setAg_nombres(String ag_nombres) {
        this.ag_nombres = ag_nombres;
    }

    public String getAg_nombre() {
        return ag_nombre;
    }

    public void setAg_nombre(String ag_nombre) {
        this.ag_nombre = ag_nombre;
    }

    public String getUsuario_titulo() {
        return usuario_titulo;
    }

    public void setUsuario_titulo(String usuario_titulo) {
        this.usuario_titulo = usuario_titulo;
    }

    public Integer getUsuario_estado() {
        return usuario_estado;
    }

    public void setUsuario_estado(Integer usuario_estado) {
        this.usuario_estado = usuario_estado;
    }

    public Integer getPer_id() {
        return per_id;
    }

    public void setPer_id(Integer per_id) {
        this.per_id = per_id;
    }

    public String getPer_nombres() {
        return per_nombres;
    }

    public void setPer_nombres(String per_nombres) {
        this.per_nombres = per_nombres;
    }

    public String getPer_apellidos() {
        return per_apellidos;
    }

    public void setPer_apellidos(String per_apellidos) {
        this.per_apellidos = per_apellidos;
    }

    public String getPer_correo() {
        return per_correo;
    }

    public void setPer_correo(String per_correo) {
        this.per_correo = per_correo;
    }

    public String getPer_cedula() {
        return per_cedula;
    }

    public void setPer_cedula(String per_cedula) {
        this.per_cedula = per_cedula;
    }

    public String getPer_completos() {
        return per_completos;
    }

    public void setPer_completos(String per_completos) {
        this.per_completos = per_completos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
