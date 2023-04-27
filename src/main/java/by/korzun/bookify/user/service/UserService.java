package by.korzun.bookify.user.service;

import by.korzun.bookify.author.model.Author;
import by.korzun.bookify.author.service.AuthorService;
import by.korzun.bookify.book.model.Book;
import by.korzun.bookify.book.service.BookService;
import by.korzun.bookify.genre.service.GenreService;
import by.korzun.bookify.user.model.User;
import by.korzun.bookify.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден."));
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User addGenreRecommendationsToUser(String userEmail, List<Long> genreIds) {
        User user = loadUserByUsername(userEmail)
                .setGenreRecommendations(
                        genreIds.stream()
                                .map(genreService::findById)
                                .collect(Collectors.toList())
                );
        return userRepository.save(user);
    }

    public User addAuthorRecommendationsToUser(String userEmail, List<Long> authorIds) {
        User user = loadUserByUsername(userEmail)
                .setAuthorRecommendations(
                        authorIds.stream()
                                .map(authorService::findById)
                                .collect(Collectors.toList())
                );
        return userRepository.save(user);
    }

    public List<Book> getBooksBasedOnAuthorRecommendations(User user) {
        return user.getAuthorRecommendations().stream()
                .map(Author::getBooks)
                .flatMap(List::stream)
                .limit(3)
                .toList();
    }

    public List<Book> getBooksBasedOnGenreRecommendations(User user) {
        return user.getGenreRecommendations().stream()
                .map(bookService::findByGenre)
                .flatMap(List::stream)
                .limit(3)
                .toList();
    }
}
