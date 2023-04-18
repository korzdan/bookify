package by.korzun.bookify.auth.service;

import by.korzun.bookify.auth.model.AuthRequestDto;
import by.korzun.bookify.auth.model.AuthResponseDto;
import by.korzun.bookify.auth.model.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto registerUser(RegisterRequestDto userRegisterRequestDto);
    AuthResponseDto registerAdmin(RegisterRequestDto registerRequestDto);
    AuthResponseDto registerSuperAdmin(RegisterRequestDto registerRequestDto);
    AuthResponseDto authenticate(AuthRequestDto authRequestDto);
}
