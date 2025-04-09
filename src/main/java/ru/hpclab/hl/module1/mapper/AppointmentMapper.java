package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.AppointmentDTO;
import ru.hpclab.hl.module1.entity.AppointmentEntity;
import ru.hpclab.hl.module1.entity.DoctorEntity;
import ru.hpclab.hl.module1.entity.PatientEntity;
import ru.hpclab.hl.module1.model.AppointmentStatus;

public class AppointmentMapper {
    private AppointmentMapper() {
    }

    /**
     * Конвертация из DTO в Entity
     * Требует `PatientEntity` и `DoctorEntity`, так как в DTO хранятся только их ID.
     */
    public static AppointmentEntity toEntity(AppointmentDTO dto, PatientEntity patient, DoctorEntity doctor) {
        if (dto == null) return null;

        return new AppointmentEntity(
                dto.getId(),
                patient,
                doctor,
                dto.getAppointmentDate(),
                dto.getDiagnosis(),
                AppointmentStatus.SCHEDULED // По умолчанию устанавливаем статус "Запланировано"
        );
    }

    /**
     * Конвертация из Entity в DTO
     */
    public static AppointmentDTO toDTO(AppointmentEntity entity) {
        if (entity == null) return null;

        return new AppointmentDTO(
                entity.getId(),
                entity.getPatient().getId(),
                entity.getDoctor().getId(),
                entity.getAppointmentDate(),
                entity.getDiagnosis(),
                entity.getDoctor().getSpecialization() // Добавляем специализацию в DTO
        );
    }
}
