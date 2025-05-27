package com.course.traceability.infraestructure.out.feign.user;

import com.course.traceability.domain.exception.UserNotFoundException;
import com.course.traceability.domain.model.feign.User;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserClientFeignAdapterTest {
    @Mock
    IUserFeignClient feign;

    @InjectMocks
    UserClientFeignAdapter adapter;

    @Test
    void getUserById_success() {
        User user = new User();
        when(feign.getById(1L)).thenReturn(user);
        assertEquals(user, adapter.getUserById(1L));
    }

    @Test
    void getUserById_notFound() {
        when(feign.getById(1L)).thenThrow(mock(FeignException.NotFound.class));
        assertThrows(UserNotFoundException.class, () -> adapter.getUserById(1L));
    }

}