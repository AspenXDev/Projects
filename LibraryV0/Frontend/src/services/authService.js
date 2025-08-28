// PATH: src/services/authService.js
import { api } from "./api";

export const login = async (username, password) => {
  try {
    const response = await api.post("/auth/login", {
      username,
      password,
    });
    const { token, username: user, role } = response.data;

    // save token to localStorage for later
    localStorage.setItem("token", token);

    return { user, role, token };
  } catch (err) {
    console.error("Login failed:", err.response?.data || err.message);
    throw err;
  }
};
