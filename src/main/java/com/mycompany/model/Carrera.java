/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author luana
 */
@Entity
@Table(name = "carrera")
@NamedQueries({
    @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c"),
    @NamedQuery(name = "Carrera.findByCodigo", query = "SELECT c FROM Carrera c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Carrera.findByNombre", query = "SELECT c FROM Carrera c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Carrera.findByDescripcion", query = "SELECT c FROM Carrera c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Carrera.findByPerfilProfesional", query = "SELECT c FROM Carrera c WHERE c.perfilProfesional = :perfilProfesional"),
    @NamedQuery(name = "Carrera.findByMercadoLaboral", query = "SELECT c FROM Carrera c WHERE c.mercadoLaboral = :mercadoLaboral")})
public class Carrera implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "perfil_profesional")
    private String perfilProfesional;
    @Basic(optional = false)
    @Column(name = "mercado_laboral")
    private String mercadoLaboral;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carreraCodigo")
    private List<PlanEstudios> planEstudiosList;
    
    public static final String[] HEADER_CARRERA = {"ID","NOMBRE","DESCRIPTION","PROFESSIONAL PERFILE", "JOB OPPORTUNITIES"};

    public Carrera() {
    }

    public Carrera(String codigo) {
        this.codigo = codigo;
    }

    public Carrera(String codigo, String nombre, String descripcion, String perfilProfesional, String mercadoLaboral) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.perfilProfesional = perfilProfesional;
        this.mercadoLaboral = mercadoLaboral;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPerfilProfesional() {
        return perfilProfesional;
    }

    public void setPerfilProfesional(String perfilProfesional) {
        this.perfilProfesional = perfilProfesional;
    }

    public String getMercadoLaboral() {
        return mercadoLaboral;
    }

    public void setMercadoLaboral(String mercadoLaboral) {
        this.mercadoLaboral = mercadoLaboral;
    }

    public List<PlanEstudios> getPlanEstudiosList() {
        return planEstudiosList;
    }

    public void setPlanEstudiosList(List<PlanEstudios> planEstudiosList) {
        this.planEstudiosList = planEstudiosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carrera{" + "codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", perfilProfesional=" + perfilProfesional + ", mercadoLaboral=" + mercadoLaboral + ", planEstudiosList=" + planEstudiosList + '}';
    }

    
    
}
