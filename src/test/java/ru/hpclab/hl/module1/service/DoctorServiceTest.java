package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.hpclab.hl.module1.dto.DoctorDTO;
import ru.hpclab.hl.module1.entity.DoctorEntity;
import ru.hpclab.hl.module1.repository.AppointmentRepository;
import ru.hpclab.hl.module1.repository.DoctorRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DoctorServiceTest {

    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        doctorRepository = mock(DoctorRepository.class);
        appointmentRepository = mock(AppointmentRepository.class);
        doctorService = new DoctorService(doctorRepository, appointmentRepository);
    }

    @Test
    void shouldReturnAllDoctors() {
        // Создаем doctor1
        DoctorEntity doctor1 = new DoctorEntity();
        doctor1.setId(1L);
        doctor1.setFio("Иванов Иван Иванович");
        doctor1.setSpecialization("Терапевт");
        doctor1.setWorkSchedule("9:00-17:00");

        // Создаем doctor2
        DoctorEntity doctor2 = new DoctorEntity();
        doctor2.setId(2L);
        doctor2.setFio("Петров Петр Петрович");
        doctor2.setSpecialization("Хирург");
        doctor2.setWorkSchedule("10:00-18:00");

        when(doctorRepository.findAll()).thenReturn(Arrays.asList(doctor1, doctor2));

        List<DoctorDTO> doctors = doctorService.getAllDoctors();

        assertThat(doctors).hasSize(2);
        assertThat(doctors.get(0).getFio()).isEqualTo("Иванов Иван Иванович");
        assertThat(doctors.get(1).getSpecialization()).isEqualTo("Хирург");
    }

    @Test
    void shouldReturnDoctorById() {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(1L);
        doctor.setFio("Смирнов Алексей Николаевич");
        doctor.setSpecialization("Невролог");
        doctor.setWorkSchedule("8:00-16:00");

        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        DoctorDTO dto = doctorService.getDoctorById(1L);

        assertThat(dto).isNotNull();
        assertThat(dto.getSpecialization()).isEqualTo("Невролог");
    }

    @Test
    void shouldSaveDoctor() {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFio("Иванов Иван Иванович");
        doctor.setSpecialization("Терапевт");
        doctor.setWorkSchedule("9:00-17:00");

        DoctorEntity saved = new DoctorEntity();
        saved.setId(1L);
        saved.setFio(doctor.getFio());
        saved.setSpecialization(doctor.getSpecialization());
        saved.setWorkSchedule(doctor.getWorkSchedule());

        when(doctorRepository.save(any())).thenReturn(saved);

        DoctorDTO savedDTO = doctorService.saveDoctor(new DoctorDTO(null, doctor.getFio(), doctor.getSpecialization(), doctor.getWorkSchedule()));

        assertThat(savedDTO.getId()).isEqualTo(1L);
        assertThat(savedDTO.getFio()).isEqualTo("Иванов Иван Иванович");
    }

    @Test
    void shouldDeleteDoctor() {
        doctorService.deleteDoctor(1L);
        verify(doctorRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldCheckDoctorAvailability() {
        LocalDateTime time = LocalDateTime.now();
        when(appointmentRepository.countAppointmentsForSpecializationAtTime("Терапевт", time)).thenReturn(0L);

        boolean available = doctorService.isDoctorAvailable("Терапевт", time);
        assertThat(available).isTrue();
    }
}
