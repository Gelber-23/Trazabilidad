package com.course.traceability.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TrackResponse {
    private String id;
    private Long idOrder;
    private Long idClient;
    private Long idEmployee;
    private String email;
    private Date date;
    private String previousState;
    private String newState;
    private String emailEmployee;
}
