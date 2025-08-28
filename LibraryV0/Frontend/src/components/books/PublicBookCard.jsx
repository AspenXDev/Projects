// PATH: src/components/books/PublicBookCard.jsx
import React from "react";
import "../../styling/BookCard.css"; // <-- correct relative path: from components/books -> src/styling
import { AuthProvider, useAuth } from "../../contexts/AuthContext.jsx";

export function PublicBookCard({ book }) {
  const available =
    book.availableCopies ?? book.available_copies ?? book.available_copies ?? 0;
  const status = book.status ?? "Unknown";
  const section = book.locationSection ?? book.location_section ?? "";
  const shelf = book.locationShelf ?? book.location_shelf ?? "";
  const row = book.locationRow ?? book.location_row ?? "";

  return (
    <article className="public-card">
      <header className="public-card__head">
        <h3 className="public-card__title">{book.title}</h3>
        <span className={`badge ${status.toLowerCase()}`}>{status}</span>
      </header>

      <div className="public-card__meta">
        <div className="meta-line">
          <span className="meta-label">Author</span>
          <span className="meta-value">{book.author}</span>
        </div>
        <div className="meta-line">
          <span className="meta-label">Available</span>
          <span className="meta-value">{available}</span>
        </div>
        <div className="meta-line">
          <span className="meta-label">Location</span>
          <span className="meta-value">
            {section ? `${section} / Shelf ${shelf}, Row ${row}` : "—"}
          </span>
        </div>
      </div>
    </article>
  );
}
