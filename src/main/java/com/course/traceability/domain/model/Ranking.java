package com.course.traceability.domain.model;

import java.time.Duration;

public class Ranking {

    private Long idEmployee;
    private int orderQuantity;
    private Duration durationOrder;
    private String average;

    public Ranking() {
    }

    public Ranking(Long idEmployee, int orderQuantity, Duration durationOrder, String average) {
        this.idEmployee = idEmployee;
        this.orderQuantity = orderQuantity;
        this.durationOrder = durationOrder;
        this.average = average;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Duration getDurationOrder() {
        return durationOrder;
    }

    public void setDurationOrder(Duration durationOrder) {
        this.durationOrder = durationOrder;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
