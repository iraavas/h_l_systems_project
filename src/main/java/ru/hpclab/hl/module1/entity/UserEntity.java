package ru.hpclab.hl.module1.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "tUser")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String identifier;
    private String fio;
    @OneToMany(mappedBy = "userEntity", cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    private List<PhoneEntity> phones = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String identifier, String fio, List<PhoneEntity> phones) {
        this.id = id;
        this.identifier = identifier;
        this.fio = fio;
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneEntity> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", fio='" + fio + '\'' +
                ", phones=" + phones +
                '}';
    }
}