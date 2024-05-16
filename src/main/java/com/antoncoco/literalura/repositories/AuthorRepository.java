package com.antoncoco.literalura.repositories;

import com.antoncoco.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT author FROM Author author WHERE ?1 BETWEEN author.birthYear and author.deathYear")
    List<Author> getAuthorsAliveInYear(int year);

    Optional<Author> getAuthorByName(String name);
}
