// PATH: src/pages/Reservations.jsx
import React, { useEffect, useState } from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";
import { ReservationService } from "../services/ReservationService";
import { ReservationsList } from "../components/reservations/ReservationsList";

export function Reservations() {
  const [reservations, setReservations] = useState([]);

  useEffect(() => {
    ReservationService.getAllReservations()
      .then((data) => setReservations(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      <h2>Reservations</h2>
      <ReservationsList reservations={reservations} />
    </div>
  );
}
