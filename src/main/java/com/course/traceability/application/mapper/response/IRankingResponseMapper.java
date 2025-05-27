package com.course.traceability.application.mapper.response;

import com.course.traceability.application.dto.response.RankingResponse;
import com.course.traceability.domain.model.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRankingResponseMapper {
    RankingResponse toResponse (Ranking ranking);
}
