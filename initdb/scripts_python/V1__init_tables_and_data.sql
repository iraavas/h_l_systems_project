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

INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (1, 'Смирнова Анна Петровна', '1974-08-04', '1894 975491');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (2, 'Сидорова Наталья Петровна', '1970-08-05', '4743 684262');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (3, 'Сидорова Елена Сергеевна', '1973-08-28', '6727 934678');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (4, 'Кузнецов Дмитрий Дмитриевич', '1952-02-25', '9643 493969');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (5, 'Иванова Наталья Дмитриевна', '2009-03-16', '4650 406686');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (6, 'Сидорова Татьяна Ивановна', '1981-05-24', '5175 541737');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (7, 'Сидоров Иван Дмитриевич', '1983-01-18', '7550 705175');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (8, 'Смирнов Алексей Дмитриевич', '1980-03-21', '3551 556261');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (9, 'Смирнов Алексей Петрович', '1988-10-28', '4303 728293');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (10, 'Смирнов Иван Иванович', '2002-11-12', '9096 648523');

INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (1, 'Михайлова Светлана Андрейовна', 'Кардиолог', '9:00-17:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (2, 'Васильев Андрей Евгенийович', 'Стоматолог', '8:00-16:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (3, 'Михайлова Юлия Владимировна', 'Терапевт', '8:00-18:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (4, 'Михайлова Екатерина Евгенийовна', 'Стоматолог', '9:00-16:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (5, 'Васильев Владимир Александрович', 'Кардиолог', '8:00-17:00');

INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (1, 4, 5, '2025-04-24 05:40:12', 'ИБС', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (2, 2, 2, '2025-04-28 01:40:12', 'Профосмотр', 'CANCELLED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (3, 1, 4, '2025-04-18 01:40:12', 'Кариес', 'COMPLETED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (4, 1, 3, '2025-04-21 05:40:12', 'Грипп', 'COMPLETED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (5, 3, 2, '2025-04-24 03:40:12', 'Кариес', 'COMPLETED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (6, 6, 5, '2025-04-15 00:40:12', 'ИБС', 'CANCELLED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (7, 8, 1, '2025-04-14 23:40:12', 'ИБС', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (8, 6, 1, '2025-04-18 05:40:12', 'ИБС', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (9, 7, 4, '2025-05-08 04:40:12', 'Пульпит', 'CANCELLED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (10, 9, 5, '2025-04-30 07:40:12', 'Аритмия', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (11, 1, 5, '2025-05-04 02:40:12', 'Аритмия', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (12, 4, 3, '2025-04-30 06:40:12', 'Гипертония', 'CANCELLED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (13, 7, 1, '2025-04-25 03:40:12', 'Гипертония', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (14, 3, 1, '2025-04-29 05:40:12', 'ИБС', 'CANCELLED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (15, 7, 4, '2025-05-05 02:40:12', 'Пульпит', 'COMPLETED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (16, 5, 2, '2025-04-30 03:40:12', 'Пульпит', 'SCHEDULED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (17, 4, 5, '2025-04-14 03:40:12', 'Аритмия', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (18, 1, 4, '2025-05-07 01:40:12', 'Пульпит', 'SCHEDULED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (19, 8, 4, '2025-04-16 02:40:12', 'Профосмотр', 'SCHEDULED', 'Стоматолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (20, 3, 3, '2025-05-10 05:40:12', 'ОРВИ', 'SCHEDULED', 'Терапевт');

COMMIT;
