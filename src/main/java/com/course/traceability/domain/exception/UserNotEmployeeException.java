package com.course.traceability.domain.exception;

import com.course.traceability.domain.util.ExceptionsConstants;

public class UserNotEmployeeException extends RuntimeException {

    public UserNotEmployeeException() {
        super(ExceptionsConstants.USER_NOT_EMPLOYEE_EXCEPTION);
    }

}
