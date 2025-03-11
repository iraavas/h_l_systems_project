package ru.hpclab.hl.module1.service;

import ru.hpclab.hl.module1.model.User;

import java.util.Set;

public class UserService {
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
