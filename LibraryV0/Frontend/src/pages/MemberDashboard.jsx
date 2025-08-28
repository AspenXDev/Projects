import React, { useEffect, useState } from "react";
import { api } from "../services/api.js"; // JWT attached automatically for protected calls
import { BooksList } from "../components/books/BooksList.jsx";

export function MemberDashboard() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const res = await api.get("/books"); // protected API, JWT attached
        setBooks(res.data);
      } catch (err) {
        console.error(err);
        setError("Failed to load books");
      } finally {
        setLoading(false);
      }
    };
    fetchBooks();
  }, []);

  if (loading) return <div>Loading books...</div>;
  if (error) return <div style={{ color: "red" }}>{error}</div>;

  return (
    <div style={{ padding: 20 }}>
      <h2>Books</h2>
      <BooksList books={books} />
    </div>
  );
}
