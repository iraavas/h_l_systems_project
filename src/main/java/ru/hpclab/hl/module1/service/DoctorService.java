package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.Doctor;
import ru.hpclab.hl.module1.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private static final List<Doctor> doctors = new ArrayList<>();

    @Autowired
    private DoctorRepository doctorRepository;

    // 1. Получить всех врачей
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    // 2. Найти врача по имени
    public Doctor getDoctorByName(String fullName) {
        return doctors.stream()
                .filter(doctor -> doctor.getFullName().equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }

    // 3. Добавить нового врача
    public Doctor saveDoctor(Doctor doctor) {
        // Проверяем, нет ли уже врача с таким именем
        if (getDoctorByName(doctor.getFullName()) != null) {
            throw new IllegalArgumentException("Врач с таким именем уже существует!");
        }
        doctors.add(doctor);
        return doctor;
    }

    // 4. Удалить врача по имени
    public void deleteDoctor(String fullName) {
        doctors.removeIf(doctor -> doctor.getFullName().equalsIgnoreCase(fullName));
    }

    // 5. Обновить данные врача
    public Doctor updateDoctor(String fullName, Doctor updatedDoctor) {
        for (Doctor doctor : doctors) {
            if (doctor.getFullName().equalsIgnoreCase(fullName)) {
                doctor.setSpecialization(updatedDoctor.getSpecialization());
                doctor.setSchedule(updatedDoctor.getSchedule());
                return doctor;
            }
        }
        return null;
    }
}
