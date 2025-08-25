// src/services/AuthService.js
import axios from "axios";

// Base URL for authentication endpoints
const API_URL = "http://localhost:8081/auth";

/**
 * Login a user with username and password
 * @param {string} username
 * @param {string} password
 * @returns {Promise<{token: string, role: string}>}
 */
export async function login(username, password) {
  try {
    const response = await axios.post(`${API_URL}/login`, {
      username,
      password,
    });

    if (!response.data || !response.data.token) {
      throw new Error(response.data?.message || "Login failed");
    }

    // Store token & role in localStorage for convenience (optional)
    localStorage.setItem("token", response.data.token);
    localStorage.setItem("role", response.data.role);

    return response.data; // { token, role }
  } catch (err) {
    console.error("AuthService.login error:", err);
    throw err;
  }
}

/**
 * Log out the current user
 */
export function logout() {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
}

/**
 * Reset password using full name and email
 * @param {string} full_name
 * @param {string} email
 * @returns {Promise<{message: string}>}
 */
export async function resetPassword(full_name, email) {
  try {
    const response = await axios.post(`${API_URL}/reset-password`, {
      full_name,
      email,
    });
    if (!response.data || !response.data.message) {
      throw new Error("Password reset failed");
    }
    return response.data; // { message }
  } catch (err) {
    console.error("AuthService.resetPassword error:", err);
    throw err;
  }
}
