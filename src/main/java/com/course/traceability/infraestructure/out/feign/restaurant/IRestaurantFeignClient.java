package com.course.traceability.infraestructure.out.feign.restaurant;

import com.course.traceability.domain.model.feign.Restaurant;
import com.course.traceability.infraestructure.out.feign.ClientFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "plazoleta",
        path = "/restaurant/",
        configuration = ClientFeignConfig.class
)
public interface IRestaurantFeignClient {

    @GetMapping("/{id}")
    Restaurant getRestaurantById(@PathVariable("id") Long id);
}
