package com.antoncoco.literalura.views;

import com.antoncoco.literalura.controllers.MenuController;
import com.antoncoco.literalura.enums.MenuOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {
    private final Scanner scanner;
    private final MenuController menuController;

    @Autowired
    public MainMenu(MenuController menuController) {
        scanner = new Scanner(System.in);
        this.menuController = menuController;
    }
    public void init() {
        MenuOptions optionSelected;
        do {
            System.out.println("=============MENU==============");
            MenuOptions[] options = MenuOptions.values();
            int numberOfOptions = options.length;
            for(int i = 0; i < numberOfOptions; i++){
                System.out.println((i + 1) + " - " + options[i].getOptionLabel());
            }
            System.out.println("Selecciona la opción deseada: ");
            int option = scanner.nextInt();
            optionSelected = options[option - 1];
            menuController.executeMenuOption(optionSelected);
        } while (optionSelected != MenuOptions.EXIT);
    }
}
