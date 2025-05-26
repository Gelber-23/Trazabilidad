package com.course.traceability.application.handler.impl;

import com.course.traceability.application.dto.request.TrackRequest;
import com.course.traceability.application.dto.response.TrackResponse;
import com.course.traceability.application.handler.ITrackHandler;
import com.course.traceability.application.mapper.request.ITrackRequestMapper;
import com.course.traceability.application.mapper.response.ITrackResponseMapper;
import com.course.traceability.domain.api.ITrackServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TrackHandler implements ITrackHandler {

    private final ITrackServicePort trackServicePort;
    private final ITrackRequestMapper trackRequestMapper;
    private final ITrackResponseMapper trackResponseMapper;

    @Override
    public void createTrack(TrackRequest trackRequest) {
        trackServicePort.createTrack(trackRequestMapper.toTrack(trackRequest));
    }

    @Override
    public List<TrackResponse> getTrackOrder(Long idOrder) {
        return trackResponseMapper.toResponseList(trackServicePort.getTrackOrder(idOrder));
    }
}
