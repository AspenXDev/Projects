// path: Backend/src/main/java/com/library/lms/repository/BookRepository.java
package com.library.lms.repository;

import com.library.lms.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {

    // Titles where at least one copy is currently out
    @Query("SELECT COUNT(b) FROM Book b WHERE b.availableCopies < b.totalCopies")
    long countTitlesWithCopiesBorrowed();
}
