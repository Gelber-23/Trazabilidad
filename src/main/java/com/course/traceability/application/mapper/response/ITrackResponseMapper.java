package com.course.traceability.application.mapper.response;

import com.course.traceability.application.dto.response.TrackResponse;
import com.course.traceability.domain.model.Track;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITrackResponseMapper {
    TrackResponse toResponse (Track track);
    List<TrackResponse> toResponseList(List<Track> trackList);
}
