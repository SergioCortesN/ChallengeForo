package com.alurachallenges.forohub.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI forohubOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("ForoHub API")
                        .description("API REST del foro - documentación generada automáticamente")
                        .version("v1.0.0")
                        .contact(new Contact().name("ForoHub Maintainer"))
                        .license(new License().name("MIT")))
                .externalDocs(new ExternalDocumentation().description("Challenge context").url("/context/instructions.md"));
    }
}
