package com.antoncoco.literalura;

import com.antoncoco.literalura.client.GutendexClient;
import com.antoncoco.literalura.dto.BooksDTO;
import com.antoncoco.literalura.utils.JSONToObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		String json = new GutendexClient().queryToGutendexAPI("/books/?page=2&search=dickens");
		System.out.println(json);
		BooksDTO booksDTO = new JSONToObjectMapper().convertDataFromString(json, BooksDTO.class);
		booksDTO.books().forEach(bookDTO -> System.out.println(bookDTO.toString()));
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

}
