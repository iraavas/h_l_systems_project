package ru.hpclab.hl.module1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.hpclab.hl.module1.entity.PhoneEntity;
import ru.hpclab.hl.module1.entity.UserEntity;
import ru.hpclab.hl.module1.repository.UserRepository;
import ru.hpclab.hl.module1.service.UserService;

import java.util.Arrays;
import java.util.UUID;

@Configuration
public class ServicesConfig {

    @Bean
    UserService userService(UserRepository userRepository) {
        UserService userService = new UserService(userRepository);
        for (int i = 0; i < 5; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setIdentifier(UUID.randomUUID().toString());
            userEntity.setFio("FIO" + i);
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setNumber("111111" + i);
            phoneEntity.setUserEntity(userEntity);
            userEntity.setPhones(Arrays.asList(phoneEntity));

            userRepository.save(userEntity);
        }
        return userService;
    }
}
