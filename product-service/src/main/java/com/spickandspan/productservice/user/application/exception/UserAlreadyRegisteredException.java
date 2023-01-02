package com.spickandspan.productservice.user.application.exception;


import com.spickandspan.productservice.common.exception.GlobalException;

public class UserAlreadyRegisteredException extends GlobalException {
    public UserAlreadyRegisteredException(String message, String code) {
        super(message, code);
    }
}