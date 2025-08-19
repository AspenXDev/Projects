package com.library.lms.service;

import com.library.lms.model.Book;
import java.util.List;

public interface BookService {
    Book createBook(Book book);
    Book getBookById(Integer id);
    List<Book> getAllBooks();
    Book updateBook(Integer id, Book book);
    void deleteBook(Integer id);
    List<Book> getBooksByStatus(String status);  // declare only
}