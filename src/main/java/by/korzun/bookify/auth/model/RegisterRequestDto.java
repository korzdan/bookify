package by.korzun.bookify.auth.model;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String email;
    private String password;
}
