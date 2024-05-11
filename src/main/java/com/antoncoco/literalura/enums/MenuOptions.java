package com.antoncoco.literalura.enums;

import lombok.Getter;

@Getter
public enum MenuOptions {
    SEARCH_BY_TITLE("Búsqueda de libro por título"),
    LIST_ALL("Lista de todos los libros"),
    EXIT("Salir de la aplicación");

    private final String optionLabel;
    MenuOptions(String optionLabel) {
        this.optionLabel = optionLabel;
    }
}
