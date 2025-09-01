// path: Frontend/src/services/LoanService.js
import { api } from "./api.js";

export const getAllLoans = async () => {
  const response = await api.get("/loans");
  return response.data;
};

export const getLoansByMemberId = async (memberId) => {
  const response = await api.get(`/loans/member/${memberId}`);
  return response.data;
};
