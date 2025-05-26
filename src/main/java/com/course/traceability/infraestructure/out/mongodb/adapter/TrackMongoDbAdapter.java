package com.course.traceability.infraestructure.out.mongodb.adapter;

import com.course.traceability.domain.model.Track;
import com.course.traceability.domain.spi.ITrackPersistencePort;
import com.course.traceability.infraestructure.out.mongodb.entity.TrackEntity;
import com.course.traceability.infraestructure.out.mongodb.mapper.ITrackEntityMapper;
import com.course.traceability.infraestructure.out.mongodb.repository.ITrackRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class TrackMongoDbAdapter implements ITrackPersistencePort {
    private final ITrackRepository trackRepository;
    private final ITrackEntityMapper trackEntityMapper;

    @Override
    public void createTrack(Track track) {
        trackRepository.save(trackEntityMapper.toEntity(track));
    }

    @Override
    public List<Track> getTrackOrder(Long idOrder) {
        List<TrackEntity> trackEntityList = trackRepository.findAllByIdOrder(idOrder);

        return trackEntityMapper.toModelList(trackEntityList);
    }
}
