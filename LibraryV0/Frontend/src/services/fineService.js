import { api } from "./api";

export const FineService = {
  getAllFines: async () => {
    const response = await api.get("/fines");
    return response.data;
  },
};
