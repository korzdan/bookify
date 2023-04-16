package by.korzun.bookify.book.service;

import by.korzun.bookify.author.model.Author;
import by.korzun.bookify.author.service.AuthorService;
import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.book.repository.BookRepository;
import by.korzun.bookify.publisher.model.Publisher;
import by.korzun.bookify.publisher.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthorFullName(String authorFullName) {
        return authorService.findByFullName(authorFullName).stream()
                .map(Author::getBooks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByPublisherName(String publisherName) {
        return publisherService.findByName(publisherName).stream()
                .map(Publisher::getBooks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
