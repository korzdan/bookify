package by.korzun.bookify.book.service;

import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.genre.model.Genre;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthorFullName(String authorFullName);
    Book findById(Long bookId);
    List<Book> findByGenre(Genre genre);
    List<Book> findByPublisherName(String publisherName);
}
