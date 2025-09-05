// path: Frontend/src/main.jsx
import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import { AuthProvider } from "./contexts/AuthContext.jsx";
import "./index.css";
import { LandingPage } from "./pages/LandingPage.jsx";

const root = createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <LandingPage />
      </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>
);
