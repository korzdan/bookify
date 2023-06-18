package by.bookify.auth.service;

import by.bookify.auth.model.AuthRequestDto;
import by.bookify.auth.model.AuthResponseDto;
import by.bookify.auth.model.RegisterRequestDto;
import by.bookify.user.model.Role;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto userRegisterRequestDto, Role role);

    AuthResponseDto login(AuthRequestDto authRequestDto);
}
