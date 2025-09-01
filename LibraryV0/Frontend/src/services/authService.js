// src/services/AuthService.js
import axios from "axios";

const API_URL = "http://localhost:8081/auth";

export async function login(username, password) {
  const response = await axios.post(`${API_URL}/login`, { username, password });
  return response.data; // should be { username, role, token }
}

export async function getCurrentUser(token) {
  const response = await axios.get(`${API_URL}/me`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
}
