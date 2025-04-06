import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 100 }, // Рампап до 100 виртуальных пользователе>
    { duration: '50s', target: 100 }, // Удержание 100 пользователей 50 сек
    { duration: '20s', target: 0 },   // Снижение нагрузки до 0 за 20 сек
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95% запросов должны быть < 500мс
  },
};

export default function () {
  let response = http.get('http://192.168.100.20:8080/patients');
  check(response, {
    'Статус 200': (r) => r.status === 200,
  });
  sleep(1); // Задержка между запросами