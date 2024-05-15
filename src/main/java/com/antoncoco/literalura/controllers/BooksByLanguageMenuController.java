package com.antoncoco.literalura.controllers;

import com.antoncoco.literalura.enums.BookLanguageOptions;
import com.antoncoco.literalura.enums.MenuOptions;
import com.antoncoco.literalura.models.Book;
import com.antoncoco.literalura.services.BookService;
import com.antoncoco.literalura.utils.IReusableSubMenuOptionsExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksByLanguageMenuController implements IReusableSubMenuOptionsExecution<MenuOptions, BookLanguageOptions> {
    private final BookService bookService;
    @Autowired
    public BooksByLanguageMenuController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void executeMenuOption(MenuOptions mainMenuOption, BookLanguageOptions currentMenuOption) {
        String language = currentMenuOption.getLabel();
        String languageAbbreviation = currentMenuOption.getAbbreviation();
        switch (mainMenuOption) {
            case LIST_BOOKS_BY_LANGUAGE -> {
                List<Book> allBooksByLanguage = this.bookService.getBooksByLanguage(languageAbbreviation);
                if (allBooksByLanguage.isEmpty()) {
                    System.out.println("Ninguno de los libros buscados está en el idioma: " + language);
                } else if (allBooksByLanguage.size() == 1) {
                    System.out.println("Este es el único libro buscado que está en " + language + ":");
                    System.out.println(allBooksByLanguage.get(0));
                } else {
                    System.out.println("Los libros que están en " + language + " son:");
                    allBooksByLanguage.forEach(System.out::println);
                }
            }
            case COUNT_BOOKS_BY_LANGUAGE -> {
                int countBooksByLanguage = this.bookService.countBooksByLanguage(languageAbbreviation);
                System.out.println("La cantidad de libros consultados que están en "
                        + language + " es: " + countBooksByLanguage);
            }
            default -> System.out.println("Este submenú aún no está preparado para procesar " +
                    "la opción seleccionada en el menú principal.");
        }
    }
}
