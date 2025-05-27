package com.course.traceability.infraestructure.out.feign.restaurant;

import com.course.traceability.domain.exception.RestaurantNotFoundException;
import com.course.traceability.domain.model.feign.Restaurant;
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
class RestaurantFeignClientAdapterTest {

    @Mock
    IRestaurantFeignClient feign;

    @InjectMocks
    RestaurantFeignClientAdapter adapter;

    @Test
    void getRestaurantById_success() {
        Restaurant restaurant = new Restaurant();
        when(feign.getRestaurantById(1L)).thenReturn(restaurant);
        assertEquals(restaurant, adapter.getRestaurantById(1L));
    }

    @Test
    void getRestaurantById_notFound() {
        when(feign.getRestaurantById(1L)).thenThrow(mock(FeignException.NotFound.class));
        assertThrows(RestaurantNotFoundException.class, () -> adapter.getRestaurantById(1L));
    }

}