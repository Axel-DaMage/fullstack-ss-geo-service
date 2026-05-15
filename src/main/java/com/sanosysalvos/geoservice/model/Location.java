package com.sanosysalvos.geoservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "locations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_mascota")
    private Long mascotaId;

    @Column(name = "latitud", nullable = false)
    @JsonProperty("latitude")
    private Double latitud;

    @Column(name = "longitud", nullable = false)
    @JsonProperty("longitude")
    private Double longitud;

    @Column(name = "zona_nombre", length = 100)
    @JsonProperty("zone")
    private String zonaNombre;

    @Column(name = "direccion", length = 255)
    @JsonProperty("address")
    private String direccion;

    @Column(name = "reportado_en", nullable = false)
    @JsonProperty("reportedAt")
    private LocalDateTime reportadoEn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zona")
    private Zone zonaEntity;

    @JsonIgnore
    public Zone getZona() { return zonaEntity; }
    public void setZona(Zone zona) { this.zonaEntity = zona; }

    @Column(name = "creado_en", nullable = false, updatable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en")
    private LocalDateTime actualizadoEn;

    @PrePersist
    protected void onCreate() {
        creadoEn = LocalDateTime.now();
        actualizadoEn = LocalDateTime.now();
        if (reportadoEn == null) {
            reportadoEn = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        actualizadoEn = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMascotaId() { return mascotaId; }
    public void setMascotaId(Long mascotaId) { this.mascotaId = mascotaId; }

    public Double getLatitud() { return latitud; }
    public void setLatitud(Double latitud) { this.latitud = latitud; }

    public Double getLongitud() { return longitud; }
    public void setLongitud(Double longitud) { this.longitud = longitud; }

    public String getZonaNombre() { return zonaNombre; }
    public void setZonaNombre(String zonaNombre) { this.zonaNombre = zonaNombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public LocalDateTime getReportadoEn() { return reportadoEn; }
    public void setReportadoEn(LocalDateTime reportadoEn) { this.reportadoEn = reportadoEn; }

    public Zone getZonaEntity() { return zonaEntity; }
    public void setZonaEntity(Zone zonaEntity) { this.zonaEntity = zonaEntity; }

    public LocalDateTime getCreadoEn() { return creadoEn; }
    public void setCreadoEn(LocalDateTime creadoEn) { this.creadoEn = creadoEn; }

    public LocalDateTime getActualizadoEn() { return actualizadoEn; }
    public void setActualizadoEn(LocalDateTime actualizadoEn) { this.actualizadoEn = actualizadoEn; }
}