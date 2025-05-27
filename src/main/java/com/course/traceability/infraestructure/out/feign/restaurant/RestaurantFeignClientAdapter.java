package com.course.traceability.infraestructure.out.feign.restaurant;

import com.course.traceability.domain.exception.RestaurantNotFoundException;
import com.course.traceability.domain.model.feign.Restaurant;
import com.course.traceability.domain.spi.feign.IRestaurantPort;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestaurantFeignClientAdapter implements IRestaurantPort {
    private final IRestaurantFeignClient feign;

    @Override
    public Restaurant getRestaurantById(Long userId) {
        try {
            return feign.getRestaurantById(userId);
        } catch (FeignException.NotFound ex) {
            throw new RestaurantNotFoundException(ex.getMessage());
        }
    }
}
