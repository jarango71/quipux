package com.quitpux.api.config;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.quitpux.api.dto.RouteDTO;


@Component
@Scope(scopeName=ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RouteDefinitionsContext {
	
	private final ConcurrentHashMap<String, RouteDTO> serviceDescriptions; 

	private RouteDefinitionsContext(){
		serviceDescriptions = new ConcurrentHashMap<String, RouteDTO>();
	}
	 
	public void add(String serviceName, RouteDTO route){
		serviceDescriptions.put(serviceName, route);
	}
	 
	public RouteDTO get(String serviceId){
		return this.serviceDescriptions.get(serviceId);
    }
	
	public void clear(){
		this.serviceDescriptions.clear();
    }
	
	public List<RouteDTO>  getDefinitions(){
		return serviceDescriptions.values().stream().collect(Collectors.toList());
    }
	
}