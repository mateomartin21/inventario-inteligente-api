package com.portafoliomateo.invent_api.service;

import com.portafoliomateo.invent_api.model.Producto;
import com.portafoliomateo.invent_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public List<Producto> obtenerPorCategoria(String categoria) {
        return repository.findByCategoriaIgnoreCase(categoria);
    }

    public Producto guardarProducto(Producto producto) {
        return repository.save(producto);
    }

    public List<Producto> obtenerAlertas() {
        return repository.findProductosEnAlerta();
    }

    public boolean eliminarProducto(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public Producto actualizarProducto(Long id, Producto datosNuevos) {
        return repository.findById(id).map(existente -> {
            existente.setNombre(datosNuevos.getNombre());
            existente.setSku(datosNuevos.getSku());
            existente.setCantidadActual(datosNuevos.getCantidadActual());
            existente.setStockMinimo(datosNuevos.getStockMinimo());
            existente.setPrecio(datosNuevos.getPrecio());
            existente.setCategoria(datosNuevos.getCategoria());
            return repository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Producto con ID " + id + " no encontrado"));
    }
}