package com.antoncoco.literalura.controllers;

import com.antoncoco.literalura.enums.BookLanguageOptions;
import com.antoncoco.literalura.enums.MenuOptions;
import com.antoncoco.literalura.exceptions.UnexpectedMenuOptionException;
import com.antoncoco.literalura.models.Book;
import com.antoncoco.literalura.services.BookService;
import com.antoncoco.literalura.utils.IReusableSubMenuOptionsExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.IntSummaryStatistics;
import java.util.List;

@Component
public class BooksByLanguageMenuController
        implements IReusableSubMenuOptionsExecution<MenuOptions, BookLanguageOptions> {
    private final BookService bookService;
    @Autowired
    public BooksByLanguageMenuController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void executeMenuOption(MenuOptions mainMenuOption, BookLanguageOptions currentMenuOption)
            throws UnexpectedMenuOptionException {
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
            case STATISTICS_BOOKS_BY_LANGUAGE -> {
                IntSummaryStatistics booksDownloadsStatisticsByLanguage = this.bookService
                        .getBooksDownloadsStatisticsByLanguage(languageAbbreviation);
                System.out.println("Estas son las estadísticas de las descargas de los libros buscados" +
                        " que están en " + language + ".");
                System.out.println("Cantidad de libros: " + booksDownloadsStatisticsByLanguage.getCount());
                System.out.println("Total de descargas: " + booksDownloadsStatisticsByLanguage.getSum());
                System.out.println("Promedio de descargas: " + booksDownloadsStatisticsByLanguage.getAverage());
                System.out.println("Mínimo de descargas: " + booksDownloadsStatisticsByLanguage.getMin());
                System.out.println("Máximo de descargas: " + booksDownloadsStatisticsByLanguage.getMax());
            }
            default -> throw new UnexpectedMenuOptionException("Este submenú no está diseñado para trabajar con" +
                    " la opción: " + mainMenuOption.getOptionLabel()
                    + " del menú principal");
        }
    }
}
