package com.course.traceability.domain.spi;


import com.course.traceability.domain.model.Track;

import java.util.List;

public interface IEfficiencyPersistencePort {
    List<Track> getEfficiencyTracks (Long idOrder) ;
    List<Track>  getEfficiencyByRestaurant (Long idRestaurant);

    List<Track> getTrackHistoryByEmployee(Long idEmployee);
}
