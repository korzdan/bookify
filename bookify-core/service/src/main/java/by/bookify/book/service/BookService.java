package by.bookify.book.service;


import by.bookify.domain.book.dto.BookCreateDto;
import by.bookify.domain.book.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long bookId);

    Book updateBookQuantityParameters(Long id, Integer number);

    Book createBook(BookCreateDto dto);
}
