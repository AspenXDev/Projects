import { useState, useEffect, useContext, useCallback } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { getAllLoans } from "../services/loanService";

const Loans = () => {
  const { user } = useContext(AuthContext);
  const [loans, setLoans] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchLoans = useCallback(async () => {
    if (!user) return;

    setLoading(true);
    setError(null);
    try {
      const res = await getAllLoans();
      setLoans(res.data || []);
    } catch (err) {
      console.error("Failed to fetch loans:", err);
      setError("Unable to fetch loans. Please try again later.");
    } finally {
      setLoading(false);
    }
  }, [user]);

  useEffect(() => {
    fetchLoans();
  }, [fetchLoans]);

  if (!user) return <p>Please log in to view loans.</p>;
  if (loading) return <p>Loading loans...</p>;
  if (error) return <p className="error">{error}</p>;

  return (
    <div className="p-6">
      <h1>{user.role === "Members" ? "My Loans" : "All Loans"}</h1>
      {loans.length ? (
        <ul>
          {loans.map((loan) => (
            <li key={loan.loan_id}>
              {loan.book_title} — {loan.status} (Due: {loan.due_date})
            </li>
          ))}
        </ul>
      ) : (
        <p>No loans found.</p>
      )}
    </div>
  );
};

export default Loans;
