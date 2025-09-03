import { api } from "./api.js";

// Get all fines
export const getAllFines = async () => {
  const response = await api.get("/fines");
  return response.data;
};

// Only the logged-in member's unpaid fines
export const getMyFines = async () => {
  const response = await api.get("/fines/my");
  return response.data;
};
