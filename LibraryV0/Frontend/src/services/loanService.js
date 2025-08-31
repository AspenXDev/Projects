import { api } from "./api";

// Fetch all loans
export const getAllLoans = async () => {
  const response = await api.get("/loans");
  return response.data;
};

// Fetch loans for a specific member by user ID
export const getLoansByMemberId = async (memberId) => {
  const response = await api.get(`/loans/member/${memberId}`);
  return response.data;
};
