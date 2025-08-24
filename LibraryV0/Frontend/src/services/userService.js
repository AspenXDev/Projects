import axios from "axios";

const API_URL = "http://localhost:8081/users";

const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return { headers: { Authorization: `Bearer ${token}` } };
};

export const createUser = (user) => axios.post(API_URL, user, getAuthHeaders());
export const getUserById = (id) =>
  axios.get(`${API_URL}/${id}`, getAuthHeaders());
export const getAllUsers = () => axios.get(API_URL, getAuthHeaders());
export const updateUser = (id, user) =>
  axios.put(`${API_URL}/${id}`, user, getAuthHeaders());
export const deleteUser = (id) =>
  axios.delete(`${API_URL}/${id}`, getAuthHeaders());

export const getUserByUsername = (username) =>
  axios.get(`${API_URL}/username/${username}`, getAuthHeaders());
export const getUserByEmail = (email) =>
  axios.get(`${API_URL}/email/${email}`, getAuthHeaders());
