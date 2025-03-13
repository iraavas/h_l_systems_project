package ru.hpclab.hl.module1.controller;

import ru.hpclab.hl.module1.model.Appointment;
import ru.hpclab.hl.module1.model.Doctor;
import ru.hpclab.hl.module1.service.AppointmentService;
import ru.hpclab.hl.module1.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
    }

    // 1. Получить все записи на прием
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // 2. Получить конкретную запись на прием по ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable int id) {
        return appointmentService.getAppointmentById(id);
    }

    // 3. Проверить доступность врача перед записью
    @GetMapping("/check-availability")
    public boolean checkDoctorAvailability(@RequestParam String doctorName, @RequestParam String dateTime) {
        Doctor doctor = doctorService.getDoctorByName(doctorName);
        if (doctor == null) {
            throw new IllegalArgumentException("Врач с таким именем не найден!");
        }
        return appointmentService.isDoctorAvailable(doctor, LocalDateTime.parse(dateTime));
    }

    // 4. Создать новую запись на прием (с проверкой врача)
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    // 5. Обновить существующую запись на прием
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable int id, @RequestBody Appointment updatedAppointment) {
        return appointmentService.updateAppointment(id, updatedAppointment);
    }

    // 6. Удалить запись на прием
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable int id) {
        appointmentService.deleteAppointment(id);
    }
}
