package com.quitpux.api.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RoutesConfig {
    
	private final UpdateRouteContext updateRouteContext;

	
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
      return new ApiRouteLocator(updateRouteContext, routeLocatorBuilder);
    }
   
}