package com.course.traceability.infraestructure.out.mongodb.repository;

import com.course.traceability.infraestructure.out.mongodb.entity.TrackEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ITrackRepository  extends MongoRepository<TrackEntity, Long> {
    List<TrackEntity> findAllByIdOrder(Long idOrder);
}
