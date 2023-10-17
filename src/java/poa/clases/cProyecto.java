/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.clases;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Erika Arévalo
 */
public class cProyecto {

    private Integer ti_fecha;
    private Integer tp_id;
    private String tp_nombre;
    private Integer proyecto_id;
    private String proyecto_nombre;
    private Integer proyecto_ap;
    private String proyecto_fin;
    private String proyecto_proposito;
    private String proyecto_responsable;
    private String proyecto_integrantes;
    private String proyecto_doc;
    private Boolean proyecto_multi;
    private String proyecto_fi;
    private String proyecto_ff;
    private String proyecto_codigo;
    private Integer proyecto_servicio;
    private Integer proyecto_ag;
    private Double proyecto_monto;
    private List<cProcesoAcciones> proceso;
    private List<cAreaGestion> areas;
    private List<cProyecto> estado;
    private List<cProyecto> integrantes;
    private cProcesoAcciones proac;
    private Integer estado_id;
    private String estado_nombre;
    private cAreaGestion ag;
    private cPerspectivaObjetivo per;
    private String estado_observacion;
    private String usuario_nombre;
    private Date estado_fecha;
    private List<cProyecto> area_gestion;
    private List<cProyecto> monto_proy;
    private String proyecto_acciones;
    private String proyecto_proceso;
    private List<cActividadRequerimiento> requerimientos;
    private List<cActividadRequerimiento> cuatri;
    private Double mp_monto;
    private Integer mp_anio;
    private Integer proyecto_plurianual;
    private Integer deudas_id;
    private String deudas_proceso;
    private Integer deudas_tcon;
    private Integer deudas_financiamiento;
    private String financiamiento_nombre;
    private String tcon_nombre;
    private Double deuda_monto_contrato;
    private Double deuda_monto_iva;
    private Integer deudas_oei;
    private String deudas_contrato;
    private String deudas_presupuesto;
    private Double deudas_anticipo;
    private Integer pp_id;
    private Double pe_eficacia;
    private Double pe_eficiencia;
    private Double pe_efectividad;
    private Double pe_ejecucion;
    private Integer proyecto_planificados;
    private Integer proy_cuatrimestre;
    private Integer proy_enviados;
    private Integer proy_evaluados;
    private String proyecto_fi_rep;
    private String proyecto_ff_rep;
    private Integer proyecto_anio;
    private Double deudas_monto_pendiente;
    private Timestamp tiempo_fecha;
    private Integer deudas_reforma;
    private String proyecto_responsable_ced;

    public String getProyecto_proceso() {
        return proyecto_proceso;
    }

    public void setProyecto_proceso(String proyecto_proceso) {
        this.proyecto_proceso = proyecto_proceso;
    }
    
    public String getProyecto_acciones() {
        return proyecto_acciones;
    }

    public void setProyecto_acciones(String proyecto_acciones) {
        this.proyecto_acciones = proyecto_acciones;
    }

    public Integer getTi_fecha() {
        return ti_fecha;
    }

    public void setTi_fecha(Integer ti_fecha) {
        this.ti_fecha = ti_fecha;
    }

    public Integer getTp_id() {
        return tp_id;
    }

    public void setTp_id(Integer tp_id) {
        this.tp_id = tp_id;
    }

    public String getTp_nombre() {
        return tp_nombre;
    }

    public void setTp_nombre(String tp_nombre) {
        this.tp_nombre = tp_nombre;
    }

