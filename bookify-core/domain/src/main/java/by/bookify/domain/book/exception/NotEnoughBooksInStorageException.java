package by.bookify.domain.book.exception;

public class NotEnoughBooksInStorageException extends RuntimeException {
    public NotEnoughBooksInStorageException(String message) {
        super(message);
    }
}
