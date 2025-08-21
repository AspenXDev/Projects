package com.library.lms.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.lms.model.Book;
import com.library.lms.model.enums.BookStatus;
import com.library.lms.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // ======================
    // CRUD
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
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
        return bookService.updateBook(id, bookDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
    }

    // ======================
    // Convenience endpoints
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
}
