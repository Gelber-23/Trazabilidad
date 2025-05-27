package com.course.traceability.infraestructure.out.feign;

import feign.RequestTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import static org.junit.jupiter.api.Assertions.*;

class ClientFeignConfigTest {
    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void bearerAuthInterceptor_shouldAddAuthorizationHeader() {
        Jwt jwt = Jwt.withTokenValue("mock-token").header("alg", "none").claim("sub", "user").build();
        JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(jwt);
        SecurityContextHolder.getContext().setAuthentication(jwtToken);

        RequestTemplate template = new RequestTemplate();
        new ClientFeignConfig().bearerAuthInterceptor().apply(template);

        assertTrue(template.headers().containsKey("Authorization"));
        assertEquals("Bearer mock-token", template.headers().get("Authorization").iterator().next());
    }

    @Test
    void bearerAuthInterceptor_shouldDoNothingIfNoJwt() {
        SecurityContextHolder.clearContext(); // No authentication set
        RequestTemplate template = new RequestTemplate();
        new ClientFeignConfig().bearerAuthInterceptor().apply(template);

        assertFalse(template.headers().containsKey("Authorization"));
    }

}