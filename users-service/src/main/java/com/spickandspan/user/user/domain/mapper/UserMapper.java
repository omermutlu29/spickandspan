package com.spickandspan.user.user.domain.mapper;

import com.spickandspan.user.user.domain.dto.res.UserResponse;
import com.spickandspan.user.user.domain.entity.UserEntity;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public static UserResponse userEntityToResponseDTO(UserEntity userEntity, UserRepresentation userRepresentation) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(userEntity.getId());
        userResponse.setEmail(userRepresentation.getEmail());
        userResponse.setFirstName(userRepresentation.getFirstName());
        userResponse.setLastName(userRepresentation.getLastName());
        userResponse.setStatus(userEntity.getStatus());
        return userResponse;
    }
}
