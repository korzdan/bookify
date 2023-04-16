package by.korzun.bookify.author.model.exception;

import lombok.Getter;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
