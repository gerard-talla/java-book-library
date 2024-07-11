package com.agents.java_book_library.domains;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class LoanBook {

    @NotBlank
    private Date lendDate;
    private Date returnDate;

    private Book book;

}
