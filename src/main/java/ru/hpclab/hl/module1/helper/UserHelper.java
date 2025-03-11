package ru.hpclab.hl.module1.helper;

import ru.hpclab.hl.module1.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserHelper {
    private static final Set<User> users = new HashSet<>();
    public static User addNewRandomUser() {
        User newUser = new User(UUID.randomUUID(), "Best User in the world");
        users.add(newUser);
        return newUser;
    }

    public static Set<User> allUsers() {
        return users;
    }
}
