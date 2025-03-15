package ru.hpclab.hl.module1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.hpclab.hl.module1.dto.PatientDTO;
import ru.hpclab.hl.module1.service.PatientService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mvc;

    private PatientService patientService; // НЕ MockBean

    private ObjectMapper objectMapper = new ObjectMapper();

    private PatientDTO patientDTO;

    @BeforeEach
    void setUp() {
        patientService = Mockito.mock(PatientService.class); // МОКИРУЕМ вручную!
        patientDTO = new PatientDTO(1L, "Иван Иванов", LocalDate.of(1990, 1, 1), "1234567890");
    }

    @Test
    void getAllPatients_shouldReturnPatientsList() throws Exception {
        when(patientService.getAllPatients()).thenReturn(List.of(patientDTO));

        mvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].fio").value("Иван Иванов"));
    }
}
