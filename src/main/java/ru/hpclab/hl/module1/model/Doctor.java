package ru.hpclab.hl.module1.model;

import java.util.List;

public class Doctor {
    private String fullName;
    private String specialization;
    private List<String> schedule; // Время работы врача (например, ["09:00", "10:00", "11:00"])

    public Doctor(String fullName, String specialization, List<String> schedule) {
        this.fullName = fullName;
        this.specialization = specialization;
        this.schedule = schedule;
    }

    public Doctor() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    // Новый метод: Проверка доступности врача по времени
    public boolean isAvailableAt(String appointmentTime) {
        return schedule.contains(appointmentTime);
    }
}
