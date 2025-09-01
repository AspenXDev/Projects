// path: Frontend/src/components/books/BooksPublicList.jsx
import React, { useEffect, useState } from "react";
import { getAllPublicBooks } from "../../services/BookService.js";

export function BooksPublicList() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    getAllPublicBooks().then(setBooks).catch(console.error);
  }, []);

  return (
    <div className="p-6">
      <h2 className="text-xl font-bold mb-4">Available Books</h2>
      <ul className="space-y-2">
        {books.map((book, index) => (
          <li
            key={book.id ?? book.bookId ?? index}
            className="p-4 border rounded-md shadow-sm bg-white hover:shadow-md"
          >
            <strong>{book.title}</strong> by {book.author} —{" "}
            {book.availableCopies > 0 ? "Available" : "Checked Out"}
          </li>
        ))}
      </ul>
    </div>
  );
}
