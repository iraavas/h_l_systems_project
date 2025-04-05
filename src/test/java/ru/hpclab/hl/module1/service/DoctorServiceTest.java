//package ru.hpclab.hl.module1.service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import ru.hpclab.hl.module1.dto.DoctorDTO;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional // Откатывает изменения после каждого теста
//public class DoctorServiceTest {
//
//    @Autowired
//    private DoctorService doctorService;
//
//    @Test
//    void shouldReturnEmptyListIfNoDoctors() {
//        List<DoctorDTO> doctors = doctorService.getAllDoctors();
//        assertThat(doctors).isEmpty();
//    }
//
//    @Test
//    void shouldSaveAndRetrieveDoctor() {
//        DoctorDTO doctorDTO = new DoctorDTO(null, "Dr. John Doe", "Cardiologist", "9-17");
//
//        DoctorDTO savedDoctor = doctorService.saveDoctor(doctorDTO);
//        assertThat(savedDoctor).isNotNull();
//        assertThat(savedDoctor.getId()).isNotNull();
//        assertThat(savedDoctor.getFio()).isEqualTo("Dr. John Doe");
//
//        DoctorDTO retrievedDoctor = doctorService.getDoctorById(savedDoctor.getId());
//        assertThat(retrievedDoctor).isNotNull();
//        assertThat(retrievedDoctor.getFio()).isEqualTo("Dr. John Doe");
//    }
//
//    @Test
//    void shouldUpdateDoctor() {
//        DoctorDTO doctorDTO = new DoctorDTO(null, "Dr. Smith", "Dentist", "10-18");
//        DoctorDTO savedDoctor = doctorService.saveDoctor(doctorDTO);
//
//        DoctorDTO updatedDoctorDTO = new DoctorDTO(savedDoctor.getId(), "Updated Name", "Neurologist", "8-16");
//        DoctorDTO updatedDoctor = doctorService.updateDoctor(savedDoctor.getId(), updatedDoctorDTO);
//
//        assertThat(updatedDoctor).isNotNull();
//        assertThat(updatedDoctor.getFio()).isEqualTo("Updated Name");
//        assertThat(updatedDoctor.getSpecialization()).isEqualTo("Neurologist");
//    }
//
//    @Test
//    void shouldDeleteDoctor() {
//        DoctorDTO doctorDTO = new DoctorDTO(null, "Dr. Emily", "Surgeon", "9-17");
//        DoctorDTO savedDoctor = doctorService.saveDoctor(doctorDTO);
//
//        doctorService.deleteDoctor(savedDoctor.getId());
//
//        DoctorDTO deletedDoctor = doctorService.getDoctorById(savedDoctor.getId());
//        assertThat(deletedDoctor).isNull();
//    }
//
//    @Test
//    void shouldCheckDoctorAvailability() {
//        boolean available = doctorService.isDoctorAvailable("Cardiologist", LocalDateTime.of(2025, 3, 20, 10, 0));
//        assertThat(available).isTrue(); // Должно быть доступно, если нет записей
//    }
//}
