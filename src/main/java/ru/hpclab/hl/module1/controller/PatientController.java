package ru.hpclab.hl.module1.controller;

import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.dto.PatientDTO;
import ru.hpclab.hl.module1.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    // Внедрение через конструктор
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Получить список всех пациентов
    @GetMapping
    public List<PatientDTO> getAllPatients() {
        return patientService.getAllPatients();
        // Возвращаем список DTO, аналогично вашему .stream().map(...) в примере
    }

    // Получить одного пациента по ID
    @GetMapping("/{id}")
    public PatientDTO getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // Создать пациента
    @PostMapping
    public PatientDTO addPatient(@RequestBody PatientDTO patientDTO) {
        return patientService.savePatient(patientDTO);
    }

    // Обновить данные пациента
    @PutMapping("/{id}")
    public PatientDTO updatePatient(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
        return patientService.updatePatient(id, patientDTO);
    }

    // Удалить пациента
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
}
