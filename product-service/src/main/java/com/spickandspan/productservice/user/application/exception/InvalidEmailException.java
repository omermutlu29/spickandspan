package com.spickandspan.productservice.user.application.exception;

import com.spickandspan.productservice.common.exception.GlobalException;

public class InvalidEmailException extends GlobalException {
    public InvalidEmailException(String message, String code) {
        super(message, code);
    }
}
