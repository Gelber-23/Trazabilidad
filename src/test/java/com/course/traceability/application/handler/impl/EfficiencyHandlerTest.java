package com.course.traceability.application.handler.impl;

import com.course.traceability.application.dto.response.EfficiencyResponse;
import com.course.traceability.application.dto.response.RankingResponse;
import com.course.traceability.application.mapper.response.IEfficiencyResponseMapper;
import com.course.traceability.application.mapper.response.IRankingResponseMapper;
import com.course.traceability.domain.api.IEfficiencyServicePort;
import com.course.traceability.domain.model.Efficiency;
import com.course.traceability.domain.model.Ranking;
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
class EfficiencyHandlerTest {
    @Mock
    private IEfficiencyServicePort efficiencyServicePort;

    @Mock
    private IEfficiencyResponseMapper efficiencyResponseMapper;

    @Mock
    private IRankingResponseMapper rankingResponseMapper;

    @InjectMocks
    private EfficiencyHandler efficiencyHandler;

    @Test
    void getEfficiency_shouldReturnEfficiencyResponse() {
        Long orderId = 1L;
        Efficiency efficiency = new Efficiency();
        EfficiencyResponse expectedResponse = new EfficiencyResponse();

        when(efficiencyServicePort.getEfficiency(orderId)).thenReturn(efficiency);
        when(efficiencyResponseMapper.toResponse(efficiency)).thenReturn(expectedResponse);

        EfficiencyResponse result = efficiencyHandler.getEfficiency(orderId);

        assertEquals(expectedResponse, result);
        verify(efficiencyServicePort).getEfficiency(orderId);
        verify(efficiencyResponseMapper).toResponse(efficiency);
    }

    @Test
    void getEfficiencyByRestaurant_shouldReturnListOfEfficiencyResponse() {
        Long restaurantId = 1L;
        List<Efficiency> efficiencies = Collections.singletonList(new Efficiency());
        List<EfficiencyResponse> expectedResponse = Collections.singletonList(new EfficiencyResponse());

        when(efficiencyServicePort.getEfficiencyByRestaurant(restaurantId)).thenReturn(efficiencies);
        when(efficiencyResponseMapper.toResponseList(efficiencies)).thenReturn(expectedResponse);

        List<EfficiencyResponse> result = efficiencyHandler.getEfficiencyByRestaurant(restaurantId);

        assertEquals(expectedResponse, result);
        verify(efficiencyServicePort).getEfficiencyByRestaurant(restaurantId);
        verify(efficiencyResponseMapper).toResponseList(efficiencies);
    }

    @Test
    void getRankEmployee_shouldReturnRankingResponse() {
        Long employeeId = 5L;
        Ranking ranking = new Ranking();
        RankingResponse expectedResponse = new RankingResponse();

        when(efficiencyServicePort.getRankEmployee(employeeId)).thenReturn(ranking);
        when(rankingResponseMapper.toResponse(ranking)).thenReturn(expectedResponse);

        RankingResponse result = efficiencyHandler.getRankEmployee(employeeId);

        assertEquals(expectedResponse, result);
        verify(efficiencyServicePort).getRankEmployee(employeeId);
        verify(rankingResponseMapper).toResponse(ranking);
    }

}