import React from "react";
import { useAuth } from "../contexts/AuthContext";

export function Navbar({ currentPage }) {
  const { user, logout } = useAuth();

  const handleLogout = () => {
    logout();
    window.location.href = "/login"; // redirect to login
  };

  return (
    <nav
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "10px 20px",
        backgroundColor: "#333",
        color: "#fff",
      }}
    >
      <div>
        <strong>{user?.username || "User"}</strong> | {currentPage}
      </div>
      <button
        onClick={handleLogout}
        style={{
          backgroundColor: "#ff4d4f",
          color: "#fff",
          border: "none",
          padding: "5px 12px",
          borderRadius: 4,
          cursor: "pointer",
        }}
      >
        Logout
      </button>
    </nav>
  );
}
