package com.course.traceability.application.handler.impl;

import com.course.traceability.application.dto.request.TrackRequest;
import com.course.traceability.application.dto.response.TrackResponse;
import com.course.traceability.application.mapper.request.ITrackRequestMapper;
import com.course.traceability.application.mapper.response.ITrackResponseMapper;
import com.course.traceability.domain.api.ITrackServicePort;
import com.course.traceability.domain.model.Track;
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
class TrackHandlerTest {


    @Mock
    private ITrackServicePort trackServicePort;

    @Mock
    private ITrackRequestMapper trackRequestMapper;

    @Mock
    private ITrackResponseMapper trackResponseMapper;

    @InjectMocks
    private TrackHandler trackHandler;

    @Test
    void createTrack_shouldCallServiceWithMappedTrack() {
        TrackRequest request = new TrackRequest();
        Track mappedTrack = new Track();

        when(trackRequestMapper.toTrack(request)).thenReturn(mappedTrack);

        trackHandler.createTrack(request);

        verify(trackRequestMapper).toTrack(request);
        verify(trackServicePort).createTrack(mappedTrack);
    }

    @Test
    void getTrackOrder_shouldReturnMappedTrackResponses() {
        Long orderId = 1L;
        List<Track> domainTracks = Collections.singletonList(new Track());
        List<TrackResponse> expectedResponses = Collections.singletonList(new TrackResponse());

        when(trackServicePort.getTrackOrder(orderId)).thenReturn(domainTracks);
        when(trackResponseMapper.toResponseList(domainTracks)).thenReturn(expectedResponses);

        List<TrackResponse> result = trackHandler.getTrackOrder(orderId);

        assertEquals(expectedResponses, result);
        verify(trackServicePort).getTrackOrder(orderId);
        verify(trackResponseMapper).toResponseList(domainTracks);
    }

}