package by.bookify.book.service;

import by.bookify.book.exception.BookNotFoundException;
import by.bookify.book.model.Book;
import by.bookify.book.model.BookCreateDto;
import by.bookify.book.model.BookLanguage;
import by.bookify.book.repository.BookRepository;
import by.bookify.genre.service.GenreService;
import by.bookify.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final StatisticsService statisticsService;

    @Override
    @Cacheable("books")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value = "single-book", key = "#bookId")
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found."));
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value ="single-book", key="#id"),
                    @CacheEvict(value="books", allEntries= true)
            }
    )
    public void updateBookQuantityParameters(Long id, Integer number) {
        Book book = findById(id);
        book.setStorageNum(book.getStorageNum() - number)
                .setOrderNum(book.getOrderNum() + number);
        bookRepository.save(book);
        log.info("Book with id: {} ordered. In storage left {}", id, book.getStorageNum());
    }

    @Override
    @Transactional
    @CacheEvict(value = "books", allEntries = true)
    public Book createBook(BookCreateDto dto) {
        Book book = toBook(dto);
        statisticsService.incrementBooksNum();
        Book newBook = bookRepository.save(book);
        log.info("Book has been created with id: {}", newBook.getId());
        return newBook;
    }

    private Book toBook(BookCreateDto dto) {
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
    }
}
