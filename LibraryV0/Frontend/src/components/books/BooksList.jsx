// src/components/books/BooksList.jsx

import React from "react";
import { BookCard } from "./BookCard.jsx";
import "../../styling/BooksLists.css";

export function BooksList({ books }) {
  if (!books || books.length === 0) return <p>No books available.</p>;

  return (
    <div className="books-grid">
      {books.map((book) => (
        <BookCard key={book.book_id} book={book} />
      ))}
    </div>
  );
}
