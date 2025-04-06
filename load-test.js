import http from 'k6/http';
import { check, sleep } from 'k6';

export let options = {
  stages: [
    { duration: '30s', target: 100 }, // ������ �� 100 ����������� ������������>
    { duration: '50s', target: 100 }, // ��������� 100 ������������� 50 ���
    { duration: '20s', target: 0 },   // �������� �������� �� 0 �� 20 ���
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95% �������� ������ ���� < 500��
  },
};

export default function () {
  let response = http.get('http://192.168.100.20:8080/patients');
  check(response, {
    '������ 200': (r) => r.status === 200,
  });
  sleep(1); // �������� ����� ���������