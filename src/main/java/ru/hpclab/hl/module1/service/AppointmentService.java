package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.controller.exception.DoctorException;
import ru.hpclab.hl.module1.dto.AppointmentDTO;
import ru.hpclab.hl.module1.entity.AppointmentEntity;
import ru.hpclab.hl.module1.entity.DoctorEntity;
import ru.hpclab.hl.module1.entity.PatientEntity;
import ru.hpclab.hl.module1.mapper.AppointmentMapper;
import ru.hpclab.hl.module1.repository.AppointmentRepository;
import ru.hpclab.hl.module1.repository.DoctorRepository;
import ru.hpclab.hl.module1.repository.PatientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(AppointmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AppointmentDTO getAppointmentById(Long id) {
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findById(id);
        return appointmentEntity.map(AppointmentMapper::toDTO).orElse(null);
    }

    public AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO) {
        // Проверяем, существует ли пациент
        PatientEntity patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Пациент не найден"));

        // Проверяем, существует ли доктор
        DoctorEntity doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Доктор не найден"));

        // Проверяем, доступен ли врач по специализации на указанное время
        Long count = appointmentRepository.countAppointmentsForSpecializationAtTime(
                appointmentDTO.getSpecialization(), appointmentDTO.getAppointmentDate());
        if (count > 0) {
            throw new DoctorException("Врач с специализацией " + appointmentDTO.getSpecialization() +
                    " уже занят в это время: " + appointmentDTO.getAppointmentDate());
        }

        // Создаём запись
        AppointmentEntity appointmentEntity = AppointmentMapper.toEntity(appointmentDTO, patient, doctor);
        return AppointmentMapper.toDTO(appointmentRepository.save(appointmentEntity));
    }

    public AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO) {
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    // Проверяем, существует ли пациент
                    PatientEntity patient = patientRepository.findById(appointmentDTO.getPatientId())
                            .orElseThrow(() -> new RuntimeException("Пациент не найден"));

                    // Проверяем, существует ли доктор
                    DoctorEntity doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
                            .orElseThrow(() -> new RuntimeException("Доктор не найден"));

                    existingAppointment.setPatient(patient);
                    existingAppointment.setDoctor(doctor);
                    existingAppointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
                    existingAppointment.setDiagnosis(appointmentDTO.getDiagnosis());

                    return AppointmentMapper.toDTO(appointmentRepository.save(existingAppointment));
                })
                .orElse(null);
    }

    public boolean isDoctorAvailable(String specialization, LocalDateTime appointmentDate) {
        // Проверяем, доступен ли врач с нужной специализацией на указанное время
        Long count = appointmentRepository.countAppointmentsForSpecializationAtTime(specialization, appointmentDate);
        return count == 0; // если count > 0, значит врач занят
    }




    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
