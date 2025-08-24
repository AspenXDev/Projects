import React, { useEffect, useState } from "react";
import BookCard from "./BookCard";
import "../styling/BooksList.css";

export default function BooksList() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const res = await fetch("http://localhost:8081/books");
        const data = await res.json();
        setBooks(data);
      } catch (err) {
        console.error("Error fetching books:", err);
      }
    };
    fetchBooks();
  }, []);

  return (
    <div className="books-grid">
      {books.length > 0 ? (
        books.map((book) => <BookCard key={book.book_id} book={book} />)
      ) : (
        <p
          style={{
            color: "#003366",
            fontSize: "1.2rem",
            textAlign: "center",
            width: "100%",
          }}
        >
          No books available
        </p>
      )}
    </div>
  );
}
