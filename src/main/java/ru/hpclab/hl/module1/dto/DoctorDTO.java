package ru.hpclab.hl.module1.dto;

public class DoctorDTO {

    private Long id;
    private String fio;
    private String specialization;
    private String workSchedule;

    public DoctorDTO() {
    }

    public DoctorDTO(Long id, String fio, String specialization, String workSchedule) {
        this.id = id;
        this.fio = fio;
        this.specialization = specialization;
        this.workSchedule = workSchedule;
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
}
