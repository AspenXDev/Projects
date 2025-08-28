import { api } from "./api";

export const ReservationService = {
  getAllReservations: async () => {
    const response = await api.get("/reservations");
    return response.data;
  },
  placeReservation: async (memberId, bookId) => {
    const response = await api.post("/reservations", { memberId, bookId });
    return response.data;
  },
};
