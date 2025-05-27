package com.course.traceability.application.handler;

import com.course.traceability.application.dto.response.EfficiencyResponse;
import com.course.traceability.application.dto.response.RankingResponse;

import java.util.List;

public interface IEfficiencyHandler {
    EfficiencyResponse getEfficiency (Long idOrder) ;
    List<EfficiencyResponse> getEfficiencyByRestaurant (Long idRestaurant);
    RankingResponse getRankEmployee ( Long idEmployee);
}
