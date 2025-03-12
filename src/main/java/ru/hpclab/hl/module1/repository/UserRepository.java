package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.hpclab.hl.module1.entity.UserEntity;

import java.util.*;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Override
    List<UserEntity> findAll();

    @Query("select u from UserEntity u join u.phones ph where ph.number = :phoneNumber")
    UserEntity getByPhone(@Param("phoneNumber") String phoneNumber);
}