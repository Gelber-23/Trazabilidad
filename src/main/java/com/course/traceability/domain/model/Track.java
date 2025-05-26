package com.course.traceability.domain.model;

import java.util.Date;

public class Track {
    private String id;
    private Long idOrder;
    private Long idClient;
    private Long idEmployee;
    private String email;
    private Date date;
    private String previousState;
    private String newState;
    private String emailEmployee;

    public Track() {
    }

    public Track(String id, Long idOrder, Long idClient, Long idEmployee, String email, Date date, String previousState, String newState, String emailEmployee) {
        this.id = id;
        this.idOrder = idOrder;
        this.idClient = idClient;
        this.idEmployee = idEmployee;
        this.email = email;
        this.date = date;
        this.previousState = previousState;
        this.newState = newState;
        this.emailEmployee = emailEmployee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPreviousState() {
        return previousState;
    }

    public void setPreviousState(String previousState) {
        this.previousState = previousState;
    }

    public String getNewState() {
        return newState;
    }

    public void setNewState(String newState) {
        this.newState = newState;
    }

    public String getEmailEmployee() {
        return emailEmployee;
    }

    public void setEmailEmployee(String emailEmployee) {
        this.emailEmployee = emailEmployee;
    }
}
