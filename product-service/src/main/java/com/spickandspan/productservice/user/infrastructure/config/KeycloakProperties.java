package com.spickandspan.productservice.user.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KeycloakProperties {
    @Value("${app.config.keycloak.server-url}")
    private String serverUrl;

    @Value("${app.config.keycloak.realm}")
    private String realm;

    @Value("${app.config.keycloak.clientId}")
    private String clientId;

    @Value("${app.config.keycloak.client-secret}")
    private String clientSecret;

    @Value("${app.config.keycloak.username}")
    private String username;

    @Value("${app.config.keycloak.password}")
    private String password;

    private static Keycloak keycloakInstance = null;

    public Keycloak getInstance() {
        if (keycloakInstance == null) {
            return  KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .username(username)
                    .password(password)
                    .build();
        }
        return keycloakInstance;
    }
}