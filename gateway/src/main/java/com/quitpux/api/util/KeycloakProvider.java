package com.quitpux.api.util;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakProvider {

	@Value("${keycloack.provider.server-url}")
    private String server_url;
	
	@Value("${keycloack.provider.realm-name}")
    private String realm_name;
	
	@Value("${keycloack.provider.realm-master}")
    private String realm_master;
	
	@Value("${keycloack.provider.admin-clic}")
    private String admin_clic;
	
	@Value("${keycloack.provider.user-console}")
    private String user_console;
	
	@Value("${keycloack.provider.password-console}")
	private String password_console;
	
	@Value("${keycloack.provider.client-secret}")
    private String client_secret;

    public RealmResource getRealmResource() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(server_url)
                .realm(realm_master)
                .clientId(admin_clic)
                .username(user_console)
                .password(password_console)
                .clientSecret(client_secret)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(10)
                        .build())
                .build();

        return keycloak.realm(realm_name);
    }

    public UsersResource getUserResource() {
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }
}
