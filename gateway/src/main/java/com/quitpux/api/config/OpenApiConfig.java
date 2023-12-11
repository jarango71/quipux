package com.quitpux.api.config;


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
                .title("Quipux API Gateway")
                .description("API Documentation for the Quipux API Gateway")
                .version("1.0.0");
    }
}
