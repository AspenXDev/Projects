// path: Frontend/src/services/ReservationService.js
import { api } from "./api.js";

export const getAllReservations = async () => {
  const response = await api.get("/reservations");
  return response.data;
};

export const placeReservation = async (memberId, bookId) => {
  const response = await api.post("/reservations", { memberId, bookId });
  return response.data;
};
