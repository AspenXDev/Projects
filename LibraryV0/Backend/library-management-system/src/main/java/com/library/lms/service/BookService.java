package com.library.lms.service;

import com.library.lms.model.Book;
import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book getBookById(Integer bookId);
    List<Book> getAllBooks();
    List<Book> searchBooks(String keyword); // search by title, author, isbn
    Book updateBook(Book book);
    void deleteBook(Integer bookId);
    void updateAvailableCopies(Integer bookId, int newAvailableCopies);
}
