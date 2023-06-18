package by.bookify.statistics.exception;

import by.bookify.general.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class StatisticsExceptionHandler {

    @ExceptionHandler(StatisticsNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleStatisticsNotFoundException(StatisticsNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    }
}
