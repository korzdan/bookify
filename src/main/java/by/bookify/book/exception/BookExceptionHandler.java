package by.bookify.book.exception;

import by.bookify.general.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class BookExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleBookNotFound(BookNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(NotEnoughBooksInStorageException.class)
    public ResponseEntity<ExceptionResponse> handleNotEnoughBooksInStorage(NotEnoughBooksInStorageException e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ExceptionResponse(e.getMessage()));
    }
}
