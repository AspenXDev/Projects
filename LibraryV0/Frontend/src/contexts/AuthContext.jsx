// src/contexts/AuthContext.jsx
import React, { createContext, useContext, useState } from "react";
import * as AuthService from "../services/AuthService";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem("user");
    return saved ? JSON.parse(saved) : null;
  });

  // Login function
  const login = async (username, password) => {
    try {
      const data = await AuthService.login(username, password);

      const newUser = {
        token: data.token,
        role: data.role.toLowerCase(), // "members" or "librarians"
        username, // store input username
        user_id: null, // backend doesn’t return this yet
      };

      setUser(newUser);
      localStorage.setItem("user", JSON.stringify(newUser));
      localStorage.setItem("token", data.token);
      localStorage.setItem("role", newUser.role);

      return newUser.role;
    } catch (err) {
      console.error("AuthContext.login error:", err);
      throw err;
    }
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem("user");
    localStorage.removeItem("token");
    localStorage.removeItem("role");
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
