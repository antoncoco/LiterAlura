package com.antoncoco.literalura.services;

import com.antoncoco.literalura.client.GutendexClient;
import com.antoncoco.literalura.dto.BookDTO;
import com.antoncoco.literalura.dto.BooksDTO;
import com.antoncoco.literalura.exceptions.BookNotFoundException;
import com.antoncoco.literalura.models.Author;
import com.antoncoco.literalura.models.Book;
import com.antoncoco.literalura.repositories.AuthorRepository;
import com.antoncoco.literalura.repositories.BookRepository;
import com.antoncoco.literalura.utils.JSONToObjectMapper;
import com.antoncoco.literalura.utils.URLNormalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> getAllBooksSearched() {
        return bookRepository.findAll();
    }

    public int countBooksByLanguage(String language) {
        return bookRepository.countBooksByLanguage(language);
    }

    public List<Book> getBooksByLanguage(String language) {
        return bookRepository.findBooksByLanguage(language);
    }

    public Book getBookByTitle(String title) throws BookNotFoundException {
        Optional<Book> optionalBook = this.bookRepository.findByTitleContainsIgnoreCase(title);
        return optionalBook
                .orElse(this.getAndSaveNewBookIfNotAlreadyPresent(optionalBook, title));
    }

    public Book getBookByAuthorName(String authorName) throws BookNotFoundException {
        Optional<Book> optionalBook = this.bookRepository.findByAuthorName(authorName);
        return optionalBook
                .orElse(this.getAndSaveNewBookIfNotAlreadyPresent(optionalBook, authorName));
    }

    private Book getBookFromAPI(String searchParameter) throws BookNotFoundException {
        String json = new GutendexClient()
                .queryToGutendexAPI("/books/?search="
                        + URLNormalizer.normalizeSearchParameterWithSpaces(searchParameter));
        BooksDTO booksDTO = new JSONToObjectMapper().convertDataFromString(json, BooksDTO.class);
        if (booksDTO.books().isEmpty()) {
            throw new BookNotFoundException("Ningún libro coincide con la búsqueda: " + searchParameter);
        }
        return this.convertBookDTOToBook(booksDTO.books().get(0));
    }

    private Book getAndSaveNewBookIfNotAlreadyPresent(Optional<Book> optionalBook, String searchParameter) throws BookNotFoundException {
        if(optionalBook.isEmpty()) {
            Book bookFromAPI = this.getBookFromAPI(searchParameter);
            Optional<Book> optionalBookIsAlreadyPersisted = this.bookRepository.findBookById(bookFromAPI.getId());
            if (optionalBookIsAlreadyPersisted.isEmpty()) {
                Optional<Integer> optionalPossibleAuthorId = getIdIfAuthorIsAlreadyStored(bookFromAPI.getAuthor());
                optionalPossibleAuthorId
                        .ifPresent(authorId -> bookFromAPI.getAuthor().setId(authorId));
                this.bookRepository.save(bookFromAPI);
            }
            return bookFromAPI;
        }
        return optionalBook.get();
    }

    private Optional<Integer> getIdIfAuthorIsAlreadyStored(Author author) {
        Optional<Author> optionalAuthor = this.authorRepository.getAuthorByName(author.getName());
        return optionalAuthor.map(Author::getId);
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
