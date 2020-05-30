package com.example.healthrecord;

import java.util.List;

public class Patient {

    static final String collectionName = "Patients";

    private String firstName;
    private String lastName;
    private String address;
    private String mobNumber;
    private boolean isStudent;
    private double height;
    private double weight;

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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    String getFullName() {
        return firstName + " " + lastName;
    }

    double getBMI(){
        double temp = height/100;
        return weight/(temp*temp);
    }
}
