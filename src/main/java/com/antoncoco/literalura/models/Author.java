package com.antoncoco.literalura.models;

import jakarta.persistence.*;
import lombok.*;

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
    @ToString.Exclude
    private int id;
    @Column(name = "author_name", unique = true, nullable = false)
    private String name;
    @Column(name = "author_birth_year")
    private int birthYear;
    @Column(name = "author_death_year")
    private int deathYear;
}