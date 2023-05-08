package by.korzun.bookify.genre.controller;

import by.korzun.bookify.genre.model.Genre;
import by.korzun.bookify.genre.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> findAll() {
        return ResponseEntity.ok(genreService.findAll());
    }
}
