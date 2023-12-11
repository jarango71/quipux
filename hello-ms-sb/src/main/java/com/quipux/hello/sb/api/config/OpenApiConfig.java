package com.quipux.hello.sb.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Quipux API Hello MS Spring Boot")
                .description("API Documentation for the API Hello MS Spring Boot")
                .version("1.0.0");
                /*.license(new License()
                        .name(openApiProperties.getLicense())
                        .url(openApiProperties.getLicenseUrl()))
                .termsOfService(openApiProperties.getTermsOfService())
                .contact(openApiProperties.getContact())
                .extensions(openApiProperties.getExtensions());*/
    }
}