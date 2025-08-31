// path: Frontend/src/components/books/BookCard.jsx
import React from "react";
import "../../styling/BookCard.css";

export const BookCard = ({ book, dueDate, renewCount, status }) => {
  if (!book) return null;

  return (
    <div className="book-card">
      <h3 className="book-card__title">{book.title}</h3>
      <p>
        Author: <strong>{book.author}</strong>
      </p>
      <p>
        ISBN: <strong>{book.isbn}</strong>
      </p>

      {(dueDate || status || typeof renewCount === "number") && (
        <div className="book-card__loan-meta">
          {status && (
            <p className="status">
              Status: <strong>{status}</strong>
            </p>
          )}
          {dueDate && (
            <p>
              Due: <strong>{new Date(dueDate).toLocaleDateString()}</strong>
            </p>
          )}
          {typeof renewCount === "number" && (
            <p>
              Renewals: <strong>{renewCount}</strong>
            </p>
          )}
        </div>
      )}
    </div>
  );
};
