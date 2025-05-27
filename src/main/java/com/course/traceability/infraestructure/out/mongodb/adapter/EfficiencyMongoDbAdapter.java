package com.course.traceability.infraestructure.out.mongodb.adapter;

import com.course.traceability.domain.model.Track;
import com.course.traceability.domain.spi.IEfficiencyPersistencePort;
import com.course.traceability.infraestructure.out.mongodb.entity.TrackEntity;
import com.course.traceability.infraestructure.out.mongodb.mapper.ITrackEntityMapper;
import com.course.traceability.infraestructure.out.mongodb.repository.ITrackRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;


@RequiredArgsConstructor
public class EfficiencyMongoDbAdapter implements IEfficiencyPersistencePort {

    private final ITrackRepository trackRepository;
    private final ITrackEntityMapper trackEntityMapper;

    @Override
    public List<Track> getEfficiencyTracks(Long idOrder) {

        List<TrackEntity> entities = trackRepository.findAllByIdOrder(idOrder);


        return trackEntityMapper.toModelList(entities);
    }

    @Override
    public List<Track> getEfficiencyByRestaurant(Long idRestaurant) {

        List<TrackEntity> entities = trackRepository.findAllByIdRestaurant(idRestaurant);
        return trackEntityMapper.toModelList(entities);


    }

    @Override
    public List<Track> getTrackHistoryByEmployee(Long idEmployee) {
        List<TrackEntity> entities = trackRepository.findAllByIdEmployee(idEmployee);
        return trackEntityMapper.toModelList(entities);
    }

}
