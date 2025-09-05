// path: Frontend/src/pages/MemberDashboardMockWithNavbar.jsx
import React from "react";
import { BookCard } from "../components/books/BookCard.jsx";
import "../styling/Dashboard.css";

// Hardcoded Navbar
const Navbar = ({ user }) => {
  const role = "member";
  const displayName = user.fullName || user.username;
  return (
    <nav
      style={{
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
        padding: "12px 20px",
        backgroundColor: "#f5f8fb",
        borderBottom: "1px solid #e0e6ef",
      }}
    >
      <div
        style={{
          fontWeight: 700,
          color: "#003366",
          display: "flex",
          gap: 12,
          alignItems: "center",
        }}
      >
        <button
          style={{
            padding: "6px 10px",
            backgroundColor: "transparent",
            color: "#003366",
            border: "1px solid #cfe1f8",
            borderRadius: 6,
            cursor: "pointer",
          }}
        >
          Home
        </button>
        <span>{`Welcome, ${displayName}`}</span>
      </div>
      <div style={{ display: "flex", gap: "8px" }}>
        <button
          style={{
            padding: "6px 12px",
            backgroundColor: "#228B22",
            color: "#fff",
            border: "none",
            borderRadius: 4,
            cursor: "pointer",
          }}
        >
          Dashboard
        </button>
        <button
          style={{
            padding: "6px 12px",
            backgroundColor: "#003366",
            color: "#fff",
            border: "none",
            borderRadius: 4,
            cursor: "pointer",
          }}
        >
          Logout
        </button>
      </div>
    </nav>
  );
};

export const MemberDashboardMockWithNavbar = () => {
  const user = { fullName: "Bob Smith", username: "bob_smith" };

  const member = { fullName: "Bob Smith", membershipValidUntil: "2025-03-10" };
  const loans = [
    {
      loanId: 101,
      book: { title: "National Geographic - January 2025" },
      dueDate: "2025-08-10",
      renewCount: 0,
      status: "Active",
      fine: 2.0,
    },
    {
      loanId: 102,
      book: { title: "Python Programming 101" },
      dueDate: "2025-08-15",
      renewCount: 1,
      status: "Active",
      fine: 5.5,
    },
  ];

  const overdueLoans = loans.filter((l) => new Date(l.dueDate) < new Date());
  const totalUnpaidFines = overdueLoans.reduce((sum, l) => sum + l.fine, 0);
  const activeLoans = loans;

  return (
    <div>
      <Navbar user={user} />

      <div className="dashboard-container">
        <h2>Welcome, {member.fullName}</h2>

        <section className="summary-cards">
          <div className="card">
            <h3>Membership</h3>
            <p>
              Status: <strong>Expired</strong>
            </p>
            <p>
              Valid Until:{" "}
              <strong>
                {new Date(member.membershipValidUntil).toLocaleDateString()}
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
              <strong style={{ color: "crimson" }}>
                {overdueLoans.length}
              </strong>
            </p>
          </div>
        </section>

        <section className="loans-section">
          <h3>Your Active Loans</h3>
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
        </section>

        {overdueLoans.length > 0 && (
          <section className="overdue-section" style={{ marginTop: 20 }}>
            <h3 style={{ color: "crimson" }}>⚠️ Overdue Loans</h3>
            <ul>
              {overdueLoans.map((l) => (
                <li key={l.loanId}>
                  {l.book?.title ?? "Untitled"} — due{" "}
                  {new Date(l.dueDate).toLocaleDateString()} (fine: $
                  {l.fine.toFixed(2)})
                </li>
              ))}
            </ul>
          </section>
        )}
      </div>
    </div>
  );
};
