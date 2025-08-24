import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";

const LibrarianDashboard = () => {
  const { user } = useContext(AuthContext);

  if (!user) return <p>Please log in to access the dashboard.</p>;

  return (
    <div className="p-6">
      <h2>Librarian Dashboard</h2>
      <ul>
        <li>
          <Link to="/books">Books Catalog</Link>
        </li>
        <li>
          <Link to="/members">Members</Link>
        </li>
        <li>
          <Link to="/loans">All Loans</Link>
        </li>
        <li>
          <Link to="/reservations">All Reservations</Link>
        </li>
        <li>
          <Link to="/fines">All Fines</Link>
        </li>
      </ul>
    </div>
  );
};

export default LibrarianDashboard;
