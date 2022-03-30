package com.example.proyectoClinica.domain;

public class Address {
    private Long id;
    private String street;
    private String locality;
    private int number;
    private String province;

    public Address(String street, String locality, int number, String province) {
        this.street = street;
        this.locality = locality;
        this.number = number;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", locality='" + locality + '\'' +
                ", number=" + number +
                ", province='" + province + '\'' +
                '}';
    }
}
