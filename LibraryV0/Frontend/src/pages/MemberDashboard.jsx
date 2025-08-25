import React from "react";
import { useAuth } from "../contexts/AuthContext";
import { Navbar } from "../components/Navbar";

export function MemberDashboard() {
  const { user } = useAuth(); // <-- get the user

  return (
    <div>
      <Navbar currentPage="Member Dashboard" />
      <div style={{ padding: 20 }}>
        <h3>{user?.username || "Member"}, welcome to your dashboard!</h3>
        {/* Other member-specific content */}
      </div>
    </div>
  );
}
