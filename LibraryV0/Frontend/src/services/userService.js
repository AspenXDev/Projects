import { api } from "./api";

export const UserService = {
  getCurrentUser: async () => {
    const response = await api.get("/users/me");
    return response.data;
  },
};
