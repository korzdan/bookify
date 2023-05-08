package by.korzun.bookify.book.service;

import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.book.model.CreateBookDto;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findByTitle(String title);
    Book findById(Long bookId);
    Book decrementStorageNumAndIncOrderNum(Long id, Integer number);
    Book createBook(CreateBookDto dto);
}
