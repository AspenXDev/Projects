// path: Frontend/src/components/books/BookCard.jsx
import React from "react";
import "../../styling/BookCard.css";

export function BookCard({ book, dueDate, renewCount, status, overdue }) {
  if (!book) return null;

  // Determine availability from book.status or prop status
  const availability =
    book.status === "Available" || status === "Available"
      ? "Available"
      : book.status || status || "Unavailable";

  return (
    <div
      className={`book-card ${
        availability === "Available" ? "available" : "unavailable"
      }`}
    >
      <h3>{book?.title ?? book?.name ?? "Untitled"}</h3>
      <p>Author: {book?.author ?? "—"}</p>
      <p>
        Location: Section {book?.locationSection ?? "—"}, Shelf{" "}
        {book?.locationShelf ?? "—"}, Row {book?.locationRow ?? "—"}
      </p>
      <p>
        Status: <strong>{availability}</strong>
      </p>

      {/* Loan info if provided */}
      {typeof dueDate !== "undefined" && dueDate && (
        <p>Due: {new Date(dueDate).toLocaleDateString()}</p>
      )}
      {typeof renewCount !== "undefined" && <p>Renewals: {renewCount}</p>}
      {overdue && <p style={{ color: "crimson", fontWeight: 700 }}>Overdue</p>}
    </div>
  );
}
