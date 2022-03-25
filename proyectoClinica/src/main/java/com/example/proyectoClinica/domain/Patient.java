package com.example.proyectoClinica.domain;

import java.util.Date;

public class Patient {
    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private int dni;
    private Date admissionDate;
    private Address address;
    private Dentist dentist;

    public Patient(String lastName, String firstName, String email, int dni, Date admissionDate, Address address, Dentist dentist) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.dni = dni;
        this.admissionDate = admissionDate;
        this.address = address;
        this.dentist = dentist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                ", admissionDate=" + admissionDate +
                '}';
    }
}
