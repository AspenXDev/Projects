// path: src/components/auth/LoginForm.jsx
import React, { useState } from "react";
import { useAuth } from "../../contexts/AuthContext.jsx";
import { login as loginService } from "../../services/AuthService.js";

export function LoginForm() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { login } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await loginService(username, password);
      login(response); // AuthContext handles navigation
    } catch (err) {
      // handle both axios errors and generic errors
      setError(err.response?.data?.message || err.message || "Login failed");
    }
  };

  return (
    <form
      onSubmit={handleSubmit}
      style={{ display: "flex", flexDirection: "column" }}
    >
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        required
        style={{ marginBottom: 10, padding: 8 }}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        required
        style={{ marginBottom: 10, padding: 8 }}
      />
      <button type="submit" style={{ padding: 8 }}>
        Login
      </button>
      {error && <p style={{ color: "red", marginTop: 10 }}>{error}</p>}
    </form>
  );
}
