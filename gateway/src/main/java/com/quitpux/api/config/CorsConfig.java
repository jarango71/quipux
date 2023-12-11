package com.quitpux.api.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class CorsConfig {
	
    private final CorsProperties corsProperties;

    @Bean
    @ConditionalOnProperty(prefix = "http", name = "cors-enabled", matchIfMissing = false, havingValue = "true")
    public CorsWebFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = corsProperties.getCors();
        
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            source.registerCorsConfiguration("/**", config);
        }
        
        return new CorsWebFilter(source);
    }

}
