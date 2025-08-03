CREATE DATABASE Library_DB;
USE Library_DB;
CREATE TABLE Books (
  book_id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  authorname VARCHAR(255),
  isbn CHAR(17) UNIQUE,-- May have to format all ISBN to ISBN-13 at frontend
  published DATE,
  available BOOLEAN DEFAULT TRUE
);
CREATE TABLE Members (
  member_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE,
  phone VARCHAR(20),
  registered_on DATETIME DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE Loans (
  loan_id INT AUTO_INCREMENT PRIMARY KEY,
  book_id INT NOT NULL,
  member_id INT NOT NULL,
  loan_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  due_date DATE NOT NULL,
  return_date DATE, -- When the book was actually returned
  returned BOOLEAN DEFAULT FALSE,
  late_fee DECIMAL(5,2) DEFAULT 0.00, -- Fee to be paid for late return
  FOREIGN KEY (book_id) REFERENCES Books(book_id),
  FOREIGN KEY (member_id) REFERENCES Members(member_id)
);

CREATE TABLE Locations (
  location_id INT AUTO_INCREMENT PRIMARY KEY,
  location_section VARCHAR(50),   -- e.g., "Fiction", "Reference", "Periodicals"
  location_shelf INT,
  location_row INT,
  note TEXT              -- extra details eg. "Top shelf near entrance"
);

ALTER TABLE Books ADD location_id INT;
ALTER TABLE Books
  ADD FOREIGN KEY (location_id) REFERENCES Locations(location_id);
