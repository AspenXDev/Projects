import { useContext } from "react";
import { AuthContext } from "../contexts/AuthContext";

const BookCard = ({ book }) => {
  return (
    <div className="book-card">
      <h2>{book.title}</h2>
      <p>
        <strong>Author:</strong> {book.author}
      </p>
      <p>
        <strong>ISBN:</strong> {book.isbn}
      </p>
      <p>
        <strong>Status:</strong> {book.status}
      </p>
      <p>
        <strong>Available Copies:</strong> {book.available_copies}
      </p>
    </div>
  );
};

export default BookCard;
