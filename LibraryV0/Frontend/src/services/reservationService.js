// src/services/ReservationService.js
import axios from "axios";

const API_URL = "http://localhost:8081/reservations";
const getAuthHeaders = () => ({
  headers: { Authorization: `Bearer ${localStorage.getItem("token")}` },
});

export const createReservation = async (reservation) =>
  (await axios.post(API_URL, reservation, getAuthHeaders())).data;
export const getReservationById = async (id) =>
  (await axios.get(`${API_URL}/${id}`, getAuthHeaders())).data;
export const getAllReservations = async () =>
  (await axios.get(API_URL, getAuthHeaders())).data;
export const updateReservation = async (id, reservation) =>
  (await axios.put(`${API_URL}/${id}`, reservation, getAuthHeaders())).data;
export const deleteReservation = async (id) =>
  (await axios.delete(`${API_URL}/${id}`, getAuthHeaders())).data;

export const getReservationsByMember = async (memberId) =>
  (await axios.get(`${API_URL}/member/${memberId}`, getAuthHeaders())).data;
export const getReservationsByBook = async (bookId) =>
  (await axios.get(`${API_URL}/book/${bookId}`, getAuthHeaders())).data;
export const getReservationsByStatus = async (status) =>
  (await axios.get(`${API_URL}/status/${status}`, getAuthHeaders())).data;
