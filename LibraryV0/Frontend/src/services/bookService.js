// src/services/BookService.js
import { api } from "./api.js";

// Public fetch — no token
export async function getAllPublicBooks() {
  try {
    const res = await api.get("/books"); // backend /books is permitAll
    return res.data;
  } catch (err) {
    console.error("Error fetching public books:", err);
    throw err;
  }
}

// Protected CRUD endpoints
export async function createBook(book) {
  const res = await api.post("/books", book);
  return res.data;
}

export async function updateBook(bookId, book) {
  const res = await api.put(`/books/${bookId}`, book);
  return res.data;
}

export async function deleteBook(bookId) {
  const res = await api.delete(`/books/${bookId}`);
  return res.data;
}
