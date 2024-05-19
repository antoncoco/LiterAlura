package com.antoncoco.literalura.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Book {
    @Id
    @Column(name = "book_id", nullable = false)
    private int id;
    @Column(name = "book_title", nullable = false)
    private String title;
    @Column(name = "book_downloads")
    private int downloads;
    @Column(name = "book_language")
    private String language;
    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Author author;

    @Override
    public String toString() {
        return "Libro: " + this.title
                + ".\n\tCuenta con " + this.downloads + " descargas.\n\t"
                + this.author.toString();
    }
}
