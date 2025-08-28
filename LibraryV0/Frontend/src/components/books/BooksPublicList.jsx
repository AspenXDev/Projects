// src/components/books/BooksPublicList.jsx
import React, { useState, useEffect, useCallback, useRef } from "react";
import { getAllPublicBooks } from "../../services/BookService.js";
import { PublicBookCard } from "./PublicBookCard.jsx";
import "../../styling/BooksLists.css";

const PAGE_SIZE = 12;

export function BooksPublicList() {
  const [allBooks, setAllBooks] = useState([]);
  const [visibleBooks, setVisibleBooks] = useState([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const [loading, setLoading] = useState(true);
  const loaderRef = useRef(null);

  const fetchAllBooks = useCallback(async () => {
    setLoading(true);
    try {
      const data = await getAllPublicBooks();
      setAllBooks(data);
      setVisibleBooks(data.slice(0, PAGE_SIZE));
      setPage(1);
      setHasMore(data.length > PAGE_SIZE);
    } catch (err) {
      console.error("Error loading books:", err);
    } finally {
      setLoading(false);
    }
  }, []);

  const loadMore = useCallback(() => {
    if (!hasMore || loading) return;
    const nextPage = page + 1;
    const newBooks = allBooks.slice(0, nextPage * PAGE_SIZE);
    setVisibleBooks(newBooks);
    setPage(nextPage);
    if (newBooks.length >= allBooks.length) setHasMore(false);
  }, [allBooks, hasMore, loading, page]);

  useEffect(() => {
    fetchAllBooks();
  }, [fetchAllBooks]);

  useEffect(() => {
    const loader = loaderRef.current;
    if (!loader) return;
    const observer = new IntersectionObserver(
      (entries) => {
        if (entries[0].isIntersecting) loadMore();
      },
      { root: null, rootMargin: "200px", threshold: 0.1 }
    );
    observer.observe(loader);
    return () => observer.disconnect();
  }, [loadMore]);

  return (
    <div style={{ padding: 20 }}>
      <h1>All Books</h1>
      {loading && <p>Loading books…</p>}
      <div className="public-grid">
        {visibleBooks.map((b) => (
          <PublicBookCard key={b.book_id ?? b.isbn} book={b} />
        ))}
      </div>
      <div ref={loaderRef} style={{ marginTop: 20, textAlign: "center" }}>
        {hasMore ? "Loading more…" : "— End of list —"}
      </div>
    </div>
  );
}
