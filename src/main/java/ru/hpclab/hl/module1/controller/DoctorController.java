package ru.hpclab.hl.module1.controller;

import ru.hpclab.hl.module1.model.Doctor;
import ru.hpclab.hl.module1.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{fullName}")
    public Doctor getDoctorByName(@PathVariable String fullName) {
        return doctorService.getDoctorByName(fullName);
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    @DeleteMapping("/{fullName}")
    public void deleteDoctor(@PathVariable String fullName) {
        doctorService.deleteDoctor(fullName);
    }

    @PutMapping("/{fullName}")
    public Doctor updateDoctor(@PathVariable String fullName, @RequestBody Doctor updatedDoctor) {
        return doctorService.updateDoctor(fullName, updatedDoctor);
    }
}
