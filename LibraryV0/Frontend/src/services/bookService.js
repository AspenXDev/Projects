import axios from "axios";

const API_URL = "http://localhost:8081/books";

const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return { headers: { Authorization: `Bearer ${token}` } };
};

export const getAllBooks = () => axios.get(API_URL);
export const getBookById = (id) => axios.get(`${API_URL}/${id}`);
export const createBook = (book) => axios.post(API_URL, book, getAuthHeaders());
export const updateBook = (id, book) =>
  axios.put(`${API_URL}/${id}`, book, getAuthHeaders());
export const deleteBook = (id) =>
  axios.delete(`${API_URL}/${id}`, getAuthHeaders());
export async function getBooks() {
  const response = await axios.get("/api/books");
  return response.data;
}
