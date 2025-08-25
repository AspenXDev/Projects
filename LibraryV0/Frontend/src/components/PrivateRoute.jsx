import React, { useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";

export function PrivateRoute({ roles, children }) {
  const { user } = useContext(AuthContext);

  if (!user) {
    // No user logged in -> go to LandingPage
    return <Navigate to="/" replace />;
  }

  if (
    roles &&
    !roles.some((r) => r.toLowerCase() === user.role.toLowerCase())
  ) {
    // Role mismatch -> go to LandingPage
    return <Navigate to="/" replace />;
  }

  return children;
}
