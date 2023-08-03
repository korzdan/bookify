package by.bookify.auth.service;


import by.bookify.domain.auth.dto.AuthRequestDto;
import by.bookify.domain.auth.dto.AuthResponseDto;
import by.bookify.domain.auth.dto.RegisterRequestDto;
import by.bookify.domain.user.model.Role;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto userRegisterRequestDto, Role role);

    AuthResponseDto login(AuthRequestDto authRequestDto);
}
