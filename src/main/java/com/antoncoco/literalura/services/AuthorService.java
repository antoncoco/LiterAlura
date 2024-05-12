package com.antoncoco.literalura.services;

import com.antoncoco.literalura.dto.AuthorDTO;
import com.antoncoco.literalura.models.Author;
import com.antoncoco.literalura.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthorsOfBooksSearched() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsAliveInACertainYear(int year) {
        return authorRepository.getAuthorsAliveInYear(year);
    }

    static Author convertAuthorDTOToAuthor(AuthorDTO authorDTO) {
        return Author.builder()
                .name(authorDTO.name())
                .birthYear(authorDTO.birthYear())
                .deathYear(authorDTO.deathYear())
                .build();
    }
}
