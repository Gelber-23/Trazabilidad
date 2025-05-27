package com.course.traceability.infraestructure.out.mongodb.adapter;

import com.course.traceability.domain.model.Track;
import com.course.traceability.infraestructure.out.mongodb.entity.TrackEntity;
import com.course.traceability.infraestructure.out.mongodb.mapper.ITrackEntityMapper;
import com.course.traceability.infraestructure.out.mongodb.repository.ITrackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EfficiencyMongoDbAdapterTest {


    @Mock
    private ITrackRepository repository;

    @Mock
    private ITrackEntityMapper mapper;

    @InjectMocks
    private EfficiencyMongoDbAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEfficiencyTracks() {
        List<TrackEntity> entities = Collections.singletonList(new TrackEntity());
        List<Track> tracks = Collections.singletonList(new Track());
        when(repository.findAllByIdOrder(1L)).thenReturn(entities);
        when(mapper.toModelList(entities)).thenReturn(tracks);
        assertEquals(tracks, adapter.getEfficiencyTracks(1L));
    }

    @Test
    void getEfficiencyByRestaurant() {
        List<TrackEntity> entities = Collections.singletonList(new TrackEntity());
        List<Track> tracks = Collections.singletonList(new Track());
        when(repository.findAllByIdRestaurant(1L)).thenReturn(entities);
        when(mapper.toModelList(entities)).thenReturn(tracks);
        assertEquals(tracks, adapter.getEfficiencyByRestaurant(1L));
    }

    @Test
    void getTrackHistoryByEmployee() {
        List<TrackEntity> entities = Collections.singletonList(new TrackEntity());
        List<Track> tracks = Collections.singletonList(new Track());
        when(repository.findAllByIdEmployee(1L)).thenReturn(entities);
        when(mapper.toModelList(entities)).thenReturn(tracks);
        assertEquals(tracks, adapter.getTrackHistoryByEmployee(1L));
    }

}