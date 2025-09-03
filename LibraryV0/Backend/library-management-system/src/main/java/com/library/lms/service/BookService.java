// path: Backend/src/main/java/com/library/lms/service/BookService.java
package com.library.lms.service;

import java.util.List;
import com.library.lms.model.Book;

public interface BookService {
    Book createBook(Book book);
    Book updateBook(Integer bookId, Book book);
    Book getBookById(Integer bookId);
    List<Book> getAllBooks();
    void deleteBook(Integer bookId);
}
