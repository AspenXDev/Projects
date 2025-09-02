import axios from "axios";

const API_URL = "http://localhost:8081/auth";

// ======================
// Login
// ======================
export async function login(username, password) {
  const response = await axios.post(`${API_URL}/login`, {
    username,
    password,
  });
  return response.data; // { username, role, token }
}

// ======================
// Register
// ======================
export async function register(userData) {
  const response = await axios.post(`${API_URL}/register`, userData);
  return response.data; // { username, role, token }
}

// ======================
// Current User
// ======================
export async function getCurrentUser(token) {
  const response = await axios.get(`${API_URL}/me`, {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
}
