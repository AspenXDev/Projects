// PATH: src/components/Navbar.jsx
import React from "react";
import { LogOutButton } from "./LogOutButton.jsx";
import { useAuth } from "../contexts/AuthContext.jsx";

export default function Navbar() {
  const { user } = useAuth();

  return (
    <nav
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "12px 20px",
        backgroundColor: "#f5f8fb",
        borderBottom: "1px solid #e0e6ef",
      }}
    >
      <div style={{ fontWeight: 700, color: "#003366" }}>
        {user ? `Welcome, ${user.username}` : "Library App"}
      </div>
      <div>{user && <LogOutButton />}</div>
    </nav>
  );
}
