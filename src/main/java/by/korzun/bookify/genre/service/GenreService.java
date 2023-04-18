package by.korzun.bookify.genre.service;

import by.korzun.bookify.genre.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
    Genre findById(Long id);
}
