package com.spickandspan.user.user.application.exception;

import com.spickandspan.user.common.exception.GlobalException;

public class InvalidEmailException extends GlobalException {
    public InvalidEmailException(String message, String code) {
        super(message, code);
    }
}
