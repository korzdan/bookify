package by.bookify.book.service;

import by.bookify.book.exception.BookCreationException;
import by.bookify.book.exception.BookNotFoundException;
import by.bookify.book.model.Book;
import by.bookify.book.model.BookCreateDto;
import by.bookify.book.model.BookLanguage;
import by.bookify.book.repository.BookRepository;
import by.bookify.genre.service.GenreService;
import by.bookify.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final StatisticsService statisticsService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found."));
    }

    @Override
    public Book updateBookQuantityParameters(Long id, Integer number) {
        Book book = findById(id);
        book.setStorageNum(book.getStorageNum() - number)
                .setOrderNum(book.getOrderNum() + number);
        Book updatedBook = bookRepository.save(book);
        log.info("Book with id: {} ordered. In storage left {}", id, book.getStorageNum());
        return updatedBook;
    }

    @Override
    @Transactional
    public Book createBook(BookCreateDto dto) {
        Book book = toBook(dto);
        statisticsService.incrementBooksNum();
        Book newBook = bookRepository.save(book);
        log.info("Book has been created with id: {}", newBook.getId());
        return newBook;
    }

    private Book toBook(BookCreateDto dto) {
        try {
            return new Book()
                    .setTitle(dto.getTitle())
                    .setDescription(dto.getDescription())
                    .setPublicationDate(LocalDate.parse(dto.getPublicationDate()))
                    .setGenre(genreService.findById(dto.getGenreId()))
                    .setPages(dto.getPages())
                    .setIsbn(dto.getIsbn())
                    .setLanguage(BookLanguage.valueOf(dto.getLanguage().toUpperCase()))
                    .setStorageNum(dto.getStorageNum())
                    .setOrderNum(0)
                    .setAuthor(dto.getAuthor())
                    .setPublisher(dto.getPublisher())
                    .setPrice(dto.getPrice());
        } catch (DateTimeParseException e) {
            throw new BookCreationException("The date must be in format YYYY-MM-DD.");
        }
    }
}
