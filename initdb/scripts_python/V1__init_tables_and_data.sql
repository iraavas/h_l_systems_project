-- Flyway migration script V1

BEGIN TRANSACTION;

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
    status TEXT,
    specialization VARCHAR(100) NOT NULL
);

-- Добавление индексов
CREATE INDEX idx_patient_insurance ON t_patient (insurance_number);
CREATE INDEX idx_doctor_specialization ON t_doctor (specialization);
CREATE INDEX idx_appointment_date ON t_appointment (appointment_date);
CREATE INDEX idx_appointment_specialization ON t_appointment (specialization);

INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (1, 'Смирнов Дмитрий Алексеевич', '1985-12-21', '5493 884329');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (2, 'Петрова Наталья Ивановна', '1973-12-13', '6904 376479');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (3, 'Смирнов Петр Дмитриевич', '1956-11-09', '6428 749254');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (4, 'Иванов Дмитрий Сергеевич', '1972-12-09', '7817 908829');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (5, 'Кузнецов Петр Сергеевич', '2009-07-16', '4309 113852');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (6, 'Сидоров Иван Дмитриевич', '1990-07-12', '1363 107226');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (7, 'Смирнова Ольга Ивановна', '1958-09-28', '2669 965285');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (8, 'Смирнова Елена Сергеевна', '1967-10-28', '2566 601368');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (9, 'Сидорова Татьяна Ивановна', '1950-04-16', '2440 763836');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (10, 'Иванов Петр Петрович', '1997-08-26', '4298 496164');

INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (1, 'Кузнецов Владимир Евгенийович', 'Хирург', '8:00-17:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (2, 'Иванов Евгений Михаилович', 'Отоларинголог', '9:00-17:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (3, 'Николаева Светлана Владимировна', 'Хирург', '8:00-16:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (4, 'Попов Михаил Владимирович', 'Невролог', '9:00-17:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (5, 'Федорова Юлия Андрейовна', 'Отоларинголог', '8:00-18:00');

INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (1, 8, 5, '2025-04-15 05:47:13', 'Синусит', 'COMPLETED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (2, 2, 1, '2025-04-11 08:47:13', 'Послеоперационный осмотр', 'SCHEDULED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (3, 9, 2, '2025-04-12 07:47:13', 'Ангина', 'SCHEDULED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (4, 3, 3, '2025-04-14 05:47:13', 'Травма', 'SCHEDULED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (5, 4, 4, '2025-04-13 07:47:13', 'Неврит', 'COMPLETED', 'Невролог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (6, 3, 3, '2025-04-23 04:47:13', 'Грыжа', 'COMPLETED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (7, 10, 5, '2025-04-23 01:47:13', 'Отит', 'SCHEDULED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (8, 3, 5, '2025-05-02 04:47:13', 'Ангина', 'COMPLETED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (9, 10, 3, '2025-04-21 06:47:13', 'Грыжа', 'CANCELLED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (10, 10, 5, '2025-04-25 05:47:13', 'Отит', 'SCHEDULED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (11, 10, 1, '2025-05-06 07:47:13', 'Аппендицит', 'CANCELLED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (12, 5, 4, '2025-04-19 06:47:13', 'Неврит', 'CANCELLED', 'Невролог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (13, 7, 3, '2025-05-01 04:47:13', 'Грыжа', 'CANCELLED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (14, 2, 2, '2025-04-28 09:47:13', 'Синусит', 'COMPLETED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (15, 4, 4, '2025-04-07 07:47:13', 'Мигрень', 'CANCELLED', 'Невролог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (16, 9, 5, '2025-04-26 09:47:13', 'Отит', 'SCHEDULED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (17, 3, 1, '2025-04-13 01:47:13', 'Травма', 'CANCELLED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (18, 2, 1, '2025-05-01 09:47:13', 'Грыжа', 'SCHEDULED', 'Хирург');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (19, 9, 5, '2025-04-28 05:47:13', 'Отит', 'COMPLETED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (20, 2, 4, '2025-04-10 05:47:13', 'Радикулит', 'SCHEDULED', 'Невролог');

COMMIT;
