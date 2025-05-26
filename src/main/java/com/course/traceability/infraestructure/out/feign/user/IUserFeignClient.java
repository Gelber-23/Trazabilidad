package com.course.traceability.infraestructure.out.feign.user;

import com.course.traceability.domain.model.feign.User;
import com.course.traceability.infraestructure.out.feign.ClientFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "users",
        path = "/users/",
        configuration = ClientFeignConfig.class
)
public interface IUserFeignClient {
    @GetMapping("/{id}")
    User getById(@PathVariable("id") Long id);
}
