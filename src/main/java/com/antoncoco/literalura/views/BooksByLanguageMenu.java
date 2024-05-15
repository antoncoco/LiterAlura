package com.antoncoco.literalura.views;

import com.antoncoco.literalura.controllers.BooksByLanguageMenuController;
import com.antoncoco.literalura.enums.BookLanguageOptions;
import com.antoncoco.literalura.enums.MenuOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class BooksByLanguageMenu {
    private final Scanner scanner;
    private final BooksByLanguageMenuController booksByLanguageMenuController;

    @Autowired
    public BooksByLanguageMenu(BooksByLanguageMenuController booksByLanguageMenuController) {
        this.scanner = new Scanner(System.in);
        this.booksByLanguageMenuController = booksByLanguageMenuController;
    }

    public void init(MenuOptions menuOption) {
        int option, numberOfOptions;
        boolean hasSelectedCorrectOption = false;
        do {
            BookLanguageOptions[] bookLanguageOptions = BookLanguageOptions.values();
            numberOfOptions = bookLanguageOptions.length;
            System.out.println("======IDIOMAS======");
            System.out.println("Selecciona la opción que corresponda al idioma de los libros que deseas consultar:");
            for(int i = 0; i < numberOfOptions; i++) {
                System.out.println((i + 1) + " - " + bookLanguageOptions[i].getLabel());
            }
            option = scanner.nextInt();
            int optionIndex = option - 1;
            if(optionIndex >= 0 && optionIndex < numberOfOptions ) {
                BookLanguageOptions selectedOption = bookLanguageOptions[optionIndex];
                booksByLanguageMenuController.executeMenuOption(menuOption, selectedOption);
                hasSelectedCorrectOption = true;
            } else {
                System.out.println("Has puesto una opción inválida. ¡Intenta de nuevo!");
            }
        } while(!hasSelectedCorrectOption);
    }
}
