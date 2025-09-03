import { api } from "./api.js";

// Get all loans
export const getAllLoans = async () => {
  const response = await api.get("/loans");
  return response.data;
};

// Only the logged-in member's loans
export const getMyLoans = async () => {
  const response = await api.get("/loans/my");
  return response.data;
};
