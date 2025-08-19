package com.library.lms.service.impl;

import com.library.lms.model.Book;
import com.library.lms.repository.BookRepository;
import com.library.lms.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
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
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        existing.setPublishedYear(book.getPublishedYear());
        existing.setCategory(book.getCategory());
        existing.setTotalCopies(book.getTotalCopies());
        existing.setAvailableCopies(book.getAvailableCopies());
        existing.setLocationSection(book.getLocationSection());
        existing.setLocationShelf(book.getLocationShelf());
        existing.setLocationRow(book.getLocationRow());
        existing.setStatus(book.getStatus());

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
    public List<Book> getBooksByStatus(String status) {
        return bookRepository.findByStatus(status);
    }
}