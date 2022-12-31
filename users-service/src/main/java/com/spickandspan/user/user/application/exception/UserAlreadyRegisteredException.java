package com.spickandspan.user.user.application.exception;


import com.spickandspan.user.common.exception.GlobalException;

public class UserAlreadyRegisteredException extends GlobalException {
    public UserAlreadyRegisteredException(String message, String code) {
        super(message, code);
    }
}