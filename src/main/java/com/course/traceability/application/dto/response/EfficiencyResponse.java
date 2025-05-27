package com.course.traceability.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class EfficiencyResponse {
    private Long idOrder;
    private Long idEmployee;
    private Duration durationOrder;
    private String durationString;

}
