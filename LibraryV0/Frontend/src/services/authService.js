import axios from "axios";

const API_URL = "http://localhost:8081/auth";

export const login = async (username, password) => {
  const response = await axios.post(`${API_URL}/login`, { username, password });
  return response.data; // { message, token }
};
export async function getUserProfile() {
  const response = await axios.get("/api/user/profile");
  return response.data;
}
