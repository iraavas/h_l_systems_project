package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Appointment;
import ru.hpclab.hl.module1.model.Doctor;
import ru.hpclab.hl.module1.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private static final List<Appointment> appointments = new ArrayList<>();

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 1. Получить все записи на прием
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // 2. Получить запись на прием по ID
    public Appointment getAppointmentById(int id) {
        return appointments.stream()
                .filter(appointment -> appointment.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // 3. Проверить доступность врача
    public boolean isDoctorAvailable(Doctor doctor, LocalDateTime appointmentDateTime) {
        String appointmentTime = appointmentDateTime.toLocalTime().toString();

        // Проверяем, входит ли время в расписание врача
        boolean withinSchedule = doctor.getSchedule().contains(appointmentTime);

        // Проверяем, не занят ли врач в это время
        boolean isFree = appointments.stream()
                .noneMatch(a -> a.getDoctor().equals(doctor) && a.getAppointmentDate().equals(appointmentDateTime));

        return withinSchedule && isFree;
    }

    // 4. Создать новую запись на прием (с проверкой доступности врача)
    public Appointment saveAppointment(Appointment appointment) {
        if (!isDoctorAvailable(appointment.getDoctor(), appointment.getAppointmentDate())) {
            throw new IllegalArgumentException("Врач недоступен в это время!");
        }

        appointment.setId(appointments.size() + 1);
        appointments.add(appointment);
        return appointment;
    }

    // 5. Обновить существующую запись на прием
    public Appointment updateAppointment(int id, Appointment updatedAppointment) {
        Optional<Appointment> appointmentOptional = appointments.stream()
                .filter(appointment -> appointment.getId() == id)
                .findFirst();

        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            if (!isDoctorAvailable(updatedAppointment.getDoctor(), updatedAppointment.getAppointmentDate())) {
                throw new IllegalArgumentException("Врач недоступен в это время!");
            }
            appointment.setPatient(updatedAppointment.getPatient());
            appointment.setDoctor(updatedAppointment.getDoctor());
            appointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
            appointment.setDiagnosis(updatedAppointment.getDiagnosis());
            return appointment;
        }
        return null;
    }

    // 6. Удалить запись на прием
    public void deleteAppointment(int id) {
        appointments.removeIf(appointment -> appointment.getId() == id);
    }
}
