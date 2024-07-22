package com.agents.java_book_library.services;

import com.agents.java_book_library.domains.LoanBook;
import com.agents.java_book_library.exceptions.BookNotFound;
import com.agents.java_book_library.exceptions.LoanBookNotFound;
import com.agents.java_book_library.mappers.BookMapper;
import com.agents.java_book_library.mappers.LoanBookMapper;
import com.agents.java_book_library.mappers.MemberMapper;
import com.agents.java_book_library.model.LoanBookDTO;
import com.agents.java_book_library.repositories.LoanBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanBookService {

    private final LoanBookRepository repository;
    private final LoanBookMapper mapper;
    private final MemberMapper memberMapper;
    private final BookMapper bookMapper;

    public List<LoanBookDTO> getAllLoanBook() {
        return repository.findAll().stream()
                .map(mapper::toLoanBookDTO).collect(Collectors.toList());
    }

    public List<LoanBookDTO> getLoanBookByMember(Long memberId) {
        return repository.findLoanBooksByMember(memberId)
                .stream().map(mapper::toLoanBookDTO).collect(Collectors.toList());
    }

    public List<LoanBookDTO> getLoanBooksByLendDate(Date lendDate) {
        return repository.findLoanBooksByLendDate(lendDate)
                .stream().map(mapper::toLoanBookDTO).collect(Collectors.toList());
    }

    public List<LoanBookDTO> getLoanBooksByLendDateAndReturnDate(Date lendDate, Date returnDate) {
        return repository.findLoanBooksByLendDateAndReturnDate(lendDate, returnDate)
                .stream().map(mapper::toLoanBookDTO).collect(Collectors.toList());
    }

    public LoanBookDTO createLoan(LoanBookDTO loanBookDTO) {
        LoanBook loanBook = mapper.toLoanBook(loanBookDTO);
        return mapper.toLoanBookDTO(repository.save(loanBook));
    }

    public void deleteBook(Long loanId) {
        repository.findById(loanId)
                .orElseThrow(() -> new BookNotFound(loanId));
        repository.deleteById(loanId);
    }

    public LoanBookDTO updateLoan(LoanBookDTO loanBookDTO) {
        LoanBook loanBook = repository.findById(loanBookDTO.getId())
                .orElseThrow(() -> new LoanBookNotFound(loanBookDTO.getId()));

        loanBook.setLendDate(loanBookDTO.getLendDate());
        loanBook.setReturnDate(loanBookDTO.getReturnDate());
        loanBook.setBook(bookMapper.toBook(loanBookDTO.getBook()));
        loanBook.setMember(memberMapper.toMember(loanBookDTO.getMember()));

        return mapper.toLoanBookDTO(repository.save(loanBook));
    }

}
