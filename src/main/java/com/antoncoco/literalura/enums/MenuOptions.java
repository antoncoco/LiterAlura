package com.antoncoco.literalura.enums;

import com.antoncoco.literalura.utils.IEnumMenuOptionsMarker;
import lombok.Getter;

@Getter
public enum MenuOptions implements IEnumMenuOptionsMarker {
    SEARCH_BOOK_BY_TITLE("Búsqueda de libro por título"),
    LIST_ALL_BOOKS("Lista de todos los libros buscados"),
    LIST_ALL_AUTHORS("Lista de los autores de todos los libros buscados"),
    LIST_AUTHORS_ALIVE("Lista de autores de los libros buscados que estaban vivos en cierto año"),
    COUNT_BOOKS_BY_LANGUAGE("Cantidad de los libros buscados que están en cierto idioma"),
    EXIT("Salir de la aplicación");

    private final String optionLabel;
    MenuOptions(String optionLabel) {
        this.optionLabel = optionLabel;
    }
}
