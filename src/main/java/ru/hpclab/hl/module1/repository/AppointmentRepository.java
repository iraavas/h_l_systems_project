package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.entity.AppointmentEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    // Найти все приёмы у конкретного врача на указанное время
    List<AppointmentEntity> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

    // Найти все приёмы пациента
    List<AppointmentEntity> findByPatientId(Long patientId);

    // Найти все приёмы врача
    List<AppointmentEntity> findByDoctorId(Long doctorId);

    // Проверяем, есть ли уже запись с нужной специализацией на указанное время
    @Query("SELECT COUNT(a) FROM AppointmentEntity a " +
            "WHERE a.doctor.specialization = :specialization " +
            "AND a.appointmentDate = :appointmentDate")
    Long countAppointmentsForSpecializationAtTime(String specialization, LocalDateTime appointmentDate);

}

