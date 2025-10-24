/**
 * LandingPage component represents the main page of the Library application.
 *
 * This component is responsible for:
 * - Fetching the list of all books via the getAllBooks service call on mount.
 * - Managing state for the complete book list, featured (new arrival) books, and search filters.
 * - Initializing a featured books section using a fixed list of titles ("Pride and Prejudice",
 *   "1984", "To Kill A Mockingbird") or defaulting to the first three books if none match.
 * - Allowing users to filter books by title, author, ISBN, and category.
 * - Rendering separate sections for featured books and all books.
 *
 * State Variables:
 * @state {Array} books - The complete list of books fetched from the backend.
 * @state {Array} featured - A subset of books designated as featured or new arrivals.
 * @state {string} searchTitle - The search query for filtering books by title.
 * @state {string} searchAuthor - The search query for filtering books by author.
 * @state {string} searchISBN - The search query for filtering books by ISBN.
 * @state {string} filterCategory - The selected category for filtering books.
 *
 * Filtering Logic:
 * - Books are filtered by checking if they include the search strings for title, author, and ISBN.
 * - The category filter is applied by comparing the lowercased category of the book with the selected filter.
 *
 * Rendering:
 * - A header with a welcome message.
 * - A search filter section with input fields for title, author, ISBN, and a dropdown for category.
 * - A featured books section showcasing the new arrivals if available.
 * - An all books section displaying the filtered list of books or a "No books found" message.
 *
 * @component
 * @example
 * // Example usage:
 * // <LandingPage />
 */
// path: Frontend/src/pages/LandingPage.jsx

import React, { useEffect, useState } from "react";
import { getAllBooks } from "../services/BookService.js";
import { BookCard } from "../components/books/BookCard.jsx";
import "../styling/LandingPage.css";

const unwrap = (r) => (r && r.data !== undefined ? r.data : r);

export function LandingPage() {
  const [books, setBooks] = useState([]);
  const [featured, setFeatured] = useState([]);

  const [searchTitle, setSearchTitle] = useState("");
  const [searchAuthor, setSearchAuthor] = useState("");
  const [searchISBN, setSearchISBN] = useState("");
  const [filterCategory, setFilterCategory] = useState("");

  useEffect(() => {
    getAllBooks()
      .then(unwrap)
      .then((data) => {
        const list = Array.isArray(data) ? data : [];
        setBooks(list);

        // Featured section → fixed list of "new arrivals"
        const chosenTitles = [
          "Pride and Prejudice",
          "1984",
          "To Kill A Mockingbird",
        ];
        const chosen = list.filter((b) => chosenTitles.includes(b.title));
        setFeatured(chosen.length ? chosen.slice(0, 3) : list.slice(0, 3));
      })
      .catch((err) => {
        console.error("Error fetching books:", err);
        setBooks([]);
        setFeatured([]);
      });
  }, []);

  const categories = Array.from(
    new Set((books || []).map((b) => b.category).filter(Boolean))
  ).sort();

  const filteredBooks = (books || []).filter((b) => {
    const titleMatch =
      !searchTitle ||
      (b.title || "").toLowerCase().includes(searchTitle.toLowerCase());
    const authorMatch =
      !searchAuthor ||
      (b.author || "").toLowerCase().includes(searchAuthor.toLowerCase());
    const isbnMatch =
      !searchISBN ||
      (b.isbn || "").toLowerCase().includes(searchISBN.toLowerCase());
    const categoryMatch =
      !filterCategory ||
      (b.category || "").toLowerCase() === filterCategory.toLowerCase();
    return titleMatch && authorMatch && isbnMatch && categoryMatch;
  });

  return (
    <div className="landing-container">
      <header className="landing-header">
        <h1>Welcome to the Library</h1>
      </header>

      {/* ========================= */}
      {/* Search Filters */}
      {/* ========================= */}
      <section className="search-filter">
        <input
          aria-label="Search books by title"
          type="text"
          placeholder="Search by title..."
          value={searchTitle}
          onChange={(e) => setSearchTitle(e.target.value)}
        />

        <input
          aria-label="Search books by author"
          type="text"
          placeholder="Search by author..."
          value={searchAuthor}
          onChange={(e) => setSearchAuthor(e.target.value)}
          style={{ marginTop: 8 }}
        />

        <input
          aria-label="Search books by ISBN"
          type="text"
          placeholder="Search by ISBN..."
          value={searchISBN}
          onChange={(e) => setSearchISBN(e.target.value)}
          style={{ marginTop: 8 }}
        />

        <select
          value={filterCategory}
          onChange={(e) => setFilterCategory(e.target.value)}
          style={{ marginTop: 8 }}
        >
          <option value="">All Categories</option>
          {categories.map((cat) => (
            <option key={cat} value={cat}>
              {cat}
            </option>
          ))}
        </select>
      </section>

      {/* ========================= */}
      {/* Featured Books */}
      {/* ========================= */}
      {featured.length > 0 && (
        <section className="featured-books">
          <h2>New Arrivals</h2>
          <div className="books-grid">
            {featured.map((book) => {
              const key =
                book.bookId ??
                book.book_id ??
                book.id ??
                book.isbn ??
                book.title;
              return <BookCard key={key} book={book} />;
            })}
          </div>
        </section>
      )}

      {/* ========================= */}
      {/* All Books */}
      {/* ========================= */}
      <section className="all-books" style={{ marginTop: 24 }}>
        <h2>All Books</h2>
        {filteredBooks.length === 0 ? (
          <p className="muted">No books found.</p>
        ) : (
          <div className="books-grid">
            {filteredBooks.map((book) => {
              const key =
                book.bookId ??
                book.book_id ??
                book.id ??
                book.isbn ??
                book.title;
              return <BookCard key={key} book={book} />;
            })}
          </div>
        )}
      </section>
    </div>
  );
}
