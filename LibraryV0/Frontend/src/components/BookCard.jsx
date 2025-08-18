export default function BookCard({ book }) {
  return (
    <div
      style={{
        border: "1px solid #ddd",
        padding: "1rem",
        marginBottom: "0.5rem",
      }}
    >
      <h3>{book.title}</h3>
      <p>Status: {book.status}</p>
    </div>
  );
}
