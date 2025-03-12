package ru.hpclab.hl.module1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hpclab.hl.module1.mapper.UserMapper;
import ru.hpclab.hl.module1.model.User;
import ru.hpclab.hl.module1.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers().stream()
                .map(UserMapper::entity2User).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable long id) {
        return UserMapper.entity2User(userService.getUserById(id));
    }

    @GetMapping("/users/phone/{phoneNumber}")
    public User getUserByPhoneNumber(@PathVariable String phoneNumber) {
        return UserMapper.entity2User(userService.getUserByPhoneNumber(phoneNumber));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @PostMapping(value = "/users")
    public User saveUser(@RequestBody User user) {
        return UserMapper.entity2User(userService.saveUser(UserMapper.user2Entity(user)));
    }

    @PutMapping(value = "/users/{id}")
    public User updateUser(@PathVariable(required = false) long id, @RequestBody User user) {
        return UserMapper.entity2User(userService.updateUser(id, UserMapper.user2Entity(user)));
    }

}
