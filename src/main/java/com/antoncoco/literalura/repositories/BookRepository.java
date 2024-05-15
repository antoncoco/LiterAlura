package com.antoncoco.literalura.repositories;

import com.antoncoco.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT book from Book book join Author author " +
            "on book.author.id = author.id where UPPER(author.name) LIKE CONCAT('%', UPPER(?1), '%') " +
            "ORDER BY book.id LIMIT 1")
    Optional<Book> findByAuthorName(String author);

    int countBooksByLanguage(String language);

    List<Book> findBooksByLanguage(String language);
}
