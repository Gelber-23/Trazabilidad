package com.course.traceability.application.handler.impl;

import com.course.traceability.application.dto.response.EfficiencyResponse;
import com.course.traceability.application.dto.response.RankingResponse;
import com.course.traceability.application.handler.IEfficiencyHandler;
import com.course.traceability.application.mapper.response.IEfficiencyResponseMapper;
import com.course.traceability.application.mapper.response.IRankingResponseMapper;
import com.course.traceability.domain.api.IEfficiencyServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EfficiencyHandler implements IEfficiencyHandler {

    private final IEfficiencyServicePort efficiencyServicePort;
    private final IEfficiencyResponseMapper efficiencyResponseMapper;
    private final IRankingResponseMapper rankingResponseMapper;

    @Override
    public EfficiencyResponse getEfficiency(Long idOrder) {
        return efficiencyResponseMapper.toResponse(efficiencyServicePort.getEfficiency(idOrder));
    }

    @Override
    public List<EfficiencyResponse> getEfficiencyByRestaurant(Long idRestaurant) {
        return efficiencyResponseMapper.toResponseList(efficiencyServicePort.getEfficiencyByRestaurant(idRestaurant));
    }

    @Override
    public RankingResponse getRankEmployee(Long idEmployee) {
        return rankingResponseMapper.toResponse(efficiencyServicePort.getRankEmployee(idEmployee));
    }
}
