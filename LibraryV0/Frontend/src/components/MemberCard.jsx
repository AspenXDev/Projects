import React from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";
export function MemberCard({ member }) {
  return (
    <div>
      <h4>{member.full_name}</h4>
      <p>Membership Valid Until: {member.membership_valid_until}</p>
      <p>Status: {member.membership_status}</p>
    </div>
  );
}
