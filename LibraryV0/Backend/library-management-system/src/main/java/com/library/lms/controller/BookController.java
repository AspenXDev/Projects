package com.library.lms.controller;

import com.library.lms.model.Book;
import com.library.lms.model.enums.BookStatus;
import com.library.lms.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ======================
    // GET ALL BOOKS
    // ======================
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            logger.debug("Fetched {} books from database", books.size());
            return ResponseEntity.ok(books);
        } catch (Exception e) {
            logger.error("Error fetching books", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ======================
    // GET BOOK BY ID
    // ======================
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        try {
            return bookRepository.findById(id)
                    .map(book -> {
                        logger.debug("Fetched book: {}", book);
                        return ResponseEntity.ok(book);
                    })
                    .orElseGet(() -> {
                        logger.warn("Book with id {} not found", id);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception e) {
            logger.error("Error fetching book with id {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ======================
    // CREATE NEW BOOK
    // ======================
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            if (book.getStatus() == null) {
                book.setStatus(BookStatus.Available);
            }
            Book saved = bookRepository.save(book);
            logger.debug("Created new book: {}", saved);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid enum value provided for BookStatus", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error creating book", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ======================
    // UPDATE BOOK
    // ======================
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
        try {
            return bookRepository.findById(id)
                    .map(book -> {
                        book.setTitle(updatedBook.getTitle());
                        book.setAuthor(updatedBook.getAuthor());
                        book.setIsbn(updatedBook.getIsbn());
                        book.setCategory(updatedBook.getCategory());
                        book.setPublishedYear(updatedBook.getPublishedYear());
                        book.setTotalCopies(updatedBook.getTotalCopies());
                        book.setAvailableCopies(updatedBook.getAvailableCopies());
                        book.setLocationSection(updatedBook.getLocationSection());
                        book.setLocationShelf(updatedBook.getLocationShelf());
                        book.setLocationRow(updatedBook.getLocationRow());

                        if (updatedBook.getStatus() != null) {
                            book.setStatus(updatedBook.getStatus());
                        }

                        Book saved = bookRepository.save(book);
                        logger.debug("Updated book: {}", saved);
                        return ResponseEntity.ok(saved);
                    })
                    .orElseGet(() -> {
                        logger.warn("Book with id {} not found for update", id);
                        return ResponseEntity.notFound().build();
                    });
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid enum value provided for BookStatus", e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("Error updating book with id {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // ======================
    // DELETE BOOK
    // ======================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        try {
            return bookRepository.findById(id)
                    .map(book -> {
                        bookRepository.delete(book);
                        logger.debug("Deleted book: {}", book);
                        return ResponseEntity.noContent().<Void>build();
                    })
                    .orElseGet(() -> {
                        logger.warn("Book with id {} not found for deletion", id);
                        return ResponseEntity.notFound().build();
                    });
        } catch (Exception e) {
            logger.error("Error deleting book with id {}", id, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
