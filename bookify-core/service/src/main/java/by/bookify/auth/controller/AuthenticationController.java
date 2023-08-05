package by.bookify.auth.controller;

import by.bookify.auth.service.AuthService;
import by.bookify.domain.auth.dto.AuthRequestDto;
import by.bookify.domain.auth.dto.AuthResponseDto;
import by.bookify.domain.auth.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static by.bookify.domain.user.model.Role.ROLE_ADMIN;
import static by.bookify.domain.user.model.Role.ROLE_USER;


@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.register(registerRequestDto, ROLE_USER));
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponseDto> registerAdmin(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.register(registerRequestDto, ROLE_ADMIN));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authService.login(authRequestDto));
    }
}
