// path : Frontend/src/pages/LandingPage.jsx
import React, { useEffect, useState } from "react";
import { getAllBooks } from "../services/BookService.js";
import { useAuth } from "../contexts/AuthContext.jsx";
import { BookCard } from "../components/books/BookCard.jsx";
import "../styling/LandingPage.css";

const unwrap = (r) => (r && r.data !== undefined ? r.data : r);

export const LandingPage = () => {
  const { user, logout } = useAuth();
  const [books, setBooks] = useState([]);
  const [featured, setFeatured] = useState([]);
  const [search, setSearch] = useState("");
  const [filterCategory, setFilterCategory] = useState("");

  useEffect(() => {
    getAllBooks()
      .then(unwrap)
      .then((data) => {
        setBooks(data || []);
        if (data?.length > 3) {
          const shuffled = [...data].sort(() => 0.5 - Math.random());
          setFeatured(shuffled.slice(0, 3));
        } else {
          setFeatured(data);
        }
      })
      .catch((err) => console.error("Error fetching books:", err));
  }, []);

  const filteredBooks = books.filter(
    (b) =>
      (!search || b.title.toLowerCase().includes(search.toLowerCase())) &&
      (!filterCategory ||
        (b.category ?? "").toLowerCase() === filterCategory.toLowerCase())
  );

  const categories = Array.from(
    new Set(books.map((b) => b.category).filter(Boolean))
  );

  return (
    <div className="landing-container">
      <header className="landing-header">
        <h1>Welcome to the Library</h1>
        {!user ? (
          <button
            className="login-btn"
            onClick={() => (window.location.href = "/login")}
          >
            Login
          </button>
        ) : (
          <button className="login-btn" onClick={logout}>
            Logout
          </button>
        )}
      </header>

      <section className="search-filter">
        <input
          type="text"
          placeholder="Search by title..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
        <select
          value={filterCategory}
          onChange={(e) => setFilterCategory(e.target.value)}
        >
          <option value="">All Categories</option>
          {categories.map((cat) => (
            <option key={cat} value={cat}>
              {cat}
            </option>
          ))}
        </select>
      </section>

      {featured.length > 0 && (
        <section className="featured-books">
          <h2>New In</h2>
          <div className="books-grid">
            {featured.map((book) => (
              <BookCard key={book.bookId} book={book} />
            ))}
          </div>
        </section>
      )}

      <section className="all-books">
        <h2>All Books</h2>
        {filteredBooks.length === 0 ? (
          <p className="muted">No books found.</p>
        ) : (
          <div className="books-grid">
            {filteredBooks.map((book) => (
              <BookCard key={book.bookId} book={book} />
            ))}
          </div>
        )}
      </section>
    </div>
  );
};
