// src/pages/MemberDashboard.jsx
import React, { useEffect, useState, useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";
import { getLoansByMemberId } from "../services/LoanService";
import { getFinesByMemberId } from "../services/FineService";
import { getMemberByUserId } from "../services/MemberService";
import { BookCard } from "../components/books/BookCard.jsx";
import "../styling/Dashboard.css";

export const MemberDashboard = () => {
  const { user } = useContext(AuthContext);
  const [member, setMember] = useState(null);
  const [loans, setLoans] = useState([]);
  const [fines, setFines] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!user?.user_id) return;

    Promise.all([
      getMemberByUserId(user.user_id).then(setMember).catch(console.error),
      getLoansByMemberId(user.user_id)
        .then((res) => setLoans(res || []))
        .catch(console.error),
      getFinesByMemberId(user.user_id)
        .then((res) => setFines(res || []))
        .catch(console.error),
    ]).finally(() => setLoading(false));
  }, [user]);

  const totalUnpaidFines = fines.reduce(
    (sum, f) => (!f.paid ? sum + Number(f.amount || 0) : sum),
    0
  );
  const overdueLoans = loans.filter(
    (l) => l.status === "Active" && new Date(l.dueDate) < new Date()
  );

  return (
    <div className="dashboard-container">
      <h2>Welcome, {member?.fullName || user?.username || "Member"}</h2>

      <section className="summary-cards">
        <div className="card">
          <h3>Membership</h3>
          <p>
            Status: <strong>{member?.membershipStatus || "—"}</strong>
          </p>
          <p>
            Valid Until:{" "}
            <strong>
              {member?.membershipValidUntil
                ? new Date(member.membershipValidUntil).toLocaleDateString()
                : "—"}
            </strong>
          </p>
        </div>

        <div className="card">
          <h3>Fines</h3>
          <p>
            Total Unpaid: <strong>${totalUnpaidFines.toFixed(2)}</strong>
          </p>
        </div>

        <div className="card">
          <h3>Loans</h3>
          <p>
            Active:{" "}
            <strong>{loans.filter((l) => l.status === "Active").length}</strong>
          </p>
          <p>
            Overdue:{" "}
            <strong style={{ color: "red" }}>{overdueLoans.length}</strong>
          </p>
        </div>
      </section>

      <section className="loans-section">
        <h3>Your Active Loans</h3>
        {loading ? (
          <p className="muted">Loading…</p>
        ) : loans.length === 0 ? (
          <p className="muted">No active loans.</p>
        ) : (
          <div className="books-grid">
            {loans.map((loan) => (
              <BookCard
                key={loan.loanId}
                book={loan.book}
                dueDate={loan.dueDate}
                renewCount={loan.renewCount}
                status={loan.status}
                overdue={new Date(loan.dueDate) < new Date()}
              />
            ))}
          </div>
        )}
      </section>
    </div>
  );
};
