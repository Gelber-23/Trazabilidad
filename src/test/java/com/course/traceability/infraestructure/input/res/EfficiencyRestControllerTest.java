package com.course.traceability.infraestructure.input.res;

import com.course.traceability.application.dto.response.EfficiencyResponse;
import com.course.traceability.application.dto.response.RankingResponse;
import com.course.traceability.application.handler.IEfficiencyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EfficiencyRestControllerTest {

    @Mock
    private IEfficiencyHandler handler;

    @InjectMocks
    private EfficiencyRestController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEfficiency() {
        EfficiencyResponse response = new EfficiencyResponse();
        when(handler.getEfficiency(1L)).thenReturn(response);
        ResponseEntity<EfficiencyResponse> result = controller.getEfficiency(1L);
        assertEquals(response, result.getBody());
    }

    @Test
    void getEfficiencyByRestaurant() {
        List<EfficiencyResponse> list = Collections.singletonList(new EfficiencyResponse());
        when(handler.getEfficiencyByRestaurant(1L)).thenReturn(list);
        ResponseEntity<List<EfficiencyResponse>> result = controller.getEfficiencyByRestaurant(1L);
        assertEquals(list, result.getBody());
    }

    @Test
    void getRankEmployee() {
        RankingResponse response = new RankingResponse();
        when(handler.getRankEmployee(1L)).thenReturn(response);
        ResponseEntity<RankingResponse> result = controller.getRankEmployee(1L);
        assertEquals(response, result.getBody());
    }
}