import axios from "axios";

const API_URL = "http://localhost:8081/loans";

const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return { headers: { Authorization: `Bearer ${token}` } };
};

export const getAllLoans = () => axios.get(API_URL, getAuthHeaders());
export const getLoanById = (id) =>
  axios.get(`${API_URL}/${id}`, getAuthHeaders());
export const createLoan = (loan) => axios.post(API_URL, loan, getAuthHeaders());
export const updateLoan = (id, loan) =>
  axios.put(`${API_URL}/${id}`, loan, getAuthHeaders());
export const deleteLoan = (id) =>
  axios.delete(`${API_URL}/${id}`, getAuthHeaders());

export const getLoansByMember = (memberId) =>
  axios.get(`${API_URL}/member/${memberId}`, getAuthHeaders());
export const getLoansByBook = (bookId) =>
  axios.get(`${API_URL}/book/${bookId}`, getAuthHeaders());
export const getLoansByStatus = (status) =>
  axios.get(`${API_URL}/status/${status}`, getAuthHeaders());
