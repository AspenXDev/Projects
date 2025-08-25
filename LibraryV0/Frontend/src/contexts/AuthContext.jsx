import React, { createContext, useState, useEffect, useContext } from "react";
import * as AuthService from "../services/AuthService";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");
    const role = localStorage.getItem("role");
    if (token && role) {
      setUser({ token, role });
    }
  }, []);

  const login = async (username, password) => {
    try {
      const { token, role } = await AuthService.login(username, password);
      localStorage.setItem("token", token);
      localStorage.setItem("role", role);
      setUser({ token, role });
      return role; // ✅ returns role to caller
    } catch (err) {
      console.error("AuthContext login error:", err);
      throw err;
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
