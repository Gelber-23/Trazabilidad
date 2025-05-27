package com.course.traceability.domain.usecase;

import com.course.traceability.domain.api.ITrackServicePort;
import com.course.traceability.domain.exception.ClientNotExistException;
import com.course.traceability.domain.exception.TrackValidationException;
import com.course.traceability.domain.exception.UserNotClientException;
import com.course.traceability.domain.exception.UserNotEmployeeException;
import com.course.traceability.domain.model.Track;
import com.course.traceability.domain.model.feign.User;
import com.course.traceability.domain.spi.ITrackPersistencePort;
import com.course.traceability.domain.spi.IUtilsPort;
import com.course.traceability.domain.spi.feign.IUserPort;
import com.course.traceability.domain.util.ValidationsConstant;
import com.course.traceability.domain.util.ValuesConstants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TrackUseCase implements ITrackServicePort {

    private final ITrackPersistencePort trackPersistencePort;
    private final IUserPort userPort;
    private final IUtilsPort utilsPort;

    public TrackUseCase(ITrackPersistencePort trackPersistencePort, IUserPort userPort, IUtilsPort utilsPort) {
        this.trackPersistencePort = trackPersistencePort;
        this.userPort = userPort;
        this.utilsPort = utilsPort;
    }

    @Override
    public void createTrack(Track track) {
        validateTrack(track);
        long idUser = utilsPort.getCurrentUserId();

        User employee = userPort.getUserById(idUser);
        if(employee.getRol().getId() != ValuesConstants.ID_ROLE_EMPLOYEE)
            throw new UserNotEmployeeException();

        User client = userPort.getUserById(track.getIdClient());

        if( client == null || client.getId() <= 0)
            throw new ClientNotExistException();

        track.setDate(new Date());
        track.setEmail(client.getEmail());
        track.setEmailEmployee(employee.getEmail());


        trackPersistencePort.createTrack(track);
    }

    @Override
    public List<Track> getTrackOrder(Long idOrder) {
        long idUser = utilsPort.getCurrentUserId();
        User user = userPort.getUserById(idUser);
        if(user.getRol().getId() != ValuesConstants.ID_ROLE_CLIENT)
            throw new UserNotClientException();

        return trackPersistencePort.getTrackOrder(idOrder);
    }

    private void validateTrack(Track track){
        List<String> errors = new ArrayList<>();
        if (track.getIdOrder() == null || track.getIdOrder() <=0 ) {

            errors.add(ValidationsConstant.ID_ORDER_REQUIRED_MESSAGE);
        }

        if (track.getIdClient() == null || track.getIdClient() <= 0) {
            errors.add(ValidationsConstant.ID_CLIENT_REQUIRED_MESSAGE);

        }

        if (track.getIdRestaurant() == null || track.getIdRestaurant() <= 0) {
            errors.add(ValidationsConstant.ID_RESTAURANT_REQUIRED_MESSAGE);

        }
        if (track.getIdEmployee() == null) {
            errors.add(ValidationsConstant.ID_EMPLOYEE_REQUIRED_MESSAGE);

        }
        if (Objects.equals(track.getPreviousState(), "") || track.getPreviousState().isEmpty()) {
            errors.add(ValidationsConstant.PREVIOUS_STATE_REQUIRED_MESSAGE);

        }
        if (Objects.equals(track.getNewState(), "") || track.getNewState().isEmpty()) {
            errors.add(ValidationsConstant.NEW_STATE_REQUIRED_MESSAGE);

        }

        if (!errors.isEmpty()) {
            throw new TrackValidationException(errors);
        }
    }


}
