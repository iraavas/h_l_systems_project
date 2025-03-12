package ru.hpclab.hl.module1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hpclab.hl.module1.entity.UserEntity;
import ru.hpclab.hl.module1.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceTest.UserServiceTestConfiguration.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndGet(){
        //create
        UserEntity userEntity = new UserEntity(0L, UUID.randomUUID().toString(), "name", null);

        UserEntity savedUser = userService.saveUser(userEntity);

        Assertions.assertEquals(userEntity.getFio(), savedUser.getFio());
        Mockito.verify(userRepository, Mockito.times(1)).save(userEntity);

        //getAll
        List<UserEntity> userList = userService.getAllUsers();

        Assertions.assertEquals("name1", userList.get(0).getFio());
        Assertions.assertEquals("name2", userList.get(1).getFio());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();

    }

    @Configuration
    static class UserServiceTestConfiguration {

        @Bean
        UserRepository userRepository() {
            UserRepository userRepository = mock(UserRepository.class);
            when(userRepository.save(any())).thenReturn(new UserEntity(0L,
                    UUID.randomUUID().toString(), "name", null));
            when(userRepository.findAll())
                    .thenReturn(Arrays.asList(new UserEntity(1L, UUID.randomUUID().toString(), "name1", null),
                            new UserEntity(2L, UUID.randomUUID().toString(), "name2", null)));
            return userRepository;
        }

        @Bean
        UserService UserService(UserRepository userRepository){
            return new UserService(userRepository);
        }
    }

}