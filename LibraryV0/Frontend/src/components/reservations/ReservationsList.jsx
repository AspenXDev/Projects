import React from "react";
import { AuthProvider, useAuth } from "../../contexts/AuthContext.jsx";
// import "../../styling/ReservationCard.css";

export function ReservationsList({ reservations }) {
  return (
    <div>
      {reservations.map((r) => (
        <ReservationCard key={r.reservation_id} reservation={r} />
      ))}
    </div>
  );
}
