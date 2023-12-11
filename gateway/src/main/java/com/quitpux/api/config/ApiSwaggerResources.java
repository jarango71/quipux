package com.quitpux.api.config;

import java.util.ArrayList;
import java.util.List;

import com.quitpux.api.dto.RouteDTO;

import lombok.AllArgsConstructor;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@AllArgsConstructor
public class ApiSwaggerResources implements SwaggerResourcesProvider {
	
	private final UpdateRouteContext updateRouteContext;

    @Override
    public List<SwaggerResource> get() {
    	
        List<SwaggerResource> resources = new ArrayList<>();
        
        resources.add(swaggerResource("gateway", "/v3/api-docs", "3.0"));
        
        for (RouteDTO route : updateRouteContext.getDefinitionsContext().getDefinitions()) {
        	resources.add(swaggerResource(route.getName(), route.getUri() + "/v3/api-docs", "3.0"));
        }
        
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

    
}