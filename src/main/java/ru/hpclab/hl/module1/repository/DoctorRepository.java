package ru.hpclab.hl.module1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hpclab.hl.module1.entity.DoctorEntity;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    // Найти всех врачей определённой специализации
    List<DoctorEntity> findAllBySpecialization(String specialization);
}
