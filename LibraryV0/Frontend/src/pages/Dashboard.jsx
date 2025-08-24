import React, { useEffect, useState } from "react";
import { getUserProfile } from "../services/AuthService";
import MemberDashboard from "./MemberDashboard";
import LibrarianDashboard from "./LibrarianDashboard";

const Dashboard = () => {
  const [role, setRole] = useState(null);

  useEffect(() => {
    getUserProfile()
      .then((res) => setRole(res.data.role)) // { role: "Members" or "Librarians" }
      .catch(() => setRole(null));
  }, []);

  if (!role) return <p>Loading...</p>;

  return (
    <div className="p-6">
      {role === "Members" && <MemberDashboard />}
      {role === "Librarians" && <LibrarianDashboard />}
    </div>
  );
};

export default Dashboard;
