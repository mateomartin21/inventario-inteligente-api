package com.portafoliomateo.invent_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity 
@Data  
public class Producto {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String sku; // Código único de producto
    private String nombre;
    private Integer cantidadActual;
    private Integer stockMinimo;
    private Double precio;
    private String categoria;

    // Un método que nos dice si el producto necesita reabastecerse
    public boolean necesitaAlerta() {
        return this.cantidadActual <= this.stockMinimo;
    }
}
