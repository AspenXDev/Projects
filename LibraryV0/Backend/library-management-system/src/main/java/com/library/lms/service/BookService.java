package com.library.lms.service;

import java.util.List;
import com.library.lms.model.Book;
import com.library.lms.model.enums.BookStatus;

public interface BookService {
    Book createBook(Book book);
    Book getBookById(Integer id);
    List<Book> getAllBooks();
    Book updateBook(Integer id, Book book);
    void deleteBook(Integer id);
    List<Book> getBooksByStatus(BookStatus status);  // use enum
}
