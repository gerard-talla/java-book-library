CREATE TABLE Author (
                        authorId BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        dateOfBirth VARCHAR(255) NOT NULL
);

CREATE TABLE Book (
                      bookId BIGINT AUTO_INCREMENT PRIMARY KEY,
                      title VARCHAR(255) NOT NULL,
                      genre VARCHAR(255) NOT NULL,
                      price DECIMAL(10, 2) NOT NULL,
                      author_id BIGINT NOT NULL,
                      CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES Author(authorId)
);

CREATE TABLE Member (
                        memberId BIGINT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(255) UNIQUE NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        phoneNumber VARCHAR(255) NOT NULL
);

CREATE TABLE LoanBook (
                      LoanBookId BIGINT AUTO_INCREMENT PRIMARY KEY,
                      lendDate DATE NOT NULL,
                      returnDate DATE NOT NULL,
                      book_id BIGINT NOT NULL,
                      member_id BIGINT NOT NULL,
                      CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES Book(bookId),
                      CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES Member(memberId)
);
