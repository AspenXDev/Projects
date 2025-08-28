import React from "react";

import { AuthProvider, useAuth } from "../../contexts/AuthContext.jsx";
// import "../../styling/ReservationCard.css";

export function ReservationCard({ reservation }) {
  return (
    <div>
      <p>Book: {reservation.book.title}</p>
      <p>Status: {reservation.status}</p>
      <p>Hold Until: {reservation.hold_until}</p>
    </div>
  );
}
