package com.antoncoco.literalura.repositories;

import com.antoncoco.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
