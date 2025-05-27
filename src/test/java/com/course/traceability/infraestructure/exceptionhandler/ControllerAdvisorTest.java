package com.course.traceability.infraestructure.exceptionhandler;

import com.course.traceability.domain.exception.*;
import com.course.traceability.domain.util.ExceptionsConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAdvisorTest {

    private ControllerAdvisor advisor;

    @BeforeEach
    void setUp() {
        advisor = new ControllerAdvisor();
    }

    @Test
    void handleUserNotClient() {

        ResponseEntity<Map<String, String>> response = advisor.handleUserNotClient(new UserNotClientException());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ExceptionsConstants.USER_NOT_CLIENT_EXCEPTION, Objects.requireNonNull(response.getBody()).get("Message"));
    }

    @Test
    void handleUserNotEmployee() {
        String msg = ExceptionsConstants.USER_NOT_EMPLOYEE_EXCEPTION;
        ResponseEntity<Map<String, String>> response = advisor.handleUserNotEmployee(new UserNotEmployeeException());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(msg, Objects.requireNonNull(response.getBody()).get("Message"));
    }

    @Test
    void handleClientNotExist() {
        String msg = ExceptionsConstants.CLIENT_NOT_EXIST_EXCEPTION;
        ResponseEntity<Map<String, String>> response = advisor.handleClientNotExist(new ClientNotExistException());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(msg, Objects.requireNonNull(response.getBody()).get("Message"));
    }

    @Test
    void handleUserNotFound() {
        String msg = ExceptionsConstants.USER_NOT_FOUND_EXCEPTION + ": " + 10L;
        ResponseEntity<Map<String, String>> response = advisor.handleUserNotFound(new UserNotFoundException(10L));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(msg, Objects.requireNonNull(response.getBody()).get("Message"));
    }

    @Test
    void handleUserNotOwner() {
        String msg = ExceptionsConstants.USER_NOT_OWNER_EXCEPTION;
        ResponseEntity<Map<String, String>> response = advisor.handleUserNotOwner(new UserNotOwnerException());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(msg, Objects.requireNonNull(response.getBody()).get("Message"));
    }

    @Test
    void handleRestaurantNotFound() {
        String msg = "Restaurant not found";
        ResponseEntity<Map<String, String>> response = advisor.handleRestaurantNotFound(new RestaurantNotFoundException(msg));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(msg, Objects.requireNonNull(response.getBody()).get("Message"));
    }

    @Test
    void handleTrackValidation() {
        List<String> errors = Arrays.asList("error1", "error2");
        ResponseEntity<Map<String, List<String>>> response = advisor.handleTrackValidation(new TrackValidationException(errors));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(errors, Objects.requireNonNull(response.getBody()).get("errors"));
    }

}