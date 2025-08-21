package com.library.lms.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.library.lms.model.Book;

public class TestGetBooks {

    private static final Logger logger = LoggerFactory.getLogger(TestGetBooks.class);

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            Book[] books = restTemplate.getForObject("http://localhost:8081/books", Book[].class);

            if (books == null || books.length == 0) {
                logger.warn("No books returned from API!");
            } else {
                for (Book b : books) {
                    logger.info("Book retrieved: ID={} | Title={} | Author={} | ISBN={} | Year={} | Category={} | " +
                                    "Total={} | Available={} | Location=[Section={}, Shelf={}, Row={}] | Status={} | CreatedAt={} | UpdatedAt={}",
                            b.getBookId(),
                            b.getTitle(),
                            b.getAuthor(),
                            b.getIsbn(),
                            b.getPublishedYear(),
                            b.getCategory(),
                            b.getTotalCopies(),
                            b.getAvailableCopies(),
                            b.getLocationSection(),
                            b.getLocationShelf(),
                            b.getLocationRow(),
                            b.getStatus(),
                            b.getCreatedAt(),
                            b.getUpdatedAt()
                    );
                }
            }
        } catch (Exception ex) {
            logger.error("Error while calling /books API", ex);
        }
    }
}
