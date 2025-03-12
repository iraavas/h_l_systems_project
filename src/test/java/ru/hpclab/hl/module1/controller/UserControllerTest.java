package ru.hpclab.hl.module1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.hpclab.hl.module1.Module1Application;
import ru.hpclab.hl.module1.entity.PhoneEntity;
import ru.hpclab.hl.module1.entity.UserEntity;
import ru.hpclab.hl.module1.mapper.UserMapper;
import ru.hpclab.hl.module1.repository.UserRepository;


import java.util.Arrays;
import java.util.UUID;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Module1Application.class)
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase(provider = ZONKY)
public class UserControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        userRepository.deleteAll();
    }

    @Test
    public void get_should_returnUser_when_userExists() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setIdentifier(UUID.randomUUID().toString());
        userEntity.setFio("FIO");
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setNumber("89152214545");
        phoneEntity.setUserEntity(userEntity);
        userEntity.setPhones(Arrays.asList(phoneEntity));

        UserEntity savedUserEntity = userRepository.save(userEntity);
        String expectedJson = objectMapper.writeValueAsString(UserMapper.entity2User(savedUserEntity));


        mvc.perform(get("/users/" + userEntity.getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }
}
