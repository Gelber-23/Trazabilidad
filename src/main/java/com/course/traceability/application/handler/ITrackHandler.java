package com.course.traceability.application.handler;

import com.course.traceability.application.dto.request.TrackRequest;
import com.course.traceability.application.dto.response.TrackResponse;
import com.course.traceability.domain.model.Track;

import java.util.List;

public interface ITrackHandler {
    void createTrack (TrackRequest trackRequest);
    List<TrackResponse> getTrackOrder(Long idOrder );
}
