package com.course.traceability.infraestructure.out.mongodb.adapter;

import com.course.traceability.domain.model.Track;
import com.course.traceability.infraestructure.out.mongodb.entity.TrackEntity;
import com.course.traceability.infraestructure.out.mongodb.mapper.ITrackEntityMapper;
import com.course.traceability.infraestructure.out.mongodb.repository.ITrackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrackMongoDbAdapterTest {

    @Mock
    ITrackRepository repository;

    @Mock
    ITrackEntityMapper mapper;

    @InjectMocks
    TrackMongoDbAdapter adapter;


    @Test
    void createTrack() {
        Track track = new Track();
        TrackEntity entity = new TrackEntity();
        when(mapper.toEntity(track)).thenReturn(entity);
        adapter.createTrack(track);
        verify(repository).save(entity);
    }

    @Test
    void getTrackOrder() {
        List<TrackEntity> entities = Collections.singletonList(new TrackEntity());
        List<Track> tracks = Collections.singletonList(new Track());
        when(repository.findAllByIdOrder(1L)).thenReturn(entities);
        when(mapper.toModelList(entities)).thenReturn(tracks);
        assertEquals(tracks, adapter.getTrackOrder(1L));
    }

}