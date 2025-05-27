package com.course.traceability.domain.exception;

import com.course.traceability.domain.util.ExceptionsConstants;

public class UserNotOwnerException extends RuntimeException {

    public UserNotOwnerException() {
        super(ExceptionsConstants.USER_NOT_OWNER_EXCEPTION);
    }

}
