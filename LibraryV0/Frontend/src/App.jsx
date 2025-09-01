// path: Frontend/src/App.jsx
import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { LandingPage } from "./pages/LandingPage.jsx";
import { LoginPage } from "./components/auth/LoginPage.jsx";
import { MemberDashboard } from "./pages/MemberDashboard.jsx";
import { LibrarianDashboard } from "./pages/LibrarianDashboard.jsx";
import { Books } from "./pages/Books.jsx";
import { PrivateRoute } from "./components/PrivateRoute.jsx";

export default function App() {
  return (
    <Routes>
      {/* Public routes */}
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />

      {/* Member routes */}
      <Route
        path="/members"
        element={
          <PrivateRoute roles={["member"]}>
            <MemberDashboard />
          </PrivateRoute>
        }
      />

      {/* Librarian routes */}
      <Route
        path="/librarians"
        element={
          <PrivateRoute roles={["librarian"]}>
            <LibrarianDashboard />
          </PrivateRoute>
        }
      />

      {/* Example books page accessible only to members */}
      <Route
        path="/books"
        element={
          <PrivateRoute roles={["member", "librarian"]}>
            <Books />
          </PrivateRoute>
        }
      />

      {/* Catch-all: redirect unknown routes to LandingPage */}
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
