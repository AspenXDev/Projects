// path: Frontend/src/pages/BookManagement.jsx
import React, { useEffect, useState } from "react";
import {
  getAllBooks,
  createBook,
  updateBook,
  deleteBook,
} from "../services/BookService.js";
import "../styling/Management.css";

export const BookManagement = () => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [editingBook, setEditingBook] = useState(null);
  const [formData, setFormData] = useState({
    title: "",
    author: "",
    totalCopies: 1,
    availableCopies: 1,
  });

  const fetchBooks = async () => {
    try {
      setLoading(true);
      const data = await getAllBooks();
      setBooks(Array.isArray(data.data) ? data.data : []);
    } catch (err) {
      console.error("Error fetching books:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((p) => ({ ...p, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editingBook) {
        await updateBook(editingBook.bookId, formData);
      } else {
        await createBook(formData);
      }
      setFormData({
        title: "",
        author: "",
        totalCopies: 1,
        availableCopies: 1,
      });
      setEditingBook(null);
      fetchBooks();
    } catch (err) {
      console.error("Error saving book:", err);
    }
  };

  const handleEdit = (book) => {
    setEditingBook(book);
    setFormData({
      title: book.title,
      author: book.author,
      totalCopies: book.totalCopies,
      availableCopies: book.availableCopies,
    });
  };

  const handleDelete = async (bookId) => {
    if (!window.confirm("Are you sure you want to delete this book?")) return;
    try {
      await deleteBook(bookId);
      fetchBooks();
    } catch (err) {
      console.error("Error deleting book:", err);
    }
  };

  return (
    <div className="management-container">
      <h2>Book Management</h2>

      <form className="book-form" onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          placeholder="Title"
          value={formData.title}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="author"
          placeholder="Author"
          value={formData.author}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="totalCopies"
          placeholder="Total Copies"
          value={formData.totalCopies}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="availableCopies"
          placeholder="Available Copies"
          value={formData.availableCopies}
          onChange={handleChange}
          required
        />
        <button type="submit">{editingBook ? "Update" : "Add"} Book</button>
      </form>

      {loading ? (
        <p className="muted">Loading books...</p>
      ) : books.length === 0 ? (
        <p className="muted">No books available.</p>
      ) : (
        <table className="book-table">
          <thead>
            <tr>
              <th>Title</th>
              <th>Author</th>
              <th>Total Copies</th>
              <th>Available Copies</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {books.map((b) => (
              <tr key={b.bookId}>
                <td>{b.title}</td>
                <td>{b.author}</td>
                <td>{b.totalCopies}</td>
                <td>{b.availableCopies}</td>
                <td>
                  <button onClick={() => handleEdit(b)}>Edit</button>
                  <button onClick={() => handleDelete(b.bookId)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};
