package com.spickandspan.productservice.common.exception;


public class PermissionDeniedException extends GlobalException {
    public PermissionDeniedException() {
        super("Permission Denied", GlobalErrorCode.PERMISSION_DENIED);
    }

    public PermissionDeniedException(String message) {
        super(message, GlobalErrorCode.PERMISSION_DENIED);
    }
}
