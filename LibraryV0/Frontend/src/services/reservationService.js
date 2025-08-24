import axios from "axios";

const API_URL = "http://localhost:8081/reservations";

const getAuthHeaders = () => {
  const token = localStorage.getItem("token");
  return { headers: { Authorization: `Bearer ${token}` } };
};

export const createReservation = (reservation) =>
  axios.post(API_URL, reservation, getAuthHeaders());
export const getReservationById = (id) =>
  axios.get(`${API_URL}/${id}`, getAuthHeaders());
export const getAllReservations = () => axios.get(API_URL, getAuthHeaders());
export const updateReservation = (id, reservation) =>
  axios.put(`${API_URL}/${id}`, reservation, getAuthHeaders());
export const deleteReservation = (id) =>
  axios.delete(`${API_URL}/${id}`, getAuthHeaders());

export const getReservationsByMember = (memberId) =>
  axios.get(`${API_URL}/member/${memberId}`, getAuthHeaders());
export const getReservationsByBook = (bookId) =>
  axios.get(`${API_URL}/book/${bookId}`, getAuthHeaders());
export const getReservationsByStatus = (status) =>
  axios.get(`${API_URL}/status/${status}`, getAuthHeaders());
