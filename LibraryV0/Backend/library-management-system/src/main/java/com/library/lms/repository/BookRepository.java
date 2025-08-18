package com.library.lms.repository;

import com.library.lms.model.Book;
import com.library.lms.model.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStatus(BookStatus status);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
