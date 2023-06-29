/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poa.clases;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Erika Arévalo
 */
public class cActividadRequerimiento {

    private Integer actividad_id;
    private String actividad_nombre;
    private String actividad_responsable;
    private Integer actividad_tipo;
    private Double actividad_porcentaje;
    private Integer mes_id;
    private Double mes_porcentaje;
    private Integer actividad_componente;
    private Double actividad_monto;
    private Integer actividad_prioridad;
    private List<cActividadRequerimiento> cuatri;
    private List<cActividadRequerimiento> mes;
    private List<cComponenteMeta> componente;
    private Double c1;
    private Double c2;
    private Double c3;
    private Integer unidad_id;
    private String unidad_nombre;
    private Integer tc_id;
    private String tc_nombre;
    private Integer req_id;
    private String req_nombre;
    private String req_descripcion;
    private String req_cpc;
    private Integer req_iva;
    private Double req_cantidad;
    private Double req_costo_unitario;
    private Double req_costo_sin_iva;
    private Double req_costo_total;
    private Integer financiamiento_id;
    private String financiamiento_nombre;
    private Integer req_tipo;
    private Integer req_verificacion;
    private Integer req_prioridad;
    private Integer req_rg;
    private String req_rgnombre;
    private List<cActividadRequerimiento> req;
    private List<cActividadRequerimiento> deudas;
    private String proyecto_nombre;
    private String ag_nombre;
    private String pac_catalogo;
    private String pac_tipo_producto;
    private Integer pac_num_operacion;
    private Integer pac_codigo_proyecto;
    private String pac_tipo_regimen;
    private String pac_procedimiento_sug;
    private String pac_fondo_bid;
    private Integer presupuesto_id;
    private String presupuesto_unidad_desc;
    private String presupuesto_programa;
    private String presupuesto_subprograma;
    private String presupuesto_proyecto;
    private String presupuesto_actividad;
    private String presupuesto_obra;
    private String presupuesto_geografico;
    private String presupuesto_renglo_aux;
    private String presupuesto_fuente;
    private String presupuesto_organismo;
    private String presupuesto_correlativo;
    private String presupuesto_presupuesto;
    private Integer presupuesto_ejercicio;
    private Integer presupuesto_entidad;
    private Integer presupuesto_unidad_ejec;
    private Integer presupuesto_renglo;
    private Integer perspectiva_id;
    private Date req_prioridad_fecha;
    private Integer req_reforma;
    private Integer req_req_id;
    private String ag_alias;
    private Integer req_anio;
    private Integer req_estado;
    private Integer deu_iva;
    private String estado_nombre;
    private Integer solicitud_estado;
    private String solestado_observacion;
    private Date solestado_fecha;
    private String usuario_nombre;
    private Integer ag_id;
    private String solicitud_cedula;
    private String solicitud_nombre;
    private String solicitud_cargo;
    private String solicitud_centro_costo;
    private Integer solicitud_autoridades;
    private Integer solicitud_id;
    private String solicitud_codigo;
    private String autoridades_nombre;
    private String autoridades_cargo;
    private String usuario_cedula;
    private Integer autoridades_id;
    private String usuario_titulo;
    private String autoridades_cedula;
    private Integer solestado_numero;
    private Integer proyecto_id;
    private Boolean req_disponible;
    private Double verificado_iva;
    private Double verificado_uni_iva;
    private String estado_observacion;
    private Boolean req_salvaguardar;
    private Double reqestado_cantidad;
    private Double reqestado_costo_unitario;
    private Double reqestado_costo_total;
    private Integer reqestado_iva;
    private String reqeje_nombre;
    private Double reqeje_cantidad_anual;
    private Double reqeje_costo_unitario;
    private Double reqeje_costo_sin_iva;
    private Integer reqeje_oei;
    private String reqeje_descripcion;
    private Integer reqeje_unidad;
    private Integer reqeje_iva;
    private Integer req_reforma2;
    private Double req_incremento;
    private Double req_total;
    private Integer presupuesto_reforma;
    private Integer n_horas;
    private Double sueldo_total;
    private String fecha_inicio;
    private String fecha_fin;
    private Double cantidad;
    private Double verificado_uni_npac;
    private Integer are_id;
    private String are_descripcion;
    private String are_archivo;
    private Double ae_porcentaje;
    private Integer ae_tiempo;
    private Double ae_ejecucion;
    private List<cActividadRequerimiento> actividad_eval;
    private List<cActividadRequerimiento> fechanopac;
    private Double ae_autoeval;
    private String ae_observacion;
    private Double ae_eval;
    private Double ae_evaluacion;
    private Integer me_cuatrimestre;
    private Double cp_valor;
    private String cp_tipo;
    private Integer ap_id;
    private String objetivo_nombre;
    private Double pe_eficacia;
    private Double pe_eficiencia;
    private Double pe_efectividad;
    private Double pe_ejecucion;
    private String logro_nombre;
    private String nudo_nombre;
    private String req_iva2;
    private Integer pc_id;
    private String pc_nombre;

