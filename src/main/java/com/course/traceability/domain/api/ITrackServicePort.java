package com.course.traceability.domain.api;

import com.course.traceability.domain.model.Track;

import java.util.List;

public interface ITrackServicePort {
    void createTrack (Track track);
    List<Track> getTrackOrder(Long idOrder );
}
