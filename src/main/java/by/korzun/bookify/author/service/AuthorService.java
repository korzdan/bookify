package by.korzun.bookify.author.service;

import by.korzun.bookify.author.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findByFullName(String fullName);
}
