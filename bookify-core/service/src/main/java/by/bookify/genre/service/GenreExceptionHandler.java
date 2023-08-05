package by.bookify.genre.service;

import by.bookify.domain.genre.exception.GenreNotFoundException;
import by.bookify.general.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GenreExceptionHandler {

    @ExceptionHandler(GenreNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleGenreNotFound(GenreNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }
}
