package com.antoncoco.literalura.enums;

import com.antoncoco.literalura.utils.IEnumMenuOptionsMarker;
import lombok.Getter;

@Getter
public enum MenuOptions implements IEnumMenuOptionsMarker {
    SEARCH_BOOK_BY_TITLE("Buscar libro por título"),
    SEARCH_BOOK_BY_AUTHOR("Buscar libro por autor"),
    LIST_ALL_BOOKS("Listar todos los libros buscados"),
    LIST_ALL_AUTHORS("Listar los autores de todos los libros buscados"),
    LIST_AUTHORS_ALIVE("Listar cuáles autores de los libros buscados estaban vivos en cierto año"),
    LIST_BOOKS_BY_LANGUAGE("Listar los libros buscados que están en cierto idioma"),
    COUNT_BOOKS_BY_LANGUAGE("Obtener la cantidad de libros buscados que están en cierto idioma"),
    STATISTICS_BOOKS_BY_LANGUAGE("Estadísticas de de descargas (por idioma) de los libros buscados"),
    TOP_TEN_MOST_DOWNLOADED_BOOKS("Top 10 de los libros buscados que tienen más descargas"),
    EXIT("Salir de la aplicación");

    private final String optionLabel;
    MenuOptions(String optionLabel) {
        this.optionLabel = optionLabel;
    }
}
