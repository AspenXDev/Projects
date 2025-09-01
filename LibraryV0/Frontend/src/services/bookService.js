// path: Frontend/src/services/BookService.js
import { api } from "./api.js";

// Public endpoints
export const getAllPublicBooks = async () => {
  const response = await api.get("/books");
  return response.data;
};
export const getBookById = async (id) => {
  const response = await api.get(`/books/${id}`);
  return response.data;
};

// Librarian-only endpoints
export const getAllBooks = async () => {
  const response = await api.get("/books");
  return response.data;
};
export const createBook = async (book) => {
  const response = await api.post("/books", book);
  return response.data;
};
export const updateBook = async (id, book) => {
  const response = await api.put(`/books/${id}`, book);
  return response.data;
};
export const deleteBook = async (id) => {
  await api.delete(`/books/${id}`);
};
