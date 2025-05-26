package com.course.traceability.domain.model.feign;

import java.util.Date;

public class User {
    private long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String phone;
    private Date birthdate;
    private String email;
    private String password;
    private RoleDto rol;
    private Long idRestaurant;

    public User() {
    }

    public User(long id, String name, String lastName, String documentNumber, String phone, Date birthdate, String email, String password, RoleDto rol, Long idRestaurant) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.idRestaurant = idRestaurant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDto getRol() {
        return rol;
    }

    public void setRol(RoleDto rol) {
        this.rol = rol;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
