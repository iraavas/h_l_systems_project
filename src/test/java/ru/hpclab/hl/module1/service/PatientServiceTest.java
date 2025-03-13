package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hpclab.hl.module1.dto.PatientDTO;
import ru.hpclab.hl.module1.entity.PatientEntity;
import ru.hpclab.hl.module1.mapper.PatientMapper;
import ru.hpclab.hl.module1.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private PatientEntity patientEntity;
    private PatientDTO patientDTO;

    @BeforeEach
    void setUp() {
        patientEntity = new PatientEntity(1L, "Иван Иванов", LocalDate.of(1990, 1, 1), "1234567890", null);
        patientDTO = PatientMapper.toDTO(patientEntity);
    }

    @Test
    void getAllPatients_shouldReturnPatientsList() {
        when(patientRepository.findAll()).thenReturn(List.of(patientEntity));

        List<PatientDTO> patients = patientService.getAllPatients();

        assertNotNull(patients);
        assertEquals(1, patients.size());
        assertEquals("Иван Иванов", patients.get(0).getFio());
    }

    @Test
    void getPatientById_shouldReturnPatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patientEntity));

        PatientDTO patient = patientService.getPatientById(1L);

        assertNotNull(patient);
        assertEquals("Иван Иванов", patient.getFio());
    }
}
