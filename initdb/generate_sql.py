import os
import random
from datetime import datetime, timedelta

def generate_patients(count=10):
    male_first_names = ["Иван", "Петр", "Сергей", "Алексей", "Дмитрий"]
    female_first_names = ["Анна", "Елена", "Ольга", "Татьяна", "Наталья"]
    male_middle_names = ["Иванович", "Петрович", "Сергеевич", "Алексеевич", "Дмитриевич"]
    female_middle_names = ["Ивановна", "Петровна", "Сергеевна", "Алексеевна", "Дмитриевна"]
    male_last_names = ["Иванов", "Петров", "Сидоров", "Смирнов", "Кузнецов"]
    female_last_names = ["Иванова", "Петрова", "Сидорова", "Смирнова", "Кузнецова"]

    patients = []
    for i in range(1, count + 1):
        gender = random.choice(["male", "female"])
        if gender == "male":
            first_name = random.choice(male_first_names)
            middle_name = random.choice(male_middle_names)
            last_name = random.choice(male_last_names)
        else:
            first_name = random.choice(female_first_names)
            middle_name = random.choice(female_middle_names)
            last_name = random.choice(female_last_names)

        fio = f"{last_name} {first_name} {middle_name}"
        birth_date = f"{random.randint(1950, 2010)}-{random.randint(1, 12):02d}-{random.randint(1, 28):02d}"
        insurance_number = f"{random.randint(1000, 9999)} {random.randint(100000, 999999)}"

        patients.append({
            "id": i,
            "fio": fio,
            "birth_date": birth_date,
            "insurance_number": insurance_number
        })
    return patients

def generate_doctors(count=5):
    specialties = ["Терапевт", "Хирург", "Кардиолог", "Невролог", "Офтальмолог", "Отоларинголог", "Стоматолог"]
    male_names = ["Александр", "Михаил", "Андрей", "Евгений", "Владимир"]
    female_names = ["Мария", "Екатерина", "Ирина", "Светлана", "Юлия"]
    male_last_names = ["Смирнов", "Иванов", "Кузнецов", "Попов", "Васильев"]
    female_last_names = ["Петрова", "Сидорова", "Михайлова", "Федорова", "Николаева"]

    doctors = []
    for i in range(1, count + 1):
        gender = random.choice(["male", "female"])
        if gender == "male":
            first_name = random.choice(male_names)
            last_name = random.choice(male_last_names)
            middle_name = random.choice(male_names) + "ович"
        else:
            first_name = random.choice(female_names)
            last_name = random.choice(female_last_names)
            middle_name = random.choice(male_names) + "овна"

        fio = f"{last_name} {first_name} {middle_name}"
        specialization = random.choice(specialties)
        work_schedule = f"{random.randint(8, 9)}:00-{random.randint(16, 18)}:00"

        doctors.append({
            "id": i,
            "fio": fio,
            "specialization": specialization,
            "work_schedule": work_schedule
        })
    return doctors

def generate_appointments(count=20, patient_count=10, doctor_count=5, doctors=None):
    specialization_diagnosis_map = {
        "Терапевт": ["ОРВИ", "Грипп", "Гастрит", "Гипертония"],
        "Хирург": ["Аппендицит", "Грыжа", "Травма", "Послеоперационный осмотр"],
        "Кардиолог": ["Гипертония", "ИБС", "Аритмия"],
        "Невролог": ["Мигрень", "Неврит", "Радикулит"],
        "Офтальмолог": ["Конъюнктивит", "Катаракта", "Близорукость"],
        "Отоларинголог": ["Ангина", "Синусит", "Отит"],
        "Стоматолог": ["Кариес", "Пульпит", "Профосмотр"]
    }

    appointments = []
    for i in range(1, count + 1):
        doctor = random.choice(doctors)
        specialization = doctor['specialization']
        diagnoses = specialization_diagnosis_map.get(specialization, ["Общее обследование"])
        diagnosis = random.choice(diagnoses)

        appointment_date = datetime.now() + timedelta(days=random.randint(1, 30), hours=random.randint(9, 17))

        appointments.append({
            "id": i,
            "patient_id": random.randint(1, patient_count),
            "doctor_id": doctor['id'],
            "appointment_date": appointment_date.strftime("%Y-%m-%d %H:%M:%S"),
            "diagnosis": diagnosis,
            "status": random.choice(["SCHEDULED", "COMPLETED", "CANCELLED"]),
            "specialization": specialization
        })
    return appointments

def generate_sql_scripts():
    base_dir = os.path.dirname(os.path.abspath(__file__))
    scripts_path = os.path.join(base_dir, "scripts_python")
    os.makedirs(scripts_path, exist_ok=True)

    patients = generate_patients()
    doctors = generate_doctors()
    appointments = generate_appointments(doctors=doctors)

    filepath = os.path.join(scripts_path, "V1__init_tables_and_data.sql")
    with open(filepath, "w", encoding="utf-8") as f:
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

        for patient in patients:
            f.write(f"INSERT INTO t_patient (id, fio, date_of_birth, insurance_number) VALUES (")
            f.write(f"{patient['id']}, '{patient['fio']}', '{patient['birth_date']}', '{patient['insurance_number']}');\n")

        f.write("\n")
        for doctor in doctors:
            f.write(f"INSERT INTO t_doctor (id, fio, specialization, work_schedule) VALUES (")
            f.write(f"{doctor['id']}, '{doctor['fio']}', '{doctor['specialization']}', '{doctor['work_schedule']}');\n")

        f.write("\n")
        for appointment in appointments:
            f.write(f"INSERT INTO t_appointment (id, patient_id, doctor_id, appointment_date, diagnosis, status, specialization) VALUES (")
            f.write(f"{appointment['id']}, {appointment['patient_id']}, {appointment['doctor_id']}, ")
            f.write(f"'{appointment['appointment_date']}', '{appointment['diagnosis']}', '{appointment['status']}', '{appointment['specialization']}');\n")

        f.write("\nCOMMIT;\n")

    print(f"SQL-скрипт сгенерирован: {filepath}")

if __name__ == "__main__":
    generate_sql_scripts()
