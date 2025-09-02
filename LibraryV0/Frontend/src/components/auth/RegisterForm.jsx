// path: Frontend/src/components/auth/RegisterForm.jsx
import React, { useState } from "react";
import { useAuth } from "../../contexts/AuthContext.jsx";
import { register as registerService } from "../../services/AuthService.js";

export function RegisterForm() {
  const [username, setUsername] = useState("");
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { login } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const response = await registerService({
        username,
        fullName,
        email,
        password,
      });
      login(response); // automatically log in after registration
    } catch (err) {
      setError(
        err.response?.data?.error || err.message || "Registration failed"
      );
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
        type="text"
        placeholder="Full Name"
        value={fullName}
        onChange={(e) => setFullName(e.target.value)}
        required
        style={{ marginBottom: 10, padding: 8 }}
      />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
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
        Register
      </button>
      {error && <p style={{ color: "red", marginTop: 10 }}>{error}</p>}
    </form>
  );
}
