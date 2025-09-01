import React from "react";
import "../../styling/BookCard.css";

export function PublicBookCard({ book }) {
  return (
    <div className="public-card">
      <div className="public-card__head">
        <h3 className="public-card__title">{book.title ?? book.name}</h3>
        <div
          className={`badge ${
            book.availableCopies > 0 ? "available" : "borrowed"
          }`}
        >
          {book.availableCopies > 0 ? "Available" : "Borrowed"}
        </div>
      </div>
      <div className="public-card__meta">
        <div className="meta-line">
          <div className="meta-label">Author</div>
          <div className="meta-value">{book.author}</div>
        </div>
        <div className="meta-line">
          <div className="meta-label">ISBN</div>
          <div className="meta-value">{book.isbn}</div>
        </div>
      </div>
    </div>
  );
}
