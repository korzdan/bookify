package by.bookify.user.service;

import by.bookify.domain.user.exception.UserNotFoundException;
import by.bookify.domain.user.model.User;
import by.bookify.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    void loadUserByUsername_userExist_true() {
        String username = "username";
        User user = new User()
                .setName("name")
                .setSurname("surname")
                .setEmail("username");

        when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));
        User actualUser = userService.loadUserByUsername(username);

        assertEquals("name", actualUser.getName());
        assertEquals("surname", actualUser.getSurname());
        assertEquals("username", actualUser.getUsername());
    }

    @Test
    void loadUserByUsername_userDoesNotExist_thrownException() {
        String username = "username";

        when(userRepository.findByEmail(username)).thenReturn(Optional.empty());

        UserNotFoundException actualException = assertThrows(
                UserNotFoundException.class,
                () -> userService.loadUserByUsername(username));
        assertEquals("User hasn't been found.", actualException.getMessage());
    }

    @Test
    void existsByEmail_verifyCallOfExistsByEmail_true() {
        String email = "email";

        userService.existsByEmail(email);
        verify(userRepository).existsByEmail(email);
    }

    @Test
    void save_verifyCallOfSave_true() {
        User user = new User()
                .setName("name")
                .setSurname("surname")
                .setEmail("username");

        userService.save(user);
        verify(userRepository).save(user);
    }
}
