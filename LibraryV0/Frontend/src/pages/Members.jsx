// PATH: src/pages/Members.jsx
import React, { useEffect, useState } from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";
import { MemberService } from "../services/MemberService";
import { MemberCard } from "../components/MemberCard";

export function Members() {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    MemberService.getAllMembers()
      .then((data) => setMembers(data))
      .catch((err) => console.error(err));
  }, []);

  return (
    <div>
      <h2>All Members</h2>
      {members.map((member) => (
        <MemberCard key={member.member_id} member={member} />
      ))}
    </div>
  );
}
