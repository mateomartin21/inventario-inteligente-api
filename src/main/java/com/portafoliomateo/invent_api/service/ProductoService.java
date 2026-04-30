package com.portafoliomateo.invent_api.service;

import com.portafoliomateo.invent_api.model.Producto;
import com.portafoliomateo.invent_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service 
public class ProductoService {

    @Autowired 
    private ProductoRepository repository;

    public Producto guardarProducto(Producto producto) {
        return repository.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return repository.findAll();
    }

    public List<Producto> obtenerAlertas() {
        return repository.findAll().stream()
                .filter(Producto::necesitaAlerta) 
                .collect(Collectors.toList());
    }
    
    public boolean eliminarProducto(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; 
        }
        return false;
    }

}