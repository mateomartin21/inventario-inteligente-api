package com.portafoliomateo.invent_api.controller;

import com.portafoliomateo.invent_api.model.Producto;
import com.portafoliomateo.invent_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Gestión de Inventario", description = "Endpoints para el control total de stock y productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @Operation(summary = "Listar todos los productos")
    @GetMapping
    public List<Producto> listar() {
        return service.obtenerTodos();
    }

    @Operation(summary = "Obtener un producto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar productos por categoría")
    @GetMapping("/buscar")
    public List<Producto> buscarPorCategoria(@RequestParam String categoria) {
        return service.obtenerPorCategoria(categoria);
    }

    @Operation(summary = "Registrar un nuevo producto")
    @PostMapping
    public Producto crear(@Valid @RequestBody Producto producto) {
        return service.guardarProducto(producto);
    }

    @Operation(summary = "Actualizar stock o datos de un producto existente")
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto) {
        return ResponseEntity.ok(service.actualizarProducto(id, producto));
    }

    @Operation(summary = "Obtener productos con stock bajo (Alertas)")
    @GetMapping("/alertas")
    public List<Producto> obtenerAlertas() {
        return service.obtenerAlertas();
    }

    @Operation(summary = "Eliminar un producto del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return service.eliminarProducto(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}