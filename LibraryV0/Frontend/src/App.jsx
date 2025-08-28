// PATH: src/App.jsx
import React from "react";
import { Routes, Route } from "react-router-dom";
import { LandingPage } from "./pages/LandingPage.jsx";
import { LoginPage } from "./components/auth/LoginPage.jsx";
import { BooksPublicList } from "./components/books/BooksPublicList.jsx";
import { MemberDashboard } from "./pages/MemberDashboard.jsx";
import { LibrarianDashboard } from "./pages/LibrarianDashboard.jsx";
import { PrivateRoute } from "./components/PrivateRoute.jsx";

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/books" element={<BooksPublicList />} />

      <Route
        path="/member-dashboard"
        element={
          <PrivateRoute roles={["members"]}>
            <MemberDashboard />
          </PrivateRoute>
        }
      />
      <Route
        path="/librarian-dashboard"
        element={
          <PrivateRoute roles={["librarians"]}>
            <LibrarianDashboard />
          </PrivateRoute>
        }
      />
      {/* fallback */}
      <Route path="*" element={<LandingPage />} />
    </Routes>
  );
}
