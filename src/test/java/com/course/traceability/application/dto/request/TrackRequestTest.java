package com.course.traceability.application.dto.request;


import com.course.traceability.domain.util.ValidationsConstant;
import jakarta.validation.ConstraintViolation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TrackRequestTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void allFieldsValid_noViolations() {
        TrackRequest request = new TrackRequest();
        request.setIdOrder(1L);
        request.setIdClient(1L);
        request.setIdEmployee(1L);
        request.setIdRestaurant(1L);
        request.setPreviousState("PREPARING");
        request.setNewState("DELIVERED");

        Set<ConstraintViolation<TrackRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void allFieldsInvalid_allViolationsTriggered() {
        TrackRequest request = new TrackRequest(); // All nulls & blanks

        Set<ConstraintViolation<TrackRequest>> violations = validator.validate(request);

        assertEquals(6, violations.size());

        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.ID_ORDER_REQUIRED_MESSAGE)));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.ID_CLIENT_REQUIRED_MESSAGE)));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.ID_EMPLOYEE_REQUIRED_MESSAGE)));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.ID_EMPLOYEE_REQUIRED_MESSAGE))); // Reused for restaurant
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.PREVIOUS_STATE_REQUIRED_MESSAGE)));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.NEW_STATE_REQUIRED_MESSAGE)));
    }

    @Test
    void testMinConstraintViolation() {
        TrackRequest request = new TrackRequest();
        request.setIdOrder(0L);
        request.setIdClient(0L);
        request.setIdEmployee(0L);
        request.setIdRestaurant(0L);
        request.setPreviousState("Valid");
        request.setNewState("Valid");

        Set<ConstraintViolation<TrackRequest>> violations = validator.validate(request);

        assertEquals(4, violations.size());
        assertTrue(violations.stream().allMatch(v -> v.getMessage().contains("required")));
    }

    @Test
    void testBlankFields() {
        TrackRequest request = new TrackRequest();
        request.setIdOrder(1L);
        request.setIdClient(1L);
        request.setIdEmployee(1L);
        request.setIdRestaurant(1L);
        request.setPreviousState("   ");
        request.setNewState("");

        Set<ConstraintViolation<TrackRequest>> violations = validator.validate(request);

        assertEquals(2, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.PREVIOUS_STATE_REQUIRED_MESSAGE)));
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals(ValidationsConstant.NEW_STATE_REQUIRED_MESSAGE)));
    }

}