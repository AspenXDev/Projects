// path: Frontend/src/App.jsx
import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { LandingPage } from "./pages/LandingPage.jsx";
import { LoginPage } from "./components/auth/LoginPage.jsx";
import { RegisterPage } from "./components/auth/RegisterPage.jsx";
import { MemberDashboard } from "./pages/MemberDashboard.jsx";
import { LibrarianDashboard } from "./pages/LibrarianDashboard.jsx";
import { Books } from "./pages/Books.jsx";
import { PrivateRoute } from "./components/PrivateRoute.jsx";
import { Layout } from "./components/Layout.jsx";

export default function App() {
  return (
    <Routes>
      {/* Public routes */}
      <Route
        path="/"
        element={
          <Layout>
            <LandingPage />
          </Layout>
        }
      />
      <Route
        path="/login"
        element={
          <Layout>
            <LoginPage />
          </Layout>
        }
      />
      <Route
        path="/register"
        element={
          <Layout>
            <RegisterPage />
          </Layout>
        }
      />

      {/* Member routes */}
      <Route
        path="/members"
        element={
          <PrivateRoute roles={["member"]}>
            <Layout>
              <MemberDashboard />
            </Layout>
          </PrivateRoute>
        }
      />

      {/* Librarian routes */}
      <Route
        path="/librarians"
        element={
          <PrivateRoute roles={["librarian"]}>
            <Layout>
              <LibrarianDashboard />
            </Layout>
          </PrivateRoute>
        }
      />

      {/* Books page accessible to members and librarians */}
      <Route
        path="/books"
        element={
          <PrivateRoute roles={["member", "librarian"]}>
            <Layout>
              <Books />
            </Layout>
          </PrivateRoute>
        }
      />

      {/* Catch-all: redirect unknown routes to LandingPage */}
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
