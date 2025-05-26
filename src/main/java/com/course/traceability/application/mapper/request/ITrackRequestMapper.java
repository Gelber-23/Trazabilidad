package com.course.traceability.application.mapper.request;

import com.course.traceability.application.dto.request.TrackRequest;
import com.course.traceability.domain.model.Track;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrackRequestMapper {
 Track toTrack (TrackRequest trackRequest);
}
