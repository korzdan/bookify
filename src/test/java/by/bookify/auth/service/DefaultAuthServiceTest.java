package by.bookify.auth.service;

import by.bookify.auth.model.AuthResponseDto;
import by.bookify.auth.model.RegisterRequestDto;
import by.bookify.security.jwt.JwtService;
import by.bookify.statistics.service.StatisticsService;
import by.bookify.user.model.Role;
import by.bookify.user.model.User;
import by.bookify.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultAuthServiceTest {

    @Mock
    private StatisticsService statisticsService;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;
    @InjectMocks
    private DefaultAuthService authService;

    @Test
    void register() {
        RegisterRequestDto dto = new RegisterRequestDto()
                .setEmail("Email")
                .setPassword("Password");
        User newUser = new User()
                .setEmail(dto.getEmail())
                .setPassword("123")
                .setOrdersNum(0)
                .setIsEnabled(true)
                .setRole(Role.ROLE_USER);

        when(passwordEncoder.encode("Password"))
                .thenReturn("123");
        when(userService.save(newUser))
                .thenReturn(newUser);
        when(jwtService.generateToken(newUser))
                .thenReturn("123");
        AuthResponseDto result = authService.register(dto, Role.ROLE_USER);

        assertEquals("123", result.getToken());
    }

}
