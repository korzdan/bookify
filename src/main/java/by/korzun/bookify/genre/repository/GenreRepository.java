package by.korzun.bookify.genre.repository;

import by.korzun.bookify.genre.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findALl();
}
