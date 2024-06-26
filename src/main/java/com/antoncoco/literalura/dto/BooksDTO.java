package com.antoncoco.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BooksDTO(
        int count,
        @JsonAlias("results") List<BookDTO> books
) {
}
