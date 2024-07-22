package com.agents.java_book_library.repositories;

import com.agents.java_book_library.domains.LoanBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LoanBookRepository extends JpaRepository<LoanBook, Long> {

    List<LoanBook> findLoanBooksByLendDate(Date lendDate);
    List<LoanBook> findLoanBooksByLendDateAndReturnDate(Date lendFate, Date returnDate);
    List<LoanBook> findLoanBooksByMember(Long memberId);
}
