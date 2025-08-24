import React, { useEffect, useState } from "react";
import { getAllBooks } from "../services/bookService";
import { Link } from "react-router-dom";

const LandingPage = () => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    getAllBooks()
      .then((res) => setBooks(res.data))
      .catch((err) => console.error("Error fetching books:", err));
  }, []);

  return (
    <div style={{ padding: 20 }}>
      <h1>Library Catalog</h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(2, 1fr)",
          gap: 10,
          overflowX: "auto",
        }}
      >
        {books.map((book) => (
          <div
            key={book.book_id}
            style={{ border: "1px solid #000", padding: 10 }}
          >
            <h2>{book.title}</h2>
            <p>{book.author}</p>
            <p>Status: {book.status}</p>
            <p>
              Location: {book.location_section}-{book.location_shelf}-
              {book.location_row}
            </p>
          </div>
        ))}
      </div>

      <div style={{ marginTop: 20 }}>
        <Link to="/login">Login</Link>
      </div>
    </div>
  );
};

export default LandingPage;
