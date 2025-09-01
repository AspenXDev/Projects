// path: Frontend/src/contexts/AuthContext.jsx
import React, { createContext, useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

const AuthContext = createContext(null);

export function useAuth() {
  return useContext(AuthContext);
}

export function AuthProvider({ children }) {
  const navigate = useNavigate();
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem("user");
    return saved ? JSON.parse(saved) : null;
  });

  // Only load once on mount
  useEffect(() => {
    if (!user) {
      const saved = localStorage.getItem("user");
      if (saved) setUser(JSON.parse(saved));
    }
  }, []); // empty dependency to run only once

  function login(data) {
    if (!data || !data.token) throw new Error("Invalid login response");

    // normalize role to lowercase for routing
    const normalizedRole =
      data.role === "Members"
        ? "member"
        : data.role === "Librarians"
        ? "librarian"
        : "member";

    const newUser = {
      username: data.username,
      token: data.token,
      role: normalizedRole,
      userId: data.userId ?? null,
    };

    setUser(newUser);
    localStorage.setItem("user", JSON.stringify(newUser));
    localStorage.setItem("token", newUser.token);
    localStorage.setItem("role", newUser.role);

    // Navigate immediately based on role
    if (normalizedRole === "member") navigate("/members", { replace: true });
    else if (normalizedRole === "librarian")
      navigate("/librarians", { replace: true });
    else navigate("/", { replace: true });

    return newUser;
  }

  function logout() {
    setUser(null);
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    navigate("/login", { replace: true });
  }

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}
