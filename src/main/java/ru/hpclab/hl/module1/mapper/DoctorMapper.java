package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.DoctorDTO;
import ru.hpclab.hl.module1.entity.DoctorEntity;

public class DoctorMapper {
    private DoctorMapper() {
    }

    /**
     * Конвертация из DTO в Entity
     */
    public static DoctorEntity toEntity(DoctorDTO dto) {
        if (dto == null) return null;

        return new DoctorEntity(
                dto.getId(),
                dto.getFio(),
                dto.getSpecialization(),
                dto.getWorkSchedule(),
                null // Поле appointments не передаётся в DTO, поэтому ставим null
        );
    }

    /**
     * Конвертация из Entity в DTO
     */
    public static DoctorDTO toDTO(DoctorEntity entity) {
        if (entity == null) return null;

        return new DoctorDTO(
                entity.getId(),
                entity.getFio(),
                entity.getSpecialization(),
                entity.getWorkSchedule()
        );
    }
}