    public Integer getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(Integer proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public String getProyecto_nombre() {
        return proyecto_nombre;
    }

    public void setProyecto_nombre(String proyecto_nombre) {
        this.proyecto_nombre = proyecto_nombre;
    }

    public Integer getProyecto_ap() {
        return proyecto_ap;
    }

    public void setProyecto_ap(Integer proyecto_ap) {
        this.proyecto_ap = proyecto_ap;
    }

    public String getProyecto_fin() {
        return proyecto_fin;
    }

    public void setProyecto_fin(String proyecto_fin) {
        this.proyecto_fin = proyecto_fin;
    }

    public String getProyecto_proposito() {
        return proyecto_proposito;
    }

    public void setProyecto_proposito(String proyecto_proposito) {
        this.proyecto_proposito = proyecto_proposito;
    }

    public String getProyecto_responsable() {
        return proyecto_responsable;
    }

    public void setProyecto_responsable(String proyecto_responsable) {
        this.proyecto_responsable = proyecto_responsable;
    }

    public String getProyecto_integrantes() {
        return proyecto_integrantes;
    }

    public void setProyecto_integrantes(String proyecto_integrantes) {
        this.proyecto_integrantes = proyecto_integrantes;
    }

    public String getProyecto_doc() {
        return proyecto_doc;
    }

    public void setProyecto_doc(String proyecto_doc) {
        this.proyecto_doc = proyecto_doc;
    }

    public Boolean getProyecto_multi() {
        return proyecto_multi;
    }

    public void setProyecto_multi(Boolean proyecto_multi) {
        this.proyecto_multi = proyecto_multi;
    }

    public String getProyecto_fi() {
        return proyecto_fi;
    }

    public void setProyecto_fi(String proyecto_fi) {
        this.proyecto_fi = proyecto_fi;
    }

    public String getProyecto_ff() {
        return proyecto_ff;
    }

    public void setProyecto_ff(String proyecto_ff) {
        this.proyecto_ff = proyecto_ff;
    }

    public String getProyecto_codigo() {
        return proyecto_codigo;
    }

    public void setProyecto_codigo(String proyecto_codigo) {
        this.proyecto_codigo = proyecto_codigo;
    }

    public Integer getProyecto_servicio() {
        return proyecto_servicio;
    }

    public void setProyecto_servicio(Integer proyecto_servicio) {
        this.proyecto_servicio = proyecto_servicio;
    }

    public Integer getProyecto_ag() {
        return proyecto_ag;
    }

    public void setProyecto_ag(Integer proyecto_ag) {
        this.proyecto_ag = proyecto_ag;
    }

    public Double getProyecto_monto() {
        return proyecto_monto;
    }

    public void setProyecto_monto(Double proyecto_monto) {
        this.proyecto_monto = proyecto_monto;
    }

    public List<cProcesoAcciones> getProceso() {
        return proceso;
    }

    public void setProceso(List<cProcesoAcciones> proceso) {
        this.proceso = proceso;
    }

    public cProcesoAcciones getProac() {
        return proac;
    }

    public void setProac(cProcesoAcciones proac) {
        this.proac = proac;
    }

    public String getEstado_nombre() {
        return estado_nombre;
    }

    public void setEstado_nombre(String estado_nombre) {
        this.estado_nombre = estado_nombre;
    }

    public cAreaGestion getAg() {
        return ag;
    }

    public void setAg(cAreaGestion ag) {
        this.ag = ag;
    }

    public cPerspectivaObjetivo getPer() {
        return per;
    }

    public void setPer(cPerspectivaObjetivo per) {
        this.per = per;
    }

    public Integer getEstado_id() {
        return estado_id;
    }

    public void setEstado_id(Integer estado_id) {
        this.estado_id = estado_id;
    }

    public List<cAreaGestion> getAreas() {
        return areas;
    }

    public void setAreas(List<cAreaGestion> areas) {
        this.areas = areas;
    }

    public List<cProyecto> getEstado() {
        return estado;
    }

    public void setEstado(List<cProyecto> estado) {
        this.estado = estado;
    }

    public String getEstado_observacion() {
        return estado_observacion;
    }

    public void setEstado_observacion(String estado_observacion) {
        this.estado_observacion = estado_observacion;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public Date getEstado_fecha() {
        return estado_fecha;
    }

    public void setEstado_fecha(Date estado_fecha) {
        this.estado_fecha = estado_fecha;
    }

    public List<cProyecto> getArea_gestion() {
        return area_gestion;
    }

    public void setArea_gestion(List<cProyecto> area_gestion) {
        this.area_gestion = area_gestion;
    }

    public List<cActividadRequerimiento> getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(List<cActividadRequerimiento> requerimientos) {
        this.requerimientos = requerimientos;
    }

    public List<cProyecto> getMonto_proy() {
        return monto_proy;
    }

    public void setMonto_proy(List<cProyecto> monto_proy) {
        this.monto_proy = monto_proy;
    }

    public Double getMp_monto() {
        return mp_monto;
    }

    public void setMp_monto(Double mp_monto) {
        this.mp_monto = mp_monto;
    }

    public Integer getMp_anio() {
        return mp_anio;
    }

    public void setMp_anio(Integer mp_anio) {
        this.mp_anio = mp_anio;
    }

    public Integer getProyecto_plurianual() {
        return proyecto_plurianual;
    }

    public void setProyecto_plurianual(Integer proyecto_plurianual) {
        this.proyecto_plurianual = proyecto_plurianual;
    }

    public List<cActividadRequerimiento> getCuatri() {
        return cuatri;
    }

    public void setCuatri(List<cActividadRequerimiento> cuatri) {
        this.cuatri = cuatri;
    }

    public Integer getDeudas_id() {
        return deudas_id;
    }

    public void setDeudas_id(Integer deudas_id) {
        this.deudas_id = deudas_id;
    }

    public String getDeudas_proceso() {
        return deudas_proceso;
    }

    public void setDeudas_proceso(String deudas_proceso) {
        this.deudas_proceso = deudas_proceso;
    }

    public Integer getDeudas_tcon() {
        return deudas_tcon;
    }

    public void setDeudas_tcon(Integer deudas_tcon) {
        this.deudas_tcon = deudas_tcon;
    }

    public Integer getDeudas_financiamiento() {
        return deudas_financiamiento;
    }

    public void setDeudas_financiamiento(Integer deudas_financiamiento) {
        this.deudas_financiamiento = deudas_financiamiento;
    }

    public String getFinanciamiento_nombre() {
        return financiamiento_nombre;
    }

    public void setFinanciamiento_nombre(String financiamiento_nombre) {
        this.financiamiento_nombre = financiamiento_nombre;
    }

    public String getTcon_nombre() {
        return tcon_nombre;
    }

    public void setTcon_nombre(String tcon_nombre) {
        this.tcon_nombre = tcon_nombre;
    }

    public Double getDeuda_monto_contrato() {
        return deuda_monto_contrato;
    }

    public void setDeuda_monto_contrato(Double deuda_monto_contrato) {
        this.deuda_monto_contrato = deuda_monto_contrato;
    }

    public Double getDeuda_monto_iva() {
        return deuda_monto_iva;
    }

    public void setDeuda_monto_iva(Double deuda_monto_iva) {
        this.deuda_monto_iva = deuda_monto_iva;
    }

    public Integer getDeudas_oei() {
        return deudas_oei;
    }

    public void setDeudas_oei(Integer deudas_oei) {
        this.deudas_oei = deudas_oei;
    }

    public String getDeudas_contrato() {
        return deudas_contrato;
    }

    public void setDeudas_contrato(String deudas_contrato) {
        this.deudas_contrato = deudas_contrato;
    }

    public String getDeudas_presupuesto() {
        return deudas_presupuesto;
    }

    public void setDeudas_presupuesto(String deudas_presupuesto) {
        this.deudas_presupuesto = deudas_presupuesto;
    }

    public Double getDeudas_anticipo() {
        return deudas_anticipo;
    }

    public void setDeudas_anticipo(Double deudas_anticipo) {
        this.deudas_anticipo = deudas_anticipo;
    }

    public Integer getPp_id() {
        return pp_id;
    }

    public void setPp_id(Integer pp_id) {
        this.pp_id = pp_id;
    }

    public Double getPe_eficacia() {
        return pe_eficacia;
    }

    public void setPe_eficacia(Double pe_eficacia) {
        this.pe_eficacia = pe_eficacia;
    }

    public Double getPe_eficiencia() {
        return pe_eficiencia;
    }

    public void setPe_eficiencia(Double pe_eficiencia) {
        this.pe_eficiencia = pe_eficiencia;
    }

    public Double getPe_efectividad() {
        return pe_efectividad;
    }

    public void setPe_efectividad(Double pe_efectividad) {
        this.pe_efectividad = pe_efectividad;
    }

    public Double getPe_ejecucion() {
        return pe_ejecucion;
    }

    public void setPe_ejecucion(Double pe_ejecucion) {
        this.pe_ejecucion = pe_ejecucion;
    }

    public Integer getProyecto_planificados() {
        return proyecto_planificados;
    }

    public void setProyecto_planificados(Integer proyecto_planificados) {
        this.proyecto_planificados = proyecto_planificados;
    }

    public Integer getProy_cuatrimestre() {
        return proy_cuatrimestre;
    }

    public void setProy_cuatrimestre(Integer proy_cuatrimestre) {
        this.proy_cuatrimestre = proy_cuatrimestre;
    }

    public Integer getProy_enviados() {
        return proy_enviados;
    }

    public void setProy_enviados(Integer proy_enviados) {
        this.proy_enviados = proy_enviados;
    }

    public Integer getProy_evaluados() {
        return proy_evaluados;
    }

    public void setProy_evaluados(Integer proy_evaluados) {
        this.proy_evaluados = proy_evaluados;
    }

    public String getProyecto_fi_rep() {
        return proyecto_fi_rep;
    }

    public void setProyecto_fi_rep(String proyecto_fi_rep) {
        this.proyecto_fi_rep = proyecto_fi_rep;
    }

    public String getProyecto_ff_rep() {
        return proyecto_ff_rep;
    }

    public void setProyecto_ff_rep(String proyecto_ff_rep) {
        this.proyecto_ff_rep = proyecto_ff_rep;
    }

    public Integer getProyecto_anio() {
        return proyecto_anio;
    }

    public void setProyecto_anio(Integer proyecto_anio) {
        this.proyecto_anio = proyecto_anio;
    }

    public List<cProyecto> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<cProyecto> integrantes) {
        this.integrantes = integrantes;
    }

    public Double getDeudas_monto_pendiente() {
        return deudas_monto_pendiente;
    }

    public void setDeudas_monto_pendiente(Double deudas_monto_pendiente) {
        this.deudas_monto_pendiente = deudas_monto_pendiente;
    }

    public Timestamp getTiempo_fecha() {
        return tiempo_fecha;
    }

    public void setTiempo_fecha(Timestamp tiempo_fecha) {
        this.tiempo_fecha = tiempo_fecha;
    }

    public Integer getDeudas_reforma() {
        return deudas_reforma;
    }

    public void setDeudas_reforma(Integer deudas_reforma) {
        this.deudas_reforma = deudas_reforma;
    }

    public String getProyecto_responsable_ced() {
        return proyecto_responsable_ced;
    }

    public void setProyecto_responsable_ced(String proyecto_responsable_ced) {
        this.proyecto_responsable_ced = proyecto_responsable_ced;
    }
}
