package com.quitpux.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String jwkSetUri;
	   
    @Autowired
    private JwtAuthConverter jwtAuthenticationConverter;
    
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
        		.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
        				.pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .pathMatchers(HttpMethod.POST,"/api/users/login").permitAll()
                        .pathMatchers(HttpMethod.POST,"/api/users/register").permitAll()
                        .pathMatchers(HttpMethod.GET, "/health").permitAll()
                        .pathMatchers(HttpMethod.GET, "/swagger-ui.html", "/swagger-ui/**", "/v2/api-docs.yaml", "/v3/api-docs.yaml", "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                                .jwt(jwtSpec -> jwtSpec
                                                .jwtDecoder(jwtDecoder())
                                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                                )
                )
                
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
    	return ReactiveJwtDecoders.fromIssuerLocation(jwkSetUri);
    }

}