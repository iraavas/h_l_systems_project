import random
from datetime import datetime, timedelta
import os

def generate_patients(count=10):
    first_names = ["Иван", "Петр", "Сергей", "Алексей", "Дмитрий", "Анна", "Елена", "Ольга", "Татьяна", "Наталья"]
    last_names = ["Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов", "Васильев", "Попов", "Новиков", "Федоров", "Морозов"]
    middle_names = ["Иванович", "Петрович", "Сергеевич", "Алексеевич", "Дмитриевич", "Ивановна", "Петровна", "Сергеевна", "Алексеевна", "Дмитриевна"]

    patients = []
    for i in range(1, count + 1):
        last_name = random.choice(last_names)
        first_name = random.choice(first_names)
        middle_name = random.choice(middle_names)
        patient = {
            "id": i,
            "fio": f"{last_name} {first_name} {middle_name}",
            "birth_date": f"{random.randint(1950, 2010)}-{random.randint(1, 12):02d}-{random.randint(1, 28):02d}",
            "insurance_number": f"{random.randint(1000, 9999)} {random.randint(100000, 999999)}"
        }
        patients.append(patient)
    return patients

def generate_doctors(count=5):
    specialties = ["Терапевт", "Хирург", "Кардиолог", "Невролог", "Офтальмолог", "Отоларинголог", "Стоматолог"]
    first_names = ["Александр", "Михаил", "Андрей", "Евгений", "Владимир", "Мария", "Екатерина", "Ирина", "Светлана", "Юлия"]
    last_names = ["Смирнов", "Иванов", "Кузнецов", "Попов", "Васильев", "Петрова", "Сидорова", "Михайлова", "Федорова", "Николаева"]

    doctors = []
    for i in range(1, count + 1):
        last_name = random.choice(last_names)
        first_name = random.choice(first_names)
        middle_name = random.choice(first_names) + "ович" if first_name[-1] != 'а' else random.choice(first_names) + "овна"
        doctor = {
            "id": i,
            "fio": f"{last_name} {first_name} {middle_name}",
            "specialization": random.choice(specialties),
            "work_schedule": f"{random.randint(8, 10)}:00-{random.randint(15, 18)}:00"
        }
        doctors.append(doctor)
    return doctors

def generate_appointments(count=20, patient_count=10, doctor_count=5, doctors=None):
    appointments = []
    for i in range(1, count + 1):
        appointment_date = datetime.now() + timedelta(days=random.randint(1, 30), hours=random.randint(9, 17))
        doctor_id = random.randint(1, doctor_count)
        specialization = doctors[doctor_id-1]['specialization'] if doctors else "Терапевт"

        appointment = {
            "id": i,
            "patient_id": random.randint(1, patient_count),
            "doctor_id": doctor_id,
            "appointment_date": appointment_date.strftime("%Y-%m-%d %H:%M:%S"),
            "diagnosis": random.choice(["ОРВИ", "Гипертония", "Гастрит", "Ангина", "Бронхит", "Консультация"]),
            "status": random.choice(["SCHEDULED", "COMPLETED", "CANCELLED"]),
            "specialization": specialization
        }
        appointments.append(appointment)
    return appointments

def generate_sql_scripts():
    # Создаем директорию, если её нет
    os.makedirs("initdb", exist_ok=True)

    patients = generate_patients()
    doctors = generate_doctors()
    appointments = generate_appointments(doctors=doctors)

    # Имя файла соответствует Flyway naming convention
    with open("scripts_python/V1__init_tables_and_data.sql", "w", encoding="utf-8") as f:
        f.write("-- Flyway migration script V1\n\n")
        f.write("BEGIN TRANSACTION;\n\n")

        f.write("-- Создание таблицы пациентов\n")
        f.write("CREATE TABLE t_patient (\n")
        f.write("    id bigserial PRIMARY KEY,\n")
        f.write("    fio VARCHAR(255) NOT NULL,\n")
        f.write("    date_of_birth DATE NOT NULL,\n")
        f.write("    insurance_number VARCHAR(50) UNIQUE NOT NULL\n")
        f.write(");\n\n")

        f.write("-- Создание таблицы врачей\n")
        f.write("CREATE TABLE t_doctor (\n")
        f.write("    id bigserial PRIMARY KEY,\n")
        f.write("    fio VARCHAR(255) NOT NULL,\n")
        f.write("    specialization VARCHAR(100) NOT NULL,\n")
        f.write("    work_schedule VARCHAR(255) NOT NULL\n")
        f.write(");\n\n")

        f.write("-- Создание таблицы приёмов\n")
        f.write("CREATE TABLE t_appointment (\n")
        f.write("    id bigserial PRIMARY KEY,\n")
        f.write("    patient_id BIGINT NOT NULL REFERENCES t_patient(id) ON DELETE CASCADE,\n")
        f.write("    doctor_id BIGINT NOT NULL REFERENCES t_doctor(id) ON DELETE CASCADE,\n")
        f.write("    appointment_date TIMESTAMP NOT NULL,\n")
        f.write("    diagnosis TEXT,\n")
        f.write("    status TEXT,\n")
        f.write("    specialization VARCHAR(100) NOT NULL\n")
        f.write(");\n\n")

        f.write("-- Добавление индексов\n")
        f.write("CREATE INDEX idx_patient_insurance ON t_patient (insurance_number);\n")
        f.write("CREATE INDEX idx_doctor_specialization ON t_doctor (specialization);\n")
        f.write("CREATE INDEX idx_appointment_date ON t_appointment (appointment_date);\n")
        f.write("CREATE INDEX idx_appointment_specialization ON t_appointment (specialization);\n\n")

        f.write("-- Вставка тестовых данных\n")
        for patient in patients:
            f.write(f"INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (")
            f.write(f"{patient['id']}, '{patient['fio']}', ")
            f.write(f"'{patient['birth_date']}', '{patient['insurance_number']}');\n")

        f.write("\n")
        for doctor in doctors:
            f.write(f"INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (")
            f.write(f"{doctor['id']}, '{doctor['fio']}', ")
            f.write(f"'{doctor['specialization']}', '{doctor['work_schedule']}');\n")

        f.write("\n")
        for appointment in appointments:
            f.write(f"INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (")
            f.write(f"{appointment['id']}, {appointment['patient_id']}, {appointment['doctor_id']}, ")
            f.write(f"'{appointment['appointment_date']}', '{appointment['diagnosis']}', ")
            f.write(f"'{appointment['status']}', '{appointment['specialization']}');\n")

        f.write("\nCOMMIT;\n")

if __name__ == "__main__":
    generate_sql_scripts()
    print("SQL скрипты успешно сгенерированы в файле scripts_python/V1__init_tables_and_data.sql")