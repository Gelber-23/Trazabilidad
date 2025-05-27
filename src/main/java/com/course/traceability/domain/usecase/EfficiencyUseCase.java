package com.course.traceability.domain.usecase;

import com.course.traceability.domain.api.IEfficiencyServicePort;
import com.course.traceability.domain.exception.UserNotOwnerException;
import com.course.traceability.domain.model.Efficiency;
import com.course.traceability.domain.model.Ranking;
import com.course.traceability.domain.model.Track;
import com.course.traceability.domain.model.feign.Restaurant;
import com.course.traceability.domain.model.feign.User;
import com.course.traceability.domain.spi.IEfficiencyPersistencePort;
import com.course.traceability.domain.spi.IUtilsPort;
import com.course.traceability.domain.spi.feign.IRestaurantPort;
import com.course.traceability.domain.spi.feign.IUserPort;
import com.course.traceability.domain.util.ValuesConstants;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class EfficiencyUseCase implements IEfficiencyServicePort {

    private final IEfficiencyPersistencePort efficiencyPersistencePort;
    private final IUserPort userPort;
    private final IRestaurantPort restaurantPort;
    private final IUtilsPort utilsPort;

    public EfficiencyUseCase(IEfficiencyPersistencePort efficiencyPersistencePort, IUserPort userPort, IRestaurantPort restaurantPort, IUtilsPort utilsPort) {
        this.efficiencyPersistencePort = efficiencyPersistencePort;
        this.userPort = userPort;
        this.restaurantPort = restaurantPort;
        this.utilsPort = utilsPort;
    }

    @Override
    public Efficiency getEfficiency(Long idOrder) {


        List<Track> tracks = efficiencyPersistencePort.getEfficiencyTracks(idOrder);
        Duration duration = calculateDuration(tracks);
        String durationStr = formatDuration(duration);

        Efficiency efficiency = new Efficiency();
        efficiency.setIdOrder(idOrder);
        efficiency.setIdEmployee(tracks.getFirst().getIdEmployee());
        efficiency.setDurationOrder(duration);
        efficiency.setDurationString(durationStr);

        return efficiency;
    }

    @Override
    public List<Efficiency> getEfficiencyByRestaurant(Long idRestaurant) {
        long idUser = utilsPort.getCurrentUserId();

        User owner = userPort.getUserById(idUser);
        if(owner==null || owner.getRol().getId() != ValuesConstants.ID_ROLE_OWNER)
            throw new UserNotOwnerException();

        Restaurant restaurant = restaurantPort.getRestaurantById(idRestaurant);

        if ( owner.getId() != restaurant.getId_owner()){
            throw new UserNotOwnerException();
        }


        List<Track> tracks = efficiencyPersistencePort.getEfficiencyByRestaurant(idRestaurant);


        Map<Long, List<Track>> grouped = tracks.stream()
                .collect(Collectors.groupingBy(Track::getIdOrder));

        return grouped.entrySet().stream()
                .map(entry -> {
                    Long oid = entry.getKey();
                    Duration dur = calculateDuration(entry.getValue());
                    String ds = formatDuration(dur);
                    Efficiency efficiency = new Efficiency();
                    efficiency.setIdOrder(oid);
                    efficiency.setDurationOrder(dur);
                    efficiency.setDurationString(ds);
                    efficiency.setIdEmployee(entry.getValue().getFirst().getIdEmployee());
                    return efficiency;
                })
                .toList();

    }

    @Override
    public Ranking getRankEmployee(Long idEmployee) {
        long idUser = utilsPort.getCurrentUserId();

        User owner = userPort.getUserById(idUser);
        if(owner==null || owner.getRol().getId() != ValuesConstants.ID_ROLE_OWNER)
            throw new UserNotOwnerException();

        List<Track> tracks = efficiencyPersistencePort.getTrackHistoryByEmployee(idEmployee);

        List<Track> tracksFiltered = tracks.stream()
                .filter(t -> "DELIVERED".equals(t.getNewState()))
                .toList();

        int orderQty = (int) tracksFiltered.stream()
                .map(Track::getIdOrder)
                .distinct().count();

        Duration avgDur = getAverageTimeByEmployee(tracks);
        String avgStr = formatDuration(avgDur);

        Ranking r = new Ranking();
        r.setIdEmployee(idEmployee);
        r.setOrderQuantity(orderQty);
        r.setDurationOrder(avgDur);
        r.setAverage(avgStr);

        return r;
    }

    public Duration getAverageTimeByEmployee(List<Track> tracks) {
        Map<Long, List<Track>> byOrder = tracks.stream()
                .collect(Collectors.groupingBy(Track::getIdOrder));
        List<Duration> durations = byOrder.values().stream()
                .map(this::calculateDuration)
                .filter(d -> !d.isZero())
                .toList();
        if (durations.isEmpty()) {
            return Duration.ZERO;
        }
        long totalNanos = durations.stream().mapToLong(Duration::toNanos).sum();
        long avgNanos = totalNanos / durations.size();
        return Duration.ofNanos(avgNanos);
    }

    private Duration calculateDuration(List<Track> tracks) {

        Optional<Instant> start = tracks.stream()
                .filter(t -> "PENDING".equals(t.getPreviousState()))
                .map(Track::getDate)
                .map(Date::toInstant)
                .min(Comparator.naturalOrder());

        Optional<Instant> end = tracks.stream()
                .filter(t -> "DELIVERED".equals(t.getNewState()))
                .map(Track::getDate)
                .map(Date::toInstant)
                .max(Comparator.naturalOrder());

        if (start.isPresent() && end.isPresent()) {
            return Duration.between(start.get(), end.get());
        }
        return Duration.ZERO;
    }

    private String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long secs = duration.toSecondsPart();
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}
