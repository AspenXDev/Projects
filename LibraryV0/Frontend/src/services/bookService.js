// src/services/BookService.js
import axios from "axios";

const API_URL = "http://localhost:8081/books";

const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return { headers: { Authorization: `Bearer ${token}` } };
};

export const getAllBooks = async () => (await axios.get(API_URL)).data;
export const getBookById = async (id) =>
  (await axios.get(`${API_URL}/${id}`)).data;
export const createBook = async (book) =>
  (await axios.post(API_URL, book, getAuthHeaders())).data;
export const updateBook = async (id, book) =>
  (await axios.put(`${API_URL}/${id}`, book, getAuthHeaders())).data;
export const deleteBook = async (id) =>
  (await axios.delete(`${API_URL}/${id}`, getAuthHeaders())).data;