    public Integer getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(Integer actividad_id) {
        this.actividad_id = actividad_id;
    }

    public String getActividad_nombre() {
        return actividad_nombre;
    }

    public void setActividad_nombre(String actividad_nombre) {
        this.actividad_nombre = actividad_nombre;
    }

    public String getActividad_responsable() {
        return actividad_responsable;
    }

    public void setActividad_responsable(String actividad_responsable) {
        this.actividad_responsable = actividad_responsable;
    }

    public Integer getActividad_tipo() {
        return actividad_tipo;
    }

    public void setActividad_tipo(Integer actividad_tipo) {
        this.actividad_tipo = actividad_tipo;
    }

    public Double getActividad_porcentaje() {
        return actividad_porcentaje;
    }

    public void setActividad_porcentaje(Double actividad_porcentaje) {
        this.actividad_porcentaje = actividad_porcentaje;
    }

    public Integer getMes_id() {
        return mes_id;
    }

    public void setMes_id(Integer mes_id) {
        this.mes_id = mes_id;
    }

    public Double getMes_porcentaje() {
        return mes_porcentaje;
    }

    public void setMes_porcentaje(Double mes_porcentaje) {
        this.mes_porcentaje = mes_porcentaje;
    }

    public Integer getActividad_componente() {
        return actividad_componente;
    }

    public void setActividad_componente(Integer actividad_componente) {
        this.actividad_componente = actividad_componente;
    }

    public Double getActividad_monto() {
        return actividad_monto;
    }

    public void setActividad_monto(Double actividad_monto) {
        this.actividad_monto = actividad_monto;
    }

    public Integer getActividad_prioridad() {
        return actividad_prioridad;
    }

    public void setActividad_prioridad(Integer actividad_prioridad) {
        this.actividad_prioridad = actividad_prioridad;
    }

    public List<cActividadRequerimiento> getCuatri() {
        return cuatri;
    }

    public void setCuatri(List<cActividadRequerimiento> cuatri) {
        this.cuatri = cuatri;
    }

    public List<cActividadRequerimiento> getMes() {
        return mes;
    }

    public void setMes(List<cActividadRequerimiento> mes) {
        this.mes = mes;
    }

    public Double getC1() {
        return c1;
    }

    public void setC1(Double c1) {
        this.c1 = c1;
    }

    public Double getC2() {
        return c2;
    }

    public void setC2(Double c2) {
        this.c2 = c2;
    }

    public Double getC3() {
        return c3;
    }

    public void setC3(Double c3) {
        this.c3 = c3;
    }

    public Integer getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(Integer unidad_id) {
        this.unidad_id = unidad_id;
    }

    public String getUnidad_nombre() {
        return unidad_nombre;
    }

    public void setUnidad_nombre(String unidad_nombre) {
        this.unidad_nombre = unidad_nombre;
    }

    public Integer getTc_id() {
        return tc_id;
    }

    public void setTc_id(Integer tc_id) {
        this.tc_id = tc_id;
    }

