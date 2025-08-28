// PATH: src/pages/LandingPage.jsx
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styling/LandingPage.css";
import { BooksPublicList } from "../components/books/BooksPublicList.jsx";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";

export function LandingPage() {
  const navigate = useNavigate();
  return (
    <div className="landing-page" style={{ padding: 20 }}>
      <header style={{ display: "flex", justifyContent: "space-between" }}>
        <h1>Library</h1>
        <div>
          <button onClick={() => navigate("/login")}>Login</button>
          <button onClick={() => navigate("/books")} style={{ marginLeft: 8 }}>
            Browse Books
          </button>
        </div>
      </header>

      <section style={{ marginTop: 16 }}>
        <h2>Explore our collection</h2>
        <p>Browse books and magazines. Scroll to load more.</p>
      </section>

      <main style={{ marginTop: 24 }}>
        <BooksPublicList />
      </main>
    </div>
  );
}
