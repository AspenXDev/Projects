// path: Frontend/src/services/FineService.js
import { api } from "./api.js";

export const getAllFines = async () => {
  const response = await api.get("/fines");
  return response.data;
};

export const getFinesByMemberId = async (memberId) => {
  const response = await api.get(`/fines/member/${memberId}`);
  return response.data;
};
