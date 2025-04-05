package ru.hpclab.hl.module1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.hpclab.hl.module1.dto.PatientDTO;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class PatientControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("module1")
            .withUsername("postgres")
            .withPassword("postgres");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateAndGetPatient() {
        // Создание пациента
        PatientDTO newPatient = new PatientDTO();
        newPatient.setFio("Иванов Иван Иванович");
        newPatient.setDateOfBirth(LocalDate.of(1990, 1, 1));
        newPatient.setInsuranceNumber("1234 567890");

        ResponseEntity<PatientDTO> postResponse = restTemplate.postForEntity("/patients", newPatient, PatientDTO.class);
        assertThat(postResponse.getStatusCode().is2xxSuccessful()).isTrue();

        PatientDTO created = postResponse.getBody();
        assertThat(created).isNotNull();
        assertThat(created.getId()).isNotNull();

        // Получение пациента по ID
        ResponseEntity<PatientDTO> getResponse = restTemplate.getForEntity("/patients/" + created.getId(), PatientDTO.class);
        assertThat(getResponse.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getFio()).isEqualTo("Иванов Иван Иванович");
    }

    @Test
    void shouldReturnListOfPatients() {
        ResponseEntity<PatientDTO[]> response = restTemplate.getForEntity("/patients", PatientDTO[].class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    void shouldDeletePatient() {
        // Добавляем пациента
        PatientDTO patient = new PatientDTO();
        patient.setFio("Удаляемый Пациент");
        patient.setDateOfBirth(LocalDate.of(1985, 5, 5));
        patient.setInsuranceNumber("1111 222222");

        PatientDTO saved = restTemplate.postForObject("/patients", patient, PatientDTO.class);
        assertThat(saved).isNotNull();

        // Удаляем
        restTemplate.delete("/patients/" + saved.getId());

        // Проверяем, что не найден
        ResponseEntity<PatientDTO> response = restTemplate.getForEntity("/patients/" + saved.getId(), PatientDTO.class);
        assertThat(response.getBody()).isNull(); // или проверка по statusCode = 404, если контроллер это отдает
    }
}
