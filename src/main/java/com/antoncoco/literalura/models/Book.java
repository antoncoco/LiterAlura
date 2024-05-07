package com.antoncoco.literalura.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Book {
    @Id
    @Column(name = "book_id", nullable = false)
    private int id;
    @Column(name = "book_title", nullable = false)
    private String title;
    @Column(name = "book_authors")
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<Author> authors;
}
