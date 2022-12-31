package com.spickandspan.user.user.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeycloakManager {

    private final KeycloakProperties keycloakProperties;

    public Keycloak getKeyCloakInstance() {
        return keycloakProperties.getInstance();
    }

}
