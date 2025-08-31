// src/components/auth/LoginPage.jsx
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../contexts/AuthContext.jsx";
import { LoginForm } from "./LoginForm.jsx";

export function LoginPage() {
  const { user } = useAuth();
  const navigate = useNavigate();

  // Auto-redirect if already logged in
  useEffect(() => {
    if (user?.role === "member") navigate("/members");
    else if (user?.role === "librarian") navigate("/librarians");
  }, [user, navigate]);

  return (
    <div
      style={{
        maxWidth: 480,
        margin: "50px auto",
        padding: 20,
        border: "1px solid #ddd",
        borderRadius: 8,
        boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
      }}
    >
      <h2 style={{ textAlign: "center", marginBottom: 24 }}>Login</h2>
      <LoginForm />
    </div>
  );
}
