package com.course.traceability.domain.usecase;

import com.course.traceability.domain.exception.ClientNotExistException;
import com.course.traceability.domain.exception.TrackValidationException;
import com.course.traceability.domain.exception.UserNotClientException;
import com.course.traceability.domain.exception.UserNotEmployeeException;
import com.course.traceability.domain.model.Track;
import com.course.traceability.domain.model.feign.RoleDto;
import com.course.traceability.domain.model.feign.User;
import com.course.traceability.domain.spi.ITrackPersistencePort;
import com.course.traceability.domain.spi.IUtilsPort;
import com.course.traceability.domain.spi.feign.IUserPort;
import com.course.traceability.domain.util.ValuesConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TrackUseCaseTest {
    @Mock
    private ITrackPersistencePort trackPersistencePort;

    @Mock
    private IUserPort userPort;

    @Mock
    private IUtilsPort utilsPort;

    @InjectMocks
    private TrackUseCase trackUseCase;

    private Track createValidTrack() {
        Track track = new Track();
        track.setIdOrder(1L);
        track.setIdClient(2L);
        track.setIdRestaurant(3L);
        track.setIdEmployee(4L);
        track.setPreviousState("PREVIOUS");
        track.setNewState("NEW");
        return track;
    }

    private User createUser(long id, int roleId, String email) {
        RoleDto role = new RoleDto();
        role.setId(roleId);
        User user = new User();
        user.setId(id);
        user.setRol(role);
        user.setEmail(email);
        return user;
    }

    @Test
    void testCreateTrackSuccess() {
        Track track = createValidTrack();

        Mockito.when(utilsPort.getCurrentUserId()).thenReturn(4L);
        Mockito.when(userPort.getUserById(4L)).thenReturn(createUser(4L, ValuesConstants.ID_ROLE_EMPLOYEE, "emp@example.com"));
        Mockito.when(userPort.getUserById(2L)).thenReturn(createUser(2L, ValuesConstants.ID_ROLE_CLIENT, "client@example.com"));

        trackUseCase.createTrack(track);

        Mockito.verify(trackPersistencePort).createTrack(Mockito.any(Track.class));
    }

    @Test
    void testCreateTrackThrowsWhenNotEmployee() {
        Track track = createValidTrack();

        Mockito.when(utilsPort.getCurrentUserId()).thenReturn(4L);
        Mockito.when(userPort.getUserById(4L)).thenReturn(createUser(4L, 999, "emp@example.com")); // not employee

        Assertions.assertThrows(UserNotEmployeeException.class, () -> trackUseCase.createTrack(track));
    }

    @Test
    void testCreateTrackThrowsWhenClientIsNull() {
        Track track = createValidTrack();

        Mockito.when(utilsPort.getCurrentUserId()).thenReturn(4L);
        Mockito.when(userPort.getUserById(4L)).thenReturn(createUser(4L, ValuesConstants.ID_ROLE_EMPLOYEE, "emp@example.com"));
        Mockito.when(userPort.getUserById(2L)).thenReturn(null);

        Assertions.assertThrows(ClientNotExistException.class, () -> trackUseCase.createTrack(track));
    }

    @Test
    void testCreateTrackThrowsWhenClientIdInvalid() {
        Track track = createValidTrack();
        track.setIdClient(0L);

        Assertions.assertThrows(TrackValidationException.class, () -> trackUseCase.createTrack(track));
    }

    @Test
    void testGetTrackOrderSuccess() {
        Mockito.when(utilsPort.getCurrentUserId()).thenReturn(2L);
        Mockito.when(userPort.getUserById(2L)).thenReturn(createUser(2L, ValuesConstants.ID_ROLE_CLIENT, "client@example.com"));

        List<Track> expectedList = Collections.singletonList(createValidTrack());
        Mockito.when(trackPersistencePort.getTrackOrder(1L)).thenReturn(expectedList);

        List<Track> result = trackUseCase.getTrackOrder(1L);
        Assertions.assertEquals(expectedList, result);
    }

    @Test
    void testGetTrackOrderThrowsWhenUserIsNotClient() {
        Mockito.when(utilsPort.getCurrentUserId()).thenReturn(2L);
        Mockito.when(userPort.getUserById(2L)).thenReturn(createUser(2L, ValuesConstants.ID_ROLE_EMPLOYEE, "client@example.com"));

        Assertions.assertThrows(UserNotClientException.class, () -> trackUseCase.getTrackOrder(1L));
    }

    @Test
    void testValidationErrors_AllFieldsInvalid() {
        Track track = new Track();
        track.setPreviousState("");
        track.setNewState("");

        TrackValidationException exception = Assertions.assertThrows(TrackValidationException.class, () -> {
            trackUseCase.createTrack(track);
        });

        Assertions.assertTrue(exception.getErrors().size() >= 6);
    }

}