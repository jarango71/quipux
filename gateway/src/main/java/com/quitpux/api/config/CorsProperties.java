package com.quitpux.api.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@ConfigurationProperties(prefix = "http")
@Validated
public class CorsProperties {
	
	private boolean corsEnabled;
	private final CorsConfiguration cors = new CorsConfiguration();
	
	public boolean isCorsEnabled() {
		return corsEnabled;
	}
	
	public void setCorsEnabled(boolean corsEnabled) {
		this.corsEnabled = corsEnabled;
	}
	
	public CorsConfiguration getCors() {
		return cors;
	}

}