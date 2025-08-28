import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext.jsx";

export function PrivateRoute({ roles, children }) {
  const { user } = useAuth();

  // If no user, block access → redirect to login/home
  if (!user) {
    return <Navigate to="/" replace />;
  }

  // If roles are specified and user doesn’t match → block access
  if (
    roles &&
    !roles.some((r) => r.toLowerCase() === user.role?.toLowerCase())
  ) {
    return <Navigate to="/" replace />;
  }

  // Otherwise allow access
  return children;
}
