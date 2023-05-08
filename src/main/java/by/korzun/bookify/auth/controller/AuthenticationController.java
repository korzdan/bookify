package by.korzun.bookify.auth.controller;

import by.korzun.bookify.auth.model.AuthRequestDto;
import by.korzun.bookify.auth.model.AuthResponseDto;
import by.korzun.bookify.auth.model.RegisterRequestDto;
import by.korzun.bookify.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.registerUser(registerRequestDto));
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AuthResponseDto> registerAdmin(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.registerAdmin(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authService.authenticate(authRequestDto));
    }
}
