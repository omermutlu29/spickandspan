package com.spickandspan.productservice.user.domain.dto.res;

import com.spickandspan.productservice.user.domain.entity.Status;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;//references to our user table's id field.
    private String email;
    private String firstName;
    private String lastName;
    private Status status;
}