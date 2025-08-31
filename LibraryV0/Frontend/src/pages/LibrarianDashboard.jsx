// path: Frontend/src/pages/LibrarianDashboard.jsx
import React, { useEffect, useState } from "react";
import { getAllBooks } from "../services/BookService";
import { getAllLoans } from "../services/LoanService";
import { getAllFines } from "../services/FineService";
import { getAllMembers } from "../services/MemberService";
import { BookCard } from "../components/books/BookCard.jsx";
import "../styling/Dashboard.css";

export const LibrarianDashboard = () => {
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [loans, setLoans] = useState([]);
  const [fines, setFines] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      getAllBooks().then((r) => r.data || []),
      getAllMembers().then((r) => r.data || []),
      getAllLoans().then((r) => r.data || []),
      getAllFines().then((r) => r.data || []),
    ])
      .then(([b, m, l, f]) => {
        setBooks(b);
        setMembers(m);
        setLoans(l);
        setFines(f);
      })
      .catch((err) => console.error(err))
      .finally(() => setLoading(false));
  }, []);

  const overdueLoans = loans.filter(
    (l) => l.status === "Active" && new Date(l.dueDate) < new Date()
  );

  const totalUnpaidFines = fines
    .filter((f) => !f.paid)
    .reduce((sum, f) => sum + Number(f.amount || 0), 0);

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
            <strong>{loans.filter((l) => l.status === "Active").length}</strong>
          </p>
          <p>
            Overdue: <strong>{overdueLoans.length}</strong>
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
            {books.map((book) => (
              <BookCard key={book.bookId} book={book} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
};
