import React from "react";
import { LogOutButton } from "./LogOutButton";

export function Navbar({ currentPage }) {
  return (
    <nav
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "10px 20px",
        backgroundColor: "#f5f5f5",
        borderBottom: "1px solid #ddd",
      }}
    >
      <div>
        <strong>{currentPage}</strong>
      </div>
      <div>
        <LogOutButton />
      </div>
    </nav>
  );
}
