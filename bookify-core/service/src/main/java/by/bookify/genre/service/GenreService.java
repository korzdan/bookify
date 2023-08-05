package by.bookify.genre.service;

import by.bookify.domain.genre.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();

    Genre findById(Long id);
}
