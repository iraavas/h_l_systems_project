import argparse
import random
from faker import Faker
import requests
from datetime import datetime, timedelta

fake = Faker("ru_RU")
BASE_URL = "http://localhost:8081"

def clear_all():
    requests.delete(f"{BASE_URL}/clear")

def generate_patient():
    gender = random.choice(["male", "female"])
    fio = fake.name_male() if gender == "male" else fake.name_female()
    date_of_birth = fake.date_of_birth(minimum_age=20, maximum_age=80).isoformat()
    insurance_number = fake.unique.bothify(text="#### ######")
    return {
        "fio": fio,
        "dateOfBirth": date_of_birth,
        "insuranceNumber": insurance_number
    }

def generate_doctor_by_specialization(specialization):
    fio = fake.name_male()
    work_schedule = f"{random.choice(['Пн-Пт', 'Пн-Ср', 'Вт-Чт'])} {random.randint(8,10)}:00-{random.randint(16,19)}:00"
    return {
        "fio": fio,
        "specialization": specialization,
        "workSchedule": work_schedule
    }

def generate_appointment(patient_id, doctor, appointment_date):
    specialization = doctor["specialization"]
    diagnosis_map = {
        "Терапевт": ["ОРВИ", "Гастрит", "Гипертония"],
        "Хирург": ["Аппендицит", "Грыжа", "Травма"],
        "Кардиолог": ["Аритмия", "ИБС", "Гипертония"],
        "Невролог": ["Мигрень", "Радикулит"],
        "Офтальмолог": ["Катаракта", "Близорукость"],
        "Отоларинголог": ["Отит", "Синусит"],
        "Стоматолог": ["Кариес", "Пульпит"]
    }
    diagnosis = random.choice(diagnosis_map.get(specialization, ["Обследование"]))
    return {
        "patientId": patient_id,
        "doctorId": doctor["id"],
        "appointmentDate": appointment_date,
        "diagnosis": diagnosis,
        "specialization": specialization
    }

def populate(endpoint, count):
    if endpoint == "patients":
        for _ in range(count):
            data = generate_patient()
            requests.post(f"{BASE_URL}/patients", json=data)

    elif endpoint == "doctors":
        specializations = [
            "Терапевт", "Хирург", "Кардиолог", "Невролог",
            "Офтальмолог", "Отоларинголог", "Стоматолог"
        ]
        for spec in specializations:
            for _ in range(count // len(specializations)):
                data = generate_doctor_by_specialization(spec)
                requests.post(f"{BASE_URL}/doctors", json=data)

    elif endpoint == "appointments":
        patients = []
        doctors = []

        for _ in range(count):
            patient = generate_patient()
            resp = requests.post(f"{BASE_URL}/patients", json=patient)
            if resp.ok:
                patients.append(resp.json()["id"])

        specializations = [
            "Терапевт", "Хирург", "Кардиолог", "Невролог",
            "Офтальмолог", "Отоларинголог", "Стоматолог"
        ]
        for spec in specializations:
            for _ in range(10):  # 10 врачей на специализацию
                doctor = generate_doctor_by_specialization(spec)
                resp = requests.post(f"{BASE_URL}/doctors", json=doctor)
                if resp.ok:
                    doctor["id"] = resp.json()["id"]
                    doctors.append(doctor)

        for _ in range(count):
            doctor = random.choice(doctors)
            appointment_date = (datetime.now() + timedelta(days=random.randint(1, 30))).strftime("%Y-%m-%dT%H:%M:%S")
            patient_id = random.choice(patients)
            appointment = generate_appointment(patient_id, doctor, appointment_date)
            requests.post(f"{BASE_URL}/appointments", json=appointment)

    else:
        print(f"Неизвестный endpoint: {endpoint}")

def main():
    parser = argparse.ArgumentParser(description="Генератор тестовых данных для REST-сервиса")
    parser.add_argument("--count", type=int, default=500, help="Количество объектов для создания")
    parser.add_argument("--endpoint", type=str, required=True,
                        choices=["patients", "doctors", "appointments", "all"],
                        help="API endpoint для генерации")
    args = parser.parse_args()

    clear_all()

    if args.endpoint == "all":
        populate("patients", args.count)
        populate("doctors", args.count // 10) # врачей в 10 раз меньше
        populate("appointments", args.count)
    else:
        populate(args.endpoint, args.count)

if __name__ == "__main__":
    main()
