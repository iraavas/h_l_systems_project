package ru.hpclab.hl.module1.controller;

import ru.hpclab.hl.module1.model.Patient;
import ru.hpclab.hl.module1.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{insuranceNumber}")
    public Patient getPatientByInsurance(@PathVariable String insuranceNumber) {
        return patientService.getPatientByInsurance(insuranceNumber);
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @DeleteMapping("/{insuranceNumber}")
    public void deletePatient(@PathVariable String insuranceNumber) {
        patientService.deletePatient(insuranceNumber);
    }

    @PutMapping("/{insuranceNumber}")
    public Patient updatePatient(@PathVariable String insuranceNumber, @RequestBody Patient updatedPatient) {
        return patientService.updatePatient(insuranceNumber, updatedPatient);
    }
}
