package com.quitpux.api.config;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import com.quitpux.api.dto.RouteDTO;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class ApiRouteLocator implements RouteLocator {

  private final UpdateRouteContext updateRouteContext;

  private final RouteLocatorBuilder routeLocatorBuilder;
  

  @Override
  public Flux<Route> getRoutes() {
    RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
    
    for (RouteDTO route : updateRouteContext.getDefinitionsContext().getDefinitions()) {
    	routesBuilder.route(route.getName(),
                r -> r.path(route.getPath())
                        .filters(f -> f.stripPrefix(1))
                        .uri(route.getUri()));
    }
    
    return routesBuilder.build().getRoutes();
  }

}