package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.PatientDTO;
import ru.hpclab.hl.module1.entity.PatientEntity;
import ru.hpclab.hl.module1.mapper.PatientMapper;
import ru.hpclab.hl.module1.repository.PatientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Long id) {
        Optional<PatientEntity> patientEntity = patientRepository.findById(id);
        return patientEntity.map(PatientMapper::toDTO).orElse(null);
    }

    public PatientDTO savePatient(PatientDTO patientDTO) {
        PatientEntity entity = PatientMapper.toEntity(patientDTO);
        return PatientMapper.toDTO(patientRepository.save(entity));
    }

    public PatientDTO updatePatient(Long id, PatientDTO newPatientDTO) {
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setFio(newPatientDTO.getFio());
                    existingPatient.setDateOfBirth(newPatientDTO.getDateOfBirth());
                    existingPatient.setInsuranceNumber(newPatientDTO.getInsuranceNumber());
                    return PatientMapper.toDTO(patientRepository.save(existingPatient));
                })
                .orElse(null); // Можно выбрасывать исключение, если пациента нет
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
