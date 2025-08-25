// src/services/LoanService.js
import axios from "axios";

const API_URL = "http://localhost:8081/loans";
const getAuthHeaders = () => ({
  headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
});

export const getAllLoans = async () =>
  (await axios.get(API_URL, getAuthHeaders())).data;
export const getLoanById = async (id) =>
  (await axios.get(`${API_URL}/${id}`, getAuthHeaders())).data;
export const createLoan = async (loan) =>
  (await axios.post(API_URL, loan, getAuthHeaders())).data;
export const updateLoan = async (id, loan) =>
  (await axios.put(`${API_URL}/${id}`, loan, getAuthHeaders())).data;
export const deleteLoan = async (id) =>
  (await axios.delete(`${API_URL}/${id}`, getAuthHeaders())).data;

export const getLoansByMember = async (memberId) =>
  (await axios.get(`${API_URL}/member/${memberId}`, getAuthHeaders())).data;
export const getLoansByBook = async (bookId) =>
  (await axios.get(`${API_URL}/book/${bookId}`, getAuthHeaders())).data;
export const getLoansByStatus = async (status) =>
  (await axios.get(`${API_URL}/status/${status}`, getAuthHeaders())).data;
