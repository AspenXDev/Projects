export default function MemberCard({ member }) {
  return (
    <div
      style={{
        border: "1px solid #ddd",
        padding: "1rem",
        marginBottom: "0.5rem",
      }}
    >
      <h3>{member.name}</h3>
      <p>Email: {member.email}</p>
    </div>
  );
}
