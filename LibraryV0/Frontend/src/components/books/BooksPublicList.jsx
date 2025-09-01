// path: Frontend/src/components/books/BooksPublicList.jsx
import React, { useEffect, useState } from "react";
import { getAllPublicBooks } from "../../services/BookService.js";
import { BookCard } from "./BookCard.jsx";
import "../../styling/BooksLists.css";

export function BooksPublicList() {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(true);
    getAllPublicBooks()
      .then((res) => {
        // support both direct array or { data: [...] }
        const data = res && res.data !== undefined ? res.data : res;
        setBooks(Array.isArray(data) ? data : []);
      })
      .catch((err) => {
        console.error("Error loading public books:", err);
        setBooks([]);
      })
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <p style={{ padding: 20 }}>Loading books…</p>;

  return (
    <div style={{ padding: 20 }}>
      <h2 style={{ marginBottom: 12 }}>Available Books</h2>
      {books.length === 0 ? (
        <p className="muted">No books found.</p>
      ) : (
        <div className="books-grid">
          {books.map((book, idx) => {
            const key =
              book.bookId ?? book.book_id ?? book.id ?? book.isbn ?? idx;
            return <BookCard key={key} book={book} />;
          })}
        </div>
      )}
    </div>
  );
}
