package com.antoncoco.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        int id,
        String title,
        List<AuthorDTO> authors,
        List<String> subjects,
        List<String> bookshelves,
        List<String> languages,
        Boolean copyright,
        @JsonAlias("download_count") int downloadCount
) {
}
