// path: Backend/src/main/java/com/library/lms/service/impl/BookServiceImpl.java
package com.library.lms.service.impl;

import com.library.lms.exception.BookNotFoundException;
import com.library.lms.model.Book;
import com.library.lms.repository.BookRepository;
import com.library.lms.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book createBook(Book book) {
        // To normalize fields (e.g., availableCopies <= totalCopies), have to do it here
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Integer bookId, Book updatedBook) {
        Book existing = getBookById(bookId);
        existing.setTitle(updatedBook.getTitle());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setIsbn(updatedBook.getIsbn());
        existing.setCategory(updatedBook.getCategory());
        existing.setTotalCopies(updatedBook.getTotalCopies());
        existing.setAvailableCopies(updatedBook.getAvailableCopies());
        existing.setLocationSection(updatedBook.getLocationSection());
        existing.setLocationShelf(updatedBook.getLocationShelf());
        existing.setLocationRow(updatedBook.getLocationRow());
        existing.setStatus(updatedBook.getStatus());
        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Integer bookId) {
        Book existing = getBookById(bookId);
        bookRepository.delete(existing);
    }
}
