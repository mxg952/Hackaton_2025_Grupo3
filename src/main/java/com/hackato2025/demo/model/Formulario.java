package com.hackato2025.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "formularios")
public class Formulario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "residente_local", nullable = false)
    private Boolean residenteLocal;
    
    @Column(nullable = false, length = 50)
    private String barrio;
    
    @Column(name = "nota_media", nullable = false)
    private Double notaMedia;
    
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    
    // Constructores
    public Formulario() {
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public Formulario(Boolean residenteLocal, String barrio, Double notaMedia) {
        this.residenteLocal = residenteLocal;
        this.barrio = barrio;
        this.notaMedia = notaMedia;
        this.fechaCreacion = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Boolean getResidenteLocal() { return residenteLocal; }
    public void setResidenteLocal(Boolean residenteLocal) { this.residenteLocal = residenteLocal; }
    
    public String getBarrio() { return barrio; }
    public void setBarrio(String barrio) { this.barrio = barrio; }
    
    public Double getNotaMedia() { return notaMedia; }
    public void setNotaMedia(Double notaMedia) { this.notaMedia = notaMedia; }
    
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    @Override
    public String toString() {
        return "Formulario{" +
                "id=" + id +
                ", residenteLocal=" + residenteLocal +
                ", barrio='" + barrio + '\'' +
                ", notaMedia=" + notaMedia +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}