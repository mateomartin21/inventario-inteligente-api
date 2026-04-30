package com.portafoliomateo.invent_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity 
@Data  
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio y no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El SKU es obligatorio para el control de inventario")
    private String sku;

    @NotNull(message = "La cantidad actual no puede ser nula")
    @Min(value = 0, message = "La cantidad no puede ser un número negativo")
    private Integer cantidadActual;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un valor mayor a cero")
    private Double precio;

    @NotBlank(message = "La categoría es necesaria para los reportes de inversión")
    private String categoria;
    
    // Un método que nos dice si el producto necesita reabastecerse
    public boolean necesitaAlerta() {
        return this.cantidadActual <= this.stockMinimo;
    }
}
