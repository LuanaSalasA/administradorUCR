/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author luana
 */
@Entity
@Table(name = "plan_estudios")
@NamedQueries({
    @NamedQuery(name = "PlanEstudios.findAll", query = "SELECT p FROM PlanEstudios p"),
    @NamedQuery(name = "PlanEstudios.findById", query = "SELECT p FROM PlanEstudios p WHERE p.id = :id"),
    @NamedQuery(name = "PlanEstudios.findByDescripcion", query = "SELECT p FROM PlanEstudios p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PlanEstudios.findByCantidadCreditos", query = "SELECT p FROM PlanEstudios p WHERE p.cantidadCreditos = :cantidadCreditos"),
    @NamedQuery(name = "PlanEstudios.findByFechaAprobacion", query = "SELECT p FROM PlanEstudios p WHERE p.fechaAprobacion = :fechaAprobacion"),
    @NamedQuery(name = "PlanEstudios.findByFechaVigor", query = "SELECT p FROM PlanEstudios p WHERE p.fechaVigor = :fechaVigor")})
public class PlanEstudios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "cantidad_creditos")
    private int cantidadCreditos;
    @Basic(optional = false)
    @Column(name = "fecha_aprobacion")
    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;
    @Basic(optional = false)
    @Column(name = "fecha_vigor")
    @Temporal(TemporalType.DATE)
    private Date fechaVigor;
    @ManyToMany(mappedBy = "planEstudiosList")
    private List<Curso> cursoList;
    @JoinColumn(name = "carrera_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Carrera carreraCodigo;
    
    public static final String[] HEADER_STUDY_PLAN = {"ID","MAJOR CODE","NUMBER OF CREDITS","APPROVAL DATE","EFFECTIVE DATE","DESCRIPTION","MAJOR"};

    public PlanEstudios() {
    }

    public PlanEstudios(Integer id) {
        this.id = id;
    }

    public PlanEstudios(Integer id, String descripcion, int cantidadCreditos, Date fechaAprobacion, Date fechaVigor, Carrera carrera) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidadCreditos = cantidadCreditos;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaVigor = fechaVigor;
        this.carreraCodigo = carrera;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadCreditos() {
        return cantidadCreditos;
    }

    public void setCantidadCreditos(int cantidadCreditos) {
        this.cantidadCreditos = cantidadCreditos;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaVigor() {
        return fechaVigor;
    }

    public void setFechaVigor(Date fechaVigor) {
        this.fechaVigor = fechaVigor;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public Carrera getCarreraCodigo() {
        return carreraCodigo;
    }

    public void setCarreraCodigo(Carrera carreraCodigo) {
        this.carreraCodigo = carreraCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanEstudios)) {
            return false;
        }
        PlanEstudios other = (PlanEstudios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanEstudios{" + "id=" + id + ", descripcion=" + descripcion + ", cantidadCreditos=" + cantidadCreditos + ", fechaAprobacion=" + fechaAprobacion + ", fechaVigor=" + fechaVigor + ", cursoList=" + cursoList + ", carreraCodigo=" + carreraCodigo + '}';
    }

    
    
}
