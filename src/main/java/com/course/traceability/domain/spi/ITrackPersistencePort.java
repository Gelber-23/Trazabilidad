package com.course.traceability.domain.spi;

import com.course.traceability.domain.model.Track;

import java.util.List;

public interface ITrackPersistencePort {
    void createTrack (Track track);
    List<Track> getTrackOrder(Long idOrder );
}
