package com.portafoliomateo.invent_api.repository;

import com.portafoliomateo.invent_api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE p.cantidadActual <= p.stockMinimo")
    List<Producto> findProductosEnAlerta();

    List<Producto> findByCategoriaIgnoreCase(String categoria);
}