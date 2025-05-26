package com.course.traceability.infraestructure.exceptionhandler;

import com.course.traceability.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {
    private static final String MESSAGE = "Message";

    @ExceptionHandler(UserNotClientException.class)
    public ResponseEntity<Map<String, String>> handleUserNotClient (
            UserNotClientException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(
                        MESSAGE,
                        ex.getMessage()
                ));
    }
    @ExceptionHandler(UserNotEmployeeException.class)
    public ResponseEntity<Map<String, String>> handleUserNotEmployee (
            UserNotEmployeeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(
                        MESSAGE,
                        ex.getMessage()
                ));
    }
    @ExceptionHandler(ClientNotExistException.class)
    public ResponseEntity<Map<String, String>> handleClientNotExist (
            ClientNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(
                        MESSAGE,
                        ex.getMessage()
                ));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(
            UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(
                        MESSAGE,
                        ex.getMessage()
                ));
    }
    @ExceptionHandler(TrackValidationException.class)
    public ResponseEntity<Map<String, List<String>>> handleTrackValidation(
            TrackValidationException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap("errors", ex.getErrors()));
    }

}
