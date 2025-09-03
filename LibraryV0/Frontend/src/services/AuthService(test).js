// Example: Frontend/src/services/auth.js
import { api } from "./api";

export async function login(username, password) {
  try {
    const response = await api.post("/auth/login", { username, password });
    const token = response.data.token;

    if (token) {
      localStorage.setItem("token", token); // 🔑 Persist the token
    }

    return response.data;
  } catch (error) {
    console.error("Login failed:", error);
    throw error;
  }
}
