import React, { useState, useEffect, useRef, useCallback } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../contexts/AuthContext";
import { PublicBookCard } from "../components/PublicBookCard";
import "../styling/LandingPage.css";

const PAGE_SIZE = 12;

export const LandingPage = () => {
  const [allBooks, setAllBooks] = useState([]);
  const [visibleBooks, setVisibleBooks] = useState([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [showAuth, setShowAuth] = useState(false);
  const [authMode, setAuthMode] = useState("login");

  const { login } = useAuth();
  const navigate = useNavigate();
  const loaderRef = useRef(null);

  // -------------------
  // FETCH BOOKS
  // -------------------
  const fetchAllBooks = useCallback(async () => {
    try {
      setLoading(true);
      setError(null);
      const res = await axios.get("http://localhost:8081/books");
      const data = Array.isArray(res.data) ? res.data : [];
      setAllBooks(data);
      setVisibleBooks(data.slice(0, PAGE_SIZE));
      setPage(1);
      setHasMore(data.length > PAGE_SIZE);
    } catch (err) {
      console.error("Books fetch error:", err);
      setError("Unable to load books.");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchAllBooks();
  }, [fetchAllBooks]);

  // -------------------
  // INFINITE SCROLL
  // -------------------
  const loadMore = useCallback(() => {
    if (!hasMore || loading) return;
    setPage((prev) => {
      const nextPage = prev + 1;
      setVisibleBooks(allBooks.slice(0, nextPage * PAGE_SIZE));
      if (nextPage * PAGE_SIZE >= allBooks.length) setHasMore(false);
      return nextPage;
    });
  }, [allBooks, hasMore, loading]);

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
    return () => loader && observer.unobserve(loader);
  }, [loadMore]);

  // -------------------
  // HANDLE LOGIN
  // -------------------
  const handleLoginSubmit = async (e) => {
    e.preventDefault();
    const username = e.target.username.value;
    const password = e.target.password.value;

    try {
      const role = await login(username, password);
      setShowAuth(false);

      const r = (role || "").toLowerCase();
      if (r.startsWith("librar")) navigate("/librarian-dashboard");
      else if (r.startsWith("member")) navigate("/member-dashboard");
      else navigate("/");
    } catch (err) {
      console.error("Login failed:", err);
      alert(err.message || "Invalid username or password");
    }
  };

  const handleRegisterSubmit = (e) => {
    e.preventDefault();
    setShowAuth(false);
    alert("Registration not implemented yet.");
  };

  // -------------------
  // RENDER
  // -------------------
  return (
    <div className="landing-page">
      <header className="landing-topbar">
        <h1 className="landing-title">Library</h1>
        <button className="auth-toggle-btn" onClick={() => setShowAuth(true)}>
          Login / Register
        </button>
      </header>

      <section className="landing-hero">
        <h2>Explore our collection</h2>
        <p>Browse books and magazines. Scroll to load more.</p>
      </section>

      <main className="cards-wrap">
        {error && <p className="error">{error}</p>}
        {loading && <p className="muted">Loading…</p>}
        {!loading && !error && visibleBooks.length === 0 && (
          <p className="muted">No books available.</p>
        )}

        <div className="public-grid">
          {visibleBooks.map((b) => (
            <PublicBookCard key={b.bookId ?? b.book_id ?? b.isbn} book={b} />
          ))}
        </div>

        <div ref={loaderRef} className="infinite-loader">
          {hasMore ? "Loading more…" : "— End of list —"}
        </div>
      </main>

      {showAuth && (
        <>
          <div className="auth-backdrop" onClick={() => setShowAuth(false)} />
          <aside className="auth-panel" role="dialog">
            <div className="auth-header">
              <button
                className={`auth-tab ${authMode === "login" ? "active" : ""}`}
                onClick={() => setAuthMode("login")}
              >
                Login
              </button>
              <button
                className={`auth-tab ${
                  authMode === "register" ? "active" : ""
                }`}
                onClick={() => setAuthMode("register")}
              >
                Create Account
              </button>
              <button className="auth-close" onClick={() => setShowAuth(false)}>
                ✕
              </button>
            </div>

            {authMode === "login" ? (
              <form className="auth-form" onSubmit={handleLoginSubmit}>
                <label>
                  Username
                  <input name="username" type="text" required />
                </label>
                <label>
                  Password
                  <input name="password" type="password" required />
                </label>
                <button type="submit" className="primary-btn">
                  Login
                </button>
              </form>
            ) : (
              <form className="auth-form" onSubmit={handleRegisterSubmit}>
                <label>
                  Username
                  <input type="text" required />
                </label>
                <label>
                  Email
                  <input type="email" required />
                </label>
                <label>
                  Full Name
                  <input type="text" required />
                </label>
                <label>
                  Password
                  <input type="password" required />
                </label>
                <div className="hint">
                  New accounts are created as <b>Members</b> and marked{" "}
                  <b>inactive</b> until approved.
                </div>
                <button type="submit" className="primary-btn">
                  Create Account
                </button>
              </form>
            )}
          </aside>
        </>
      )}
    </div>
  );
};
