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

INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (1, 'Петрова Анна Сергеевна', '1979-09-02', '8114 724255');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (2, 'Сидоров Дмитрий Сергеевич', '1967-03-23', '8118 222561');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (3, 'Петрова Татьяна Ивановна', '1979-05-22', '2481 852572');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (4, 'Кузнецов Сергей Иванович', '2006-04-22', '9095 624002');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (5, 'Сидоров Сергей Дмитриевич', '1951-07-10', '8098 881945');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (6, 'Смирнов Алексей Дмитриевич', '2002-05-08', '7131 799437');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (7, 'Иванова Анна Сергеевна', '2002-09-15', '6286 805526');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (8, 'Петрова Елена Ивановна', '1985-09-09', '8227 546145');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (9, 'Петров Иван Иванович', '1985-04-04', '2420 355756');
INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (10, 'Иванов Дмитрий Алексеевич', '1997-04-05', '8468 795206');

INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (1, 'Петрова Ирина Александровна', 'Кардиолог', '8:00-16:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (2, 'Попов Михаил Михаилович', 'Терапевт', '8:00-16:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (3, 'Васильев Александр Михаилович', 'Офтальмолог', '8:00-18:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (4, 'Михайлова Юлия Андрейовна', 'Невролог', '8:00-17:00');
INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (5, 'Васильев Александр Александрович', 'Терапевт', '9:00-18:00');

INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (1, 1, 4, '2025-04-29 07:06:23', 'Радикулит', 'COMPLETED', 'Невролог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (2, 2, 4, '2025-04-12 06:06:23', 'Неврит', 'SCHEDULED', 'Невролог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (3, 5, 1, '2025-04-14 01:06:23', 'ИБС', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (4, 8, 5, '2025-04-16 05:06:23', 'ОРВИ', 'CANCELLED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (5, 5, 2, '2025-04-12 01:06:23', 'Грипп', 'CANCELLED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (6, 4, 1, '2025-04-07 23:06:23', 'Аритмия', 'SCHEDULED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (7, 10, 3, '2025-04-12 00:06:23', 'Конъюнктивит', 'COMPLETED', 'Офтальмолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (8, 4, 3, '2025-04-18 06:06:23', 'Конъюнктивит', 'SCHEDULED', 'Офтальмолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (9, 2, 2, '2025-04-28 07:06:23', 'Гипертония', 'SCHEDULED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (10, 6, 5, '2025-04-22 04:06:23', 'Гастрит', 'COMPLETED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (11, 3, 2, '2025-04-20 04:06:23', 'Гипертония', 'SCHEDULED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (12, 4, 1, '2025-04-15 05:06:23', 'Аритмия', 'CANCELLED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (13, 3, 1, '2025-05-02 02:06:23', 'Аритмия', 'COMPLETED', 'Кардиолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (14, 5, 3, '2025-04-21 07:06:23', 'Катаракта', 'CANCELLED', 'Офтальмолог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (15, 3, 2, '2025-04-21 06:06:23', 'Гипертония', 'CANCELLED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (16, 3, 4, '2025-04-15 06:06:23', 'Радикулит', 'SCHEDULED', 'Невролог');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (17, 6, 2, '2025-04-10 01:06:23', 'Гипертония', 'CANCELLED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (18, 3, 2, '2025-04-30 03:06:23', 'Гипертония', 'COMPLETED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (19, 10, 5, '2025-04-11 06:06:23', 'Грипп', 'COMPLETED', 'Терапевт');
INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (20, 2, 1, '2025-04-15 06:06:23', 'Гипертония', 'SCHEDULED', 'Кардиолог');

COMMIT;
