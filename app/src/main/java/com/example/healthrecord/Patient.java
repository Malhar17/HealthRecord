package com.example.healthrecord;

import java.util.List;

public class Patient {

    static final String collectionName = "Patients";

    private String firstName;
    private String lastName;
    private String address;
    private String mobNumber;
    private boolean isStudent;

    public Patient() {
    }

    Patient(String firstName, String lastName, String address, String mobNumber, boolean isStudent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.mobNumber = mobNumber;
        this.isStudent = isStudent;
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

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    String getFullName() {
        return firstName + " " + lastName;
    }
}