    public String getTc_nombre() {
        return tc_nombre;
    }

    public void setTc_nombre(String tc_nombre) {
        this.tc_nombre = tc_nombre;
    }

    public Integer getReq_id() {
        return req_id;
    }

    public void setReq_id(Integer req_id) {
        this.req_id = req_id;
    }

    public String getReq_nombre() {
        return req_nombre;
    }

    public void setReq_nombre(String req_nombre) {
        this.req_nombre = req_nombre;
    }

    public String getReq_descripcion() {
        return req_descripcion;
    }

    public void setReq_descripcion(String req_descripcion) {
        this.req_descripcion = req_descripcion;
    }

    public String getReq_cpc() {
        return req_cpc;
    }

    public void setReq_cpc(String req_cpc) {
        this.req_cpc = req_cpc;
    }

    public Integer getReq_iva() {
        return req_iva;
    }

    public void setReq_iva(Integer req_iva) {
        this.req_iva = req_iva;
    }

    public Double getReq_cantidad() {
        return req_cantidad;
    }

    public void setReq_cantidad(Double req_cantidad) {
        this.req_cantidad = req_cantidad;
    }

    public Double getReq_costo_unitario() {
        return req_costo_unitario;
    }

    public void setReq_costo_unitario(Double req_costo_unitario) {
        this.req_costo_unitario = req_costo_unitario;
    }

    public Double getReq_costo_sin_iva() {
        return req_costo_sin_iva;
    }

    public void setReq_costo_sin_iva(Double req_costo_sin_iva) {
        this.req_costo_sin_iva = req_costo_sin_iva;
    }

    public Double getReq_costo_total() {
        return req_costo_total;
    }

    public void setReq_costo_total(Double req_costo_total) {
        this.req_costo_total = req_costo_total;
    }

    public Integer getFinanciamiento_id() {
        return financiamiento_id;
    }

    public void setFinanciamiento_id(Integer financiamiento_id) {
        this.financiamiento_id = financiamiento_id;
    }

    public String getFinanciamiento_nombre() {
        return financiamiento_nombre;
    }

    public void setFinanciamiento_nombre(String financiamiento_nombre) {
        this.financiamiento_nombre = financiamiento_nombre;
    }

    public Integer getReq_tipo() {
        return req_tipo;
    }

    public void setReq_tipo(Integer req_tipo) {
        this.req_tipo = req_tipo;
    }

    public Integer getReq_verificacion() {
        return req_verificacion;
    }

    public void setReq_verificacion(Integer req_verificacion) {
        this.req_verificacion = req_verificacion;
    }

    public Integer getReq_prioridad() {
        return req_prioridad;
    }

    public void setReq_prioridad(Integer req_prioridad) {
        this.req_prioridad = req_prioridad;
    }

    public Integer getReq_rg() {
        return req_rg;
    }

    public void setReq_rg(Integer req_rg) {
        this.req_rg = req_rg;
    }

    public List<cActividadRequerimiento> getReq() {
        return req;
    }

    public void setReq(List<cActividadRequerimiento> req) {
        this.req = req;
    }

    public String getReq_rgnombre() {
        return req_rgnombre;
    }

    public void setReq_rgnombre(String req_rgnombre) {
        this.req_rgnombre = req_rgnombre;
    }

    public String getProyecto_nombre() {
        return proyecto_nombre;
    }

    public void setProyecto_nombre(String proyecto_nombre) {
        this.proyecto_nombre = proyecto_nombre;
    }

    public String getAg_nombre() {
        return ag_nombre;
    }

    public void setAg_nombre(String ag_nombre) {
        this.ag_nombre = ag_nombre;
    }

    public String getPac_catalogo() {
        return pac_catalogo;
    }

    public void setPac_catalogo(String pac_catalogo) {
        this.pac_catalogo = pac_catalogo;
    }

    public String getPac_tipo_producto() {
        return pac_tipo_producto;
    }

    public void setPac_tipo_producto(String pac_tipo_producto) {
        this.pac_tipo_producto = pac_tipo_producto;
    }

