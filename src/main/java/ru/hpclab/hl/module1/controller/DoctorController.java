package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.DoctorDTO;
import ru.hpclab.hl.module1.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    // Внедрение через конструктор
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Получить список всех врачей
    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Получить одного врача по ID
    @GetMapping("/{id}")
    public DoctorDTO getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    // Создать врача
    @PostMapping
    public DoctorDTO addDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.saveDoctor(doctorDTO);
    }

    // Обновить врача
    @PutMapping("/{id}")
    public DoctorDTO updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        return doctorService.updateDoctor(id, doctorDTO);
    }

    // Удалить врача
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}
