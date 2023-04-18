package by.korzun.bookify.user.service;

import by.korzun.bookify.auth.model.UserRegisterRequestDto;
import by.korzun.bookify.author.service.AuthorService;
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

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден."));
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден."));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User addGenreRecommendationsToUser(String userEmail, List<Long> genreIds) {
        return findByEmail(userEmail)
                .setGenreRecommendations(
                        genreIds.stream()
                                .map(genreService::findById)
                                .collect(Collectors.toList())
                );
    }

    public User addAuthorRecommendationsToUser(String userEmail, List<Long> authorIds) {
        return findByEmail(userEmail)
                .setAuthorRecommendations(
                        authorIds.stream()
                                .map(authorService::findById)
                                .collect(Collectors.toList())
                );
    }
}
