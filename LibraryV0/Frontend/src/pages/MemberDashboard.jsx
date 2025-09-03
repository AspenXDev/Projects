// path: Frontend/src/pages/MemberDashboard.jsx
import React, { useEffect, useState } from "react";
import { useAuth } from "../contexts/AuthContext.jsx";
import { getMyLoans } from "../services/LoanService.js";
import { getMyFines } from "../services/FineService.js";
import { getMyMemberInfo } from "../services/MemberService.js";
import { BookCard } from "../components/books/BookCard.jsx";
import "../styling/Dashboard.css";

export const MemberDashboard = () => {
  const { user } = useAuth();
  const [member, setMember] = useState(null);
  const [loans, setLoans] = useState([]);
  const [fines, setFines] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!user) return;

    setLoading(true);
    Promise.allSettled([getMyMemberInfo(), getMyLoans(), getMyFines()])
      .then(([memberRes, loansRes, finesRes]) => {
        if (memberRes.status === "fulfilled") setMember(memberRes.value);
        if (loansRes.status === "fulfilled") setLoans(loansRes.value || []);
        if (finesRes.status === "fulfilled") setFines(finesRes.value || []);
      })
      .catch(console.error)
      .finally(() => setLoading(false));
  }, [user]);

  // Derived fields
  const totalUnpaidFines = fines.reduce(
    (sum, f) => sum + Number(f.amount || 0),
    0
  );

  const activeLoans = loans.filter((l) => l.status === "Active");
  const overdueLoans = activeLoans.filter(
    (l) => new Date(l.dueDate) < new Date()
  );

  const membershipValid =
    member?.membershipValidUntil &&
    new Date(member.membershipValidUntil) > new Date();

  return (
    <div className="dashboard-container">
      <h2>
        Welcome,{" "}
        {member?.fullName || user?.fullName || user?.username || "Member"}
      </h2>

      <section className="summary-cards">
        <div className="card">
          <h3>Membership</h3>
          <p>
            Status: <strong>{membershipValid ? "Active" : "Expired"}</strong>
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
            Active: <strong>{activeLoans.length}</strong>
          </p>
          <p>
            Overdue:{" "}
            <strong style={{ color: "crimson" }}>{overdueLoans.length}</strong>
          </p>
        </div>
      </section>

      <section className="loans-section">
        <h3>Your Active Loans</h3>
        {loading ? (
          <p className="muted">Loading…</p>
        ) : activeLoans.length === 0 ? (
          <p className="muted">No active loans.</p>
        ) : (
          <div className="books-grid">
            {activeLoans.map((loan) => (
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

      {overdueLoans.length > 0 && (
        <section className="overdue-section" style={{ marginTop: 20 }}>
          <h3 style={{ color: "crimson" }}>⚠️ Overdue Loans</h3>
          <ul>
            {overdueLoans.map((l) => (
              <li key={l.loanId}>
                {l.book?.title ?? "Untitled"} — due{" "}
                {new Date(l.dueDate).toLocaleDateString()}
              </li>
            ))}
          </ul>
        </section>
      )}
    </div>
  );
};
