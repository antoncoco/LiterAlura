package com.antoncoco.literalura;

import com.antoncoco.literalura.views.MainMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	private final MainMenu mainMenu;

	@Autowired
	public LiteraluraApplication(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	@Override
	public void run(String... args) {
		mainMenu.init();
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

}
