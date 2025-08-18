import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav style={{ padding: "1rem", borderBottom: "1px solid #ccc" }}>
      <Link to="/" style={{ marginRight: "1rem" }}>
        Dashboard
      </Link>
      <Link to="/books" style={{ marginRight: "1rem" }}>
        Books
      </Link>
      <Link to="/members">Members</Link>
    </nav>
  );
}
