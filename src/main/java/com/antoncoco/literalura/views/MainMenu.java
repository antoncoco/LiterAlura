package com.antoncoco.literalura.views;

import com.antoncoco.literalura.controllers.MainMenuController;
import com.antoncoco.literalura.enums.MenuOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu implements IMenuView {
    private final Scanner scanner;
    private final MainMenuController mainMenuController;

    @Autowired
    public MainMenu(MainMenuController mainMenuController) {
        scanner = new Scanner(System.in);
        this.mainMenuController = mainMenuController;
    }

    @Override
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
            mainMenuController.executeMenuOption(optionSelected);
        } while (optionSelected != MenuOptions.EXIT);
    }
}
