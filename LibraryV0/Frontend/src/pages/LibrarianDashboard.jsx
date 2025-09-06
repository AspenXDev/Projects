// path: Frontend/src/pages/LibrarianDashboard.jsx
import React, { useEffect, useState } from "react";
import { api } from "../services/api";
import "../styling/LandingPage.css"; // for same slim card/grid styling

export function LibrarianDashboard() {
  const [books, setBooks] = useState([]);
  const [members, setMembers] = useState([]);
  const [loans, setLoans] = useState([]);
  const [fines, setFines] = useState([]);

  // Fetch everything on mount
  useEffect(() => {
    fetchBooks();
    fetchMembers();
    fetchLoans();
    fetchFines();
  }, []);

  const fetchBooks = async () => {
    try {
      const res = await api.get("/books");
      setBooks(res.data);
    } catch (err) {
      console.error("Error fetching books:", err);
    }
  };

  const fetchMembers = async () => {
    try {
      const res = await api.get("/members");
      setMembers(res.data);
    } catch (err) {
      console.error("Error fetching members:", err);
    }
  };

  const fetchLoans = async () => {
    try {
      const res = await api.get("/loans");
      setLoans(res.data);
    } catch (err) {
      console.error("Error fetching loans:", err);
    }
  };

  const fetchFines = async () => {
    try {
      const res = await api.get("/fines");
      setFines(res.data);
    } catch (err) {
      console.error("Error fetching fines:", err);
    }
  };

  // CRUD helpers
  const deleteBook = async (id) => {
    await api.delete(`/books/${id}`);
    fetchBooks();
  };

  const deleteMember = async (id) => {
    await api.delete(`/members/${id}`);
    fetchMembers();
  };

  const deleteLoan = async (id) => {
    await api.delete(`/loans/${id}`);
    fetchLoans();
  };

  const deleteFine = async (id) => {
    await api.delete(`/fines/${id}`);
    fetchFines();
  };

  return (
    <div className="landing-container">
      <header className="landing-header">
        <h1>Librarian Dashboard</h1>
      </header>

      {/* Books */}
      <section className="all-books" style={{ marginTop: 24 }}>
        <div className="flex justify-between items-center mb-3">
          <h2>Books</h2>
          <button className="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700">
            Add Book
          </button>
        </div>
        <div className="books-grid">
          {books.map((book) => (
            <div key={book.id} className="book-card">
              <h3>{book.title}</h3>
              <p>{book.author}</p>
              <p>{book.genre}</p>
              <div className="card-actions">
                <button className="mr-2 text-blue-600">Update</button>
                <button
                  className="text-red-600"
                  onClick={() => deleteBook(book.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* Members */}
      <section className="all-books" style={{ marginTop: 24 }}>
        <div className="flex justify-between items-center mb-3">
          <h2>Members</h2>
          <button className="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700">
            Add Member
          </button>
        </div>
        <div className="books-grid">
          {members.map((m) => (
            <div key={m.id} className="book-card">
              <h3>{m.name}</h3>
              <p>Joined: {m.joinDate}</p>
              <p>Expires: {m.expiryDate}</p>
              <p>Unpaid Fines: ${m.unpaidFines || 0}</p>
              <div className="card-actions">
                <button className="mr-2 text-blue-600">Update</button>
                <button
                  className="text-red-600"
                  onClick={() => deleteMember(m.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* Loans */}
      <section className="all-books" style={{ marginTop: 24 }}>
        <div className="flex justify-between items-center mb-3">
          <h2>Loans</h2>
          <button className="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700">
            Add Loan
          </button>
        </div>
        <div className="books-grid">
          {loans.map((loan) => (
            <div key={loan.id} className="book-card">
              <h3>{loan.bookTitle}</h3>
              <p>Member: {loan.memberName}</p>
              <p>Due: {loan.dueDate}</p>
              <p>Status: {loan.status}</p>
              <div className="card-actions">
                <button className="mr-2 text-blue-600">Update</button>
                <button
                  className="text-red-600"
                  onClick={() => deleteLoan(loan.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </section>

      {/* Fines */}
      <section className="all-books" style={{ marginTop: 24 }}>
        <div className="flex justify-between items-center mb-3">
          <h2>Fines</h2>
          <button className="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700">
            Add Fine
          </button>
        </div>
        <div className="books-grid">
          {fines.map((fine) => (
            <div key={fine.id} className="book-card">
              <h3>{fine.memberName}</h3>
              <p>Amount: ${fine.amount}</p>
              <p>Reason: {fine.reason}</p>
              <p>Paid: {fine.paid ? "Yes" : "No"}</p>
              <div className="card-actions">
                <button className="mr-2 text-blue-600">Update</button>
                <button
                  className="text-red-600"
                  onClick={() => deleteFine(fine.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
}
