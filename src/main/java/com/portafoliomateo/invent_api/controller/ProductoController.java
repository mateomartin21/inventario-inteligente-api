package com.portafoliomateo.invent_api.controller;

import com.portafoliomateo.invent_api.model.Producto;
import com.portafoliomateo.invent_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/productos")
// NUEVO: Nombra y describe todo el grupo de botones
@Tag(name = "Catálogo de Productos", description = "Operaciones principales para gestionar los artículos del almacén")
public class ProductoController {

    @Autowired
    private ProductoService service;

    // NUEVO: Explica qué hace este botón específico
    @Operation(summary = "Añadir Nuevo Artículo", description = "Registra un producto completamente nuevo en el sistema con su información financiera y de stock.")
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return service.guardarProducto(producto);
    }

    @Operation(summary = "Ver Catálogo Completo", description = "Obtiene la lista de todos los artículos registrados actualmente en la base de datos.")
    @GetMapping
    public List<Producto> listarTodos() {
        return service.obtenerTodos();
    }

    @Operation(summary = "Alertas de Stock Crítico", description = "Filtra y muestra de forma automática solo los productos que están por debajo de su nivel de stock de seguridad.")
    @GetMapping("/alertas")
    public List<Producto> listarAlertas() {
        return service.obtenerAlertas();
    }

    @Operation(summary = "Eliminar Producto Obsoleto", description = "Remueve permanentemente un artículo del inventario buscando por su número de ID único.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = service.eliminarProducto(id);
        
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado correctamente.");
        } else {
            return ResponseEntity.status(404).body("Error: El producto con ID " + id + " no existe.");
        }
    }
}