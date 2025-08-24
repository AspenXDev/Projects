import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";

const MemberDashboard = () => {
  const { user } = useContext(AuthContext);

  if (!user) return <p>Please log in to access the dashboard.</p>;

  return (
    <div className="p-6">
      <h2>Member Dashboard</h2>
      <ul>
        <li>
          <Link to="/books">View Books</Link>
        </li>
        <li>
          <Link to="/loans">My Loans</Link>
        </li>
        <li>
          <Link to="/reservations">My Reservations</Link>
        </li>
        <li>
          <Link to="/fines">My Fines</Link>
        </li>
      </ul>
    </div>
  );
};

export default MemberDashboard;
