package com.course.traceability.infraestructure.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.junit.jupiter.api.Assertions.*;

class UtilsAdapterTest {

    private final UtilsAdapter utilsAdapter = new UtilsAdapter();

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void returnsLongId_whenJwtHasLongClaim() {
        Jwt jwt = Jwt.withTokenValue("token").claim("id", 5L).header("alg", "none").build();
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt));
        assertEquals(5L, utilsAdapter.getCurrentUserId());
    }

    @Test
    void returnsLong_whenJwtHasIntegerClaim() {
        Jwt jwt = Jwt.withTokenValue("token").claim("id", 7).header("alg", "none").build();
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt));
        assertEquals(7L, utilsAdapter.getCurrentUserId());
    }

    @Test
    void returnsZero_whenClaimIsOtherType() {
        Jwt jwt = Jwt.withTokenValue("token").claim("id", "invalid").header("alg", "none").build();
        SecurityContextHolder.getContext().setAuthentication(new JwtAuthenticationToken(jwt));
        assertEquals(0L, utilsAdapter.getCurrentUserId());
    }


    @Test
    void returnsNull_whenAuthenticationIsNull() {
        assertNull(utilsAdapter.getCurrentUserId());
    }

}