package by.bookify.genre.exception;

import by.bookify.general.model.ExceptionResponse;
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
