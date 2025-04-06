import json
import pandas as pd
import matplotlib.pyplot as plt

# Функция для построения и сохранения графиков
def plot_and_save(df, x, y, title, xlabel, ylabel, filename, color='blue', label=None):
    plt.figure(figsize=(12, 6))
    plt.plot(df[x], df[y], label=label, color=color)
    plt.title(title)
    plt.xlabel(xlabel)
    plt.ylabel(ylabel)
    plt.legend()
    plt.grid(True)
    plt.tight_layout()
    plt.savefig(filename, format='png')
    plt.close()

# Путь к JSON-файлу
file_path = 'D:/proj/vm1/result_2.json'

# Загрузка NDJSON данных
data = []
with open(file_path, 'r', encoding='utf-8') as file:
    for line in file:
        try:
            data.append(json.loads(line))
        except json.JSONDecodeError as e:
            print(f"Ошибка при чтении строки: {e}")

# Преобразование в DataFrame
df = pd.DataFrame(data)

# Оставляем только записи с типом 'Point'
points_df = df[df['type'] == 'Point']

# Выделяем нужные метрики
def extract_metric(metric_name):
    subset = points_df[points_df['metric'] == metric_name]
    return pd.DataFrame({
        'timestamp': pd.to_datetime(subset['data'].apply(lambda x: x['time'])),
        metric_name: subset['data'].apply(lambda x: x['value'])
    })

df_waiting = extract_metric('http_req_waiting')
df_sending = extract_metric('http_req_sending')

# Построение отдельных графиков
plot_and_save(df_waiting, 'timestamp', 'http_req_waiting',
              'Время ожидания HTTP-запроса', 'Время', 'мс',
              'http_req_waiting_time.png', color='blue', label='Ожидание')

plot_and_save(df_sending, 'timestamp', 'http_req_sending',
              'Время отправки HTTP-запроса', 'Время', 'мс',
              'http_req_sending_time.png', color='green', label='Отправка')

# Объединённый график
df_combined = pd.merge(df_waiting, df_sending, on='timestamp', how='inner')
plt.figure(figsize=(12, 6))
plt.plot(df_combined['timestamp'], df_combined['http_req_waiting'], label='Ожидание (мс)', color='blue')
plt.plot(df_combined['timestamp'], df_combined['http_req_sending'], label='Отправка (мс)', color='green')
plt.title('Время ожидания и отправки HTTP-запроса')
plt.xlabel('Время')
plt.ylabel('мс')
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig('http_req_combined_times.png', format='png')
plt.close()