    public Integer getPac_num_operacion() {
        return pac_num_operacion;
    }

    public void setPac_num_operacion(Integer pac_num_operacion) {
        this.pac_num_operacion = pac_num_operacion;
    }

    public Integer getPac_codigo_proyecto() {
        return pac_codigo_proyecto;
    }

    public void setPac_codigo_proyecto(Integer pac_codigo_proyecto) {
        this.pac_codigo_proyecto = pac_codigo_proyecto;
    }

    public String getPac_tipo_regimen() {
        return pac_tipo_regimen;
    }

    public void setPac_tipo_regimen(String pac_tipo_regimen) {
        this.pac_tipo_regimen = pac_tipo_regimen;
    }

    public String getPac_procedimiento_sug() {
        return pac_procedimiento_sug;
    }

    public void setPac_procedimiento_sug(String pac_procedimiento_sug) {
        this.pac_procedimiento_sug = pac_procedimiento_sug;
    }

    public String getPac_fondo_bid() {
        return pac_fondo_bid;
    }

    public void setPac_fondo_bid(String pac_fondo_bid) {
        this.pac_fondo_bid = pac_fondo_bid;
    }

    public String getPresupuesto_unidad_desc() {
        return presupuesto_unidad_desc;
    }

    public void setPresupuesto_unidad_desc(String presupuesto_unidad_desc) {
        this.presupuesto_unidad_desc = presupuesto_unidad_desc;
    }

    public String getPresupuesto_programa() {
        return presupuesto_programa;
    }

    public void setPresupuesto_programa(String presupuesto_programa) {
        this.presupuesto_programa = presupuesto_programa;
    }

    public String getPresupuesto_subprograma() {
        return presupuesto_subprograma;
    }

    public void setPresupuesto_subprograma(String presupuesto_subprograma) {
        this.presupuesto_subprograma = presupuesto_subprograma;
    }

    public String getPresupuesto_proyecto() {
        return presupuesto_proyecto;
    }

    public void setPresupuesto_proyecto(String presupuesto_proyecto) {
        this.presupuesto_proyecto = presupuesto_proyecto;
    }

    public String getPresupuesto_actividad() {
        return presupuesto_actividad;
    }

    public void setPresupuesto_actividad(String presupuesto_actividad) {
        this.presupuesto_actividad = presupuesto_actividad;
    }

    public String getPresupuesto_obra() {
        return presupuesto_obra;
    }

    public void setPresupuesto_obra(String presupuesto_obra) {
        this.presupuesto_obra = presupuesto_obra;
    }

    public String getPresupuesto_geografico() {
        return presupuesto_geografico;
    }

    public void setPresupuesto_geografico(String presupuesto_geografico) {
        this.presupuesto_geografico = presupuesto_geografico;
    }

    public String getPresupuesto_renglo_aux() {
        return presupuesto_renglo_aux;
    }

    public void setPresupuesto_renglo_aux(String presupuesto_renglo_aux) {
        this.presupuesto_renglo_aux = presupuesto_renglo_aux;
    }

    public String getPresupuesto_fuente() {
        return presupuesto_fuente;
    }

    public void setPresupuesto_fuente(String presupuesto_fuente) {
        this.presupuesto_fuente = presupuesto_fuente;
    }

    public String getPresupuesto_organismo() {
        return presupuesto_organismo;
    }

    public void setPresupuesto_organismo(String presupuesto_organismo) {
        this.presupuesto_organismo = presupuesto_organismo;
    }

    public String getPresupuesto_correlativo() {
        return presupuesto_correlativo;
    }

    public void setPresupuesto_correlativo(String presupuesto_correlativo) {
        this.presupuesto_correlativo = presupuesto_correlativo;
    }

    public String getPresupuesto_presupuesto() {
        return presupuesto_presupuesto;
    }

    public void setPresupuesto_presupuesto(String presupuesto_presupuesto) {
        this.presupuesto_presupuesto = presupuesto_presupuesto;
    }

    public Integer getPresupuesto_ejercicio() {
        return presupuesto_ejercicio;
    }

