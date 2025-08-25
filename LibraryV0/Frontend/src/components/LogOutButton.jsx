import React from "react";
import { useAuth } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";

export function LogOutButton() {
  const { logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/", { replace: true }); // Always go back to LandingPage
  };

  return (
    <button
      onClick={handleLogout}
      style={{
        padding: "6px 12px",
        cursor: "pointer",
        borderRadius: 4,
        border: "1px solid #888",
        backgroundColor: "#fff",
      }}
    >
      Log Out
    </button>
  );
}
