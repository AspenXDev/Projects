// path: Frontend/src/services/UserService.js
import { api } from "./api.js";

export const getCurrentUser = async () => {
  const response = await api.get("/users/me");
  return response.data;
};
