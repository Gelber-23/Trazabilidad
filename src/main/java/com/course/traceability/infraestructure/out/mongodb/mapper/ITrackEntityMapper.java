package com.course.traceability.infraestructure.out.mongodb.mapper;

import com.course.traceability.domain.model.Track;
import com.course.traceability.infraestructure.out.mongodb.entity.TrackEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE )
public interface ITrackEntityMapper {
    TrackEntity toEntity(Track track);
    Track toModel(TrackEntity trackEntity);
    List<Track> toModelList(List<TrackEntity> trackEntityList);


}
