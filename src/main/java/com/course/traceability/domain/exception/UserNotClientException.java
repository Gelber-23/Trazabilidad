package com.course.traceability.domain.exception;

import com.course.traceability.domain.util.ExceptionsConstants;

public class UserNotClientException extends RuntimeException {

    public UserNotClientException() {
        super(ExceptionsConstants.USER_NOT_CLIENT_EXCEPTION);
    }

}

