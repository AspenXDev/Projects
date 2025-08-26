package com.library.lms.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import com.library.lms.model.Book;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGetBooks {

    private static final Logger logger = LoggerFactory.getLogger(TestGetBooks.class);

    @Test
    void testFetchBooks() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            Book[] books = restTemplate.getForObject("http://localhost:8081/books", Book[].class);

            if (books == null || books.length == 0) {
                logger.warn("No books returned from API!");
            } else {
                for (Book b : books) {
                    logger.info("Book retrieved: ID={} | Title={} | Author={} | ISBN={} | Year={} | Category={} | Total={} | Available={} | Status={}",
                            b.getBookId(), b.getTitle(), b.getAuthor(), b.getIsbn(), b.getPublishedYear(),
                            b.getCategory(), b.getTotalCopies(), b.getAvailableCopies(), b.getStatus());
                }
            }
        } catch (Exception ex) {
            logger.error("Error while calling /books API", ex);
        }
    }
}
