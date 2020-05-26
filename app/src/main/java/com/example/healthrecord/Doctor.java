package com.example.healthrecord;

public class Doctor {

    static final String collectionName = "Doctors";

    private String firstName;
    private String lastName;
    private String address;
    private String mobNumber;
    private String specialization;

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String address, String mobNumber, String specialization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobNumber = mobNumber;
        this.specialization = specialization;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    String getFullName() {
        return firstName + " " + lastName;
    }
}
