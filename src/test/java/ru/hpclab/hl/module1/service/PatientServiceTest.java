
package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import ru.hpclab.hl.module1.dto.PatientDTO;
import ru.hpclab.hl.module1.entity.PatientEntity;
import ru.hpclab.hl.module1.mapper.PatientMapper;
import ru.hpclab.hl.module1.repository.PatientRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    private PatientRepository patientRepository;
    private PatientService patientService;

    @BeforeEach
    void setUp() {
        patientRepository = mock(PatientRepository.class);
        patientService = new PatientService(patientRepository);
    }

    @Test
    void testGetAllPatients() {
        PatientEntity patient1 = new PatientEntity();
        patient1.setId(1L);
        patient1.setFio("Иванов Иван Иванович");
        patient1.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient1.setInsuranceNumber("1234 567890");

        PatientEntity patient2 = new PatientEntity();
        patient2.setId(2L);
        patient2.setFio("Петров Петр Петрович");
        patient2.setDateOfBirth(LocalDate.of(1985, 5, 10));
        patient2.setInsuranceNumber("2345 678901");

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        List<PatientDTO> result = patientService.getAllPatients();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getFio()).isEqualTo("Иванов Иван Иванович");
        assertThat(result.get(1).getFio()).isEqualTo("Петров Петр Петрович");
    }

    @Test
    void testGetPatientById() {
        PatientEntity patient = new PatientEntity();
        patient.setId(1L);
        patient.setFio("Иванов Иван Иванович");
        patient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        patient.setInsuranceNumber("1234 567890");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO result = patientService.getPatientById(1L);

        assertThat(result).isNotNull();
        assertThat(result.getFio()).isEqualTo("Иванов Иван Иванович");
    }

    @Test
    void testSavePatient() {
        PatientDTO dto = new PatientDTO();
        dto.setFio("Новый Пациент");
        dto.setDateOfBirth(LocalDate.of(2000, 1, 1));
        dto.setInsuranceNumber("0000 111111");

        PatientEntity savedEntity = new PatientEntity();
        savedEntity.setId(100L);
        savedEntity.setFio(dto.getFio());
        savedEntity.setDateOfBirth(dto.getDateOfBirth());
        savedEntity.setInsuranceNumber(dto.getInsuranceNumber());

        when(patientRepository.save(any(PatientEntity.class))).thenReturn(savedEntity);

        PatientDTO result = patientService.savePatient(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(100L);
        assertThat(result.getFio()).isEqualTo("Новый Пациент");
    }

    @Test
    void testUpdatePatient() {
        PatientEntity existing = new PatientEntity();
        existing.setId(1L);
        existing.setFio("Старое ФИО");
        existing.setDateOfBirth(LocalDate.of(1980, 1, 1));
        existing.setInsuranceNumber("1111 111111");

        PatientDTO updatedDTO = new PatientDTO();
        updatedDTO.setFio("Новое ФИО");
        updatedDTO.setDateOfBirth(LocalDate.of(1995, 5, 5));
        updatedDTO.setInsuranceNumber("2222 222222");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any(PatientEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        PatientDTO result = patientService.updatePatient(1L, updatedDTO);

        assertThat(result).isNotNull();
        assertThat(result.getFio()).isEqualTo("Новое ФИО");
        assertThat(result.getInsuranceNumber()).isEqualTo("2222 222222");
    }

    @Test
    void testDeletePatient() {
        patientService.deletePatient(1L);
        verify(patientRepository, times(1)).deleteById(1L);
    }
}
