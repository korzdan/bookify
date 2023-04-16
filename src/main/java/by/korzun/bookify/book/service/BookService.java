package by.korzun.bookify.book.service;

import by.korzun.bookify.book.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findByTitle(String title);
    List<Book> findByAuthorFullName(String authorFullName);
    List<Book> findByPublisherName(String publisherName);
}
