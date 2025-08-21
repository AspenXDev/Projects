package com.library.lms.test;

import com.library.lms.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class TestGetBooks {

    private static final Logger logger = LoggerFactory.getLogger(TestGetBooks.class);

    public static void main(String[] args) {
        // Start Spring Boot context (optional, for config loading)
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(com.library.lms.LibraryManagementSystemApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Adjust port to match your application.properties
            String url = "http://localhost:8081/books";
            Book[] books = restTemplate.getForObject(url, Book[].class);

            if (books == null || books.length == 0) {
                logger.warn("No books returned from API!");
            } else {
                Arrays.stream(books).forEach(book -> logger.info("""
                        ID: {}
                        Title: {}
                        Author: {}
                        ISBN: {}
                        Published Year: {}
                        Category: {}
                        Total Copies: {}
                        Available Copies: {}
                        Location Section: {}
                        Location Shelf: {}
                        Location Row: {}
                        Status: {}
                        Created At: {}
                        Updated At: {}
                        --------------------------------------
                        """,
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
                        book.getStatus(),
                        book.getCreatedAt(),
                        book.getUpdatedAt()
                ));
            }
        } catch (Exception e) {
            logger.error("Exception while fetching books", e);
        } finally {
            context.close();
        }
    }
}
