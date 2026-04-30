package com.portafoliomateo.invent_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("InventAPI")
                        .version("v1.0")
                        .description("Sistema de gestión de stock, alertas de reabastecimiento y análisis financiero para gerentes de tienda.")
                        .contact(new Contact()
                                .name("Mateo Martin")
                                .email("mateo.martin@tecdesoftware.edu.mx")));
    }
}