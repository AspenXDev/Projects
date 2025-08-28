import { api } from "./api";

export const LoanService = {
  getAllLoans: async () => {
    const response = await api.get("/loans");
    return response.data;
  },
  createLoan: async (memberId, bookId) => {
    const response = await api.post("/loans", { memberId, bookId });
    return response.data;
  },
  returnLoan: async (loanId) => {
    const response = await api.put(`/loans/${loanId}/return`);
    return response.data;
  },
};
