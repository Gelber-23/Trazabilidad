package com.course.traceability.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class RankingResponse {
    private Long idEmployee;
    private int orderQuantity;
    private Duration durationOrder;
    private String durationString;
}
