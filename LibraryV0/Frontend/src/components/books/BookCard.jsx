import React from "react";
import "../../styling/BookCard.css";

export function BookCard({ book }) {
  return (
    <div className="book-card">
      <h3>{book.title}</h3>
      <p>Author: {book.author}</p>
      <p>ISBN: {book.isbn}</p>
    </div>
  );
}
