import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import * as AuthService from "../services/AuthService";

export function LoginPage() {
  const { login } = useAuth(); // use login from context
  const navigate = useNavigate();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const [resetMessage, setResetMessage] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);

    try {
      const role = await login(username, password);

      // Navigate based on role
      if (role.startsWith("librar")) {
        navigate("/librarian-dashboard");
      } else if (role.startsWith("member")) {
        navigate("/member-dashboard");
      } else {
        navigate("/");
      }
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.error || err.message || "Login failed");
    } finally {
      setLoading(false);
    }
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();
    setError("");
    setResetMessage("");
    setLoading(true);

    try {
      const res = await AuthService.resetPassword(fullName, email);
      setResetMessage(res.message);
    } catch (err) {
      console.error(err);
      setError(err.response?.data?.error || err.message || "Reset failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: 20, maxWidth: 420, margin: "auto" }}>
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div>
          <label>Username:</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
            style={{ width: "100%" }}
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            style={{ width: "100%" }}
          />
        </div>
        {error && <p style={{ color: "red" }}>{error}</p>}
        <button type="submit" disabled={loading}>
          {loading ? "Logging in..." : "Login"}
        </button>
      </form>

      <hr />

      <h3>Reset Password</h3>
      <form onSubmit={handleResetPassword}>
        <div>
          <label>Full Name:</label>
          <input
            type="text"
            value={fullName}
            onChange={(e) => setFullName(e.target.value)}
            required
            style={{ width: "100%" }}
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={{ width: "100%" }}
          />
        </div>
        {resetMessage && <p style={{ color: "green" }}>{resetMessage}</p>}
        <button type="submit" disabled={loading}>
          {loading ? "Processing..." : "Reset Password"}
        </button>
      </form>
    </div>
  );
}
