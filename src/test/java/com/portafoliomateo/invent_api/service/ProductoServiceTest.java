package com.portafoliomateo.invent_api.service;

import com.portafoliomateo.invent_api.model.Producto;
import com.portafoliomateo.invent_api.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock 
    ProductoRepository repository;
    
    @InjectMocks 
    ProductoService service;

    @Test
    void obtenerAlertas_deberiaRetornarSoloProductosBajoStock() {
        // 1. Preparar los datos falsos (Mock)
        Producto alerta = new Producto();
        alerta.setCantidadActual(2);
        alerta.setStockMinimo(5);
        when(repository.findProductosEnAlerta()).thenReturn(List.of(alerta));

        // 2. Ejecutar el método real
        List<Producto> resultado = service.obtenerAlertas();

        // 3. Verificar que funcione como esperamos
        assertEquals(1, resultado.size());
        verify(repository).findProductosEnAlerta();
    }
}