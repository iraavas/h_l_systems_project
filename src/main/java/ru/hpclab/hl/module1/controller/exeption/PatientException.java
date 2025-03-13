package ru.hpclab.hl.module1.controller.exeption;

/**
 * Исключение для ошибок, связанных с пациентами.
 */
public class PatientException extends RuntimeException {
    public PatientException(String message) {
        super(message);
    }
}


