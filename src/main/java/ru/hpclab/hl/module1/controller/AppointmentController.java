package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.AppointmentDTO;
import ru.hpclab.hl.module1.service.AppointmentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Получить список всех записей (приёмов)
    @GetMapping
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Получить конкретный приём по ID
    @GetMapping("/{id}")
    public AppointmentDTO getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    // Создать запись на приём
    @PostMapping
    public AppointmentDTO addAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        // В сервисе будет проверка: доступен ли врач (по дате/времени) + совпадает ли специализация, если нужно
        return appointmentService.saveAppointment(appointmentDTO);
    }

    // Удалить запись на приём
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }

    // Обновить запись на приём
    @PutMapping("/{id}")
    public AppointmentDTO updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.updateAppointment(id, appointmentDTO);
    }

    // Новый метод для проверки доступности врача по специализации и времени
    @GetMapping("/check-doctor-availability")
    public boolean checkDoctorAvailability(
            @RequestParam String specialization,
            @RequestParam String appointmentDate) {
        // Парсим строку времени
        LocalDateTime appointmentDateTime = LocalDateTime.parse(appointmentDate);

        // Проверяем доступность врача
        return appointmentService.isDoctorAvailable(specialization, appointmentDateTime);
    }
}
