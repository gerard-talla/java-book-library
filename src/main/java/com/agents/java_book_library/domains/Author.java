package com.agents.java_book_library.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Author {

    @Id
    @GeneratedValue
    private Integer authorId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Date of birth is mandatory")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
