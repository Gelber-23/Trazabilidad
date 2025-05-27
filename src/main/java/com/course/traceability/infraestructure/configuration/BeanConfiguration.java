package com.course.traceability.infraestructure.configuration;

import com.course.traceability.domain.api.IEfficiencyServicePort;
import com.course.traceability.domain.api.ITrackServicePort;
import com.course.traceability.domain.spi.IEfficiencyPersistencePort;
import com.course.traceability.domain.spi.ITrackPersistencePort;
import com.course.traceability.domain.usecase.EfficiencyUseCase;
import com.course.traceability.domain.usecase.TrackUseCase;
import com.course.traceability.infraestructure.out.feign.restaurant.RestaurantFeignClientAdapter;
import com.course.traceability.infraestructure.out.feign.user.UserClientFeignAdapter;
import com.course.traceability.infraestructure.out.mongodb.adapter.EfficiencyMongoDbAdapter;
import com.course.traceability.infraestructure.out.mongodb.adapter.TrackMongoDbAdapter;
import com.course.traceability.infraestructure.out.mongodb.mapper.ITrackEntityMapper;
import com.course.traceability.infraestructure.out.mongodb.repository.ITrackRepository;
import com.course.traceability.infraestructure.util.UtilsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITrackRepository trackRepository;
    private final ITrackEntityMapper trackEntityMapper;
    private final UtilsAdapter utilsAdapter;
    private final UserClientFeignAdapter userClientFeignAdapter;
    private final RestaurantFeignClientAdapter restaurantFeignClientAdapter;
    @Bean
    public ITrackPersistencePort trackPersistencePort(){
        return new TrackMongoDbAdapter(trackRepository, trackEntityMapper);
    }

    @Bean
    public ITrackServicePort trackServicePort(){
        return new TrackUseCase(trackPersistencePort(),userClientFeignAdapter,utilsAdapter);
    }

    @Bean
    public IEfficiencyPersistencePort efficiencyPersistencePort(){
        return new EfficiencyMongoDbAdapter(trackRepository, trackEntityMapper);
    }

    @Bean
    public IEfficiencyServicePort efficiencyServicePort(){
        return new EfficiencyUseCase(efficiencyPersistencePort(),userClientFeignAdapter,restaurantFeignClientAdapter, utilsAdapter);
    }
}
