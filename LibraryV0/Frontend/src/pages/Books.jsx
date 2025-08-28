import React from "react";
import { AuthProvider, useAuth } from "../contexts/AuthContext.jsx";
import { BooksPublicList } from "../components/books/BooksPublicList";

export function Books() {
  return <BooksPublicList />;
}
