import React, { useState } from "react";
import { Link } from "react-router-dom";

const LandingPage = () => {
  const [search, setSearch] = useState("");

  const books = [
    {
      id: 1,
      title: "The Great Gatsby",
      author: "F. Scott Fitzgerald",
      available: true,
    },
    { id: 2, title: "1984", author: "George Orwell", available: false },
    {
      id: 3,
      title: "To Kill a Mockingbird",
      author: "Harper Lee",
      available: true,
    },
  ];

  const filteredBooks = books.filter((book) =>
    book.title.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <div className="min-h-screen bg-[#e0f0ff] p-6 flex flex-col items-center">
      <h1 className="text-4xl font-bold text-[#003366] mb-6">
        Library Catalog
      </h1>

      <input
        type="text"
        placeholder="Search books..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
        className="mb-6 p-2 w-full max-w-md border border-gray-300 rounded shadow-sm"
      />

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6 w-full max-w-5xl">
        {filteredBooks.map((book) => (
          <div
            key={book.id}
            className="p-4 border rounded-lg shadow-sm bg-white"
          >
            <h2 className="font-semibold text-[#003366] text-lg">
              {book.title}
            </h2>
            <p className="text-gray-700">{book.author}</p>
            <p className={book.available ? "text-green-600" : "text-red-600"}>
              {book.available ? "Available" : "Checked Out"}
            </p>
          </div>
        ))}
      </div>

      <Link
        to="/login"
        className="mt-8 px-4 py-2 bg-[#007bff] text-white rounded"
      >
        Member / Librarian Login
      </Link>
    </div>
  );
};

export default LandingPage;
