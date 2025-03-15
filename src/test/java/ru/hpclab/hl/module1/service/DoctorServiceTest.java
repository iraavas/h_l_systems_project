package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hpclab.hl.module1.dto.DoctorDTO;
import ru.hpclab.hl.module1.entity.DoctorEntity;
import ru.hpclab.hl.module1.mapper.DoctorMapper;
import ru.hpclab.hl.module1.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    private DoctorEntity doctorEntity;
    private DoctorDTO doctorDTO;

    @BeforeEach
    void setUp() {
        doctorEntity = new DoctorEntity(1L, "Доктор Айболит", "Терапевт", "9:00 - 18:00", null);
        doctorDTO = DoctorMapper.toDTO(doctorEntity);
    }

    @Test
    void getAllDoctors_shouldReturnDoctorsList() {
        when(doctorRepository.findAll()).thenReturn(List.of(doctorEntity));

        List<DoctorDTO> doctors = doctorService.getAllDoctors();

        assertNotNull(doctors);
        assertEquals(1, doctors.size());
        assertEquals("Доктор Айболит", doctors.get(0).getFio());
    }
}
