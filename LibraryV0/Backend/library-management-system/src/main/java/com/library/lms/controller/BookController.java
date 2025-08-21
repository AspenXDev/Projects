package com.library.lms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.library.lms.model.Book;
import com.library.lms.model.enums.BookStatus;
import com.library.lms.service.BookService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ======================
    // CRUD Endpoints
    // ======================
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        // Note: availableCopies will be automatically capped to totalCopies by service
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
        // Note: availableCopies will be automatically capped to totalCopies by service
        return bookService.updateBook(id, bookDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    // ======================
    // Convenience Endpoints
    // ======================
    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookService.getBooksByStatus(BookStatus.Available);
    }

    @GetMapping("/borrowed")
    public List<Book> getBorrowedBooks() {
        return bookService.getBooksByStatus(BookStatus.Borrowed);
    }

    @GetMapping("/reserved")
    public List<Book> getReservedBooks() {
        return bookService.getBooksByStatus(BookStatus.Reserved);
    }

    // ======================
    // Exception Handlers
    // ======================
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
