package com.agents.java_book_library.controllers;

import com.agents.java_book_library.api.LoanApi;
import com.agents.java_book_library.model.LoanBookDTO;
import com.agents.java_book_library.services.LoanBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanBookController implements LoanApi {

    private final LoanBookService loanBookService;

    @Override
    public ResponseEntity<LoanBookDTO> addLoan(LoanBookDTO loanBookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(loanBookService.createLoan(loanBookDTO));
    }

    @Override
    public ResponseEntity<Void> deleteLoanByID(Long loanId) {
        loanBookService.deleteBook(loanId);
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<List<LoanBookDTO>> findAllLoan() {
        return ResponseEntity.ok(loanBookService.getAllLoanBook());
    }

    @Override
    public ResponseEntity<List<LoanBookDTO>> getLoanBookByMember(Long memberId) {
        return ResponseEntity.ok(loanBookService.getLoanBookByMember(memberId));
    }

    @Override
    public ResponseEntity<List<LoanBookDTO>> getLoanBooksByLendDate(Date lendDate) {
        return ResponseEntity.ok(loanBookService.getLoanBooksByLendDate(lendDate));
    }

    @Override
    public ResponseEntity<LoanBookDTO> updateLoan(LoanBookDTO loanBookDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(loanBookService.updateLoan(loanBookDTO));
    }
}
