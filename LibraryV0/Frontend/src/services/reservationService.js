// path: Frontend/src/services/ReservationService.js
import { api } from "./api.js";

const getAuthHeader = () => ({
  Authorization: `Bearer ${localStorage.getItem("token")}`,
});

// --------------------
// Reservations
// --------------------
export const getAllReservations = async () => {
  const response = await api.get("/reservations", { headers: getAuthHeader() });
  return response.data;
};

export const placeReservation = async (memberId, bookId) => {
  const response = await api.post(
    "/reservations",
    { memberId, bookId },
    { headers: getAuthHeader() }
  );
  return response.data;
};
