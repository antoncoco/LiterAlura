package com.antoncoco.literalura.controllers;

import com.antoncoco.literalura.enums.MenuOptions;
import com.antoncoco.literalura.exceptions.BookNotFoundException;
import com.antoncoco.literalura.exceptions.HTTPStatusCode5XXException;
import com.antoncoco.literalura.models.Author;
import com.antoncoco.literalura.models.Book;
import com.antoncoco.literalura.services.AuthorService;
import com.antoncoco.literalura.services.BookService;
import com.antoncoco.literalura.utils.IMenuOptionsExecution;
import com.antoncoco.literalura.views.BooksByLanguageMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class MainMenuController implements IMenuOptionsExecution<MenuOptions> {
    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService;
    private final AuthorService authorService;
    private final BooksByLanguageMenu booksByLanguageMenu;

    @Autowired
    public MainMenuController(
            BookService bookService,
            AuthorService authorService,
            BooksByLanguageMenu booksByLanguageMenu
    ) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.booksByLanguageMenu = booksByLanguageMenu;
    }

    @Override
    public void executeMenuOption(MenuOptions menuOption) {
        switch (menuOption) {
            case SEARCH_BOOK_BY_TITLE -> {
                try {
                    System.out.println("Ingresa el título del libro que deseas buscar:");
                    String title = scanner.nextLine();
                    System.out.println("Buscando...");
                    Book bookByTitle = this.bookService.getBookByTitle(title);
                    System.out.println(bookByTitle);
                } catch (BookNotFoundException | HTTPStatusCode5XXException e) {
                    System.out.println(e.getMessage());
                }
            }
            case SEARCH_BOOK_BY_AUTHOR -> {
                try {
                    System.out.println("Ingresa el nombre del autor para buscar uno de sus libros:");
                    String authorName = scanner.nextLine();
                    System.out.println("Buscando...");
                    Book bookByAuthorName = this.bookService.getBookByAuthorName(authorName);
                    System.out.println(bookByAuthorName);
                } catch (BookNotFoundException | HTTPStatusCode5XXException e) {
                    System.out.println(e.getMessage());
                }
            }
            case LIST_ALL_BOOKS -> {
                List<Book> booksSearched = this.bookService.getAllBooksSearched();
                if (booksSearched.isEmpty()) {
                    System.out.println("No se han buscado libros. ¡Realiza tu primer búsqueda!");
                } else {
                    System.out.println("Estos son los libros que has buscado:");
                    booksSearched.forEach(System.out::println);
                }
            }
            case LIST_ALL_AUTHORS -> {
                List<Author> authorsOfBooksSearched = this.authorService.getAllAuthorsOfBooksSearched();
                if (authorsOfBooksSearched.isEmpty()) {
                    System.out.println("No hay autores que mostrar porque no se han buscado libros.\n" +
                            "¡Realiza tu primer búsqueda!");
                } else {
                    System.out.println("Estos son los autores de los libros que has buscado:");
                    authorsOfBooksSearched.forEach(System.out::println);
                }
            }
            case LIST_AUTHORS_ALIVE -> {
                try {
                    System.out.println("Ingresa el año donde quieres saber qué autores estaban con vida:");
                    int year = scanner.nextInt();
                    List<Author> authorsAliveInACertainYear = this.authorService.getAuthorsAliveInACertainYear(year);
                    if (authorsAliveInACertainYear.isEmpty()) {
                        System.out.println("NINGUNO de los autores de tus libros buscados estaba VIVO ese año");
                    } else if (authorsAliveInACertainYear.size() == 1) {
                        System.out.println("Solo este autor estaba con vida en " + year + ":");
                        System.out.println(authorsAliveInACertainYear.get(0));
                    } else {
                        System.out.println("Estos autores estaban con vida en " + year + ":");
                        authorsAliveInACertainYear.forEach(System.out::println);
                    }
                } catch (InputMismatchException e){
                    System.out.println("Entrada no valida. El año que ingreses debe ser un número entero.");
                    scanner.nextLine(); //Flush scanner
                }
            }
            case TOP_TEN_MOST_DOWNLOADED_BOOKS -> {
                List<Book> topTenBooksByDownloads = this.bookService.getTopTenBooksByDownloads();
                int realSizeOftopTenBooksByDownloads = topTenBooksByDownloads.size();
                if (realSizeOftopTenBooksByDownloads == 0) {
                    System.out.println("No has buscado ningún libro.\n" +
                            "¡Empieza a buscar para ver el TOP 10 de libros con más descargas!");
                } else if (realSizeOftopTenBooksByDownloads < 10) {
                    System.out.println("¡No has buscado suficientes libros!\n" +
                            "Debes buscar al menos " + (10 - realSizeOftopTenBooksByDownloads) + " libro(s) más " +
                            "para poder generar un TOP 10 de los más descargados.");
                } else {
                    System.out.println("Estos son los 10 libros buscados que tienen MAYOR número de descargas:");
                    topTenBooksByDownloads.forEach(System.out::println);
                }

            }
            case COUNT_BOOKS_BY_LANGUAGE, LIST_BOOKS_BY_LANGUAGE, STATISTICS_BOOKS_BY_LANGUAGE -> this.booksByLanguageMenu.init(menuOption);
            case EXIT -> System.out.println("Hasta luego. ¡Vuela pronto!");
        }
    }
}
