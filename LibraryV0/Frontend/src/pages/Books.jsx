import { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import BookCard from "../components/BookCard";
import api from "../services/api";

export default function Books() {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    // placeholder until API is ready
    setBooks([
      { id: 1, title: "Book A", status: "Available" },
      { id: 2, title: "Book B", status: "Borrowed" },
    ]);

    // Uncomment when backend is ready:
    // api.get('/books').then(res => setBooks(res.data));
  }, []);

  return (
    <div>
      <Navbar />
      <h1>Books</h1>
      {books.map((book) => (
        <BookCard key={book.id} book={book} />
      ))}
    </div>
  );
}