    public void setPresupuesto_ejercicio(Integer presupuesto_ejercicio) {
        this.presupuesto_ejercicio = presupuesto_ejercicio;
    }

    public Integer getPresupuesto_entidad() {
        return presupuesto_entidad;
    }

    public void setPresupuesto_entidad(Integer presupuesto_entidad) {
        this.presupuesto_entidad = presupuesto_entidad;
    }

    public Integer getPresupuesto_unidad_ejec() {
        return presupuesto_unidad_ejec;
    }

    public void setPresupuesto_unidad_ejec(Integer presupuesto_unidad_ejec) {
        this.presupuesto_unidad_ejec = presupuesto_unidad_ejec;
    }

    public Integer getPresupuesto_renglo() {
        return presupuesto_renglo;
    }

    public void setPresupuesto_renglo(Integer presupuesto_renglo) {
        this.presupuesto_renglo = presupuesto_renglo;
    }

    public Integer getPerspectiva_id() {
        return perspectiva_id;
    }

    public void setPerspectiva_id(Integer perspectiva_id) {
        this.perspectiva_id = perspectiva_id;
    }

    public Date getReq_prioridad_fecha() {
        return req_prioridad_fecha;
    }

    public void setReq_prioridad_fecha(Date req_prioridad_fecha) {
        this.req_prioridad_fecha = req_prioridad_fecha;
    }

    public Integer getReq_reforma() {
        return req_reforma;
    }

    public void setReq_reforma(Integer req_reforma) {
        this.req_reforma = req_reforma;
    }

    public Integer getReq_req_id() {
        return req_req_id;
    }

    public void setReq_req_id(Integer req_req_id) {
        this.req_req_id = req_req_id;
    }

    public String getAg_alias() {
        return ag_alias;
    }

    public void setAg_alias(String ag_alias) {
        this.ag_alias = ag_alias;
    }

    public Integer getReq_anio() {
        return req_anio;
    }

    public void setReq_anio(Integer req_anio) {
        this.req_anio = req_anio;
    }

    public Integer getReq_estado() {
        return req_estado;
    }

    public void setReq_estado(Integer req_estado) {
        this.req_estado = req_estado;
    }

    public Integer getDeu_iva() {
        return deu_iva;
    }

    public void setDeu_iva(Integer deu_iva) {
        this.deu_iva = deu_iva;
    }

    public List<cActividadRequerimiento> getDeudas() {
        return deudas;
    }

    public void setDeudas(List<cActividadRequerimiento> deudas) {
        this.deudas = deudas;
    }

    public String getEstado_nombre() {
        return estado_nombre;
    }

    public void setEstado_nombre(String estado_nombre) {
        this.estado_nombre = estado_nombre;
    }

    public Integer getSolicitud_estado() {
        return solicitud_estado;
    }

    public void setSolicitud_estado(Integer solicitud_estado) {
        this.solicitud_estado = solicitud_estado;
    }

    public String getSolestado_observacion() {
        return solestado_observacion;
    }

    public void setSolestado_observacion(String solestado_observacion) {
        this.solestado_observacion = solestado_observacion;
    }

    public Date getSolestado_fecha() {
        return solestado_fecha;
    }

    public void setSolestado_fecha(Date solestado_fecha) {
        this.solestado_fecha = solestado_fecha;
    }

    public String getUsuario_nombre() {
        return usuario_nombre;
    }

    public void setUsuario_nombre(String usuario_nombre) {
        this.usuario_nombre = usuario_nombre;
    }

    public Integer getAg_id() {
        return ag_id;
    }

    public void setAg_id(Integer ag_id) {
        this.ag_id = ag_id;
    }

    public String getSolicitud_cedula() {
        return solicitud_cedula;
    }

    public void setSolicitud_cedula(String solicitud_cedula) {
        this.solicitud_cedula = solicitud_cedula;
    }

    public String getSolicitud_nombre() {
        return solicitud_nombre;
    }

