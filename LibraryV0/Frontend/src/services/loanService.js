// path: Frontend/src/services/LoanService.js
import { api } from "./api.js";

const getAuthHeader = () => ({
  Authorization: `Bearer ${localStorage.getItem("token")}`,
});

// --------------------
// Loans
// --------------------
export const getAllLoans = async () => {
  const response = await api.get("/loans", { headers: getAuthHeader() });
  return response.data;
};

export const getMyLoans = async () => {
  const response = await api.get("/loans/my", { headers: getAuthHeader() });
  return response.data;
};

export const getLoansByMember = async (memberId) => {
  const response = await api.get(`/loans/member/${memberId}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const getLoansByBook = async (bookId) => {
  const response = await api.get(`/loans/book/${bookId}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const getLoansByStatus = async (status) => {
  const response = await api.get(`/loans/status/${status}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const borrowBook = async (memberId, bookId) => {
  const response = await api.post(`/loans/borrow/${memberId}/${bookId}`, null, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const returnBook = async (loanId) => {
  const response = await api.put(`/loans/return/${loanId}`, null, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const renewLoan = async (loanId) => {
  const response = await api.put(`/loans/renew/${loanId}`, null, {
    headers: getAuthHeader(),
  });
  return response.data;
};
