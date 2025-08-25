import axios from "axios";

const API_URL = "http://localhost:8081/auth";

export const login = async (username, password) => {
  const response = await axios.post(`${API_URL}/login`, { username, password });

  // Expecting { token, role } from backend
  if (!response.data?.token || !response.data?.role) {
    throw new Error("Login failed: token or role missing");
  }

  return response.data; // { token, role }
};

export const getUserProfile = async () => {
  const response = await axios.get(`${API_URL}/profile`);
  return response.data;
};
