// src/components/auth/LoginForm.jsx
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../../contexts/AuthContext.jsx";
import { api } from "../../services/api.js";

export function LoginForm() {
  const { login } = useAuth();
  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const res = await api.post("/auth/login", { username, password });
      const data = res.data;

      if (!data || !data.token) {
        setError("Invalid response from server");
        return;
      }

      const role = login(data); // stores user & returns role

      // Role-based navigation
      if (role === "librarian") navigate("/librarians");
      else if (role === "member") navigate("/members");
      else navigate("/");
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ maxWidth: 400, margin: "0 auto" }}>
      <div style={{ marginBottom: 12 }}>
        <label>Username</label>
        <input
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
          style={{ width: "100%", padding: 8 }}
        />
      </div>
      <div style={{ marginBottom: 12 }}>
        <label>Password</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          style={{ width: "100%", padding: 8 }}
        />
      </div>
      {error && <div style={{ color: "red", marginBottom: 12 }}>{error}</div>}
      <button
        type="submit"
        disabled={loading}
        style={{ padding: 10, width: "100%" }}
      >
        {loading ? "Logging in..." : "Login"}
      </button>
    </form>
  );
}
