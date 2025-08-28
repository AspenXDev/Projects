import React from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";

export function Dashboard() {
  return (
    <div>
      <h2>Dashboard</h2>
      <p>Select a module from the navbar.</p>
    </div>
  );
}
