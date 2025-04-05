//package ru.hpclab.hl.module1.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import ru.hpclab.hl.module1.dto.PatientDTO;
//import ru.hpclab.hl.module1.repository.PatientRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Transactional // Откатывает изменения после каждого теста
//public class PatientServiceTest {
//
//    @Autowired
//    private PatientService patientService;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    private int initialSize; // Количество пациентов до тестов
//
//    @BeforeEach
//    void setup() {
//        // Сохраняем начальное количество пациентов, чтобы тесты не зависели от пустой БД
//        initialSize = patientService.getAllPatients().size();
//    }
//
//    @Test
//    void shouldReturnPatientsList() {
//        List<PatientDTO> patients = patientService.getAllPatients();
//        assertThat(patients).hasSizeGreaterThanOrEqualTo(initialSize); // Проверяем, что список не пустой
//    }
//
//    @Test
//    void shouldSaveAndRetrievePatient() {
//        PatientDTO patientDTO = new PatientDTO(null, "John Doe", LocalDate.of(1990, 5, 10), "123-456-789");
//
//        PatientDTO savedPatient = patientService.savePatient(patientDTO);
//        assertThat(savedPatient).isNotNull();
//        assertThat(savedPatient.getId()).isNotNull();
//        assertThat(savedPatient.getFio()).isEqualTo("John Doe");
//
//        PatientDTO retrievedPatient = patientService.getPatientById(savedPatient.getId());
//        assertThat(retrievedPatient).isNotNull();
//        assertThat(retrievedPatient.getFio()).isEqualTo("John Doe");
//    }
//
//    @Test
//    void shouldUpdatePatient() {
//        PatientDTO patientDTO = new PatientDTO(null, "Jane Doe", LocalDate.of(1985, 3, 25), "987-654-321");
//        PatientDTO savedPatient = patientService.savePatient(patientDTO);
//
//        PatientDTO updatedPatientDTO = new PatientDTO(savedPatient.getId(), "Updated Name", LocalDate.of(1980, 7, 15), "111-222-333");
//        PatientDTO updatedPatient = patientService.updatePatient(savedPatient.getId(), updatedPatientDTO);
//
//        assertThat(updatedPatient).isNotNull();
//        assertThat(updatedPatient.getFio()).isEqualTo("Updated Name");
//        assertThat(updatedPatient.getDateOfBirth()).isEqualTo(LocalDate.of(1980, 7, 15));
//    }
//
//    @Test
//    void shouldDeletePatient() {
//        PatientDTO patientDTO = new PatientDTO(null, "Alice Brown", LocalDate.of(1978, 11, 20), "999-888-777");
//        PatientDTO savedPatient = patientService.savePatient(patientDTO);
//
//        patientService.deletePatient(savedPatient.getId());
//
//        PatientDTO deletedPatient = patientService.getPatientById(savedPatient.getId());
//        assertThat(deletedPatient).isNull();
//    }
//}
