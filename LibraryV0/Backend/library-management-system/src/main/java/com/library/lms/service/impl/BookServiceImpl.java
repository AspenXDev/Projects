package com.library.lms.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.lms.model.Book;
import com.library.lms.model.enums.BookStatus;
import com.library.lms.repository.BookRepository;
import com.library.lms.service.BookService;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        adjustCopies(book);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        Book existing = getBookById(id);

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        existing.setPublishedYear(book.getPublishedYear());
        existing.setCategory(book.getCategory());
        existing.setTotalCopies(book.getTotalCopies());
        existing.setLocationSection(book.getLocationSection());
        existing.setLocationShelf(book.getLocationShelf());
        existing.setLocationRow(book.getLocationRow());
        existing.setStatus(book.getStatus());

        // Automatically adjust availableCopies
        if (book.getAvailableCopies() != null) {
            existing.setAvailableCopies(book.getAvailableCopies());
        }
        adjustCopies(existing);

        return bookRepository.save(existing);
    }

    @Override
    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBooksByStatus(BookStatus status) {
        return bookRepository.findByStatus(status);
    }

    // ======================
    // Private helper
    // ======================
    /**
     * Ensures availableCopies is never negative and never exceeds totalCopies.
     * Automatically caps availableCopies at totalCopies if needed.
     */
    private void adjustCopies(Book book) {
        if (book.getTotalCopies() < 0) {
            book.setTotalCopies(0);
        }
        if (book.getAvailableCopies() < 0) {
            book.setAvailableCopies(0);
        }
        if (book.getAvailableCopies() > book.getTotalCopies()) {
            book.setAvailableCopies(book.getTotalCopies());
        }
    }
}
