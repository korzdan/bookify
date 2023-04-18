package by.korzun.bookify.genre.service;

import by.korzun.bookify.genre.exception.GenreNotFound;
import by.korzun.bookify.genre.model.Genre;
import by.korzun.bookify.genre.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFound("Жанр не найден."));
    }
}