    public void setSolicitud_nombre(String solicitud_nombre) {
        this.solicitud_nombre = solicitud_nombre;
    }

    public String getSolicitud_cargo() {
        return solicitud_cargo;
    }

    public void setSolicitud_cargo(String solicitud_cargo) {
        this.solicitud_cargo = solicitud_cargo;
    }

    public String getSolicitud_centro_costo() {
        return solicitud_centro_costo;
    }

    public void setSolicitud_centro_costo(String solicitud_centro_costo) {
        this.solicitud_centro_costo = solicitud_centro_costo;
    }

    public Integer getSolicitud_autoridades() {
        return solicitud_autoridades;
    }

    public void setSolicitud_autoridades(Integer solicitud_autoridades) {
        this.solicitud_autoridades = solicitud_autoridades;
    }

    public Integer getSolicitud_id() {
        return solicitud_id;
    }

    public void setSolicitud_id(Integer solicitud_id) {
        this.solicitud_id = solicitud_id;
    }

    public String getSolicitud_codigo() {
        return solicitud_codigo;
    }

    public void setSolicitud_codigo(String solicitud_codigo) {
        this.solicitud_codigo = solicitud_codigo;
    }

    public String getAutoridades_nombre() {
        return autoridades_nombre;
    }

    public void setAutoridades_nombre(String autoridades_nombre) {
        this.autoridades_nombre = autoridades_nombre;
    }

    public String getAutoridades_cargo() {
        return autoridades_cargo;
    }

    public void setAutoridades_cargo(String autoridades_cargo) {
        this.autoridades_cargo = autoridades_cargo;
    }

    public String getUsuario_cedula() {
        return usuario_cedula;
    }

    public void setUsuario_cedula(String usuario_cedula) {
        this.usuario_cedula = usuario_cedula;
    }

    public Integer getAutoridades_id() {
        return autoridades_id;
    }

    public void setAutoridades_id(Integer autoridades_id) {
        this.autoridades_id = autoridades_id;
    }

    public String getUsuario_titulo() {
        return usuario_titulo;
    }

    public void setUsuario_titulo(String usuario_titulo) {
        this.usuario_titulo = usuario_titulo;
    }

    public String getAutoridades_cedula() {
        return autoridades_cedula;
    }

    public void setAutoridades_cedula(String autoridades_cedula) {
        this.autoridades_cedula = autoridades_cedula;
    }

    public Integer getSolestado_numero() {
        return solestado_numero;
    }

    public void setSolestado_numero(Integer solestado_numero) {
        this.solestado_numero = solestado_numero;
    }

    public Integer getProyecto_id() {
        return proyecto_id;
    }

    public void setProyecto_id(Integer proyecto_id) {
        this.proyecto_id = proyecto_id;
    }

    public Boolean getReq_disponible() {
        return req_disponible;
    }

    public void setReq_disponible(Boolean req_disponible) {
        this.req_disponible = req_disponible;
    }

    public Double getVerificado_iva() {
        return verificado_iva;
    }

    public void setVerificado_iva(Double verificado_iva) {
        this.verificado_iva = verificado_iva;
    }

    public Double getVerificado_uni_iva() {
        return verificado_uni_iva;
    }

    public void setVerificado_uni_iva(Double verificado_uni_iva) {
        this.verificado_uni_iva = verificado_uni_iva;
    }

    public String getEstado_observacion() {
        return estado_observacion;
    }

    public void setEstado_observacion(String estado_observacion) {
        this.estado_observacion = estado_observacion;
    }

    public Boolean getReq_salvaguardar() {
        return req_salvaguardar;
    }

    public void setReq_salvaguardar(Boolean req_salvaguardar) {
        this.req_salvaguardar = req_salvaguardar;
    }

    public Double getReqestado_cantidad() {
        return reqestado_cantidad;
    }

    public void setReqestado_cantidad(Double reqestado_cantidad) {
        this.reqestado_cantidad = reqestado_cantidad;
    }

    public Double getReqestado_costo_unitario() {
        return reqestado_costo_unitario;
    }

