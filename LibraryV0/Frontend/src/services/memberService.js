// path: Frontend/src/services/MemberService.js
import { api } from "./api.js";

// Librarian
export const getAllMembers = async () => {
  const response = await api.get("/members");
  return response.data;
};

// By member ID
export const getMemberById = async (id) => {
  const response = await api.get(`/members/${id}`);
  return response.data;
};

// By user ID
export const getMemberByUserId = async (userId) => {
  const response = await api.get(`/members/${userId}`);
  return response.data;
};

// Member's loans
export const getMemberLoans = async (id) => {
  const response = await api.get(`/members/${id}/loans`);
  return response.data;
};

// Member's fines
export const getMemberFines = async (id) => {
  const response = await api.get(`/members/${id}/fines`);
  return response.data;
};
