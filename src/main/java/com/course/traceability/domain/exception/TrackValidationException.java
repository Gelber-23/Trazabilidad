package com.course.traceability.domain.exception;

import java.util.List;

public class TrackValidationException  extends RuntimeException {
    private final List<String> errors;

    public TrackValidationException(List<String> errors) {
        super("Errors: " + errors);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}