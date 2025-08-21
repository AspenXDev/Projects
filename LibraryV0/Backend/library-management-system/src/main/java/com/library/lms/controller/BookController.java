package com.library.lms.controller;

import com.library.lms.model.Book;
import com.library.lms.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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
}
