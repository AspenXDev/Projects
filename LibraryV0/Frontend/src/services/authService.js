// path: Frontend/src/services/AuthService.js
import axios from "axios";

export async function login(username, password) {
  const response = await axios.post("http://localhost:8081/auth/login", {
    username,
    password,
  });
  return response.data; // { username, role, token }
}

export async function getCurrentUser(token) {
  const response = await axios.get("http://localhost:8081/auth/me", {
    headers: { Authorization: `Bearer ${token}` },
  });
  return response.data;
}
