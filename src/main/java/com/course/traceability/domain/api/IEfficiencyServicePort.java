package com.course.traceability.domain.api;

import com.course.traceability.domain.model.Efficiency;
import com.course.traceability.domain.model.Ranking;

import java.util.List;

public interface IEfficiencyServicePort {

    Efficiency getEfficiency (Long idOrder) ;
    List<Efficiency> getEfficiencyByRestaurant (Long idRestaurant);

    Ranking getRankEmployee(Long idEmployee);
}
