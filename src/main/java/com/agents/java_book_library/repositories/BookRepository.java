package com.agents.java_book_library.repositories;

import com.agents.java_book_library.domains.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    Page<Book> findAll(Pageable pageable);
}
