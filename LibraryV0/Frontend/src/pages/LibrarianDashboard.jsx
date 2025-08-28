import React from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";

export function LibrarianDashboard() {
  return (
    <div>
      <h2>Librarian Dashboard</h2>
      <p>Manage books, members, and reservations.</p>
    </div>
  );
}
