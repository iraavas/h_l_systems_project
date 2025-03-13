package ru.hpclab.hl.module1.dto;

import java.time.LocalDate;

public class PatientDTO {

    private Long id;
    private String fio;
    private LocalDate dateOfBirth;
    private String insuranceNumber;

    public PatientDTO() {
    }

    public PatientDTO(Long id, String fio, LocalDate dateOfBirth, String insuranceNumber) {
        this.id = id;
        this.fio = fio;
        this.dateOfBirth = dateOfBirth;
        this.insuranceNumber = insuranceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}
