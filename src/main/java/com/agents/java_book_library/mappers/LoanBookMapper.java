package com.agents.java_book_library.mappers;

import com.agents.java_book_library.domains.LoanBook;
import com.agents.java_book_library.model.LoanBookDTO;
import org.mapstruct.Mapper;

@Mapper
public interface LoanBookMapper {

    LoanBook toLoanBook(LoanBookDTO loanBookDTO);
    LoanBookDTO toLoanBookDTO(LoanBook loanBook);
}
