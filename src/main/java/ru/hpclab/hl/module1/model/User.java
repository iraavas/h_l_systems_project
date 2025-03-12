package ru.hpclab.hl.module1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private Long id;
    private UUID identifier;
    private String fio;
    private List<Phone> phones = new ArrayList<>();

    public User(Long id, UUID identifier, String fio, List<Phone> phones) {
        this.id = id;
        this.identifier = identifier;
        this.fio = fio;
        this.phones = phones;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", identifier=" + identifier +
                ", fio='" + fio + '\'' +
                ", phones=" + phones +
                '}';
    }
}
