package com.antoncoco.literalura.controllers;

import com.antoncoco.literalura.enums.MenuOptions;
import com.antoncoco.literalura.models.Book;
import com.antoncoco.literalura.services.AuthorService;
import com.antoncoco.literalura.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuController {
    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public MenuController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    public void executeMenuOption(MenuOptions menuOption) {
        switch (menuOption) {
            case SEARCH_BOOK_BY_TITLE -> {
                System.out.println("Ingresa el nombre del autor que deseas buscar:");
                String authorName = scanner.nextLine();
                Book bookByAuthorName = bookService.getBookByAuthorName(authorName);
                System.out.println(bookByAuthorName);
            }
            case LIST_ALL_BOOKS -> {
                System.out.println("Estos son los libros que has buscado:");
                this.bookService.getAllBooksSearched().forEach(System.out::println);
            }
            case LIST_ALL_AUTHORS -> {
                System.out.println("Estos son los autores de los libros que has buscado:");
                this.authorService.getAllAuthorsOfBooksSearched().forEach(System.out::println);
            }
            case EXIT -> System.out.println("Hasta luego. ¡Vuela pronto!");
        }
    }
}
