package ru.hpclab.hl.module1.model;

import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime appointmentDate;
    private String diagnosis;

    public Appointment(int id, Patient patient, Doctor doctor, LocalDateTime appointmentDate, String diagnosis) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.diagnosis = diagnosis;
    }

    public Appointment() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
}
