package ru.hpclab.hl.module1.service;


import ru.hpclab.hl.module1.controller.exeption.UserException;
import ru.hpclab.hl.module1.entity.UserEntity;
import ru.hpclab.hl.module1.repository.UserRepository;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

public class UserService {
    private final UserRepository userRepository;
    public static final String USER_NOT_FOUND_MSG = "User with ID %s not found";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException(format(USER_NOT_FOUND_MSG, id)));
    }

    public UserEntity getUserByPhoneNumber(String phoneNumber) {
        return userRepository.getByPhone(phoneNumber);
    }

    public UserEntity saveUser(UserEntity userEntity) {
        userEntity.setIdentifier(UUID.randomUUID().toString());
        return userRepository.save(userEntity);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public UserEntity updateUser(long id, UserEntity userEntity) {
        userEntity.setId(id);
        //when id is not empty save works with update logic
        return userRepository.save(userEntity);
    }
}
