package com.course.traceability.domain.exception;

import com.course.traceability.domain.util.ExceptionsConstants;

public class UserNotFoundException   extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super(ExceptionsConstants.USER_NOT_FOUND_EXCEPTION + ": " + userId);
    }

}
