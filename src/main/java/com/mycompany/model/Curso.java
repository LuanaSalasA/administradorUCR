/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author luana
 */
@Entity
@Table(name = "curso")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findBySigla", query = "SELECT c FROM Curso c WHERE c.sigla = :sigla"),
    @NamedQuery(name = "Curso.findByBloque", query = "SELECT c FROM Curso c WHERE c.bloque = :bloque"),
    @NamedQuery(name = "Curso.findByHorasTrabajo", query = "SELECT c FROM Curso c WHERE c.horasTrabajo = :horasTrabajo"),
    @NamedQuery(name = "Curso.findByHorasLectivas", query = "SELECT c FROM Curso c WHERE c.horasLectivas = :horasLectivas"),
    @NamedQuery(name = "Curso.findByCantidadCreditos", query = "SELECT c FROM Curso c WHERE c.cantidadCreditos = :cantidadCreditos"),
    @NamedQuery(name = "Curso.findByModalidad", query = "SELECT c FROM Curso c WHERE c.modalidad = :modalidad"),
    @NamedQuery(name = "Curso.findByDescripcion", query = "SELECT c FROM Curso c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Curso.findByNombre", query = "SELECT c FROM Curso c WHERE c.nombre = :nombre")})
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "sigla")
    private Integer sigla;
    @Basic(optional = false)
    @Column(name = "bloque")
    private String bloque;
    @Basic(optional = false)
    @Column(name = "horas_trabajo")
    private int horasTrabajo;
    @Basic(optional = false)
    @Column(name = "horas_lectivas")
    private int horasLectivas;
    @Basic(optional = false)
    @Column(name = "cantidad_creditos")
    private int cantidadCreditos;
    @Basic(optional = false)
    @Column(name = "modalidad")
    private String modalidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "plan_estudios_has_curso", joinColumns = {
        @JoinColumn(name = "curso_sigla", referencedColumnName = "sigla")}, inverseJoinColumns = {
        @JoinColumn(name = "plan_estudios_id", referencedColumnName = "id")})
    @ManyToMany
    private List<PlanEstudios> planEstudiosList;
    
    public static final String[] HEADER_CURSO = {"ACRONYM","NAME","BLOCK","WORKING_HOURS","TEACHING_HOURS","NUMBER_CREDITS","MODALITY","DESCRIPTION"};

    public Curso() {
    }

    public Curso(Integer sigla) {
        this.sigla = sigla;
    }

    public Curso(Integer sigla, String bloque, int horasTrabajo, int horasLectivas, int cantidadCreditos, String modalidad, String descripcion, String nombre) {
        this.sigla = sigla;
        this.bloque = bloque;
        this.horasTrabajo = horasTrabajo;
        this.horasLectivas = horasLectivas;
        this.cantidadCreditos = cantidadCreditos;
        this.modalidad = modalidad;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Integer getSigla() {
        return sigla;
    }

    public void setSigla(Integer sigla) {
        this.sigla = sigla;
    }

    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    public int getHorasTrabajo() {
        return horasTrabajo;
    }

    public void setHorasTrabajo(int horasTrabajo) {
        this.horasTrabajo = horasTrabajo;
    }

    public int getHorasLectivas() {
        return horasLectivas;
    }

    public void setHorasLectivas(int horasLectivas) {
        this.horasLectivas = horasLectivas;
    }

    public int getCantidadCreditos() {
        return cantidadCreditos;
    }

    public void setCantidadCreditos(int cantidadCreditos) {
        this.cantidadCreditos = cantidadCreditos;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (sigla != null ? sigla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.sigla == null && other.sigla != null) || (this.sigla != null && !this.sigla.equals(other.sigla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curso{" + "sigla=" + sigla + ", bloque=" + bloque + ", horasTrabajo=" + horasTrabajo + ", horasLectivas=" + horasLectivas + ", cantidadCreditos=" + cantidadCreditos + ", modalidad=" + modalidad + ", descripcion=" + descripcion + ", nombre=" + nombre + ", planEstudiosList=" + planEstudiosList + '}';
    }

    
    
}
