package ru.hpclab.hl.module1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="t_doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fio;
    private String specialization;

    @Column(name="work_schedule")
    private String workSchedule;
//    private List<Appointment> appointments = new ArrayList<>();

}
