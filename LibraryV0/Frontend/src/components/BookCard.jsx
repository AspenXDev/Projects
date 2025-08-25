import React from "react";
import "../styling/BookCard.css";

const BookCard = ({ book }) => {
  return (
    <div className="book-card">
      <h2>{book.title}</h2>
      <p>
        <strong>Author:</strong> {book.author}
      </p>
      <p>
        <strong>Available Copies:</strong> {book.available_copies}
      </p>
      <p>
        <strong>Status:</strong> {book.status}
      </p>
      <p>
        <strong>Location:</strong> {book.location_section} / Shelf{" "}
        {book.location_shelf}, Row {book.location_row}
      </p>
    </div>
  );
};

export default BookCard;
