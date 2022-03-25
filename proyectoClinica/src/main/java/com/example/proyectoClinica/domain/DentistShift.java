package com.example.proyectoClinica.domain;

import java.util.Date;

public class DentistShift {
    private Long id;
    private Date date;
    private Patient patient;
    private Dentist dentist;

    public DentistShift(Date date, Patient patient, Dentist dentist) {
        this.date = date;
        this.patient = patient;
        this.dentist = dentist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    @Override
    public String toString() {
        return "DentistShift{" +
                "id=" + id +
                ", date=" + date +
                ", patient=" + patient +
                ", dentist=" + dentist +
                '}';
    }
}
