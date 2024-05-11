package com.antoncoco.literalura.controllers;

import com.antoncoco.literalura.enums.MenuOptions;
import com.antoncoco.literalura.models.Book;
import com.antoncoco.literalura.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuController {
    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService;

    @Autowired
    public MenuController(BookService bookService) {
        this.bookService = bookService;
    }

    public void executeMenuOption(MenuOptions menuOption) {
        switch (menuOption) {
            case SEARCH_BY_TITLE -> {
                System.out.println("Ingresa el nombre del autor que deseas buscar:");
                String authorName = scanner.nextLine();
                Book bookByAuthorName = bookService.getBookByAuthorName(authorName);
                System.out.println(bookByAuthorName);
            }
            case LIST_ALL -> {
                System.out.println("Estos son los libros que has buscado:");
                this.bookService.getAllBooksSearched().forEach(System.out::println);
            }
            case EXIT -> System.out.println("Hasta luego. ¡Vuela pronto!");
        }
    }
}
