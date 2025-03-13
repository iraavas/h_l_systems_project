package ru.hpclab.hl.module1.model;

public class Patient {
    private String fullName;
    private String birthDate;
    private String insuranceNumber;

    public Patient(String fullName, String birthDate, String insuranceNumber) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.insuranceNumber = insuranceNumber;
    }

    public Patient() {}

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getInsuranceNumber() { return insuranceNumber; }
    public void setInsuranceNumber(String insuranceNumber) { this.insuranceNumber = insuranceNumber; }
}
