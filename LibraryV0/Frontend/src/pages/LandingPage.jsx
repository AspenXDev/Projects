import { useEffect, useState, useRef } from "react";
import axios from "axios";
import "../Styling/LandingPage.css";

export default function LandingPage() {
  const [books, setBooks] = useState([]);
  const [page, setPage] = useState(1); // current page
  const [hasMore, setHasMore] = useState(true); // flag to check if more books exist
  const loaderRef = useRef(null);

  // Fetch books
  const fetchBooks = async () => {
    try {
      const res = await axios.get(
        `http://localhost:8081/books?page=${page}&size=10`
      ); // backend should support pagination: page & size
      if (res.data.length === 0) {
        setHasMore(false);
      } else {
        setBooks((prev) => [...prev, ...res.data]);
      }
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, [page]);

  // Infinite scroll observer
  useEffect(() => {
    const observer = new IntersectionObserver(
      (entries) => {
        if (entries[0].isIntersecting && hasMore) {
          setPage((prev) => prev + 1);
        }
      },
      { threshold: 1 }
    );

    if (loaderRef.current) {
      observer.observe(loaderRef.current);
    }

    return () => {
      if (loaderRef.current) {
        observer.unobserve(loaderRef.current);
      }
    };
  }, [loaderRef, hasMore]);

  return (
    <div className="landing-container">
      <h1>Browse Books</h1>

      {books.length === 0 ? (
        <p style={{ textAlign: "center", marginTop: "40px" }}>
          No books available
        </p>
      ) : (
        <div className="book-card-container">
          {books.map((book) => (
            <div className="book-card" key={book.bookId}>
              <h3>{book.title}</h3>
              <p>
                <span className="label">Author:</span> {book.author}
              </p>
              <p>
                <span className="label">Available Copies:</span>{" "}
                {book.availableCopies}
              </p>
              <p>
                <span className="label">Status:</span> {book.status}
              </p>
              <p>
                <span className="label">Location:</span> {book.locationSection}{" "}
                / Shelf {book.locationShelf}, Row {book.locationRow}
              </p>
            </div>
          ))}
        </div>
      )}

      {/* Loader element for intersection observer */}
      {hasMore && (
        <div
          ref={loaderRef}
          style={{ textAlign: "center", padding: "20px", color: "#003366" }}
        >
          Loading more books...
        </div>
      )}
    </div>
  );
}
