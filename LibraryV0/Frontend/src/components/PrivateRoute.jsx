// path: Frontend/src/components/PrivateRoute.jsx
import React from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext.jsx";

// Normalizes role the same way as Navbar
function normalizeRole(user) {
  if (!user) return null;
  const raw =
    user.role ??
    user.roleName ??
    (user.role && (user.role.roleName || user.role.name)) ??
    null;

  if (!raw) return null;
  if (typeof raw === "string") {
    let r = raw.toLowerCase();
    if (r.endsWith("s")) r = r.slice(0, -1);
    if (r.includes("member")) return "member";
    if (r.includes("librarian")) return "librarian";
    return r;
  }
  if (typeof raw === "object" && raw.roleName) {
    let r = raw.roleName.toLowerCase();
    if (r.endsWith("s")) r = r.slice(0, -1);
    if (r.includes("member")) return "member";
    if (r.includes("librarian")) return "librarian";
    return r;
  }
  return null;
}

export function PrivateRoute({ roles, children }) {
  const { user } = useAuth();

  if (!user) return <Navigate to="/login" replace />;

  if (roles && roles.length > 0) {
    const normalized = normalizeRole(user);
    // roles incoming (from App.jsx) are expected to be lowercase singular strings:
    // e.g. ["member"], ["librarian"], ["member", "librarian"]
    if (!roles.includes(normalized)) return <Navigate to="/" replace />;
  }

  return children;
}
