package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fio; // ФИО пациента

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth; // Дата рождения

    @Column(name = "insurance_number", nullable = false, unique = true)
    private String insuranceNumber; // Номер страхового полиса

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AppointmentEntity> appointments; // Связь с приёмами

    public PatientEntity() {
    }

    public PatientEntity(Long id, String fio, LocalDate dateOfBirth, String insuranceNumber, List<AppointmentEntity> appointments) {
        this.id = id;
        this.fio = fio;
        this.dateOfBirth = dateOfBirth;
        this.insuranceNumber = insuranceNumber;
        this.appointments = appointments;
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

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}
