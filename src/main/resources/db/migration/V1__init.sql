-- Создание таблицы пациентов
CREATE TABLE t_patient (
    id bigserial PRIMARY KEY,
    fio VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    insurance_number VARCHAR(50) UNIQUE NOT NULL
);

-- Создание таблицы врачей
CREATE TABLE t_doctor (
    id bigserial PRIMARY KEY,
    fio VARCHAR(255) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    work_schedule VARCHAR(255) NOT NULL
);

-- Создание таблицы приёмов
CREATE TABLE t_appointment (
    id bigserial PRIMARY KEY,
    patient_id BIGINT NOT NULL REFERENCES t_patient(id) ON DELETE CASCADE,
    doctor_id BIGINT NOT NULL REFERENCES t_doctor(id) ON DELETE CASCADE,
    appointment_date TIMESTAMP NOT NULL,
    diagnosis TEXT,
    status TEXT
);

-- Добавление индексов
CREATE INDEX idx_patient_insurance ON t_patient (insurance_number);
CREATE INDEX idx_doctor_specialization ON t_doctor (specialization);
CREATE INDEX idx_appointment_date ON t_appointment (appointment_date);
