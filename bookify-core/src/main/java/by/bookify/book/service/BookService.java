package by.bookify.book.service;

import by.bookify.book.model.Book;
import by.bookify.book.model.BookCreateDto;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long bookId);

    Book updateBookQuantityParameters(Long id, Integer number);

    Book createBook(BookCreateDto dto);
}
