import { useState, useEffect, useContext, useCallback } from "react";
import MemberCard from "../components/MemberCard";
import { getAllMembers } from "../services/memberService";
import AuthContext from "../contexts/AuthContext";

const Members = () => {
  const { user } = useContext(AuthContext);
  const [members, setMembers] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch members function wrapped in useCallback to fix eslint hook warning
  const fetchMembers = useCallback(async () => {
    if (!user) return;

    setLoading(true);
    setError(null);

    try {
      // sanitize search input
      const cleanSearch = search.replace(/[<>;"'`]/g, "");
      const res = await getAllMembers(cleanSearch); // memberService handles backend call
      setMembers(res.data || []);
    } catch (err) {
      console.error("Failed to fetch members:", err);
      setError("Unable to fetch members. Please try again later.");
    } finally {
      setLoading(false);
    }
  }, [search, user]);

  // Fetch members on mount and whenever search changes
  useEffect(() => {
    fetchMembers();
  }, [fetchMembers]);

  if (!user) return <p>Please log in to view members.</p>;
  if (loading) return <p>Loading members...</p>;

  return (
    <div className="p-6">
      <h1>Members</h1>

      <input
        type="text"
        placeholder="Search by name or email"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        className="border p-2 mb-4"
      />

      {error && <p className="error">{error}</p>}

      {members.length ? (
        <div className="member-grid">
          {members.map((member) => (
            <MemberCard key={member.member_id} member={member} />
          ))}
        </div>
      ) : (
        <p>No members found</p>
      )}
    </div>
  );
};

export default Members;
