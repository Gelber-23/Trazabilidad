package com.course.traceability.application.mapper.response;

import com.course.traceability.application.dto.response.EfficiencyResponse;
import com.course.traceability.domain.model.Efficiency;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IEfficiencyResponseMapper {
    EfficiencyResponse toResponse (Efficiency efficiency);
    List<EfficiencyResponse> toResponseList(List<Efficiency> efficiencyList);
}
