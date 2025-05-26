package com.course.traceability.domain.spi.feign;

import com.course.traceability.domain.model.feign.User;

public interface IUserPort {
    User getUserById(Long userId);
}
