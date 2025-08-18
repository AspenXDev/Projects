import { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import MemberCard from "../components/MemberCard";
import api from "../services/api";

export default function Members() {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    // placeholder until API is ready
    setMembers([
      { id: 1, name: "Alice", email: "alice@example.com" },
      { id: 2, name: "Bob", email: "bob@example.com" },
    ]);

    // Uncomment when backend is ready:
    // api.get('/members').then(res => setMembers(res.data));
  }, []);

  return (
    <div>
      <Navbar />
      <h1>Members</h1>
      {members.map((member) => (
        <MemberCard key={member.id} member={member} />
      ))}
    </div>
  );
}