    public void setReqestado_costo_unitario(Double reqestado_costo_unitario) {
        this.reqestado_costo_unitario = reqestado_costo_unitario;
    }

    public Double getReqestado_costo_total() {
        return reqestado_costo_total;
    }

    public void setReqestado_costo_total(Double reqestado_costo_total) {
        this.reqestado_costo_total = reqestado_costo_total;
    }

    public Integer getReqestado_iva() {
        return reqestado_iva;
    }

    public void setReqestado_iva(Integer reqestado_iva) {
        this.reqestado_iva = reqestado_iva;
    }

    public String getReqeje_nombre() {
        return reqeje_nombre;
    }

    public void setReqeje_nombre(String reqeje_nombre) {
        this.reqeje_nombre = reqeje_nombre;
    }

    public Double getReqeje_cantidad_anual() {
        return reqeje_cantidad_anual;
    }

    public void setReqeje_cantidad_anual(Double reqeje_cantidad_anual) {
        this.reqeje_cantidad_anual = reqeje_cantidad_anual;
    }

    public Double getReqeje_costo_unitario() {
        return reqeje_costo_unitario;
    }

    public void setReqeje_costo_unitario(Double reqeje_costo_unitario) {
        this.reqeje_costo_unitario = reqeje_costo_unitario;
    }

    public Double getReqeje_costo_sin_iva() {
        return reqeje_costo_sin_iva;
    }

    public void setReqeje_costo_sin_iva(Double reqeje_costo_sin_iva) {
        this.reqeje_costo_sin_iva = reqeje_costo_sin_iva;
    }

    public Integer getReqeje_oei() {
        return reqeje_oei;
    }

    public void setReqeje_oei(Integer reqeje_oei) {
        this.reqeje_oei = reqeje_oei;
    }

    public String getReqeje_descripcion() {
        return reqeje_descripcion;
    }

    public void setReqeje_descripcion(String reqeje_descripcion) {
        this.reqeje_descripcion = reqeje_descripcion;
    }

    public Integer getReqeje_unidad() {
        return reqeje_unidad;
    }

    public void setReqeje_unidad(Integer reqeje_unidad) {
        this.reqeje_unidad = reqeje_unidad;
    }

    public Integer getReqeje_iva() {
        return reqeje_iva;
    }

    public void setReqeje_iva(Integer reqeje_iva) {
        this.reqeje_iva = reqeje_iva;
    }

    public Integer getReq_reforma2() {
        return req_reforma2;
    }

    public void setReq_reforma2(Integer req_reforma2) {
        this.req_reforma2 = req_reforma2;
    }

    public Double getReq_incremento() {
        return req_incremento;
    }

    public void setReq_incremento(Double req_incremento) {
        this.req_incremento = req_incremento;
    }

    public Double getReq_total() {
        return req_total;
    }

    public void setReq_total(Double req_total) {
        this.req_total = req_total;
    }

    public Integer getPresupuesto_reforma() {
        return presupuesto_reforma;
    }

    public void setPresupuesto_reforma(Integer presupuesto_reforma) {
        this.presupuesto_reforma = presupuesto_reforma;
    }

    public Integer getN_horas() {
        return n_horas;
    }

    public void setN_horas(Integer n_horas) {
        this.n_horas = n_horas;
    }

    public Double getSueldo_total() {
        return sueldo_total;
    }

