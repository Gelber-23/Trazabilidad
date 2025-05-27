package com.course.traceability.domain.model;

import java.time.Duration;

public class Efficiency {

    private Long idOrder;
    private Long idEmployee;
    private Duration durationOrder;
    private String durationString;

    public Efficiency() {
    }

    public Efficiency(Long idOrder, Long idEmployee, Duration durationOrder, String durationString) {
        this.idOrder = idOrder;
        this.idEmployee = idEmployee;
        this.durationOrder = durationOrder;
        this.durationString = durationString;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Duration getDurationOrder() {
        return durationOrder;
    }

    public void setDurationOrder(Duration durationOrder) {
        this.durationOrder = durationOrder;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }
}
