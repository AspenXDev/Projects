import { useEffect, useState } from "react";
import BookCard from "../components/BookCard";
import { getBooks } from "../services/bookService";
import { getUserProfile } from "../services/AuthService";

const Books = () => {
  const [books, setBooks] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [authenticated, setAuthenticated] = useState(false);

  // Check authentication on mount
  useEffect(() => {
    const checkAuth = async () => {
      try {
        await getUserProfile();
        setAuthenticated(true);
      } catch {
        setAuthenticated(false);
      } finally {
        setLoading(false);
      }
    };
    checkAuth();
  }, []);

  // Fetch books whenever search or authentication status changes
  useEffect(() => {
    if (!authenticated) return;

    const fetchBooks = async () => {
      setLoading(true);
      setError(null);

      try {
        // sanitize search input to prevent injections
        const cleanSearch = search.replace(/[<>;"'`]/g, "");
        const data = await getBooks(cleanSearch);
        setBooks(data);
      } catch (err) {
        console.error("Error fetching books:", err);
        setError("Unable to fetch books. Please try again later.");
      } finally {
        setLoading(false);
      }
    };

    fetchBooks();
  }, [search, authenticated]);

  if (loading) return <p>Loading books...</p>;
  if (!authenticated) return <p>Please log in to view the book catalog.</p>;

  return (
    <div>
      <h1>Book Catalog</h1>
      <input
        type="text"
        placeholder="Search by title, author, or ISBN"
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />
      {error && <p className="error">{error}</p>}
      <div className="book-grid">
        {books.length ? (
          books.map((book) => <BookCard key={book.book_id} book={book} />)
        ) : (
          <p>No books found</p>
        )}
      </div>
    </div>
  );
};

export default Books;
