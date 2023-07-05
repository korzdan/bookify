package by.bookify.book.service;

import by.bookify.book.exception.BookNotFoundException;
import by.bookify.book.model.Book;
import by.bookify.book.model.BookLanguage;
import by.bookify.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DefaultBookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private DefaultBookService bookService;

    @Test
    void findById_bookExist_True() {
        Long bookId = 1L;
        Book book = new Book()
                .setAuthor("Author")
                .setLanguage(BookLanguage.RUS)
                .setTitle("Title");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        Book actualBook = bookService.findById(bookId);

        assertEquals(actualBook.getAuthor(), "Author");
        assertEquals(actualBook.getLanguage(), BookLanguage.RUS);
        assertEquals(actualBook.getTitle(), "Title");
    }

    @Test
    void findById_bookDoesNotExist_ThrownException() {
        Long bookId = 10L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
        BookNotFoundException actualException = assertThrows(BookNotFoundException.class,
                () -> bookService.findById(bookId));

        assertEquals("Book not found.", actualException.getMessage());
    }
}
