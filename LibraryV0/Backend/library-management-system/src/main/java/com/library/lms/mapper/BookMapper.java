// BookMapper.java
package com.library.lms.mapper;

import com.library.lms.dto.*;
import com.library.lms.model.*;
import com.library.lms.model.enums.BookStatus;

import java.time.LocalDateTime;

/**
 * Mapper for converting between {@link Book} entity and {@link BookDTO} record.
 */
public class BookMapper {

    /**
     * Converts a {@link Book} entity to a {@link BookDTO} record.
     *
     * @param book The Book entity to convert.
     * @return The corresponding BookDTO record, or null if the entity is null.
     */
    public static BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDTO(
                book.getBookId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getPublishedYear(),
                book.getCategory(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                book.getLocationSection(),
                book.getLocationShelf(),
                book.getLocationRow(),
                book.getStatus() != null ? book.getStatus().name() : null, // Convert enum to String
                book.getCreatedAt(),
                book.getUpdatedAt()
        );
    }

    /**
     * Converts a {@link BookDTO} record to a new {@link Book} entity.
     *
     * @param dto The BookDTO record to convert.
     * @return A new Book entity, or null if the DTO is null.
     */
    public static Book toEntity(BookDTO dto) {
        if (dto == null) {
            return null;
        }
        Book book = new Book();
        book.setBookId(dto.bookId()); // bookId might be null for new entities
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setIsbn(dto.isbn());
        book.setPublishedYear(dto.publishedYear());
        book.setCategory(dto.category());
        book.setTotalCopies(dto.totalCopies());
        book.setAvailableCopies(dto.availableCopies());
        book.setLocationSection(dto.locationSection());
        book.setLocationShelf(dto.locationShelf());
        book.setLocationRow(dto.locationRow());
        if (dto.status() != null) {
            book.setStatus(BookStatus.valueOf(dto.status())); // Convert String to enum
        }
        // Timestamps createdAt and updatedAt are typically set by the database
        return book;
    }

    /**
     * Updates an existing {@link Book} entity with data from a {@link BookDTO} record.
     *
     * @param book The existing Book entity to update.
     * @param dto The BookDTO record containing the updated data.
     * @return The updated Book entity.
     */
    public static Book updateEntityFromDTO(Book book, BookDTO dto) {
        if (book == null || dto == null) {
            return book;
        }
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setIsbn(dto.isbn()); // Be cautious with ISBN updates if it's a primary identifier
        book.setPublishedYear(dto.publishedYear());
        book.setCategory(dto.category());
        book.setTotalCopies(dto.totalCopies());
        book.setAvailableCopies(dto.availableCopies());
        book.setLocationSection(dto.locationSection());
        book.setLocationShelf(dto.locationShelf());
        book.setLocationRow(dto.locationRow());
        if (dto.status() != null) {
            book.setStatus(BookStatus.valueOf(dto.status()));
        }
        // Timestamps createdAt and updatedAt are typically handled by the database
        return book;
    }
}