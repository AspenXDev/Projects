// path: Frontend/src/pages/LibrarianDashboard.jsx
import React, { useEffect, useState } from "react";
import {
  getAllBooks,
  createBook,
  updateBook,
  deleteBook,
} from "../services/BookService.js";
import {
  getAllMembers,
  createMember,
  updateMember,
  deleteMember,
} from "../services/MemberService.js";
import { getAllLoans } from "../services/LoanService.js";
import { getAllFines } from "../services/FineService.js";
import { BookCard } from "../components/books/BookCard.jsx";

import "../styling/Dashboard.css";

const unwrap = (r) => (r && r.data !== undefined ? r.data : r);

const InlineForm = ({
  title,
  fields,
  onSubmit,
  initialValues = {},
  onCancel,
}) => {
  const [values, setValues] = useState(
    fields.reduce(
      (acc, f) => ({ ...acc, [f.name]: initialValues[f.name] || "" }),
      {}
    )
  );
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setValues({ ...values, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    try {
      await onSubmit(values);
      setValues(fields.reduce((acc, f) => ({ ...acc, [f.name]: "" }), {}));
      if (onCancel) onCancel();
    } catch (err) {
      setError(err.response?.data?.error || err.message || "Submission failed");
    }
  };

  return (
    <div style={{ marginBottom: 24 }}>
      <h3>{title}</h3>
      <form
        onSubmit={handleSubmit}
        style={{ display: "flex", flexDirection: "column", maxWidth: 400 }}
      >
        {fields.map((f) => (
          <input
            key={f.name}
            type={f.type || "text"}
            name={f.name}
            placeholder={f.placeholder}
            value={values[f.name]}
            onChange={handleChange}
            required={f.required !== false}
            style={{ marginBottom: 10, padding: 8 }}
          />
        ))}
        <div style={{ display: "flex", gap: 8 }}>
          <button type="submit" style={{ padding: 8 }}>
            Submit
          </button>
          {onCancel && (
            <button type="button" style={{ padding: 8 }} onClick={onCancel}>
              Cancel
            </button>
          )}
        </div>
        {error && <p style={{ color: "red", marginTop: 10 }}>{error}</p>}
      </form>
    </div>
  );
};

export const LibrarianDashboard = () => {
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [loans, setLoans] = useState([]);
  const [fines, setFines] = useState([]);
  const [loading, setLoading] = useState(true);
  const [editingBook, setEditingBook] = useState(null);
  const [editingMember, setEditingMember] = useState(null);

  const fetchAll = async () => {
    setLoading(true);
    try {
      const results = await Promise.allSettled([
        getAllBooks().then(unwrap),
        getAllMembers().then(unwrap),
        getAllLoans().then(unwrap),
        getAllFines().then(unwrap),
      ]);
      const [b, m, l, f] = results;
      if (b.status === "fulfilled")
        setBooks(Array.isArray(b.value) ? b.value : []);
      if (m.status === "fulfilled")
        setMembers(Array.isArray(m.value) ? m.value : []);
      if (l.status === "fulfilled")
        setLoans(Array.isArray(l.value) ? l.value : []);
      if (f.status === "fulfilled")
        setFines(Array.isArray(f.value) ? f.value : []);
    } catch (e) {
      console.error("LibrarianDashboard fetch error", e);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchAll();
  }, []);

  const overdueLoans = (loans || []).filter(
    (l) => l.status === "Active" && new Date(l.dueDate) < new Date()
  );
  const totalUnpaidFines = (fines || [])
    .filter((f) => !f.paid)
    .reduce((s, f) => s + Number(f.amount || 0), 0);

  // Book submission
  const handleBookSubmit = async (data) => {
    if (editingBook) {
      await updateBook(editingBook.bookId, data);
      setEditingBook(null);
    } else {
      await createBook(data);
    }
    await fetchAll();
  };

  // Member submission
  const handleMemberSubmit = async (data) => {
    if (editingMember) {
      await updateMember(editingMember.memberId, data);
      setEditingMember(null);
    } else {
      await createMember(data);
    }
    await fetchAll();
  };

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

      {/* Books Section */}
      <section className="books-section">
        <h3>Books Overview</h3>
        <InlineForm
          title={editingBook ? "Edit Book" : "Create New Book"}
          fields={[
            { name: "title", placeholder: "Title" },
            { name: "author", placeholder: "Author" },
            { name: "isbn", placeholder: "ISBN" },
            {
              name: "publishedYear",
              placeholder: "Published Year",
              type: "number",
            },
            { name: "category", placeholder: "Category" },
            {
              name: "totalCopies",
              placeholder: "Total Copies",
              type: "number",
            },
            {
              name: "availableCopies",
              placeholder: "Available Copies",
              type: "number",
            },
          ]}
          onSubmit={handleBookSubmit}
          initialValues={editingBook || {}}
          onCancel={() => setEditingBook(null)}
        />

        {loading ? (
          <p className="muted">Loading…</p>
        ) : books.length === 0 ? (
          <p className="muted">No books found.</p>
        ) : (
          <div className="books-grid">
            {books.map((b) => (
              <div key={b.bookId} style={{ position: "relative" }}>
                <BookCard book={b} onClick={() => setEditingBook(b)} />
                <button
                  style={{ position: "absolute", top: 4, right: 4 }}
                  onClick={async (e) => {
                    e.stopPropagation();
                    if (window.confirm("Delete this book?")) {
                      await deleteBook(b.bookId);
                      await fetchAll();
                    }
                  }}
                >
                  Delete
                </button>
              </div>
            ))}
          </div>
        )}
      </section>

      {/* Members Section */}
      <section className="members-section">
        <h3>Members Overview</h3>
        <InlineForm
          title={editingMember ? "Edit Member" : "Create New Member"}
          fields={[
            { name: "username", placeholder: "Username" },
            { name: "fullName", placeholder: "Full Name" },
            { name: "email", placeholder: "Email", type: "email" },
            { name: "password", placeholder: "Password", type: "password" },
          ]}
          onSubmit={handleMemberSubmit}
          initialValues={editingMember || {}}
          onCancel={() => setEditingMember(null)}
        />

        {loading ? (
          <p className="muted">Loading…</p>
        ) : members.length === 0 ? (
          <p className="muted">No members found.</p>
        ) : (
          <div className="members-grid">
            {members.map((m) => (
              <div
                key={m.memberId}
                className="member-card"
                style={{ position: "relative", cursor: "pointer" }}
                onClick={() => setEditingMember(m)}
              >
                <strong>{m.fullName}</strong>
                <p>Username: {m.username}</p>
                <p>Email: {m.email}</p>
                <p>Status: {m.membershipStatus}</p>

                <button
                  style={{ position: "absolute", top: 4, right: 4 }}
                  onClick={async (e) => {
                    e.stopPropagation();
                    if (window.confirm("Delete this member?")) {
                      await deleteMember(m.memberId);
                      await fetchAll();
                    }
                  }}
                >
                  Delete
                </button>
              </div>
            ))}
          </div>
        )}
      </section>
    </div>
  );
};
