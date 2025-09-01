// path: Frontend/src/pages/LibrarianDashboard.jsx
import React, { useEffect, useState } from "react";
import { getAllBooks } from "../services/BookService.js";
import { getAllMembers } from "../services/MemberService.js";
import { getAllLoans } from "../services/LoanService.js";
import { getAllFines } from "../services/FineService.js";
import { BookCard } from "../components/books/BookCard.jsx";

import "../styling/Dashboard.css";

const unwrap = (r) => (r && r.data !== undefined ? r.data : r);

export const LibrarianDashboard = () => {
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [loans, setLoans] = useState([]);
  const [fines, setFines] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    Promise.allSettled([
      getAllBooks().then(unwrap),
      getAllMembers().then(unwrap),
      getAllLoans().then(unwrap),
      getAllFines().then(unwrap),
    ])
      .then((results) => {
        const [b, m, l, f] = results;
        if (b.status === "fulfilled")
          setBooks(Array.isArray(b.value) ? b.value : []);
        if (m.status === "fulfilled")
          setMembers(Array.isArray(m.value) ? m.value : []);
        if (l.status === "fulfilled")
          setLoans(Array.isArray(l.value) ? l.value : []);
        if (f.status === "fulfilled")
          setFines(Array.isArray(f.value) ? f.value : []);
      })
      .catch((e) => console.error("LibrarianDashboard fetch error", e))
      .finally(() => setLoading(false));
  }, []);

  const overdueLoans = (loans || []).filter(
    (l) => l.status === "Active" && new Date(l.dueDate) < new Date()
  );
  const totalUnpaidFines = (fines || [])
    .filter((f) => !f.paid)
    .reduce((s, f) => s + Number(f.amount || 0), 0);

  return (
    <div className="dashboard-container">
      <h2>Librarian Dashboard</h2>

      <section className="summary-cards">
        <div className="card">
          <h3>Books</h3>
          <p>
            Total: <strong>{books.length}</strong>
          </p>
          <p>
            Borrowed:{" "}
            <strong>
              {
                books.filter(
                  (b) => (b.availableCopies ?? 0) < (b.totalCopies ?? 0)
                ).length
              }
            </strong>
          </p>
        </div>

        <div className="card">
          <h3>Members</h3>
          <p>
            Total: <strong>{members.length}</strong>
          </p>
          <p>
            Active:{" "}
            <strong>
              {members.filter((m) => m.membershipStatus === "Active").length}
            </strong>
          </p>
          <p>
            Expired:{" "}
            <strong>
              {members.filter((m) => m.membershipStatus === "Expired").length}
            </strong>
          </p>
        </div>

        <div className="card">
          <h3>Loans</h3>
          <p>
            Total Active:{" "}
            <strong>
              {(loans || []).filter((l) => l.status === "Active").length}
            </strong>
          </p>
          <p>
            Overdue:{" "}
            <strong style={{ color: "crimson" }}>{overdueLoans.length}</strong>
          </p>
        </div>

        <div className="card">
          <h3>Fines</h3>
          <p>
            Total Unpaid: <strong>${totalUnpaidFines.toFixed(2)}</strong>
          </p>
        </div>
      </section>

      <section className="books-section">
        <h3>Books Overview</h3>
        {loading ? (
          <p className="muted">Loading…</p>
        ) : books.length === 0 ? (
          <p className="muted">No books found.</p>
        ) : (
          <div className="books-grid">
            {books.map((b) => (
              <BookCard key={b.bookId} book={b} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
};
