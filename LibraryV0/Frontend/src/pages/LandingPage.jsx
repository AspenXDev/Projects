import { useEffect, useState, useRef, useCallback } from "react";
import axios from "axios";
import "../styling/LandingPage.css";
import PublicBookCard from "../components/PublicBookCard";

const PAGE_SIZE = 12; // front-end mock pagination size

export default function LandingPage() {
  // books
  const [allBooks, setAllBooks] = useState([]);
  const [visibleBooks, setVisibleBooks] = useState([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // auth drawer
  const [showAuth, setShowAuth] = useState(false);
  const [authMode, setAuthMode] = useState("login"); // "login" | "register"

  // loader ref for IntersectionObserver
  const loaderRef = useRef(null);

  // fetch all books once (public endpoint)
  const fetchAllBooks = useCallback(async () => {
    try {
      setLoading(true);
      setError(null);
      const res = await axios.get("http://localhost:8081/books");
      const data = Array.isArray(res.data) ? res.data : [];
      setAllBooks(data);
      const initial = data.slice(0, PAGE_SIZE);
      setVisibleBooks(initial);
      setPage(1);
      setHasMore(data.length > PAGE_SIZE);
    } catch (e) {
      console.error(e);
      setError("Unable to load books.");
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchAllBooks();
  }, [fetchAllBooks]);

  // load more (front-end slice)
  const loadMore = useCallback(() => {
    if (!hasMore || loading) return;

    setPage((prev) => {
      const nextPage = prev + 1;
      const nextItems = allBooks.slice(0, nextPage * PAGE_SIZE);
      setVisibleBooks(nextItems);
      if (nextPage * PAGE_SIZE >= allBooks.length) setHasMore(false);
      return nextPage;
    });
  }, [allBooks, hasMore, loading]);

  // IntersectionObserver (eslint-friendly cleanup)
  useEffect(() => {
    const loader = loaderRef.current;
    if (!loader) return;

    const observer = new IntersectionObserver(
      (entries) => {
        const first = entries[0];
        if (first.isIntersecting) {
          loadMore();
        }
      },
      { root: null, rootMargin: "200px", threshold: 0.1 }
    );

    observer.observe(loader);
    return () => {
      if (loader) observer.unobserve(loader);
    };
  }, [loadMore]);

  // mock submit handlers (front-end only for now)
  const handleLoginSubmit = (e) => {
    e.preventDefault();
    // TODO: wire to real login later
    console.log("Login submit (mock)");
    setShowAuth(false);
  };

  const handleRegisterSubmit = (e) => {
    e.preventDefault();
    // TODO: wire to real register later
    // Role = 1 (Members), is_active = false will be enforced on backend.
    console.log("Register submit (mock)");
    setShowAuth(false);
  };

  return (
    <div className="landing-page">
      {/* top bar */}
      <header className="landing-topbar">
        <h1 className="landing-title">Library</h1>
        <button
          className="auth-toggle-btn"
          onClick={() => setShowAuth(true)}
          aria-label="Open login/register"
        >
          Login / Register
        </button>
      </header>

      {/* hero */}
      <section className="landing-hero">
        <h2>Explore our collection</h2>
        <p>Browse books and magazines. Scroll to load more.</p>
      </section>

      {/* content */}
      <main className="cards-wrap">
        {error && <p className="error">{error}</p>}
        {!error && loading && <p className="muted">Loading…</p>}

        {!loading && !error && visibleBooks.length === 0 && (
          <p className="muted">No books available.</p>
        )}

        <div className="public-grid">
          {visibleBooks.map((b) => (
            <PublicBookCard key={b.bookId ?? b.book_id ?? b.isbn} book={b} />
          ))}
        </div>

        {/* sentinel for infinite scroll */}
        <div ref={loaderRef} className="infinite-loader">
          {hasMore ? "Loading more…" : "— End of list —"}
        </div>
      </main>

      {/* auth drawer (hidden by default) */}
      {showAuth && (
        <>
          <div className="auth-backdrop" onClick={() => setShowAuth(false)} />
          <aside className="auth-panel" role="dialog" aria-modal="true">
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
              <button
                className="auth-close"
                onClick={() => setShowAuth(false)}
                aria-label="Close"
              >
                ✕
              </button>
            </div>

            {authMode === "login" ? (
              <form className="auth-form" onSubmit={handleLoginSubmit}>
                <label>
                  Username
                  <input type="text" required />
                </label>
                <label>
                  Password
                  <input type="password" required />
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

                {/* Front-end note: role_id = 1 (Members), is_active = false */}
                <div className="hint">
                  New accounts are created as <b>Members</b> and marked{" "}
                  <b>inactive</b> until a Librarian approves.
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
}
