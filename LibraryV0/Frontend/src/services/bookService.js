// path: Frontend/src/services/BookService.js
import { api } from "./api.js";

// Helper to get JWT headers
const getAuthHeader = () => {
  const token = localStorage.getItem("token");
  return { Authorization: `Bearer ${token}` };
};

// --------------------
// Public Endpoints
// --------------------
export const getAllPublicBooks = async () => {
  const response = await api.get("/books");
  return response.data;
};

export const getBookById = async (id) => {
  const response = await api.get(`/books/${id}`);
  return response.data;
};

// --------------------
// Librarian-only Endpoints
// --------------------
export const getAllBooks = async () => {
  const response = await api.get("/books", { headers: getAuthHeader() });
  return response.data;
};

export const createBook = async (book) => {
  const response = await api.post("/books", book, { headers: getAuthHeader() });
  return response.data;
};

export const updateBook = async (id, book) => {
  const response = await api.put(`/books/${id}`, book, {
    headers: getAuthHeader(),
  });
  return response.data;
};

export const deleteBook = async (id) => {
  const response = await api.delete(`/books/${id}`, {
    headers: getAuthHeader(),
  });
  return response.data;
};
