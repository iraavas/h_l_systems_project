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
import ru.hpclab.hl.module1.dto.DoctorDTO;
import ru.hpclab.hl.module1.service.DoctorService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DoctorController.class)
@AutoConfigureMockMvc
public class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    private DoctorService doctorService; // НЕ MockBean

    private ObjectMapper objectMapper = new ObjectMapper();

    private DoctorDTO doctorDTO;

    @BeforeEach
    void setUp() {
        doctorService = Mockito.mock(DoctorService.class); // МОКИРУЕМ вручную!
        doctorDTO = new DoctorDTO(1L, "Доктор Айболит", "Терапевт", "9:00 - 18:00");
    }

    @Test
    void getAllDoctors_shouldReturnDoctorsList() throws Exception {
        when(doctorService.getAllDoctors()).thenReturn(List.of(doctorDTO));

        mvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].fio").value("Доктор Айболит"));
    }
}
