package by.korzun.bookify.auth.service;

import by.korzun.bookify.auth.exception.UserAlreadyExistsException;
import by.korzun.bookify.auth.model.AuthRequestDto;
import by.korzun.bookify.auth.model.AuthResponseDto;
import by.korzun.bookify.auth.model.RegisterRequestDto;
import by.korzun.bookify.security.service.JwtService;
import by.korzun.bookify.user.model.Role;
import by.korzun.bookify.user.model.User;
import by.korzun.bookify.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAuthService implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto registerUser(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("Пользователь с таким email уже существует.");
        }
        User newUser = userRepository.save(toUser(registerRequestDto, Role.USER));
        return new AuthResponseDto(jwtService.generateToken(newUser));
    }

    @Override
    public AuthResponseDto registerAdmin(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("Админ с таким email уже существует.");
        }
        User newUser = userRepository.save(toUser(registerRequestDto, Role.ADMIN));
        return new AuthResponseDto(jwtService.generateToken(newUser));
    }

    @Override
    public AuthResponseDto registerSuperAdmin(RegisterRequestDto registerRequestDto) {
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("Супер-админ с таким email уже существует.");
        }
        User newUser = userRepository.save(toUser(registerRequestDto, Role.SUPER_ADMIN));
        return new AuthResponseDto(jwtService.generateToken(newUser));
    }

    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword())
        );
        User user = userRepository.findByEmail(authRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден."));
        return new AuthResponseDto(jwtService.generateToken(user));
    }

    private User toUser(RegisterRequestDto registerRequestDto, Role role) {
         return new User()
                .setEmail(registerRequestDto.getEmail())
                .setPassword(passwordEncoder.encode(registerRequestDto.getPassword()))
                .setIsEnabled(true)
                .setRole(role);
    }
}
