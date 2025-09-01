// path: Frontend/src/components/books/BookCard.jsx
import React from "react";
import "../../styling/BookCard.css";

/**
 * BookCard - robust reading of fields returned from backend
 * Expects book object with any of:
 *  - availableCopies | available_copies | available (boolean) | total_copies
 *  - location (string) OR location_section + location_shelf + location_row
 *  - title, author, category
 */
export function BookCard({ book }) {
  if (!book) return null;

  // derive availability
  const availableCopies =
    book.availableCopies ??
    book.available_copies ??
    (typeof book.available === "boolean"
      ? book.available
        ? 1
        : 0
      : undefined) ??
    book.total_copies ??
    0;

  const isAvailable =
    typeof book.available === "boolean"
      ? book.available
      : Number(availableCopies) > 0;

  // derive location string
  const locSection = book.location_section ?? book.locationSection ?? null;
  const locShelf =
    book.location_shelf ??
    book.locationShelf ??
    book.location_shelf_number ??
    null;
  const locRow = book.location_row ?? book.locationRow ?? null;

  const locationParts = [];
  if (book.location) locationParts.push(book.location);
  if (locSection) locationParts.push(locSection);
  if (locShelf || locRow) {
    const shelf = locShelf ? `Shelf ${locShelf}` : null;
    const row = locRow ? `Row ${locRow}` : null;
    const shelfRow = [shelf, row].filter(Boolean).join(" • ");
    if (shelfRow) locationParts.push(shelfRow);
  }
  const locationString =
    locationParts.length > 0 ? locationParts.join(" • ") : "Unknown";

  // category badge (if present)
  const category = book.category || book.cat || null;

  return (
    <article className="book-card" aria-label={book.title ?? "Book"}>
      <div className="book-card-top">
        <div>
          <h3 className="book-title">{book.title ?? "Untitled"}</h3>
          {category && <span className="book-badge">{category}</span>}
        </div>
        <div
          className={`book-availability ${
            isAvailable ? "available" : "unavailable"
          }`}
        >
          {isAvailable ? "Available" : "Checked Out"}
        </div>
      </div>

      <div className="book-meta">
        <div className="book-author">
          <em>by {book.author ?? "—"}</em>
        </div>
        <div className="book-location">Location: {locationString}</div>
      </div>

      <div className="book-footer">
        {/* optional extra meta if backend provides them */}
        {typeof availableCopies !== "undefined" && (
          <div>
            Copies: <strong>{availableCopies}</strong>
          </div>
        )}
        {book.published_year && <div>Year: {book.published_year}</div>}
      </div>
    </article>
  );
}
