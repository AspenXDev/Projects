package com.library.lms.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Find a user by username (use Optional<T> wrapper because it is possible the user may not exist)
    Optional<User> findByUsername(String username);
}
