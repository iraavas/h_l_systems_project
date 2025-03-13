package ru.hpclab.hl.module1.repository;

import ru.hpclab.hl.module1.model.Appointment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AppointmentRepository {
    private static final List<Appointment> appointments = new ArrayList<>();

    public List<Appointment> findAll() {
        return appointments;
    }
}
