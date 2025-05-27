package com.course.traceability.infraestructure.input.res;

import com.course.traceability.application.dto.request.TrackRequest;
import com.course.traceability.application.dto.response.TrackResponse;
import com.course.traceability.application.handler.ITrackHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackRestControllerTest {
    @Mock
    private ITrackHandler trackHandler;

    @InjectMocks
    private TrackRestController trackRestController;



    @Test
    void createTrack_shouldReturnCreatedStatus() {
        TrackRequest request = new TrackRequest();

        ResponseEntity<Void> response = trackRestController.createTrack(request);

        verify(trackHandler, times(1)).createTrack(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void getTrackOrder_shouldReturnListOfTrackResponse() {
        Long idOrder = 1L;
        List<TrackResponse> expectedList = List.of(new TrackResponse());

        when(trackHandler.getTrackOrder(idOrder)).thenReturn(expectedList);

        ResponseEntity<List<TrackResponse>> response = trackRestController.getTrackOrder(idOrder);

        verify(trackHandler, times(1)).getTrackOrder(idOrder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedList.size(), response.getBody().size());
        assertSame(expectedList, response.getBody());
    }

}