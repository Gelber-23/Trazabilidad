package com.course.traceability.domain.spi.feign;

import com.course.traceability.domain.model.feign.Restaurant;

public interface IRestaurantPort {
    Restaurant getRestaurantById(Long idRestaurant);
}
