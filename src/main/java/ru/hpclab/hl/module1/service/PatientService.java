package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Patient;
import ru.hpclab.hl.module1.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private static final List<Patient> patients = new ArrayList<>();

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Patient getPatientByInsurance(String insuranceNumber) {
        return patients.stream()
                .filter(patient -> patient.getInsuranceNumber().equals(insuranceNumber))
                .findFirst()
                .orElse(null);
    }

    public Patient savePatient(Patient patient) {
        patients.add(patient);
        return patient;
    }

    public void deletePatient(String insuranceNumber) {
        patients.removeIf(patient -> patient.getInsuranceNumber().equals(insuranceNumber));
    }

    public Patient updatePatient(String insuranceNumber, Patient updatedPatient) {
        Optional<Patient> patientOptional = patients.stream()
                .filter(patient -> patient.getInsuranceNumber().equals(insuranceNumber))
                .findFirst();
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            patient.setFullName(updatedPatient.getFullName());
            patient.setBirthDate(updatedPatient.getBirthDate());
            return patient;
        }
        return null;
    }
}
