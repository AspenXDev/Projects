package com.library.lms.service.impl;

import com.library.lms.model.Book;
import com.library.lms.repository.BookRepository;
import com.library.lms.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) {
            throw new RuntimeException("Book not found with ID: " + bookId);
        }
        return book.get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContaining(keyword, keyword, keyword);
    }

    @Override
    public Book updateBook(Book book) {
        if (!bookRepository.existsById(book.getBookId())) {
            throw new RuntimeException("Book not found with ID: " + book.getBookId());
        }
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Integer bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public void updateAvailableCopies(Integer bookId, int newAvailableCopies) {
        Book book = getBookById(bookId);
        book.setAvailableCopies(newAvailableCopies);
        if (newAvailableCopies == 0) {
            book.setStatus("Borrowed");
        } else {
            book.setStatus("Available");
        }
        bookRepository.save(book);
    }
}
