package com.course.traceability.domain.usecase;

import com.course.traceability.domain.exception.UserNotOwnerException;
import com.course.traceability.domain.model.Efficiency;
import com.course.traceability.domain.model.Ranking;
import com.course.traceability.domain.model.Track;
import com.course.traceability.domain.model.feign.Restaurant;
import com.course.traceability.domain.model.feign.RoleDto;
import com.course.traceability.domain.model.feign.User;
import com.course.traceability.domain.spi.IEfficiencyPersistencePort;
import com.course.traceability.domain.spi.IUtilsPort;
import com.course.traceability.domain.spi.feign.IRestaurantPort;
import com.course.traceability.domain.spi.feign.IUserPort;
import com.course.traceability.domain.util.ValuesConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EfficiencyUseCaseTest {
    @Mock
    private IEfficiencyPersistencePort efficiencyPersistencePort;

    @Mock
    private IUserPort userPort;

    @Mock
    private IRestaurantPort restaurantPort;

    @Mock
    private IUtilsPort utilsPort;

    @InjectMocks
    private EfficiencyUseCase efficiencyUseCase;

    @Test
    void testGetEfficiency() {
        Track track1 = new Track();
        track1.setIdEmployee(1L);
        track1.setDate(new Date(System.currentTimeMillis() - 30000));
        track1.setPreviousState("PENDING");

        Track track2 = new Track();
        track2.setIdEmployee(1L);
        track2.setDate(new Date());
        track2.setNewState("DELIVERED");

        when(efficiencyPersistencePort.getEfficiencyTracks(1L)).thenReturn(List.of(track1, track2));

        Efficiency result = efficiencyUseCase.getEfficiency(1L);

        assertEquals(1L, result.getIdOrder());
        assertEquals(1L, result.getIdEmployee());
        assertNotNull(result.getDurationOrder());
        assertNotNull(result.getDurationString());
    }

    @Test
    void testGetEfficiencyByRestaurant_Success() {
        when(utilsPort.getCurrentUserId()).thenReturn(10L);
        User user = new User();
        user.setId(10L);
        user.setRol(new RoleDto(ValuesConstants.ID_ROLE_OWNER, "OWNER","Owner"));
        when(userPort.getUserById(10L)).thenReturn(user);

        Restaurant restaurant = new Restaurant();
        restaurant.setId_owner(10L);
        when(restaurantPort.getRestaurantById(5L)).thenReturn(restaurant);

        Track t1 = new Track();
        t1.setIdOrder(1L);
        t1.setIdEmployee(5L);
        t1.setDate(new Date(System.currentTimeMillis() - 100000));
        t1.setPreviousState("PENDING");

        Track t2 = new Track();
        t2.setIdOrder(1L);
        t2.setIdEmployee(5L);
        t2.setDate(new Date());
        t2.setNewState("DELIVERED");

        when(efficiencyPersistencePort.getEfficiencyByRestaurant(5L)).thenReturn(List.of(t1, t2));

        List<Efficiency> result = efficiencyUseCase.getEfficiencyByRestaurant(5L);

        assertEquals(1, result.size());
        assertEquals(1L, result.getFirst().getIdOrder());
    }

    @Test
    void testGetEfficiencyByRestaurant_NotOwner_ThrowsException() {
        when(utilsPort.getCurrentUserId()).thenReturn(11L);
        User user = new User();
        user.setId(11L);
        user.setRol(new RoleDto(ValuesConstants.ID_ROLE_EMPLOYEE,"EMPLOYEE", "EMPLOYEE"));
        when(userPort.getUserById(11L)).thenReturn(user);

        assertThrows(UserNotOwnerException.class, () -> efficiencyUseCase.getEfficiencyByRestaurant(5L));
    }


    @Test
    void testGetRankEmployee_Success() {
        when(utilsPort.getCurrentUserId()).thenReturn(1L);
        User owner = new User();
        owner.setId(1L);
        owner.setRol(new RoleDto(ValuesConstants.ID_ROLE_OWNER, "OWNER" , "OWNER"));

        when(userPort.getUserById(1L)).thenReturn(owner);

        Track t1 = new Track();
        t1.setIdOrder(1L);
        t1.setDate(new Date(System.currentTimeMillis() - 50000));
        t1.setPreviousState("PENDING");

        Track t2 = new Track();
        t2.setIdOrder(1L);
        t2.setDate(new Date());
        t2.setNewState("DELIVERED");

        when(efficiencyPersistencePort.getTrackHistoryByEmployee(5L)).thenReturn(List.of(t1, t2));

        Ranking r = efficiencyUseCase.getRankEmployee(5L);

        assertEquals(5L, r.getIdEmployee());
        assertEquals(1, r.getOrderQuantity());
        assertNotNull(r.getAverage());
    }

    @Test
    void testGetRankEmployee_NotOwner_ThrowsException() {
        when(utilsPort.getCurrentUserId()).thenReturn(1L);
        User user = new User();
        user.setId(1L);
        user.setRol(new RoleDto(ValuesConstants.ID_ROLE_EMPLOYEE, "EMPLOYEE", "EMPLOYEE"));
        when(userPort.getUserById(1L)).thenReturn(user);

        assertThrows(UserNotOwnerException.class, () -> efficiencyUseCase.getRankEmployee(5L));
    }

    @Test
    void testGetAverageTimeByEmployee_EmptyList_ReturnsZero() {
        Duration result = efficiencyUseCase.getAverageTimeByEmployee(Collections.emptyList());
        assertEquals(Duration.ZERO, result);
    }

}