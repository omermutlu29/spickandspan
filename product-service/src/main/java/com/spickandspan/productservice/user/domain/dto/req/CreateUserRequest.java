package com.spickandspan.productservice.user.domain.dto.req;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
