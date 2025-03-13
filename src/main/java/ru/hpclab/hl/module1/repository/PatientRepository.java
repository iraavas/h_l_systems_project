package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientRepository {
    private static final List<Patient> patients = new ArrayList<>();

    public List<Patient> findAll() {
        return patients;
    }
}
