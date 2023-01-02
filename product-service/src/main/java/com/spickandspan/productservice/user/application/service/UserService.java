package com.spickandspan.productservice.user.application.service;

import com.spickandspan.productservice.common.exception.EntityNotFoundException;
import com.spickandspan.productservice.common.exception.GlobalErrorCode;
import com.spickandspan.productservice.common.exception.GlobalException;
import com.spickandspan.productservice.user.application.exception.UserAlreadyRegisteredException;
import com.spickandspan.productservice.user.domain.dto.req.CreateUserRequest;
import com.spickandspan.productservice.user.domain.dto.res.UserResponse;
import com.spickandspan.productservice.user.domain.entity.Status;
import com.spickandspan.productservice.user.domain.entity.UserEntity;
import com.spickandspan.productservice.user.domain.mapper.UserMapper;
import com.spickandspan.productservice.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final KeycloakUserService keycloakUserService;
    private final UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest createUserRequest) {

        List<UserRepresentation> isUserExist = keycloakUserService.readUserByEmail(createUserRequest.getEmail());
        if (isUserExist.size() > 0) {
            throw new UserAlreadyRegisteredException("This email already registered as a user. Please check and retry.", GlobalErrorCode.ERROR_EMAIL_REGISTERED);
        }
        Integer userCreationResponse = keycloakUserService.createUserRepresentation(createUserRequest);
        if (userCreationResponse == 201) {
            log.info("User created under given username {}", createUserRequest.getEmail());
            List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(createUserRequest.getEmail());
            return UserMapper.userEntityToResponseDTO(this.createUserEntity(userRepresentations.get(0)), userRepresentations.get(0));
        }
        throw new GlobalException("While creating user a problem occurred", "400");
    }

    public UserEntity createUserEntity(UserRepresentation userRepresentation) {
        UserEntity userEntity = new UserEntity();
        userEntity.setKeycloakId(userRepresentation.getId());
        userEntity.setStatus(Status.APPROVED);
        return userRepository.save(userEntity);
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public UserEntity findByKeycloakId(String keycloakId) {
        return userRepository.findByKeycloakId(keycloakId).orElseThrow(EntityNotFoundException::new);
    }


    /*public List<UserResponse> readUsers(Pageable pageable) {
        Page<UserEntity> allUsersInDb = userRepository.findAll(pageable);
        List<UserEntity> userEntities = allUsersInDb.getContent();
        return userEntities.stream().map(userEntity -> {
            UserRepresentation userRepresentation = keycloakUserService.readUser(userEntity.getKeycloakId());
            return UserMapper.userEntityToResponseDTO(userEntity, userRepresentation);
        }).collect(Collectors.toList());
    }

    public UserResponse readUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        UserRepresentation userRepresentation = keycloakUserService.readUser(userEntity.getKeycloakId());
        return UserMapper.userEntityToResponseDTO(userEntity, userRepresentation);
    }*/

}