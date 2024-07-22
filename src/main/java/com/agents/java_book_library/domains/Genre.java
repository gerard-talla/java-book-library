package com.agents.java_book_library.domains;

import lombok.Getter;

@Getter
public enum Genre {

    FICTION("Fiction"),

    MYSTERY("Mystery"),

    SCIENCE_FICTION("Science fiction"),

    NARRATIVE("Narrative"),

    SCIENCE("Science"),

    HISTORICAL("Historical"),

    NOVEL("Novel"),

    THRILLER("Thriller"),

    EDUCATION("Education");

    private String value;

    Genre(String value) {
        this.value = value;
    }
}
