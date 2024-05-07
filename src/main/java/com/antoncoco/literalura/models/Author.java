package com.antoncoco.literalura.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;
    @Column(name = "author_name", unique = true, nullable = false)
    private String name;
    @Column(name = "author_birth_year")
    private int birthYear;
    @Column(name = "author_death_year")
    private int deathYear;
    @ManyToMany(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "authors_books",
            joinColumns = @JoinColumn(name = "fk_author"),
            inverseJoinColumns = @JoinColumn(name = "fk_book")
    )
    @ToString.Exclude
    private Set<Book> books;
}