    public void setSueldo_total(Double sueldo_total) {
        this.sueldo_total = sueldo_total;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getVerificado_uni_npac() {
        return verificado_uni_npac;
    }

    public void setVerificado_uni_npac(Double verificado_uni_npac) {
        this.verificado_uni_npac = verificado_uni_npac;
    }

    public Integer getAre_id() {
        return are_id;
    }

    public void setAre_id(Integer are_id) {
        this.are_id = are_id;
    }

    public String getAre_descripcion() {
        return are_descripcion;
    }

    public void setAre_descripcion(String are_descripcion) {
        this.are_descripcion = are_descripcion;
    }

    public String getAre_archivo() {
        return are_archivo;
    }

    public void setAre_archivo(String are_archivo) {
        this.are_archivo = are_archivo;
    }

    public Double getAe_porcentaje() {
        return ae_porcentaje;
    }

    public void setAe_porcentaje(Double ae_porcentaje) {
        this.ae_porcentaje = ae_porcentaje;
    }

    public Integer getAe_tiempo() {
        return ae_tiempo;
    }

    public void setAe_tiempo(Integer ae_tiempo) {
        this.ae_tiempo = ae_tiempo;
    }

    public Double getAe_ejecucion() {
        return ae_ejecucion;
    }

    public void setAe_ejecucion(Double ae_ejecucion) {
        this.ae_ejecucion = ae_ejecucion;
    }

    public List<cActividadRequerimiento> getActividad_eval() {
        return actividad_eval;
    }

    public void setActividad_eval(List<cActividadRequerimiento> actividad_eval) {
        this.actividad_eval = actividad_eval;
    }

    public Double getAe_autoeval() {
        return ae_autoeval;
    }

    public void setAe_autoeval(Double ae_autoeval) {
        this.ae_autoeval = ae_autoeval;
    }

    public String getAe_observacion() {
        return ae_observacion;
    }

    public void setAe_observacion(String ae_observacion) {
        this.ae_observacion = ae_observacion;
    }

    public Double getAe_eval() {
        return ae_eval;
    }

    public void setAe_eval(Double ae_eval) {
        this.ae_eval = ae_eval;
    }

    public Double getAe_evaluacion() {
        return ae_evaluacion;
    }

    public void setAe_evaluacion(Double ae_evaluacion) {
        this.ae_evaluacion = ae_evaluacion;
    }

    public Integer getMe_cuatrimestre() {
        return me_cuatrimestre;
    }

    public void setMe_cuatrimestre(Integer me_cuatrimestre) {
        this.me_cuatrimestre = me_cuatrimestre;
    }

    public Double getCp_valor() {
        return cp_valor;
    }

    public void setCp_valor(Double cp_valor) {
        this.cp_valor = cp_valor;
    }

    public String getCp_tipo() {
        return cp_tipo;
    }

    public void setCp_tipo(String cp_tipo) {
        this.cp_tipo = cp_tipo;
    }

    public Integer getAp_id() {
        return ap_id;
    }

    public void setAp_id(Integer ap_id) {
        this.ap_id = ap_id;
    }

    public String getObjetivo_nombre() {
        return objetivo_nombre;
    }

    public void setObjetivo_nombre(String objetivo_nombre) {
        this.objetivo_nombre = objetivo_nombre;
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

    public String getLogro_nombre() {
        return logro_nombre;
    }

    public void setLogro_nombre(String logro_nombre) {
        this.logro_nombre = logro_nombre;
    }

    public String getNudo_nombre() {
        return nudo_nombre;
    }

    public void setNudo_nombre(String nudo_nombre) {
        this.nudo_nombre = nudo_nombre;
    }

    public List<cComponenteMeta> getComponente() {
        return componente;
    }

    public void setComponente(List<cComponenteMeta> componente) {
        this.componente = componente;
    }

    public Integer getPresupuesto_id() {
        return presupuesto_id;
    }

    public void setPresupuesto_id(Integer presupuesto_id) {
        this.presupuesto_id = presupuesto_id;
    }

    public List<cActividadRequerimiento> getFechanopac() {
        return fechanopac;
    }

    public void setFechanopac(List<cActividadRequerimiento> fechanopac) {
        this.fechanopac = fechanopac;
    }

    public String getReq_iva2() {
        return req_iva2;
    }

    public void setReq_iva2(String req_iva2) {
        this.req_iva2 = req_iva2;
    }

    public Integer getPc_id() {
        return pc_id;
    }

    public void setPc_id(Integer pc_id) {
        this.pc_id = pc_id;
    }

    public String getPc_nombre() {
        return pc_nombre;
    }

    public void setPc_nombre(String pc_nombre) {
        this.pc_nombre = pc_nombre;
    }
}
