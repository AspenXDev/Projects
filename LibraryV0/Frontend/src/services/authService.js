// path: Frontend/src/services/AuthService.js
import axios from "axios";

const API_URL = "http://localhost:8081/auth";

// ======================
// Login
// ======================
export async function login(username, password) {
  const response = await axios.post(`${API_URL}/login`, { username, password });
  const data = response.data; // { username, role, token }

  if (data.token) {
    localStorage.setItem("token", data.token); // persist token
  }

  return data;
}

// ======================
// Register
// ======================
export async function register(userData) {
  const response = await axios.post(`${API_URL}/register`, userData);
  const data = response.data;

  if (data.token) {
    localStorage.setItem("token", data.token); // persist token
  }

  return data;
}

// ======================
// Current User
// ======================
export async function getCurrentUser() {
  const token = localStorage.getItem("token");
  if (!token) throw new Error("No token found");

  const response = await axios.get(`${API_URL}/me`, {
    headers: { Authorization: `Bearer ${token}` },
  });

  return response.data;
}

// ======================
// Logout
// ======================
export function logout() {
  localStorage.removeItem("token");
}
