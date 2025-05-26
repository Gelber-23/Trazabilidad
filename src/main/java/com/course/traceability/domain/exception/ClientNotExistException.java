package com.course.traceability.domain.exception;

import com.course.traceability.domain.util.ExceptionsConstants;

public class ClientNotExistException extends RuntimeException {

    public ClientNotExistException() {
        super(ExceptionsConstants.CLIENT_NOT_EXIST_EXCEPTION);
    }

}
