// path: Frontend/src/services/UserService.js
import { api } from "./api.js";

const getAuthHeader = () => ({
  Authorization: `Bearer ${localStorage.getItem("token")}`,
});

export const getCurrentUser = async () => {
  const response = await api.get("/users/me", { headers: getAuthHeader() });
  return response.data;
};
