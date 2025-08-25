import axios from "axios";

const API_URL = "http://localhost:8081/auth";

export const login = async (username, password) => {
  const response = await axios.post(`${API_URL}/login`, { username, password });

  if (!response.data || !response.data.token) {
    throw new Error(response.data?.message || "Login failed");
  }

  return response.data; // { token, role }
};

export const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
};
