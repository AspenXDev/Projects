import React from "react";
import { useAuth } from "../contexts/AuthContext";
import { Navbar } from "../components/Navbar";

export function LibrarianDashboard() {
  const { user } = useAuth(); // <-- get the user

  return (
    <div>
      <Navbar currentPage="Librarian Dashboard" />
      <div style={{ padding: 20 }}>
        <h3>{user?.username || "Librarian"}, welcome to your dashboard!</h3>
        {/* Other librarian-specific content */}
      </div>
    </div>
  );
}
