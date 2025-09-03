package com.library.lms.repository;
import com.library.lms.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    Optional<Librarian> findByUser_UserId(Integer userId);
    
}
