package ru.hpclab.hl.module1.controller.exeption;

/**
 * Исключение для ошибок, связанных с записями к врачу.
 */
public class AppointmentException extends RuntimeException {
    public AppointmentException(String message) {
        super(message);
    }
}


