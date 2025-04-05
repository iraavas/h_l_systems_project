package ru.hpclab.hl.module1.mapper;

import ru.hpclab.hl.module1.dto.PatientDTO;
import ru.hpclab.hl.module1.entity.PatientEntity;

public class PatientMapper {
    private PatientMapper() {
    }

    /**
     * Конвертация из DTO в Entity
     */
    public static PatientEntity toEntity(PatientDTO dto) {
        if (dto == null) return null;

        return new PatientEntity(
                dto.getId(),
                dto.getFio(),
                dto.getDateOfBirth(),
                dto.getInsuranceNumber(),
                null // Поле appointments не передаётся в DTO, поэтому ставим null
        );
    }

    /**
     * Конвертация из Entity в DTO
     */
    public static PatientDTO toDTO(PatientEntity entity) {
        if (entity == null) return null;

        return new PatientDTO(
                entity.getId(),
                entity.getFio(),
                entity.getDateOfBirth(),
                entity.getInsuranceNumber()
        );
    }
}
