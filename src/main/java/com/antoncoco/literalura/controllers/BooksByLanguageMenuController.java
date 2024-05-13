package com.antoncoco.literalura.controllers;

import com.antoncoco.literalura.enums.BookLanguageOptions;
import com.antoncoco.literalura.services.BookService;
import com.antoncoco.literalura.utils.IMenuOptionsExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BooksByLanguageMenuController implements IMenuOptionsExecution<BookLanguageOptions> {
    private final BookService bookService;
    @Autowired
    public BooksByLanguageMenuController(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void executeMenuOption(BookLanguageOptions menuOption) {
        int countBooksByLanguage = this.bookService.countBooksByLanguage(menuOption.getAbbreviation());
        System.out.println("La cantidad de libros consultados que están en "
                + menuOption.getLabel()
                + " es: " + countBooksByLanguage);
    }
}
