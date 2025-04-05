//package ru.hpclab.hl.module1.controller;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//import ru.hpclab.hl.module1.dto.PatientDTO;
//import ru.hpclab.hl.module1.repository.PatientRepository;
//
//import java.time.LocalDate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PatientControllerTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    @BeforeEach
//    void setUp() {
//        patientRepository.deleteAll(); // Очищаем таблицу перед каждым тестом
//    }
//
//    @Test
//    void shouldCreateNewPatient() {
//        PatientDTO newPatient = new PatientDTO(null, "John Doe", LocalDate.of(1992, 5, 15), "john.doe@example.com");
//
//        ResponseEntity<PatientDTO> response = restTemplate.postForEntity("/patients", newPatient, PatientDTO.class);
//
//        assertThat(response.getStatusCode()).isIn(HttpStatus.OK, HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getFio()).isEqualTo("John Doe");
//    }
//
//    @Test
//    void shouldUpdatePatient() {
//        // Создаём пациента перед обновлением
//        PatientDTO savedPatient = new PatientDTO(null, "Jane Doe", LocalDate.of(1985, 3, 25), "jane.doe@example.com");
//        ResponseEntity<PatientDTO> createdResponse = restTemplate.postForEntity("/patients", savedPatient, PatientDTO.class);
//        assertThat(createdResponse.getBody()).isNotNull();
//        Long patientId = createdResponse.getBody().getId();
//
//        // Обновляем данные
//        PatientDTO updatedPatient = new PatientDTO(patientId, "Updated Name", LocalDate.of(1990, 1, 1), "updated@example.com");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<PatientDTO> request = new HttpEntity<>(updatedPatient, headers);
//
//        ResponseEntity<PatientDTO> response = restTemplate.exchange("/patients/" + patientId, HttpMethod.PUT, request, PatientDTO.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getFio()).isEqualTo("Updated Name");
//    }
//}
