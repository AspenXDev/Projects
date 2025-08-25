import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./contexts/AuthContext";
import LandingPage from "./pages/LandingPage";
import LoginPage from "./pages/LoginPage";
import MemberDashboard from "./pages/MemberDashboard";
import LibrarianDashboard from "./pages/LibrarianDashboard";
import PrivateRoute from "./components/PrivateRoute";

const App = () => {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          {/* Public landing page with infinite scroll + login option */}
          <Route path="/" element={<LandingPage />} />

          {/* Dedicated login page (still available if user prefers) */}
          <Route path="/login" element={<LoginPage />} />

          {/* Member-only dashboard */}
          <Route
            path="/member-dashboard"
            element={
              <PrivateRoute roles={["member"]}>
                <MemberDashboard />
              </PrivateRoute>
            }
          />

          {/* Librarian-only dashboard */}
          <Route
            path="/librarian-dashboard"
            element={
              <PrivateRoute roles={["librarian"]}>
                <LibrarianDashboard />
              </PrivateRoute>
            }
          />
        </Routes>
      </Router>
    </AuthProvider>
  );
};

export default App;
