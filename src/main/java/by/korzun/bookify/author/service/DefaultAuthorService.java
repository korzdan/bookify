package by.korzun.bookify.author.service;

import by.korzun.bookify.author.model.Author;
import by.korzun.bookify.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAuthorService implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findByFullName(String fullName) {
        return authorRepository.findByFullName(fullName);
    }
}
