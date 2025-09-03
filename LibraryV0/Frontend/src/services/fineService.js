// path: Frontend/src/services/FineService.js
import { api } from "./api.js";

const getAuthHeader = () => ({
  Authorization: `Bearer ${localStorage.getItem("token")}`,
});

// --------------------
// Fines
// --------------------
export const getAllFines = async () => {
  const response = await api.get("/fines", { headers: getAuthHeader() });
  return response.data;
};

export const getMyFines = async () => {
  const response = await api.get("/fines/my", { headers: getAuthHeader() });
  return response.data;
};

export const getFinesByMember = async (memberId) => {
  const response = await api.get(`/fines/member/${memberId}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const getFinesByLoan = async (loanId) => {
  const response = await api.get(`/fines/loan/${loanId}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const createFine = async (fineData) => {
  const response = await api.post("/fines", fineData, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const updateFine = async (fineId, fineData) => {
  const response = await api.put(`/fines/${fineId}`, fineData, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const deleteFine = async (fineId) => {
  const response = await api.delete(`/fines/${fineId}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};
