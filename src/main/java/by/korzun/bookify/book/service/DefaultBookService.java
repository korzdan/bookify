package by.korzun.bookify.book.service;

import by.korzun.bookify.book.exception.BookNotFound;
import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.book.model.BookLanguage;
import by.korzun.bookify.book.model.CreateBookDto;
import by.korzun.bookify.book.repository.BookRepository;
import by.korzun.bookify.genre.service.GenreService;
import by.korzun.bookify.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final StatisticsService statisticsService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public Book findById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFound("Книга не найдена."));
    }

    @Override
    public Book decrementStorageNumAndIncOrderNum(Long id, Integer number) {
        Book book = findById(id);
        book.setStorageNum(book.getStorageNum() - number);
        book.setOrderNum(book.getOrderNum() + number);
        return bookRepository.save(book);
    }

    @Override
    public Book createBook(CreateBookDto dto) {
        Book newBook = new Book()
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
        statisticsService.incBooks();
        return bookRepository.save(newBook);
    }
}
