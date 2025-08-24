import React, { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";

const LogoutButton = () => {
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  return (
    <button
      onClick={handleLogout}
      className="px-3 py-1 bg-red-500 text-white rounded hover:bg-red-700 transition-colors"
    >
      Logout
    </button>
  );
};

export default LogoutButton;
