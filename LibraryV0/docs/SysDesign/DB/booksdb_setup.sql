-- 1. Create the database
CREATE DATABASE IF NOT EXISTS booksdb
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- 2. Create a user and grant privileges (optional if you want to avoid using root)
CREATE USER IF NOT EXISTS 'bookuser'@'localhost' IDENTIFIED BY 'bookpassword';
GRANT ALL PRIVILEGES ON booksdb.* TO 'bookuser'@'localhost';
FLUSH PRIVILEGES;

-- 3. Switch to the database
USE booksdb;

-- 4. Create the books table
CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DOUBLE
);

-- 5. Insert at least 10 book entries
INSERT INTO books (name, price) VALUES ('The Great Gatsby', 10.99);
INSERT INTO books (name, price) VALUES ('To Kill a Mockingbird', 12.50);
INSERT INTO books (name, price) VALUES ('1984', 9.99);
INSERT INTO books (name, price) VALUES ('Pride and Prejudice', 8.75);
INSERT INTO books (name, price) VALUES ('Moby Dick', 11.20);
INSERT INTO books (name, price) VALUES ('War and Peace', 14.30);
INSERT INTO books (name, price) VALUES ('The Catcher in the Rye', 10.00);
INSERT INTO books (name, price) VALUES ('The Hobbit', 13.45);
INSERT INTO books (name, price) VALUES ('Crime and Punishment', 12.00);
INSERT INTO books (name, price) VALUES ('Brave New World', 9.50);
