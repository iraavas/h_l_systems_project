package ru.hpclab.hl.module1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Сервис для сбора и вывода статистики по пациентам, врачам и приёмам.
 */
@Service
public class MedicalStatsService {

    @Value("${statisticsservice.infostring:appointments}")
    private String infoString;

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @Autowired
    public MedicalStatsService(PatientService patientService, DoctorService doctorService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    @Async(value = "applicationTaskExecutor")
    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void scheduleStatisticsTask() {
        System.out.println(
                Thread.currentThread().getName() +
                        " - Medical Statistics Report: " + infoString +
                        " - Patients: " + patientService.getAllPatients().size() +
                        " - Doctors: " + doctorService.getAllDoctors().size() +
                        " - Appointments: " + appointmentService.getAllAppointments().size()
        );
    }
}

