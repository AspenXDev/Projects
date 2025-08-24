import React, { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";

const LoginPage = () => {
  const [role, setRole] = useState("member");
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleLogin = () => {
    login(role);
    navigate(role === "member" ? "/member-dashboard" : "/librarian-dashboard");
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-[#e0f0ff] p-6">
      <h1 className="text-4xl font-bold text-[#003366] mb-6">Login</h1>

      <select
        value={role}
        onChange={(e) => setRole(e.target.value)}
        className="mb-4 p-2 border rounded"
      >
        <option value="member">Member</option>
        <option value="librarian">Librarian</option>
      </select>

      <button
        onClick={handleLogin}
        className="px-4 py-2 bg-[#007bff] text-white rounded"
      >
        Login
      </button>
    </div>
  );
};

export default LoginPage;
