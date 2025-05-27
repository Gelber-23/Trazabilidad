package com.course.traceability.infraestructure.security;

import com.course.traceability.domain.util.ValuesConstants;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PermissionServiceTest {

    private final PermissionService service = new PermissionService();
    private static final String ROLE_CLIENT= ValuesConstants.ROLE_STRING_VALUE_CLIENT;
    private static final String ROLE_OWNER= ValuesConstants.ROLE_STRING_VALUE_OWNER;

    @Test
    void isClient_returnsTrue_whenClientRolePresent() {
        Authentication auth = mock(Authentication.class);
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(ROLE_CLIENT)
        );
        doReturn(authorities).when(auth).getAuthorities();
        assertTrue(service.isClient(auth));
    }

    @Test
    void isClient_returnsFalse_whenClientRoleMissing() {
        Authentication auth = mock(Authentication.class);
        Collection<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(ROLE_OWNER)
        );
        doReturn(authorities).when(auth).getAuthorities();
        assertFalse(service.isClient(auth));
    }

    @Test
    void isOwner_returnsTrue_whenOwnerRolePresent() {
        Authentication auth = mock(Authentication.class);
        Collection<GrantedAuthority> authorities =  Collections.singletonList(
                new SimpleGrantedAuthority(ROLE_OWNER)
        );
        doReturn(authorities).when(auth).getAuthorities();
        assertTrue(service.isOwner(auth));
    }

    @Test
    void isOwner_returnsFalse_whenOwnerRoleMissing() {
        Authentication auth = mock(Authentication.class);
        Collection<GrantedAuthority> authorities =  Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_OTHER")
        );
        doReturn(authorities).when(auth).getAuthorities();
        assertFalse(service.isOwner(auth));
    }
}