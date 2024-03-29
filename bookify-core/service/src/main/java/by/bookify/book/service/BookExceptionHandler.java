package by.bookify.book.service;

import by.bookify.domain.book.exception.BookCreationException;
import by.bookify.domain.book.exception.BookNotFoundException;
import by.bookify.domain.book.exception.NotEnoughBooksInStorageException;
import by.bookify.general.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBookNotFound(BookNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(NotEnoughBooksInStorageException.class)
    public ResponseEntity<ExceptionResponse> handleNotEnoughBooksInStorage(NotEnoughBooksInStorageException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(BookCreationException.class)
    public ResponseEntity<ExceptionResponse> handleBookCreationException(BookCreationException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ExceptionResponse(e.getMessage()));
    }
}
