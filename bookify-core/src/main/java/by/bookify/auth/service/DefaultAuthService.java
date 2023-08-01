package by.bookify.auth.service;

import by.bookify.auth.exception.UserAlreadyExistsException;
import by.bookify.auth.model.AuthRequestDto;
import by.bookify.auth.model.AuthResponseDto;
import by.bookify.auth.model.RegisterRequestDto;
import by.bookify.security.jwt.JwtService;
import by.bookify.statistics.service.StatisticsService;
import by.bookify.user.model.Role;
import by.bookify.user.model.User;
import by.bookify.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAuthService implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StatisticsService statisticsService;

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto dto, Role role) {
        checkUserExists(dto);
        User newUser = userService.save(toUser(dto, role));
        statisticsService.incrementUsersNum();
        String token = jwtService.generateToken(newUser);
        log.info("User with id: {} registered!", newUser.getId());
        return new AuthResponseDto(token);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword())
        );
        User user = (User) userService.loadUserByUsername(authRequestDto.getEmail());
        return new AuthResponseDto(jwtService.generateToken(user));
    }

    private void checkUserExists(RegisterRequestDto dto) {
        if (userService.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("User with such an email already exists.");
        }
    }

    private User toUser(RegisterRequestDto registerRequestDto, Role role) {
        return new User()
                .setName(registerRequestDto.getName())
                .setOrdersNum(0)
                .setSurname(registerRequestDto.getSurname())
                .setEmail(registerRequestDto.getEmail())
                .setPassword(passwordEncoder.encode(registerRequestDto.getPassword()))
                .setIsEnabled(true)
                .setRole(role);
    }
}
