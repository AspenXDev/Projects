// src/services/FineService.js
import axios from "axios";

const API_URL = "http://localhost:8081/fines";
const getAuthHeaders = () => ({
  headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
});

export const getAllFines = async () =>
  (await axios.get(API_URL, getAuthHeaders())).data;
export const getFineById = async (id) =>
  (await axios.get(`${API_URL}/${id}`, getAuthHeaders())).data;
export const createFine = async (fine) =>
  (await axios.post(API_URL, fine, getAuthHeaders())).data;
export const updateFine = async (id, fine) =>
  (await axios.put(`${API_URL}/${id}`, fine, getAuthHeaders())).data;
export const deleteFine = async (id) =>
  (await axios.delete(`${API_URL}/${id}`, getAuthHeaders())).data;
export const getFinesByLoan = async (loanId) =>
  (await axios.get(`${API_URL}/loan/${loanId}`, getAuthHeaders())).data;
export const getFinesByMember = async (memberId) =>
  (await axios.get(`${API_URL}/member/${memberId}`, getAuthHeaders())).data;
export const getFinesByPaidStatus = async (paid) =>
  (await axios.get(`${API_URL}/paid/${paid}`, getAuthHeaders())).data;
