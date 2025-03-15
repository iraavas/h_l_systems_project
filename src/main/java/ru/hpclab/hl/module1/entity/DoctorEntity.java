package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fio; // ФИО врача

    @Column(nullable = false)
    private String specialization; // Специализация врача

    @Column(name = "work_schedule")
    private String workSchedule; // График работы

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AppointmentEntity> appointments; // Связь с приёмами

    public DoctorEntity() {
    }

    public DoctorEntity(Long id, String fio, String specialization, String workSchedule, List<AppointmentEntity> appointments) {
        this.id = id;
        this.fio = fio;
        this.specialization = specialization;
        this.workSchedule = workSchedule;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(String workSchedule) {
        this.workSchedule = workSchedule;
    }

    public List<AppointmentEntity> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentEntity> appointments) {
        this.appointments = appointments;
    }
}
