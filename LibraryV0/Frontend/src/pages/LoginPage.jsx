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
    <div
      style={{
        minHeight: "100vh",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <h1>Login</h1>
      <select value={role} onChange={(e) => setRole(e.target.value)}>
        <option value="member">Member</option>
        <option value="librarian">Librarian</option>
      </select>
      <button onClick={handleLogin} style={{ marginTop: "1rem" }}>
        Login
      </button>
    </div>
  );
};

export default LoginPage;
