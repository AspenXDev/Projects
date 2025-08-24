import axios from "axios";

const API_URL = "http://localhost:8081/fines";

const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return { headers: { Authorization: `Bearer ${token}` } };
};

export const getAllFines = () => axios.get(API_URL, getAuthHeaders());
export const getFineById = (id) =>
  axios.get(`${API_URL}/${id}`, getAuthHeaders());
export const createFine = (fine) => axios.post(API_URL, fine, getAuthHeaders());
export const updateFine = (id, fine) =>
  axios.put(`${API_URL}/${id}`, fine, getAuthHeaders());
export const deleteFine = (id) =>
  axios.delete(`${API_URL}/${id}`, getAuthHeaders());

export const getFinesByLoan = (loanId) =>
  axios.get(`${API_URL}/loan/${loanId}`, getAuthHeaders());
export const getFinesByMember = (memberId) =>
  axios.get(`${API_URL}/member/${memberId}`, getAuthHeaders());
export const getFinesByPaidStatus = (paid) =>
  axios.get(`${API_URL}/paid/${paid}`, getAuthHeaders());
