// path: Frontend/src/components/Navbar.jsx
import React from "react";
import { LogOutButton } from "./LogOutButton.jsx";
import { useAuth } from "../contexts/AuthContext.jsx";
import { useNavigate } from "react-router-dom";

export default function Navbar() {
  const { user } = useAuth();
  const navigate = useNavigate();

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
      <div style={{ display: "flex", gap: "8px" }}>
        {!user ? (
          <>
            <button
              style={{
                padding: "6px 12px",
                backgroundColor: "#003366",
                color: "#fff",
                border: "none",
                borderRadius: 4,
                cursor: "pointer",
              }}
              onClick={() => navigate("/login")}
            >
              Login
            </button>
            <button
              style={{
                padding: "6px 12px",
                backgroundColor: "#0077cc",
                color: "#fff",
                border: "none",
                borderRadius: 4,
                cursor: "pointer",
              }}
              onClick={() => navigate("/register")}
            >
              Register
            </button>
          </>
        ) : (
          <LogOutButton />
        )}
      </div>
    </nav>
  );
}
