// src/components/PrivateRoute.jsx
import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext.jsx";

export function PrivateRoute({ roles, children }) {
  const { user } = useAuth();

  if (!user) return <Navigate to="/login" replace />;

  if (
    roles &&
    !roles.some((r) => r.toLowerCase() === user.role?.toLowerCase())
  ) {
    return <Navigate to="/" replace />;
  }

  return children;
}
