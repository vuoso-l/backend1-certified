package exerc.models;

import java.util.Date;

public class Patient {
    private Long id;
    private String lastName;
    private String firstName;
    private int dni;
    private Date admissionDate;
    private Address address;

    public Patient(String lastName, String firstName, int dni, Date admissionDate, Address address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dni = dni;
        this.admissionDate = admissionDate;
        this.address = address;
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
}
