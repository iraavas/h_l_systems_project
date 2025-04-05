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

-- Вставка тестовых данных
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (1, 'Смирнов Сергей Петрович', '1956-01-07', '2250 462768');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (2, 'Попов Наталья Дмитриевич', '1956-07-06', '9347 748360');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (3, 'Петров Алексей Петрович', '1996-12-14', '6191 444969');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (4, 'Кузнецов Иван Сергеевич', '1955-02-18', '3157 328262');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (5, 'Федоров Елена Сергеевич', '1968-01-04', '6246 580952');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (6, 'Иванов Елена Иванович', '1957-12-17', '7250 126490');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (7, 'Федоров Ольга Петровна', '1978-06-14', '8341 175162');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (8, 'Васильев Алексей Иванович', '1986-05-17', '3546 116757');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (9, 'Федоров Татьяна Алексеевич', '1963-10-24', '1993 767508');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (10, 'Сидоров Ольга Сергеевна', '1956-09-21', '6033 110715');

INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (1, 'Михайлова Михаил Евгенийович', 'Кардиолог', '9:00-17:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (2, 'Попов Андрей Екатеринаович', 'Отоларинголог', '8:00-18:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (3, 'Николаева Мария Михаилович', 'Стоматолог', '10:00-18:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (4, 'Иванов Андрей Михаилович', 'Кардиолог', '9:00-17:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (5, 'Петрова Мария Светланаович', 'Офтальмолог', '9:00-16:00');

INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (1, 8, 3, '2025-04-23 17:15:29', 'Консультация', 'CANCELLED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (2, 9, 4, '2025-04-22 20:15:29', 'Бронхит', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (3, 5, 5, '2025-04-12 20:15:29', 'Бронхит', 'SCHEDULED', 'Офтальмолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (4, 2, 2, '2025-04-19 01:15:29', 'Гастрит', 'SCHEDULED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (5, 4, 3, '2025-04-16 23:15:29', 'ОРВИ', 'SCHEDULED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (6, 2, 5, '2025-04-09 01:15:29', 'Гастрит', 'SCHEDULED', 'Офтальмолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (7, 2, 4, '2025-04-19 21:15:29', 'Гипертония', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (8, 9, 1, '2025-04-09 21:15:29', 'Гастрит', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (9, 10, 1, '2025-04-07 21:15:29', 'Гастрит', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (10, 1, 2, '2025-04-17 21:15:29', 'Гастрит', 'CANCELLED', 'Отоларинголог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (11, 3, 4, '2025-04-24 21:15:29', 'ОРВИ', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (12, 9, 1, '2025-05-02 22:15:29', 'Консультация', 'CANCELLED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (13, 2, 1, '2025-04-19 17:15:29', 'Гипертония', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (14, 1, 5, '2025-04-23 18:15:29', 'Консультация', 'CANCELLED', 'Офтальмолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (15, 5, 1, '2025-05-03 21:15:29', 'Гипертония', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (16, 1, 3, '2025-04-22 20:15:29', 'Ангина', 'COMPLETED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (17, 1, 1, '2025-04-21 17:15:29', 'Гипертония', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (18, 4, 1, '2025-04-16 01:15:29', 'Гастрит', 'CANCELLED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (19, 1, 1, '2025-04-14 22:15:29', 'Консультация', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (20, 10, 4, '2025-05-04 19:15:29', 'Гастрит', 'SCHEDULED', 'Кардиолог');

COMMIT;
