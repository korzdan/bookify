package by.bookify.user.service;

import by.bookify.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Boolean existsByEmail(String email);

    User save(User user);

    void updateOrderNum(User user);
}
