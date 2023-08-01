package by.bookify.book.service;

import by.bookify.book.exception.BookCreationException;
import by.bookify.book.exception.BookNotFoundException;
import by.bookify.book.model.Book;
import by.bookify.book.model.BookCreateDto;
import by.bookify.book.model.BookLanguage;
import by.bookify.book.repository.BookRepository;
import by.bookify.genre.model.Genre;
import by.bookify.genre.service.GenreService;
import by.bookify.statistics.service.StatisticsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultBookServiceTest {

    public static final Long BOOK_ID = 1L;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private GenreService genreService;
    @Mock
    private StatisticsService statisticsService;
    @InjectMocks
    private DefaultBookService bookService;

    @Test
    void findById_bookExist_true() {
        Book book = new Book()
                .setAuthor("Author")
                .setLanguage(BookLanguage.RUS)
                .setTitle("Title");

        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));
        Book actualBook = bookService.findById(BOOK_ID);

        assertEquals("Author", actualBook.getAuthor());
        assertEquals(BookLanguage.RUS, actualBook.getLanguage());
        assertEquals("Title", actualBook.getTitle());
    }

    @Test
    void findById_bookDoesNotExist_thrownException() {
        when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());

        BookNotFoundException actualException = assertThrows(BookNotFoundException.class,
                () -> bookService.findById(BOOK_ID));
        assertEquals("Book not found.", actualException.getMessage());
    }

    @Test
    void updateBookQuantityParameters() {
        Book book = new Book()
                .setOrderNum(2)
                .setStorageNum(2);

        when(bookRepository.findById(BOOK_ID))
                .thenReturn(Optional.of(book));
        when(bookRepository.save(book))
                .thenReturn(book);
        Book actualBook = bookService.updateBookQuantityParameters(BOOK_ID, 1);

        assertEquals(3, actualBook.getOrderNum());
        assertEquals(1, actualBook.getStorageNum());
    }

    @Test
    void createBook() {
        BookCreateDto dto = new BookCreateDto()
                .setTitle("Title")
                .setPublicationDate("2020-07-12")
                .setGenreId(1L)
                .setLanguage("RUS");
        Genre genre = new Genre()
                .setName("Genre");
        Book expectedBook = new Book()
                .setTitle("Title")
                .setPublicationDate(LocalDate.parse("2020-07-12"))
                .setGenre(genre)
                .setLanguage(BookLanguage.RUS);

        when(bookRepository.save(any()))
                .thenReturn(expectedBook);
        when(genreService.findById(1L))
                .thenReturn(genre);
        Book actualBook = bookService.createBook(dto);

        assertEquals(expectedBook.getTitle(), actualBook.getTitle());
        assertEquals(expectedBook.getPublicationDate(), actualBook.getPublicationDate());
        assertEquals(expectedBook.getLanguage(), actualBook.getLanguage());
        assertEquals(expectedBook.getGenre(), actualBook.getGenre());
    }

    @Test
    void createBook_throwsBookCreationException() {
        BookCreateDto dto = new BookCreateDto()
                .setPublicationDate("date");

        BookCreationException exception = assertThrows(
                BookCreationException.class,
                () -> bookService.createBook(dto)
        );
        assertEquals("The date must be in format YYYY-MM-DD.", exception.getMessage());
    }
}
