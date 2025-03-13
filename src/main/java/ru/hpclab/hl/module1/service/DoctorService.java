package ru.hpclab.hl.module1.service;

import org.springframework.stereotype.Service;
import ru.hpclab.hl.module1.dto.DoctorDTO;
import ru.hpclab.hl.module1.entity.DoctorEntity;
import ru.hpclab.hl.module1.mapper.DoctorMapper;
import ru.hpclab.hl.module1.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(DoctorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        Optional<DoctorEntity> doctorEntity = doctorRepository.findById(id);
        return doctorEntity.map(DoctorMapper::toDTO).orElse(null);
    }

    public DoctorDTO saveDoctor(DoctorDTO doctorDTO) {
        DoctorEntity entity = DoctorMapper.toEntity(doctorDTO);
        return DoctorMapper.toDTO(doctorRepository.save(entity));
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO newDoctorDTO) {
        return doctorRepository.findById(id)
                .map(existingDoctor -> {
                    existingDoctor.setFio(newDoctorDTO.getFio());
                    existingDoctor.setSpecialization(newDoctorDTO.getSpecialization());
                    existingDoctor.setWorkSchedule(newDoctorDTO.getWorkSchedule());
                    return DoctorMapper.toDTO(doctorRepository.save(existingDoctor));
                })
                .orElse(null);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
