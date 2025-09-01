// path: Frontend/src/components/Layout.jsx
import React from "react";
import Navbar from "./Navbar.jsx";

export function Layout({ children }) {
  return (
    <div>
      <Navbar />
      <main style={{ padding: "16px" }}>{children}</main>
    </div>
  );
}
