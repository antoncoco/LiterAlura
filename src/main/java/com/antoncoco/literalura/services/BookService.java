package com.antoncoco.literalura.services;

import com.antoncoco.literalura.client.GutendexClient;
import com.antoncoco.literalura.dto.BookDTO;
import com.antoncoco.literalura.dto.BooksDTO;
import com.antoncoco.literalura.models.Book;
import com.antoncoco.literalura.repositories.BookRepository;
import com.antoncoco.literalura.utils.JSONToObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookByAuthorName(String authorName) {
        Optional<Book> optionalBook = this.bookRepository.findByAuthorName(authorName);
        if(optionalBook.isEmpty()) {
            Book bookFromAPI = this.getBookFromAPI(authorName);
            this.bookRepository.save(bookFromAPI);
            return bookFromAPI;
        }
        return optionalBook.get();
    }
    private Book getBookFromAPI(String searchParameter) {
        String json = new GutendexClient().queryToGutendexAPI("/books/?search=" + searchParameter);
        BooksDTO booksDTO = new JSONToObjectMapper().convertDataFromString(json, BooksDTO.class);
        return this.convertBookDTOToBook(booksDTO.books().get(0));
    }

    Book convertBookDTOToBook(BookDTO bookDTO) {
        return Book.builder()
                .id(bookDTO.id())
                .title(bookDTO.title())
                .author(AuthorService.convertAuthorDTOToAuthor(bookDTO.authors().get(0)))
                .downloads(bookDTO.downloadCount())
                .language(bookDTO.languages().get(0))
                .build();
    }
}
