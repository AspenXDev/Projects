// PATH: src/components/LogOutButton.jsx
import React from "react";
import { useNavigate } from "react-router-dom";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";

export function LogOutButton() {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/", { replace: true });
  };

  return (
    <button
      onClick={handleLogout}
      style={{
        padding: "6px 12px",
        cursor: "pointer",
        borderRadius: 6,
        border: "1px solid #888",
        background: "#fff",
        color: "#003366",
      }}
    >
      Log Out
    </button>
  );
}
