package com.course.traceability.infraestructure.security;

import com.course.traceability.domain.util.ValuesConstants;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component

public class PermissionService {

    private static final String ROLE_CLIENT= ValuesConstants.ROLE_STRING_VALUE_CLIENT;
    private static final String ROLE_OWNER= ValuesConstants.ROLE_STRING_VALUE_OWNER;

    public boolean isClient(Authentication auth) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(ROLE_CLIENT));
    }
    public boolean isOwner(Authentication auth) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(ROLE_OWNER));
    }

}
