package com.agents.java_book_library.controllers;

import com.agents.java_book_library.services.AuthorService;
//import com.agents.java_book_library.api.AuthorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    @Autowired
    private AuthorService authorService;
}
