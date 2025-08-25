// src/services/UserService.js
import axios from "axios";

const API_URL = "http://localhost:8081/users";
const getAuthHeaders = () => ({
  headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
});

export const createUser = async (user) =>
  (await axios.post(API_URL, user, getAuthHeaders())).data;
export const getUserById = async (id) =>
  (await axios.get(`${API_URL}/${id}`, getAuthHeaders())).data;
export const getAllUsers = async () =>
  (await axios.get(API_URL, getAuthHeaders())).data;
export const updateUser = async (id, user) =>
  (await axios.put(`${API_URL}/${id}`, user, getAuthHeaders())).data;
export const deleteUser = async (id) =>
  (await axios.delete(`${API_URL}/${id}`, getAuthHeaders())).data;
export const getUserByUsername = async (username) =>
  (await axios.get(`${API_URL}/username/${username}`, getAuthHeaders())).data;
export const getUserByEmail = async (email) =>
  (await axios.get(`${API_URL}/email/${email}`, getAuthHeaders())).data;

// For dashboard: get a member's registration info
export const getMemberInfo = async (id) =>
  (await axios.get(`${API_URL}/${id}`, getAuthHeaders())).data;
