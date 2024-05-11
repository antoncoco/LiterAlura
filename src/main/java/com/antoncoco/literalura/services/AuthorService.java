package com.antoncoco.literalura.services;

import com.antoncoco.literalura.dto.AuthorDTO;
import com.antoncoco.literalura.models.Author;

public class AuthorService {
    static Author convertAuthorDTOToAuthor(AuthorDTO authorDTO) {
        return Author.builder()
                .name(authorDTO.name())
                .birthYear(authorDTO.birthYear())
                .deathYear(authorDTO.deathYear())
                .build();
    }
}
