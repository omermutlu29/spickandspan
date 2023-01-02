package com.spickandspan.productservice.user.application.service;

import com.spickandspan.productservice.user.infrastructure.config.KeycloakProperties;
import com.spickandspan.productservice.user.domain.dto.req.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    @Value("${app.config.keycloak.realm}")
    private String realm;
    private final KeycloakProperties keyCloakManager;

    private Integer createUser(UserRepresentation userRepresentation) {
        return keyCloakManager.getInstance().realm(realm).users().create(userRepresentation).getStatus();
    }

    public void updateUser(UserRepresentation userRepresentation) {
        keyCloakManager.getInstance().realm(realm).users().get(userRepresentation.getId()).update(userRepresentation);
    }


    public List<UserRepresentation> readUserByEmail(String email) {
        try{
            return keyCloakManager.getInstance().realm(realm).users().search(email).stream().toList();
        }catch (Exception exception){
            System.out.println(exception.getCause().toString());
            System.out.println(exception.getMessage().toString());
            throw exception;
        }
    }


    public UserRepresentation readUser(String authId) {
        try {
            UserResource userResource = keyCloakManager.getInstance().realm(realm).users().get(authId);
            return userResource.toRepresentation();
        } catch (Exception e) {
            throw new EntityNotFoundException("User not found under given ID");
        }
    }

    public Integer createUserRepresentation(CreateUserRequest createUserRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEmail(createUserRequest.getEmail());
        userRepresentation.setEmailVerified(false);
        userRepresentation.setEnabled(false);
        userRepresentation.setFirstName(createUserRequest.getFirstName());
        userRepresentation.setLastName(createUserRequest.getLastName());
        userRepresentation.setUsername(createUserRequest.getEmail());

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(createUserRequest.getPassword());
        credentialRepresentation.setTemporary(false);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));
        return this.createUser(userRepresentation);
    }
}