package com.course.traceability.infraestructure.out.feign.user;

import com.course.traceability.domain.exception.UserNotFoundException;
import com.course.traceability.domain.model.feign.User;
import com.course.traceability.domain.spi.feign.IUserPort;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserClientFeignAdapter  implements IUserPort {
    private final IUserFeignClient feign;

    @Override
    public User getUserById(Long userId) {
        try {
            return feign.getById(userId);
        } catch (FeignException.NotFound ex) {
            throw new UserNotFoundException(userId);
        }
    }
}
