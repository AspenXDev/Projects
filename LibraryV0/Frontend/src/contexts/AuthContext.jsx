import React, { createContext, useContext, useState } from "react";
import * as AuthService from "../services/AuthService";

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem("user");
    return saved ? JSON.parse(saved) : null;
  });

  const login = async (username, password) => {
    const data = await AuthService.login(username, password);
    const normalizedRole = data.role.toLowerCase(); // "members" or "librarians"
    const newUser = { token: data.token, role: normalizedRole, username };
    setUser(newUser);
    localStorage.setItem("user", JSON.stringify(newUser));
    return normalizedRole;
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem("user");
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
