package by.bookify.user.service;

import by.bookify.domain.user.exception.UserNotFoundException;
import by.bookify.domain.user.model.User;
import by.bookify.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User hasn't been found."));
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void updateOrderNum(User user) {
        user.setOrdersNum(user.getOrdersNum() + 1);
        userRepository.save(user);
    }
}
