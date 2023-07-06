package by.bookify.genre.service;

import by.bookify.genre.exception.GenreNotFoundException;
import by.bookify.genre.model.Genre;
import by.bookify.genre.repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultGenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private DefaultGenreService genreService;

    @Test
    void findById_genreExist_true() {
        Long genreId = 1L;
        Genre genre = new Genre().setName("Genre");

        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));
        Genre actualGenre = genreService.findById(genreId);

        assertEquals(actualGenre.getName(), "Genre");
    }

    @Test
    void findById_genreDoesNotExist_thrownException() {
        Long bookId = 10L;

        when(genreRepository.findById(bookId)).thenReturn(Optional.empty());
        GenreNotFoundException actualException = assertThrows(GenreNotFoundException.class,
                () -> genreService.findById(bookId));

        assertEquals("Genre not found.", actualException.getMessage());
    }

    @Test
    void findAll_verifyCallOfFindAll_true() {
        genreService.findAll();
        verify(genreRepository).findAll();
    }
}
