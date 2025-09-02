// path: Frontend/src/components/Navbar.jsx
import React from "react";
import { LogOutButton } from "./LogOutButton.jsx";
import { useAuth } from "../contexts/AuthContext.jsx";
import { useNavigate } from "react-router-dom";

// Helper: robust role normalization
function normalizeRole(user) {
  if (!user) return null;
  // possible shapes: user.role = "Members" | "Members"/"Librarians" string,
  // or user.role = { roleName: "Members" } (server-side Role object),
  // or user.roleName = "Members"
  const raw =
    user.role ??
    user.roleName ??
    (user.role && (user.role.roleName || user.role.name)) ??
    null;

  if (!raw) return null;
  if (typeof raw === "string") {
    let r = raw.toLowerCase();
    // convert plural to singular ("members" -> "member")
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

// Helper: get display name; prefer full name but fall back
function getDisplayName(user) {
  if (!user) return "";
  return (
    user.fullName ??
    user.full_name ??
    user.fullname ??
    user.name ??
    user.username ??
    ""
  );
}

export default function Navbar() {
  const { user } = useAuth();
  const navigate = useNavigate();

  const role = normalizeRole(user);
  const displayName = getDisplayName(user);

  return (
    <nav
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "12px 20px",
        backgroundColor: "#f5f8fb",
        borderBottom: "1px solid #e0e6ef",
      }}
    >
      <div
        style={{
          fontWeight: 700,
          color: "#003366",
          display: "flex",
          gap: 12,
          alignItems: "center",
        }}
      >
        <button
          onClick={() => navigate("/")}
          style={{
            padding: "6px 10px",
            backgroundColor: "transparent",
            color: "#003366",
            border: "1px solid #cfe1f8",
            borderRadius: 6,
            cursor: "pointer",
          }}
        >
          Home
        </button>
        <span>
          {user ? `Welcome, ${displayName || user.username}` : "Library App"}
        </span>
      </div>

      <div style={{ display: "flex", gap: "8px" }}>
        {!user ? (
          <>
            <button
              style={{
                padding: "6px 12px",
                backgroundColor: "#003366",
                color: "#fff",
                border: "none",
                borderRadius: 4,
                cursor: "pointer",
              }}
              onClick={() => navigate("/login")}
            >
              Login
            </button>

            <button
              style={{
                padding: "6px 12px",
                backgroundColor: "#0077cc",
                color: "#fff",
                border: "none",
                borderRadius: 4,
                cursor: "pointer",
              }}
              onClick={() => navigate("/register")}
            >
              Register
            </button>
          </>
        ) : (
          <>
            {role === "member" && (
              <button
                style={{
                  padding: "6px 12px",
                  backgroundColor: "#228B22",
                  color: "#fff",
                  border: "none",
                  borderRadius: 4,
                  cursor: "pointer",
                }}
                onClick={() => navigate("/members")}
              >
                Dashboard
              </button>
            )}

            {role === "librarian" && (
              <button
                style={{
                  padding: "6px 12px",
                  backgroundColor: "#8B0000",
                  color: "#fff",
                  border: "none",
                  borderRadius: 4,
                  cursor: "pointer",
                }}
                onClick={() => navigate("/librarians")}
              >
                Admin
              </button>
            )}

            <LogOutButton />
          </>
        )}
      </div>
    </nav>
  );
}
